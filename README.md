# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上一样:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

这个仓库是项目的门面。**开发在三条互相独立的分支上进行**,每条分支对应一段 Minecraft 版本区间,各有自己的 `README`、构建配置、文档和版本号:

| 分支 | Minecraft | OptiFine 有构建的版本 | 状态 |
|---|---|---|---|
| [`1.20.x`](../../tree/1.20.x) | 1.20.1 – 1.20.6 | 1.20.1、1.20.2、1.20.4、1.20.6 | 骨架 |
| [`1.21.x`](../../tree/1.21.x) | 1.21 – 1.21.11 | 1.21、1.21.1、1.21.3、1.21.4、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10、1.21.11 | 骨架 |
| [`26.x`](../../tree/26.x) | 26.1.2 | 26.1.2 | 骨架 |

三条分支**不做合并**,一个分支产出的 jar 也不能拿到另一条线上用:不同版本区间的运行期命名空间、OptiFine 的补丁形式和 NeoForge 的转换流程都不一样。

> **状态:骨架阶段。** 分支、版本矩阵、构建配置与文档先落地,加载器本身仍在实现中 —— 目前没有可用的发布产物,也没有通过验证的运行结果。每个分支的 `docs/PLAN.md` 写了设计与里程碑。

## 命名

- 显示名 **OptifiNeoforge**,mod id `optifineoforge`
- 产物 `OptifiNeoforge-<版本>+mc<Minecraft 版本>.jar`,版本号遵循 [语义化版本 2.0.0](https://semver.org/lang/zh-CN/),`+mc...` 是编译信息;加载器跑通之前一律停在 `0.x`

## 许可

**MPL-2.0**(`LICENSE`)。加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。

**不包含、也不分发 OptiFine 本体**:OptiFine 版权归 sp614x 所有,请自行获取。
