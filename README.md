# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上的做法相同:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时由本模组运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

本分支是 **1.20.x 线**,覆盖 **Minecraft 1.20.1、1.20.2、1.20.4、1.20.6**(OptiFine 在这条线上出过构建的全部四个版本)。1.21.x 线(1.21 – 1.21.11)与 26.x 线(Minecraft 26.1.2)在各自的分支上独立开发,三条线的 jar 不能互相替代。

> **状态:四个版本全部实机验证,`1.0.0` 已发布(能用本分支的 Gradle 构建产出 jar 的那几条)。** 判据是**一次真实启动**(rig 的 `VERDICT: STARTED` + `Setting user` + 本次运行没有崩溃报告 + stderr 与记录一致),不是"能编译";逐条数字在 `docs/MATRIX.md`(项目的运行日志)。
>
> 两条如实写下的边界:**1.20.2 与 1.20.4 带已知的 Reflector 缺陷**(OptiFine 自己的 `Reflector` 表每次启动写 4 条 `NoClassDefFoundError` 到 stderr,异常被 OptiFine 吞掉,不阻断启动;1.20.1 与 1.20.6 没有这条);**1.20.4 的运行要求**是 `config/fml.toml` 里 `earlyWindowProvider = "none"`(否则 FML 的 early window 与 OptiFine 换装的渲染类同帧重入,`EXITED (15s)`)。

## 支持的版本

| Minecraft | NeoForge | 产物 | OptiFine 正式版 | OptiFine 最新 preview | Java | 状态(实测) |
|---|---|---|---|---|---|---|
| 1.20.1 | `net.neoforged:forge:1.20.1-47.1.106` | `OptifiNeoforge-1.0.0+mc1.20.1.jar` | `OptiFine_1.20.1_HD_U_I6.jar` | `preview_OptiFine_1.20.1_HD_U_I6_pre6.jar` | 17 | 已验证 · **未发布**(那一代的 NeoForge 坐标是旧的 `net.neoforged:forge`,Gradle 插件解析不到;`[OptiFine]` 157 行、stderr 27 字节、无崩溃报告) |
| 1.20.2 | `20.2.88` | `OptifiNeoforge-1.0.0+mc1.20.2.jar` | 无 | `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar` | 17 | 已验证 · **未发布**(实测本分支的 Gradle 构建解析不到 `20.2.88` 的 moddev 变体;`[OptiFine]` 239 行、stderr 14 625 字节、无崩溃报告) |
| 1.20.4 | `20.4.251` | `OptifiNeoforge-1.0.0+mc1.20.4.jar` | `OptiFine_1.20.4_HD_U_I7.jar` | `preview_OptiFine_1.20.4_HD_U_I8_pre4.jar` | 17 | 已验证 · 已发布(241 行、stderr 14 481 字节、无崩溃报告) |
| 1.20.6 | `20.6.141` | `OptifiNeoforge-1.0.0+mc1.20.6.jar` | 无 | `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar` | 21 | 已验证 · 已发布(222 行、stderr 0 字节、无崩溃报告;这一版起 FML 拒绝原版 OptiFine jar,必须用本项目的重打包) |

两列 OptiFine 都只表示"该构建存在",不代表可用;这里也不表示正式版比 preview 更适合移植。

- mod id `optifineoforge`,仅客户端。
- **一个 jar 只对应一个 MC 版本**:这条线跨了四个版本,NeoForge 坐标、Java 版本与运行期命名空间在每个版本上都不同,不能混用,也不能拿别的线的 jar 顶替。
- Java 在这条线上**不统一**:1.20.1 / 1.20.2 / 1.20.4 是 **17**,1.20.6 起是 **21**。
- NeoForge 坐标也跨了一个时代:1.20.1 是 `net.neoforged:forge:1.20.1-47.1.106`,1.20.2 起才是独立的 `net.neoforged:neoforge`(`20.x.y`)。
- **1.20.2 的 OptiFine 只有一个 preview**(`I7_pre1`);**1.20.3 与 1.20.5 没有任何 OptiFine 构建**,因此不在支持范围内。
- 四个版本合计 29 个 OptiFine 构建,逐条列在 `docs/VERSIONS.md`。
- 这条线**内部**还有两处分界(元数据文件名、运行期命名空间),确切位置尚未确认,见 `docs/VERSIONS.md` 的待确认一节。

## 安装

发布的是**加载器侧**(jar 里没有任何 OptiFine 的类)。要得到能直接放进 `mods/` 的成品,按本项目的 rig 流水线
对**你自己下载的、与该 MC 版本严格一致**的 OptiFine jar 做重打包、SRG→官方名重写、打补丁与成员回填
(1.20.6 起还必须换掉 FML 拒绝的那份元数据),然后把产物放进这个版本自己的 `mods/` 目录,用对应的 **NeoForge**
版本启动;不要用启动器注入 OptiFine 的版本(那会与本模组重复)。1.20.4 上还要把 `config/fml.toml` 的
`earlyWindowProvider` 设成 `none`。上面每一条验收数字都是这样量出来的。

