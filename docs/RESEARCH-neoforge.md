# RESEARCH-neoforge.md — 让 OptiFine 在 NeoForge 上运行：逐版本调研

目标：与 OptiFabric-Reforged 在 Fabric 上的做法对应，把 OptiFine 带到 NeoForge。覆盖 15 个有 OptiFine 构建的 MC 版本：
1.20.1, 1.20.2, 1.20.4, 1.20.6, 1.21, 1.21.1, 1.21.3, 1.21.4, 1.21.6, 1.21.7, 1.21.8, 1.21.9, 1.21.10, 1.21.11, 26.1.2。
（1.20.3, 1.20.5, 1.21.2, 1.21.5, 26.1, 26.1.1, 26.2 无 OptiFine 构建，已用 optifine.net 下载页实证：<https://optifine.net/downloads>。）

**取证方式**（全部可复现）：从 `https://maven.neoforged.net/releases/net/neoforged/...` 下载 NeoForge universal/installer jar 与
`net.neoforged.fancymodloader:loader` 各线 jar，用 `tar.exe -tf/-xf` + `javap` + class 常量池字符串分析；从 `https://optifine.net/downloads` 下载 8 个 OptiFine jar 分析。
本文中 **CONFIRMED** = 有二进制或文档直接证据；**UNCONFIRMED** = 明确写出原因与下一步该查的 URL。

---

## 0. 逐版本矩阵

| MC | NeoForge 坐标 | FML loader 版本 | Java | 元数据文件 | 生产运行时命名 | MDG 支持 | 策略结论 |
|---|---|---|---|---|---|---|---|
| 1.20.1 | `net.neoforged:forge:1.20.1-47.1.106` | `47.2.2` | 17 | `META-INF/mods.toml` | SRG（Forge 47 代码库，`net.minecraftforge.*`） | `net.neoforged.moddev.legacyforge` 2.0.147 | **A 可用（原样）** |
| 1.20.2 | `net.neoforged:neoforge:20.2.93` | `1.0.16` | 17 | `META-INF/mods.toml` | Mojang official | ✗ → NeoGradle `7.0.116` | **A 可用（原样）** |
| 1.20.4 | `net.neoforged:neoforge:20.4.251` | `2.0.17` | 17 | `META-INF/mods.toml` | Mojang official | ✓ `net.neoforged.moddev` 2.0.147 | **A 可用（原样）** |
| 1.20.6 | `net.neoforged:neoforge:20.6.141` | `3.0.45` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | A 需重打包（去 `Installer`），否则 B |
| 1.21 | `net.neoforged:neoforge:21.0.167` | `4.0.23` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | A 需重打包，否则 B |
| 1.21.1 | `net.neoforged:neoforge:21.1.250` | `4.0.44` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | A 需重打包，否则 B |
| 1.21.3 | `net.neoforged:neoforge:21.3.97` | `5.0.8` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | A 需改元数据+重打包；建议 B |
| 1.21.4 | `net.neoforged:neoforge:21.4.157` | `6.0.18` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | A 需改元数据+重打包；建议 B |
| 1.21.6 | `net.neoforged:neoforge:21.6.20-beta` | `9.0.2` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | 同上，建议 B |
| 1.21.7 | `net.neoforged:neoforge:21.7.25-beta` | `9.0.14` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | 同上，建议 B |
| 1.21.8 | `net.neoforged:neoforge:21.8.54` | `9.0.18` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | 同上，建议 B |
| 1.21.9 | `net.neoforged:neoforge:21.9.16-beta` | `10.0.14` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | UNCONFIRMED（无 MDK 模板） | **只能 B**（ModLauncher 已移除） |
| 1.21.10 | `net.neoforged:neoforge:21.10.64` | `10.0.32` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | **只能 B** |
| 1.21.11 | `net.neoforged:neoforge:21.11.45` | `10.0.36` | 21 | `META-INF/neoforge.mods.toml` | Mojang official | ✓ 2.0.147 | **只能 B** |
| 26.1.2 | `net.neoforged:neoforge:26.1.2.109` | `11.0.15` | **25** | `META-INF/neoforge.mods.toml` | 未混淆（游戏自带 official 名） | ✓ 2.0.147（Gradle ≥ 9.1.0） | **A 需改元数据+去 `Installer`；OptiFine 自身已实现新 SPI** |

坐标的“最新版”已用 `maven-metadata.xml` 逐线核对（`20.2.93 / 20.4.251 / 20.6.141 / 21.0.167 / 21.1.250 / 21.3.97 / 21.4.157 / 21.6.20-beta / 21.7.25-beta / 21.8.54 / 21.9.16-beta / 21.10.64 / 21.11.45 / 26.1.2.109` 均为该线最新）。
FML 版本取自各 `neoforge-<v>.pom` 的 `net.neoforged.fancymodloader:loader:<v>` 依赖；1.20.1 取自 installer `version.json`。

