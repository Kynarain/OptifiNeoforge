# OptifiNeoforge

在 **NeoForge** 上加载 **OptiFine** 的客户端模组,做法与 OptiFabric 在 Fabric Loader 上一样:把 OptiFine 的 jar 和本模组一起放进 `mods/`,启动时运行 OptiFine 自带的补丁流程,并把打过补丁的 Minecraft 类接进 NeoForge 的类转换流程。

这个仓库是项目的门面。**开发在三条互相独立的分支上进行**,每条分支对应一段 Minecraft 版本区间,各有自己的 `README`、构建配置、文档和版本号:

| 分支 | Minecraft | OptiFine 有构建的版本 | 状态 |
|---|---|---|---|
| [`1.20.x`](../../tree/1.20.x) | 1.20.1 – 1.20.6 | 1.20.1、1.20.2、1.20.4、1.20.6 | **4 / 4 已实机验证**,1.0.0 |
| [`1.21.x`](../../tree/1.21.x) | 1.21 – 1.21.11 | 1.21、1.21.1、1.21.3、1.21.4、1.21.6、1.21.7、1.21.8、1.21.9、1.21.10、1.21.11 | **10 / 10 已实机验证**,1.0.0 |
| [`26.x`](../../tree/26.x) | 26.1.2 | 26.1.2 | **1 / 1 已实机验证**,1.0.0 |

三条分支**不做合并**,一个分支产出的 jar 也不能拿到另一条线上用:不同版本区间的运行期命名空间、OptiFine 的补丁形式和 NeoForge 的转换流程都不一样。

> **状态:15 / 15 条线已实机验证,其中 10 条已发布 `1.0.0`。** 判据是**一次真实启动**,不是"能编译":每种 Minecraft 版本都用自己的 NeoForge 与自己的 OptiFine 构建各跑一次,客户端进到标题画面(`Setting user`)、声音引擎启动(`Sound engine started`)、本次运行**没有写出崩溃报告**,并且 stderr 与记录一致。逐条的实测数字见下面的矩阵,每个已发布的版本附**一个** jar(加载器侧,不含 OptiFine)。
>
> **另有 5 条已验证但没有发布,而它们的构建阻塞现在都已解除**(每条都实测过一次)。`1.20.1` 与 `1.20.2` 原来都解不出依赖:前者那一代 NeoForge 是旧的 `net.neoforged:forge` 坐标,产物文件的 POM 是 `packaging=pom` 且没有依赖、真正带分类符,而插件要的是 `net.neoforged:neoforge`;后者整个 `20.2.x` 系列在 Maven 上都没有 Gradle Module Metadata(实测 `20.2.88` 没有 `.module`,`20.4.251` 与 `20.6.141` 有),所以 ModDevGradle 取不到它需要的 `neoforge-moddev-bundle` 变体。这两条现在**绕开该插件**,按一张取自各版自身元数据的显式类路径编译 —— 本线源码里没有一处 `net.minecraft` / `com.mojang` 的 import,所以既不用下载也不用反编译 Minecraft,构建以秒计。`1.21.9` / `1.21.10` / `1.21.11` 原来死在 `:compileJava`:实现 `cpw.mods.modlauncher.api.ITransformationService` 的那批类放在 `src/main`,而这三版 NeoForge 已经没有 ModLauncher(实测 1.21.11 报 `程序包cpw.mods.modlauncher.api不存在` 共 100 个错误);那批类现在移进自己的源码根,由 `-Pmountpoint` 按代次选择编不编。
>
> **但"能构建"不等于"可发布",这五条仍未发布。** `1.20.1` / `1.20.2` 的产物与已发布的 `1.20.4` / `1.20.6` 同类(含 ModLauncher 转换服务,但不含载荷);`1.21.9` – `1.21.11` 的产物只有加载器侧工具 + mod 骨架,因为那三条的挂载点是我们自己的 `ClassProcessor`,由 rig 编译进含 OptiFine 类的载荷 jar(与 `26.x` 为 26.1.2 发布的产物同类)。**发布本身是独立的一步,尚未做**,而这一轮**没有重跑任何一次实机启动**(这台机器上没有 rig),所以这五条的实机判据仍然只有矩阵里那一份。
>
> 顺带记录一处**构建层面的改正**:`1.20.x` 线的元数据文件名分界已经量清(1.20.1 – 1.20.4 只读 `META-INF/mods.toml`,1.20.6 两个名字都读),而此前四个版本一律写 `neoforge.mods.toml` —— 也就是说已发布的 1.20.4 产物里那份元数据,对 20.4.251 的 FML 等于不存在。现在写出的名字与各版自己的产物一致;发布说明与已上传的资产没有改动。

