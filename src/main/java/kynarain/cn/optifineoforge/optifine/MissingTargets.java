/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Lists every game member the payload calls that the runtime does not have.
 *
 * <p>Written for the failure that member restoration cannot fix. {@code BlockEntity.<init>} in OptiFine's
 * compilation calls {@code gatherCapabilities()}, NeoForge 20.4 replaced Forge's capability system with
 * attachments and deleted that method, and the restore plan was right not to restore it - a donor cannot
 * come from a runtime that has no such member:</p>
 *
 * <pre>NoSuchMethodError: 'void net.minecraft.world.level.block.entity.BlockEntity.gatherCapabilities()'</pre>
 *
 * <p>{@link SrgRemap} cannot see these either, because it only ever looks at SRG-shaped names
 * ({@code m_}/{@code f_}) while Forge API members keep their plain names. So this walks <em>every</em>
 * reference whose owner is a game class and asks the runtime, hierarchy included, whether the member is
 * there. What it prints is the complete set that {@code gatherCapabilities} is one of, which is what a
 * decision about shimming or stripping needs.</p>
 */
public final class MissingTargets {
	private MissingTargets() {
	}

	/** Development aid: {@code MissingTargets <payload jar> <runtime jar> [more runtime jars...]}. */
	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.err.println("usage: MissingTargets <payload jar> <runtime jar> [more runtime jars...]");
			System.exit(2);
		}
		Path payload = Path.of(args[0]);
		SrgMemberMap.RuntimeIndex runtime = new SrgMemberMap.RuntimeIndex();
		for(int index = 1; index < args.length; index++) {
			runtime.add(Path.of(args[index]));
		}
		// The payload's own classes belong in the index as well, and this is the difference between a
		// useful report and a useless one: OptiFine *adds* members to the classes it replaces
		// (ModelPart.getChildModelDeep, Options.ofClouds, TextureAtlasSprite.spriteNormal), so a
		// reference to one of those is missing from the runtime while being perfectly satisfiable -
		// because the swapped class is what will be loaded. Indexing only the runtime reported 1259
		// "missing" references of which the great majority were OptiFine's own additions.
		runtime.add(payload);

		Map<String, Integer> counts = new TreeMap<>();
		Map<String, String> firstSeen = new LinkedHashMap<>();
		int references = 0;
		int classes = 0;
		try(ZipFile zip = new ZipFile(payload.toFile())) {
			for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements(); ) {
				ZipEntry entry = it.nextElement();
				if(!entry.getName().endsWith(".class") || SrgRemap.isUnusedNamespace(entry.getName())) {
					continue;
				}
				ClassNode node = new ClassNode();
				try(InputStream stream = zip.getInputStream(entry)) {
					new ClassReader(stream.readAllBytes()).accept(node, ClassReader.SKIP_DEBUG);
				}
				classes++;
				for(MethodNode method : node.methods) {
					for(AbstractInsnNode instruction = method.instructions.getFirst(); instruction != null; instruction = instruction.getNext()) {
						String owner = null;
						String name = null;
						String desc = null;
						boolean isMethod = false;
						if(instruction instanceof MethodInsnNode call) {
							owner = call.owner;
							name = call.name;
							desc = call.desc;
							isMethod = true;
						} else if(instruction instanceof FieldInsnNode field) {
							owner = field.owner;
							name = field.name;
							desc = field.desc;
						}
						if(owner == null || !isGameClass(owner)) {
							continue;
						}
						references++;
						if(declares(runtime, owner, name, desc, isMethod)) {
							continue;
						}
						String key = owner + "." + name + (isMethod ? desc : ":" + desc);
						counts.merge(key, 1, Integer::sum);
						firstSeen.putIfAbsent(key, node.name);
					}
				}
			}
		}

		List<Map.Entry<String, Integer>> ordered = new ArrayList<>(counts.entrySet());
		ordered.sort((left, right) -> Integer.compare(right.getValue(), left.getValue()));
		System.out.println("scanned " + classes + " classes, " + references
				+ " references to game members, " + ordered.size() + " of them missing from the runtime");
		int shown = 0;
		for(Map.Entry<String, Integer> entry : ordered) {
			if(shown++ >= 40) {
				System.out.println("  ... " + (ordered.size() - 40) + " more");
				break;
			}
			System.out.println("  " + entry.getValue() + "x  " + entry.getKey()
					+ "   (first seen in " + firstSeen.get(entry.getKey()) + ")");
		}
		// Grouped by owner, because one decision usually covers a whole class.
		Map<String, Integer> byOwner = new TreeMap<>();
		for(String key : counts.keySet()) {
			byOwner.merge(key.substring(0, key.indexOf('.')), counts.get(key), Integer::sum);
		}
		System.out.println("by owner: " + byOwner);
	}

	private static boolean isGameClass(String owner) {
		return owner.startsWith("net/minecraft/") || owner.startsWith("com/mojang/");
	}

	/** Whether the runtime declares the member, on the owner or anywhere up its hierarchy. */
	private static boolean declares(SrgMemberMap.RuntimeIndex runtime, String owner, String name, String desc,
			boolean method) {
		for(String candidate : runtime.hierarchy(owner)) {
			// A JDK ancestor counts as present. Enum.ordinal() is reached this way - Direction inherits it
			// from java.lang.Enum - and java.base is not a jar that can be indexed here, while it is
			// never absent at runtime either. Without this the report was dominated by ordinal() calls.
			if(candidate.startsWith("java/") || candidate.startsWith("javax/") || candidate.startsWith("jdk/")) {
				return true;
			}
			String key = candidate + "." + name + desc;
			if(method ? runtime.methods.contains(key) : runtime.fields.contains(key)) {
				return true;
			}
		}
		return false;
	}

	/** Kept so the class compiles standalone against the same helpers the other tools use. */
	static Map<String, Integer> emptyCounts() {
		return new HashMap<>();
	}
}
