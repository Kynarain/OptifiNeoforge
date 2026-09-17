/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Supplies the Forge-era members of <em>game</em> classes that OptiFine's {@code Reflector} table
 * expects and this runtime does not have.
 *
 * <p>Where {@link ForgeApiShims} answers for the {@code net.minecraftforge.**} types NeoForge deleted,
 * this answers for the members Forge once added <em>to the game's own classes</em>. A missing one is
 * not a missing class: {@code Reflector.forgeItemTags.create(...)} resolves against
 * {@code net.minecraft.tags.ItemTags}, which exists, so the lookup fails quietly and the caller gets
 * null - measured in {@link ReflectorGaps}, which is the tool that finds these.</p>
 *
 * <p>Measured case, 1.21.11: OptiFine's patched {@code net.minecraft.world.item.DyeColor} builds its
 * two tag fields with</p>
 *
 * <pre>this.dyesTag = (TagKey) Reflector.ForgeItemTags_create.call("forge", "dyes/" + name);</pre>
 *
 * <p>and the table's {@code ForgeItemTags} is {@code new ReflectorClass(ItemTags.class)} with
 * {@code makeMethod("create", String.class, String.class)} - Forge's two-argument helper on the game's
 * own tag class. NeoForge kept only {@code create(Identifier)}, so the call answers null, both tag
 * fields of every {@code DyeColor} constant are null, and
 * {@code net.neoforged.neoforge.common.Tags$Items.DYES_BLACK} - which is
 * {@code DyeColor.BLACK.getTag()} - is null, which makes NeoForge's own
 * {@code TagConventionLogWarning.<clinit>} throw and the whole mod load fail with
 * {@code Mod loading failures have occurred}.</p>
 *
 * <p>The supplied body is not a stub: the tag fields are read by the game, so a null answer is worse
 * than no method at all. What it does is what this runtime's own class does - measured, not assumed:
 * NeoForge's {@code Tags$Items.tag(String)} is {@code ItemTags.create(Identifier.fromNamespaceAndPath
 * ("c", name))} and the runtime's own {@code DyeColor} constructor builds its tags the same way, so the
 * two-argument helper the Forge generation used to name conventional tags is answered with this
 * runtime's conventional namespace. The namespace is taken as given unless it is {@code forge}, the
 * Forge generation's name for that same set of tags.</p>
 *
 * <p>Usage: {@code ForgeEraMembers <payload jar> <runtime jar> [runtime jar...] [--dry-run]}. The
 * classes it writes go into the payload under {@code srg/}, so the mount point installs them exactly
 * like the classes OptiFine's own patches produce.</p>
 */
public final class ForgeEraMembers {
	/** The runtime's name for the conventional tag namespace the Forge generation called "forge". */
	private static final String CONVENTIONAL_NAMESPACE = "c";
	private static final String FORGE_ERA_NAMESPACE = "forge";

	private static final String ITEM_TAGS = "net/minecraft/tags/ItemTags";
	private static final String TAG_KEY = "net/minecraft/tags/TagKey";
	private static final String IDENTIFIER = "net/minecraft/resources/Identifier";

	private ForgeEraMembers() {
	}

	/** One Forge-era member of a game class, and why the runtime does not have it. */
	private record Member(String owner, String name, String desc, String reason) {
	}

	/**
	 * The members this tool knows how to supply, each with the measurement that says the runtime is
	 * missing it and the reason the answer has to be real rather than a default value.
	 */
	private static List<Member> table() {
		List<Member> members = new ArrayList<>();
		members.add(new Member(ITEM_TAGS, "create", "(Ljava/lang/String;Ljava/lang/String;)L" + TAG_KEY + ";",
				"Forge's two-argument ItemTags.create; NeoForge kept only create(Identifier)."
						+ " OptiFine's DyeColor stores the answer in a field, so null there fails the mod load."));
		return members;
	}