## 实测矩阵(2026-09-14 – 2026-09-18)

| 分支 | Minecraft | NeoForge | `VERDICT` | `Setting user` | `[OptiFine]` 行数 | stderr | 本次崩溃报告 | 产物 |
|---|---|---|---|---|---|---|---|---|
| 1.20.x | 1.20.1 | 47.1.106 | `STARTED (40s, Sound engine started)` | ✓ | 157 | 27 字节 | 无 | 未发布(见上) |
| 1.20.x | 1.20.2 | 20.2.88 | `STARTED (40s, Sound engine started)` | ✓ | 239 | 14 625 字节¹ | 无 | 未发布(见上) |
| 1.20.x | 1.20.4 | 20.4.251 | `STARTED (40s, Sound engine started)` | ✓ | 241 | 14 481 字节¹ | 无 | [1.0.0+mc1.20.4](../../releases/tag/v1.0.0+mc1.20.4) |
| 1.20.x | 1.20.6 | 20.6.141 | `STARTED (40s, Sound engine started)` | ✓ | 222 | 0 字节 | 无 | [1.0.0+mc1.20.6](../../releases/tag/v1.0.0+mc1.20.6) |
| 1.21.x | 1.21 | 21.0.167 | `STARTED (40s, Sound engine started)` | ✓ | 252 | 14 141 字节¹ | 无 | [1.0.0+mc1.21](../../releases/tag/v1.0.0+mc1.21) |
| 1.21.x | 1.21.1 | 21.1.250 | `STARTED (40s, Sound engine started)` | ✓ | 223 | 0 字节 | 无 | [1.0.0+mc1.21.1](../../releases/tag/v1.0.0+mc1.21.1) |
| 1.21.x | 1.21.3 | 21.3.97 | `STARTED (40s, Sound engine started)` | ✓ | 225 | 0 字节 | 无 | [1.0.0+mc1.21.3](../../releases/tag/v1.0.0+mc1.21.3) |
| 1.21.x | 1.21.4 | 21.4.149 | `STARTED (40s, Sound engine started)` | ✓ | 232 | 0 字节 | 无 | [1.0.0+mc1.21.4](../../releases/tag/v1.0.0+mc1.21.4) |
| 1.21.x | 1.21.6 | 21.6.20-beta | `STARTED (40s, Sound engine started)` | ✓ | 340 | 0 字节 | 无 | [1.0.0+mc1.21.6](../../releases/tag/v1.0.0+mc1.21.6)² |
| 1.21.x | 1.21.7 | 21.7.25-beta | `STARTED (40s, Sound engine started)` | ✓ | 340 | 0 字节 | 无 | [1.0.0+mc1.21.7](../../releases/tag/v1.0.0+mc1.21.7)² |
| 1.21.x | 1.21.8 | 21.8.54 | `STARTED (40s, Sound engine started)` | ✓ | 337 | 0 字节 | 无 | [1.0.0+mc1.21.8](../../releases/tag/v1.0.0+mc1.21.8) |
| 1.21.x | 1.21.9 | 21.9.16-beta | `STARTED (40s, Sound engine started)` | ✓ | 289 | 0 字节 | 无 | 未发布(见上) |
| 1.21.x | 1.21.10 | 21.10.64 | `STARTED (40s, Sound engine started)` | ✓ | 280 | 0 字节 | 无 | 未发布(见上) |
| 1.21.x | 1.21.11 | 21.11.45 | `STARTED (40s, Sound engine started)` | ✓ | 197 | 107 字节³ | 无 | 未发布(见上) |
| 26.x | 26.1.2 | 26.1.2.109 | `STARTED (40s, Sound engine started)` | ✓ | 3478 | 107 字节³ | 无 | [1.0.0+mc26.1.2](../../releases/tag/v1.0.0+mc26.1.2)⁴ |

¹ **已知缺陷(1.20.2 / 1.20.4 / 1.21 共有,已接受、不阻断)**:OptiFine 自带的 `Reflector` 表里有几条它在
`GameRenderer.frameInit` 里反射的游戏类,在这几个 OptiFine 构建(`I7_pre1` / `I7` / `J1_pre9`)里解析不到,
每次启动固定写 4 条 `NoClassDefFoundError`(类名在 `BlockState` / `ItemStack` / `PoseStack` /
`BlockEntityWithoutLevelRenderer` 之间取)到 stderr,异常被 OptiFine 自己吞掉。已排除"是我们换类换坏的":
`ItemStack` 在这几条线上一次都没被换装,而且同一份 loader、同一张模块图在 1.21.8 上 stderr 是 0 字节。
缺陷跟着 OptiFine 构建走,不跟着加载器走;代价是 OptiFine 那几项反射功能不可用,不是能不能启动。

