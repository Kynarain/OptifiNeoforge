# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上的做法相同:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时由本模组运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

本分支是 **1.21.x 线**,覆盖 **Minecraft 1.21 – 1.21.11**(OptiFine 出过构建的全部十个版本)。1.20.x 线(1.20.1 – 1.20.6)与 26.x 线(Minecraft 26.1.2)在各自的分支上独立开发,三条线的 jar 不能互相替代。

> **状态:十个版本全部实机验证,`1.0.0` 已发布。** 判据是**一次真实启动**(rig 的 `VERDICT: STARTED` +
> `Setting user` + 本次运行没有崩溃报告 + stderr 与记录一致),不是"能编译"。逐条的实测数字在
> [`docs/MATRIX.md`](../../blob/1.20.x/docs/MATRIX.md)(项目的运行日志)里,发布的是其中能用本分支的 Gradle
> 构建产出 jar 的那几条(见每个 Release 的正文)。
>
> 两条如实写下的边界:**1.21.6 / 1.21.7 的验收不含"启用光影包"**(这两版的 OptiFine 只有预览构建,启用光影包会
> 崩在 OptiFine 自己内部,见 `docs/PLAN.md`);**1.21 带已知的 Reflector 缺陷**(OptiFine 的 `J1_pre9` 构建每次
> 启动写 4 条 `NoClassDefFoundError` 到 stderr,异常被 OptiFine 吞掉,不阻断启动)。

## 支持的版本

| Minecraft | NeoForge | 产物 | OptiFine 正式版 | OptiFine 最新 preview | Java | 状态(实测) |
|---|---|---|---|---|---|---|
| 1.21 | `21.0.167` | `OptifiNeoforge-1.0.0+mc1.21.jar` | 无 | `preview_OptiFine_1.21_HD_U_J1_pre9.jar` | 21 | 已验证 · 已发布(`[OptiFine]` 252 行、stderr 14 141 字节、无崩溃报告) |
| 1.21.1 | `21.1.250` | `OptifiNeoforge-1.0.0+mc1.21.1.jar` | `OptiFine_1.21.1_HD_U_J1.jar` | `preview_OptiFine_1.21.1_HD_U_J1_pre15.jar` | 21 | 已验证 · 已发布(223 行、stderr 0 字节、无崩溃报告) |
| 1.21.3 | `21.3.97` | `OptifiNeoforge-1.0.0+mc1.21.3.jar` | `OptiFine_1.21.3_HD_U_J2.jar` | `preview_OptiFine_1.21.3_HD_U_J2_pre12.jar` | 21 | 已验证 · 已发布(225 行、stderr 0 字节、无崩溃报告) |
| 1.21.4 | `21.4.149` | `OptifiNeoforge-1.0.0+mc1.21.4.jar` | `OptiFine_1.21.4_HD_U_J3.jar` | `preview_OptiFine_1.21.4_HD_U_J4_pre2.jar` | 21 | 已验证 · 已发布(232 行、stderr 0 字节、无崩溃报告) |
| 1.21.6 | `21.6.20-beta` | `OptifiNeoforge-1.0.0+mc1.21.6.jar` | 无 | `preview_OptiFine_1.21.6_HD_U_J6_pre3.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**) |
| 1.21.7 | `21.7.25-beta` | `OptifiNeoforge-1.0.0+mc1.21.7.jar` | 无 | `preview_OptiFine_1.21.7_HD_U_J6_pre7.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**) |
| 1.21.8 | `21.8.54` | `OptifiNeoforge-1.0.0+mc1.21.8.jar` | 无 | `preview_OptiFine_1.21.8_HD_U_J6_pre16.jar` | 21 | 已验证 · 已发布(337 行、stderr 0 字节、无崩溃报告) |
| 1.21.9 | `21.9.16-beta` | `OptifiNeoforge-1.0.0+mc1.21.9.jar` | 无 | `preview_OptiFine_1.21.9_HD_U_J7_pre2.jar` | 21 | 已验证 · **未发布**(构建已通过:产物是加载器侧工具 + mod 骨架,不含挂载点,见下文) |
| 1.21.10 | `21.10.64` | `OptifiNeoforge-1.0.0+mc1.21.10.jar` | 无 | `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar` | 21 | 已验证 · **未发布**(同上) |
| 1.21.11 | `21.11.45` | `OptifiNeoforge-1.0.0+mc1.21.11.jar` | `OptiFine_1.21.11_HD_U_J9.jar` | `preview_OptiFine_1.21.11_HD_U_J9_pre4.jar` | 21 | 已验证 · **未发布**(同上;stderr 107 字节 = 它的无 mod 对照跑) |

