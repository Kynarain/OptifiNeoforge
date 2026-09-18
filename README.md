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
| 1.21.3 | `21.3.97` | `OptifiNeoforge-1.0.0+mc1.21.3.jar` | `OptiFine_1.21.3_HD_U_J2.jar` | `preview_OptiFine_1.21.3_HD_U_J2_pre12.jar` | 21 | 已验证 · 已发布(225 行、stderr 0 字节、无崩溃报告;本轮本机复现四项判据一致、225 行,见下) |
| 1.21.4 | `21.4.149` | `OptifiNeoforge-1.0.0+mc1.21.4.jar` | `OptiFine_1.21.4_HD_U_J3.jar` | `preview_OptiFine_1.21.4_HD_U_J4_pre2.jar` | 21 | 已验证 · 已发布(232 行、stderr 0 字节、无崩溃报告;本轮本机复现四项判据一致、232 行,见下) |
| 1.21.6 | `21.6.20-beta` | `OptifiNeoforge-1.0.0+mc1.21.6.jar` | 无 | `preview_OptiFine_1.21.6_HD_U_J6_pre3.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**;本轮本机复现四项判据一致、347 行,见下) |
| 1.21.7 | `21.7.25-beta` | `OptifiNeoforge-1.0.0+mc1.21.7.jar` | 无 | `preview_OptiFine_1.21.7_HD_U_J6_pre7.jar` | 21 | 已验证 · 已发布(340 行、stderr 0 字节、无崩溃报告;**不含启用光影包**;本轮本机复现四项判据一致、347 行,见下) |
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

### 本轮本机复现的六条线(2026-09-19)

同一条 rig 流程(离线换类 + 成员回填 + 重定父类 + 运行时 stub + 两条计划)在这六条线上各跑了一遍。判据是
`Setting user` + `Sound engine started` + 本次运行无新崩溃报告 + stderr;`[OptiFine]` 行数是 `latest.log` 里的原始条数:

| 线 | NeoForge | OptiFine | 四项判据 | `[OptiFine]` 行数 | README 记录 | 图集 `Created:` |
|---|---|---|---|---|---|---|
| 1.21.1 | `21.1.250` | `OptiFine_1.21.1_HD_U_J1` | 通过 | 232 | 223 | 14 |
| 1.21.3 | `21.3.97` | `OptiFine_1.21.3_HD_U_J2` | 通过 | **225** | 225(一致) | 14 |
| 1.21.4 | `21.4.149` | `OptiFine_1.21.4_HD_U_J3` | 通过 | **232** | 232(一致) | — |
| 1.21.6 | `21.6.20-beta` | `preview_OptiFine_1.21.6_HD_U_J6_pre3` | 通过 | 347 | 340 | 13 |
| 1.21.7 | `21.7.25-beta` | `preview_OptiFine_1.21.7_HD_U_J6_pre7` | 通过 | 347 | 340 | 13 |
| 1.21.8 | `21.8.54` | `preview_OptiFine_1.21.8_HD_U_J6_pre16` | 通过 | 344 | 337 | 13 |

1.21.4 那一行是用**本轮改动后的加载器代码**重建并复跑的,目的是确认共用的加载器代码没有被改坏 —— 行数与记录逐字一致。
六条线都**没有重新发布**,表里的"已发布"仍指原有产物。

**1.21.6 / 1.21.7 与 1.21.8 是同一个冲突,机制也同一套**:`PayloadDrift` 在这两版上量到的常量分歧都是
`ModelDiscovery$ModelWrapper.SLOT_COUNT` 负载 7 / 运行时 8(NeoForge 在 21.6 之前就加了第 8 个槽位),于是这两条线也用
同一份 `keep-runtime.txt`(整类保留 + 删掉 OptiFine jar 里的补丁条目)与各自的接口计划(13 条)通过 —— 这正是"计划由工具
实测产出"的价值:同一个冲突在三条线上自动重现,而不是每条线重新查一遍。1.21.7 上 `PayloadDrift` 还报出第二个常量分歧
`MappableRingBuffer.BUFFER_COUNT` 负载 5 / 运行时 3,它**故意不进** keep plan:3 是原版的值,5 是 OptiFine 自己的改动,
且该类的数组与取模都自洽、外部只调方法,留下它才是对的(1.21.8 上同一处也这么判)。

**一条 OptiFine 侧的坑,记下来免得下次再查**:1.21.1 与 1.21.3 这两个构建(J1 / J2)在**没有 `optionsof.txt`** 的游戏目录上会自己崩 ——
`Options.loadOfOptions` 抛 `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`,堆栈整个落在 OptiFine 自己加的方法里,
`Minecraft.<init>` 就停住,之后的错误界面又会在 `Font.ellipsize` 上二次崩,现场只剩两次崩溃报告。放入一份有效的
`optionsof.txt`(这两条线用的是从 1.21.4 目录拿来的同一份,1826 字节)后即通过。1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 的游戏目录
里本来就有这个文件,所以它们的 `-Fresh` 复跑没有暴露这一点。这是 OptiFine 的行为,不是加载器的判据,但如果要给别人复现
步骤,这一步要写进去。

**1.21 这一轮没有跑起来,而拦住它的是一条与既有记录不符的实测事实。** 这条线的补丁负载是 **SRG 成员名**(`f_21340_`、
`m_91087_`),而运行时是官方名(`DATA_MOB_FLAGS_ID`、`runTick`):实测 `Mob` 两侧的字段名逐条对不上,于是 `MissingTargets`
报出 3281 个"运行时没有的"引用、其中 2094 个只能留给加载器,成员回填计划也从其它线的 293 条涨到 **6251 条** —— 这一版
**不能**按离线换类直接用。`SrgMemberMap` 的注释把分界线记在 **1.20.6**("OptiFine switched the member names in its payload
from SRG to Mojang's official names at 1.20.6"),但这台机器上的 `preview_OptiFine_1.21_HD_U_J1_pre9` 仍然是 SRG 名,**这条
记录至少对 J1_pre9 不成立**。要让这条线能跑,需要把 `SrgRemap` 那一步接进 rig,而它要两张表:MCPConfig 的 `joined.tsrg`
(本机有)和 NeoForm 的 `-mappings-merged.txt`(本机没有,要用 installertools 的合并步骤生成)。这一步没做,所以 1.21 本轮的
状态是"未跑",不是"失败",也不是"通过"。

**试着把这一步补上时,又量到三件事**(都记在这里,因为下一步从这里开始):

1. `SrgRemap` 要在 **ASM 9.10.1** 上跑,rig 的工具 classpath 是 9.8,在 9.8 上它直接抛
   `NoSuchMethodError: 'void org.objectweb.asm.commons.Remapper.<init>(int)'` —— 源码注释里写了这件事(`super(Opcodes.ASM9)`
   正是为 9.10.1 写的,而 Gradle 侧用解析策略拿 9.10.1)。按这个版本另建一份 classpath 后它就跑起来了。
2. 第二张表可以从 Mojang 的 `minecraft_1.21_client_mappings.txt`(proguard 形状)转出来,转换脚本在 rig 里
   (`proguard-to-tsrg.ps1`)。第一版转出来联接失败:与 MCPConfig 的 `joined.tsrg` 做联接时 **54084 个成员在另一侧没有对应**,
   `SrgRemap` 于是只改写 4588 个方法名、17527 个字段名,**17620 个无法解析**。查出两个原因,都是转换脚本自己的:
   ① 两侧的描述符**不在同一个命名空间** —— `joined.tsrg` 写的是混淆类型(`a (Lakr;)Lgql;`),从 proguard 的 Java 类型直接
   转出来的是官方类型(`()Lnet/minecraft/resources/ResourceLocation;`),而 `SrgMemberMap` 的键包含描述符,于是永远对不上;
   ② 更隐蔽的一处:脚本里把形参表存进了 `$args`,那是 PowerShell 自己的自动变量,赋值被吞掉,于是表里**每个方法都变成无参**。
   两处修好后,联接的落空数从 54084 降到 **82**,改写变成 21803 个方法名 + 17527 个字段名,**无法解析的只剩 395**
   (253 个字段"表里没有"、113 个方法同因、29 个"成员换了形状")。
3. **要改写两个 jar,不是一个**。只改写补丁游戏类不够:OptiFine 自己的类(最终进 OptiFine jar 的那些)同样用 SRG 成员名,
   实测第一次启动死在 `srg/net/optifine/render/RenderEnv.<init>` 的
   `NoSuchFieldError: ... Direction does not have member field 'net.minecraft.core.Direction[] f_122346_'`。对
   **prepared OptiFine jar** 再跑一次 `SrgRemap` 后,那一步的改写是 3528 个方法名 + 1537 个字段名,无法解析 4 个。
4. 改写之后这条线的规模立刻回到正常:成员回填计划从 **6251 条 / 356 类**降到 **363 条 / 97 类**,"运行时没有的引用"从
   **3281** 降到 **62**(其中 46 条补进 payload、12 条留给加载器),`PayloadDrift` 的常量分歧为 0(不需要 keep plan)。

**1.21 现在的实测进度:到标题界面,但没过判据。** 用上面这条链跑起来后,`Setting user` 为 True、stderr 0 字节,但
`Sound engine started` 为 False,并在 `Minecraft.<init>` 里崩了一次:

```
IllegalAccessError: class net.neoforged.neoforge.client.NeoForgeRenderTypes$Internal tried to access
  protected field net.minecraft.client.renderer.RenderStateShard.RENDERTYPE_ENTITY_SOLID_SHADER
