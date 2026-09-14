
## 2026-09-15: the 1.20.1 line starts with OptiFine, and two per-line rules it taught

After the loader was adapted to ModLauncher 10.0.9, the 1.20.1 pipeline runs end to
end and the game starts with the mod present:

    OptiFineTransformationService: Targets: 412
    OptifiNeoforge: Member restore plan: 550 members across 87 classes
    NeoForge mod loading, version 47.1.106, for MC 1.20.1
    NeoForge v47.1.106 Initialized
    VERDICT: STARTED (marker: Sound engine started)

Two things had to be right for that, and both are per-line rules worth keeping:

1. Compile this line with Java 17. The first attempt with the rig's Java 21 defaults
   died on UnsupportedClassVersionError: the loader's classes were class file version
   65 and this runtime recognises up to 61. Gradle already encodes this through
   target_java_version=17 on the line; the rig needs the same explicitly (-Javac and
   -Java pointing at jdk-17).
2. Do not add the Forge API stubs on this line. NeoForge 1.20.1 is the Forge-compatible
   line, so net.minecraftforge.** classes really exist and our generated shells would
   shadow them; the build produced 69 of them, and removing those entries from the
   combined jar before launching is what let the run proceed. The repack tooling needs
   a per-line switch for this rather than a post-processing step.

Still environmental on this line, not mod-related: assets/indexes/5.json and the game
icons are missing from this machine, so the window shows errors and the rig cannot take
its screenshot (CopyFromScreen: The handle is invalid).
## 2026-09-15: the 1.20.1 line runs clean, and two rig defects it exposed

With this line's asset objects complete and the Forge shells switched off, the run has
nothing left to report:

    forge shells in the jar: 0
    VERDICT: STARTED (marker: Sound engine started)
    OptiFineTransformationService: Targets: 412
    OptifiNeoforge: Member restore plan: 550 members across 87 classes
    NeoForge v47.1.106 Initialized
    Reloading ResourceManager: vanilla, mod_resources
    Created: 1024x512x4 minecraft:textures/atlas/blocks.png-atlas

and the log holds no Exception, no "Caused by" and no "failed to load" at all. The
earlier "Default font failed to load" was the missing asset data, not the mod, and it
went away with the objects.

The switch that produces this is OptifineJar's --no-forge-stubs, driven by the rig's
-ForgeStubs. Two defects of the harness had to be fixed for it to take effect, and both
are worth remembering because of how they presented:

1. Splatting an array into the tool's argument list produced the wrong arguments - the
   tool printed its usage line while the identical call typed by hand succeeded. The
   invocation is now written out as two explicit calls instead of @stubArgs.
2. The rig regenerates the Forge shells in a step of its own, independent of the tool's
   flag, so the first "no shells" build still carried 69 of them and the game died with
   java.lang.module.ResolutionException: Module optifine contains package
   net.minecraftforge.eventbus.api, module net.minecraftforge.eventbus does not export
   it to optifine. That step is now gated by the same switch.

Only the rig's screenshot remains unavailable on this machine, and that is the session
rather than the game: CopyFromScreen reports "The handle is invalid" without an
interactive desktop.