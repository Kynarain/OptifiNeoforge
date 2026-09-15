# 版本矩阵现状与推进顺序

本文只记录**实测**的差距与顺序,不重复 `docs/PLAN.md`(计划)与 `docs/DEVELOPMENT.md`(实测记录)。

## 当前真实进度(2026-09-15,阶段性总结)

**目标尚未达成。** 逐行的实测状态如下,判据是"实机启动 + 行为与已验证线一致",不是"能编译":

| 线 | 版本 | NeoForge | 状态 | 证据 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 | 47.1.106 | **已验证** | 启动成功、`forge shells in the jar: 0`、OptiFine 412 targets、计划 550 成员/87 类、资源重载无错、贴图集建成 |
| 1.20.x | 1.20.2 | 20.2.93 | **未开始** | OptiFine 1.20.2 的 jar 未下载;`mcp_config-1.20.2.zip` 已确认存在,可照 1.20.4 的路走 |
| 1.20.x | 1.20.4 | 20.4.251 | **可运行**(见下) | 换装 313 类、`Caught error: 0`、资源包不再被移除、模型烘焙通过、进到标题画面(`Setting user`)、OptiFine 着色器与连接材质在跑、无崩溃报告。**运行要求**:`config/fml.toml` 设 `earlyWindowProvider = "none"` —— FML 的 early window 与 OptiFine 换装的渲染类会在同一帧里重入(`SimpleBufferBuilder: Already building`),而 `-Dfml.earlyprogresswindow=false` 是 Forge 时代的属性,FML 2.0.17 不认 | 换装 274 类、资源包不再被移除(`Caught error: 0`)、模型烘焙过关;当前崩在 NeoForge early-display 与 OptiFine 版 `LoadingOverlay` 的重入(`IllegalStateException: Already building` @ `NeoForgeLoadingOverlay.render`) |
| 1.20.x | 1.20.6 | 20.6.141 | **未开始** | 从这一版起 FML 拒绝原版 OptiFine jar(`IncompatibleModReason.OPTIFINE`);且载荷命名空间变回官方名,重映射步骤应整体跳过 |
| 1.21.x | 1.21.4 | 21.4.149 | **已验证** | 启动成功、OptiFine 474 targets、模型烘焙(`missingModel=SimpleBakedModel`)、1024×1024 贴图集、首次资源重载 0 错 |
| 1.21.x | 1.21.1 / 1.21.2 / 1.21.3 / 1.21.5 / 1.21.6 / 1.21.7 / 1.21.8 / 1.21.9 / 1.21.10 / 1.21.11 | 21.x | **未开始** | 其中 1.21.9+ 已无 ModLauncher,只能走自定义 `ClassProcessor` |
| 26.x | 26.1.2 | 26.1.2.109 | **未开始** | FML 11 去掉 ModLauncher;OptiFine K1_pre2 自带 `OptiFineClassProcessor`,需先绕过 `IncompatibleModReason` 与 `loaderVersion` |

**发布:一次都没有。** `release/version.ps1` 从未真正跑过;仓库有三个分支(`main` 落地页、`1.20.x`、`1.21.x`、`26.x`),`main` 只有落地页。

### 1.20.4 剩下那一步的具体做法(下一轮起点)

崩溃点在 `NeoForgeLoadingOverlay.render` → FML `SimpleBufferBuilder.begin` 报 `Already building`。因为
OptiFine 换装了 `LoadingOverlay`(25 fields / 12 methods),它的加载画面代码与 NeoForge 的 early-display
同帧都去驱动同一个 buffer。查的方向:

1. 先确认是不是同一帧两条渲染路径都进了 early display(在 `NeoForgeLoadingOverlay.render` 上加一个
   探针,照 `ReloadProbe` 那套);
2. 若是 OptiFine 的 `LoadingOverlay` 补丁在抢渲染,考虑**不换装 `LoadingOverlay`**(把它从
   `PatchedClassTransformer` 的目标表里去掉)——加载画面本来就不是 OptiFine 的核心价值,
   而它是这一条线上唯一已知的冲突源;
3. 之后再按 1.21.4 的验收清单核对(贴图集、模型烘焙、资源重载 0 错),把 1.20.4 从"接近"推到"已验证"。

### 已经建成的可复用资产(与版本无关)

- `SrgMemberMap` / `SrgRemap`:SRG↔official 表 + 载荷改名(1.20.2/1.20.4 必需;1.20.6+ 自然空转)
- `MissingTargets`:列出"运行时与载荷都没有"的成员,并可用 `--stub` 补默认实现
- `OptifineJar` / `OptifinePipeline` / `MemberRestorePlan` / `ForgeApiShims` / `OptifineJarFixer`
- loader 侧:`PatchedClassTransformer`(换装成品类,含访问标志与接口并集两条规则)+ 既有各项修补
- rig:`build-rig-jar.ps1` 的 `-SrgRemap` / `-StubMissing` / `-ShipPayload` 三个开关已跑通 1.20.4

### 这一条线走过的路(为什么每一步都是必需的)

