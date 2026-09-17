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
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import kynarain.cn.optifineoforge.optifine.MemberRestorePlan;


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
public final class MemberRestoreTransformer implements NodeTransformer {
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
	public ClassNode transform(ClassNode input) {
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
			if(method.name.startsWith(MemberRestorePlan.INITIALISER_PREFIX)) {
				// Two shapes: an instance initialiser takes the object, a static one takes nothing and
				// belongs in the class's static initialiser instead of in every constructor. Ported from
				// the 1.21.x line, where both were measured; see docs/MATRIX.md.
				if(isInterface) {
					// An interface is the one class these must not be called in, and the launch that
					// proved it failed twice over. Its own static initialiser already fills its fields,
					// so the call is pointless; a call written as a class method reference is rejected
					// outright - "Method 'void BlockStateModel$Unbaked.optifineoforge$init$...()' must be
					// InterfaceMethodref constant" - and one written correctly would be worse, because
					// an interface's fields are final and assigning one from another method is rejected
					// in turn. So no initialiser is called for an interface; nothing is restored for it
					// here either, since an initialiser that is never called has no purpose.
					continue;
				}
				// An initialiser writes exactly one field, and a class that does not have that field can
				// only fail when the initialiser runs:
				//
				//   NoSuchFieldError: cache
				//     at net.minecraft.Util$9.optifineoforge$init$cache(Util.java)
				//     at net.minecraft.Util$9.<init>(Util.java:663)
				//
				// which is 1.20.2 measured. {@code Util$9} is in the member restore plan because the
				// payload's patched jar has a copy, but the copy that is *installed* is the runtime's -
				// the payload ships none for it (the nested-family pass drops it) - and the runtime's
				// class has no {@code cache}. The declaration is present only when the payload's class is
				// the one in place, so the field is the test: no field, no call, and the reason is logged
				// rather than turned into a crash on the first constructor.
				String absent = absentInitialiserField(method, input);
				if(absent != null) {
					LOGGER.warn("Not restoring " + method.name + " in " + input.name + ": it assigns " + absent
							+ ", which this class does not have, so the payload's copy of the class is not the one"
							+ " in place");
					continue;
				}
				if("()V".equals(method.desc)) {
					// Every static initialiser is taken, including one for a field the class already
					// declares. The guard that used to skip those was written for an IllegalAccessError
					// and caused a worse one: it skipped RenderSystem.PIPELINE_MODIFIERS, which the
					// runtime's own class does not fill in on these lines, and the client died on its
					// first frame with "PIPELINE_MODIFIERS is null".
					staticInitialisers.add(method.name);
				} else {
					initialisers.add(method.name);
				}
			}
			if(!hasMethod(input, method.name, method.desc)) {
				MethodNode copy = new MethodNode(method.access, method.name, method.desc, method.signature,
						method.exceptions == null ? null : method.exceptions.toArray(new String[0]));
				method.accept(copy);
				input.methods.add(copy);
				restored++;
			}
		}
		if(!staticInitialisers.isEmpty()) {
			// The value is *inlined* into the class's own static initialiser rather than called through a
			// separate method, and that is the whole point: a static final field may only be assigned
			// from the class's own <clinit>, so a call to a helper that writes it is rejected with
			//
			//   IllegalAccessError: Update to static final field
			//     ModelDiscovery$ModelWrapper.KEY_ADDITIONAL_PROPERTIES attempted from a different method
			//     than the initializer method
			//
			// and the earlier guard against that was worse than the disease: it skipped every initialiser
			// for a field the class already declared, which left RenderSystem.PIPELINE_MODIFIERS null.
			// Writing the value here is legal whether or not the field is final, whether the class
			// already declares it, and whether the payload's own initialiser touched it - the payload's
			// code runs first, since its instructions are already in place.
			MethodNode clinit = null;
			for(MethodNode method : input.methods) {
				if("<clinit>".equals(method.name)) {
					clinit = method;
					break;
				}
			}
			InsnList values = new InsnList();
			int inlined = 0;
			for(String name : staticInitialisers) {
				for(MethodNode method : input.methods) {
					if(name.equals(method.name) && "()V".equals(method.desc) && method.instructions != null) {
						for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
							if(insn.getOpcode() == Opcodes.RETURN) {
								continue;
							}
							AbstractInsnNode step = copy(insn);
							if(step == null) {
								LOGGER.warn("Cannot inline " + name + " into " + input.name
										+ ": an instruction of kind " + insn.getClass().getSimpleName()
										+ " has no copy here; the field stays at its default");
								inlined = -1;
								break;
							}
							values.add(step);
						}
						if(inlined >= 0) {
							inlined++;
						}
						break;
					}
				}
				if(inlined < 0) {
					break;
				}
			}
			if(inlined > 0) {
				if(clinit == null) {
					clinit = new MethodNode(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
					clinit.instructions.add(values);
					clinit.instructions.add(new InsnNode(Opcodes.RETURN));
					clinit.maxStack = 8;
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
						clinit.instructions.insertBefore(last, values);
						clinit.maxStack = Math.max(clinit.maxStack, 8);
					}
				}
				LOGGER.info("Initialised " + inlined + " restored static fields in " + input.name);
			}
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
		return input;
	}

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

	/**
	 * The field one initialiser assigns that the target class does not declare, or {@code null}.
	 *
	 * <p>Every initialiser the plan writes is a straight-line run ending in one field store, so the
	 * first store in the body is the field it is about. A field is looked up by name and descriptor
	 * only, because that is what the JVM resolves a store against.</p>
	 */
	private static String absentInitialiserField(MethodNode initialiser, ClassNode target) {
		if(initialiser.instructions == null) {
			return null;
		}
		for(AbstractInsnNode insn = initialiser.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			if(insn instanceof FieldInsnNode store && (store.getOpcode() == Opcodes.PUTFIELD
					|| store.getOpcode() == Opcodes.PUTSTATIC)) {
				return hasField(target, store.name, store.desc) ? null
						: store.owner.replace('/', '.') + "." + store.name + " " + store.desc;
			}
		}
		return null;
	}

	/**
	 * A fresh copy of one instruction, or null for a kind the inliner does not carry.
	 *
	 * <p>A copy rather than the node itself, because these instructions are still linked into the donor
	 * method's list and moving them would corrupt it. Only the kinds a value-producing run is made of
	 * are handled: pushing a constant or a static, creating and constructing an object, a cast, and a
	 * call. A jump or a local read never reaches here - the plan does not extract those - so a branch
	 * would mean the extraction changed, and saying so is better than writing something wrong.</p>
	 */
	private static AbstractInsnNode copy(AbstractInsnNode insn) {
		if(insn instanceof InsnNode plain) {
			return new InsnNode(plain.getOpcode());
		}
		if(insn instanceof MethodInsnNode call) {
			return new MethodInsnNode(call.getOpcode(), call.owner, call.name, call.desc, call.itf);
		}
		if(insn instanceof FieldInsnNode field) {
			return new FieldInsnNode(field.getOpcode(), field.owner, field.name, field.desc);
		}
		if(insn instanceof TypeInsnNode type) {
			return new TypeInsnNode(type.getOpcode(), type.desc);
		}
		if(insn instanceof LdcInsnNode ldc) {
			return new LdcInsnNode(ldc.cst);
		}
		if(insn instanceof IntInsnNode integer) {
			return new IntInsnNode(integer.getOpcode(), integer.operand);
		}
		if(insn instanceof InvokeDynamicInsnNode dynamic) {
			// A value built through a lambda is still one expression. The bootstrap method and its
			// arguments are shared rather than copied - they are immutable, and ASM writes them out into
			// this class's own bootstrap table as it writes the instruction.
			return new InvokeDynamicInsnNode(dynamic.name, dynamic.desc, dynamic.bsm, dynamic.bsmArgs.clone());
		}
		return null;
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
	public Set<String> targetClasses() {
		Set<String> result = new LinkedHashSet<>();
		for(String name : targets) {
			result.add(name);
		}
		return result;
	}

}
