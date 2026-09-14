# 发布流程

本文写的是**一条线一次发布**该怎么做。版本号的规则在 `docs/VERSIONING.md`,版本号只通过
`release/version.ps1` 修改。

## 现状(先说实话)

- **还没有真正发布过一次。** 三条线都停在 `0.x`:`0.x` 的含义是"加载器还没在真实游戏里跑起来"
  (见 `docs/VERSIONING.md`),目前只有 `1.21.4` 这一条能启动到主循环,而且首轮资源重载仍有一次贴图集失败
  (记录在 `docs/DEVELOPMENT.md`)。
- **构建依赖本地已有的 OptiFine jar。** 仓库**不包含、也不分发** OptiFine;`test-downloads/` 被
  `.gitignore` 排除,构建脚本从那里取输入。还没有"从镜像自动下载指定版本 OptiFine"的 CI 流程。
- **只有 GitHub Release 这一条渠道。** CurseForge / Modrinth 的账号、审核与上传脚本都还没有。
- 因此 0.x 阶段的产物**按预发布对待**:可以挂在 GitHub Release 上,但不往公开模组平台发。

## 前置条件

| 线 | Minecraft | NeoForge | Java | 分支 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 见该分支 `gradle.properties` | 17 / 21 | `1.20.x` |
| 1.21.x | 1.21.1 … 1.21.11 | 见该分支 `gradle.properties` | 21 | `1.21.x` |
| 26.x | 26.1.2 | 见该分支 `gradle.properties` | 25 | `26.x` |

发布前必须确认:在**正确分支**上;`gradle.properties` 里的 `minecraft_version` 就是这次要发的版本;
以及**这条线真的跑过** `optifineoforge-test` 里的启动脚本(不要凭另一条线的结果代替)。

## 步骤

1. **升级版本号**(只这样做,不要手改 `gradle.properties`):

   ```
   .\release\version.ps1 show          # 现在是什么,产物会叫什么
   .\release\version.ps1 patch         # 或 minor / major,按 docs/VERSIONING.md 的档位表
   ```

   脚本会打印产物名,形如 `OptifiNeoforge-0.1.1+mc26.1.2.jar`。

2. **把 `gradle.properties` 与这次改动的说明放在同一个提交里**,说明写清"为什么要升这一档"。

3. **构建**:

   ```
   .\gradlew build
   ```

   产物在 `build/libs/OptifiNeoforge-<版本>+mc<Minecraft 版本>.jar`。构建前请把这条线对应的
   OptiFine jar 放进 `test-downloads/`(构建与重打包工具从那里取输入)。

4. **记录**:把这一轮验证到的、以及验证失败的都写进 `docs/DEVELOPMENT.md`(那份文档就是本项目的
   实测记录,负面结果同样要记)。

5. **打标签并推送**:

   ```
   git tag -a v0.1.1+mc26.1.2 -m "26.x 0.1.1+mc26.1.2"
   git push origin v0.1.1+mc26.1.2
   ```

   标签名与 jar 版本一致,便于"某个版本号对某个产物"。

6. **创建 GitHub Release**,附上第 3 步的 jar,正文至少写:对应的 Minecraft 与 NeoForge 版本、
   这一版能做什么/不能做什么(尤其"加载器是否已在游戏中跑起来"),以及已知问题。

## 不要做的事

- **不要把 OptiFine 的 jar 提交或附进 Release。** 本项目只提供加载器侧的修补,OptiFine 由用户自己
  从官方渠道获取;仓库许可证是 MPL-2.0,但 OptiFine 的再分发条款与本项目无关。
- **不要在一条分支上为另一条线的 Minecraft 版本升版本号。** 三条线的版本号互不相关
  (`docs/VERSIONING.md`),`+mc...` 的编译信息就是为了防止"同一个版本号对应两个产物"。
- **不要在没有实机验证的情况下把 `0.x` 提升到 `1.0.0`。** 升到 `1.0.0` 是在断言"加载器确实能在
  游戏里跑起来",这要由一次真实的启动记录(rig 的 `VERDICT: STARTED` 与截图)来支撑。