1.20.4 从"完全跑不起来"到"只差一个崩溃",中间依次解决:安装器不产出派生 jar → 打补丁需要**原版混淆
jar**(不是 SRG jar)→ 载荷与补丁产物都是 SRG 名,需要改名(含声明,不只是引用)→ 改名要跑在成员恢复计划
之前 → 成品类不能平铺在 `srg/` 下(模块包冲突)→ 成品类由我方 transformer 换装 → 换装必须复制访问标志
(类取 OptiFine 的、成员取两者中更宽的)→ 运行时与载荷都没有的成员要补空实现 → 换装**接口**时超接口要
取并集而不是替换。每一步都有实测证据,记在 `docs/DEVELOPMENT.md`。


## 实测:三条分支的差距

```
origin/1.20.x : 10 files
origin/1.21.x : 10 files
origin/26.x   : 39 files
```

`26.x` 上那 39 个文件包含**全部实现**:loader 侧的 `OptifiNeoforgeTransformationService` 与各项修补
(`TagHelperFix`、`PackRootsFix`、`ReloadProbeFix`、`ModelProbeFix`、`NativeImageProbeFix`、
`MemberRestoreTransformer`、`RenderTargetFix`、`ReloadableResourceManagerFix`、`ConventionTags`)、离线工具
(`MemberRestorePlan`、`ForgeApiShims`、`OptifineJar`、`OptifineJarFixer`、`OptifinePipeline`、
`OptifineConfig`、`DonorVerifier`),以及 `build.gradle`、`gradle.properties`、`release/version.ps1`。

`1.20.x` 与 `1.21.x` 目前仍只有骨架:**没有 loader、没有工具、没有对应行的构建配置**。

## 各行的目标参数

| 线 | Minecraft | NeoForge | Java | 备注 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 | `1.20.1-47.1.106` / `20.2.93` / `20.4.251` | 17 | 前两者载荷是真正的 SRG 名,需要 SRG→official 重映射 |
| 1.20.x | 1.20.6 | `20.6.141` | 21 | 从这一版起 FML 拒绝原版 OptiFine jar(`IncompatibleModReason.OPTIFINE`) |
| 1.21.x | 1.21.1 … 1.21.11 | `21.0.167` … `21.11.45` | 21 | 1.21.6 / 1.21.7 / 1.21.9 只有 beta;1.21.3 起 `loaderVersion="[14,)"` 校验失败 |
| 26.x | 26.1.2 | `26.1.2.109` | 25 | FML 11 已去掉 ModLauncher,走 `ClassProcessor` |

## 推进顺序(按依赖,不按版本号)

1. **把实现搬到 `1.21.x`**:先只搬与版本无关的部分(工具与 loader 骨架),再按该行改
   `gradle.properties`(`minecraft_version`、`neoforge_version`、`mod_version_base`、Java 21)与
   `build.gradle` 的 `targetJavaVersion`。验证标准是**该分支能 `gradlew build`**。
2. **`1.21.4` 的功能阻塞先解决**(重复的监听器清单 → 贴图集 `clearTextureData` 关掉了要上传的精灵,
   见 `docs/DEVELOPMENT.md`)。同一条线上修完再往下铺,可以避免把同一个错误复制到三条线。
3. **`1.20.x`**:同样先搬实现,再补 SRG→official 重映射(1.20.2 / 1.20.4),最后处理 1.20.6 起 FML
   对原版 OptiFine jar 的拒绝。
4. **`26.x` 的 `ClassProcessor` 路线**:FML 11 没有 ModLauncher,现有 `ITransformationService` 在此行
   不适用,需要单独实现 `net.neoforged.neoforgespi.transformation.ClassProcessor` 那条路。
5. 每完成一行,按 `docs/PUBLISHING.md` 走一次发布演练(升版、构建、打标签),但**不要**在没有实机
   启动记录的情况下把 `0.x` 升到 `1.0.0`。

## 已知风险

- **不要假设"工具跨版本通用"**:工具里对命名空间(`srg/` 与 `notch/`)、`patch/srg` 与 `patch/notch`
   的假设在 1.20.6 前后是不同的,搬迁时必须逐行确认。
- **不要用另一条线的启动结果代替验证**:每条线的 `gradle.properties`、OptiFine 版本与 NeoForge
   版本都不同,rig 里的 profile 与游戏目录也要各自建立。

## Measured 2026-09-15: only one version-specific assumption in the code

Scanning the implementation for version-specific assumptions before moving it
(class-file version constants, ModLauncher dependencies, hard-coded versions)
gave a smaller answer than expected:

- Exactly one class-file version is written anywhere: `Opcodes.V17` in
  `ForgeApiShims`. A Java 17 class file loads on the 21 and 25 runtimes just as
  well, so no line needs it changed.
- All nine loader transformers depend on ModLauncher
  (`OptifiNeoforgeTransformationService`, `MemberRestoreTransformer`,
  `TagHelperFix`, `PackRootsFix`, `ReloadProbeFix`, `ModelProbeFix`,
  `NativeImageProbeFix`, `RenderTargetFix`, `ReloadableResourceManagerFix`).
  That confirms from the code side that 26.x needs the loader layer rewritten
  against `net.neoforged.neoforgespi.transformation.ClassProcessor`, while
  1.20.x and 1.21.x reuse it as it is.
- No code branches on the Minecraft version; versions appear only in comments and
  command-line arguments, and OptifineConfig reads them from OptiFine's own
  constant pool.

