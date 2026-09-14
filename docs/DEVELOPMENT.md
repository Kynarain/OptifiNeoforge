# 开发记录(26.x 线)

## 怎么读 OptiFine 的 jar

`src/main/java/kynarain/cn/optifineoforge/optifine/OptifineJar.java` 只依赖 JDK,不依赖 Minecraft、NeoForge 或任何 loader,所以可以直接用 `javac` 编译后对着真实 jar 跑:

```powershell
javac -d out src\main\java\kynarain\cn\optifineoforge\optifine\OptifineJar.java
java -cp out kynarain.cn.optifineoforge.optifine.OptifineJar <OptiFine jar 路径>
```

它输出 jar 的元数据文件名、ModLauncher 服务、顶层目录构成、补丁负载的后缀,以及 manifest 的主要字段;第二种调用形式会把重写元数据后的副本写到指定路径。

OptiFine 的 jar 不进仓库(`test-downloads/` 已忽略)。下载走第三方镜像,注意**它返回的 302 里 `Location` 是相对路径**,`curl -L` 有时跟不下去,直接用镜像的 maven 路径更稳:

```powershell
curl.exe -sSL -o preview_OptiFine_26.1.2_HD_U_K1_pre2.jar `
  "https://bmclapi2.bangbang93.com/maven/com/optifine/26.1.2/preview_OptiFine_26.1.2_HD_U_K1_pre2.jar"
```

## 实测:7 个构建的结构(2026-09-14)

| OptiFine 构建 | 大小 | 条目 | 元数据 | 转换服务 | `notch/` | `srg/` | `patch/` |
|---|---|---|---|---|---|---|---|
| 1.20.1 HD_U_I6 | 7,145,205 | 6493 | `mods.toml` | 有 | 703 | 635 | 4888 |
| 1.20.4 HD_U_I7 | 7,232,045 | 6568 | `mods.toml` | 有 | 711 | 643 | 4948 |
| 1.21.1 HD_U_J1 | 7,322,249 | 6594 | `mods.toml` | 有 | 736 | 660 | 4924 |
| 1.21.6 HD_U_J6_pre3 | 7,518,992 | 6928 | `mods.toml` | 有 | 773 | 709 | 5172 |
| 1.21.7 HD_U_J6_pre7 | 7,587,441 | 6986 | `mods.toml` | 有 | 776 | 712 | 5224 |
| 1.21.11 HD_U_J9 | 8,045,105 | 7351 | `mods.toml` | 有 | 827 | 756 | 5496 |
| 26.1.2 HD_U_K1_pre2 | 7,797,229 | 7356 | `mods.toml` | 有 | 829 | 759 | 5490 |

从 1.20.1 到 26.1.2,**每一个构建的结构都一样**:

1. **全都是安装器形态**:带 `patch/`(xdelta 差分包)与 `optifine/Installer`。
2. **元数据一律是 Forge 的** `META-INF/mods.toml`(`modLoader="javafml"`,`loaderVersion="[14,)"`,`modId="optifine"`),**没有一个构建带 `neoforge.mods.toml`** —— 包括 26.1.2。要 NeoForge 认它,这一份必须重写。
3. **每个构建都注册同一个 ModLauncher 服务**:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` → `optifine.OptiFineTransformationService`;转型器是 `optifine.OptiFineTransformer implements cpw.mods.modlauncher.api.ITransformer<ClassNode>`,两个类的常量池里都没有 `net.minecraftforge.*`。这是"复用 OptiFine 自己的转换服务"这条路线的依据。
4. **同一套类有两个命名空间变体**:`net/optifine/Config.class`、`net/optifine/BetterGrass.class` 之类在 `notch/` 与 `srg/` 下各有一份;而游戏类只在 `notch/` 下有(`notch/net/minecraft/client/ClientBrandRetriever.class` 存在,`srg/` 下没有)。补丁负载则统一挂在 `patch/srg/` 下(`patch/srg/com/mojang/blaze3d/...class.xdelta` + 同名 `.md5`)。
5. **26.1.2 的补丁负载明显更薄**:同样是 `patch/srg/` 布局,但未混淆版本上很多条目是 12 字节的 xdelta(等于"无需改动"),而不是 1.20.1 那种几 KB 的真实差异 —— 与"游戏本身已经未混淆"一致。

## 这对加载器意味着什么

- **路线 A(复用 OptiFine 的转换服务)在整个版本区间上都成立**:服务类与转型器在 1.20.1 到 26.1.2 的每一个构建里都在,且只依赖 ModLauncher。要做的第一件事是把 Forge 的 `mods.toml` 换成 NeoForge 读的那份。
- **命名空间的选择有据可依**:jar 里同时带 `notch/` 与 `srg/` 变体,所以"用哪一份"取决于目标运行期命名空间(1.20.x/1.21.x 的 NeoForge 用 SRG 还是官方名,以及 26.1.2 的未混淆官方名)。这一点还没定论,见 `docs/VERSIONS.md` 的待确认项。
- **补丁负载是 xdelta**,不是整类替换:OptiFabric 的做法(调用 `optifine.Patcher`)在我们的场景里仍然适用,前提是拿到它期望的那份原版 jar。

## 尚未确认

- NeoForge 各版本的运行期命名空间(是否为 SRG),以及"第三方的 `ITransformationService` 还能不能从 `mods/` 里被发现"。
- OptiFine 的 `patch/srg/` 前缀在 26.1.2 上究竟是"SRG 名"还是只是沿用的目录名(条目名看起来就是官方名)。
- `optifine.Patcher` 在未混淆的游戏 jar 上的行为:是否需要先把它转成混淆名,还是可以直接打补丁。
