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
| 1.21.1 | `21.1.250` | `OptifiNeoforge-1.0.0+mc1.21.1.jar` | `OptiFine_1.21.1_HD_U_J1.jar` | `preview_OptiFine_1.21.1_HD_U_J1_pre15.jar` | 21 | 已验证 · 已发布(223 行、stderr 0 字节、无崩溃报告;本轮本机复现四项判据一致、232 行,见下) |
| 1.21.3 | `21.3.97` | `OptifiNeoforge-1.0.0+mc1.21.3.jar` | `OptiFine_1.21.3_HD_U_J2.jar` | `preview_OptiFine_1.21.3_HD_U_J2_pre12.jar` | 21 | 已验证 · 已发布(225 行、stderr 0 字节、无崩溃报告) |
| 1.21.4 | `21.4.149` | `OptifiNeoforge-1.0.0+mc1.21.4.jar` | `OptiFine_1.21.4_HD_U_J3.jar` | `preview_OptiFine_1.21.4_HD_U_J4_pre2.jar` | 21 | 已验证 · 已发布(232 行、stderr 0 字节、无崩溃报告) |
| 1.21.6 | `21.6.20-beta` | `OptifiNeoforge-1.0.0+mc1.21.6.jar` | 无 | `preview_OptiFine_1.21.6_HD_U_J6_pre3.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**) |
| 1.21.7 | `21.7.25-beta` | `OptifiNeoforge-1.0.0+mc1.21.7.jar` | 无 | `preview_OptiFine_1.21.7_HD_U_J6_pre7.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**) |
| 1.21.8 | `21.8.54` | `OptifiNeoforge-1.0.0+mc1.21.8.jar` | 无 | `preview_OptiFine_1.21.8_HD_U_J6_pre16.jar` | 21 | 已验证 · 已发布(337 行、stderr 0 字节、无崩溃报告;本轮本机复现四项判据一致、344 行,见下) |
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

**1.21.1:四项判据在本机复现通过(`[OptiFine]` 232 行,记录为 223)。** 同一条 rig 流程(离线换类 + 计划)在这条线上重跑,
量到三件事:

- **重定父类那条修正在这条线上也是必需的,而且是它救回来的**。第一次跑挂在 NeoForge 自己的 `AttachmentSync.onChunkSent`:
  `VerifyError: Type 'BlockEntity' is not assignable to 'AttachmentHolder'` —— 这正是加载器注释里记的那种失败。原因不是计划错,
  而是本轮的加载器 jar 一开始是从一份**旧的** Gradle 产物装出来的(那份产物早于上一轮的重定父类修正)。用当前代码重建后日志里出现
  `Reparent plan covers ...: ... equal, so that copy is not the runtime's and the plan is applied on its own measurement`,并通过 ——
  说明"两份父类字符串相同不代表交上来的就是运行时的副本"这一判断在 1.21.1 上同样成立。
- **接口计划在这条线上也生效**:13 个类被补回运行时的扩展接口(如 `BlockState` 的 `IBlockStateExtension`、`Font` 的
  `IFontExtension`)。它是通过的必要条件与否**没有单独测**:只测到它确实运行、且整条线通过。
- **这条线的 OptiFine 构建(J1)在空游戏目录上会自己崩**:`Options.loadOfOptions` 抛
  `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`,位置是 OptiFine 自己加的方法(1.21.4 / 1.21.8 两版
  在同样空的目录上不会)。放入一份有效的 `optionsof.txt` 后即通过。这是 OptiFine 侧的行为,不是加载器的判据,记在这里是为了
  下次复现时不必再查一遍。

本机实测:四项判据通过、新增崩溃报告 0、stderr 0 字节、`[OptiFine]` **232 行**、图集 `Created:` 14。同样**没有重新发布**。

**1.21.8:四项判据在本机复现通过(`[OptiFine]` 344 行,记录为 337)。** 本轮按**离线换类**路线
重建了这一版(`preview_OptiFine_1.21.8_HD_U_J6_pre16` + NeoForge `21.8.54`),第一次跑停在 `Setting user` 之后:模型重载抛
`ExceptionInInitializerError`(索引 7 越界,长度 7),没有图集、没有声音引擎。根因由新增的离线工具 `PayloadDrift` 定位:
**补丁负载是 OptiFine 为它自己的基线编出来的,与这条 NeoForge 线的运行时在结构上不一致**,而这类不一致成员级回填够不到。