So bringing 1.21.x up is copying the implementation plus writing that line's
build configuration rather than porting it, and 26.x is the only line that needs
genuinely new code.
## 2026-09-15: the 1.20.x line needs a different NeoForge coordinate

The implementation and this line's build configuration are committed on 1.20.x
(ee908cb), but its build does not resolve the plugin's dependency:

    Could not find net.neoforged:neoforge:1.20.1-47.1.106.
      Searched in .../net/neoforged/neoforge/1.20.1-47.1.106/neoforge-1.20.1-47.1.106.pom

NeoForge publishes that version under a different artifact id, which was checked
rather than assumed - fetching
https://maven.neoforged.net/releases/net/neoforged/forge/1.20.1-47.1.106/forge-1.20.1-47.1.106.pom
returns 200 and names the artifact "forge", while the "neoforge" path does not
exist. ModDevGradle 2.0.147 asks for net.neoforged:neoforge, and trying 1.0.23 -
the generation from that era - asks for the same thing, so the version string alone
does not select the artifact.

So this line needs either the plugin generation that knows about the forge artifact
(the net.neoforged.gradle.userdev line, which predates ModDevGradle) or an explicit
dependency on net.neoforged:forge with the plugin's resolver out of the way. That is
the next thing to try for 1.20.x; nothing else about the line is blocked, and the
implementation is in place and compiles wherever this line's build is set up to
resolve its dependencies.

Process note: the attempt left the working tree dirty on 1.20.x because the build
was run before deciding whether to keep the change, and the branch switch in the
finally block was refused for that reason. Reverted; the lesson is to make the tree
clean before switching, not after.
## 2026-09-15: what the 1.20.x build actually asked for, and what it means

Two attempts, each with the exact coordinate the toolchain demanded:

    net.neoforged.moddev (2.0.147 and 1.0.23) -> Could not find net.neoforged:neoforge:1.20.1-47.1.106
    net.neoforged.moddev.legacyforge (2.0.147) -> Could not find net.minecraftforge:forge:1.20.1-47.1.106

NeoForge publishes this line as net.neoforged:forge:1.20.1-47.1.106 (the pom under
that artifact id returns 200 and names "forge"; the "neoforge" path does not exist).
The normal plugin always asks for net.neoforged:neoforge. The legacy plugin - which
NeoForged's ModDevGradle 2 announcement says covers "Forge 1.17 to 1.20.1 and
NeoForge 1.20.1" - asks for net.minecraftforge:forge, i.e. it treated 47.1.106 as a
MinecraftForge version, and Forge's 47.1.x line does not have that build.

So the remaining question for this line is how the legacy plugin is told that the
artifact lives under net.neoforged rather than net.minecraftforge. Two candidates,
to try in that order: a fully qualified coordinate in legacyForge.version, or the
plugin generation from before ModDevGradle that shipped alongside NeoForge 1.20.1.

Worth recording too, because it changes what this line needs beyond the build
script: NeoForge 1.20.1 is the Forge-compatible line, so it runs on ModLauncher-era
FML - the loader layer our 1.21.x line already uses applies as it is, and OptiFine's
own jar may be accepted without the FML refusals that start at 1.20.6. This line's
mod metadata is also different: 1.20.1 reads META-INF/mods.toml with the Forge-style
dependency entries, not the META-INF/neoforge.mods.toml the other lines ship, so the
resource has to be written for this line rather than copied.
## 2026-09-15: the toolchain composes the coordinate itself, and cannot name 1.20.1

Handing the plugin a fully qualified coordinate shows how it builds one:

    Supplied String module notation
    'net.neoforged:neoforge:net.neoforged:forge:1.20.1-47.1.106' is invalid.

It takes the version string and prepends group and artifact itself -
net.neoforged:neoforge in the mode it chose here, net.minecraftforge:forge in the
run before - so a coordinate cannot be passed through that field, and neither mode
asks for the artifact NeoForge actually published this line under
(net.neoforged:forge:1.20.1-47.1.106, pom verified).

That closes the Gradle question for 1.20.1 with the plugins tried: the main plugin
(2.0.147, 1.0.23) wants net.neoforged:neoforge, the legacy plugin wants either that
or net.minecraftforge:forge depending on the mode it picks. What is left for the
build script is the plugin generation from that era - NeoGradle's userdev - which is
a different DSL, or leaving this line's Gradle build open and noting it.

None of that blocks the line's actual work: the 1.21.4 verification never went
through Gradle. The rig builds the loader by compiling the sources against
ModLauncher, ASM and log4j, repacks OptiFine, patches the game jar, plans the
restores and combines the result into a mod jar. That pipeline is what produced every
measurement on the 1.21.x line, and for 1.20.1 it needs only an OptiFine jar for that
version (already in test-downloads) and a NeoForge 1.20.1 instance to launch.

So the order for this line becomes: verify it through the rig first, and treat the
Gradle build as a separate item that is understood, not mysterious.
## 2026-09-15: a NeoForge 1.20.1 instance exists now, and the launcher owes it one property

Setup done on this machine, so the next round can start with a launch:

