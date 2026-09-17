/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.fml10;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.neoforged.neoforgespi.transformation.ClassProcessor;
import net.neoforged.neoforgespi.transformation.ProcessorName;
import net.neoforged.neoforgespi.transformation.SimpleClassProcessor;
import net.neoforged.neoforgespi.transformation.SimpleTransformationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * The mount point for the FML 10 lines (1.21.9 / 1.21.10 / 1.21.11), where ModLauncher no longer
 * exists and OptiFine's own jar therefore has nothing to register with.
 *
 * <p>Why this class has to exist at all, measured rather than assumed: FML 10.0.36 has no
 * {@code cpw.mods.modlauncher} classes and no {@code ModDirTransformerDiscoverer} service, and the
 * main class of the 21.11.45 profile is {@code net.neoforged.fml.startup.Client}. OptiFine's
 * 1.21.11 build ({@code OptiFine_1.21.11_HD_U_J9.jar}) declares exactly one service file,
 * {@code META-INF/services/cpw.mods.modlauncher.api.ITransformationService}, so on these versions
 * nothing OptiFine ships can ever be called. Its 26.1.2 build does carry a
 * {@code ClassProcessor}, which is what made that line's mount point unnecessary - this class is
 * that same job for the three versions whose OptiFine builds predate the API.</p>
 *
 * <p>What it installs comes from the offline route, exactly as on 26.1.2: OptiFine ships patch data
 * ({@code patch/srg/**}); the rig applies it to the vanilla archive in advance and ships the
 * finished classes under {@code srg/} inside this same jar, together with the members NeoForge
 * added back, the re-parented hierarchies and the Forge API shims. So at runtime there is no
 * patching to do here: {@link #transform} copies the finished class over the game's own.</p>
 *
 * <p>Registered through {@code META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor},
 * which is how FML 10 loads processors from a mod file
 * ({@code FMLLoader.createClassProcessorSet} -> {@code ServiceLoaderUtil.loadServices}).</p>
 */
public final class OptifinePayloadClassProcessor extends SimpleClassProcessor {
	private static final Logger LOGGER = LogManager.getLogger("OptifiNeoforge");

	/** Where the rig puts the finished game classes: OptiFine's own layout, one directory up. */
	private static final String PAYLOAD_ROOT = "srg/";

	/**
	 * The finished classes, read once. Read from this class's own jar rather than through a
	 * resource lookup, because the payload sits at a path ({@code srg/}) that no package claims and
	 * a jar that is also a mod file is not always reachable by resource name.
	 */
	private static Map<String, byte[]> payload;

	private static int installed;

	public OptifinePayloadClassProcessor() {
		LOGGER.info("OptifiNeoforge: OptifinePayloadClassProcessor constructed (FML 10 mount point)");
	}

	@Override
	public ProcessorName name() {
		return new ProcessorName("optifineoforge", "payload");
	}

	@Override
	public Set<Target> targets() {
		Set<Target> result = new TreeSet<>((left, right) -> left.className().compareTo(right.className()));
		for(String name : payload().keySet()) {
			// Binary names, with dots: this is what SimpleClassProcessor.handlesClass compares against
			// (SelectionContext.type().getClassName()) and what SimpleClassProcessor.Target's own
			// validation accepts (its constructor calls ClassDesc.of, which is a binary-name parser).
			// Passing the internal form with slashes threw
			//   IllegalArgumentException: Invalid class name: com/mojang/blaze3d/buffers/GpuBuffer$MappedView
			// out of NameValidation.validateClassName, inside handlesClass, on the very first class FML
			// asked about - so nothing was ever installed. The '$' of a nested class stays as it is:
			// ASM's Type.getClassName() keeps it too.
			result.add(new Target(name.replace('/', '.')));
		}
		return result;
	}

	@Override
	public void transform(ClassNode node, SimpleTransformationContext context) {
		byte[] bytes = payload().get(node.name);
		if(bytes == null) {
			return;
		}
		ClassNode finished = new ClassNode();
		new ClassReader(bytes).accept(finished, 0);
		copy(finished, node);
		installed++;
		LOGGER.info("OptiFine payload: installed " + node.name.replace('/', '.') + " (" + finished.fields.size()
				+ " fields, " + finished.methods.size() + " methods) [" + installed + " so far]");
	}

	/**
	 * Copies one class node over another, the same way OptiFine's own newer processor does it: every
	 * structural part is taken from the source, so nothing of the runtime's copy survives - a field
	 * or an interface left behind would be a class that compiles differently from the one OptiFine
	 * was tested with.
	 *
	 * <p>With one exception, and it is the rule this project paid for twice on the ModLauncher lines:
	 * a <em>member's</em> visibility keeps whichever of the two copies is wider. The class flags are
	 * OptiFine's, but NeoForge's access transformers widen members of its own copy, and NeoForge's
	 * code then relies on the widened form. Installing OptiFine's copy wholesale made
	 * {@code NeoForgeRenderTypes$Internal} fail with
	 * {@code IllegalAccessError: ... tried to access method 'RenderType create(String, RenderSetup)'},
	 * because OptiFine's compilation of that method is narrower than NeoForge's widened one.</p>
	 */
	private static void copy(ClassNode source, ClassNode target) {
		Map<String, Integer> wasMethods = visibilityOfMethods(target.methods);
		Map<String, Integer> wasFields = visibilityOfFields(target.fields);
		boolean targetIsInterface = (target.access & Opcodes.ACC_INTERFACE) != 0;
		for(FieldNode field : source.fields) {
			int merged = wider(field.access, wasFields.get(field.name + field.desc));
			if(targetIsInterface) {
				// An interface's fields have to be public static final whatever either copy says, and
				// the JVM rejects the class outright otherwise ("ClassFormatError: Illegal field
				// modifiers"), which is measured on the 1.21.x lines of this project.
				merged = (merged & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED))
						| Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL;
			} else if(!wasFields.containsKey(field.name + field.desc)
					|| (wasFields.get(field.name + field.desc) & Opcodes.ACC_FINAL) == 0) {
				// final is not inherited from the payload: NeoForge's access transformers strip it
				// from fields its own subclasses assign, and a swapped class carrying the payload's
				// final bit then rejects that assignment ("IllegalAccessError: Update to non-static
				// final field ... attempted from a different class"). static is deliberately left as
				// the payload has it.
				merged &= ~Opcodes.ACC_FINAL;
			}
			field.access = merged;
		}
		for(MethodNode method : source.methods) {
			method.access = wider(method.access, wasMethods.get(method.name + method.desc));
		}
		target.version = source.version;
		target.access = source.access;
		target.name = source.name;
		target.signature = source.signature;
		target.superName = source.superName;
		target.interfaces = source.interfaces;
		target.sourceFile = source.sourceFile;
		target.sourceDebug = source.sourceDebug;
		target.module = source.module;
		target.outerClass = source.outerClass;
		target.outerMethod = source.outerMethod;
		target.outerMethodDesc = source.outerMethodDesc;
		target.visibleAnnotations = source.visibleAnnotations;
		target.invisibleAnnotations = source.invisibleAnnotations;
		target.visibleTypeAnnotations = source.visibleTypeAnnotations;
		target.invisibleTypeAnnotations = source.invisibleTypeAnnotations;
		target.attrs = source.attrs;
		target.innerClasses = source.innerClasses;
		target.nestHostClass = source.nestHostClass;
		target.nestMembers = source.nestMembers;
		target.permittedSubclasses = source.permittedSubclasses;
		target.recordComponents = source.recordComponents;
		target.fields = source.fields;
		target.methods = source.methods;
	}

	/** The three access bits that say who may use a member; everything else in the word is not visibility. */
	private static final int VISIBILITY = Opcodes.ACC_PUBLIC | Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED;

	/** Visibility of each declared method, keyed by name and descriptor, for the wider-of-two rule. */
	private static Map<String, Integer> visibilityOfMethods(java.util.List<MethodNode> methods) {
		Map<String, Integer> result = new java.util.HashMap<>();
		for(MethodNode method : methods) {
			result.putIfAbsent(method.name + method.desc, method.access);
		}
		return result;
	}

	private static Map<String, Integer> visibilityOfFields(java.util.List<FieldNode> fields) {
		Map<String, Integer> result = new java.util.HashMap<>();
		for(FieldNode field : fields) {
			result.putIfAbsent(field.name + field.desc, field.access);
		}
		return result;
	}

	/** {@code access}, with its visibility replaced by the wider of its own and {@code other}'s. */
	private static int wider(int access, Integer other) {
		if(other == null) {
			return access;
		}
		if(rank(other) <= rank(access)) {
			return access;
		}
		return (access & ~VISIBILITY) | (other & VISIBILITY);
	}

	/** public 3, protected 2, package 1, private 0 - what "wider" means for the merge above. */
	private static int rank(int access) {
		if((access & Opcodes.ACC_PUBLIC) != 0) {
			return 3;
		}
		if((access & Opcodes.ACC_PROTECTED) != 0) {
			return 2;
		}
		return (access & Opcodes.ACC_PRIVATE) != 0 ? 0 : 1;
	}

	private static synchronized Map<String, byte[]> payload() {		if(payload != null) {
			return payload;
		}
		Map<String, byte[]> read = new LinkedHashMap<>();
		try {
			URL own = OptifinePayloadClassProcessor.class.getProtectionDomain().getCodeSource().getLocation();
			LOGGER.info("OptiFine payload: reading finished classes from " + own);
			File file = new File(own.toURI());
			try (ZipFile zip = new ZipFile(file)) {
				java.util.Enumeration<? extends ZipEntry> entries = zip.entries();
				while(entries.hasMoreElements()) {
					ZipEntry entry = entries.nextElement();
					String entryName = entry.getName();
					if(!entryName.startsWith(PAYLOAD_ROOT) || !entryName.endsWith(".class")) {
						continue;
					}
					String name = entryName.substring(PAYLOAD_ROOT.length(), entryName.length() - ".class".length());
					// OptiFine's own classes live under the same root and are loaded as ordinary
					// classes from the classes mod file, not installed over a game class.
					if(name.startsWith("net/optifine/") || name.startsWith("net/minecraftforge/")) {
						continue;
					}
					try (InputStream in = zip.getInputStream(entry)) {
						read.put(name, readAll(in));
					}
				}
			}
		} catch(Throwable t) {
			LOGGER.error("OptiFine payload: could not read the finished classes - nothing will be installed", t);
		}
		LOGGER.info("OptiFine payload: " + read.size() + " finished game classes");
		payload = read;
		return payload;
	}

	private static byte[] readAll(InputStream in) throws java.io.IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(8192);
		byte[] buffer = new byte[8192];
		int count;
		while((count = in.read(buffer)) > 0) {
			out.write(buffer, 0, count);
		}
		return out.toByteArray();
	}
}
