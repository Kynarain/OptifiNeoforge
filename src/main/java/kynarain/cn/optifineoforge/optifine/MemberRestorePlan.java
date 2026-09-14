/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
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
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Works out which members OptiFine's replacements drop, before the game runs.
 *
 * <p>Every class OptiFine replaces is one it compiled itself, against Forge - and Forge's copy of
 * that class is not NeoForge's. Whatever NeoForge added to the class since is simply gone, and the
 * game meets it one member at a time: a constructor, then a field, then an accessor, each as its
 * own crash during startup. That is a poor way to find them, so they are found here instead: take
 * the classes OptiFine produces (the patcher's output) and the classes the game actually has (the
 * NeoForge client jar), and list what is in one and not the other.</p>
 *
 * <p>The output is a plain text plan, one member per line:</p>
 *
 * <pre>
 * F &lt;class&gt; &lt;field name&gt; &lt;field descriptor&gt;
 * M &lt;class&gt; &lt;method name&gt; &lt;method descriptor&gt;
 * </pre>
 *
 * <p>It is shipped inside the loader jar and applied while classes are transformed, which is the
 * only place the repair can happen - OptiFine's replacement only exists then.</p>
 */
public final class MemberRestorePlan {
	/** The patcher writes OptiFine's classes here. */
	private static final String PATCHED_ROOT = "srg/";
	/** How far the dependency closure may grow before it gives up. */
	private static final int MAX_CLOSURE_ROUNDS = 8;
	/** Where the environment assertions live that a copied body has to be freed of. */
	private static final String RENDER_SYSTEM = "com/mojang/blaze3d/systems/RenderSystem";

	/**
	 * Members whose donor body must not be used, only stubbed.
	 *
	 * <p>NeoForge's GL state backup is called before the render thread is registered, and a body
	 * that does the real work walks into code that asserts otherwise - "Rendersystem called from
	 * wrong thread" - while the do-nothing stub merely leaves the state alone. Measured: with the
	 * real body the launch died at 10s, with the stub it reached 60s. Neither is right; restoring
	 * the state machine properly is a separate piece of work, so these four keep the stub for now.
	 * </p>
	 */
	private static final java.util.Set<String> STUB_ONLY = java.util.Set.of(
			"com/mojang/blaze3d/systems/RenderSystem backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/systems/RenderSystem restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/platform/GlStateManager _backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V",
			"com/mojang/blaze3d/platform/GlStateManager _restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V");

	private MemberRestorePlan() {
	}

	/** The lines of a plan: what the runtime class has and OptiFine's replacement does not. */
	public static List<String> plan(Path patchedJar, Path runtimeJar) throws IOException {
		return plan(patchedJar, runtimeJar, null);
	}

	/**
	 * The same, and additionally a donor class per affected class when {@code donorDir} is given.
	 *
	 * <p>A donor holds exactly the members that OptiFine's replacement dropped, with their original
	 * field declarations and, for methods, their original bodies. Stubbing them was the first
	 * approach and it is not enough: several of the dropped members are part of NeoForge's render
	 * state machine, where a method that does nothing is worse than a method that is missing (the
	 * loading overlay ends up in an inconsistent state and the game dies with {@code Already
	 * building}). Copying the original body in is the same repair without the guesswork.</p>
	 *
	 * <p>The donor's internal name is the name of the class being repaired, so the copied bodies
	 * resolve their own fields and calls; it is stored under a path of its own and is never loaded
	 * as that class.</p>
	 */
	public static List<String> plan(Path patchedJar, Path runtimeJar, Path donorDir) throws IOException {
		List<String> lines = new ArrayList<>();
		int classes = 0;
		int skipped = 0;

		if(donorDir != null) {
			Files.createDirectories(donorDir);
		}

		try(ZipFile patched = new ZipFile(patchedJar.toFile()); ZipFile runtime = new ZipFile(runtimeJar.toFile())) {
			for(Enumeration<? extends ZipEntry> it = patched.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				if(entry.isDirectory() || !entry.getName().startsWith(PATCHED_ROOT) || !entry.getName().endsWith(".class")) {
					continue;
				}
				String internalName = entry.getName().substring(PATCHED_ROOT.length(), entry.getName().length() - ".class".length());
				ZipEntry counterpart = runtime.getEntry(internalName + ".class");
				if(counterpart == null) {
					// A class OptiFine adds rather than replaces: nothing to compare against.
					skipped++;
					continue;
				}
				classes++;

				ClassNode mine = read(patched.getInputStream(entry));
				ClassNode theirs = read(runtime.getInputStream(counterpart));

				List<FieldNode> fields = missingFields(mine, theirs);
				List<MethodNode> methods = missingMethods(mine, theirs);
				closeOverReferences(mine, theirs, fields, methods, internalName);
				for(FieldNode field : fields) {
					lines.add("F " + internalName + " " + field.name + " " + field.desc);
				}
				for(MethodNode method : methods) {
					lines.add("M " + internalName + " " + method.name + " " + method.desc);
				}

				if(donorDir != null && !(fields.isEmpty() && methods.isEmpty())) {
					writeDonor(donorDir, internalName, mine, theirs, fields, methods);
				}
			}
		}

		System.out.println("compared " + classes + " replaced classes (" + skipped + " without a runtime counterpart), "
				+ lines.size() + " members to restore" + (donorDir == null ? "" : ", donors written to " + donorDir));
		return lines;
	}