## 构建

需要 **JDK 17**(1.20.1 / 1.20.2 / 1.20.4)或 **JDK 21**(1.20.6),按目标版本选。仓库根目录就是 Gradle 项目,
目标版本用 `-Pmc` 切换,并且**必须**同时给出该版本的 NeoForge 与 ModLauncher 代次(`-Pneoforge` /
`-Pmodlauncher`),这样"没验证过的配对"不会看起来像支持:

```powershell
.\gradlew build                                                                          # 默认目标:1.20.4 / 20.4.251 / ModLauncher 10
.\gradlew build -Pmc=1.20.6 -Pneoforge=20.6.141 -Pmodlauncher=11 -Ptarget_java_version=21 # 1.20.6
```

产物为 `build/libs/OptifiNeoforge-<版本>+mc<MC 版本>.jar`,例如 `OptifiNeoforge-1.0.0+mc1.20.6.jar`。
**1.20.1 与 1.20.2 不在这个构建里**:1.20.1 那一代是旧的 `net.neoforged:forge` 坐标,1.20.2(`20.2.88`)
在 Maven 上没有 ModDevGradle 需要的 `neoforge-moddev-bundle` 变体 —— 两条都是实测到的构建工具限制,
它们仍由 rig 编译并用 rig 的流水线启动(实测记录见 `docs/MATRIX.md`)。

## 工作原理(计划)

OptiFine 的 Forge 侧入口是一个 ModLauncher 服务:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 指向 `optifine.OptiFineTransformationService`,它再通过 `optifine.OptiFineTransformer`(实现 ModLauncher 的 `ITransformer<ClassNode>`)把补丁类插进加载流程。NeoForge 同样跑在 ModLauncher 上,所以这条路可以复用,但需要处理四件事:

1. **元数据**:OptiFine 的 jar 里是 Forge 时代的 `META-INF/mods.toml`。这条线早期的 NeoForge(到 1.20.4)本身就是 Forge 时代的产物,那份元数据**可能**直接可用;1.20.6 及之后预期要换成 NeoForge 自己的 `META-INF/neoforge.mods.toml`,但确切切换点没有实测;
2. **命名空间**:1.20.x 时代 Forge/NeoForge 的运行期名预期是 **SRG**,而 OptiFine 的补丁按它发布时的命名空间存放,错配会整段失效;
3. **补丁重叠**:NeoForge 自己也会改原版类,两边的改动需要按顺序合并,而不是互相顶掉;
4. **线内的版本分叉**:1.20.1 走 `net.neoforged:forge` 坐标,1.20.6 起要 JDK 21,四个版本的构建与元数据要分别配置,不能指望一份配置覆盖整条线。

设计细节与里程碑见 `docs/PLAN.md`。

## 已知限制

- **发布的是加载器侧,不是"装进 `mods/` 就能用"的成品**:Release 附的 jar 里没有 OptiFine 的类,成品要按上面的流水线用你自己的 OptiFine jar 合成。
- **1.20.3 与 1.20.5 没有 OptiFine 构建**,这两版不在支持范围内;1.20.2 只有一个 preview(`I7_pre1`),它已经实测通过(见上表),代价是那 4 条 Reflector `NoClassDefFoundError`。
- 这条线跨了 Forge 时代的 NeoForge(1.20.1)与独立的 `20.x` 系列,元数据文件名与运行期命名空间都可能在这条线**内部**发生变化,所以四个版本要分别验证,不能由一个版本的结果外推。
- 若 1.20.6 一侧的运行期名已经是官方名而不是 SRG,那么本线内部就需要两套命名空间处理 —— 这一侧属于待确认事项。
- 与 OptiFabric 一样,**不包含、也不分发 OptiFine 本体**:OptiFine 的 jar 由用户自行获取,本项目只把它当作补丁来源。
- **这条线的实测记录在 `docs/MATRIX.md`**(2026-09-18 的回归:四个版本各自对着自己记录的基线逐项一致)。构建列表里"存在"不等于"可用",正式版也不代表比 preview 更适配 —— 这两句仍然成立,区别只是现在每个版本都有自己的启动数字了。

## 许可与致谢

- 本项目遵循 **MPL-2.0**(`LICENSE`),加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。
- **不包含、也不分发 OptiFine 本体**,OptiFine 版权归 sp614x 所有,请自行获取。
- 各版本的构建列表、NeoForge 坐标与下载命令见 `docs/VERSIONS.md`,版本号规则见 `docs/VERSIONING.md`,设计与里程碑见 `docs/PLAN.md`。
