/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;

/**
 * Puts back, in bulk, the members OptiFine's replacements drop.
 *
 * <p>The list comes from {@code optifine.MemberRestorePlan}, which computed it offline by comparing
 * the classes OptiFine produces with the classes NeoForge actually has; it travels inside this jar
 * as {@code optifineoforge/member-restores.txt}. Meeting those members one crash at a time during
 * startup is what the first fixes in this package did - a constructor, then a field, then an
 * accessor - and the count was never known. It is 134 members across 40 classes on Minecraft 1.21.4,
 * and this transformer restores all of them in one pass.</p>
 *
 * <p>Most restorations here are honest in shape only: a field gets its declared type, and a method
 * gets a body that returns the default value for its return type. That is enough to stop the game
 * dying on a missing member, and it is not the same as being correct - the methods that need real
 * behaviour are marked in {@link #EXACT} and restored by their own fixes instead, and every stub this
 * transformer adds is logged so the list stays visible.</p>
 */
public final class MemberRestoreTransformer implements ITransformer<ClassNode> {
	private static final Logger LOGGER = LogManager.getLogger("OptifiNeoforge");
	/** The plan, as written by the offline generator. */
	private static final String PLAN_RESOURCE = "/optifineoforge/member-restores.txt";
	private static final String FIELD = "F";
	private static final String METHOD = "M";

	/**
	 * Members that must not be stubbed because their behaviour is what the caller is after; they are
	 * restored by a fix of their own, and skipping them here keeps the result independent of the
	 * order ModLauncher runs the transformers in.
	 */
	private static final Set<String> EXACT = Set.of(
			ReloadableResourceManagerFix.RESOURCE_MANAGER + " getListeners ()Ljava/util/List;",
			ReloadableResourceManagerFix.RESOURCE_MANAGER + " updateListenersFrom (Lnet/neoforged/neoforge/event/SortedReloadListenerEvent;)V");

	/** Class to the fields and methods to restore, in plan order. */
	private final Map<String, Set<String>> fields = new LinkedHashMap<>();
	private final Map<String, Set<String>> methods = new LinkedHashMap<>();

	public MemberRestoreTransformer() {
		int count = 0;
		try(InputStream stream = MemberRestoreTransformer.class.getResourceAsStream(PLAN_RESOURCE)) {
			if(stream == null) {
				LOGGER.warn("No member restore plan in this jar (" + PLAN_RESOURCE + "); nothing will be restored");
				return;
			}
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
				String line;
				while((line = reader.readLine()) != null) {
					String[] parts = line.trim().split(" ", 4);
					if(parts.length < 4) {
						continue;
					}
					String member = parts[2] + " " + parts[3];
					if(EXACT.contains(parts[1] + " " + member)) {
						continue;
					}
					if(FIELD.equals(parts[0])) {
						fields.computeIfAbsent(parts[1], key -> new LinkedHashSet<>()).add(member);
					} else if(METHOD.equals(parts[0])) {
						methods.computeIfAbsent(parts[1], key -> new LinkedHashSet<>()).add(member);
					}
					count++;
				}
			}
		} catch(IOException e) {
			LOGGER.error("Could not read the member restore plan", e);
			return;
		}
		LOGGER.info("Member restore plan: " + count + " members across " + (fields.size() + methods.size()) + " classes");
	}

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		Set<String> missingFields = fields.get(input.name);
		if(missingFields != null) {
			for(String member : missingFields) {
				String[] parts = member.split(" ");
				if(!hasField(input, parts[0])) {
					input.fields.add(new FieldNode(Opcodes.ACC_PUBLIC, parts[0], parts[1], null, null));
					LOGGER.info("Restored field " + input.name + "." + parts[0] + " " + parts[1]);
				}
			}
		}

		Set<String> missingMethods = methods.get(input.name);
		if(missingMethods != null) {
			for(String member : missingMethods) {
				String[] parts = member.split(" ");
				if(!hasMethod(input, parts[0], parts[1])) {
					input.methods.add(stub(parts[0], parts[1]));
					LOGGER.info("Restored method " + input.name + "." + parts[0] + parts[1] + " as a default-value stub");
				}
			}
		}
		return input;
	}

	/** A method of the right shape whose body returns the default value for its return type. */
	private static MethodNode stub(String name, String descriptor) {
		MethodNode method = new MethodNode(Opcodes.ACC_PUBLIC, name, descriptor, null, null);
		InsnList body = method.instructions;
		String returns = descriptor.substring(descriptor.lastIndexOf(')') + 1);
		switch(returns) {
			case "V" -> body.add(new InsnNode(Opcodes.RETURN));
			case "J" -> {
				body.add(new InsnNode(Opcodes.LCONST_0));
				body.add(new InsnNode(Opcodes.LRETURN));
			}
			case "D" -> {
				body.add(new InsnNode(Opcodes.DCONST_0));
				body.add(new InsnNode(Opcodes.DRETURN));
			}
			case "F" -> {
				body.add(new InsnNode(Opcodes.FCONST_0));
				body.add(new InsnNode(Opcodes.FRETURN));
			}
			case "Z", "B", "C", "S", "I" -> {
				body.add(new InsnNode(Opcodes.ICONST_0));
				body.add(new InsnNode(Opcodes.IRETURN));
			}
			default -> {
				body.add(new InsnNode(Opcodes.ACONST_NULL));
				body.add(new InsnNode(Opcodes.ARETURN));
			}
		}
		method.maxStack = 2;
		method.maxLocals = countArguments(descriptor) + 1;
		return method;
	}

	private static int countArguments(String descriptor) {
		int count = 0;
		for(int index = 1; index < descriptor.indexOf(')'); index++) {
			char c = descriptor.charAt(index);
			switch(c) {
				case 'L' -> {
					index = descriptor.indexOf(';', index);
					count++;
				}
				case '[' -> {
					// counted with the element type
				}
				case 'J', 'D' -> count += 2;
				default -> count++;
			}
		}
		return count;
	}

	private static boolean hasField(ClassNode node, String name) {
		for(FieldNode field : node.fields) {
			if(name.equals(field.name)) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasMethod(ClassNode node, String name, String descriptor) {
		for(MethodNode method : node.methods) {
			if(name.equals(method.name) && descriptor.equals(method.desc)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public Set<Target<ClassNode>> targets() {
		Set<String> names = new LinkedHashSet<>(fields.keySet());
		names.addAll(methods.keySet());
		Set<Target<ClassNode>> targets = new LinkedHashSet<>();
		for(String name : names) {
			targets.add(Target.targetClass(name));
		}
		return targets;
	}

	@Override
	public TargetType<ClassNode> getTargetType() {
		return TargetType.CLASS;
	}
}
