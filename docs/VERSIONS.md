# 版本矩阵(1.20.x 线)

本线覆盖 **Minecraft 1.20.1、1.20.2、1.20.4、1.20.6** —— OptiFine 在这条线上出过构建的全部四个版本。数据来源与核对方式写在文末。

## 四个版本各自的参数

| Minecraft | NeoForge | Java | OptiFine 构建数 | 运行期命名空间 | mod 元数据文件 |
|---|---|---|---|---|---|
| `1.20.1` | `net.neoforged:forge:1.20.1-47.1.106` | 17 | 14(2 正式版 + 12 preview) | SRG(待确认) | `META-INF/mods.toml`(待确认) |
| `1.20.2` | `net.neoforged:neoforge:20.2.93` | 17 | 1(0 正式版 + 1 preview) | SRG(待确认) | `META-INF/mods.toml`(待确认) |
| `1.20.4` | `net.neoforged:neoforge:20.4.251` | 17 | 11(1 正式版 + 10 preview) | SRG(待确认) | `META-INF/mods.toml`(待确认) |
| `1.20.6` | `net.neoforged:neoforge:20.6.141` | 21 | 3(0 正式版 + 3 preview) | SRG 或官方名(待确认) | `META-INF/neoforge.mods.toml`(待确认) |

- NeoForge 版本是各线在 `maven.neoforged.net` 元数据里的**最新构建**(核对时间见文末),不是"已验证可用"的版本。
- **元数据文件名与运行期命名空间这两列都是预期值**,列在这里是为了说明这条线内部有分界,而不是结论;确切切换点见待确认一节。
- 1.20.1 的 NeoForge 是 Forge 时代的产物,坐标是 `net.neoforged:forge:1.20.1-47.1.106`(该线最新的 `47.1.x`),与 1.20.2 起的 `net.neoforged:neoforge` 不同。

## 全线共有的参数

| 项目 | 值 | 依据 |
|---|---|---|
| mod id | `optifineoforge` | 本项目 |
| 产物 | `OptifiNeoforge-<版本>+mc<MC 版本>.jar` | 本项目 |
| 载入方式 | ModLauncher(NeoForge 自带) | OptiFine 的入口是 `cpw.mods.modlauncher.api.ITransformationService` |
| 骨架版本号 | `0.1.0` | 见 `docs/VERSIONING.md` |
| 状态 | 骨架 | 没有可用产物,也没有实测记录 |

## 1.20.1 的全部 OptiFine 构建(14 个)

**正式版**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I5` | `OptiFine_1.20.1_HD_U_I5.jar` |
| `HD_U_I6` | `OptiFine_1.20.1_HD_U_I6.jar` |

**preview**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I5_pre4` | `preview_OptiFine_1.20.1_HD_U_I5_pre4.jar` |
| `HD_U_I5_pre5` | `preview_OptiFine_1.20.1_HD_U_I5_pre5.jar` |
| `HD_U_I5_pre6` | `preview_OptiFine_1.20.1_HD_U_I5_pre6.jar` |
| `HD_U_I5_pre7` | `preview_OptiFine_1.20.1_HD_U_I5_pre7.jar` |
| `HD_U_I5_pre8` | `preview_OptiFine_1.20.1_HD_U_I5_pre8.jar` |
| `HD_U_I5_pre9` | `preview_OptiFine_1.20.1_HD_U_I5_pre9.jar` |
| `HD_U_I6_pre1` | `preview_OptiFine_1.20.1_HD_U_I6_pre1.jar` |
| `HD_U_I6_pre2` | `preview_OptiFine_1.20.1_HD_U_I6_pre2.jar` |
| `HD_U_I6_pre3` | `preview_OptiFine_1.20.1_HD_U_I6_pre3.jar` |
| `HD_U_I6_pre4` | `preview_OptiFine_1.20.1_HD_U_I6_pre4.jar` |
| `HD_U_I6_pre5` | `preview_OptiFine_1.20.1_HD_U_I6_pre5.jar` |
| `HD_U_I6_pre6` | `preview_OptiFine_1.20.1_HD_U_I6_pre6.jar` |