	/** A class file carrying only the dropped members, bodies included. */
	private static void writeDonor(Path donorDir, String internalName, ClassNode replacement, ClassNode runtime, List<FieldNode> fields, List<MethodNode> methods)
			throws IOException {
		ClassNode donor = new ClassNode();
		donor.version = runtime.version;
		donor.access = Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER;
		donor.name = internalName;
		donor.superName = runtime.superName;
		donor.interfaces = new ArrayList<>(runtime.interfaces);
		for(FieldNode field : fields) {
			donor.fields.add(new FieldNode(widened(field.access), field.name, field.desc, field.signature, field.value));
		}
		for(MethodNode method : methods) {
			// A copied body may name something OptiFine renamed, and then the copied method does not
			// verify and takes the whole class down with it. Only bodies whose own-class references
			// all exist in the replacement are copied; the rest fall back to a default-value stub,
			// which is logged so the difference stays visible.
			boolean fits = referencesOnlyExisting(method, internalName, replacement)
					&& !STUB_ONLY.contains(internalName + " " + method.name + " " + method.desc);
			MethodNode copy = new MethodNode(widened(method.access), method.name, method.desc, method.signature,
					method.exceptions == null ? null : method.exceptions.toArray(new String[0]));
			method.accept(copy);
			if(fits) {
				// A body that asserts which thread it runs on cannot be copied as it stands: the
				// caller here is NeoForge's own code, which reaches some of these before the render
				// thread is registered - the assertion then fires where the original class would
				// simply have done the work. The assertion is dropped and the rest of the body kept.
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

	/**
	 * The same member, but reachable: a dropped member was usually reachable from the code that
	 * calls it, so a private or package-private copy would only move the failure to
	 * IllegalAccessError.
	 */
	/** Whether every reference the body makes to its own class is present in the replacement. */
	private static boolean referencesOnlyExisting(MethodNode method, String internalName, ClassNode replacement) {
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
				// assignable to reference class" - which is how the first real failure of the donor
				// approach showed itself.
				if(call.getOpcode() == Opcodes.INVOKESPECIAL && owner != null && !owner.equals(internalName)
						&& !owner.equals(replacement.superName)) {
					return false;
				}
			}
			if(owner == null || !owner.equals(internalName)) {
				continue;
			}
			boolean found = false;
			if(desc.startsWith("(")) {
				for(MethodNode candidate : replacement.methods) {
					if(candidate.name.equals(name) && candidate.desc.equals(desc)) {
						found = true;
						break;
					}
				}
			} else {
				for(FieldNode candidate : replacement.fields) {
					if(candidate.name.equals(name) && candidate.desc.equals(desc)) {
						found = true;
						break;
					}
				}
			}
			if(!found) {
				return false;
			}
		}
		return true;
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

	/**
	 * Adds the members a restored body depends on, repeatedly.
	 *
	 * <p>A copied body often names another member of its own class that OptiFine also dropped, and
	 * then the copy does not verify and falls back to a stub returning a default value - which is how
	 * the model pipeline ended up handing null block models to the renderer. Following those
	 * references and restoring what they point at closes the gap, so that "would not verify" means
	 * "restore the chain it depends on" instead of "give up and return null".</p>
	 *
	 * <p>Bounded by rounds rather than by a graph walk; a cycle simply stops the additions, because a
	 * member goes into the known set as soon as it has been added once.</p>
	 */
	private static void closeOverReferences(ClassNode replacement, ClassNode runtime, List<FieldNode> fields,
			List<MethodNode> methods, String internalName) {
		java.util.Set<String> known = new java.util.HashSet<>();
		for(FieldNode field : replacement.fields) {
			known.add("F " + field.name + " " + field.desc);
		}
		for(MethodNode method : replacement.methods) {
			known.add("M " + method.name + " " + method.desc);
		}
		for(FieldNode field : fields) {
			known.add("F " + field.name + " " + field.desc);
		}
		for(MethodNode method : methods) {
			known.add("M " + method.name + " " + method.desc);
		}

		int added = 0;
		for(int round = 0; round < MAX_CLOSURE_ROUNDS; round++) {
			List<FieldNode> moreFields = new ArrayList<>();
			List<MethodNode> moreMethods = new ArrayList<>();
			for(MethodNode method : methods) {
				if(method.instructions == null) {
					continue;
				}
				for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
					String name;
					String desc;
					boolean isField;
					if(insn instanceof FieldInsnNode field) {
						if(!internalName.equals(field.owner)) {
							continue;
						}
						name = field.name; desc = field.desc; isField = true;
					} else if(insn instanceof MethodInsnNode call) {
						if(!internalName.equals(call.owner)) {
							continue;
						}
						name = call.name; desc = call.desc; isField = false;
					} else {
						continue;
					}
					String key = (isField ? "F " : "M ") + name + " " + desc;
					if(known.contains(key)) {
						continue;
					}
					known.add(key);
					if(isField) {
						for(FieldNode candidate : runtime.fields) {
							if(candidate.name.equals(name) && candidate.desc.equals(desc)) {
								moreFields.add(candidate);
								break;
							}
						}
					} else {
						for(MethodNode candidate : runtime.methods) {
							if(candidate.name.equals(name) && candidate.desc.equals(desc)) {
								moreMethods.add(candidate);
								break;
							}
						}
					}
				}
			}
			if(moreFields.isEmpty() && moreMethods.isEmpty()) {
				break;
			}
			fields.addAll(moreFields);
			methods.addAll(moreMethods);
			added += moreFields.size() + moreMethods.size();
		}
		if(added > 0) {
			System.out.println("  closure added " + added + " depended-on members: " + internalName);
		}
	}

	private static int widened(int access) {
		return (access & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED)) | Opcodes.ACC_PUBLIC;
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

	/** Development aid: {@code MemberRestorePlan <patched jar> <runtime jar> <out file>}. */
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