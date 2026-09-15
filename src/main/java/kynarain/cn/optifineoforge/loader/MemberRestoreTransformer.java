/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import kynarain.cn.optifineoforge.optifine.MemberRestorePlan;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;

/**
 * Puts back, in bulk and with their original bodies, the members OptiFine's replacements drop.
 *
 * <p>Every class OptiFine replaces is one it compiled itself, against Forge, and whatever NeoForge
 * added to that class since is gone. {@code optifine.MemberRestorePlan} works out which members
 * those are offline, by comparing the classes the patcher produces with the classes the game has,
 * and writes a <em>donor</em> class per affected class: a class file carrying exactly those members,
 * fields with their declarations and methods with their original instructions. They travel inside
 * this jar under {@code optifineoforge/donors/} and are copied in here, as the class is transformed.</p>
 *
 * <p>The first version of this transformer stubbed the methods instead - returning the default value
 * for the return type - and that turned out to be the wrong repair for anything on the render path:
 * NeoForge backs up and restores GL state around its loading overlay, and a {@code backupGlState}
 * that does nothing leaves the game in a state where the overlay's own buffer builder is still
 * "building" from the previous frame and the launch dies with {@code Already building}. Copying the
 * original body is the same repair without the guesswork; where a copied body names something
 * OptiFine renamed, that shows up as a single missing member and can be dealt with on its own.</p>
 */
public final class MemberRestoreTransformer implements ITransformer<ClassNode> {
	private static final Logger LOGGER = LogManager.getLogger("OptifiNeoforge");
	/** The list of classes to act on, as written by the offline generator. */
	private static final String PLAN_RESOURCE = "/optifineoforge/member-restores.txt";
	/** Where the donor classes live, one per class, named after it. */
	private static final String DONOR_ROOT = "/optifineoforge/donors/";

	/** The classes that have something to restore. */
	private final Set<String> targets = new LinkedHashSet<>();