## 1.20.2 的全部 OptiFine 构建(1 个)

**正式版**

无。

**preview**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I7_pre1` | `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar` |

1.20.2 只有这一个构建,没有正式版。它能否作为移植对象尚未验证。

## 1.20.4 的全部 OptiFine 构建(11 个)

**正式版**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I7` | `OptiFine_1.20.4_HD_U_I7.jar` |

**preview**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I7_pre2` | `preview_OptiFine_1.20.4_HD_U_I7_pre2.jar` |
| `HD_U_I7_pre3` | `preview_OptiFine_1.20.4_HD_U_I7_pre3.jar` |
| `HD_U_I7_pre4` | `preview_OptiFine_1.20.4_HD_U_I7_pre4.jar` |
| `HD_U_I7_pre5` | `preview_OptiFine_1.20.4_HD_U_I7_pre5.jar` |
| `HD_U_I7_pre6` | `preview_OptiFine_1.20.4_HD_U_I7_pre6.jar` |
| `HD_U_I7_pre7` | `preview_OptiFine_1.20.4_HD_U_I7_pre7.jar` |
| `HD_U_I8_pre1` | `preview_OptiFine_1.20.4_HD_U_I8_pre1.jar` |
| `HD_U_I8_pre2` | `preview_OptiFine_1.20.4_HD_U_I8_pre2.jar` |
| `HD_U_I8_pre3` | `preview_OptiFine_1.20.4_HD_U_I8_pre3.jar` |
| `HD_U_I8_pre4` | `preview_OptiFine_1.20.4_HD_U_I8_pre4.jar` |

## 1.20.6 的全部 OptiFine 构建(3 个)

**正式版**

无。

**preview**

| 补丁号 | 文件名 |
|---|---|
| `HD_U_I9_pre1` | `preview_OptiFine_1.20.6_HD_U_I9_pre1.jar` |
| `HD_U_J1_pre17` | `preview_OptiFine_1.20.6_HD_U_J1_pre17.jar` |
| `HD_U_J1_pre18` | `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar` |

1.20.6 只有 preview,而且跳过了 `I9` 与 `J1` 的绝大多数 pre 版本(镜像列表里只有 `I9_pre1` 与 `J1_pre17`/`J1_pre18`)。

## 下载(第三方镜像)

镜像地址是 `<MC 版本>/<type>/<patch>`,三个字段直接取自构建列表接口的 `type` 与 `patch`;**正式版与 preview 的路径都是同样四段**,区别在切分方式:正式版的 `type` 是 `HD_U`、`patch` 是字母号(如 `I6`),preview 的 `type` 是 `HD_U_<字母号>`、`patch` 是 `pre<n>`。请求会 302 跳到官方分发,实测返回 `200` 且 `Content-Type: application/java-archive`。

```powershell
# 1.20.1:正式版 I6
curl.exe -L -o OptiFine_1.20.1_HD_U_I6.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.1/HD_U/I6"

# 1.20.1:最新 preview I6_pre6
curl.exe -L -o preview_OptiFine_1.20.1_HD_U_I6_pre6.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.1/HD_U_I6/pre6"

# 1.20.2:唯一的构建(只有 preview)
curl.exe -L -o preview_OptiFine_1.20.2_HD_U_I7_pre1.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.2/HD_U_I7/pre1"

# 1.20.4:正式版 I7,以及最新 preview I8_pre4
curl.exe -L -o OptiFine_1.20.4_HD_U_I7.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.4/HD_U/I7"
curl.exe -L -o preview_OptiFine_1.20.4_HD_U_I8_pre4.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.4/HD_U_I8/pre4"

# 1.20.6:最新 preview J1_pre18
curl.exe -L -o preview_OptiFine_1.20.6_HD_U_J1_pre18.jar `
  "https://bmclapi2.bangbang93.com/optifine/1.20.6/HD_U_J1/pre18"
```

自查某个版本有没有构建(判据是返回的正文是否为空数组,不要只看状态码):

```powershell
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/1.20.1"   # -> 14 条(I5、I6 与各自的 preview)
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/1.20.2"   # -> 1 条(I7_pre1)
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/1.20.3"   # -> []
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/1.20.5"   # -> []
```

