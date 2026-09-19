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
		probeLayers();
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
	 * Where OptiFine's own classes live, printed once, because the stack trace of the failure this
	 * answers is not enough to tell which loader it is.
	 *
	 * <p>Measured on 1.21.11, the failure is</p>
	 *
	 * <pre>NoClassDefFoundError: net/minecraft/world/level/storage/ValueOutput
	 *   at Class.getDeclaredMethods0(Native Method)
	 *   at net.optifine.reflect.ReflectorMethod.getMethods(ReflectorMethod.java:263)
	 *   at ... GameRenderer.frameInit(1304)
	 * Caused by: ClassNotFoundException: net.minecraft.world.level.storage.ValueOutput
	 *   at java.net.URLClassLoader.findClass(URLClassLoader.java:445)</pre>
	 *
	 * <p>{@code getDeclaredMethods0} resolves the parameter types of the class it is enumerating with
	 * <em>that class's own</em> defining loader, and the loader in the trace is a plain
	 * {@code URLClassLoader} - which is not what a class in a module layer would be. So the question is
	 * which loader defines {@code net.optifine.reflect.Reflector}, and whether it can see game classes
	 * at all. Both are printed here rather than reasoned about: each candidate loader, its parent chain,
	 * its URLs when it has any, and what it resolves the three names to.</p>
	 */
	private static void probeLayers() {
		if(probed) {
			return;
		}
		probed = true;
		// Off unless asked for, the same way the 1.20.x/1.21.x lines' reload probe is: a payload that
		// prints fifteen lines about class loaders on every launch is noise in a shipped mod, and the
		// question it answers is asked once. Set OPTIFINEOFORGE_DEBUG_LAYERS=1 for the run that needs it.
		if(System.getenv("OPTIFINEOFORGE_DEBUG_LAYERS") == null) {
			return;
		}
		try {
			reportLoader("own", OptifinePayloadClassProcessor.class.getClassLoader());
			reportLoader("system", ClassLoader.getSystemClassLoader());
			reportLoader("context", Thread.currentThread().getContextClassLoader());
		} catch(Throwable t) {
			LOGGER.warn("OptiFine classes probe failed: " + t);
		}
	}

	private static void reportLoader(String label, ClassLoader loader) {
		StringBuilder chain = new StringBuilder();
		for(ClassLoader step = loader; step != null; step = step.getParent()) {
			chain.append(step.getClass().getName());
			if(step instanceof java.net.URLClassLoader urls) {
				// Every URL of every loader in the chain, because the failure turns on exactly that:
				// ReflectorClass.getTargetClass() is "Class.forName(name)" with no loader, so the class
				// that resolves a game type is whichever loader defined ReflectorClass - and a second
				// copy of OptiFine's classes on a plain URL classpath would resolve game names through
				// a loader that never sees the game module layer. Printing only the top of the chain
				// hides precisely the loader that matters when it is not the top.
				for(URL url : urls.getURLs()) {
					chain.append(" [").append(url).append(']');
				}
			}
			chain.append(" -> ");
		}
		LOGGER.info("OptiFine classes probe: " + label + " = "
				+ (loader == null ? "bootstrap" : loader.getClass().getName()) + "; chain " + chain + "bootstrap");
		for(String name : new String[] {"net.optifine.reflect.Reflector",
				"net.optifine.reflect.ReflectorClass",
				"net.minecraft.world.level.storage.ValueOutput",
				"net.minecraft.client.renderer.GameRenderer"}) {
			try {
				Class<?> found = Class.forName(name, false, loader);
				LOGGER.info("OptiFine classes probe: " + label + " sees " + name + " -> module "
						+ found.getModule().getName() + ", defined by "
						+ (found.getClassLoader() == null ? "bootstrap" : found.getClassLoader().getClass().getName()));
			} catch(Throwable t) {
				LOGGER.info("OptiFine classes probe: " + label + " cannot load " + name + ": "
						+ t.getClass().getName() + ": " + t.getMessage());
			}
		}
	}

	private static boolean probed;

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
	 *
	 * <p>And a second exception, measured on 1.21.9 and not on the older lines: members the runtime has
	 * and the payload does not are <em>kept</em>, appended after OptiFine's own. Modern NeoForge patches
	 * the game's sources at build time rather than only transforming them at load time, so its copy of a
	 * class can carry members OptiFine's compilation never had. The failure that named this rule was</p>
	 *
	 * <pre>NoSuchMethodError: 'java.util.List
	 *     net.minecraft.server.packs.resources.ReloadableResourceManager.getListeners()'
	 *   at net.neoforged.neoforge.client.event.AddClientReloadListenersEvent.&lt;init&gt;(...:29)
	 *   at net.neoforged.neoforge.client.ClientHooks.initClientHooks(...:973)
	 *   at net.minecraft.client.Minecraft.&lt;init&gt;(Minecraft.java:665)</pre>
	 *
	 * <p>which is NeoForge's own method, called by NeoForge's own class, against a class this processor
	 * had just replaced with a copy that predates NeoForge's patch. Nothing of the runtime's survives in
	 * the parts OptiFine does define - that is what the paragraph above is about - but a member that
	 * exists <em>only</em> in the runtime has no OptiFine version to conflict with, and dropping it can
	 * only remove something some other NeoForge class was compiled against.</p>
	 */
	private static void copy(ClassNode source, ClassNode target) {
		Map<String, Integer> wasMethods = visibilityOfMethods(target.methods);
		Map<String, Integer> wasFields = visibilityOfFields(target.fields);
		String runtimeSuperName = target.superName;
		java.util.List<String> runtimeInterfaces = new java.util.ArrayList<>(target.interfaces);
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
		// Before the field copies below, because re-parenting can clear the payload's generic signature when
		// it still names the Forge superclass, and target.signature is taken from source.signature.
		String reparentedSuper = reparent(source, runtimeSuperName);
		target.version = source.version;
		target.access = source.access;
		target.name = source.name;
		target.signature = source.signature;
		target.superName = reparentedSuper;
		// Union, payload first: OptiFine's copy marks the class with its own Forge-era interfaces and the
		// runtime's marks it with NeoForge's, and a class can carry both lists. Dropping the runtime's is
		// what the missing getListeners() above is a symptom of; dropping OptiFine's would take away
		// markers its own code tests for.
		target.interfaces = union(source.interfaces, runtimeInterfaces);
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
		int keptFields = 0;
		int keptMethods = 0;
		for(FieldNode field : target.fields) {
			// Keyed by name and descriptor both: a field that exists in both with a different type is
			// OptiFine's to define, and appending the runtime's would be a duplicate-field error.
			if(hasField(source.fields, field.name, field.desc)) {
				continue;
			}
			source.fields.add(field);
			keptFields++;
		}
		for(MethodNode method : target.methods) {
			if(hasMethod(source.methods, method.name, method.desc)) {
				continue;
			}
			source.methods.add(method);
			keptMethods++;
		}
		target.fields = source.fields;
		target.methods = source.methods;
		if(keptFields != 0 || keptMethods != 0) {
			// Logged rather than silent: this is the count that says how much of this class is the
			// runtime's rather than OptiFine's, and on a line where the two disagree it is the number
			// that explains a NoSuchMethodError.
			LOGGER.info("OptiFine payload: " + target.name.replace('/', '.') + " kept " + keptFields
					+ " runtime fields and " + keptMethods + " runtime methods the payload does not define");
		}
	}

	/**
	 * Which superclass the installed class gets, and the constructor chain that has to be rewritten for it.
	 *
	 * <p>Measured on 1.21.9, and this is the second half of the same story as the kept members above: the
	 * payload's {@code BlockEntity} extends {@code net.minecraftforge.common.capabilities
	 * .CapabilityProvider$BlockEntities} (OptiFine's compilation is Forge-era, and that class is one of
	 * the shims), while NeoForge's own copy extends
	 * {@code net.neoforged.neoforge.attachment.AttachmentHolder}, and NeoForge's
	 * {@code BlockEntity.setData} is</p>
	 *
	 * <pre>aload_0 ; invokevirtual setChanged()V
	 * aload_0 ; aload_1 ; aload_2
	 * invokespecial net/neoforged/neoforge/attachment/AttachmentHolder.setData(...)</pre>
	 *
	 * <p>so keeping that member inside a class that is <em>not</em> an {@code AttachmentHolder} produced
	 * {@code VerifyError: Bad invokespecial instruction: current class isn't assignable to reference
	 * class} at {@code BlockEntity.setData @7} - and via {@code FieldLocatorTypes}'s reflection it took
	 * {@code Reflector}'s class initialisation down with it. Taking the runtime's superclass alone is not
	 * enough either, and that was measured too: the payload's own constructor chains to the Forge
	 * superclass's constructor, so the class then fails with
	 * {@code VerifyError: Bad <init> method call ... Type 'net/minecraftforge/common/capabilities
	 * .CapabilityProvider$BlockEntities' is not assignable to 'net/minecraft/world/level/block/entity
	 * .BlockEntity'}. Both halves have to move together, which is what the {@code reparent.txt} plan the
	 * ModLauncher lines already use describes: {@code reparent <class> <runtime superclass>
	 * <constructor>}. The rig produces it with {@code HierarchyPlan}, run against the payload and the
	 * runtime's own jars - on 1.21.9 it finds exactly one movable class, this one, onto
	 * {@code AttachmentHolder} via {@code ()V}.</p>
	 *
	 * <p>Without a plan the payload's superclass is left alone, as it was before this rule existed: an
	 * unplanned rewrite is how the constructor mismatch above was produced.</p>
	 */
	private static String reparent(ClassNode source, String runtimeSuperName) {
		String[] planned = reparents().get(source.name);
		if(planned == null) {
			return source.superName;
		}
		String plannedSuper = planned[0];
		String plannedCtor = planned[1];
		String forgeSuper = source.superName;
		if(forgeSuper == null || !forgeSuper.startsWith("net/minecraftforge/")) {
			LOGGER.warn("OptiFine payload: " + source.name.replace('/', '.') + " is planned to re-parent but its "
					+ "copy extends " + forgeSuper + ", not a Forge type - left alone");
			return forgeSuper;
		}
		if(runtimeSuperName == null || !plannedSuper.equals(runtimeSuperName)) {
			LOGGER.warn("OptiFine payload: " + source.name.replace('/', '.') + " is planned onto " + plannedSuper
					+ " while the runtime's copy extends " + runtimeSuperName + " - the plan and the runtime do "
					+ "not match, so nothing is rewritten");
			return forgeSuper;
		}
		int rewritten = 0;
		for(MethodNode method : source.methods) {
			if(!"<init>".equals(method.name)) {
				continue;
			}
			for(org.objectweb.asm.tree.AbstractInsnNode instruction : method.instructions) {
				if(!(instruction instanceof org.objectweb.asm.tree.MethodInsnNode call)
						|| call.getOpcode() != Opcodes.INVOKESPECIAL
						|| !forgeSuper.equals(call.owner) || !"<init>".equals(call.name)) {
					continue;
				}
				if("()V".equals(plannedCtor)) {
					// The arguments are already on the stack and the runtime's superclass takes none, so
					// they are discarded rather than the pushes deleted: whatever the payload evaluates
					// for them stays evaluated exactly as OptiFine wrote it.
					org.objectweb.asm.Type[] arguments = org.objectweb.asm.Type.getArgumentTypes(call.desc);
					for(int index = arguments.length - 1; index >= 0; index--) {
						method.instructions.insertBefore(call, new org.objectweb.asm.tree.InsnNode(
								arguments[index].getSize() == 2 ? Opcodes.POP2 : Opcodes.POP));
					}
				} else if(!plannedCtor.equals(call.desc)) {
					LOGGER.warn("OptiFine payload: " + source.name.replace('/', '.') + " is planned to call "
							+ plannedCtor + " on the runtime's superclass while its constructor calls "
							+ call.desc + " on the Forge one - nothing is rewritten");
					return forgeSuper;
				}
				call.owner = plannedSuper;
				call.desc = plannedCtor;
				call.itf = false;
				rewritten++;
			}
		}
		if(rewritten == 0) {
			LOGGER.warn("OptiFine payload: " + source.name.replace('/', '.') + " extends " + forgeSuper
					+ " and no constructor of it chains to that superclass - nothing is rewritten");
			return forgeSuper;
		}
		if(source.signature != null && source.signature.contains(forgeSuper)) {
			// The generic signature names the superclass it was compiled against; a stale one is not a
			// verification problem, only a lie to anything that reads it, so it goes.
			source.signature = null;
		}
		LOGGER.info("OptiFine payload: re-parented " + source.name.replace('/', '.') + " from the Forge type "
				+ forgeSuper.replace('/', '.') + " onto the runtime's " + plannedSuper.replace('/', '.')
				+ " (super(" + plannedCtor + "), " + rewritten + " constructor call(s) rewritten)");
		return plannedSuper;
	}

	/**
	 * The reparent plan, read once from this jar at the same path the ModLauncher lines use. The jar is
	 * read directly rather than by resource lookup for the same reason the payload is: {@code srg/} is
	 * where the classes sit and no package claims it, while this file does sit at a normal resource path,
	 * so a jar that is also a mod file can carry it.
	 */
	private static Map<String, String[]> reparents() {
		if(reparents != null) {
			return reparents;
		}
		Map<String, String[]> result = new LinkedHashMap<>();
		try(InputStream stream = OptifinePayloadClassProcessor.class
				.getResourceAsStream("/optifineoforge/reparent.txt")) {
			if(stream == null) {
				LOGGER.info("OptiFine payload: no /optifineoforge/reparent.txt in this jar; no class has its "
						+ "hierarchy rewritten");
			} else {
				java.io.BufferedReader reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(stream, java.nio.charset.StandardCharsets.UTF_8));
				String line;
				while((line = reader.readLine()) != null) {
					String[] parts = line.split("\t");
					if(parts.length == 4 && "reparent".equals(parts[0])) {
						result.put(parts[1], new String[] {parts[2], parts[3]});
					}
				}
				LOGGER.info("OptiFine payload: " + result.size() + " re-parent plan entry(ies)");
			}
		} catch(Throwable t) {
			LOGGER.warn("OptiFine payload: could not read the re-parent plan: " + t);
		}
		reparents = result;
		return reparents;
	}

	private static Map<String, String[]> reparents;

	/** Both interface lists, payload first, each name once. */
	private static java.util.List<String> union(java.util.List<String> payload, java.util.List<String> runtime) {
		java.util.List<String> result = new java.util.ArrayList<>(payload);
		for(String name : runtime) {
			if(!result.contains(name)) {
				result.add(name);
			}
		}
		return result;
	}

	/** Whether a field with this name and descriptor is already in the list. */
	private static boolean hasField(java.util.List<FieldNode> fields, String name, String desc) {
		for(FieldNode field : fields) {
			if(field.name.equals(name) && field.desc.equals(desc)) {
				return true;
			}
		}
		return false;
	}

	/** Whether a method with this name and descriptor is already in the list. */
	private static boolean hasMethod(java.util.List<MethodNode> methods, String name, String desc) {
		for(MethodNode method : methods) {
			if(method.name.equals(name) && method.desc.equals(desc)) {
				return true;
			}
		}
		return false;
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
