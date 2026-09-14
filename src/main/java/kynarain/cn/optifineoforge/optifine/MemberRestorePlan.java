/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Works out which members OptiFine's replacements drop, before the game runs.
 *
 * <p>Every class OptiFine replaces is one it compiled itself, against Forge - and Forge's copy of
 * that class is not NeoForge's. Whatever NeoForge added to the class since is gone, and the game
 * meets it one member at a time. So they are found here instead: take the classes OptiFine produces
 * (the patcher's output) and the classes the game actually has (the NeoForge client jar), and list
 * what is in one and not the other.</p>
 *
 * <p>Finding them is not enough, because a restored body has to verify. A body that names another
 * dropped member - in its own class or, just as often, in a class that was replaced too - does not
 * load at all, so the search follows those references and restores what they point at, across
 * classes, until nothing new appears. What is left is written out as a donor class per affected
 * class: the members with their original bodies, for the transformer to copy in.</p>
 *
 * <p>The plan is a plain text file, one member per line:</p>
 *
 * <pre>
 * F &lt;class&gt; &lt;field name&gt; &lt;field descriptor&gt;
 * M &lt;class&gt; &lt;method name&gt; &lt;method descriptor&gt;
 * </pre>
 */
public final class MemberRestorePlan {
	/** The patcher writes OptiFine's classes here. */
	private static final String PATCHED_ROOT = "srg/";
	/** Where the environment assertions live that a copied body has to be freed of. */
	private static final String RENDER_SYSTEM = "com/mojang/blaze3d/systems/RenderSystem";
	/** How far the reference closure may grow before it gives up. */
	private static final int MAX_CLOSURE_ROUNDS = 8;
	/** The name restored fields are initialised through, added to every constructor. */
	public static final String INITIALISER_PREFIX = "optifineoforge$init$";

	/**
	 * Members whose donor body must not be used, only stubbed.
	 *
	 * <p>NeoForge's GL state backup is called before the render thread is registered, and a body that
	 * does the real work walks into code that asserts otherwise, while the do-nothing stub merely
	 * leaves the state alone. Neither is right; restoring the state machine properly is separate
	 * work, so these four keep the stub.</p>
	 */
	private static final Set<String> STUB_ONLY = Set.of(
			"com/mojang/blaze3d/systems/RenderSystem backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/systems/RenderSystem restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/platform/GlStateManager _backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/platform/GlStateManager _restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V");

	private MemberRestorePlan() {
	}

	/** The lines of a plan: what the runtime classes have and OptiFine's replacements do not. */
	public static List<String> plan(Path patchedJar, Path runtimeJar) throws IOException {
		return plan(patchedJar, runtimeJar, null);
	}

	/** The same, and additionally a donor class per affected class when {@code donorDir} is given. */
	public static List<String> plan(Path patchedJar, Path runtimeJar, Path donorDir) throws IOException {
		if(donorDir != null) {
			Files.createDirectories(donorDir);
		}

		// Every replaced class and its runtime counterpart, held together: the closure below has to
		// look at classes other than the one it started from.
		Map<String, ClassNode> replacements = new LinkedHashMap<>();
		Map<String, ClassNode> runtimes = new LinkedHashMap<>();
		int skipped = 0;
		try(ZipFile patched = new ZipFile(patchedJar.toFile()); ZipFile runtime = new ZipFile(runtimeJar.toFile())) {
			for(Enumeration<? extends ZipEntry> it = patched.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				if(entry.isDirectory() || !entry.getName().startsWith(PATCHED_ROOT) || !entry.getName().endsWith(".class")) {
					continue;
				}
				String internalName = entry.getName().substring(PATCHED_ROOT.length(), entry.getName().length() - ".class".length());
				replacements.put(internalName, read(patched.getInputStream(entry)));
				ZipEntry counterpart = runtime.getEntry(internalName + ".class");
				if(counterpart == null) {
					skipped++; // a class OptiFine adds rather than replaces
					continue;
				}
				runtimes.put(internalName, read(runtime.getInputStream(counterpart)));
			}
		}

		Map<String, List<FieldNode>> fields = new LinkedHashMap<>();
		Map<String, List<MethodNode>> methods = new LinkedHashMap<>();
		for(Map.Entry<String, ClassNode> entry : runtimes.entrySet()) {
			ClassNode replacement = replacements.get(entry.getKey());
			fields.put(entry.getKey(), missingFields(replacement, entry.getValue()));
			methods.put(entry.getKey(), missingMethods(replacement, entry.getValue()));
		}

		int added = closeOverReferences(replacements, runtimes, fields, methods);

		List<String> lines = new ArrayList<>();
		for(Map.Entry<String, ClassNode> entry : runtimes.entrySet()) {
			String internalName = entry.getKey();
			List<FieldNode> classFields = fields.get(internalName);
			List<MethodNode> classMethods = methods.get(internalName);
			for(FieldNode field : classFields) {
				lines.add("F " + internalName + " " + field.name + " " + field.desc);
			}
			for(MethodNode method : classMethods) {
				lines.add("M " + internalName + " " + method.name + " " + method.desc);
			}
			if(donorDir != null && !(classFields.isEmpty() && classMethods.isEmpty())) {
				writeDonor(donorDir, internalName, replacements.get(internalName), entry.getValue(), classFields, classMethods);
			}
		}

		System.out.println("compared " + runtimes.size() + " replaced classes (" + skipped + " without a runtime counterpart), "
				+ lines.size() + " members to restore, closure added " + added
				+ (donorDir == null ? "" : ", donors written to " + donorDir));
		return lines;
	}

	/**
	 * Restores what the restored bodies refer to, across classes, until nothing new appears.
	 *
	 * <p>A copied body often names a member that OptiFine dropped - sometimes one of its own class,
	 * more often one of another replaced class, since the replaced classes call each other. Following
	 * those references is the difference between a body that verifies and a body that has to fall
	 * back to returning a default value, which is how null block models reached the renderer. The
	 * search is bounded by rounds, and a member is only ever added once, so a cycle stops adding
	 * rather than looping.</p>
	 *
	 * @return how many members the closure added
	 */
	private static int closeOverReferences(Map<String, ClassNode> replacements, Map<String, ClassNode> runtimes,
			Map<String, List<FieldNode>> fields, Map<String, List<MethodNode>> methods) {
		int added = 0;
		for(int round = 0; round < MAX_CLOSURE_ROUNDS; round++) {
			int before = added;
			for(Map.Entry<String, List<MethodNode>> entry : methods.entrySet()) {
				String owner = entry.getKey();
				ClassNode ownerReplacement = replacements.get(owner);
				ClassNode ownerRuntime = runtimes.get(owner);
				if(ownerReplacement == null || ownerRuntime == null) {
					continue;
				}
				for(MethodNode method : new ArrayList<>(entry.getValue())) {
					if(method.instructions == null) {
						continue;
					}
					for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
						String target;
						String name;
						String desc;
						boolean isField;
						if(insn instanceof FieldInsnNode field) {
							target = field.owner; name = field.name; desc = field.desc; isField = true;
						} else if(insn instanceof MethodInsnNode call) {
							target = call.owner; name = call.name; desc = call.desc; isField = false;
							if("<init>".equals(name) || "<clinit>".equals(name)) {
								continue;
							}
						} else {
							continue;
						}
						// The reference may be to any replaced class, not only to the one whose body
						// it came from - and it is that class's replacement which has to gain the
						// member for the body to verify.
						ClassNode targetReplacement = replacements.get(target);
						ClassNode targetRuntime = runtimes.get(target);
						if(targetReplacement == null || targetRuntime == null) {
							continue;
						}
						if(hasMember(targetReplacement, name, desc) || isPlanned(fields, methods, target, name, desc)) {
							continue;
						}
						if(isField) {
							FieldNode candidate = findField(targetRuntime, name, desc);
							if(candidate != null) {
								fields.get(target).add(candidate);
								added++;
							}
						} else {
							MethodNode candidate = findMethod(targetRuntime, name, desc);
							if(candidate != null) {
								methods.get(target).add(candidate);
								added++;
							}
						}
					}
				}
			}
			if(added == before) {
				break;
			}
		}
		return added;
	}

	private static boolean isPlanned(Map<String, List<FieldNode>> fields, Map<String, List<MethodNode>> methods,
			String owner, String name, String desc) {
		if(desc.startsWith("(")) {
			for(MethodNode method : methods.get(owner)) {
				if(method.name.equals(name) && method.desc.equals(desc)) {
					return true;
				}
			}
			return false;
		}
		for(FieldNode field : fields.get(owner)) {
			if(field.name.equals(name) && field.desc.equals(desc)) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasMember(ClassNode node, String name, String desc) {
		if(desc.startsWith("(")) {
			return findMethod(node, name, desc) != null;
		}
		return findField(node, name, desc) != null;
	}

	private static MethodNode findMethod(ClassNode node, String name, String desc) {
		for(MethodNode method : node.methods) {
			if(method.name.equals(name) && method.desc.equals(desc)) {
				return method;
			}
		}
		return null;
	}

	private static FieldNode findField(ClassNode node, String name, String desc) {
		for(FieldNode field : node.fields) {
			if(field.name.equals(name) && field.desc.equals(desc)) {
				return field;
			}
		}
		return null;
	}

	private static List<FieldNode> missingFields(ClassNode mine, ClassNode theirs) {
		Map<String, FieldNode> present = new TreeMap<>();
		for(FieldNode field : mine.fields) {
			present.put(field.name + " " + field.desc, field);
		}
		List<FieldNode> missing = new ArrayList<>();
		for(FieldNode field : theirs.fields) {
			// Enum constants and compiler-generated fields are not worth restoring.
			if(!present.containsKey(field.name + " " + field.desc) && !field.name.startsWith("$") && !field.name.startsWith("this$")) {
				missing.add(field);
			}
		}
		return missing;
	}

	private static List<MethodNode> missingMethods(ClassNode mine, ClassNode theirs) {
		Map<String, MethodNode> present = new TreeMap<>();
		for(MethodNode method : mine.methods) {
			present.put(method.name + " " + method.desc, method);
		}
		List<MethodNode> missing = new ArrayList<>();
		for(MethodNode method : theirs.methods) {
			if("<clinit>".equals(method.name) || "<init>".equals(method.name)) {
				continue; // constructors are handled by the targeted fixes
			}
			if(method.name.startsWith("lambda$") || method.name.startsWith("access$")) {
				continue; // synthetic
			}
			String key = method.name + " " + method.desc;
			if(!present.containsKey(key)) {
				missing.add(method);
			}
		}
		return missing;
	}

	/** A class file carrying only the dropped members, bodies included. */
	private static void writeDonor(Path donorDir, String internalName, ClassNode replacement, ClassNode runtime,
			List<FieldNode> fields, List<MethodNode> methods) throws IOException {
		ClassNode donor = new ClassNode();
		donor.version = runtime.version;
		donor.access = Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER;
		donor.name = internalName;
		donor.superName = runtime.superName;
		donor.interfaces = new ArrayList<>(runtime.interfaces);
		for(FieldNode field : fields) {
			// Final is dropped: a final instance field may only be assigned from the class's own
			// <init>, and the initialisation is carried out by a restored method, which the JVM
			// rejects with "Update to non-static final field ... attempted from a different method".
			donor.fields.add(new FieldNode(widened(field.access) & ~Opcodes.ACC_FINAL, field.name, field.desc,
					field.signature, field.value));
			if((field.access & Opcodes.ACC_STATIC) == 0) {
				MethodNode initialiser = initialiser(runtime, internalName, field);
				if(initialiser != null) {
					donor.methods.add(initialiser);
				} else {
					System.out.println("  no safe initialiser for field " + internalName + "." + field.name);
				}
			}
		}
		for(MethodNode method : methods) {
			boolean fits = referencesOnlyExisting(method, internalName, replacement, fields, methods)
					&& !STUB_ONLY.contains(internalName + " " + method.name + " " + method.desc);
			MethodNode copy = new MethodNode(widened(method.access), method.name, method.desc, method.signature,
					method.exceptions == null ? null : method.exceptions.toArray(new String[0]));
			method.accept(copy);
			if(fits) {
				int stripped = stripEnvironmentAsserts(copy);
				if(stripped > 0) {
					System.out.println("  assertions dropped (" + stripped + "): " + internalName + "." + method.name + method.desc);
				}
			} else {
				copy.instructions = stubBody(method.desc);
				copy.tryCatchBlocks.clear();
				copy.localVariables = null;
				System.out.println("  stub (body would not verify): " + internalName + "." + method.name + method.desc);
			}
			donor.methods.add(copy);
		}

		Path target = donorDir.resolve(internalName + ".class");
		Files.createDirectories(target.getParent());
		ClassWriter writer = new ClassWriter(0);
		donor.accept(writer);
		Files.write(target, writer.toByteArray());
	}

	/** Whether every reference the body makes to its own class is present in the replacement. */
	private static boolean referencesOnlyExisting(MethodNode method, String internalName, ClassNode replacement,
			List<FieldNode> plannedFields, List<MethodNode> plannedMethods) {
		if(method.instructions == null) {
			return false;
		}
		for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			String owner = null;
			String name = null;
			String desc = null;
			if(insn instanceof FieldInsnNode field) {
				owner = field.owner; name = field.name; desc = field.desc;
			} else if(insn instanceof MethodInsnNode call) {
				owner = call.owner; name = call.name; desc = call.desc;
				// A super call only verifies if the class it is copied into has the same superclass.
				// OptiFine's compilation of a class can extend something else entirely, and then the
				// copied body fails with "Bad invokespecial instruction: current class isn't
				// assignable to reference class".
				if(call.getOpcode() == Opcodes.INVOKESPECIAL && owner != null && !owner.equals(internalName)
						&& !owner.equals(replacement.superName)) {
					return false;
				}
			}
			if(owner == null || !owner.equals(internalName)) {
				continue;
			}
			// The member may be one the plan already restores: the class this body lands in will have
			// it, so the body verifies. Checking against the raw replacement refused bodies that are
			// in fact fine - ASM's verifier accepts them once the plan is applied, and
			// LiquidBlockRenderer is the case that showed it.
			if(!hasMember(replacement, name, desc) && !isPlannedMember(plannedFields, plannedMethods, name, desc)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes the no-argument environment assertions from a copied body.
	 *
	 * <p>They are static calls with no arguments, so dropping the instruction leaves the stack
	 * balanced; only calls whose name starts with {@code assert} and which return void are touched.</p>
	 *
	 * @return how many were dropped
	 */
	private static int stripEnvironmentAsserts(MethodNode method) {
		if(method.instructions == null) {
			return 0;
		}
		int dropped = 0;
		for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null;) {
			AbstractInsnNode next = insn.getNext();
			if(insn instanceof MethodInsnNode call && "()V".equals(call.desc) && call.name.startsWith("assert")
					&& RENDER_SYSTEM.equals(call.owner)) {
				method.instructions.remove(insn);
				dropped++;
			}
			insn = next;
		}
		return dropped;
	}

	/** The body a stub gets: return the default value for the return type. */
	private static InsnList stubBody(String descriptor) {
		InsnList body = new InsnList();
		String returns = descriptor.substring(descriptor.lastIndexOf(')') + 1);
		switch(returns) {
			case "V" -> body.add(new InsnNode(Opcodes.RETURN));
			case "J" -> { body.add(new InsnNode(Opcodes.LCONST_0)); body.add(new InsnNode(Opcodes.LRETURN)); }
			case "D" -> { body.add(new InsnNode(Opcodes.DCONST_0)); body.add(new InsnNode(Opcodes.DRETURN)); }
			case "F" -> { body.add(new InsnNode(Opcodes.FCONST_0)); body.add(new InsnNode(Opcodes.FRETURN)); }
			case "Z", "B", "C", "S", "I" -> { body.add(new InsnNode(Opcodes.ICONST_0)); body.add(new InsnNode(Opcodes.IRETURN)); }
			default -> { body.add(new InsnNode(Opcodes.ACONST_NULL)); body.add(new InsnNode(Opcodes.ARETURN)); }
		}
		return body;
	}

	/**
	 * The same member, but reachable: a dropped member was usually reachable from the code that calls
	 * it, so a private or package-private copy would only move the failure to IllegalAccessError.
	 */
	/**
	 * The sequence that initialises one field, lifted out of the runtime class's constructor.
	 *
	 * <p>A restored field is nobody's responsibility: NeoForge added it, OptiFine's compilation of the
	 * class knows nothing about it, and so it stays null until the {@code putfield} that would have
	 * set it is found and copied. That is what happened to {@code Gui.layerManager}: the stub never
	 * touched it, the real body called {@code initModdedLayers()} on it at once.</p>
	 *
	 * <p>Only straight-line initialisations are lifted - the usual {@code this.x = new Y(...)} or
	 * {@code this.x = argument} shape, touching no local but slot 0 - because a slice with a branch in
	 * it cannot be moved without also moving the code the branch came from. Anything else is left
	 * null and said so, rather than guessed at.</p>
	 *
	 * @return a static method taking the instance, or {@code null} when nothing safe was found
	 */
	private static MethodNode initialiser(ClassNode runtime, String internalName, FieldNode field) {
		for(MethodNode constructor : runtime.methods) {
			if(!"<init>".equals(constructor.name) || constructor.instructions == null) {
				continue;
			}
			for(AbstractInsnNode insn = constructor.instructions.getFirst(); insn != null; insn = insn.getNext()) {
				if(!(insn instanceof FieldInsnNode store) || store.getOpcode() != Opcodes.PUTFIELD) {
					continue;
				}
				if(!internalName.equals(store.owner) || !field.name.equals(store.name) || !field.desc.equals(store.desc)) {
					continue;
				}

				// Walk back to the start of the straight-line run that produced the value.
				List<AbstractInsnNode> slice = new ArrayList<>();
				boolean safe = true;
				for(AbstractInsnNode back = insn.getPrevious(); back != null; back = back.getPrevious()) {
					if(back instanceof LabelNode || back instanceof JumpInsnNode || back instanceof TableSwitchInsnNode
							|| back instanceof LookupSwitchInsnNode || back instanceof LineNumberNode || back instanceof FrameNode) {
						break;
					}
					if(back instanceof VarInsnNode var && var.var != 0) {
						safe = false; // depends on a constructor argument or another local
						break;
					}
					slice.add(0, back);
				}
				if(!safe || slice.isEmpty()) {
					continue;
				}

				MethodNode initialiser = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
						INITIALISER_PREFIX + field.name, "(L" + internalName + ";)V", null, null);
				for(AbstractInsnNode step : slice) {
					initialiser.instructions.add(step);
				}
				initialiser.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, internalName, field.name, field.desc));
				initialiser.instructions.add(new InsnNode(Opcodes.RETURN));
				initialiser.maxStack = 8;
				initialiser.maxLocals = 1;
				return initialiser;
			}
		}
		return null;
	}
	private static boolean isPlannedMember(List<FieldNode> plannedFields, List<MethodNode> plannedMethods, String name, String desc) {
		if(desc.startsWith("(")) {
			for(MethodNode method : plannedMethods) {
				if(method.name.equals(name) && method.desc.equals(desc)) {
					return true;
				}
			}
			return false;
		}
		for(FieldNode field : plannedFields) {
			if(field.name.equals(name) && field.desc.equals(desc)) {
				return true;
			}
		}
		return false;
	}

	private static int widened(int access) {
		return (access & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED)) | Opcodes.ACC_PUBLIC;
	}

	private static ClassNode read(InputStream stream) throws IOException {
		try(stream) {
			ClassNode node = new ClassNode();
			new ClassReader(stream.readAllBytes()).accept(node, 0);
			return node;
		}
	}

	/** Grouped for a readable summary: class to number of members. */
	public static Map<String, Integer> summarise(List<String> lines) {
		Map<String, Integer> perClass = new TreeMap<>();
		for(String line : lines) {
			String[] parts = line.split(" ");
			if(parts.length >= 2) {
				perClass.merge(parts[1], 1, Integer::sum);
			}
		}
		return perClass;
	}

	/** Development aid: {@code MemberRestorePlan <patched jar> <runtime jar> <out file> [donor dir]}. */
	public static void main(String[] args) throws IOException {
		if(args.length < 3) {
			System.err.println("usage: MemberRestorePlan <patched jar> <runtime jar> <out file> [donor dir]");
			System.exit(2);
		}
		List<String> lines = plan(Path.of(args[0]), Path.of(args[1]), args.length > 3 ? Path.of(args[3]) : null);
		Files.write(Path.of(args[2]), lines, StandardCharsets.UTF_8);
		System.out.println("wrote " + args[2]);
		Map<String, Integer> perClass = summarise(lines);
		perClass.forEach((name, count) -> System.out.println("  " + count + "  " + name));
		System.out.println("distinct classes: " + new TreeSet<>(perClass.keySet()).size());
	}
}
