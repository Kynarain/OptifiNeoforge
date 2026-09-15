/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * The classes a payload ships under a name the runtime does not use, and the name the runtime does use.
 *
 * <p>Nested class names are assigned by whoever built the artefact, and the two artefacts here were
 * built differently: OptiFine's payload takes its names from the obfuscated jar, the runtime's from
 * NeoForm. Measured on 1.20.1, and the case that put this in the startup path:</p>
 *
 * <pre>payload: net/minecraft/client/particle/ParticleEngine$ParticleDefinition
 * runtime: net/minecraft/client/particle/ParticleEngine$1ParticleDefinition
 * both   : record with (ResourceLocation, Optional&lt;List&lt;ResourceLocation&gt;&gt;)</pre>
 *
 * <p>One class, two names. Without a bridge the swapped {@code ParticleEngine} calls its own name, the
 * runtime never has that class, and ModLauncher - which only calls a transformer for a class the
 * runtime already has - never installs the payload's copy, so the call fails with
 * {@code NoSuchMethodError}. Shipping the payload's class <em>as</em> the runtime's name is the repair:
 * the index then names the class the runtime asks for.</p>
 *
 * <p>Which runtime class is the counterpart is decided by structure, not by guessing from the number:
 * same superclass, same multiset of field descriptors, same multiset of method descriptors. Member
 * <em>names</em> cannot be used - they are part of what differs - and a candidate set that is not
 * exactly one is left alone and reported, because a wrong pairing would install the wrong class.</p>
 */
public final class NestedNameBridge {
	/** Where the payload keeps the classes it replaces; the runtime keeps them at their own names. */
	private static final String PAYLOAD_ROOT = "srg/";
	/** OptiFine's own classes share that root and are not game classes. */
	private static final String OPTIFINE_PACKAGE = "net/optifine/";

	private NestedNameBridge() {
	}

	/** Payload class name to the runtime class name it must be shipped as, for the pairs found. */
	public static Map<String, String> pairs(Path payload, List<Path> runtimeJars) throws IOException {
		Map<String, ClassNode> payloadNodes = new TreeMap<>();
		try(ZipFile zip = new ZipFile(payload.toFile())) {
			for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				String name = entry.getName();
				if(!name.startsWith(PAYLOAD_ROOT) || !name.endsWith(".class")) {
					continue;
				}
				String internal = name.substring(PAYLOAD_ROOT.length(), name.length() - ".class".length());
				if(internal.startsWith(OPTIFINE_PACKAGE) || internal.indexOf('$') < 0) {
					continue;
				}
				payloadNodes.put(internal, read(zip.getInputStream(entry)));
			}
		}
		if(payloadNodes.isEmpty()) {
			return Map.of();
		}

		// Which runtime names exist at all, and the nodes for the outer classes the payload mentions.
		Set<String> runtimeNames = new LinkedHashSet<>();
		Set<String> outerPrefixes = new LinkedHashSet<>();
		for(String internal : payloadNodes.keySet()) {
			outerPrefixes.add(internal.substring(0, internal.indexOf('$')) + "$");
		}
		Map<String, ClassNode> runtimeNodes = new TreeMap<>();
		for(Path jar : runtimeJars) {
			try(ZipFile zip = new ZipFile(jar.toFile())) {
				for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
					ZipEntry entry = it.nextElement();
					String name = entry.getName();
					if(!name.endsWith(".class")) {
						continue;
					}
					String internal = name.substring(0, name.length() - ".class".length());
					if(internal.indexOf('$') < 0 || !runtimeNames.add(internal)) {
						continue;
					}
					for(String prefix : outerPrefixes) {
						if(internal.startsWith(prefix)) {
							runtimeNodes.put(internal, read(zip.getInputStream(entry)));
							break;
						}
					}
				}
			}
		}

		Set<String> taken = new LinkedHashSet<>();
		Map<String, String> pairs = new LinkedHashMap<>();
		for(Map.Entry<String, ClassNode> entry : payloadNodes.entrySet()) {
			String internal = entry.getKey();
			if(runtimeNames.contains(internal)) {
				continue; // same name on both sides: nothing to bridge
			}
			String prefix = internal.substring(0, internal.indexOf('$')) + "$";
			List<String> matches = new ArrayList<>();
			for(Map.Entry<String, ClassNode> candidate : runtimeNodes.entrySet()) {
				if(!candidate.getKey().startsWith(prefix) || runtimeNames.contains(candidate.getKey())
						&& payloadNodes.containsKey(candidate.getKey())) {
					continue;
				}
				if(taken.contains(candidate.getKey())) {
					continue;
				}
				if(sameShape(entry.getValue(), candidate.getValue())) {
					matches.add(candidate.getKey());
				}
			}
			if(matches.size() == 1) {
				pairs.put(internal, matches.get(0));
				taken.add(matches.get(0));
			} else if(matches.size() > 1) {
				System.err.println("ambiguous bridge for " + internal + ": " + matches);
			}
		}
		return pairs;
	}

	/**
	 * Whether two classes are the same class under different names: same superclass, same field
	 * descriptors, same method descriptors. Names are deliberately not compared.
	 */
	private static boolean sameShape(ClassNode left, ClassNode right) {
		return sameName(left.superName, right.superName)
				&& descriptorsOf(left).equals(descriptorsOf(right));
	}

	private static Set<String> descriptorsOf(ClassNode node) {
		Set<String> all = new TreeSet<>();
		for(FieldNode field : node.fields) {
			all.add("F " + field.desc);
		}
		for(MethodNode method : node.methods) {
			all.add("M " + method.desc);
		}
		return all;
	}

	private static boolean sameName(String left, String right) {
		return left == null ? right == null : left.equals(right);
	}

	private static ClassNode read(InputStream stream) throws IOException {
		ClassNode node = new ClassNode();
		new ClassReader(stream.readAllBytes()).accept(node, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
		return node;
	}

	public static void main(String[] args) throws IOException {
		// usage: NestedNameBridge <payload jar> <runtime jar> [more runtime jars...]
		if(args.length < 2) {
			System.err.println("usage: NestedNameBridge <payload jar> <runtime jar> [more...]");
			System.exit(2);
		}
		List<Path> runtime = new ArrayList<>();
		for(int index = 1; index < args.length; index++) {
			runtime.add(Path.of(args[index]));
		}
		Map<String, String> pairs = pairs(Path.of(args[0]), runtime);
		for(Map.Entry<String, String> pair : pairs.entrySet()) {
			System.out.println(pair.getKey() + "\t" + pair.getValue());
		}
		System.err.println("bridged nested names: " + pairs.size());
		for(Map.Entry<String, String> pair : pairs.entrySet()) {
			System.err.println("  " + pair.getKey() + " -> " + pair.getValue());
		}
	}
}
