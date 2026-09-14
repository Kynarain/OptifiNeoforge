# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上的做法相同:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时由本模组运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

本分支是 **1.21.x 线**,覆盖 **Minecraft 1.21 – 1.21.11**(OptiFine 出过构建的全部十个版本)。1.20.x 线(1.20.1 – 1.20.6)与 26.x 线(Minecraft 26.1.2)在各自的分支上独立开发,三条线的 jar 不能互相替代。

> **状态:骨架阶段。** 分支、版本矩阵、构建配置与文档先落地,加载器本身仍在实现中 —— 目前没有可用的发布产物,也没有通过验证的运行结果。

## 支持的版本

| Minecraft | NeoForge | 产物 | OptiFine 正式版 | OptiFine 最新 preview | Java | 状态 |
|---|---|---|---|---|---|---|
| 1.21 | `21.0.167` | `OptifiNeoforge-0.1.0+mc1.21.jar` | 无 | `preview_OptiFine_1.21_HD_U_J1_pre9.jar` | 21 | 骨架 |
| 1.21.1 | `21.1.250` | `OptifiNeoforge-0.1.0+mc1.21.1.jar` | `OptiFine_1.21.1_HD_U_J1.jar` | `preview_OptiFine_1.21.1_HD_U_J1_pre15.jar` | 21 | 骨架 |
| 1.21.3 | `21.3.97` | `OptifiNeoforge-0.1.0+mc1.21.3.jar` | `OptiFine_1.21.3_HD_U_J2.jar` | `preview_OptiFine_1.21.3_HD_U_J2_pre12.jar` | 21 | 骨架 |
| 1.21.4 | `21.4.157` | `OptifiNeoforge-0.1.0+mc1.21.4.jar` | `OptiFine_1.21.4_HD_U_J3.jar` | `preview_OptiFine_1.21.4_HD_U_J4_pre2.jar` | 21 | 骨架 |
| 1.21.6 | `21.6.20-beta` | `OptifiNeoforge-0.1.0+mc1.21.6.jar` | 无 | `preview_OptiFine_1.21.6_HD_U_J6_pre3.jar` | 21 | 骨架 |
| 1.21.7 | `21.7.25-beta` | `OptifiNeoforge-0.1.0+mc1.21.7.jar` | 无 | `preview_OptiFine_1.21.7_HD_U_J6_pre7.jar` | 21 | 骨架 |
| 1.21.8 | `21.8.54` | `OptifiNeoforge-0.1.0+mc1.21.8.jar` | 无 | `preview_OptiFine_1.21.8_HD_U_J6_pre16.jar` | 21 | 骨架 |
| 1.21.9 | `21.9.16-beta` | `OptifiNeoforge-0.1.0+mc1.21.9.jar` | 无 | `preview_OptiFine_1.21.9_HD_U_J7_pre2.jar` | 21 | 骨架 |
| 1.21.10 | `21.10.64` | `OptifiNeoforge-0.1.0+mc1.21.10.jar` | 无 | `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar` | 21 | 骨架 |
| 1.21.11 | `21.11.45` | `OptifiNeoforge-0.1.0+mc1.21.11.jar` | `OptiFine_1.21.11_HD_U_J9.jar` | `preview_OptiFine_1.21.11_HD_U_J9_pre4.jar` | 21 | 骨架 |

两列 OptiFine 都只表示"该构建存在",不代表可用;这里也不表示正式版比 preview 更适合移植。

- mod id `optifineoforge`,仅客户端,全线要求 **Java 21**。
- **一个 jar 只对应一个 MC 版本**:十个版本分属十条 NeoForge 线,元数据与命名空间要按版本各自处理,不能混用,也不能拿别的线的 jar 顶替。
- **三个版本的 NeoForge 只有 beta**:1.21.6(`21.6.20-beta`)、1.21.7(`21.7.25-beta`)、1.21.9(`21.9.16-beta`)这三条线在 `maven.neoforged.net` 上**没有任何非 beta 构建**,所以这三个产物只能用 beta 版 NeoForge 测试,用户也要在启动器里允许 beta 版本。
- **1.21.2 与 1.21.5 没有任何 OptiFine 构建**,不在支持范围内。
- OptiFine 只出到 preview 的版本有不少:1.21、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10 这六版的构建全是 preview。
- 十个版本合计 94 个 OptiFine 构建,逐条列在 `docs/VERSIONS.md`。

