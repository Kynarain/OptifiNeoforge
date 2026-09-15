/*
 * OptifiNeoforge - loads OptiFine into NeoForge.
 * Licensed under MPL-2.0; see LICENSE at the repository root.
 */

package kynarain.cn.optifineoforge.loader;

import java.util.Set;
import java.util.function.Supplier;

/**
 * A {@code Set} wearing the {@code Supplier} the newer SecureJarHandler asks for.
 *
 * <p>The same interface, one generation apart. OptiFine's {@code optifine/OptiFineJar} builds a
 * {@code cpw.mods.jarhandling.impl.SimpleJarMetadata} and was compiled when its third parameter was the
 * set itself:</p>
 *
 * <pre>SecureJarHandler 2.1.10  SimpleJarMetadata(String, String, Set&lt;String&gt;, List&lt;Provider&gt;)
 * SecureJarHandler 2.1.24  SimpleJarMetadata(String, String, Supplier&lt;Set&lt;String&gt;&gt;, List&lt;Provider&gt;)</pre>
 *
 * <p>Measured on 1.20.2: its only OptiFine build is a preview compiled against 2.1.10, while every
 * NeoForge 20.2.x ships 2.1.24, so the launch dies with {@code NoSuchMethodError} inside OptiFine's own
 * class. No 20.2.x ships the older handler - the series on Maven is 20.2.86 to 20.2.93 and all of them
 * use 2.1.24 - so the call has to be repaired, and this is the object that lets it be repaired by
 * wrapping rather than by rewriting the method: {@link kynarain.cn.optifineoforge.optifine.OptifineJarFixer}
 * inserts {@code new SetSupplier(set)} in front of the call and points the call at the new descriptor.</p>
 *
 * <p>It lives in the loader package because that package is what the build already ships, and it has to
 * be in this jar: the repaired call runs inside OptiFine's layer service, which loads from here.</p>
 */
public final class SetSupplier implements Supplier<Set<String>> {
	private final Set<String> values;

	public SetSupplier(Set<String> values) {
		this.values = values;
	}

	@Override
	public Set<String> get() {
		return values;
	}
}
