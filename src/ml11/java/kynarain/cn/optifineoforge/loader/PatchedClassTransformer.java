/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Handle;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

import cpw.mods.modlauncher.api.IModuleLayerManager;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformer.Target;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;

/**
 * Puts OptiFine's own compilation of a game class in place of NeoForge's.
 *
 * <p>This exists because of a dead end worth writing down. On these lines OptiFine's patches are
 * deltas against the <em>obfuscated</em> game jar, and its runtime transformer applies them by asking
 * for the obfuscated base class as a resource - which NeoForge's runtime, being officially named,
 * cannot provide. Every single target fails:</p>
 *
 * <pre>java.io.IOException: Base resource not found: fcn.class
 *   at optifine.Patcher.applyPatch(Patcher.java:148)
 *   at optifine.OptiFineTransformer.getOptiFineResourcePatched(OptiFineTransformer.java:441)</pre>
 *
 * <p>So the patching is done offline instead, where the obfuscated jar still exists, and the finished
 * classes travel in this jar. They cannot simply be flattened into it at their real paths - that makes
 * this jar's module export packages the {@code minecraft} module already exports, and the module layer
 * refuses to resolve it:</p>
 *
 * <pre>java.lang.module.ResolutionException: Modules srg and minecraft export package
 *   net.minecraft.client.renderer.block to module mixinsynthetic</pre>
 *
 * <p>Hence the storage path: the finished classes live under {@code optifineoforge/patched/}, whose
 * entries claim no game package, and this transformer puts their content into the class the game asked
 * for. {@code optifineoforge/patched-index.txt} lists which ones there are, because a ModLauncher
 * transformer has to declare its targets before it is ever called.</p>
 *
 * <p>Only the members and the type hierarchy are taken from OptiFine's version; the class's own name
 * and version stay as the game asked for them. What NeoForge added to the class and OptiFine's
 * compilation lacks is put back by {@link MemberRestoreTransformer}, which is why this transformer has
 * to run first.</p>
 */
public final class PatchedClassTransformer implements ITransformer<ClassNode> {
	private static final Logger LOGGER = LogManager.getLogger("OptifiNeoforge");
	/** Where the finished classes are stored, so that no game package is claimed by this module. */
	static final String PREFIX = "/optifineoforge/patched/";
	private static final String INDEX = "/optifineoforge/patched-index.txt";

	/**
	 * The members a runtime class has to be given while it loads, produced by the offline stub pass.
	 *
	 * <p>These are the references nothing can satisfy: not the runtime, not the payload. When the owner is
	 * a payload class the add can happen offline, and the file is then empty for it; when the owner is a
	 * runtime class - OptiFine's call into a method only its own copy of the class declares - it has to
	 * happen here, on the class as it is defined.</p>
	 */
	private static final String STUBS = "/optifineoforge/stubs.txt";

	/** Owner internal name to the members it needs, in order. */
	private static final Map<String, List<String[]>> STUBS_BY_OWNER = loadStubs();

	private static Map<String, List<String[]>> loadStubs() {
		Map<String, List<String[]>> result = new LinkedHashMap<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(STUBS)) {
			if(stream == null) {
				return Map.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String[] parts = line.split("\t");
				if(parts.length == 3) {
					result.computeIfAbsent(parts[0], key -> new ArrayList<>()).add(new String[] {parts[1], parts[2]});
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + STUBS + ": " + e);
		}
		LOGGER.info("Runtime stubs to add: " + result.values().stream().mapToInt(List::size).sum()
				+ " members across " + result.size() + " classes");
		return result;
	}

	/** Adds the stubs listed for this class, if it is one of them and lacks them. */
	private static void stubMissing(ClassNode input) {
		List<String[]> wanted = STUBS_BY_OWNER.get(input.name);
		if(wanted == null) {
			return;
		}
		for(String[] member : wanted) {
			if(hasMethod(input, member[0], member[1])) {
				continue;
			}
			input.methods.add(defaultBody(member[0], member[1], (input.access & Opcodes.ACC_INTERFACE) != 0));
			LOGGER.info("Stubbed " + input.name.replace('/', '.') + "." + member[0] + member[1]);
		}
	}

	/**
	 * Rewrites the payload's superclass onto the runtime's, when the build planned that for this class,
	 * and returns {@code null} on success or the reason it must not be done.
	 *
	 * <p>Three things have to hold, and each one is checked rather than assumed. The payload's superclass
	 * has to be one of the Forge types this jar supplies - a shim, with no state and no superclass of its
	 * own - because only then is discarding it lossless. The class has to be on the build's plan, which is
	 * where the runtime superclass was inspected: it must have a constructor a subclass may chain to, and
	 * the loader cannot look at a game class to find that out. And the class body must not name the old
	 * superclass anywhere except in those constructor calls: a {@code super.something()} call site names
	 * its owner, and once the class no longer extends that type the call cannot resolve.</p>
	 *
	 * <p>Measured on 1.21.1, the shape this exists for is exactly one class -
	 * {@code BlockEntity extends CapabilityProvider<BlockEntity>}, whose constructor does
	 * {@code aload_0; ldc class BlockEntity; invokespecial CapabilityProvider.<init>(Ljava/lang/Class;)V}
	 * and whose body names the old superclass nowhere else. The Forge superclass stores a reference to
	 * the object for its capability lookups; NeoForge's {@code AttachmentHolder} keeps its map on the
	 * object itself, so dropping the argument is the correct translation, not a shortcut. It matters
	 * that the class is not simply left alone: OptiFine's compilation of it carries its own state
	 * ({@code nbtTag}, {@code nbtTagUpdateMs}) and {@code requestModelDataUpdate()}, which the runtime's
	 * version has no reason to have.</p>
	 */
	private static String reparent(ClassNode patched, ClassNode runtime) {
		String[] planned = REPARENTS_BY_CLASS.get(patched.name);
		if(planned == null) {
			return null;
		}
		String plannedSuper = planned[0];
		String plannedCtor = planned[1];
		String forgeSuper = patched.superName;
		if(forgeSuper == null || !forgeSuper.startsWith("net/minecraftforge/")) {
			return "the plan says to re-parent it but its copy extends " + forgeSuper + ", not a Forge type";
		}
		// The plan was made from the same jars, so a disagreement means the plan and the runtime are not
		// from the same build: the runtime's class has to be the one the plan was measured against. A null
		// reference means the caller established that what it holds is not the runtime's copy at all - see
		// the caller's note - and then there is nothing here to disagree with.
		if(runtime != null && !plannedSuper.equals(runtime.superName)) {
			return "the plan re-parents it onto " + plannedSuper + " while the runtime's copy extends "
					+ runtime.superName + ", so the plan and the runtime do not match";
		}
		// The shim has to be a root, or it is not one of ours to discard.
		boolean ours = PatchedClassTransformer.class.getResource("/" + forgeSuper + ".class") != null;
		String shimSuper = ownSuperName(forgeSuper);
		if(!ours || (shimSuper != null && !"java/lang/Object".equals(shimSuper))) {
			return "the payload's copy of it extends " + forgeSuper + ", which this jar does not supply as "
					+ "a shim of its own";
		}
		int rewritten = 0;
		for(MethodNode method : patched.methods) {
			if(!"<init>".equals(method.name)) {
				continue;
			}
			for(AbstractInsnNode instruction : method.instructions) {
				if(!(instruction instanceof MethodInsnNode call) || call.getOpcode() != Opcodes.INVOKESPECIAL
						|| !forgeSuper.equals(call.owner) || !"<init>".equals(call.name)) {
					continue;
				}
				if("()V".equals(plannedCtor)) {
					// The arguments are already on the stack, and the runtime's superclass takes none:
					// they are discarded rather than the pushes being deleted, which keeps whatever the
					// class evaluates for them evaluated exactly as OptiFine wrote it.
					Type[] arguments = Type.getArgumentTypes(call.desc);
					for(int index = arguments.length - 1; index >= 0; index--) {
						method.instructions.insertBefore(call,
								new InsnNode(arguments[index].getSize() == 2 ? Opcodes.POP2 : Opcodes.POP));
					}
				} else if(!plannedCtor.equals(call.desc)) {
					return "the plan calls " + plannedCtor + " on the runtime's superclass while the payload's "
							+ "constructor calls " + call.desc + " on the Forge one, so the arguments do not "
							+ "line up";
				}
				call.owner = plannedSuper;
				call.desc = plannedCtor;
				call.itf = false;
				rewritten++;
			}
		}
		if(rewritten == 0) {
			return "the payload's copy of it extends " + forgeSuper + " and no constructor of it chains to "
					+ "that superclass, so the hierarchy cannot be rewritten";
		}
		String survivor = namesOldSuper(patched, forgeSuper);
		if(survivor != null) {
			return "the payload's copy of it extends " + forgeSuper + " and its body still names that type ("
					+ survivor + "), which would not resolve afterwards";
		}
		patched.superName = plannedSuper;
		if(patched.signature != null && patched.signature.contains(forgeSuper)) {
			// The generic signature names the superclass it was compiled against; a stale one is not a
			// verification problem, only a lie to anything that reads it, so it goes.
			patched.signature = null;
		}
		LOGGER.info("Re-parented " + patched.name.replace('/', '.') + " from the Forge type " + forgeSuper
				+ " onto the runtime's " + plannedSuper + " (super(" + plannedCtor + "), " + rewritten
				+ " constructor call(s) rewritten)");
		return null;
	}

