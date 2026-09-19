# 设计与里程碑(1.21.x 线)

## 目标

让 OptiFine 在 **NeoForge** 上工作,做法与 OptiFabric 在 Fabric Loader 上一样:不去重新实现 OptiFine,而是

1. 用 OptiFine 自带的补丁器把它自己的补丁打进原版客户端;
2. 重建补丁类里被搬走的 lambda;
3. 按目标版本的命名空间把结果对齐;
4. 把打过补丁的 Minecraft 类交给 NeoForge 的类转换流程,让它们顶替原版类。

OptiFabric 在 Fabric 侧走的是 `GameTransformer.patchedClasses`。NeoForge 侧对应的位置还没有最终确定,这是本线第一个要解决的问题(见下)。

本线要覆盖 **1.21、1.21.1、1.21.3、1.21.4、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10、1.21.11** 十个 MC 版本。十个版本分属十条 NeoForge 线,所以里程碑的每一条都要逐版本判据,不能由一个版本的结果外推。

## 本线的额外差异

| 方面 | 1.21.x 线的情况 |
|---|---|
| NeoForge 供给 | 十版分属十条线(`21.0` – `21.11`);其中 **`21.6`、`21.7`、`21.9` 只有 beta 构建**(最新 `21.6.20-beta` / `21.7.25-beta` / `21.9.16-beta`),这三版只能在 beta 版 NeoForge 上测 |
| Java | 全线 **21**,CI 一套即可 |
| mod 元数据文件 | 预期全线是 `META-INF/neoforge.mods.toml`;具体字段要求要逐版本核对 |
| 运行期命名空间 | 预期已是官方(Mojang)名,不再是 SRG;**确切切换点待确认**(它大约落在 1.20.5/1.21 前后,而本线起点正在附近) |
| OptiFine 供给 | 1.21.2 与 1.21.5 完全没有 OptiFine;1.21、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10 六版只有 preview |
| 上游缺陷 | **1.21.6 / 1.21.7 启用光影包必崩**,来自 OptiFine 预览构建自身(缺一个前置的 `setParentTexture` 关联),七个构建行为一致 |
| OptiFine 系列号 | `J6` 横跨 1.21.6 – 1.21.8,`J7` 横跨 1.21.9 – 1.21.10,同号系列的构建能否互相替代未验证 |

## 与 Fabric 线的关键差异

| 方面 | OptiFabric(Fabric) | 本项目(NeoForge) |
|---|---|---|
| 补丁时机 | Fabric Loader 的 `GameTransformer`,在 Mixin 之前 | ModLauncher / NeoForge 的转换流程,顺序需要核实 |
| OptiFine 自身 | 纯字节码补丁 + 自己的类,没有 loader 集成 | **自带 Forge 时代的 loader 集成**(`optifine.OptiFineTransformationService`) |
| 元数据 | 不涉及 | OptiFine 的 jar 里是 `META-INF/mods.toml`,NeoForge 期望自己的 `neoforge.mods.toml` |
| 命名空间 | official → intermediary | 这条线预期是官方(Mojang)名,不需要重映射;若某个版本仍在 SRG 一侧,则要按版本分支 |
| 第三方补丁 | 只有 Fabric API 的 mixin | NeoForge 自己也会改原版类,补丁需要合并 |
| NeoForge 供给 | 不涉及 | 1.21.6 / 1.21.7 / 1.21.9 只有 beta 构建,可测性本身受限 |

## 两条可选路线

**路线 A —— 让 OptiFine 自己的 ModLauncher 服务跑起来。**
OptiFine 的 `optifine.OptiFineTransformationService` 只依赖 `cpw.mods.modlauncher.*`(`ITransformationService`、`ITransformer<ClassNode>`、`SecureJar`),不引用 `net.minecraftforge.*`。理论上只要让 NeoForge 发现并加载这个服务、并把它的元数据修好,补丁流程就能原样工作。代价是:顺序、投票(`castVote`)、与 NeoForge 自身补丁的合并都不在我们手里。

