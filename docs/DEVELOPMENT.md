# 开发记录(26.x 线)

本文件记录本线自己的实测,以及跨线共用的两个调研结论的入口:`docs/RESEARCH-optifine.md`(OptiFine jar 的结构与补丁机制,7 个构建逐个拆开看)与 `docs/RESEARCH-neoforge.md`(NeoForge/FML 侧每个版本允许什么)。

## 怎么读 OptiFine 的 jar

`src/main/java/kynarain/cn/optifineoforge/optifine/OptifineJar.java` 与 `OptifineConfig.java` 只依赖 JDK,不依赖 Minecraft、NeoForge 或任何 loader,所以可以直接 `javac` 编译后对着真实 jar 跑:

```powershell
javac -d out src\main\java\kynarain\cn\optifineoforge\optifine\*.java
java -cp out kynarain.cn.optifineoforge.optifine.OptifineJar <OptiFine jar 路径>
java -cp out kynarain.cn.optifineoforge.optifine.OptifineConfig <OptiFine jar 路径>
```

OptiFine 的 jar 不进仓库(`test-downloads/` 已忽略)。下载走第三方镜像,注意**它返回的 302 里 `Location` 是相对路径**,`curl -L` 不一定跟得下去,直接用镜像的 maven 路径更稳:

```powershell
curl.exe -sSL -o preview_OptiFine_26.1.2_HD_U_K1_pre2.jar `
  "https://bmclapi2.bangbang93.com/maven/com/optifine/26.1.2/preview_OptiFine_26.1.2_HD_U_K1_pre2.jar"
```

## 实测:7 个构建的结构(2026-09-14)

| OptiFine 构建 | 大小 | 条目 | 元数据 | ModLauncher 服务 | `notch/` | `srg/` | `patch/` |
|---|---|---|---|---|---|---|---|
| 1.20.1 HD_U_I6 | 7,145,205 | 6493 | `mods.toml` | 有 | 703 | 635 | 4888 |
| 1.20.4 HD_U_I7 | 7,232,045 | 6568 | `mods.toml` | 有 | 711 | 643 | 4948 |
| 1.21.1 HD_U_J1 | 7,322,249 | 6594 | `mods.toml` | 有 | 736 | 660 | 4924 |
| 1.21.6 HD_U_J6_pre3 | 7,518,992 | 6928 | `mods.toml` | 有 | 773 | 709 | 5172 |
| 1.21.7 HD_U_J6_pre7 | 7,587,441 | 6986 | `mods.toml` | 有 | 776 | 712 | 5224 |
| 1.21.11 HD_U_J9 | 8,045,105 | 7351 | `mods.toml` | 有 | 827 | 756 | 5496 |
| 26.1.2 HD_U_K1_pre2 | 7,797,229 | 7356 | `mods.toml` | 有 | 829 | 759 | 5490 |

1. **全都是安装器形态**:带 `patch/`(xdelta 差分包 + 同名 `.md5`)与 `optifine/Installer`。
2. **元数据一律是 Forge 的** `META-INF/mods.toml`(`modLoader="javafml"`,`loaderVersion="[14,)"`,`modId="optifine"`),**没有一个构建带 `neoforge.mods.toml`** —— 包括 26.1.2。
3. **每个构建都注册 ModLauncher 服务**:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` → `optifine.OptiFineTransformationService`,转型器是 `optifine.OptiFineTransformer`(26.1.2 里改名为 `OptiFineBaseTransformer`,常量值不变),只依赖 ModLauncher、ASM 与 log4j。
4. **`patch/` 下有三个前缀**,不是一个:`patch/srg/`、`patch/notch/`、`patch/assets/`(1.20.1:824 / 822 / 3242 条)。转型器只读 `patch/srg/` 与 `srg/`,**从不碰 `notch/`**。
5. **补丁负载的命名空间在 1.20.6 换过一次**:1.20.1 / 1.20.2 / 1.20.4 的负载里是**真正的 SRG 成员名**(`f_127499_` 之类),OptiFine 自己的 `srg/net/optifine/**` 也是;从 **1.20.6** 起负载与自家类里**一个 SRG 成员都没有**,已经是 Mojang 官方名 —— `srg/` 从那时起只是沿用的目录名。
6. **26.1.2 走得更远**:`patch/srg` 与 `patch/notch` 的 566 个同名负载**逐字节相同**,负载里没有任何混淆名,`patch2.cfg` 把 srg 映射成恒等;它同时**自带 NeoForge 自己的 SPI**:`META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` 与 `...locating.IModFileCandidateLocator` 都指向 `optifine.OptiFineClassProcessor`,manifest 里还有 `FMLModType: LIBRARY`。

## 26.1.2:客户端 jar 与离线补丁实测(2026-09-14)