## 为什么这条线是这四个版本

OptiFine 在 1.20 系列里只发布了 1.20.1、1.20.2、1.20.4、1.20.6 的构建:**1.20.3 与 1.20.5 的构建列表都是空数组**,没有 OptiFine 就没有可移植的对象。NeoForge 侧这两版都有构建,但本项目只支持有 OptiFine 的版本。1.20.2 虽然只有唯一一个 preview,仍然在矩阵里,因为它确实存在构建。

## 这条线内部的三处分界

1. **NeoForge 坐标**:1.20.1 是 `net.neoforged:forge:1.20.1-47.1.106`,1.20.2 起是 `net.neoforged:neoforge`。
2. **Java 版本**:1.20.1 / 1.20.2 / 1.20.4 是 17,1.20.6 起是 21。
3. **元数据文件名**:1.20.4 及更早预期读 `META-INF/mods.toml`,1.20.6 及之后预期读 `META-INF/neoforge.mods.toml` —— 确切切换点待确认。

因此这条线的四个产物各自独立构建、独立验证,**同一个 jar 不能跨版本使用**。

## 待确认(骨架阶段的已知缺口)

- **`mods.toml` → `neoforge.mods.toml` 的确切切换点**:1.20.4 及更早是 Forge 时代的 `/NeoForge`(预期读 `META-INF/mods.toml`),1.20.6 及之后预期读 `META-INF/neoforge.mods.toml`;这中间是否有一段两种都认,以及 20.2.x / 20.4.x 是否也已经接受 `neoforge.mods.toml`,都没有实测。
- **运行期命名空间的确切切换点**:预期 1.20.x 这条线是 **SRG**,官方名大约从 1.20.5/1.21 前后开始。本线的 1.20.6 到底属于哪一侧(还是要同时处理两套)没有核实,`docs/PLAN.md` 里的补丁管线要按这个结论才定得下来。
- **元数据的字段要求**:各版本 `mods.toml` / `neoforge.mods.toml` 的必填字段(`loaderVersion` 的取值范围、`modLoader` 取值等)需要对着对应 NeoForge 版本的文档核对。
- **OptiFine 的 ModLauncher 服务是否还会被自动发现**:Forge 时代由 `ModDirTransformerDiscoverer` 从 `mods/` 里发现第三方的 `ITransformationService`。NeoForge 20.2 / 20.4 / 20.6 是否保留这条发现路径、1.20.1 的 `net.neoforged:forge` 是否与之一致,都需要实测;若不再支持,就得改走 NeoForge 自己的转换 API。
- **OptiFine 侧的补丁负载**:这四个版本的 OptiFine jar 用的是哪种命名空间的补丁,以及 `optifine.Patcher` 在 Forge 时代的客户端 jar 上是否仍按老流程工作,未验证。
- **1.20.2 的唯一 preview**:`I7_pre1` 是否带上完整补丁负载、能否作为移植对象,未验证;这是本线最薄的一环。
- **1.20.1 的 `47.1.x` 与 20.x 的差异边界**:`net.neoforged:forge` 与 `net.neoforged:neoforge` 在 ModLauncher 版本、元数据读取与类转换 API 上的差异范围不清楚,可能需要两套实现。
- **"正式版"不等于"可用"**:构建列表只说明构建存在,不代表能在 NeoForge 上跑通;本线尚无任何实测记录。

## 数据来源

- OptiFine 构建列表:`https://bmclapi2.bangbang93.com/optifine/<MC 版本>`(`type`、`patch`、`filename` 三个字段);下载路径按 `<MC 版本>/<type>/<patch>` 拼出,已用 `curl.exe -I` 逐个核对到 302 目标文件名。
- NeoForge 版本:`https://maven.neoforged.net/releases/net/neoforged/neoforge/maven-metadata.xml`(20.2.93 / 20.4.251 / 20.6.141)与 `https://maven.neoforged.net/releases/net/neoforged/forge/maven-metadata.xml`(1.20.1-47.1.106,该线最新 `47.1.x`)。元数据 `<lastUpdated>` 为 `20260913090722`。
- 核对时间:2026-09-14。