`1.0.0` 的含义按 `docs/VERSIONING.md`:它由这一条线自己的实机启动记录支撑,不是"功能完备"的断言。
表中 NeoForge 一列是**这条线实际验证用的版本**(1.21.4 用的是 `21.4.149`,不是镜像里更新的 `21.4.157`)。

**1.21.9 / 1.21.10 / 1.21.11:构建阻塞已解除,但仍未发布。** 原来的原因写在"构建而不是验证"上,本轮复现了
它:这三版的 NeoForge 去掉了 ModLauncher,而本分支把实现 `cpw.mods.modlauncher.api.ITransformationService`
的那批类放在 `src/main`,于是 `:compileJava` 直接失败 —— 2026 在这台机器上复现 1.21.11(`21.11.45`),
`错误: 程序包cpw.mods.modlauncher.api不存在` 共 **100 个**,全部落在实现 ModLauncher 接口的那批类上。

现在这批类移到了 `src/ml11/java`,由 `-Pmountpoint` 决定编不编:`modlauncher`(1.21 – 1.21.8)编,
`fml10`(1.21.9 起)不编。三条线的构建因此都能通过,实测各一次:`1.21.9`(`21.9.16-beta`)、
`1.21.10`(`21.10.64`)、`1.21.11`(`21.11.45`),产物都在 `build/libs/`。

`fml10` 产出的 jar 里是**加载器侧工具 + mod 骨架**,没有挂载点:这三条线的挂载点是我们自己的
`ClassProcessor`(`26.x` 分支的 `src/fml10`,由 rig 编译**进载荷 jar**),而那份载荷 jar 含 OptiFine 的类、
按 `docs/PUBLISHING.md` 不分发 —— 这与 `26.x` 为 26.1.2 发布的产物是同一类东西。

**本节只改了构建,没有改任何实机结论**:这台机器上没有 rig,本轮**没有**重跑启动,
所以这三条的实机判据仍然只有 `docs/MATRIX.md` 里那一份。发布本身也仍是独立的一步(需要一条启动记录与
Release 正文),尚未做。

两列 OptiFine 都只表示"该构建存在",不代表可用;这里也不表示正式版比 preview 更适合移植。

- mod id `optifineoforge`,仅客户端,全线要求 **Java 21**。
- **一个 jar 只对应一个 MC 版本**:十个版本分属十条 NeoForge 线,元数据与命名空间要按版本各自处理,不能混用,也不能拿别的线的 jar 顶替。
- **三个版本的 NeoForge 只有 beta**:1.21.6(`21.6.20-beta`)、1.21.7(`21.7.25-beta`)、1.21.9(`21.9.16-beta`)这三条线在 `maven.neoforged.net` 上**没有任何非 beta 构建**,所以这三个产物只能用 beta 版 NeoForge 测试,用户也要在启动器里允许 beta 版本。
- **1.21.2 与 1.21.5 没有任何 OptiFine 构建**,不在支持范围内。
- OptiFine 只出到 preview 的版本有不少:1.21、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10 这六版的构建全是 preview。
- 十个版本合计 94 个 OptiFine 构建,逐条列在 `docs/VERSIONS.md`。

## 安装

