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
### 二分的结果:不是"某个类坏",而是"载荷一被装上 FML 就关掉自己"

- 用 -Xlog:exceptions=trace 抓:整份载荷跑完后日志**最后一条异常**仍是无关的 AWT/字体噪音(2.8 秒处),
  **没有任何异常伴随那次关闭** —— 无 mod 的对照也是同样的收尾,但它能继续走到 Setting user。
- 于是做二分:把载荷缩到**只含一个类**(srg/net/minecraft/util/Mth.class,jar 共 19 016 字节),
  OptiFine payload: 1 finished game classes → installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far]
  → **紧接着仍然是 Closing FML Loader**。
- 也就是说:**不是某个类把游戏弄坏**,而是"载荷一旦被装上,FML 就把自己关掉"。
  下一步要看的是处理器与 FML 10 交互的这一段(例如它在安装时是否动了 FML 正在扫描的那个 jar —— 之前出现过
  zip file closed 的抱怨,可能就是这条线索的另一面),而不是继续缩小类的范围。
### 定位到唯一一处:是 ClassProcessor 的安装动作,不是 jar / locator / 元数据 / 类本身

同一个载荷 jar,**只去掉那条 ClassProcessor 的服务文件**(其余——locator 服务、
eoforge.mods.toml、那个成品类——
完全一样),启动结果就变成:

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  [OptiFine] lines    : 0
`

也就是说:jar 本身没问题、locator 没问题、元数据没问题、类也没问题,**唯一让 FML 10 关闭自己的就是"处理器真的去装类"这一步**。
剩下要查的就只有 OptifinePayloadClassProcessor 的安装实现与 FML 10 扫描/持有 jar 的方式之间的相互作用
(它自己去读同一个 jar 的那段代码是最可疑的地方,之前那条 zip file closed 也指向这里)。
### 1.21.9 这一轮收口(下一步的实验已经指名)

已经站住的部分:

- **启动方式** ✔:launch-fml10.ps1(rig),无 mod 对照 VERDICT: STARTED;
- **载荷投递** ✔:载荷 jar 被 FML 10 发现,locator 服务、
eoforge.mods.toml、srg/** 成品类都按设计工作
  (OptiFine payload: 517 finished game classes → 逐条 installed ...);
- **唯一卡点** ✗:只要那条 ClassProcessor 服务文件在,处理器一开始装类,FML 10 就 Closing FML Loader
  (无异常、stderr 0;把服务文件去掉则一切正常跑 Setting user —— 这一步已经把范围钉死了)。

下一轮该做的实验(按代价排序):

1. **把服务文件留着,但让 	argets() 返回空集**:这样能区分"FML 10 不喜欢**有处理器注册**"和
   "FML 10 不喜欢**处理器真的装类**"这两件事 —— 本文件里 	argets() 的形状很直白(遍历 payload().keySet()
   造 Target),改一行就能试;
2. 若上一步证明是"装类"本身,RU 可疑处是 	ransform 里把成品 ClassNode 覆盖到 
ode 上的那段
   (FML 10 的扫描线程可能正在读同一批 jar),再逐层缩小;
3. 另一条互不排斥的路:不注册处理器,改用**事先把成品类覆盖进游戏 jar**(rig 层面做完),让这条线像 1.20.x/1.21.x
   那样"不改类也能跑",代价是失去了运行期换装。
### 一行实验的结论:FML 10 允许"注册处理器",不允许它"真的装类"

把 	argets() 改成返回空集(**处理器照旧注册,服务文件照旧在**,只是一个类都不声明),其余全部不变:

`
===== VERDICT: STARTED =====
Setting user        : True
Sound engine started: True
new crash reports   : 0
`

日志里 OptifinePayloadClassProcessor constructed (FML 10 mount point) 仍在 ✔,而 installed ... 一条都没有 ✔。
也就是说:

- **"有 ClassProcessor 注册"本身完全没问题**;
- 问题精确地落在**它声明并安装类**这一步 —— 即 	argets() 报出的那 517 个类,以及 	ransform 里
  copy(finished, node) 把成品覆盖回去的那段。

**下一次的一行实验**(把这个范围再切一半):保留 	argets() 照常报类,但让 	ransform 只记日志、**什么都不改**
(即 copy 不执行)——
- 若仍会关闭 ⇒ 问题在"声明了这些类"这一侧(FML 10 对处理器声明面的处理);
- 若正常跑起来 ⇒ 问题就在 copy(覆盖 ClassNode 的具体做法,FML 10 的扫描线程可能在读同一批结构)。
### second bisect:问题不在"声明类",而在"真的把成品覆盖上去"那一步

保留 	argets() 照常声明那个类(FML 会正常询问它),但让 	ransform **什么都不做**(取 payload() 的那行改成
直接置空,于是方法立刻返回):

`
===== VERDICT: STARTED =====
Setting user        : True
Sound engine started: True
new crash reports   : 0
`

⇒ **声明这些类没问题,copy 那步才是元凶**。两次一行实验合起来把范围钉到了唯一一处:
OptifinePayloadClassProcessor.transform → copy(finished, node)(把成品 ClassNode 的内容覆盖到 FML 交来的

ode 上)。

**下一次要试的具体假设**(按可能性排序):

1. **就地改 FML 交来的那个 ClassNode 可能不被 FML 10 接受** —— 改为构造一个新的 ClassNode 并把它作为结果
   交回去(如果 SimpleClassProcessor 的契约支持)或者至少不共享字段/方法列表;
2. copy 里那次"取更宽可见性"的合并或接口字段归一化,可能产出 FML 10 的校验不接受的东西(1.20.x/1.21.x 的加载器
   是 ModLauncher 的 NodeTransformer,契约不同);
3. 兜底方案(与第 2 轮记过的那条一致):不注册处理器,改为**事先把成品类直接覆盖进游戏 jar**,让这条线"运行期不换装"。
### third bisect:不是"合并逻辑",而是"替换这个动作本身"

把 copy(finished, node) 换成**最简单的、完全不合并**的赋值(superName / interfaces / fields / methods / access /
version / signature 直接取自成品),再跑:

`
OptiFine payload: installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far]
Closing FML Loader 27329d2a
`

**照样关闭**。三个一行实验连起来:

| 变体 | 结果 |
|---|---|
| 服务文件在、	argets() 空集 | STARTED |
| 	argets() 照常声明、	ransform 空操作 | STARTED |
| 	ransform 里做**最小**的类替换(不合并) | **Closing FML Loader** |

⇒ **问题不在我们的合并逻辑**,而在"**在 FML 10 的处理管线里替换掉它交来的这个类**"这件事本身
(很可能是 SimpleClassProcessor 的契约:处理器不该改 
ode,而应通过上下文的 API 声明替换;
具体的契约要以 FML 自己的源码为准,本轮没有去读)。

**因此走兜底方案**:不注册处理器,改为**在 rig 层面事先把成品类覆盖进游戏 jar**
(libraries\net\minecraft\client\1.21.9-…\client-1.21.9-…-srg.jar 的副本),
这样这条线"运行期不换装",与 1.20.x/1.21.x 的验收环境等价(那两个分支的换装是运行期的,但验收看的是游戏能不能起来)。
### 兜底方案的实测结果:能起来,但 OptiFine 是"哑"的

按兜底方案做了:把载荷里 1233 个 srg/** 成品类写进游戏 jar 的副本(用 libraries\net\minecraft\client\1.21.9-…\
client-1.21.9-…-srg.jar,原件留成 .rig-original),实测 **替换 515 个游戏类、追加 716 个 OptiFine 自身的类**
(结果 23 289 537 字节),然后不带任何 mod 启动:

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 0
`