已下载并实测的 OptiFine 构建（其余版本的 jar 名可由 <https://optifine.net/downloads> 的 `adloadx?f=...` 链接直接读出，本文未逐个下载，标 UNCONFIRMED）：
`OptiFine_1.20.1_HD_U_I6.jar`、`OptiFine_1.21.1_HD_U_J1.jar`、`OptiFine_1.21.3_HD_U_J2.jar`(仅见文件名)、`OptiFine_1.21.4_HD_U_J3.jar`、
`preview_OptiFine_1.21.8_HD_U_J6_pre16.jar`、`preview_OptiFine_1.21.9_HD_U_J7_pre2.jar`、`preview_OptiFine_1.21.10_HD_U_J7_pre11.jar`、
`OptiFine_1.21.11_HD_U_J9.jar`、`preview_OptiFine_26.1.2_HD_U_K1_pre2.jar`。
1.20.2 / 1.20.4 / 1.20.6 / 1.21 / 1.21.6 / 1.21.7 的具体构建号：**UNCONFIRMED**（未下载），下一步：抓 `https://optifine.net/downloads` 并用正则 `OptiFine_(1\.20\.2|1\.20\.4|1\.20\.6|1\.21|1\.21\.6|1\.21\.7)_[A-Za-z0-9_]+\.jar` 提取。

---

## 1. 运行时命名方案（production / development）

- **1.20.1：SRG。** `net.neoforged:forge:1.20.1-47.1.106` 实际就是 Forge 47.1.106 代码库：universal jar 内 `net/minecraftforge/**` 1239 个条目、`net/neoforged/**` **0** 个，installer `version.json` 的 `id = 1.20.1-forge-47.1.106`、`mainClass = cpw.mods.bootstraplauncher.BootstrapLauncher`。
  MDG 文档明确：*"Forge used SRG mappings as intermediary mappings in 1.20.1 and below ... you need to reobfuscate it to SRG mappings for it to work in production"*（<https://github.com/neoforged/ModDevGradle/blob/main/LEGACY.md>）。
- **1.20.2 起（至 1.21.11）：运行时 = Mojang official 名。** 切换点在 20.2：*"We are now using MojMaps everywhere."*（<https://neoforged.net/news/20.2release/>）；旁证：*"NeoForge has removed srg mappings as intermediary, such that both the development environment and production environment both use the same mappings."*（<https://docs.architectury.dev/loom/neoforge_migration/>）。
- **26.1.2：游戏本身不再混淆**，运行时就是 vanilla 自己的名字。*"Mojang has removed obfuscation in 26.1."*（<https://neoforged.net/news/26.1release/>）；*"26.1 is the first version of Minecraft to not be obfuscated."*（<https://fabricmc.net/2026/03/14/261.html>）。
  二进制旁证：OptiFine `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` 的 `patch/notch/**` 与 `patch/srg/**` **名字集合完全相同（566 vs 566，diff=0）**，且都是 `com/mojang/blaze3d/...` 真名；而 `OptiFine_1.21.11_HD_U_J9.jar` 的 `patch/notch/**` 仍是混淆名（`ama$a.class.xdelta`），与 `patch/srg/**` 有 1109 处不同。
- **开发环境**：1.20.1 = 用 official 编译 + reobf 到 SRG；1.20.2–1.21.11 = 开发/生产同名（NeoForm，`net.neoforged:neoform:<mc>-<timestamp>`，例如 `1.21-20240613.152323`）；26.1+ = vanilla 自带名（NeoForm 版本号改为 `<mc>-<build>`，如 `26.1.2-1`，见 `neoforge-26.1.2.109.pom`）。
  **Parchment 只提供参数名与 javadoc**，其 `p_` 前缀不是 SRG（<https://docs.neoforged.net/toolchain/docs/parchment/>）；26.1+ 起不再需要。
- **OptiFine 侧的对应证据**：所有 OptiFine jar 都同时带两套补丁树 `patch/notch/**`（混淆名）与 `patch/srg/**`（映射名），`optifine/OptiFineTransformer` 常量池里有 `PREFIX_SRG = "srg/"`、`PREFIX_PATCH_SRG = "patch/srg/"`、`SUFFIX_CLASS_XDELTA = ".class.xdelta"`。**即 OptiFine 按“运行时类名落在哪个命名空间”选择补丁树** —— 这正好解释了为什么 1.20.1（SRG）与 1.20.2+（official）都能被同一套机制覆盖。
  **UNCONFIRMED**：`patch/srg/**` 内部的 **成员级**名字究竟是 SRG（`m_12345_`）还是 official 方法名 —— 目录里只有类名，两者类名相同，无法从路径判定；下一步：解一个 `.xdelta`（`optifine.xdelta` 解码器）或对比 `patchMap` 配置。

## 2. 需要的 Java 版本

Class 文件主版本实测（`javap` / 手读 class header）：FML `1.0.16`→61、`2.0.17`→61、`3.0.45`→65、`4.0.44`→65、`6.0.18`→65、`9.0.18`→65、`10.0.36`→65、`11.0.15`→**69**；`neoforge-26.1.2.109-universal.jar` 的 `NeoForgeMod.class` 也是 69。