## 安装(尚未可用)

计划中的流程:把本模组的 jar 与该 MC 版本**严格一致**的 OptiFine jar 一起放进这个版本自己的 `mods/` 目录,用 **NeoForge** 版本启动(1.21.6 / 1.21.7 / 1.21.9 上要允许 beta 版本),不要用启动器注入 OptiFine 的版本(那个是启动器在启动时注入 OptiFine,会与本模组重复)。首次启动需要跑完整的补丁流程,之后走缓存。骨架阶段这些都还做不到 —— 没有产物可放。

## 构建(构建配置尚未落地)

需要 **JDK 21**(整条线统一)。本分支目前只有文档与许可,Gradle 构建配置随骨架的后续提交并入;形式与 26.x 线一致,仓库根目录就是 Gradle 项目:

```powershell
.\gradlew build
```

产物为 `build/libs/OptifiNeoforge-<版本>+mc<MC 版本>.jar`,例如 `OptifiNeoforge-0.1.0+mc1.21.11.jar`。

## 工作原理(计划)

OptiFine 的 Forge 侧入口是一个 ModLauncher 服务:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 指向 `optifine.OptiFineTransformationService`,它再通过 `optifine.OptiFineTransformer`(实现 ModLauncher 的 `ITransformer<ClassNode>`)把补丁类插进加载流程。NeoForge 同样跑在 ModLauncher 上,所以这条路可以复用,但需要处理三件事:

1. **元数据**:OptiFine 的 jar 里是 Forge 时代的 `META-INF/mods.toml`,NeoForge 需要它自己的那一份 `META-INF/neoforge.mods.toml`;
2. **命名空间**:这条线的运行期名预期已是官方(Mojang)名,不再是 SRG,可能不需要重映射;但切换点的确切位置**尚未确认**(它落在 1.20.5/1.21 前后,本线起点正在附近),错配会整段失效;
3. **补丁重叠**:NeoForge 自己也会改原版类,两边的改动需要按顺序合并,而不是互相顶掉。

设计细节与里程碑见 `docs/PLAN.md`。

## 已知限制

- **骨架阶段没有任何可用产物**:本线当前不可安装、不可运行,下文所有"行为"都是计划而非结论。
- **1.21.2 与 1.21.5 没有 OptiFine 构建**,这两版不在支持范围内。
- **1.21.6 / 1.21.7 启用光影包会崩**,这是 OptiFine 侧自身的缺陷:这两版可以在不启用光影包时正常启动、正常渲染(标题界面无异常、无崩溃报告),但只要启用光影包,游戏就会在启动阶段崩:

  ```
  java.lang.NullPointerException: Cannot read field "norm" because "multiTex" is null
    at net.optifine.shaders.ShadersTex.initDynamicTextureNS(...)
  ```

  原因是这两版的 OptiFine 预览构建给纹理初始化插入的调用**缺少一个前置的 `setParentTexture` 关联**,而被调用的 `initDynamicTextureNS` 会直接解引用 `getMultiTexID()` 的结果。这两版可用的 OptiFine 构建共七个(1.21.6 三个 + 1.21.7 四个),行为一致,降级到更早的 preview 不能规避。**这是 OptiFine 补丁负载自身的问题,与本模组的加载器适配无关**;来源见下。
- 上面这条来自姊妹项目 **OptiFabric-Reforged** 的 1.21.x 线记录;本项目**尚未**在自己这条线上复现过任何行为,也没有任何实测记录 —— 构建列表里"存在"不等于"可用"。
- 这条线的 1.21.6 / 1.21.7 / 1.21.9 只能用 **beta 版 NeoForge**,beta 本身的变动会增加排查噪声。
- 与 OptiFabric 一样,**不包含、也不分发 OptiFine 本体**:OptiFine 的 jar 由用户自行获取,本项目只把它当作补丁来源。

## 许可与致谢

- 本项目遵循 **MPL-2.0**(`LICENSE`),加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。
- **不包含、也不分发 OptiFine 本体**,OptiFine 版权归 sp614x 所有,请自行获取。
- 各版本的构建列表、NeoForge 坐标与下载命令见 `docs/VERSIONS.md`,版本号规则见 `docs/VERSIONING.md`,设计与里程碑见 `docs/PLAN.md`。