也就是说:**游戏起来了,但日志里一个 optifine 字样都没有** —— OptiFine 的代码根本没有被激活。
原因不难理解:在 ModLauncher 那几条线上,是 **OptiFine 自己的 transformation service** 把它启动起来的;
这条 FML 10 的路径上没有任何东西去启动它,光把**打过补丁的游戏类**放进去并不等于 OptiFine 在跑。
所以兜底方案**不足以**当作"这条线通过"的依据 —— 它只证明"预覆盖不炸",不证明 OptiFine 生效。

**结论(下一步)**:还是要修处理器这一侧 —— 去读 FML 10 SimpleClassProcessor / SimpleTransformationContext
的契约(本轮没读),搞清楚"替换"应该怎么声明(而不是就地改 
ode);或者找一个在无 ModLauncher 情况下
启动 OptiFine 自身初始化的方式。
### 读 FML 10 的处理器契约(本轮读到的东西)

从 rig 的 libraries\net\neoforged\fancymodloader\loader\10.0.14\loader-10.0.14.jar 里 javap 出来:

- SimpleClassProcessor:bstract void transform(ClassNode, SimpleTransformationContext) +
  bstract Set<Target> targets();handlesClass 与 **processClass 都是 final** ⇒ 用这个基类时,
  **处理器没有地方声明"我用哪种重写方式"**。
- SimpleTransformationContext 只有 	ype() / empty() / initialSha256() —— **没有**"声明替换"之类的 API,
  所以就地改 
ode 确实是设计用法(前面那条"就地改是不是不被接受"的猜测到此可以划掉)。
- 但 ClassProcessor 接口本身有 **ComputeFlags processClass(TransformationContext)**,而
  ClassProcessor 的取值是:**NO_REWRITE / SIMPLE_REWRITE / COMPUTE_MAXS / COMPUTE_FRAMES**。

**这给出一个很具体的、可验证的假设**:我们继承 SimpleClassProcessor,而它把 processClass 定成 final ⇒
FML 用它内部固定的旗标写回;如果那个旗标弱于 COMPUTE_FRAMES,而我们**整方法替换**了字节码(帧会变),
写出来的类就是帧不一致的 —— 症状正好可以是"FML 在处理过程中直接放弃,而且什么都不打印"。
**下一步**:改成直接实现 ClassProcessor 接口,在 processClass 里返回 ComputeFlags.COMPUTE_FRAMES
(以及 handlesClass 用同一批目标名),再看那三条线是否能起来。
## 1.21.9 的这一轮:FML 10 上的"静默失败"被拆开了,而且 OptiFine 真的跑起来了

这一节把三条线(1.21.9 / 1.21.10 / 1.21.11)的 FML 10 挂载点从"起不来且没有一行错误"推到了"OptiFine 已经在跑"。
全部结论都是这一轮在这台机器上量出来的,写清楚哪一条推翻了前面的猜测。

### 先推翻一条:`ComputeFlags` 不是原因

上一轮把 `SimpleClassProcessor` 的 `processClass` 是 `final`、而 `ClassProcessor` 接口有
`ComputeFlags processClass(...)` 当成主要嫌疑。`javap -c` 直接读出来是假的:

```
public final ClassProcessor$ComputeFlags processClass(ClassProcessor$TransformationContext);
   0: aload_0
   1: aload_1
   2: invokevirtual  ClassProcessor$TransformationContext.node()Lorg/objectweb/asm/tree/ClassNode;
   5: aload_1
   6: invokevirtual  transform:(Lorg/objectweb/asm/tree/ClassNode;LSimpleTransformationContext;)V
   9: getstatic      ClassProcessor$ComputeFlags.COMPUTE_FRAMES
  12: areturn
```

它**无条件**返回 `COMPUTE_FRAMES`(没有分支,没有 `empty()` 判断)。所以"基类给的旗标太弱"是错的,
改成直接实现接口不会改变任何事。这条到此结束。

### "没有任何错误"的机制:FML 把异常送进了一个模态对话框

`net.neoforged.fml.startup.Client.main` 的字节码是:

```
 10: invokestatic Entrypoint.startup([Ljava/lang/String;ZLnet/neoforged/api/distmarker/Dist;Z)LFMLLoader;
 25: invokestatic Entrypoint.createMainMethodCallable(LFMLLoader;Ljava/lang/String;)Ljava/lang/invoke/MethodHandle;
 31: invokevirtual MethodHandle.invokeExact([Ljava/lang/String;)V
 46: invokevirtual FMLLoader.close()V          <- "Closing FML Loader" 就是这里
 ...
 76: astore_1 / 77: invokestatic FatalErrorReporting.reportFatalError(Throwable;)V / 81: System.exit(1)
```

而 `FatalErrorReporting.reportFatalError(String)` 是:

```
 0: ldc "java.awt.headless" / 2: ldc "false" / 4: System.setProperty   <- 强制非 headless
 8: GraphicsEnvironment.isHeadless() / 11: ifne 21
14: showErrorUsingSwing(String)      -> JOptionPane.showMessageDialog(...)   <- 模态,阻塞
21: ... TinyFileDialogs.tinyfd_messageBox(...)
51: System.exit(1)
```

**所以 FML 10 上启动期抛异常的表现是**:stderr 0 字节、没有 crash-report、日志最后一行是
`Closing FML Loader`、JVM 一直不退出(对话框在等人点确定)。这不是"FML 加载器坏了",
是一个没人看的窗口。为了确认不是猜的,把该 JVM 的顶层窗口枚举出来:

```
1508380|vis|SunAwtDialog|Fatal Error          <- 就是它
 2625878|vis|GLFW30|Minecraft: NeoForge Loading...
```

