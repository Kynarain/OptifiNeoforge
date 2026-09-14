/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Reports the listeners a resource reload will run, in the order it will run them.
 *
 * <p>Silent unless {@code -Doptifineoforge.debug.reload=true} is set: this exists to answer one
 * question that the log cannot otherwise answer, namely whether {@code BlockRenderDispatcher} - which
 * asks {@code ModelManager} for baked models while it reloads - is reached before {@code ModelManager}
 * has run its own apply step. When it is, {@code ModelManager.getModel} falls back to its
 * {@code missingModel}, which apply has not set yet, and the caller gets a null model.</p>
 */
public final class ReloadProbe {
	private static final Logger LOGGER = LogManager.getLogger("OptifiNeoforge");
	/** The switch that turns this on; off by default so a shipped game logs nothing extra. */
	private static final String ENABLED = "optifineoforge.debug.reload";
	/** The same switch as an environment variable, for a launch that cannot be given -D arguments. */
	private static final String ENABLED_ENV = "OPTIFINEOFORGE_DEBUG_RELOAD";
	private static final String MODEL_MANAGER = "net.minecraft.client.resources.model.ModelManager";
	private static final String BLOCK_RENDER_DISPATCHER = "net.minecraft.client.renderer.block.BlockRenderDispatcher";
	private static final AtomicInteger RELOADS = new AtomicInteger();

	private ReloadProbe() {
	}

	/** One line per listener, with the two that matter marked. */
	public static void listeners(List<?> listeners) {
		if(!enabled()) {
			return;
		}
		StringBuilder report = new StringBuilder();
		report.append("reload ").append(RELOADS.incrementAndGet()).append(": ").append(listeners.size())
				.append(" listeners");
		for(int index = 0; index < listeners.size(); index++) {
			Object listener = listeners.get(index);
			String name = listener == null ? "null" : listener.getClass().getName();
			report.append("\n    ").append(index).append("  ").append(name)
					.append("  @").append(System.identityHashCode(listener));
			if(MODEL_MANAGER.equals(name)) {
				report.append("   <- bakes the models");
			} else if(BLOCK_RENDER_DISPATCHER.equals(name)) {
				report.append("   <- asks for baked models");
			}
		}
		LOGGER.info(report.toString());
	}

	/** Marks the moment a method starts, so the log shows the order the reload really ran in. */
	public static void enter(String label) {
		if(!enabled()) {
			return;
		}
		LOGGER.info("enter " + label);
	}

	/**
	 * Marks the moment a method finishes, with what the instance is left holding.
	 *
	 * <p>Only for a model class: the two fields named here are the ones the null-model failure turns
	 * on, and reading them says whether the bake produced anything at all. A {@code ModelBakery} has
	 * the unbaked missing model, a {@code ModelManager} has the baked one, and a
	 * {@code ModelBakery$BakingResult} has the one that gets handed over.</p>
	 */
	public static void leave(Object instance, String label) {
		if(!enabled()) {
			return;
		}
		if(instance == null) {
			LOGGER.info("leave " + label);
			return;
		}
		LOGGER.info("leave " + label + ": missingModel=" + describe(read(instance, "missingModel"))
				+ ", blockStates=" + size(read(instance, "bakedBlockStateModels")));
	}

	/** The same reading, for a value being returned rather than an instance finishing a method. */
	public static void result(Object value, String label) {
		if(!enabled()) {
			return;
		}
		LOGGER.info("result " + label + ": missingModel=" + describe(read(value, "missingModel"))
				+ ", blockStates=" + size(read(value, "blockStateModels")));
	}

	/** The value a method hands back, named - for questions about a call that answers null. */
	public static void value(Object value, String label) {
		if(!enabled()) {
			return;
		}
		LOGGER.info("value " + label + ": " + describe(value));
	}

	/** How many listeners a list holds, for following the list through registration. */
	public static void size(List<?> listeners, String label) {
		if(!enabled()) {
			return;
		}
		LOGGER.info("size " + label + ": " + (listeners == null ? "null" : Integer.toString(listeners.size())));
	}

	/** Who called in, briefly - for a method that should not be running yet. */
	public static void trace(String label) {
		if(!enabled()) {
			return;
		}
		StringBuilder report = new StringBuilder("trace " + label);
		StackTraceElement[] frames = Thread.currentThread().getStackTrace();
		int shown = 0;
		for(StackTraceElement frame : frames) {
			String type = frame.getClassName();
			if(type.startsWith("java.") || type.startsWith("jdk.") || type.startsWith("kynarain.")) {
				continue;
			}
			report.append("\n    at ").append(type).append('.').append(frame.getMethodName());
			if(++shown == 8) {
				break;
			}
		}
		LOGGER.info(report.toString());
	}

	private static Object read(Object instance, String name) {
		for(Class<?> type = instance.getClass(); type != null; type = type.getSuperclass()) {
			try {
				java.lang.reflect.Field field = type.getDeclaredField(name);
				field.setAccessible(true);
				return field.get(instance);
			} catch(NoSuchFieldException | IllegalAccessException | RuntimeException absent) {
				// Try the superclass, then give up: this is a diagnostic.
			}
		}
		return null;
	}

	private static String describe(Object value) {
		return value == null ? "null" : value.getClass().getSimpleName();
	}

	private static String size(Object value) {
		return value instanceof java.util.Map<?, ?> map ? Integer.toString(map.size()) : "n/a";
	}

	static boolean enabled() {
		return Boolean.getBoolean(ENABLED) || "true".equalsIgnoreCase(System.getenv(ENABLED_ENV));
	}
}