- NeoForge 1.20.1 installed as the profile 1.20.1-forge-47.1.106 (installer:
  forge-1.20.1-47.1.106-installer.jar, run with Java 17). The installer reported
  errors twice while maven.neoforged.net timed out, and wrote a profile whose 33
  libraries were missing. The libraries were then fetched directly from the
  profile's own downloads.artifact entries with a retry loop (30 downloaded, 4
  already present, 0 failed), after which the installer ran to completion
  ("Successfully installed client into launcher").
- Java 17 is at C:\Program Files\Java\jdk-17, and the vanilla 1.20.1 jar is present.
- test-downloads already holds OptiFine_1.20.1_HD_U_I6.jar and the 1.20.1 client jar.

The profile still will not start, and the reason is specific rather than mysterious:
BootstrapLauncher 1.1.2 - the era's launcher - requires a system property the
installer does not write into the profile. Reading the class shows it looks up
"legacyClassPath", with "legacyClassPath.file" as the variant pointing at a file, and
line 141 is the Optional.orElseThrow() that fails with "No value present". The
profile's own arguments.jvm carries -DlibraryDirectory, the module path with
--add-modules ALL-MODULE-PATH, and the add-opens/add-exports, but no legacyClassPath.

So the rig needs one era-specific addition for this line - pass
-DlegacyClassPath=<the game classpath it already computes> (or write it to a file and
pass legacyClassPath.file) - and then the same pipeline that verified 1.21.4 can run
here. That is the first thing to do next, followed by this line's mod metadata:
1.20.1 reads META-INF/mods.toml with Forge-style dependency entries, not the
META-INF/neoforge.mods.toml the other lines ship.
## 2026-09-15: the 1.20.x line's loader must target the older ModLauncher API

The combined-jar pipeline runs on 1.20.1 up to the loader compile, and stops there
with a concrete API difference rather than a mystery. Compiling the loader package
against the ModLauncher this line actually uses (10.0.9, installed by the NeoForge
1.20.1 installer) gives:

    MemberRestoreTransformer.java:34: error: cannot find symbol
    import cpw.mods.modlauncher.api.TargetType;
    symbol: class TargetType
    location: package cpw.mods.modlauncher.api
    ...: error: type Target does not take parameters
    ...: error: type TargetType does not take parameters

So on 10.0.9 the transformer API is not generic the way 11.0.4's is: there is no
TargetType class in that package, and Target is unparameterised. The loader was
written against 11.0.4, which is what 1.21.4 ships - so "the 1.20.x line reuses the
loader layer as it is" was true only for lines whose ModLauncher matches, and this
one does not.

The work this leaves is bounded and mechanical: adapt the eight transformers
(MemberRestoreTransformer, TagHelperFix, PackRootsFix, ReloadProbeFix, ModelProbeFix,
NativeImageProbeFix, SortProbeFix, RenderTargetFix, ReloadableResourceManagerFix) to
the 10.0.9 signatures. Two ways to do it, to be decided by what 10.0.9 offers: either
a per-line copy of the loader sources compiled against 10.0.9, or a small version
shim in this repository that both APIs can compile against. The pipeline itself needs
nothing else - steps 1 to 3 (repack, patch, member-restore plan) already ran for
1.20.1, which is the larger half.
## 2026-09-15: the exact shape of the 10.0.9 transformer API

Read from modlauncher-10.0.9.jar, so the adaptation is mechanical rather than探索:

    public interface ITransformer<T> {
        T transform(T, ITransformerVotingContext);
        TransformerVoteResult castVote(ITransformerVotingContext);
        Set<ITransformer$Target> targets();
        default String[] labels();
    }
    public final class ITransformer$Target {            // nested, and NOT generic
        public static Target targetClass(String);
        public static Target targetPreClass(String);
        public static Target targetMethod(String, String, String);
        public static Target targetField(String, String);
        public TargetType getTargetType();              // the type travels on the target
    }
    public final class ITransformer$TargetType extends Enum { CLASS, METHOD, FIELD, PRE_CLASS }

Against 11.0.4, where Target<T> and TargetType<T> are top-level and generic and
ITransformer declares TargetType<T> getTargetType(), the per-line changes are:

1. imports: Target and TargetType are nested types of ITransformer on 10.0.9
   (cpw.mods.modlauncher.api.ITransformer$Target), so the import lines change, and the
   TargetType import disappears entirely;
2. targets(): Set<Target> rather than Set<Target<ClassNode>> - Target is not generic;
3. the getTargetType() override is removed: the method does not exist on 10.0.9, and the
   type is carried by each Target instead;
4. the factory call Target.targetClass(name) is the same name in both, so the bodies of
   targets() otherwise stay as they are.

That is nine files - MemberRestoreTransformer, TagHelperFix, PackRootsFix, ReloadProbeFix,
ModelProbeFix, NativeImageProbeFix, SortProbeFix, RenderTargetFix,
ReloadableResourceManagerFix - and the transformation service itself needs nothing: its
List<? extends ITransformer<?>> signature is the same in both APIs.
## 2026-09-15: the 1.20.1 line loads OptiFine; only this machine's assets are short

After the Java 17 and Forge-shell rules from the previous round, the 1.20.1 run gets
all the way through mod loading with the mod present:

    OptiFineTransformationService: Targets: 412
    OptifiNeoforge: Member restore plan: 550 members across 87 classes
    NeoForge mod loading, version 47.1.106, for MC 1.20.1
    NeoForge v47.1.106 Initialized

