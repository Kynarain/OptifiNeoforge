/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.optifine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * What an OptiFine jar contains, and how to turn it into something NeoForge will accept.
 *
 * <p>OptiFine ships the same artefact to every loader: an installer-or-mod jar whose Forge
 * integration is a ModLauncher service, plus the patch payload it applies to the game. Its
 * metadata is Forge's, though - {@code META-INF/mods.toml} with a Forge loader version range -
 * and NeoForge expects its own file with its own fields. That rewrite is what this class does,
 * and it is deliberately free of any Minecraft, NeoForge or loader dependency so that it can be
 * inspected and tested on its own.</p>
 *
 * <p>Nothing here decides *when* the patched classes get installed; that is the loader's job.
 * This class only has to answer two questions: what is in the jar, and what would make the
 * loader read it.</p>
 *
 * <p>{@link #main} is a development aid: it prints the layout of a jar, or writes the rewritten
 * copy, without needing a Minecraft installation.</p>
 */
public final class OptifineJar {
	private static final String MANIFEST = "META-INF/MANIFEST.MF";
	private static final String SERVICES = "META-INF/services/";
	/** The Forge-era metadata file name. */
	public static final String FORGE_METADATA = "META-INF/mods.toml";
	/** The NeoForge metadata file name. */
	public static final String NEOFORGE_METADATA = "META-INF/neoforge.mods.toml";

	private OptifineJar() {
	}

	/**
	 * A read-only description of one OptiFine jar.
	 *
	 * @param size                 file size in bytes
	 * @param entries              number of zip entries
	 * @param manifest             the main attributes of {@code META-INF/MANIFEST.MF}
	 * @param metadataName         {@code META-INF/mods.toml}, {@code META-INF/neoforge.mods.toml},
	 *                             or {@code null} when the jar carries neither
	 * @param metadata             the text of that file, or {@code null}
	 * @param services             every {@code META-INF/services/*} file and the implementations
	 *                             it names
	 * @param transformationService the ModLauncher transformation service OptiFine registers,
	 *                             taken from that service file, or {@code null}
	 * @param patchRoots           top-level directory to number of entries, for the directories
	 *                             that carry content (the whole {@code optifine/} package counts
	 *                             as one root)
	 * @param patchSuffixes        the file-name suffixes seen under the patch directories, which
	 *                             is how the payload format is recognised
	 */
	public record Layout(long size, int entries, Map<String, String> manifest, String metadataName,
			String metadata, Map<String, List<String>> services, String transformationService,
			Map<String, Integer> patchRoots, Set<String> patchSuffixes) {

		/** Whether the jar still needs its Forge metadata replaced before NeoForge will read it. */
		public boolean needsNeoForgeMetadata() {
			return !NEOFORGE_METADATA.equals(metadataName);
		}

		/** Whether the jar carries OptiFine's own Forge integration rather than only its classes. */
		public boolean hasTransformationService() {
			return transformationService != null;
		}

		/** Whether the jar is the installer form: it carries xdelta packages to apply itself. */
		public boolean isInstaller() {
			return patchRoots.containsKey("patch");
		}

		/** One line per fact, for the command line tool and for logging. */
		public String describe() {
			StringBuilder out = new StringBuilder();
			out.append("size            : ").append(size).append(" bytes\n");
			out.append("entries         : ").append(entries).append('\n');
			out.append("metadata        : ").append(metadataName == null ? "(none)" : metadataName).append('\n');
			out.append("transform svc   : ").append(transformationService == null ? "(none)" : transformationService).append('\n');
			out.append("installer form  : ").append(isInstaller()).append('\n');
			out.append("patch roots     : ").append(patchRoots).append('\n');
			out.append("patch suffixes  : ").append(patchSuffixes).append('\n');
			for(Map.Entry<String, String> e : new TreeMap<>(manifest).entrySet()) {
				out.append("manifest ").append(e.getKey()).append(": ").append(e.getValue()).append('\n');
			}
			for(Map.Entry<String, List<String>> e : new TreeMap<>(services).entrySet()) {
				out.append("service         : ").append(e.getKey()).append(" -> ").append(e.getValue()).append('\n');
			}
			return out.toString();
		}
	}

	/** Reads the layout of {@code jar}. The file is only read, never modified. */
	public static Layout inspect(Path jar) throws IOException {
		long size = Files.size(jar);
		int entries = 0;
		Map<String, String> manifest = new LinkedHashMap<>();
		Map<String, List<String>> services = new LinkedHashMap<>();
		Map<String, Integer> roots = new TreeMap<>();
		Set<String> suffixes = new LinkedHashSet<>();
		String metadataName = null;
		String metadata = null;

		try(ZipFile zip = new ZipFile(jar.toFile())) {
			for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
				ZipEntry entry = it.nextElement();
				entries++;
				String name = entry.getName();
				if(entry.isDirectory()) {
					continue;
				}

				if(MANIFEST.equalsIgnoreCase(name)) {
					manifest.putAll(readManifest(zip.getInputStream(entry)));
					continue;
				}

				if(FORGE_METADATA.equals(name) || NEOFORGE_METADATA.equals(name)) {
					// A jar should carry one of them; if a rewritten jar carries both, the
					// NeoForge one is the interesting one.
					if(metadataName == null || NEOFORGE_METADATA.equals(name)) {
						metadataName = name;
						metadata = read(zip.getInputStream(entry));
					}
					continue;
				}

				if(name.startsWith(SERVICES) && name.indexOf('/', SERVICES.length()) < 0) {
					String service = name.substring(SERVICES.length());
					List<String> impls = new ArrayList<>();
					for(String line : read(zip.getInputStream(entry)).split("\\R")) {
						String trimmed = line.trim();
						if(!trimmed.isEmpty() && !trimmed.startsWith("#")) {
							impls.add(trimmed);
						}
					}
					services.put(service, impls);
					continue;
				}

				int slash = name.indexOf('/');
				String root = slash < 0 ? "(default package)" : name.substring(0, slash);
				roots.merge(root, 1, Integer::sum);
				if(root.equals("srg") || root.equals("notch") || root.equals("patch")) {
					suffixes.add(suffixOf(name));
				}
			}
		}

		String service = null;
		List<String> modLauncher = services.get("cpw.mods.modlauncher.api.ITransformationService");
		if(modLauncher != null && !modLauncher.isEmpty()) {
			service = modLauncher.get(0);
		}

		return new Layout(size, entries, manifest, metadataName, metadata, services, service, roots, suffixes);
	}

	/**
	 * Writes {@code in} to {@code out} with its metadata replaced by {@code metadataText} under
	 * {@code metadataName}, dropping the other metadata file if the jar had one.
	 *
	 * <p>An already rewritten jar is left alone rather than rewritten twice, so the call is safe
	 * to repeat on a cached copy.</p>
	 */
	public static void rewriteMetadata(Path in, Path out, String metadataName, String metadataText) throws IOException {
		Path parent = out.toAbsolutePath().getParent();
		if(parent != null) {
			Files.createDirectories(parent);
		}
		Path temp = Files.createTempFile(parent, "optifine-", ".jar");
		try {
			try(ZipFile zip = new ZipFile(in.toFile());
					ZipOutputStream target = new ZipOutputStream(Files.newOutputStream(temp))) {
				for(Enumeration<? extends ZipEntry> it = zip.entries(); it.hasMoreElements();) {
					ZipEntry entry = it.nextElement();
					String name = entry.getName();
					if(FORGE_METADATA.equals(name) || NEOFORGE_METADATA.equals(name)) {
						continue; // replaced below
					}
					ZipEntry copy = new ZipEntry(name);
					copy.setTime(entry.getTime());
					target.putNextEntry(copy);
					if(!entry.isDirectory()) {
						try(InputStream stream = zip.getInputStream(entry)) {
							stream.transferTo(target);
						}
					}
					target.closeEntry();
				}

				ZipEntry metadata = new ZipEntry(metadataName);
				target.putNextEntry(metadata);
				target.write(metadataText.getBytes(StandardCharsets.UTF_8));
				target.closeEntry();
			}
			Files.move(temp, out, StandardCopyOption.REPLACE_EXISTING);
		} finally {
			Files.deleteIfExists(temp);
		}
	}

	private static String suffixOf(String name) {
		int lastDot = name.lastIndexOf('.');
		if(lastDot < 0) {
			return "(no extension)";
		}
		// ".class.xdelta" has to stay recognisable as a whole, not collapse to ".xdelta".
		int previousDot = name.lastIndexOf('.', lastDot - 1);
		return previousDot < 0 ? name.substring(lastDot) : name.substring(previousDot);
	}

	/** The main attributes of a manifest, folded to lower case keys. */
	private static Map<String, String> readManifest(InputStream stream) throws IOException {
		Map<String, String> attributes = new LinkedHashMap<>();
		String text = read(stream).replace("\r\n", "\n").replace('\r', '\n');
		for(String line : text.split("\n")) {
			if(line.isEmpty() || line.startsWith(" ")) {
				continue; // continuation lines are not interesting for us
			}
			int colon = line.indexOf(':');
			if(colon > 0) {
				attributes.put(line.substring(0, colon).trim().toLowerCase(Locale.ROOT), line.substring(colon + 1).trim());
			}
		}
		return attributes;
	}

	private static String read(InputStream stream) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		stream.transferTo(buffer);
		return buffer.toString(StandardCharsets.UTF_8);
	}

	/**
	 * Development aid: {@code OptifineJar <jar>} prints the layout, and
	 * {@code OptifineJar <in> <out> <metadata file>} writes the rewritten copy with the metadata
	 * read from standard input... which is awkward from a shell, so the second form instead reads
	 * a template file: {@code OptifineJar <in> <out> <metadata file> <template file>}.
	 */
	public static void main(String[] args) throws IOException {
		if(args.length == 1) {
			System.out.print(inspect(Path.of(args[0])).describe());
			return;
		}
		if(args.length == 4) {
			Path in = Path.of(args[0]);
			Path out = Path.of(args[1]);
			String metadataName = args[2];
			String template = Files.readString(Path.of(args[3]), StandardCharsets.UTF_8);
			Layout layout = inspect(in);
			rewriteMetadata(in, out, metadataName, template);
			System.out.println("wrote " + out + " (" + Files.size(out) + " bytes)");
			System.out.println("was : " + (layout.metadataName() == null ? "(no metadata)" : layout.metadataName()));
			System.out.println("now : " + inspect(out).metadataName());
			return;
		}
		System.err.println("usage: OptifineJar <jar>");
		System.err.println("       OptifineJar <in> <out> <metadata file> <template file>");
		System.exit(2);
	}
}