	public MemberRestoreTransformer() {
		int members = 0;
		try(InputStream stream = MemberRestoreTransformer.class.getResourceAsStream(PLAN_RESOURCE)) {
			if(stream == null) {
				LOGGER.warn("No member restore plan in this jar (" + PLAN_RESOURCE + "); nothing will be restored");
				return;
			}
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
				String line;
				while((line = reader.readLine()) != null) {
					String[] parts = line.trim().split(" ", 4);
					if(parts.length < 4) {
						continue;
					}
					if(EXACT.contains(parts[1] + " " + parts[2] + " " + parts[3])) {
						continue;
					}
					targets.add(parts[1]);
					members++;
				}
			}
		} catch(IOException e) {
			LOGGER.error("Could not read the member restore plan", e);
			return;
		}
		LOGGER.info("Member restore plan: " + members + " members across " + targets.size() + " classes");
	}

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		if(!targets.contains(input.name)) {
			return input;
		}
		ClassNode donor = donor(input.name);
		if(donor == null) {
			LOGGER.warn("No donor class for " + input.name + "; its dropped members stay missing");
			return input;
		}

		int restored = 0;
		for(FieldNode field : donor.fields) {
			if(!hasField(input, field.name, field.desc)) {
				input.fields.add(new FieldNode(field.access, field.name, field.desc, field.signature, field.value));
				restored++;
			}
		}
		List<String> initialisers = new ArrayList<>();
		List<String> staticInitialisers = new ArrayList<>();
		boolean isInterface = (input.access & Opcodes.ACC_INTERFACE) != 0;
		for(MethodNode method : donor.methods) {
			if(!hasMethod(input, method.name, method.desc)) {
				MethodNode copy = new MethodNode(method.access, method.name, method.desc, method.signature,
						method.exceptions == null ? null : method.exceptions.toArray(new String[0]));
				method.accept(copy);
				input.methods.add(copy);
				restored++;
			}
			if(method.name.startsWith(MemberRestorePlan.INITIALISER_PREFIX)) {
				// Two shapes: an instance initialiser takes the object, a static one takes nothing and
				// belongs in the class's static initialiser instead of in every constructor.
				if(isInterface) {
					// An interface is the one class these must not be called in, and the launch that
					// proved it failed twice over. Its own static initialiser already fills its fields,
					// so the call is pointless; a call written as a class method reference is rejected
					// outright - "Method 'void BlockStateModel$Unbaked.optifineoforge$init$...()' must be
					// InterfaceMethodref constant" - and one written correctly would be worse, because
					// an interface's fields are final and assigning one from another method is rejected
					// in turn. So no initialiser is called for an interface; the methods stay in the
					// donor and are simply not used.
					continue;
				}
				if("()V".equals(method.desc)) {
					staticInitialisers.add(method.name);
				} else {
					initialisers.add(method.name);
				}
			}
		}
		if(!staticInitialisers.isEmpty()) {
			// The payload's own static initialiser runs first - it is the class's - and these calls go
			// after it, at the end, so that NeoForge's fields end up with NeoForge's values rather than
			// the defaults the payload's initialiser knows nothing about. A class with no static
			// initialiser at all gets one, because OptiFine's copy of a class that NeoForge added
			// static state to may well have none.
			MethodNode clinit = null;
			for(MethodNode method : input.methods) {
				if("<clinit>".equals(method.name)) {
					clinit = method;
					break;
				}
			}
			InsnList calls = new InsnList();
			for(String name : staticInitialisers) {
				calls.add(new MethodInsnNode(Opcodes.INVOKESTATIC, input.name, name, "()V", false));
			}
			// The owner is a class here, and only a class: an interface was sent back before this
			// point, because a static call on an interface has to be an InterfaceMethodref and the
			// assignment it would make is illegal there anyway.
			if(clinit == null) {
				clinit = new MethodNode(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
				clinit.instructions.add(calls);
				clinit.instructions.add(new InsnNode(Opcodes.RETURN));
				clinit.maxStack = 1;
				clinit.maxLocals = 0;
				input.methods.add(clinit);
			} else {
				AbstractInsnNode last = null;
				for(AbstractInsnNode insn = clinit.instructions.getFirst(); insn != null; insn = insn.getNext()) {
					if(insn.getOpcode() == Opcodes.RETURN) {
						last = insn;
					}
				}
				if(last == null) {
					LOGGER.warn("No return in the static initialiser of " + input.name
							+ "; " + staticInitialisers.size() + " restored fields stay at their defaults");
				} else {
					clinit.instructions.insertBefore(last, calls);
					clinit.maxStack = Math.max(clinit.maxStack, 1);
				}
			}
			LOGGER.info("Initialised " + staticInitialisers.size() + " restored static fields in " + input.name);
		}
		if(!initialisers.isEmpty()) {
			// A restored field needs the assignment NeoForge's own class would have made; the donor
			// carries that sequence as a static method, and every constructor calls it once - except
			// for the fields that constructor assigns itself. A restored constructor does exactly
			// that, and calling the initialiser there afterwards would overwrite the value it just
			// stored with the initialiser's default.
			for(MethodNode constructor : input.methods) {
				if(!"<init>".equals(constructor.name) || constructor.instructions == null) {
					continue;
				}
				Set<String> assigned = assignedFields(constructor, input.name);
				List<String> wanted = new ArrayList<>();
				for(String name : initialisers) {
					String field = name.substring(MemberRestorePlan.INITIALISER_PREFIX.length());
					if(!assigned.contains(field)) {
						wanted.add(name);
					}
				}
				if(wanted.isEmpty()) {
					continue;
				}
				for(AbstractInsnNode insn = constructor.instructions.getFirst(); insn != null; insn = insn.getNext()) {
					if(insn.getOpcode() != Opcodes.RETURN) {
						continue;
					}
					InsnList call = new InsnList();
					call.add(new VarInsnNode(Opcodes.ALOAD, 0));
					for(String name : wanted) {
						call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, input.name, name, "(L" + input.name + ";)V", false));
					}
					constructor.instructions.insertBefore(insn, call);
				}
				constructor.maxStack = Math.max(constructor.maxStack, 1);
				constructor.maxLocals = Math.max(constructor.maxLocals, 1);
			}
			LOGGER.info("Initialised " + initialisers.size() + " restored fields in " + input.name);
		}
		if(restored > 0) {
			LOGGER.info("Restored " + restored + " members in " + input.name + " from its donor");
		}
		repairSpriteCollection(input);
		return input;
	}

	/**
	 * Puts OptiFine's model-sprite collection back on the call path NeoForge replaced it on.
	 *
	 * <p>This is the mirror image of the restore above, and it belongs in the same pass because of the
	 * order it needs: it has to run on the class <em>after</em> the runtime's members are back, so that
	 * the repair lands in the method the game actually calls. It began as a transformer of its own and
	 * was silently useless there for a reason worth writing down, because the same mistake is easy to
	 * repeat: {@code ClassNode.name} is the <em>internal</em> name, with slashes, and the check compared
	 * it against the dotted name, so every call returned at the first line and the only evidence was a
	 * log line that never appeared.</p>
	 *
	 * <p>What it repairs: where NeoForge <em>grows a signature</em>, OptiFine's version of the method
	 * keeps its own body and loses the call path, because NeoForge's callers are compiled against the
	 * longer descriptor. Measured on 1.21.8:</p>
	 *
	 * <pre>payload  ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos)
	 *            ... calls CustomItems.collectModelSprites(map)   &lt;- what sets the flag
	 * runtime  ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos,
	 *                                                 StandaloneModelLoader$LoadedModels)
	 *            ... restored, has no such call, and is the one NeoForge calls</pre>
	 *
	 * <p>Nothing fails when that call is missing: {@code CustomItems.registerIcons} waits for the flag
	 * in a loop that sleeps 100 ms and logs every fiftieth turn, so the game reaches its title screen and
	 * then sits there printing</p>
	 *
	 * <pre>[OptiFine] Waiting for model sprites</pre>
	 *
	 * <p>The repair calls what the payload's own version calls, with the argument both signatures share
	 * - the map - and does nothing when the collection is already reached from the method the game uses
	 * or when the first argument is not that map.</p>
	 */
	private static void repairSpriteCollection(ClassNode input) {
		if(!MODEL_MANAGER.equals(input.name)) {
			return;
		}
		MethodNode target = null;
		int widest = -1;
		for(MethodNode method : input.methods) {
			if(!"discoverModelDependencies".equals(method.name) || method.instructions == null) {
				continue;
			}
			int arguments = org.objectweb.asm.Type.getArgumentTypes(method.desc).length;
			if(arguments > widest) {
				target = method;
				widest = arguments;
			}
		}
		if(target == null) {
			LOGGER.warn("No discoverModelDependencies in " + input.name
					+ "; OptiFine's sprite collection stays off the call path");
			return;
		}
		for(AbstractInsnNode instruction : target.instructions) {
			if(instruction instanceof MethodInsnNode call && CUSTOM_ITEMS.equals(call.owner)
					&& COLLECT_SPRITES.equals(call.name)) {
				LOGGER.info("OptiFine's sprite collection is already called by discoverModelDependencies"
						+ target.desc);
				return;
			}
		}
		org.objectweb.asm.Type[] arguments = org.objectweb.asm.Type.getArgumentTypes(target.desc);
		if(arguments.length < 1 || !"Ljava/util/Map;".equals(arguments[0].getDescriptor())) {
			LOGGER.warn("Cannot restore OptiFine's sprite collection: discoverModelDependencies" + target.desc
					+ " does not start with the map it is given");
			return;
		}
		InsnList call = new InsnList();
		call.add(new VarInsnNode(Opcodes.ALOAD, 0));
		call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, CUSTOM_ITEMS, COLLECT_SPRITES, "(Ljava/util/Map;)V", false));
		target.instructions.insert(call);
		target.maxStack = Math.max(target.maxStack, 1);
		LOGGER.info("Restored OptiFine's model sprite collection into " + input.name.replace('/', '.')
				+ ".discoverModelDependencies" + target.desc);
	}

	/** OptiFine's sprite collection, and the class that owns it. */
	private static final String CUSTOM_ITEMS = "net/optifine/CustomItems";
	private static final String COLLECT_SPRITES = "collectModelSprites";
	/** The class whose model discovery is the call path, as the module graph spells it. */
	private static final String MODEL_MANAGER = "net/minecraft/client/resources/model/ModelManager";

	/** The donor class for a target, or {@code null} when the jar has none. */
	private static ClassNode donor(String internalName) {
		String resource = DONOR_ROOT + internalName + ".class";
		try(InputStream stream = MemberRestoreTransformer.class.getResourceAsStream(resource)) {
			if(stream == null) {
				return null;
			}
			ClassNode donor = new ClassNode();
			new ClassReader(stream.readAllBytes()).accept(donor, 0);
			return donor;
		} catch(IOException e) {
			LOGGER.error("Could not read donor " + resource, e);
			return null;
		}
	}

	private static boolean hasField(ClassNode node, String name, String descriptor) {
		for(FieldNode field : node.fields) {
			if(name.equals(field.name) && descriptor.equals(field.desc)) {
				return true;
			}
		}
		return false;
	}

	/** The names of the fields one constructor assigns on its own class. */
	private static Set<String> assignedFields(MethodNode constructor, String owner) {
		Set<String> assigned = new LinkedHashSet<>();
		if(constructor.instructions == null) {
			return assigned;
		}
		for(AbstractInsnNode insn = constructor.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			if(insn instanceof org.objectweb.asm.tree.FieldInsnNode field
					&& field.getOpcode() == Opcodes.PUTFIELD && owner.equals(field.owner)) {
				assigned.add(field.name);
			}
		}
		return assigned;
	}

	private static boolean hasMethod(ClassNode node, String name, String descriptor) {
		for(MethodNode method : node.methods) {
			if(name.equals(method.name) && descriptor.equals(method.desc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Members that must not be restored from a donor because their behaviour is what the caller is
	 * after and the donor would not provide it: these are restored by a fix of their own, which also
	 * keeps the result independent of the order ModLauncher runs the transformers in.
	 */
	private static final Set<String> EXACT = Set.of(
			ReloadableResourceManagerFix.RESOURCE_MANAGER + " getListeners ()Ljava/util/List;",
			ReloadableResourceManagerFix.RESOURCE_MANAGER + " updateListenersFrom (Lnet/neoforged/neoforge/event/SortedReloadListenerEvent;)V");

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public Set<Target<ClassNode>> targets() {
		Set<Target<ClassNode>> result = new LinkedHashSet<>();
		for(String name : targets) {
			result.add(Target.targetClass(name));
		}
		return result;
	}

	@Override
	public TargetType<ClassNode> getTargetType() {
		return TargetType.CLASS;
	}
}