| 类 | 冲突 | 现象 |
|---|---|---|
| `ModelDiscovery$ModelWrapper` | `SLOT_COUNT` 负载 7 / 运行时 8(NeoForge 加了第 8 个槽位 `KEY_ADDITIONAL_PROPERTIES`) | 该常量内联进 `slot(int)` 的 `Objects.checkIndex` 与 `fixedSlots` 的数组长度,回填计划写进去的 `KEY_ADDITIONAL_PROPERTIES = slot(7)` 在该类自己的 `<clinit>` 里越界 |
| 14 个类 | 负载缺运行时才有的 NeoForge 扩展接口(如 `UnbakedGeometry` 缺 `UnbakedGeometryExtension`) | 运行时的 `ModelWrapper` 调用 `UnbakedGeometry.bake(..., ContextMap)`,而该方法只由这个扩展接口声明 |

两处都不是"某个成员写错",而是**已内联的常量**和**不在被替换类里的接口**:前者改不了,后者加不上。所以本轮加了两条
由 `PayloadDrift` 离线实测生成的计划:

- `keep-runtime.txt`(`owner<TAB>*`):整类保留运行时的副本,并把该类的补丁条目从 OptiFine jar 里删掉。只做前者不够 ——
  实测 OptiFine 的转换服务注册在**前面**(顺序 `[mixin, OptiFine, fml, OptifiNeoforge]`),而它打出来的类是它自己的编译结果,
  于是"不装负载"留下的恰恰是 OptiFine 的那一份;补丁条目删掉之后,运行时的类才会被加载。
- `runtime-interfaces.txt`(`owner<TAB>interface`):把运行时自己的扩展接口补回去,对**所有**被转换的类生效,不只是被替换的那些
  (上面这张表的第二行就是没补的结果)。

同轮量到并修正的两处 rig 问题:① `MissingTargets --stub` 必须给足运行时 classpath(游戏 jar + NeoForge universal + 174 个库 jar)。
只给游戏 jar 时它报 126 个缺失、写出 804 行 stub 文件,而其中几乎全是库成员;给全之后只剩 16 个,而这 16 个里有
`BlockModelPart.layer()` —— 少了它模型烘焙在 `SingleVariant.<init>` 就崩;补出来的 payload 才是要交给加载器的那一个。
② 转换器的 `targets` 不能只取负载索引,否则不在负载里的类根本不会被它看到,接口计划与运行时 stub 会静默失效。

本机实测(本机 rig,`-Fresh`、`earlyWindowProvider=none`、200 秒):

| 判据 | 结果 |
|---|---|
| `Setting user` | 通过 |
| `Sound engine started` | 通过 |
| 本次运行新增崩溃报告 | **0** |
| stderr | **0 字节** |
| 图集 `Created:` | 13 |
| `[OptiFine]` 行数 | **344**(记录为 337,相差 7 行;口径是 `latest.log` 里 `[OptiFine]` 的出现次数) |

口径说明:rig 的 harness 打印的 `[OptiFine] lines` 是 stdout、stderr 与 `latest.log` 三个来源**合并后**的匹配数,同一批行
会被计两次,所以它显示的是上表这个数的两倍(本轮 1.21.8 显示 688、1.21.4 显示 464);表里所有版本记录的都是 `latest.log` 的
原始条数。688 与 337 曾经看起来像两倍关系,核实后不是:原始条数为 344,与 337 只差 7 行。

**没有回归**:本轮改的是 1.21.x 共用的加载器代码,所以用同一份代码重建了已验证的 1.21.4(`OptiFine_1.21.4_HD_U_J3` +
`21.4.149`,同样的 rig 命令)并复跑:**四项判据通过、新增崩溃报告 0、stderr 0 字节、`[OptiFine]` 232 行**,与表里记录的
232 行完全一致。这一版 1.21.8 **没有重新发布**,表里的"已发布"仍指原有产物。

那一轮 1.21.8 还截图确认了"确实有画面":窗口标题 `Minecraft NeoForge* 1.21.8`(标题在两版都一样,不是判据),
`PrintWindow` 抓到的帧均值 RGB 85,83,81、量化色桶 125、最常见颜色只占 15%;对照组(标题界面)是 79,80,76 / 99 桶,
而卡在加载遮罩时抓到的是均值 237,78,87、单色占 84% 的红屏。1.21.8 这一帧与对照组同类,**不是**加载遮罩。
截图只说明"画面有内容",不等于标题界面逐像素正确 —— 这一项按"有画面"记录,不按"界面正确"记录。

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