发布的是**加载器侧**(jar 里没有任何 OptiFine 的类)。要得到能直接放进 `mods/` 的成品,按本项目的 rig 流水线
对**你自己下载的、与该 MC 版本严格一致**的 OptiFine jar 做重打包与打补丁(1.21.1 / 1.21.3 / 1.21.8 上还包含
离线换类与成员回填,1.21.4 走 OptiFine 自己的运行期补丁),然后把产物放进这个版本自己的 `mods/` 目录,
用对应的 **NeoForge** 版本启动(1.21.6 / 1.21.7 / 1.21.9 上要允许 beta 版本)。不要用启动器注入 OptiFine 的版本
(那会与本模组重复)。上面每一条验收数字都是这样量出来的。

## 构建

需要 **JDK 21**(整条线统一)。仓库根目录就是 Gradle 项目,目标版本用 `-Pmc` 切换(非默认目标必须同时给
`-Pneoforge` 与 `-Pmountpoint`,以免"没验证过的配对看起来像支持"):

```powershell
.\gradlew build                                                                   # 默认目标:1.21.4 / 21.4.149
.\gradlew build "-Pmc=1.21.8"  "-Pneoforge=21.8.54"  "-Pmountpoint=modlauncher"   # 这一代还带 ModLauncher
.\gradlew build "-Pmc=1.21.11" "-Pneoforge=21.11.45" "-Pmountpoint=fml10"         # 这一代已经没有 ModLauncher
```

**在 PowerShell 里 `-P...` 必须加引号**:不加时 `-Pmc=1.21.11` 会被拆开,报
`Task '.21.11' not found in root project`(Gradle 9.6.1 实测);`cmd.exe` 下不加引号也可以。

`-Pmountpoint` 决定编哪个挂载点源码根:1.21 – 1.21.8 的 NeoForge 还带 ModLauncher,编 `src/ml11/java`
(实现 `ITransformationService` 的转换服务与各 transformer);1.21.9 起 NeoForge 已经没有 ModLauncher、
`cpw.mods.modlauncher.api` 随之消失,那批类编不过,所以 `fml10` 不编任何挂载点根。

产物为 `build/libs/OptifiNeoforge-<版本>+mc<MC 版本>.jar`,例如 `OptifiNeoforge-1.0.0+mc1.21.8.jar`。
**十条线的目标现在都能构建,而且十条各实测构建过一次**;两种挂载点的产物内容不同:

| Minecraft | NeoForge | `-Pmountpoint` | jar 大小 | 条目 | `loader/**` |
|---|---|---|---|---|---|
| 1.21 | 21.0.167 | `modlauncher` | 161 233 字节 | 55 | 16 个类 |
| 1.21.1 | 21.1.250 | `modlauncher` | 161 234 字节 | 55 | 16 个类 |
| 1.21.3 | 21.3.97 | `modlauncher` | 161 235 字节 | 55 | 16 个类 |
| 1.21.4 | 21.4.149 | `modlauncher` | 161 235 字节 | 55 | 16 个类 |
| 1.21.6 | 21.6.20-beta | `modlauncher` | 161 238 字节 | 55 | 16 个类 |
| 1.21.7 | 21.7.25-beta | `modlauncher` | 161 239 字节 | 55 | 16 个类 |
| 1.21.8 | 21.8.54 | `modlauncher` | 161 235 字节 | 55 | 16 个类 |
| 1.21.9 | 21.9.16-beta | `fml10` | 109 769 字节 | 39 | **0 个类** |
| 1.21.10 | 21.10.64 | `fml10` | 109 765 字节 | 39 | **0 个类** |
| 1.21.11 | 21.11.45 | `fml10` | 109 765 字节 | 39 | **0 个类** |

十条的离线工具都是 32 个类。字节数的差异只是元数据里那几行字符串的长短,不是代码差异:`1.21.9` 比同组的
另外两条大 4 字节,正是 `21.9.16-beta` 比 `21.10.64` / `21.11.45` 长 4 个字符。