**路线 B —— 自己跑补丁器,自己交出补丁类(像 OptiFabric)。**
在 `preLaunch` 阶段调用 `optifine.Patcher` 打补丁、重建 lambda、对齐命名空间,然后把结果交给 NeoForge 的转换 API。可控性最高,代价是工作量大,而且要先弄清 NeoForge 允不允许整类顶替。**这条线比 1.20.x 轻松一点**:运行期名预期就是官方名,少了 SRG 对齐这一步 —— 前提是命名空间的待确认项按预期落定。

骨架阶段两条都留着:**先用最小代价验证路线 A 能不能成立**(它是"能不能跑"的问题),同时按路线 B 的形态组织代码(补丁器调用、缓存、fixer 框架都放在 `core` 里,不依赖具体挂载点)。

## 里程碑

| # | 内容 | 完成判据 |
|---|---|---|
| M0 | 骨架:三条分支、版本矩阵、构建配置、文档 | 本提交(文档部分)与随后的构建配置提交 |
| M1 | 路线 A 可行性:修好 OptiFine jar 的元数据,让 NeoForge 认它 | **逐版本**能启动到标题界面,日志里能看到 OptiFine 的转换服务被加载;先做 1.21.1 与 1.21.11 这两个有正式版 OptiFine 的版本 |
| M2 | 补丁管线:调用 `optifine.Patcher`,建立缓存(`<游戏目录>/.optifine/<OptiFine 版本>/`) | 首次启动完成补丁,二次启动走缓存 |
| M3 | 补丁类注入 + fixer 框架:补回被搬走的方法、处理与 NeoForge 自身补丁的重叠 | 进世界不崩,方块/物品/区块渲染正常 |
| M4 | 完整兼容:光影包、抗锯齿、连接纹理;第三方模组(尤其依赖 NeoForge 渲染钩子的) | 与 OptiFabric 在 Fabric 上的验收口径对齐;**1.21.6 / 1.21.7 的验收口径要分开写**:这两版只能按"不启用光影包"判据,光影缺陷属于 OptiFine 侧,先挂起 |
| M5 | 发布:版本脚本、发布说明、CurseForge / Modrinth 元数据 | 能一条命令出包并发布,十个产物各自打包;只有 beta NeoForge 的三版要在发布说明里写明 |

每条线按同样的里程碑推进,但各自独立验收。本线的 M1 建议从 1.21.1 与 1.21.11 入手:这两版有 OptiFine 的正式版,先把管线跑通,再往只有 preview 的版本上铺。

## 上游缺陷与它的处理边界

1.21.6 与 1.21.7 的七个 OptiFine 构建在启用光影包时必崩(空指针,`multiTex` 为 null,崩在 `net.optifine.shaders.ShadersTex.initDynamicTextureNS`),根因是这些预览构建注入的调用**缺少前置的 `setParentTexture` 关联**。这是 OptiFine 补丁负载自身的问题,与加载器无关,所以:

- 本项目**不把它当成 NeoForge 适配的失败**,M4 上这两版按"不启用光影包"验收;
- 本项目也**不承诺**去修它:若要修,等于在本项目里改写 OptiFine 的补丁结果(属于 fixer 的职责范围),要先评估值不值得;
- 更省事的路线是等 OptiFine 出新构建;新构建出现前,这两版的状态在文档里保持"已知缺陷"而不是"不支持"。

## 提交纪律

- 分支互相独立:`1.20.x`、`1.21.x`、`26.x` 各自有自己的 `README`、矩阵、构建配置和文档,不做跨分支的合并,也不跨线复制版本号。
- 线内也要按版本立据:十个版本的结论不能互相顶替,写文档时要说清是哪一个版本;`J6` / `J7` 这种同号系列尤其容易混。
- 文档与实测口径要一致:没在真实游戏里验证过的东西写成计划,不写成结论。
- OptiFine 的 jar 不进仓库,也不随产物分发。

## 2026-09-19/20:1.21.9 这条线的侦察结果(本轮实测)

在**本机重建的 rig** 上做了两件事的确认,结论是"加载器这一半已经能造,缺的是另外两半":

1. **加载器构建可以过**:在本分支上执行

   .\gradlew jar -Pmc=1.21.9 -Pneoforge=21.9.16-beta -Pmountpoint=fml10

   得到 uild/libs/OptifiNeoforge-1.0.0+mc1.21.9.jar(129 356 字节)—— 也就是本文件上面那段注释说的
   "loader-side tools and the mod skeleton alone",与预期一致 ✔。
