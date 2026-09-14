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
import org.objectweb.asm.tree.ClassNode;
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

	private MemberRestorePlan() {
	}

	/** The lines of a plan: what the runtime class has and OptiFine's replacement does not. */
	public static List<String> plan(Path patchedJar, Path runtimeJar) throws IOException {
		List<String> lines = new ArrayList<>();
		int classes = 0;
		int skipped = 0;

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

				for(String field : missingFields(mine, theirs)) {
					lines.add("F " + internalName + " " + field);
				}
				for(String method : missingMethods(mine, theirs)) {
					lines.add("M " + internalName + " " + method);
				}
			}
		}

		System.out.println("compared " + classes + " replaced classes (" + skipped + " without a runtime counterpart), "
				+ lines.size() + " members to restore");
		return lines;
	}

	private static List<String> missingFields(ClassNode mine, ClassNode theirs) {
		Map<String, FieldNode> present = new TreeMap<>();
		for(FieldNode field : mine.fields) {
			present.put(field.name + " " + field.desc, field);
		}
		List<String> missing = new ArrayList<>();
		for(FieldNode field : theirs.fields) {
			// Enum constants and compiler-generated fields are not worth restoring.
			if(!present.containsKey(field.name + " " + field.desc) && !field.name.startsWith("$") && !field.name.startsWith("this$")) {
				missing.add(field.name + " " + field.desc);
			}
		}
		return missing;
	}

	private static List<String> missingMethods(ClassNode mine, ClassNode theirs) {
		Map<String, MethodNode> present = new TreeMap<>();
		for(MethodNode method : mine.methods) {
			present.put(method.name + " " + method.desc, method);
		}
		List<String> missing = new ArrayList<>();
		for(MethodNode method : theirs.methods) {
			if("<clinit>".equals(method.name) || "<init>".equals(method.name)) {
				continue; // constructors are handled by the targeted fixes
			}
			if(method.name.startsWith("lambda$") || method.name.startsWith("access$")) {
				continue; // synthetic
			}
			String key = method.name + " " + method.desc;
			if(!present.containsKey(key)) {
				missing.add(key);
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
		if(args.length != 3) {
			System.err.println("usage: MemberRestorePlan <patched jar> <runtime jar> <out file>");
			System.exit(2);
		}
		List<String> lines = plan(Path.of(args[0]), Path.of(args[1]));
		Files.write(Path.of(args[2]), lines, StandardCharsets.UTF_8);
		System.out.println("wrote " + args[2]);
		Map<String, Integer> perClass = summarise(lines);
		perClass.forEach((name, count) -> System.out.println("  " + count + "  " + name));
		System.out.println("distinct classes: " + new TreeSet<>(perClass.keySet()).size());
	}
}
