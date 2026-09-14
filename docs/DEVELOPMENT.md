# 寮€鍙戣褰?26.x 绾?

鏈枃浠惰褰曟湰绾胯嚜宸辩殑瀹炴祴,浠ュ強璺ㄧ嚎鍏辩敤鐨勪袱涓皟鐮旂粨璁虹殑鍏ュ彛:`docs/RESEARCH-optifine.md`(OptiFine jar 鐨勭粨鏋勪笌琛ヤ竵鏈哄埗,7 涓瀯寤洪€愪釜鎷嗗紑鐪?涓?`docs/RESEARCH-neoforge.md`(NeoForge/FML 渚ф瘡涓増鏈厑璁镐粈涔?銆?
## 鎬庝箞璇?OptiFine 鐨?jar

`src/main/java/kynarain/cn/optifineoforge/optifine/OptifineJar.java` 涓?`OptifineConfig.java` 鍙緷璧?JDK,涓嶄緷璧?Minecraft銆丯eoForge 鎴栦换浣?loader,鎵€浠ュ彲浠ョ洿鎺?`javac` 缂栬瘧鍚庡鐫€鐪熷疄 jar 璺?

```powershell
javac -d out src\main\java\kynarain\cn\optifineoforge\optifine\*.java
java -cp out kynarain.cn.optifineoforge.optifine.OptifineJar <OptiFine jar 璺緞>
java -cp out kynarain.cn.optifineoforge.optifine.OptifineConfig <OptiFine jar 璺緞>
```

OptiFine 鐨?jar 涓嶈繘浠撳簱(`test-downloads/` 宸插拷鐣?銆備笅杞借蛋绗笁鏂归暅鍍?娉ㄦ剰**瀹冭繑鍥炵殑 302 閲?`Location` 鏄浉瀵硅矾寰?*,`curl -L` 涓嶄竴瀹氳窡寰椾笅鍘?鐩存帴鐢ㄩ暅鍍忕殑 maven 璺緞鏇寸ǔ:

```powershell
curl.exe -sSL -o preview_OptiFine_26.1.2_HD_U_K1_pre2.jar `
  "https://bmclapi2.bangbang93.com/maven/com/optifine/26.1.2/preview_OptiFine_26.1.2_HD_U_K1_pre2.jar"
```

## 瀹炴祴:7 涓瀯寤虹殑缁撴瀯(2026-09-14)

| OptiFine 鏋勫缓 | 澶у皬 | 鏉＄洰 | 鍏冩暟鎹?| ModLauncher 鏈嶅姟 | `notch/` | `srg/` | `patch/` |
|---|---|---|---|---|---|---|---|
| 1.20.1 HD_U_I6 | 7,145,205 | 6493 | `mods.toml` | 鏈?| 703 | 635 | 4888 |
| 1.20.4 HD_U_I7 | 7,232,045 | 6568 | `mods.toml` | 鏈?| 711 | 643 | 4948 |
| 1.21.1 HD_U_J1 | 7,322,249 | 6594 | `mods.toml` | 鏈?| 736 | 660 | 4924 |
| 1.21.6 HD_U_J6_pre3 | 7,518,992 | 6928 | `mods.toml` | 鏈?| 773 | 709 | 5172 |
| 1.21.7 HD_U_J6_pre7 | 7,587,441 | 6986 | `mods.toml` | 鏈?| 776 | 712 | 5224 |
| 1.21.11 HD_U_J9 | 8,045,105 | 7351 | `mods.toml` | 鏈?| 827 | 756 | 5496 |
| 26.1.2 HD_U_K1_pre2 | 7,797,229 | 7356 | `mods.toml` | 鏈?| 829 | 759 | 5490 |

1. **鍏ㄩ兘鏄畨瑁呭櫒褰㈡€?*:甯?`patch/`(xdelta 宸垎鍖?+ 鍚屽悕 `.md5`)涓?`optifine/Installer`銆?2. **鍏冩暟鎹竴寰嬫槸 Forge 鐨?* `META-INF/mods.toml`(`modLoader="javafml"`,`loaderVersion="[14,)"`,`modId="optifine"`),**娌℃湁涓€涓瀯寤哄甫 `neoforge.mods.toml`** 鈥斺€?鍖呮嫭 26.1.2銆?3. **姣忎釜鏋勫缓閮芥敞鍐?ModLauncher 鏈嶅姟**:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 鈫?`optifine.OptiFineTransformationService`,杞瀷鍣ㄦ槸 `optifine.OptiFineTransformer`(26.1.2 閲屾敼鍚嶄负 `OptiFineBaseTransformer`,甯搁噺鍊间笉鍙?,鍙緷璧?ModLauncher銆丄SM 涓?log4j銆?4. **`patch/` 涓嬫湁涓変釜鍓嶇紑**,涓嶆槸涓€涓?`patch/srg/`銆乣patch/notch/`銆乣patch/assets/`(1.20.1:824 / 822 / 3242 鏉?銆傝浆鍨嬪櫒鍙 `patch/srg/` 涓?`srg/`,**浠庝笉纰?`notch/`**銆?5. **琛ヤ竵璐熻浇鐨勫懡鍚嶇┖闂村湪 1.20.6 鎹㈣繃涓€娆?*:1.20.1 / 1.20.2 / 1.20.4 鐨勮礋杞介噷鏄?*鐪熸鐨?SRG 鎴愬憳鍚?*(`f_127499_` 涔嬬被),OptiFine 鑷繁鐨?`srg/net/optifine/**` 涔熸槸;浠?**1.20.6** 璧疯礋杞戒笌鑷绫婚噷**涓€涓?SRG 鎴愬憳閮芥病鏈?*,宸茬粡鏄?Mojang 瀹樻柟鍚?鈥斺€?`srg/` 浠庨偅鏃惰捣鍙槸娌跨敤鐨勭洰褰曞悕銆?6. **26.1.2 璧板緱鏇磋繙**:`patch/srg` 涓?`patch/notch` 鐨?566 涓悓鍚嶈礋杞?*閫愬瓧鑺傜浉鍚?*,璐熻浇閲屾病鏈変换浣曟贩娣嗗悕,`patch2.cfg` 鎶?srg 鏄犲皠鎴愭亽绛?瀹冨悓鏃?*鑷甫 NeoForge 鑷繁鐨?SPI**:`META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` 涓?`...locating.IModFileCandidateLocator` 閮芥寚鍚?`optifine.OptiFineClassProcessor`,manifest 閲岃繕鏈?`FMLModType: LIBRARY`銆?
## 26.1.2:瀹㈡埛绔?jar 涓庣绾胯ˉ涓佸疄娴?2026-09-14)

鏈満浠?Mojang 瀹樻柟娓呭崟鍙栧埌 26.1.2 鐨?client jar(38,113,927 瀛楄妭,30,675 涓潯鐩?,纭瀹?*鏈贩娣?*:`net/minecraft/client/Minecraft.class` 瀛樺湪,褰㈠ `a.class` 鐨勬贩娣嗙被 **0 涓?*銆?
鐢ㄥ悓涓€濂楀弽灏勮皟鐢ㄥ瀹冭窇 OptiFine 鐨勮ˉ涓佸櫒:

| 椤圭洰 | 缁撴灉 |
|---|---|
| 鑰楁椂 | 1.5 绉?|
| 杈撳嚭 | 7,898,538 瀛楄妭,4611 涓潯鐩?|
| 杈撳嚭鐨勭洰褰曟瀯鎴?| `assets/` 1785銆乣notch/` 1395銆乣srg/` 1325銆乣optifine/` 53銆乣doc/` 39,鍙︽湁 `patch.cfg` / `patch2.cfg` |
| 琚ˉ涓佺殑娓告垙绫?| 972 涓?鍚嶅瓧閮芥槸瀹樻柟鍚?涓?娓告垙鏈贩娣?涓€鑷? |
| 鎶ラ敊 | 鏃?閫€鍑虹爜 0 |

涔熷氨鏄:**鏈贩娣嗙殑 26.1.2 涓?OptiFine 鑷繁鐨勮ˉ涓佸櫒鍙互姝ｅ父宸ヤ綔,浜у嚭鐨勮ˉ涓佺被宸茬粡鏄繍琛屾湡鐢ㄧ殑瀹樻柟鍚?鈥斺€?杩欎竴绾夸笉闇€瑕佷换浣曢噸鏄犲皠**銆傝繖鏄暣鏉￠摼涓婄浜屼釜琚绾块獙璇佺殑鐜妭(绗竴涓槸 1.20.1,瑙?`docs/RESEARCH-optifine.md` 涓庝笂琛?銆?
## 琛ヤ竵浜х墿鐨勬媶鍒?2026-09-14)

琛ヤ竵鍣ㄧ殑杈撳嚭杩樿鎷嗘垚涓や唤,`OptifinePipeline.split` 鍋氳繖浠朵簨:涓€浠界粰 classpath(OptiFine 鑷繁鐨勭被涓庤祫婧?,涓€浠芥槸琚ˉ涓佺殑娓告垙绫?鎸夊唴閮ㄥ悕绱㈠紩,鐢ㄤ簬椤舵浛鍘熺増)銆備袱鏉¤鍒?

- **鍙彇 `srg/` 閭ｄ竴浠藉彉浣?* 鈥斺€?杞瀷鍣ㄨ鐨勫氨鏄畠,`notch/` 閭ｄ唤涓㈠純,鍏嶅緱澶氬嚭涓€濂楀悓鍚嶇殑绫汇€?- **涓㈡帀瀹夎鍣?*:`optifine/Installer*`銆乣optifine/Patcher*`銆乣optifine/Differ*`銆乣optifine/xdelta/**`銆乣optifine/json/**` 鈥斺€?鎴戜滑鑷繁璺戣ˉ涓佸櫒,杩愯鏈熶笉闇€瑕佸畠浠?鑰?loader 鍙鐪嬭 `optifine/Installer.class` 灏变細鎷掔粷鏁翠釜 jar(瑙?`docs/RESEARCH-neoforge.md`)銆傚叾浣欎竴鍒?OptiFine 鐨?`assets/`銆乣doc/`銆乣META-INF/services/**`)鍘熸牱淇濈暀銆?
瀹炴祴:

| | 26.1.2 | 1.20.1 |
|---|---|---|
| 琛ヤ竵鑰楁椂 | 1.5 绉?| 1.2 绉?|
| 琛ヤ竵鍣ㄨ緭鍑?| 7,898,538 瀛楄妭 / 4611 鏉＄洰 | 6,638,739 瀛楄妭 / 4049 鏉＄洰 |
| 鎷嗗垎鍚?classpath jar | 2,789,483 瀛楄妭 / 2616 鏉＄洰(`assets/` 1785銆乣net/optifine/` 759銆乣doc/` 39銆佹牴 `optifine/` 19銆乣META-INF/` 5) | 2,638,138 瀛楄妭 / 2489 鏉＄洰 |
| 琚ˉ涓佺殑娓告垙绫?| **566**(鍏朵腑 `net/minecraft` 486) | **412**(鍏朵腑 `net/minecraft` 354) |
| `optifine/Installer.class` | 涓嶅瓨鍦?宸插墧闄? | 涓嶅瓨鍦?宸插墧闄? |

鎷嗗嚭鏉ョ殑绫绘槸鍚?*鍙獙璇?*(JVM 鏍￠獙閫氳繃)杩樻病娴?鈥斺€?閭ｉ渶瑕?ASM 鎴?`ClassLoader` 灞傞潰鐨勬鏌?浠ュ強鐪熸満鍔犺浇銆?
## 棣栨鐪熸満瀹炴祴:NeoForge 21.4.149(Minecraft 1.21.4,2026-09-14)

鏈満瑁呭ソ浜?JDK 21 涓?25(`C:\Users\kynar\.jdks\`),浜庢槸鍙互鐪熺殑鍚姩涓€娆?NeoForge銆傜敤鐨勬槸鐢ㄦ埛鍚姩鍣ㄩ噷宸叉湁鐨?`1.21.4-NeoForge_21.4.149`(FML 6.0.18銆丮odLauncher 11.0.4),閰嶄竴涓嫭绔嬬殑娓告垙鐩綍涓庣嫭绔嬬殑 `mods/`,涓嶅姩鐢ㄦ埛鑷繁鐨勯厤缃€傛祴璇曡剼鏈湪浠撳簱澶栫殑 `C:\Users\kynar\IdeaProjects\optifineoforge-test\launch-neoforge.ps1`銆?
**瀵圭収(鏃犳ā缁?**:鍚姩鍒版爣棰樼晫闈?40 绉?`Sound engine started`,鏃犲穿婧冩姤鍛?鈥斺€?娴嬭瘯鍙版湰韬彲鐢ㄣ€?
**鎶婇噸鏂版墦鍖呰繃鐨?OptiFine 1.21.4(J3)鏀捐繘 `mods/`**,渚濇鍑虹幇涓変釜闂,鍓嶄袱涓凡瑙ｅ喅:

1. **`NoSuchFileException: ...optifine-1.21.4-for-neoforge.jar#214`** 鈥斺€?OptiFine 鐢ㄨ嚜宸辩被鐨?code source 鎵?jar(`getProtectionDomain().getCodeSource().getLocation()`),鍦?NeoForge 涓嬭繖鏄?union 鏂囦欢绯荤粺璺緞 `union:/...jar%23214!/`,瀹冨彧鍘绘帀缁撳熬鐨?`!`,鐣欎笅 `#214`,浜庢槸 `ZipFile` 鎵撲笉寮€,ModLauncher 鐩存帴鍒ゅ畾 `InvalidLauncherSetupException: Invalid Services found OptiFine`銆?   鈫?**淇硶**:`OptifineJarFixer` 閲嶅啓 `optifine.OptiFineTransformationService.toFile(URI)`,鎶?`!` 涔嬪悗涓?`#` 涔嬪悗鐨勯儴鍒嗕竴骞舵埅鎺?閲嶅啓鍚庣殑鏂规硶瑕侀噸绠楁爤甯?鍚﹀垯 JVM 鎶?`Expected stackmap frame at this location`)銆傛墦鍖呮椂鑷姩搴旂敤銆?2. 淇ソ涔嬪悗,**OptiFine 鑷繁鐨勮浆鎹㈡湇鍔″湪 NeoForge 涓婅窇璧锋潵浜?*:

   ```
   [optifine.OptiFineTransformationService]: OptiFine ZIP file: ...\mods\optifine-1.21.4-for-neoforge.jar
   [optifine.OptiFineTransformer]: Target.PRE_CLASS is available
   [optifine.OptiFineTransformer]: Forge JAR not available
   [optifine.OptiFineTransformationService]: OptiFineTransformationService.transformers
   [optifine.OptiFineTransformer]: Targets: 474
   ```

   鍗?鏈嶅姟琚彂鐜般€乯ar 琚墦寮€銆?*474 涓ˉ涓佺洰鏍囨敞鍐屾垚鍔?*,ModLauncher 涓嶅啀鎷掔粷銆傝繖鏄暣鏉¤矾绾跨殑绗竴涓湡鏈鸿瘉鎹€?3. **涓嬩竴涓嫤璺殑闂(灏氭湭瑙ｅ喅)**:FML 鐨勬棭鏈熺獥鍙ｉ樁娈?`DisplayWindow.updateModuleReads` 浼氬幓鎵柟娉曠鍚?鑰?OptiFine 鐨勭被寮曠敤浜?**Forge 鐨?API 绫诲瀷** `net.minecraftforge.client.extensions.IForgeVertexConsumer` 鈥斺€?NeoForge 涓婅繖涓寘涓嶅瓨鍦?`net.neoforged.neoforge.*`),浜庢槸 `ClassNotFoundException` 璁╁惎鍔ㄤ腑姝?瑙佷笅涓€鑺?銆?
## Forge API 涓庢々绫?2026-09-14)

**鍏抽敭鍙戠幇:寮曠敤涓嶅湪绫婚噷,鑰屽湪琛ヤ竵璐熻浇閲屻€?* `IForgeVertexConsumer` 杩欎釜鍚嶅瓧鍦ㄦ暣涓?jar 鐨?`.class` 閲屼竴娆￠兘涓嶅嚭鐜?鍙嚭鐜板湪 `patch/srg/com/mojang/blaze3d/vertex/VertexConsumer.class.xdelta` 鈥斺€?涔熷氨鏄?**OptiFine 鐨勮ˉ涓佽鍘熺増 `VertexConsumer` 鍘诲疄鐜?Forge 鐨勬帴鍙?*銆傛墍浠ュ彧鎵被鏂囦欢鏄笉澶熺殑,蹇呴』杩?`patch/**` 涓€璧锋壂銆?
澶勭悊鍔炴硶(`ForgeApiShims` + `OptifineJar` 涓ゅ):

- 鎵?jar 閲?*鎵€鏈?*鏉＄洰(璺宠繃 `assets/`銆乣doc/` 涓?`notch/`),鏀堕泦鍏ㄩ儴 `net/minecraftforge/**` 鍚嶅瓧 鈥斺€?1.21.4 J3 涓婃槸 **55 涓?*;
- 涓烘瘡涓悕瀛楃敓鎴愪竴涓?*绌烘々绫?*(鎺ュ彛杩樻槸绫?鎸?OptiFine 鑷甫鐨勯偅浠?`notch/<鍚屽悕>` 鐨勮闂爣蹇楀喅瀹?,鎵撹繘浜ょ粰 loader 鐨?jar;
- 椤哄甫**涓㈡帀鏁翠釜 `notch/` 鏍?*(1.21.4 涓婃槸 768 涓潯鐩?:閭ｆ槸娣锋穯鍛藉悕绌洪棿鐨勫彉浣?杞瀷鍣ㄤ粠涓嶈瀹?鑰屽畠鐨勭鍚嶆寚鍚戞贩娣嗗悗鐨勬父鎴忕被鍨?`gng`銆乣akv`),鐣欑潃鍙細鎶婂悓涓€涓け璐ユ尓鍒颁笅涓€涓被鍨嬨€?
**缁撴灉:鍚姩瓒婅繃浜嗙鍚嶆壂鎻忛樁娈?OptiFine 鐪熺殑璺戣捣鏉ヤ簡**:

```
[OptiFine] (Reflector) Class not present: net.minecraftforge.common.extensions.IForgeEntity
[OptiFine] OptiFine_1.21.4_HD_U_J3
[OptiFine] Build: 20250209-131348
[OptiFine] OS: Windows 11 (amd64) version 10.0
[OptiFine] Java: 21.0.12.1, Eclipse Adoptium
[OptiFine] OpenGL: NVIDIA GeForce RTX 4060 Laptop GPU/PCIe/SSE2, version 3.2.0 NVIDIA 591.86
```

**涓嬩竴涓け璐?宸插畾浣?鏈В鍐?**:娓告垙鍦?`Minecraft.<init>` 宕?

```
java.lang.NoSuchMethodError: 'void com.mojang.blaze3d.pipeline.RenderTarget.<init>(boolean, boolean)'
	at com.mojang.blaze3d.pipeline.MainTarget.<init>(MainTarget.java:22)
```

鏂瑰悜:OptiFine 鐨勮ˉ涓佹敼杩?`RenderTarget` 鐨勬瀯閫犲嚱鏁?鑰?`MainTarget` 杩欎竴渚т粛鏄師鐗堝舰鐘?鈥斺€?涓よ€呰**鎴愬**搴旂敤銆傚彲鑳芥槸琛ヤ竵搴旂敤椤哄簭(OptiFine 鐨勮浆鍨嬪櫒涓?NeoForge 鑷繁鐨勮浆鍨嬪櫒鍦ㄥ悓涓€鎵圭被涓婂厛鍚庤繍琛?銆佷篃鍙兘鏌愪釜 xdelta 娌℃湁鎴愬姛搴旂敤銆備笅涓€姝ュ氨鏄妸杩欎釜閰嶅闂鏌ユ竻妤氥€?
### 鏌ユ竻浜?OptiFine 鐨勬暣绫绘浛鎹細涓㈡帀 NeoForge 渚ц繕鍦ㄧ敤鐨勬垚鍛?
瀵规瘮涓変唤 `RenderTarget`(閮芥槸 javap 鍑烘潵鐨?:

| 鏉ユ簮 | 鏋勯€犲嚱鏁?|
|---|---|
| NeoForge 杩愯鏃?NeoForm `client-1.21.4-...-srg.jar`) | `RenderTarget(boolean)`銆乣RenderTarget(boolean, boolean)` 鈥斺€?涓€鍙傞偅涓彧鏄浆璋冧簩鍙?|
| NeoForge 鐨?`MainTarget` | 璋?`super(ZZ)` |
| OptiFine 琛ヤ竵浜х墿(`srg/com/mojang/blaze3d/pipeline/RenderTarget.class`) | **鍙湁 `RenderTarget(boolean)`** |

涔熷氨鏄:OptiFine 鐢?*鑷繁缂栬瘧鐨勪竴浠?* RenderTarget 椤舵帀浜?NeoForge 鐨勯偅浠?鑰岄偅浠芥槸鎸?Forge 鐨勫舰鐘剁紪鐨?灏戜簡 NeoForge 璋冪敤鏂硅鐨勯噸杞姐€傚悓涓€涓ā寮忚繕浼氫涪瀛楁 鈥斺€?淇帀鏋勯€犲嚱鏁颁箣鍚?涓嬩竴涓敊璇氨鏄?NeoForge 鐨?`MainTarget.allocateDepthAttachment` 鎵句笉鍒板瓧娈?`useStencil`(OptiFine 閭ｄ唤鎶婂畠鏀瑰悕鎴愪簡 `stencilEnabled`)銆?
**澶勭悊鍔炴硶**:鍔犱竴涓垜浠嚜宸辩殑 ModLauncher 杞瀷鏈嶅姟(`OptifiNeoforgeTransformationService` + `RenderTargetFix`),鍦?`TargetType.CLASS` 闃舵鎶?OptiFine 涓㈡帀鐨勬垚鍛樿ˉ鍥炲幓(鐩墠鏄偅涓簩鍙傛瀯閫犲嚱鏁?杞皟涓€鍙?銆傚疄娴?

```
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, fml, OptifiNeoforge]
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.transformers
```

鏈嶅姟涓?OptiFine 鐨勬湇鍔″悓鏃惰鍔犺浇,`NoSuchMethodError: RenderTarget.<init>(ZZ)` 娑堝け,澶辫触鐐规帹杩涘埌涓嬩竴涓己澶辨垚鍛?`NoSuchFieldError: ... MainTarget does not have member field 'boolean useStencil'`)銆?*缁撹:淇鏂瑰悜瀵逛簡,鍙槸瑕侀€愰」琛ラ綈 OptiFine 鏇挎崲鎺夌殑鎴愬憳**(瀛楁 + 鏂规硶),杩欎竴绫讳慨澶嶅彲浠ョ户缁寜鍚屾牱鏂瑰紡鍔犮€?
### 杩欐槸涓€涓彲閲嶅鐨?琛ユ垚鍛?娴佹按绾?
淇帀鏋勯€犲嚱鏁板悗,鍚屼竴涓ā寮忓張鍑虹幇浜嗕袱娆?姣忔閮芥槸涓€涓?NeoForge 渚ф柊澧炵殑鎴愬憳琚?OptiFine 鐨勬浛鎹㈢増鏈涪鎺?

| # | 鎶ラ敊 | 涓㈠け鐨勬垚鍛?| 鎴戜滑鐨勪慨澶?|
|---|---|---|---|
| 1 | `NoSuchMethodError: RenderTarget.<init>(ZZ)` | 浜屽弬鏋勯€犲嚱鏁?| 琛ヤ竴涓浆璋冧竴鍙傜殑鏋勯€犲嚱鏁?|
| 2 | `NoSuchFieldError: MainTarget does not have member field 'boolean useStencil'` | 瀛楁 `useStencil` | 琛ュ瓧娈?骞跺湪琛ュ嚭鏉ョ殑鏋勯€犲嚱鏁伴噷璧嬪€?|
| 3 | `NoSuchMethodError: ReloadableResourceManager.getListeners()` | 璁块棶鍣?`getListeners()` | 琛ヤ竴涓繑鍥?`listeners` 瀛楁鐨?getter(瀛楁鍚嶄笌绫诲瀷閮芥病鍙?鎵€浠ユ槸绮剧‘杩樺師) |
| 4 | `NoSuchMethodError: ReloadableResourceManager.updateListenersFrom(SortedReloadListenerEvent)` | 鎺掑簭鏇存柊鏂规硶 | 鎸?NeoForge 鐨勫疄鐜拌浆璋?`ReloadListenerSort.sort(event)`;OptiFine 閭ｄ唤鎶?`listeners` 澹版槑鎴?final,`putfield` 浼氳鏍￠獙鍣ㄦ嫆缁?鎵€浠ユ敼鎴愬線鍘熷垪琛ㄩ噷 `addAll` |
| 5(褰撳墠) | `NoSuchMethodError: net.minecraft.client.gui.Gui.initModdedOverlays()` | NeoForge 缁?`Gui` 鍔犵殑鏂规硶 | 鏈鐞?|

姣忎慨涓€涓?鍚姩灏卞線鍓嶈蛋涓€姝?15 绉?鈫?20 绉?鈫?45 绉?,**璇存槑鏂瑰悜姝ｇ‘銆佷笖澶辫触鐐规槸鏈夐檺鐨?*銆備笅涓€杞鍋氱殑鏄妸"閫愪釜宕┿€侀€愪釜淇?鎹㈡垚**鎴愭壒鎺ㄥ**:鍒楀嚭 OptiFine 鏇挎崲鎺夌殑鎵€鏈夌被(琛ヤ竵璐熻浇閲?`patch/srg/**` 鐨勯偅浜?,閫愪釜涓庤繍琛屾椂(A=NeoForm 鐨?client jar)瀵规瘮鎴愬憳闆嗗悎,鑷姩鍒楀嚭"杩愯鏃舵湁鐨勩€丱ptiFine 閭ｄ唤娌℃湁鐨?鎴愬憳娓呭崟,鍐嶆寜鍚屼竴濂楄鍒欐壒閲忚ˉ榻?瀛楁鐩存帴琛ャ€乬etter/setter 鎸夊悓鍚嶅瓧娈佃ˉ銆佸叾浣欏厛琛ユ姏寮傚父鐨勬々骞惰褰?銆?
- **FML 6 涓嶆帴鍙?`mods/` 閲屾病鏈夊厓鏁版嵁鐨?jar**(鏃ュ織:`not a valid mod file`)鈥斺€?transformer jar 涓嶉渶瑕?mod 鍏冩暟鎹?杩欐潯鑰佽鐭╁湪杩欓噷涓嶆垚绔嬨€傛祴璇曞彴鐨勫仛娉曟槸鎶婃垜浠殑鏈嶅姟绫诲杩涢偅涓凡缁忚鎺ュ彈鐨?OptiFine jar,骞舵妸鏈嶅姟鏂囦欢**杩藉姞**涓€琛?涓€涓湇鍔℃枃浠跺彲浠ュ垪澶氫釜瀹炵幇)銆?- **PowerShell 鍙橀噺鍚嶄笉鍖哄垎澶у皬鍐?*:`$Out` 涓?`$out` 鏄悓涓€涓彉閲?鑴氭湰閲屽悓鏃剁敤浣滆緭鍑鸿矾寰勪笌娴佸璞℃椂浼氫簰鐩歌鐩栥€?- **.NET Framework 鐨?`ZipFile.CreateFromDirectory` 鍐欏嚭鐨勬潯鐩悕鐢ㄥ弽鏂滄潬**,鑰?SecureJar 鐨?union 鏂囦欢绯荤粺鍙姝ｆ枩鏉?琛ㄧ幇涓?`UnionFileSystem$NoSuchFileException: kynarain/cn/.../RenderTargetFix.class`銆傛墜宸ユ寜 `/` 鍐欐潯鐩嵆鍙€?
## 灏氭湭纭

- 涓婇潰绗?3 鏉′箣澶栬繕鏈夊灏?`net.minecraftforge.*` 寮曠敤闇€瑕佸鐞?1.21.4 鐨?OptiFine 閲屾湁涓€鎵?銆?- 1.20.6 鈥?1.21.8 杩欐潯鍖洪棿鏄惁閮藉儚 1.21.4 涓€鏍疯兘琚?NeoForge 鎺ュ彈(1.20.6 璧?FML 杩樹細棰濆鎷掔粷"鍘熺増 OptiFine jar",鎴戜滑宸插墧闄ゅ畨瑁呭櫒鍏ュ彛,浣嗘湭閫愪釜瀹炴祴)銆?- 琛ヤ竵绫昏 NeoForge 搴旂敤鍚?OptiFine 杩愯鏈熺殑 MD5 鏍￠獙鏄惁浠嶇劧閫氳繃銆?- 1.21.9 鍙婁互鍚?ModLauncher 宸蹭笉瀛樺湪,蹇呴』鏀圭敤 NeoForge 鐨?`ClassProcessor`,杩欐潯璺繕娌¤瘯銆?
## 杩欏鍔犺浇鍣ㄦ剰鍛崇潃浠€涔?
| MC | OptiFine 璐熻浇鐨勫懡鍚嶇┖闂?| NeoForge 杩愯鏈熷懡鍚嶇┖闂?| 缁撹 |
|---|---|---|---|
| 1.20.1 | SRG | SRG(`net.neoforged:forge:47.x`) | 璺戣ˉ涓佸櫒鍚庣敤 `srg/` 閭ｄ唤鍗冲彲,**涓嶉渶瑕侀噸鏄犲皠** |
| 1.20.2 / 1.20.4 | SRG | 瀹樻柟鍚?20.2 璧? | 璺戣ˉ涓佸櫒鍚庤**鎶?SRG 閲嶆槧灏勬垚瀹樻柟鍚?* |
| 1.20.6 鈥?1.21.8 | 瀹樻柟鍚?| 瀹樻柟鍚?| 璐熻浇鐩存帴鍙敤;鍏冩暟鎹繀椤绘敼鍐?**杩欎竴鍖洪棿浠嶈兘澶嶇敤 OptiFine 鑷繁鐨?ModLauncher 杞崲鏈嶅姟**(鈮?FML 9) |
| 1.21.9 鈥?1.21.11 | 瀹樻柟鍚?| 瀹樻柟鍚?| **ModLauncher 宸茶 NeoForge 绉婚櫎**,鑰岃繖涓夌増 OptiFine 鏃㈡病鏈?`ClassProcessor` 涔熸病鏈夊彲鐢ㄧ殑鍏ュ彛 鈫?鍙兘鐢辨垜浠窇琛ヤ竵鍣ㄥ苟瀹炵幇 `ClassProcessor` |
| 26.1.2 | 瀹樻柟鍚?鏈贩娣? | 瀹樻柟鍚?鏈贩娣? | OptiFine **鑷甫 NeoForge 鐨?`ClassProcessor`**,鎴戜滑鐨勫伐浣滄槸閲嶆柊鎵撳寘 jar(鍏冩暟鎹?+ 鍘绘帀瀹夎鍣ㄥ叆鍙?骞舵妸琛ヤ竵绫绘帴涓?|

涓ゆ潯宸茬煡鐨勭‖绾︽潫(瑙?`docs/RESEARCH-neoforge.md`):浠?**1.20.6** 璧?FML 鏈変竴鏉￠拡瀵瑰師鐗?OptiFine jar 鐨勬嫆缁濊鍒?鎺㈤拡鏄?`optifine/Installer.class`),鎵€浠ュ繀椤婚噸鏂版墦鍖?**1.21.3** 璧?OptiFine 澹版槑鐨?`loaderVersion` 浼氭牎楠屽け璐?鍏冩暟鎹繀椤绘敼鍐欍€?
## 灏氭湭纭

- `notch/net/minecraftforge/**`(70 涓被,鎬昏绾?56鈥?4 KB)鏄畬鏁村疄鐜拌繕鏄々;1.21.11 鐨?`srg/net/optifine/shaders/ShadersRender` 閲屾湁**纭紩鐢?* `net/minecraftforge/client/event/ViewportEvent$ComputeCameraAngles`,鑰?NeoForge 涓婃槸 `net.neoforged.neoforge.*` 鈥斺€?杩欐槸宸茬煡鐨勭涓€涓繀鐒惰淇殑鐐广€?- 1.20.6 璧?璐熻浇宸叉槸瀹樻柟鍚?杩欐潯瑙勫垯鏄粠 7 涓瀯寤哄鎺ㄧ殑,`1.21`銆乣1.21.3`銆乣1.21.4`銆乣1.21.8`銆乣1.21.9`銆乣1.21.10` 鏈€愪釜纭銆?- OptiFine 杩愯鏈熶細鐢?MD5 鏍￠獙琛ヤ竵缁撴灉,鑰?NeoForge 鑷繁涔熶細鏀瑰師鐗堢被 鈥斺€?涓よ€呭彔鍔犲悗鏍￠獙鏄惁杩橀€氳繃,鍙兘鍦ㄧ湡鏈轰笂鐪嬨€?- 鏈満娌℃湁 JDK 25,26.1.2 鐨?NeoForge 瀹炰緥杩樿捣涓嶆潵,鍥犳"鑳戒笉鑳借繘娓告垙"杩欎竴灞傚皻鏈獙璇併€?
### 成批补齐:132 个成员,一次修完(2026-09-14)

把"逐个崩、逐个修"换成了离线推导 + 运行时批量补齐:

- `MemberRestorePlan`(离线工具)拿**补丁产物**与**运行时**的 NeoForm client jar 逐个类比对成员集合,输出"运行时有的、OptiFine 那份没有的"清单;
- 实测:**比对 248 个被替换的类,得到 134 个待补成员**(`Gui` 一个类就占 16 个);
- 清单作为资源打进 jar(`optifineoforge/member-restores.txt`),`MemberRestoreTransformer` 在 `TargetType.CLASS` 阶段按清单补:字段按原类型补,方法补一个返回类型默认值的桩,**每个桩都打日志**(所以"哪些只是不崩、哪些是真还原"始终可见);
- 需要真实语义的两个成员(`getListeners`、`updateListenersFrom`)排除在批量之外,仍由各自的 fix 精确还原,因此不依赖 ModLauncher 的转型器执行顺序。

**结果:游戏越过了整个 `Minecraft.<init>`,进入加载界面的渲染循环**(`NeoForgeLoadingOverlay.render` → `DisplayWindow.render`),启动时长从 45 秒推到 60 秒。当前失败点是 FML 自己的早期窗口渲染器抛 `IllegalStateException: Already building.` —— 位置在 FML 内部,方向是某个被补成默认值的桩(或 OptiFine 替换掉的渲染相关类)让它进了不一致状态。下一步:把桩清单与这个失败点对上,优先把与渲染/缓冲区有关的成员从"默认值桩"升级成真实实现。

### 当前阻塞点:被补成"默认值"的桩不够,FML 的早期显示进了不一致状态(2026-09-14)

批量补齐之后启动走到了 `NeoForgeLoadingOverlay.render` → FML 的 `DisplayWindow.render`,然后抛:

```
java.lang.IllegalStateException: Already building.
	at net.neoforged.fml.earlydisplay.SimpleBufferBuilder.begin(SimpleBufferBuilder.java:152)
	at net.neoforged.fml.earlydisplay.RenderElement.renderText(RenderElement.java:252)
```

`-Dfml.earlyprogresswindow=false` 没有效果(NeoForge 21.4 仍然建自己的加载覆盖层)。查了清单里与渲染相关的条目,一共 7 个,其中 4 个是 NeoForge 的 GL 状态备份功能:

```
M com/mojang/blaze3d/platform/GlStateManager _backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/platform/GlStateManager _restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/systems/RenderSystem backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/systems/RenderSystem restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/vertex/VertexFormatElement$Usage getExtensionInfo ()Lnet/neoforged/fml/common/asm/enumextension/ExtensionInfo;
M com/mojang/blaze3d/vertex/VertexFormatElement findNextId ()I
M net/minecraft/client/renderer/chunk/SectionCompiler compile (...)
```

**判断**:把这四个备份/恢复方法补成"什么都不做的桩"是不对的 —— 它们是渲染状态机的一部分。下一步不该继续加桩,而是**把桩换成真身**:

> 在离线阶段从运行时类里把缺失成员的**原始字节码**抽出来(字段 + 方法体),打成"供体(donor)类"随 jar 一起发;运行时转型器把这些成员连同方法体整体复制进 OptiFine 的替换版本。这样"补回去"就是真的补回去,而不是"让它不崩"。少数方法体的常量池会指向 OptiFine 改名过的成员而失效,这类会在日志里显形,再单独处理。

这条是下一轮的第一件事。

### 供体类:把"补桩"换成"补真身"(2026-09-14)

`MemberRestorePlan` 现在不只输出清单,还**为每个受影响的类生成一个供体(donor)类**:里面只放 OptiFine 丢掉的那些成员 —— 字段带声明,方法带**原始字节码**。供体的内部名就是被修类的名字(所以复制过去的方法体里的 `this` 字段访问能对上),但存放在 `optifineoforge/donors/<类名>.class`,不会被当成那个类加载。1.21.4 上是 **57 个供体、106 KB**。

`MemberRestoreTransformer` 改为从供体复制成员(字段 + 方法体),不再造默认值桩。

**但直接复制会出问题**:复制过来的方法体可能引用 OptiFine 改名过的成员,这时整个类都过不了校验(`VerifyError: Error exists in the bytecode`)。所以生成供体时加了一道**静态校验**:遍历方法体里所有指向本类的字段/方法引用,只要有一个在替换版本里不存在,就**降级成默认值桩并打日志**。实测降级的有 `Gui.initModdedOverlays`、`Gui.renderHealthLevel`、`Camera.getRoll`、`ClientLevel.getModelData` 等(共十几处),其余照抄真身。

**测试台还差一步**:供体作为资源要能被 `getResourceAsStream` 找到,而 union 文件系统需要**目录条目**(之前给服务类踩过同一个坑)。当前日志里成片的 `No donor class for ...` 就是这个原因 —— 打进 combined jar 时要为供体路径补目录条目。修好这一步才能看出供体复制的真实效果。

### 供体的第一次实测:补真身并不总是更好(2026-09-14)

供体打包修好之后(目录条目 + 命令行默认走 `prepareForLoader` 的完整流程),供体真的被读到了(日志里不再有 `No donor class for ...`),`RenderSystem`、`Gui`(16 个)、`ClientLevel`(9 个)等类的成员都是**带原始方法体**补进去的。

但出现**回退**:

```
java.lang.IllegalStateException: Rendersystem called from wrong thread
```

`RenderSystem.backupGlState` / `restoreGlState` 的原始方法体里有线程断言,而这里是在早期启动的主线程上被调用的 —— 于是"真身"比"什么都不做的桩"**更早**把启动打断(桩版本能跑到 60 秒,供体版本 10 秒就死)。

**结论:补什么、怎么补,要按成员区分**,不能一刀切:

- 兜底做法(默认值桩)在"只是被人读一下"的成员上是对的;
- 供体真身在"有实际副作用、且调用环境一致"的成员上是对的;
- 而带线程/环境断言的成员(如 `RenderSystem` 的两个状态方法)两边都不对:桩破坏状态机,真身断言失败。这类需要**按调用环境改写**(把断言去掉,或改成 `GlStateManager` 的直接调用)。

下一步:给供体生成加一条**按成员分类**的规则 —— 方法体里出现 `assertOnRenderThread` 一类环境断言的,先剥掉这层断言再复制(而不是退回桩),并把这批方法在日志里单列。

### 定位一次误判:"wrong thread" 其实是次生错误(2026-09-14)

加了断言剥离(复制方法体前去掉 `RenderSystem.assert*` 这类无参环境断言)之后,启动仍是 10 秒退出,报的还是那句 `Rendersystem called from wrong thread`。**但这次把完整栈翻出来了,发现它不是根因**:

```
java.lang.IllegalStateException: Rendersystem called from wrong thread
  at com.mojang.blaze3d.systems.RenderSystem.constructThreadException(RenderSystem.java:142)
  at com.mojang.blaze3d.systems.RenderSystem.assertOnRenderThread(RenderSystem.java:136)
  at com.mojang.blaze3d.systems.RenderSystem.getCapsString(RenderSystem.java:564)
  at net.minecraft.SystemReport.setDetail(SystemReport.java:70)
  at net.minecraft.client.Minecraft.fillSystemReport(Minecraft.java:2334)
  at net.minecraft.client.Minecraft.fillReport(Minecraft.java:2307)
  at net.minecraft.client.main.Main.main(Main.java:200)
```

`Main.main` 走进 `fillReport` 说明**在它之前就已经有一次异常**,而这次断言失败发生在**组装崩溃报告**的时候(主线程读 GL 能力字符串)。也就是说:真正要查的是它之前那个异常,而这个"wrong thread"只是它的影子。

教训:失败的栈要**从头看**,不能只看最后一条消息 —— 上一步据此得出的"供体真身更差"的结论需要重新验证。下一步:在完整 stderr 里找 `Main.main` 之前的第一处异常(可能在 mod 加载或 `Minecraft.run` 的早期),再决定成员的补法。

### 更正:并没有"供体更差"这回事(2026-09-14)

把两次运行的崩溃报告按时间对齐之后,上一节的结论是错的:

| 运行 | 启动 | 崩溃报告 | 描述 |
|---|---|---|---|
| 22:50:43 桩版本 | →22:51:37 | crash-…22.51.37 | `Rendering overlay` / `Already building.` |
| 22:54:53 供体版本 | →22:55:06 | crash-…22.55.06 | `Rendering overlay` / `Already building.` |

**两次都到达同一个阻塞点**(FML 早期显示的 `SimpleBufferBuilder.begin`),只是到达它的耗时不同(55 秒 vs 14 秒,差别来自资源/库加载的波动,不是补法导致)。所以:

- **"供体真身让启动从 60 秒退化到 10 秒"是不成立的**,那是把"最后一条日志消息"当成了根因;
- 断言剥离与 4 个 GL 状态成员的排除仍然保留(它们各自有依据),但**当前唯一的阻塞点自始至终没变**:FML 加载覆盖层的缓冲区状态机。

顺带确认两件事:

- `-Dfml.earlyprogresswindow=false` 在 NeoForge 21.4 上**无效**(jar 里也找不到可用的开关字符串),所以没法用配置绕过早期显示;
- FML 的早期显示**完全用自己的类**(`SimpleBufferBuilder` / `SimpleFont` / `Format` / `Mode`)与 LWJGL,不用 Minecraft 的 `VertexFormat`/`GlStateManager` —— 因此问题不在"OptiFine 替换了哪个渲染类",而在**某一帧的绘制中途抛了异常**,把 `building` 留成了 true(下一帧的第一次 `begin` 就炸)。下一轮要抓的是**第一帧里被吞掉的那个异常**。

### 供体校验再补一条:不许复制 `super` 调用(2026-09-14)

完整日志翻出了**真正的第一处异常** —— 不是 `Already building`,而是**类校验失败**:

```
java.lang.VerifyError: Bad invokespecial instruction: current class isn't assignable to reference class.
Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.VerifyError ...
Caused by: java.lang.NoClassDefFoundError: Could not initialize class net.optifine.reflect.Reflector
```

出错的字节码是 `aload_0; invokevirtual …; aload_0; aload_1; aload_2; invokespecial …; areturn` —— 典型的"复制过来的方法体里有一句 `super.xxx(...)`"。**OptiFine 那份的父类未必和运行时那份一样**,于是一句 `invokespecial` 指向的父类对不上,整个类过不了校验。

修法:供体校验再加一条 —— 方法体里凡是 `INVOKESPECIAL` 且 owner 不是本类的(即 `super` 调用),只有在**替换版本的父类与运行时一致**时才允许复制,否则降级成桩。

修掉之后 `VerifyError` 消失,启动回到同一个阻塞点(FML 加载覆盖层的 `Already building.`),而且这次是**正常的崩溃报告**,不是连锁的初始化失败。**目前 1.21.4 上唯一的阻塞点就是它。**

### 突破:FML 早期显示可以用配置关掉,游戏随即跑进资源加载(2026-09-14)

翻 `FMLConfig$ConfigValue` 的枚举常量时发现它除了窗口尺寸,还有两个开关:`earlyWindowControl` 与 `earlyWindowProvider`,而配置来自**游戏目录的 `config/fml.toml`**。写上:

```toml
earlyWindowControl = false
```

再启动,那个纠缠了十几轮的 `SimpleBufferBuilder.begin -> Already building.` **直接消失**,失败点整个换了一层:

```
Description: Rendering overlay
java.lang.NullPointerException: Cannot invoke "BakedModel.getParticleIcon()" because the return value
  of "BlockModelShaper.getBlockModel(BlockState)" is null
	at LiquidBlockRenderer.setupSprites(LiquidBlockRenderer.java:43)
	at BlockRenderDispatcher.onResourceManagerReload(BlockRenderDispatcher.java:157)
	at ResourceManagerReloadListener...
	at Minecraft.runTick(Minecraft.java:1211) -> Minecraft.run
```

也就是说:**游戏已经在跑主循环、在重载资源、在烘焙模型**了 —— 不再是启动早期的类加载/签名扫描问题,而是"某个模型查不到"。方向很明确:清单里就有 `ModelManager`(4 个成员)、`BlockRenderDispatcher`(4 个)、`LiquidBlockRenderer`(3 个)、`SimpleBakedModel`(5 个)这些与模型烘焙直接相关的类,它们的成员是**桩**(返回 null)还是真身,决定了这条链能不能通 —— 正是"按成员区分补法"要解决的那类。

结论进 `docs/DEVELOPMENT.md`,并记下测试台要求:`config/fml.toml` 里 `earlyWindowControl = false` 是跑 1.21.4 的必要条件。

### 模型链的现状:该补的成员大多降级成了桩(2026-09-14)

关掉早期窗口之后暴露出来的那批 null 模型,和清单里"降级成桩"的成员高度重合。查了一遍,`ModelBlockRenderer` / `BlockRenderDispatcher` / `LiquidBlockRenderer` / 各 `blockentity` 渲染器上**十几个成员都没能复制真身**,原因是同一句:

```
stub (body would not verify): net/minecraft/client/renderer/block/ModelBlockRenderer.tesselateBlock(...)
stub (body would not verify): net/minecraft/client/renderer/block/BlockRenderDispatcher.renderBatched(...)
stub (body would not verify): net/minecraft/client/renderer/block/LiquidBlockRenderer.shouldRenderFace(...)
...
```

也就是说:**这些方法体引用了别的、同样被 OptiFine 丢掉的成员**,所以校验不过,只能退化成"返回默认值"。逐个手补不可持续。

**下一步的做法(传递闭包)**:如果方法体 X 引用了本类里缺失的成员 Y,而 Y 在运行时类里存在,那就**把 Y 也一起补进来**,再重新校验 —— 反复直到不再有新的引用或达到上限。这样"校验不过"就不再等于"退化成桩",而是"把这条依赖链补齐"。真正的边界条件是环引用与上限,两者都要显式处理并打日志。

### 闭包的第一版只跟了本类引用 —— 而这批失败是**跨类**的(2026-09-14)

按上一节的方案加了传递闭包(方法体引用本类缺失成员时,把那个成员也补进来,反复直到稳定)。构建与启动都正常,**但清单大小没变(仍是 132 个),也没有打印任何 `closure added`** —— 说明这些"校验不过"的方法体,**引用的本类成员其实都已在清单里**。

于是原因只能是另一类:**它们引用的是别的类上的成员,而那些类同样被 OptiFine 替换过、同样缺成员**。例如 `ModelBlockRenderer.tesselateBlock` 会去调 `BlockRenderDispatcher`/`BakedModel` 上的方法,OptiFine 版的那些类里未必有。

所以闭包要**跨类**做:

1. 先把"补丁产物里的每个类"与"运行时对应的类"各建一份成员表;
2. 遍历所有待补成员的方法体,收集它引用的**(owner, 名字, 描述符)** —— 不限本类;
3. 只要 owner 在补丁产物里(即也是被替换的类)、而它的替换版本缺这个成员、运行时里又有,就**把该成员加到那个类的清单里**;
4. 重复直到不再变化。

这一版闭包代码留在仓库里(它是跨类版本的前置),下一轮把它从"只看本类"扩成"走整张引用图"。

### 跨类闭包做好了,但它也不是原因(2026-09-14)

把闭包从"只看本类"改成"跟着引用的 **owner** 走"(第一版把引用一律当成落在本类,等于没跨类),现在会真的跨类补齐了 —— 实测 `closure added 1`,清单从 132 变成 133。

**但模型链的 null 依旧**,失败点一字未变。所以那些"校验不过"的方法体,**不是因为引用的成员缺失**:

- 它们引用的成员,要么在替换版本里本来就有,要么在运行时类里根本找不到(所以闭包无从可补);
- 那就意味着失败来自别处 —— 描述符/父类/字段可见性/调用点形态,或者方法体里用了 OptiFine 那份类**不具备的泛型签名与接口**。

**下一轮该做的是把这个判断换成证据**:拿一个具体的"校验不过"的方法(例如 `ModelBlockRenderer.tesselateBlock`),把**复制后的类**交给校验器(ASM 的 `CheckClassAdapter`,或直接 `ClassLoader.defineClass` 看 VerifyError 的详细信息),"到底是什么让它不过"就不再靠猜。

### 用校验器取证:真身其实能过,是我们的判据太严(2026-09-14)

新工具 `DonorVerifier` 把"替换版本 + 全部缺失成员(带原始方法体)"合成一个类,交给 ASM 的 `CheckClassAdapter` 校验,直接把"为什么不过"打出来。第一条结论就很关键:

```
### net/minecraft/client/renderer/block/LiquidBlockRenderer
  verifies clean with every body copied in          ← 全部复制进去是能过的!
### net/minecraft/client/renderer/block/ModelBlockRenderer
  ClassNotFoundException: net.minecraftforge.client.extensions.IForgeBakedModel   ← 只是校验器看不到我们的桩类
```

也就是说,`referencesOnlyExisting` 的判据(要求被引用的成员**已经存在于替换版本**)过于严格:实际运行时,那些成员**本来就会被计划补进去**(闭包补的),所以方法体能过。改成"对着**补完计划之后的类**判断"后,一批原本降级成桩的方法重新变成了真身。

### 新暴露的问题:补回来的**字段**没人初始化

失败点随即往前移了一格(这次是 `Initializing game`):

```
java.lang.NullPointerException: Cannot invoke "GuiLayerManager.initModdedLayers()" because "this.layerManager" is null
	at net.minecraft.client.gui.Gui.initModdedOverlays(Gui.java:1457)
	at net.neoforged.neoforge.client.ClientHooks.initClientHooks(ClientHooks.java:1015)
```

`layerManager` 是 NeoForge 给 `Gui` 加的字段 —— 我们**补了字段**(所以不再是 `NoSuchFieldError`),但**没人在构造函数里初始化它**。之前它是桩方法、根本不解引用,所以看不出来;换成真身之后立刻显形。

**下一步**:补字段时一并补"初始化" —— 在运行时类的构造器里找到给该字段 `putfield` 的那段指令,把它照搬进替换版本的构造器(而不是猜一个默认值)。这和"构造函数重载"是同一类问题的两个面。

### 补字段的初始化(2026-09-14)

按上一节的方案做了:供体里为每个补回来的**实例字段**多带一个静态方法

```
public static void optifineoforge$init$<字段>(<类> self)
```

它的方法体就是**从运行时类的构造器里原样搬出来的那段初始化指令**(例如 `self.layerManager = new GuiLayerManager()`),只搬"直线段"——遇到跳转/标签/switch 就放弃,涉及除 slot 0 以外局部变量的也放弃(否则搬过去就不成立),放弃时打印 `no safe initialiser for field ...`。

转型器把这批初始化方法一起放进类里,并在**每个构造函数的每个 `RETURN` 之前**插入调用。实测确实生效:

```
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/gui/Gui
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/gui/Font
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/multiplayer/ClientLevel
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/resources/model/ModelManager
```

**当前新问题(未解决)**:转型器在读取某个供体时抛异常(`MemberRestoreTransformer.donor:161`),把这次启动打断了 —— 需要看完整消息确认是哪个供体、以及是不是新加的初始化方法让那个类文件写坏了(例如被搬的指令段其实不完整)。

### 两个供体侧的修复:栈帧与 final 字段(2026-09-14)

**① 搬初始化指令时不能带上栈帧。** 把构造器里的初始化片段搬到供体的静态方法里时,原片段可能夹着 `FrameNode`;搬过去之后帧与新的方法上下文对不上,**写入时直接抛 `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`**,把整个启动打断。现在遇到 `FrameNode` 就当作片段边界(直线段本来不需要帧)。

顺带做了一件一直缺的事:**离线校验器**。`CheckDonors`(单文件 Java,用 ASM 把供体里每个方法单独写一遍)一次性指出是 `SimpleBakedModel` 的供体坏了 —— 这类问题本来要跑一次完整启动才能看到,现在离线几秒就能定位。

**② 补回来的字段不能带 `final`。** 初始化由我们的静态方法执行,而 JVM 规定**非静态 final 字段只能在本类的 `<init>` 里赋值**:

```
IllegalAccessError: Update to non-static final field ModelManager.modelBakery attempted from a different
  method (optifineoforge$init$modelBakery) than the initializer method <init>
```

补字段时去掉 `final` 即可(语义上仍是"构造期赋值一次")。

修完这两处,启动回到**同一个模型链阻塞点**,但路面已经明显不同:真身比例大幅提高(`Gui` 17 个成员 + 1 个字段初始化、`ModelManager` 5 个成员 + 1 个字段初始化、`ClientLevel` 10 个成员……),而且**不再有 VerifyError / IllegalAccessError / 供体读取失败**这类结构性错误。

### 两个真正的第一因:Forge 标签助手与 union 文件系统(2026-09-14 晚)

模型链的 null 一直不是第一因,下面两条才是 —— 两条都**不是**"缺成员",所以成员补全这一步永远看不到它们。

**① `ItemTags.create(String, String)`:Forge 的助手,NeoForge 没有。** 完整栈第一次读完才看清:

```
Failed to create mod instance. ModID: neoforge, class net.neoforged.neoforge.common.NeoForgeMod
Caused by: NullPointerException: Cannot invoke "TagKey.toString()" because "tag2" is null
  at TagConventionLogWarning.createForgeMapEntry(TagConventionLogWarning.java:557)
  at TagConventionLogWarning.<clinit>(TagConventionLogWarning.java:200)
```

`<clinit>` 第 200 行是 `Tags.Items.DYES_BLACK`,而它是 `DyeColor.BLACK.getTag()`。OptiFine 的 DyeColor 构造器里写着:

```
Reflector.ForgeItemTags_create.call("forge", "dyes/" + name)  ->  this.dyesTag
```

`ForgeItemTags_create` 是 `net.minecraft.tags.ItemTags.create(String, String)`,Forge 给 `ItemTags` 补的助手。NeoForge 的 `ItemTags` 只有 `create(ResourceLocation)`,反射找不到方法就返回 null,于是两个标签字段都是 null。NeoForge 自己把这个值当作约定标签,静态初始化直接 NPE,`neoforge` 这个 mod 构造失败,接下来就是上百行

```
Cowardly refusing to send event net.neoforged.neoforge.client.event.* to a broken mod state
```

NeoForge 的客户端事件全部不再派发(模型烘焙相关的事件也在其中),所以"模型是 null"是这条链的末端症状。

修法是**把方法补回去**,而不是改 OptiFine 的调用点(调用点可能不止一处,而反射只需要方法存在):新增 `TagHelperFix`,向 `net.minecraft.tags.ItemTags` 注入 `create(String, String)`,内部委托 NeoForge 已有的 `create(ResourceLocation)`;命名空间 `forge` 交给 `ConventionTags` 按版本映射(`1.21+` → `c`, `1.20.x` → `forge`),这样 OptiFine 拿到的标签与 NeoForge 自己的 `Tags` 完全一致。修复后 `Failed to create mod instance` 与 `Cowardly refusing` 全部消失,启动第一次越过 mod 构造。

**② `Path.toFile()`:在 union 文件系统上会抛。** 下一个栈是:

```
java.lang.UnsupportedOperationException: Path not associated with default file system.
  at java.nio.file.Path.toFile(Path.java:772)
  at net.optifine.util.ResUtils.collectFiles(ResUtils.java:104)
  at net.optifine.CustomItems.update(CustomItems.java:168)
  at net.optifine.util.TextureUtils.resourcesPreReload(TextureUtils.java:315)
```

`ResUtils.collectFiles` 按包类型分流:目录包走 `File` 遍历,zip 包走解压,而 `PathPackResources` 只做 `root.toFile()`。Forge 时代 mod 资源就是磁盘目录,这一句成立;NeoForge 的 mod 资源在 jar 的 union 文件系统里,这一句直接抛异常,而且发生在**初始资源重载**里,游戏在构造 `Minecraft` 时就死了。

修法:新增 `PackRootsFix`(只改调用点,保留 OptiFine 其余分流逻辑)与 `PackRoots.toFile`,依次尝试三条路 —— `path.toFile()`;从 union 字符串(`union:/.../mod.jar%23214!/assets`)还原出底层 jar 或目录并交给 OptiFine 的 zip 分支;都不行就把整棵子树复制到临时目录(退出时清理)。全失败返回 null,OptiFine 视为"这个包没有可用文件" —— 丢一个自定义物品目录远好过拒绝启动。全 jar 扫描确认 OptiFine 里只有两个类调用 `Path.toFile`:`ResUtils` 与 `OptiFineTransformationService`(后者的调用在重打包阶段已被 `OptifineJarFixer` 改写)。

修完这两条,rig 判定标记第一次走到 **`Sound engine started`**,资源重载跑完,崩溃点推进到渲染加载界面。

**新的第一因(随后修掉,见下一节):`ClientLanguage.componentStorage` 补回来了但没有值。** 计划为 `net/minecraft/client/resources/language/ClientLanguage` 补了字段 `componentStorage` 和两个方法(`appendFrom`、`getComponent`,都来自 NeoForge 客户端 jar 的供体),但 `Initialised ... restored fields` 里没有这个类:供体的赋值在 3 参构造器里是 `this.componentStorage = <第 3 个参数>`,片段读了局部变量(参数),按现有"只允许 slot 0"的规则被拒,字段保持 null,供体版 `getComponent` 一读就 NPE:

```
NullPointerException: Cannot invoke "java.util.Map.get(Object)" because "this.componentStorage" is null
  at ClientLanguage.getComponent(ClientLanguage.java:103)
```

值得记下的是 OptiFine 的 `ClientLanguage` 是 **1.21.1 形状**(只有 2 参构造器、`appendFrom(String, List, Map)`、`getLanguageData()`),既没有 `componentStorage` 也没有 `loadFromJson` —— 说明这两个是 NeoForge 补丁成员,数据源在 NeoForge 自己的装载路径里。顺带确认了"空 map 默认值"是安全的:NeoForge 的 `TranslatableContents.decompose` 拿到 null 就退回字符串查找(`getOrDefault`),并不会因此出错。

### 补字段的第三个来源:委托构造器里的默认值(2026-09-14 晚)

上面那条最后选了更小的做法:**值直接从"本类自己的短构造器"里取**。NeoForge 的 `ClientLanguage` 有两个构造器,3 参的那个 `this.componentStorage = <第 3 个参数>`,而 2 参的那个用 `Map.of()` 委托过去 —— 也就是说**当类里没有 3 参构造器时,NeoForge 自己认定的默认值就是 `Map.of()`**。

`MemberRestorePlan` 现在这样处理:赋值右边**正好是一个参数加载**时(其余情形仍走原来的"直线段搬迁"),去找同类的另一个构造器对同一个 `<init>` 的委托调用,把那个位置上的实参取出来当默认值。实参只接受"单条、不需要从栈上取东西"的指令(常量、`GETSTATIC`、`NEW`、局部变量加载、零参 `INVOKESTATIC`),否则放弃并保持原样 —— 宁可留空也不猜。对 `ClientLanguage` 生成的正是:

```
public static void optifineoforge$init$componentStorage(ClientLanguage self) {
    self.componentStorage = Map.of();
}
```

这里还有一个小坑值得记:**局部变量加载必须算作"压栈而不弹栈"**。第一版把 `ILOAD/ALOAD` 一律判为不合格,于是"先读参数再委托"的构造器整个被否掉,`Map.of()` 那一路根本没被看到 —— 表现为供体里就是没有初始化方法。查这种问题不需要启动游戏,`javap` 供体类文件一眼就能看到。

### 现状:游戏能启动并停在主循环(2026-09-14 晚)

补上这条之后 rig 的判定第一次是 **`STARTED`**(不是崩溃):`OpenAL initialized` → `Sound engine started`,贴图集逐个建成并伴随 OptiFine 自己的日志(**`[OptiFine] Animated sprites: 0`**、`[OptiFine] Scaled too small texture: minecraft:missingno`),进程持续运行到 rig 主动结束,并留下了截图。

首轮资源重载仍然报一次错,但**没有把游戏打掉**:

```
NullPointerException: BakedModel.getParticleIcon() ... BlockModelShaper.getBlockModel(BlockState) is null
  at LiquidBlockRenderer.setupSprites(LiquidBlockRenderer.java:43)
  at BlockRenderDispatcher.onResourceManagerReload(BlockRenderDispatcher.java:157)
  at ResourceManagerReloadListener.lambda$reload$0(ResourceManagerReloadListener.java:16)
```

随后是 `Caught error loading resourcepacks, removing all selected resourcepacks`,游戏自己重试第二轮重载,第二轮贴图集正常建完 —— 也就是说**模型确实被烘焙过,只是第一轮里 `BlockRenderDispatcher` 的应用阶段跑在了 `ModelManager.apply` 之前**(两边的 `missingModel` 都只在 `apply(ReloadState, ProfilerFiller)` 里赋值,`javap` 已确认),`getModel` 于是拿 `missingModel` 这个 null 当兜底返回。第二轮能好,说明问题出在**第一轮的监听器集合/顺序**上,而不是模型数据本身。

这条是下一轮的第一件事:把 `ReloadableResourceManager`(OptiFine 也替换了它,`ReloadableResourceManagerFix` 补的正是 NeoForge 的 `getListeners`/`updateListenersFrom`)在首轮重载时实际使用的监听器顺序打出来,与 `ModelManager`/`BlockRenderDispatcher` 的应有次序对照。

### 空模型的真因:烘焙出来的缺失模型本身就是 null(2026-09-14 深夜)

上一节那条猜测**被实测推翻**:顺序没问题,烘焙也确实跑了。为了看清这一点加了两个**默认关闭**的探针(打开方式:`-Doptifineoforge.debug.reload=true` 或环境变量 `OPTIFINEOF..._DEBUG_RELOAD=true`):

- `ReloadProbeFix`:在 `ReloadableResourceManager.createReload` 读 `listeners` 的地方插一条 `DUP` + 调用,把这次重载**将要按序执行的监听器清单**打出来;
- `ModelProbeFix`:在 `ModelManager.apply` 打 enter/leave(退出时反射读 `missingModel` 与 `blockStates`),在 `ModelBakery.bakeModels` 打进去时未烘焙模型、返回时烘焙结果,在 `BlockRenderDispatcher.onResourceManagerReload` 打 enter。

实测(1.21.4,`logs/run-probe7-1214`):

```
reload 1: 50 listeners
8   net.minecraft.client.resources.model.ModelManager          <- bakes the models
11  net.minecraft.client.renderer.block.BlockRenderDispatcher  <- asks for baked models
...
result ModelBakery (unbaked): missingModel=BlockModel
result ModelBakery.bakeModels: missingModel=null, blockStates=27870
enter BlockRenderDispatcher.onResourceManagerReload
```

三条结论:

1. **顺序正确**:`ModelManager` 在第 8 位、`BlockRenderDispatcher` 在第 11 位,`apply` 也真的执行了(而且把 27870 个方块状态装进了 `bakedBlockStateModels`)。所以"应用阶段跑在烘焙之前"不成立。
2. **`missingModel` 在烘焙那一刻就已经是 null**:同一批 worker 线程里,`ModelBakery` 自己的未烘焙缺失模型是 `BlockModel`(正常),但 `bakeModels` 返回的 `BakingResult.missingModel` 是 null;`ModelManager.apply` 只是把这个 null 原样搬进字段。于是 `getModel` 对**不在那 27870 项里的状态**(例如流体)返回 null —— `LiquidBlockRenderer.setupSprites` 一取 `getParticleIcon()` 就 NPE。
3. **监听器清单里每个原版监听器出现了两次**(0–21 与 24–45 是同一批,48/49 才是 OptiFine 的)。也就是 NeoForge 排序后的清单被**追加**到了原版清单后面,而不是替换。这本身就是要修的缺陷(每个原版监听器会跑两遍,烘焙也确实跑了两遍:两个 worker 各打了一次)。

顺带记一个**诊断陷阱**,它浪费了两次运行:`ITransformer.transform` 拿到的 `ClassNode.name` 是**斜杠内部名**,而 ModLauncher 的 `Target.targetClass` 用的是**点号名**。把两者混用会让探针静默失效(日志里 `probed 0`),看起来就像"这个类根本没被转换"。判据是同一行日志里打印的类名 —— 带斜杠的就是内部名。

下一步很明确:`bakeWithTopModelValues`(NeoForge 给 `UnbakedModel` 加的静态助手)会把调用转给 `IUnbakedModelExtension.bake(TextureSlots, ModelBaker, ModelState, boolean, boolean, ItemTransforms, ContextMap)`(七个参数、最后一个 `ContextMap`,由 `UnbakedModel` 继承的 NeoForge 扩展接口声明)。OptiFine **没有**替换 `BlockModel`(补丁清单里没有它的 xdelta),所以 null 是从 NeoForge 这条路上出来的 —— 要读的是 `IUnbakedModelExtension` 里那个默认实现的函数体(它不在 `neoforge-...-client.jar` 里,得去别的 NeoForge 产物里找),确认它在什么条件下返回 null。

### 空模型的真因找到了:被我们自己"打成桩"的方法(2026-09-14 深夜)

顺着上一节继续打探针,`BlockModel.bake`(七参、带 `ContextMap`)每一次返回都是 `null`:

```
value BlockModel.bake (...ContextMap;)Lnet/minecraft/client/resources/model/BakedModel;: null
value UnbakedModel.bakeWithTopModelValues (...): null
```

也就是说 null 不是"传丢了",而是从烘焙里出来的。`IUnbakedModelExtension`(在 `neoforge-...-universal.jar` 里)的默认实现只是老老实实转调六参 `UnbakedModel.bake`,NeoForge 的 `BlockModel.bake(七参)` 最后一行是 `SimpleBakedModel.bakeElements(...)` —— 于是问题落在这个**静态助手**上。

查供体就一目了然:

```
public static BakedModel bakeElements(...);
Code:
0: aconst_null
1: areturn
```

**它是被 `MemberRestorePlan` 打成桩的**:`referencesOnlyExisting` 判定"函数体引用了不存在的成员"时,会写一个返回默认值的桩,而桩对对象返回类型就是 `aconst_null`。这条路径一个模型都活不下来 —— 这既是空模型的真因,也是"补回来的成员看着对了、其实全是空壳"这类问题的样本。

**为什么会被判不合格?** 规则里有一条"super 调用只有在被复制进去的类的父类相同时才成立"(防止 `VerifyError: Bad invokespecial`)。但它的写法把**所有** `INVOKESPECIAL` 都当成 super 调用,包括 `new SimpleBakedModel$Builder(...)` 这种**构造别的类**的调用 —— 而 `SimpleBakedModel$Builder` 恰好被 OptiFine 替换过,于是这一句被误判,进而把整个 `bakeElements` 打成了桩。

修法只有一行语义:构造器调用不是 super 调用(`invokespecial` 但名字是 `<init>`,作用在刚分配出来的别的类的对象上,跟被复制进去的类的父类无关)。改完之后:

- 构建输出里 **`stub (body would not verify)` 一行都没有了**(此前有,只是没被我打印出来看);
- 供体里的 `SimpleBakedModel.bakeElements` 是真正的函数体(会 `new SimpleBakedModel$Builder(...)`、逐个 `bakeFace`、最后 `builder.build(...)`)。

这套"静默打桩"值得当作一条教训记下来:**桩是最后的兜底,但它的失败方式是静默的**,只有拿到具体症状(模型全空)才暴露。以后每次构建都该看一眼有哪些桩,而不是让它悄悄存在。

修完后旧症状消失(`BlockModelShaper.getBlockModel` 返回 null 的 NPE 不再出现、`Cowardly refusing ... broken mod state` 也没了),但紧接着换成一个**明确得多**的错误 —— OptiFine 替换过的 `SimpleBakedModel$Builder` 构造器里读了 Forge 类:

```
java.lang.NoSuchFieldError: Class net.minecraftforge.client.RenderTypeGroup does not have member
  field 'net.minecraftforge.client.RenderTypeGroup EMPTY'
  at net.minecraft.client.resources.model.SimpleBakedModel$Builder.<init>(SimpleBakedModel.java:215)
  at net.minecraft.client.resources.model.SimpleBakedModel.bakeElements(SimpleBakedModel.java:100)
```

我们为 Forge 类生成的**空壳**(`ForgeApiShims`)只有类名、没有成员,而 OptiFine 的代码要读 `RenderTypeGroup.EMPTY` 这个静态字段。这就是下一步:让 Forge 空壳带上 OptiFine 真正引用的成员(字段给常量/占位实例,方法给默认返回),而不是只有一个空类 —— 否则每修好一处就会撞上下一个。

另外记一句好消息:这一轮 OptiFine 的功能已经在跑 —— 日志里有 `[OptiFine] ConnectedTextures: optifine/ctm/default/00_glass_white/glass_white.properties`、`CustomItems: Registering sprites`、`BetterGrass: Parsing default configuration`,贴图集也在正常拼接。

### 三处连着的修复,模型终于真的烘焙出来了(2026-09-15 凌晨)

**① Forge 空壳不再是空壳。** `ForgeApiShims` 现在除了类名,还带上 OptiFine 的类**真正读写的成员**:扫 OptiFine jar 里每个 class 的常量池(ASM `visitFieldInsn`/`visitMethodInsn`),把 owner 是 `net/minecraftforge/**` 的字段与方法按签名收集起来,生成到对应空壳里 —— 字段给默认值(null/0/false),方法给默认返回;接口的实例方法保持抽象(谎称能答不如说答不了),引用到的构造器补一个只调 `super()` 的。

这里有个**关键的顺序问题**:需要成员的引用在**打完补丁的游戏类**里(例如 OptiFine 替换后的 `SimpleBakedModel$Builder` 读 `RenderTypeGroup.EMPTY`),而补丁输出是在重打包之后才有的。所以 rig 的构建脚本改成在 patch 步骤之后、用 `ForgeApiShims <输出目录> <patched jar> <OptiFine jar>` **重新生成**一遍空壳,并在合并时丢掉重打包阶段生成的那批空壳(否则会被"只添加不覆盖"的逻辑挡住)。实测:55 个 Forge 类型、54 个成员,`RenderTypeGroup` 现在有 `EMPTY`、`isEmpty()`、`block()`。

**② 构造器也要补。** 修完 ①,下一个错误是:

```
NoSuchMethodError: 'void SimpleBakedModel.<init>(List, Map, boolean, boolean, boolean,
  TextureAtlasSprite, ItemTransforms, net.neoforged.neoforge.client.RenderTypeGroup)'
```

这是 NeoForge 给 `SimpleBakedModel` 加的重载(第八个参数是 **NeoForge** 的 `RenderTypeGroup`),OptiFine 的替换版本只有 **Forge** 类型的那个同名重载;而我们补回来的 `SimpleBakedModel$Builder.build(...)` 正是要调 NeoForge 这个。计划里原先一句 `continue` 把所有 `<init>` 都跳过了("constructors are handled by the targeted fixes"),现在改成**构造器也进计划**,但有一条硬规则:**构造器不合格就不补,绝不打桩** —— 空构造器不会连 `super`,整个类都过不了验证,补一个坏的还不如不补(会打印 `constructor not restorable`)。计划成员数从 124 涨到 132(含这个构造器)。

顺带修了一个会**互相打架**的地方:补字段的初始化方法此前会被插进**每个**构造器的每个 `RETURN` 之前,包括刚补进来的那个构造器 —— 而那个构造器自己已经赋了值,初始化方法再把默认值写回去,等于把值抹掉。现在只对那些**没有自己赋值**的字段插初始化调用。

**结果(实测)**:模型链彻底通了 ——

```
result ModelBakery.bakeModels: missingModel=SimpleBakedModel, blockStates=27870
leave ModelManager.apply: missingModel=SimpleBakedModel, blockStates=27870
[OptiFine] Animated sprites: 2
Created: 1024x1024x4 minecraft:textures/atlas/blocks.png-atlas
Sound engine started
```

`missingModel` 是一个真的 `SimpleBakedModel`(不再是 null),方块贴图集 1024x1024 也建出来了,rig 判定 **`STARTED`**。

**新的第一因(未解决)**:贴图集拼接报错,游戏随后自行恢复:

```
net.minecraft.ReportedException: Stitching texture atlas
Caused by: java.lang.IllegalStateException: Image is not allocated.
```

这条大概率在 OptiFine 的自定义贴图/连接纹理路径上(`CustomItems`/`ConnectedTextures` 这一轮已经在跑),下一轮从这里开始。

### 贴图集上传失败的定位(2026-09-15 凌晨)

又加了一个探针(`NativeImageProbeFix`,默认关闭):在 `NativeImage.close` 打一条带**短栈**的 trace,并在 `NativeImage.upload` 打 enter。一次运行拿到 31402 次 `close`、40283 次 `upload`,按调用者分组:

```
  30634  net.minecraft.client.renderer.texture.SpriteContents.close
    230  SpriteContents$InterpolationData.close
    176  SpriteContents.rescale
    160  atlas.sources.LazyLoadedImage.release
```

再对 `SpriteContents.close` 的调用者分组,只有一个:

```
  30634  net.minecraft.client.renderer.texture.TextureAtlas.clearTextureData
```

完整栈长这样(一次实例):

```
at com.mojang.blaze3d.platform.NativeImage.close
at net.minecraft.client.renderer.texture.SpriteContents$InterpolationData.close
at net.minecraft.client.renderer.texture.SpriteContents$Ticker.close
at net.minecraft.client.renderer.texture.TextureAtlasSprite$1.close
at net.minecraft.client.renderer.texture.TextureAtlas.clearTextureData
at net.minecraft.client.renderer.texture.TextureAtlas.upload
at net.minecraft.client.resources.model.AtlasSet$StitchResult.upload
at net.minecraft.client.resources.model.ModelManager.apply
```

也就是说:**`TextureAtlas.upload` 在 `clearTextureData()` 里关掉的,正是它接下来要上传的那批精灵** —— `clearTextureData` 本意是释放**上一批**精灵的 ticker 与插值数据,这里却关到了新的那批,于是 `SpriteContents.uploadFirstFrame` 一上传就撞上 `Image is not allocated.`(30k 这个数量级也和方块贴图集的精灵数吻合)。

最可能的原因就是上一节记下的那个缺陷:**监听器清单里每个原版监听器出现了两次**。同一批精灵被两轮上传/清理交叉处理时,第二轮的 `clearTextureData` 关掉的正是第一轮刚建好的精灵。下一轮第一件事:查清这份清单为什么会重复 —— 需要对照 OptiFine 替换后的 `Minecraft`(Forge 时代那版)与 NeoForge 的注册顺序,以及 `ReloadableResourceManager.updateListenersFrom` 到底该替换还是追加。

### 重复清单的来源缩到 NeoForge 自己的排序里(2026-09-15 凌晨)

探针扩到 `registerReloadListener`(打印被注册的监听器)与 `updateListenersFrom`(替换前后各打印一次清单长度)。实测序列非常干净:

```
registerReloadListener: LanguageManager … PeriodicNotificationManager   ← 22 个原版监听器
size before updateListenersFrom: 22
size after  updateListenersFrom: 48      ← 确实是"替换",不是追加
registerReloadListener: (匿名类) ×2                                      ← OptiFine 的 TextureUtils$1/$2
reload 1: 50 listeners
```

于是先前的猜测被排除两条,也定位到真正的范围:

- **`updateListenersFrom` 是对的**(替换而非追加)。离线读供体字节码就是这个: `this.listeners = ReloadListenerSort.sort(event)` —— 供体是 NeoForge 自己的实现,一行不差。
- **`Minecraft` 没有被 OptiFine 替换**(补丁清单里没有它),所以那 22 次注册就是游戏自己的。
- **重复发生在排序结果内部**:替换后立刻就是 48 条,而 48 = 原版 22 + 2 个 NeoForge lambda + **原版 22 又一遍** + ObjLoader/AnimationLoader;最后 OptiFine 的 2 个匿名监听器是在替换之后再注册的,于是变成 50。

调用链也读清了:`ClientHooks.initClientHooks(Minecraft, ReloadableResourceManager)` 里 `new AddClientReloadListenersEvent(resourceManager)` → `ModLoader.postEvent(event)` → `resourceManager.updateListenersFrom(event)`,而这个事件的构造器就是把 `ReloadableResourceManager.getListeners()`(那 22 个)交给 `SortedReloadListenerEvent`。

也就是:**当 `getListeners()` 里已经有原版监听器时,NeoForge 的这条排序路径会把它们再算一遍**。下一轮要读的是 `SortedReloadListenerEvent` 的图/注册表构建与 `ReloadListenerSort.sortListeners`,确认原版那一组是从哪里第二次进来的,再决定修在我们这边(例如事件构造前让 `getListeners()` 只给出模组新增的那些)还是在替换类上补一个等价物。

### 排序内部:两个待验的机制(2026-09-15 凌晨)

上面两条路都读过了,结论是**它们都不可能凭空产生重复**,于是把范围压到两种机制之一,下一轮用一个探针就能分开:

- `SortedReloadListenerEvent` 的构造器(`neoforge-...-universal.jar`)对传进来的清单逐个调用
  `addListener(nameLookup.apply(listener), listener)`,而 `addListener` 往 `LinkedHashMap registry` 里放 —— **同一个 key 只会覆盖,不会重复**;
- `ReloadListenerSort.sortListeners` 最后是
  `TopologicalSort.topologicalSort(graph, comparingInt(order))`,输入是 guava 的 `MutableGraph`,而 guava 图是**节点的集合,重复节点会被合并**。

也就是说,要让排序结果里出现两遍原版监听器,只能是下面二者之一:

1. **两遍的 key 不同**(例如 `getNameForClass` 前后返回不同的名字),于是 `registry` 与图中同时留下两组、每组 22 个;
2. **NeoForge 内部还有一份原版监听器的来源**(例如 `VanillaClientListeners` 自己往图里注册了一遍),与游戏注册的那 22 个各自成组。

区分办法很直接:在 `updateListenersFrom` 返回处把结果清单**按对象身份**(`System.identityHashCode`)打出来 —— 如果两组 22 个的 identity 相同,就是机制 1;如果 identity 不同,就是机制 2(两组是不同实例)。这比继续读字节码快得多。

### 实测:是**同一个对象**出现了两次(2026-09-15 凌晨)

探针打上了 identity,结果很干净:

```
8   net.minecraft.client.resources.model.ModelManager  @2007522934   <- bakes the models
32  net.minecraft.client.resources.model.ModelManager  @2007522934   <- bakes the models
```

两组里对应位置的 identity **完全相同** —— 所以不是"两份不同的实例",而是**同一批对象被排进了结果两次**:机制 1 那一侧。50 条里只有 28 个不同的类名,也印证了这一点。

结合前面读到的两段实现(事件构造器用 `LinkedHashMap` 注册、`sortListeners` 走 guava 图),最合理的解释是:**结果 = 「按原版名字排列的那一组」前缀 + 「走拓扑排序得到的那一组」**,而后者本来也应该包含模组监听器;当事件构造时传进来的 `getListeners()` **已经含那 22 个原版监听器**时,它们就同时出现在这两部分里。

也就是说,问题可能不在"谁注册了两次",而在**事件构造的时刻**:这一个运行里 22 次注册发生在 `updateListenersFrom` 之前。下一轮要做一次**对照测量**:用同一套探针,在**只装 NeoForge、不打 OptiFine 补丁**的情况下跑一遍(需要一个只含 loader 与探针、不含 OptiFine 的 jar),看原版 NeoForge 在这个时刻 `getListeners()` 是不是空的、`updateListenersFrom` 前后各是多少 —— 这能直接判定"顺序被谁改了",而不必继续猜。
