package kynarain.cn.optifineoforge.optifine;

import java.nio.charset.StandardCharsets;

/**
 * Points OptiFine's FXAA post chain at the vertex stage this game version actually ships, so FXAA can compile.
 *
 * <p>Measured 2026-09-24 on 1.21.9, with FXAA requested for real (optionsshaders.txt {@code antialiasingLevel=2},
 * the option whose GUI labels are "FXAA 2x"/"FXAA 4x"): the client logs</p>
 *
 * <pre>[Render thread/ERROR] [com.mojang.blaze3d.opengl.GlDevice/]: Couldn't find source for VERTEX shader (minecraft:post/blit)
 * [Render thread/ERROR] [com.mojang.blaze3d.opengl.GlDevice/]: Couldn't compile pipeline minecraft:fxaa_of_2x/1: vertex shader minecraft:post/blit was invalid</pre>
 *
 * <p>FXAA is therefore engaged - OptiFine ships its chain at the post-1.21.6 location
 * ({@code assets/minecraft/post_effect/fxaa_of_2x.json} plus its own
 * {@code assets/minecraft/shaders/post/fxaa_of_2x.{vsh,fsh}}), the game found the JSON and started building the
 * pipeline - and then the second pass fails, because it asks for a vertex shader source
 * {@code minecraft:post/blit} that does not exist: 1.21.9 ships {@code assets/minecraft/shaders/post/blit.fsh} (the
 * fragment stage) but no {@code post/blit.vsh}, and every one of the game's own post-effect passes uses
 * {@code minecraft:core/screenquad} as its vertex stage instead - for example its own blit pass in
 * {@code post_effect/entity_outline.json} is exactly {@code core/screenquad} + {@code post/blit} + {@code BlitConfig}.
 * FXAA stops silently; nothing crashes.</p>
 *
 * <p>So the repair is one field, in the blit pass of both chains: {@code vertex_shader} becomes
 * {@code minecraft:core/screenquad}, which turns OptiFine's blit pass into the same pair the game itself uses. The
 * FXAA pass is left alone - it names OptiFine's own {@code post/fxaa_of_2x} vertex stage, which this jar carries and
 * which compiled (the log named pass {@code /1}, not {@code /0}).</p>
 *
 * <p>Applied by {@link OptifinePipeline#split} while OptiFine's resources are written into the classpath jar, and
 * reported either way: on a line whose JSON already names a vertex stage it does not touch anything.</p>
 */
public final class FxaaPostChainRepair {
	/** The two chains OptiFine ships, at the location the post-1.21.6 resource layout uses. */
	public static final String[] ENTRIES = {
		"assets/minecraft/post_effect/fxaa_of_2x.json",
		"assets/minecraft/post_effect/fxaa_of_4x.json"
	};

	/** The blit pass as OptiFine writes it, and what the game's own blit passes use. */
	private static final String BLIT_PAIR = "\"vertex_shader\": \"minecraft:post/blit\",";
	private static final String SCREENQUAD = "\"vertex_shader\": \"minecraft:core/screenquad\",";

	private FxaaPostChainRepair() {
	}

	/**
	 * Rewrites the blit pass's vertex stage if this file still asks for the missing {@code post/blit} one.
	 *
	 * @return the file to write, or {@code null} when nothing was changed
	 */
	public static byte[] apply(byte[] original) {
		String text = new String(original, StandardCharsets.UTF_8);
		if(!text.contains(BLIT_PAIR)) {
			return null;
		}
		// Verified against both chains as OptiFine ships them: the exact string occurs once per file, in the blit
		// pass (the FXAA pass names "minecraft:post/fxaa_of_2x"/"fxaa_of_4x" as its vertex stage), so a blind
		// replacement of this one line cannot touch the pass that already compiles.
		String replaced = text.replace(BLIT_PAIR + "\n", SCREENQUAD + "\n")
				.replace(BLIT_PAIR + "\r\n", SCREENQUAD + "\r\n");
		if(replaced.equals(text)) {
			return null;
		}
		return replaced.getBytes(StandardCharsets.UTF_8);
	}
}