| Java | MC 版本 |
|---|---|
| 17 | 1.20.1, 1.20.2, 1.20.4 |
| 21 | 1.20.6, 1.21, 1.21.1, 1.21.3, 1.21.4, 1.21.6, 1.21.7, 1.21.8, 1.21.9, 1.21.10, 1.21.11 |
| 25 | 26.1 及所有 26.1.x（含 26.1.2） |

文档印证：<https://docs.neoforged.net/docs/gettingstarted/>（Java 25）、<https://neoforged.net/news/26.1release/>（"Minecraft is now using Java 25"）、MDG 的 `VersionCapabilitiesInternal.getJavaVersion`（边界 `24w14a`：17→21；`1.21.11`：21→25），MDG 会把 MC 版本对应的 toolchain 作为 convention 自动设置。
注意：`net.neoforged:forge:1.20.1-47.1.106` 本身也是 Java 17（MDK 明确 "Mojang ships Java 17 to end users in 1.20.1"）。26.1 起构建要求 **Gradle ≥ 9.1.0**（<https://neoforged.net/news/26.1release/>）。

## 3. 元数据文件：`mods.toml` → `neoforge.mods.toml`

**重命名发生在 FML 3.x = NeoForge 20.6（MC 1.20.6）**，证据是 NeoForge 自己的 jar + FML 常量池字符串：

| 版本 | NeoForge 自身元数据 | FML 要求的文件名（字符串证据） |
|---|---|---|
| 1.20.1 `forge-1.20.1-47.1.106-universal.jar` | `META-INF/mods.toml`，`loaderVersion="[24,]"` | `mods.toml`（"Mod file {} is missing mods.toml file"） |
| 20.2.93 / 20.4.251 | `META-INF/mods.toml`，`loaderVersion="[1,]"` | `mods.toml` |
| 20.6.141 及之后全部（含 21.x、26.1.2） | `META-INF/neoforge.mods.toml`，`loaderVersion="[3,]"` | `META-INF/neoforge.mods.toml`（"Mod file {} is missing {} file"） |

`modLoader` 仍是 `"javafml"`（`FMLJavaModLanguageProvider.NAME = "javafml"`）。**`loaderVersion` 不是装饰**：`LanguageProviderLoader.findLanguage()` 里
`versionSupportMatrix.testVersionSupportMatrix(modLoaderVersion, modLoader, "languageloader", (llid, range) -> range.containsVersion(mlw.version()))` 失败会抛
`ModLoadingIssue.error("fml.modloadingissue.language.missingversion", ...)`（<https://github.com/neoforged/FancyModLoader/blob/main/loader/src/main/java/net/neoforged/fml/loading/LanguageProviderLoader.java>）。
`javafml` 的版本 = FML 自己的版本（`BuiltInLanguageLoader.version()` 从 jar 版本/FMLVersion 取）。

**这对 OptiFine 是致命的**：14 个版本里 OptiFine 的 `META-INF/mods.toml` 内容完全一致（1.20.1 I6 与 26.1.2 K1_pre2 逐字节同款头部）：

```toml
modLoader="javafml"
loaderVersion="[14,)"
license="All rights reserved"
issueTrackerURL="https://github.com/sp614x/optifine/issues"
[[mods]]
modId="optifine"
```

- 1.20.1：javafml 版本 = `47.2.2` ≥ 14 → 通过。
- 20.2 / 20.4 / 20.6 / 1.21 / 1.21.1：FML 版本只有 1.0.16 / 2.0.17 / 3.0.45 / 4.0.23-4.0.44，**但**这些分支的 `VersionSupportMatrix` 里有兼容后门：
  `add("languageloader.javafml", "42");` → 因为 `[14,)` 含 42，检查**通过**（分支 `1.0/2.0/3.0/4.0` 的 `VersionSupportMatrix.java`）。
- **1.21.3 起（FML 分支 `5.0` 及以后）该后门被删除**（只剩 `mod.minecraft` / `mod.neoforge` 项，见分支 `5.0`–`11.0` 与 main），于是 `loaderVersion="[14,)"` 直接触发 `fml.modloadingissue.language.missingversion` 硬错误。
  结论：**从 1.21.3 开始，OptiFine 原样 jar 一定加载失败，必须改写元数据（改成 `[3,)` 等）**。

另外，**从 FML 3.0.45（1.20.6）起，NeoForge 会主动拒收 OptiFine jar**：`net.neoforged.fml.loading.moddiscovery.IncompatibleModReason` 的枚举常量
`OLDFORGE`(探针 `META-INF/mods.toml`)、`MINECRAFT_FORGE`、`LITELOADER`(`litemod.json`)、**`OPTIFINE`(探针 `optifine/Installer.class`)** 在 FML `3.0.45 / 4.0.44 / 6.0.18 / 9.0.18 / 10.0.36 / 11.0.15` 中全部存在（FML `1.0.16`/`2.0.17` 无此类）。
调用方是 `ModDiscoverer$DiscoveryPipeline`：命中后抛 `fml.modloadingissue.brokenfile.optifine` 并 "Skipping jar."。**我们下载的 8 个 OptiFine jar 全部含 `optifine/Installer.class`、`optifine/InstallerFrame.class`**，所以在 1.20.6+ 上原样放进 `mods/` 会被跳过（1.20.1/1.20.2/1.20.4 无此检查）。