Adding assets/indexes/5.json (from the vanilla profile's own assetIndex.url) removed
the index error but not the next one, and the next one is data rather than code: this
machine's assets/objects holds the 1.21.4 set and not all of 1.20.1's, so the vanilla
pack fails to open, and the game stops with

    java.lang.IllegalStateException: Default font failed to load

That is worth separating clearly in the record: on this line the mod's own path is
working - OptiFine's service, our plan of 550 members across 87 classes, and NeoForge's
initialisation all complete - while the visual confirmation (textures, the rig's
screenshot) waits on downloading that version's asset objects. The download of the
missing objects is running as a background job; systems/... once it finishes, the run
can be repeated and the reload checked the way 1.21.4's was.
## 2026-09-15: for 1.20.4 the installer must actually finish, because it generates what FML needs

NeoForge 20.4.251 installs as a profile (49 libraries, 34 present, 15 downloaded by
hand), ModLauncher 10.0.9 starts, and then FML stops on artifacts that are not there:

    java.io.IOException: Invalid paths argument, contained no existing paths:
      libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-srg.jar
      libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-extra.jar
      libraries/net/neoforged/neoforge/20.4.251/neoforge-20.4.251-client.jar

Those three are not published artifacts. Fetching the last of them - the one that looks
most like a normal maven coordinate - returns 404 from the NeoForged repository, so they
are produced locally: the installer downloads NeoForm's tooling (AutoRenamingTool,
srgutils, javadoctor and friends), runs it against the vanilla 1.20.4 jar and the
mappings, and writes the srg, extra and client jars itself. On this machine that
installer keeps failing on SocketTimeoutException against maven.neoforged.net, which is
the same flakiness that hit Gradle and the 1.20.1 installer, so it never reaches the
generation step.

The way through, then, is the pattern that already worked twice: run the installer,
collect the "Downloading library from <url>" lines it reports as timed out, fetch those
exact URLs with Invoke-WebRequest and retries, repeat until it prints "Successfully
installed client into launcher" - at which point the three derived jars exist and
1.20.4 can be brought up like 1.20.1 was.

## 2026-09-15:1.20.4 已能启动,剩下的阻塞是命名空间而不是安装器

上一节推演的两半都得到了实测确认。

**安装器**:第 1 轮就完成了。三个派生 jar 现在都在

    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-srg.jar   (17,348,932)
    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-slim.jar  (13,383,682)
    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-extra.jar (11,061,879)
    libraries/net/neoforged/neoforge/20.4.251/neoforge-20.4.251-client.jar                        ( 5,299,238)

`launch-neoforge.ps1 -Profile neoforge-20.4.251 -NoMods -JavaExe jdk-17` 的基线启动结果是

    VERDICT: STARTED (40s, marker: Sound engine started)

Java 17、ModLauncher 10.0.9、`NeoForge mod loading, version 20.4.251, for MC 1.20.4 with MCP
20240627.114801`。**1.20.4 这一行的实例本身是好的**,再出问题就是我们的 mod 的问题。

**命名空间**:两侧确实不一致,而且这次是字节码级证据,不是靠目录名推断。

1. OptiFine 的载荷引用的是**真正的 SRG 名**。取 `srg/net/optifine/Config.class`,用 `javap -v` 分类常量池里
   `m_\d{4,6}_` / `f_\d{4,6}_` 的出现形式:

   | 构建 | `Methodref`/`Fieldref` | `String` |
   |---|---|---|
   | 1.20.1 I6 | 49 | 0 |
   | 1.20.4 I7 | 47 | 0 |
   | 1.21.4 J3 | 0 | 0 |

   关键是第二列:它们是**直接引用**,不是映射表里的字符串。所以"那只是 OptiFine 自带的对照表数据"这个
   解释不成立 —— 1.20.4 的类就是照着一个 SRG 命名的 Minecraft 编译的。

2. NeoForge 20.4 的运行时是**官方名**。`client-1.20.4-20240627.114801-srg.jar` 里
   `net/minecraft/world/item/Item` 的成员是 `getId`、`byId`、`builtInRegistryHolder`、`onUseTick`,
   `BY_BLOCK`/`MAX_STACK_SIZE`,**没有一个 `m_`**。文件名里的 `srg` 只是安装器那一步的历史名字,
   不代表内容是 SRG。

   这也解释了为什么已验证的两条线能跑:1.20.1 的 NeoForge 20.1 本身就是 SRG,两边同构;
   1.21.1 起 OptiFine 也换成官方名,两边同构。**只有 1.20.2 / 1.20.4 这一带两边不同构。**

**那么重映射要用的表从哪来 —— 这里有个缺口。** NeoForm 1.20.4 自带的两个映射文件都查过了:

- `neoform-1.20.4-20240627.114801-mappings.txt` 的表头是 `tsrg2 obf srg id`。但 srg 那一列**与 obf 完全相同**
  (`cuz cuz 1657`),全文**一个 `m_` 名都没有**,第三列是数字 ID。也就是说 NeoForm 对 1.20.4 **不发布 SRG 名**。
- `neoform-1.20.4-20240627.114801-mappings-merged.txt` 的表头是 `tsrg2 left right`,内容是 obf → 官方名
  (`a com/mojang/math/Axis`)。