² **1.21.6 / 1.21.7 的验收不含"启用光影包"**:这两版的 OptiFine 只有预览构建,启用光影包会崩在 OptiFine
自己的 `ShadersTex.initDynamicTextureNS`(这些构建缺一个前置的 `setParentTexture` 关联)。这是 OptiFine
补丁负载自身的问题,与加载器无关,所以这两条线按"不启用光影包"验收。

³ **107 字节不是本模组写的**:内容是 FML 终端日志组件的一行环境告警
(`Advanced terminal features are not available in this environment`)。1.21.11 与 26.1.2 各有一条**不带任何 mod**
的对照跑,那 107 字节逐字节相同(只有时间戳不同),所以这两条线本模组自己写入 stderr 的字节数是 0。

⁴ **26.1.2 的验证方式与另外两条线不同(如实说明)**:OptiFine 的 `K1_pre2` 自带 `ClassProcessor`,所以这一线
的挂载点是 OptiFine 自己的,游戏里**没有**装本项目的 loader;本项目贡献的是离线流水线(打补丁、改父类、
回填成员、生成 shim),它把载荷装配出来交给 OptiFine 的 processor。jar 里装的是这套工具与 mod 骨架。

**运行要求(1.20.4,以及 1.21.9 / 1.21.10 这类 FML 10 线)**:游戏目录的 `config/fml.toml` 要设
`earlyWindowProvider = "none"`。FML 的 early window 与 OptiFine 换装的渲染类会在同一帧里重入,报
`IllegalStateException: Already building`;实测(同一份载荷、同一个目录,只差这一行)默认的
`fmlearlywindow` 是 `EXITED (15s)`、标题画面都没到,`none` 是 `STARTED (40s)`。

**证据在哪**:逐条的实测记录(包括失败的、被否定的结论)在 [`docs/MATRIX.md`](../../blob/1.20.x/docs/MATRIX.md)
(项目的运行日志,随每轮实测追加),各分支自己的 `docs/` 里是版本矩阵与里程碑。原始启动日志不在仓库里:
每次启动的 `stdout.log` / `stderr.log` / `screen.png` 在本地 rig 的 `optifineoforge-test/logs/run-*`,
因为里面含游戏与 OptiFine 的输出,不进版本库。

## 命名与产物

- 显示名 **OptifiNeoforge**,mod id `optifineoforge`
- 产物 `OptifiNeoforge-<版本>+mc<Minecraft 版本>.jar`,版本号遵循 [语义化版本 2.0.0](https://semver.org/lang/zh-CN/),`+mc...` 是编译信息(SemVer §10),标签名与产物名一致
- **一个 Minecraft 版本一个产物**:jar 里的 `META-INF/neoforge.mods.toml` 把 `minecraft` 依赖钉成
  `[<该版本>,<该版本>]`,所以 `+mc1.21.4` 的 jar 在 1.21.8 上不会被加载 —— 请挑与自己游戏版本一致的那个
- `1.0.0` 的含义按各分支的 `docs/VERSIONING.md`:它由一次真实启动记录支撑(上面的矩阵),不是"功能完备"的断言
- 已发布的 10 个版本在 [Releases](../../releases) 页,每个 Release 附**一个** jar 与那一条线的实测说明;标签名与产物名一致
- 版本号只通过 `release/version.ps1` 修改;发布流程见 `docs/PUBLISHING.md`(在三条开发分支上)

**发布的 jar 里没有 OptiFine**:仓库与 Release 都只提供加载器侧的修补。要得到能直接放进 `mods/` 的成品,
按项目流水线对**你自己下载的** OptiFine jar 做重打包与打补丁 —— 上面每一条验收数字都是这样量出来的。

## 许可

**MPL-2.0**(`LICENSE`)。加载思路与部分代码移植自 [Chocohead/OptiFabric](https://github.com/Chocohead/OptiFabric)(作者 Modmuss50、Chocohead)。

**不包含、也不分发 OptiFine 本体**:OptiFine 版权归 sp614x 所有,请自行获取。