## 4. Gradle 工具链（ModDevGradle）

- 当前应使用：`net.neoforged.moddev` **2.0.147**（Gradle Plugin Portal；不在 Maven Central），覆盖 **1.20.4 及以后**；插件 id 家族另有 `net.neoforged.moddev.repositories`（settings.gradle 用）。
- **1.20.1 必须用 `net.neoforged.moddev.legacyforge` 2.0.x**（同一版本号）。主插件写死 `net.neoforged:neoforge:<version>`，所以 `1.20.1-47.1.106` 会去解析不存在的 `net.neoforged:neoforge:1.20.1-47.1.106`；legacy 插件的 Javadoc：*"Only NeoForge for Minecraft 1.20.1 is supported when using this plugin."*（<https://github.com/neoforged/ModDevGradle/blob/main/LEGACY.md>）
- **1.20.2 MDG 不可用**（无 `.module`/`-moddev-config.json`，无 `MDK-1.20.2-ModDevGradle` 模板）→ 用 **NeoGradle `net.neoforged.gradle.userdev` 7.0.116**（模板 `https://github.com/NeoForgeMDKs/MDK-1.20.2-NeoGradle`），需要在 `pluginManagement.repositories` 加 `maven { url = 'https://maven.neoforged.net/releases' }`。
- 最小 build 片段（1.21.1，取自官方 MDK，插件版本换为 2.0.147）：

```groovy
// settings.gradle
pluginManagement { repositories { gradlePluginPortal() } }

// build.gradle
plugins {
    id 'java-library'
    id 'net.neoforged.moddev' version '2.0.147'
}
java.toolchain.languageVersion = JavaLanguageVersion.of(21)
neoForge {
    version = '21.1.250'
    runs { client { client() }; server { server(); programArgument '--nogui' }; data { data() } }
    mods { optifine_neoforge { sourceSet(sourceSets.main) } }
}
```

- 1.20.1（legacy）片段：`plugins { id 'net.neoforged.moddev.legacyforge' version '2.0.147' }` + `legacyForge { neoForgeVersion = '1.20.1-47.1.106'; runs { client { client() } }; mods { ... } }`；legacy 插件会为 `jar` 任务自动配置 SRG reobf（产物在 `build/devlibs` 之外需上传 `reobfJar` 输出）。
- 多项目（一版本一子项目 + 共享 `core`）的坑：
  1. **一个 Gradle project 只能启用一次 modding、只能有一个 NeoForge/NeoForm 版本**（`BREAKING_CHANGES.md`；`ModDevArtifactsWorkflow.create` 抛 `"You cannot enable modding in the same project twice."`）。`neoForge.version` 是 per-project 属性，没有 build 级版本。
  2. 想要“一个 project 多版本”的 PR #35（SourceSet based configuration）**已关闭未合并** → 官方认可的形状就是“一子项目一版本”。
  3. 共享 `core` 只能用 MDG 的 vanilla-mode（`neoForge { neoFormVersion = '1.21-20240613.152323' }`），因此它同样被钉在**某一个** MC 版本；要真正版本无关的 `core` 只能纯 Java（或每版本一个 core）。
  4. 子项目名里**不要带点**（如 `1.21.1-neoforge`）：MDG issue #353，生成的 IntelliJ run config 找不到 module，回退到整工程 classpath → `Found multiple copies of net/minecraft/server/MinecraftServer.class`；Gradle 任务本身正常。
  5. 15 个子项目建议开 `disableRecompilation`（2.0.124+ 显式、2.0.136+ 在 `CI=true` 时自动）以省掉反复反编译。
  6. 仓库管理：只要在某个 `build.gradle` 里自己声明任何 repository，该 project 的集中仓库管理即失效。

## 5. 关键问题：mod 如何在 NeoForge 上替换 vanilla 类

### (a) FML 还会从 `mods/` 发现第三方 `ITransformationService` 吗？

**会，但只到 MC 1.21.8（FML 9.x）为止；从 21.9（FML 10.x）起整套 ModLauncher 被移除。** 证据：

| FML | `ModDirTransformerDiscoverer` | `META-INF/services/cpw.mods.modlauncher.api.ITransformationService`（FML 自己的） | `cpw/mods/modlauncher/**` 类 |
|---|---|---|---|
| 1.0.16 / 2.0.17 / 3.0.45 / 4.0.44 / 5.0.8 / 6.0.18 / 9.0.18 | 有 | 有 | 有（9.0.18 内联打包） |
| **10.0.36（21.11）** | **无** | **无** | **无** |
| **11.0.15（26.1.2）** | **无** | **无** | **无** |

