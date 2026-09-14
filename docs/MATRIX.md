
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