```

这是加载器注释里已经写过的那类"访问权限谁更宽"的问题,只是这次踩在字段上:规则取"负载与交上来的那份里更宽的",而这条线上
OptiFine 自己的转换器**先**替掉了 `RenderStateShard`(本行日志里能看到它被换掉),于是交上来的那份就是 OptiFine 的,
两边都是 `protected`,运行时的 AT 加宽结果看不到 —— 和 1.21.8 上"接口计划为什么必须存在"是同一个根因。

**于是加了第三条计划(access plan),它解决了一类问题,但没解决这一处。** 做法与另外两条一样,由 `PayloadDrift` 离线量:
`owner<TAB>name<TAB>desc<TAB>access`,取"负载的可见性比运行时的窄"的成员,加载器把它当作可见性下限(负载自己的 flags 更宽时
仍以负载为准)。这条线上它量到 **15 个类、81 个成员**,日志里确实生效了(`Widened 63 member(s) of RenderType`,
`ParticleEngine.register` 等),**但 `RenderStateShard` 不在名单里** —— 原因查清了:

- 运行时 jar 里 `RenderStateShard.RENDERTYPE_ENTITY_SOLID_SHADER` 就是 `protected static final`,所以离线比对看不到任何分歧;
- 真正把它变宽的是 **NeoForge 自己的 AT 配置**:`neoforge-21.0.167-universal.jar` 的 `META-INF/accesstransformer.cfg` 里有
  `public net.minecraft.client.renderer.RenderStateShard *`,由 FML 在加载期应用,而不是烘进那个 jar。

所以下一步要把 **AT 配置**当成第三个来源(`PayloadDrift` 现在只比两个 jar),或者让转换器真正拿到 AT 之后的运行时类。
这一轮没做,1.21 因此仍然是**未通过**:四个判据里 `Setting user` 与 stderr 为通过、`Sound engine started` 为不通过、本次运行有 1 份崩溃报告。

**另一处 rig 侧的坑**:`add-line.ps1` 每次都会用**原始** OptiFine jar 重新生成 prepared jar,所以对 prepared jar 做过的改写会被
它覆盖 —— 实测第一次重跑就退回到 `NoSuchFieldError: Direction.f_122346_`。这条线上正确的顺序是:prepare-line → 改写补丁游戏类 →
build-jars(生成 prepared jar)→ **再改写 prepared jar** → 启动。



### 复现一次启动需要什么(本轮量出来的 rig 要求)

上面每个数字都出自同一套流程,而流程自身这一轮也被量出几条硬性要求 —— 写在这里,免得下一个人或下一轮重新踩:

1. **加载器 jar 必须为当前这一版重新构建**。借别的线的产物、或用改动加载器代码之前的旧产物,都会表现成"加载器 bug":
   实测 1.21.1 用旧产物跑,挂在 NeoForge 自己的 `AttachmentSync.onChunkSent`,报
   `VerifyError: Type 'BlockEntity' is not assignable to 'AttachmentHolder'` —— 而那正是重定父类那条修正在修的东西。
2. **`MissingTargets --stub` 要给足运行时 classpath**(游戏 jar + NeoForge universal + 库 jar)。给少了会得到一份几乎全是
   库成员的 804 行 stub 文件,真正要补的十几个成员(例如 `BlockModelPart.layer()`)被埋在里面;而且交给加载器的 payload
   必须是**补齐之后**的那一个。这条命令行会超出 Windows 上限(220 多个 jar),要用 Java 的 `@argfile` 传。
3. **转换器的 `targets` 必须包含各计划里的类**,不能只取负载索引:不在负载里的类不会被它看到,接口计划与运行时 stub
   会静默失效。
4. **游戏目录里要有 `optionsof.txt`**(见上文,OptiFine J1/J2 的坑)。
5. 启动参数要 `earlyWindowProvider=none`,并且**不要最小化窗口**、`options.txt` 里 `enableVsync=false`;否则渲染线程会
   卡在 `glfwSwapBuffers`,读起来像"没起来"。
6. `[OptiFine]` 行数按 `latest.log` 原始条数记录;rig 打印的那个数是三个来源合并后的匹配数,正好是它的两倍。

第 1、2、3 条已经写进 rig 的 `add-line.ps1`:一条命令把"下载原版客户端 → 装 NeoForge(含安装器 IPv6 补种重试)→
跑离线管线 → 生成两条计划 → 补运行时 stub → 按目标构建加载器 jar → 组装两个 jar → 放 `optionsof.txt`"串起来,
剩下的只有 `launch.ps1` 与判读。

### 1.21.8 的根因:负载与运行时的结构性冲突

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

口径说明:rig 的 harness 打印的 `[OptiFine] lines` 是 stdout、stderr 与 `latest.log` 三个来源**合并后**的匹配数,同一批行
会被计两次,所以它显示的是上面那个数的两倍(1.21.8 显示 688、1.21.4 显示 464);文档里所有版本记录的都是 `latest.log` 的
原始条数。688 与 337 曾经看起来像两倍关系,核实后不是:原始条数为 344,与 337 只差 7 行。

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