- `net.neoforged.fml.loading.ModDirTransformerDiscoverer implements cpw.mods.modlauncher.serviceapi.ITransformerDiscoveryService`（FML 1.0.16 与 9.0.18 签名一致，方法 `candidates(Path)` / `earlyInitialization(String,String[])` / `allExcluded()`），配套 `TransformerDiscovererConstants.SERVICES` + `shouldLoadInServiceLayer(JarContents)`；FML 自己通过 `META-INF/services/cpw.mods.modlauncher.serviceapi.ITransformerDiscoveryService` 注册它。**这正是 Forge 的 `ModDirTransformerDiscoverer` 在 NeoForge 上的等价物，没有任何“限制 OptiFine”的额外关卡。**
- FML 10/11 的加载器 jar 里 `META-INF/services/` 只剩 `IModLanguageLoader`、`IDependencyLocator`、`IModFileReader`、`IGlobalPropertyService`；installer 也不再拉 `cpw.mods:modlauncher`，主类从 `cpw.mods.bootstraplauncher.BootstrapLauncher` 变成 **`net.neoforged.fml.startup.Client`**（对比 21.8.54 的 installer）。
- 官方 PR 说明：*"This is a rework of FML's transformation API. The new API removes/merges `ILaunchPluginService` and `ITransformationService` into a single library-level service, `ClassProcessor`."*（<https://github.com/neoforged/FancyModLoader/pull/358>，2025-10-03 合并；消费方 PR：<https://github.com/neoforged/NeoForge/pull/2655> "[1.21.9] [Breaking] Update for FML transformation changes and package reorganization"）。

### (b) NeoForge 自己的替代 API

`net.neoforged.neoforgespi.transformation`（FML 10.0.x 起出现，21.9+）：

```
ClassProcessor: ProcessorName name(); Set<ProcessorName> runsBefore(); Set<ProcessorName> runsAfter();
  Set<String> generatesPackages(); OrderingHint orderingHint();
  boolean handlesClass(ClassProcessor.SelectionContext);
  ClassProcessor.ComputeFlags processClass(ClassProcessor.TransformationContext);
  void afterProcessing(ClassProcessor.AfterProcessingContext); void link(ClassProcessor.LinkContext);
ClassProcessorProvider: void createProcessors(ClassProcessorProvider.Context, ClassProcessorProvider.Collector);
BytecodeProvider: byte[] getByteCode(String name) throws ClassNotFoundException;
ClassProcessorIds: neoforge / computing_frames / simple_processors_default / runtime_enum_extender /
  access_transformer / mixin / neoforge_dev_dist_cleaner
（另有 BaseSimpleProcessor / SimpleClassProcessor / SimpleMethodProcessor / SimpleFieldProcessor）
```

注册机制（`FMLLoader.createClassProcessorSet`，main 分支 <https://github.com/neoforged/FancyModLoader/blob/main/loader/src/main/java/net/neoforged/fml/loading/FMLLoader.java>）：

```java
return ClassProcessorSet.builder()
    .markMarker(ClassProcessorIds.SIMPLE_PROCESSORS_GROUP)
    .markMarker(ClassProcessorIds.COMPUTING_FRAMES)
    .addProcessors(ServiceLoaderUtil.loadServices(launchContext, ClassProcessor.class, builtInProcessors))
    .addProcessorProviders(ServiceLoaderUtil.loadServices(launchContext, ClassProcessorProvider.class))
    .build();
```

即 **mod 只要在 jar 里放 `META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor`（或 `...ClassProcessorProvider`）就会被 ServiceLoader 装载**。
早期/定位类服务（`IModFileCandidateLocator`、`IModFileReader`、`IDependencyLocator`、`GraphicsBootstrapper`、`ImmediateWindowProvider`）由 `net.neoforged.fml.loading.EarlyServiceDiscovery` 在 `mods/` 目录预扫描。
参考概述：<https://deepwiki.com/neoforged/FancyModLoader/7.1-asm-transformations>。
**极其重要**：OptiFine 的 26.1.2 预览版**自己已经实现了这套 API** —— `optifine/OptiFineClassProcessor extends optifine/OptiFineBaseTransformerService implements net.neoforged.neoforgespi.transformation.ClassProcessor, net.neoforged.neoforgespi.locating.IModFileCandidateLocator`，并在 jar 内声明 `META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` 与 `...locating.IModFileCandidateLocator`（均为 `optifine.OptiFineClassProcessor`）；同时保留旧的 `cpw.mods.modlauncher.api.ITransformationService` 服务文件与 `optifine/OptiFineTransformationService`（class major 65 = Java 21）。

### (c) 能否整体替换 vanilla 类？