本机从 Mojang 官方清单取到 26.1.2 的 client jar(38,113,927 字节,30,675 个条目),确认它**未混淆**:`net/minecraft/client/Minecraft.class` 存在,形如 `a.class` 的混淆类 **0 个**。

用同一套反射调用对它跑 OptiFine 的补丁器:

| 项目 | 结果 |
|---|---|
| 耗时 | 1.5 秒 |
| 输出 | 7,898,538 字节,4611 个条目 |
| 输出的目录构成 | `assets/` 1785、`notch/` 1395、`srg/` 1325、`optifine/` 53、`doc/` 39,另有 `patch.cfg` / `patch2.cfg` |
| 被补丁的游戏类 | 972 个,名字都是官方名(与"游戏未混淆"一致) |
| 报错 | 无;退出码 0 |

也就是说:**未混淆的 26.1.2 上,OptiFine 自己的补丁器可以正常工作,产出的补丁类已经是运行期用的官方名 —— 这一线不需要任何重映射**。这是整条链上第二个被离线验证的环节(第一个是 1.20.1,见 `docs/RESEARCH-optifine.md` 与上表)。

## 补丁产物的拆分(2026-09-14)

补丁器的输出还要拆成两份,`OptifinePipeline.split` 做这件事:一份给 classpath(OptiFine 自己的类与资源),一份是被补丁的游戏类(按内部名索引,用于顶替原版)。两条规则:

- **只取 `srg/` 那一份变体** —— 转型器读的就是它,`notch/` 那份丢弃,免得多出一套同名的类。
- **丢掉安装器**:`optifine/Installer*`、`optifine/Patcher*`、`optifine/Differ*`、`optifine/xdelta/**`、`optifine/json/**` —— 我们自己跑补丁器,运行期不需要它们,而 loader 只要看见 `optifine/Installer.class` 就会拒绝整个 jar(见 `docs/RESEARCH-neoforge.md`)。其余一切(OptiFine 的 `assets/`、`doc/`、`META-INF/services/**`)原样保留。

实测:

| | 26.1.2 | 1.20.1 |
|---|---|---|
| 补丁耗时 | 1.5 秒 | 1.2 秒 |
| 补丁器输出 | 7,898,538 字节 / 4611 条目 | 6,638,739 字节 / 4049 条目 |
| 拆分后 classpath jar | 2,789,483 字节 / 2616 条目(`assets/` 1785、`net/optifine/` 759、`doc/` 39、根 `optifine/` 19、`META-INF/` 5) | 2,638,138 字节 / 2489 条目 |
| 被补丁的游戏类 | **566**(其中 `net/minecraft` 486) | **412**(其中 `net/minecraft` 354) |
| `optifine/Installer.class` | 不存在(已剔除) | 不存在(已剔除) |

拆出来的类是否**可验证**(JVM 校验通过)还没测 —— 那需要 ASM 或 `ClassLoader` 层面的检查,以及真机加载。

## 首次真机实测:NeoForge 21.4.149(Minecraft 1.21.4,2026-09-14)

