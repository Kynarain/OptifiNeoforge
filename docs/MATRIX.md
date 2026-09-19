# 版本矩阵现状与推进顺序

> **本文按时间追加,是"当时写了什么"的记录。** 顶部的阶段总结写于 2026-09-16,里面的"未开始""发布:一次都没有"
> 等判断在 2026-09-18 之后已经不成立:**15 / 15 条线全部实机验证,1.0.0 已发布(10 条)** ——
> 当前状态与逐条数字见文末的 [「1.0.0:第一次真实发布」](#10第一次真实发布2026-09-18) 一节,那里也有未发布的
> 5 条及各自的原因。下面保留原文,不改写历史结论。

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
| 1.21.x | 1.21.6 / 1.21.7 / 1.21.8 | 21.6.20-beta / 21.7.25-beta / 21.8.54 | **已验证**(2026-09-16) | 三条都 `STARTED (40s)`、`Setting user` ✓、**stderr 0 字节**、无本次运行的 crash report:1.21.6 340 行 `[OptiFine]`,1.21.7 340 行,1.21.8 337 行;三条都换装 ~500 个类并回填 ~350 个成员。三条的上游缺陷(OptiFine 预览版开启着色器会崩在 OptiFine 内部)使验收不含着色器,见 `OptifiNeoforge-121x/docs/PLAN.md` |
| 1.21.x | 1.21 / 1.21.9 / 1.21.10 / 1.21.11 | 21.0.167 / 21.9.16-beta / 21.10.64 / 21.11.45 | **未开始** | 1.21.2 与 1.21.5 没有 OptiFine 构建;1.21 只是前置(NeoForge 21.0.167 未装);**1.21.9 起 NeoForge 不再用 ModLauncher**(实测 21.11.45 的 profile:主类 `net.neoforged.fml.startup.Client`,库里没有 modlauncher/securejarhandler,只有 FML 10 + sponge-mixin),挂载点要换成 `ClassProcessor`,而 OptiFine 1.21.11 J9 **不含** processor,要自己写 |
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

### 当前修订(2026-09-16 05:40)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知的 Reflector 缺陷) |
| 1.21.x | 1.21.1 / 1.21.3 / **1.21.8** / 1.21.4 | 21.1.250 / 21.3.97 / **21.8.54** / 21.4.149 | **已验证**(四条) |
| 1.21.x | 1.21 / 1.21.6 / 1.21.7 | 21.0.167 / 21.6.20-beta / 21.7.25-beta | 前置已装好,未起跑 |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 需要 `ClassProcessor` 挂载点 |
| 26.x | 26.1.2 | 26.1.2.109 | 需要 `ClassProcessor` 挂载点,但 OptiFine `K1_pre2` **自带** processor |

### 1.21.8 通过:第五个坑是调用路径,找到它花了三次纠正

`Waiting for model sprites` 的根因不是缺调用,而是**那个调用走不到**。三次纠正依次是:

1. 先把修复放进**参数最多**的那个 `discoverModelDependencies`(理由是"NeoForge 的调用方用长签名")——
   探针显示实际被调用的是**参数少的那个**,也就是 OptiFine 自己那份。
2. 于是认为"载荷那份既然含有这个调用就没问题"——**读字节码发现调用在一个分支后面**:
   `109: ifeq 121 / 114: resolveCustomModels / 118: invokestatic collectModelSprites`,这个条件在本运行时不成立,
   于是采集永不发生、标志永远为 false、贴图集装配永远等下去。
3. 修法改成**在每个重载开头无条件调用**之后,等待消失:日志出现 `CustomItems: Registering sprites`,重载走完,
   客户端进到标题画面。

同一行还逼出两条规则,而且两条都先写错过一次:

- **回填的静态字段要有值**:值在运行时的 `<clinit>` 里,而换装换掉的正是 `<clinit>`。做法是把该字段赋值前的
  直线代码抽成初始化方法,在目标类 `<clinit>` 末尾调用。它修的实测失败是
  `RenderSystem.PIPELINE_MODIFIERS is null`(第一帧渲染时)。
- **该初始化方法不能对"类本来就有的字段"调用**:从别的方法写 `static final` 是非法,
  实测 `IllegalAccessError: Update to static final field ModelDiscovery$ModelWrapper.KEY_ADDITIONAL_PROPERTIES`。
  而且判断必须问"**回填之前**这个类有没有这个字段"——问成"之后"就把所有初始化都跳过了(包括
  `PIPELINE_MODIFIERS`),于是又回到同一个 null 崩溃。这两次都是自伤,记在代码注释里。

另外这条线需要把**模型发现那一族留给运行时**:NeoForge 的 `ModelWrapper` 有八个 slot 和一个 `slot(int)`
工厂,OptiFine 那份是按七个编译的、初始化时把 7 交给工厂,于是
`IndexOutOfBoundsException: Index 7 out of bounds for length 7`。这是 rig 里的一条线设置,证据写在旁边。

1.21.8 的最终判据:

| 指标 | 1.21.8(neoforge-21.8.54 + OptiFine `J6_pre16`) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** ✓ |
| `Setting user` | ✓ |
| `[OptiFine]` / `Pre-stitch` / CTM / 着色器 | **337 / 13 / 3 / ✓** |
| `Caught error` / `Waiting for model sprites` / `PIPELINE_MODIFIERS` NPE | **0 / 0 / 0** ✓ |
| 静态字段初始化 | **5 处** ✓ |
| stderr | **0 字节** ✓ |
| 截图 | 已保存 ✓ |

**探针本身也踩过两次坑**,一并记下:一是探针默认**关着**(`-Doptifineoforge.debug.reload`),
"没有 enter 行"被读成"方法没被调用",而其实只是开关没开;二是把它打开后 NativeImage 一类探针**每张贴图刷一行**,
一次运行三万多行、看起来像卡住。现在开关默认关闭,launcher 里写明何时该开。


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

### 26.1.2 的前置(下一轮的起点)

`prepare-line.ps1 -McVersion 26.1.2 -NfPrefix 26.1.2` 跑通了:原版 `26.1.2.jar` ✓、**NeoForge
26.1.2.109** 的 universal ✓、原版库 56 个补齐 ✓(LWJGL 3.4.1、authlib 7.0.63、lz4-java 等)。
两件事与 ModLauncher 世代不同,都是量出来的:

1. **没有 `-client.jar` 这一步,也不需要它**:26.1.2 的 profile 里 `inheritsFrom: 26.1.2`,游戏类由原版
   档案提供,26 个库全是 FML 11 那一套(`fancymodloader 11.0.15`、`sponge-mixin 0.17.3`、ASM 9.9.1,
   没有 modlauncher/securejarhandler)。installer 自己那个 `neoforge-<版本>-client.jar` 也因此始终不产出
   (`maven` 上也没有这个 classifier:`neoforge-26.1.2.109-client.jar` 取回 404),而这不影响这条线。
2. **挂载点只能是 `ClassProcessor`**:与 21.11.45 同因 —— 没有 ModLauncher,我们现有的
   `ITransformationService`/`ITransformer` 无处可挂。而 OptiFine 26.1.2 的 `K1_pre2` **自带**
   `optifine/OptiFineClassProcessor`(实现 `ClassProcessor` + `IModFileCandidateLocator`,并注册了两个
   service 文件),所以这条线的正确做法是"让 NeoForge 接受 OptiFine 的 jar、让它自己的 processor 跑",
   要处理的正是 FML 的 `IncompatibleModReason.OPTIFINE` 拒绝与元数据门槛。

## 1.21.6 / 1.21.7 / 1.21.8 一次收口:两个坑都在"回填"这条路上(2026-09-16 上午)

上一轮结束时 1.21.6 悬在 `[OptiFine] Mipmap levels: 4`:日志 116 744 行、16.5 MB,而当时的统计写着
`NPE=0`。**先把日志按"重复次数"排序**,结论立刻变了 —— 那份日志里 4 428 帧属于同一个 NPE 的堆栈:

```
java.lang.NullPointerException: Cannot invoke "MapCodec.decode(...)" because "this.val$fallbackCodec" is null
	at neoforged/neoforge/common/util/NeoForgeExtraCodecs$1.decode(NeoForgeExtraCodecs.java:250)
	...
	at net/minecraft/client/resources/model/BlockStateModelLoader.lambda$loadBlockStates$1(BlockStateModelLoader.java:60)
2214 × Failed to load blockstate definition <所有方块>
```

也就是说:方块状态**一个都没装载成功**,资源重载永远走不完,客户端停在加载画面 —— 不是"卡住",是"每一步都
在报错"。同一形状在 1.21.8 的回归跑里也复现了(`reg1218`),说明它不是 1.21.6 独有。

### 坑一:`invokedynamic` 不在取值游走的表里

上一轮把"取静态字段初值"的游走从"退到上一个 label"改成了**数栈**的游走(为了修 1.21.6 的
`PIPELINE_MODIFIERS`)。数栈游走的每一步都要能说出"这条指令净产出几个值",而 `invokedynamic` 当时
不在表里 ⇒ 返回 `null` ⇒ 整段取值被判定为"说不清" ⇒ 静态字段**只回填了声明、没有回填值**。

1.21.8 的载荷类 `SingleVariant$Unbaked` 正好是这种形状(载荷只有 `CODEC`,运行时多了 `MAP_CODEC`):

```
0: getstatic     Variant.MAP_CODEC
3: invokedynamic #1  apply:()Ljava/util/function/Function;   // lambda
8: invokedynamic #2  apply:()Ljava/util/function/Function;   // lambda
13: invokevirtual MapCodec.xmap(Function,Function)MapCodec
16: putstatic     SingleVariant$Unbaked.MAP_CODEC
```

而 NeoForge 的 `BlockStateModel$Unbaked.CODEC` 用 `dispatchMapOrElse(..., fallback)` 把
`SingleVariant.Unbaked.MAP_CODEC` 当兜底,那个匿名类在**构造时**就把 null 捕获进了 `val$fallbackCodec`,
于是此后每一次方块状态反序列化都 NPE。

修法两条,缺一不可(第二条是第一次修完才发现的):

| # | 位置 | 改动 | 不改会怎样 |
|---|---|---|---|
| 1 | `MemberRestorePlan.stackDelta` / `invoke` | 认识 `INVOKEDYNAMIC`(无接收者,净产出 = 返回非 void ? 1 : 0 − 参数个数) | 字段只有声明没有值 |
| 2 | `MemberRestoreTransformer.copy` | 能复制 `InvokeDynamicInsnNode`(bootstrap 与参数共享,ASM 会写进本类自己的 bootstrap 表) | 值取到了却内联不进去,日志里只留一句 warn |

安全性是查过字节码才下结论的:这两个 `invokedynamic` 的 bootstrap 指向 `SingleVariant$Unbaked.<init>` 与
`SingleVariant$Unbaked.variant()`,OptiFine 那份类**两者都有**,所以把这段代码搬到载荷类里执行是合法的。

**规则(新增)**:静态字段初值的取值游走必须认识 `invokedynamic` —— "值由 lambda 构造"是这一代 Minecraft
的常态(记录类的 `xmap`/`dispatch` 全是这个形状),不是例外。

### 坑二(工具层):构建脚本的过滤器把工具的告警吞掉了

上一轮的构建日志里,工具其实**一直在报**:

```
      no safe static initialiser for field net/minecraft/client/renderer/block/model/BlockStateModel$Unbaked.WEIGHTED_MODEL_CODEC
```

而 rig 的这一步只放行 `^compared` 开头的汇总行,`no safe …` 这类"我放弃了"的告警全部被丢掉 ⇒
"某个字段被回填成 null"这件事在**构建日志里完全不可见**,只能在启动崩溃之后从 16 MB 的 stdout 里反推。
过滤器已改成 `^compared|no safe|constructor not restorable|could not re-read|stub \(body`。

**规则(新增)**:任何"跳过 / 放弃 / 降级"的分支都要能在构建日志里看到。看不见的降级等于没有降级。

### 坑三(1.21.6 独有):回填进来的构造器不知道载荷**自有**的字段

坑一修完后 1.21.6 前进了一大截,然后换了一个位置崩:

```
NullPointerException: Cannot invoke "GpuTexture.getFormat()" because "textureIn" is null
	at GlCommandEncoder.verifyColorTexture(264) ← clearColorAndDepthTextures(173)
	at RenderTargetDescriptor.prepare(23) ← CrossFrameResourcePool.acquire ← FrameGraphBuilder.execute
	at PostChain.process ← GameRenderer.processBlurEffect ← GuiRenderer.draw     // 第一帧的模糊后处理
```

**先做对照实验再动手**:同一条线用 `-NoMods` 空跑 NeoForge 21.6.20-beta ⇒ `STARTED`、无 crash。
所以这是我们的载荷造成的,不是 beta 版的问题(1.21.7 同一天通过,也说明不是整代的问题)。

逐条字节码看下来,因果链很短:

| 类 | 方法 | 关键字节码 |
|---|---|---|
| 载荷 `RenderTarget` | `<init>(String,Z)` | `aload_0; iconst_1; putfield enabled` ← OptiFine 自己的字段 |
| 载荷 `RenderTarget` | `resize(II)` | `getfield enabled; ifne 28` —— `enabled == false` 时**只写尺寸就 return**,不建缓冲 |
| 运行时 `RenderTarget` | `<init>(String,ZZ)` | 回填进来的那份:设 `label`/`useDepth`/`useStencil`,**从不碰 `enabled`** |
| 运行时 `TextureTarget` | `<init>(String,IIZZ)` | 调的就是三参构造 ⇒ 每个由帧图分配的 render target 都是 `enabled = false` |

于是 `colorTexture` 恒为 null,`RenderTargetDescriptor.prepare` 把它交给
`clearColorAndDepthTextures` 时炸在 `verifyColorTexture`。

**规则(新增)**:回填一个**构造器**时,还要回填"载荷自有、运行时没有"的实例字段初值 —— 从**载荷自己**的
构造器里取那段赋值。判据与实例字段回填一致:只在"该构造器自己不给这个字段赋值"时才调用,所以 OptiFine
自己编译的构造器一个都不受影响(它们的 `assignedFields` 命中)。

顺带修掉一个**潜伏**的错误:实例初值提取的回退游走会把 `putfield` 的**接收者** `aload_0` 一起吞进切片,
而包装器自己还要再 push 一次接收者:

```
aload_0            <- 包装器自己 push 的
aload_0; iconst_1  <- 被吞进来的切片(接收者 + 值)
putfield enabled
```

这样 `return` 时栈上还剩一个引用,**不会通过校验**。现在先走数栈游走(它天然不含接收者),回退时才要求
"切片首指令不是接收者 push"(覆盖 `this.x = this.y` 这种值本身以 `aload_0` 开头的形状)。
这条是**发货前用 javap 看供体字节码**发现的 —— 离线工具产出的类值得逐个看过再启动。

### 一个副作用:一批实例字段现在真的有值了

新增的"载荷自有字段"这一趟不只修了 `RenderTarget`,同一批里其它"取不到初值"的实例字段也一并取到了:
1.21.6 的 `Camera.roll`、`Gui.leftHeight/rightHeight`、`ClientLevel.dayTimeFraction/dayTimePerTick`、
`ModelManager.bakedStandaloneModels`、`EntityRenderState.partialTick` 等从 `no safe initialiser` 名单上消失。
剩下的只有 5 个**静态**字段(`VideoSettingsScreen` 的常量与 `FABULOUS`、两个 `$SwitchMap` 合成表、
`BlockStateModel$Unbaked.WEIGHTED_MODEL_CODEC`)—— 前两类是常量与合成表(载荷自己会建),
最后那个字段所在的接口按"运行时给接口加过成员就不换装"的规则**根本没换装**,所以不影响。

### 本轮实测(2026-09-16 06:44–06:47,连续三条)

| 线 | NeoForge | VERDICT | `Setting user` | `[OptiFine]` | 日志行数 | stderr | 本次运行的 crash |
|---|---|---|---|---|---|---|---|
| 1.21.6 | 21.6.20-beta | `STARTED (40s, Sound engine started)` | ✓ | 340 | 907 | **0 字节** | 无 |
| 1.21.7 | 21.7.25-beta | `STARTED (40s, Sound engine started)` | ✓ | 340 | 929 | **0 字节** | 无 |
| 1.21.8 | 21.8.54 | `STARTED (40s, Sound engine started)` | ✓ | 337 | 899 | **0 字节** | 无 |

回归(同一轮改动之后重跑):1.21.8 337 行 / 899 行日志、1.21.1 223 行 / 2 321 行日志、stderr 均为 0 字节,
与改动前**逐项一致**(1.21.6 的 116 744 行 → 907 行就是坑一的量级)。

**rig 的另一处误导,顺手修掉**:`launch-neoforge.ps1` 收尾时打印的是"`crash-reports` 里最新的那份",
而游戏目录是复用的 ⇒ 上一轮崩溃的报告会被当成这一轮的结论打印出来(我因此白追了一次四小时前的
`PIPELINE_MODIFIERS`)。现在只认**本次启动之后**写入的报告,措辞也改成 `no crash report from this run`。

### 当前修订(2026-09-16 上午)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知的 Reflector 缺陷;**未同步本轮 loader/工具改动**) |
| 1.21.x | 1.21.1 / 1.21.3 / 1.21.4 / **1.21.6** / **1.21.7** / **1.21.8** | 21.1.250 / 21.3.97 / 21.4.149 / **21.6.20-beta** / **21.7.25-beta** / **21.8.54** | **已验证**(六条) |
| 1.21.x | 1.21 | 21.0.167 | 前置未装(NeoForge 21.0.167 不在本机) |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 需要 `ClassProcessor` 挂载点(OptiFine 侧没有) |
| 26.x | 26.1.2 | 26.1.2.109 | 需要挂载点,但 OptiFine `K1_pre2` **自带** processor |

### FML 10/11 挂载点:这一轮量到的三件事(下一轮起点)

1. **service 文件名与类型**(OptiFine 26.1.2 `K1_pre2` 就是这么注册自己的):
   * `META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` → `optifine/OptiFineClassProcessor`
   * `META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator`
   而 OptiFine **1.21.11 J9 里没有**这两个,只有老的
   `META-INF/services/cpw.mods.modlauncher.api.ITransformationService` ⇒ 26.1.2 可以"让 OptiFine 自己换装、
   我们只回填",**1.21.9/1.21.10/1.21.11 必须我们自己写 ClassProcessor**(把现有 `ITransformer` 的逻辑搬过去)。
2. **两条线的 FML 代次**:1.21.11 → `fancymodloader:loader:10.0.36`(FML 10),26.1.2 →
   `loader:11.0.15`(FML 11);两者 `mainClass` 都是 `net.neoforged.fml.startup.Client`,库里都没有
   modlauncher/securejarhandler。
3. **SPI 就在 loader jar 里**:`loader-10.0.36.jar` 含整套
   `net/neoforged/neoforgespi/transformation/*`(`ClassProcessor`、`SimpleClassProcessor`、
   `BaseSimpleProcessor`、`SimpleFieldProcessor`、`SimpleMethodProcessor`、`ProcessorName`、
   `ClassProcessorProvider`),`SimpleClassProcessor` 的契约是
   `void transform(ClassNode, SimpleTransformationContext)` + `Set<Target> targets()`,
   别的 FML 部件用 `ServiceLoaderUtil.loadServices(context, ClassProcessorProvider.class)` 装载。
   ⇒ 我们的 loader 编译 classpath 在 FML 10 线上要从 modlauncher 换成 `loader-10.0.36.jar`。

## 26.1.2 第一次实机:三重门,过了两道(2026-09-16 上午)

这一轮把 26.x 从"完全没起跑"推进到"客户端进标题画面、OptiFine 的 processor 真的被 FML 调起来了"。
三次运行,每次一个门:

### 门一:JDK。26.1.2 要 Java 25,不是 21

原版 `26.1.2.json` 写着 `javaVersion.majorVersion: 25`(`java-runtime-epsilon`),它的 JVM 参数里有
`--sun-misc-unsafe-memory-access=allow`。用本机默认的 JDK 21 起,第一行就死:

```
Unrecognized option: --sun-misc-unsafe-memory-access=allow
Error: Could not create the Java Virtual Machine.
```

换 `C:\Users\kynar\.jdks\jdk-25\bin\java.exe` 之后正常。**结论:26.x 线的 `Jdk` 参数是
`$jdk25`**,1.21.x 线的 21 不适用。

### 门二:FML 11 认为"这是 OptiFine"的判据,就是一个文件

`IncompatibleModReason.detect(JarContents)` 是**逐条按文件存在性**判的;反编译 `loader-11.0.15.jar`
拿到全部判据:

| 判据 | 文件 |
|---|---|
| OLDFORGE | `mcmod.info` |
| MINECRAFT_FORGE | `META-INF/mods.toml` |
| FABRIC / QUILT / LITELOADER | `fabric.mod.json` / `quilt.mod.json` / `litemod.json` |
| **OPTIFINE** | **`optifine/Installer.class`** |
| BUKKIT | `plugin.yml` |

也就是说 FML 的"拒绝 OptiFine"只是**看到官方 jar 里那个安装器类**;去掉
`optifine/Installer*.class`(共 3 个条目)它就不再拒绝。另一个必改项是元数据:OptiFine 自带
`META-INF/mods.toml` 写的是 `modLoader="javafml"` + **`loaderVersion="[14,)"`**(Forge 时代的范围),
FML 11 的 javafml 是 11.x,这个范围过不了,要改成 `[1,)`。

### 门三(已过):OptiFine 自带的 processor 在 FML 11 下确实会跑

打完补丁的探针 jar(`preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` 去掉 3 个 Installer 条目 + 改
`loaderVersion`)放进 `mods/`,FML 11 的日志按顺序给出:

```
mods/optifine-probe.jar
OptiFineClassProcessor.init()
OptiFineBaseTransformerService: OptiFine ZIP file: .../mods/optifine-probe.jar
OptiFineBaseTransformer: Forge JAR not available
OptiFineClassProcessor: handlesClass: <每个被加载的类>
```

⇒ **26.1.2 不需要我们自己写挂载点**,OptiFine 的 `ClassProcessor` + `IModFileCandidateLocator`
是活的。客户端也在 40 秒进入标题画面(`Sound engine started`、`Setting user` ✓)。

### 门三之后的真路障:OptiFine 的补丁**取不到原版类**

探针这次是"OptiFine 挂上了但一个类都没换成":stderr 836 512 字节,6917 次同一个异常的头两行是

```
java.io.IOException: Base resource not found: net/minecraft/resources/Identifier.class
	at optifine.Patcher.applyPatch(Patcher.java:148)
	at optifine.OptiFineBaseTransformer.getOptiFineResourcePatched(OptiFineBaseTransformer.java:331)
	at optifine.OptiFineClassProcessor.handlesClass(OptiFineClassProcessor.java:70)
```

`Identifier` 是 26.x 的新名字(原来叫 `ResourceLocation`),说明 OptiFine 认识 26.x 的命名,问题在
**它去哪个 jar 里找"base"**:FML 11 把游戏类放在 `minecraft` 模块里
(`jar(~libraries/net/neoforged/minecraft-client-patched/26.1.2.109/minecraft-client-patched-26.1.2.109.jar)`),
而 `Patcher.applyPatch` 找的是它自己解析出来的那个"基础 jar 资源"集合 —— 这也是下一轮的第一个问题:
**它是按路径找不到,还是按名字找不到**(OptiFine 期望未打补丁的原版 jar,或期望 `srg/` 前缀)。

顺带量到的两件事实,后面都要用:

* FML 11 的**游戏类运行时 jar 是 `net/neoforged:minecraft-client-patched:26.1.2.109`**
  (`~libraries/...`),这就是 26.x 线上 `MemberRestorePlan` 要对比的"运行时"那一侧;
  profile 的 `libraries` 里**没有** NeoForge universal 的条目,FML 11 自己按
  `~libraries/net/neoforged/neoforge/26.1.2.109/neoforge-26.1.2.109-universal.jar` 找到它 ——
  所以 rig 的 launcher **不用改**就能起这条线(实测)。
* 标题画面那一次的 stderr 里还有一条 GUI 文本路径的异常
  (`GuiRenderState.lambda$forEachText$0`,26.x 新的渲染状态体系),与 OptiFine 的 Font 补丁有关,
  等补丁真的生效之后再判它。

### 门三的下一步已经定位:OptiFine 的补丁**从系统 classpath 读基础类**

把 `OptiFineBaseTransformer` 反编译到方法级,`Patcher.applyPatch` 的局部变量表把因果讲得很清楚:

```
line 140: baseName = getPatchBase(name, patterns, cfgMap)
line 146: baseIn   = resourceProvider.getResourceStream(baseName)   // ← 基础类字节
line 154: patchStream = new ByteArrayInputStream(bytesDiff)         // ← xdelta 数据(调用方从 zip 里取)
line 157: gdp = new GDiffPatcher(baseBytes, patchStream, outputStream)
```

而 `OptiFineBaseTransformer.getResourceStream(name)` 的实现是:

```java
name = Utils.removePrefix(name, "/");
Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(name);   // ← 系统 classpath
while(urls.hasMoreElements()) { URL url = ...; if(forgeJarUrlStr != null && url.getPath().startsWith(forgeJarUrlStr)) continue; return url.openStream(); }
return null;
```

也就是说:**OptiFine 期望"基础游戏类"出现在 JVM 的系统 classpath 上**(`forgeJarUrlStr` 只是用来在扫描时
跳过它自己识别出的那个 jar)。`forgeJarUrlStr` 来自构造函数里的
`Class.forName("net.minecraft.client.Minecraft").getProtectionDomain().getCodeSource().getLocation()`,
拿不到就打 `Forge JAR not available`。

**26.x 的门就在 rig 的启动器上**:NeoForge profile 自己没有 jar(`versions/<profile>/<profile>.jar` 不存在),
而真实启动器会把 `inheritsFrom` 那一版的原版 jar 放进 classpath;rig 的 `launch-neoforge.ps1` 之前只
"报告缺失然后跳过",所以 `Minecraft.class` 的来源拿不到、基础类也扫不到。改成"自身 jar 缺失时退回
`inheritsFrom` 的 jar"之后:

| 观测 | 改之前 | 改之后 |
|---|---|---|
| `OptiFineBaseTransformer` | `Forge JAR not available` | **`Forge JAR URL: file:/.../versions/26.1.2/26.1.2.jar`** |
| stderr 的 `Base resource not found` | **6917** 次 | **373** 次(373 个不同的类名) |
| 客户端 | 标题画面(`Sound engine started`) | 标题画面(`Sound engine started`) |
| `[OptiFine]` 行数 | 0 | 0 |

⇒ 大部分类的"基础字节"已经能取到了(6917 → 373),但 **OptiFine 依然没有真正生效**(`[OptiFine]` 0 行),
而剩下的 373 个类名里第一个就是 `net/minecraft/resources/Identifier.class`(最早由 NeoForge 的
`ClientHooks.<clinit>` 触发)。下一轮就从这两个问题进去:

1. **那 373 个名字为什么取不到基础字节**:它们确实都在原版 jar 里(抽查
   `BossHealthOverlay` / `ModelPart` / `SingleVariant$Unbaked` / `GlDevice$ShaderCompilationKey` /
   `Identifier` 五个,原版与 `minecraft-client-patched` 两个 jar 里都有),所以不是"文件不存在",
   更像是**扫描到的 URL 被跳过或打开失败**(前一版里 `skip` 判据用的是 `url.getPath().startsWith(forgeJarUrlStr)`,
   现在 `forgeJarUrlStr` 非空,这条判据第一次真正生效)。
2. **`OptiFineClassProcessor` 到底装没装类**:`handlesClass` 会真的做一次补丁来判"我能不能处理",
   `processClass` 才决定安装;日志里只有 `handlesClass` 没有别的,`[OptiFine]` 一行都没有 ⇒
   需要读 `OptiFineClassProcessor.processClass` 的返回(`ComputeFlags`)与安装条件。

### 三次实测把这条路走完了:26.1.2 不能靠"运行期补丁",要走离线

`OptiFineClassProcessor` 的方法体把契约写死了(反编译到方法级):

```java
public boolean handlesClass(SelectionContext context) {          // line 66-72
    LOGGER.info("OptiFine: handlesClass: " + context.type().getClassName());
    String classPath = this.transformer.getSrgClassPath(name);    // "srg/<name>.class"
    return this.transformer.getOptiFineResourceStream(classPath) != null;
}
```

也就是说:**能不能处理 = 能不能从 OptiFine 自己的 zip 里取到这个类的成品**;取不到就走"现场打补丁",
而现场打补丁要按上面那条规则去系统 classpath 找基础类。三次启动把这条路的三个结局都量出来了:

| 试验 | classpath 里放了什么 | `Forge JAR URL` | stderr `Base resource not found` | `processClass` | 结果 |
|---|---|---|---|---|---|
| ① 什么都不放 | — | `Forge JAR not available` | **6917** | 0 | 进标题画面,OptiFine 未生效 |
| ② 加原版 jar | `26.1.2.jar` | `file:/.../versions/26.1.2/26.1.2.jar` | **373** | 0 | 进标题画面,OptiFine 仍未生效 |
| ③ 加补丁后 jar(放最前) | `minecraft-client-patched-…jar` | `file:/.../minecraft-client-patched-…jar`(**正确**) | 未及统计 | — | **FML 11 拒绝启动**:`NeoForge dev environment Minecraft jar does not have a Minecraft-Dists attribute` / `The patched Minecraft jar is missing` |

②里那 373 个名字**不是**"jar 里没有":373 个全部同时存在于原版 jar 与补丁 jar,而且**每一个都有对应的
`srg/...class.xdelta` 补丁**(用哈希表逐个查过)。所以现象是"扫描到的 URL 被跳过或打开失败":
`getResourceStream` 里那条 `url.getPath().startsWith(forgeJarUrlStr)` 在②里第一次真正生效,而它跳过的
正是**装着基础类的那个 jar**(因为 `Minecraft.class` 现在从它解析出来)。剩下 6538 个类不抛异常也不等于
成功 —— `handlesClass` 依然返回 false、`processClass` 一次都没被调用(`processClass` 的字符串在常量池里,
日志里 0 次)。

③本可以给出正确的 `forgeJarUrlStr`,但 FML 11 不允许把补丁后的游戏 jar 放在**原始 classpath** 上:
它会把那个 jar 当成 mod file / dev 环境实例来校验,直接以 `Minecraft-Dists` 缺失收场。

**结论(下一轮的做法)**:26.x 不能沿用"让 OptiFine 自己在运行期补"的路子,要走**与本项目其它线完全一致的
离线路子**——rig 里用 OptiFine 的 xdelta 打**原版** jar,产出 `srg/net/minecraft/**` 的成品类,再按运行期名字
重映射、连同成员回填一起装进载荷。这样 `getOptiFineResourceStream` 直接命中成品,
`getOptiFineResourceStreamPatched` 与"基础类在哪"这条整链都不再参与——这也正是 1.20.x/1.21.x 各线能跑通的
原因(它们的 OptiFine jar 本来就带 `srg/` 成品,rig 的 3c 步"成品入、补丁数据出"就是干这个的)。

具体三步:

1. `OptifinePipeline` 用**原版 26.1.2 jar** 作基础,把 `patch/srg/**` 的 xdelta 全部应用,得到成品类;
2. 以 `~libraries/net/neoforged/minecraft-client-patched/26.1.2.109/minecraft-client-patched-26.1.2.109.jar`
   为"运行时那一侧"做名字对齐(需要 26.1.2 的合并映射表,先确认 `neoform-26.1.2-*` 里有没有);
3. 成品类放进重打包后的 jar(`patch/**` 丢掉),保留 OptiFine 自己的 processor 与两个 service 文件来"装",
   我们的 `MemberRestorePlan` 先量一遍 26.1.2 到底缺多少成员 —— OptiFine 这一版是**直接对着 NeoForge 26.1.2
   编译**的(它的 `OptiFineBaseTransformer` 构造器里就引用了 `net.neoforged.neoforge.client.extensions.IMinecraftExtension`),
   所以回填量可能远小于 1.21.x 的各线。

## 26.1.2 通过:离线路线走通,四步一个坑(2026-09-17)

按上面那条结论走离线路线之后,这条线是"每修一处就前进一步"的又一例。四步里前一步已由上一轮留下,
后三步是这一轮做的,每一步都先量到根因再改:

| # | 现象 | 根因 | 修法 |
|---|---|---|---|
| 1 | `NoSuchMethodError: RenderPipelines.lambda$registerCustomPipelines$0` | 运行时的方法从供体回填了,它调用的合成 lambda 没回填 | 已在载荷里补上(上一轮) |
| 2 | `IllegalStateException: Already registered modded debug entries!` | 取值提取"在类里任意方法找赋值",把 `registerModdedDebugEntries()` **方法体内**的 `MODDED_ENTRIES_REGISTERED = true` 当成了类的初值 | `57713b7`:先找 `<clinit>`,再只找 `<clinit>` 能到达的方法(`reachedFrom`) |
| 3 | `NullPointerException: Map.size() ... "m" is null`(在 `RegisterDebugEntriesEvent.<init>` 里) | `PROFILES_MUTABLE` 被提升成**空 map**:运行时的 `<clinit>` 先 `new HashMap` 赋值、再**往同一个 map 里 put** 两个 profile;只搬赋值就只剩空壳。NeoForge 的构造器读 `mutableProfiles.get(DEFAULT)` ⇒ null | 新增 `populatedView`:当 `<clinit>` 在赋值后**又读同一个字段**时,改从它的只读视图重建——末尾那句 `Collections.unmodifiableMap(PROFILES_MUTABLE)` → `PROFILES` 说明两者内容相同,而 `PROFILES` 是载荷自己 `<clinit>` 里(本代码之前)就已赋好的,于是发 `new HashMap(PROFILES)` |
| 4 | `NoSuchMethodError: Font.ellipsize(FormattedText, int)`(在 NeoForge 自己的 `ExtendedButton.extractContents`) | **接口并集没做**:载荷的 `Font implements net.minecraftforge.client.extensions.IForgeFont`(我们生成的**空 shim**),运行时的 `Font implements net.neoforged.neoforge.client.extensions.IFontExtension`,而 `ellipsize` 只是后者的 **default 方法** —— 换装等于把承载这个方法的接口丢掉了 | 新增 `ReparentPayload.unionInterfaces`:补上运行时那份实现的接口,但**只补抽象方法载荷已经有的**(否则会把 NoSuchMethodError 变成更晚的 AbstractMethodError)。**必须放在成员回填之后**:`IFontExtension` 的抽象方法 `self()` 正是回填来的,先做并集会因缺 `self()` 而拒绝 |

实测(2026-09-17 22:40,`logs\run-diag2612m`):

```
VERDICT: STARTED (40s, marker: Sound engine started)   no crash report from this run
Setting user ✓        [OptiFine] 3478 行        OptiFine: processClass: 795 次
ConnectedTextures 40 行    Shaders 101 行     NullPointerException 0    NoSuchMethodError 0
stderr 107 字节 = 1 行 "Advanced terminal features are not available in this environment"
```

关于那 107 字节:**同一条线不带任何 mod 的对照跑(`run-ctrl2612b`)stderr 也正好是 107 字节、同一行**,
只有时间戳不同 ⇒ 那是 FML 11 在重定向 stdio 下由终端日志组件写出的环境告警,不是本 mod 的输出。
判据按"mod 自己往 stderr 写了 0 字节"计。

载荷规模(供体/接口那两步的实测):`restored 253 member(s) across 72 class(es) from 222 planned`、
接口并集补了 8 个类(`Font`/`VertexConsumer`/`BlockState`/`ModelBaker` 等)、`built` 后
`mods-stage-2612\optifine-payload.jar` 4 034 946 字节。第二次独立复跑(`run-final2612`)数字一致:
`[OptiFine]` 3478、`processClass` 795、`Setting user` ✓、CTM 40、无本次 crash、stderr 107 字节。

### 顺带修掉一个自己引入的回归:原版 jar 不能加在 ModLauncher 线的 classpath 上

为了 26.1.2 的"基础类"那条线,启动器曾加过一条兜底:**profile 自己没有 jar 时退回 `inheritsFrom` 那一版的 jar**。
按规矩跑 1.21.8 回归时它立刻露出来了:

```
java.lang.module.ResolutionException: Module minecraft contains package com.mojang.blaze3d.buffers,
  module _1._21._8 exports package com.mojang.blaze3d.buffers to minecraft
	at cpw.mods.modlauncher.ModuleLayerHandler.buildLayer(ModuleLayerHandler.java:83)
```

原版 jar 会以自己的自动模块(`_1._21._8`)加入模块路径,与游戏层里的 `minecraft` 模块导出同一个包 ⇒
模块解析在**任何 mod 加载之前**就失败(52 行日志、`Setting user` 0 次)。修法是把这条兜底**限定在
FML 10/11 的 profile 上**(`mainClass == net.neoforged.fml.startup.Client`):那几条线的游戏类来自
`minecraft-client-patched` 与 FML 自己的解析,而 ModLauncher 线的游戏类来自 libraries 里的补丁后 client jar,
**不需要也不允许**再多一份原版 jar。

修完复跑:1.21.8 回到基线(`lines=900 stderr=0 settingUser=1 optifine=337 sound=1 CTM=38`),
26.1.2 数字不变 ⇒ 两边都干净。**规则(新增)**:启动器里任何"补一份游戏 jar"的动作都必须按主类分代次,
ModLauncher 线与 FML 10/11 线的 classpath 不能共用一个形状。

## 1.21 通过,以及它带出来的那条已知缺陷被量到了新的一层(2026-09-18)

1.21 是本轮"前置已装好、直接建线"的第一条。它和 1.21.1 / 1.21.3 同形(ModLauncher 11.0.4、FML 4.0.23、
`neoforge.mods.toml`、JDK 21),所以线参数没有任何新东西 —— 唯一要按镜像实际清单确认的是 OptiFine 这一版:
**1.21 只有 preview 构建**(`/optifine/1.21` 返回 8 条,全部是 `J1_pre1..pre9`,没有 release),
所以这一条线用的是最新的 `preview_OptiFine_1.21_HD_U_J1_pre9.jar`,与 1.21.6/1.21.7/1.21.8 同样处理。

线参数落在 `build-line.ps1` 的新条目 `'121'`(`Repo` = `OptifiNeoforge-121x`、`Work` = `build-121`、
`Out` = `mods-stage-121\optifiNeoforge-combined.jar`、`Profile` = `neoforge-21.0.167`、
`GameDir` = `game121`),`Work` 下另建了 `optifine-mods.toml`(`neoforge` 区间 `[21.0,)`、`minecraft` 区间
`[1.21,1.21.1)`;这个模板每个 `Work` 一份,是 `build-rig-jar.ps1` 的必需输入)。
`datafixerupper` / `brigadier` 按 1.21 自己的版本 json 取 **8.0.16 / 1.2.9**(不是 1.21.3 的 1.3.10)。

构建(2026-09-17 23:51,`logs\build-line-121-first.txt`):

```
1/5 tools ✓   2/5 repack ✓(OptiFine 根条目 srg=1098、notch=1165)
3/5 patch ✓   patched 6 852 376 B;4150 条进、1199 条丢;classpath jar 2 695 865 B
3b/5 SRG→official:重写 21907 个方法名 + 17730 个字段名,88 个解析不到,13 个因同名被拒
3b1/5 家族跳过 1 个(com/mojang/blaze3d/vertex/VertexMultiConsumer)
3b4/5 嵌套改名配对 3 个(Gui$1DisplayEntry / LevelChunkSection$1BlockCounter / ParticleEngine$1ParticleDefinition)
3b2/5 打桩 22 个成员(7 个类),11 个留给 loader
3c/5 载荷 4 165 109 B / 1140 类,434 个搬到 optifineoforge/patched,1165 条 patch/notch 丢弃
4/5 loader ✓  5/5 combined jar 4 334 149 B / 3111 条 / 61 个供体
```

实测(2026-09-18 00:03,`logs\run-final121b`):

```
VERDICT: STARTED (40s, marker: Sound engine started)   无本次崩溃报告
Setting user ✓   [OptiFine] 252 行   换装类 315 个   Pre-stitch 14   CTM 38   着色器 14 行
stderr 14 141 字节 = 4 条 CNFE(见下)
```

`Setting user`、`Sound engine started`、OptiFine 的 Config 都在跑(252 行 `[OptiFine]`,`Pre-stitch` 14 次,
CTM 38 行),**但 stderr 不是 0**,而是与 1.20.2 / 1.20.4 完全同形的 4 条 `NoClassDefFoundError`:

```
java.lang.NoClassDefFoundError: net.minecraft.world.item.ItemStack
  at java.base/java.lang.Class.getDeclaredMethods0(Native Method)
  at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
  at net.optifine.reflect.ReflectorMethod.getTargetMethod(ReflectorMethod.java:82)
  at net.optifine.reflect.ReflectorResolver.resolve(ReflectorResolver.java:45)
  at net.minecraft.client.renderer.GameRenderer.frameInit(GameRenderer.java:1568)
Caused by: java.lang.ClassNotFoundException: net.minecraft.world.item.ItemStack
  at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
  at cpw.mods.cl.ModuleClassLoader.loadClass(ModuleClassLoader.java:216)     ← 两层
  at cpw.mods.cl.ModuleClassLoader.loadClass(ModuleClassLoader.java:216)     ← 然后才是应用加载器
```

每次跑固定 4 条、类名在 `ItemStack` / `BlockState` / `PoseStack` / `BlockEntityWithoutLevelRenderer`
之间取,与 1.20.2(14 631 字节)/ 1.20.4(14 481 字节)的字节数同量级、三条头完全一样。

### 这一轮把这条缺陷往前推了一层(新测量,不是猜测)

上一轮对它的记录停在"与加载器无关,是独立待办",并留下"先量清楚它到底为什么加载不了"。这一轮在 loader 里
加了一个**只读模块元数据**的探针(`PatchedClassTransformer.logModulesOnce`,不加载任何游戏类),量到:

| 观测 | 结果 |
|---|---|
| 四个包的归属 | `net.minecraft.world.level.block.state` / `com.mojang.blaze3d.vertex` / `net.minecraft.client.renderer` / `net.minecraft.world.item` **全部 owned by `minecraft` = true** |
| 是否导出给载荷模块 | 对 `srg`(GAME 层)`exported = true`;**对 `optifine` 也 `exported = true`** |
| 读边 | `srg -> minecraft: reads it = true`;`optifine -> minecraft: reads it = **false**` |
| 载荷在哪 | `GAME layer holds neoforge minecraft **srg(PAYLOAD)** mixin_synthetic mixinextras.neoforge`,即 `net.optifine.reflect` 属于 GAME 层的 `srg` |
| 我们自己(loader 类)在哪 | `SERVICE layer holds optifine` —— 同一个 jar 同时被 ModLauncher 当作 transformation service 装进了 SERVICE 层 |

⇒ **不是"包没导出"、也不是"读边缺失"**:那两个包既归 `minecraft` 又已导出给 `srg`,而 `srg` 也读得到
`minecraft`。所以缺陷不在模块图上,而在**发起这次解析的那个加载器**:栈里是"两层 `ModuleClassLoader`
都没接住,最后落到应用类加载器"。

另外两条排除了"是我们换装换坏的":

- `ItemStack` 在整条线里**一次都没被换装**(`Replaced net.minecraft.world.item.ItemStack` 出现 0 次),
  却照样出现在失败名单里 ⇒ 与"我们换进去的那份字节"无关;
- `BlockState` / `PoseStack` / `BlockEntityWithoutLevelRenderer` 各自只被换装 **1 次**(日志可数),说明
  失败的那次请求**没有走到任何会换装的加载器**(否则日志里会出现第二次 `Replaced`)。

**下一条线索(下一轮的第一个实验)**:在探针里把 `optifine`(SERVICE 层,读不到 `minecraft`)自己
`getPackages()` 的样本打出来,看同一个 jar 是否在两个层各有一份**同名不同包**的 `Reflector*`
(例如 `srg.net.optifine.reflect` 与 `net.optifine.reflect`);若是,则失败的那次反射发生在 SERVICE 层那份上,
而它的加载器根本不连游戏层 —— 修法就落在"别让同一批类在两处各定义一次"或"给 SERVICE 层模块补读边"上,
而不是继续在 Reflector 里找。

### 同一份 loader、同一张模块图,1.21.8 是干净的 ⇒ 缺陷跟着 OptiFine 构建走

改了共享的启动器(`launch-neoforge.ps1` 只多打一个退出码)与 121x 的 loader(多打一段模块探针)之后,
按规矩跑了 1.21.8 回归(`logs\run-reg1218y`,2026-09-18 00:15):

```
VERDICT: STARTED (40s, Sound engine started)   [OptiFine] 337   Setting user ✓   Sound engine ✓
换装 380 类   CTM 38   stderr 0 字节   无本次崩溃报告   919 行
```

与上一轮记录的基线(`optifine=337 settingUser=1 sound=1 CTM=38 stderr=0`)**数字一致**(行数 900→919,
多出来的正是新加的那 19 行探针)⇒ 1.21.8 未受影响,两条改动都是"只加日志"。

而**这张模块图在 1.21.8 上完全一样**:`SERVICE layer holds optifine`(读不到 `minecraft`)、
`GAME layer holds ... srg(PAYLOAD) ...`、四个包同样 `owned by minecraft = true, exported to srg = true`。
同一份 loader、同一张图,1.21.8 的 stderr 是 0,1.21 是 14 141 字节 ⇒ **缺陷不随加载器走,随 OptiFine 构建走**:
它出现在 1.20.2(`I7_pre1`)、1.20.4(`I7`)、1.21(`J1_pre9`)三条线,而 1.20.1(`I6`)、1.20.6(`J1_pre18`)、
1.21.1(`J1`)、1.21.3(`J2`)、1.21.4(`J3`)、1.21.6/7/8(`J6_pre*`)都没有。三条带缺陷的线里两条是 preview
(1.20.2 的唯一构建也是 preview),但 1.20.4 是 release ⇒ 判据不是 preview/release,而是 **I7 与 J1_pre9
这一批 OptiFine 的 `Reflector` 表**。

## 1.21.11:FML 10 的挂载点首次由我们提供,并真的装上了类(2026-09-18)

这是本轮的第二件事,也是**第一条 FML 10 线(FML 10.0.36,主类 `net.neoforged.fml.startup.Client`,
classpath 里没有 modlauncher/securejarhandler)**。26.1.2 那条线不需要自己的挂载点,因为 OptiFine 的
K1_pre2 **自带** `OptiFineClassProcessor`;而 1.21.9 / 1.21.10 / 1.21.11 的 OptiFine
(`J7_pre*` / `J9`)只声明了一个服务文件 —— 实测 `OptiFine_1.21.11_HD_U_J9.jar` 的
`META-INF/services/` 下只有 `cpw.mods.modlauncher.api.ITransformationService`,在 FML 10 上**没有宿主**。

### 新代码(26.x 分支 `src/fml10/java`,由 rig 编译,不进 Gradle 构建)

| 类 | 作用 |
|---|---|
| `OptifinePayloadClassProcessor extends SimpleClassProcessor` | 挂载点本体:`targets()` = 载荷里 `srg/` 下那批成品游戏类;`transform()` 从**自己这个 jar**(`getProtectionDomain().getCodeSource()`,不是资源名 —— `srg/` 这个路径没有任何包认领它)读出成品类,整类覆盖运行时的类 |
| `OptifinePayloadLocator implements IModFileCandidateLocator` | **什么都不找**,只为了让这个 jar 被 FML 的早期服务扫描认出来 |

`OptifinePayloadLocator` 的存在完全来自一次实测:`EarlyServiceDiscovery.SERVICES` 恰好是
`{IModFileCandidateLocator, IModFileReader, IDependencyLocator, GraphicsBootstrapper,
ImmediateWindowProvider}` —— **`ClassProcessor` 不在里面**。所以只声明 `ClassProcessor` 的 jar 不会被预加载,
`FMLLoader.createClassProcessorSet` 跑的时候它的类还不在 launch context 上,处理器**一次都不会被构造**。
实测:1.21.11 第一次启动进了标题画面、`mods/` 两个 jar 都被接受,但 `[OptiFine]` **0 行**、
处理器日志一行都没有。OptiFine 自己的 26.1.2 jar 也是同时声明这两个服务文件的,原因相同。

### 逐个坑(每个都来自一次启动,而不是读代码)

| # | 现象 | 根因 | 修法 |
|---|---|---|---|
| 1 | `InvalidModFileException: Missing license (optifine-payload.jar)` | 我写的载荷元数据模板少了 `license` 字段(FML 10 必需) | 模板补 `license = "All rights reserved"` |
| 2 | 处理器没被构造 | `ClassProcessor` 不在早期服务集合里(见上) | 加 `OptifinePayloadLocator` + `IModFileCandidateLocator` 服务文件 |
| 3 | `IllegalArgumentException: Invalid class name: com/mojang/blaze3d/buffers/GpuBuffer$MappedView`(在 `NameValidation.validateClassName` ← `SimpleClassProcessor$Target.<init>` ← 我们的 `targets()`) | `Target` 的构造器用 `ClassDesc.of` 校验,**要的是点号二进制名**,我给的是斜杠内部名 | `targets()` 里 `name.replace('/', '.')`(嵌套类的 `$` 保持不变,ASM 的 `Type.getClassName()` 也是这个形状) |
| 4 | `IllegalAccessError: NeoForgeRenderTypes$Internal tried to access method 'RenderType create(String, RenderSetup)'` | 整类覆盖把 OptiFine 编译版里**更窄**的成员可见性也带了进来,而 NeoForge 的 access transformer 把运行时那份放宽过,NeoForge 自己的代码依赖放宽后的形式 | 移植 1.21.x 那条已验证的规则:类标志取 OptiFine 的,**成员可见性取两者更宽的**;字段在运行时那份没有 final 时去掉 final;接口字段强制 `public static final` |

### 实测(2026-09-18 00:26,`logs\run-diag2111e`)

```
VERDICT: STARTED (40s, marker: Sound engine started)
Setting user ✓   Sound engine ✓   [OptiFine] 182 行
挂载点: reading finished classes from file:.../mods/optifine-payload.jar
挂载点: 568 finished game classes;installed ... [391 so far]     ← 391 个类真的被装了进去
stderr 15 860 字节(2 条 CNFE);本轮 game2111 出现 2 份崩溃报告(见下)
```

链路的构建数字(`build-2111-chain.ps1`,与 26.1.2 的 `build-2612-chain.ps1` 同形):比较 566 个被替换类
(758 个运行时没有对应)、计划回填 367 个成员、实际 `restored 398 member(s) across 92 class(es)`、
57 个 Forge 类型生成 shim(其中 5 个被补了 default 方法)、成品游戏类 568 个、
载荷 `optifine-payload.jar` 4 029 634 字节、OptiFine 自己的类 775 个进 `srg/` 另加 `optifine-classes.jar`
1 465 469 字节(modId `optifineclasses`)。

### 还没通过:两份崩溃报告,两个精确的下一问

1. `crash-2026-09-18_00.26.06-fml.txt`:`Mod loading failures have occurred`,
   `TagConventionLogWarning.createForgeMapEntry` ← `ExceptionInInitializerError` @ `NeoForgeMod.<init>:600`。
   **下一问**:这是"我们装进去的类"引起的,还是 21.11.45 本身的问题 —— 判据是同一 profile **不带我们两个 jar**
   的对照跑(26.1.2 那条线就是靠同一手法把 107 字节 stderr 归给终端的)。
2. `ClassNotFoundException: net.minecraft.world.level.storage.ValueOutput`,栈是
   `ReflectorMethod.getMethods` ← `ReflectorResolver.resolve` ← `GameRenderer.frameInit`,
   而**加载器是 `java.net.URLClassLoader`**(帧上写 `TRANSFORMER/optifineclasses@1.0.0/…`)——
   也就是说 OptiFine 自己的类(`net.optifine.**`,在 `optifineclasses.jar` 里)这次是从一个 URLClassLoader 出来的,
   它看不到游戏类。**下一问**:FML 10 把"带 metadata 的普通 mod 文件"放进哪一层、用哪个加载器 ——
   26.1.2 那条线的同类问题是"早期服务层的类取到的是**原版**归档里的那份",这次连类都找不到,
   两者是不是同一个原因的两种表现(判据:打印 `net.optifine.reflect.Reflector.class.getModule()`
   与它的 `getClassLoader()`)。

注意 `[OptiFine] 182 行` 与 `Setting user`/`Sound engine` 同时出现 ⇒ OptiFine 在 FML 10 上**确实是活的**,
挂载点这一层已经不是问题;剩下的两问都在"OptiFine 自己的类住在哪一层/哪一份字节"上。

## 打包/发布:第一次真的跑起来(2026-09-18)

`gradlew build` 与 `release/version.ps1` 到这一轮为止**只在 dry-run 里出现过**。这次三条线各自真跑了一遍,
产物命名矩阵如下(`release/version.ps1 show` 的真实输出 + `build/libs/` 里的真实文件):

| 分支 | `minecraft_version` | 版本(经 `release/version.ps1`) | 产物 | 字节 |
|---|---|---|---|---|
| `1.20.x` | 1.20.4 | 0.2.0(未升) | `OptifiNeoforge-0.2.0+mc1.20.4.jar` | **未产出**(见下) |
| `1.21.x` | 1.21.4 | 0.1.0 → **0.1.1**(`patch`) | `OptifiNeoforge-0.1.1+mc1.21.4.jar` | 161 231 |
| `26.x` | 26.1.2 | 0.1.0 → **0.1.1**(`patch`) | `OptifiNeoforge-0.1.1+mc26.1.2.jar` | 124 172 |

升版理由按 `docs/VERSIONING.md` 的档位表:两条线都是 `patch`(只修正行为/新增已支持版本,不改使用方式),
`1.0.0` 仍然保留 —— 它是"加载器确实在游戏里跑起来"的断言,由 rig 的 `VERDICT: STARTED` 支撑,这一次不由打包动作给出。

### 两个真实的拦路虎(都已修,且第二个修错一次)

1. **26.x:`src/main/java` 根本编译不过**。这条分支的目标是 FML 11(没有 ModLauncher),而 `src/main/java` 里
   放着 ModLauncher 时代的 `OptifiNeoforgeTransformationService` 与 13 个 transformer ⇒ 100 个错误,
   全是 `程序包 cpw.mods.modlauncher.api 不存在`。修法不是删,是**搬**:`src/main/java` → `src/ml11/java`
   (与 1.20.x 分支既有的 `src/ml10` / `src/ml11` 约定一致),`src/main/java` 只留这个 MC 目标能编译的东西
   (mod 入口 + 离线工具)。搬完 `BUILD SUCCESSFUL`。
2. **两条线的 ASM 版本都是坑,而且方向相反**:
   - 26.x:NeoForge 26.1.2.109 把 `org.ow2.asm:asm-commons` **strictly 钉在 9.9.1**,仓库里写 9.10.1 ⇒
     整个 classpath 解析不了。`asm_version` 改 9.9.1 即可。
   - 1.21.x:NeoForge 21.4.149 把 `org.ow2.asm:asm` **strictly 钉在 9.8**,而 9.8 **没有** `Remapper(int)`
     构造器,`SrgRemap.Renamer` 用的正是它。第一版修法是改成无参 `super()` + 抑制弃用 —— **错了,而且错得很值**:
     它编译通过,却**悄悄改了工具的输出**(1.21 载荷从 4 165 109 变成 4 165 610 字节),1.21 随即
     `EXITED (15s)` 崩在
     `AbstractMethodError: … ParticleEngine$$Lambda … does not define or inherit 'ParticleProvider create(SpriteSet)'`
     (`ParticleEngine.register`)。**规尺**:ASM 9.10.1 上这两个构造器**不等价**。
     正确的修法是 `build.gradle` 里加 `resolutionStrategy.eachDependency` 把 `org.ow2.asm.*` 统一到
     `project.asm_version`(= 工具被验证过的那版),`SrgRemap` 原样恢复。
     恢复后复跑(`logs\run-verify121back`):载荷回到 **4 165 109 字节**、`VERDICT: STARTED (40s, Sound engine started)`、
     `Setting user` ✓、无本次崩溃报告、stderr 14 141 字节(仍是那 4 条已知 Reflector 异常)⇒ 1.21 回到基线。

### 1.20.x 没产出:不是代码问题,是网络

`gradlew build` 停在 `:createMinecraftArtifacts`(约 3–4 分钟后超时),失败信息是
maven.neoforged.net 的 TLS 握手被断开(`Remote host terminated the handshake`,与 26.x 第一次失败同因)。
代码侧没有改动,`release/version.ps1 show` 给出的产物名是 `OptifiNeoforge-0.2.0+mc1.20.4.jar`,
下一轮重试这个任务即可 —— 判据是 `build/libs/` 里出现该文件。

### 当前修订(2026-09-18)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知 Reflector 缺陷) |
| 1.21.x | **1.21** / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **已验证**(七条;**1.21 带已知 Reflector 缺陷**:`STARTED` + `Setting user` ✓ + 无崩溃 + stderr 14 141 字节) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **已验证**(12/15 条) |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 需要我们自己写 `ClassProcessor`(OptiFine 1.21.11 J9 不含) |

---

## 1.20.x 补上 26.x / 1.21.x 的工具与 loader 工作,四条线重新量过(2026-09-18)

这一轮修的是那条"诚实的缺口":1.20.x 分支从来没有拿到后面两轮在这个项目里长出来的规则。搬的是**规则**,
不是文件 —— 每一条都落在 1.20.x 真的会走到的地方,搬不动的都写清原因,而不是留下跑不到的代码。

### 搬过去的(各自对应哪一处)

| 来源 | 规则 | 落在 1.20.x 的哪里 |
|---|---|---|
| 1.21.x | 静态字段初值(`staticInitialiser` / `staticInitialiserFrom` / `readFrom`)、`valueRun` 数栈游走、`INVOKEDYNAMIC` 的认识与复制、`isReceiverPush` 兜底、`lambda$`/`access$` **只在载荷里没有同名成员时**回填、**载荷自有实例字段**的初值 | `MemberRestorePlan`(整文件按 26.x 替换,先核过差异是纯增量的:1.20.x→26.x 只有 8 处被改的行、0 处只删不补) |
| 26.x | `reachedFrom`(先找 `<clinit>`,再只找它能到达的方法 —— 26.1.2 的 `MODDED_ENTRIES_REGISTERED` 是方法**设置**的标志,不是类初始值)、`populatedView`(赋值后又被填充的字段改从只读视图重建 —— 26.1.2 的 `PROFILES_MUTABLE` 被提升成空 map) | 同上 |
| 1.21.x | 静态初值**内联进 `<clinit>`**、接口不调用初始化、`copy()` 认识 `InvokeDynamicInsnNode` | loader `MemberRestoreTransformer` |
| 1.21.x | 接口并集**对类也生效**(kept-interfaces)、"运行时给这个接口加过成员就不换装"(读 `member-restores.txt` 的 `RESTORED_CLASSES` 守卫) | loader `PatchedClassTransformer` |

### 明确**没**搬的(每条都有理由,不是遗漏)

- **换类被拒时 `return input`**(1.21.x 的行为):1.20.x 的已验证行为是"记一行日志、照载荷自己的父类换进去",
  1.20.2 正是靠它 `STARTED`(那一版的 `CapabilityProvider` 把 `serializeCaps()` 声明为 final)。
- **`namesOldSuper` 加 `TypeInsnNode` 判据**:这条在 1.21.x 是收紧,在 1.20.x 会把 1.20.4 **正在生效**的
  `BlockEntity → AttachmentHolder` 变成"拒绝换父类"。要改必须单独立一次测量。
- **`repairSpriteCollection`(ModelManager 的精灵采集修复)**:它调用 `net.optifine.CustomItems.collectModelSprites`,
  那是 1.21.8 那一版 OptiFine 才有的方法;1.20.x 的载荷里没有它,照搬就是 `NoSuchMethodError`。
- **`ReparentPayload` / `RestoreMembers` / `ShimInheritedMembers`**:这三个是 26.x **离线路线**的工具,而 1.20.x
  的路线没有对应的构建步骤(它的父类改写与接口并集在运行期 loader 里,shim 由 `ForgeApiShims` 生成)。
  `ReparentPayload.unionInterfaces` 的规则以"接口并集"的形式落在 loader 上;`ShimInheritedMembers` 的
  无名条目守卫只对那个工具自己的输入有意义 —— 而且已核对:**1.20.x 的 `ForgeApiShims` 与 26.x 逐字节相同**,
  所以那一条在 1.20.x 无对应物。
- **`SrgRemap` 的构造器原样不动**(测量过:无参构造器会改变重映射结果,1.21 那次的代价是载荷 +501 字节与
  `AbstractMethodError`)。

### 回归:四条线,每条对着自己记录的基线

| 线 | VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | `Caught error` | stderr | 与基线比 |
|---|---|---|---|---|---|---|---|---|
| 1.20.1 / 47.1.106 | `STARTED (40s, Sound engine started)` | ✓ | **157** | 12 | 2 | 0 | **27 字节** | 与 `run-nf1201-rewrite` **逐项相同**(157 / 12 / 2 / 27 字节) |
| 1.20.2 / 20.2.88 | `STARTED (40s, Sound engine started)` | ✓ | **239** | 13 | 2 | 0 | 14 625 字节 | `[OptiFine]` 239 = 基线;stderr 同形状(4 条 CNFE,类名在 `BlockState`/`ItemStack`/`PoseStack`… 之间取,本来就是变的) |
| 1.20.4 / 20.4.251 | `STARTED (40s, Sound engine started)` | ✓ | **241** | 13 | 3 | 0 | **14 481 字节** | 与 `run-final1204` **逐项相同** |
| 1.20.6 / 20.6.141 | `STARTED (40s, Sound engine started)` | ✓ | **222** | 14 | 3 | 0 | **0 字节** | 与 `run-nf1206-regcheck` **逐项相同** |

四条本次都没有崩溃报告。**顺带得到一条新测量**:接口并集对**类**也生效这件事,在 1.20.4 上一共改到 9 个类、
在 1.20.1 上 1 个、1.20.2 上 8 个,而四条线的判据**全部与基线一致** ⇒ 当初"类级并集弄坏了 1.20.x"的结论
是被那条已知 Reflector 缺陷污染了的(1.20.4 的 `BlockState`/`ItemStack` 基线里本来就有,字节数完全相同)。

### 回归里露出来的两个缺陷(都是本轮修掉的)

**一、`NoSuchFieldError: cache`:计划给一个"不会装上"的类补了初值。**

1.20.2 加上这些规则之后 `EXITED (10s)`:

```
Caused by: java.lang.NoSuchFieldError: cache
  at net.minecraft.Util$9.optifineoforge$init$cache(Util.java)
  at net.minecraft.Util$9.<init>(Util.java:663)
```

因果链是短的,而且**根因不在新规则本身**:`Util$9` 在 `member-restores.txt` 里(载荷的补丁产物有这个类,
所以计划比到了它),但载荷**没有** `srg/net/minecraft/Util$9.class`(嵌套家族那一步把它丢掉了),
于是真正被装上的类是运行时的那个,而它没有 `cache` 字段 —— 而"载荷自有实例字段"这条新规则恰好给
`cache` 造了一个初始化方法,换装时被内联进构造器。旧代码同一条路只回填两个运行时方法(它们本来就在),
所以一直是"碰巧无害"。

修法落在 loader 而不是计划里,因为**只有 loader 知道哪个类真的在手上**:
`MemberRestoreTransformer` 在收集初始化方法时先看它写的那个字段在目标类里在不在,不在就**不恢复、不调用**,
并写明原因。实测它在四条线上各只触发一次,而且触发的正是 `Util$9`:

```
Not restoring optifineoforge$init$cache in net/minecraft/Util$9: it assigns
net.minecraft.Util$9.cache Ljava/util/Map;, which this class does not have, so the payload's
copy of the class is not the one in place
```

**二、`NoSuchMethodError: SimpleJarMetadata`:一个"按行才成立"的修复被无条件做了。**

1.20.1 加完规则后 `EXITED (5s)`,而且死在 **OptiFine 自己的类**里:

```
NoSuchMethodError: 'void cpw.mods.jarhandling.impl.SimpleJarMetadata.<init>(String, String, Supplier, List)'
  at optifine.OptiFineJar.lambda$1(OptiFineJar.java)
```

`OptifineJarFixer` 把 OptiFine 那个 `Set` 形状的调用改写成 `Supplier` 形状 —— 这个修复是 **2026-09-16 00:01
为 1.20.2 加的**(`60ca008`/`a08e210`),而 1.20.1 的上一次实测是 **2026-09-15 23:45**,之后再没跑过。
四条 profile 的 securejarhandler 版本是量出来的:

| profile | securejarhandler | `SimpleJarMetadata` 第三个参数 |
|---|---|---|
| `1.20.1-forge-47.1.106` | **2.1.10** | `Set`(OptiFine 自己的调用形状,**不需要修**) |
| `neoforge-20.2.88` / `neoforge-20.4.251` | 2.1.24 | `Supplier`(需要修) |
| `neoforge-20.6.141` | 3.0.8 | `Supplier`(需要修) |

所以修法是把这条修复变成**按行**的:`OptifineJar` 多一个 `--old-securejarhandler`,rig 多一个
`OldSecureJarHandler` 开关,**只有 1.20.1** 的那条线打开;默认(不开)保持其它三条线已验证的形状。
**规则(新增)**:凡是"改运行时 API 形状"的修复,都要问它是运行时的性质还是这个 jar 的性质;
是运行时的,就必须按行给参数。1.20.1 修好后 stderr 回到 **27 字节、与基线逐字节相同**。

## 1.21.11 的两问:一问答完,一问把因果链量到了底(2026-09-18)

### (a) `crash-…-fml.txt` 是**我们的类**引起的,不是 21.11.45 自己的问题

判据就是指定的对照跑:**同一个 profile、把两个 jar 拿掉**(`launch-neoforge.ps1 -NoMods`):

```
mods: (none)     VERDICT: STARTED (40s, marker: Sound engine started)
--- no crash report from this run ---
```

⇒ 不带我们的 jar 时 21.11.45 干净启动。所以那份 `Mod loading failures` 是本 mod 装进去的类造成的。

顺带把**哪一个字段是 null** 量出来了:崩在 `TagConventionLogWarning.<clinit>` 的数组字面量里,
`LineNumberTable` 把源码行 201 映到偏移 **2942**,而那条语句(数组下标 149)的第三个参数正是
`getstatic net/neoforged/neoforge/common/Tags$Items.DYES_BLACK`(行 52 起一行一条,52+149=201,
两边吻合)。`Tags$Items` 自己的 `<clinit>` 里 128 个 TagKey 字段都有赋值,`tag(name)` 是
`ItemTags.create(Identifier.fromNamespaceAndPath("c", name))` —— 而 `net.minecraft.resources.Identifier`
**正是 391 个被换装类里的一个**。所以这是一条独立的、精确的下一问(**不是本轮的结论**):
装上 OptiFine 编译的 `Identifier` 之后,`Tags$Items.DYES_BLACK` 为什么是 null 而它前面的 `DYES` 不是。

### (b) `ClassNotFoundException: ValueOutput`:**是我们生成的 Forge shim 落在错误加载器上**

探针(`OptifinePayloadClassProcessor`,一次一行的层图;现在挂在 `OPTIFINEOFORGE_DEBUG_LAYERS` 后面),
实测出来的加载器图:

```
own     = URLClassLoader [optifine-payload.jar] [earlydisplay-10.0.36.jar] [loader-10.0.36.jar]
          -> AppClassLoader -> PlatformClassLoader -> bootstrap
          own 看不到 Reflector / ReflectorClass / ValueOutput / GameRenderer(全部 CNFE)
system  = AppClassLoader,同样四个都看不到
context = TransformingClassLoader
          -> URLClassLoader [game2111/.cache/jij/<sha>/net.neoforged.neoforge-coremods-21.11.45.jar]
          -> URLClassLoader [optifine-payload.jar] [earlydisplay-10.0.36.jar] [loader-10.0.36.jar]
          -> AppClassLoader -> PlatformClassLoader -> bootstrap
          context 看得到:Reflector -> module **optifineclasses**、ReflectorClass -> module optifineclasses、
                          ValueOutput / GameRenderer -> module **minecraft**
          (三者都由同一个 TransformingClassLoader 定义)
```

也就是说:**OptiFine 的类与游戏类住在同一个加载器上**,探针用它加载 `ValueOutput` 是成功的。
失败的解析来自 URLClassLoader,而那两条 URL 类路径上**没有游戏类**(也没有 `optifine-classes.jar`)。
再把静态证据接上去,整条链闭合:

1. `net/optifine/reflect/Reflector.class` 里含字符串 **`IForgeBlockEntity`** ⇒ OptiFine 的 Reflector 表里有这一条;
2. 我们的构建把这个接口作为 **Forge shim 生成**并放进 `optifine-payload.jar`,路径是**真包名**
   `net/minecraftforge/common/extensions/IForgeBlockEntity.class`;
3. 这个 shim 的成员签名里有游戏类型:`serializeCaps(net.minecraft.world.level.storage.ValueOutput)`;
4. `optifine-payload.jar` 就在那个早期 `URLClassLoader` 的类路径上(上面 `own` 的 URL 里);
5. `ReflectorClass.getTargetClass()` 是 **`Class.forName(name)`**(反编译看到,只有这一处、没有指定加载器),
   该名字在游戏模块层里没有 ⇒ 落到父加载器(URLClassLoader)上,由它定义这个 shim;
6. `ReflectorMethod.getMethods` → `getDeclaredMethods()` 在那个 shim 上做,参数类型 `ValueOutput`
   用 shim 自己的加载器解析 ⇒ `URLClassLoader.findClass` ⇒ `ClassNotFoundException`。

这就是 26.1.2 那个"早期服务层解析到另一份字节"的**同类问题的另一种表现**,而且给出了做法:
**shim 不能以真包名待在会被早期 URL 类路径加载的地方** —— 它们要和游戏类一样经 `srg/` 这条无人认领的路径、
由挂载点(ClassProcessor)装进游戏模块层,由**同一个**加载器定义。这是一条明确的下一步,
判据仍是 `STARTED`、`Setting user`、stderr 0 字节、无本次崩溃报告。

同一次运行的数字与 `run-diag2111e` 完全一致(`[OptiFine]` 182、装 391 个类、`Setting user` ✓、
stderr 15 860 字节),说明探针本身没有扰动这条线。

## 打包:1.20.x 的产物出来了(2026-09-18)

上一轮 `gradlew build` 只败在网络。这一轮先重试了两次(仍是
`maven.neoforged.net`:`Remote host terminated the handshake` / `ClosedChannelException`),第三次
**越过了 `createMinecraftArtifacts`**,于是露出了第二个拦路虎 —— 与 1.21.x 同一个,但方向不同:

```
:compileJava FAILED
  SrgRemap.java:181: 错误: 无法将类 Remapper 中的构造器 Remapper 应用到给定类型;
    需要: 没有参数   找到: int
```

这条线的 `gradle.properties` 把 `asm_version` 写成 9.8,而本线 NeoForge(20.4.251)的
`modDevApiElements` 钉的就是那一版,9.8 的 `Remapper` 没有 API 版构造器。**不能**改成无参构造器
(测量过:那会改变重映射结果)。而 rig 编译同一批工具用的是 `libraries/` 里最新的 ASM = **9.10.1**,
所以照 1.21.x 的做法:把 `asm_version` 定为 **9.10.1**,并在 `build.gradle` 里用
`resolutionStrategy.eachDependency` 只对本项目自己的 `compileOnly` 依赖统一到这一版。修完:

```
BUILD SUCCESSFUL in 1m 15s     (Minecraft 1.20.4, NeoForge 20.4.251, Java 17)
build/libs/OptifiNeoforge-0.2.0+mc1.20.4.jar   159 588 字节
```

`release/version.ps1 show` 给的产物名与之一致。**没有**打标签、**没有**建 Release(用户未授权发布)。

## 共享 rig 改过之后的回归(规矩要求的那一次)

这一轮改了 `build-line.ps1` 与 `build-rig-jar.ps1`(加 `OldSecureJarHandler` 与
`--old-securejarhandler` 的传递)。按规矩重跑一条已验证线:

| 线 | VERDICT | `Setting user` | `[OptiFine]` | CTM | 换装 | stderr | 行数 |
|---|---|---|---|---|---|---|---|
| 1.21.8 / 21.8.54 | `STARTED (40s, Sound engine started)` | ✓ | **337**(= 基线) | 3 | 380 | **0 字节** | 919 |

与记录的基线(`optifine=337 settingUser=1 sound=1 CTM=38 stderr=0`)**逐项一致**,并且
`no crash report from this run` ⇒ 共享 rig 的改动没有碰到已验证的线(默认不开新开关时,命令行与改前完全相同)。

## 1.21.9 / 1.21.10:前置走了两步,卡在原版 jar(镜像的 URL 是空的)

OptiFine 那一半**完成**(镜像的元数据与文件名都核过):

| 版本 | 镜像列出的构建 | 已取 |
|---|---|---|
| 1.21.9 | `J7_pre1`、`J7_pre2` | `preview_OptiFine_1.21.9_HD_U_J7_pre2.jar`(7 664 893 字节) |
| 1.21.10 | `J7_pre2` … `J7_pre11`(10 条) | `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar`(7 805 444 字节) |

NeoForge 那一半**走了一半**:`prepare-line.ps1 -McVersion 1.21.9 -NfPrefix 21.9` 与
`-McVersion 1.21.10 -NfPrefix 21.10` 都跑到了最后,而且**安装器下来了** ——
`nf-21.9.16-beta-installer.jar`(6 344 885 字节)、`nf-21.10.64-installer.jar`(4 145 753 字节),
`versions\neoforge-21.9.16-beta\` 与 `versions\neoforge-21.10.64\` 的 profile 也都写出来了
(说明 `maven.neoforged.net` 在 01:02 / 01:04 那两个时刻是通的,本轮早些时候它超时过)。

**真正卡住的是原版 jar,而原因在镜像的 JSON 里**:`bmclapi2` 给的版本 json 里
`downloads.client.url` 是**空字符串**(实测 `1.21.9.json`),于是 `prepare-line.ps1` 拼出一个
"主机名为空"的 URI 并重试 10 次:

```
client url:
  attempt 1 failed at  : 无效的 URI: 未能分析主机名。
```

⇒ 两条线的原版 jar 都还是 False,`neoforge-21.9.16-beta-client.jar` / `-universal.jar` 也是 False
(安装器那 3–4 次尝试没有产出它们 —— 与 21.0.167 记录过的同一种形状:安装器自己报"缺库",要手工补齐再跑)。
**下一问**是脚本级的,而且很窄:`prepare-line.ps1` 在镜像 JSON 的 `downloads.client.url` 为空时,
应当退回 `piston-meta` 取那一条 URL(它已经在用 piston-meta 作整份清单的兜底,只是没用在 client url 上),
然后按老办法手工补齐安装器报缺的库;之后 1.21.10 照做,挂载点直接复用 1.21.11 的
`OptifinePayloadClassProcessor`(同代 FML 10.0.36)。

### 当前修订(2026-09-18,本轮之后)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条,本轮拿到 26.x/1.21.x 的全部规则后重新量过;1.20.2/1.20.4 带已知 Reflector 缺陷) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **已验证**(七条;1.21 带已知 Reflector 缺陷;1.21.8 本轮回归一致) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **已验证**(12/15 条) |
| 1.21.x | 1.21.11 | 21.11.45 | 挂载点已装 391 个类、`[OptiFine]` 182 行;两问都已量清(见上),**未通过** |
| 1.21.x | 1.21.9 / 1.21.10 | 21.9.16-beta / 21.10.64 | OptiFine 已取;NeoForge 卡在 maven 不可达 |

**打包**:`1.20.x` 的 `build/libs/OptifiNeoforge-0.2.0+mc1.20.4.jar` 已产出(159 588 字节);
`1.21.x` 与 `26.x` 上一轮的产物不变;**未发布**(无标签、无 Release)。

---

## 1.21.11 通过:两问都修掉,而第二问的根因不是 `Identifier`(2026-09-18)

两条线(FML 10 的挂载点 + 离线载荷)都动过,判据不变:`STARTED`、`Setting user`、stderr(除掉终端那一行)、
无本次崩溃报告。

### (a) Forge shim:从早期 classpath 搬进游戏层

上一轮把因果链量到了底,这一轮只改**位置**,而位置就是全部:

- **改前**:56 个 shim 由 `build-payload-2612.ps1 -ShimsDir` 写进 `optifine-payload.jar` 的**真包名**路径
  (`net/minecraftforge/**`)。这个 jar 同时是早期服务 jar(`IModFileCandidateLocator` 服务文件让它被 FML 10
  预加载),所以它的根类由一个普通 `URLClassLoader` 定义,而那个加载器看不到游戏模块层。
- **改后**:`build-2111-chain.ps1` 第 7 步不再传 `-ShimsDir`,新增第 9 步把 `forge-shims-filled` 里的
  56 个类写进 **`optifine-classes.jar`**——那是 `mods/` 里的普通 mod 文件,它的类由游戏层那个
  `TransformingClassLoader` 定义,和游戏类同一个加载器。反射解析(`ReflectorClass.getTargetClass()` =
  `Class.forName`,用的是调用者自己的加载器,而 Reflector 就在同一个模块里)因此拿到的是能解析游戏类型的 shim。

脚本自己给出了判据,而不是靠读日志:

```
payload: 4017409 bytes
  finished game classes under srg/: 569; OptiFine's own under srg/: 775
  Forge shims left at real package paths in THIS jar: 0   (0 is the point ...)
classes jar: 1486796 bytes (Forge API shims written into optifine-classes.jar: 56)
```

效果(stderr):**15 860 → 107 字节**,而那 107 字节与**不带 mod 的对照跑逐字节相同**(`logs\run-ctrl2111c`
的 stderr 也是 107 字节,内容只有 log4j 那句 `Advanced terminal features are not available in this
environment`)⇒ 这一轮之后,这条线的 stderr 里没有一个字节来自我们。修掉的两条是:

```
java.lang.NoClassDefFoundError: net/minecraft/core/HolderLookup$Provider
  at java.lang.Class.getDeclaredMethods0(Native Method)
  at net.optifine.reflect.ReflectorMethod.getMethods(...)
Caused by: java.lang.ClassNotFoundException ... at java.net.URLClassLoader.findClass
```
被枚举的那个类是 shim `net/minecraftforge/common/extensions/IForgeBlockEntity`(实测:56 个 shim 里只有它
同时含 `HolderLookup$Provider` 与 `serializeCaps(...ValueOutput)` 这类游戏类型签名)。

### (b) 那个 null 的来源:`Reflector` 表里的一条**游戏类**成员,不是 `Identifier`

上一轮把 null 钉到了 `TagConventionLogWarning` 的数组字面量第 149 项(源码行 201 ↔ 偏移 2942,
`createForgeMapEntry(Registries.ITEM, "dyes/black", Tags$Items.DYES_BLACK)`)。这一轮先读 `Tags$Items`,
问题的一半就没了:

```
DYES       = tag("dyes")              -> ItemTags.create(Identifier.fromNamespaceAndPath("c","dyes"))
DYES_BLACK = DyeColor.BLACK.getTag()  -> 被换装的那个 DyeColor 自己的字段
```

所以"为什么 `DYES` 不是 null 而 `DYES_BLACK` 是"的答案很简单:两者根本不是同一种表达式。再看被换装的
`DyeColor` 构造器(payload 里那份),链条闭合:

```
this.dyesTag = (TagKey) Reflector.ForgeItemTags_create.call("forge", "dyes/" + translationKeyIn);
```

而 `Reflector.ForgeItemTags` **不是一个 Forge 类**:`Reflector.<clinit>` 里它是
`new ReflectorClass(net.minecraft.tags.ItemTags.class)` + `makeMethod("create", String.class, String.class)`
——即 **Forge 当年给游戏类 `ItemTags` 加的双参数重载**。NeoForge 的 `ItemTags` 只剩
`create(Identifier)`(实测 `javap`),于是查不到方法,`ReflectorMethod.call` 按设计**静默返回 null**,
`dyesTag`/`dyedTag` 十六个颜色全是 null,`Tags$Items.DYES_BLACK` 是 null,NeoForge 自己的
`TagConventionLogWarning.<clinit>` 抛 NPE,`ExceptionInInitializerError` → `Mod loading failures`。

**这条缺陷的形状值得单独记住**:OptiFine 通过反射读 Forge API,缺目标时**不报错**,把一个 null 写进游戏类的
字段;读它的是三个类之外的 NeoForge 代码。载入 mod 时看不到,崩溃点离原因很远。

### 两个新工具(26.x `src/main/java/.../optifine/`,由 rig 编译)

| 工具 | 作用 | 实测数字(1.21.11) |
|---|---|---|
| `ReflectorGaps` | 从 `Reflector.<clinit>` 的直线字节码里**还原整张反射表**(目标类、成员名、参数类型),再扫载荷里对它的用法,报出"运行时没有这个目标"的条目,并标出**把返回值写进字段**的那些 | 表 242 条(方法 127 / 字段 12 / 类 103);扫 568 个载荷类、143 处用法;缺目标 8 条,**其中 1 条是 stored** |
| `ForgeEraMembers` | 给 stored 的那些补**真实现**(不是空壳),写进载荷 `srg/` 下,由挂载点照常装 | `net/minecraft/tags/ItemTags.create(String,String)`:运行时类 16 459 → 16 781 字节 |

`ItemTags.create(String namespace, String path)` 的实现是把 `forge` 映射到本运行时的常规命名空间 `c`,
其余命名空间原样传递——这不是猜的:NeoForge 自己的 `Tags$Items.tag(String)` 就是
`ItemTags.create(Identifier.fromNamespaceAndPath("c", name))`,vanilla 的 `DyeColor` 构造器同样用 `"c"`,
所以这样做出来的值与"这个运行时本来会算出的值"一致。

### 实测(2026-09-18)

| 运行 | 载荷 | VERDICT | `Setting user` | `Sound engine` | `[OptiFine]` | 装上的类 | stderr | 本次崩溃报告 |
|---|---|---|---|---|---|---|---|---|
| `run-fix2111a` | 未回填(`-SkipRestore` 的坑,见下) | EXITED | ✓ | ✗ | 26 | 167 | 107 字节 | 客户端崩溃 `NoSuchMethodError RenderTarget.<init>(String,ZZ)` |
| `run-fix2111b` | 完整(回填 398 个成员) | STARTED | ✓ | ✓ | **196** | 400 | 107 字节 | 无 |
| **`run-fix2111c`(验收)** | 同上 | **STARTED (40s, Sound engine started)** | ✓ | ✓ | **196** | **400** | **107 字节**(=对照跑的 107) | **无** |
| `run-ctrl2111c`(同 profile,**不带两个 jar**) | — | STARTED (40s) | — | ✓ | — | — | 107 字节 | 无 |
| `run-reg2111fix1218`(回归,1.21.8 / 21.8.54) | 上一轮产物 | STARTED (40s) | ✓ | ✓ | **337**(=基线) | — | **0 字节** | 无 |

链路构建数字(`build-2111-chain.ps1 -SkipPatch`,回填完整):比较 566 个被替换类、
计划回填 367 个成员、**实际 restored 398 member(s) across 92 class(es)、0 个类没捐体**、
57 个 Forge 类型 40 个成员、56 个 shim(其中 5 个补了 default 方法)、成品游戏类 **569** 个
(568 + 补上来的 `ItemTags`)、载荷 `optifine-payload.jar` **4 017 409 字节**、
OptiFine 自己的类 775 个进 `srg/` 另加 `optifine-classes.jar` **1 486 796 字节**(modId `optifineclasses`)。

**踩过的坑,记下来省下一次**:`-SkipRestore` 会把 `$restored` 设回 `$repatched`(即**未回填**的 jar),
所以那次链是"合法地"用未回填的类装的载荷,于是出现
`NoSuchMethodError: void RenderTarget.<init>(String, boolean, boolean)`(`MainTarget.<init>` ←
`ClientHooks.instantiateMainTarget`)——而回填计划里明明有这一条
(`M com/mojang/blaze3d/pipeline/RenderTarget <init> (Ljava/lang/String;ZZ)V`),
回填后的类里也真的有。那个开关的含义是"跳过回填",不是"沿用上次回填的产物"。

### 还没修:另外 7 条 gap(已量出,不在本轮结论里)

`ReflectorGaps` 报的 8 条里,只有 1 条把返回值写进字段;剩下 7 条都是**调用后立刻用**的,所以不会崩,
但都静默给出默认值(false/null/void):

| 条目 | 目标(运行时缺) | 谁在调 |
|---|---|---|
| `ChunkAccess_getWorldForge` | `ChunkAccess.getWorldForge` | `ChunkMap` |
| `ForgeBlockElementFace_data` | `BlockElementFace.data` | `FaceBakery` |
| `ForgeEntity_isInWaterOrSwimmable` | `Entity.isInWaterOrSwimmable` | `LivingEntityRenderer` |
| `ForgeItemBlockRenderTypes_isFancy` | `ItemBlockRenderTypes.isFancy` | `SingleVariant` |
| `ForgeKeyBinding_setKeyConflictContext` | `KeyMapping.setKeyConflictContext` | `Options` |
| `ForgeParticleResources_getProvider` | `ParticleResources.getProvider` | `ParticleEngine` |
| `TerrainParticle_updateSprite` | `TerrainParticle.updateSprite` | `ClientLevel` |

**下一问**(窄且可判):这 7 条各自"默认值"与真值差多少 —— 例如 `ParticleResources.getProvider` 返回 null
会不会让 `ParticleEngine` 少一路粒子、`isFancy` 恒 false 会不会让快/精致模型选择走错。
判据是每一条给出"OptiFine 侧的可观察差异"(不是"没崩就算过")。

### 1.21.9 / 1.21.10:原版 jar 拿到了,NeoForge 装完了

上一轮卡在镜像的 `downloads.client.url` 为空;这一轮实测发现比"空"更糟:**镜像那份 version json
PowerShell 5.1 根本解析不了**(`Invalid JSON primitive: 4 97 114 103 ...`,正是 `"Arguments"` 的字节),
而**启动器要读的正是这个文件**(libraries / assetIndex / arguments),所以它不只是下载源的问题。

`prepare-line.ps1` 的修法(窄,三条):

1. 取不到 client url 时退回 `piston-meta`(`Get-PistonVersionJson`:清单 → 版本 json → `downloads.client`),
   并且**把 piston-meta 的那份 json 写回原路径**(镜像那份留作 `<version>.json.mirror`)——因为下载 URL 只是
   这个文件里的一个字段;
2. 下载后**核对 sha1**(两个来源是不同主机,不核对的话后面所有测量都没有意义);
3. 新增 `Test-Fml10Install`:这一代安装器**不产出 `-client.jar`**,它跑 `PROCESS_MINECRAFT_JAR` 写出
   `minecraft-client-patched-<ver>.jar` 并打印 `Successfully installed client into launcher`,而脚本原来据此报
   "client jar present: False" 并**白跑四次安装器**(1.21.10 上实测)。现在按"profile 存在 + mainClass 是
   `net.neoforged.fml.startup.Client` + patched jar 存在"判定。

实测:

| 线 | 原版 jar | sha1 | 安装器 | 结果 |
|---|---|---|---|---|
| 1.21.10 / 21.10.64 | 30 592 168 字节 | 与 piston-meta 一致 | 第 1 次即完成;手工补 4 个库(earlydisplay 10.0.32 360 274、loader 10.0.32 660 036、neoform 1.21.10-20251010.172816 mappings 541 275、universal 21.10.64 3 805 764) | profile ✓、`minecraft-client-patched-21.10.64.jar` 32 903 514 字节 ✓、universal ✓、**本代无 `-client.jar`** |
| 1.21.9 / 21.9.16-beta | 30 591 861 字节(`ce92fd8d…`) | 与 piston-meta 一致 | 第 1 次报缺 5 个库并手工补齐(earlydisplay/loader 10.0.14、sponge-mixin 0.16.4、neoform 1.21.9-20250930.151910、universal 21.9.16-beta),第 2 次完成 | profile ✓、`neoforge-21.9.16-beta-client.jar` ✓、universal ✓ |

两条线的 OptiFine jar 上一轮已取(1.21.9 `J7_pre2` 7 664 893 字节、1.21.10 `J7_pre11` 7 805 444 字节)。
**下一步**是这两条线复用 1.21.11 的挂载点:同一代 FML 10,但 `OptifinePayloadClassProcessor` 目前按
`loader-10.0.36` 编译、`build-2111-chain.ps1` 把 10.0.36 的路径写死了,而 21.10 用 loader **10.0.32**、
21.9 用 **10.0.14** ⇒ 差事是把这三个路径参数化(挂载点本身不随行变),再各跑一次链。

**"挂载点不随行变"这句是量过的,不是推断的**:`src/fml10/java` 那两个源文件**一个字节没改**,
分别对着 `loader-10.0.32.jar` 与 `loader-10.0.14.jar` 编译,**两次都是 exit 0、各产出 2 个类**(实跑)。

### 当前修订(2026-09-18,本轮之后)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知 Reflector 缺陷) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **已验证**(七条;1.21 带已知 Reflector 缺陷;本轮 1.21.8 回归 337/0 字节,与基线一致) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **已验证** |
| 1.21.x | **1.21.11** | 21.11.45 | **已验证**(`STARTED` + `Setting user` + `Sound engine` + `[OptiFine]` 196 行 + 装 400 个类 + stderr 107 字节=对照跑的 107 + 无崩溃报告)⇒ **13/15 条** |
| 1.21.x | 1.21.9 / 1.21.10 | 21.9.16-beta / 21.10.64 | OptiFine ✓、原版 jar ✓、NeoForge 装完 ✓;差"参数化挂载点编译用的 loader 版本"后各跑一次链 |

**打包**:产物不变(1.20.x `OptifiNeoforge-0.2.0+mc1.20.4.jar` 159 588、1.21.x 与 26.x 上一轮产物);
**未发布**(无标签、无 Release)。








## 1.21.10 与 1.21.9:一条链管三条线,四个缺陷,15/15(2026-09-18)

这两条线的"前置"在上一轮就算完成了(原版 jar 与 sha1、安装器与 profile、OptiFine 两个 preview 构建),本轮做的是
**把它们真的跑起来**,而两条线各自暴露出同一批缺陷 —— 因为 1.21.9 / 1.21.10 / 1.21.11 走的是同一条链、同一个挂载点,
差别只有 loader 版本、NeoForge 版本和原版 jar 三个名字。

### 一、先补齐两条线各自缺的两件事(都是实测)

1. **本机 libraries 里缺两个库,而 rig 的启动器不会报**。`1.21.9.json` / `1.21.10.json` 里要
   `com.mojang:jtracy:1.0.36` 与 `io.netty:netty-codec-http:4.1.118.Final`,两份都不在。
   缺 jtracy 的后果不是"少个功能",而是**连 pre-bootstrap 都过不去**:

   ```
   java.lang.NoClassDefFoundError: com/mojang/jtracy/TracyClient
     at net.minecraft.client.main.Main.main(Main.java:115)
     at net.neoforged.fml.startup.Client.main(Client.java:19)
   Description: Pre-bootstrap
   ```

   按版本 json 里的 url 取回并逐字节核对 sha1:`jtracy-1.0.36.jar` 12 876 字节(`20a63d06…`)、
   `jtracy-1.0.36-natives-windows.jar` 47 599 字节(`b42bc771…`)、
   `netty-codec-http-4.1.118.Final.jar` 674 362 字节(`eda08a71…`)。
   顺带记一笔 rig 的缺口:启动器只在**profile 自己**的 libraries 缺失时打印 `!! N libraries not found`,
   从 `inheritsFrom` 那一版继承来的库缺失时是**静默跳过**,所以这一类问题只能靠自己比对版本 json 发现。

2. **FML 10 线的早期窗口:属性名是 ModLauncher 时代的,在 FML 10 上是空气**。
   rig 的启动器给每条线都加 `-Dfml.earlyprogresswindow=false`,但 FML 10.0.32 / 10.0.14 只认游戏目录里
   `config/fml.toml` 的 `earlyWindowProvider`;新建的游戏目录默认是 `fmlearlywindow`。实测(同一份载荷、同一个目录,
   只差这一行):

   | `earlyWindowProvider` | 观测 | 结果 |
   |---|---|---|
   | `fmlearlywindow`(默认) | 日志:`Loading ImmediateWindowProvider fmlearlywindow`;约 8-10 秒后 `IllegalStateException: Already building.` 落在 `SimpleBufferBuilder.begin ← RenderContext.renderText/blitTextureRegion ← PerformanceElement.render ← LoadingScreenRenderer.renderToFramebuffer ← DisplayWindow.renderToFramebuffer ← NeoForgeLoadingOverlay.render`,本次运行的 `crash-…-client.txt` 一份 | **EXITED(15s)**,标题画面都没到 |
   | `none` | 日志:`Failed to find ImmediateWindowProvider none, disabling` | **STARTED(40s)** |

   三次带 `fmlearlywindow` 的启动都崩在同一处(`run-final2110a`、`run-fresh2110a`、`run-ab2110on`),
   改 `none` 之后四次全部正常。已核对 1.21.11 与 1.21.8 的游戏目录里本来就是 `none`
   —— 也就是**这不是新缺陷,是这两条线的目录还没被设过**。
   下一问(窄):rig 的启动器要么按 FML 代次写这一行,要么把那条失效的 `-Dfml.earlyprogresswindow` 注释改掉。

### 二、`build-2111-chain.ps1` 参数化:三条线一条链

| 参数 | 1.21.11(默认,已验证那条) | 1.21.10 | 1.21.9 |
|---|---|---|---|
| `-McVersion` | `1.21.11` | `1.21.10` | `1.21.9` |
| `-NeoForgeVersion` | `21.11.45` | `21.10.64` | `21.9.16-beta` |
| `-LoaderVersion`(挂载点编译用的 loader jar) | `10.0.36` | `10.0.32` | `10.0.14` |
| `-RuntimeJar`(比对/回填的"运行时那一侧") | `minecraft-client-patched-21.11.45.jar`(默认推导) | `minecraft-client-patched-21.10.64.jar`(默认推导) | **`neoforge-21.9.16-beta-client.jar`**(显式) |

`src/fml10` 两个源文件一个字节没改,只换 loader jar。1.21.9 的运行时那一侧要显式给,是因为**那一代安装器
用的是老的 `--clean`/`--apply` 路径**,产出的是 `neoforge-21.9.16-beta-client.jar`(只有类),而不是
`minecraft-client-patched-*.jar`;FML 10.0.14 认这个形状(`GameLocator.locateProductionMinecraft` 在
`minecraft-client-patched` 缺失时回退到 `net.minecraft:client:<mc>-<neoform>:srg` + `:extra` +
`net.neoforged:neoforge:<ver>:client`,启动日志里也能看到 `minecraft (composite(jar(…-srg.jar), jar(…-extra.jar), jar(…-client.jar)))`)。
它作为"补丁后的游戏类"这一侧的替代是量过的:srg jar 9969 个类、client jar 9981 个类,对称差只有 1 / 13 个(都是补丁新增的合成类)。
两个 metadata 模板现在**缺则生成、在则不覆盖**,1.21.11 那两个已验证的模板永远不会被重写。

### 三、四个缺陷,每一个都由一次启动量出来

| # | 现象 | 根因(实测) | 修法 |
|---|---|---|---|
| 1 | `NoClassDefFoundError: net/minecraft/resources/Identifier`(`DyeColor.<clinit>` ← 我们供给的 `ItemTags.create`),随后 38 次 `Cowardly refusing to send event … to a broken mod state`,OptiFine 的精灵采集永不发生,客户端永远打印 `[OptiFine] Waiting for model sprites` | `ForgeEraMembers` 把 26.x 的**类名写死**成 `Identifier`,而 1.21.10 / 1.21.9 还叫 `ResourceLocation`。实测:`Identifier.class` 只在 21.11.45 与 26.1.2.109 的补丁后 jar 里,21.10.64 与 21.9 的 client jar 里是 `ResourceLocation.class` | 工具改成**从运行时 jar 里解析**这个名字(两个都在时取新名 ⇒ 1.21.11 的输出逐字节不变);两个都没有就拒绝写 |
| 2 | `VerifyError: Operand stack underflow`,`Location: BlockModelWrapper.<init>(List, List, ModelRenderProperties)V @12: invokestatic`;随后 `Caught error loading resourcepacks, removing all selected resourcepacks`,客户端停在加载画面 | 恢复这个类的两个实例字段初值(`renderType`、`modelLocation`)时,**只为整串调用 push 了一次 `aload_0`**,第二个 `invokestatic`(描述符 `(L…BlockModelWrapper;)V`)拿到空栈 | `RestoreMembers`:每次调用各 push 一次接收者。**这是工具里一直存在的缺陷**,其它线上没有任何构造器需要两个初值,所以从没发作 |
| 3 | `ExceptionInInitializerError ← IndexOutOfBoundsException: Index 7 out of bounds for length 7`,栈是 `ModelDiscovery$ModelWrapper.slot ← <clinit> ← ModelDiscovery.<init> ← ModelManager.discoverModelDependencies` | 恢复 NeoForge 加的第八个槽 `KEY_ADDITIONAL_PROPERTIES` 时,把 `slot(7)` 放进了一个**仍然写着 7 的类**:`SLOT_COUNT=7`、`slot(int)` 的界是 7、构造器里的 `AtomicReferenceArray` 也是 7(OptiFine 这份是按 7 槽的原版编的) | 新工具 `SlotLayoutRepair`:拿运行时的 `SLOT_COUNT`,只改这三处数字(7 → 8),三处缺任何一处就拒绝写,防止"半对齐"的类在别处炸 |
| 4 | 上一轮 1.21.10 停在 `Waiting for model sprites`(修好 #1 之后仍在) | 与 1.21.x 线上量到过的**同一个缺陷的离线版**:`CustomItems.registerIcons` 等 `modelSpritesUpdated`,唯一写它的是 `CustomItems.collectModelSprites`,而载荷里的这个调用要么在 NeoForge 扩容过的四参重载里(从运行时恢复,体内没有这个调用),要么在 OptiFine 自己那份的 `if(Config.isCustomItems())` 后面 | 新工具 `SpriteCollectionRepair`:在两个重载的**头部**各插一次 `CustomItems.collectModelSprites(map)`(与 1.21.x loader 的 `repairSpriteCollection` 同一条规则) |

方法级的证据(每条都可在本轮产物里复现):

* #2:`javap` 修前 `8: aload_0; 9: invokestatic optifineoforge$init$renderType; 12: invokestatic optifineoforge$init$modelLocation`,修后 `12: aload_0; 13: invokestatic …$modelLocation`。
* #3:运行时 `slot(int)` 是 `bipush 8; Objects.checkIndex`,载荷是 `bipush 7`;修后载荷 `SLOT_COUNT: 7 → 8`、界 8、数组 8。
* #4:线程转储(`logs/jstack-2110.txt`)把等待的那个人钉在
  `net.optifine.Config.sleep ← net.optifine.CustomItems.registerIcons ← net.optifine.util.TextureUtils.registerCustomSprites
  ← net.minecraft.client.renderer.texture.TextureAtlas.preStitch ← SpriteLoader.lambda$loadAndStitch$7
  ← SimpleReloadInstance.lambda$prepareTasks$0`;
  修后同一次日志里 `CustomItems: Collecting model sprites` 2 次、`Registering sprites` 1 次、`Waiting for model sprites` **0** 次。

### 四、实测(2026-09-18)

| 线 | NeoForge | 载荷 | VERDICT | `Setting user` | `Sound engine` | `[OptiFine]` | 装上的类 | `Pre-stitch` | stderr | 本次 crash |
|---|---|---|---|---|---|---|---|---|---|---|
| **1.21.10** | 21.10.64 | 3 882 296 B | `STARTED (40s, Sound engine started)` | ✓ | ✓ | **280** | **394** | 13 | **0 字节** | 无 |
| 1.21.10 对照(同 profile/同目录,无 mod) | 21.10.64 | — | `STARTED (40s)` | ✓ | ✓ | 0 | — | — | **0 字节** | 无 |
| **1.21.9** | 21.9.16-beta | 3 807 587 B | `STARTED (40s, Sound engine started)` | ✓ | ✓ | **289** | **383** | 13 | **0 字节** | 无 |
| 1.21.9 对照(同 profile/同目录,无 mod) | 21.9.16-beta | — | `STARTED (40s)` | ✓ | ✓ | 0 | — | — | **0 字节** | 无 |
| 1.21.9 首次启动(目录里已有 `options.txt`、还没有 `optionsof.txt`) | 21.9.16-beta | 同上 | `STARTED (40s)` | ✓ | ✓ | 290 | 383 | 13 | 673 字节(见五-1) | 无 |
| 回归 **1.21.11** | 21.11.45 | 4 017 419 B | `STARTED (40s, Sound engine started)` | ✓ | ✓ | **197**(基线 196) | **400**(=基线) | 15 | **107 字节**(=它的对照) | 无 |

* 1.21.11 的 `[OptiFine]` 196 → 197 与日志行数 708 → 709 都只多一行,正是 #4 那条修复让
  `CustomItems: Collecting model sprites` 在四参重载里也打印一次;装上的类仍是 400、stderr 仍是 107 字节。

链路构建数字:

| 线 | 比较的被替换类 | 计划回填 | 实际回填 | reparent | 成品游戏类 | payload | classes jar | Reflector 表 / gap |
|---|---|---|---|---|---|---|---|---|
| 1.21.10 | 549(720 无对应) | 349 | **384 / 89 类** | 1 | 552 | 3 882 296 B | 1 431 539 B | 247 / 8(1 stored) |
| 1.21.9 | 515(718 无对应) | 342 | **375 / 88 类** | 1 | 518 | 3 807 587 B | 1 427 651 B | 246 / 6(1 stored) |
| 1.21.11(回归) | 566 | 365 | **398 / 92 类** | 1 | 569 | 4 017 419 B | 1 486 796 B | 247 / 8(1 stored) |

两处新工具的自我报告(1.21.10 与 1.21.9 完全同形):

```
7d/9 putting OptiFine's sprite collection back on the call path the game uses
      collectModelSprites added to the head of discoverModelDependencies(…3 参…)(OptiFine's own guarded call is still there)
      collectModelSprites added to the head of discoverModelDependencies(…4 参…)(the restored runtime overload, which had no call at all)
7e/9 aligning a restored member with the class layout the runtime grew
      ModelDiscovery$ModelWrapper SLOT_COUNT: 7 -> 8 / 界 8 / 数组 8
```

1.21.11 那条线上 7e 报的是"这个类不在载荷里"(它的 OptiFine 构建不替换这个类),这是**结果而不是失败**,
工具按缺席处理并原样写回。

### 五、还没修的,以及精确的下一问

1. **首启一次性的 stderr,不是本轮的结论**。OptiFine 的 `Options.loadOfOptions` 对文件的每一行做
   `String[] parts = line.split(":"); key = parts[0]; value = parts[1];`,**没有长度检查**;原版
   `options.txt` 里有一行 `lastServer:`(`"lastServer:".split(":")` 在 Java 里长度是 1),于是
   `ArrayIndexOutOfBoundsException` 进了 stderr(1.21.10 679 字节、1.21.9 673 字节),
   异常被 OptiFine 自己吞掉、游戏照常进标题画面。它只在"目录里有 `options.txt`、还没有 `optionsof.txt`"的
   **第一次**启动出现(那次启动里 OptiFine 就会写出 `optionsof.txt`,第二次起 stderr 回到 0)。
   **1.21.11 线上潜伏着同一个缺陷** —— 它的目录里早就有 `optionsof.txt`,所以从没触发过,本轮的 107 字节与它无关。
   下一问(窄且可判):要不要在载荷里给这个方法补一个长度守卫(判据:首次启动的 stderr 也回到 0),以及
   1.21.x / 1.20.x 各线的同一段代码长什么样(它们的目录同样是复用的,缺陷可能只是没被触发)。
2. **1.21.x 的 loader 里同一处 `aload_0` 缺陷**。`MemberRestoreTransformer` 里那段"给每个构造器插初值调用"的代码
   与 `RestoreMembers` 修前逐字相同(一次 `aload_0` + N 次 `invokestatic`)。它在已验证的 7 条 1.21.x 线上没有发作
   (每条线上每个构造器最多需要 1 个初值),但它是同一个缺陷。下一问:照 26.x 的修法改掉,并重跑一条已验证线,
   判据是逐项等于基线(本轮没有改它,所以那 7 条线的状态不变)。
3. 1.21.11 剩下的两问仍在原地,本轮没有动:七条 Reflector gap 各自的可观察代价,以及 196/400 与已知良好运行
   的逐项对照。

### 六、当前修订(2026-09-18,本轮之后)

| 线 | 版本 | NeoForge | 状态 |
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **已验证**(四条;1.20.2/1.20.4 带已知 Reflector 缺陷;本轮未动) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **已验证**(七条;1.21 带已知 Reflector 缺陷;本轮未动) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **已验证** |
| 1.21.x | 1.21.9 / 1.21.10 / **1.21.11** | 21.9.16-beta / 21.10.64 / 21.11.45 | **已验证**(三条;均 `STARTED` + `Setting user` + `Sound engine` + stderr = 各自对照 + 本次无崩溃报告) |

⇒ **15 / 15 条线全部已验证**。1.21.11 本轮按回归重跑(`[OptiFine]` 197、装上 400、stderr 107 字节 = 它的对照)。

`optionsof.txt` 那条首启缺陷是所有 FML 10 线共有的已知项(不影响启动),写在五-1。
**未发布**(无标签、无 Release)。
### 七、顺手关掉的一个潜伏缺陷:1.21.x loader 里同一个 `aload_0`(2026-09-18)

上一节五-2 记的那处"同一段代码"本轮改掉了,并按要求重跑了一条已验证线。改动是把
"一次 `aload_0` + N 次 `invokestatic`"改成"每次调用各 push 一次接收者"(`MemberRestoreTransformer`),
对一个只恢复 1 个初值的构造器来说**生成的字节码逐字节不变**,所以它对本轮的 7 条 1.21.x 线是"关掉地雷"而不是改行为。
判据是重跑 1.21.8(`build-line.ps1 -Line 1218 -Launch -Tag reg1218aload -TimeoutSec 150`):

| 项 | 基线 | 本次 |
|---|---|---|
| VERDICT | `STARTED (40s, Sound engine started)` | `STARTED (40s, Sound engine started)` |
| `Setting user` / `Sound engine` | ✓ / ✓ | ✓ / ✓ |
| `[OptiFine]` 行数 | 337 | **337** |
| CTM 行数 | 38 | **38** |
| stderr | 0 字节 | **0 字节** |
| 日志行数 | 919 | **919** |
| 换装的类 | 380 | 380 |
| 本次 crash | 无 | 无 |

⇒ 逐项一致。分支 `1.21.x` 的头因此从 `094952b` 前进一格(见提交),那 7 条线的状态不变。

## 1.0.0:第一次真实发布(2026-09-18)

### 一、发布决定(照 `docs/VERSIONING.md` 与 `docs/PUBLISHING.md` 走)

- `docs/VERSIONING.md` 说得很清楚:**`0.x` 的含义是"加载器还没在真实游戏里跑起来",第一个跑起来的产物才升到
  `1.0.0`**。15 / 15 条线现在都有实机记录(判据见上面的逐轮实测),所以三条分支各自用
  `release/version.ps1 patch -Base 1.0.0` 升版 —— 脚本里 `-Base` 就是为"第一次真跑起来"准备的那条路,
  没有手改 `gradle.properties`;每个分支的 `gradle.properties` 与"为什么升这一档"的说明放在同一个提交里。
- **一个 MC 版本一个产物,一个产物一个标签。** 判据是实测的:jar 里的 `META-INF/neoforge.mods.toml` 把
  `minecraft` 依赖写成闭区间(1.21.4 的 jar 里是 `[1.21.4,1.21.4]`),所以 `+mc1.21.4` 的 jar 在 1.21.8 上
  不会被加载。标签名与产物名一致(`v1.0.0+mc<MC 版本>`),这是 PUBLISHING.md 第 5 步要求的"某个版本号对某个产物"。

### 二、已发布(10 条;正文写在各自的 Release 里)

| 线 | MC / NeoForge | 标签 | 产物(字节) | SHA-256(前 12) | 验收(实机记录) |
|---|---|---|---|---|---|
| 26.x | 26.1.2 / 26.1.2.109 | `v1.0.0+mc26.1.2` | 159 635 | `14890f4b8ad5` | `STARTED`、`Setting user` ✓、`[OptiFine]` 3478、stderr 107 B(= 无 mod 对照跑)、本次无 crash |
| 1.20.x | 1.20.4 / 20.4.251 | `v1.0.0+mc1.20.4` | 159 589 | `45aa72956e9a` | `STARTED`、✓、241、stderr 14 481 B¹、本次无 crash |
| 1.20.x | 1.20.6 / 20.6.141 | `v1.0.0+mc1.20.6` | 159 708 | `46daf38b0913` | `STARTED`、✓、222、stderr 0 B、本次无 crash |
| 1.21.x | 1.21 / 21.0.167 | `v1.0.0+mc1.21` | 161 233 | `ea88795872ea` | `STARTED`、✓、252、stderr 14 141 B¹、本次无 crash |
| 1.21.x | 1.21.1 / 21.1.250 | `v1.0.0+mc1.21.1` | 161 234 | `b8032c557d15` | `STARTED`、✓、223、stderr 0 B、本次无 crash |
| 1.21.x | 1.21.3 / 21.3.97 | `v1.0.0+mc1.21.3` | 161 235 | `bbbd37fb498d` | `STARTED`、✓、225、stderr 0 B、本次无 crash |
| 1.21.x | 1.21.4 / 21.4.149 | `v1.0.0+mc1.21.4` | 161 235 | `b7125668db37` | `STARTED`、✓、232、stderr 0 B、本次无 crash |
| 1.21.x | 1.21.6 / 21.6.20-beta | `v1.0.0+mc1.21.6` | 161 238 | `de29a28b13e6` | `STARTED`、✓、340、stderr 0 B、本次无 crash(**不含启用光影包**) |
| 1.21.x | 1.21.7 / 21.7.25-beta | `v1.0.0+mc1.21.7` | 161 239 | `48fb0e7edb87` | `STARTED`、✓、340、stderr 0 B、本次无 crash(**不含启用光影包**) |
| 1.21.x | 1.21.8 / 21.8.54 | `v1.0.0+mc1.21.8` | 161 235 | `09582cc096bf` | `STARTED`、✓、337、stderr 0 B、本次无 crash |

¹ 1.20.2 / 1.20.4 / 1.21 三条线带的那条已知 Reflector 缺陷(每次启动 4 条 `NoClassDefFoundError`);1.20.2 本轮
没有产出 jar(见下),但它的这条缺陷与 1.20.4 同形。

**发布的是什么**:各分支自己的 Gradle 构建产物,**加载器侧**(实测:每个 jar 里 `net/optifine/**` 条目数为 0),
不含 OptiFine 的类、也没有随包分发 OptiFine。能直接放进 `mods/` 的成品仍要按 rig 的流水线用**用户自己的**
OptiFine jar 合成 —— 上面每一条验收数字都是那样量出来的。

### 三、已验证但这一轮没有发布(5 条,原因都是构建而不是验证)

| 线 | 原因(实测) |
|---|---|
| 1.20.1 / 47.1.106 | 那一代 NeoForge 是旧的 `net.neoforged:forge` 坐标,Gradle 插件报 `Could not find net.neoforged:neoforge:1.20.1-47.1.106`(`gradle.properties` 里早就记着这条) |
| 1.20.2 / 20.2.88 | `20.2.88` 在 Maven 上没有 ModDevGradle 需要的 `neoforge-moddev-bundle` 变体:`Unable to find a variant … with the requested capability` |
| 1.21.9 / 1.21.10 / 1.21.11 | 这三版 NeoForge 已经没有 ModLauncher,而本分支的 `src/main` 编译的是 `cpw.mods.modlauncher.api.ITransformationService`。**实测 1.21.11**(21.11.45):`BUILD FAILED in 11m 15s`,死在 `:compileJava`,`错误: 程序包cpw.mods.modlauncher.api不存在`(MemberRestoreTransformer 等);**1.21.10 同样实测一次**(21.10.64,`BUILD FAILED in 14m`,`SortProbeFix` 与 `MemberRestoreTransformer` 同一错误);1.21.9 未单独尝试(同一条路)。这三条的挂载点是我们自己的 `ClassProcessor`(26.x 分支 `src/fml10`,由 rig 编译**进载荷 jar**),而载荷 jar 含 OptiFine 的类、不分发 |

### 四、这一轮的网络实况(为什么有的线要重试)

每个**非默认**目标都要走一遍 neoform 流水线(重新下载 MC、NeoForge 与整套库),而这一轮
`maven.neoforged.net` / `libraries.minecraft.net` 反复断连:1.21.8 第一次 `BUILD FAILED in 15m 10s`、
1.20.6 第一次 10m 28s(log4j 的 `maven-metadata.xml` 取不到)、1.21.6 第一次 4m 40s
(`fancymodloader:securejarhandler:9.0.2` 解析不到)、1.21 三次全败。

**重试是有效的,而且第二次通常很快**:失败的那一次已经把绝大多数产物落进缓存,1.21.8 重试 2m51s、
1.20.6 重试 40s、1.21.6 重试 9m18s、**1.21 重试(第四次)4m34s** 都过了。所以"某一轮构建失败"在本项目里首先是一个
网络事实,不是代码事实 —— 上面第三节里真正属于"构建不出来"的只有 1.20.1、1.20.2 与 1.21.9/1.21.10/1.21.11 五条,
而 1.21 那条只是网络,重试之后就发布了。

### 五、这一轮改了哪些文档(都在各自分支上提交并推送)

- 三条分支的 `gradle.properties`(0.1.1 / 0.1.1 / 0.2.0 → 1.0.0)与各自 README 的实测状态;
- `26.x` 的 `docs/PUBLISHING.md`("现状"一节改成"已经发布过一次",并写明发布的 10 条/未发布的 5 条,以及
  "构建失败先重试并区分网络与真构建不出来"这条这一轮量出来的规矩);
- `26.x` 的 `docs/MATRIX.md` 顶部加了"这份副本已过时,运行日志在 `1.20.x`"的指引;
- `main` 的落地页从"骨架阶段"改成 15/15 已实机验证 + 实测矩阵 + 产物命名规则 + 证据在哪;
- 标签全部打在"升版那一提交"上(26.x `bf3de0d`、1.21.x `1340e58`、1.20.x `48dd023`),没有提交任何构建产物、
  OptiFine jar 或 `test-downloads/`。

### 六、发布本身的记录

10 个 GitHub Release 通过 GitHub API 创建,资产用 `Invoke-RestMethod` 上传,每个 Release 附**一个** jar、
正文里写该线的验收数字与已接受缺陷。逐个 Release 的 URL、字节数与 SHA-256 见上表
(Release URL 形如 `https://github.com/Kynarain/OptifiNeoforge/releases/tag/v1.0.0+mc1.21.4`)。
标签全部是附注标签(`git tag -a`),消息里带产物名、字节数与 SHA-256;`git push origin <tag>` 逐个推送。

## 2026-09-18(下半):五条"构建不出来"的线全部结清

上一节把它们的共同点写成了一句话:"真正属于'构建不出来'的只有 1.20.1、1.20.2 与
1.21.9/1.21.10/1.21.11 五条"。这一轮把五条都打通了。

### 一、1.21.9 / 1.21.10 / 1.21.11:挂载点源码根按代次分开(`1.21.x`)

复现:`-Pmc=1.21.11 -Pneoforge=21.11.45` 死在 `:compileJava`,**100 个错误**,全部是
`错误: 程序包cpw.mods.modlauncher.api不存在`,落在实现 ModLauncher 接口的那批类上 —— 那批类放在
`src/main`,而这三版 NeoForge 已经没有 ModLauncher。

做法与 `1.20.x` 早就用过的 `src/ml10` / `src/ml11` 同形:15 个类移到 `src/ml11/java`,由新的
`-Pmountpoint` 决定编不编 —— `modlauncher`(1.21 – 1.21.8)编,`fml10`(1.21.9 起)不编;非默认目标
**必须**给这个开关,与既有的 `-Pneoforge` 同一条规矩。

实测(每条各构建一次):1.21.9(21.9.16-beta)、1.21.10(21.10.64)、1.21.11(21.11.45)全部
`BUILD SUCCESSFUL`,产物 39 项、109 765 字节(1.21.9 多 4 字节,是元数据里更长的 NeoForge 串),
`loader/**` **0 个类**、离线工具 32 个类,即"加载器侧工具 + mod 骨架"。默认目标 1.21.4 未受影响:
161 235 字节、16 个 loader 类,与本轮之前同尺寸。**这批类的移动是纯改名,15 个文件各 0 行改动。**

### 二、1.20.1 与 1.20.2:绕开 ModDevGradle,按显式类路径编译(`1.20.x`)

1.20.1 的结法就在更早两节的候选名单里("an explicit dependency on net.neoforged:forge with the
plugin's resolver out of the way")。本轮把它做成:这两个目标**不应用** ModDevGradle,由 `java` 插件加一张
显式 `compileOnly` 清单构建。坐标取自各版自己的元数据 —— 20.2.88 的 POM 与 1.20.1-47.1.106 的 userdev
`config.json` 都点名 `cpw.mods:modlauncher:10.0.9`,FML 分别是 fancymodloader 1.0.16 与 47.2.2。

**这两个目标不需要 Minecraft**:本分支源码里没有一处 `net.minecraft` / `com.mojang` 的 import。所以既没有
Minecraft 下载,也没有反编译与 NeoForm,构建 1 – 6 秒完成;代价是没有 `runClient` 开发运行。

清单里有两处是踩出来的:
- `@Mod` 注解**不在 loader jar 里**,它在 `language-java`(javafml 语言提供者)里,`ModContainer` 在 `core` 里。
  只列 `loader` 时 `net.neoforged.fml.common.Mod` 找不到 —— 是逐个 jar 查了才定下来的。
- `DonorVerifier` 用了 `CheckClassAdapter`,即 **`asm-util`**。在 ModDevGradle 目标上它是从 NeoForge 发行
  传递进来的、从来没被点名过,在这条显式清单里必须写出来(否则 `程序包org.objectweb.asm.util不存在`)。
  这一条对四条线都成立,已经补进 `build.gradle` 的公共依赖。

### 三、新查出来的第三处分界:登记用的 mod id 随版本变

1.20.1 的 NeoForge 47.1.106 在自己的 `META-INF/mods.toml` 里登记为 **mod id `forge`**(displayName 仍是
"NeoForge")、版本 `47.1.106`,而它的产物坐标是 `net.neoforged:forge:1.20.1-47.1.106`。20.2.88 起才是
`neoforge` 且与坐标同名。依赖块照抄别的版本写 `neoforge`,在 1.20.1 上会去要一份那份发行里**根本没有**的 mod。

同一轮把**元数据文件名**的切换点量清了(`docs/VERSIONS.md` 挂着的"待确认"之一):

| 目标 | 该版 NeoForge 自己的元数据文件 | 登记 mod id | FML loader 读的名字 |
|---|---|---|---|
| 1.20.1 (47.1.106) | `META-INF/mods.toml` | `forge` | `mods.toml` |
| 1.20.2 (20.2.88) | `META-INF/mods.toml` | `neoforge` | `mods.toml` |
| 1.20.4 (20.4.251) | `META-INF/mods.toml` | `neoforge` | `mods.toml` |
| 1.20.6 (20.6.141) | `META-INF/neoforge.mods.toml` | `neoforge` | 两个名字都读 |

两路判据一致:各版 FML loader jar 里的字面常量,**以及**各版 NeoForge 自己产物里的文件名。两个 NeoForge
universal jar 里两种字面量都没有,所以做决定的确实是 loader jar,不是它。

**顺带改正一处**:1.20.4 的 jar 以前带的是 `neoforge.mods.toml`,而 20.4.251 的 FML 只读 `mods.toml`,
那份元数据对它等于不存在。现在四个版本写出的名字都与该版自己的产物一致。这是**构建层面**的改正,
**没有重跑实机启动**,所以已发布 jar 的正文说明没有跟着动。

还有一处本轮量了、但**不是元数据而是源码**的分界:FML 的 API 包名 —— 1.20.1 是 `net.minecraftforge.fml` +
`net.minecraftforge.eventbus.api`,1.20.2 起是 `net.neoforged.fml` + `net.neoforged.bus.api`。一份源码跨不过去,
所以按目标选源码根(`src/forge` 与 `src/neoforged`),与 `src/ml10`/`src/ml11` 同形。实测:1.20.1 的
`OptifiNeoforge.class` 常量池里只有 `net/minecraftforge/**`,没有一处 `net/neoforged`。

### 四、这一轮的网络实况:坏的是 IPv4 那一侧

上一节把构建失败归给"网络成段断连",这一轮量到了更具体的原因:

`maven.neoforged.net` 在 CDN77 后面,同时有 AAAA 与 A 记录。在这台机器上 **IPv4 那一侧是有损的**:
`curl -4` 连发五次,两次握手直接失败、两次成功、一次 20 秒超时(成功那两次各约 15 – 17 秒);同一 URL
`curl -6` **1.7 秒 200**。Java 的 HttpClient(NeoForm 的下载器)走的正是坏的那一侧,所以报的是
`SSLHandshakeException: Remote host terminated the handshake` 与 `ConnectException`。

三处具体表现:
- `mergetool-2.0.3-fatjar.jar` 反复取不下来,报"服务器可能不支持客户端请求的 TLS 版本",而 curl 一取就有;
- `asm-commons-9.8.jar` 更隐蔽:缓存目录
  `~/.gradle/caches/neoformruntime/artifacts/org/ow2/asm/asm-commons/9.8/` **存在但是空的**,于是每次构建
  都死在同一个文件上。按该路径用 IPv6 补种后构建立刻通过;
- 1.20.4 的 `minecraft_1.20.4_client_mappings.txt` 下到 2 390 577 字节后**彻底停住**(连续 35 秒字节数不变),
  同样 IPv6 取回(8 897 012 字节)后重试成功。

处置:`JAVA_TOOL_OPTIONS=-Djava.net.preferIPv6Addresses=true`(对所有 JVM 生效,包括 NeoForm fork 出来的
那个 `java.exe`),以及对停住的下载按缓存路径直接补种。**结论:这类报错先看 IPv4/IPv6,再谈"重试"。**

另一条与网络无关但同样费时间的:PowerShell 里 `-P` 参数**必须加引号**。裸写 `-Pmc=1.20.6` 会被拆开,
Gradle 报 `Task '.20.6' not found in root project`(`cmd.exe` 下不加引号也可以)。文档示例已改成带引号。

### 五、这一轮的结果:十五条线现在都能构建

每一条**各构建一次**。产物结构与同一分支内的分界一致,下面是全部十五条:

| 分支 | 目标 | 结果 | 产物 |
|---|---|---|---|
| `1.20.x` | 1.20.1 / 1.20.1-47.1.106 | `BUILD SUCCESSFUL` | 57 项、159 735 字节、18 个 loader 类、`mods.toml` |
| `1.20.x` | 1.20.2 / 20.2.88 | `BUILD SUCCESSFUL` | 57 项、159 741 字节、18 个 loader 类、`mods.toml` |
| `1.20.x` | 1.20.4 / 20.4.251(默认) | `BUILD SUCCESSFUL` | 57 项、159 744 字节、18 个 loader 类、`mods.toml` |
| `1.20.x` | 1.20.6 / 20.6.141 | `BUILD SUCCESSFUL` | 57 项、159 959 字节、18 个 loader 类、`neoforge.mods.toml` |
| `1.21.x` | 1.21 / 21.0.167 | `BUILD SUCCESSFUL` | 55 项、161 233 字节、16 个 loader 类 |
| `1.21.x` | 1.21.1 / 21.1.250 | `BUILD SUCCESSFUL` | 55 项、161 234 字节、16 个 loader 类 |
| `1.21.x` | 1.21.3 / 21.3.97 | `BUILD SUCCESSFUL` | 55 项、161 235 字节、16 个 loader 类 |
| `1.21.x` | 1.21.4 / 21.4.149(默认) | `BUILD SUCCESSFUL` | 55 项、161 235 字节、16 个 loader 类 |
| `1.21.x` | 1.21.6 / 21.6.20-beta | `BUILD SUCCESSFUL` | 55 项、161 238 字节、16 个 loader 类 |
| `1.21.x` | 1.21.7 / 21.7.25-beta | `BUILD SUCCESSFUL` | 55 项、161 239 字节、16 个 loader 类 |
| `1.21.x` | 1.21.8 / 21.8.54 | `BUILD SUCCESSFUL` | 55 项、161 235 字节、16 个 loader 类 |
| `1.21.x` | 1.21.9 / 21.9.16-beta | `BUILD SUCCESSFUL` | 39 项、109 769 字节、**0 个 loader 类** |
| `1.21.x` | 1.21.10 / 21.10.64 | `BUILD SUCCESSFUL` | 39 项、109 765 字节、**0 个 loader 类** |
| `1.21.x` | 1.21.11 / 21.11.45 | `BUILD SUCCESSFUL` | 39 项、109 765 字节、**0 个 loader 类** |
| `26.x` | 26.1.2 / 26.1.2.109 | `BUILD SUCCESSFUL` | 52 项、159 635 字节、45 个工具类、0 个 loader 类 |

字节数的差异来自元数据里那几行字符串的长短(MC 版本、NeoForge 版本、mod id),不是代码差异:`1.21.x` 那组
16 个 loader 类的十一条彼此只差几字节,而 `1.21.9` 比同组的另外两条大 4 字节,正是 `21.9.16-beta` 比
`21.10.64` / `21.11.45` 长 4 个字符。

**边界,如实写下**:本轮**只动了构建**。这台机器上没有 `optifineoforge-test` 这个 rig,所以**没有重跑任何
一次实机启动** —— 上表十五行的实机判据仍然只有本文件更早那些记录,五条原本"没发"的线也仍然**没有发布**
(发布是独立的一步,需要一条启动记录与 Release 正文)。构建通过不等于能跑,这一条不变。

## 2026-09-19:把 rig 从零建起来,以及 1.21.4 的第一次真机对照

上一节的边界是"这台机器上没有 rig,所以没有重跑实机启动"。这一轮先把 rig 建起来,然后跑第一条线。

### 一、rig 是重建的,不是找回来的

`optifineoforge-test` 在这台机器上不存在(按名字搜过 C:/D:/E:,也搜过 `build-rig-jar.ps1`、`*-chain.ps1`、
`launch*.ps1`),git 历史里也没有:`git log --all --diff-filter=A --name-only` 里只有 `release/version.ps1`。
所以下面这些是这一轮**新写的**,放在 `C:\Users\kynar\IdeaProjects\optifineoforge-test\`(仓库外):

| 脚本 | 作用 |
|---|---|
| `fetch-libraries.ps1` | 读 `versions\*\*.json`,按 Mojang 的 rules 取本机该用的库,缺的用 `curl -6` 下到 `libraries\`,并把 `:natives-windows` 那类解到 `natives\` |
| `seed-installer-toolchain.ps1` | 把 NeoForge 安装器自己要用的 NeoForm 工具链(neoform zip、binarypatcher、AutoRenamingTool、SpecialSource、installertools、jarsplitter)先下好 |
| `build-jars.ps1` | 用仓库自己的离线工具装配两个 jar:给 loader 的 OptiFine jar,和带服务注册 + 成员还原计划的 loader jar |
| `launch.ps1` | 按 profile JSON 合并父子、展开带 rules 的参数、替换占位符、去重 classpath、启动、限时、然后判 `Setting user` / `Sound engine started` / 本次崩溃报告 / stderr 字节数 |

**安装器为什么必须先喂饱**:NeoForge 的安装器不只是下一个 profile JSON —— 它在本地跑 NeoForm 流水线,
把 `libraries\net\neoforged\neoforge\21.4.149\neoforge-21.4.149-client.jar`(1637 个类)造出来,那才是运行时
那份被 NeoForge 改过的游戏类。它自己会 `java.net.preferIPv4Stack=true`,于是下载成片超时
(`SocketTimeoutException: Connect timed out`)——与本文件上一节量到的 IPv4 有损是同一件事。先按日志里的
52 个坐标用 IPv6 补齐,安装器就一次通过(`Successfully installed client into launcher.`)。

### 二、对照组:不带任何 mod,判定标准必须成立

NeoForge 21.4.149 / 1.21.4,game dir 干净,不带 mod:

```
===== VERDICT: STARTED =====
  Setting user         : True
  Sound engine started : True
  new crash reports    : 0
  stderr bytes         : 0
```

也就是说 rig 本身是能复现本项目判据的:真的起了客户端、真的进了标题画面、声音引擎真的起来了、本次没有崩溃
报告、stderr 一字未写。下面对照都在这个基础上。

### 三、装上 OptiFine 与本项目的 loader 之后的四步,以及卡在哪

1. **只放两个 jar(OptiFine + Gradle 产出的 loader),崩在 NeoForge 自己的方法上**:
   `java.lang.NoSuchMethodError: 'void net.minecraft.client.gui.Gui.initModdedOverlays()'`,
   栈是 `net.neoforged.neoforge.client.ClientHooks.initClientHooks` ← `Minecraft.<init>`。
   原因是 OptiFine 的 1.21.4 构建用**它自己编译的**游戏类顶替运行时那份,而它编译时没有 NeoForge 后加的成员。
   这正是本仓库 `MemberRestoreTransformer` 存在的理由 —— **而那份计划(Gradle 产物里没有)必须由 rig 生成**。

2. **用仓库自己的工具生成还原计划**:`OptifinePipeline <mc jar> <optifine jar> <workdir>` 打补丁,
   再 `MemberRestorePlan <patched jar> <runtime jar> <out> [donor dir]` 出计划。运行时那份游戏类是
   `client-…-srg.jar` 叠上 `neoforge-…-client.jar`,叠完 8866 项、19928455 字节。实测计划:
   **316 行、83 个类、83 个 donor**,其中就有出事的 `M Gui initModdedOverlays ()V`。
   注意 `OptifinePipeline` 必须喂**混淆**的原版客户端:喂 SRG/官方名那份会
   `OptiFine's patcher failed ... Base resource not found: fdp.class`。

3. **计划装进去之后:`initModdedOverlays` 那个崩溃消失了,服务也真的被注册了**。日志里能读到
   `OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, fml, OptifiNeoforge…]`、
   `OptiFineTransformer: Targets: 474`、`Member restore plan: 314 members across 82 classes`,
   以及逐类的 `Restored N members in … from its donor`。本次没有崩溃报告、**stderr 0 字节**、700 行 `[OptiFine]`。

4. **然后卡在 FML 的 early window 上,而且是本项目已经记录过的那一个**:
   `java.lang.IllegalStateException: Already building.`,栈是
   `fml_earlydisplay.SimpleBufferBuilder.begin` ← `DisplayWindow.paintFramebuffer` ← `NeoForgeLoadingOverlay.render`
   ← `GameRenderer.render`。本文件更早的 1.20.4 那条记的正是这个缺陷,并给出 `earlyWindowProvider = "none"`。
   **这一轮把它的适用范围扩到了 21.4.149**:默认的 `fmlearlywindow` 在这里同样必崩。

5. **而 `none` 在 21.4.149 上不是等价的替代**:换成 `none` 之后不再崩,但客户端**卡在加载界面**。
   两次线程转储(相隔约 40 秒,`jstack`)都是同一个形状:Render 线程在
   `Minecraft.runTick → RenderSystem.limitDisplayFPS → glfwWaitEventsTimeout` 里空转,**不是**卡住;
   16 个 `Worker-Main-*` 全部 `WAITING (parking)`、ForkJoinPool 队列为空、CPU 不再增长;日志停在
   OptiFine 的 28 行 `Pre-stitch:` 之后不再前进。也就是说重载没有卡在锁上,而是**没有任何人在做事**,
   加载界面的移除也就永远不发生。`earlyWindowControl = false` 一起设上,结果相同。

### 四、顺带量到的两件与本线无关但会误导的事

- **最小化窗口 + 垂直同步会让 Render 线程死在 `glfwSwapBuffers`**:第一次转储是
  `Window.updateDisplay → RenderSystem.flipFrame → glfwSwapBuffers`。去掉窗口最小化并把
  `enableVsync:false` 写进 `options.txt` 之后,那次卡死消失(转储变成上面的 `glfwWaitEventsTimeout`)。
  这与 `OptiFabric` 那份 DEVELOPMENT 记的成因一致。
- **`optionsof.txt` 尾随换行会让 OptiFine 自己的解析器抛异常**:
  `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at `Options.loadOfOptions`。
  文件非空、88 行、每行都有冒号,唯一异常之处是结尾多一个换行(按 `\r?\n` 切会多出一个空行)。
  后续几次运行 stderr 是 0 字节,说明这不是每次都有。

### 五、由此发现的一个**已发布产物**的缺陷(本轮已修)

loader 的类实现 `cpw.mods.modlauncher.api.ITransformationService`,但**没有任何东西声明它**:
四个分支(`main` / `1.20.x` / `1.21.x` / `26.x`)用 `git ls-tree` 都找不到
`META-INF/services/cpw.mods.modlauncher.api.ITransformationService`,构建出来的 jar 里
`META-INF/services/**` 条目数是 **0**。ModLauncher 只通过这个文件发现转换服务,所以**已发布的 9 个 ModLauncher loader jar**
(`1.20.x` 的 2 个、`1.21.x` 的 7 个)实际上是惰性的 —— 加载侧根本不会被调用(`26.1.2` 那一线不受影响:
它的 Gradle 产物里没有 loader 类,挂载点是 OptiFine 自己的 `ClassProcessor`)。
实测对照:手工补上这个文件之后,日志里立刻出现 `OptifiNeoforgeTransformationService.onLoad` 那一行;
不补,同一份启动里本项目的加载侧一个字都没有。

修法按本线已有的"每个代次一个源码根"的写法:`1.21.x` 上 `-Pmountpoint=modlauncher` 时加
`src/ml11/resources`,`fml10` 时不加;`1.20.x` 上按 `-Pmodlauncher` 加 `src/ml10/resources` 或
`src/ml11/resources`。改完实测:`1.21.4` 与 `1.20.4` / `1.20.6` / `1.20.2` 的 jar 里各 **1 条**,
`1.21.11`(fml10)仍是 **0 条**。

### 六、这一轮的边界(哪些**没有**成立)

- **1.21.4 这一条没有通过**,卡在第三节第 5 步:客户端活着、没崩、stderr 干净、`Setting user` 也到了,
  但 `Sound engine started` 没出现,加载界面没有移除。**所以本文件更早那条"1.21.4 已实机验证"的记录,
  这一轮没有复现出来**,而这不是"跑得久一点"的问题(两次 300 秒、一次 450 秒的观察形状相同)。
- 差异可能与还原计划的**来源**有关:本轮的计划是从**离线**补丁产物算出来的,而 1.21.4 上顶替游戏类的是
  OptiFine 的**运行期** transformer。运行期那份是打在"NeoForge 已经改过的类"上的,它自带 NeoForge 的成员,
  而离线那份不带 —— 于是按离线产物算出来的计划会把一批本来就在的成员再补一遍。计划里
  `Stitcher`(logger 字段 + 两个 lambda)、`TextureAtlas.getTextures`、`SpriteResourceLoader.loadSprite`
  都在贴图拼接这条路上,值得下一个回合从这里查。
- 本轮**没有**跑其它 14 条线,也**没有**发布任何东西。

## 2026-09-19(下半):1.21.4 通过了,而卡住它的不是加载器而是 shim 的形状

上一节的结尾把 1.21.4 的停滞归给"还原计划是从离线产物算的",并说下一回合从贴图拼接那条路查。**那个猜测是错的**:
问题不在计划,在**我们自己的 `ForgeApiShims` 生成的 Forge API stub 是接口而不是类**。

### 一、这一刻的证据

1.21.4 / NeoForge 21.4.149 / OptiFine `OptiFine_1.21.4_HD_U_J3`,rig 的判定:

```
===== VERDICT: STARTED =====
  Setting user         : True
  Sound engine started : True     ← L353,[net.minecraft.client.sounds.SoundEngine/]: Sound engine started
  new crash reports    : 0
  stderr bytes         : 0
```

日志里同一个目录还有:**232 行 `[OptiFine]`**、**14 条 `Created: minecraft:textures/atlas/…`**(停滞时是 0 条)、
`Caught error loading resourcepacks` 0 次、`NoSuchFieldError` / `NoSuchMethodError` /
`IncompatibleClassChangeError` / `InstantiationError` 各 0 次。本次运行没有崩溃报告(目录里那两份是 00:48 与 00:57 的,
都在这个修好之前)。`FATAL/ERROR` 只剩两条,都是离线测试账号必然的:Realms 401 与 `Failed to fetch user properties`,
对照跑里一模一样。

**232 行、stderr 0 字节、无崩溃报告** —— 与本文件更早为 1.21.4 记下的那一行逐项相同,所以这条线的实机判据这一轮
是**被独立复现**了一次,而不是照抄。

### 二、三个失败,一个根因:shape

装上 OptiFine 与本项目的 loader 之后,失败是**一次一个**地往前走,每修掉一个就露出下一个:

| # | 症状 | 根因 | 处置 |
|---|---|---|---|
| 1 | `NoSuchMethodError: 'void net.minecraft.client.gui.Gui.initModdedOverlays()'` ← `ClientHooks.initClientHooks` | OptiFine 用它自己编译的游戏类顶替运行时那份,而它编译时没有 NeoForge 后加的成员 | 用仓库自己的 `OptifinePipeline` + `MemberRestorePlan` 生成还原计划(**316 行、83 个类、83 个 donor**),装进 loader jar |
| 2 | `NoSuchFieldError: net.minecraftforge.client.RenderTypeGroup 没有成员 EMPTY`,于是 `Caught error loading resourcepacks`,再是 `Failed to wait for future Registration events` | stub 只从 **OptiFine jar** 扫出来,而 OptiFine jar 只点出 **3** 个成员;被顶替的**补丁类**还点出 **51** 个,`EMPTY` 就在里面 | 生成 stub 时把**补丁 jar 一起**喂进去(实测 3 → **54** 个成员) |
| 3 | `IncompatibleClassChangeError: BlockEntity 以接口 CapabilityProvider 作为父类`;修掉后 `InstantiationError: RenderTypeGroup`;再修掉后 `InstantiationError` 出自 stub 自己的 `<clinit>` | `ForgeApiShims` 用"OptiFine 自己那份拷贝"判断类型是接口还是类,而它找的是 `notch/<Forge 类型>.class` —— **Forge 类型在 OptiFine 里根本不可能有这份拷贝**(OptiFine 发的是游戏类,不是 Forge 的),所以查找永远落空,接口这个兜底永远生效,**每个 stub 都成了接口** | 改成**按用法**决定(见第三节) |

第 3 条里"`new` 一个接口"的那一步最容易被误读:报错栈顶是
`at net.minecraftforge.client.RenderTypeGroup.<clinit>`,也就是说崩的不是调用方,而是 **stub 自己的静态初始化器** ——
它要给 `EMPTY` 赋值就得构造一个实例,而接口不能构造。

### 三、`ForgeApiShims` 的修法(已提交,`1.21.x` `f6eeec2`)

形状改为在**读引用类的时候**记录下来,只认两种"只有类才允许"的用法:

- 有类**继承**它(`ClassVisitor.visit` 的 `superName`);
- 有类**构造**它(`visitTypeInsn` 的 `NEW`,或 `visitMethodInsn` 的 `<init>`);
- 以及**自己声明了自己类型的字段**(`declaresItself`)—— 因为给这种字段赋值就要构造实例,而接口不能。
  `RenderTypeGroup.EMPTY` 正是这一种。

老的那条查找留着,只作为"没有任何这类用法"时的兜底。改完实测:`CapabilityProvider` 与 `RenderTypeGroup` 变成
**class**,而真的该是接口的 `RenderType`、`ChunkRenderTypeSet` 仍然是 **interface**。

另外两条不在这轮的代码改动里、但属于同一个 rig 缺口的:

- stub 的扫描范围要包含**补丁类**(见上表第 2 条),这是调用方的事,rig 现在两边都喂。
- **`HierarchyPlan` 的 `reparent.txt` 在 1.21.4 上不起作用**,原因值得单独记:`OptiFineTransformer` 与本项目的
  `PatchedClassTransformer` **各自都声明了同一批 474 个目标**,而 OptiFine 的排在前面 —— 于是我们的
  `reparent(patched, input)` 拿到的是**已经被 OptiFine 换过的** `input`,它的 `superName` 与 payload 的相同,
  `sameName` 判真,整段 reparent 被跳过。**结论:在 1.21.4 这条线上,离线载荷与 OptiFine 自己的 transformer 不能同时用**
  —— 这与本分支 README 早就写下的"1.21.4 走 OptiFine 自己的运行期补丁"一致。上面那次通过的运行就是**不带载荷**的:
  loader jar 只有 0.28 MB(计划 + donor + 55 个 stub),游戏类由 OptiFine 的 transformer 顶替。

### 四、另外两次"看起来像卡死"其实各有原因

- **`IllegalStateException: Already building.`**(`fml_earlydisplay.SimpleBufferBuilder.begin` ←
  `DisplayWindow.paintFramebuffer` ← `NeoForgeLoadingOverlay.render`):本文件为 1.20.4 记过的那个 early window 重入,
  这一轮在 **21.4.149** 上也量到了。它还是**竞态**:同一份配置有一次崩、有一次没崩。
- **换成 `earlyWindowProvider = "none"` 之后不再是崩,而是停在加载界面**。两次 `jstack` 相隔约 40 秒形状相同:
  Render 线程在 `Minecraft.runTick → RenderSystem.limitDisplayFPS → glfwWaitEventsTimeout` 里空转,**不是**卡住;
  16 个 `Worker-Main-*` 全部 `WAITING (parking)`、ForkJoinPool 队列为空。窗口截图与对照跑对比是**判定性的**:
  对照跑(标题画面)整幅均值 RGB 是 (78,78,75) 的灰、布局是标志在上/按钮居中;停滞那次整幅 **94% 是平的 (224,64,64) 红**,
  中间一块文字加一条横向进度条 —— 那是 NeoForge 的加载界面,不是标题画面。**注意窗口标题不能当判据**:
  对照跑和停滞跑都叫 `Minecraft NeoForge* 1.21.4`,那个星号与加载无关。
- 顺带:`-WindowStyle Minimized` 加垂直同步会让 Render 线程死在 `glfwSwapBuffers`;不最小化并把
  `enableVsync:false` 写进 `options.txt` 之后消失。这与 `OptiFabric` 那份 DEVELOPMENT 记的成因一致。

### 五、边界

- 这一轮只让 **1.21.4 一条线**通过,其余 14 条**没有跑**。它们各自走的路不同(1.21.1 / 1.21.3 / 1.21.8 离线换类、
  1.20.x 的 SRG 重映射、1.21.9 – 1.21.11 的 FML 10 挂载点、26.1.2 的 OptiFine 自带 `ClassProcessor`),
  不能由这一条外推。
- **没有发布任何东西**,已发布的 10 个 jar 也没有重新构建。
- 通过的是**判定标准**(标题画面 + 声音引擎 + 无崩溃报告 + stderr 0 字节),不是"功能完备":光影包、抗锯齿、
  进世界这些都还没测。

## 2026-09-19(第三段):往第二条线走 —— 1.21.8 走到最后一道坎

1.21.4 通过之后按同样的路数做 **1.21.8**(NeoForge 21.8.54,OptiFine `J6_pre16`,同样是**不带离线载荷**的配置:
游戏类由 OptiFine 的 transformer 顶替,loader jar 只带计划、donor、stub、reparent)。这一轮为了不再靠手敲命令,
把每个线都要重复的那一段写成了 rig 里的 `prepare-line.ps1`(叠运行时视图 → 打补丁 → 还原计划 → stub → 分层计划),
其余仍旧是 `build-jars.ps1` + `launch.ps1`。它一次跑通,产出:运行时视图 9498 项、补丁类 **516**(441 net/minecraft)、
还原计划 **353 行 / 88 个 donor**、**60 个 stub**、reparent 1 条。

### 一、又踩到 `ForgeApiShims` 的两个形状缺陷(都已修并推送)

1. **同一个构造器写了两遍**。stub 先无条件写一个 `()V`,后面那个"把记录到的成员都写出来"的循环又写一遍,于是:

   ```
   ClassFormatError: Duplicate method name "<init>" with signature "()V" in class file
     net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities
   ```

   现在只有当记录里没有 `<init> ()V` 时才写默认那个。这是这个生成器**第四个**靠"真的把 shim 放进客户端跑"才
   暴露出来的形状缺陷(前三个是接口/类的判断、自己类型的字段、以及扫描范围)。

2. 顺带把上一段的接口/类修正**移植到 `1.20.x` 与 `26.x`**:这两个分支的 `ForgeApiShims.java` 与 1.21.x 修之前
   是逐字节相同的副本,所以用 `git diff` 出的补丁直接 `git apply`(实测 `--check` 通过),不做手改。

### 二、1.21.8 上量到的两件事

- **`optionsof.txt` 不在时的第一次启动会抛异常**:
  `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at `Options.loadOfOptions`
  (1.21.8 是 `Options.java:3108`,1.21.4 上是 3071,两版同一个缺陷)。文件存在且内容合法时不再出现 ——
  也就是说这是 **OptiFine 自己在"文件还不存在"这一支上的问题**,不是我们换类换坏的。实机表现:第一次 stderr
  2268 字节,第二次 **0 字节**,而 `[OptiFine]` 行数从 58 变成 56(没有别的变化)。rig 因此可以照
  `OptiFabric` 那份 launch 脚本的做法,在建 game dir 时先写一份合法的 `optionsof.txt`。
- **`GpuTexture.isStencilEnabled()` 缺失**(当前的坎):

  ```
  java.lang.NoSuchMethodError: 'boolean com.mojang.blaze3d.textures.GpuTexture.isStencilEnabled()'
    at com.mojang.blaze3d.opengl.GlCommandEncoder.clearColorTexture(GlCommandEncoder.java:196)
    at net.minecraft.client.renderer.LightTexture.<init>(LightTexture.java:79)
    at net.minecraft.client.renderer.GameRenderer.<init>(GameRenderer.java:181)
    at net.minecraft.client.Minecraft.<init>(Minecraft.java:599)
  ```

  这是**反方向**的缺失:调用方是 OptiFine 换过的类,而被调用的 `GpuTexture` 是**运行时自己的**类 ——
  所以 `member-restores.txt` 帮不上(它只往被换掉的类里补成员),要靠 loader 的 `stubs.txt`(往运行时类里补)。
  `MissingTargets --stub` 这一轮**已经跑通并产出 804 条**,但这一次崩溃没有被它覆盖,原因是它自己也把
  **载荷**索引进去了:它的文档写得很清楚 —— OptiFine 会给它替换的类**加**成员,所以"运行时没有、而载荷有"的引用
  被认为是可满足的。这里 `GpuTexture` 的情形是:载荷里有这个成员,但**真正被加载的那份没有**(这一类在 1.21.8 上
  没有被换装),于是工具没报、也就没进 `stubs.txt`。**下一问**:对"调用方被换、被调用方没被换"的引用单独走一遍,
  或者把 `--stub` 的跳过前缀／载荷索引按"实际会被换装的类集合"给定,而不是按补丁产物的全集。

### 三、这一段的边界

- **1.21.8 还没有通过**:它到了 `Setting user`、stderr 0 字节,但 `Sound engine started` 没出现,并且写了一份
  崩溃报告(`GpuTexture.isStencilEnabled`)。**1.21.4 的通过没有受到影响**(那一轮之后没有再动过它)。
- 三条分支的这一轮改动都已提交并推送:`1.21.x` 三个提交(服务注册、shim 形状、构造器重复)、`1.20.x` 三个提交
  (元数据/构建 + 形状修正移植 + 本文件)。**没有发布任何东西。**

## 2026-09-19(第四段):1.21.8 的 shim 补齐,以及卡住它的那一步不是换类而是分层

上一段把 1.21.8 停在"`GpuTexture.isStencilEnabled` 缺失",并猜 `MissingTargets` 没覆盖到它。这一轮把 shim
这一层补齐了(`1.21.x` `5bbe9dd`),**`[OptiFine]` 行数从 62 变成 442**,但那条线仍未通过 —— 而卡住它的东西换了。

### 一、`ForgeApiShims` 又补了两处,都是"跑起来才知道"

1. **成员不能只从调用点推**。原来的壳只声明"OptiFine 的类在这个类型上点名的成员",但**调用可以写在子类型上、
   经过父类型解析** —— 于是壳不完整。实测:`IForgeGpuTexture.isStencilEnabled` 是以
   `GpuTexture.isStencilEnabled` 的形式被调用的,所以壳里没有它,而 **OptiFine 自带的 `GpuTexture` 已经装上了**
   也照样崩。现在把 OptiFine 自带那份拷贝的**声明**读进来。
   注意**不搬字节**:第一版就是照搬,结果栽在另一头 —— OptiFine 自带的签名写在它自己的**混淆**命名空间里,
   于是 `NoClassDefFoundError: avs`。判据是"描述符里的类型是否在包内":混淆的游戏类是单段名(`avs`、`fmk`),
   而能解析到的都有斜杠。
2. **类型的集合不能只靠引用扫描**。扫描只看见写进描述符和指令的名字,而 ModLauncher 在变换一个类时**要走它的
   父类与接口链**,那个类型必须存在、哪怕没有一处字节码点名它。实测:`Cannot find class
   net/minecraftforge/common/extensions/IForgeEntity` ← `TransformerClassWriter.computeHierarchyFromFile`,
   而这个类型既不在扫描出的 60 个里,也不在壳里。现在**OptiFine 自带的 Forge 类型全部纳入**,集合从 60 变成 **89**。
3. 顺带:记录到的 `<clinit>` 会和壳自己写的那份撞车(`ClassFormatError: Duplicate method name "<clinit>"`),
   现在构造器与静态初始化器都不再从拷贝里记。

### 二、1.21.8 现在停在哪:分层,而且**那一步根本没跑**

新的崩溃不再是缺成员,而是:

```
java.lang.VerifyError: Bad type on operand stack
  Location: net/neoforged/neoforge/client/network/ClientPayloadHandler.handle(...)
  Reason: Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
          'net/neoforged/neoforge/attachment/AttachmentHolder'
```

**这正是本仓库 `PatchedClassTransformer` 自己的注释里记过的那一条**(注释里连 `AttachmentSync.onChunkSent`
@82 的原文都抄了),也就是说它属于 `reparent.txt` 要处理的情形。这一轮量到的事实:

| 事实 | 判据 |
|---|---|
| 运行时 `BlockEntity` 继承 `net.neoforged.neoforge.attachment.AttachmentHolder` | `javap` 运行时视图 |
| OptiFine 的 `BlockEntity` 继承的是 **`net.minecraftforge.common.capabilities.CapabilityProvider$BlockEntities`**(嵌套类,不是 `CapabilityProvider`) | `javap` 补丁产物 |
| 计划的第 3 个字段与运行时一致(`()V`),计划本身正确 | `reparent.txt` 内容 + `Hierarchy rewrites planned: 1` |
| shim 齐全(`CapabilityProvider$BlockEntities.class` 在 loader jar 里) | 资源检查 |
| **reparent 一次也没成功执行** | 日志里 `Re-parented` **0 行** |
| 也**不是被拒绝** | 日志里 `Left ... BlockEntity alone` **0 行**(另有 3 个类被拒,都不是它) |

两件事都为零,只剩一种解释:`if(!sameName(patched.superName, input.superName))` 这个**前置判断没成立** ——
也就是 transformer 收到的 `input`(它认为的"运行时那份")的父类,**已经等于** payload 的父类,即那个 Forge 类型。
所以整段 reparent 被跳过,装进去的 `BlockEntity` 仍然挂着 Forge 的父类,VerifyError 随之而来。

另一处值得并排看的事实:**1.21.8 上 `OptiFineTransformer` 没有打印 `Targets:`**(1.21.4 上打印 `Targets: 474`),
本项目的 `Patched-class targets: 516` 是唯一的。所以这一条线上并不存在"两个 transformer 抢同一批类"的问题,
`input` 为什么已经是 Forge 版本,是**下一问**。

### 三、rig 的一条操作事实(省下一次同样的困惑)

新建的 game dir 里 `config/fml.toml` 是**默认的 `fmlearlywindow`**,于是会撞上本文件记过的
`IllegalStateException: Already building.`(1.21.8 上这次出现在 `PerformanceElement.render` → 进度条)。
`launch.ps1` 的 `-EarlyWindowProvider skip` 是"别动这个文件",所以新目录第一次跑要么不传这个开关、要么先写
`earlyWindowProvider = "none"`。

### 四、边界

- **1.21.8 仍未通过**;`[OptiFine]` 442 行说明它已经走到很后面(1.21.4 通过与它同量级)。**1.21.4 的通过不受影响。**
- 这一轮只动了生成 shim 的工具,没有碰 loader 的换类逻辑;**reparent 那一问没有结论**,只把"它没跑"与
  "为什么没跑"的边界量清楚了。
- 其余 13 条线没有跑,**没有发布任何东西**。

### 五、补充:那一问已经答了(reparent 为什么没跑)

上一节把"`input` 为什么已经是 Forge 版本"留成了下一问。这一轮给 `PatchedClassTransformer` 加了一行日志
(`1.21.x` `0af9601`)—— 因为"计划覆盖的类"被静默跳过时,`Re-parented` 与 `Left ... alone` **同时为零**,
而这与"计划根本没被读到"是同一个形状,分不清就要多花一轮。日志实测输出:

```
Reparent plan covers net.minecraft.world.level.block.entity.BlockEntity:
  the payload extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities,
  the class handed over extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities
  - equal, so nothing is rewritten and the copy is installed as it is
```

也就是**判据本身**,不用再猜:交给 transformer 的那份 `input`,父类**已经是** Forge 类型。于是这一条线上
`BlockEntity` 始终挂着 Forge 父类,因果链是完整的:

1. reparent 被跳过(`1.21.x` 上 `Re-parented` 0 行、`Left ... BlockEntity alone` 0 行) →
2. `VerifyError: Bad type on operand stack`,`BlockEntity` 不能赋给 `AttachmentHolder`
   (`ClientPayloadHandler.handle`)→ FML 报 `Failed to wait for future Mod Construction` →
3. FML 随后对注册事件 `Cowardly refusing to send event ...`,而 OptiFine 卡在
   `[OptiFine] Waiting for model sprites`(180 秒里刷了 33 次,没有图集、没有声音引擎)。

这一轮加的是**日志不是修法**:修法取决于"为什么交过来的那份已经是 OptiFine 的",这一点仍未定。

### 六、一条方法论教训(rig 差点给出一次假通过)

把 mod jar 名字写空时,`launch.ps1` 会把**目录**当成 jar 复制进 `mods/`,于是那一次运行报出
`VERDICT: STARTED`、`Setting user` ✓、`Sound engine started` ✓、0 崩溃报告、stderr 0 字节 —— 而
**`[OptiFine]` 行数是 0**,也就是 OptiFine 根本没加载。判定脚本现在会拒绝非文件的 mod 路径。

**记这一条的原因比它本身重要**:本项目把"`[OptiFine]` 行数"当验收数字之一,而这一次它恰好是唯一能戳破
假通过的那一列。判据里的每一列都不是装饰。

## 2026-09-19(第五段):reparent 修好了,而它露出的是一条更深的分歧

### 一、reparent 现在真的执行了(`1.21.x` `6f66c27`)

上一节量到"交给 transformer 的那份 `input`,父类已经是 Forge 类型"。据此改掉那个前置判断:计划覆盖的类
**即使两份的父类相同也照做**,而"这份是不是运行时那份"改由"它是不是已经挂着 Forge 父类"来判定;判定为
不可用时,`reparent()` 跳过它已经无法做的那次比对(计划本来就是 `HierarchyPlan` 用同一批 jar 量出来的),
并且**两种结果都各打一行日志**。

实测(1.21.8):

```
Re-parented net.minecraft.world.level.block.entity.BlockEntity from the Forge type
  net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities onto the runtime's …
```

`VerifyError: Bad type on operand stack` **从日志里消失了**,`Failed to wait for future Mod Construction` 也不再出现。
这条线**仍未通过**,但卡点换到了下一处。

### 二、新卡点的**根因已经查到数据层**,不是猜的

```
ClassFormatError: Illegal field modifiers in class
  net/minecraft/client/renderer/block/model/BlockStateModel$Unbaked: 0x9
```

`0x9` = `public static`(缺 `final`),而**接口里的字段必须是 `public static final`**。两边的实测:

| | 形态 | 那两个字段 |
|---|---|---|
| 运行时的类(`runtime-1.21.8.jar`) | `public interface …` | `public static final … SINGLE_MODEL_CODEC` / `WEIGHTED_MODEL_CODEC` |
| **donor**(`plan\donors\…BlockStateModel$Unbaked.class`) | `public class … implements …` —— **被写成普通类** | `public static …`(**没有 final**) |

也就是说:**`MemberRestorePlan` 把 donor 写成普通类、并把字段的 `final` 去掉**(去掉是为了让运行时能由
`optifineoforge$init$…` 填值),而 `MemberRestoreTransformer` **原样**把字段复制进目标类 —— 目标是接口,于是
`0x9` 非法。`PatchedClassTransformer` 里**有**这条规则(接口字段一律强制 `public static final`,本文件在 26.x 的
注释里也写过"接口的字段必须是 public static final,否则 JVM 直接以 ClassFormatError 拒绝"),但
`MemberRestoreTransformer` 里**没有**。

### 三、由此露出的更深一层(下一问)

把三件事并排看:

1. 1.21.8 上 `OptiFineTransformer` **不打印** `Targets:`(1.21.4 上打印 `474`),但那份 `input` 的父类**已经是
   Forge 类型** —— 说明**先换类的是 OptiFine**,我们的 transformer 拿到的是它换过的那一份;
2. 因此本文件的判断里,"保持运行时的版本"这一类决定(那 3 个 `Left ... alone`)在 1.21.8 上**并不会真的
   拿到运行时的版本**,而是留在 OptiFine 的那一份上;
3. 上面第二节的崩溃正是这一类的后果之一。

**所以下一问不是某个字段的标志位,而是**:loader 需要拿到**真正的运行时类**(它已经捕获了 module layer manager,
`OptifiNeoforgeTransformationService.layers()` 就是),而不是把 `input` 当成运行时那份。

### 四、边界

- **1.21.4 仍然通过**(这一轮没碰它的路径);**1.21.8 仍未通过**,但它已经越过换装与 mod 构造阶段。
- 这一轮改了 `PatchedClassTransformer` 的判定与 `reparent` 的空引用容忍;**没有**动 `MemberRestoreTransformer`
  (第二节那条修法还没做)。
- 其余 13 条线没有跑,**没有发布任何东西**。

## 2026-09-19(续):给 1.20.x 的 loader 加"删除成员"这一档,1.20.4 的类定义因此修好

上一条把 1.20.4 的阻塞定死在一个类上:载荷的 `AbstractClientPlayer` 带了三个对 final 方法的重写。本轮把这一档修法做出来了,
并且**实测确实把这一处修好了** —— 但 1.20.4 仍然**没有通过**,新的阻塞记在下面第二节。

### 一、加了什么(代码在本分支,不在 rig)

`PatchedClassTransformer` 新增第三个计划文件 `optifineoforge/drop-members.txt`,行格式
`owner<TAB>name<TAB>desc`,语义是**这个成员不要出现在交付出去的类里**。前两个计划都表达不了这一档:

- `keep-runtime.txt`(已有)是把**游戏侧的方法体**换回载荷成员上 —— 前提是运行时那个类**有**这个成员;
- `member-restores.txt`(已有)是补上"运行时**有**、载荷没有"的成员;
- 而这里要的是"载荷有、运行时没有、且存在就会让类定义不了",只能**删掉**。

计划在 `dropMembers(ClassNode)` 里生效,排在 `keepRuntimeBodies` **之后**(最后说话),方法与字段都覆盖,每删一个成员都会
打一行日志。rig 的 `build-jars.ps1` 相应新增 `-DropMembersFile`(嵌入 `optifineoforge/drop-members.txt`),1.20.4 的计划文件是
rig 里的 `drop-members-1.20.4.txt`,内容就是那三行 `net/minecraft/client/player/AbstractClientPlayer` 上的
`getX/getY/getZ ()D`。

**效果(实测)**:`IncompatibleClassChangeError` 消失,`AbstractClientPlayer` 正常定义;同一次启动里 `[OptiFine]` 行数从
**0–2 行**推进到 **10 行**,载入过程走到 `Minecraft.<init>` 里面(日志里能看到 `GlDebug`、`ClientBrandRetriever`、
`AbstractTexture` 等类被换装并做成员恢复)。

### 二、1.20.4 现在停在哪(新阻塞,已量到证据)

同一次启动在 `Minecraft.<init>` 里抛异常,而**原始异常看不到**,因为崩溃处理那条路自己先死了:

```
at TRANSFORMER/srg/net.optifine.CrashReporter.extendCrashReport(CrashReporter.java:127)
at TRANSFORMER/srg/net.optifine.CrashReporter.onCrashReport(CrashReporter.java:42)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.CrashReport.getFriendlyReport(CrashReport.java:172)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.client.Minecraft.crash(Minecraft.java:949)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.client.main.Main.main(Main.java:165)
Caused by: java.lang.ExceptionInInitializerError
Caused by: java.lang.NullPointerException: Cannot read field "gameDirectory" because the return value of
   "net.minecraft.client.Minecraft.getInstance()" is null
	at TRANSFORMER/srg/net.optifine.shaders.Shaders.<clinit>(Shaders.java:603)
```

也就是说:`Main.main:165` 是 `catch` 块,**真正的原始异常被 `Minecraft.crash` 吃掉了**,而 crash 报告在
`CrashReporter → Shaders.<clinit>` 处因为 `Minecraft.getInstance()` 还是 null 再次抛错。为了把原始异常挖出来,试了
JVM 的 `-Xlog:exceptions=trace`(87 938 行日志):里面有崩溃处理自身那条链(`RenderSystem.assertOnRenderThread` →
`SystemReport.setDetail` → `CrashReporter` → `Shaders.<clinit>`),**但没有**原始异常的产生记录(JVM 自己生成的 helpful
NPE 不走这个日志标签),所以这一轮**没有拿到原始 throwable**。

顺带确认了两件事,避免把环境问题误判成我们的问题:

- **同一台 rig、同一个实例、不加任何 mod** 的对照跑到了 `VERDICT: STARTED`(`Setting user` ✓、声音引擎 ✓、
  0 崩溃、stderr 0 字节)—— 所以 GL/原生库/`earlyWindowProvider` 这套环境是好的,上面的崩溃确实来自我们这一侧;
- rig 自身有两个坑也修掉了(脚本在 rig 里):`natives\` 是**所有线共用**的一个目录,而 1.20.1–1.20.4 要 LWJGL **3.3.2**、
  1.20.6 起要 **3.3.3**,后跑的线会把前一条线的 DLL 留在那里(实测报 `[LWJGL] Incompatible Java and native library
  versions detected`),现在用新增的 `natives-for.ps1 -Lwjgl 3.3.2` 在启动前重建;另外启动崩过的 JVM **会留在后台**占着
  `glfw.dll`,重装 natives 前必须先收掉(`launch.ps1` 的 "stopped the JVM" 并不总能覆盖崩溃路径)。

`launch.ps1` 另加了一条 `RIG_EXTRA_JVM`(用 `|` 分隔):形如 `-Xlog:...` 的参数没法通过 `-File` 传进去 —— PowerShell 会把它
当成参数名,报 `MissingArgument`。

### 三、二分法的第一次尝试:`-Doptifineoforge.skipPayload=true`(结果有用,但**这一问被混淆了**)

给 1.20.x 的 transformer 加了一个调试开关 `-Doptifineoforge.skipPayload=true`:它让**载荷一个类都不投递**(stub 照旧跑,
因为未被换装的运行时类需要它们),用来把失败二分到"换装"还是"其余部分"。第一次运行(载荷关闭)的结果**不是**同一个崩溃,
而是回到了更早那条:

```
Caused by: java.lang.NoSuchMethodError:
  'net.minecraft.network.chat.MutableComponent net.minecraft.network.chat.Component.m_237115_(java.lang.String)'
  at net.minecraft.resources.ResourceLocation.<clinit>
  ...
  at net.minecraft.client.main.Main.main(Main.java:61)
```

原因也量到了:**重打包后的 OptiFine jar 里仍然带着全部 4948 个 `patch/` 条目**(`patch/srg/**` 与 `patch/notch/**` 都在,
另外 `net/optifine/**` 是 0 条 —— OptiFine 自己的类在 loader jar 里,服务文件
`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 在 OptiFine jar 里)。也就是说 **OptiFine 自己的
transformer 仍在装载期按 `patch/srg` 打补丁、并把 SRG 名写进游戏类**,这正是 1.21 那条线记录过的现象;1.21 用
`SrgNameTable` + 装载期改名(`-Doptifineoforge.renameSrg`)压住它,而**那条能力在 1.20.x 这条线上没有**。所以载荷一关,
没人去覆盖 OptiFine 装载期打出来的那些类,SRG 名就漏出来了 —— 这一问**不能**用来判"崩溃是不是换装引起的"。

反过来说,这条也解释了我们**默认**(载荷开启)那次为什么能走到 `Minecraft.<init>`:我们的投递把那 427 个类覆盖掉了,
SRG 名随之消失。下一步因此收敛成两件互相独立的事:(a) 把 1.21.x 的**装载期改名**移植到 1.20.x(它同时能让 skipPayload
这个开关变得有意义);(b) 或者让重打包阶段**不带补丁数据**,使 OptiFine 的 transformer 无法再自行打补丁、载荷只能由我们投递
—— 这需要 rig 侧支持"丢掉全部 patch 条目"(现有 `--unpatched <file>` 是按类丢,1.20.x 的 `OptifineJar` 里也还没有这个选项)。

### 四、又一个被排除的嫌疑:OptiFine jar 里那 4948 条补丁数据**不是**这次崩溃的原因

把重打包后的 OptiFine jar 里 `patch/**` 全部去掉(保留 906 条其它条目,拷成一个 `optifine-1.20.4-nopatches.jar`,
再走一遍 `build-jars.ps1`),重新启动:**失败点一模一样** —— 崩溃报告仍死在
`CrashReporter → Shaders.<clinit>` 那条链上,`[OptiFine]` 行数 8(带补丁数据时是 10)。所以装载期补丁数据只解释
`skipPayload` 那次看到的 SRG 漏出,**不解释**默认这几次的构造期崩溃。

同一批日志把崩溃位置夹得更紧:最后三行永远是

```
[OptifiNeoforge/]: Replaced net.minecraft.client.renderer.texture.AbstractTexture with OptiFine's patched version (...)
[OptifiNeoforge/]: Initialised 1 restored fields in net/minecraft/client/renderer/texture/AbstractTexture
[OptifiNeoforge/]: Restored 3 members in net/minecraft/client/renderer/texture/AbstractTexture from its donor
```

也就是说:**换装与成员恢复都成功返回了**,崩溃发生在"这个被换装的类第一次被游戏使用/初始化"的时候。

### 五、把位置夹到"谁在构造期初始化了 OptiFine 的着色器类"(并且更正了上一版的一个错判)

用 `-Xlog:class+init=info` 再跑一次,崩溃前**最后初始化的类**是这一串(4.714–4.716 秒,紧随 `AbstractTexture`
换装之后):

```
net/optifine/shaders/ProgramStage
net/optifine/shaders/Program
net/optifine/shaders/ProgramStack
net/optifine/shaders/config/Property
net/optifine/shaders/config/PropertyDefaultTrueFalse
```

也就是说,**在 `Minecraft` 的构造过程里,OptiFine 的着色器机制就已经被初始化了**,而 `Shaders.<clinit>`(它去读
`Minecraft.getInstance().gameDirectory`)在那时必然是 null —— 所以那个 NPE **很可能就是原始异常本身**,不只是崩溃
报告路径上的次生错误(时间也吻合:类初始化在 4.71 秒,崩溃报告相关的异常在 5.04 秒之后)。

**更正上一版的一个错判**:我先前写"成员恢复会写静态字段、从而强制 `X.<clinit>`"—— 读代码后**不成立**。1.20.x 的
`MemberRestoreTransformer` 并不在运行期写静态字段,它是把 donor 的静态初始化**内联进被换装类的 `<clinit>`**
(源码里那条注释写着"value is inlined into the class's own static initialiser rather than called through a separate
method");日志里的 `Initialised N restored static fields in X` 只是**换装期**打的字,不代表运行期提前初始化了 X。

所以下一步的问题变得很具体:**上游 OptiFine 是靠什么保证 `Shaders` 不在构造期被初始化,而我们这条流程为什么提前碰到了它** ——
候选是"被换装的某个类在构造期就调到了 `net/optifine/Config`"(载荷里 `AbstractTexture` 的字节码里确实有
`net/optifine/Config.getMipmapType()`),而 `Config.<clinit>` 会不会链到着色器类,是下一步要用
`-Xlog:class+init=debug`(带初始化上下文)或直接 `javap -c` 看 `Config` 的 `<clinit>` 来定的。

### 六、触发链已经量出来:`Config.<clinit>` → `Shaders.<clinit>` → NPE,而且这条链是 OptiFine 自己的

同一份 `-Xlog:class+init` 日志按时间排出来是这样(本机实测):

```
5.019  com/mojang/blaze3d/platform/GlDebug          <- 我们换装过的类
5.021  net/minecraft/client/ClientBrandRetriever   <- 我们换装过的类
5.022  com/mojang/blaze3d/platform/GlUtil
5.025  net/optifine/Config                          <- OptiFine 自己的类
5.034  net/optifine/shaders/Shaders                 <- 9 毫秒后
   ...  随后是 ProgramStage / Program / ProgramStack / Property / ...
```

再对载荷里那份 `net/optifine/shaders/Shaders.class` 做 `javap -p -c`:它的 `<clinit>` 有 **2074 行**字节码,其中
偏移 2922/2925 就是

```
invokestatic  net/minecraft/client/Minecraft.getInstance()
getfield      net/minecraft/client/Minecraft.gameDirectory
```

也就是说 **`Shaders` 这个类在 `Minecraft` 实例存在之前根本无法初始化** —— 上游 OptiFine 必然是在实例存在之后才第一次碰到
它。我们这条流程里,构造期被换装的 `GlDebug`/`ClientBrandRetriever` 这类类先初始化,顺带把 OptiFine 的 `Config` 拉起来,
`Config.<clinit>` 再链到 `Shaders.<clinit>`,于是必然 NPE。**这条链本身是 OptiFine 自己的类之间的链**(不是我们拼出来的),
所以我们能做的是**改变"什么时候碰到它"**,而不是改 `Shaders`。

顺带把"换另一个 1.20.4 构建"这条也量了:两个构建在这两处**完全一样** —— 都没有 `Entity`/`blv` 的补丁项,
`AbstractClientPlayer` 的 srg 补丁都是 7997 字节。所以换 `I8_pre4` 没有理由改变这条链(这只说明"不太可能",不是"不会")。

### 七、下一步(可执行的顺序)

1. 找出**具体是哪一个被换装的类**在构造期碰到 `Config`:把 `-Xlog:class+init=trace` 与 `-Xlog:class+load` 对齐,或对
   候选类(`GlDebug`、`ClientBrandRetriever`、`GlUtil`)逐个用 `javap -c` 看它们的 `<clinit>` 里有没有
   `net/optifine/Config` 调用。
2. 若锁定到某一个类,再用本分支已有的能力把它排除在换装之外(整类保留运行时版本 —— 注意当前 `keep-runtime.txt` 的解析
   只认三列 `owner|name|desc`,整类那条形式在这条线上还**没有**实现,需要先补上),看崩溃是否随之消失。
3. 另一条互不排斥的路:让 OptiFine 的 `Config`/`Shaders` 在 `Minecraft` 实例就绪之后再初始化,即把"谁先碰它"的顺序
   改回来 —— 这需要在 loader 侧找一个更晚的挂点,而不是改 OptiFine。

### 八、这一轮又排除了两条,并给 loader 加了一个"初始化/调用点追踪"开关

1. **`AbstractTexture.setFilter` 不是触发点(实测)。** 用成员级 keep 计划
   (`net/minecraft/client/renderer/texture/AbstractTexture<TAB>setFilter<TAB>(ZZ)V`,日志确认
   `Kept the game's body of ...` 生效)把载荷那一段 `Config.getMipmapType()` 调用换回游戏侧实现后,**崩溃点没变**
   (仍是 `CrashReporter → Shaders.<clinit>`,10 行 `[OptiFine]`)。
2. **loader 追不到 OptiFine 自己的类(实测,很重要)。** 新加的
   `-Doptifineoforge.traceInit=<内部名>[,<名>...]` 会在被追踪类的 `<clinit>` 和"含 `net/optifine/Config` 调用的方法"
   开头插入 `new Throwable().printStackTrace()`。对 `net/optifine/Config` 用这个开关:**既没有 `Replaced
   net.optifine.Config` 那行换装日志,也没有任何追踪输出** —— 说明 `net/optifine/**` 根本不经过我们的 transformer,
   它们是作为**模块**(栈帧里的 `srg` 就是模块名)直接加载的。因此"在 OptiFine 自己的类里插桩"这件事,loader 侧做不到,
   只能离线改写载荷 jar。
3. 用 `-Doptifineoforge.traceInit=*`(追踪**每一个**会调用 `Config` 的交付类)跑一次:52 个类被插桩成功,但**崩溃前
   没有任何一个插桩点被执行** ⇒ **构造期第一次碰 `Config` 的不是我们交付的任何游戏类**。这一条把范围收得很窄:
   触发来自 OptiFine 自己的类在启动阶段的某次初始化/调用(候选如 3.758 秒就初始化了的
   `net/optifine/reflect/Reflector` 那一套反射解析 —— `Class.forName(String)` 是会初始化目标类的)。

**结论**:要再往前推,只能**离线**在载荷 jar 里给 `srg/net/optifine/Config.class` 插桩(需要一个小 ASM 工具,
把栈打到 `System.err`/游戏日志),或者换一条线。这两条都记在下面的"下一步"里。

### 十、原始崩溃终于看到了 —— 而且是我们自己流水线造成的(1.20.4 的关键发现)

前面那些 `Config`/`Shaders` 的 NPE **全都发生在崩溃报告里**(追踪栈显示它们的调用者是
`Minecraft.fillSystemReport → SystemReport.setDetail → GlDebug.<clinit> → GlDebug.makeIgnoredErrors`,以及
`CrashReporter.extendCrashReport`),所以它们只是**次生错误** —— 这也解释了为什么崩溃报告一直写不出来。为了绕开这条
死循环,新加了两个调试开关:

- `-Doptifineoforge.traceInit=<名>[,<名>|*]`:在被追踪类的 `<clinit>` 与其"调用/访问 `net/optifine/Config`"的方法开头
  打印标记行与栈(**注意输出落在进程 stderr**,不是 `latest.log` —— 我先前按 `latest.log` 找,误判成"没有插桩点运行");
- `-Doptifineoforge.traceCrash=true`:在 `CrashReport.forThrowable` 开头打印那个 throwable,绕开 OptiFine 的崩溃回调。

`traceCrash` 一把就把**原始异常**拿了出来:

```
java.lang.RuntimeException: java.lang.IncompatibleClassChangeError:
  Expected static method 'com.mojang.serialization.Codec
  net.minecraft.world.level.block.state.BlockState.codec(com.mojang.serialization.Codec, java.util.function.Function)'
  at net.minecraft.world.level.block.state.BlockState.<clinit>(BlockState.java:19)
  ... Blocks.<clinit> → FireBlock.bootStrap → Bootstrap.bootStrap → Main.main:157
```

**根因也量出来了,而且在我们这一侧**(逐阶段 `javap` 对照):

| 阶段 | `BlockState` 里的相关成员 |
|---|---|
| OptiFine 补丁输出(`optifine-patched.jar`) | 只有字段 `f_61039_`,**没有** `m_61127_` |
| 我们的 `MissingTargets --stub` 之后(`optifine-patched-stubbed.jar`) | 多出一个 **`public`(非 static)** `Codec m_61127_(Codec, Function)` |
| 改名后交给 loader 的那一份 | `CODEC` 字段 + **非 static** 的 `codec(Codec, Function)` |

也就是说:**`MissingTargets --stub` 给一个"载荷自己的类"补了一个成员,而且补成了实例方法**,而该类自己的 `<clinit>`
是用 `invokestatic` 调它的 ⇒ `IncompatibleClassChangeError: Expected static method` ✔。这正是从第一轮起就挡住 1.20.4
的那次崩溃。

**下一步(明确)**:让 stub 阶段不要给载荷自己的类补成员(或至少保留 `static` 标志),再重跑装配与启动。这条修好之后,
1.20.4 才有继续往前的可能。

顺带记下本轮另一个新能力:**整类保留运行时版本**(`keep-runtime.txt` 的 `owner<TAB>*` 形式)已实现并在 1.20.4 上实测生效
(日志 `Kept the runtime's whole com.mojang.blaze3d.platform.GlDebug ...`),但它**没有**改变崩溃 —— 因为真正的阻塞在别处(见上)。


### 十一、边界

- **1.20.6 仍然是本分支唯一实测通过的版本**;1.20.4 的阻塞本轮**定位到我们自己的流水线**(stub 阶段给载荷自己的类补了
  一个非 static 成员),修法明确但**尚未修**,所以 1.20.4 仍未通过;1.20.1 / 1.20.2 未跑。
- 本轮新增的 loader 能力是**通用**的(任何线都能用 `drop-members.txt`、`keep-runtime.txt` 的整类形式、`traceInit` /
  `traceCrash` 两个调试开关),但**只有 1.20.4 实测用过它们**。
- **没有发布任何东西**;已发布的 jar 没有重建。

## 2026-09-19(晚间):在本机重建的 rig 上跑 1.20.6(实测与记录的差异),以及 1.20.4 的实测阻塞

原始 rig(`optifineoforge-test`)在这台机器上**不存在**,所以这一轮是**从零重建**一个等价 rig:下载原版客户端
与 NeoForge 安装器、把本仓库的离线流水线(`kynarain.cn.optifineoforge.optifine`)跑在**用户自己的** OptiFine jar
上、再用 rig 的 `launch.ps1` 起一次真实客户端。OptiFine 不随仓库分发,所以载荷必须现场生成 —— 这一轮从头到尾
没有用过任何别人预打包的 OptiFine 载荷。

### 一、1.20.6:判据逐项(记录 vs 本机实测)

目标:`1.20.6` / NeoForge `20.6.141`(ModLauncher 11、Java 21)/ `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar`。

| 判据 | 记录 | 本机实测 | 结论 |
|---|---|---|---|
| `VERDICT` | `STARTED` | `STARTED` | 一致 |
| `Setting user` | ✓ | ✓(`Setting user: Dev`) | 一致 |
| 本次运行的崩溃报告 | 0 | 0 | 一致 |
| stderr | 0 字节 | 0 字节 | 一致 |
| `[OptiFine]` 行数(`latest.log`) | 222 | **231** | **不一致(+9)** |

- 231 是**可复现**的:两次独立运行都是 231(rig 打印的 462 是合并输出 = stdout+stderr+latest.log,正好 2×)。
- 这 +9 **不是**这条线独有:同一台机器上 1.21 与 1.21.1 是 +9、1.21.6 / 1.21.7 / 1.21.8 是 +7,而 1.21.3 与
  1.21.4 与记录**完全相等**。**记录里的 `latest.log` 不在仓库里**,所以"多出来的是哪 9 行"无法从这边归因;
  按现有证据看更可能是环境差异(驱动/资源包枚举),不是载荷差异。
- 本机这 231 行的构成(按消息形状分组):`(Reflector) Class not present` 40、`Scaled non power of` 18、
  `(Reflector) Method not present` 17、`Multitexture: false` 14、`Animated sprites` 14、`Scaled too small
  texture` 12、`(Reflector) Field not present` 12、`Unknown resource pack type` 11,其余是 CTM 与贴图集类。
- 窗口另用 rig 的 `capture-window.ps1` 抓过一次(`PrintWindow=True`,窗口标题 `Minecraft* 1.20.6`)。它与两条
  **已确认是标题界面**的截图(控制组、1.21.8)同类:292 个颜色桶、最大桶只占 11%、含 0.09% 近黄色像素
  (标题界面的 splash 文字);而 1.21 卡在加载遮罩时抓到的形态是 87 个桶、最大桶占 84%、均值 `233,73,83`。
  判据里的 `Sound engine started` 也为真 —— 1.21 卡在遮罩时**没有**这一行。

### 二、这条线上"1.20.6 起不再需要"的东西,本轮是实测到的

1. **载荷命名空间**:`MissingTargets` 扫这份载荷是 **43918 条游戏成员引用、只有 23 条在运行时里找不到**(0.05%),
   而同一工具在同一台机器上扫 **1.20.4** 的载荷是 **43162 条里 3178 条找不到**(7.4%)。所以 1.20.4 的载荷是
   SRG 名、1.20.6 的载荷已经是官方名 —— 与本文档"1.20.6+ 自然空转"的判断一致,这次是量出来的,不是推的。
   直接后果:1.20.6 这一条线**不需要** `SrgNameTable` / `SrgRemap` 这一步(1.20.4 需要,见第四节)。
2. **keep 计划为空**:`PayloadDrift` 为 1.20.6 提出的 `keep-runtime.proposed.txt` 是 0 行(1.21 那条线要手写),
   接口计划 13 行、访问计划 104 行、成员恢复 285 条 / 77 个 donor、`reparent.txt` 1 行、运行时 stub 3 条。

### 三、`optionsof.txt` 在 1.20.6 上也确实是必需的(实测)

把游戏目录里的 `optionsof.txt` 移走后重跑:客户端在 OptiFine 自己的 Options 加载处抛异常(日志尾部有栈),
`[OptiFine]` 行数变成 233,即**这不是一次干净运行**。放回该文件后同一套 jar 回到 `STARTED`。所以"某些构建的空
游戏目录会炸"这条已知限制,在 1.20.6 / J1_pre18 上同样成立。

### 四、1.20.4:装配成功,启动死在一处,原因已定位

NeoForge `20.4.251`(ModLauncher 10、Java 17)+ `OptiFine_1.20.4_HD_U_I7.jar`:装配本身走通(载荷 427 个游戏类、
成员恢复 6009 条 / 371 个类、接口计划 12 行、访问计划 1 行、stub 228 条 + 2035 条留给 loader),但启动在
`net.minecraft.commands.BrigadierExceptions.<clinit>` 处死:

```
java.lang.NoSuchMethodError: 'net.minecraft.network.chat.MutableComponent net.minecraft.network.chat.Component.m_237115_(java.lang.String)'
```

这是**载荷里的 SRG 引用没改名**的典型形态(第二节量到的 7.4% 就是它)。

**随后就把这一步做了** —— 这是本轮 1.20.4 的进展:从 Forge maven 取到 MCPConfig `1.20.4-20231207.112700`
的 `joined.tsrg`(注意版本串**不是** NeoForm 的 `20240627.114801`,拿后者去下载是 404),用 rig 的
`proguard-to-tsrg.ps1` 把 Mojang 的 `client-1.20.4-…-mappings.txt` 转成 obf→official 的 tsrg2(7787 类 /
35 236 字段 / 68 037 方法),再对**载荷 jar 与重打包后的 OptiFine jar 各跑一次** `SrgRemap`:OptiFine 那侧改了
3462 个方法名与 1400 个字段名、3 个无法解析;载荷那侧有 252 个字段与 137 个方法"表里没有对应项"、52 个"成员形状
变了"。重新组装后启动,**上一条 `NoSuchMethodError` 消失**,载入阶段推进到下一处:

```
java.lang.IncompatibleClassChangeError: class net.minecraft.client.player.AbstractClientPlayer
  overrides final method net.minecraft.world.entity.Entity.getY()D
```

用 `javap` 量到的四件事(全部在本机),其中第 4 件把这条阻塞**在我们的 loader 之外**也证死了:

1. 载荷里的 `AbstractClientPlayer` 声明了 **三个** 对 final 方法的重写:`m_20185_()` / `m_20186_()` /
   `m_20189_()`,即改名后的 `getX()` / `getY()` / `getZ()`;这三行在**未改名的载荷里就有**,所以是 OptiFine
   自己补丁带的方法,不是改名造的(改名本身是对的:joined.tsrg 是 `tsrg2 obf srg id` 三列格式,`blv` 对
   `net/minecraft/src/C_507_`,Entity 的 `dr/dt` 方法正是 `m_20185_`/`m_20186_`,而我的 obf→official 表给出
   `getX`/`getY` —— 两边一致)。
2. 运行时的 `AbstractClientPlayer` **一个都没有**(`getX/getY/getZ` 在 1.20.4 只在 `Entity` 上声明);
3. 而 `Entity` 的这三个存取器在 1.20.4 上是 **final**:原版混淆 jar 的 `blv`(= Entity)是 `public final double
   dt();`,NeoForge 的 `neoforge-20.4.251-client.jar` 与 `client-…-srg.jar` 也都是 `public final double getX/getY/getZ()`。
   顺带量了 1.20.6 作为对照:它的 `Entity` **同样**是 final,但 **1.20.6 的载荷里根本没有 `AbstractClientPlayer`
   这个类** —— 这正是那条线能过的原因之一。
4. **把 loader 完全排除在外的验证**:从载荷里取出这一个 class 文件、按真实类名放到临时目录,用 `jshell`
   以「载荷 jar + `runtime-1.20.4.jar` + NeoForge universal」为 classpath 直接 `Class.forName`(需要 universal 是因为
   接口 `IPlayerExtension` 由 loader 注入)。JVM 的原话与游戏里那一行**逐字相同**:
   `IncompatibleClassChangeError: class net.minecraft.client.player.AbstractClientPlayer overrides final method
   net.minecraft.world.entity.Entity.getY()D`。也就是说:**这份 OptiFine 载荷的这个类,在 1.20.4 运行时上按原样
   就是不可定义的**,与我们的 transformer、keep 计划、member restore 都无关。

顺带把"拿未修改的 OptiFine jar 做对照"这条也跑了:1.20.4 上 FML **同样拒绝**它
(`InvalidLauncherSetupException: Invalid Services found OptiFine`),所以那条对照路走不通,上面的第 4 件才是可用的证据。

**再往下量了一层,把一个看似合理的解释也否掉了**(这三条同样都在本机):

- "OptiFine 的 1.20.4 载荷是为 **Forge** 运行时而编的、Forge 去掉了那三个 `final`" —— **否掉**:把 Forge
  `1.20.4-49.0.50` 的 userdev 拉下来(3 049 360 字节),`patches/net/minecraft/world/entity/Entity.java.patch`
  644 行里**没有一处** `getX()` / `getY()` / `position()`;所以 Forge 的 Entity 同样保留 final,这条解释不成立。
- 三个重写也**不是从基类继承来的**:原版混淆的 `AbstractClientPlayer`(`fsg`)里 `dr()/dt()/du()` 一个都没有 ——
  也就是说它们是 OptiFine 打补丁之后**出现在载荷这一份里**的。
- 换一个 1.20.4 的 OptiFine 构建也不解决问题:两个构建**都**带
  `patch/srg/net/minecraft/client/player/AbstractClientPlayer.class.xdelta`(`I7` 4948 条补丁项、`I8_pre4` 4956 条,
  两份条目表已逐个数过)。
- **更正(同一轮内自查)**:下面这条 md5 观察**不构成证据**,不要当作"基类喂错了"的依据。把两套补丁的 md5 都拉出来比过:
  OptiFine 的 `patch/notch/` 那一套(即喂原版混淆 jar 时走的那一套)对 **1.20.4 和 1.20.6 两版各抽 400 条**做对照,
  **匹配 0 条、不匹配 400 条**(1.20.4 例:`fns.class` 期望 `0be75f7e…`、实际 `55120f89…`;1.20.6 例:`ggf.class`
  期望 `1c312056…`、实际 `3736f2a3…`)。1.20.6 那条线是**能跑通的**,所以 `.md5` 显然不是"原始 class 文件的哈希"
  (更像是 OptiFine 自己对基类做归一化之后的校验值,或者在 `Patcher.process` 这条路上根本不生效)。也就是说
  `OptifinePipeline` 喂给 `optifine.Patcher` 的基类**没有证据有问题**,我先前那句"这份基类在磁盘上没有对应物"
  只是误读。
- 另外把"OptiFine 是拿哪一套补丁打我们的输入"也确认了:jar 里 `patch/notch/` 与 `patch/srg/` **两套都在**
  (1.20.4 的 `AbstractClientPlayer` 就是 `patch/notch/fsg.class.xdelta` 5431 字节 + `patch/srg/…` 7997 字节各一份),
  而我们喂进去的是**原版混淆 jar**,所以走的是 notch 那一套 —— 这一套在 `optifine.Patcher.process` 里和 OptiFine
  自己的安装器用的是同一份代码、同样的两个入参。
- 因此结论收窄成一句:**载荷这一份 `AbstractClientPlayer` 就是 OptiFine 那份补丁的产物**(不是我们映射错的),
  而它在 1.20.4 运行时上不可定义。修法只能在我们这一侧(见下)。

### 五、边界

- 本轮**实测通过**的是 1.20.6(判据四项里三项与记录逐字一致,`[OptiFine]` 行数 +9 已如实写明);**1.20.4 未通过**,
  SRG 改名做完之后停在 `AbstractClientPlayer overrides final method Entity.getY()` —— 并且已用脱离 loader 的
  `jshell` 测试证明这份载荷的那个类在 1.20.4 运行时上按原样不可定义;1.20.1 / 1.20.2 这一轮**没有跑**。
- 1.21.x 线本轮在另一份记录里推进(1.21 – 1.21.8 六条线实测、1.21 的资源重载阻塞仍未解),26.x 线这一轮没碰。
- 为了能重跑,rig 被改动的地方(全部在 rig 内、不在仓库里):`add-line.ps1` 增加了 `-ModLauncher`(1.20.x 用
  `-Pmodlauncher` 而不是 `-Pmountpoint`)、`-TargetJavaVersion`(1.20.6 必须 `21`,否则 Gradle 报
  "No matching variant of net.neoforged:neoform:1.20.6-… compatible with Java 17")、安装器坐标的 `-InstallerArtifact`
  (1.20.1 是 `forge`)、"Gradle 构建成功才算产物"的检查(一次失败的构建会留下同名旧 jar,用它启动等于测错对象),
  以及**没有** SRG 表的线不再硬塞 `-SrgTableFile`;另新增 `get-optifine.ps1`(OptiFine 的下载要先用 adloadx 页
  换取一次性 token,再打 `downloadx`)。
- **没有发布任何东西**;已发布的 jar 没有重建。

