# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上的做法相同:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时由本模组运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

本分支是 **26.x 线**,对应 **Minecraft 26.1.2**。1.20.x 线(1.20.1 – 1.20.6)与 1.21.x 线(1.21 – 1.21.11)在各自的分支上独立开发,三条线的 jar 不能互相替代。

> **状态:骨架阶段。** 分支、版本矩阵、构建配置与文档先落地,加载器本身仍在实现中 —— 目前没有可用的发布产物,也没有通过验证的运行结果。

## 支持的版本

| Minecraft | NeoForge | 产物 | OptiFine 构建 | Java | 状态 |
|---|---|---|---|---|---|
| 26.1.2 | `26.1.2.109` | `OptifiNeoforge-0.1.0+mc26.1.2.jar` | `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` | 25 | 骨架 |

- mod id `optifineoforge`,仅客户端。
- 26.1 起游戏**未混淆**,官方名即运行名,因此这一线没有映射表要处理。
- 26.1.2 的 OptiFine 只有 preview 构建;完整构建列表见 `docs/VERSIONS.md`。
- 26.1.2 之外的版本(26.1、26.1.1、26.2+)目前没有 OptiFine 构建,没有可移植的对象。

## 安装(尚未可用)

计划中的流程:把本模组的 jar 与对应版本的 OptiFine jar 一起放进 `mods/`,用 **NeoForge** 版本启动。首次启动需要跑完整的补丁流程,之后走缓存。

## 构建

需要 **JDK 25**(26.1.2 自身的要求)。仓库根目录就是 Gradle 项目:

```powershell
.\gradlew build
```

产物为 `build/libs/OptifiNeoforge-<版本>+mc26.1.2.jar`。

## 工作原理(计划)

OptiFine 的 Forge 侧入口是一个 ModLauncher 服务:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 指向 `optifine.OptiFineTransformationService`,它再通过 `optifine.OptiFineTransformer`(实现 ModLauncher 的 `ITransformer<ClassNode>`)把补丁类插进加载流程。NeoForge 同样跑在 ModLauncher 上,所以这条路可以复用,但需要处理三件事:

1. **元数据**:OptiFine 的 jar 里是 Forge 时代的 `META-INF/mods.toml`,NeoForge 需要它自己的那份;
2. **命名空间**:OptiFine 的补丁按它发布时的命名空间存放,26.x 这条线是未混淆的官方名,错配会整段失效;
3. **补丁重叠**:NeoForge 自己也会改原版类,两边的改动需要按顺序合并,而不是互相顶掉。

设计细节与里程碑见 `docs/PLAN.md`,两个前置调研见 `docs/RESEARCH-neoforge.md` 与 `docs/RESEARCH-optifine.md`。

## 许可与致谢

- 本项目遵循 **MPL-2.0**(`LICENSE`),加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。
- **不包含、也不分发 OptiFine 本体**,OptiFine 版权归 sp614x 所有,请自行获取。