本机装好了 JDK 21 与 25(`C:\Users\kynar\.jdks\`),于是可以真的启动一次 NeoForge。用的是用户启动器里已有的 `1.21.4-NeoForge_21.4.149`(FML 6.0.18、ModLauncher 11.0.4),配一个独立的游戏目录与独立的 `mods/`,不动用户自己的配置。测试脚本在仓库外的 `C:\Users\kynar\IdeaProjects\optifineoforge-test\launch-neoforge.ps1`。

**对照(无模组)**:启动到标题界面,40 秒,`Sound engine started`,无崩溃报告 —— 测试台本身可用。

**把重新打包过的 OptiFine 1.21.4(J3)放进 `mods/`**,依次出现三个问题,前两个已解决:

1. **`NoSuchFileException: ...optifine-1.21.4-for-neoforge.jar#214`** —— OptiFine 用自己类的 code source 找 jar(`getProtectionDomain().getCodeSource().getLocation()`),在 NeoForge 下这是 union 文件系统路径 `union:/...jar%23214!/`,它只去掉结尾的 `!`,留下 `#214`,于是 `ZipFile` 打不开,ModLauncher 直接判定 `InvalidLauncherSetupException: Invalid Services found OptiFine`。
   → **修法**:`OptifineJarFixer` 重写 `optifine.OptiFineTransformationService.toFile(URI)`,把 `!` 之后与 `#` 之后的部分一并截掉(重写后的方法要重算栈帧,否则 JVM 报 `Expected stackmap frame at this location`)。打包时自动应用。
2. 修好之后,**OptiFine 自己的转换服务在 NeoForge 上跑起来了**:

   ```
   [optifine.OptiFineTransformationService]: OptiFine ZIP file: ...\mods\optifine-1.21.4-for-neoforge.jar
   [optifine.OptiFineTransformer]: Target.PRE_CLASS is available
   [optifine.OptiFineTransformer]: Forge JAR not available
   [optifine.OptiFineTransformationService]: OptiFineTransformationService.transformers
   [optifine.OptiFineTransformer]: Targets: 474
   ```

   即:服务被发现、jar 被打开、**474 个补丁目标注册成功**,ModLauncher 不再拒绝。这是整条路线的第一个真机证据。
3. **下一个拦路的问题(尚未解决)**:FML 的早期窗口阶段 `DisplayWindow.updateModuleReads` 会去扫方法签名,而 OptiFine 的类引用了 **Forge 的 API 类型** `net.minecraftforge.client.extensions.IForgeVertexConsumer` —— NeoForge 上这个包不存在(`net.neoforged.neoforge.*`),于是 `ClassNotFoundException` 让启动中止(见下一节)。

## Forge API 与桩类(2026-09-14)

**关键发现:引用不在类里,而在补丁负载里。** `IForgeVertexConsumer` 这个名字在整个 jar 的 `.class` 里一次都不出现,只出现在 `patch/srg/com/mojang/blaze3d/vertex/VertexConsumer.class.xdelta` —— 也就是 **OptiFine 的补丁让原版 `VertexConsumer` 去实现 Forge 的接口**。所以只扫类文件是不够的,必须连 `patch/**` 一起扫。

处理办法(`ForgeApiShims` + `OptifineJar` 两处):

- 扫 jar 里**所有**条目(跳过 `assets/`、`doc/` 与 `notch/`),收集全部 `net/minecraftforge/**` 名字 —— 1.21.4 J3 上是 **55 个**;
- 为每个名字生成一个**空桩类**(接口还是类,按 OptiFine 自带的那份 `notch/<同名>` 的访问标志决定),打进交给 loader 的 jar;
- 顺带**丢掉整个 `notch/` 树**(1.21.4 上是 768 个条目):那是混淆命名空间的变体,转型器从不读它,而它的签名指向混淆后的游戏类型(`gng`、`akv`),留着只会把同一个失败挪到下一个类型。

**结果:启动越过了签名扫描阶段,OptiFine 真的跑起来了**:

```
[OptiFine] (Reflector) Class not present: net.minecraftforge.common.extensions.IForgeEntity
[OptiFine] OptiFine_1.21.4_HD_U_J3
[OptiFine] Build: 20250209-131348
[OptiFine] OS: Windows 11 (amd64) version 10.0
[OptiFine] Java: 21.0.12.1, Eclipse Adoptium
[OptiFine] OpenGL: NVIDIA GeForce RTX 4060 Laptop GPU/PCIe/SSE2, version 3.2.0 NVIDIA 591.86
```

**下一个失败(已定位,未解决)**:游戏在 `Minecraft.<init>` 崩:

```
java.lang.NoSuchMethodError: 'void com.mojang.blaze3d.pipeline.RenderTarget.<init>(boolean, boolean)'
	at com.mojang.blaze3d.pipeline.MainTarget.<init>(MainTarget.java:22)
```

方向:OptiFine 的补丁改过 `RenderTarget` 的构造函数,而 `MainTarget` 这一侧仍是原版形状 —— 两者要**成对**应用。可能是补丁应用顺序(OptiFine 的转型器与 NeoForge 自己的转型器在同一批类上先后运行)、也可能某个 xdelta 没有成功应用。下一步就是把这个配对问题查清楚。

### 查清了:OptiFine 的整类替换会丢掉 NeoForge 侧还在用的成员

对比三份 `RenderTarget`(都是 javap 出来的):

| 来源 | 构造函数 |
|---|---|
| NeoForge 运行时(NeoForm `client-1.21.4-...-srg.jar`) | `RenderTarget(boolean)`、`RenderTarget(boolean, boolean)` —— 一参那个只是转调二参 |
| NeoForge 的 `MainTarget` | 调 `super(ZZ)` |
| OptiFine 补丁产物(`srg/com/mojang/blaze3d/pipeline/RenderTarget.class`) | **只有 `RenderTarget(boolean)`** |

也就是说:OptiFine 用**自己编译的一份** RenderTarget 顶掉了 NeoForge 的那份,而那份是按 Forge 的形状编的,少了 NeoForge 调用方要的重载。同一个模式还会丢字段 —— 修掉构造函数之后,下一个错误就是 NeoForge 的 `MainTarget.allocateDepthAttachment` 找不到字段 `useStencil`(OptiFine 那份把它改名成了 `stencilEnabled`)。

**处理办法**:加一个我们自己的 ModLauncher 转型服务(`OptifiNeoforgeTransformationService` + `RenderTargetFix`),在 `TargetType.CLASS` 阶段把 OptiFine 丢掉的成员补回去(目前是那个二参构造函数,转调一参)。实测:

```
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, fml, OptifiNeoforge]
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.transformers
```

服务与 OptiFine 的服务同时被加载,`NoSuchMethodError: RenderTarget.<init>(ZZ)` 消失,失败点推进到下一个缺失成员(`NoSuchFieldError: ... MainTarget does not have member field 'boolean useStencil'`)。**结论:修复方向对了,只是要逐项补齐 OptiFine 替换掉的成员**(字段 + 方法),这一类修复可以继续按同样方式加。

### 测试台踩到的三个坑

- **FML 6 不接受 `mods/` 里没有元数据的 jar**(日志:`not a valid mod file`)——"transformer jar 不需要 mod 元数据"这条老规矩在这里不成立。测试台的做法是把我们的服务类塞进那个已经被接受的 OptiFine jar,并把服务文件**追加**一行(一个服务文件可以列多个实现)。
- **PowerShell 变量名不区分大小写**:`$Out` 与 `$out` 是同一个变量,脚本里同时用作输出路径与流对象时会互相覆盖。
- **.NET Framework 的 `ZipFile.CreateFromDirectory` 写出的条目名用反斜杠**,而 SecureJar 的 union 文件系统只认正斜杠,表现为 `UnionFileSystem$NoSuchFileException: kynarain/cn/.../RenderTargetFix.class`。手工按 `/` 写条目即可。

## 尚未确认

- 上面第 3 条之外还有多少 `net.minecraftforge.*` 引用需要处理(1.21.4 的 OptiFine 里有一批)。
- 1.20.6 – 1.21.8 这条区间是否都像 1.21.4 一样能被 NeoForge 接受(1.20.6 起 FML 还会额外拒绝"原版 OptiFine jar",我们已剔除安装器入口,但未逐个实测)。
- 补丁类被 NeoForge 应用后,OptiFine 运行期的 MD5 校验是否仍然通过。
- 1.21.9 及以后:ModLauncher 已不存在,必须改用 NeoForge 的 `ClassProcessor`,这条路还没试。

## 这对加载器意味着什么

| MC | OptiFine 负载的命名空间 | NeoForge 运行期命名空间 | 结论 |
|---|---|---|---|
| 1.20.1 | SRG | SRG(`net.neoforged:forge:47.x`) | 跑补丁器后用 `srg/` 那份即可,**不需要重映射** |
| 1.20.2 / 1.20.4 | SRG | 官方名(20.2 起) | 跑补丁器后要**把 SRG 重映射成官方名** |
| 1.20.6 – 1.21.8 | 官方名 | 官方名 | 负载直接可用;元数据必须改写;**这一区间仍能复用 OptiFine 自己的 ModLauncher 转换服务**(≤ FML 9) |
| 1.21.9 – 1.21.11 | 官方名 | 官方名 | **ModLauncher 已被 NeoForge 移除**,而这三版 OptiFine 既没有 `ClassProcessor` 也没有可用的入口 → 只能由我们跑补丁器并实现 `ClassProcessor` |
| 26.1.2 | 官方名(未混淆) | 官方名(未混淆) | OptiFine **自带 NeoForge 的 `ClassProcessor`**,我们的工作是重新打包 jar(元数据 + 去掉安装器入口)并把补丁类接上 |

两条已知的硬约束(见 `docs/RESEARCH-neoforge.md`):从 **1.20.6** 起 FML 有一条针对原版 OptiFine jar 的拒绝规则(探针是 `optifine/Installer.class`),所以必须重新打包;**1.21.3** 起 OptiFine 声明的 `loaderVersion` 会校验失败,元数据必须改写。

## 尚未确认

- `notch/net/minecraftforge/**`(70 个类,总计约 56–64 KB)是完整实现还是桩;1.21.11 的 `srg/net/optifine/shaders/ShadersRender` 里有**硬引用** `net/minecraftforge/client/event/ViewportEvent$ComputeCameraAngles`,而 NeoForge 上是 `net.neoforged.neoforge.*` —— 这是已知的第一个必然要修的点。
- 1.20.6 起"负载已是官方名"这条规则是从 7 个构建外推的,`1.21`、`1.21.3`、`1.21.4`、`1.21.8`、`1.21.9`、`1.21.10` 未逐个确认。
- OptiFine 运行期会用 MD5 校验补丁结果,而 NeoForge 自己也会改原版类 —— 两者叠加后校验是否还通过,只能在真机上看。
- 本机没有 JDK 25,26.1.2 的 NeoForge 实例还起不来,因此"能不能进游戏"这一层尚未验证。
