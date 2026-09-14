/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * Supplies the Forge API types OptiFine's classes name but NeoForge does not have.
 *
 * <p>OptiFine is a Forge mod, and parts of it are written against Forge's own API - {@code
 * net.minecraftforge.client.extensions.IForgeVertexConsumer} and friends. NeoForge has no such
 * package: its equivalents live under {@code net.neoforged.neoforge.*} and are not
 * signature-compatible. The references are mostly in method signatures, and a signature that
 * cannot be resolved stops the launch before any mod runs, because FML walks the method signatures
 * of every class in the game layer while it is setting up the early window:</p>
 *
 * <pre>ClassNotFoundException: net.minecraftforge.client.extensions.IForgeVertexConsumer
 *   at DisplayWindow.updateModuleReads(DisplayWindow.java:618)</pre>
 *
 * <p>OptiFine does carry its own copies of those Forge classes - under {@code notch/} - but they are
 * compiled against the obfuscated game (their signatures name {@code gng}, {@code akv} and so on),
 * so putting them on the runtime classpath only moves the failure to the next unresolvable type.</p>
 *
 * <p>What is generated here instead is a stub per referenced type, empty apart from its shape: the
 * classes OptiFine actually calls into keep their members for a later pass, but the first thing that
 * has to hold is that every name in every signature resolves. The stubs are derived from the
 * OptiFine jar itself, so each one has the same kind - interface or class - as OptiFine's own copy,
 * which matters because a class is loaded against the supertype it declares.</p>
 */
public final class ForgeApiShims {
	/** The package NeoForge does not provide, and the one the stubs have to live in. */
	private static final String FORGE_PACKAGE = "net/minecraftforge/";
	/** Where OptiFine keeps its own copy of those classes, which tells us the shape. */
	private static final String OPTIFINE_COPY = "notch/";
	private static final Pattern REFERENCE = Pattern.compile("net/minecraftforge/[A-Za-z0-9_/$]+");

	private ForgeApiShims() {
	}

	/**
	 * Every {@code net/minecraftforge} type named anywhere in the OptiFine jar, as internal names.
	 *
	 * <p>Both the classes and the patch payload are searched. The payload matters as much as the
	 * classes: OptiFine's patches make vanilla classes implement Forge interfaces - the first
	 * failure of this kind was {@code IForgeVertexConsumer} being added to
	 * {@code com.mojang.blaze3d.vertex.VertexConsumer} - and the patched class is what the loader
	 * then has to resolve. That name appears nowhere in the jar's classes, only in
	 * {@code patch/srg/.../VertexConsumer.class.xdelta}.</p>
	 *
	 * <p>Classes are read as bytes and searched rather than parsed: the constant pool holds these
	 * names, and searching the raw bytes catches the call sites as well as the signatures.</p>
	 */
	public static Set<String> referencedTypes(Path optifineJar) throws IOException {
		Set<String> names = new TreeSet<>();
		try(ZipFile zip = new ZipFile(optifineJar.toFile())) {
			for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				String entryName = entry.getName();
				if(entry.isDirectory() || entryName.startsWith("assets/") || entryName.startsWith("doc/")) {
					continue;
				}
				if(entryName.startsWith(OPTIFINE_COPY)) {
					// OptiFine's own obfuscated-namespace copies are dropped from the jar we ship,
					// so nothing they name has to resolve.
					continue;
				}
				String text = new String(readAll(zip.getInputStream(entry)), StandardCharsets.ISO_8859_1);
				Matcher matcher = REFERENCE.matcher(text);
				while(matcher.find()) {
					names.add(matcher.group());
				}
			}
		}
		return names;
	}

	/** A stub class file per referenced type, keyed by internal name, ready to be added to the jar. */
	public static Map<String, byte[]> generate(Path optifineJar) throws IOException {
		Set<String> names = referencedTypes(optifineJar);
		Map<String, byte[]> stubs = new LinkedHashMap<>();
		try(ZipFile zip = new ZipFile(optifineJar.toFile())) {
			for(String name : names) {
				stubs.put(name + ".class", stub(name, isInterface(zip, name)));
			}
		}
		return stubs;
	}

	/** Whether OptiFine's own copy of this type is an interface. */
	private static boolean isInterface(ZipFile zip, String name) {
		ZipEntry own = zip.getEntry(OPTIFINE_COPY + name + ".class");
		if(own == null) {
			// Nothing to go on: an interface is the safer shape, since a class that implements an
			// empty interface still verifies while the reverse does not hold for every use.
			return true;
		}
		try(InputStream stream = zip.getInputStream(own)) {
			return (new ClassReader(readAll(stream)).getAccess() & Opcodes.ACC_INTERFACE) != 0;
		} catch(IOException e) {
			return true;
		}
	}

	private static byte[] stub(String internalName, boolean isInterface) {
		ClassWriter writer = new ClassWriter(0);
		int access = Opcodes.ACC_PUBLIC | (isInterface ? Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT : Opcodes.ACC_SUPER);
		writer.visit(Opcodes.V17, access, internalName, null, isInterface ? "java/lang/Object" : "java/lang/Object", null);
		if(!isInterface) {
			var constructor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			constructor.visitCode();
			constructor.visitVarInsn(Opcodes.ALOAD, 0);
			constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			constructor.visitInsn(Opcodes.RETURN);
			constructor.visitMaxs(1, 1);
			constructor.visitEnd();
		}
		writer.visitEnd();
		return writer.toByteArray();
	}

	private static byte[] readAll(InputStream stream) throws IOException {
		try(stream) {
			return stream.readAllBytes();
		}
	}

	/** A short summary of what the shims would cover. */
	public static String describe(Path optifineJar) throws IOException {
		Set<String> names = referencedTypes(optifineJar);
		Set<String> packages = new LinkedHashSet<>();
		for(String name : names) {
			int lastSlash = name.lastIndexOf('/');
			packages.add(lastSlash < 0 ? name : name.substring(0, lastSlash));
		}
		return names.size() + " referenced Forge types in " + packages.size() + " packages";
	}

	/** Development aid: report the referenced types, or write the stubs into a directory. */
	public static void main(String[] args) throws Exception {
		Path jar = Path.of(args[0]);
		Set<String> names = referencedTypes(jar);
		System.out.println(describe(jar));
		for(String name : names) {
			System.out.println("  " + name);
		}
		if(args.length > 1) {
			Path out = Path.of(args[1]);
			Map<String, byte[]> stubs = generate(jar);
			for(Map.Entry<String, byte[]> entry : stubs.entrySet()) {
				Path target = out.resolve(entry.getKey());
				java.nio.file.Files.createDirectories(target.getParent());
				java.nio.file.Files.write(target, entry.getValue());
			}
			System.out.println("wrote " + stubs.size() + " stubs to " + out);
		}
	}
}