`fml10` 的 jar 只有加载器侧工具与 mod 骨架,因为这三条线的挂载点是我们自己的 `ClassProcessor`,由 rig 编译
**进载荷 jar**。十个 jar 里都**没有** OptiFine 的类,也都**不含** `META-INF/services/`,所以都不是"放进 `mods/`
就能用"的成品 —— 这一点与 `docs/PUBLISHING.md` 对已发布 jar 的说明一致。构建通过**不等于**能跑:这台机器上
没有 rig,本轮没有重跑实机启动,十条的实机判据仍然只有 `docs/MATRIX.md` 里那一份。

## 工作原理(计划)

OptiFine 的 Forge 侧入口是一个 ModLauncher 服务:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 指向 `optifine.OptiFineTransformationService`,它再通过 `optifine.OptiFineTransformer`(实现 ModLauncher 的 `ITransformer<ClassNode>`)把补丁类插进加载流程。NeoForge 同样跑在 ModLauncher 上,所以这条路可以复用,但需要处理三件事:

1. **元数据**:OptiFine 的 jar 里是 Forge 时代的 `META-INF/mods.toml`,NeoForge 需要它自己的那一份 `META-INF/neoforge.mods.toml`;
2. **命名空间**:这条线的运行期名预期已是官方(Mojang)名,不再是 SRG,可能不需要重映射;但切换点的确切位置**尚未确认**(它落在 1.20.5/1.21 前后,本线起点正在附近),错配会整段失效;
3. **补丁重叠**:NeoForge 自己也会改原版类,两边的改动需要按顺序合并,而不是互相顶掉。

设计细节与里程碑见 `docs/PLAN.md`。

## 已知限制

- **发布的是加载器侧,不是"装进 `mods/` 就能用"的成品**:Release 附的 jar 里没有 OptiFine 的类,成品要按上面的流水线用你自己的 OptiFine jar 合成。
- **1.21.2 与 1.21.5 没有 OptiFine 构建**,这两版不在支持范围内。
- **1.21.6 / 1.21.7 启用光影包会崩**,这是 OptiFine 侧自身的缺陷:这两版可以在不启用光影包时正常启动、正常渲染(标题界面无异常、无崩溃报告),但只要启用光影包,游戏就会在启动阶段崩:

  ```
  java.lang.NullPointerException: Cannot read field "norm" because "multiTex" is null
    at net.optifine.shaders.ShadersTex.initDynamicTextureNS(...)
  ```

  原因是这两版的 OptiFine 预览构建给纹理初始化插入的调用**缺少一个前置的 `setParentTexture` 关联**,而被调用的 `initDynamicTextureNS` 会直接解引用 `getMultiTexID()` 的结果。这两版可用的 OptiFine 构建共七个(1.21.6 三个 + 1.21.7 四个),行为一致,降级到更早的 preview 不能规避。**这是 OptiFine 补丁负载自身的问题,与本模组的加载器适配无关**;来源见下。
- 上面这条已经在**本线自己**的 1.21.6 / 1.21.7 上复现并确认过(验收因此不含启用光影包),不再是照搬姊妹项目的结论;1.21.7 的四个预览构建行为一致。另外 1.21 带一条已知的 Reflector 缺陷(OptiFine `J1_pre9` 每次启动往 stderr 写 4 条 `NoClassDefFoundError`,不影响启动)——两条都记在 `docs/MATRIX.md`。
- 这条线的 1.21.6 / 1.21.7 / 1.21.9 只能用 **beta 版 NeoForge**,beta 本身的变动会增加排查噪声。
- 与 OptiFabric 一样,**不包含、也不分发 OptiFine 本体**:OptiFine 的 jar 由用户自行获取,本项目只把它当作补丁来源。

## 许可与致谢

- 本项目遵循 **MPL-2.0**(`LICENSE`),加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。
- **不包含、也不分发 OptiFine 本体**,OptiFine 版权归 sp614x 所有,请自行获取。
- 各版本的构建列表、NeoForge 坐标与下载命令见 `docs/VERSIONS.md`,版本号规则见 `docs/VERSIONING.md`,设计与里程碑见 `docs/PLAN.md`。
