# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上的做法相同:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时由本模组运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

本分支是 **1.20.x 线**,覆盖 **Minecraft 1.20.1、1.20.2、1.20.4、1.20.6**(OptiFine 在这条线上出过构建的全部四个版本)。1.21.x 线(1.21 – 1.21.11)与 26.x 线(Minecraft 26.1.2)在各自的分支上独立开发,三条线的 jar 不能互相替代。

> **状态:骨架阶段。** 分支、版本矩阵、构建配置与文档先落地,加载器本身仍在实现中 —— 目前没有可用的发布产物,也没有通过验证的运行结果。

## 支持的版本

| Minecraft | NeoForge | 产物 | OptiFine 正式版 | OptiFine 最新 preview | Java | 状态 |
|---|---|---|---|---|---|---|
| 1.20.1 | `net.neoforged:forge:1.20.1-47.1.106` | `OptifiNeoforge-0.1.0+mc1.20.1.jar` | `OptiFine_1.20.1_HD_U_I6.jar` | `preview_OptiFine_1.20.1_HD_U_I6_pre6.jar` | 17 | 骨架 |
| 1.20.2 | `20.2.93` | `OptifiNeoforge-0.1.0+mc1.20.2.jar` | 无 | `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar` | 17 | 骨架 |
| 1.20.4 | `20.4.251` | `OptifiNeoforge-0.1.0+mc1.20.4.jar` | `OptiFine_1.20.4_HD_U_I7.jar` | `preview_OptiFine_1.20.4_HD_U_I8_pre4.jar` | 17 | 骨架 |
| 1.20.6 | `20.6.141` | `OptifiNeoforge-0.1.0+mc1.20.6.jar` | 无 | `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar` | 21 | 骨架 |

两列 OptiFine 都只表示"该构建存在",不代表可用;这里也不表示正式版比 preview 更适合移植。

- mod id `optifineoforge`,仅客户端。
- **一个 jar 只对应一个 MC 版本**:这条线跨了四个版本,NeoForge 坐标、Java 版本与运行期命名空间在每个版本上都不同,不能混用,也不能拿别的线的 jar 顶替。
- Java 在这条线上**不统一**:1.20.1 / 1.20.2 / 1.20.4 是 **17**,1.20.6 起是 **21**。
- NeoForge 坐标也跨了一个时代:1.20.1 是 `net.neoforged:forge:1.20.1-47.1.106`,1.20.2 起才是独立的 `net.neoforged:neoforge`(`20.x.y`)。
- **1.20.2 的 OptiFine 只有一个 preview**(`I7_pre1`);**1.20.3 与 1.20.5 没有任何 OptiFine 构建**,因此不在支持范围内。
- 四个版本合计 29 个 OptiFine 构建,逐条列在 `docs/VERSIONS.md`。
- 这条线**内部**还有两处分界(元数据文件名、运行期命名空间),确切位置尚未确认,见 `docs/VERSIONS.md` 的待确认一节。

## 安装(尚未可用)

计划中的流程:把本模组的 jar 与该 MC 版本**严格一致**的 OptiFine jar 一起放进这个版本自己的 `mods/` 目录,用 **NeoForge** 版本启动,不要用启动器注入 OptiFine 的版本(那个是启动器在启动时注入 OptiFine,会与本模组重复)。首次启动需要跑完整的补丁流程,之后走缓存。骨架阶段这些都还做不到 —— 没有产物可放。

## 构建(构建配置尚未落地)

需要 **JDK 17**(1.20.1 / 1.20.2 / 1.20.4)或 **JDK 21**(1.20.6),按目标版本选。本分支目前只有文档与许可,Gradle 构建配置随骨架的后续提交并入;形式与 26.x 线一致,仓库根目录就是 Gradle 项目:

```powershell
.\gradlew build
```

产物为 `build/libs/OptifiNeoforge-<版本>+mc<MC 版本>.jar`,例如 `OptifiNeoforge-0.1.0+mc1.20.4.jar`。

## 工作原理(计划)

OptiFine 的 Forge 侧入口是一个 ModLauncher 服务:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 指向 `optifine.OptiFineTransformationService`,它再通过 `optifine.OptiFineTransformer`(实现 ModLauncher 的 `ITransformer<ClassNode>`)把补丁类插进加载流程。NeoForge 同样跑在 ModLauncher 上,所以这条路可以复用,但需要处理四件事:

1. **元数据**:OptiFine 的 jar 里是 Forge 时代的 `META-INF/mods.toml`。这条线早期的 NeoForge(到 1.20.4)本身就是 Forge 时代的产物,那份元数据**可能**直接可用;1.20.6 及之后预期要换成 NeoForge 自己的 `META-INF/neoforge.mods.toml`,但确切切换点没有实测;
2. **命名空间**:1.20.x 时代 Forge/NeoForge 的运行期名预期是 **SRG**,而 OptiFine 的补丁按它发布时的命名空间存放,错配会整段失效;
3. **补丁重叠**:NeoForge 自己也会改原版类,两边的改动需要按顺序合并,而不是互相顶掉;
4. **线内的版本分叉**:1.20.1 走 `net.neoforged:forge` 坐标,1.20.6 起要 JDK 21,四个版本的构建与元数据要分别配置,不能指望一份配置覆盖整条线。

设计细节与里程碑见 `docs/PLAN.md`。

## 已知限制

- **骨架阶段没有任何可用产物**:本线当前不可安装、不可运行,下文所有"行为"都是计划而非结论。
- **1.20.3 与 1.20.5 没有 OptiFine 构建**,这两版不在支持范围内;1.20.2 只有一个 preview,它能否作为移植对象尚未验证。
- 这条线跨了 Forge 时代的 NeoForge(1.20.1)与独立的 `20.x` 系列,元数据文件名与运行期命名空间都可能在这条线**内部**发生变化,所以四个版本要分别验证,不能由一个版本的结果外推。
- 若 1.20.6 一侧的运行期名已经是官方名而不是 SRG,那么本线内部就需要两套命名空间处理 —— 这一侧属于待确认事项。
- 与 OptiFabric 一样,**不包含、也不分发 OptiFine 本体**:OptiFine 的 jar 由用户自行获取,本项目只把它当作补丁来源。
- 本线**没有任何实测记录**:构建列表里"存在"不等于"可用",正式版也不代表比 preview 更适配。

## 许可与致谢

- 本项目遵循 **MPL-2.0**(`LICENSE`),加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。
- **不包含、也不分发 OptiFine 本体**,OptiFine 版权归 sp614x 所有,请自行获取。
- 各版本的构建列表、NeoForge 坐标与下载命令见 `docs/VERSIONS.md`,版本号规则见 `docs/VERSIONING.md`,设计与里程碑见 `docs/PLAN.md`。