- OptiFine 的做法本质就是**整类替换**：`optifine/OptiFineTransformer implements cpw.mods.modlauncher.api.ITransformer<org.objectweb.asm.tree.ClassNode>`，`transform(ClassNode, ITransformerVotingContext)` 内部用 `loadClass(InputStream)` 把 `patch/srg/<Name>.class.xdelta` 解出来的完整类返回；常量池里还有 `"Target.PRE_CLASS is available"/"not available"` 与 `getTargetPreClass`，说明它在 ModLauncher 支持时优先用 `TargetType.PRE_CLASS`（即在其它 transformer 之前拿到较原始的类）。
- 新 API 下同样可以：`processClass(TransformationContext)` 拿到 `org.objectweb.asm.tree.ClassNode`，可任意重写；OptiFine 26.1.2 的 `OptiFineClassProcessor` 暴露了 `public static void copyClassNode(ClassNode, ClassNode)` 与 `resetClassNode(ClassNode)`，`OptiFineBaseTransformer` 里保留了 `applyPatch(String, byte[], Pattern[], Map, IResourceProvider)`、`getHashMd5`、`".md5"` 与 `"/MD5 not matching, name: "` 等字符串。
- **约束（重要风险）**：OptiFine 的运行时补丁会**校验输入类的 MD5**（`patch/<ns>/<Name>.class.md5`，错误串 "MD5 not matching"）。而 NeoForge 生产环境里 Minecraft 类**已经是 NeoForge 打补丁后的版本**，MD5 很可能与 OptiFine 记录的 vanilla 字节不一致。旧版靠 `PRE_CLASS` 规避，新 API 里没有等价的 “pre-class” 阶段标识（`ClassProcessor` 只有 `handlesClass/processClass/link` 与名字排序）。
  **CONFIRMED**：MD5 校验代码存在。**UNCONFIRMED**：在 NeoForge 21.9+/26.1.2 上 MD5 是否真的会不匹配（需要实机跑一次 OptiFine 的 ClassProcessor 看日志）；下一步：用 26.1.2 + `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` 实测，或对比 `patch/srg/<Class>.class.md5` 与 NeoForge 生产 `minecraft-<v>-client.jar` 中同类字节。
- 与 NeoForge 自身补丁的关系：FML 的处理器按 `ProcessorName` 全序 + `runsBefore/runsAfter` 图排序，`neoforge:computing_frames` 是帧重算的标记点（PR #358 原文）；NeoForge 的补丁在构建期（NeoForm 合并/打补丁 jar）就已应用，因此运行期的“vanilla”其实是“NeoForge 版 vanilla”。

### (d) `--launchTarget` / userdev 目标是否影响上述机制

- ModLauncher 时代（≤1.21.8）的 launch target 只选择 `cpw.mods.modlauncher.api.ILaunchHandlerService` 实现类：FML 47.2.2 的 service 文件列出 `net.minecraftforge.fml.loading.targets.ForgeClientLaunchHandler / ForgeClientDevLaunchHandler / ForgeClientUserdevLaunchHandler / ...Gametest...`；FML 1.0.16–6.0.18 则是 `FMLClientLaunchHandler / FMLClientDevLaunchHandler / FMLClientUserdevLaunchHandler / Common*` 等（`net/neoforged/fml/loading/targets/**`），5.0+ 之后改名为 `NeoForge*LaunchHandler`。
- 它们负责 classpath/模块层解析，**不影响** transformation service 的发现：`ModDirTransformerDiscoverer` 扫 `mods/`，`ClasspathTransformerDiscoverer` 扫 classpath（开发环境用），两者都注册在 `ITransformerDiscoveryService` 下、在所有 target 中都生效。
- FML 10+ 不再有 launch target 概念（入口是 `net.neoforged.fml.startup.Client`），dev/prod 的差别体现在 discovery pipeline 上。
- **UNCONFIRMED**：`--launchTarget` 的**字符串值**（如 `forgeclient` / `fmluserdevclient`）未从二进制中提取；下一步：`javap -c net/neoforged/fml/loading/targets/*LaunchHandler.class` 看 `name()` 返回值，或看 MDG/NeoGradle 生成的 run 配置。

### (e) 证据索引（可复现的关键观测）

| 观测 | 命令/路径 | 结果 |
|---|---|---|
| FML 是否还有 ModLauncher 发现器 | `tar -tf loader-<v>.jar \| Select-String ModDirTransformerDiscoverer` | ≤`9.0.18` 有；`10.0.36`、`11.0.15` 无 |
| FML 是否声明 ModLauncher 服务 | `tar -tf loader-<v>.jar \| Select-String 'META-INF/services/cpw'` | ≤`9.0.18` 有 `ITransformationService`/`ITransformerDiscoveryService`；10/11 无 |
| 新 SPI 首次出现 | `tar -tf loader-<v>.jar \| Select-String neoforgespi/transformation` | `10.0.36`、`11.0.15` 有 `ClassProcessor*`；≤`9.0.18` 无 |
| 生产主类变化 | `installer/version.json` 的 `mainClass` | `21.8.54` = `cpw.mods.bootstraplauncher.BootstrapLauncher`；`21.11.45`/`26.1.2.109` = `net.neoforged.fml.startup.Client` |
| 元数据文件名 | `tar -tf neoforge-<v>-universal.jar \| Select-String 'META-INF/.*mods.toml'` | `20.2.93`/`20.4.251` = `mods.toml`；`20.6.141`+ = `neoforge.mods.toml` |
| OptiFine 拒收探针 | 反编译/字符串 `net/neoforged/fml/loading/moddiscovery/IncompatibleModReason.class` | `OPTIFINE` ← `optifine/Installer.class`，自 FML `3.0.45` 起存在 |
| javafml 版本后门 | `VersionSupportMatrix.java`（分支 `1.0`/`2.0`/`3.0`/`4.0` vs `5.0`+） | `add("languageloader.javafml","42")` 仅在 1.0–4.0 分支 |
| OptiFine 入口 | `tar -tf OptiFine_*.jar \| Select-String 'META-INF/services'` | ≤1.21.11：仅 `cpw.mods.modlauncher.api.ITransformationService`；26.1.2：额外有 `net.neoforged.neoforgespi.transformation.ClassProcessor` 与 `...locating.IModFileCandidateLocator` |
| OptiFine 补丁树 | `tar -tf OptiFine_*.jar \| Select-String '^patch/'` | 每版都有 `patch/notch/**` + `patch/srg/**`；26.1.2 两者名字集合完全相同（566/566，diff=0） |