	/**
	 * The re-parent plan, {@code class name to the runtime superclass and the constructor to call},
	 * produced by the build's {@code HierarchyPlan} step. Written there and not decided here because the
	 * decision needs the runtime superclass, which a transformer cannot look at: it holds two copies of
	 * one class, never the class above them.
	 */
	private static final String REPARENTS = "/optifineoforge/reparent.txt";

	private static final Map<String, String[]> REPARENTS_BY_CLASS = loadReparents();

	private static Map<String, String[]> loadReparents() {
		Map<String, String[]> result = new LinkedHashMap<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(REPARENTS)) {
			if(stream == null) {
				// Lines whose payload already speaks NeoForge's hierarchy - 1.20.1, where the Forge
				// package is the runtime's own - have nothing to move and no plan.
				LOGGER.info("No " + REPARENTS + " in this jar; no class has its hierarchy rewritten");
				return Map.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String[] parts = line.split("\t");
				// Four fields, because the constructor is part of the decision: a runtime superclass may
				// want the argument the payload passes rather than none at all.
				if(parts.length == 4 && "reparent".equals(parts[0])) {
					result.put(parts[1], new String[] {parts[2], parts[3]});
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + REPARENTS + ": " + e);
		}
		LOGGER.info("Hierarchy rewrites planned: " + result.size());
		return Map.copyOf(result);
	}

	/**
	 * The first place a class still names a type, as a description, or null when it names it nowhere.
	 *
	 * <p>Instruction operands and the class's own metadata are both searched: a member reference names
	 * its owner, a type instruction names a class, and a local variable table or an annotation can name
	 * either. Strings are deliberately not searched - OptiFine has string constants holding class names
	 * it looks up itself, and those are not affected by a rewrite of the hierarchy.</p>
	 */
	private static String namesOldSuper(ClassNode node, String internalName) {
		String fieldType = "L" + internalName + ";";
		for(MethodNode method : node.methods) {
			for(AbstractInsnNode instruction : method.instructions) {
				if(instruction instanceof MethodInsnNode call && (internalName.equals(call.owner)
						|| call.desc.contains(fieldType))) {
					return "a call to " + call.name + call.desc;
				}
				if(instruction instanceof FieldInsnNode field && (internalName.equals(field.owner)
						|| field.desc.contains(fieldType))) {
					return "a read of " + field.name;
				}
				if(instruction instanceof TypeInsnNode type && internalName.equals(type.desc)) {
					return "a " + type.getOpcode() + " of the type";
				}
			}
		}
		return null;
	}

	/** Null-safe class-name comparison: a class with no superclass equals only another such class. */
	private static boolean sameName(String left, String right) {
		return left == null ? right == null : left.equals(right);
	}

	/**
	 * Why the payload's copy of a class must not be installed, or {@code null} when it may be.
	 *
	 * <p>The question is not whether the two copies name the same superclass but whether the payload's
	 * hierarchy, as it will really be loaded, still reaches the type the runtime's callers were compiled
	 * against. Keeping {@code java.lang.Object} needs nothing: every class reaches it. Anything else has
	 * to be found on the payload's own chain, and a Forge API type on that chain is supplied by this jar
	 * as a shim - {@code /net/minecraftforge/...} below - whose own superclass the build re-parents, so
	 * the walk sees the type the game will actually see.
	 *
	 * <p>The walk is deliberately limited to this jar's resources. Asking a class loader for a game class
	 * from inside a transformer is the one thing that reliably produces an unusable failure, and no
	 * shim's superclass is a game class anyway.
	 */
	private static String hierarchyProblem(String payloadSuper, String runtimeSuper) {
		if(sameName(payloadSuper, runtimeSuper)) {
			return null;
		}
		if(runtimeSuper == null || "java/lang/Object".equals(runtimeSuper)) {
			return null;
		}
		String current = payloadSuper;
		for(int hops = 0; hops < 16 && current != null; hops++) {
			if(current.equals(runtimeSuper)) {
				return null;
			}
			current = ownSuperName(current);
		}
		return "the payload's copy of it extends " + payloadSuper + " and that chain does not reach "
				+ runtimeSuper + ", which the runtime's version extends";
	}

