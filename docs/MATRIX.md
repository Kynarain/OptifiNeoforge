# 版本矩阵现状与推进顺序

本文只记录**实测**的差距与顺序,不重复 `docs/PLAN.md`(计划)与 `docs/DEVELOPMENT.md`(实测记录)。

## 当前真实进度(2026-09-16,阶段性总结)

**目标尚未达成**,但六条线已经实机跑到标题画面。逐行的实测状态如下,判据是"实机启动 + 行为与已验证线一致",
不是"能编译":

| 线 | 版本 | NeoForge | 状态 | 证据 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 | 47.1.106 | **已验证** | 启动成功、`forge shells in the jar: 0`、OptiFine 412 targets、计划 550 成员/87 类、资源重载无错、贴图集建成 |
| 1.20.x | 1.20.2 | 20.2.88 | **已验证** | `STARTED (40s)`、`Setting user` ✓、239 行 `[OptiFine]`、`Pre-stitch` ×13、着色器 14 行、`Caught error: 0`;已知缺陷见下(Reflector 加载 `BlockState`/`ItemStack` 失败,stderr 14,631 字节) |
| 1.20.x | 1.20.4 | 20.4.251 | **已验证** | `STARTED (40s)`、`Setting user` ✓、241 行 `[OptiFine]`、`Pre-stitch` ×13、CTM ✓、`Caught error: 0`;已知缺陷同上(stderr 14,481 字节)。**运行要求**:`config/fml.toml` 设 `earlyWindowProvider = "none"` —— FML 的 early window 与 OptiFine 换装的渲染类会在同一帧里重入(`SimpleBufferBuilder: Already building`) |
| 1.20.x | 1.20.6 | 20.6.141 | **已验证** | 启动成功、OptiFine 着色器与连接材质在跑、贴图集建成;这一版起 FML 拒绝原版 OptiFine jar,所以要靠本项目的重打包与元数据修复 |
| 1.21.x | 1.21.1 | 21.1.250 | **已验证**(2026-09-16) | `STARTED (40s)`、`Setting user` ✓、313 个类换装(目标 426)、223 行 `[OptiFine]`、`Pre-stitch` ×14、CTM ×3、着色器 ✓、`Caught error: 0`、**stderr 0 字节**;载荷父类被改写(`CapabilityProvider` → `AttachmentHolder`) |
| 1.21.x | 1.21.3 | 21.3.97 | **已验证**(2026-09-16) | 一次性跑通:`STARTED (40s)`、`Setting user` ✓、268 个类换装(目标 445)、225 行 `[OptiFine]`、`Pre-stitch` ×14、CTM ×3、着色器 ✓、`Caught error: 0`、**stderr 0 字节**;载荷父类改写与 1.21.1 同形(`CapabilityProvider` → `AttachmentHolder`) |
| 1.21.x | 1.21.4 | 21.4.149 | **已验证** | 启动成功、OptiFine 474 targets、模型烘焙(`missingModel=SimpleBakedModel`)、1024×1024 贴图集、232 行 `[OptiFine]`、`Pre-stitch` ×14、stderr 0 字节;这一线走 OptiFine 自己的运行期补丁,不换类 |
| 1.21.x | 1.21 / 1.21.6 / 1.21.7 / 1.21.8 / 1.21.9 / 1.21.10 / 1.21.11 | 21.x | **未开始** | 1.21.2 与 1.21.5 没有 OptiFine 构建;**1.21.9 起 NeoForge 不再用 ModLauncher**(实测 21.11.45 的 profile:主类 `net.neoforged.fml.startup.Client`,库里没有 modlauncher/securejarhandler,只有 FML 10 + sponge-mixin),那几条线的挂载点要换成 `ClassProcessor`;1.21.6/1.21.7 的上游缺陷见 `OptifiNeoforge-121x/docs/PLAN.md` |
| 26.x | 26.1.2 | 26.1.2.109 | **未开始** | FML 11 去掉 ModLauncher;OptiFine K1_pre2 自带 `OptiFineClassProcessor`,需先绕过 `IncompatibleModReason` 与 `loaderVersion` |

**已知缺陷(与加载器无关,1.20.2 / 1.20.4 共有)**:OptiFine 的 `Reflector` 在 `GameRenderer.frameInit` 里
反射 `BlockState` 时拿到
`NoClassDefFoundError: net.minecraft.world.level.block.state.BlockState`
(原因 `ClassNotFoundException: …BlockState`,`ItemStack` 同样),来自
`net.optifine.reflect.ReflectorMethod.getMethod`。基线(改动前后)字节数一致,不随本轮的父类改写变化,
所以它是**独立待办**:它决定的是 OptiFine 的哪些反射功能不可用,而不是这两条线能否跑起来。

**发布:一次都没有。** `release/version.ps1` 只在 dry-run 里跑过;仓库有四个分支(`main` 落地页、`1.20.x`、
`1.21.x`、`26.x`),`main` 只有落地页。

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

**打包路径的一个缺口(2026-09-15,实测)**

`.\gradlew build` 在本分支默认目标 1.20.1 上直接失败,原因不是本 mod 的代码:

```
Execution failed for task ':createMinecraftArtifacts'
> Could not find net.neoforged:neoforge:1.20.1-47.1.106
  Searched in: https://maven.neoforged.net/releases/net/neoforged/neoforge/1.20.1-47.1.106/...
```

1.20.1 那一代的坐标是旧的 `net.neoforged:forge`,ModDevGradle 去要 `net.neoforged:neoforge` 自然找不到。
因此本分支**默认构建目标改成已实机验证过的 1.20.4**(`minecraft_version=1.20.4` / `neoforge_version=20.4.251`),
1.20.1 仍可由 rig 构建与启动,只是"打包成 jar"这一环要等这条坐标问题解决。`-Pmc` 换目标时的规则不变:
非默认目标必须同时给 `-Pneoforge`,并且现在还要给 `-Pmodlauncher`(1.20.1–1.20.4 = 10,1.20.6 = 11)。

改成 1.20.4 之后再跑:`net.neoforged:neoforge:20.4.251` **解析成功**、NeoForm 流程也真的跑起来了
(花了 10 分 20 秒),最后死在流水线的 **recompile** 节点,原因是 `java.net.ConnectException`
(下载中途断线),不是配置问题。也就是说打包路径剩下的障碍是**这条网络**,重跑即可 —— 与 20.6.141
安装器那次同源。产物会落在 `build/libs/`。

**1.20.1 现状:能进标题界面,但 OptiFine 还没真正生效(2026-09-15 当晚实测)**

`VERDICT: STARTED (40s, marker: Sound engine started)`、`Setting user`、`screen.png` 0.94 MB、
无新 crash report —— 与兼容层无关。但同一次运行里:

- **`Replaced net` = 0**:本 mod 的替换 transformer 一个类都没换,因为这份 jar 走的是"保留补丁数据"的路线
  (`-ShipPayload $false`),没有 `patched-index.txt`。
- **`Shaders` / `Connected textures` / `Pre-stitch` 全为 0**:OptiFine 的功能没有生效。
- **stderr 1.29 MB / 11862 行**,开头即:`java.io.IOException: Base resource not found: eud.class`
  来自 `optifine.Patcher.applyPatch`,而 `OptiFineTransformer` 自报 `Targets: 412`。

也就是说 1.20.1 与 1.20.2/1.20.4 是**同一个病**:OptiFine 的运行时补丁器要找**混淆 base 名**,而运行时交给它的是
SRG 名(所以 412 个目标全部 `Base resource not found`)。结论是这一行**也必须走离线载荷**路线。
两个补充实测:

1. 这一行"保留补丁数据"的产物是 5.87 MB(`combined-nostubs.jar` 5,870,135 / 今天重建 5,876,768),
   而离线载荷产物是 4.24 MB —— 与当初记录的"1.20.1 已跑通"的那份大小一致,那份其实也只是**能启动**,
   OptiFine 同样没生效(当时记的是"clean reload、图集",不是光影/CTM)。
2. 改成离线载荷(`-ShipPayload $true`)后,连续两次都在换掉 5 个类左右后死掉,失败点是
   **SecureJar 打不开 union 路径**:
   `java.nio.file.FileSystemNotFoundException` ← `Jar$JarModuleDataProvider.open(Jar.java:291)`
   ← `ModuleClassLoader.getClassBytes` ← `net.minecraft.util.Mth.<clinit>`。
   这是 1.20.1 那一代 securejarhandler 的路径,不是我们的改名/回填逻辑。

**下一步(1.20.1)**:先查 SecureJar 那一步——它要打开的到底是哪个 jar(值得加一个探针把 `Jar` 的路径打出来),
以及把 `net/minecraft/util/Mth` 放进 `-SkipSwapped` 后错误是否换到别的类(能换就说明是我们的替换顺序/时机,
不换就说明是那一代 securejarhandler 的 union 处理)。定向实验比重跑猜测便宜:一次 build+launch 约 1.5 分钟。

**已经做完的两个定向实验(2026-09-15 当晚,结论比猜测明确)**

1. **同一份 jar,去掉 `patched-index.txt` 后能正常启动**:把离线载荷产物复制到探针目录并删掉
   `optifineoforge/patched-index.txt`,再启动 —— `VERDICT: STARTED (40s)`、截图已存、stderr 只有
   `Failed to load forge logo`,而且日志明确写着
   `No /optifineoforge/patched-index.txt in this jar; no classes will be swapped in`。
   所以**出错的是"本 mod 的 transformer 真的开始换类"这件事**,不是 jar 的内容(载荷布局、donor、plan 都无辜)。
2. **把索引限制成只有 `net/minecraft/*`(354 条)后仍然以同一条栈失败** —— 触发点在 `net/minecraft/**` 里,
   不在 `com/mojang/**`(blaze3d 那些类这次根本没注册)。

失败形态固定:`ModuleClassLoader.getClassBytes` → `JarModuleReader.open` →
`Jar$JarModuleDataProvider.open(Jar.java:291)` → `Paths.get` →
`FileSystemNotFoundException`,最先在 `net.minecraft.util.Mth.<clinit>` 上暴露;ML 版本是 10.0.9,
与 1.20.4 相同,但那一代的 securejarhandler 不同 —— 这解释了"同样是 ML 10、1.20.4 行却没事"。

**下一步(1.20.1,已缩小到一条)**:把索引继续二分到单条 —— 先用只含 `net/minecraft/util/Mth` 的索引启动:
失败就说明是这一个类,不失败就说明是"只要换类就炸"(那问题在 transformer 的注册时机/securejarhandler 的
union 读取,而不在某个类)。两个脚本已经就位:`make-probe-jar.ps1`(删条目)、
`make-index-probe.ps1 -Keep "<通配符>"`(裁索引),一次 build+launch 约 1.5 分钟。

**二分做完了,答案是"不是某一个类"(2026-09-15 当晚)**

- **只留 `net/minecraft/util/Mth` 一条**(`Patched-class targets: 1`):仍然以同一条栈死在
  `Mth.<clinit>`。
- **把 `Mth` 放进 `-SkipSwapped`**(让运行时自己那份留下):错误只是**换了个类** —— 变成
  `CrashReport.m_127526_(CrashReport.java:170)`,而且还是在 `Main.main:149`(也就是崩溃上报那条路径里)。

所以这不是"某个类不能换",而是**"只要有类被我们换掉,就会在某个类读取上炸"**。结合三条已知:
同样的代码在 1.20.4 上没事(ML 版本相同,securejarhandler 不同)、去掉索引就正常、
失败点每次都是"被换过的类第一次调用 OptiFine 自己的类的时候" —— 目前最站得住的假设是:

> 1.20.1 这一代的 securejarhandler 在**读取被 union 进游戏层的 mod jar 里的类**时会走
> `Jar$JarModuleDataProvider.open` → `Paths.get(union 路径)` 而抛 `FileSystemNotFoundException`。
> 我们的 mod jar 正是被 union 的(模块名 `optifine`,在 GAME 层),而 **OptiFine 自己的类就住在里面**
> (`srg/net/optifine/**`)。于是一旦某个被换过的游戏类去调 OptiFine 的辅助类,读取就炸 ——
> 这解释了"为什么去掉索引就没事"(不调就不会读)、"为什么换 Mth 会炸在 Mth"(Mth 的补丁第一件事就是调
> OptiFine)、以及"为什么不换 Mth 就炸在 CrashReport"(换下一个被调用者)。

**1.20.1 的第二个真相:SecureJar 那条报错是次生的,底下还有一个匿名类编号问题**

修好探针方法后(`Util*` 这种写法在 PowerShell 里是**大小写不敏感**的,`net/minecraft/Util*` 会把
`net/minecraft/util/Mth` 一起match进来 —— 之前三次"二分"因此都被污染了),用**按字节测出来的**条件重做探针:
只保留"不引用 `net/optifine`"的载荷类(268 个),丢掉引用 OptiFine 的 141 个 ——

- **SecureJar 那条 `FileSystemNotFoundException` 消失了**,换成一条干净、真实、可解释的错:

```
java.lang.VerifyError: Bad type on operand stack
  Location: net/minecraft/Util.m_137584_()V @13: invokevirtual
  Reason: Type 'net/minecraft/Util$9' is not assignable to 'java/lang/Thread'
```

也就是说:**匿名类编号在两边不一样**。运行时那份 `Util$9 extends java.lang.Thread`,而载荷里 `Util$9` 是
`Util.memoize` 后面的 BiFunction 缓存类 —— 把载荷那份装进去,`Util` 里"造线程"的代码就过不了校验。
这同时反过来说明:先前 SecureJar 报错**只在被换类去调 OptiFine 自己的类时出现**(那 141 个),所以
"OptiFine 自己的类住在被 union 的 mod jar 里、这一代 securejarhandler 读不了"这个假设仍然站得住。

**已经做的修正(代码里,不是脚本)**:`PatchedClassTransformer` 加了一条守卫 —— 名字里含 `$` 的类,
如果载荷那份的父类与运行时那份不同,就**不换**(OptiFine 只改方法体、不改继承;而 `Outer$N` 的编号两边不一致
时,父类不同就是"这不是同一个类")。守卫本身按预期生效了:

```
Left net.minecraft.Util$7 alone: the payload's copy of it extends java/lang/Thread
while the runtime's extends java/lang/Object
```

**但它暴露了真正的形状**:守卫只挡了 `Util$7`,于是变成了"载荷的 `Util` + 运行时的 `Util$7`" —— 载荷的 `Util`
造线程时期望自己的 `Util$7`(Thread),拿到的却是运行时那份(Object),`VerifyError` 只是**换了个类名**:

```
Type 'net/minecraft/Util$7' is not assignable to 'java/lang/Thread'
```

**结论:匿名类必须按"家族"整体决定,不能按单个类决定。** 外层类与它的 `$N` 内部类是同一份编译的产物,
两者混搭必然自相矛盾;而载荷那一族内部自洽、运行时那一族内部自洽,所以要么整族换、要么整族不换
(编号错位时只能整族不换)。外层类本身不需要换也没关系 —— `$N` 是私有的,外部代码不会引用它们。

**下一步(明确的实现,不是探索)**:在构建期(离线、能同时看到载荷与运行时)按家族做决定 ——
凡"族里任一成员的父类与运行时对应类不同"就把整族(外层 + 所有 `Outer$*`)从索引与载荷里去掉。
这样剩下的族两边一致,可以整体安装;被牺牲的只是少数几个族的 OptiFine 补丁(例如 `Util`),
而 shaders 需要的 `RenderSystem`/`Mth`/`GlStateManager` 这些**顶层类不受影响**。

**家族规则已实现并生效(同一晚)**:新工具 `NestedFamilyGuard`(构建期跑,载荷 + 运行时都看得见),
输出要整族跳过的名字;rig 拿它同时喂**载荷搬运**和**transformer 目标索引**,两者因此不会各说各话。
1.20.1 实测它只找出 **2 个族**并整族跳过:

```
families to skip: 2 (com/mojang/blaze3d/vertex/VertexMultiConsumer, net/minecraft/Util)
Patched-class targets: 417        (= 435 - 18)
```

`VerifyError` 那一类错误至此消失(新的 stderr 里已经没有它)。剩下的仍是 SecureJar 那条。

**SecureJar 那条已经被定位得更细了(两个新证据)**

1. **完全不换类的那次运行里,`[OptiFine]` 日志一行都没有**(`net.optifine` 相关只有一条
   `additionalClassesLocator: [optifine., net.optifine.]`)。也就是说那条能启动的路径里 **OptiFine 的代码从未被调用过**。
2. 一旦有类被换(第一个被换的类 `Mth` 就调用 OptiFine),失败点正好是**第一次加载 `net.optifine.*`**。

而**我们自己**的类也住在同一个 jar 里(`kynarain/cn/optifineoforge/loader/**`),它们加载得好好的。
所以问题不在"读 union 的 mod jar",而在 **OptiFine 自己那条取类路径**:它按 code source 算出 URL 交给
ModLauncher(`additionalClassesLocator`),在 union 文件系统下那个 URL 是 union 形状,这一代
securejarhandler 打不开 —— 这**正是 `OptifineJarFixer` 当初为它的 transformation service 修过的那类 bug**
(`new ZipFile(path)` 遇到 `...jar#177` 而报 `NoSuchFileException`)。

**下一步(1.20.1)**:看 `OptifineJarFixer` 现在到底修了哪几个类的方法,再把同一处理扩到
`additionalClassesLocator` 的实现上(1.20.1 的 OptiFine jar 里,它多半与 service 在同一个类里,
但用的 API 不同);修好之后这一行应当能带着完整载荷启动。

**顺手修掉一个自己造的回归(同晚,记下来是因为它是"接口契约"而不是笔误)**

给 `NestedFamilyGuard` 接线时,我把 stub pass 的 skip 表从"内部名"改成了"点名"
(`$_ -replace '/', '.'`),理由是 `MissingTargets` 的用法写的是 "skipped prefixes"。
结果是 **1.20.4 从 STARTED 退化成 15 秒退出**,而失败信息看着毫不相干:

```
java.lang.NoSuchMethodError: 'void net.minecraft.client.gui.screens.LoadingOverlay.update()'
```

原因链:`MissingTargets` 的 skip 谓词是拿**类文件里读出来的内部名**去比(`skip.test(node.name)`),
点名永远匹配不上 → `LoadingOverlay` 不再被排除出"载荷索引" → 它自己声明的 `update()` 被判为**可满足**
(而不是"只有 OptiFine 那份才有")→ 于是不再生成那条延迟 stub → 运行时一调用就 `NoSuchMethodError`。
这是文档里早就写过的那个陷阱("with OptiFine's LoadingOverlay still counted as present …"),只是这次是
我自己踩的。**契约:给这两个工具的 skip 表一律用内部名(带斜杠)。**

修好后 1.20.4 立刻恢复:`VERDICT: TIMEOUT (201s)`(跑满整个 200 秒窗口,即稳定存活)、
`Runtime stubs to add: 76 members across 27 classes`、`Patched-class targets: 448` —— 与当初验证的那组数字一致。
`NestedFamilyGuard` 本身在 1.20.4 上只找出 `com/mojang/blaze3d/vertex/VertexMultiConsumer` 一个族并跳过,
且已用 `-NoFamilyGuard` 对照实验证明它**不是**那次退化的原因。

**1.20.1 的最大障碍已解决:`OptifineJarFixer` 原来"整段替换" `toFile`,把副作用删掉了**

反汇编 1.20.1 的 `optifine/OptiFineTransformationService.toFile(URI)` 才看清:它**本来就处理 union**——
scheme 为 `union` 时取 `getPath()`、砍掉 `#<index>`、`new File(path)`、`ofZipFileUrl = file.toURI().toURL()`
(指令 57-63),然后继续打开 zip。而我们那个"修 union 路径"的 fixer 是**直接把整个方法体换掉**的,
于是 `ofZipFileUrl` **永远不再被赋值**;它自己的 `getResourceUrl(String)` 又正是拿这个静态字段拼类 URL 的
(而且会先给非 `optifine/` 的名字加 `srg/` 前缀)—— 结果 ModLauncher 拿到 union 形状的 URL,
报出那条看着毫不相干的 `FileSystemNotFoundException`。

修法:按**形状**分流(两种形状都用 javap 实测过,不是靠文件大小猜的)——

- **非 union 分支用 `new File(URI)` 的那一版(1.20.1)**:保留原方法体,只在已有的 `#` 裁剪之后**插入**一段
  "砍掉第一个 `!`" 的指令(`toFile` 里能找到 `INVOKESPECIAL java/io/File.<init>(Ljava/net/URI;)V` 这个特征)。
- **非 union 分支用 `new File(uri.getPath())` 的那一版(1.20.4/1.21.4)**:整段替换(那正是修
  `NoSuchFileException: ...jar#177` 的办法)。

结果:1.20.4 恢复 `VERDICT: TIMEOUT (181s)` ✓;1.20.1 的 stderr 从 11862 行/1.29 MB 变成**只剩一行**
`Failed to load forge logo`(良性),而且 **OptiFine 自己的 `Config` 成功加载**(`[OptiFine] Version found: I6`)——
"从 union 的 mod jar 里读 `net.optifine.*` 失败"这个问题到此为止。

**1.20.1 现在的失败点(已进入正常启动流程,性质完全不同)**

```
Description: Initializing game
java.lang.IllegalAccessError: Update to non-static final field
  net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState.f_110131_ attempted from a different class
	at net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState.<init>(ForgeRenderTypes.java:371)
	at ... RenderType.m_110497_ ... FontSet ... Minecraft.<init>(Minecraft.java:475)
```

也就是说:我们换进去的类里,某个字段带着**载荷的 `final` 位**,而 NeoForge 自己的代码会写这个字段
(`ForgeRenderTypes$CustomizableTextureState` 写 `f_110131_`)→ JVM 拒绝。这与文档里那条"成员可见性取两者更宽的"
是同族问题,只是当时只处理了可见性、没处理 `final`。

**下一步(1.20.1,明确)**:在 `PatchedClassTransformer` 合并字段访问位时,**不要从载荷继承 `ACC_FINAL`**
(字段的 final 位以运行时那份为准;运行时非 final 就必须保持非 final)。这一条改完再跑 1.20.1。

**这条"不要继承 final"试过了,结果是错的,已回退(同晚,负结果照样记下来)**

改动本身:合并字段访问位时,若运行时那份不是 final 就清掉 final。结果**两条线一起退**:

- **1.20.4 从 `TIMEOUT (181s)` 退成 `EXITED (10s)`**(明确回归,失败出现在崩溃上报那条路径里的
  `RenderSystem.<clinit>`);
- **1.20.1 冒出新错**,而且这个新错正是这条规则**自己造出来的**:

```
java.lang.RuntimeException: java.lang.ClassFormatError:
  Illegal field modifiers in class com/mojang/blaze3d/vertex/VertexConsumer: 0x9
```

`0x9` = `public final`,而**接口字段必须是 `public static final`** —— 清 final 的做法把不该动的位动了,
真正的差异其实是 **`static` 位**:载荷那份 `VertexConsumer` 的字段没有 static(0x9),运行时那份有(0x19)。
也就是说字段的访问位要**逐位**处理,并且 `static`/`final` 都应当以**运行时那份**为准,而不是笼统地"清 final"。

回退后 1.20.4 立刻恢复:`VERDICT: TIMEOUT (181s)`、`Runtime stubs to add: 76 members across 27 classes`、
`Patched-class targets: 448`(仍是那组验证过的数字)。