2. **缺的两半**:
   a. **FML 10 的 ClassProcessor 要打包进载荷 jar**:26.x 分支的 src/fml10 只有两个类
      (OptifinePayloadClassProcessor / OptifinePayloadLocator),按注释它们要由 rig **编译进载荷 jar**
      (带着 OptiFine 的类),而不是编译进加载器 jar —— rig 目前没有这一步。
   b. **1.21.9+ 没有 ModLauncher**,而 rig 的 launch.ps1 是围绕 ModLauncher 写的(module path、-p、
      --launchTarget、ignoreList 等)—— 要跑这三条线,得给 rig 加一条 FML 10 的启动路径。

这两件事都**没有做**,所以 1.21.9 / 1.21.10 / 1.21.11 仍然是未实测的。
### 补充(同一轮的下一步):FML 10 的 ClassProcessor 确实能编出来(实测)

- **SPI 在哪里**:在 rig 的 libraries 里扫 515 个 jar,**只有一份**含有
  
et/neoforged/neoforgespi/transformation/ClassProcessor.class ——
  libraries\net\neoforged\fancymodloader\loader\10.0.14\loader-10.0.14.jar(随 NeoForge 21.9.16-beta 装进来的)。
- **编译配方(实测通过)**:26.x 那两份源码 + 上面这个 loader jar + libraries\net\neoforged\neoforgespi\**
  + log4j-api + rig 的 ASM jar ⇒ **2 个 class / 7470 字节的 jar** ✔。也就是说"把 ClassProcessor 编出来"这一步
  没有任何未知数。
- **仍然缺的**:① 按本文件上面的注释,这两个类要和 OptiFine 的类一起**打进载荷 jar**(rig 没有这一步);
  ② **1.21.9+ 的启动路径**(没有 ModLauncher,launch.ps1 那套 module path / --launchTarget 都不适用)。
  ②是这三条线唯一的大件,做完才能谈"实测"。
### 再补充:FML 10 的启动路径侦察(实测到这里,尚未打通)

装好 NeoForge **21.9.16-beta** 之后看了它自己的 profile,量到:

- mainClass 是 **
et.neoforged.fml.startup.Client**(不是 cpw.mods.bootstraplauncher.BootstrapLauncher),
  **没有 ModLauncher**,也**没有** --launchTarget / module path —— 只有三个 --fml.* 参数
  (
eoForgeVersion / mcVersion / 
eoFormVersion),其余按父 profile(1.21.9.json)的常规游戏参数给。
- 按这条路径手工拼 classpath 起了一次**无 mod 的对照**:
  进程**能起来并活着超过 90 秒**,也写出了 logs/latest.log ✔ —— 但 FML 自己的后台扫描报
  java.lang.IllegalStateException: zip file closed(BackgroundScanHandler → CompositeJarContents.visitContent
  → Scanner.scan),即"扫描某个 jar 时它已经被关掉"。也就是说**手工拼的 classpath 不足以让 FML 10 接管这些 jar**,
  官方启动器显然是按另一种方式把 jar 交给它的。
- 顺带澄清一个假警报:按 profile 解析 classpath 时会看到 30 个"缺失"的 jar,但那**全是别的平台的 natives**
  (linux / macos ✗),rig 的 etch-libraries.ps1 本来就按规则跳过它们,Windows 上不需要。

**结论**:1.21.9+ 的三条线要能实测,还差"按 FML 10 期望的方式准备并启动"这件事 —— 本轮没打通,
所以 1.21.9 / 1.21.10 / 1.21.11 仍然是**未实测**。
### FML 10 侦察的第三批实测:它其实走得很远,然后在 0.8 秒内自己关掉

把启动参数补全(--gameDir/--assetsDir/--assetIndex/--username 等,只留 libraries 在 classpath 上,不再把 client jar
塞进去)之后,FML 10 的日志显示:

