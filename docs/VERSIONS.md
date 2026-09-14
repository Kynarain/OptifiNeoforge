# 版本矩阵(26.x 线)

本线只对应 **Minecraft 26.1.2**。数据来源与核对方式写在表后。

| 项目 | 值 | 依据 |
|---|---|---|
| Minecraft | `26.1.2` | OptiFine 只有这一版的构建 |
| NeoForge | `26.1.2.109` | `maven.neoforged.net` 元数据里 `26.1` 线的最新构建 |
| Java | **25** | 26.1.2 自身的运行要求 |
| 运行期命名空间 | 官方名(未混淆) | 26.1 起游戏不再混淆,没有映射表 |
| mod 元数据文件 | `META-INF/neoforge.mods.toml` | NeoForge 的现代格式;待实测确认 |
| 载入方式 | ModLauncher(NeoForge 自带) | OptiFine 的入口是 `cpw.mods.modlauncher.api.ITransformationService` |
| mod id | `optifineoforge` | 本项目 |
| 产物 | `OptifiNeoforge-<版本>+mc26.1.2.jar` | 本项目 |

## 26.1.2 的全部 OptiFine 构建

| 类型 | 补丁号 | 文件名 |
|---|---|---|
| preview | `HD_U_K1_pre1` | `preview_OptiFine_26.1.2_HD_U_K1_pre1.jar` |
| preview | `HD_U_K1_pre2` | `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` |

下载(第三方镜像,会 302 跳到官方分发;注意 preview 的路径是四段):

```powershell
curl.exe -L -o preview_OptiFine_26.1.2_HD_U_K1_pre2.jar `
  "https://bmclapi2.bangbang93.com/optifine/26.1.2/HD_U_K1/pre2"
```

自查某个版本有没有构建(判据是返回的正文是否为空数组,不要只看状态码):

```powershell
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/26.1.2"   # -> pre1 / pre2
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/26.1.1"   # -> []
curl.exe -s "https://bmclapi2.bangbang93.com/optifine/26.2"     # -> []
```

## 为什么这条线只有一版

`26.1`、`26.1.1`、`26.2`、`26.2.1` 等版本在 OptiFine 的构建列表里都是空数组 —— 没有 OptiFine 就没有可移植的对象。NeoForge 侧这些版本都有构建(`26.2.0.88` 等),但本项目只支持有 OptiFine 的版本。

## 待确认(骨架阶段的已知缺口)

- `neoforge.mods.toml` 的具体字段要求(`loaderVersion` 的取值范围、`modLoader` 取值)需要对着 NeoForge 26.1.2 的文档核对。
- NeoForge 26.1.2 的 ModLauncher 是否仍会从 `mods/` 里发现第三方的 `ITransformationService`(Forge 时代由 `ModDirTransformerDiscoverer` 负责),还是必须改用 NeoForge 自己的转换 API —— 见 `docs/RESEARCH-neoforge.md`。
- OptiFine 的 26.1.2 构建用的是哪种命名空间的补丁负载,以及它的 `optifine.Patcher` 在未混淆的游戏 jar 上是否仍按老流程工作 —— 见 `docs/RESEARCH-optifine.md`。

## 数据来源

- OptiFine 构建列表:`https://bmclapi2.bangbang93.com/optifine/versionlist`(497 个 MC 版本)与 `https://bmclapi2.bangbang93.com/optifine/<MC 版本>`。
- NeoForge 版本:`https://maven.neoforged.net/releases/net/neoforged/neoforge/maven-metadata.xml`。
- 核对时间:2026-09-14。