**给 rig 加了两件东西**(都在仓库外,`optifineoforge-test\`):

* `diagnostic\kynarain\cn\optifineoforge\rig\DiagnosticClient.java` —— 继承
  `net.neoforged.fml.startup.Entrypoint`(`startup` 与 `createMainMethodCallable` 都是 `protected static`,
  子类可用),跑**同一条** FML 管道,但把 throwable 打到 stderr,然后 `Runtime.halt(1)`。
  只改失败可见性,不改任何变换路径。
* `launch-fml10.ps1 -MainClass <fqcn> -ExtraClasspath <dir>` —— 把入口点换成上面这个类。
* `capture-hwnd.ps1` —— 按标题抓一个顶层窗口(PrintWindow,失败再 CopyFromScreen),留着备用;
  本轮抓到了那张图,但当前模型读不了图像,所以真正解决问题的是上面那个入口点。

### 那条"最小替换"探针为什么死的:**探针罐里没有 OptiFine 自己的类**

换上诊断入口点后,第一次运行 stderr 就有 2036 字节,原因一目了然:

```
java.lang.NoClassDefFoundError: Could not initialize class net.minecraft.util.Mth
	at com.mojang.blaze3d.buffers.Std140SizeCalculator.align(...)
Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.NoClassDefFoundError:
        net/optifine/util/MathUtils [in thread "main"]
	at net.minecraft.util.Mth.<clinit>(Mth.java:55)
```

OptiFine 编译出来的 `Mth` 的 `<clinit>` **调用 OptiFine 自己的 `net.optifine.util.MathUtils`**,
而那个探针罐里根本没有任何 `net/optifine/**`。于是类初始化失败 → 前面那三步二分法得到的结论
("在 FML 10 管道里替换一个类本身就会炸")**是错的**,作废:炸的是缺类,不是替换。

### 真正的坑:军规罐子(Early Service jar)的加载器看不见游戏类

把 OptiFine 自己的类**放进同一个 payload 罐子**再跑(`srg/net/optifine/**` 之外另加普通路径的
`net/optifine/**`),FML 的日志说得很清楚:

```
Found 1 early service jars (out of 1)
Loading FML Early Services:  - mods/optifine-payload-fml10-withown.jar
```

也就是说这个罐子被当成"早期服务罐",里面的类由一个普通 `URLClassLoader` 加载,而那个加载器
**一个游戏类都看不见**:

```
java.lang.NoClassDefFoundError: net/minecraft/world/level/chunk/ChunkAccess
	at FML Early Services//net.optifine.reflect.Reflector.<clinit>(Reflector.java:143)
Caused by: java.lang.ClassNotFoundException: net.minecraft.world.level.chunk.ChunkAccess
	at java.net.URLClassLoader.findClass(URLClassLoader.java:445)
```

(`Client` 里的那条旧注释记的 1.21.11 上的 `ValueOutput` 失败,根因就是这个,不是"某个加载器恰好没配好"。)

**修法:拆成两个 mod 文件**

1. `optifine-payload-fml10.jar` —— 还是早期服务罐:处理器两个类 + 两个 service 文件 +
   `META-INF/neoforge.mods.toml` + `srg/**`(要装到游戏类上的成品类,1233 个)。
2. `optifine-own-classes.jar` —— **普通** mod 文件(`neoforge.mods.toml`,modId `optifine`),
   装 OptiFine 自己的 `net/optifine/**`(716 个)、`assets/minecraft/**`、以及
   `net/minecraftforge/**` 桩类。

这一拆之后,`Reflector` 变成 `TRANSFORMER/optifine@1.0.0/net.optifine.reflect.Reflector`,
能正常解析游戏类,`net.minecraft.util.Mth.<clinit>` 也拿到了 `MathUtils`。

### 还差一层:Forge API 桩类必须在那第二个罐子里

拆开之后的第一个失败是帧计算阶段要去解析一个不存在的类:

```
Caused by: java.lang.RuntimeException: Cannot find class net/minecraftforge/common/extensions/IForgeLivingEntity
	at net.neoforged.fml.classloading.transformation.TransformerClassWriter.computeHierarchyFromFile(...:149)
	at org.objectweb.asm.Frame.merge(...)
	at ...ClassTransformer.transform(...:126)
	at TRANSFORMER/optifine@1.0.0/net.optifine.reflect.Reflector.<clinit>(Reflector.java:172)
```

把 rig 里已经准备好的 `work\1.21.9\stubs`(87 个 `net/minecraftforge/**` 桩类)加进
`optifine-own-classes.jar` 就好了。注意这与 1.20.1 那次踩的坑方向相反:那次把桩类塞进
**加载器罐**导致模块 `ResolutionException`,这里的正确位置是**普通 mod 文件**。

### 结果:1.21.9 上 OptiFine 已经在运行

```
===== VERDICT: FAILED =====
  Setting user        : True
  [OptiFine] lines    : 32
```

`latest.log` 里是完整的 OptiFine 自述:

```
[OptiFine] OptiFine_1.21.9_HD_U_J7_pre2
[OptiFine] Build: 20251002-002421
[OptiFine] LWJGL: 3.4.0 Win32 WGL Null EGL OSMesa VisualC DLL
[OptiFine] OpenGL: AMD Radeon RX 7800 XT, version 3.3.0 Core Profile Context 25.12.1.251128
[OptiFine] Maximum texture size: 16384x16384
[OptiFine] Checking for new version
```

也就是说:在这条线上 OptiFine 自己的代码真的被加载、被初始化、并且读到了显卡信息。
**这不是验收通过**(下面还有两个拦路的),但它把"FML 10 上 OptiFine 能不能活"这个问题答成了"能"。

### 剩下的两个拦路石(都已定位)

1. **`Options.loadOfOptions` 数组越界**(stderr,渲染线程):
   `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`
   at `net.minecraft.client.Options.loadOfOptions(Options.java:3174)`。
   游戏目录里有一个 1826 字节的 `optionsof.txt`,是先前某个版本写下的;先怀疑它,清掉再看。
2. **NeoForge 给游戏类补的成员被整类替换吃掉了**:
   `NoSuchMethodError: 'java.util.List net.minecraft.server.packs.resources.ReloadableResourceManager.getListeners()'`
   at `net.neoforged.neoforge.client.event.AddClientReloadListenersEvent.<init>`。
   现代 NeoForge 是把补丁直接打进游戏类的,OptiFine 那份编译结果里当然没有这个方法。
   这正是 1.20.x 那条线上 `keep-runtime.txt` 要干的事,**FML 10 的处理器还没有这套计划**——
   下一步就是给它做一份 1.21.9 的 keep-runtime 计划并把计划支持补进处理器。
## 1.21.9 续:计划机制接上了,OptiFine 已经跑到"Setting user: True"

上一节把 OptiFine 的类送进了游戏类加载器;这一节把**成员/层级**这两件事从"我临时加的粗暴规则"
换成仓库自己的计划机制,并把结果推到 `Setting user: True` + 32 行 `[OptiFine]`。

### 先把处理器搬进仓库自己的构建

此前 FML 10 的处理器只有 26.x 分支有源码,rig 用 `javac` 手编;也就是说**被测试的那个 jar 里的类,
不属于任何一次提交**。现在:

* `src/fml10/java/kynarain/cn/optifineoforge/fml10/` —— 两个类从 26.x 取回,放上本分支;
* `src/fml10/resources/META-INF/services/` —— 两个 service 文件(ClassProcessor 与
  IModFileCandidateLocator),与 `src/ml11/resources` 同样的做法;
* `build.gradle` 在 `-Pmountpoint=fml10` 时把这两个 source root 加进 `sourceSets.main`;
* 构建方式(注意 `JAVA_HOME` 必须是 JDK 21,否则 Gradle 自己的 Groovy 先死在
  `Unsupported class file major version 71`):

  ```
  gradlew.bat -Pmc=1.21.9 -Pneoforge=21.9.16-beta -Pmountpoint=fml10 compileJava
  ```

rig 侧新增 `build-fml10-payload.ps1`:从 `build\classes\java\main` 取这两个类,从
`work\<line>\optifine-patched.jar` 取 1233 个 `srg/**` 成品类,加 service 与 `neoforge.mods.toml`,
再把 `work\<line>\plan\reparent.txt` 放到 `optifineoforge/reparent.txt`。

**顺手踩到一个必须记下来的坑**:`ZipFile.CreateFromDirectory` 在 PowerShell 5.1(.NET Framework)上
写的条目名用**反斜杠**。这样的罐子 FML 直接拒绝:

```
WARN [ne.ne.fm.lo.mo.ModDiscoverer/SCAN]: Skipping jar. File mods/optifine-payload-fml10.jar is not a valid mod file
```

症状极具误导性:游戏**正常启动**、`Setting user: True`、0 崩溃报告、stderr 0 字节 —— 唯一的异常信号是
`[OptiFine] lines: 0`。脚本改成手工写条目、一律用 `/`。

### 两条规则:留运行时独有的成员,按计划换父类

**第一条(成员)**:现代 NeoForge 是**把补丁打进游戏类**的,所以运行时的类可以有 OptiFine 那份编译
里根本没有的成员。实测到的那一处:

```
NoSuchMethodError: 'java.util.List net.minecraft.server.packs.resources.ReloadableResourceManager.getListeners()'
	at net.neoforged.neoforge.client.event.AddClientReloadListenersEvent.<init>(...:29)
	at net.neoforged.neoforge.client.ClientHooks.initClientHooks(...:973)
```

处理器现在**保留运行时独有、负载没有的字段与方法**(按 name+desc 判重),并把数量打进日志。

**第二条(层级)**:`BlockEntity` 两侧的父类不一样 —— 负载那份 extends
`net.minecraftforge.common.capabilities.CapabilityProvider$BlockEntities`(OptiFine 是 Forge 时代编的,
那个类是 shim),运行时那份 extends `net.neoforged.neoforge.attachment.AttachmentHolder`。两边各试一次,
**两次都被验证器打回**,而且错法不同:

* 只搬成员、不动父类:`VerifyError: Bad invokespecial instruction: current class isn't assignable to
  reference class` at `BlockEntity.setData @7` —— NeoForge 的 `setData` 体内是
  `invokevirtual setChanged()V` 然后 `invokespecial AttachmentHolder.setData(...)`;
* 只把父类换成运行时的:`VerifyError: Bad <init> method call ... Type
  'net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities' is not assignable to
  'net/minecraft/world/level/block/entity/BlockEntity'` —— 负载自己的构造器还在 chain 到 Forge 父类的构造器。

两半必须一起动,而这正是 ModLauncher 线早就在用的 `reparent.txt` 计划(格式
`reparent <类> <运行时父类> <构造器>`)。处理器现在**读计划**,没有计划就不动层级;
按计划的 `()V` 重写时把栈上的实参 POP 掉(与 `PatchedClassTransformer.reparent` 同一套做法)。

计划由仓库自己的 `HierarchyPlan` 生成。**它在这里有个调用上的陷阱**:`readRuntime` 是"先到先得",
所以第一个 jar 必须是**真正的运行时视图**(这里 NeoForge 的 `-client.jar`,游戏类被它覆盖),
否则 vanilla 的 `BlockEntity`(父类是 Object)会把它盖掉,于是计划**静默地空**:

```
# 错误(0/0,静默): runtime 传 client-…-srg.jar
# 正确:            runtime 传 neoforge-21.9.16-beta-client.jar
reparent net.minecraft.world.level.block.entity.BlockEntity onto net/neoforged/neoforge/attachment/AttachmentHolder via ()V (the payload extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities)
reparent plan: 1 class(es) movable, 0 refused
```

1 个类,和 1.21.4 那条线上记的"shape 就是这一个类"完全一致。

### 这一轮的结果

```
===== VERDICT: FAILED =====
  Setting user        : True
  new crash reports   : 1
  stderr bytes        : 698
  [OptiFine] lines    : 32
```

OptiFine 的类在里面、`Reflector` 初始化成功、游戏把用户设置读出来了。剩下两处,都已定位到行:

1. **`Gui.layerManager` 是 null**:
   `NullPointerException: Cannot invoke "…GuiLayerManager.initModdedLayers()" because "this.layerManager"
   is null` at `Gui.initModdedOverlays(Gui.java:1604)` ← `ClientHooks.initClientHooks`。
   这个字段是 NeoForge 加的,它**就在仓库自己为 1.21.9 生成的 `member-restores.txt` 里**:
   `F net/minecraft/client/gui/Gui layerManager Lnet/neoforged/neoforge/client/gui/GuiLayerManager;`,
   而且 rig 里已经有配套的 `donors/`。也就是说**"保留成员"只解决了一半**:字段留下来了(所以不是
   `NoSuchFieldError`),但给字段赋值的初始化在 NeoForge 的 `Gui.<init>` 里,而那个构造器被 OptiFine
   的副本整段替换掉了。下一步就是把 `member-restores.txt` + donors 接进 FML 10 的处理器
   (ModLauncher 线上由 `MemberRestoreTransformer` 内联 donor 代码)。
2. **`Options.loadOfOptions` 数组越界**(stderr,698 字节):
   `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at
   `net.minecraft.client.Options.loadOfOptions(Options.java:3174)`。
   实测过的两件事:① 删掉 `optionsof.txt` 再跑仍然出现;② 运行中游戏会重新写出这个文件(88 行,
   全是 `key:value`,没有一行缺冒号)。所以"读到旧版本写的文件"这个猜测**站不住**,
   还得再看这一行到底在切什么。
## 1.21.9 再续:成员恢复接上了,OptiFine 跑到 167 行,卡在 FML 自己的加载画面上

### 这一轮加进处理器的两件事

**1. 成员恢复(计划 + donor)。** 上一节停在 `Gui.layerManager` 为 null:字段被"保留运行时成员"留下来了,
但给它赋值的初始化在 NeoForge 的构造器里,而那个构造器被 OptiFine 的副本取代了。处理器现在读
`/optifineoforge/member-restores.txt` 与 `/optifineoforge/donors/<类>.class`,把 donor 里有、成品类里没有的
字段/方法补进去,并把 donor 里的合成初始化方法**内联**:静态的进本类 `<clinit>`,实例的进每个
"自己没有赋过这个字段"的构造器。

两个都是量出来的,不是选的:

* **静态必须内联,不能调用**。静态 final 字段只能在**本类的初始化方法**里赋值,调用一个 helper 去写会被拒:
  `IllegalAccessError: Update to static final field ... attempted from a different method than the initializer method`。
* **实例也必须内联**。先按 ml11 那套"每个构造器调一次 helper"做,1.21.9 直接给出:
  ```
  IllegalAccessError: Update to non-static final field com.mojang.blaze3d.opengl.GlDevice.deviceProperties
    attempted from a different method (optifineoforge$init$deviceProperties) than the initializer method <init>
      at com.mojang.blaze3d.opengl.GlDevice.optifineoforge$init$deviceProperties(GlDevice.java)
      at com.mojang.blaze3d.opengl.GlDevice.<init>(GlDevice.java:95)
  ```
  内联进构造器之后,同一个 `putfield` 就合法了,而且**接收者天然对齐**:donor 的初始化方法把对象放在局部 0,
  `<init>` 也是。
* **指令克隆器缺 `VarInsnNode`** —— 这是 `Gui.layerManager` 一度仍然是 null 的直接原因,日志里写得很清楚:
  `cannot inline optifineoforge$init$layerManager: an instruction of kind VarInsnNode has no copy here`。
  只允许局部 0(其余局部在构造器里会与形参撞车),其余一律拒绝而不是猜。

**2. 一处针对性的修复:`ReloadableResourceManager` 的监听器列表被冻结。** 这是 NeoForge 与 OptiFine 的
**先后顺序**冲突,两边单独看都没错:

* NeoForge 在 `Minecraft` 构造器里通过 `AddClientReloadListenersEvent` 收集监听器,排序结果**不可改**——
  `ReloadListenerSort.sort` 的最后一步是 `Collections.unmodifiableList`(从
  `neoforge-21.9.16-beta-universal.jar` 里读出来的),`ReloadableResourceManager.updateListenersFrom` 把它直接
  赋给字段;
* OptiFine 自己的补丁在同一个构造器里、**晚几行**(它注入到 `Window.setDefaultErrorCallback` 的那次调用)
  注册一个监听器,于是撞上冻结的列表:
  ```
  UnsupportedOperationException
    at java.util.Collections$UnmodifiableCollection.add
    at net.minecraft.server.packs.resources.ReloadableResourceManager.registerReloadListener(...:43)
    at net.optifine.util.TextureUtils.registerResourceListener(TextureUtils.java:412)
  ```
  NeoForge 自己之后不再注册,所以这个冻结在纯 NeoForge 里看不出来。

  修在**冻结点**而不是 `registerReloadListener`:在 `updateListenersFrom` 调用 `ReloadListenerSort.sort` 之后插
  `new ArrayList<>(list)`(`NEW/DUP_X1/SWAP/INVOKESPECIAL`),让字段恢复 vanilla 构造器给的契约(一个可以
  继续加的 List),NeoForge 算出来的顺序一点不动。字段在这里可赋值,是因为处理器本来就会把
  运行时非 final 的字段的 `final` 清掉——这个字段正是如此(负载编成 `final`,NeoForge 的没有)。

### 结果:167 行 `[OptiFine]`,然后倒在 FML 自己的加载画面上

```
===== VERDICT: FAILED =====
  Setting user        : True
  new crash reports   : 1 -> crash-…22.07.08-client.txt
  stderr bytes        : 0
  [OptiFine] lines    : 167
```

OptiFine 这次是真的在工作(`[OptiFine] Scaled non power of 2: minecraft:leaf_3, 5 -> 10` 这类贴图处理已经跑起来了),
游戏也进了主循环,但停在 **NeoForge 的加载覆盖层**上:

```
java.lang.IllegalStateException: Already building.
	at net.neoforged.fml.earlydisplay.render.SimpleBufferBuilder.begin(SimpleBufferBuilder.java:185)
	at …RenderContext.renderText(RenderContext.java:91)
	at …PerformanceElement.render(PerformanceElement.java:75)
	at …LoadingScreenRenderer.renderToFramebuffer(LoadingScreenRenderer.java:280)
	at TRANSFORMER/neoforge@21.9.16-beta/…NeoForgeLoadingOverlay.render(NeoForgeLoadingOverlay.java:68)
	at TRANSFORMER/minecraft@1.21.9/net.minecraft.client.renderer.GameRenderer.render(GameRenderer.java:811)
	at TRANSFORMER/minecraft@1.21.9/net.minecraft.client.Minecraft.runTick(Minecraft.java:1330)
```

而且是**先有一大片 GL 错误**才轮到它:同一份日志里 `OpenGL API ERROR: 1167` 之类共 **865 行**,第一次出现在
OptiFine 刚开始处理贴图之后,失败的调用是 `glClear`。

**这不是环境问题,是对照组量出来的**:同样的 rig、同样的 90 秒、**不带任何 mod** 跑一次:

```
===== VERDICT: STARTED =====    Setting user: True, Sound engine started: True, 崩溃 0, stderr 0
日志总行数 76,OpenGL API ERROR 0 行,Already building 0 次
```

也就是说这批 GL 错误是**我们装的类带来的**:OptiFine 在资源重载期间的 GL 操作把状态弄坏了(或者某个
被换掉的 `GlStateManager`/`GlDevice` 那一路还缺东西),FML 的早期显示在坏掉的 GL 状态上画,一次 `draw`
中途抛错就把 `SimpleBufferBuilder.building` 留在 true,下一帧 `begin` 直接抛 "Already building"。

**下一步**:把 1.21.8 那条线最后用过的那几份计划照 `add-line.ps1` 的顺序补齐 —— `PayloadDrift`
(keep-runtime / runtime-interfaces / access)与 `MissingTargets --stub`,再把它们接进 FML 10 的处理器;
1.21.8 上"GL 状态那一路"正是靠这几份计划才过的。
## 1.21.9 第四轮:资源重载跑通了(Sound engine started),卡在 NeoForge 自己的约定标签检查

### 这一轮接进处理器的东西,全部来自仓库自己的离线工具

按 `add-line.ps1` 的顺序给 1.21.9 补齐了计划:

* **`MissingTargets --stub`**:扫 1281 个类、43634 条游戏成员引用,**13 条在运行时不存在**,给 4 个类
  (`GpuTexture`、`BlockModelPart`、`BlockStateModel`、`BlockEntity`)补了 12 个成员,1 条留给加载器。
  产物 `optifine-patched-stubbed.jar` 现在就是 payload 的 `srg/**` 来源(`build-fml10-payload.ps1` 优先用它)。
* **`PayloadDrift`**:`constant drift: 2 class(es)`,两份是
  `net/minecraft/client/renderer/MappableRingBuffer`(`BUFFER_COUNT: payload=5 runtime=3`)与
  `net/minecraft/client/resources/model/ModelDiscovery$ModelWrapper`(`SLOT_COUNT: payload=7 runtime=8`)。
  写出的 `keep-runtime.proposed.txt` 就是"这两类整个用运行时的"。另外 15 条 interface 计划、164 条 access 计划。
* 处理器现在读 `/optifineoforge/keep-runtime.txt`(即 proposed 的正式名);被计划的类**不安装**,日志明说
  "is kept as the runtime's own class",并连"代价"一起写在计划文件里。

### 三处按症状定位、按量到的形状修的

1. **模型精灵集合**:客户端进资源重载后**什么都不做**,只每 5 秒打一行
   `[OptiFine] Waiting for model sprites`(永远)。这是 OptiFine 的图集拼装等它自己的标志位。payload 里那次调用
   在,但**藏在分支后面**(从字节码读出来):
   ```
   106: invokestatic net/optifine/Config.isCustomItems:()Z
   109: ifeq 121
   118: invokestatic net/optifine/CustomItems.collectModelSprites:(Ljava/util/Map;)V
   ```
   修法与 1.21.8 那条线一致:把调用放到 `discoverModelDependencies` 每个"第一个参数是 Map"的重载的**开头**,
   无条件。接收者是局部 0(静态方法,查过而不是照抄——ml11 那份无条件压局部 0,只在静态时才成立)。
2. **FML 早期加载画面与 OptiFine 抢同一个 GL 上下文**:不关早期窗口时,GL 错误刷屏(一次跑 7195 行),
   然后 `SimpleBufferBuilder.begin` 抛 `Already building`,崩溃报告写 "Rendering overlay"。**对照组**证明这与环境无关:
   同样 rig、90 秒、**不带 mod**,76 行日志、0 条 GL 错误、STARTED。rig 现在有 `-NoEarlyWindow`
   (写 FML 自己的 `earlyWindowControl=false`),并且这件事被当作 **rig 设置**记下来,因为其它线的跑法里它是开着的。
3. **成员恢复的两个"必须内联"和两个"不能抄"**:静态初始化方法内联进本类 `<clinit>`、实例的(带局部 0 接收者)
   内联进每个构造器;`<clinit>` 本身**既不从 donor 抄、也不从运行时保留**——`BreezeWindLayer` 就是反例:
   payload 那份声明 `private ResourceLocation TEXTURE_LOCATION;`(实例,构造器里 putfield),
   运行时那份同名同描述符但是 `static final`,运行时的 `<clinit>` 用 `GETSTATIC` 读它,
   于是 `IncompatibleClassChangeError: Expected static field ... TEXTURE_LOCATION`,第二次资源重载直接死在
   `EntityRenderers.createEntityRenderers`。

### 结果

```
===== VERDICT: FAILED =====
  Setting user        : True
  Sound engine started: True      <- 资源重载这一次真的完成了
  new crash reports   : 1 -> crash-…22.30.08-fml.txt
  stderr bytes        : 0
  [OptiFine] lines    : 283
```

**下一处已经定位到具体一行**,而且是 NeoForge 自己的代码:

```
Exception message: java.lang.NullPointerException: Cannot invoke "net.minecraft.tags.TagKey.toString()"
    because "tag2" is null
	at net.neoforged.neoforge.common.TagConventionLogWarning.createForgeMapEntry(TagConventionLogWarning.java:556)
	at net.neoforged.neoforge.common.TagConventionLogWarning.<clinit>(TagConventionLogWarning.java:201)
	at net.neoforged.neoforge.common.NeoForgeMod.<init>(NeoForgeMod.java:585)
```

`createForgeMapEntry(ResourceKey, String, TagKey)` 的第三个参数是**调用方传进来的**,即
`TagConventionLogWarning.<clinit>` 里某个标签静态字段**是 null**。这是上一条"`<clinit>` 不能抄"的**另一半**:
为了修 `BreezeWindLayer` 我把运行时 `<clinit>` 整个丢掉了,而被"保留运行时成员"补进来的**静态字段**正是靠它赋值的。
**下一步的规则**应当是把丢掉改成**有条件保留**:扫运行时 `<clinit>` 里每条 `owner == 本类` 的
`GETSTATIC`/`PUTSTATIC`,要求成品类里那个字段同名同描述符**且也是 static**;全部满足就保留这个初始化方法,
有一条不满足就丢掉(并记日志)。这样 `BreezeWindLayer` 那类仍然被挡住,而标签静态字段能拿到值。
**这一处已经查到具体字段了**(下一轮直接从这里进):把 `TagConventionLogWarning.<clinit>` 的
`LineNumberTable` 读出来,`line 201` 对应字节码偏移 `2942`,该处正是:

```
2930: sipush 149
2933: getstatic Registries.ITEM
2936: ldc_w   "dyes/black"
2939: getstatic net/neoforged/neoforge/common/Tags$Items.DYES_BLACK : Lnet/minecraft/tags/TagKey;
2942: invokestatic createForgeMapEntry(ResourceKey;String;TagKey)
```

也就是说**为 null 的是 `net.neoforged.neoforge.common.Tags$Items.DYES_BLACK`——NeoForge 自己的静态标签字段**,
不是我们装的任何类。已核对的:两个 mod 罐子里**都没有 `net/neoforged/**` 条目**(所以不是我们把 NeoForge 的类
遮蔽掉了)。因此下一轮要查的是:`Tags$Items` 的 `<clinit>` 为什么没有给这个字段赋值——最可能的方向是它取的
值来自游戏类里被我们换掉的静态方法(日志里 OptiFine 自己也报过
`[OptiFine] (Reflector) Method not present: net.minecraft.tags.ItemTags.create`),而 `<clinit>` 里的赋值
被异常/分支跳过了。

另外把这一轮"条件保留 `<clinit>`"的实测结果记清楚:**它没有解决这一处**(改完再跑,仍然是同一个 NPE),
所以"保留运行时 `<clinit>`"既不是充分条件也不是充分修法;这一处的根因在上面那条链上,不在 `<clinit>` 的取舍。
## 1.21.9 通过验收:四检查项全中,并且跑了两次

### 最后一处根因:OptiFine 用反射找一个 1.21.9 已经没有的方法

上一节停在 `Tags$Items.DYES_BLACK` 为 null。整条链这一轮全部读出来了:

NeoForge 的 `Tags$Items.<clinit>` 是
```
657: getstatic   net/minecraft/world/item/DyeColor.BLACK
660: invokevirtual DyeColor.getTag:()Lnet/minecraft/tags/TagKey;
663: putstatic   DYES_BLACK
```
而 `getTag()` 返回的是 `DyeColor` 构造器里赋的那个字段,payload 里那次赋值长这样:
```
47: getstatic     net/optifine/reflect/Reflector.ForgeItemTags_create
57: ldc           "forge"                                  <- Forge 时代的命名空间
70: invokevirtual net/optifine/reflect/ReflectorMethod.call([Ljava/lang/Object;)Ljava/lang/Object;
73: checkcast     net/minecraft/tags/TagKey
76: putfield      dyesTag:Lnet/minecraft/tags/TagKey;
```
OptiFine 的 `Reflector.<clinit>` 里那个句柄是
`ForgeItemTags_create = ForgeItemTags.makeMethod("create", String.class, String.class)`,
指向 `net.minecraft.tags.ItemTags.create(String, String)` —— **而 1.21.9 的运行时只有 `create(ResourceLocation)`**,
于是 `ReflectorMethod.call` 返回 null(日志里那句 `[OptiFine] (Reflector) Method not present:
net.minecraft.tags.ItemTags.create` 就是它),`dyesTag` 为 null,`DYES_BLACK` 为 null,`TagConventionLogWarning`
在自己的 `<clinit>` 里炸掉,NeoForge 报"has failed to load correctly"。

**注意这一处 `MissingTargets --stub` 结构上看不到**:那不是一条字节码里对游戏成员的引用,而是反射器字段里的一个
**字符串**。所以修法是处理器**生成**这个方法:往 `ItemTags` 上加
`public static TagKey<Item> create(String namespace, String path)`,内部把 Forge 时代的 `forge` 映射成
NeoForge 的 `c`(与 ModLauncher 线上 `ConventionTags` 同一套规则),再委托给运行时的 `create(ResourceLocation)`。
生成的字节码是 `LDC "forge"; ALOAD 0; String.equals; IFEQ; LDC "c"; GOTO; ALOAD 0; ...`。

`ItemTags` **不在 payload 里**(payload 没有这个类的副本),所以处理器新增了一个"只修不换"的目标集合
`REPAIR_ONLY_TARGETS`:`targets()` 里声明它,`transform()` 在没有 payload 字节时只跑修复。

### 结果:验收四项全中,且可重复

同一条命令跑两次,两次逐项相同:

```
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 365
```

日志侧面证据:`OpenGL API ERROR` **0** 行、`NeoForge mod loading, version 21.9.16-beta` 成功、
`[OptiFine] OptiFine_1.21.9_HD_U_J7_pre2` 完整自述、且**标题界面确实在跑**——
`RealmsNotificationsScreen.<init>` 会调 `RealmsAvailability.get()`,而那个界面只由 `TitleScreen` 构造
(它自己用 `inTitleScreen()` 判断当前界面是 `TitleScreen`),日志里正好有这条:
`[IO-Worker-1/ERROR] [com.mojang.realmsclient.RealmsAvailability/]: Couldn't connect to realms`
(离线机器的正常结果,不是错误)。

### 必须一起记下来的口径

* **`-NoEarlyWindow` 是 rig 设置**:FML 的早期加载画面与 OptiFine 的贴图工作抢同一个 GL 上下文,
  开着它客户端会死在 `SimpleBufferBuilder "Already building"`。关掉它是 FML 自己的开关
  (`earlyWindowControl=false`),而**其它线的跑法里它是开着的**——所以这条线的成绩要按这个前提读。
* 启动入口是 rig 的诊断入口点 `DiagnosticClient`(同一条 `Entrypoint.startup` 管道,只是把异常打到
  stderr)。用它的原因写在 `launch-fml10.ps1` 与 `DiagnosticClient` 的注释里:官方入口点
  `net.neoforged.fml.startup.Client` 把异常交给一个**模态对话框**,什么也不打印。
* 这条线的 payload 不随仓库发布(`srg/**` 是 OptiFine 打过补丁的游戏类),rig 侧由
  `build-fml10-payload.ps1` 用仓库自己的离线工具产出。
## 1.21.10 / 1.21.11 的准备:一条 FML 10 线的完整配方(rig 侧已脚本化)

1.21.9 通过之后,剩下两条 FML 10 线(1.21.10 / 1.21.11)用的是**同一个挂载点、同一套计划**,
所以把它们做成一条命令就值得。rig 侧新增两个脚本,步骤与输入来源都写在脚本头部:

* **`prepare-fml10-line.ps1`**(`-Mc` / `-NeoForge` / `-OptifineJar`):
  1. **runtime 视图** = NeoForge 的 `-client.jar`(覆盖)+ NeoForm 的 `client-*-srg.jar`;
     注意 `HierarchyPlan` 必须**先**拿 overlay,否则 vanilla 的 `BlockEntity` 会把 NeoForge 的盖掉、
     计划静默为空(1.21.9 上踩过,0/0)。
  2. `OptifinePipeline <obf 原版客户端> <OptiFine jar> <work>` —— 仓库自己的离线补丁流程。
  3. `MemberRestorePlan`(成员恢复计划 + donors)。
  4. `HierarchyPlan`(要换父类的类 + 要 chain 的构造器)。
  5. `MissingTargets --stub`(**必须给全运行时 classpath**:游戏 + universal + 每个库 jar;用 argfile 传参,
     因为这几条线的库 jar 有几百个,命令行会被 Windows 拒掉)。
  6. `PayloadDrift`(keep-runtime / interfaces / access 三份计划)。
  7. Gradle `-Pmountpoint=fml10` 编译挂载点,再产出两个 jar。
* **`build-fml10-own-classes.ps1`**:第二个 mod jar(OptiFine 自己的类 + Forge API 桩 + 它自己的
  `neoforge.mods.toml`)。桩类目录为空时会**当场用 `ForgeApiShims` 生成**,两者都必须从 OptiFine jar
  **和**打补丁后的类里取(只用 OptiFine jar 会漏成员)。
  两个 jar 都必须手工按 `/` 分隔写 zip 条目:`ZipFile.CreateFromDirectory` 在 PowerShell 5.1 上写反斜杠,
  FML 会直接判"not a valid mod file",而症状是**客户端正常启动但 mod 根本没装**(1.21.9 上踩过)。

1.21.9 的 OptiFine 构建与 1.21.10 的都从第三方镜像按 IPv4 取到;1.21.11 的**正式版**在镜像上路径不对
(返回 9 字节 "Not Found"),改走 `get-optifine.ps1` 的 optifine.net 两步 token 流程取到了
(`OptiFine_1.21.11_HD_U_J9.jar`,8 045 116 字节)。两条线的 jar 都只放在 rig 的 `downloads\` 下,不进仓库。
### 1.21.10:安装缺件卡在网络,已定位到具体两件

为了把 1.21.10 也跑起来,这一轮做了这些(都留在 rig 里,不进仓库):

* **OptiFine jar 取到了两条线**:1.21.10 的 `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar`(7 805 444 字节,
  第三方镜像按 IPv4);1.21.11 的**正式版** `OptiFine_1.21.11_HD_U_J9.jar`(8 045 116 字节)——
  镜像上那条路径不对(返回 9 字节 "Not Found"),改走 `get-optifine.ps1` 的 optifine.net 两步 token 流程取到。
* **NeoForge 21.10.64 装上了**(universal jar、1577 个库、126 个 native),`versions\1.21.10\1.21.10.jar`
  (30 592 168 字节,混淆原版客户端)也在。
* **两个 rig 脚本写好并语法自检通过**:`prepare-fml10-line.ps1`(runtime 视图 → OptifinePipeline →
  MemberRestorePlan → HierarchyPlan → MissingTargets --stub → PayloadDrift → 两个 jar)与
  `build-fml10-own-classes.ps1`(第二个 mod jar,含 Forge API 桩的按需生成)。
* **卡点**:`libraries\net\minecraft\client\1.21.10-…` 下的 slim/extra/srg 三个 NeoForm 客户端变体,
  以及 `libraries\net\neoforged\neoforge\21.10.64\neoforge-21.10.64-client.jar`(NeoForge 打补丁后的客户端覆盖层)
  **都不存在**;安装每次都报 `libraries: fetched 1, already present 1577, failed 1`。
  直接取这两件时 **`maven.neoforged.net:443` 连不上**(curl 72 秒超时),这是环境/网络问题,不是配置问题。
  已经把 Gradle 模块缓存里的 `neoform-1.21.10-20251010.172816.zip`(889 425 字节)**补种**到 rig 的
  `libraries\net\neoforged\neoform\1.21.10-20251010.172816\`,并核对了 `neoforge-21.10.64-userdev.jar`:
  里面是 `patches/**`、`ats/accesstransformer.cfg`、`config.json`,**不含编译后的客户端类**,所以覆盖层必须由
  NeoForm 流程(neoform zip + 这些补丁)产出,不是能直接下载的成品。

**下一步**:网络恢复后重跑 `add-line.ps1 -InstallOnly`(neoform zip 已就位,可能就能补齐三个客户端变体),
再跑 `prepare-fml10-line.ps1 -Mc 1.21.10 -NeoForge 21.10.64 -OptifineJar <jar>`,最后按脚本末尾打印的命令启动。
这个脚本里的每一步都是 1.21.9 上量过的同一条链,所以剩下的风险集中在"安装能不能补齐"这一件上。
## 1.21.10 通过验收:同一套计划,零改动

```
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 356
```

两次跑法逐项一致(`[OptiFine] OptiFine_1.21.10_HD_U_J7_pre11`、`OpenGL API ERROR` 0 行、标题界面标志
`RealmsAvailability` 在跑、无崩溃报告、stderr 0 字节)。**这条线没有为它改一行处理器代码**:
成员恢复、reparent 计划、keep-runtime、stub 计划、精灵集合修复、反射式 tag creator 全部原样生效,
这正是把这些东西做成"计划驱动"的价值。

### 这一轮为 1.21.10 解决的三件事

1. **安装缺件换了一条路**。maven.neoforged.net 从这台机器上连不上,安装器始终报
   `libraries: fetched 1, already present 1577, failed 1`,`libraries\net\minecraft\client\1.21.10-…` 的
   slim/extra/srg 与 `neoforge-21.10.64-client.jar` 都拿不到。**ModDevGradle 自己的 NeoForm 运行时早就把
   等价物算出来了**:
   `~/.gradle/caches/neoformruntime/intermediate_results/compiledWithNeoForge_<hash>_output.jar`
   —— 一个 jar 里同时有打补丁后的游戏类(`net/minecraft/**`)和 NeoForge 自己的类
   (`net/neoforged/neoforge/**`),13323 个条目,是 "overlay + srg client" 的超集。
   `prepare-fml10-line.ps1` 因此加了 `-RuntimeJar`:给它就跳过安装器那两个成品。同样地,
   neoform zip 也从 Gradle 模块缓存补种进 `libraries\net\neoforged\neoform\1.21.10-20251010.172816\`。
   **这是拿缓存里的等价产物替代缺失下载,记下来是因为它不是"官方安装"那条路。**
2. **诊断入口点要跨 FML 版本**。FML 10.0.32(NeoForge 21.10.64)把 API 换了:
   `startup(...)` 返回 `Entrypoint$StartupResult`(不再是 `FMLLoader`)、
   `createMainMethodCallable(StartupResult, String)`、关闭走 `StartupResult.close()`。
   按 10.0.14 编译的诊断类第一行就死:
   `NoSuchMethodError: 'FMLLoader … .startup(String[], boolean, Dist, boolean)'`。
   新的 `DiagnosticClientAny` **不引用任何 FML API**,全部用反射找方法,所以两条线共用一个类。
3. **`-NoEarlyWindow` 同样适用**:开着早期加载画面时,1.21.10 死在与 1.21.9 一模一样的
   `SimpleBufferBuilder "Already building"`(FML 早期画面与 OptiFine 的贴图工作抢 GL 状态)。
   另外 `-NoEarlyWindow` 现在要求 `config\fml.toml` 已存在——第一次启动会写它,所以新线要先不带这个开关跑一次。

### 1.21.11 的状态:缺一个装不了件

OptiFine 正式版 jar 已在 rig 里(`OptiFine_1.21.11_HD_U_J9.jar`,8 045 116 字节),Gradle 模块缓存里也有
`neoforge-21.11.45-universal.jar` / `-userdev.jar`,但**没有 `neoforge-21.11.45-installer.jar`**,
而 `versions\neoforge-21.11.45\neoforge-21.11.45.json` 这个 profile(库清单与 JVM/游戏参数)只有安装器能生成;
`maven.neoforged.net:443` 这一轮仍然连不上(25 秒超时)。所以 1.21.11 卡在**环境**上,不是代码上:
网络恢复后跑 `add-line.ps1 -InstallOnly -Mc 1.21.11 -NeoForge 21.11.45 -MountPoint fml10`,
再用 1.21.10 同样的 `-RuntimeJar` 路径(从 NeoForm 缓存取)跑 `prepare-fml10-line.ps1`,即可按同一套流程验证。