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
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

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
	private static final String PROBE = "kynarain/cn/optifineoforge/loader/ReloadProbe";

	@Override
	public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
		for(MethodNode method : input.methods) {
			if(!CREATE_RELOAD.equals(method.name)) {
				continue;
			}
			for(AbstractInsnNode insn = method.instructions.getFirst(); insn != null; insn = insn.getNext()) {
				if(!(insn instanceof FieldInsnNode field) || field.getOpcode() != Opcodes.GETFIELD
						|| !input.name.equals(field.owner) || !LISTENERS.equals(field.name)) {
					continue;
				}
				InsnList probe = new InsnList();
				probe.add(new InsnNode(Opcodes.DUP));
				probe.add(new MethodInsnNode(Opcodes.INVOKESTATIC, PROBE, "listeners", "(Ljava/util/List;)V", false));
				method.instructions.insert(insn, probe);
				return input;
			}
		}
		return input;
	}

	@Override
	public TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public Set<Target<ClassNode>> targets() {
		return Set.of(Target.targetClass(RELOADABLE));
	}

	@Override
	public TargetType<ClassNode> getTargetType() {
		return TargetType.CLASS;
	}
}
