/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.util.Set;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;

/**
 * Hands the reload listener list to {@link ReloadProbe} as the reload is created.
 *
 * <p>{@code createReload} loads {@code this.listeners} and passes it straight to
 * {@code SimpleReloadInstance.create}, so the list read there is exactly the list that will be run,
 * in that order. The probe is a duplicate left on the stack, so nothing about the reload changes.</p>
 *
 * <p>This is a diagnostic, not a repair: it is the measurement that says whether the null-model
 * failure on the first reload is an ordering problem at all. OptiFine replaces
 * {@code ReloadableResourceManager} too, so the list it uses is the one NeoForge's restored
 * {@code updateListenersFrom} was supposed to have replaced, and seeing the real order is the only
 * way to tell which of the two won.</p>
 */
public final class ReloadProbeFix implements ITransformer<ClassNode> {
	/** The class whose reload the probe watches. */
	static final String RELOADABLE = "net/minecraft/server/packs/resources/ReloadableResourceManager";
	/** The method that turns the list into a reload, and the field it reads to get it. */
	private static final String CREATE_RELOAD = "createReload";
	private static final String LISTENERS = "listeners";
	/** Where listeners enter the list, and where the list is replaced by the sorted one. */
	private static final String REGISTER = "registerReloadListener";
	private static final String UPDATE = "updateListenersFrom";
	private static final String PROBE = "kynarain/cn/optifineoforge/loader/ReloadProbe";

	/**
	 * The reload's own per-listener task, which is where a reload stops when one listener never finishes.
	 *
	 * <p>Called {@code lambda$of$0} in {@code SimpleReloadInstance}, and static with the listener as its
	 * fourth parameter - which is why the injection loads local 3. Instrumenting it is what turns "the reload
	 * never completes" into "this listener's task started and never returned", which is the difference
	 * between a diagnosis and a guess: on 1.21 the reload starts with its 28 listeners, the log's last
	 * activity is OptiFine's connected-texture parsing, and every thread is idle afterwards.</p>
	 */
	private static final String LISTENER_TASK = "lambda$of$0";

	/** {@code SimpleReloadInstance}, the class that owns the per-listener task. */
	private static final String SIMPLE_RELOAD = "net/minecraft/server/packs/resources/SimpleReloadInstance";

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		for(MethodNode method : input.methods) {
			if(CREATE_RELOAD.equals(method.name)) {
				probeCreateReload(input, method);
			} else if(LISTENER_TASK.equals(method.name) && SIMPLE_RELOAD.equals(input.name)) {
				probeListenerTask(method);
			} else if(REGISTER.equals(method.name)) {
				// Who is registered, and when - the list ends up holding some of them twice.
				InsnList call = new InsnList();
				call.add(new VarInsnNode(Opcodes.ALOAD, 1));
				call.add(new LdcInsnNode("registerReloadListener"));
				call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, "value",
						"(Ljava/lang/Object;Ljava/lang/String;)V", false));
				method.instructions.insert(call);
			} else if(UPDATE.equals(method.name)) {
				probeSize(input, method, "before updateListenersFrom");
				for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
					if(insn.getOpcode() == Opcodes.RETURN) {
						insertSize(input, method, insn, "after updateListenersFrom");
					}
				}
			}
		}
		return input;
	}

	/** Marks one listener's task as started, and as finished when it returns. */
	private static void probeListenerTask(MethodNode method) {
		method.instructions.insert(taskCall("started"));
		probeBarrier(method);
		for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			if(insn.getOpcode() == Opcodes.RETURN) {
				method.instructions.insertBefore(insn, taskCall("finished"));
			}
		}
	}

	/**
	 * Marks the listeners that reach the reload's barrier.
	 *
	 * <p>Vanilla hands each listener a {@code PreparationBarrier} and waits for every one of them, so a
	 * single listener that never calls it holds the whole reload open - measured on 1.21 as 28 tasks started
	 * and none finished. Listing the ones that do arrive names the one that does not, which is the whole
	 * question at that point.</p>
	 */
	private static void probeBarrier(MethodNode method) {
		for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			if(insn instanceof MethodInsnNode call && BARRIER.equals(call.owner) && BARRIER_WAIT.equals(call.name)) {
				method.instructions.insert(insn, barrierCall());
			}
		}
	}

	/** {@code PreparableReloadListener$PreparationBarrier}, and the method everyone has to reach. */
	private static final String BARRIER = "net/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier";

	private static final String BARRIER_WAIT = "wait";

	private static InsnList barrierCall() {
		InsnList call = new InsnList();
		call.add(new VarInsnNode(Opcodes.ALOAD, 3));
		call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, "reachedBarrier", "(Ljava/lang/Object;)V", false));
		return call;
	}

	private static InsnList taskCall(String probeMethod) {
		InsnList call = new InsnList();
		call.add(new VarInsnNode(Opcodes.ALOAD, 3));
		call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, probeMethod, "(Ljava/lang/Object;)V", false));
		return call;
	}

	/** Hands the list the reload will actually run to the probe. */
	private static void probeCreateReload(ClassNode input, MethodNode method) {
		for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
			if(!(insn instanceof FieldInsnNode field) || field.getOpcode() != Opcodes.GETFIELD
					|| !input.name.equals(field.owner) || !LISTENERS.equals(field.name)) {
				continue;
			}
			InsnList probe = new InsnList();
			probe.add(new InsnNode(Opcodes.DUP));
			probe.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, "listeners", "(Ljava/util/List;)V", false));
			method.instructions.insert(insn, probe);
			return;
		}
	}

	private static void probeSize(ClassNode input, MethodNode method, String label) {
		method.instructions.insert(sizeCall(input, label));
	}

	private static void insertSize(ClassNode input, MethodNode method, AbstractInsnNode before, String label) {
		method.instructions.insertBefore(before, sizeCall(input, label));
	}

	/** Reads the manager's own listener list and reports how long it is. */
	private static InsnList sizeCall(ClassNode input, String label) {
		InsnList call = new InsnList();
		call.add(new VarInsnNode(Opcodes.ALOAD, 0));
		call.add(new FieldInsnNode(Opcodes.GETFIELD, input.name, LISTENERS, "Ljava/util/List;"));
		call.add(new LdcInsnNode(label));
		call.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, "size",
				"(Ljava/util/List;Ljava/lang/String;)V", false));
		return call;
	}

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public Set<Target<ClassNode>> targets() {
		return Set.of(Target.targetClass(RELOADABLE), Target.targetClass(SIMPLE_RELOAD));
	}

	@Override
	public TargetType<ClassNode> getTargetType() {
		return TargetType.CLASS;
	}
}
