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