关键源码链接：<https://github.com/neoforged/FancyModLoader/blob/main/loader/src/main/java/net/neoforged/fml/loading/FMLLoader.java>（`createClassProcessorSet`）、
<https://github.com/neoforged/FancyModLoader/blob/main/loader/src/main/java/net/neoforged/fml/loading/LanguageProviderLoader.java>（`loaderVersion` 校验）、
<https://github.com/neoforged/FancyModLoader/blob/main/loader/src/main/java/net/neoforged/fml/loading/moddiscovery/IncompatibleModReason.java>。

## 6. 逐版本策略结论（A = 让 OptiFine 自己的服务跑；B = 我们自己跑 `optifine.Patcher` 并把结果交给 NeoForge API）

- **1.20.1：A（原样可用）**。SRG 运行时、`mods.toml`、ModLauncher + `ModDirTransformerDiscoverer`、无 `IncompatibleModReason`、`loaderVersion="[14,)"` 通过（javafml = 47.2.2）。这是唯一一个“和 Forge 1.20.1 完全同构”的版本。
- **1.20.2 / 1.20.4：A 不行，必须先做 SRG→official 重映射**（已实测修正，见 `docs/MATRIX.md` 2026-09-15 末节）。
  运行时是 Mojang 官方名（`client-1.20.4-*-srg.jar` 里 `Item` 的成员是 `getId`/`builtInRegistryHolder`，无 `m_`），
  而 OptiFine 1.20.2/1.20.4 的载荷**直接引用 SRG 成员名**（`srg/net/optifine/Config.class` 常量池里 47 个
  `Methodref`/`Fieldref` 带 `m_`/`f_`，且不是字符串）。**先前这里写的“`patch/srg` 树仍匹配，因为类名一致”
  是错的**：类名确实一致（1.17 起都用官方类名），但成员名不一致,链接会在运行时失败。
  元数据那一侧仍然成立：`mods.toml` 被接受、`loaderVersion` 有 `javafml=42` 后门、无 OptiFine 拒收检查。
  另一处已确认的来源:NeoForm 1.20.4 自带映射里 srg 列与 obf 列相同、不含任何 `m_` 名,但
  **MCPConfig 发布过 1.20.4**(`de.oceanlabs.mcp:mcp_config:1.20.4`,`config/joined.tsrg` 里 srg 列是真的
  `m_*`/`f_*`),把它与 NeoForm 的 `-mappings-merged.txt`(obf→官方名)串联即可得到 SRG→official 成员表。
  实测还确认:OptiFine 引用的**类名是官方名**、只有成员名是 SRG,所以重映射只需要改成员名。
- **1.20.6 / 1.21 / 1.21.1：A 只差一步**。元数据通过（后门仍在），ModLauncher 仍在 → 只要**把 `optifine/Installer*.class` 从 jar 里删掉**（重打包）即可让原服务被发现；否则 NeoForge 会以 `brokenfile.optifine` 跳过整个 jar。B 也可行但没必要。
- **1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8：A 需要同时改元数据 + 重打包**（`loaderVersion` 必须从 `[14,)` 改成当前 FML 线可接受的区间，例如 `[3,)`/`[5,)`/`[9,)`，且 `mods.toml` 要改名为 `neoforge.mods.toml`）。改完 ModLauncher 路径仍成立。**若不想维护“改 jar”，就统一走 B。**
- **1.21.9 / 1.21.10 / 1.21.11：只能 B**。ModLauncher 在 FML 10.x 已被彻底移除，`ITransformationService` 无宿主；而这三版的 OptiFine（`1.21.9 J7_pre2` / `1.21.10 J7_pre11` / `1.21.11 J9`）**没有** `ClassProcessor` 实现（只有旧的 `cpw.mods.modlauncher.api.ITransformationService` + `OptiFineTransformer`）。我们必须自己写 `ClassProcessor`，内部复用 `optifine.Patcher.applyPatch(...)` / OptiFine 的 xdelta 数据。
- **26.1.2：A（改元数据 + 去 Installer 后）最省力**，因为 OptiFine K1_pre2 **自带** `OptiFineClassProcessor`（还兼任 `IModFileCandidateLocator`）。但必须处理两件事：(1) `IncompatibleModReason.OPTIFINE` 会在服务被发现之前把整个 jar 跳过；(2) 该 jar 的 `META-INF/mods.toml` 仍是 `loaderVersion="[14,)"`，而 FML 11.0.15 < 14 且后门已无 → `missingversion`。同时 vanilla 未混淆，`patch/notch` 与 `patch/srg` 等价，命名匹配问题消失。