所以"该版本的 SRG ↔ official 映射表"**不在 NeoForm 里**。但它确实存在 —— 这一节最后查到了来源,
见下面"映射表的来源"。

**要重映射的只有成员名,不是类名。** 这一点必须先量清楚,因为它决定实现的大小。OptiFine 1.20.4 里
`net/minecraft/client/Minecraft` 的引用长这样:

    #220 = Methodref  // net/minecraft/client/Minecraft.m_91087_:()Lnet/minecraft/client/Minecraft;
    #230 = Methodref  // net/minecraft/client/Minecraft.m_91268_:()Lcom/mojang/blaze3d/platform/Window;
    #612 = Methodref  // net/minecraft/client/Options.m_232119_:()Lnet/minecraft/client/OptionInstance;

**类名是官方名,成员名是 SRG 名**,而且常量池里 `net/minecraft/src/C_` 这种 SRG 类名出现 **0 次**
(1.17 起类名就只有官方名了)。所以重映射表只需要按"宿主类 + 成员名"改成员名,描述符原样保留。

**映射表的来源(已确认)**:MCPConfig 发布过 1.20.4 的映射。

    https://maven.minecraftforge.net/de/oceanlabs/mcp/mcp_config/1.20.4/mcp_config-1.20.4.zip   HTTP 200, 1,872,980 B

里面 `config/joined.tsrg`(6,011,226 B) 的表头也是 `tsrg2 obf srg id`,但**这一份的 srg 列是真的**:

    a net/minecraft/src/C_252363_ 252363
    	a f_252495_ 252495
    	b f_252529_ 252529

于是把两份同以 obf 为左列的映射串起来就能得到 SRG↔official:

| 来源 | 左 | 右 |
|---|---|---|
| MCPConfig `config/joined.tsrg` | obf | SRG(`net/minecraft/src/C_*`、`m_*`/`f_*`) |
| NeoForm `...-mappings-merged.txt` | obf | 官方名(`net/minecraft/world/item/Item`、`getId`) |

即:同一个 obf 类 + 同一个 obf 成员,在左表里读 SRG 名、在右表里读官方名,配对即得
`(官方宿主类, SRG 成员名) → 官方成员名`。两份文件都已取到本地
(`test-downloads/mcp1204-joined.tsrg`、`libraries/net/neoforged/neoform/1.20.4-20240627.114801/...-mappings-merged.txt`)。
注意两份的成员行都不带描述符,所以表要以"宿主类 + 成员名"为键,描述符保持不动。

**一个被否证的捷径**:本来想用 `patch/srg/*.class.md5` 判定"OptiFine 是针对哪个 jar 打的补丁",
结果 427 个 md5 对本地所有候选 jar 全部 0 命中 —— NeoForm 官方 jar、vanilla 混淆 jar、slim、extra、
`neoforge-*-client.jar`、`neoforge-*-universal.jar` 逐个比对都是 0;`patch/notch` 对 vanilla 混淆 jar 也是
0/426。结论是**这些 md5 不是输入校验和**(很可能校验的是 xdelta 之后的产物),这条路不能用来判定命名空间。

**下一步**:写一个离线生成器把上面两张表合成 1.20.2 / 1.20.4 的 SRG→official 成员表,再在 loader 的
transformer 里用 ASM `Remapper` 把 OptiFine 载荷的成员引用改写掉(类名不动)。1.20.2 需要同样查一次
`mcp_config-1.20.2.zip` 是否存在。

**这一步已完成(2026-09-15,详见 `docs/DEVELOPMENT.md`)**:`SrgMemberMap` 建表并全量验证,
OptiFine 1.20.4 的 **3844 个 SRG 引用改写后 100% 命中** NeoForge 20.4 运行时,所需改写对只有
**1330 条 / 86 KB**;`mcp_config-1.20.2.zip` 也已确认存在(HTTP 200)。1.20.2 / 1.20.4 这一带因此
从"需要研究"变成"按表改写"的机械工作,剩下的是接进 `OptifinePipeline` 与 loader transformer。

**改名本身也已做完并验证(2026-09-15)**:`SrgRemap` 作为**构建步骤**统一改名(不是 loader transformer ——
ModLauncher 要求预先声明 targets,而要改的类有几千个)。OptiFine 1.20.4 载荷实测:

| | 引用 | 声明 |
|---|---|---|
| 改名前 | 3844 | 108 |
| 改名后 | **0** | **0** |

共改写 3457 个方法名 + 1400 个字段名,0 个无法解析,0 个 SRG 形状的字符串常量(SRG 名只出现在引用和
声明里,不参与反射字符串,所以不需要动字符串)。声明那 108 个是关键:只改引用会让 OptiFine 替换类里
覆盖父类的方法悄悄变成另一个方法。**剩下的是把 `SrgRemap` 接进 rig 的构建流程并实机启动 1.20.4。**

**1.20.4 接进 rig 的两条硬结论(2026-09-15 当天稍晚,详见 `docs/DEVELOPMENT.md`)**

1. **打补丁的输入是原版混淆 jar**(`versions/1.20.4/1.20.4.jar`),不是 SRG/官方名 jar。`Patcher.applyPatch`
   用 `getPatchBase` 把补丁条目名换算成混淆 base 名去找,拿官方名 jar 会报
   `Base resource not found: eon.class`。换过来之后 1271 ms 通过,补丁出 427 个游戏类(369 个
   `net/minecraft`)。1.21.4 那一行一直是这么跑通的 —— `1.21.4-client.jar` 本身就是原版混淆 jar。