	/** The superclass of a class this jar ships outside the payload tree, or null if it ships none. */
	private static String ownSuperName(String internalName) {
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream("/" + internalName + ".class")) {
			return stream == null ? null : new ClassReader(stream.readAllBytes()).getSuperName();
		} catch(IOException e) {
			return null;
		}
	}

	private static boolean hasMethod(ClassNode node, String name, String desc) {
		for(MethodNode method : node.methods) {
			if(method.name.equals(name) && method.desc.equals(desc)) {
				return true;
			}
		}
		return false;
	}

	/** A method that returns the default value for its return type; a default method in an interface. */
	private static MethodNode defaultBody(String name, String desc, boolean isInterface) {
		MethodNode method = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_SYNTHETIC, name, desc, null, null);
		org.objectweb.asm.Type returnType = org.objectweb.asm.Type.getReturnType(desc);
		switch(returnType.getSort()) {
			case org.objectweb.asm.Type.VOID -> method.instructions.add(new InsnNode(Opcodes.RETURN));
			case org.objectweb.asm.Type.BOOLEAN, org.objectweb.asm.Type.BYTE, org.objectweb.asm.Type.CHAR,
					org.objectweb.asm.Type.SHORT, org.objectweb.asm.Type.INT -> {
				method.instructions.add(new InsnNode(Opcodes.ICONST_0));
				method.instructions.add(new InsnNode(Opcodes.IRETURN));
			}
			case org.objectweb.asm.Type.LONG -> {
				method.instructions.add(new InsnNode(Opcodes.LCONST_0));
				method.instructions.add(new InsnNode(Opcodes.LRETURN));
			}
			case org.objectweb.asm.Type.FLOAT -> {
				method.instructions.add(new InsnNode(Opcodes.FCONST_0));
				method.instructions.add(new InsnNode(Opcodes.FRETURN));
			}
			case org.objectweb.asm.Type.DOUBLE -> {
				method.instructions.add(new InsnNode(Opcodes.DCONST_0));
				method.instructions.add(new InsnNode(Opcodes.DRETURN));
			}
			default -> {
				method.instructions.add(new InsnNode(Opcodes.ACONST_NULL));
				method.instructions.add(new InsnNode(Opcodes.ARETURN));
			}
		}
		return method;
	}

	/** The three access bits that say who may use a class; everything else in the word is not visibility. */
	private static final int VISIBILITY = Opcodes.ACC_PUBLIC | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED;

	private static final Set<String> TARGETS = loadTargets();

	/**
	 * Every class this transformer has to be called for, which is not the same as every class it swaps.
	 *
	 * <p>The index alone is not enough, and the gap was measured rather than reasoned about. ModLauncher
	 * calls a transformer only for the classes it declares, so a class that is not in the payload was never
	 * offered here - and that silently disabled the other two things this transformer does to a class it
	 * does <em>not</em> swap: the runtime interfaces from the interface plan, which exist precisely for
	 * classes that arrive stripped of them, and the runtime stubs, whose owners are runtime classes by
	 * definition. On 1.21.8 the stub file's own deferred entry names a class that is in no payload at all.
	 * So the set is the union of every plan's owners.</p>
	 */
	private static Set<String> loadTargets() {
		Set<String> targets = new HashSet<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(INDEX)) {
			if(stream == null) {
				// Lines where OptiFine already speaks the runtime's names need none of this, and the
				// index is simply absent there.
				LOGGER.info("No " + INDEX + " in this jar; no classes will be swapped in");
			} else {
				for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
					String name = line.trim();
					if(name.isEmpty()) {
						continue;
					}
					// Accept both the bare class name and the entry path: the index first shipped as entry
					// paths, and the target factory then received 'Foo.class' as a class name, so no target
					// ever matched and the transformer was silently never called.
					if(name.endsWith(".class")) {
						name = name.substring(0, name.length() - ".class".length());
					}
					targets.add(name.replace('/', '.'));
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + INDEX + ": " + e);
		}
		int swapped = targets.size();
		addFirstField(targets, KEEP_RUNTIME);
		addFirstField(targets, RUNTIME_INTERFACES);
		addFirstField(targets, STUBS);
		LOGGER.info("Patched-class targets: " + swapped + " to swap, " + targets.size() + " in all");
		return Set.copyOf(targets);
	}

	/** Adds the owner column of a tab separated plan file, dotted, to {@code targets}. */
	private static void addFirstField(Set<String> targets, String resource) {
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(resource)) {
			if(stream == null) {
				return;
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String text = line.trim();
				if(text.isEmpty() || text.startsWith("#")) {
					continue;
				}
				String owner = text.split("\t")[0].trim();
				if(!owner.isEmpty()) {
					targets.add(owner.replace('/', '.'));
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + resource + ": " + e);
		}
	}

	/**
	 * Gives a class the loader keeps the members the payload declares and this copy does not have.
	 *
	 * <p>The mirror of the member restore, and it exists because keeping a class is not the same as leaving
	 * it alone: the payload's <em>callers</em> are still installed, and they call whatever their own
	 * compilation saw. Measured on 1.21, where two decisions meet:</p>
	 *
	 * <pre>SpriteLoader is replaced by the payload
	 * SpriteResourceLoader is left as the runtime has it (the interface rule above)
	 *   -> NoSuchMethodError: SpriteResourceLoader.create(java.util.Collection)
	 *      at SpriteLoader.loadAndStitch(SpriteLoader.java:187)</pre>
	 *
	 * <p>{@code MissingTargets} cannot report this by construction: it indexes the payload as well as the
	 * runtime, on the reasoning that a swapped class is what will be loaded - and the whole point here is
	 * that this class is not swapped. So the members come from the payload's own copy, which this
	 * transformer already holds.</p>
	 *
	 * <p>Interface fields are normalised rather than copied, for the reason recorded on the swap path: the
	 * JVM rejects any other modifier combination outright
	 * ({@code ClassFormatError: Illegal field modifiers in class ...: 0x9}). A method with a body copied
	 * into an interface needs no change - a non-abstract, non-static interface method is a default method
	 * by definition.</p>
	 */
	private static void addPayloadMembers(ClassNode input, ClassNode patched) {
		if(patched == null) {
			return;
		}
		boolean isInterface = (input.access & Opcodes.ACC_INTERFACE) != 0;
		int fields = 0;
		int methods = 0;
		for(FieldNode field : patched.fields) {
			if(hasField(input, field.name, field.desc)) {
				continue;
			}
			int access = field.access;
			if(isInterface) {
				access = (access & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED))
						| Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL;
			}
			input.fields.add(new FieldNode(access, field.name, field.desc, field.signature, field.value));
			fields++;
		}
		for(MethodNode method : patched.methods) {
			if("<init>".equals(method.name) || "<clinit>".equals(method.name)) {
				continue;
			}
			if(hasMethod(input, method.name, method.desc)) {
				continue;
			}
			MethodNode copy = new MethodNode(method.access, method.name, method.desc, method.signature,
					method.exceptions == null ? null : method.exceptions.toArray(new String[0]));
			method.accept(copy);
			input.methods.add(copy);
			methods++;
		}
		if(fields > 0 || methods > 0) {
			LOGGER.info("Gave " + input.name.replace('/', '.') + " the payload's " + fields + " field(s) and "
					+ methods + " method(s), because the payload's own callers are still installed");
		}
	}

	private static boolean hasField(ClassNode node, String name, String desc) {
		for(FieldNode field : node.fields) {
			if(field.name.equals(name) && field.desc.equals(desc)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		ClassNode result = decide(input, context);
		renameSrgMembers(result);
		dump(result);
		return result;
	}

	/**
	 * The SRG names the loader has to rewrite while transforming, {@code owner name official}.
	 *
	 * <p>Rewriting the jars cannot do this, and that is measured rather than assumed. On a line whose
	 * OptiFine build predates OptiFine's switch to official member names, OptiFine's transformation service
	 * patches classes <em>at load time</em> from the {@code patch/srg/*.xdelta} data inside its jar - binary
	 * deltas, which rewriting the jar's {@code .class} entries cannot reach. Its output therefore still
	 * carries SRG names, and the loader is the only place left to fix them. On 1.21 the class the JVM
	 * verified contained</p>
	 *
	 * <pre>net/minecraft/server/packs/resources/Resource
	 *   .m_215509_()Lnet/minecraft/server/packs/resources/ResourceMetadata;</pre>
	 *
	 * <p>while no entry of the shipped loader jar contained that string at all; the run died with
	 * {@code NoSuchMethodError: 'ResourceMetadata Resource.m_215509_()'} and the sound engine never started.
	 * {@code SrgNameTable} writes this table offline, limited to the owners the payload's own classes
	 * declare or reference - which is what keeps it a few hundred kilobytes instead of the whole mapping.</p>
	 */
	private static final String SRG_TABLE = "/optifineoforge/srg-to-official.txt";

	/** The shape of an SRG member name, the same one the offline tools look for. */
	private static final java.util.regex.Pattern SRG_NAME = java.util.regex.Pattern.compile("[fm]_\\d+_");

	/** {@code owner} to its {@code srg} to {@code official} member names. */
	private static final Map<String, Map<String, String>> SRG_NAMES = loadSrgNames();

	private static Map<String, Map<String, String>> loadSrgNames() {
		Map<String, Map<String, String>> result = new LinkedHashMap<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(SRG_TABLE)) {
			if(stream == null) {
				return Map.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String text = line.trim();
				if(text.isEmpty() || text.startsWith("#")) {
					continue;
				}
				String[] parts = text.split("\t");
				if(parts.length != 3) {
					continue;
				}
				result.computeIfAbsent(parts[0], key -> new LinkedHashMap<>()).put(parts[1], parts[2]);
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + SRG_TABLE + ": " + e);
		}
		int names = result.values().stream().mapToInt(Map::size).sum();
		if(names > 0) {
			LOGGER.info("SRG names to rewrite while transforming: " + names + " across " + result.size()
					+ " owner(s)");
		}
		return Map.copyOf(result);
	}

	/** The official name for an SRG-shaped member of {@code owner}, or null when the table has none. */
	private static String officialName(String owner, String name) {
		if(!SRG_NAME.matcher(name).matches()) {
			return null;
		}
		Map<String, String> names = SRG_NAMES.get(owner);
		return names == null ? null : names.get(name);
	}

	/**
	 * Rewrites the SRG-shaped member names a class declares and references, where the table knows them.
	 *
	 * <p>Only names of that shape are touched, and only where the table answers for the owner, so this is
	 * inert on every line whose OptiFine already speaks official names - all of them but 1.21.</p>
	 */
	private static void renameSrgMembers(ClassNode node) {
		if(SRG_NAMES.isEmpty() || node == null || !("false".equals(System.getProperty("optifineoforge.renameSrg")))) {
			return;
		}
		int renamed = 0;
		// References only, never declarations, and that is a measured correction rather than caution. With
		// declarations renamed as well, 1.21 went from 299 [OptiFine] lines at the title screen to 0 and died
		// inside OptiFine's own Reflector.<clinit>: OptiFine's classes name their own members in the same
		// m_/f_ shape this table uses, so rewriting a declaration rewrites OptiFine's own name for it. What
		// its patch data gets wrong is what it CALLS, and those members are declared by the runtime classes
		// the table describes - so only the call sites are touched.
		for(MethodNode method : node.methods) {
			if(method.instructions == null) {
				continue;
			}
			for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
				if(insn instanceof MethodInsnNode call) {
					String official = officialName(call.owner, call.name);
					if(official != null) {
						call.name = official;
						renamed++;
					}
				} else if(insn instanceof FieldInsnNode fieldInsn) {
					String official = officialName(fieldInsn.owner, fieldInsn.name);
					if(official != null) {
						fieldInsn.name = official;
						renamed++;
					}
				} else if(insn instanceof InvokeDynamicInsnNode dynamic) {
					// A lambda's target travels as a handle in the bootstrap arguments, so a name there
					// needs the same treatment as a call instruction - and a lambda body is exactly where
					// 1.21's failure came from.
					for(int index = 0; index < dynamic.bsmArgs.length; index++) {
						if(dynamic.bsmArgs[index] instanceof Handle handle
								&& SRG_NAME.matcher(handle.getName()).matches()) {
							String official = officialName(handle.getOwner(), handle.getName());
							if(official != null) {
								dynamic.bsmArgs[index] = new Handle(handle.getTag(), handle.getOwner(), official,
										handle.getDesc(), handle.isInterface());
								renamed++;
							}
						}
					}
				}
			}
		}
		if(renamed > 0) {
			LOGGER.info("Rewrote " + renamed + " SRG name(s) in " + node.name.replace('/', '.')
					+ ": OptiFine's patch data emits them");
		}
	}

	/**
	 * Writes the class this transformer hands back, when {@code -Doptifineoforge.dump=<dir>} is set.
	 *
	 * <p>Added because reading the jars on disk stopped being enough: on 1.21 a run failed with
	 * {@code NoSuchMethodError: Resource.m_215509_()} thrown from
	 * {@code SpriteResourceLoader.lambda$create$0}, and a byte scan of both shipped jars found the string
	 * {@code m_215509_} in neither - so the offending copy is made at load time, somewhere between the jar
	 * and the class the JVM verifies. Guessing which step did it was a mistake worth not repeating; this
	 * answers it by writing the final bytes out.</p>
	 */
	private static void dump(ClassNode node) {
		String directory = System.getProperty("optifineoforge.dump");
		if(directory == null || node == null || node.name == null) {
			return;
		}
		try {
			Path target = Path.of(directory, node.name + ".class");
			Files.createDirectories(target.getParent());
			ClassWriter writer = new ClassWriter(0);
			node.accept(writer);
			Files.write(target, writer.toByteArray());
		} catch(Exception e) {
			LOGGER.warn("could not dump " + node.name + ": " + e);
		}
	}

	private ClassNode decide(ClassNode input, ITransformerVotingContext context) {
		logModulesOnce();
		// Stubs first and unconditionally, because some belong to runtime classes that are never swapped -
		// the case that used to fall through the gap. OptiFine's GameRenderer calls
		// LoadingOverlay.update(), a method only OptiFine's own LoadingOverlay declares, so leaving the
		// overlay unswapped to avoid its clash with NeoForge's loading screen broke the pair:
		//   NoSuchMethodError: 'void net.minecraft.client.gui.screens.LoadingOverlay.update()'
		// Giving the runtime's overlay that one method keeps both halves working.
		stubMissing(input);
		// And the runtime interfaces, before anything decides whether this class is swapped at all. They
		// cannot be read off the class being handed over, and that is measured rather than cautious: on
		// 1.21.8 OptiFine's transformation service registers before this one, its patch of a class is its
		// own compilation of that class, and its compilation never heard of NeoForge's extension
		// interfaces - so a class arriving here has lost them whether or not this transformer replaces it.
		// What that cost, on the line it was measured: the runtime's own ModelWrapper calls
		// UnbakedGeometry.bake(..., ContextMap), a method only UnbakedGeometryExtension declares.
		injectPlannedInterfaces(input);
		ClassNode patched;
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(PREFIX + input.name + ".class")) {
			if(stream == null) {
				return input;
			}
			patched = new ClassNode();
			new ClassReader(stream.readAllBytes()).accept(patched, 0);
		} catch(IOException e) {
			LOGGER.warn("could not read the patched " + input.name + ": " + e);
			return input;
		}

		// The keep plan's blunt form, and it comes before every other decision about this class for that
		// reason: a class listed here cannot be repaired member by member, so nothing below should be
		// reached for it. See KEEP_RUNTIME_CLASSES for the measurement that put ModelDiscovery$ModelWrapper
		// on the list. The runtime stubs above have already been applied, and they must be - the payload is
		// not installed, but this class as the runtime has it is still the one OptiFine's classes call.
		if(KEEP_RUNTIME_CLASSES.contains(input.name)) {
			LOGGER.info("Left " + input.name.replace('/', '.') + " alone: the keep plan keeps this runtime's"
					+ " copy whole, because a constant the payload inlined into its own bodies does not match"
					+ " the one here");
			addPayloadMembers(input, patched);
			return input;
		}

		// A payload class is only a patch of this one if it really is the same class, and the superclass
		// is where that assumption is checked. Two different things make the two copies disagree.
		//
		// Numbered nested names: those numbers are assigned by whoever built the artefact, and the two
		// artefacts here were built differently - OptiFine's payload takes its names from the obfuscated
		// jar, the runtime's from NeoForm. Measured on 1.20.1: the runtime's net.minecraft.Util$9 extends
		// java.lang.Thread, while the payload's Util$9 is the BiFunction cache class behind Util.memoize.
		// Installing it broke the class that uses it:
		//
		//   VerifyError: Bad type on operand stack
		//     Location: net/minecraft/Util.m_137584_()V @13: invokevirtual
		//     Reason: Type 'net/minecraft/Util$9' is not assignable to 'java/lang/Thread'
		//
		// NeoForge's own hierarchy changes: on a Forge-targeted line the payload extends the Forge type
		// OptiFine was compiled against, while the runtime class extends the NeoForge type that replaced
		// it. Measured on 1.21.1 - payload BlockEntity extends
		// net.minecraftforge.common.capabilities.CapabilityProvider, runtime BlockEntity extends
		// net.neoforged.neoforge.attachment.AttachmentHolder - and the swap then fails far from here:
		//
		//   VerifyError: Bad type on operand stack
		//     Location: net/neoforged/neoforge/attachment/AttachmentSync.onChunkSent(...) @82: invokestatic
		//     Reason: Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
		//             'net/neoforged/neoforge/attachment/AttachmentHolder'
		//
		// The second case is repaired here, on the class being swapped, and where the repair happens is
		// the whole lesson: Forge's API types travel with this jar as shims, so the obvious fix looks
		// like re-parenting the shim - make CapabilityProvider extend AttachmentHolder - and it cannot
		// work. This jar's module sits *below* the game layer, and a module reads the layers under it,
		// not the ones above: the shim's own superclass then fails to resolve,
		//
		//   NoClassDefFoundError: net/neoforged/neoforge/attachment/AttachmentHolder
		//     at cpw.mods.cl.ModuleClassLoader.loadFromModule(ModuleClassLoader.java:311)
		//
		// while the game layer reads this one without any help (the same log line says
		// "optifine -> minecraft: reads it = false" and the swap still resolves every shim it names).
		// So the payload's superclass is rewritten onto the runtime's instead, and {@link #reparent}
		// does that, including the constructor's super call.
		// A class the plan means to move is attempted even when the two supers already agree, because
		// agreement does not mean the reference is the runtime's copy. Where OptiFine's own transformer has
		// already replaced the class, what arrives here carries the Forge superclass too - measured on
		// 1.21.8, where the guard that used to be here skipped the whole block on that basis and left
		// BlockEntity on a Forge superclass, so NeoForge's own code failed verification against it:
		//
		//   VerifyError: Bad type on operand stack
		//     Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
		//     'net/neoforged/neoforge/attachment/AttachmentHolder'
		//
		// HierarchyPlan measured the plan from these same jars, so when the reference cannot be used the
		// plan is applied on its own measurement. The reference is unusable exactly when it already extends
		// the Forge type the payload extends, which is the only way the two can agree while the runtime's
		// own copy does not, and each outcome is logged rather than inferred from two silences.
		boolean planned = REPARENTS_BY_CLASS.containsKey(input.name);
		boolean referenceIsRuntime = !planned || !sameName(patched.superName, input.superName);
		if(planned) {
			LOGGER.info("Reparent plan covers " + input.name.replace('/', '.') + ": the payload extends "
					+ patched.superName + ", the class handed over extends " + input.superName
					+ (referenceIsRuntime
							? " - so the plan is checked against that copy"
							: " - equal, so that copy is not the runtime's and the plan is applied on its own"
									+ " measurement"));
		}
		if(planned || !referenceIsRuntime) {
			String problem = reparent(patched, referenceIsRuntime ? input : null);
			if(problem == null && referenceIsRuntime) {
				problem = hierarchyProblem(patched.superName, input.superName);
			}
			if(problem != null) {
				LOGGER.info("Left " + input.name.replace('/', '.') + " alone: " + problem);
				return input;
			}
		}

		// An interface that the runtime adds members to is left as the runtime has it, and this is a
		// measured rule rather than caution. An interface carries no constructor, so the only place a
		// static field of it can be assigned is its static initialiser - and installing OptiFine's copy
		// replaces that initialiser with one that knows nothing about NeoForge's fields. Measured on
		// 1.21.8, where the runtime's BlockStateModel$Unbaked declares SINGLE_MODEL_CODEC and
		// WEIGHTED_MODEL_CODEC that NeoForge's own BlockStateModelHooks reads while registering block
		// state models. The plan restores the declarations so that the members exist, but the donor of an
		// interface carries no initialiser to restore the values with, and what is left is a field the
		// runtime would have filled and that nothing fills here.
		//
		// There is also a blunt form of the same failure, which is how this was found rather than
		// predicted: the donors are written as plain classes, so a restored interface field arrives with
		// the class's modifiers -
		//
		//   java.lang.ClassFormatError: Illegal field modifiers in class BlockStateModel$Unbaked: 0x9
		//
		// 0x9 being public static with no final, which no interface field may be. Normalising the
		// modifiers would have made the class loadable and left it broken in the way described above, so
		// the class is not installed at all.
		if((patched.access & Opcodes.ACC_INTERFACE) != 0 && RESTORED_CLASSES.contains(patched.name)) {
			LOGGER.info("Left " + patched.name.replace('/', '.') + " alone: the runtime adds members to that "
					+ "interface, and installing OptiFine's copy would replace the static initialiser that "
					+ "fills them");
			addPayloadMembers(input, patched);
			return input;
		}

		// Content in place rather than returning OptiFine's node: the transformers after this one in
		// the chain, ours included, are handed the same node and expect the class they were told about.
		input.superName = patched.superName;
		// The access flags matter as much as the members, and taking only part of the word was the mistake.
		// Forcing the visibility bits fixed "cannot access its superinterface" - OptiFine makes
		// OptionInstance$SliderableValueSet public so that its own class may implement it - and the very
		// next launch failed the mirror image: "cannot inherit from final class OptionInstance", because
		// OptiFine's patch also drops final so that SliderPercentageOptionOF may extend it. OptiFine's
		// compilation is the version of this class for this runtime, so its whole access word wins.
		input.access = patched.access;
		// Interfaces are additive, not a replacement. OptiFine's compilation of an interface extends the
		// Forge extension interface while the runtime's extends NeoForge's own, and the two carry
		// different inherited members - so replacing the list outright removed a route the runtime's own
		// callers were compiled against:
		//   runtime ModelBaker extends net.neoforged.neoforge.client.extensions.IModelBakerExtension
		//   OptiFine ModelBaker extends net.minecraftforge.client.extensions.IForgeModelBaker
		//   NoSuchMethodError: 'BakedModel ModelBaker.bake(ResourceLocation, ModelState, Function)'
		// Keeping both lets either route resolve.
		//
		// The same holds for a class, and there the runtime's interfaces are not a detail: NeoForge
		// patches a game class to implement its extension interface and its own code then casts to it,
		// so a swapped class that drops the interface dies at the first cast instead:
		//   runtime BlockEntity implements net.neoforged.neoforge.common.extensions.IBlockEntityExtension
		//   payload BlockEntity implements net.minecraftforge.common.extensions.IForgeBlockEntity
		//   ClassCastException: BlockEntity cannot be cast to IBlockEntityExtension
		// An interface added this way obliges the class to implement its abstract methods, and this is
		// why the addition is safe anyway: an abstract method of an injected interface is implemented by
		// the runtime class OptiFine compiled instead of, so it is a member of the runtime class that
		// the payload lacks - exactly what MemberRestorePlan restores from the donor classes. Measured
		// for the one abstract method IBlockEntityExtension declares, getPersistentData(): the plan for
		// this line restores BlockEntity.customPersistentData, the field NeoForge's implementation reads.
		List<String> kept = new ArrayList<>(patched.interfaces == null ? List.<String>of() : patched.interfaces);
		int injected = 0;
		for(String name : input.interfaces) {
			if(!kept.contains(name)) {
				kept.add(name);
				injected++;
			}
		}
		// The plan's interfaces are already on `input` - injectPlannedInterfaces ran before the payload was
		// even looked at, and it has to, because the class that needs them is not always a swapped one. The
		// loop above therefore keeps them without knowing it, which is the point: one place decides, and
		// the swap cannot undo it.
		input.interfaces = kept;
		if((patched.access & Opcodes.ACC_INTERFACE) == 0 && injected > 0) {
			LOGGER.info("Kept the runtime's " + injected + " interface(s) on " + input.name.replace('/', '.')
					+ ": " + patched.interfaces.size() + " from the payload, " + kept.size() + " in place");
		}
		input.signature = patched.signature;
		// Members are not the same story as the class. Taking OptiFine's word for the class flags is
		// right - it changes them on purpose - but for a member the game's own copy may have been widened
		// by an access transformer, and NeoForge's code then relies on that. Replacing RenderType wholesale
		// made NeoForgeRenderTypes\$Internal fail:
		//   IllegalAccessError: ... tried to access method 'RenderType.create(...)'
		// OptiFine's compilation has create() narrower than NeoForge's widened version, so each member
		// keeps whichever of the two visibilities is wider.
		Map<String, Integer> wasMethods = visibilityOf(input.methods);
		Map<String, Integer> wasFields = visibilityOfFields(input.fields);
		// Snapshotted before the swap: a few members must keep the game's body, and by the time the swap is
		// done the originals are only reachable through this list.
		List<MethodNode> originalMethods = new ArrayList<>(input.methods);
		List<FieldNode> fields = new ArrayList<>(patched.fields);
		List<MethodNode> methods = new ArrayList<>(patched.methods);
		for(FieldNode field : fields) {
			Integer wasField = wasFields.get(field.name + field.desc);
			int mergedField = wider(field.access, wasField);
			if((input.access & Opcodes.ACC_INTERFACE) != 0) {
				// An interface's fields have to be public static final whatever either copy says, and the
				// JVM rejects the class outright otherwise. Measured, back when final was cleared for
				// every field: "ClassFormatError: Illegal field modifiers in class
				// com/mojang/blaze3d/vertex/VertexConsumer: 0x9", where 0x9 is public final.
				mergedField = (mergedField & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED))
						| Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL;
			} else if(wasField == null || (wasField & Opcodes.ACC_FINAL) == 0) {
				// final is not inherited from the payload, and this is the narrow form of a rule that was
				// wrong twice before it was right. NeoForge's access transformers strip final from fields
				// its own subclasses assign, and then a swapped class carrying the payload's final bit
				// rejects that assignment:
				//
				//   IllegalAccessError: Update to non-static final field
				//     net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState.f_110131_
				//     attempted from a different class
				//
				// That class extends RenderStateShard$TextureStateShard - measured with javap - so the
				// field is inherited from a class OptiFine does patch, and the write is NeoForge's own
				// constructor. static is deliberately left as the payload has it: taking it from the
				// runtime as well produced the VertexFormat failure above.
				mergedField &= ~Opcodes.ACC_FINAL;
			}
			field.access = mergedField;
		}
		for(MethodNode method : methods) {
			method.access = wider(method.access, wasMethods.get(method.name + method.desc));
		}
		// And the runtime's own widened word, which the two lines above cannot see when OptiFine replaced
		// the class before this transformer ran. See RUNTIME_ACCESS.
		int floored = 0;
		for(FieldNode field : fields) {
			Integer floorWord = runtimeAccessOf(input.name, field.name, field.desc);
			if(floorWord != null) {
				int before = field.access;
				field.access = wider(field.access, floorWord);
				if(field.access != before) {
					floored++;
				}
			}
		}
		for(MethodNode method : methods) {
			Integer floorWord = runtimeAccessOf(input.name, method.name, method.desc);
			if(floorWord != null) {
				int before = method.access;
				method.access = wider(method.access, floorWord);
				if(method.access != before) {
					floored++;
				}
			}
		}
		if(floored > 0) {
			LOGGER.info("Widened " + floored + " member(s) of " + input.name.replace('/', '.')
					+ " to the runtime's access from the plan");
		}
		input.fields = fields;
		input.methods = methods;
		keepRuntimeBodies(input, originalMethods);
		LOGGER.info("Replaced " + input.name.replace('/', '.') + " with OptiFine's patched version ("
				+ fields.size() + " fields, " + methods.size() + " methods)");
		return input;
	}

	/**
	 * The classes the member restore plan has something to put back into, read from the same file the
	 * transformer that does the restoring reads. Only the owner of each line matters here.
	 */
	private static final String MEMBER_RESTORES = "/optifineoforge/member-restores.txt";

	private static final Set<String> RESTORED_CLASSES = loadRestoredClasses();

	private static Set<String> loadRestoredClasses() {		Set<String> result = new HashSet<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(MEMBER_RESTORES)) {
			if(stream == null) {
				return Set.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String[] parts = line.trim().split(" ", 4);
				if(parts.length >= 4) {
					result.add(parts[1]);
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + MEMBER_RESTORES + ": " + e);
		}
		LOGGER.info("Classes with members to restore: " + result.size());
		return Set.copyOf(result);
	}

	/** Adds the interfaces the plan requires of {@code input}, and says so when it changes anything. */
	private static void injectPlannedInterfaces(ClassNode input) {
		List<String> wanted = RUNTIME_INTERFACES_BY_CLASS.get(input.name);
		if(wanted == null) {
			return;
		}
		List<String> interfaces = input.interfaces == null ? new ArrayList<>() : new ArrayList<>(input.interfaces);
		int added = 0;
		for(String name : wanted) {
			if(!interfaces.contains(name)) {
				interfaces.add(name);
				added++;
			}
		}
		if(added > 0) {
			input.interfaces = interfaces;
			LOGGER.info("Injected " + added + " runtime interface(s) on " + input.name.replace('/', '.')
					+ " from the interface plan: " + wanted);
		}
	}

	private static boolean loggedModules;

	/**
	 * Members whose runtime copy this line's access transformers widened, {@code owner name desc access}.
	 *
	 * <p>The visibility rule in {@link #transform} takes the wider of the payload's own member and of the
	 * one on the class it was handed - and on a line where OptiFine's transformer replaced that class
	 * first, both of those are the payload's. The runtime's widened copy is then nowhere in reach, and
	 * NeoForge's own code, which was compiled against the wider one, fails at the first access. Measured on
	 * 1.21:</p>
	 *
	 * <pre>IllegalAccessError: class NeoForgeRenderTypes$Internal tried to access protected field
	 *   RenderStateShard.RENDERTYPE_ENTITY_SOLID_SHADER</pre>
	 *
	 * <p>{@code PayloadDrift} writes this list from the runtime jar, offline, which is the only place that
	 * copy is available. The field is a floor, not a replacement: the payload's own flags still win where
	 * they are wider.</p>
	 */
	private static final String RUNTIME_ACCESS = "/optifineoforge/runtime-access.txt";

	/** {@code owner|name|desc} to the access word the runtime's copy carries. */
	private static final Map<String, Integer> RUNTIME_ACCESS_BY_MEMBER = loadRuntimeAccess();

	private static Map<String, Integer> loadRuntimeAccess() {
		Map<String, Integer> result = new LinkedHashMap<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(RUNTIME_ACCESS)) {
			if(stream == null) {
				return Map.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String text = line.trim();
				if(text.isEmpty() || text.startsWith("#")) {
					continue;
				}
				String[] parts = text.split("\t");
				if(parts.length != 4) {
					LOGGER.warn("Ignoring a " + RUNTIME_ACCESS + " line that is not 'owner name desc"
							+ " access': " + text);
					continue;
				}
				try {
					result.put(parts[0] + "|" + parts[1] + "|" + parts[2], Integer.parseInt(parts[3].trim()));
				} catch(NumberFormatException e) {
					LOGGER.warn("Ignoring an access word that is not a number: " + text);
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + RUNTIME_ACCESS + ": " + e);
		}
		if(!result.isEmpty()) {
			LOGGER.info("Runtime access from the plan: " + result.size() + " member(s)");
		}
		return Map.copyOf(result);
	}

	/** The plan's access word for this member, or null when it is not listed. */
	private static Integer runtimeAccessOf(String owner, String name, String desc) {
		return RUNTIME_ACCESS_BY_MEMBER.get(owner + "|" + name + "|" + desc);
	}

	/**
	 * The interfaces each swapped class must implement, taken from the runtime's copy of it rather than
	 * from the class this transformer is handed.
	 *
	 * <p>One line per interface, {@code owner<TAB>interface}, both internal names; {@code #} comments and
	 * blank lines are ignored. Written by {@code PayloadDrift}, which reads it straight out of the runtime
	 * jar. See the injection site in {@link #transform} for why the class handed over is not a safe source
	 * for this list - it is the measured reason a class the runtime's own code casts to an extension
	 * interface ended up not implementing it.</p>
	 */
	private static final String RUNTIME_INTERFACES = "/optifineoforge/runtime-interfaces.txt";

	/** {@code owner} to the runtime interfaces that must be present on it. */
	private static final Map<String, List<String>> RUNTIME_INTERFACES_BY_CLASS = loadRuntimeInterfaces();

	private static Map<String, List<String>> loadRuntimeInterfaces() {
		Map<String, List<String>> result = new LinkedHashMap<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(RUNTIME_INTERFACES)) {
			if(stream == null) {
				return Map.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String text = line.trim();
				if(text.isEmpty() || text.startsWith("#")) {
					continue;
				}
				String[] parts = text.split("\t");
				if(parts.length != 2) {
					LOGGER.warn("Ignoring a " + RUNTIME_INTERFACES + " line that is not 'owner interface': "
							+ text);
					continue;
				}
				result.computeIfAbsent(parts[0].trim(), key -> new ArrayList<>()).add(parts[1].trim());
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + RUNTIME_INTERFACES + ": " + e);
		}
		int count = result.values().stream().mapToInt(List::size).sum();
		if(count > 0) {
			LOGGER.info("Runtime interfaces from the plan: " + count + " across " + result.size()
					+ " class(es)");
		}
		return Map.copyOf(result);
	}

	/**
	 * Members whose bodies must stay the game's own, even though OptiFine's copy declares them too.
	 *
	 * <p>The mirror of the stub list. Some of OptiFine's methods assume a partner it does not ship: its
	 * {@code ModelPart.getChild(String)} looks each child up by {@code child.getId()} - an id held in a
	 * field OptiFine added - and that id is only ever set by OptiFine's own compilation of the baking code.
	 * OptiFine patches no {@code PartDefinition} at all, so on this line the runtime bakes the parts, no ids
	 * are ever set, every lookup returns null, and the first model that asks for a child dies with
	 * "this.head is null" while {@code minecraft:skull} is being built. Keeping the game's plain
	 * {@code children.get(name)} is the repair, and it is enough: the callers only want the child.</p>
	 *
	 * <p>Two line forms, tab separated: {@code owner name desc} keeps one method's body,
	 * {@code owner *} keeps the whole class. Lines starting with {@code #} are comments. An owner is an
	 * internal name, with slashes. The file is optional and absent on every line with nothing to say.</p>
	 */
	private static final String KEEP_RUNTIME = "/optifineoforge/keep-runtime.txt";

	/** Two forms of the same decision: {@code owner|name|desc} per member, and whole classes by name. */
	private record KeepPlan(Set<String> members, Set<String> classes) {
	}

	private static final KeepPlan KEEP_PLAN = loadKeepRuntime();

	/** {@code owner|name|desc} for each member that keeps the game's body. */
	private static final Set<String> KEEP_RUNTIME_MEMBERS = KEEP_PLAN.members();

	/**
	 * Classes the plan keeps whole: the runtime's copy is installed and the payload's is dropped.
	 *
	 * <p>The member form above repairs one body. This form exists for a class where no member-level repair
	 * can work, and the mechanism that puts it out of reach is a constant <em>inlined</em> into the
	 * payload's bytecode. Measured on 1.21.8, {@code ModelDiscovery$ModelWrapper}: the payload carries
	 * {@code private static final int SLOT_COUNT = 7} and the runtime 8, because NeoForge adds an eighth
	 * slot to the seven vanilla has ({@code KEY_ADDITIONAL_PROPERTIES}, read by
	 * {@code getTopAdditionalProperties()}). {@code SLOT_COUNT} is inlined into {@code slot(int)}, which
	 * guards with {@code Objects.checkIndex(index, SLOT_COUNT)}, so the restored field's own initialiser -
	 * the plan restores {@code KEY_ADDITIONAL_PROPERTIES = slot(7)}, inlined into the class's static
	 * initialiser - dies inside the class it was restored into:</p>
	 *
	 * <pre>ExceptionInInitializerError
	 *   at ModelDiscovery$ModelWrapper.slot(ModelDiscovery.java:212)   &lt;- the payload's, bound 7
	 *   at ModelDiscovery$ModelWrapper.&lt;clinit&gt;(ModelDiscovery.java:200)</pre>
	 *
	 * <p>and the same 7 sizes the class's {@code AtomicReferenceArray fixedSlots}, so the restored slot has
	 * nowhere to live even once the guard is repaired. The two copies cannot be reconciled member by member;
	 * the runtime's class is the one NeoForge's own callers are compiled against, so it is the one that is
	 * loaded. What the payload adds to it - {@code context} and {@code getContext()} - is dropped with it,
	 * and that is measured to cost nothing: a scan of all 2578 payload classes found that pair referenced
	 * only inside that class, while the runtime reaches the same behaviour through
	 * {@code UnbakedGeometryExtension.bake(..., ContextMap)}, which is the route the rest of the game calls.
	 * The keep plan is written by {@code PayloadDrift}, which reports this disagreement offline.</p>
	 */
	private static final Set<String> KEEP_RUNTIME_CLASSES = KEEP_PLAN.classes();

	private static KeepPlan loadKeepRuntime() {
		Set<String> members = new HashSet<>();
		Set<String> classes = new HashSet<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(KEEP_RUNTIME)) {
			if(stream == null) {
				return new KeepPlan(Set.of(), Set.of());
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String text = line.trim();
				if(text.isEmpty() || text.startsWith("#")) {
					continue;
				}
				String[] parts = text.split("\t");
				if(parts.length == 3) {
					members.add(parts[0] + "|" + parts[1] + "|" + parts[2]);
				} else if(parts.length == 2 && "*".equals(parts[1].trim())) {
					classes.add(parts[0].trim());
				} else {
					LOGGER.warn("Ignoring a " + KEEP_RUNTIME + " line that is neither 'owner name desc' nor"
							+ " 'owner *': " + text);
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + KEEP_RUNTIME + ": " + e);
		}
		if(!members.isEmpty()) {
			LOGGER.info("Members keeping the game's body: " + members.size());
		}
		if(!classes.isEmpty()) {
			LOGGER.info("Classes keeping the game's copy whole: " + classes.size());
		}
		return new KeepPlan(Set.copyOf(members), Set.copyOf(classes));
	}

	/** Puts the game's own version of the listed members back over the swapped-in ones. */
	private static void keepRuntimeBodies(ClassNode input, List<MethodNode> originals) {
		if(KEEP_RUNTIME_MEMBERS.isEmpty()) {
			return;
		}
		for(MethodNode original : originals) {
			if(!KEEP_RUNTIME_MEMBERS.contains(input.name + "|" + original.name + "|" + original.desc)) {
				continue;
			}
			for(int index = 0; index < input.methods.size(); index++) {
				MethodNode candidate = input.methods.get(index);
				if(candidate.name.equals(original.name) && candidate.desc.equals(original.desc)) {
					input.methods.set(index, original);
					LOGGER.info("Kept the game's body of " + input.name.replace('/', '.') + "."
							+ original.name + original.desc);
					break;
				}
			}
		}
	}

	/** Visibility of each declared method, keyed by name and descriptor, for the wider-of-two rule. */
	private static Map<String, Integer> visibilityOf(List<MethodNode> methods) {
		Map<String, Integer> result = new LinkedHashMap<>();
		for(MethodNode method : methods) {
			result.putIfAbsent(method.name + method.desc, method.access);
		}
		return result;
	}

	private static Map<String, Integer> visibilityOfFields(List<FieldNode> fields) {
		Map<String, Integer> result = new LinkedHashMap<>();
		for(FieldNode field : fields) {
			result.putIfAbsent(field.name + field.desc, field.access);
		}
		return result;
	}

	/** {@code access}, with its visibility replaced by the wider of its own and {@code other}'s. */
	private static int wider(int access, Integer other) {
		if(other == null) {
			return access;
		}
		int mine = rank(access);
		int theirs = rank(other);
		if(theirs <= mine) {
			return access;
		}
		return (access & ~VISIBILITY) | (other & VISIBILITY);
	}

	/** public 3, protected 2, package 1, private 0 - what "wider" means for the merge above. */
	private static int rank(int access) {
		if((access & Opcodes.ACC_PUBLIC) != 0) {
			return 3;
		}
		if((access & Opcodes.ACC_PROTECTED) != 0) {
			return 2;
		}
		return (access & Opcodes.ACC_PRIVATE) != 0 ? 0 : 1;
	}

	/**
	 * Prints the real module graph once, the first time a class is transformed.
	 *
	 * <p>Added because the access failure was being reasoned about backwards, from an error message:
	 * {@code IllegalAccessError: class net.optifine.config.SliderableValueSetInt cannot access its
	 * superinterface net.minecraft.client.OptionInstance$SliderableValueSet (鈥?is in module srg 鈥? 鈥?is
	 * in module minecraft@1.20.4 鈥?}. Reading the graph says directly whether the game's package is not
	 * exported to us, or whether we cannot read the game module at all - two different repairs.</p>
	 *
	 * <p>Only module metadata is touched: no game class is loaded, which would be a bad thing to do from
	 * inside a transformer.</p>
	 */
	private static void logModulesOnce() {
		if(loggedModules) {
			return;
		}
		loggedModules = true;
		try {
			Module ours = PatchedClassTransformer.class.getModule();
			IModuleLayerManager layers = OptifiNeoforgeTransformationService.layers();
			LOGGER.info("module graph: our module is " + ours.getName() + ", layer manager "
					+ (layers == null ? "not captured" : "captured"));
			if(layers == null) {
				return;
			}
			layers.getLayer(IModuleLayerManager.Layer.GAME).ifPresent(layer -> {
				StringBuilder names = new StringBuilder();
				for(Module module : layer.modules()) {
					names.append(module.getName()).append(' ');
				}
				LOGGER.info("module graph: GAME layer holds " + names.toString().trim());
				// Which layer holds us, and which holds the payload, is the question the Reflector
				// defect turns on: a module name can exist in more than one layer, and the layer whose
				// reads matter is the one that owns the class doing the reflecting. ModLauncher 11 has
				// four layers (BOOT / SERVICE / PLUGIN / GAME) and mod files live in PLUGIN.
				for(IModuleLayerManager.Layer which : IModuleLayerManager.Layer.values()) {
					layers.getLayer(which).ifPresent(other -> {
						StringBuilder otherNames = new StringBuilder();
						for(Module module : other.modules()) {
							otherNames.append(module.getName())
									.append(module.getPackages().contains("net.optifine.reflect") ? "(PAYLOAD)" : "")
									.append(' ');
						}
						LOGGER.info("module graph: " + which + " layer holds " + otherNames.toString().trim());
					});
				}
				layer.findModule("minecraft").ifPresent(game -> {
					// Both edges matter and they are granted separately, which is the whole reason this
					// probe exists: an export to a module that cannot read the game is worth nothing, and
					// the first measurement showed exactly that shape - the export went to 'optifine'
					// while the payload actually lives in 'srg'.
					for(String candidate : new String[] {"optifine", "srg", "neoforge"}) {
						layer.findModule(candidate).ifPresent(module -> {
							LOGGER.info(
								"module graph: " + candidate + " -> minecraft: reads it = " + module.canRead(game)
										+ ", net.minecraft.client is exported to it = "
										+ game.isExported("net.minecraft.client", module)
										+ ", net.minecraft.resources is exported to it = "
										+ game.isExported("net.minecraft.resources", module));
							// The Reflector defect, measured instead of guessed. OptiFine's Reflector
							// reflects method signatures and the JVM then has to load the types it finds
							// - and four of those loads fail with ClassNotFoundException even though the
							// classes plainly exist, which is what these four packages are: the exact
							// ones named by the failing reflectors on this family of lines
							// (BlockState, PoseStack, BlockEntityWithoutLevelRenderer, ItemStack).
							// Metadata only: nothing is loaded, so this cannot itself change the run.
							for(String pkg : new String[] {"net.minecraft.world.level.block.state",
									"com.mojang.blaze3d.vertex", "net.minecraft.client.renderer",
									"net.minecraft.world.item", "net.optifine.reflect"}) {
								LOGGER.info("module graph: package " + pkg + " : owned by minecraft = "
										+ game.getPackages().contains(pkg)
										+ ", exported to " + candidate + " = " + game.isExported(pkg, module)
										+ ", owned by " + candidate + " = " + module.getPackages().contains(pkg));
							}
						});
					}
				});
			});
		} catch(Throwable t) {
			LOGGER.warn("module probe failed: " + t);
		}
	}


	@Override
	public Set<Target<ClassNode>> targets() {
		Set<Target<ClassNode>> result = new java.util.LinkedHashSet<>();
		for(String name : TARGETS) {
			result.add(Target.targetClass(name));
		}
		return result;
	}

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		// Unconditional, as on the 1.20.x line: the class is corrected because this line needs it
		// corrected, not because of what another mod did to it.
		return TransformerVoteResult.YES;
	}

	@Override
	public TargetType<ClassNode> getTargetType() {
		return TargetType.CLASS;
	}
}