## 7. 我们必须构建什么

1. **jar 重打包器（A 必需，B 也需要）**：从官方 OptiFine jar 生成 NeoForge 友好的 mod jar：
   - `META-INF/mods.toml` → `META-INF/neoforge.mods.toml`（1.20.6+），`loaderVersion` 按目标版本改写（`[1,)`/`[3,)`/`[5,)`/`[9,)`/`[10,)`/`[11,)` 中线的最低值）；
   - 移除 `optifine/Installer.class`、`optifine/InstallerFrame*.class`（绕过 `IncompatibleModReason.OPTIFINE`），保留其余资源与 `patch/**`；
   - 按版本决定是否删除 `META-INF/services/cpw.mods.modlauncher.api.ITransformationService`（ModLauncher 不存在的版本必须删，否则只会白报错）。
2. **共享 `core`（版本无关）**：`Patcher` 调用层 + `patch/**` 读取 + MD5 校验 + 失败诊断；不依赖任何 `net.minecraft.*`，纯 Java（不要用 MDG vanilla-mode，那会被钉死在一个 MC 版本）。
3. **每版本一个 adapter 子项目**（子项目名不要带点）：
   - ≤1.21.8 线：`implements cpw.mods.modlauncher.api.ITransformationService` + `ITransformer<ClassNode>` 的薄包装（可直接复用 OptiFine 的类，或用我们的类调用 `optifine.Patcher`）；
   - ≥1.21.9 线：`implements net.neoforged.neoforgespi.transformation.ClassProcessor`（可选同时实现 `ClassProcessorProvider`），用 `name()/runsBefore()/runsAfter()` 与 `neoforge:computing_frames`、`neoforge:mixin` 等排序，`processClass` 里整类替换。
4. **验证矩阵**：每个版本一个 run，最小断言是“Minecraft 启动且 `optifine` modId 已加载”；重点回归 1.21.9+ 的 MD5/帧重算（`ComputeFlags.COMPUTING_FRAMES`）与 mixin 顺序。
5. **构建入口**：MDG 2.0.147（1.20.4+）+ legacyforge（1.20.1）+ NeoGradle 7.0.116（1.20.2），root 只放 `settings.gradle`/版本清单，`gradle.properties` 每子项目自带。

## 8. 明确未确认项（gap list）

1. **26.1.2 上 OptiFine `ClassProcessor` 的 MD5 校验是否会失败**（影响 B/A 全部新版本）——需要实机运行；下一步：跑 `26.1.2.109` + `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` 并看日志，或对比 `patch/srg/<Class>.class.md5` 与 NeoForm 产物。
2. **`patch/srg/**` 内部成员级命名是 SRG 还是 official**（1.20.2–1.21.11）——下一步：解 xdelta 后 `javap`。
3. **`--launchTarget` 的字符串值**（`forgeclient` / `fmluserdevclient` 等）——下一步：`javap -c` targets 包，或看 MDG 生成的 run 配置。
4. **MDG 对 1.21.9 的官方支持状态**（无 MDK 模板，但 21.9.16-beta 的 Maven 目录有 `.module`/`-moddev-config.json`）→ 大概率可用；下一步：<https://github.com/neoforged/ModDevGradle/issues?q=1.21.9>。
5. **`IncompatibleModReason` 的报错文本**（`fml.modloadingissue.brokenfile.optifine` 的具体文案）——语言文件不在 loader jar 内；下一步：在 `earlydisplay`/NeoForge universal jar 或 GitHub 源码仓库里找 `fml.modloadingissue.brokenfile.*`。
6. **`IncompatibleFileReporting` 的取值语义**（拒收是 error 还是可降级为 warning）——下一步：`net/neoforged/neoforgespi/locating/IncompatibleFileReporting` 的调用点。
7. **1.21.6/1.21.7 的 FML 是 9.0.2/9.0.14（跳过 7.x/8.x）**，说明 FML 版本线不与 MC 小版本一一对应；本文按 pom 实证记录，未追查 7.x/8.x 对应哪个 MC。
8. **Mojang 官方“取消混淆”公告原文**（`https://www.minecraft.net/en-us/article/removing-obfuscation-in-java-edition`）未能抓取（minecraft.net 抓取失败、archive.org 被沙箱拦截）；26.1 未混淆结论由 NeoForge + Fabric 双方文档 + OptiFine patch 树二进制证据交叉确认。