**下一步(1.20.1,按位重写这条规则)**:字段合并时把 `ACC_STATIC` 与 `ACC_FINAL` 都取**运行时那份**
(载荷新增的字段没有运行时对应,则保留载荷自己的位);接口字段确保最终是 `public static final`。
另外这两次实验都说明一件事:**动访问位的改动必须先跑 1.20.4 回归**,它是最容易把已验证的行弄坏的地方。

**"按位取运行时"也试过了,同样是坏的,已回退(负结果 #2)**

改动:字段的 `ACC_STATIC`/`ACC_FINAL` 取运行时那份(运行时没有这个字段时才保留载荷的位),
接口字段强制 `public static final`。结果**两条线都退**,而且错得**一模一样**:

```
IllegalAccessError: Update to non-static final field
  com.mojang.blaze3d.vertex.VertexFormat.elem…      (1.20.4,字段名 elem…)
  com.mojang.blaze3d.vertex.VertexFormat.f_86…      (1.20.1,字段名 f_86…)
```

也就是说:合并后的字段被判成 **final**,而某个调用方在写它。回退后 1.20.4 立刻回到
`VERDICT: TIMEOUT (181s)` + `76 members / 27 classes` + `448 targets`(那组已验证的数字)。

**两次实验合起来给出的结论(比改动本身重要)**

1. **`IllegalAccessError: Update to … final field` 在两条线上是同一个病**:运行时有代码写一个字段,
   而合并后的类把它标成 final。区别只在字段名的命名空间(1.20.1 是 `f_86…`,1.20.4 是 `elem…`)——
   这本身又说明**两条线的"运行时那份"并不总在同一命名空间里**,而这正是这条规则难写的原因。
2. **合并的匹配键是 `名字 + 描述符`**,载荷与运行时只要**描述符**有一点不同,查找就落空,
   于是"以运行时为准"的规则**根本不会生效**,字段会原封不动带着载荷的位 ✗ —— 这解释了为什么两次
   访问位改动都是"要么无效、要么有害"。
3. **下一步应该先解决匹配,再谈取舍**:先用调试输出把 `VertexFormat`(及 `ForgeRenderTypes$CustomizableTextureState`)
   里那几个字段在载荷/运行时两侧的**名字、描述符、访问位**真实打印出来,看清"谁与谁对应、差在哪一位",
   再决定规则;而不是继续凭推测改合并逻辑。改动前先跑 1.20.4 回归。

**测出来之后,规则第三次改对了(同一晚,这次两条线都验证过)**

先做测量:`ForgeRenderTypes$CustomizableTextureState` 的 javap 显示它是
**`extends net.minecraft.client.renderer.RenderStateShard$TextureStateShard`** —— 也就是说
`f_110131_` 不是它自己声明的,而是**从 OptiFine 会替换的那个 Minecraft 类继承来的**;而写它的是
NeoForge 自己的构造器。原因就清楚了:**NeoForge 的 access transformer 把 `final` 去掉了**,
好让自家子类能赋值;而 OptiFine 的编译里这个字段仍是 `final` → 赋值被 JVM 拒绝。

正确的规则因此是"第一次的规则 + 接口例外",而不是"按位取运行时":

- **接口**的字段一律 `public static final`(否则 `ClassFormatError: Illegal field modifiers … 0x9`);
- **普通类**的字段**不从载荷继承 `final`**(运行时那份是 final 就保留,否则清掉;载荷新增的字段也清掉);
- **`static` 位不动** —— 之前把它也取运行时,正是 `VertexFormat.elem…` 那次失败的原因。

结果:**1.20.4 `VERDICT: TIMEOUT (181s)` 无回归** ✓;**1.20.1 的 `IllegalAccessError` 消失**,
失败点从"类加载期"推进到**资源重载期**(已经进到加载界面之后):

```
Description: Rendering overlay
java.lang.NoSuchMethodError:
  'void net.minecraft.client.particle.ParticleEngine$ParticleDefinition.<init>(net.minecraft.resources.ResourceLocation, java.ut…'
	at net.minecraft.client.particle.ParticleEngine.lambda$reload$5(ParticleEngine.java:269)
```

**下一步(1.20.1,新的前沿)**:`ParticleEngine$ParticleDefinition` 的构造器签名在两侧不一致 ——
载荷里被换进去的 `ParticleEngine` 调的是它编译时那个签名,而运行时的同名嵌套类没有这个构造器。
这正是"成员回填(plan/donor)"要处理的一类问题,只是方向相反(这次是**载荷在调用**、运行时缺),
先量清楚两侧该类的构造器列表,再决定是把它也纳入回填,还是把该嵌套类整族按家族规则处理。

**量清楚了:不是构造器不一致,是"同一个类被起了两个名字"(同晚)**

逐个 jar 扫过之后,事实是:

| 侧 | 类名 | 形态 |
|---|---|---|
| 载荷(OptiFine) | `net/minecraft/client/particle/ParticleEngine$ParticleDefinition` | record,构造器 `(ResourceLocation, Optional<List<ResourceLocation>>)` |
| 运行时(游戏 jar / forge client) | `net/minecraft/client/particle/ParticleEngine$**1**ParticleDefinition` | **同一个 record,同一个构造器签名** |

也就是说 `$ParticleDefinition` 与 `$1ParticleDefinition` 是**同一个类**,只是命名来源不同:
载荷的名字来自混淆 jar,运行时的来自 NeoForm。**这正是本文档早先记过的那座还没架的桥**
(`Gui$1DisplayEntry` 对 OptiFine 的 `Gui$DisplayEntry`,当时只影响 5 个补丁类,现在它挡在了 1.20.1 的启动路径上)。

还要注意一个细节:我们**确实**把载荷那份 ship 进 jar 了(`optifineoforge/patched/net/minecraft/client/particle/
ParticleEngine$ParticleDefinition.class`,2373 字节),索引里也有它(`net/minecraft/client/particle/ParticleEngine$ParticleDefinition`)
—— 但 ML 只会为"**运行时存在**的同名类"调用我们的 transformer,所以这个只存在于载荷的名字**永远不会被安装**,
而载荷那份 `ParticleEngine` 却按它编译时的名字去调 → `NoSuchMethodError`。

**下一步(1.20.1,实现而不是探索)**:架这座"类名对齐"的桥,而且有现成的判定条件可用 ——
对每个"载荷有 `$`、运行时没有同名类"的类,去运行时找**外层名相同且成员结构一致**的候选
(本例两者构造器与字段完全一样),把载荷那份**按运行时的名字**ship 出去并写进索引;
这样 ML 在问运行时的 `$1ParticleDefinition` 时,我们就能把载荷的 `$ParticleDefinition` 装进去。

**桥的第一半做好了,但它只解决一半(同晚,精确到"还差什么")**

新工具 `NestedNameBridge`(构建期):按**结构**配对 —— 父类相同、字段描述符集合相同、方法描述符集合相同,
并且**恰好只有一个**候选时才配对(多于一个就报歧义并放弃;配错会装错类)。实测找出来的对:

```
1.20.4: 3 对   Gui$DisplayEntry -> Gui$1DisplayEntry
                ParticleEngine$ParticleDefinition -> ParticleEngine$1ParticleDefinition
                LevelChunkSection$BlockCounter -> LevelChunkSection$1BlockCounter
1.20.1: 2 对   ParticleEngine$ParticleDefinition -> ParticleEngine$1ParticleDefinition
                LevelChunkSection$BlockCounter -> LevelChunkSection$1BlockCounter
```

第一对正是本文档早先记过的 `Gui$1DisplayEntry` 老问题 —— 现在它被自动找出来了。
rig 已经按"运行时的名字"ship 这些类并写进索引;**1.20.4 仍是 `VERDICT: TIMEOUT (181s)`、无回归** ✓。

**但 1.20.1 的错误没变**:`NoSuchMethodError: ParticleEngine$ParticleDefinition.<init>(…)`。
原因很清楚,而且说明这座桥需要**两半**:

- 只把**我们 ship 的类**改名,等于把类从"载荷名"搬到"运行时名";而**载荷里的引用**仍然写着载荷名
  (`ParticleEngine` 的常量池里是 `$ParticleDefinition`)—— 于是现在连那个名字都没有了 ✗。
- 也就是说:**类名对齐必须同时改"我们发的类名"和"载荷里的引用"**。后者正是本文档早先写的那句
  "架'补丁条目名 ↔ 混淆 base 名'这座桥把类名对齐"——只是现在有了精确、可判定的配对表(只用结构判定)。

**下一步(1.20.1,收尾这座桥)**:在 `NestedNameBridge` 的基础上加一遍**载荷类名重写**(与 `SrgRemap`
同一套 ASM `Remapper` 机制,只是改的是类名而不是成员名):把载荷里对旧名的**引用**改成运行时名,
同时 rig 继续按运行时名 ship 类本身。两半都到位后,`ParticleEngine` 调的就是运行时真实存在的类名了。

**桥的两半都到位,1.20.1 打通了(同一晚)**

`NestedNameBridge --rewrite <in> <out> <runtime…>`:用同一份配对表,把载荷里所有对旧类名的**引用**
(常量池、描述符、Signature、InnerClasses)改成运行时的名字,配对的那个类自己也换成运行时名;
rig 在 plan/stub/ship **之前**把 `$patched` 换成这份对齐后的 jar,所以下游看到的都是运行时名。

实测改写规模:`1.20.4: 3 对 / 1143 个类被改写`、`1.20.1: 2 对 / 2210 个类被改写`。

**结果:两条线都是 `VERDICT: STARTED (40s, marker: Sound engine started)`,1.20.1 首次真正跑起来** ——
验收口径与其它行一致:

| 指标 | 1.20.1(本次) |
|---|---|
| `Setting user`(进标题界面) | ✓ |
| `[Shaders] OpenGL Version` | ✓ `4.6.0 NVIDIA 591.86` |
| `Connected textures` | 2 行 ✓ |
| `Pre-stitch`(OptiFine 拼图集) | 12 行 ✓ |
| `[OptiFine]` 日志 | **157 行** ✓ |
| 替换类 | 233 个 |
| **stderr** | **27 字节**(基本干净) |
| 截图 / 新 crash report | 927 KB / **无** ✓ |

至此 **1.20.x 的 1.20.1、1.20.4、1.20.6 三行都已实机跑通**(1.20.6 是 4.24 MB 载荷路线、1.20.4 是
官方名重映射路线、1.20.1 是 SRG 原样 + 类名对齐路线);这一行只剩 **1.20.2**(需要 OptiFine 1.20.2 的 jar,
本地没有,`mcp_config-1.20.2.zip` 已在)。

**版本号第一次真正走了一遍 `release/version.ps1`(同晚)**

`0.1.0` → `0.2.0`(minor),依据是三行已实机跑通、OptiFine 功能在跑(shaders/CTM/图集/157 行 `[OptiFine]` 日志)。
脚本三条路径都实测过:`show` ✓、`minor -DryRun`(打印 `would change 0.1.0 -> 0.2.0`)✓、`minor` 真正写入
(UTF-8 无 BOM)✓。产物名随行变化:`OptifiNeoforge-0.2.0+mc1.20.4.jar`(本行默认目标 1.20.4),
`-Pmc=1.20.1 / 1.20.6` 时分别是 `+mc1.20.1` / `+mc1.20.6`。

关于 0.x 的判据:`VERSIONING.md` / 脚本注释写的是"0.x = loader 还没在游戏里跑起来,1.0.0 要手动 `-Base` 设"。
现在的状态是**部分成立**:1.20.x 三行跑通,但整个矩阵(1.20.2、1.21.x、26.x)远未完成 —— 所以
**不设 1.0.0**,用 `0.2.0` 表示"已开始跑通、但矩阵未完成"。等目标里那条"1.20.1 到 26.1.2 全覆盖"真正验收过了,
再用 `release/version.ps1 patch -Base 1.0.0` 一次性把它设为 1.0.0。

**目标范围的权威清单(2026-09-15 当晚,取自镜像的 OptiFine 版本表)**

目标写的是"1.20.1 到 26.1.2 之间**所有有 OptiFine 构建**的版本",而"哪些版本有"这件事不该靠记忆。
查询 `https://bmclapi2.bangbang93.com/optifine/versionList`(497 条,含 mcversion/type/patch/filename)
得到的 1.20.1 及以后清单是:

| 版本 | 构建数 | 最新构建 | 备注 |
|---|---|---|---|
| 1.20.1 | 14 | `OptiFine_1.20.1_HD_U_I6.jar` | 正式版 ✓ 本分支已跑通 |
| **1.20.2** | **1** | `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar` | **只有 preview**(先前按正式版名去下,404 就是这个原因) |
| 1.20.4 | 11 | 最新是 `pre4` preview | 正式版 I7 已有,本分支已跑通 |
| 1.20.6 | 3 | `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar` | 只有 preview,已跑通 |
| 1.21 | 8 | `preview_OptiFine_1.21_HD_U_J1_pre8.jar` | 只有 preview |
| 1.21.1 | 8 | `OptiFine_1.21.1_HD_U_J1.jar` | 正式版,jar 已在本地 |
| 1.21.3 | 13 | `OptiFine_1.21.3_HD_U_J2.jar` | 正式版 |
| 1.21.4 | 18 | 最新是 `pre2` preview | 正式版 J3 已在本地,先前已跑通 |
| 1.21.6 | 3 | `preview_…_J6_pre3.jar` | 只有 preview |
| 1.21.7 | 4 | `preview_…_J6_pre7.jar` | 只有 preview |
| 1.21.8 | 10 | `preview_…_J6_pre16.jar` | 只有 preview |
| 1.21.9 | 2 | `preview_…_J7_pre1.jar` | 只有 preview |
| 1.21.10 | 10 | `preview_…_J7_pre11.jar` | 只有 preview |
| 1.21.11 | 18 | `OptiFine_1.21.11_HD_U_J9.jar` | 正式版(本地有) |
| 26.1.2 | 2 | `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` | 只有 preview(本地有) |

顺带记一条经验:**不要按"正式版文件名"去猜下载名** —— 1.20.2 那一行就是这样白跑了一圈。
下载脚本 `fetch-optifine.ps1` 现在从元数据端点取 `type`/`patch` 再拼 URL
(`/optifine/{mcversion}/{type}/{patch}`),已实测拿到 `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar`(7,192,269 字节)。

**1.20.2 这一行现在的准备度**:OptiFine jar ✓、NeoForge 20.2.88 ✓(已装,`neoforge-20.2.88-client.jar`);
还缺 **1.20.2 的原版客户端 jar**(管道打补丁要用混淆 jar)与 **`mcp_config-1.20.2.zip`**(这一行是 SRG 载荷 +
官方名运行时的组合,与 1.20.4 同类,需要那份映射表来建 SRG↔official 表)。

**1.20.2 构建通过、启动卡在 OptiFine 自己与 SecureJarHandler 的版本错配上(同一晚,已量到具体签名)**

准备度补齐后(原版 jar 本来就在、`mcp1202-joined.tsrg` 5,781,794 字节已解出、ModLauncher 10.0.9、
1.20.2 游戏 jar 实测是官方名 `literal`),**构建整条流水线都正常**:

```
patched game classes: 423 (365 net/minecraft)
rewrote 21289 method and 17305 field names, 80 could not be resolved, 10 refused
bridged nested names: 2 → rewrote references in 1132 classes
payload: 1091 classes, 404 moved to optifineoforge/patched
compared 233 replaced classes (828 without a runtime counterpart), 56 members to restore, 4 donors closed
```

启动则死在 **OptiFine 自己的 `OptiFineJar`** 上,而且原因是一个纯粹的**库版本错配**:

```
NoSuchMethodError: 'void cpw.mods.jarhandling.impl.SimpleJarMetadata.<init>(java.lang.String, java.lang.String, java.util.S…'
	at optifine.OptiFineJar.lambda$1(OptiFineJar.java:24)
	at optifine.OptiFineJar.<init>(OptiFineJar.java:24)
	at optifine.OptiFineTransformationService.completeScan(OptiFineTransformationService.java:82)
```

两侧都用 javap 量过:

| | `SimpleJarMetadata` 的第三个参数 |
|---|---|
| OptiFine 1.20.2 调用的 | `java.util.Set<String>`(即 **2.1.10** 那一代) |
| NeoForge 20.2.88 profile 里的 securejarhandler **2.1.24** | `java.util.function.Supplier<java.util.Set<String>>` |

也就是说 **OptiFine 的 1.20.2 preview 是针对更老的 SecureJarHandler 编译的**,而 20.2.88 已经换成新版;
1.20.4/1.20.6 那两行没这个问题,是因为它们对应的 OptiFine 与 SecureJarHandler 时代对得上。

**下一步(1.20.2,两条路,先试便宜的那条)**:①装一个**更早的 NeoForge 20.2.x**,看它的
securejarhandler 是不是 2.1.10 —— 是的话这一行很可能直接可跑(与 1.20.4 同一类路线);②若所有 20.2.x 都是
2.1.24,就用现成的 ASM 工具把 OptiFine 那个 `invokespecial` 改成新签名(把 `Set` 包成 `Supplier`,`() -> set`
这种形态在字节码里很好写),这与 `OptifineJarFixer` 修 `toFile` 是同一类处理。

**路子①走不通,已实测;②的具体做法也已明确(同一晚)**

maven 上 `net.neoforged:neoforge` 的 20.2 系列只有 **7 个构建(20.2.86 – 20.2.93)**,逐个装起来查
profile 里的 securejarhandler:**全是 2.1.24**(20.2.86 / 20.2.88 / 20.2.93 实测;另外 4 个那两次安装没产出 profile);
而 `net.neoforged:forge` 那一组**根本没有 1.20.2 的构建**(0 条)。对照一下正在跑通的 1.20.1 那一行:
它的 profile 用的是 **securejarhandler 2.1.10** —— 也就是说 **OptiFine 是拿"它那个年代的 SecureJarHandler"
编译的**,1.20.1 对得上所以能跑,1.20.2 的 preview 对不上(2.1.10 的调用 vs 2.1.24 的签名)所以起不来。
不存在"换个 20.2.x 就好"的可能。

**逐字节修法(下一步实现)**:OptiFine 的 `optifine/OptiFineJar.class` 里那一处调用要改签名——
2.1.24 的第三个参数是 `Supplier<Set<String>>`,而栈上此刻是一个 `Set`。做法:

1. 在我们的 jar 里放一个极小的 `Supplier<Set<String>>` 实现(带一个吃 `Set` 的构造器),放在
   `kynarain/cn/optifineoforge/loader/` 下就行 —— rig 第 5 步本来就会把这个包的类加进产物 ✓;
2. 在 `OptifineJarFixer` 里加一条处理:`handles()` 增认 `optifine/OptiFineJar`,在那条 `invokespecial` 之前
   **插入** `NEW helper; DUP_X1; INVOKESPECIAL helper.<init>(Ljava/util/Set;)V` —— 栈就从
   `[this,name,version,set]` 变成 `[this,name,version,supplier]`,原调用照旧执行 ✓。

这与修 `toFile` 是同一类"定点插入"而非"整段替换",也是本轮学到的教训(整段替换删副作用那次)。

**修法已实现,而且暴露了一个 rig 结构问题(同一晚)**

按上面的做法做完(`SetSupplier` 放进 loader 包 ✓、`OptifineJarFixer` 增认 `optifine/OptiFineJar` 并插
`NEW/DUP_X1/INVOKESPECIAL` + 改调用描述符 ✓、`OptifineJar` 改成按名分派 `fix(name, bytes)` ✓)之后,
**第一次启动毫无变化** ✗。原因不是补丁写错,而是**产物里那份类被覆盖了**:

- 走 `-ShipPayload` 的行,OptiFine 的条目是从**管道输出**里取的,而管道输出读的是**原始 OptiFine jar**
  (未修)✗;
- 搬运步骤只把**一个**修好的类从 repack 后的 jar 里取回来 —— 就是 service 那个类(硬编码一个条目)✗,
  所以 `OptiFineJar` 的修复被原始副本盖掉了。

已把这一步改成**列出所有被修的类**(service + `OptiFineJar`)并逐个从 repack 后的 jar 取回 ✓。
第二次启动:产物里的 `optifine/OptiFineJar.class` 变成 4302 字节、含 1 处 `SetSupplier` 引用 ✓
—— 补丁确实进去了,**错误也随之换了种类**:

```
java.lang.VerifyError: Bad type on operand stack
  Location: optifine/OptiFineJar.lambda$1(...) @24: invokespecial
```

**下一步(1.20.2)**:错误从 `NoSuchMethodError` 变成同一个调用点上的 `VerifyError`,说明插入的栈操作
与那里真实的操作数形状不一致(我在推理里假设的是 `[this,name,version,set]`)。**先量再改**:把原始
`OptiFineJar.lambda$1` 从入口到那次调用之间的字节码与栈图打出来,看清第三个操作数到底是什么
(`Set`?集合构造器?数组?),再决定包一层还是换一种桥接方式。

另外:**rig 那处"取回被修类"的改动影响所有 `-ShipPayload` 行**,所以下一轮必须先跑 1.20.4、1.20.6 回归,
确认它们没有因为这次改动而变化(它们此前都是 `VERDICT: STARTED`)。

**回归已做,插入点也按字节码改了,但 1.20.2 还没过(同一晚,把两次 VerifyError 都记下来)**

- **回归 ✓**:改完 rig 之后重跑 1.20.4 与 1.20.6,两条都是 `VERDICT: STARTED (40s, marker: Sound engine started)`,
  数字与先前一致(1.20.4: 76 成员/27 类 stub、448 目标;1.20.6: 85 成员/29 类、450 目标)✓
  —— "取回所有被修类"这个改动对已验证的行没有副作用。
- 反汇编原始 `OptiFineJar.lambda$1` 看清了真实的调用序列(这正是先前推理错的地方):

```
 0: new SimpleJarMetadata          // [metadata]
 3: dup                            // [metadata, metadata]
 4: ldc "net.optifine"             // [.., name]
 6: aconst_null                    // [.., name, version]
 7: aload_0; SecureJar.getPackages()   // [.., name, version, set]
13: new ArrayList; dup; <init>     // [.., name, version, set, list]
20: invokespecial SimpleJarMetadata.<init>(String,String,Set,List)V
```

也就是说**紧挨着调用处栈顶是 `list` 而不是 `set`**,所以第一次在调用前插 `DUP_X1` 必错 ✓。
改成"在 `getPackages()` 之后(即 set 刚产生的位置)包一层"之后,`VerifyError` 从 **@24 移到了 @17**(正是新插入点 ✓),
但仍然是 `Bad type on operand stack`:

```
Location: optifine/OptiFineJar.lambda$1(...) @17: invokespecial
Reason:   Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

`uninitialized 13` 说明执行到插入点时栈顶是一个**尚未构造完的对象**(像是那个 `ArrayList`),而不是刚取出的 `set`
—— 也就是说插入位置在**打补丁后**的实际指令序列里仍不是我想的那个点(或该方法里另有路径会被走到)。
**下一步(1.20.2)**:不要再靠读原始字节码推断位置 —— 把**打补丁后**的 `lambda$1` 指令序列整段 dump 出来
(ASM 里逐条打印),按实际序列定位 `getPackages` 与我的 wrapper 的相对位置,再决定插入点;顺带确认
`getPackages` 在同一方法里是否只出现一次(搜索取的是最后一次匹配)。

**打补丁后的序列 dump 出来了:指令是对的,但 verifier 仍然不认(同一晚,所以改用"整段重写")**

```
 8: invokeinterface SecureJar.getPackages()Ljava/util/Set;      // [.., set]
13: new SetSupplier
16: dup_x1                                                      // [.., helper, set, helper]
17: invokespecial SetSupplier.<init>(Ljava/util/Set;)V          // [.., helper]
20: new ArrayList; dup; invokespecial <init>()V                  // [.., helper, list]
27: invokespecial SimpleJarMetadata.<init>(String,String,Supplier,List)V
30: areturn
```

按 javap 打出来的序列,这正是我想要的字节码(`set` 在 `helper` 之下、`invokespecial` 取顶上的 helper 当
objectref、参数是 `set`),可运行时 verifier 还是报:

```
Location: optifine/OptiFineJar.lambda$1(...) @17: invokespecial
Reason:   Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

也就是说**问题不在指令本身,而在栈图**:写入器用 `COMPUTE_FRAMES` 重算时,对"不在平台类加载器里的
`SetSupplier`"只能把公共父类判成 `java/lang/Object`(`SafeClassWriter` 的兜底),而 verifier 按帧判类型,
于是这一处的帧与实际指令不符。继续在这种"从既有序列中间插指令"的场景里跟栈图较劲,收益很低。

**决定:改成"整段重写这一个 lambda 体"**,而不是插入 —— 这条 lambda 是自包含的、没有副作用可丢,
所以替换是安全的(与 `toFile` 那次不同,那次替换丢掉了静态字段赋值):

```java
private static JarMetadata lambda$1(SecureJar jar) {
    return new SimpleJarMetadata("net.optifine", null, new SetSupplier(jar.getPackages()), new ArrayList<>());
}
```

用 ASM 从头写这段(约 15 条指令),不再触碰既有栈;`COMPUTE_FRAMES` 在这种小型直线代码上不会遇到
公共父类判定问题。这样 1.20.2 这一处就能过,且与"定点插入"的教训一致:**该整段重写时整段重写,该插入时才插入**。

**修好了:SecureJarHandler 那一处已通过(同一晚,顺手纠正了一个一直搞错的操作数顺序)**

整段重写之后错误没变,于是去看 verifier 打出来的**帧**,才发现我一直把 `invokespecial` 的操作数顺序弄反了:

```
bci: @17
stack: { uninitialized 0, uninitialized 0, 'java/lang/String', null,
         uninitialized 13, 'java/util/Set', uninitialized 13 }
Reason: Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

`invokespecial` 是**参数在栈顶**、objectref 在其下方(不是"接收者在最上面"),所以我那个
`new; dup_x1` 恰好把 helper 放到了顶上 —— 报错说的就是这个。既然集合已经在栈上,最省事的做法是
**用一个静态工厂**代替构造器:`SetSupplier.of(Set)` 返回 `Supplier`,于是整段就变成一条
`invokestatic (Ljava/util/Set;)Ljava/util/function/Supplier;`,不需要手工摆栈 ✓。

结果:**`SimpleJarMetadata` 那条错误彻底消失**(stdout 里 0 处)✓,1.20.2 已经跨过 OptiFine 的
transformation service 加载,失败点换成了下一个环境差异:

```
java.lang.NoClassDefFoundError: com/mojang/authlib/minecraft/TelemetryPropertyContainer
```

**下一步(1.20.2)**:又是"库代际差异",这次是 **authlib** —— OptiFine 的 1.20.2 preview 引用了一个
20.2.88 profile 里那份 authlib 没有的类。候选做法:①在启动脚本构造的 classpath 里把**更新版 authlib**
放在前面(启动脚本本来就在拼 classpath ✓,这是最干净的一条);②像 Forge API 那样为 `com.mojang.authlib.*`
做壳 —— 但要小心与真实 authlib 模块的包冲突,**不推荐先做这条**。

**authlib 补齐,1.20.2 已经进标题界面并带着 OptiFine 跑起来(同一晚)**

量出来的事实是:原版 1.20.2 profile 要 **authlib 5.0.47**,而这份**根本没装**(本地只有 1.5.25 / 3.18.38 /
4.0.43 / 6.x / 7.0.61)✗ —— 启动脚本拼 classpath 时对缺失的库是跳过的,于是 OptiFine 一用到 authlib 的类就
`NoClassDefFoundError`。从 `libraries.minecraft.net` 取回 5.0.47(111,087 字节)之后:

| 指标 | 1.20.2(本次) |
|---|---|
| `Setting user`(进标题界面) | ✓ |
| `Sound engine started` | ✓ |
| `[Shaders] OpenGL` | ✓ |
| `[OptiFine]` 日志 | **229 行** |
| 替换类 | 238 个 |
| 运行时长 | 从 5 秒变成 **20 秒** |

也就是**这一行已经带着 OptiFine 跑到标题界面**,随后在一次**反射**里失败(新 crash report,
`00:08:18`):

```
java.lang.NoClassDefFoundError: net/minecraft/world/level/block/state/BlockState
	at java.base/java.lang.Class.getDeclaredMethods0(Native Method)
	at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
	at net.optifine.reflect.ReflectorResolver.resolve(ReflectorResolver.java:45)
```

**下一步(1.20.2)**:这条 `NoClassDefFoundError` 出现在 OptiFine 的 `Reflector` 解析阶段,而 `BlockState`
是**游戏类**(不是库)—— 先量清楚它到底为什么加载不了(是模块读取问题,还是我们换进去的某个类把
`BlockState` 的加载路径弄坏了);这与 1.20.4 早先那次反射路径里的 `ClassNotFoundException: ItemStack` 是同一类现象,
可以对照那次的处理方式。

**1.20.2 的失败点其实是"桥在 1.20.2 上没配上对",而且它是静默的(同一晚)**

真正的最新 crash report(`00:08:18`)不是 BlockState,而是:

```
java.lang.NoSuchMethodError:
  'java.util.Optional net.minecraft.client.particle.ParticleEngine$1ParticleDefinition.f_243741_()'
	at net.minecraft.client.particle.ParticleEngine.lambda$reload$8(ParticleEngine.java:286)
```

调用方要的是 **运行时那个名字**(`$1ParticleDefinition`)、方法名是 **SRG 形状**(`f_243741_`)—— 也就是说
**被装载的是运行时自己那份**(官方成员名,自然没有 `f_243741_`),而不是我们按运行时名字 ship 的那份。
去产物里核实,原因一目了然:

```
产物条目: optifineoforge/patched/net/minecraft/client/particle/ParticleEngine$ParticleDefinition.class
索引里  : net/minecraft/client/particle/ParticleEngine$ParticleDefinition
```

**桥在 1.20.2 上没有给这个类配上对**(仍然是载荷自己的名字),所以搬运时也没改名、索引里也没有运行时那个名字,
于是运行时那份被装了进去。而 `NestedNameBridge` 的**判据是结构完全相同**(父类 + 字段描述符集合 + 方法描述符集合):
1.20.2 上这两份 `ParticleDefinition` 的成员集合并不完全相同(NeoForge 那侧有补丁),于是"恰好一个候选"的条件不成立,
**它什么也没说就放弃了** —— 这是最值得改的一点:配对失败必须**出声**。

**下一步(1.20.2,两处一起改)**:
1. **让未配对可见**:列出"载荷有 `$`、运行时没有同名类、但结构也没配上"的类(以及候选与差异),否则这类
   静默放弃以后还会以完全不相干的报错形式出现;
2. **放宽判据但要仍然可判定**:记录类(record)的两份拷贝成员名可以不同、描述符应当相同,所以对 record
   允许用"父类相同 + **字段描述符集合**相同"来配对;真正无法判定时宁可报歧义,也不要猜。

**放宽 + 出声都做了,于是看到了真正的两处问题(同一晚,上一段的推断被自己的测量推翻)**

先纠正上一段的一个错判:产物里那个**条目名**仍是 `…/ParticleEngine$ParticleDefinition.class`,但 javap 打出来
**类内部的自己的名字已经被改成运行时名字**:

```
payload: final class net.minecraft.client.particle.ParticleEngine$1ParticleDefinition extends java.lang.Record
payload: public net.minecraft.resources.ResourceLocation f_244103_();
payload: public java.util.Optional<…> f_243741_();
client-1.20.2-…-srg.jar : net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class
neoforge-20.2.88-client.jar: net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class
```

也就是说**桥其实配上了、名字也改了**,我却只看了条目名就下了"没配上"的结论 —— 这正是"要量到点上"的又一例。
真正的问题是**两处**:

1. **搬运只改了类、没改条目路径**:`--rewrite` 把类内部名字改成 `$1ParticleDefinition` 了,但搬运步骤仍按
   **改写前的条目名**生成 `optifineoforge/patched/…$ParticleDefinition.class`,索引里也是旧名 ✗。
   ML 是**按条目路径**找类的,它要的是 `…$1ParticleDefinition.class` → 找不到 → 于是**运行时自己那份**被装了进去 ✗。
   (修法:搬运用改写后 jar 的**条目名**;索引同理 —— 这条要连着 `--rewrite` 的产物一起对。)
2. **载荷里那个 record 的访问器还是 SRG 名**(`f_243741_()`、`f_244103_()` ✗),而运行时那份是官方名 ✗ ——
   这正是 `SrgRemap` 在 1.20.2 上"80 条未解析"里的一部分:**record 的访问器与它的组件字段同名**,
   而映射表里只有字段那条 ✗。修法:改名的表里,对"方法名与同类字段名相同"的情形**沿用该字段的映射**
   (record 的结构保证这一点),这样访问器就会被改成官方名 ✓。

**下一步(1.20.2)**:先改①(条目名与索引),因为它决定"到底装的是哪一份";再改②(record 访问器沿用字段映射),
两者都做完再启动一次。注意①②互不替代:即使装对了那一份,访问器仍是 SRG 也会与运行时的官方名对不上。

**①的一行根因找到了,1.20.2 打通 —— 1.20.x 四行全覆盖(同一晚)**

量到点上之后,根因是一行代码:改写工具在**查配对表时用了带 `srg/` 前缀的名字**,而配对表的键是**不带前缀的类名**
✗ —— 于是**字节里的常量池被改了(那里用的是裸类名)、条目路径却没改** ✗✗。产物里因此同时出现:

```
entry srg/net/minecraft/client/particle/ParticleEngine$ParticleDefinition   ← 旧名(条目)
pool  …ParticleEngine$1ParticleDefinition                                    ← 新名(类自己)
```

ML 按**条目路径**找类,自然只找到运行时那一份。修法就是查表前先剥掉 `srg/` 前缀、写回时再加回去 ✓。
修完后产物条目变成 `optifineoforge/patched/net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class` ✓,
启动给出:

| 指标 | 1.20.2(本次) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** |
| `Setting user`(标题界面) | ✓ |
| `[Shaders] OpenGL` | ✓ |
| `Connected textures` | 2 行 ✓ |
| `Pre-stitch`(OptiFine 拼图集) | 13 行 ✓ |
| `[OptiFine]` 日志 | **239 行** ✓ |
| 替换类 | 246 个 |
| 新 crash report / 截图 | **无** ✓ / 916 KB ✓ |

**1.20.x 这一条分支到此四行全覆盖**:1.20.1、1.20.2、1.20.4、1.20.6 —— 每行都带着 OptiFine 跑起来
(shaders + CTM + 图集),而且各自用了不同的路线(1.20.6 无补丁树 + ship;1.20.4 SRG→官方名重映射;
1.20.1 原样 SRG + 类名对齐;1.20.2 = 1.20.4 的路线 + SecureJarHandler 调用修复 + 类名对齐)。

**遗留小项(下一轮顺手看)**:1.20.2 这次 `stderr.log` 是 14,625 字节(1.20.1 那次只有 27 字节),
虽然 verdict 是 STARTED 且无新 crash report,但值得读一遍确认里面只是良性警告。

**转向 1.21.x:rig 驱动不动那一行,原因是"两边的工具集代差"(同一晚,已量到具体类名)**

建好 `1.21.x` 的 worktree(分支 21db5b2)、确认 21.4.149 用的是 **ModLauncher 11.0.4 + FML 6.0.18**、
游戏 jar 是官方名之后,用现在的 rig 去建 1.21.4 会在**打桩那一步**失败,报的是三个"类找不到":

```
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.NestedFamilyGuard
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.NestedNameBridge
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.MissingTargets
```

原因很清楚:`1.21.x` 分支的 `optifine` 工具集只有

```
DonorVerifier  ForgeApiShims  MemberRestorePlan  OptifineConfig
OptifineJar    OptifineJarFixer  OptifinePipeline
```

——**没有** `MissingTargets`(打桩)、**没有** `SrgRemap`(改名)、也没有这一轮新写的 `NestedFamilyGuard` /
`NestedNameBridge`。这些都是这几十轮在 **1.20.x** 分支上长出来的,而两条分支按项目纪律是**互相独立**的
(不做跨分支合并),所以"拿 1.20.x 的 rig 去驱动 1.21.x"必然缺件。

**两条可选路线(下一轮择一)**:
1. **按行降级**:给 rig 加一个"工具集"开关,驱动 1.21.x 时跳过 3b1(家族)/3b4(桥)/3b2(打桩)——
   1.21.4 当初**就是在这些工具存在之前**跑通的(那时的产物保留了补丁数据 ✓),所以这条路是**有先例、有把握**的;
2. **把成熟工具集移植到 1.21.x 分支**(拷贝并按该行的命名空间调整)——更彻底,但工作量大,而且要先确认那一行
   到底需不需要(1.21.x 的载荷已是官方名 ✓,未必需要 SRG 改名那一套)。

先走 1 把 1.21.4 重新跑通拿回基线,再决定移植哪些工具到 1.21.x。

**路线 1 做完:1.21.4 拿回基线(同一晚)**

给 rig 加了 `-NoNameBridge`(家族那一步本来就有 `-NoFamilyGuard`,打桩那一步已有 `-StubMissing $false`),
`build-line.ps1` 的 `1214` 条目就显式声明"这一行用自己那套工具":`StubMissing = $false`、
`NoFamilyGuard = $true`、`NoNameBridge = $true` —— 于是不再调用那条分支没有的三个工具 ✓。
(过程中因为重复键 `StubMissing` 报了一次 PowerShell 的 `DuplicateKeyInHashLiteral`,是编辑失误,已修。)

1.21.4 实测(与当初那条基线一致,而且更干净):

| 指标 | 1.21.4(本次) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** |
| `Setting user`(标题界面) | ✓ |
| `[Shaders] OpenGL` | ✓ |
| `Connected textures` | 3 行 ✓ |
| `Pre-stitch` | 14 行 ✓ |
| `[OptiFine]` 日志 | **232 行** ✓ |
| `Replaced net` | **0**(这一行本来就是让 OptiFine 自己的 transformer 打补丁)✓ |
| **stderr** | **0 字节** ✓ |
| 新 crash report / 截图 | **无** ✓ / 1.15 MB ✓ |

**到此前五行已实机验证**:1.20.1、1.20.2、1.20.4、1.20.6(1.20.x 分支)+ 1.21.4(1.21.x 分支)。
下一步是 1.21.x 分支上的其余行(1.21 / 1.21.1 / 1.21.3 / 1.21.6–1.21.11):它们的 OptiFine jar 和
对应的 NeoForge 都要按 `fetch-optifine.ps1` 的办法去取/安装,然后复用 `1214` 这套参数(那条分支的工具集、
ModLauncher 11、官方名载荷)。

**1.21.1 的准备度(同一晚,一半到位)**

- **原版 1.21.1 客户端 jar ✓**:从镜像取版本 json 再按 `downloads.client.url` 从 Mojang 的 piston-data 下,
  得到 26,836,906 字节(`versions\1.21.1\1.21.1.jar`)。
- **NeoForge 21.1.x 一共 243 个构建,最新 21.1.250** ✓,installer 也下下来了,但**装出来的东西不完整**:

```
client jar present: False
profile present: True
libraries\net\neoforged\neoforge\21.1.250\  → 空
```

也就是说 profile 目录建了、`neoforge-21.1.250-client.jar` 没有,而且 `libraries` 下对应目录是**空的** ——
这与 20.6.141 那次"安装器提前失败"是同一形态。**下一步**:像 20.6.141 那样把 installer 的输出**完整**记到文件
再读(这一轮脚本只把输出追加进日志、但日志里没有它期待的报错,所以要先把 stdout/stderr 都存下来),
按它报的坐标逐个手工取回,再重跑安装。

OptiFine 侧:1.21.1 的正式版 jar(`OptiFine_1.21.1_HD_U_J1.jar`)本地已有 ✓。

**1.21.1 装好了、能启动,但 OptiFine 没生效 —— 分行差异的教科书例子(同一晚)**

装好之后(NeoForge 21.1.250,client jar 5,675,624 字节;顺带记一个坑:installer 连报两次
`SocketTimeoutException`,依次手工取回 `neoforge-21.1.250-universal.jar` 与
`net.neoforged:neoform:1.21.1-20240808.144430@zip` 后才装上 ✓),这一行:

| 指标 | 1.21.1 |
|---|---|
| `VERDICT` | `STARTED (40s, marker: Sound engine started)` |
| `Setting user` | ✓ |
| `[Shaders] OpenGL` / `Connected textures` / `Pre-stitch` | **0 / 0 / 0** ✗ |
| `[OptiFine]` 日志 | **0 行** ✗ |
| stderr | **1,388,996 字节 / 12717 行** ✗ |

stderr 的头部就是原因:

```
java.io.IOException: Base resource not found: akr.class
	at LAYER SERVICE/optifine/optifine.Patcher.applyPatch(Patcher.java:148)
```

**`akr.class` 是混淆名** —— 也就是说 **1.21.1 的 OptiFine 载荷是按 SRG/混淆基名打补丁的**,而这一行的运行时
交给它的是官方名,于是它一个补丁都贴不上(与 1.20.2 / 1.20.4 当初同一个病)。1.21.4 之所以没事,是因为
它那一版 OptiFine 与运行时的命名**正好对得上**(stderr 0 字节 ✓)。

**结论(下一轮的方向已确定)**:1.21.1 这一行需要 1.20.2/1.20.4 用过的**离线载荷路线** —— 即
`ShipPayload` + `SrgRemap`(SRG→官方名)+ `MissingTargets`(打桩)—— 而这些工具**都不在 1.21.x 分支上**。
所以路线 2 不再是"可选":**要把成熟工具集移植到 1.21.x 分支**(`SrgRemap`、`MissingTargets`、
`NestedFamilyGuard`、`NestedNameBridge`,以及与该分支命名空间相应的映射文件),同时保留"该行用自己工具集"
的开关,因为 1.21.4 只需要其中的一部分。

**移植已完成(同一晚,提交在 1.21.x 分支上)**

把这一支缺的八个文件带过去了(提交 `e8b6de6`,该分支 21db5b2 → e8b6de6):

```
SrgMemberMap  SrgRemap       SRG→官方名成员表与改写器
MissingTargets               打桩
NestedFamilyGuard            编号家族(不是同一个类就不换)
NestedNameBridge             两个产物拼写不同的嵌套名
OptifineJarFixer/OptifineJar union 路径与 SecureJarHandler 两处修复 + 按名分派
loader/SetSupplier           SecureJarHandler 修复里要传的那个对象
```

在那条分支上用 ASM 编译通过(只有一条 deprecation 提示)✓。**注意 `OptifineJar` 与 `OptifineJarFixer`
是被"覆盖"而不是"新增"的** —— 也就是说 **1.21.4 那条已验证的线必须重跑一次回归**(它当初是用该分支自己那版
这两个类跑通的),确认移植没有改变它的行为;这是下一轮的第一件事。

**1.21.1 续做清单**:①取 `mcp_config-1.21.1.zip` 并解出 `joined.tsrg`(1.20.x 侧已有 `fetch-mcp.ps1` ✓);
②`1211` 条目改成离线路线(`ShipPayload = $true`、`SrgRemap = $true` + 两个映射文件、`StubMissing = $true`、
`NoFamilyGuard`/`NoNameBridge` 关掉);③build+launch,按 1.20.2 的验收口径取证。

**① ② 做完后的实测:补丁器不叫了,但"没人换类"(同一晚)**

- **1.21.4 移植回归 ✓**:重跑一次仍是 `STARTED (40s)`、`Setting user` ✓、**232 行 `[OptiFine]`** ✓、
  `Pre-stitch` ×14 ✓、**stderr 0 字节** ✓ —— 与移植前的基线完全一致,说明覆盖那两个类没有副作用 ✓。
- `mcp1211-joined.tsrg` 已解出(6,485,971 字节)✓,`1211` 条目已切到离线路线 ✓。构建结果:

```
rewrote 0 method and 0 field names          ← 这一行的载荷本来就是官方名,改名是空转(正常)
scanned 1128 classes, 114 missing           ← 打桩跑通
payload: 1128 classes, 420 moved to optifineoforge/patched
compared 230 replaced classes, 102 members to restore
VERDICT: STARTED (40s, marker: Sound engine started)
stderr: 0 字节                              ← 1,388,996 → 0,补丁器那 12717 条彻底消失 ✓
```

但同一份日志里:

| 指标 | 1.21.1(离线路线) |
|---|---|
| `Replaced net` | **0** ✗ |
| `[OptiFine]` / `Shaders` / `CTM` / `Pre-stitch` | **0 / 0 / 0 / 0** ✗ |

也就是说**载荷 ship 进去了,却没有任何东西把它装上去** —— 原因很直接:**1.21.x 分支没有"换类"的 transformer**
(`PatchedClassTransformer` 是 1.20.x 分支上的东西;1.21.x 那一支当初靠 OptiFine **自己**的 transformer 换类,
所以只有"修 bug 的 transformer",没有"换类的 transformer")✗。

**下一步(1.21.1)**:把 `PatchedClassTransformer` 也移植过去(它要读 `patched-index.txt` / `stubs.txt` /
`keep-runtime.txt` ✓ 这三个文件搬运步骤已经产出 ✓),并在该分支的 service 里注册(顺序仍是"换类在前、
成员回填在后" ✓)。这一支的 transformer 本来就是按 ModLauncher 11 写的,所以不需要 1.20.x 那套 ml10/ml11 适配层。

---

## 1.21.1 换类跑通:两个根因 + 一条模块层的硬边界(2026-09-16 凌晨)

**换类 transformer 移植后:73 → 313 个类装上,但崩在两个更后面的地方。** 两个根因都被实测定位,修完之后
1.21.1 与已验证的 1.21.4 判据齐平。

### 根因一:打桩把 `com.mojang.serialization.Codec` 当成了"不存在的类"

第一版移植后崩在 `TargetedConditionalEffect.equipmentDropsCodec`:

```
NullPointerException: Cannot invoke "Codec.fieldOf(String)" because the return value of
  "Codec.validate(Function)" is null
```

不是 OptiFine 的问题,是**打桩步骤的输入不完整**:`MissingTargets` 的运行时列表里只有 NeoForge 那几个 jar,
没有 `com.mojang:datafixerupper`,于是扫描看不到 `Codec`,判定"运行时没有" → 给真正的编解码器换了个
**返回 null 的空实现** → 游戏恰好在等一个真 Codec 的地方炸。修法是把载荷真正会调到的库也交给扫描:

```
RemapRuntime = @( …… , com\mojang\datafixerupper\8.0.16\datafixerupper-8.0.16.jar,
                        com\mojang\brigadier\1.3.10\brigadier-1.3.10.jar )
```

**教训**:打桩列表是"世界有多大"的定义,漏一个库不会报错,只会让一个不该被打桩的类变成 null 工厂。

### 根因二:载荷的 Forge 父类在 NeoForge 运行时不存在 —— 换类会破坏继承关系

第二个崩在 `AttachmentSync.onChunkSent`,离换类很远:

```
VerifyError: Bad type on operand stack
  Location: net/neoforged/neoforge/attachment/AttachmentSync.onChunkSent(...) @82: invokestatic
  Reason:   Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
            'net/neoforged/neoforge/attachment/AttachmentHolder'
```

实测两边的声明(javap):

| | 父类 | 接口 |
|---|---|---|
| 载荷(OptiFine 1.21.1,Forge 目标) | `net.minecraftforge.common.capabilities.CapabilityProvider<BlockEntity>` | `net.minecraftforge.common.extensions.IForgeBlockEntity` |
| 运行时(neoforge-21.1.250-**client**.jar,已打补丁的游戏) | `net.neoforged.neoforge.attachment.AttachmentHolder` | `net.neoforged.neoforge.common.extensions.IBlockEntityExtension` |

两个关键测量:

1. **游戏类来自 `neoforge-<版本>-client.jar`**,不是 `client-<版本>-srg.jar` —— 后者里 `BlockEntity extends
   java.lang.Object`(原版形态),前者里才是 NeoForge 改过的形态。所以"运行时的版本"必须取 client jar。
2. **载荷里"父类是 Forge 类型"的类只有一个**:`BlockEntity`。其余 32 个提到 `net/minecraftforge` 的游戏类
   都只是在 `implements` 里提到接口(接口可以靠 shim 补齐)。判据是逐类 javap 声明行,不是猜。

### 试错记录:shim 改父类行不通,模块层不允许

第一版修法是"把 shim 的父类改掉"(让 `CapabilityProvider extends AttachmentHolder`),构建侧已经能自动测出
这个映射(比对载荷与运行时的父类),但运行期立刻:

```
NoClassDefFoundError: net/neoforged/neoforge/attachment/AttachmentHolder
  at cpw.mods.cl.ModuleClassLoader.loadFromModule(ModuleClassLoader.java:311)
```

原因在同一份日志的模块图里:

```
module graph: our module is optifine, layer manager captured
module graph: GAME layer holds srg mixinextras.neoforge mixin_synthetic minecraft neoforge
module graph: optifine -> minecraft: reads it = false, …… exported to it = true
```

**我方 jar(`optifine` 模块)在 GAME 层之下,模块只能读它下面的层** —— 所以 shim 引用游戏层的
`AttachmentHolder` 解析不了;反过来 GAME 层的游戏类引用我方 shim **是通的**(前面能换 313 个类就是证据)。
结论:**shim 永远只能是"根"(只 extends Object)**,要修继承关系只能在"被换进游戏层的那份字节"上修。

### 修法:在换进去的类上改父类(离线出计划 + 运行期改写)

- 新增工具 `HierarchyPlan`(`src/.../optifine/HierarchyPlan.java`):拿**载荷 jar + 运行时 jar**(client jar
  在前,它才是打补丁后的游戏)比对每一对同名类,对"载荷父类是 `net/minecraftforge/*`,运行时父类不是
  Object"的类,检查两件事 —— 运行时父类**是否有可被子类调用的无参构造**(`AttachmentHolder()` ✓)、
  载荷类体里**除构造链之外是否还提到旧父类** —— 两条都过才写进 `reparent.txt`。工具不能放进运行期:
  transformer 手里只有"同一个类的两份字节",看不到它上面的那个父类。
- loader 侧 `PatchedClassTransformer.reparent(...)`:按计划表把 `superName` 换成运行时的父类,并把每个构造
  函数里 `invokespecial CapabilityProvider.<init>(Ljava/lang/Class;)V` 改成
  `invokespecial AttachmentHolder.<init>()V`(参数用 `POP` 丢掉 —— Forge 的父类只是把 `self` 存起来,
  NeoForge 的父类把 map 放在对象自己身上,丢参数是**正确的翻译**,不是走捷径)。**没有计划表就不动**,
  计划表与运行时父类不一致就拒绝换类并写明原因。
- 同一轮里加的"接口并集"对**类**也生效(原先只对接口):运行时类实现了 NeoForge 的扩展接口,
  NeoForge 自己的代码会强转它,载荷版没有这个接口就会在第一次强转处 `ClassCastException`。
  实测这一支只有 1 个接口被并进来(`IBlockEntityExtension`),它唯一的抽象方法 `getPersistentData()`
  正是成员回填计划已经补上的那一个(`BlockEntity.customPersistentData`)✓,所以并集是安全的。

### 1.21.1 现在的判据(与 1.21.4 齐平)

| 指标 | 1.21.1(neoforge-21.1.250) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** ✓ |
| `Setting user` | ✓ |
| `Replaced ` / 换类目标 | **313 个类装上** / 目标 426 ✓ |
| `[OptiFine]` 日志 | **223 行** ✓ |
| `Shaders`(SMCLog 初始化 + 装载配置) | ✓ |
| `Pre-stitch` / `Connected textures` | **14 / 3** ✓ |
| `Caught error` | **0** ✓ |
| stderr | **0 字节** ✓ |
| `BlockEntity` | 载荷版装上(带 OptiFine 自己的 `nbtTag` / `nbtTagUpdateMs` / `requestModelDataUpdate`)✓ |

**1.21.1 与 1.21.4 的差异(记录用)**:1.21.1 的 OptiFine 载荷按 SRG/混淆基名打补丁、运行期 transformer
要"混淆基类资源"而运行期给不出(`Base resource not found: akr.class` ×12717),所以走**离线换类**;
1.21.4 则是 OptiFine 自己那份 transformer 在运行期补丁(打的是 NeoForge 已改过的类,继承关系天然保留)。

---

## 把"改父类"移到 1.20.x:一次测量、两次拒绝、一条边界(2026-09-16 凌晨)

1.21.1 的修法(`HierarchyPlan` 出计划 + loader 在换进去的类上改父类)在 1.20.x 上先照搬了一遍,结果是
**两条已验证的线立刻给出反例**,而这两个反例都很值钱:它们把"什么情况下不许改"写成了可判定的规则。

### 先说结论:这条分支现在**不启用**父类改写(默认没有计划就是不改)

1.20.4/1.20.2 的载荷 `BlockEntity` 同样 `extends net.minecraftforge.common.capabilities.CapabilityProvider`,
而运行时的父类是 NeoForge 的替代品:

| 线 | 运行时父类 | 无参构造 | 载荷是否覆写它的 final 成员 |
|---|---|---|---|
| 1.21.1 / 20.4.251 | `net.neoforged.neoforge.attachment.AttachmentHolder` | 有(`AttachmentHolder()`)✓ | 没有 ✓ → **可改** |
| 1.20.2 / 20.2.88 | `net.neoforged.neoforge.common.capabilities.CapabilityProvider` | 没有,只有 `(Class)` / `(Class,boolean)` | **有** ✗ → 不许改 |

1.20.2 的实测崩法(改父类那一版):

```
IncompatibleClassChangeError: class net.minecraft.world.level.block.entity.BlockEntity
  overrides final method
  net.neoforged.neoforge.common.capabilities.CapabilityProvider.serializeCaps()Lnet/minecraft/nbt/CompoundTag;
```

因为 20.2 那一版的 `CapabilityProvider` 把 `gatherCapabilities()` / `getCapabilities()` / `serializeCaps()` /
`deserializeCaps(CompoundTag)` 全声明成 **final**,而 OptiFine 的 `BlockEntity` 恰好覆写了它们(这些方法是
**打桩步骤**补进载荷的,所以要在**打过桩的 jar** 上判,不是在补丁产物上判 —— 第一版判据看错了 jar,
于是"检查存在但没拦住",这一点也是实测出来的)。

修法两步,都落在工具里而不是靠人记:

1. `HierarchyPlan` 增加一条判据:**载荷声明了运行时父类声明为 final 的同名同描述符成员 → 拒绝**。
   实测输出(1.20.2,按 rig 的 jar 顺序):
   `no reparent for net/minecraft/world/level/block/entity/BlockEntity: it declares
   deserializeCaps(Lnet/minecraft/nbt/CompoundTag;)V, which the runtime superclass … declares final` ✓
2. 1.20.x 的 loader 里,父类改写的**失败不再是"拒绝换类",而只是记一行日志**:这条分支的已验证行为是
   "照载荷自己的父类换进去",改成拒绝就是改了已验证的东西。1.21.x 侧保留拒绝(那一支的实测是
   "拒绝 = 记一行 + 游戏照跑,不拒绝 = VerifyError 崩",所以拒绝更安全)。

### 一次被排除的假归因(记录用)

1.20.4 装上父类改写的第一次运行里 stderr 有 14,481 字节,里面是

```
NoClassDefFoundError: net/minecraft/world/level/block/state/BlockState
Caused by: ClassNotFoundException: net.minecraft.world.level.block.state.BlockState
  at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
```

看起来很像是这次改动的副作用,**但不是**:改动之前的基线运行 `logs/run-nf1204-regcheck` 的 stderr 是
**同样 14,481 字节、同样 8 条错误、同样三条头**(`BlockState` / `Caused by` / `ItemStack`)。也就是说
1.20.4 早就带着这个缺陷在跑(判据 `Setting user` ✓、`Pre-stitch` ×13、OptiFine 着色器与 CTM 都在跑),
它是一条**独立的待办**,不是这次改动引入的。**教训**:回归比较要比"stderr 大小 + 错误行集合",
不能只看"有没有报错"。

### 这条分支现在的状态

- `HierarchyPlan` 已进 1.20.x 的离线工具集(`src/.../optifine/HierarchyPlan.java`),rig 的构建步骤会跑它并
  把结果作为 `optifineoforge/reparent.txt` 打进 jar;**没有这个文件 = 一行都不改**,这就是默认。
- 1.20.2 的计划是空(被 final 覆写判据拒绝)✓,1.20.4 的计划是
  `BlockEntity → AttachmentHolder via ()V` ✓,1.20.1 无计划(那一支的 Forge 包就是运行时自己的包,
  两边继承关系本来就一致)✓。

### 四条线的回归(2026-09-16 00:55–00:59,同一批连续跑)

| 线 | VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | `Caught error` | stderr | 父类改写 |
|---|---|---|---|---|---|---|---|---|
| 1.21.1 / 21.1.250 | `STARTED (40s)` | ✓ | 223 | 14 | 3 | 0 | **0 字节** | **已改写** ✓(plan 1 条) |
| 1.21.4 / 21.4.149 | `STARTED (40s)` | ✓ | 232 | 14 | 3 | 0 | **0 字节** | 无计划(该线不换类,OptiFine 自己补丁 474 个目标)✓ |
| 1.20.4 / 20.4.251 | `STARTED (40s)` | ✓ | 241 | 13 | 3 | 0 | 14,481 字节(= 基线) | **已改写** ✓(plan 1 条) |
| 1.20.2 / 20.2.88 | `STARTED (40s)` | ✓ | 239 | 13 | 2 | 0 | 14,631 字节(= 基线形状) | 计划为空 ✓,日志写明"照载荷自己的父类换" |

1.21.4 那一行的 232 行 `[OptiFine]` 与 14 次 `Pre-stitch` 与它移植前的基线**完全一致**,说明这一轮改动没有
碰到它的路径 ✓。1.20.2 是这一轮唯一"先坏后修"的线:改父类那一版 `EXITED (10s)`,加上 final 判据之后回到
`STARTED` ✓。

---

## 1.21.3 一次跑通,以及 1.21.9 之后真正的路障(2026-09-16 凌晨)

### 1.21.3(neoforge-21.3.97 + OptiFine J2)

准备这一步这轮被脚本化了,因为它对剩下的每一条线都要做一次,而手工做每次都漏东西:

- `prepare-line.ps1`:给一个 MC 版本和 NeoForge 线前缀,取原版 client jar(镜像 → Mojang)、取该线最新的
  NeoForge installer、装到 `libraries/`,**并解析 installer 自己报出来的"下载失败的库"再手工补齐**。
- `ensure-vanilla-libs.ps1`:按版本 json 逐个核对 `libraries/` 里是否真有那个库,缺的按 json 里的 URL 取。

这两件事都是被同一类失败逼出来的:`NoClassDefFoundError` / `ClassNotFoundException` 指向一个**原版库**
而不是我们的东西。1.21.3 的第一次启动就死在

```
Caused by: java.lang.ClassNotFoundException: com.mojang.authlib.properties.Property
  at net.minecraft.SharedConstants.<clinit>(SharedConstants.java:177)
```

`authlib` 没在本地(profile 里列着,launcher 就少解析一个 jar),补上之后第二次启动即通过。另外 installer
也会自己失败:`SocketTimeoutException: Connect timed out`,而**同一条 URL 我们手工取是通的** —— 所以
"installer 报缺库 → 手工取 → 再跑 installer"是一条固定的修复路径,不是偶发。

1.21.3 的判据:

| 指标 | 1.21.3(neoforge-21.3.97) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** ✓ |
| `Setting user` | ✓ |
| 换类 | **268 个类装上** / 目标 445 ✓ |
| `[OptiFine]` 日志 | **225 行** ✓ |
| `Pre-stitch` / `Connected textures` / 着色器 | **14 / 3 / ✓** |
| `Caught error` | **0** ✓ |
| stderr | **0 字节** ✓ |
| 父类改写 | `BlockEntity` → `AttachmentHolder` ✓(与 1.21.1 同形) |

构建侧的实测:载荷的 SRG 改名是空转(`rewrote 0 method and 0 field names`)——和 1.21.1 一样,这一线的
载荷本来就是官方名;`compared 240 replaced classes … 110 members to restore`;计划表 1 条(父类改写)。

### 1.21.9 起没有 ModLauncher —— 这是 1.21.9/1.21.10/1.21.11/26.1.2 的真正路障

实测 21.11.45 装出来的 profile(`versions/neoforge-21.11.45/neoforge-21.11.45.json`):

```
mainClass: net.neoforged.fml.startup.Client
libraries: 26 个 —— fancymodloader(earlydisplay/loader 10.0.36)、sponge-mixin 0.16.5、ASM 9.8、
           JarJarSelector/Metadata、bus、accesstransformers … 
           **没有 modlauncher,没有 securejarhandler,没有 bootstraplauncher**
```

也就是说这一代 FML **不再有 ModLauncher**,我们现在的挂载点(实现
`cpw.mods.modlauncher.api.ITransformationService` / `ITransformer<ClassNode>`、把成品类塞进 GAME 层模块)
在上面根本不存在;OptiFine 自己那份 `META-INF/services/cpw.mods.modlauncher.api.ITransformationService`
也同样无处可挂 —— 实测 **1.21.11 J9 的 jar 里只有这一个 service 文件**,没有任何新 loader 的入口。

新 loader 的挂载点是 `net.neoforged.neoforgespi.transformation.ClassProcessor`(ServiceLoader 注册),
API 形状和我们现有 transformer 几乎一致:

```
public interface ClassProcessor {
  ProcessorName name();
  boolean handlesClass(SelectionContext);
  ComputeFlags processClass(TransformationContext);
  default void afterProcessing(AfterProcessingContext);
  default void link(LinkContext);
}
public abstract class SimpleClassProcessor extends BaseSimpleProcessor {
  public abstract void transform(org.objectweb.asm.tree.ClassNode, SimpleTransformationContext);
  public abstract Set<SimpleClassProcessor.Target> targets();     // ← 与现有 targets() 同形
}
```

`SimpleClassProcessor` 收的正是 ASM 的 `ClassNode`,所以 `PatchedClassTransformer`(换类 + 父类改写 + 接口
并集 + 打桩)与成员回填那套逻辑可以整体搬过去,变的只是外层接口与注册方式。

**而 26.1.2 是另一条路**:实测 OptiFine `K1_pre2` 的 jar 里已经有

```
META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor
META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator
optifine/OptiFineClassProcessor.class
  extends optifine.OptiFineBaseTransformerService
  implements ClassProcessor, IModFileCandidateLocator
```

——**新版 OptiFine 自己就支持新 loader**。所以 26.1.2 那一条的正确做法不是我们移植补丁器,而是让
NeoForge 接受 OptiFine 的 jar 并让它自己的 processor 跑起来(要处理的正是 FML 的
`IncompatibleModReason.OPTIFINE` 拒绝与 `loaderVersion` 一类的元数据门槛)。这比 1.21.11 那条线轻得多,
优先级也应该更高。

**剩下的工作因此分成两类**,不能再按"照 1.21.1 抄一遍"来做:

1. **ModLauncher 世代**(1.21 / 1.21.6 / 1.21.7 / 1.21.8):现有 loader 与 rig 直接可用,把这四条按
   1.21.3 的路子铺完即可。
2. **FML 10 世代**(1.21.9 / 1.21.10 / 1.21.11 / 26.1.2):需要 `ClassProcessor` 形态的挂载点。26.1.2 先做
   (OptiFine 自带 processor),1.21.9–1.21.11 则要把本项目的离线载荷换装逻辑搬到一个 `SimpleClassProcessor`
   上 —— 载荷、计划表、父类改写、成员回填都已经是离线产物,搬的是挂载点而不是算法。

---

## 1.21.8:换装的四个新坑,一次一个(2026-09-16 凌晨)

1.21.8(neoforge-21.8.54 + OptiFine `J6_pre16` 预览)是这一轮里"每修一个坑就前进一段"最典型的一条。
四次启动,四次都在前一次完全不同的地方停住,而每一次的根因都能写成一条通用规则:

| # | 现象 | 根因 | 规则 |
|---|---|---|---|
| 1 | `IllegalArgumentException: Only one quick play option can be specified`(Argument parsing) | 启动脚本把 profile 里四个 `${quickPlay*}` 占位符原样传给了游戏,1.21.3 之前容忍、1.21.8 不容忍 | 值仍是 `${...}` 占位符的参数**整条丢弃**(launcher 侧) |
| 2 | `ClassFormatError: Illegal field modifiers in class BlockStateModel$Unbaked: 0x9` | 供体类是按"普通类"写出的,接口字段于是变成 `public static`(0x9,无 final) | **运行时给接口加过成员的接口不换装**:接口的静态字段只能在它自己的 `<clinit>` 里赋值,换了类就等于换掉了那段赋值 |
| 3 | `NoSuchMethodError: RenderPipelines.lambda$registerCustomPipelines$0` | 载荷是按**原版**编译的,原版没有 `registerCustomPipelines`;这个方法从运行时回填了,但它调用的 lambda 被"合成成员一律不回填"的老规则挡掉了 | **lambda/access 合成方法:只要载荷里连这个名字都没有,就回填**(有同名才跳过,因为两边编号独立) |
| 4 | `NullPointerException: RenderSystem.PIPELINE_MODIFIERS is null`(第一帧渲染时) | 回填的**静态**字段只有声明没有值 —— 值在运行时的 `<clinit>` 里,而换装把 `<clinit>` 换成了载荷的那份 | **静态字段也要回填初始值**:从运行时 `<clinit>` 里取出该字段赋值前的那段直线代码,包成 `optifineoforge$init$<字段>()V`,在目标类 `<clinit>` 末尾调用 |

第 4 条修完后的实测(1.21.8):

```
Setting user ✓   Reloading ResourceManager: vanilla, mod_resources, mod/neoforge ✓
[OptiFine] 267 行   Pre-stitch ×13   CTM ×3   着色器 13 行   Caught error: 0
stderr 0 字节
Initialised 1 restored static fields in com/mojang/blaze3d/systems/RenderSystem ✓
Left BlockStateModel$Unbaked alone: the runtime adds members to that interface ✓
```

**但这条线还没算通过**:进程活到 240 秒被 harness 停掉,而它最后几分钟一直在刷

```
[OptiFine] Waiting for model sprites
```

也就是**卡在模型贴图集装配上**(`Sound engine started` 没出现)。所以 1.21.8 现在的状态是
"能进资源重载、能建贴图集前缀、然后挂住",比 1.21.3 差一步,归到**未通过**里,下轮从
`Waiting for model sprites` 往下查。

**关于这些改动的影响面**:第 2/3/4 条都动的是 1.21.x 的 loader 与离线工具,所以 1.21.1 / 1.21.3 / 1.21.4
在这一轮末尾各重跑了一次回归;1.20.x 分支**没有**同步这三条(它自己的
`MemberRestorePlan` / `MemberRestoreTransformer` 是独立副本),等 1.21.x 侧稳定后再带着回归一起过去。

### 三条已验证线的回归(三处改动之后)

| 线 | VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | stderr | 回填成员 |
|---|---|---|---|---|---|---|---|
| 1.21.1 | `STARTED (40s)` | ✓ | 223(= 基线) | 14 | 3 | **0 字节** | 229 / 59 类(改动前 102 / 38) |
| 1.21.3 | `STARTED (40s)` | ✓ | 225(= 基线) | 14 | 3 | **0 字节** | 233 / 60 类 |
| 1.21.4 | `STARTED (40s)` | ✓ | 232(= 基线) | 14 | 3 | **0 字节** | 263 / 68 类 |

三行的判据与改动前**逐项一致**,而回填的成员数明显变多(lambda 与静态初值那两条规则的作用),说明这两条
规则补的是"本来就没填上的东西",没有动到已验证的行为 ✓。

### 当前修订(2026-09-16 02:05)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知的 Reflector 缺陷) |
| 1.21.x | 1.21.1 / 1.21.3 / 1.21.4 | 21.1.250 / 21.3.97 / 21.4.149 | **已验证**(三条,判据与基线一致) |
| 1.21.x | 1.21.8 | 21.8.54 | **部分**:进到标题画面与资源重载,卡在 `[OptiFine] Waiting for model sprites`(见下) |
| 1.21.x | 1.21 / 1.21.6 / 1.21.7 | 21.0.167 / 21.6.20-beta / 21.7.25-beta | 前置已装好,未起跑 |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 需要 `ClassProcessor` 挂载点(1.21.11 的 OptiFine J9 只有 ModLauncher service) |
| 26.x | 26.1.2 | 26.1.2.109 | 需要 `ClassProcessor` 挂载点,但 OptiFine `K1_pre2` **自带** processor,优先级最高 |

### 1.21.8 的第五个坑:"签名被加长"把 OptiFine 的钩子挤出了调用路径

四处修复之后 1.21.8 的卡点仍在 `Waiting for model sprites`,这次根因是**调用路径**而不是成员:

```
载荷   ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos)
         └─ 调 CustomItems.collectModelSprites(map)   ← 真正把那个标志置位的地方
运行时 ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos,
                                              StandaloneModelLoader$LoadedModels)
         └─ 从运行时回填进来的,没有那个调用,而 NeoForge 的调用方编译的是这个长签名
```

**NeoForge 给方法加长签名之后,OptiFine 那份带钩子的重载就再也调不到了** —— 而缺这个调用不会报错:
`CustomItems.registerIcons` 是在一个"睡 100 ms、每 50 次打一行"的循环里等这个标志的,于是游戏能进标题画面,
然后一直刷那一行。修法是在回填成分之后,把载荷那份所调用的东西补到运行时那份里(参数取两边共有的那个 Map)。

这一步还留下一条**自己踩过的坑**,值得记:这个修复最初写成独立 transformer,结果"注册了但从不生效",
原因是 `ClassNode.name` 是**内部名(带斜线)**,而我拿点号名去比,于是每次调用都在第一行返回,唯一的症状就是
"该出现的日志行没出现"。现在它并入成员回填那一遍(顺序上也必须如此:回填之后才轮到修调用路径)。

**修完之后仍卡**:日志已经出现

```
Restored OptiFine's model sprite collection into net.minecraft.client.resources.model.ModelManager.
  discoverModelDependencies(...4 个参数...)
```

但 `Waiting for model sprites` 照旧 ⇒ "缺调用"是真的,但不是全部:剩下的怀疑是**顺序**(1.21.8 这一代
NeoForge 的贴图集装配与模型发现谁先谁后),即 OptiFine 等待的那个标志所依赖的步骤排在了等待之后。这条留作
1.21.8 的下一步,判据仍是 `Sound engine started` 与 stderr 0 字节。