- Starting FancyModLoader version 10.0.14 (CLIENT in PROD) ✔
- Loading ImmediateWindowProvider fmlearlywindow + **GL info: AMD Radeon RX 7800 XT GL version 3.3.0 Core**
  —— 也就是说**它真的开了早期窗口并拿到了 GL 信息** ✔
- Mod List: Minecraft 1.21.9 (minecraft) / NeoForge 21.9.16-beta (neoforge) ✔
- Building game content classloader: minecraft (composite(jar(client-1.21.9-...-srg.jar))) ... ✔
- **紧接着一行就是 Closing FML Loader** —— 从启动到关闭只有约 **0.8 秒**,而且之前**没有任何 ERROR**;
  那条 An error occurred scanning file ...client-1.21.9-...-srg.jar(zip file closed)是**关闭之后**才出现的,
  也就是**结果而不是原因**。

顺带否掉一个我先前的猜测:"client jar 也在 classpath 上"导致冲突 —— 去掉之后(只留 libraries)**现象完全一样** ✗。

**下一步**(记录在这里,便于接着做):用 -Xlog:exceptions=trace 把主线程那个"安静退出"的原因抓出来
(这个手法在本会话里已经用成功过一次:1.20.4 的原始异常就是这么挖出来的),或者对照 NeoForge 官方启动器
在 profile 之外还做了什么。
### 重大一步:FML 10 的启动路径**打通了**(无 mod 对照已进到标题界面路径)

上一节记的"0.8 秒安静退出"找到原因了 —— 是**我漏了 profile 里的 JVM 参数**,不是 FML 或 OptiFine 的问题:

- 父 profile(1.21.9.json)的 rguments.jvm 里有四个 **natives 相关属性**:
  -Djava.library.path、-Djna.tmpdir、-Dorg.lwjgl.system.SharedLibraryExtractPath、-Dio.netty.native.workdir
  (都指向 rig 的 
atives),另有 -Xss1M 与 -Dminecraft.launcher.brand/version;
- 子 profile(NeoForge)额外给 --add-opens java.base/java.lang.invoke=ALL-UNNAMED 与
  --add-exports jdk.naming.dns/com.sun.jndi.dns=java.naming。

把这些**全部**给上之后(其余同前一节:只把 profile 的 libraries 放进 -cp、-DlibraryDirectory 指向 rig 的
libraries、mainClass 用 
et.neoforged.fml.startup.Client、游戏参数用父 profile 那套),
**无 mod 对照跑起来了**:

`
[Render thread/INFO] [net.minecraft.client.Minecraft/]: Setting user: Dev
[Render thread/INFO] [net.minecraft.client.sounds.SoundEngine/SOUNDS]: Sound engine started
`

进程活着(70 秒后由我主动结束)、日志 12 010 字节 ✔。也就是说 **1.21.9 这条线的启动方式不再是未知数了** ✔
—— 这是 1.21.9 / 1.21.10 / 1.21.11 三条线此前最大的拦路石。

**注意边界**:这是**无 mod 对照**(没有 OptiFine、没有本项目的载荷),所以它既不等于"这三条线通过",也不改变
那三条线"未实测"的状态。还差的两件仍是:① 把 26.x 的 src/fml10(两个类,已实测可编译)按注释**打进载荷 jar**;
② 在 rig 里把上面这套参数固化成一条 **FML 10 的启动路径**(launch.ps1 目前只懂 ModLauncher)。
### rig 现在有了 FML 10 的启动路径(实测:无 mod 对照 VERDICT: STARTED)

新增 optifineoforge-test\launch-fml10.ps1(ModLauncher 那套留在 launch.ps1)。它做四件事:

1. 顺着 inheritsFrom 把 profile 链读出来(NeoForge profile → 原版 profile),按"父先子后、后者覆盖"合并 libraries,
   用它们拼 -cp ——**不把 client jar 放进去**(实测:放进去与否现象一样,FML 自己会用 -DlibraryDirectory
   加 --fml.neoFormVersion 找到游戏 jar);
2. 展开两边 rguments.jvm 与 rguments.game 里的占位符,并且**跳过 rule 条目**
   (实测:第一个版本照搬了 -XstartOnFirstThread 这条 macOS 专用规则,JVM 直接拒绝启动:
   Unrecognized option: -XstartOnFirstThread);
