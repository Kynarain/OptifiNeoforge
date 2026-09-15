/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import cpw.mods.modlauncher.api.IModuleLayerManager;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
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

	private static final Set<Target> TARGETS = loadTargets();

	private static Set<Target> loadTargets() {
		Set<Target> targets = new HashSet<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(INDEX)) {
			if(stream == null) {
				// Lines where OptiFine already speaks the runtime's names need none of this, and the
				// index is simply absent there.
				LOGGER.info("No " + INDEX + " in this jar; no classes will be swapped in");
				return Set.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String name = line.trim();
				if(name.isEmpty()) {
					continue;
				}
				// Accept both the bare class name and the entry path: the index first shipped as entry
				// paths, and Target.targetClass then received 'Foo.class' as a class name, so no target
				// ever matched and the transformer was silently never called.
				if(name.endsWith(".class")) {
					name = name.substring(0, name.length() - ".class".length());
				}
				targets.add(Target.targetClass(name.replace('/', '.')));
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + INDEX + ": " + e);
		}
		LOGGER.info("Patched-class targets: " + targets.size());
		return Set.copyOf(targets);
	}

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		logModulesOnce();
		// Stubs first and unconditionally, because some belong to runtime classes that are never swapped -
		// the case that used to fall through the gap. OptiFine's GameRenderer calls
		// LoadingOverlay.update(), a method only OptiFine's own LoadingOverlay declares, so leaving the
		// overlay unswapped to avoid its clash with NeoForge's loading screen broke the pair:
		//   NoSuchMethodError: 'void net.minecraft.client.gui.screens.LoadingOverlay.update()'
		// Giving the runtime's overlay that one method keeps both halves working.
		stubMissing(input);
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
		// Keeping both lets either route resolve. Only for interfaces: adding a superinterface to a class
		// obliges that class to implement its abstract methods, which its swapped body may not have.
		if((patched.access & Opcodes.ACC_INTERFACE) != 0) {
			List<String> merged = new ArrayList<>(patched.interfaces == null ? List.<String>of() : patched.interfaces);
			for(String name : input.interfaces) {
				if(!merged.contains(name)) {
					merged.add(name);
				}
			}
			input.interfaces = merged;
		} else {
			input.interfaces = patched.interfaces == null ? new ArrayList<>() : new ArrayList<>(patched.interfaces);
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
			field.access = wider(field.access, wasFields.get(field.name + field.desc));
		}
		for(MethodNode method : methods) {
			method.access = wider(method.access, wasMethods.get(method.name + method.desc));
		}
		input.fields = fields;
		input.methods = methods;
		keepRuntimeBodies(input, originalMethods);
		LOGGER.info("Replaced " + input.name.replace('/', '.') + " with OptiFine's patched version ("
				+ fields.size() + " fields, " + methods.size() + " methods)");
		return input;
	}

	private static boolean loggedModules;

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
	 */
	private static final String KEEP_RUNTIME = "/optifineoforge/keep-runtime.txt";

	/** {@code owner|name|desc} for each member that keeps the game's body. */
	private static final Set<String> KEEP_RUNTIME_MEMBERS = loadKeepRuntime();

	private static Set<String> loadKeepRuntime() {
		Set<String> result = new HashSet<>();
		try(InputStream stream = PatchedClassTransformer.class.getResourceAsStream(KEEP_RUNTIME)) {
			if(stream == null) {
				return Set.of();
			}
			for(String line : new String(stream.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
				String[] parts = line.split("\t");
				if(parts.length == 3) {
					result.add(parts[0] + "|" + parts[1] + "|" + parts[2]);
				}
			}
		} catch(IOException e) {
			LOGGER.warn("could not read " + KEEP_RUNTIME + ": " + e);
		}
		if(!result.isEmpty()) {
			LOGGER.info("Members keeping the game's body: " + result.size());
		}
		return Set.copyOf(result);
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
				layer.findModule("minecraft").ifPresent(game -> {
					// Both edges matter and they are granted separately, which is the whole reason this
					// probe exists: an export to a module that cannot read the game is worth nothing, and
					// the first measurement showed exactly that shape - the export went to 'optifine'
					// while the payload actually lives in 'srg'.
					for(String candidate : new String[] {"optifine", "srg", "neoforge"}) {
						layer.findModule(candidate).ifPresent(module -> LOGGER.info(
								"module graph: " + candidate + " -> minecraft: reads it = " + module.canRead(game)
										+ ", net.minecraft.client is exported to it = "
										+ game.isExported("net.minecraft.client", module)
										+ ", net.minecraft.resources is exported to it = "
										+ game.isExported("net.minecraft.resources", module)));
					}
				});
			});
		} catch(Throwable t) {
			LOGGER.warn("module probe failed: " + t);
		}
	}

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public Set<Target> targets() {
		return TARGETS;
	}
}