	public static void main(String[] args) throws Exception {
		boolean dryRun = false;
		List<String> files = new ArrayList<>();
		for(String arg : args) {
			if("--dry-run".equals(arg)) {
				dryRun = true;
			} else {
				files.add(arg);
			}
		}
		if(files.size() < 2) {
			System.err.println("usage: ForgeEraMembers <payload jar> <runtime jar> [runtime jar...] [--dry-run]");
			System.exit(2);
		}
		Path payload = Path.of(files.get(0));
		List<Path> runtime = new ArrayList<>();
		for(int index = 1; index < files.size(); index++) {
			runtime.add(Path.of(files.get(index)));
		}

		List<String> supplied = new ArrayList<>();
		List<String> skipped = new ArrayList<>();
		List<String> alreadyInPayload = payloadEntries(payload);
		// The payload jars this project builds carry a few directory entries twice (the build steps
		// add META-INF/services/ unconditionally), and ZipOutputStream refuses a duplicate name - so
		// the first copy of each name wins and the repeats are dropped. Measured: without this, the
		// rewrite dies on "duplicate entry: META-INF/services/" and the payload is left untouched.
		java.util.Set<String> written = new java.util.HashSet<>();
		Path temporary = payload.resolveSibling(payload.getFileName() + ".tmp");
		try(ZipFile source = new ZipFile(payload.toFile());
				ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(temporary))) {
			for(java.util.Enumeration<? extends ZipEntry> it = source.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				String name = entry.getName();
				if(!written.add(name)) {
					continue;
				}
				if(entry.isDirectory()) {
					// A jar's directory entries are written too: the jar contents abstraction reports a
					// class whose package has no directory of its own as not found.
					out.putNextEntry(new ZipEntry(name));
					out.closeEntry();
					continue;
				}
				byte[] bytes = readAll(source.getInputStream(entry));
				for(Member member : table()) {
					if(name.equals("srg/" + member.owner() + ".class")) {
						// A payload copy of the class exists: add the member to the payload's own
						// bytes rather than to the runtime's, so nothing of the payload is lost.
						byte[] withMember = addMember(bytes, member);
						if(withMember != null) {
							bytes = withMember;
							supplied.add(member.owner() + "." + member.name() + member.desc()
									+ "  (into the payload's own copy)");
						}
					}
				}
				copy(out, name, bytes);
			}
			// And the ones the payload does not carry: the runtime class comes in from the jars given,
			// with the member added, at the path the mount point reads.
			for(Member member : table()) {
				if(alreadyInPayload.contains("srg/" + member.owner() + ".class")) {
					continue;
				}
				byte[] runtimeBytes = findClass(runtime, member.owner());
				if(runtimeBytes == null) {
					skipped.add(member.owner() + ": no copy in the runtime jars given");
					continue;
				}
				byte[] withMember = addMember(runtimeBytes, member);
				if(withMember == null) {
					skipped.add(member.owner() + "." + member.name() + member.desc()
							+ ": the runtime class already declares it");
					continue;
				}
				copy(out, "srg/" + member.owner() + ".class", withMember);
				written.add("srg/" + member.owner() + ".class");
				supplied.add(member.owner() + "." + member.name() + member.desc()
						+ "  (runtime class " + runtimeBytes.length + " -> " + withMember.length + " bytes)");
			}
		}
		if(dryRun) {
			Files.deleteIfExists(temporary);
		} else {
			Files.move(temporary, payload, StandardCopyOption.REPLACE_EXISTING);
		}
		for(Member member : table()) {
			System.out.println("  reason: " + member.owner() + "." + member.name() + " - " + member.reason());
		}
		for(String line : supplied) {
			System.out.println("  supplied " + line);
		}
		for(String line : skipped) {
			System.out.println("  nothing to do: " + line);
		}
		System.out.println("supplied " + supplied.size() + " Forge-era member(s) into " + payload
				+ (dryRun ? " (dry run, not written)" : ""));
	}

	/** Every entry name of a jar, read before the jar is rewritten. */
	private static List<String> payloadEntries(Path jar) throws IOException {
		List<String> names = new ArrayList<>();
		try(ZipFile zip = new ZipFile(jar.toFile())) {
			for(java.util.Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
				names.add(it.nextElement().getName());
			}
		}
		return names;
	}

	/** The class's bytes with the member added, or null when it is already declared. */
	private static byte[] addMember(byte[] bytes, Member member) {
		ClassNode node = new ClassNode();
		new ClassReader(bytes).accept(node, 0);
		for(MethodNode method : node.methods) {
			if(method.name.equals(member.name()) && method.desc.equals(member.desc())) {
				return null;
			}
		}
		if(!ITEM_TAGS.equals(member.owner()) || !"create".equals(member.name())) {
			throw new IllegalStateException("no body is defined for " + member.owner() + "." + member.name());
		}
		// The body is written from the reader to the writer so that every other part of the runtime
		// class survives byte for byte - it replaces the runtime's own class at run time, so anything
		// dropped here would be a silent change to the game.
		ClassReader reader = new ClassReader(bytes);
		ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
		reader.accept(writer, 0);
		MethodVisitor body = writer.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, member.name(),
				member.desc(), null, null);
		body.visitCode();
		body.visitVarInsn(Opcodes.ALOAD, 0);
		body.visitLdcInsn(FORGE_ERA_NAMESPACE);
		body.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "equals",
				"(Ljava/lang/Object;)Z", false);
		org.objectweb.asm.Label keepGiven = new org.objectweb.asm.Label();
		org.objectweb.asm.Label namespaceReady = new org.objectweb.asm.Label();
		body.visitJumpInsn(Opcodes.IFEQ, keepGiven);
		body.visitLdcInsn(CONVENTIONAL_NAMESPACE);
		body.visitJumpInsn(Opcodes.GOTO, namespaceReady);
		body.visitLabel(keepGiven);
		body.visitVarInsn(Opcodes.ALOAD, 0);
		body.visitLabel(namespaceReady);
		body.visitVarInsn(Opcodes.ALOAD, 1);
		body.visitMethodInsn(Opcodes.INVOKESTATIC, IDENTIFIER, "fromNamespaceAndPath",
				"(Ljava/lang/String;Ljava/lang/String;)L" + IDENTIFIER + ";", false);
		body.visitMethodInsn(Opcodes.INVOKESTATIC, ITEM_TAGS, "create",
				"(L" + IDENTIFIER + ";)L" + TAG_KEY + ";", false);
		body.visitInsn(Opcodes.ARETURN);
		body.visitMaxs(0, 0);
		body.visitEnd();
		writer.visitEnd();
		return writer.toByteArray();
	}

	private static byte[] findClass(List<Path> jars, String name) throws IOException {
		for(Path jar : jars) {
			try(ZipFile zip = new ZipFile(jar.toFile())) {
				ZipEntry entry = zip.getEntry(name + ".class");
				if(entry != null) {
					return readAll(zip.getInputStream(entry));
				}
			}
		}
		return null;
	}

	private static void copy(ZipOutputStream out, String name, byte[] bytes) throws IOException {
		out.putNextEntry(new ZipEntry(name));
		out.write(bytes);
		out.closeEntry();
	}

	private static byte[] readAll(InputStream stream) throws IOException {
		try(stream) {
			return stream.readAllBytes();
		}
	}
}