2. **补丁产物是 SRG 名,所以改名要跑在补丁之后、`MemberRestorePlan` 之前**:补丁类贡献了 21089 个方法名
   + 17455 个字段名(占全部改名量的 85%)。剩 107 个未解析(0.28%),原因已定位为**类名不一致**
   (Mojang 的 `Gui$1DisplayEntry` 对 OptiFine 的 `Gui$DisplayEntry`),只影响 369 个补丁类里的 **5 个**;
   下一步是架"补丁条目名 ↔ 混淆 base 名"这座桥把类名对齐。

**ModLauncher 两代兼容层(2026-09-15,已实机验证)**

1. **实测差异**(javap,不是回忆):ML 10.0.9 是 `Set<ITransformer.Target> targets()`、**没有**
   `getTargetType()`、`List<ITransformer> transformers()`;ML 11.0.2 / 11.0.4 是
   `Set<ITransformer.Target<T>> targets()`、`getTargetType()` 抽象、`List<? extends ITransformer<?>>`。
   1.20.x 这一条分支要同时服务两代(1.20.1–1.20.4 是 10,1.20.6 是 11),而这不是"多一个方法"的问题:
   同一个类不可能既有 `getTargetType()` 又没有。
2. **做法**:修正逻辑本身(10 个 transformer)抽成与 loader 无关的 `NodeTransformer`
   (`targetClasses()` 返回点名 + `transform(ClassNode)`),接口适配每代一份、**同类名**:
   `src/ml10/java/.../ModLauncherAdapter.java` 与 `src/ml11/java/.../ModLauncherAdapter.java`,
   构建只编译该行那一份(Gradle `-Pmodlauncher` + `sourceSets.main.java.srcDir`;rig 是
   `-AdapterSource` + `-ModLauncherVersion`)。服务层用 `public List transformers()`(原始 List)
   一个文件同时满足两代 —— 已分别对 10.0.9/jdk17、11.0.2/jdk21、11.0.4/jdk21 编译通过。
3. **动态代理条路走不通(实测)**:先用 `Proxy` 实现 `ITransformer` 想省掉一份源码,启动即死:
   `RuntimeException: How did a non-transformer get here????`。反编译定位到
   `TransformationServiceDecorator.lambda$gatherTransformers$0`:它遍历
   `t.getClass().getGenericInterfaces()`,要求其中有 raw type 为 `ITransformer` 的 `ParameterizedType`,
   再取其第一个类型参数当分组键;代理的 `getGenericInterfaces()` 只有裸接口,于是抛错。
   所以适配器必须是**真实类**,不能用代理。
4. 顺带修正:rig 编译 loader 时原来"取 modlauncher 目录里第一个 jar",1.20.6 因此拿 10.0.9 编译 11 适配器
   而报 `does not override abstract method getTargetType()` —— 现在版本必须显式给出。

**两个"运行时"输入不是一回事(关键实测)**

- `-RuntimeJar`(成员回填的 donor 来源)必须是**游戏真正加载的那个 jar**,
  即 NeoForge 的 `neoforge-<ver>-client.jar`;`-RemapRuntime`(名字解析)才是**官方名游戏 jar** + NeoForge jar。
- 证据:1.20.4 的 `net.minecraft.Util$9`。NeoForge client jar 里它是 `BiFunction` 缓存类(与 OptiFine
  载荷同一套匿名类编号),官方名 `client-1.20.4-…-srg.jar` 里却是 `extends Thread`(NeoForm 重编译后的编号)。
  把后者当 donor,就会把 Thread 的构造器拷进一个不是 Thread 的类:
  `VerifyError: Bad <init> method call … Type 'java/lang/Thread' is not assignable to 'net/minecraft/Util$9'`。
- 命名陷阱:NeoForm 的 `*-srg.jar` 名字叫 srg,**内容却是官方名**(字面上验证:`Component` 里是 `literal`);
  1.20.1 那个才真是 SRG(`m_237115_`)。改用官方名 jar 做 `-RemapRuntime` 之后,未解析引用从 14201 降到
  **107**,与本文档早先记的 107 一致 —— 也就是说之前那 14201 是**输入拿错**,不是改名逻辑的问题。
- 诊断技巧:OptiFine 补丁过的 `CrashReport` 生成报告时会先初始化 `Shaders`,而它需要 Minecraft 实例 →
  早期异常被整个吞掉(只看到 `NullPointerException … gameDirectory`)。临时把 `net/minecraft/CrashReport`
  放进 `-SkipSwapped`,让原版打印真正的异常,才看到上面那个 VerifyError。1.20.x 的构建线保留了这个偏离。

**1.20.6:一行完全没有补丁树的 OptiFine**

- 实测 `OptiFine-1.20.6_HD_U_J1_pre18.jar`:**没有任何 `patch/` 条目**,`srg/` 下 1083 个 `.class` 就是成品类
  (369 个 `net/minecraft` 全部引用 `net/optifine`、0 个 SRG 名;657 个 `net/optifine`;57 个 `com/mojang`)。
  `files.txt` 列 183 个目标,其中 2 个没有对应载荷类;OptiFine 1.20.6 **不补 Minecraft 本身**。