3. 用 
et.neoforged.fml.startup.Client + 常规游戏参数启动,支持 -Mods(拷进 mods/)、-Fresh、-Seconds;
4. 打印与 launch.ps1 **同样形状**的 VERDICT 块(Setting user / 声音引擎 / 新崩溃报告 / stderr 字节 /
   [OptiFine] 行数),便于两条路径的结果直接对照。

实测(1.21.9 / NeoForge 21.9.16-beta,无 mod):

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 0  (latest.log, not doubled)
`

也就是说 **1.21.9+ 的"启动"这一半做完了**。三条线还差的最后一件事是把 26.x 的 src/fml10(已实测可编译)
与 OptiFine 的类一起**做成 FML 10 会发现的那个载荷 jar**,然后用这个脚本去跑。
### 载荷 jar 到底要装什么(从 src/fml10 两个类的说明里读出来的,附出处)

下一步"打包 fml10 载荷"的配方,现在不是猜的了 —— 两个类自己的文档写清了 FML 10 的发现机制:

- OptifinePayloadClassProcessor **通过 META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor
  注册**(FMLLoader.createClassProcessorSet → ServiceLoaderUtil.loadServices),它做的事是把**已经打好补丁的游戏类
  覆盖到游戏自己那份上**;而那些成品类按约定放在**同一个 jar 的 srg/ 下**(rig 事先把 patch/srg/** 应用到原版归档
  的结果,也就是本仓库离线管线 optifine-patched.jar 的布局)。
- OptifinePayloadLocator 之所以存在,是因为
  
et.neoforged.fml.loading.EarlyServiceDiscovery.SERVICES 正好只有
  {IModFileCandidateLocator, IModFileReader, IDependencyLocator, GraphicsBootstrapper, ImmediateWindowProvider} ——
  **ClassProcessor 不在其中**。所以"只声明 ClassProcessor 的 mods/ jar"不会被预加载、处理器永远不会被看到;
  那段注释还记了实测现象:那种 jar 跑到了标题界面,却**零条 [OptiFine]、日志里连处理器都没有**。
  因此这个 jar **还要**声明一个 IModFileCandidateLocator(服务文件
  META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator),并且要靠自己的元数据
  被普通的 mods/ 扫描发现。

**所以 1.21.9 的载荷 jar = 离线管线产出的 srg/** 成品类 + 那两个 fml10 类 + 两个服务文件 + META-INF/neoforge.mods.toml。**
(本轮只把这份配方记下来;**组装脚本还没写**。)
### 第一次真正跑 1.21.9 载荷:机制全部按设计工作,游戏在装完类之后安静退出

本轮做成并实测了三件事:

1. **载荷 jar 组装成功**(按上一节那份配方,临时脚本内联):work\1.21.9\optifine-patched.jar 里的
   **1233 个 srg/** 成品类** + 两个 fml10 类 + 两个服务文件 + META-INF/neoforge.mods.toml
   ⇒ jars-1.21.9\optifine-payload-fml10.jar(**2 999 556 字节**)。
2. **FML 10 确实把它当载荷吃下去了**(launch-fml10.ps1 的日志):
   - mods/optifine-payload-fml10.jar 被发现 ✔
   - OptifiNeoforge: early service jar recognised; the payload jar itself is disco...(locator 起作用 ✔)
   - OptifiNeoforge: OptifinePayloadClassProcessor constructed (FML 10 mount point) ✔
   - OptiFine payload: 517 finished game classes ✔,随后一条条
     OptiFine payload: installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far] …(到 16 条时日志中断)
3. **然后仍然是安静退出**:Closing FML Loader → Clearing ModLoader,**没有 ERROR、没有异常、stderr 0 字节**,
   Setting user 没出现。注意:**无 mod 的对照是能跑到 Setting user 的**(第 56 轮),所以问题出在装进去的类上,
   而不是启动方式。

**下一步**(明确):给这次启动加 -Xlog:exceptions=trace(本会话已两次用这招挖出"看不见"的原因),看主线程在
"装了十几二十个类"之后到底抛了什么 —— 或者先只放**少数几个类**的载荷做二分。