- rig 新增 `-PatchedFromRepackedJar`:跳过补丁步骤,payload 就是 repack 后的 jar;stub 结果按 `srg/` 原样写回(`3b3/5`)。
- 两个坑:①把 `srg/` 前缀重写成真名会被工具反噬 —— `MemberRestorePlan` 读的就是 `PATCHED_ROOT = "srg/"`,
  重写后计划变成 0 类 / 0 成员,已回退;②stub pass 一旦把 runtime jar 纳入扫描,输出里的类名会变成真名,
  所以**计划改从 payload jar 本身读**(rig 里的 `$planJar`),得到 230 类 / 73 成员 / 30 donor。
- 这一行必须 `ShipPayload`:游戏类留在 `srg/` 会让本 mod 模块导出 `net.minecraft.*`,直接
  `ResolutionException: Modules minecraft and srg export package …`;搬到 `optifineoforge/patched/` 后由本 mod 的
  transformer 装配。该行载荷已是官方名,所以是"只搬不改名"(`-ShipPayload` 不再强制要求 `-SrgRemap`)。
- 安装:NeoForge 20.6.141 安装器先报 `These libraries failed to download`(4 个坐标,含 modlauncher 11.0.2),
  手动取回后重跑即得到 `neoforge-20.6.141-client.jar`(`cli-utils-2.1.4.jar` 反复只有 9379 字节,但不影响安装完成)。

**当前状态**

- **1.20.4(ML 10 + ml10 适配器):`VERDICT: STARTED (40s, marker: Sound engine started)`** —— 兼容层无回归。
- 1.20.6(ML 11.0.2 + ml11 适配器 + 无补丁树):模块解析已过、替换已发生(`ResourceLocation`/`Util`/`Mth`/
  `CrashReport`/`Direction`/`BlockState` …),当前卡在
  `NoSuchMethodError: 'int net.minecraft.resources.ResourceLocation.compareNamespaced(…)'`。
  该成员只存在于 NeoForge client jar(官方名 jar 与载荷都没有),而 73 条回填里没有它 —— 计划里 30 个 owner
  里也没有 `ResourceLocation`,**下一步是查为什么这一条没被生成**(而不是急着加补丁)。

**1.20.6 已启动成功(2026-09-15 当日稍后)**

`VERDICT: STARTED (40s, marker: Sound engine started)`,同一次运行的实测口径与 1.20.4 一致:
`Setting user`(=进标题界面)✓、`[Shaders] OpenGL Version: 3.2.0 NVIDIA 591.86`(=光影在跑)✓、
`Connected textures` 3 行 ✓、`Pre-stitch` 14 行(OptiFine 自己拼图集)✓、替换类 268 个 ✓、
**stderr 0 字节**、**没有新 crash report**、`screen.png` 1.14 MB ✓。唯一"像错误"的行都是良性的:
`Failed to find ImmediateWindowProvider none`(FML 探测),以及 OptiFine Reflector 对 JDK 内部类
(`sun.misc.SharedSecrets` 等)的 WARN。

上一段里那个 `compareNamespaced` 之谜的答案是**两个构建脚本缺陷**,都不是改名逻辑的问题:

1. **空参数被 PowerShell 吃掉,后续参数整体左移一位**:`build-line.ps1` 对没有覆盖 `SkipSwapped` 的行
   传了 `$null`,splat 到原生命令时那个空元素消失,于是 `--stub <in> <out> <stubs> <skip> <payload> …`
   里的 `<payload>` 变成了**第一个 runtime jar(游戏 jar)**。后果:stub pass 报 `scanned 8206 classes`
   (游戏 jar 的类数)而不是载荷的 1163,把游戏 jar 整个写成输出,`3b3/5` 就地替换自然一个都对不上。
   修法:rig 侧空表一律回落到默认 skip 表,build-line 侧只有该行显式给出时才传参。
2. **`ForgeApiShims` 会写出重复的 `<init>()V`**:外壳自己那个无参构造器先写一次,载荷又引用了
   `<init>()V`,于是同一个类里出现两个,加载直接
   `ClassFormatError: Duplicate method name "<init>" with signature "()V" in class file
   net/minecraftforge/client/model/ForgeFaceData`(死在 OptiFine `Reflector.<clinit>` 里)。
   修法:每个成员只写一次(`Set` 记录,并把外壳自己的构造器先登记进去);自类型常量需要无参构造器时,
   只在真的没写过时才补。

1.20.6 这一行最终可用的配方(payload 无补丁树 + 官方名):
`-PatchedFromRepackedJar`(跳过补丁步骤,payload = repack 后的 jar)+ `-StubMissing`(stub 结果按 `srg/`
原样写回,`3b3/5`)+ `-ShipPayload`(游戏类搬出游戏包,由本 mod 的 transformer 装配)+ 计划从 payload jar
本身读(`$planJar`,`230 类 / 73 成员 / 30 donor`)+ `-RuntimeJar` = NeoForge client jar(donor 来源)、
`-RemapRuntime` = 官方名游戏 jar + NeoForge jar(名字解析)。适配器用 `src/ml11`,编译用 ModLauncher 11.0.2。