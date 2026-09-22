# 鐗堟湰鐭╅樀鐜扮姸涓庢帹杩涢『搴?

> **鏈枃鎸夋椂闂磋拷鍔?鏄?褰撴椂鍐欎簡浠€涔?鐨勮褰曘€?* 椤堕儴鐨勯樁娈垫€荤粨鍐欎簬 2026-09-16,閲岄潰鐨?鏈紑濮?"鍙戝竷:涓€娆￠兘娌℃湁"
> 绛夊垽鏂湪 2026-09-18 涔嬪悗宸茬粡涓嶆垚绔?**15 / 15 鏉＄嚎鍏ㄩ儴瀹炴満楠岃瘉,1.0.0 宸插彂甯?10 鏉?** 鈥斺€?
> 褰撳墠鐘舵€佷笌閫愭潯鏁板瓧瑙佹枃鏈殑 [銆?.0.0:绗竴娆＄湡瀹炲彂甯冦€峕(#10绗竴娆＄湡瀹炲彂甯?026-09-18) 涓€鑺?閭ｉ噷涔熸湁鏈彂甯冪殑
> 5 鏉″強鍚勮嚜鐨勫師鍥犮€備笅闈繚鐣欏師鏂?涓嶆敼鍐欏巻鍙茬粨璁恒€?

鏈枃鍙褰?*瀹炴祴**鐨勫樊璺濅笌椤哄簭,涓嶉噸澶?`docs/PLAN.md`(璁″垝)涓?`docs/DEVELOPMENT.md`(瀹炴祴璁板綍)銆?

## 褰撳墠鐪熷疄杩涘害(2026-09-16,闃舵鎬ф€荤粨)

**鐩爣灏氭湭杈炬垚**,浣嗗叚鏉＄嚎宸茬粡瀹炴満璺戝埌鏍囬鐢婚潰銆傞€愯鐨勫疄娴嬬姸鎬佸涓?鍒ゆ嵁鏄?瀹炴満鍚姩 + 琛屼负涓庡凡楠岃瘉绾夸竴鑷?,
涓嶆槸"鑳界紪璇?:

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?| 璇佹嵁 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 | 47.1.106 | **宸查獙璇?* | 鍚姩鎴愬姛銆乣forge shells in the jar: 0`銆丱ptiFine 412 targets銆佽鍒?550 鎴愬憳/87 绫汇€佽祫婧愰噸杞芥棤閿欍€佽创鍥鹃泦寤烘垚 |
| 1.20.x | 1.20.2 | 20.2.88 | **宸查獙璇?* | `STARTED (40s)`銆乣Setting user` 鉁撱€?39 琛?`[OptiFine]`銆乣Pre-stitch` 脳13銆佺潃鑹插櫒 14 琛屻€乣Caught error: 0`;宸茬煡缂洪櫡瑙佷笅(Reflector 鍔犺浇 `BlockState`/`ItemStack` 澶辫触,stderr 14,631 瀛楄妭) |
| 1.20.x | 1.20.4 | 20.4.251 | **宸查獙璇?* | `STARTED (40s)`銆乣Setting user` 鉁撱€?41 琛?`[OptiFine]`銆乣Pre-stitch` 脳13銆丆TM 鉁撱€乣Caught error: 0`;宸茬煡缂洪櫡鍚屼笂(stderr 14,481 瀛楄妭)銆?*杩愯瑕佹眰**:`config/fml.toml` 璁?`earlyWindowProvider = "none"` 鈥斺€?FML 鐨?early window 涓?OptiFine 鎹㈣鐨勬覆鏌撶被浼氬湪鍚屼竴甯ч噷閲嶅叆(`SimpleBufferBuilder: Already building`) |
| 1.20.x | 1.20.6 | 20.6.141 | **宸查獙璇?* | 鍚姩鎴愬姛銆丱ptiFine 鐫€鑹插櫒涓庤繛鎺ユ潗璐ㄥ湪璺戙€佽创鍥鹃泦寤烘垚;杩欎竴鐗堣捣 FML 鎷掔粷鍘熺増 OptiFine jar,鎵€浠ヨ闈犳湰椤圭洰鐨勯噸鎵撳寘涓庡厓鏁版嵁淇 |
| 1.21.x | 1.21.1 | 21.1.250 | **宸查獙璇?*(2026-09-16) | `STARTED (40s)`銆乣Setting user` 鉁撱€?13 涓被鎹㈣(鐩爣 426)銆?23 琛?`[OptiFine]`銆乣Pre-stitch` 脳14銆丆TM 脳3銆佺潃鑹插櫒 鉁撱€乣Caught error: 0`銆?*stderr 0 瀛楄妭**;杞借嵎鐖剁被琚敼鍐?`CapabilityProvider` 鈫?`AttachmentHolder`) |
| 1.21.x | 1.21.3 | 21.3.97 | **宸查獙璇?*(2026-09-16) | 涓€娆℃€ц窇閫?`STARTED (40s)`銆乣Setting user` 鉁撱€?68 涓被鎹㈣(鐩爣 445)銆?25 琛?`[OptiFine]`銆乣Pre-stitch` 脳14銆丆TM 脳3銆佺潃鑹插櫒 鉁撱€乣Caught error: 0`銆?*stderr 0 瀛楄妭**;杞借嵎鐖剁被鏀瑰啓涓?1.21.1 鍚屽舰(`CapabilityProvider` 鈫?`AttachmentHolder`) |
| 1.21.x | 1.21.4 | 21.4.149 | **宸查獙璇?* | 鍚姩鎴愬姛銆丱ptiFine 474 targets銆佹ā鍨嬬儤鐒?`missingModel=SimpleBakedModel`)銆?024脳1024 璐村浘闆嗐€?32 琛?`[OptiFine]`銆乣Pre-stitch` 脳14銆乻tderr 0 瀛楄妭;杩欎竴绾胯蛋 OptiFine 鑷繁鐨勮繍琛屾湡琛ヤ竵,涓嶆崲绫?|
| 1.21.x | 1.21.6 / 1.21.7 / 1.21.8 | 21.6.20-beta / 21.7.25-beta / 21.8.54 | **宸查獙璇?*(2026-09-16) | 涓夋潯閮?`STARTED (40s)`銆乣Setting user` 鉁撱€?*stderr 0 瀛楄妭**銆佹棤鏈杩愯鐨?crash report:1.21.6 340 琛?`[OptiFine]`,1.21.7 340 琛?1.21.8 337 琛?涓夋潯閮芥崲瑁?~500 涓被骞跺洖濉?~350 涓垚鍛樸€備笁鏉＄殑涓婃父缂洪櫡(OptiFine 棰勮鐗堝紑鍚潃鑹插櫒浼氬穿鍦?OptiFine 鍐呴儴)浣块獙鏀朵笉鍚潃鑹插櫒,瑙?`OptifiNeoforge-121x/docs/PLAN.md` |
| 1.21.x | 1.21 / 1.21.9 / 1.21.10 / 1.21.11 | 21.0.167 / 21.9.16-beta / 21.10.64 / 21.11.45 | **鏈紑濮?* | 1.21.2 涓?1.21.5 娌℃湁 OptiFine 鏋勫缓;1.21 鍙槸鍓嶇疆(NeoForge 21.0.167 鏈);**1.21.9 璧?NeoForge 涓嶅啀鐢?ModLauncher**(瀹炴祴 21.11.45 鐨?profile:涓荤被 `net.neoforged.fml.startup.Client`,搴撻噷娌℃湁 modlauncher/securejarhandler,鍙湁 FML 10 + sponge-mixin),鎸傝浇鐐硅鎹㈡垚 `ClassProcessor`,鑰?OptiFine 1.21.11 J9 **涓嶅惈** processor,瑕佽嚜宸卞啓 |
| 26.x | 26.1.2 | 26.1.2.109 | **鏈紑濮?* | FML 11 鍘绘帀 ModLauncher;OptiFine K1_pre2 鑷甫 `OptiFineClassProcessor`,闇€鍏堢粫杩?`IncompatibleModReason` 涓?`loaderVersion` |

**宸茬煡缂洪櫡(涓庡姞杞藉櫒鏃犲叧,1.20.2 / 1.20.4 鍏辨湁)**:OptiFine 鐨?`Reflector` 鍦?`GameRenderer.frameInit` 閲?
鍙嶅皠 `BlockState` 鏃舵嬁鍒?
`NoClassDefFoundError: net.minecraft.world.level.block.state.BlockState`
(鍘熷洜 `ClassNotFoundException: 鈥lockState`,`ItemStack` 鍚屾牱),鏉ヨ嚜
`net.optifine.reflect.ReflectorMethod.getMethod`銆傚熀绾?鏀瑰姩鍓嶅悗)瀛楄妭鏁颁竴鑷?涓嶉殢鏈疆鐨勭埗绫绘敼鍐欏彉鍖?
鎵€浠ュ畠鏄?*鐙珛寰呭姙**:瀹冨喅瀹氱殑鏄?OptiFine 鐨勫摢浜涘弽灏勫姛鑳戒笉鍙敤,鑰屼笉鏄繖涓ゆ潯绾胯兘鍚﹁窇璧锋潵銆?

**鍙戝竷:涓€娆￠兘娌℃湁銆?* `release/version.ps1` 鍙湪 dry-run 閲岃窇杩?浠撳簱鏈夊洓涓垎鏀?`main` 钀藉湴椤点€乣1.20.x`銆?
`1.21.x`銆乣26.x`),`main` 鍙湁钀藉湴椤点€?

### 1.20.4 鍓╀笅閭ｄ竴姝ョ殑鍏蜂綋鍋氭硶(涓嬩竴杞捣鐐?

宕╂簝鐐瑰湪 `NeoForgeLoadingOverlay.render` 鈫?FML `SimpleBufferBuilder.begin` 鎶?`Already building`銆傚洜涓?
OptiFine 鎹㈣浜?`LoadingOverlay`(25 fields / 12 methods),瀹冪殑鍔犺浇鐢婚潰浠ｇ爜涓?NeoForge 鐨?early-display
鍚屽抚閮藉幓椹卞姩鍚屼竴涓?buffer銆傛煡鐨勬柟鍚?

1. 鍏堢‘璁ゆ槸涓嶆槸鍚屼竴甯т袱鏉℃覆鏌撹矾寰勯兘杩涗簡 early display(鍦?`NeoForgeLoadingOverlay.render` 涓婂姞涓€涓?
   鎺㈤拡,鐓?`ReloadProbe` 閭ｅ);
2. 鑻ユ槸 OptiFine 鐨?`LoadingOverlay` 琛ヤ竵鍦ㄦ姠娓叉煋,鑰冭檻**涓嶆崲瑁?`LoadingOverlay`**(鎶婂畠浠?
   `PatchedClassTransformer` 鐨勭洰鏍囪〃閲屽幓鎺?鈥斺€斿姞杞界敾闈㈡湰鏉ュ氨涓嶆槸 OptiFine 鐨勬牳蹇冧环鍊?
   鑰屽畠鏄繖涓€鏉＄嚎涓婂敮涓€宸茬煡鐨勫啿绐佹簮;
3. 涔嬪悗鍐嶆寜 1.21.4 鐨勯獙鏀舵竻鍗曟牳瀵?璐村浘闆嗐€佹ā鍨嬬儤鐒欍€佽祫婧愰噸杞?0 閿?,鎶?1.20.4 浠?鎺ヨ繎"鎺ㄥ埌"宸查獙璇?銆?

### 宸茬粡寤烘垚鐨勫彲澶嶇敤璧勪骇(涓庣増鏈棤鍏?

- `SrgMemberMap` / `SrgRemap`:SRG鈫攐fficial 琛?+ 杞借嵎鏀瑰悕(1.20.2/1.20.4 蹇呴渶;1.20.6+ 鑷劧绌鸿浆)
- `MissingTargets`:鍒楀嚭"杩愯鏃朵笌杞借嵎閮芥病鏈?鐨勬垚鍛?骞跺彲鐢?`--stub` 琛ラ粯璁ゅ疄鐜?
- `OptifineJar` / `OptifinePipeline` / `MemberRestorePlan` / `ForgeApiShims` / `OptifineJarFixer`
- loader 渚?`PatchedClassTransformer`(鎹㈣鎴愬搧绫?鍚闂爣蹇椾笌鎺ュ彛骞堕泦涓ゆ潯瑙勫垯)+ 鏃㈡湁鍚勯」淇ˉ
- rig:`build-rig-jar.ps1` 鐨?`-SrgRemap` / `-StubMissing` / `-ShipPayload` 涓変釜寮€鍏冲凡璺戦€?1.20.4

### 杩欎竴鏉＄嚎璧拌繃鐨勮矾(涓轰粈涔堟瘡涓€姝ラ兘鏄繀闇€鐨?

1.20.4 浠?瀹屽叏璺戜笉璧锋潵"鍒?鍙樊涓€涓穿婧?,涓棿渚濇瑙ｅ喅:瀹夎鍣ㄤ笉浜у嚭娲剧敓 jar 鈫?鎵撹ˉ涓侀渶瑕?*鍘熺増娣锋穯
jar**(涓嶆槸 SRG jar)鈫?杞借嵎涓庤ˉ涓佷骇鐗╅兘鏄?SRG 鍚?闇€瑕佹敼鍚?鍚０鏄?涓嶅彧鏄紩鐢?鈫?鏀瑰悕瑕佽窇鍦ㄦ垚鍛樻仮澶嶈鍒?
涔嬪墠 鈫?鎴愬搧绫讳笉鑳藉钩閾哄湪 `srg/` 涓?妯″潡鍖呭啿绐?鈫?鎴愬搧绫荤敱鎴戞柟 transformer 鎹㈣ 鈫?鎹㈣蹇呴』澶嶅埗璁块棶鏍囧織
(绫诲彇 OptiFine 鐨勩€佹垚鍛樺彇涓よ€呬腑鏇村鐨?鈫?杩愯鏃朵笌杞借嵎閮芥病鏈夌殑鎴愬憳瑕佽ˉ绌哄疄鐜?鈫?鎹㈣**鎺ュ彛**鏃惰秴鎺ュ彛瑕?
鍙栧苟闆嗚€屼笉鏄浛鎹€傛瘡涓€姝ラ兘鏈夊疄娴嬭瘉鎹?璁板湪 `docs/DEVELOPMENT.md`銆?


## 瀹炴祴:涓夋潯鍒嗘敮鐨勫樊璺?

```
origin/1.20.x : 10 files
origin/1.21.x : 10 files
origin/26.x   : 39 files
```

`26.x` 涓婇偅 39 涓枃浠跺寘鍚?*鍏ㄩ儴瀹炵幇**:loader 渚х殑 `OptifiNeoforgeTransformationService` 涓庡悇椤逛慨琛?
(`TagHelperFix`銆乣PackRootsFix`銆乣ReloadProbeFix`銆乣ModelProbeFix`銆乣NativeImageProbeFix`銆?
`MemberRestoreTransformer`銆乣RenderTargetFix`銆乣ReloadableResourceManagerFix`銆乣ConventionTags`)銆佺绾垮伐鍏?
(`MemberRestorePlan`銆乣ForgeApiShims`銆乣OptifineJar`銆乣OptifineJarFixer`銆乣OptifinePipeline`銆?
`OptifineConfig`銆乣DonorVerifier`),浠ュ強 `build.gradle`銆乣gradle.properties`銆乣release/version.ps1`銆?

`1.20.x` 涓?`1.21.x` 鐩墠浠嶅彧鏈夐鏋?**娌℃湁 loader銆佹病鏈夊伐鍏枫€佹病鏈夊搴旇鐨勬瀯寤洪厤缃?*銆?

## 鍚勮鐨勭洰鏍囧弬鏁?

| 绾?| Minecraft | NeoForge | Java | 澶囨敞 |
|---|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 | `1.20.1-47.1.106` / `20.2.93` / `20.4.251` | 17 | 鍓嶄袱鑰呰浇鑽锋槸鐪熸鐨?SRG 鍚?闇€瑕?SRG鈫抩fficial 閲嶆槧灏?|
| 1.20.x | 1.20.6 | `20.6.141` | 21 | 浠庤繖涓€鐗堣捣 FML 鎷掔粷鍘熺増 OptiFine jar(`IncompatibleModReason.OPTIFINE`) |
| 1.21.x | 1.21.1 鈥?1.21.11 | `21.0.167` 鈥?`21.11.45` | 21 | 1.21.6 / 1.21.7 / 1.21.9 鍙湁 beta;1.21.3 璧?`loaderVersion="[14,)"` 鏍￠獙澶辫触 |
| 26.x | 26.1.2 | `26.1.2.109` | 25 | FML 11 宸插幓鎺?ModLauncher,璧?`ClassProcessor` |

## 鎺ㄨ繘椤哄簭(鎸変緷璧?涓嶆寜鐗堟湰鍙?

1. **鎶婂疄鐜版惉鍒?`1.21.x`**:鍏堝彧鎼笌鐗堟湰鏃犲叧鐨勯儴鍒?宸ュ叿涓?loader 楠ㄦ灦),鍐嶆寜璇ヨ鏀?
   `gradle.properties`(`minecraft_version`銆乣neoforge_version`銆乣mod_version_base`銆丣ava 21)涓?
   `build.gradle` 鐨?`targetJavaVersion`銆傞獙璇佹爣鍑嗘槸**璇ュ垎鏀兘 `gradlew build`**銆?
2. **`1.21.4` 鐨勫姛鑳介樆濉炲厛瑙ｅ喅**(閲嶅鐨勭洃鍚櫒娓呭崟 鈫?璐村浘闆?`clearTextureData` 鍏虫帀浜嗚涓婁紶鐨勭簿鐏?
   瑙?`docs/DEVELOPMENT.md`)銆傚悓涓€鏉＄嚎涓婁慨瀹屽啀寰€涓嬮摵,鍙互閬垮厤鎶婂悓涓€涓敊璇鍒跺埌涓夋潯绾裤€?
3. **`1.20.x`**:鍚屾牱鍏堟惉瀹炵幇,鍐嶈ˉ SRG鈫抩fficial 閲嶆槧灏?1.20.2 / 1.20.4),鏈€鍚庡鐞?1.20.6 璧?FML
   瀵瑰師鐗?OptiFine jar 鐨勬嫆缁濄€?
4. **`26.x` 鐨?`ClassProcessor` 璺嚎**:FML 11 娌℃湁 ModLauncher,鐜版湁 `ITransformationService` 鍦ㄦ琛?
   涓嶉€傜敤,闇€瑕佸崟鐙疄鐜?`net.neoforged.neoforgespi.transformation.ClassProcessor` 閭ｆ潯璺€?
5. 姣忓畬鎴愪竴琛?鎸?`docs/PUBLISHING.md` 璧颁竴娆″彂甯冩紨缁?鍗囩増銆佹瀯寤恒€佹墦鏍囩),浣?*涓嶈**鍦ㄦ病鏈夊疄鏈?
   鍚姩璁板綍鐨勬儏鍐典笅鎶?`0.x` 鍗囧埌 `1.0.0`銆?

## 宸茬煡椋庨櫓

- **涓嶈鍋囪"宸ュ叿璺ㄧ増鏈€氱敤"**:宸ュ叿閲屽鍛藉悕绌洪棿(`srg/` 涓?`notch/`)銆乣patch/srg` 涓?`patch/notch`
   鐨勫亣璁惧湪 1.20.6 鍓嶅悗鏄笉鍚岀殑,鎼縼鏃跺繀椤婚€愯纭銆?
- **涓嶈鐢ㄥ彟涓€鏉＄嚎鐨勫惎鍔ㄧ粨鏋滀唬鏇块獙璇?*:姣忔潯绾跨殑 `gradle.properties`銆丱ptiFine 鐗堟湰涓?NeoForge
   鐗堟湰閮戒笉鍚?rig 閲岀殑 profile 涓庢父鎴忕洰褰曚篃瑕佸悇鑷缓绔嬨€?

## Measured 2026-09-15: only one version-specific assumption in the code

Scanning the implementation for version-specific assumptions before moving it
(class-file version constants, ModLauncher dependencies, hard-coded versions)
gave a smaller answer than expected:

- Exactly one class-file version is written anywhere: `Opcodes.V17` in
  `ForgeApiShims`. A Java 17 class file loads on the 21 and 25 runtimes just as
  well, so no line needs it changed.
- All nine loader transformers depend on ModLauncher
  (`OptifiNeoforgeTransformationService`, `MemberRestoreTransformer`,
  `TagHelperFix`, `PackRootsFix`, `ReloadProbeFix`, `ModelProbeFix`,
  `NativeImageProbeFix`, `RenderTargetFix`, `ReloadableResourceManagerFix`).
  That confirms from the code side that 26.x needs the loader layer rewritten
  against `net.neoforged.neoforgespi.transformation.ClassProcessor`, while
  1.20.x and 1.21.x reuse it as it is.
- No code branches on the Minecraft version; versions appear only in comments and
  command-line arguments, and OptifineConfig reads them from OptiFine's own
  constant pool.

So bringing 1.21.x up is copying the implementation plus writing that line's
build configuration rather than porting it, and 26.x is the only line that needs
genuinely new code.
## 2026-09-15: the 1.20.x line needs a different NeoForge coordinate

The implementation and this line's build configuration are committed on 1.20.x
(ee908cb), but its build does not resolve the plugin's dependency:

    Could not find net.neoforged:neoforge:1.20.1-47.1.106.
      Searched in .../net/neoforged/neoforge/1.20.1-47.1.106/neoforge-1.20.1-47.1.106.pom

NeoForge publishes that version under a different artifact id, which was checked
rather than assumed - fetching
https://maven.neoforged.net/releases/net/neoforged/forge/1.20.1-47.1.106/forge-1.20.1-47.1.106.pom
returns 200 and names the artifact "forge", while the "neoforge" path does not
exist. ModDevGradle 2.0.147 asks for net.neoforged:neoforge, and trying 1.0.23 -
the generation from that era - asks for the same thing, so the version string alone
does not select the artifact.

So this line needs either the plugin generation that knows about the forge artifact
(the net.neoforged.gradle.userdev line, which predates ModDevGradle) or an explicit
dependency on net.neoforged:forge with the plugin's resolver out of the way. That is
the next thing to try for 1.20.x; nothing else about the line is blocked, and the
implementation is in place and compiles wherever this line's build is set up to
resolve its dependencies.

Process note: the attempt left the working tree dirty on 1.20.x because the build
was run before deciding whether to keep the change, and the branch switch in the
finally block was refused for that reason. Reverted; the lesson is to make the tree
clean before switching, not after.
## 2026-09-15: what the 1.20.x build actually asked for, and what it means

Two attempts, each with the exact coordinate the toolchain demanded:

    net.neoforged.moddev (2.0.147 and 1.0.23) -> Could not find net.neoforged:neoforge:1.20.1-47.1.106
    net.neoforged.moddev.legacyforge (2.0.147) -> Could not find net.minecraftforge:forge:1.20.1-47.1.106

NeoForge publishes this line as net.neoforged:forge:1.20.1-47.1.106 (the pom under
that artifact id returns 200 and names "forge"; the "neoforge" path does not exist).
The normal plugin always asks for net.neoforged:neoforge. The legacy plugin - which
NeoForged's ModDevGradle 2 announcement says covers "Forge 1.17 to 1.20.1 and
NeoForge 1.20.1" - asks for net.minecraftforge:forge, i.e. it treated 47.1.106 as a
MinecraftForge version, and Forge's 47.1.x line does not have that build.

So the remaining question for this line is how the legacy plugin is told that the
artifact lives under net.neoforged rather than net.minecraftforge. Two candidates,
to try in that order: a fully qualified coordinate in legacyForge.version, or the
plugin generation from before ModDevGradle that shipped alongside NeoForge 1.20.1.

Worth recording too, because it changes what this line needs beyond the build
script: NeoForge 1.20.1 is the Forge-compatible line, so it runs on ModLauncher-era
FML - the loader layer our 1.21.x line already uses applies as it is, and OptiFine's
own jar may be accepted without the FML refusals that start at 1.20.6. This line's
mod metadata is also different: 1.20.1 reads META-INF/mods.toml with the Forge-style
dependency entries, not the META-INF/neoforge.mods.toml the other lines ship, so the
resource has to be written for this line rather than copied.
## 2026-09-15: the toolchain composes the coordinate itself, and cannot name 1.20.1

Handing the plugin a fully qualified coordinate shows how it builds one:

    Supplied String module notation
    'net.neoforged:neoforge:net.neoforged:forge:1.20.1-47.1.106' is invalid.

It takes the version string and prepends group and artifact itself -
net.neoforged:neoforge in the mode it chose here, net.minecraftforge:forge in the
run before - so a coordinate cannot be passed through that field, and neither mode
asks for the artifact NeoForge actually published this line under
(net.neoforged:forge:1.20.1-47.1.106, pom verified).

That closes the Gradle question for 1.20.1 with the plugins tried: the main plugin
(2.0.147, 1.0.23) wants net.neoforged:neoforge, the legacy plugin wants either that
or net.minecraftforge:forge depending on the mode it picks. What is left for the
build script is the plugin generation from that era - NeoGradle's userdev - which is
a different DSL, or leaving this line's Gradle build open and noting it.

None of that blocks the line's actual work: the 1.21.4 verification never went
through Gradle. The rig builds the loader by compiling the sources against
ModLauncher, ASM and log4j, repacks OptiFine, patches the game jar, plans the
restores and combines the result into a mod jar. That pipeline is what produced every
measurement on the 1.21.x line, and for 1.20.1 it needs only an OptiFine jar for that
version (already in test-downloads) and a NeoForge 1.20.1 instance to launch.

So the order for this line becomes: verify it through the rig first, and treat the
Gradle build as a separate item that is understood, not mysterious.
## 2026-09-15: a NeoForge 1.20.1 instance exists now, and the launcher owes it one property

Setup done on this machine, so the next round can start with a launch:

- NeoForge 1.20.1 installed as the profile 1.20.1-forge-47.1.106 (installer:
  forge-1.20.1-47.1.106-installer.jar, run with Java 17). The installer reported
  errors twice while maven.neoforged.net timed out, and wrote a profile whose 33
  libraries were missing. The libraries were then fetched directly from the
  profile's own downloads.artifact entries with a retry loop (30 downloaded, 4
  already present, 0 failed), after which the installer ran to completion
  ("Successfully installed client into launcher").
- Java 17 is at C:\Program Files\Java\jdk-17, and the vanilla 1.20.1 jar is present.
- test-downloads already holds OptiFine_1.20.1_HD_U_I6.jar and the 1.20.1 client jar.

The profile still will not start, and the reason is specific rather than mysterious:
BootstrapLauncher 1.1.2 - the era's launcher - requires a system property the
installer does not write into the profile. Reading the class shows it looks up
"legacyClassPath", with "legacyClassPath.file" as the variant pointing at a file, and
line 141 is the Optional.orElseThrow() that fails with "No value present". The
profile's own arguments.jvm carries -DlibraryDirectory, the module path with
--add-modules ALL-MODULE-PATH, and the add-opens/add-exports, but no legacyClassPath.

So the rig needs one era-specific addition for this line - pass
-DlegacyClassPath=<the game classpath it already computes> (or write it to a file and
pass legacyClassPath.file) - and then the same pipeline that verified 1.21.4 can run
here. That is the first thing to do next, followed by this line's mod metadata:
1.20.1 reads META-INF/mods.toml with Forge-style dependency entries, not the
META-INF/neoforge.mods.toml the other lines ship.
## 2026-09-15: the 1.20.x line's loader must target the older ModLauncher API

The combined-jar pipeline runs on 1.20.1 up to the loader compile, and stops there
with a concrete API difference rather than a mystery. Compiling the loader package
against the ModLauncher this line actually uses (10.0.9, installed by the NeoForge
1.20.1 installer) gives:

    MemberRestoreTransformer.java:34: error: cannot find symbol
    import cpw.mods.modlauncher.api.TargetType;
    symbol: class TargetType
    location: package cpw.mods.modlauncher.api
    ...: error: type Target does not take parameters
    ...: error: type TargetType does not take parameters

So on 10.0.9 the transformer API is not generic the way 11.0.4's is: there is no
TargetType class in that package, and Target is unparameterised. The loader was
written against 11.0.4, which is what 1.21.4 ships - so "the 1.20.x line reuses the
loader layer as it is" was true only for lines whose ModLauncher matches, and this
one does not.

The work this leaves is bounded and mechanical: adapt the eight transformers
(MemberRestoreTransformer, TagHelperFix, PackRootsFix, ReloadProbeFix, ModelProbeFix,
NativeImageProbeFix, SortProbeFix, RenderTargetFix, ReloadableResourceManagerFix) to
the 10.0.9 signatures. Two ways to do it, to be decided by what 10.0.9 offers: either
a per-line copy of the loader sources compiled against 10.0.9, or a small version
shim in this repository that both APIs can compile against. The pipeline itself needs
nothing else - steps 1 to 3 (repack, patch, member-restore plan) already ran for
1.20.1, which is the larger half.
## 2026-09-15: the exact shape of the 10.0.9 transformer API

Read from modlauncher-10.0.9.jar, so the adaptation is mechanical rather than鎺㈢储:

    public interface ITransformer<T> {
        T transform(T, ITransformerVotingContext);
        TransformerVoteResult castVote(ITransformerVotingContext);
        Set<ITransformer$Target> targets();
        default String[] labels();
    }
    public final class ITransformer$Target {            // nested, and NOT generic
        public static Target targetClass(String);
        public static Target targetPreClass(String);
        public static Target targetMethod(String, String, String);
        public static Target targetField(String, String);
        public TargetType getTargetType();              // the type travels on the target
    }
    public final class ITransformer$TargetType extends Enum { CLASS, METHOD, FIELD, PRE_CLASS }

Against 11.0.4, where Target<T> and TargetType<T> are top-level and generic and
ITransformer declares TargetType<T> getTargetType(), the per-line changes are:

1. imports: Target and TargetType are nested types of ITransformer on 10.0.9
   (cpw.mods.modlauncher.api.ITransformer$Target), so the import lines change, and the
   TargetType import disappears entirely;
2. targets(): Set<Target> rather than Set<Target<ClassNode>> - Target is not generic;
3. the getTargetType() override is removed: the method does not exist on 10.0.9, and the
   type is carried by each Target instead;
4. the factory call Target.targetClass(name) is the same name in both, so the bodies of
   targets() otherwise stay as they are.

That is nine files - MemberRestoreTransformer, TagHelperFix, PackRootsFix, ReloadProbeFix,
ModelProbeFix, NativeImageProbeFix, SortProbeFix, RenderTargetFix,
ReloadableResourceManagerFix - and the transformation service itself needs nothing: its
List<? extends ITransformer<?>> signature is the same in both APIs.
## 2026-09-15: the 1.20.1 line loads OptiFine; only this machine's assets are short

After the Java 17 and Forge-shell rules from the previous round, the 1.20.1 run gets
all the way through mod loading with the mod present:

    OptiFineTransformationService: Targets: 412
    OptifiNeoforge: Member restore plan: 550 members across 87 classes
    NeoForge mod loading, version 47.1.106, for MC 1.20.1
    NeoForge v47.1.106 Initialized

Adding assets/indexes/5.json (from the vanilla profile's own assetIndex.url) removed
the index error but not the next one, and the next one is data rather than code: this
machine's assets/objects holds the 1.21.4 set and not all of 1.20.1's, so the vanilla
pack fails to open, and the game stops with

    java.lang.IllegalStateException: Default font failed to load

That is worth separating clearly in the record: on this line the mod's own path is
working - OptiFine's service, our plan of 550 members across 87 classes, and NeoForge's
initialisation all complete - while the visual confirmation (textures, the rig's
screenshot) waits on downloading that version's asset objects. The download of the
missing objects is running as a background job; systems/... once it finishes, the run
can be repeated and the reload checked the way 1.21.4's was.
## 2026-09-15: for 1.20.4 the installer must actually finish, because it generates what FML needs

NeoForge 20.4.251 installs as a profile (49 libraries, 34 present, 15 downloaded by
hand), ModLauncher 10.0.9 starts, and then FML stops on artifacts that are not there:

    java.io.IOException: Invalid paths argument, contained no existing paths:
      libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-srg.jar
      libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-extra.jar
      libraries/net/neoforged/neoforge/20.4.251/neoforge-20.4.251-client.jar

Those three are not published artifacts. Fetching the last of them - the one that looks
most like a normal maven coordinate - returns 404 from the NeoForged repository, so they
are produced locally: the installer downloads NeoForm's tooling (AutoRenamingTool,
srgutils, javadoctor and friends), runs it against the vanilla 1.20.4 jar and the
mappings, and writes the srg, extra and client jars itself. On this machine that
installer keeps failing on SocketTimeoutException against maven.neoforged.net, which is
the same flakiness that hit Gradle and the 1.20.1 installer, so it never reaches the
generation step.

The way through, then, is the pattern that already worked twice: run the installer,
collect the "Downloading library from <url>" lines it reports as timed out, fetch those
exact URLs with Invoke-WebRequest and retries, repeat until it prints "Successfully
installed client into launcher" - at which point the three derived jars exist and
1.20.4 can be brought up like 1.20.1 was.

## 2026-09-15:1.20.4 宸茶兘鍚姩,鍓╀笅鐨勯樆濉炴槸鍛藉悕绌洪棿鑰屼笉鏄畨瑁呭櫒

涓婁竴鑺傛帹婕旂殑涓ゅ崐閮藉緱鍒颁簡瀹炴祴纭銆?

**瀹夎鍣?*:绗?1 杞氨瀹屾垚浜嗐€備笁涓淳鐢?jar 鐜板湪閮藉湪

    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-srg.jar   (17,348,932)
    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-slim.jar  (13,383,682)
    libraries/net/minecraft/client/1.20.4-20240627.114801/client-1.20.4-20240627.114801-extra.jar (11,061,879)
    libraries/net/neoforged/neoforge/20.4.251/neoforge-20.4.251-client.jar                        ( 5,299,238)

`launch-neoforge.ps1 -Profile neoforge-20.4.251 -NoMods -JavaExe jdk-17` 鐨勫熀绾垮惎鍔ㄧ粨鏋滄槸

    VERDICT: STARTED (40s, marker: Sound engine started)

Java 17銆丮odLauncher 10.0.9銆乣NeoForge mod loading, version 20.4.251, for MC 1.20.4 with MCP
20240627.114801`銆?*1.20.4 杩欎竴琛岀殑瀹炰緥鏈韩鏄ソ鐨?*,鍐嶅嚭闂灏辨槸鎴戜滑鐨?mod 鐨勯棶棰樸€?

**鍛藉悕绌洪棿**:涓や晶纭疄涓嶄竴鑷?鑰屼笖杩欐鏄瓧鑺傜爜绾ц瘉鎹?涓嶆槸闈犵洰褰曞悕鎺ㄦ柇銆?

1. OptiFine 鐨勮浇鑽峰紩鐢ㄧ殑鏄?*鐪熸鐨?SRG 鍚?*銆傚彇 `srg/net/optifine/Config.class`,鐢?`javap -v` 鍒嗙被甯搁噺姹犻噷
   `m_\d{4,6}_` / `f_\d{4,6}_` 鐨勫嚭鐜板舰寮?

   | 鏋勫缓 | `Methodref`/`Fieldref` | `String` |
   |---|---|---|
   | 1.20.1 I6 | 49 | 0 |
   | 1.20.4 I7 | 47 | 0 |
   | 1.21.4 J3 | 0 | 0 |

   鍏抽敭鏄浜屽垪:瀹冧滑鏄?*鐩存帴寮曠敤**,涓嶆槸鏄犲皠琛ㄩ噷鐨勫瓧绗︿覆銆傛墍浠?閭ｅ彧鏄?OptiFine 鑷甫鐨勫鐓ц〃鏁版嵁"杩欎釜
   瑙ｉ噴涓嶆垚绔?鈥斺€?1.20.4 鐨勭被灏辨槸鐓х潃涓€涓?SRG 鍛藉悕鐨?Minecraft 缂栬瘧鐨勩€?

2. NeoForge 20.4 鐨勮繍琛屾椂鏄?*瀹樻柟鍚?*銆俙client-1.20.4-20240627.114801-srg.jar` 閲?
   `net/minecraft/world/item/Item` 鐨勬垚鍛樻槸 `getId`銆乣byId`銆乣builtInRegistryHolder`銆乣onUseTick`,
   `BY_BLOCK`/`MAX_STACK_SIZE`,**娌℃湁涓€涓?`m_`**銆傛枃浠跺悕閲岀殑 `srg` 鍙槸瀹夎鍣ㄩ偅涓€姝ョ殑鍘嗗彶鍚嶅瓧,
   涓嶄唬琛ㄥ唴瀹规槸 SRG銆?

   杩欎篃瑙ｉ噴浜嗕负浠€涔堝凡楠岃瘉鐨勪袱鏉＄嚎鑳借窇:1.20.1 鐨?NeoForge 20.1 鏈韩灏辨槸 SRG,涓よ竟鍚屾瀯;
   1.21.1 璧?OptiFine 涔熸崲鎴愬畼鏂瑰悕,涓よ竟鍚屾瀯銆?*鍙湁 1.20.2 / 1.20.4 杩欎竴甯︿袱杈逛笉鍚屾瀯銆?*

**閭ｄ箞閲嶆槧灏勮鐢ㄧ殑琛ㄤ粠鍝潵 鈥斺€?杩欓噷鏈変釜缂哄彛銆?* NeoForm 1.20.4 鑷甫鐨勪袱涓槧灏勬枃浠堕兘鏌ヨ繃浜?

- `neoform-1.20.4-20240627.114801-mappings.txt` 鐨勮〃澶存槸 `tsrg2 obf srg id`銆備絾 srg 閭ｄ竴鍒?*涓?obf 瀹屽叏鐩稿悓**
  (`cuz cuz 1657`),鍏ㄦ枃**涓€涓?`m_` 鍚嶉兘娌℃湁**,绗笁鍒楁槸鏁板瓧 ID銆備篃灏辨槸璇?NeoForm 瀵?1.20.4 **涓嶅彂甯?SRG 鍚?*銆?
- `neoform-1.20.4-20240627.114801-mappings-merged.txt` 鐨勮〃澶存槸 `tsrg2 left right`,鍐呭鏄?obf 鈫?瀹樻柟鍚?
  (`a com/mojang/math/Axis`)銆?

鎵€浠?璇ョ増鏈殑 SRG 鈫?official 鏄犲皠琛?**涓嶅湪 NeoForm 閲?*銆備絾瀹冪‘瀹炲瓨鍦?鈥斺€?杩欎竴鑺傛渶鍚庢煡鍒颁簡鏉ユ簮,
瑙佷笅闈?鏄犲皠琛ㄧ殑鏉ユ簮"銆?

**瑕侀噸鏄犲皠鐨勫彧鏈夋垚鍛樺悕,涓嶆槸绫诲悕銆?* 杩欎竴鐐瑰繀椤诲厛閲忔竻妤?鍥犱负瀹冨喅瀹氬疄鐜扮殑澶у皬銆侽ptiFine 1.20.4 閲?
`net/minecraft/client/Minecraft` 鐨勫紩鐢ㄩ暱杩欐牱:

    #220 = Methodref  // net/minecraft/client/Minecraft.m_91087_:()Lnet/minecraft/client/Minecraft;
    #230 = Methodref  // net/minecraft/client/Minecraft.m_91268_:()Lcom/mojang/blaze3d/platform/Window;
    #612 = Methodref  // net/minecraft/client/Options.m_232119_:()Lnet/minecraft/client/OptionInstance;

**绫诲悕鏄畼鏂瑰悕,鎴愬憳鍚嶆槸 SRG 鍚?*,鑰屼笖甯搁噺姹犻噷 `net/minecraft/src/C_` 杩欑 SRG 绫诲悕鍑虹幇 **0 娆?*
(1.17 璧风被鍚嶅氨鍙湁瀹樻柟鍚嶄簡)銆傛墍浠ラ噸鏄犲皠琛ㄥ彧闇€瑕佹寜"瀹夸富绫?+ 鎴愬憳鍚?鏀规垚鍛樺悕,鎻忚堪绗﹀師鏍蜂繚鐣欍€?

**鏄犲皠琛ㄧ殑鏉ユ簮(宸茬‘璁?**:MCPConfig 鍙戝竷杩?1.20.4 鐨勬槧灏勩€?

    https://maven.minecraftforge.net/de/oceanlabs/mcp/mcp_config/1.20.4/mcp_config-1.20.4.zip   HTTP 200, 1,872,980 B

閲岄潰 `config/joined.tsrg`(6,011,226 B) 鐨勮〃澶翠篃鏄?`tsrg2 obf srg id`,浣?*杩欎竴浠界殑 srg 鍒楁槸鐪熺殑**:

    a net/minecraft/src/C_252363_ 252363
    	a f_252495_ 252495
    	b f_252529_ 252529

浜庢槸鎶婁袱浠藉悓浠?obf 涓哄乏鍒楃殑鏄犲皠涓茶捣鏉ュ氨鑳藉緱鍒?SRG鈫攐fficial:

| 鏉ユ簮 | 宸?| 鍙?|
|---|---|---|
| MCPConfig `config/joined.tsrg` | obf | SRG(`net/minecraft/src/C_*`銆乣m_*`/`f_*`) |
| NeoForm `...-mappings-merged.txt` | obf | 瀹樻柟鍚?`net/minecraft/world/item/Item`銆乣getId`) |

鍗?鍚屼竴涓?obf 绫?+ 鍚屼竴涓?obf 鎴愬憳,鍦ㄥ乏琛ㄩ噷璇?SRG 鍚嶃€佸湪鍙宠〃閲岃瀹樻柟鍚?閰嶅鍗冲緱
`(瀹樻柟瀹夸富绫? SRG 鎴愬憳鍚? 鈫?瀹樻柟鎴愬憳鍚峘銆備袱浠芥枃浠堕兘宸插彇鍒版湰鍦?
(`test-downloads/mcp1204-joined.tsrg`銆乣libraries/net/neoforged/neoform/1.20.4-20240627.114801/...-mappings-merged.txt`)銆?
娉ㄦ剰涓や唤鐨勬垚鍛樿閮戒笉甯︽弿杩扮,鎵€浠ヨ〃瑕佷互"瀹夸富绫?+ 鎴愬憳鍚?涓洪敭,鎻忚堪绗︿繚鎸佷笉鍔ㄣ€?

**涓€涓鍚﹁瘉鐨勬嵎寰?*:鏈潵鎯崇敤 `patch/srg/*.class.md5` 鍒ゅ畾"OptiFine 鏄拡瀵瑰摢涓?jar 鎵撶殑琛ヤ竵",
缁撴灉 427 涓?md5 瀵规湰鍦版墍鏈夊€欓€?jar 鍏ㄩ儴 0 鍛戒腑 鈥斺€?NeoForm 瀹樻柟 jar銆乿anilla 娣锋穯 jar銆乻lim銆乪xtra銆?
`neoforge-*-client.jar`銆乣neoforge-*-universal.jar` 閫愪釜姣斿閮芥槸 0;`patch/notch` 瀵?vanilla 娣锋穯 jar 涔熸槸
0/426銆傜粨璁烘槸**杩欎簺 md5 涓嶆槸杈撳叆鏍￠獙鍜?*(寰堝彲鑳芥牎楠岀殑鏄?xdelta 涔嬪悗鐨勪骇鐗?,杩欐潯璺笉鑳界敤鏉ュ垽瀹氬懡鍚嶇┖闂淬€?

**涓嬩竴姝?*:鍐欎竴涓绾跨敓鎴愬櫒鎶婁笂闈袱寮犺〃鍚堟垚 1.20.2 / 1.20.4 鐨?SRG鈫抩fficial 鎴愬憳琛?鍐嶅湪 loader 鐨?
transformer 閲岀敤 ASM `Remapper` 鎶?OptiFine 杞借嵎鐨勬垚鍛樺紩鐢ㄦ敼鍐欐帀(绫诲悕涓嶅姩)銆?.20.2 闇€瑕佸悓鏍锋煡涓€娆?
`mcp_config-1.20.2.zip` 鏄惁瀛樺湪銆?

**杩欎竴姝ュ凡瀹屾垚(2026-09-15,璇﹁ `docs/DEVELOPMENT.md`)**:`SrgMemberMap` 寤鸿〃骞跺叏閲忛獙璇?
OptiFine 1.20.4 鐨?**3844 涓?SRG 寮曠敤鏀瑰啓鍚?100% 鍛戒腑** NeoForge 20.4 杩愯鏃?鎵€闇€鏀瑰啓瀵瑰彧鏈?
**1330 鏉?/ 86 KB**;`mcp_config-1.20.2.zip` 涔熷凡纭瀛樺湪(HTTP 200)銆?.20.2 / 1.20.4 杩欎竴甯﹀洜姝?
浠?闇€瑕佺爺绌?鍙樻垚"鎸夎〃鏀瑰啓"鐨勬満姊板伐浣?鍓╀笅鐨勬槸鎺ヨ繘 `OptifinePipeline` 涓?loader transformer銆?

**鏀瑰悕鏈韩涔熷凡鍋氬畬骞堕獙璇?2026-09-15)**:`SrgRemap` 浣滀负**鏋勫缓姝ラ**缁熶竴鏀瑰悕(涓嶆槸 loader transformer 鈥斺€?
ModLauncher 瑕佹眰棰勫厛澹版槑 targets,鑰岃鏀圭殑绫绘湁鍑犲崈涓?銆侽ptiFine 1.20.4 杞借嵎瀹炴祴:

| | 寮曠敤 | 澹版槑 |
|---|---|---|
| 鏀瑰悕鍓?| 3844 | 108 |
| 鏀瑰悕鍚?| **0** | **0** |

鍏辨敼鍐?3457 涓柟娉曞悕 + 1400 涓瓧娈靛悕,0 涓棤娉曡В鏋?0 涓?SRG 褰㈢姸鐨勫瓧绗︿覆甯搁噺(SRG 鍚嶅彧鍑虹幇鍦ㄥ紩鐢ㄥ拰
澹版槑閲?涓嶅弬涓庡弽灏勫瓧绗︿覆,鎵€浠ヤ笉闇€瑕佸姩瀛楃涓?銆傚０鏄庨偅 108 涓槸鍏抽敭:鍙敼寮曠敤浼氳 OptiFine 鏇挎崲绫婚噷
瑕嗙洊鐖剁被鐨勬柟娉曟倓鎮勫彉鎴愬彟涓€涓柟娉曘€?*鍓╀笅鐨勬槸鎶?`SrgRemap` 鎺ヨ繘 rig 鐨勬瀯寤烘祦绋嬪苟瀹炴満鍚姩 1.20.4銆?*

**1.20.4 鎺ヨ繘 rig 鐨勪袱鏉＄‖缁撹(2026-09-15 褰撳ぉ绋嶆櫄,璇﹁ `docs/DEVELOPMENT.md`)**

1. **鎵撹ˉ涓佺殑杈撳叆鏄師鐗堟贩娣?jar**(`versions/1.20.4/1.20.4.jar`),涓嶆槸 SRG/瀹樻柟鍚?jar銆俙Patcher.applyPatch`
   鐢?`getPatchBase` 鎶婅ˉ涓佹潯鐩悕鎹㈢畻鎴愭贩娣?base 鍚嶅幓鎵?鎷垮畼鏂瑰悕 jar 浼氭姤
   `Base resource not found: eon.class`銆傛崲杩囨潵涔嬪悗 1271 ms 閫氳繃,琛ヤ竵鍑?427 涓父鎴忕被(369 涓?
   `net/minecraft`)銆?.21.4 閭ｄ竴琛屼竴鐩存槸杩欎箞璺戦€氱殑 鈥斺€?`1.21.4-client.jar` 鏈韩灏辨槸鍘熺増娣锋穯 jar銆?
2. **琛ヤ竵浜х墿鏄?SRG 鍚?鎵€浠ユ敼鍚嶈璺戝湪琛ヤ竵涔嬪悗銆乣MemberRestorePlan` 涔嬪墠**:琛ヤ竵绫昏础鐚簡 21089 涓柟娉曞悕
   + 17455 涓瓧娈靛悕(鍗犲叏閮ㄦ敼鍚嶉噺鐨?85%)銆傚墿 107 涓湭瑙ｆ瀽(0.28%),鍘熷洜宸插畾浣嶄负**绫诲悕涓嶄竴鑷?*
   (Mojang 鐨?`Gui$1DisplayEntry` 瀵?OptiFine 鐨?`Gui$DisplayEntry`),鍙奖鍝?369 涓ˉ涓佺被閲岀殑 **5 涓?*;
   涓嬩竴姝ユ槸鏋?琛ヤ竵鏉＄洰鍚?鈫?娣锋穯 base 鍚?杩欏骇妗ユ妸绫诲悕瀵归綈銆?

**ModLauncher 涓や唬鍏煎灞?2026-09-15,宸插疄鏈洪獙璇?**

1. **瀹炴祴宸紓**(javap,涓嶆槸鍥炲繂):ML 10.0.9 鏄?`Set<ITransformer.Target> targets()`銆?*娌℃湁**
   `getTargetType()`銆乣List<ITransformer> transformers()`;ML 11.0.2 / 11.0.4 鏄?
   `Set<ITransformer.Target<T>> targets()`銆乣getTargetType()` 鎶借薄銆乣List<? extends ITransformer<?>>`銆?
   1.20.x 杩欎竴鏉″垎鏀鍚屾椂鏈嶅姟涓や唬(1.20.1鈥?.20.4 鏄?10,1.20.6 鏄?11),鑰岃繖涓嶆槸"澶氫竴涓柟娉?鐨勯棶棰?
   鍚屼竴涓被涓嶅彲鑳芥棦鏈?`getTargetType()` 鍙堟病鏈夈€?
2. **鍋氭硶**:淇閫昏緫鏈韩(10 涓?transformer)鎶芥垚涓?loader 鏃犲叧鐨?`NodeTransformer`
   (`targetClasses()` 杩斿洖鐐瑰悕 + `transform(ClassNode)`),鎺ュ彛閫傞厤姣忎唬涓€浠姐€?*鍚岀被鍚?*:
   `src/ml10/java/.../ModLauncherAdapter.java` 涓?`src/ml11/java/.../ModLauncherAdapter.java`,
   鏋勫缓鍙紪璇戣琛岄偅涓€浠?Gradle `-Pmodlauncher` + `sourceSets.main.java.srcDir`;rig 鏄?
   `-AdapterSource` + `-ModLauncherVersion`)銆傛湇鍔″眰鐢?`public List transformers()`(鍘熷 List)
   涓€涓枃浠跺悓鏃舵弧瓒充袱浠?鈥斺€?宸插垎鍒 10.0.9/jdk17銆?1.0.2/jdk21銆?1.0.4/jdk21 缂栬瘧閫氳繃銆?
3. **鍔ㄦ€佷唬鐞嗘潯璺蛋涓嶉€?瀹炴祴)**:鍏堢敤 `Proxy` 瀹炵幇 `ITransformer` 鎯崇渷鎺変竴浠芥簮鐮?鍚姩鍗虫:
   `RuntimeException: How did a non-transformer get here????`銆傚弽缂栬瘧瀹氫綅鍒?
   `TransformationServiceDecorator.lambda$gatherTransformers$0`:瀹冮亶鍘?
   `t.getClass().getGenericInterfaces()`,瑕佹眰鍏朵腑鏈?raw type 涓?`ITransformer` 鐨?`ParameterizedType`,
   鍐嶅彇鍏剁涓€涓被鍨嬪弬鏁板綋鍒嗙粍閿?浠ｇ悊鐨?`getGenericInterfaces()` 鍙湁瑁告帴鍙?浜庢槸鎶涢敊銆?
   鎵€浠ラ€傞厤鍣ㄥ繀椤绘槸**鐪熷疄绫?*,涓嶈兘鐢ㄤ唬鐞嗐€?
4. 椤哄甫淇:rig 缂栬瘧 loader 鏃跺師鏉?鍙?modlauncher 鐩綍閲岀涓€涓?jar",1.20.6 鍥犳鎷?10.0.9 缂栬瘧 11 閫傞厤鍣?
   鑰屾姤 `does not override abstract method getTargetType()` 鈥斺€?鐜板湪鐗堟湰蹇呴』鏄惧紡缁欏嚭銆?

**涓や釜"杩愯鏃?杈撳叆涓嶆槸涓€鍥炰簨(鍏抽敭瀹炴祴)**

- `-RuntimeJar`(鎴愬憳鍥炲～鐨?donor 鏉ユ簮)蹇呴』鏄?*娓告垙鐪熸鍔犺浇鐨勯偅涓?jar**,
  鍗?NeoForge 鐨?`neoforge-<ver>-client.jar`;`-RemapRuntime`(鍚嶅瓧瑙ｆ瀽)鎵嶆槸**瀹樻柟鍚嶆父鎴?jar** + NeoForge jar銆?
- 璇佹嵁:1.20.4 鐨?`net.minecraft.Util$9`銆侼eoForge client jar 閲屽畠鏄?`BiFunction` 缂撳瓨绫?涓?OptiFine
  杞借嵎鍚屼竴濂楀尶鍚嶇被缂栧彿),瀹樻柟鍚?`client-1.20.4-鈥?srg.jar` 閲屽嵈鏄?`extends Thread`(NeoForm 閲嶇紪璇戝悗鐨勭紪鍙?銆?
  鎶婂悗鑰呭綋 donor,灏变細鎶?Thread 鐨勬瀯閫犲櫒鎷疯繘涓€涓笉鏄?Thread 鐨勭被:
  `VerifyError: Bad <init> method call 鈥?Type 'java/lang/Thread' is not assignable to 'net/minecraft/Util$9'`銆?
- 鍛藉悕闄烽槺:NeoForm 鐨?`*-srg.jar` 鍚嶅瓧鍙?srg,**鍐呭鍗存槸瀹樻柟鍚?*(瀛楅潰涓婇獙璇?`Component` 閲屾槸 `literal`);
  1.20.1 閭ｄ釜鎵嶇湡鏄?SRG(`m_237115_`)銆傛敼鐢ㄥ畼鏂瑰悕 jar 鍋?`-RemapRuntime` 涔嬪悗,鏈В鏋愬紩鐢ㄤ粠 14201 闄嶅埌
  **107**,涓庢湰鏂囨。鏃╁厛璁扮殑 107 涓€鑷?鈥斺€?涔熷氨鏄涔嬪墠閭?14201 鏄?*杈撳叆鎷块敊**,涓嶆槸鏀瑰悕閫昏緫鐨勯棶棰樸€?
- 璇婃柇鎶€宸?OptiFine 琛ヤ竵杩囩殑 `CrashReport` 鐢熸垚鎶ュ憡鏃朵細鍏堝垵濮嬪寲 `Shaders`,鑰屽畠闇€瑕?Minecraft 瀹炰緥 鈫?
  鏃╂湡寮傚父琚暣涓悶鎺?鍙湅鍒?`NullPointerException 鈥?gameDirectory`)銆備复鏃舵妸 `net/minecraft/CrashReport`
  鏀捐繘 `-SkipSwapped`,璁╁師鐗堟墦鍗扮湡姝ｇ殑寮傚父,鎵嶇湅鍒颁笂闈㈤偅涓?VerifyError銆?.20.x 鐨勬瀯寤虹嚎淇濈暀浜嗚繖涓亸绂汇€?

**1.20.6:涓€琛屽畬鍏ㄦ病鏈夎ˉ涓佹爲鐨?OptiFine**

- 瀹炴祴 `OptiFine-1.20.6_HD_U_J1_pre18.jar`:**娌℃湁浠讳綍 `patch/` 鏉＄洰**,`srg/` 涓?1083 涓?`.class` 灏辨槸鎴愬搧绫?
  (369 涓?`net/minecraft` 鍏ㄩ儴寮曠敤 `net/optifine`銆? 涓?SRG 鍚?657 涓?`net/optifine`;57 涓?`com/mojang`)銆?
  `files.txt` 鍒?183 涓洰鏍?鍏朵腑 2 涓病鏈夊搴旇浇鑽风被;OptiFine 1.20.6 **涓嶈ˉ Minecraft 鏈韩**銆?
- rig 鏂板 `-PatchedFromRepackedJar`:璺宠繃琛ヤ竵姝ラ,payload 灏辨槸 repack 鍚庣殑 jar;stub 缁撴灉鎸?`srg/` 鍘熸牱鍐欏洖(`3b3/5`)銆?
- 涓や釜鍧?鈶犳妸 `srg/` 鍓嶇紑閲嶅啓鎴愮湡鍚嶄細琚伐鍏峰弽鍣?鈥斺€?`MemberRestorePlan` 璇荤殑灏辨槸 `PATCHED_ROOT = "srg/"`,
  閲嶅啓鍚庤鍒掑彉鎴?0 绫?/ 0 鎴愬憳,宸插洖閫€;鈶tub pass 涓€鏃︽妸 runtime jar 绾冲叆鎵弿,杈撳嚭閲岀殑绫诲悕浼氬彉鎴愮湡鍚?
  鎵€浠?*璁″垝鏀逛粠 payload jar 鏈韩璇?*(rig 閲岀殑 `$planJar`),寰楀埌 230 绫?/ 73 鎴愬憳 / 30 donor銆?
- 杩欎竴琛屽繀椤?`ShipPayload`:娓告垙绫荤暀鍦?`srg/` 浼氳鏈?mod 妯″潡瀵煎嚭 `net.minecraft.*`,鐩存帴
  `ResolutionException: Modules minecraft and srg export package 鈥;鎼埌 `optifineoforge/patched/` 鍚庣敱鏈?mod 鐨?
  transformer 瑁呴厤銆傝琛岃浇鑽峰凡鏄畼鏂瑰悕,鎵€浠ユ槸"鍙惉涓嶆敼鍚?(`-ShipPayload` 涓嶅啀寮哄埗瑕佹眰 `-SrgRemap`)銆?
- 瀹夎:NeoForge 20.6.141 瀹夎鍣ㄥ厛鎶?`These libraries failed to download`(4 涓潗鏍?鍚?modlauncher 11.0.2),
  鎵嬪姩鍙栧洖鍚庨噸璺戝嵆寰楀埌 `neoforge-20.6.141-client.jar`(`cli-utils-2.1.4.jar` 鍙嶅鍙湁 9379 瀛楄妭,浣嗕笉褰卞搷瀹夎瀹屾垚)銆?

**褰撳墠鐘舵€?*

- **1.20.4(ML 10 + ml10 閫傞厤鍣?:`VERDICT: STARTED (40s, marker: Sound engine started)`** 鈥斺€?鍏煎灞傛棤鍥炲綊銆?
- 1.20.6(ML 11.0.2 + ml11 閫傞厤鍣?+ 鏃犺ˉ涓佹爲):妯″潡瑙ｆ瀽宸茶繃銆佹浛鎹㈠凡鍙戠敓(`ResourceLocation`/`Util`/`Mth`/
  `CrashReport`/`Direction`/`BlockState` 鈥?,褰撳墠鍗″湪
  `NoSuchMethodError: 'int net.minecraft.resources.ResourceLocation.compareNamespaced(鈥?'`銆?
  璇ユ垚鍛樺彧瀛樺湪浜?NeoForge client jar(瀹樻柟鍚?jar 涓庤浇鑽烽兘娌℃湁),鑰?73 鏉″洖濉噷娌℃湁瀹?鈥斺€?璁″垝閲?30 涓?owner
  閲屼篃娌℃湁 `ResourceLocation`,**涓嬩竴姝ユ槸鏌ヤ负浠€涔堣繖涓€鏉℃病琚敓鎴?*(鑰屼笉鏄€ョ潃鍔犺ˉ涓?銆?

**1.20.6 宸插惎鍔ㄦ垚鍔?2026-09-15 褰撴棩绋嶅悗)**

`VERDICT: STARTED (40s, marker: Sound engine started)`,鍚屼竴娆¤繍琛岀殑瀹炴祴鍙ｅ緞涓?1.20.4 涓€鑷?
`Setting user`(=杩涙爣棰樼晫闈?鉁撱€乣[Shaders] OpenGL Version: 3.2.0 NVIDIA 591.86`(=鍏夊奖鍦ㄨ窇)鉁撱€?
`Connected textures` 3 琛?鉁撱€乣Pre-stitch` 14 琛?OptiFine 鑷繁鎷煎浘闆?鉁撱€佹浛鎹㈢被 268 涓?鉁撱€?
**stderr 0 瀛楄妭**銆?*娌℃湁鏂?crash report**銆乣screen.png` 1.14 MB 鉁撱€傚敮涓€"鍍忛敊璇?鐨勮閮芥槸鑹€х殑:
`Failed to find ImmediateWindowProvider none`(FML 鎺㈡祴),浠ュ強 OptiFine Reflector 瀵?JDK 鍐呴儴绫?
(`sun.misc.SharedSecrets` 绛?鐨?WARN銆?

涓婁竴娈甸噷閭ｄ釜 `compareNamespaced` 涔嬭皽鐨勭瓟妗堟槸**涓や釜鏋勫缓鑴氭湰缂洪櫡**,閮戒笉鏄敼鍚嶉€昏緫鐨勯棶棰?

1. **绌哄弬鏁拌 PowerShell 鍚冩帀,鍚庣画鍙傛暟鏁翠綋宸︾Щ涓€浣?*:`build-line.ps1` 瀵规病鏈夎鐩?`SkipSwapped` 鐨勮
   浼犱簡 `$null`,splat 鍒板師鐢熷懡浠ゆ椂閭ｄ釜绌哄厓绱犳秷澶?浜庢槸 `--stub <in> <out> <stubs> <skip> <payload> 鈥
   閲岀殑 `<payload>` 鍙樻垚浜?*绗竴涓?runtime jar(娓告垙 jar)**銆傚悗鏋?stub pass 鎶?`scanned 8206 classes`
   (娓告垙 jar 鐨勭被鏁?鑰屼笉鏄浇鑽风殑 1163,鎶婃父鎴?jar 鏁翠釜鍐欐垚杈撳嚭,`3b3/5` 灏卞湴鏇挎崲鑷劧涓€涓兘瀵逛笉涓娿€?
   淇硶:rig 渚х┖琛ㄤ竴寰嬪洖钀藉埌榛樿 skip 琛?build-line 渚у彧鏈夎琛屾樉寮忕粰鍑烘椂鎵嶄紶鍙傘€?
2. **`ForgeApiShims` 浼氬啓鍑洪噸澶嶇殑 `<init>()V`**:澶栧３鑷繁閭ｄ釜鏃犲弬鏋勯€犲櫒鍏堝啓涓€娆?杞借嵎鍙堝紩鐢ㄤ簡
   `<init>()V`,浜庢槸鍚屼竴涓被閲屽嚭鐜颁袱涓?鍔犺浇鐩存帴
   `ClassFormatError: Duplicate method name "<init>" with signature "()V" in class file
   net/minecraftforge/client/model/ForgeFaceData`(姝诲湪 OptiFine `Reflector.<clinit>` 閲?銆?
   淇硶:姣忎釜鎴愬憳鍙啓涓€娆?`Set` 璁板綍,骞舵妸澶栧３鑷繁鐨勬瀯閫犲櫒鍏堢櫥璁拌繘鍘?;鑷被鍨嬪父閲忛渶瑕佹棤鍙傛瀯閫犲櫒鏃?
   鍙湪鐪熺殑娌″啓杩囨椂鎵嶈ˉ銆?

1.20.6 杩欎竴琛屾渶缁堝彲鐢ㄧ殑閰嶆柟(payload 鏃犺ˉ涓佹爲 + 瀹樻柟鍚?:
`-PatchedFromRepackedJar`(璺宠繃琛ヤ竵姝ラ,payload = repack 鍚庣殑 jar)+ `-StubMissing`(stub 缁撴灉鎸?`srg/`
鍘熸牱鍐欏洖,`3b3/5`)+ `-ShipPayload`(娓告垙绫绘惉鍑烘父鎴忓寘,鐢辨湰 mod 鐨?transformer 瑁呴厤)+ 璁″垝浠?payload jar
鏈韩璇?`$planJar`,`230 绫?/ 73 鎴愬憳 / 30 donor`)+ `-RuntimeJar` = NeoForge client jar(donor 鏉ユ簮)銆?
`-RemapRuntime` = 瀹樻柟鍚嶆父鎴?jar + NeoForge jar(鍚嶅瓧瑙ｆ瀽)銆傞€傞厤鍣ㄧ敤 `src/ml11`,缂栬瘧鐢?ModLauncher 11.0.2銆?

**鎵撳寘璺緞鐨勪竴涓己鍙?2026-09-15,瀹炴祴)**

`.\gradlew build` 鍦ㄦ湰鍒嗘敮榛樿鐩爣 1.20.1 涓婄洿鎺ュけ璐?鍘熷洜涓嶆槸鏈?mod 鐨勪唬鐮?

```
Execution failed for task ':createMinecraftArtifacts'
> Could not find net.neoforged:neoforge:1.20.1-47.1.106
  Searched in: https://maven.neoforged.net/releases/net/neoforged/neoforge/1.20.1-47.1.106/...
```

1.20.1 閭ｄ竴浠ｇ殑鍧愭爣鏄棫鐨?`net.neoforged:forge`,ModDevGradle 鍘昏 `net.neoforged:neoforge` 鑷劧鎵句笉鍒般€?
鍥犳鏈垎鏀?*榛樿鏋勫缓鐩爣鏀规垚宸插疄鏈洪獙璇佽繃鐨?1.20.4**(`minecraft_version=1.20.4` / `neoforge_version=20.4.251`),
1.20.1 浠嶅彲鐢?rig 鏋勫缓涓庡惎鍔?鍙槸"鎵撳寘鎴?jar"杩欎竴鐜绛夎繖鏉″潗鏍囬棶棰樿В鍐炽€俙-Pmc` 鎹㈢洰鏍囨椂鐨勮鍒欎笉鍙?
闈為粯璁ょ洰鏍囧繀椤诲悓鏃剁粰 `-Pneoforge`,骞朵笖鐜板湪杩樿缁?`-Pmodlauncher`(1.20.1鈥?.20.4 = 10,1.20.6 = 11)銆?

鏀规垚 1.20.4 涔嬪悗鍐嶈窇:`net.neoforged:neoforge:20.4.251` **瑙ｆ瀽鎴愬姛**銆丯eoForm 娴佺▼涔熺湡鐨勮窇璧锋潵浜?
(鑺变簡 10 鍒?20 绉?,鏈€鍚庢鍦ㄦ祦姘寸嚎鐨?**recompile** 鑺傜偣,鍘熷洜鏄?`java.net.ConnectException`
(涓嬭浇涓€旀柇绾?,涓嶆槸閰嶇疆闂銆備篃灏辨槸璇存墦鍖呰矾寰勫墿涓嬬殑闅滅鏄?*杩欐潯缃戠粶**,閲嶈窇鍗冲彲 鈥斺€?涓?20.6.141
瀹夎鍣ㄩ偅娆″悓婧愩€備骇鐗╀細钀藉湪 `build/libs/`銆?

**1.20.1 鐜扮姸:鑳借繘鏍囬鐣岄潰,浣?OptiFine 杩樻病鐪熸鐢熸晥(2026-09-15 褰撴櫄瀹炴祴)**

`VERDICT: STARTED (40s, marker: Sound engine started)`銆乣Setting user`銆乣screen.png` 0.94 MB銆?
鏃犳柊 crash report 鈥斺€?涓庡吋瀹瑰眰鏃犲叧銆備絾鍚屼竴娆¤繍琛岄噷:

- **`Replaced net` = 0**:鏈?mod 鐨勬浛鎹?transformer 涓€涓被閮芥病鎹?鍥犱负杩欎唤 jar 璧扮殑鏄?淇濈暀琛ヤ竵鏁版嵁"鐨勮矾绾?
  (`-ShipPayload $false`),娌℃湁 `patched-index.txt`銆?
- **`Shaders` / `Connected textures` / `Pre-stitch` 鍏ㄤ负 0**:OptiFine 鐨勫姛鑳芥病鏈夌敓鏁堛€?
- **stderr 1.29 MB / 11862 琛?*,寮€澶村嵆:`java.io.IOException: Base resource not found: eud.class`
  鏉ヨ嚜 `optifine.Patcher.applyPatch`,鑰?`OptiFineTransformer` 鑷姤 `Targets: 412`銆?

涔熷氨鏄 1.20.1 涓?1.20.2/1.20.4 鏄?*鍚屼竴涓梾**:OptiFine 鐨勮繍琛屾椂琛ヤ竵鍣ㄨ鎵?*娣锋穯 base 鍚?*,鑰岃繍琛屾椂浜ょ粰瀹冪殑鏄?
SRG 鍚?鎵€浠?412 涓洰鏍囧叏閮?`Base resource not found`)銆傜粨璁烘槸杩欎竴琛?*涔熷繀椤昏蛋绂荤嚎杞借嵎**璺嚎銆?
涓や釜琛ュ厖瀹炴祴:

1. 杩欎竴琛?淇濈暀琛ヤ竵鏁版嵁"鐨勪骇鐗╂槸 5.87 MB(`combined-nostubs.jar` 5,870,135 / 浠婂ぉ閲嶅缓 5,876,768),
   鑰岀绾胯浇鑽蜂骇鐗╂槸 4.24 MB 鈥斺€?涓庡綋鍒濊褰曠殑"1.20.1 宸茶窇閫?鐨勯偅浠藉ぇ灏忎竴鑷?閭ｄ唤鍏跺疄涔熷彧鏄?*鑳藉惎鍔?*,
   OptiFine 鍚屾牱娌＄敓鏁?褰撴椂璁扮殑鏄?clean reload銆佸浘闆?,涓嶆槸鍏夊奖/CTM)銆?
2. 鏀规垚绂荤嚎杞借嵎(`-ShipPayload $true`)鍚?杩炵画涓ゆ閮藉湪鎹㈡帀 5 涓被宸﹀彸鍚庢鎺?澶辫触鐐规槸
   **SecureJar 鎵撲笉寮€ union 璺緞**:
   `java.nio.file.FileSystemNotFoundException` 鈫?`Jar$JarModuleDataProvider.open(Jar.java:291)`
   鈫?`ModuleClassLoader.getClassBytes` 鈫?`net.minecraft.util.Mth.<clinit>`銆?
   杩欐槸 1.20.1 閭ｄ竴浠?securejarhandler 鐨勮矾寰?涓嶆槸鎴戜滑鐨勬敼鍚?鍥炲～閫昏緫銆?

**涓嬩竴姝?1.20.1)**:鍏堟煡 SecureJar 閭ｄ竴姝モ€斺€斿畠瑕佹墦寮€鐨勫埌搴曟槸鍝釜 jar(鍊煎緱鍔犱竴涓帰閽堟妸 `Jar` 鐨勮矾寰勬墦鍑烘潵),
浠ュ強鎶?`net/minecraft/util/Mth` 鏀捐繘 `-SkipSwapped` 鍚庨敊璇槸鍚︽崲鍒板埆鐨勭被(鑳芥崲灏辫鏄庢槸鎴戜滑鐨勬浛鎹㈤『搴?鏃舵満,
涓嶆崲灏辫鏄庢槸閭ｄ竴浠?securejarhandler 鐨?union 澶勭悊)銆傚畾鍚戝疄楠屾瘮閲嶈窇鐚滄祴渚垮疁:涓€娆?build+launch 绾?1.5 鍒嗛挓銆?

**宸茬粡鍋氬畬鐨勪袱涓畾鍚戝疄楠?2026-09-15 褰撴櫄,缁撹姣旂寽娴嬫槑纭?**

1. **鍚屼竴浠?jar,鍘绘帀 `patched-index.txt` 鍚庤兘姝ｅ父鍚姩**:鎶婄绾胯浇鑽蜂骇鐗╁鍒跺埌鎺㈤拡鐩綍骞跺垹鎺?
   `optifineoforge/patched-index.txt`,鍐嶅惎鍔?鈥斺€?`VERDICT: STARTED (40s)`銆佹埅鍥惧凡瀛樸€乻tderr 鍙湁
   `Failed to load forge logo`,鑰屼笖鏃ュ織鏄庣‘鍐欑潃
   `No /optifineoforge/patched-index.txt in this jar; no classes will be swapped in`銆?
   鎵€浠?*鍑洪敊鐨勬槸"鏈?mod 鐨?transformer 鐪熺殑寮€濮嬫崲绫?杩欎欢浜?*,涓嶆槸 jar 鐨勫唴瀹?杞借嵎甯冨眬銆乨onor銆乸lan 閮芥棤杈?銆?
2. **鎶婄储寮曢檺鍒舵垚鍙湁 `net/minecraft/*`(354 鏉?鍚庝粛鐒朵互鍚屼竴鏉℃爤澶辫触** 鈥斺€?瑙﹀彂鐐瑰湪 `net/minecraft/**` 閲?
   涓嶅湪 `com/mojang/**`(blaze3d 閭ｄ簺绫昏繖娆℃牴鏈病娉ㄥ唽)銆?

澶辫触褰㈡€佸浐瀹?`ModuleClassLoader.getClassBytes` 鈫?`JarModuleReader.open` 鈫?
`Jar$JarModuleDataProvider.open(Jar.java:291)` 鈫?`Paths.get` 鈫?
`FileSystemNotFoundException`,鏈€鍏堝湪 `net.minecraft.util.Mth.<clinit>` 涓婃毚闇?ML 鐗堟湰鏄?10.0.9,
涓?1.20.4 鐩稿悓,浣嗛偅涓€浠ｇ殑 securejarhandler 涓嶅悓 鈥斺€?杩欒В閲婁簡"鍚屾牱鏄?ML 10銆?.20.4 琛屽嵈娌′簨"銆?

**涓嬩竴姝?1.20.1,宸茬缉灏忓埌涓€鏉?**:鎶婄储寮曠户缁簩鍒嗗埌鍗曟潯 鈥斺€?鍏堢敤鍙惈 `net/minecraft/util/Mth` 鐨勭储寮曞惎鍔?
澶辫触灏辫鏄庢槸杩欎竴涓被,涓嶅け璐ュ氨璇存槑鏄?鍙鎹㈢被灏辩偢"(閭ｉ棶棰樺湪 transformer 鐨勬敞鍐屾椂鏈?securejarhandler 鐨?
union 璇诲彇,鑰屼笉鍦ㄦ煇涓被)銆備袱涓剼鏈凡缁忓氨浣?`make-probe-jar.ps1`(鍒犳潯鐩?銆?
`make-index-probe.ps1 -Keep "<閫氶厤绗?"`(瑁佺储寮?,涓€娆?build+launch 绾?1.5 鍒嗛挓銆?

**浜屽垎鍋氬畬浜?绛旀鏄?涓嶆槸鏌愪竴涓被"(2026-09-15 褰撴櫄)**

- **鍙暀 `net/minecraft/util/Mth` 涓€鏉?*(`Patched-class targets: 1`):浠嶇劧浠ュ悓涓€鏉℃爤姝诲湪
  `Mth.<clinit>`銆?
- **鎶?`Mth` 鏀捐繘 `-SkipSwapped`**(璁╄繍琛屾椂鑷繁閭ｄ唤鐣欎笅):閿欒鍙槸**鎹簡涓被** 鈥斺€?鍙樻垚
  `CrashReport.m_127526_(CrashReport.java:170)`,鑰屼笖杩樻槸鍦?`Main.main:149`(涔熷氨鏄穿婧冧笂鎶ラ偅鏉¤矾寰勯噷)銆?

鎵€浠ヨ繖涓嶆槸"鏌愪釜绫讳笉鑳芥崲",鑰屾槸**"鍙鏈夌被琚垜浠崲鎺?灏变細鍦ㄦ煇涓被璇诲彇涓婄偢"**銆傜粨鍚堜笁鏉″凡鐭?
鍚屾牱鐨勪唬鐮佸湪 1.20.4 涓婃病浜?ML 鐗堟湰鐩稿悓,securejarhandler 涓嶅悓)銆佸幓鎺夌储寮曞氨姝ｅ父銆?
澶辫触鐐规瘡娆￠兘鏄?琚崲杩囩殑绫荤涓€娆¤皟鐢?OptiFine 鑷繁鐨勭被鐨勬椂鍊? 鈥斺€?鐩墠鏈€绔欏緱浣忕殑鍋囪鏄?

> 1.20.1 杩欎竴浠ｇ殑 securejarhandler 鍦?*璇诲彇琚?union 杩涙父鎴忓眰鐨?mod jar 閲岀殑绫?*鏃朵細璧?
> `Jar$JarModuleDataProvider.open` 鈫?`Paths.get(union 璺緞)` 鑰屾姏 `FileSystemNotFoundException`銆?
> 鎴戜滑鐨?mod jar 姝ｆ槸琚?union 鐨?妯″潡鍚?`optifine`,鍦?GAME 灞?,鑰?**OptiFine 鑷繁鐨勭被灏变綇鍦ㄩ噷闈?*
> (`srg/net/optifine/**`)銆備簬鏄竴鏃︽煇涓鎹㈣繃鐨勬父鎴忕被鍘昏皟 OptiFine 鐨勮緟鍔╃被,璇诲彇灏辩偢 鈥斺€?
> 杩欒В閲婁簡"涓轰粈涔堝幓鎺夌储寮曞氨娌′簨"(涓嶈皟灏变笉浼氳)銆?涓轰粈涔堟崲 Mth 浼氱偢鍦?Mth"(Mth 鐨勮ˉ涓佺涓€浠朵簨灏辨槸璋?
> OptiFine)銆佷互鍙?涓轰粈涔堜笉鎹?Mth 灏辩偢鍦?CrashReport"(鎹笅涓€涓璋冪敤鑰?銆?

**1.20.1 鐨勭浜屼釜鐪熺浉:SecureJar 閭ｆ潯鎶ラ敊鏄鐢熺殑,搴曚笅杩樻湁涓€涓尶鍚嶇被缂栧彿闂**

淇ソ鎺㈤拡鏂规硶鍚?`Util*` 杩欑鍐欐硶鍦?PowerShell 閲屾槸**澶у皬鍐欎笉鏁忔劅**鐨?`net/minecraft/Util*` 浼氭妸
`net/minecraft/util/Mth` 涓€璧穖atch杩涙潵 鈥斺€?涔嬪墠涓夋"浜屽垎"鍥犳閮借姹℃煋浜?,鐢?*鎸夊瓧鑺傛祴鍑烘潵鐨?*鏉′欢閲嶅仛鎺㈤拡:
鍙繚鐣?涓嶅紩鐢?`net/optifine`"鐨勮浇鑽风被(268 涓?,涓㈡帀寮曠敤 OptiFine 鐨?141 涓?鈥斺€?

- **SecureJar 閭ｆ潯 `FileSystemNotFoundException` 娑堝け浜?*,鎹㈡垚涓€鏉″共鍑€銆佺湡瀹炪€佸彲瑙ｉ噴鐨勯敊:

```
java.lang.VerifyError: Bad type on operand stack
  Location: net/minecraft/Util.m_137584_()V @13: invokevirtual
  Reason: Type 'net/minecraft/Util$9' is not assignable to 'java/lang/Thread'
```

涔熷氨鏄:**鍖垮悕绫荤紪鍙峰湪涓よ竟涓嶄竴鏍?*銆傝繍琛屾椂閭ｄ唤 `Util$9 extends java.lang.Thread`,鑰岃浇鑽烽噷 `Util$9` 鏄?
`Util.memoize` 鍚庨潰鐨?BiFunction 缂撳瓨绫?鈥斺€?鎶婅浇鑽烽偅浠借杩涘幓,`Util` 閲?閫犵嚎绋?鐨勪唬鐮佸氨杩囦笉浜嗘牎楠屻€?
杩欏悓鏃跺弽杩囨潵璇存槑:鍏堝墠 SecureJar 鎶ラ敊**鍙湪琚崲绫诲幓璋?OptiFine 鑷繁鐨勭被鏃跺嚭鐜?*(閭?141 涓?,鎵€浠?
"OptiFine 鑷繁鐨勭被浣忓湪琚?union 鐨?mod jar 閲屻€佽繖涓€浠?securejarhandler 璇讳笉浜?杩欎釜鍋囪浠嶇劧绔欏緱浣忋€?

**宸茬粡鍋氱殑淇(浠ｇ爜閲?涓嶆槸鑴氭湰)**:`PatchedClassTransformer` 鍔犱簡涓€鏉″畧鍗?鈥斺€?鍚嶅瓧閲屽惈 `$` 鐨勭被,
濡傛灉杞借嵎閭ｄ唤鐨勭埗绫讳笌杩愯鏃堕偅浠戒笉鍚?灏?*涓嶆崲**(OptiFine 鍙敼鏂规硶浣撱€佷笉鏀圭户鎵?鑰?`Outer$N` 鐨勭紪鍙蜂袱杈逛笉涓€鑷?
鏃?鐖剁被涓嶅悓灏辨槸"杩欎笉鏄悓涓€涓被")銆傚畧鍗湰韬寜棰勬湡鐢熸晥浜?

```
Left net.minecraft.Util$7 alone: the payload's copy of it extends java/lang/Thread
while the runtime's extends java/lang/Object
```

**浣嗗畠鏆撮湶浜嗙湡姝ｇ殑褰㈢姸**:瀹堝崼鍙尅浜?`Util$7`,浜庢槸鍙樻垚浜?杞借嵎鐨?`Util` + 杩愯鏃剁殑 `Util$7`" 鈥斺€?杞借嵎鐨?`Util`
閫犵嚎绋嬫椂鏈熸湜鑷繁鐨?`Util$7`(Thread),鎷垮埌鐨勫嵈鏄繍琛屾椂閭ｄ唤(Object),`VerifyError` 鍙槸**鎹簡涓被鍚?*:

```
Type 'net/minecraft/Util$7' is not assignable to 'java/lang/Thread'
```

**缁撹:鍖垮悕绫诲繀椤绘寜"瀹舵棌"鏁翠綋鍐冲畾,涓嶈兘鎸夊崟涓被鍐冲畾銆?* 澶栧眰绫讳笌瀹冪殑 `$N` 鍐呴儴绫绘槸鍚屼竴浠界紪璇戠殑浜х墿,
涓よ€呮贩鎼繀鐒惰嚜鐩哥煕鐩?鑰岃浇鑽烽偅涓€鏃忓唴閮ㄨ嚜娲姐€佽繍琛屾椂閭ｄ竴鏃忓唴閮ㄨ嚜娲?鎵€浠ヨ涔堟暣鏃忔崲銆佽涔堟暣鏃忎笉鎹?
(缂栧彿閿欎綅鏃跺彧鑳芥暣鏃忎笉鎹?銆傚灞傜被鏈韩涓嶉渶瑕佹崲涔熸病鍏崇郴 鈥斺€?`$N` 鏄鏈夌殑,澶栭儴浠ｇ爜涓嶄細寮曠敤瀹冧滑銆?

**涓嬩竴姝?鏄庣‘鐨勫疄鐜?涓嶆槸鎺㈢储)**:鍦ㄦ瀯寤烘湡(绂荤嚎銆佽兘鍚屾椂鐪嬪埌杞借嵎涓庤繍琛屾椂)鎸夊鏃忓仛鍐冲畾 鈥斺€?
鍑?鏃忛噷浠讳竴鎴愬憳鐨勭埗绫讳笌杩愯鏃跺搴旂被涓嶅悓"灏辨妸鏁存棌(澶栧眰 + 鎵€鏈?`Outer$*`)浠庣储寮曚笌杞借嵎閲屽幓鎺夈€?
杩欐牱鍓╀笅鐨勬棌涓よ竟涓€鑷?鍙互鏁翠綋瀹夎;琚壓鐗茬殑鍙槸灏戞暟鍑犱釜鏃忕殑 OptiFine 琛ヤ竵(渚嬪 `Util`),
鑰?shaders 闇€瑕佺殑 `RenderSystem`/`Mth`/`GlStateManager` 杩欎簺**椤跺眰绫讳笉鍙楀奖鍝?*銆?

**瀹舵棌瑙勫垯宸插疄鐜板苟鐢熸晥(鍚屼竴鏅?**:鏂板伐鍏?`NestedFamilyGuard`(鏋勫缓鏈熻窇,杞借嵎 + 杩愯鏃堕兘鐪嬪緱瑙?,
杈撳嚭瑕佹暣鏃忚烦杩囩殑鍚嶅瓧;rig 鎷垮畠鍚屾椂鍠?*杞借嵎鎼繍**鍜?*transformer 鐩爣绱㈠紩**,涓よ€呭洜姝や笉浼氬悇璇村悇璇濄€?
1.20.1 瀹炴祴瀹冨彧鎵惧嚭 **2 涓棌**骞舵暣鏃忚烦杩?

```
families to skip: 2 (com/mojang/blaze3d/vertex/VertexMultiConsumer, net/minecraft/Util)
Patched-class targets: 417        (= 435 - 18)
```

`VerifyError` 閭ｄ竴绫婚敊璇嚦姝ゆ秷澶?鏂扮殑 stderr 閲屽凡缁忔病鏈夊畠)銆傚墿涓嬬殑浠嶆槸 SecureJar 閭ｆ潯銆?

**SecureJar 閭ｆ潯宸茬粡琚畾浣嶅緱鏇寸粏浜?涓や釜鏂拌瘉鎹?**

1. **瀹屽叏涓嶆崲绫荤殑閭ｆ杩愯閲?`[OptiFine]` 鏃ュ織涓€琛岄兘娌℃湁**(`net.optifine` 鐩稿叧鍙湁涓€鏉?
   `additionalClassesLocator: [optifine., net.optifine.]`)銆備篃灏辨槸璇撮偅鏉¤兘鍚姩鐨勮矾寰勯噷 **OptiFine 鐨勪唬鐮佷粠鏈璋冪敤杩?*銆?
2. 涓€鏃︽湁绫昏鎹?绗竴涓鎹㈢殑绫?`Mth` 灏辫皟鐢?OptiFine),澶辫触鐐规濂芥槸**绗竴娆″姞杞?`net.optifine.*`**銆?

鑰?*鎴戜滑鑷繁**鐨勭被涔熶綇鍦ㄥ悓涓€涓?jar 閲?`kynarain/cn/optifineoforge/loader/**`),瀹冧滑鍔犺浇寰楀ソ濂界殑銆?
鎵€浠ラ棶棰樹笉鍦?璇?union 鐨?mod jar",鑰屽湪 **OptiFine 鑷繁閭ｆ潯鍙栫被璺緞**:瀹冩寜 code source 绠楀嚭 URL 浜ょ粰
ModLauncher(`additionalClassesLocator`),鍦?union 鏂囦欢绯荤粺涓嬮偅涓?URL 鏄?union 褰㈢姸,杩欎竴浠?
securejarhandler 鎵撲笉寮€ 鈥斺€?杩?*姝ｆ槸 `OptifineJarFixer` 褰撳垵涓哄畠鐨?transformation service 淇繃鐨勯偅绫?bug**
(`new ZipFile(path)` 閬囧埌 `...jar#177` 鑰屾姤 `NoSuchFileException`)銆?

**涓嬩竴姝?1.20.1)**:鐪?`OptifineJarFixer` 鐜板湪鍒板簳淇簡鍝嚑涓被鐨勬柟娉?鍐嶆妸鍚屼竴澶勭悊鎵╁埌
`additionalClassesLocator` 鐨勫疄鐜颁笂(1.20.1 鐨?OptiFine jar 閲?瀹冨鍗婁笌 service 鍦ㄥ悓涓€涓被閲?
浣嗙敤鐨?API 涓嶅悓);淇ソ涔嬪悗杩欎竴琛屽簲褰撹兘甯︾潃瀹屾暣杞借嵎鍚姩銆?

**椤烘墜淇帀涓€涓嚜宸遍€犵殑鍥炲綊(鍚屾櫄,璁颁笅鏉ユ槸鍥犱负瀹冩槸"鎺ュ彛濂戠害"鑰屼笉鏄瑪璇?**

缁?`NestedFamilyGuard` 鎺ョ嚎鏃?鎴戞妸 stub pass 鐨?skip 琛ㄤ粠"鍐呴儴鍚?鏀规垚浜?鐐瑰悕"
(`$_ -replace '/', '.'`),鐞嗙敱鏄?`MissingTargets` 鐨勭敤娉曞啓鐨勬槸 "skipped prefixes"銆?
缁撴灉鏄?**1.20.4 浠?STARTED 閫€鍖栨垚 15 绉掗€€鍑?*,鑰屽け璐ヤ俊鎭湅鐫€姣笉鐩稿共:

```
java.lang.NoSuchMethodError: 'void net.minecraft.client.gui.screens.LoadingOverlay.update()'
```

鍘熷洜閾?`MissingTargets` 鐨?skip 璋撹瘝鏄嬁**绫绘枃浠堕噷璇诲嚭鏉ョ殑鍐呴儴鍚?*鍘绘瘮(`skip.test(node.name)`),
鐐瑰悕姘歌繙鍖归厤涓嶄笂 鈫?`LoadingOverlay` 涓嶅啀琚帓闄ゅ嚭"杞借嵎绱㈠紩" 鈫?瀹冭嚜宸卞０鏄庣殑 `update()` 琚垽涓?*鍙弧瓒?*
(鑰屼笉鏄?鍙湁 OptiFine 閭ｄ唤鎵嶆湁")鈫?浜庢槸涓嶅啀鐢熸垚閭ｆ潯寤惰繜 stub 鈫?杩愯鏃朵竴璋冪敤灏?`NoSuchMethodError`銆?
杩欐槸鏂囨。閲屾棭灏卞啓杩囩殑閭ｄ釜闄烽槺("with OptiFine's LoadingOverlay still counted as present 鈥?),鍙槸杩欐鏄?
鎴戣嚜宸辫俯鐨勩€?*濂戠害:缁欒繖涓や釜宸ュ叿鐨?skip 琛ㄤ竴寰嬬敤鍐呴儴鍚?甯︽枩鏉?銆?*

淇ソ鍚?1.20.4 绔嬪埢鎭㈠:`VERDICT: TIMEOUT (201s)`(璺戞弧鏁翠釜 200 绉掔獥鍙?鍗崇ǔ瀹氬瓨娲?銆?
`Runtime stubs to add: 76 members across 27 classes`銆乣Patched-class targets: 448` 鈥斺€?涓庡綋鍒濋獙璇佺殑閭ｇ粍鏁板瓧涓€鑷淬€?
`NestedFamilyGuard` 鏈韩鍦?1.20.4 涓婂彧鎵惧嚭 `com/mojang/blaze3d/vertex/VertexMultiConsumer` 涓€涓棌骞惰烦杩?
涓斿凡鐢?`-NoFamilyGuard` 瀵圭収瀹為獙璇佹槑瀹?*涓嶆槸**閭ｆ閫€鍖栫殑鍘熷洜銆?

**1.20.1 鐨勬渶澶ч殰纰嶅凡瑙ｅ喅:`OptifineJarFixer` 鍘熸潵"鏁存鏇挎崲" `toFile`,鎶婂壇浣滅敤鍒犳帀浜?*

鍙嶆眹缂?1.20.1 鐨?`optifine/OptiFineTransformationService.toFile(URI)` 鎵嶇湅娓?瀹?*鏈潵灏卞鐞?union**鈥斺€?
scheme 涓?`union` 鏃跺彇 `getPath()`銆佺爫鎺?`#<index>`銆乣new File(path)`銆乣ofZipFileUrl = file.toURI().toURL()`
(鎸囦护 57-63),鐒跺悗缁х画鎵撳紑 zip銆傝€屾垜浠偅涓?淇?union 璺緞"鐨?fixer 鏄?*鐩存帴鎶婃暣涓柟娉曚綋鎹㈡帀**鐨?
浜庢槸 `ofZipFileUrl` **姘歌繙涓嶅啀琚祴鍊?*;瀹冭嚜宸辩殑 `getResourceUrl(String)` 鍙堟鏄嬁杩欎釜闈欐€佸瓧娈垫嫾绫?URL 鐨?
(鑰屼笖浼氬厛缁欓潪 `optifine/` 鐨勫悕瀛楀姞 `srg/` 鍓嶇紑)鈥斺€?缁撴灉 ModLauncher 鎷垮埌 union 褰㈢姸鐨?URL,
鎶ュ嚭閭ｆ潯鐪嬬潃姣笉鐩稿共鐨?`FileSystemNotFoundException`銆?

淇硶:鎸?*褰㈢姸**鍒嗘祦(涓ょ褰㈢姸閮界敤 javap 瀹炴祴杩?涓嶆槸闈犳枃浠跺ぇ灏忕寽鐨?鈥斺€?

- **闈?union 鍒嗘敮鐢?`new File(URI)` 鐨勯偅涓€鐗?1.20.1)**:淇濈暀鍘熸柟娉曚綋,鍙湪宸叉湁鐨?`#` 瑁佸壀涔嬪悗**鎻掑叆**涓€娈?
  "鐮嶆帀绗竴涓?`!`" 鐨勬寚浠?`toFile` 閲岃兘鎵惧埌 `INVOKESPECIAL java/io/File.<init>(Ljava/net/URI;)V` 杩欎釜鐗瑰緛)銆?
- **闈?union 鍒嗘敮鐢?`new File(uri.getPath())` 鐨勯偅涓€鐗?1.20.4/1.21.4)**:鏁存鏇挎崲(閭ｆ鏄慨
  `NoSuchFileException: ...jar#177` 鐨勫姙娉?銆?

缁撴灉:1.20.4 鎭㈠ `VERDICT: TIMEOUT (181s)` 鉁?1.20.1 鐨?stderr 浠?11862 琛?1.29 MB 鍙樻垚**鍙墿涓€琛?*
`Failed to load forge logo`(鑹€?,鑰屼笖 **OptiFine 鑷繁鐨?`Config` 鎴愬姛鍔犺浇**(`[OptiFine] Version found: I6`)鈥斺€?
"浠?union 鐨?mod jar 閲岃 `net.optifine.*` 澶辫触"杩欎釜闂鍒版涓烘銆?

**1.20.1 鐜板湪鐨勫け璐ョ偣(宸茶繘鍏ユ甯稿惎鍔ㄦ祦绋?鎬ц川瀹屽叏涓嶅悓)**

```
Description: Initializing game
java.lang.IllegalAccessError: Update to non-static final field
  net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState.f_110131_ attempted from a different class
	at net.minecraftforge.client.ForgeRenderTypes$CustomizableTextureState.<init>(ForgeRenderTypes.java:371)
	at ... RenderType.m_110497_ ... FontSet ... Minecraft.<init>(Minecraft.java:475)
```

涔熷氨鏄:鎴戜滑鎹㈣繘鍘荤殑绫婚噷,鏌愪釜瀛楁甯︾潃**杞借嵎鐨?`final` 浣?*,鑰?NeoForge 鑷繁鐨勪唬鐮佷細鍐欒繖涓瓧娈?
(`ForgeRenderTypes$CustomizableTextureState` 鍐?`f_110131_`)鈫?JVM 鎷掔粷銆傝繖涓庢枃妗ｉ噷閭ｆ潯"鎴愬憳鍙鎬у彇涓よ€呮洿瀹界殑"
鏄悓鏃忛棶棰?鍙槸褰撴椂鍙鐞嗕簡鍙鎬с€佹病澶勭悊 `final`銆?

**涓嬩竴姝?1.20.1,鏄庣‘)**:鍦?`PatchedClassTransformer` 鍚堝苟瀛楁璁块棶浣嶆椂,**涓嶈浠庤浇鑽风户鎵?`ACC_FINAL`**
(瀛楁鐨?final 浣嶄互杩愯鏃堕偅浠戒负鍑?杩愯鏃堕潪 final 灏卞繀椤讳繚鎸侀潪 final)銆傝繖涓€鏉℃敼瀹屽啀璺?1.20.1銆?

**杩欐潯"涓嶈缁ф壙 final"璇曡繃浜?缁撴灉鏄敊鐨?宸插洖閫€(鍚屾櫄,璐熺粨鏋滅収鏍疯涓嬫潵)**

鏀瑰姩鏈韩:鍚堝苟瀛楁璁块棶浣嶆椂,鑻ヨ繍琛屾椂閭ｄ唤涓嶆槸 final 灏辨竻鎺?final銆傜粨鏋?*涓ゆ潯绾夸竴璧烽€€**:

- **1.20.4 浠?`TIMEOUT (181s)` 閫€鎴?`EXITED (10s)`**(鏄庣‘鍥炲綊,澶辫触鍑虹幇鍦ㄥ穿婧冧笂鎶ラ偅鏉¤矾寰勯噷鐨?
  `RenderSystem.<clinit>`);
- **1.20.1 鍐掑嚭鏂伴敊**,鑰屼笖杩欎釜鏂伴敊姝ｆ槸杩欐潯瑙勫垯**鑷繁閫犲嚭鏉ョ殑**:

```
java.lang.RuntimeException: java.lang.ClassFormatError:
  Illegal field modifiers in class com/mojang/blaze3d/vertex/VertexConsumer: 0x9
```

`0x9` = `public final`,鑰?*鎺ュ彛瀛楁蹇呴』鏄?`public static final`** 鈥斺€?娓?final 鐨勫仛娉曟妸涓嶈鍔ㄧ殑浣嶅姩浜?
鐪熸鐨勫樊寮傚叾瀹炴槸 **`static` 浣?*:杞借嵎閭ｄ唤 `VertexConsumer` 鐨勫瓧娈垫病鏈?static(0x9),杩愯鏃堕偅浠芥湁(0x19)銆?
涔熷氨鏄瀛楁鐨勮闂綅瑕?*閫愪綅**澶勭悊,骞朵笖 `static`/`final` 閮藉簲褰撲互**杩愯鏃堕偅浠?*涓哄噯,鑰屼笉鏄缁熷湴"娓?final"銆?

鍥為€€鍚?1.20.4 绔嬪埢鎭㈠:`VERDICT: TIMEOUT (181s)`銆乣Runtime stubs to add: 76 members across 27 classes`銆?
`Patched-class targets: 448`(浠嶆槸閭ｇ粍楠岃瘉杩囩殑鏁板瓧)銆?

**涓嬩竴姝?1.20.1,鎸変綅閲嶅啓杩欐潯瑙勫垯)**:瀛楁鍚堝苟鏃舵妸 `ACC_STATIC` 涓?`ACC_FINAL` 閮藉彇**杩愯鏃堕偅浠?*
(杞借嵎鏂板鐨勫瓧娈垫病鏈夎繍琛屾椂瀵瑰簲,鍒欎繚鐣欒浇鑽疯嚜宸辩殑浣?;鎺ュ彛瀛楁纭繚鏈€缁堟槸 `public static final`銆?
鍙﹀杩欎袱娆″疄楠岄兘璇存槑涓€浠朵簨:**鍔ㄨ闂綅鐨勬敼鍔ㄥ繀椤诲厛璺?1.20.4 鍥炲綊**,瀹冩槸鏈€瀹规槗鎶婂凡楠岃瘉鐨勮寮勫潖鐨勫湴鏂广€?

**"鎸変綅鍙栬繍琛屾椂"涔熻瘯杩囦簡,鍚屾牱鏄潖鐨?宸插洖閫€(璐熺粨鏋?#2)**

鏀瑰姩:瀛楁鐨?`ACC_STATIC`/`ACC_FINAL` 鍙栬繍琛屾椂閭ｄ唤(杩愯鏃舵病鏈夎繖涓瓧娈垫椂鎵嶄繚鐣欒浇鑽风殑浣?,
鎺ュ彛瀛楁寮哄埗 `public static final`銆傜粨鏋?*涓ゆ潯绾块兘閫€**,鑰屼笖閿欏緱**涓€妯′竴鏍?*:

```
IllegalAccessError: Update to non-static final field
  com.mojang.blaze3d.vertex.VertexFormat.elem鈥?     (1.20.4,瀛楁鍚?elem鈥?
  com.mojang.blaze3d.vertex.VertexFormat.f_86鈥?     (1.20.1,瀛楁鍚?f_86鈥?
```

涔熷氨鏄:鍚堝苟鍚庣殑瀛楁琚垽鎴?**final**,鑰屾煇涓皟鐢ㄦ柟鍦ㄥ啓瀹冦€傚洖閫€鍚?1.20.4 绔嬪埢鍥炲埌
`VERDICT: TIMEOUT (181s)` + `76 members / 27 classes` + `448 targets`(閭ｇ粍宸查獙璇佺殑鏁板瓧)銆?

**涓ゆ瀹為獙鍚堣捣鏉ョ粰鍑虹殑缁撹(姣旀敼鍔ㄦ湰韬噸瑕?**

1. **`IllegalAccessError: Update to 鈥?final field` 鍦ㄤ袱鏉＄嚎涓婃槸鍚屼竴涓梾**:杩愯鏃舵湁浠ｇ爜鍐欎竴涓瓧娈?
   鑰屽悎骞跺悗鐨勭被鎶婂畠鏍囨垚 final銆傚尯鍒彧鍦ㄥ瓧娈靛悕鐨勫懡鍚嶇┖闂?1.20.1 鏄?`f_86鈥,1.20.4 鏄?`elem鈥)鈥斺€?
   杩欐湰韬張璇存槑**涓ゆ潯绾跨殑"杩愯鏃堕偅浠?骞朵笉鎬诲湪鍚屼竴鍛藉悕绌洪棿閲?*,鑰岃繖姝ｆ槸杩欐潯瑙勫垯闅惧啓鐨勫師鍥犮€?
2. **鍚堝苟鐨勫尮閰嶉敭鏄?`鍚嶅瓧 + 鎻忚堪绗**,杞借嵎涓庤繍琛屾椂鍙**鎻忚堪绗?*鏈変竴鐐逛笉鍚?鏌ユ壘灏辫惤绌?
   浜庢槸"浠ヨ繍琛屾椂涓哄噯"鐨勮鍒?*鏍规湰涓嶄細鐢熸晥**,瀛楁浼氬師灏佷笉鍔ㄥ甫鐫€杞借嵎鐨勪綅 鉁?鈥斺€?杩欒В閲婁簡涓轰粈涔堜袱娆?
   璁块棶浣嶆敼鍔ㄩ兘鏄?瑕佷箞鏃犳晥銆佽涔堟湁瀹?銆?
3. **涓嬩竴姝ュ簲璇ュ厛瑙ｅ喅鍖归厤,鍐嶈皥鍙栬垗**:鍏堢敤璋冭瘯杈撳嚭鎶?`VertexFormat`(鍙?`ForgeRenderTypes$CustomizableTextureState`)
   閲岄偅鍑犱釜瀛楁鍦ㄨ浇鑽?杩愯鏃朵袱渚х殑**鍚嶅瓧銆佹弿杩扮銆佽闂綅**鐪熷疄鎵撳嵃鍑烘潵,鐪嬫竻"璋佷笌璋佸搴斻€佸樊鍦ㄥ摢涓€浣?,
   鍐嶅喅瀹氳鍒?鑰屼笉鏄户缁嚟鎺ㄦ祴鏀瑰悎骞堕€昏緫銆傛敼鍔ㄥ墠鍏堣窇 1.20.4 鍥炲綊銆?

**娴嬪嚭鏉ヤ箣鍚?瑙勫垯绗笁娆℃敼瀵逛簡(鍚屼竴鏅?杩欐涓ゆ潯绾块兘楠岃瘉杩?**

鍏堝仛娴嬮噺:`ForgeRenderTypes$CustomizableTextureState` 鐨?javap 鏄剧ず瀹冩槸
**`extends net.minecraft.client.renderer.RenderStateShard$TextureStateShard`** 鈥斺€?涔熷氨鏄
`f_110131_` 涓嶆槸瀹冭嚜宸卞０鏄庣殑,鑰屾槸**浠?OptiFine 浼氭浛鎹㈢殑閭ｄ釜 Minecraft 绫荤户鎵挎潵鐨?*;鑰屽啓瀹冪殑鏄?
NeoForge 鑷繁鐨勬瀯閫犲櫒銆傚師鍥犲氨娓呮浜?**NeoForge 鐨?access transformer 鎶?`final` 鍘绘帀浜?*,
濂借鑷瀛愮被鑳借祴鍊?鑰?OptiFine 鐨勭紪璇戦噷杩欎釜瀛楁浠嶆槸 `final` 鈫?璧嬪€艰 JVM 鎷掔粷銆?

姝ｇ‘鐨勮鍒欏洜姝ゆ槸"绗竴娆＄殑瑙勫垯 + 鎺ュ彛渚嬪",鑰屼笉鏄?鎸変綅鍙栬繍琛屾椂":

- **鎺ュ彛**鐨勫瓧娈典竴寰?`public static final`(鍚﹀垯 `ClassFormatError: Illegal field modifiers 鈥?0x9`);
- **鏅€氱被**鐨勫瓧娈?*涓嶄粠杞借嵎缁ф壙 `final`**(杩愯鏃堕偅浠芥槸 final 灏变繚鐣?鍚﹀垯娓呮帀;杞借嵎鏂板鐨勫瓧娈典篃娓呮帀);
- **`static` 浣嶄笉鍔?* 鈥斺€?涔嬪墠鎶婂畠涔熷彇杩愯鏃?姝ｆ槸 `VertexFormat.elem鈥 閭ｆ澶辫触鐨勫師鍥犮€?

缁撴灉:**1.20.4 `VERDICT: TIMEOUT (181s)` 鏃犲洖褰?* 鉁?**1.20.1 鐨?`IllegalAccessError` 娑堝け**,
澶辫触鐐逛粠"绫诲姞杞芥湡"鎺ㄨ繘鍒?*璧勬簮閲嶈浇鏈?*(宸茬粡杩涘埌鍔犺浇鐣岄潰涔嬪悗):

```
Description: Rendering overlay
java.lang.NoSuchMethodError:
  'void net.minecraft.client.particle.ParticleEngine$ParticleDefinition.<init>(net.minecraft.resources.ResourceLocation, java.ut鈥?
	at net.minecraft.client.particle.ParticleEngine.lambda$reload$5(ParticleEngine.java:269)
```

**涓嬩竴姝?1.20.1,鏂扮殑鍓嶆部)**:`ParticleEngine$ParticleDefinition` 鐨勬瀯閫犲櫒绛惧悕鍦ㄤ袱渚т笉涓€鑷?鈥斺€?
杞借嵎閲岃鎹㈣繘鍘荤殑 `ParticleEngine` 璋冪殑鏄畠缂栬瘧鏃堕偅涓鍚?鑰岃繍琛屾椂鐨勫悓鍚嶅祵濂楃被娌℃湁杩欎釜鏋勯€犲櫒銆?
杩欐鏄?鎴愬憳鍥炲～(plan/donor)"瑕佸鐞嗙殑涓€绫婚棶棰?鍙槸鏂瑰悜鐩稿弽(杩欐鏄?*杞借嵎鍦ㄨ皟鐢?*銆佽繍琛屾椂缂?,
鍏堥噺娓呮涓や晶璇ョ被鐨勬瀯閫犲櫒鍒楄〃,鍐嶅喅瀹氭槸鎶婂畠涔熺撼鍏ュ洖濉?杩樻槸鎶婅宓屽绫绘暣鏃忔寜瀹舵棌瑙勫垯澶勭悊銆?

**閲忔竻妤氫簡:涓嶆槸鏋勯€犲櫒涓嶄竴鑷?鏄?鍚屼竴涓被琚捣浜嗕袱涓悕瀛?(鍚屾櫄)**

閫愪釜 jar 鎵繃涔嬪悗,浜嬪疄鏄?

| 渚?| 绫诲悕 | 褰㈡€?|
|---|---|---|
| 杞借嵎(OptiFine) | `net/minecraft/client/particle/ParticleEngine$ParticleDefinition` | record,鏋勯€犲櫒 `(ResourceLocation, Optional<List<ResourceLocation>>)` |
| 杩愯鏃?娓告垙 jar / forge client) | `net/minecraft/client/particle/ParticleEngine$**1**ParticleDefinition` | **鍚屼竴涓?record,鍚屼竴涓瀯閫犲櫒绛惧悕** |

涔熷氨鏄 `$ParticleDefinition` 涓?`$1ParticleDefinition` 鏄?*鍚屼竴涓被**,鍙槸鍛藉悕鏉ユ簮涓嶅悓:
杞借嵎鐨勫悕瀛楁潵鑷贩娣?jar,杩愯鏃剁殑鏉ヨ嚜 NeoForm銆?*杩欐鏄湰鏂囨。鏃╁厛璁拌繃鐨勯偅搴ц繕娌℃灦鐨勬ˉ**
(`Gui$1DisplayEntry` 瀵?OptiFine 鐨?`Gui$DisplayEntry`,褰撴椂鍙奖鍝?5 涓ˉ涓佺被,鐜板湪瀹冩尅鍦ㄤ簡 1.20.1 鐨勫惎鍔ㄨ矾寰勪笂)銆?

杩樿娉ㄦ剰涓€涓粏鑺?鎴戜滑**纭疄**鎶婅浇鑽烽偅浠?ship 杩?jar 浜?`optifineoforge/patched/net/minecraft/client/particle/
ParticleEngine$ParticleDefinition.class`,2373 瀛楄妭),绱㈠紩閲屼篃鏈夊畠(`net/minecraft/client/particle/ParticleEngine$ParticleDefinition`)
鈥斺€?浣?ML 鍙細涓?**杩愯鏃跺瓨鍦?*鐨勫悓鍚嶇被"璋冪敤鎴戜滑鐨?transformer,鎵€浠ヨ繖涓彧瀛樺湪浜庤浇鑽风殑鍚嶅瓧**姘歌繙涓嶄細琚畨瑁?*,
鑰岃浇鑽烽偅浠?`ParticleEngine` 鍗存寜瀹冪紪璇戞椂鐨勫悕瀛楀幓璋?鈫?`NoSuchMethodError`銆?

**涓嬩竴姝?1.20.1,瀹炵幇鑰屼笉鏄帰绱?**:鏋惰繖搴?绫诲悕瀵归綈"鐨勬ˉ,鑰屼笖鏈夌幇鎴愮殑鍒ゅ畾鏉′欢鍙敤 鈥斺€?
瀵规瘡涓?杞借嵎鏈?`$`銆佽繍琛屾椂娌℃湁鍚屽悕绫?鐨勭被,鍘昏繍琛屾椂鎵?*澶栧眰鍚嶇浉鍚屼笖鎴愬憳缁撴瀯涓€鑷?*鐨勫€欓€?
(鏈緥涓よ€呮瀯閫犲櫒涓庡瓧娈靛畬鍏ㄤ竴鏍?,鎶婅浇鑽烽偅浠?*鎸夎繍琛屾椂鐨勫悕瀛?*ship 鍑哄幓骞跺啓杩涚储寮?
杩欐牱 ML 鍦ㄩ棶杩愯鏃剁殑 `$1ParticleDefinition` 鏃?鎴戜滑灏辫兘鎶婅浇鑽风殑 `$ParticleDefinition` 瑁呰繘鍘汇€?

**妗ョ殑绗竴鍗婂仛濂戒簡,浣嗗畠鍙В鍐充竴鍗?鍚屾櫄,绮剧‘鍒?杩樺樊浠€涔?)**

鏂板伐鍏?`NestedNameBridge`(鏋勫缓鏈?:鎸?*缁撴瀯**閰嶅 鈥斺€?鐖剁被鐩稿悓銆佸瓧娈垫弿杩扮闆嗗悎鐩稿悓銆佹柟娉曟弿杩扮闆嗗悎鐩稿悓,
骞朵笖**鎭板ソ鍙湁涓€涓?*鍊欓€夋椂鎵嶉厤瀵?澶氫簬涓€涓氨鎶ユ涔夊苟鏀惧純;閰嶉敊浼氳閿欑被)銆傚疄娴嬫壘鍑烘潵鐨勫:

```
1.20.4: 3 瀵?  Gui$DisplayEntry -> Gui$1DisplayEntry
                ParticleEngine$ParticleDefinition -> ParticleEngine$1ParticleDefinition
                LevelChunkSection$BlockCounter -> LevelChunkSection$1BlockCounter
1.20.1: 2 瀵?  ParticleEngine$ParticleDefinition -> ParticleEngine$1ParticleDefinition
                LevelChunkSection$BlockCounter -> LevelChunkSection$1BlockCounter
```

绗竴瀵规鏄湰鏂囨。鏃╁厛璁拌繃鐨?`Gui$1DisplayEntry` 鑰侀棶棰?鈥斺€?鐜板湪瀹冭鑷姩鎵惧嚭鏉ヤ簡銆?
rig 宸茬粡鎸?杩愯鏃剁殑鍚嶅瓧"ship 杩欎簺绫诲苟鍐欒繘绱㈠紩;**1.20.4 浠嶆槸 `VERDICT: TIMEOUT (181s)`銆佹棤鍥炲綊** 鉁撱€?

**浣?1.20.1 鐨勯敊璇病鍙?*:`NoSuchMethodError: ParticleEngine$ParticleDefinition.<init>(鈥?`銆?
鍘熷洜寰堟竻妤?鑰屼笖璇存槑杩欏骇妗ラ渶瑕?*涓ゅ崐**:

- 鍙妸**鎴戜滑 ship 鐨勭被**鏀瑰悕,绛変簬鎶婄被浠?杞借嵎鍚?鎼埌"杩愯鏃跺悕";鑰?*杞借嵎閲岀殑寮曠敤**浠嶇劧鍐欑潃杞借嵎鍚?
  (`ParticleEngine` 鐨勫父閲忔睜閲屾槸 `$ParticleDefinition`)鈥斺€?浜庢槸鐜板湪杩為偅涓悕瀛楅兘娌℃湁浜?鉁椼€?
- 涔熷氨鏄:**绫诲悕瀵归綈蹇呴』鍚屾椂鏀?鎴戜滑鍙戠殑绫诲悕"鍜?杞借嵎閲岀殑寮曠敤"**銆傚悗鑰呮鏄湰鏂囨。鏃╁厛鍐欑殑閭ｅ彞
  "鏋?琛ヤ竵鏉＄洰鍚?鈫?娣锋穯 base 鍚?杩欏骇妗ユ妸绫诲悕瀵归綈"鈥斺€斿彧鏄幇鍦ㄦ湁浜嗙簿纭€佸彲鍒ゅ畾鐨勯厤瀵硅〃(鍙敤缁撴瀯鍒ゅ畾)銆?

**涓嬩竴姝?1.20.1,鏀跺熬杩欏骇妗?**:鍦?`NestedNameBridge` 鐨勫熀纭€涓婂姞涓€閬?*杞借嵎绫诲悕閲嶅啓**(涓?`SrgRemap`
鍚屼竴濂?ASM `Remapper` 鏈哄埗,鍙槸鏀圭殑鏄被鍚嶈€屼笉鏄垚鍛樺悕):鎶婅浇鑽烽噷瀵规棫鍚嶇殑**寮曠敤**鏀规垚杩愯鏃跺悕,
鍚屾椂 rig 缁х画鎸夎繍琛屾椂鍚?ship 绫绘湰韬€備袱鍗婇兘鍒颁綅鍚?`ParticleEngine` 璋冪殑灏辨槸杩愯鏃剁湡瀹炲瓨鍦ㄧ殑绫诲悕浜嗐€?

**妗ョ殑涓ゅ崐閮藉埌浣?1.20.1 鎵撻€氫簡(鍚屼竴鏅?**

`NestedNameBridge --rewrite <in> <out> <runtime鈥?`:鐢ㄥ悓涓€浠介厤瀵硅〃,鎶婅浇鑽烽噷鎵€鏈夊鏃х被鍚嶇殑**寮曠敤**
(甯搁噺姹犮€佹弿杩扮銆丼ignature銆両nnerClasses)鏀规垚杩愯鏃剁殑鍚嶅瓧,閰嶅鐨勯偅涓被鑷繁涔熸崲鎴愯繍琛屾椂鍚?
rig 鍦?plan/stub/ship **涔嬪墠**鎶?`$patched` 鎹㈡垚杩欎唤瀵归綈鍚庣殑 jar,鎵€浠ヤ笅娓哥湅鍒扮殑閮芥槸杩愯鏃跺悕銆?

瀹炴祴鏀瑰啓瑙勬ā:`1.20.4: 3 瀵?/ 1143 涓被琚敼鍐檂銆乣1.20.1: 2 瀵?/ 2210 涓被琚敼鍐檂銆?

**缁撴灉:涓ゆ潯绾块兘鏄?`VERDICT: STARTED (40s, marker: Sound engine started)`,1.20.1 棣栨鐪熸璺戣捣鏉?* 鈥斺€?
楠屾敹鍙ｅ緞涓庡叾瀹冭涓€鑷?

| 鎸囨爣 | 1.20.1(鏈) |
|---|---|
| `Setting user`(杩涙爣棰樼晫闈? | 鉁?|
| `[Shaders] OpenGL Version` | 鉁?`4.6.0 NVIDIA 591.86` |
| `Connected textures` | 2 琛?鉁?|
| `Pre-stitch`(OptiFine 鎷煎浘闆? | 12 琛?鉁?|
| `[OptiFine]` 鏃ュ織 | **157 琛?* 鉁?|
| 鏇挎崲绫?| 233 涓?|
| **stderr** | **27 瀛楄妭**(鍩烘湰骞插噣) |
| 鎴浘 / 鏂?crash report | 927 KB / **鏃?* 鉁?|

鑷虫 **1.20.x 鐨?1.20.1銆?.20.4銆?.20.6 涓夎閮藉凡瀹炴満璺戦€?*(1.20.6 鏄?4.24 MB 杞借嵎璺嚎銆?.20.4 鏄?
瀹樻柟鍚嶉噸鏄犲皠璺嚎銆?.20.1 鏄?SRG 鍘熸牱 + 绫诲悕瀵归綈璺嚎);杩欎竴琛屽彧鍓?**1.20.2**(闇€瑕?OptiFine 1.20.2 鐨?jar,
鏈湴娌℃湁,`mcp_config-1.20.2.zip` 宸插湪)銆?

**鐗堟湰鍙风涓€娆＄湡姝ｈ蛋浜嗕竴閬?`release/version.ps1`(鍚屾櫄)**

`0.1.0` 鈫?`0.2.0`(minor),渚濇嵁鏄笁琛屽凡瀹炴満璺戦€氥€丱ptiFine 鍔熻兘鍦ㄨ窇(shaders/CTM/鍥鹃泦/157 琛?`[OptiFine]` 鏃ュ織)銆?
鑴氭湰涓夋潯璺緞閮藉疄娴嬭繃:`show` 鉁撱€乣minor -DryRun`(鎵撳嵃 `would change 0.1.0 -> 0.2.0`)鉁撱€乣minor` 鐪熸鍐欏叆
(UTF-8 鏃?BOM)鉁撱€備骇鐗╁悕闅忚鍙樺寲:`OptifiNeoforge-0.2.0+mc1.20.4.jar`(鏈榛樿鐩爣 1.20.4),
`-Pmc=1.20.1 / 1.20.6` 鏃跺垎鍒槸 `+mc1.20.1` / `+mc1.20.6`銆?

鍏充簬 0.x 鐨勫垽鎹?`VERSIONING.md` / 鑴氭湰娉ㄩ噴鍐欑殑鏄?0.x = loader 杩樻病鍦ㄦ父鎴忛噷璺戣捣鏉?1.0.0 瑕佹墜鍔?`-Base` 璁?銆?
鐜板湪鐨勭姸鎬佹槸**閮ㄥ垎鎴愮珛**:1.20.x 涓夎璺戦€?浣嗘暣涓煩闃?1.20.2銆?.21.x銆?6.x)杩滄湭瀹屾垚 鈥斺€?鎵€浠?
**涓嶈 1.0.0**,鐢?`0.2.0` 琛ㄧず"宸插紑濮嬭窇閫氥€佷絾鐭╅樀鏈畬鎴?銆傜瓑鐩爣閲岄偅鏉?1.20.1 鍒?26.1.2 鍏ㄨ鐩?鐪熸楠屾敹杩囦簡,
鍐嶇敤 `release/version.ps1 patch -Base 1.0.0` 涓€娆℃€ф妸瀹冭涓?1.0.0銆?

**鐩爣鑼冨洿鐨勬潈濞佹竻鍗?2026-09-15 褰撴櫄,鍙栬嚜闀滃儚鐨?OptiFine 鐗堟湰琛?**

鐩爣鍐欑殑鏄?1.20.1 鍒?26.1.2 涔嬮棿**鎵€鏈夋湁 OptiFine 鏋勫缓**鐨勭増鏈?,鑰?鍝簺鐗堟湰鏈?杩欎欢浜嬩笉璇ラ潬璁板繂銆?
鏌ヨ `https://bmclapi2.bangbang93.com/optifine/versionList`(497 鏉?鍚?mcversion/type/patch/filename)
寰楀埌鐨?1.20.1 鍙婁互鍚庢竻鍗曟槸:

| 鐗堟湰 | 鏋勫缓鏁?| 鏈€鏂版瀯寤?| 澶囨敞 |
|---|---|---|---|
| 1.20.1 | 14 | `OptiFine_1.20.1_HD_U_I6.jar` | 姝ｅ紡鐗?鉁?鏈垎鏀凡璺戦€?|
| **1.20.2** | **1** | `preview_OptiFine_1.20.2_HD_U_I7_pre1.jar` | **鍙湁 preview**(鍏堝墠鎸夋寮忕増鍚嶅幓涓?404 灏辨槸杩欎釜鍘熷洜) |
| 1.20.4 | 11 | 鏈€鏂版槸 `pre4` preview | 姝ｅ紡鐗?I7 宸叉湁,鏈垎鏀凡璺戦€?|
| 1.20.6 | 3 | `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar` | 鍙湁 preview,宸茶窇閫?|
| 1.21 | 8 | `preview_OptiFine_1.21_HD_U_J1_pre8.jar` | 鍙湁 preview |
| 1.21.1 | 8 | `OptiFine_1.21.1_HD_U_J1.jar` | 姝ｅ紡鐗?jar 宸插湪鏈湴 |
| 1.21.3 | 13 | `OptiFine_1.21.3_HD_U_J2.jar` | 姝ｅ紡鐗?|
| 1.21.4 | 18 | 鏈€鏂版槸 `pre2` preview | 姝ｅ紡鐗?J3 宸插湪鏈湴,鍏堝墠宸茶窇閫?|
| 1.21.6 | 3 | `preview_鈥J6_pre3.jar` | 鍙湁 preview |
| 1.21.7 | 4 | `preview_鈥J6_pre7.jar` | 鍙湁 preview |
| 1.21.8 | 10 | `preview_鈥J6_pre16.jar` | 鍙湁 preview |
| 1.21.9 | 2 | `preview_鈥J7_pre1.jar` | 鍙湁 preview |
| 1.21.10 | 10 | `preview_鈥J7_pre11.jar` | 鍙湁 preview |
| 1.21.11 | 18 | `OptiFine_1.21.11_HD_U_J9.jar` | 姝ｅ紡鐗?鏈湴鏈? |
| 26.1.2 | 2 | `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` | 鍙湁 preview(鏈湴鏈? |

椤哄甫璁颁竴鏉＄粡楠?**涓嶈鎸?姝ｅ紡鐗堟枃浠跺悕"鍘荤寽涓嬭浇鍚?* 鈥斺€?1.20.2 閭ｄ竴琛屽氨鏄繖鏍风櫧璺戜簡涓€鍦堛€?
涓嬭浇鑴氭湰 `fetch-optifine.ps1` 鐜板湪浠庡厓鏁版嵁绔偣鍙?`type`/`patch` 鍐嶆嫾 URL
(`/optifine/{mcversion}/{type}/{patch}`),宸插疄娴嬫嬁鍒?`preview_OptiFine_1.20.2_HD_U_I7_pre1.jar`(7,192,269 瀛楄妭)銆?

**1.20.2 杩欎竴琛岀幇鍦ㄧ殑鍑嗗搴?*:OptiFine jar 鉁撱€丯eoForge 20.2.88 鉁?宸茶,`neoforge-20.2.88-client.jar`);
杩樼己 **1.20.2 鐨勫師鐗堝鎴风 jar**(绠￠亾鎵撹ˉ涓佽鐢ㄦ贩娣?jar)涓?**`mcp_config-1.20.2.zip`**(杩欎竴琛屾槸 SRG 杞借嵎 +
瀹樻柟鍚嶈繍琛屾椂鐨勭粍鍚?涓?1.20.4 鍚岀被,闇€瑕侀偅浠芥槧灏勮〃鏉ュ缓 SRG鈫攐fficial 琛?銆?

**1.20.2 鏋勫缓閫氳繃銆佸惎鍔ㄥ崱鍦?OptiFine 鑷繁涓?SecureJarHandler 鐨勭増鏈敊閰嶄笂(鍚屼竴鏅?宸查噺鍒板叿浣撶鍚?**

鍑嗗搴﹁ˉ榻愬悗(鍘熺増 jar 鏈潵灏卞湪銆乣mcp1202-joined.tsrg` 5,781,794 瀛楄妭宸茶В鍑恒€丮odLauncher 10.0.9銆?
1.20.2 娓告垙 jar 瀹炴祴鏄畼鏂瑰悕 `literal`),**鏋勫缓鏁存潯娴佹按绾块兘姝ｅ父**:

```
patched game classes: 423 (365 net/minecraft)
rewrote 21289 method and 17305 field names, 80 could not be resolved, 10 refused
bridged nested names: 2 鈫?rewrote references in 1132 classes
payload: 1091 classes, 404 moved to optifineoforge/patched
compared 233 replaced classes (828 without a runtime counterpart), 56 members to restore, 4 donors closed
```

鍚姩鍒欐鍦?**OptiFine 鑷繁鐨?`OptiFineJar`** 涓?鑰屼笖鍘熷洜鏄竴涓函绮圭殑**搴撶増鏈敊閰?*:

```
NoSuchMethodError: 'void cpw.mods.jarhandling.impl.SimpleJarMetadata.<init>(java.lang.String, java.lang.String, java.util.S鈥?
	at optifine.OptiFineJar.lambda$1(OptiFineJar.java:24)
	at optifine.OptiFineJar.<init>(OptiFineJar.java:24)
	at optifine.OptiFineTransformationService.completeScan(OptiFineTransformationService.java:82)
```

涓や晶閮界敤 javap 閲忚繃:

| | `SimpleJarMetadata` 鐨勭涓変釜鍙傛暟 |
|---|---|
| OptiFine 1.20.2 璋冪敤鐨?| `java.util.Set<String>`(鍗?**2.1.10** 閭ｄ竴浠? |
| NeoForge 20.2.88 profile 閲岀殑 securejarhandler **2.1.24** | `java.util.function.Supplier<java.util.Set<String>>` |

涔熷氨鏄 **OptiFine 鐨?1.20.2 preview 鏄拡瀵规洿鑰佺殑 SecureJarHandler 缂栬瘧鐨?*,鑰?20.2.88 宸茬粡鎹㈡垚鏂扮増;
1.20.4/1.20.6 閭ｄ袱琛屾病杩欎釜闂,鏄洜涓哄畠浠搴旂殑 OptiFine 涓?SecureJarHandler 鏃朵唬瀵瑰緱涓娿€?

**涓嬩竴姝?1.20.2,涓ゆ潯璺?鍏堣瘯渚垮疁鐨勯偅鏉?**:鈶犺涓€涓?*鏇存棭鐨?NeoForge 20.2.x**,鐪嬪畠鐨?
securejarhandler 鏄笉鏄?2.1.10 鈥斺€?鏄殑璇濊繖涓€琛屽緢鍙兘鐩存帴鍙窇(涓?1.20.4 鍚屼竴绫昏矾绾?;鈶¤嫢鎵€鏈?20.2.x 閮芥槸
2.1.24,灏辩敤鐜版垚鐨?ASM 宸ュ叿鎶?OptiFine 閭ｄ釜 `invokespecial` 鏀规垚鏂扮鍚?鎶?`Set` 鍖呮垚 `Supplier`,`() -> set`
杩欑褰㈡€佸湪瀛楄妭鐮侀噷寰堝ソ鍐?,杩欎笌 `OptifineJarFixer` 淇?`toFile` 鏄悓涓€绫诲鐞嗐€?

**璺瓙鈶犺蛋涓嶉€?宸插疄娴?鈶＄殑鍏蜂綋鍋氭硶涔熷凡鏄庣‘(鍚屼竴鏅?**

maven 涓?`net.neoforged:neoforge` 鐨?20.2 绯诲垪鍙湁 **7 涓瀯寤?20.2.86 鈥?20.2.93)**,閫愪釜瑁呰捣鏉ユ煡
profile 閲岀殑 securejarhandler:**鍏ㄦ槸 2.1.24**(20.2.86 / 20.2.88 / 20.2.93 瀹炴祴;鍙﹀ 4 涓偅涓ゆ瀹夎娌′骇鍑?profile);
鑰?`net.neoforged:forge` 閭ｄ竴缁?*鏍规湰娌℃湁 1.20.2 鐨勬瀯寤?*(0 鏉?銆傚鐓т竴涓嬫鍦ㄨ窇閫氱殑 1.20.1 閭ｄ竴琛?
瀹冪殑 profile 鐢ㄧ殑鏄?**securejarhandler 2.1.10** 鈥斺€?涔熷氨鏄 **OptiFine 鏄嬁"瀹冮偅涓勾浠ｇ殑 SecureJarHandler"
缂栬瘧鐨?*,1.20.1 瀵瑰緱涓婃墍浠ヨ兘璺?1.20.2 鐨?preview 瀵逛笉涓?2.1.10 鐨勮皟鐢?vs 2.1.24 鐨勭鍚?鎵€浠ヨ捣涓嶆潵銆?
涓嶅瓨鍦?鎹釜 20.2.x 灏卞ソ"鐨勫彲鑳姐€?

**閫愬瓧鑺備慨娉?涓嬩竴姝ュ疄鐜?**:OptiFine 鐨?`optifine/OptiFineJar.class` 閲岄偅涓€澶勮皟鐢ㄨ鏀圭鍚嶁€斺€?
2.1.24 鐨勭涓変釜鍙傛暟鏄?`Supplier<Set<String>>`,鑰屾爤涓婃鍒绘槸涓€涓?`Set`銆傚仛娉?

1. 鍦ㄦ垜浠殑 jar 閲屾斁涓€涓瀬灏忕殑 `Supplier<Set<String>>` 瀹炵幇(甯︿竴涓悆 `Set` 鐨勬瀯閫犲櫒),鏀惧湪
   `kynarain/cn/optifineoforge/loader/` 涓嬪氨琛?鈥斺€?rig 绗?5 姝ユ湰鏉ュ氨浼氭妸杩欎釜鍖呯殑绫诲姞杩涗骇鐗?鉁?
2. 鍦?`OptifineJarFixer` 閲屽姞涓€鏉″鐞?`handles()` 澧炶 `optifine/OptiFineJar`,鍦ㄩ偅鏉?`invokespecial` 涔嬪墠
   **鎻掑叆** `NEW helper; DUP_X1; INVOKESPECIAL helper.<init>(Ljava/util/Set;)V` 鈥斺€?鏍堝氨浠?
   `[this,name,version,set]` 鍙樻垚 `[this,name,version,supplier]`,鍘熻皟鐢ㄧ収鏃ф墽琛?鉁撱€?

杩欎笌淇?`toFile` 鏄悓涓€绫?瀹氱偣鎻掑叆"鑰岄潪"鏁存鏇挎崲",涔熸槸鏈疆瀛﹀埌鐨勬暀璁?鏁存鏇挎崲鍒犲壇浣滅敤閭ｆ)銆?

**淇硶宸插疄鐜?鑰屼笖鏆撮湶浜嗕竴涓?rig 缁撴瀯闂(鍚屼竴鏅?**

鎸変笂闈㈢殑鍋氭硶鍋氬畬(`SetSupplier` 鏀捐繘 loader 鍖?鉁撱€乣OptifineJarFixer` 澧炶 `optifine/OptiFineJar` 骞舵彃
`NEW/DUP_X1/INVOKESPECIAL` + 鏀硅皟鐢ㄦ弿杩扮 鉁撱€乣OptifineJar` 鏀规垚鎸夊悕鍒嗘淳 `fix(name, bytes)` 鉁?涔嬪悗,
**绗竴娆″惎鍔ㄦ鏃犲彉鍖?* 鉁椼€傚師鍥犱笉鏄ˉ涓佸啓閿?鑰屾槸**浜х墿閲岄偅浠界被琚鐩栦簡**:

- 璧?`-ShipPayload` 鐨勮,OptiFine 鐨勬潯鐩槸浠?*绠￠亾杈撳嚭**閲屽彇鐨?鑰岀閬撹緭鍑鸿鐨勬槸**鍘熷 OptiFine jar**
  (鏈慨)鉁?
- 鎼繍姝ラ鍙妸**涓€涓?*淇ソ鐨勭被浠?repack 鍚庣殑 jar 閲屽彇鍥炴潵 鈥斺€?灏辨槸 service 閭ｄ釜绫?纭紪鐮佷竴涓潯鐩?鉁?
  鎵€浠?`OptiFineJar` 鐨勪慨澶嶈鍘熷鍓湰鐩栨帀浜嗐€?

宸叉妸杩欎竴姝ユ敼鎴?*鍒楀嚭鎵€鏈夎淇殑绫?*(service + `OptiFineJar`)骞堕€愪釜浠?repack 鍚庣殑 jar 鍙栧洖 鉁撱€?
绗簩娆″惎鍔?浜х墿閲岀殑 `optifine/OptiFineJar.class` 鍙樻垚 4302 瀛楄妭銆佸惈 1 澶?`SetSupplier` 寮曠敤 鉁?
鈥斺€?琛ヤ竵纭疄杩涘幓浜?**閿欒涔熼殢涔嬫崲浜嗙绫?*:

```
java.lang.VerifyError: Bad type on operand stack
  Location: optifine/OptiFineJar.lambda$1(...) @24: invokespecial
```

**涓嬩竴姝?1.20.2)**:閿欒浠?`NoSuchMethodError` 鍙樻垚鍚屼竴涓皟鐢ㄧ偣涓婄殑 `VerifyError`,璇存槑鎻掑叆鐨勬爤鎿嶄綔
涓庨偅閲岀湡瀹炵殑鎿嶄綔鏁板舰鐘朵笉涓€鑷?鎴戝湪鎺ㄧ悊閲屽亣璁剧殑鏄?`[this,name,version,set]`)銆?*鍏堥噺鍐嶆敼**:鎶婂師濮?
`OptiFineJar.lambda$1` 浠庡叆鍙ｅ埌閭ｆ璋冪敤涔嬮棿鐨勫瓧鑺傜爜涓庢爤鍥炬墦鍑烘潵,鐪嬫竻绗笁涓搷浣滄暟鍒板簳鏄粈涔?
(`Set`?闆嗗悎鏋勯€犲櫒?鏁扮粍?),鍐嶅喅瀹氬寘涓€灞傝繕鏄崲涓€绉嶆ˉ鎺ユ柟寮忋€?

鍙﹀:**rig 閭ｅ"鍙栧洖琚慨绫?鐨勬敼鍔ㄥ奖鍝嶆墍鏈?`-ShipPayload` 琛?*,鎵€浠ヤ笅涓€杞繀椤诲厛璺?1.20.4銆?.20.6 鍥炲綊,
纭瀹冧滑娌℃湁鍥犱负杩欐鏀瑰姩鑰屽彉鍖?瀹冧滑姝ゅ墠閮芥槸 `VERDICT: STARTED`)銆?

**鍥炲綊宸插仛,鎻掑叆鐐逛篃鎸夊瓧鑺傜爜鏀逛簡,浣?1.20.2 杩樻病杩?鍚屼竴鏅?鎶婁袱娆?VerifyError 閮借涓嬫潵)**

- **鍥炲綊 鉁?*:鏀瑰畬 rig 涔嬪悗閲嶈窇 1.20.4 涓?1.20.6,涓ゆ潯閮芥槸 `VERDICT: STARTED (40s, marker: Sound engine started)`,
  鏁板瓧涓庡厛鍓嶄竴鑷?1.20.4: 76 鎴愬憳/27 绫?stub銆?48 鐩爣;1.20.6: 85 鎴愬憳/29 绫汇€?50 鐩爣)鉁?
  鈥斺€?"鍙栧洖鎵€鏈夎淇被"杩欎釜鏀瑰姩瀵瑰凡楠岃瘉鐨勮娌℃湁鍓綔鐢ㄣ€?
- 鍙嶆眹缂栧師濮?`OptiFineJar.lambda$1` 鐪嬫竻浜嗙湡瀹炵殑璋冪敤搴忓垪(杩欐鏄厛鍓嶆帹鐞嗛敊鐨勫湴鏂?:

```
 0: new SimpleJarMetadata          // [metadata]
 3: dup                            // [metadata, metadata]
 4: ldc "net.optifine"             // [.., name]
 6: aconst_null                    // [.., name, version]
 7: aload_0; SecureJar.getPackages()   // [.., name, version, set]
13: new ArrayList; dup; <init>     // [.., name, version, set, list]
20: invokespecial SimpleJarMetadata.<init>(String,String,Set,List)V
```

涔熷氨鏄**绱ф尐鐫€璋冪敤澶勬爤椤舵槸 `list` 鑰屼笉鏄?`set`**,鎵€浠ョ涓€娆″湪璋冪敤鍓嶆彃 `DUP_X1` 蹇呴敊 鉁撱€?
鏀规垚"鍦?`getPackages()` 涔嬪悗(鍗?set 鍒氫骇鐢熺殑浣嶇疆)鍖呬竴灞?涔嬪悗,`VerifyError` 浠?**@24 绉诲埌浜?@17**(姝ｆ槸鏂版彃鍏ョ偣 鉁?,
浣嗕粛鐒舵槸 `Bad type on operand stack`:

```
Location: optifine/OptiFineJar.lambda$1(...) @17: invokespecial
Reason:   Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

`uninitialized 13` 璇存槑鎵ц鍒版彃鍏ョ偣鏃舵爤椤舵槸涓€涓?*灏氭湭鏋勯€犲畬鐨勫璞?*(鍍忔槸閭ｄ釜 `ArrayList`),鑰屼笉鏄垰鍙栧嚭鐨?`set`
鈥斺€?涔熷氨鏄鎻掑叆浣嶇疆鍦?*鎵撹ˉ涓佸悗**鐨勫疄闄呮寚浠ゅ簭鍒楅噷浠嶄笉鏄垜鎯崇殑閭ｄ釜鐐?鎴栬鏂规硶閲屽彟鏈夎矾寰勪細琚蛋鍒?銆?
**涓嬩竴姝?1.20.2)**:涓嶈鍐嶉潬璇诲師濮嬪瓧鑺傜爜鎺ㄦ柇浣嶇疆 鈥斺€?鎶?*鎵撹ˉ涓佸悗**鐨?`lambda$1` 鎸囦护搴忓垪鏁存 dump 鍑烘潵
(ASM 閲岄€愭潯鎵撳嵃),鎸夊疄闄呭簭鍒楀畾浣?`getPackages` 涓庢垜鐨?wrapper 鐨勭浉瀵逛綅缃?鍐嶅喅瀹氭彃鍏ョ偣;椤哄甫纭
`getPackages` 鍦ㄥ悓涓€鏂规硶閲屾槸鍚﹀彧鍑虹幇涓€娆?鎼滅储鍙栫殑鏄渶鍚庝竴娆″尮閰?銆?

**鎵撹ˉ涓佸悗鐨勫簭鍒?dump 鍑烘潵浜?鎸囦护鏄鐨?浣?verifier 浠嶇劧涓嶈(鍚屼竴鏅?鎵€浠ユ敼鐢?鏁存閲嶅啓")**

```
 8: invokeinterface SecureJar.getPackages()Ljava/util/Set;      // [.., set]
13: new SetSupplier
16: dup_x1                                                      // [.., helper, set, helper]
17: invokespecial SetSupplier.<init>(Ljava/util/Set;)V          // [.., helper]
20: new ArrayList; dup; invokespecial <init>()V                  // [.., helper, list]
27: invokespecial SimpleJarMetadata.<init>(String,String,Supplier,List)V
30: areturn
```

鎸?javap 鎵撳嚭鏉ョ殑搴忓垪,杩欐鏄垜鎯宠鐨勫瓧鑺傜爜(`set` 鍦?`helper` 涔嬩笅銆乣invokespecial` 鍙栭《涓婄殑 helper 褰?
objectref銆佸弬鏁版槸 `set`),鍙繍琛屾椂 verifier 杩樻槸鎶?

```
Location: optifine/OptiFineJar.lambda$1(...) @17: invokespecial
Reason:   Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

涔熷氨鏄**闂涓嶅湪鎸囦护鏈韩,鑰屽湪鏍堝浘**:鍐欏叆鍣ㄧ敤 `COMPUTE_FRAMES` 閲嶇畻鏃?瀵?涓嶅湪骞冲彴绫诲姞杞藉櫒閲岀殑
`SetSupplier`"鍙兘鎶婂叕鍏辩埗绫诲垽鎴?`java/lang/Object`(`SafeClassWriter` 鐨勫厹搴?,鑰?verifier 鎸夊抚鍒ょ被鍨?
浜庢槸杩欎竴澶勭殑甯т笌瀹為檯鎸囦护涓嶇銆傜户缁湪杩欑"浠庢棦鏈夊簭鍒椾腑闂存彃鎸囦护"鐨勫満鏅噷璺熸爤鍥捐緝鍔?鏀剁泭寰堜綆銆?

**鍐冲畾:鏀规垚"鏁存閲嶅啓杩欎竴涓?lambda 浣?**,鑰屼笉鏄彃鍏?鈥斺€?杩欐潯 lambda 鏄嚜鍖呭惈鐨勩€佹病鏈夊壇浣滅敤鍙涪,
鎵€浠ユ浛鎹㈡槸瀹夊叏鐨?涓?`toFile` 閭ｆ涓嶅悓,閭ｆ鏇挎崲涓㈡帀浜嗛潤鎬佸瓧娈佃祴鍊?:

```java
private static JarMetadata lambda$1(SecureJar jar) {
    return new SimpleJarMetadata("net.optifine", null, new SetSupplier(jar.getPackages()), new ArrayList<>());
}
```

鐢?ASM 浠庡ご鍐欒繖娈?绾?15 鏉℃寚浠?,涓嶅啀瑙︾鏃㈡湁鏍?`COMPUTE_FRAMES` 鍦ㄨ繖绉嶅皬鍨嬬洿绾夸唬鐮佷笂涓嶄細閬囧埌
鍏叡鐖剁被鍒ゅ畾闂銆傝繖鏍?1.20.2 杩欎竴澶勫氨鑳借繃,涓斾笌"瀹氱偣鎻掑叆"鐨勬暀璁竴鑷?**璇ユ暣娈甸噸鍐欐椂鏁存閲嶅啓,璇ユ彃鍏ユ椂鎵嶆彃鍏?*銆?

**淇ソ浜?SecureJarHandler 閭ｄ竴澶勫凡閫氳繃(鍚屼竴鏅?椤烘墜绾犳浜嗕竴涓竴鐩存悶閿欑殑鎿嶄綔鏁伴『搴?**

鏁存閲嶅啓涔嬪悗閿欒娌″彉,浜庢槸鍘荤湅 verifier 鎵撳嚭鏉ョ殑**甯?*,鎵嶅彂鐜版垜涓€鐩存妸 `invokespecial` 鐨勬搷浣滄暟椤哄簭寮勫弽浜?

```
bci: @17
stack: { uninitialized 0, uninitialized 0, 'java/lang/String', null,
         uninitialized 13, 'java/util/Set', uninitialized 13 }
Reason: Type uninitialized 13 (current frame, stack[6]) is not assignable to 'java/util/Set'
```

`invokespecial` 鏄?*鍙傛暟鍦ㄦ爤椤?*銆乷bjectref 鍦ㄥ叾涓嬫柟(涓嶆槸"鎺ユ敹鑰呭湪鏈€涓婇潰"),鎵€浠ユ垜閭ｄ釜
`new; dup_x1` 鎭板ソ鎶?helper 鏀惧埌浜嗛《涓?鈥斺€?鎶ラ敊璇寸殑灏辨槸杩欎釜銆傛棦鐒堕泦鍚堝凡缁忓湪鏍堜笂,鏈€鐪佷簨鐨勫仛娉曟槸
**鐢ㄤ竴涓潤鎬佸伐鍘?*浠ｆ浛鏋勯€犲櫒:`SetSupplier.of(Set)` 杩斿洖 `Supplier`,浜庢槸鏁存灏卞彉鎴愪竴鏉?
`invokestatic (Ljava/util/Set;)Ljava/util/function/Supplier;`,涓嶉渶瑕佹墜宸ユ憜鏍?鉁撱€?

缁撴灉:**`SimpleJarMetadata` 閭ｆ潯閿欒褰诲簳娑堝け**(stdout 閲?0 澶?鉁?1.20.2 宸茬粡璺ㄨ繃 OptiFine 鐨?
transformation service 鍔犺浇,澶辫触鐐规崲鎴愪簡涓嬩竴涓幆澧冨樊寮?

```
java.lang.NoClassDefFoundError: com/mojang/authlib/minecraft/TelemetryPropertyContainer
```

**涓嬩竴姝?1.20.2)**:鍙堟槸"搴撲唬闄呭樊寮?,杩欐鏄?**authlib** 鈥斺€?OptiFine 鐨?1.20.2 preview 寮曠敤浜嗕竴涓?
20.2.88 profile 閲岄偅浠?authlib 娌℃湁鐨勭被銆傚€欓€夊仛娉?鈶犲湪鍚姩鑴氭湰鏋勯€犵殑 classpath 閲屾妸**鏇存柊鐗?authlib**
鏀惧湪鍓嶉潰(鍚姩鑴氭湰鏈潵灏卞湪鎷?classpath 鉁?杩欐槸鏈€骞插噣鐨勪竴鏉?;鈶″儚 Forge API 閭ｆ牱涓?`com.mojang.authlib.*`
鍋氬３ 鈥斺€?浣嗚灏忓績涓庣湡瀹?authlib 妯″潡鐨勫寘鍐茬獊,**涓嶆帹鑽愬厛鍋氳繖鏉?*銆?

**authlib 琛ラ綈,1.20.2 宸茬粡杩涙爣棰樼晫闈㈠苟甯︾潃 OptiFine 璺戣捣鏉?鍚屼竴鏅?**

閲忓嚭鏉ョ殑浜嬪疄鏄?鍘熺増 1.20.2 profile 瑕?**authlib 5.0.47**,鑰岃繖浠?*鏍规湰娌¤**(鏈湴鍙湁 1.5.25 / 3.18.38 /
4.0.43 / 6.x / 7.0.61)鉁?鈥斺€?鍚姩鑴氭湰鎷?classpath 鏃跺缂哄け鐨勫簱鏄烦杩囩殑,浜庢槸 OptiFine 涓€鐢ㄥ埌 authlib 鐨勭被灏?
`NoClassDefFoundError`銆備粠 `libraries.minecraft.net` 鍙栧洖 5.0.47(111,087 瀛楄妭)涔嬪悗:

| 鎸囨爣 | 1.20.2(鏈) |
|---|---|
| `Setting user`(杩涙爣棰樼晫闈? | 鉁?|
| `Sound engine started` | 鉁?|
| `[Shaders] OpenGL` | 鉁?|
| `[OptiFine]` 鏃ュ織 | **229 琛?* |
| 鏇挎崲绫?| 238 涓?|
| 杩愯鏃堕暱 | 浠?5 绉掑彉鎴?**20 绉?* |

涔熷氨鏄?*杩欎竴琛屽凡缁忓甫鐫€ OptiFine 璺戝埌鏍囬鐣岄潰**,闅忓悗鍦ㄤ竴娆?*鍙嶅皠**閲屽け璐?鏂?crash report,
`00:08:18`):

```
java.lang.NoClassDefFoundError: net/minecraft/world/level/block/state/BlockState
	at java.base/java.lang.Class.getDeclaredMethods0(Native Method)
	at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
	at net.optifine.reflect.ReflectorResolver.resolve(ReflectorResolver.java:45)
```

**涓嬩竴姝?1.20.2)**:杩欐潯 `NoClassDefFoundError` 鍑虹幇鍦?OptiFine 鐨?`Reflector` 瑙ｆ瀽闃舵,鑰?`BlockState`
鏄?*娓告垙绫?*(涓嶆槸搴?鈥斺€?鍏堥噺娓呮瀹冨埌搴曚负浠€涔堝姞杞戒笉浜?鏄ā鍧楄鍙栭棶棰?杩樻槸鎴戜滑鎹㈣繘鍘荤殑鏌愪釜绫绘妸
`BlockState` 鐨勫姞杞借矾寰勫紕鍧忎簡);杩欎笌 1.20.4 鏃╁厛閭ｆ鍙嶅皠璺緞閲岀殑 `ClassNotFoundException: ItemStack` 鏄悓涓€绫荤幇璞?
鍙互瀵圭収閭ｆ鐨勫鐞嗘柟寮忋€?

**1.20.2 鐨勫け璐ョ偣鍏跺疄鏄?妗ュ湪 1.20.2 涓婃病閰嶄笂瀵?,鑰屼笖瀹冩槸闈欓粯鐨?鍚屼竴鏅?**

鐪熸鐨勬渶鏂?crash report(`00:08:18`)涓嶆槸 BlockState,鑰屾槸:

```
java.lang.NoSuchMethodError:
  'java.util.Optional net.minecraft.client.particle.ParticleEngine$1ParticleDefinition.f_243741_()'
	at net.minecraft.client.particle.ParticleEngine.lambda$reload$8(ParticleEngine.java:286)
```

璋冪敤鏂硅鐨勬槸 **杩愯鏃堕偅涓悕瀛?*(`$1ParticleDefinition`)銆佹柟娉曞悕鏄?**SRG 褰㈢姸**(`f_243741_`)鈥斺€?涔熷氨鏄
**琚杞界殑鏄繍琛屾椂鑷繁閭ｄ唤**(瀹樻柟鎴愬憳鍚?鑷劧娌℃湁 `f_243741_`),鑰屼笉鏄垜浠寜杩愯鏃跺悕瀛?ship 鐨勯偅浠姐€?
鍘讳骇鐗╅噷鏍稿疄,鍘熷洜涓€鐩簡鐒?

```
浜х墿鏉＄洰: optifineoforge/patched/net/minecraft/client/particle/ParticleEngine$ParticleDefinition.class
绱㈠紩閲? : net/minecraft/client/particle/ParticleEngine$ParticleDefinition
```

**妗ュ湪 1.20.2 涓婃病鏈夌粰杩欎釜绫婚厤涓婂**(浠嶇劧鏄浇鑽疯嚜宸辩殑鍚嶅瓧),鎵€浠ユ惉杩愭椂涔熸病鏀瑰悕銆佺储寮曢噷涔熸病鏈夎繍琛屾椂閭ｄ釜鍚嶅瓧,
浜庢槸杩愯鏃堕偅浠借瑁呬簡杩涘幓銆傝€?`NestedNameBridge` 鐨?*鍒ゆ嵁鏄粨鏋勫畬鍏ㄧ浉鍚?*(鐖剁被 + 瀛楁鎻忚堪绗﹂泦鍚?+ 鏂规硶鎻忚堪绗﹂泦鍚?:
1.20.2 涓婅繖涓や唤 `ParticleDefinition` 鐨勬垚鍛橀泦鍚堝苟涓嶅畬鍏ㄧ浉鍚?NeoForge 閭ｄ晶鏈夎ˉ涓?,浜庢槸"鎭板ソ涓€涓€欓€?鐨勬潯浠朵笉鎴愮珛,
**瀹冧粈涔堜篃娌¤灏辨斁寮冧簡** 鈥斺€?杩欐槸鏈€鍊煎緱鏀圭殑涓€鐐?閰嶅澶辫触蹇呴』**鍑哄０**銆?

**涓嬩竴姝?1.20.2,涓ゅ涓€璧锋敼)**:
1. **璁╂湭閰嶅鍙**:鍒楀嚭"杞借嵎鏈?`$`銆佽繍琛屾椂娌℃湁鍚屽悕绫汇€佷絾缁撴瀯涔熸病閰嶄笂"鐨勭被(浠ュ強鍊欓€変笌宸紓),鍚﹀垯杩欑被
   闈欓粯鏀惧純浠ュ悗杩樹細浠ュ畬鍏ㄤ笉鐩稿共鐨勬姤閿欏舰寮忓嚭鐜?
2. **鏀惧鍒ゆ嵁浣嗚浠嶇劧鍙垽瀹?*:璁板綍绫?record)鐨勪袱浠芥嫹璐濇垚鍛樺悕鍙互涓嶅悓銆佹弿杩扮搴斿綋鐩稿悓,鎵€浠ュ record
   鍏佽鐢?鐖剁被鐩稿悓 + **瀛楁鎻忚堪绗﹂泦鍚?*鐩稿悓"鏉ラ厤瀵?鐪熸鏃犳硶鍒ゅ畾鏃跺畞鍙姤姝т箟,涔熶笉瑕佺寽銆?

**鏀惧 + 鍑哄０閮藉仛浜?浜庢槸鐪嬪埌浜嗙湡姝ｇ殑涓ゅ闂(鍚屼竴鏅?涓婁竴娈电殑鎺ㄦ柇琚嚜宸辩殑娴嬮噺鎺ㄧ炕)**

鍏堢籂姝ｄ笂涓€娈电殑涓€涓敊鍒?浜х墿閲岄偅涓?*鏉＄洰鍚?*浠嶆槸 `鈥?ParticleEngine$ParticleDefinition.class`,浣?javap 鎵撳嚭鏉?
**绫诲唴閮ㄧ殑鑷繁鐨勫悕瀛楀凡缁忚鏀规垚杩愯鏃跺悕瀛?*:

```
payload: final class net.minecraft.client.particle.ParticleEngine$1ParticleDefinition extends java.lang.Record
payload: public net.minecraft.resources.ResourceLocation f_244103_();
payload: public java.util.Optional<鈥? f_243741_();
client-1.20.2-鈥?srg.jar : net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class
neoforge-20.2.88-client.jar: net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class
```

涔熷氨鏄**妗ュ叾瀹為厤涓婁簡銆佸悕瀛椾篃鏀逛簡**,鎴戝嵈鍙湅浜嗘潯鐩悕灏变笅浜?娌￠厤涓?鐨勭粨璁?鈥斺€?杩欐鏄?瑕侀噺鍒扮偣涓?鐨勫張涓€渚嬨€?
鐪熸鐨勯棶棰樻槸**涓ゅ**:

1. **鎼繍鍙敼浜嗙被銆佹病鏀规潯鐩矾寰?*:`--rewrite` 鎶婄被鍐呴儴鍚嶅瓧鏀规垚 `$1ParticleDefinition` 浜?浣嗘惉杩愭楠や粛鎸?
   **鏀瑰啓鍓嶇殑鏉＄洰鍚?*鐢熸垚 `optifineoforge/patched/鈥?ParticleDefinition.class`,绱㈠紩閲屼篃鏄棫鍚?鉁椼€?
   ML 鏄?*鎸夋潯鐩矾寰?*鎵剧被鐨?瀹冭鐨勬槸 `鈥?1ParticleDefinition.class` 鈫?鎵句笉鍒?鈫?浜庢槸**杩愯鏃惰嚜宸遍偅浠?*琚浜嗚繘鍘?鉁椼€?
   (淇硶:鎼繍鐢ㄦ敼鍐欏悗 jar 鐨?*鏉＄洰鍚?*;绱㈠紩鍚岀悊 鈥斺€?杩欐潯瑕佽繛鐫€ `--rewrite` 鐨勪骇鐗╀竴璧峰銆?
2. **杞借嵎閲岄偅涓?record 鐨勮闂櫒杩樻槸 SRG 鍚?*(`f_243741_()`銆乣f_244103_()` 鉁?,鑰岃繍琛屾椂閭ｄ唤鏄畼鏂瑰悕 鉁?鈥斺€?
   杩欐鏄?`SrgRemap` 鍦?1.20.2 涓?80 鏉℃湭瑙ｆ瀽"閲岀殑涓€閮ㄥ垎:**record 鐨勮闂櫒涓庡畠鐨勭粍浠跺瓧娈靛悓鍚?*,
   鑰屾槧灏勮〃閲屽彧鏈夊瓧娈甸偅鏉?鉁椼€備慨娉?鏀瑰悕鐨勮〃閲?瀵?鏂规硶鍚嶄笌鍚岀被瀛楁鍚嶇浉鍚?鐨勬儏褰?*娌跨敤璇ュ瓧娈电殑鏄犲皠**
   (record 鐨勭粨鏋勪繚璇佽繖涓€鐐?,杩欐牱璁块棶鍣ㄥ氨浼氳鏀规垚瀹樻柟鍚?鉁撱€?

**涓嬩竴姝?1.20.2)**:鍏堟敼鈶?鏉＄洰鍚嶄笌绱㈠紩),鍥犱负瀹冨喅瀹?鍒板簳瑁呯殑鏄摢涓€浠?;鍐嶆敼鈶?record 璁块棶鍣ㄦ部鐢ㄥ瓧娈垫槧灏?,
涓よ€呴兘鍋氬畬鍐嶅惎鍔ㄤ竴娆°€傛敞鎰忊憼鈶′簰涓嶆浛浠?鍗充娇瑁呭浜嗛偅涓€浠?璁块棶鍣ㄤ粛鏄?SRG 涔熶細涓庤繍琛屾椂鐨勫畼鏂瑰悕瀵逛笉涓娿€?

**鈶犵殑涓€琛屾牴鍥犳壘鍒颁簡,1.20.2 鎵撻€?鈥斺€?1.20.x 鍥涜鍏ㄨ鐩?鍚屼竴鏅?**

閲忓埌鐐逛笂涔嬪悗,鏍瑰洜鏄竴琛屼唬鐮?鏀瑰啓宸ュ叿鍦?*鏌ラ厤瀵硅〃鏃剁敤浜嗗甫 `srg/` 鍓嶇紑鐨勫悕瀛?*,鑰岄厤瀵硅〃鐨勯敭鏄?*涓嶅甫鍓嶇紑鐨勭被鍚?*
鉁?鈥斺€?浜庢槸**瀛楄妭閲岀殑甯搁噺姹犺鏀逛簡(閭ｉ噷鐢ㄧ殑鏄８绫诲悕)銆佹潯鐩矾寰勫嵈娌℃敼** 鉁椻湕銆備骇鐗╅噷鍥犳鍚屾椂鍑虹幇:

```
entry srg/net/minecraft/client/particle/ParticleEngine$ParticleDefinition   鈫?鏃у悕(鏉＄洰)
pool  鈥articleEngine$1ParticleDefinition                                    鈫?鏂板悕(绫昏嚜宸?
```

ML 鎸?*鏉＄洰璺緞**鎵剧被,鑷劧鍙壘鍒拌繍琛屾椂閭ｄ竴浠姐€備慨娉曞氨鏄煡琛ㄥ墠鍏堝墺鎺?`srg/` 鍓嶇紑銆佸啓鍥炴椂鍐嶅姞鍥炲幓 鉁撱€?
淇畬鍚庝骇鐗╂潯鐩彉鎴?`optifineoforge/patched/net/minecraft/client/particle/ParticleEngine$1ParticleDefinition.class` 鉁?
鍚姩缁欏嚭:

| 鎸囨爣 | 1.20.2(鏈) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** |
| `Setting user`(鏍囬鐣岄潰) | 鉁?|
| `[Shaders] OpenGL` | 鉁?|
| `Connected textures` | 2 琛?鉁?|
| `Pre-stitch`(OptiFine 鎷煎浘闆? | 13 琛?鉁?|
| `[OptiFine]` 鏃ュ織 | **239 琛?* 鉁?|
| 鏇挎崲绫?| 246 涓?|
| 鏂?crash report / 鎴浘 | **鏃?* 鉁?/ 916 KB 鉁?|

**1.20.x 杩欎竴鏉″垎鏀埌姝ゅ洓琛屽叏瑕嗙洊**:1.20.1銆?.20.2銆?.20.4銆?.20.6 鈥斺€?姣忚閮藉甫鐫€ OptiFine 璺戣捣鏉?
(shaders + CTM + 鍥鹃泦),鑰屼笖鍚勮嚜鐢ㄤ簡涓嶅悓鐨勮矾绾?1.20.6 鏃犺ˉ涓佹爲 + ship;1.20.4 SRG鈫掑畼鏂瑰悕閲嶆槧灏?
1.20.1 鍘熸牱 SRG + 绫诲悕瀵归綈;1.20.2 = 1.20.4 鐨勮矾绾?+ SecureJarHandler 璋冪敤淇 + 绫诲悕瀵归綈)銆?

**閬楃暀灏忛」(涓嬩竴杞『鎵嬬湅)**:1.20.2 杩欐 `stderr.log` 鏄?14,625 瀛楄妭(1.20.1 閭ｆ鍙湁 27 瀛楄妭),
铏界劧 verdict 鏄?STARTED 涓旀棤鏂?crash report,浣嗗€煎緱璇讳竴閬嶇‘璁ら噷闈㈠彧鏄壇鎬ц鍛娿€?

**杞悜 1.21.x:rig 椹卞姩涓嶅姩閭ｄ竴琛?鍘熷洜鏄?涓よ竟鐨勫伐鍏烽泦浠ｅ樊"(鍚屼竴鏅?宸查噺鍒板叿浣撶被鍚?**

寤哄ソ `1.21.x` 鐨?worktree(鍒嗘敮 21db5b2)銆佺‘璁?21.4.149 鐢ㄧ殑鏄?**ModLauncher 11.0.4 + FML 6.0.18**銆?
娓告垙 jar 鏄畼鏂瑰悕涔嬪悗,鐢ㄧ幇鍦ㄧ殑 rig 鍘诲缓 1.21.4 浼氬湪**鎵撴々閭ｄ竴姝?*澶辫触,鎶ョ殑鏄笁涓?绫绘壘涓嶅埌":

```
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.NestedFamilyGuard
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.NestedNameBridge
java.lang.ClassNotFoundException: kynarain.cn.optifineoforge.optifine.MissingTargets
```

鍘熷洜寰堟竻妤?`1.21.x` 鍒嗘敮鐨?`optifine` 宸ュ叿闆嗗彧鏈?

```
DonorVerifier  ForgeApiShims  MemberRestorePlan  OptifineConfig
OptifineJar    OptifineJarFixer  OptifinePipeline
```

鈥斺€?*娌℃湁** `MissingTargets`(鎵撴々)銆?*娌℃湁** `SrgRemap`(鏀瑰悕)銆佷篃娌℃湁杩欎竴杞柊鍐欑殑 `NestedFamilyGuard` /
`NestedNameBridge`銆傝繖浜涢兘鏄繖鍑犲崄杞湪 **1.20.x** 鍒嗘敮涓婇暱鍑烘潵鐨?鑰屼袱鏉″垎鏀寜椤圭洰绾緥鏄?*浜掔浉鐙珛**鐨?
(涓嶅仛璺ㄥ垎鏀悎骞?,鎵€浠?鎷?1.20.x 鐨?rig 鍘婚┍鍔?1.21.x"蹇呯劧缂轰欢銆?

**涓ゆ潯鍙€夎矾绾?涓嬩竴杞嫨涓€)**:
1. **鎸夎闄嶇骇**:缁?rig 鍔犱竴涓?宸ュ叿闆?寮€鍏?椹卞姩 1.21.x 鏃惰烦杩?3b1(瀹舵棌)/3b4(妗?/3b2(鎵撴々)鈥斺€?
   1.21.4 褰撳垵**灏辨槸鍦ㄨ繖浜涘伐鍏峰瓨鍦ㄤ箣鍓?*璺戦€氱殑(閭ｆ椂鐨勪骇鐗╀繚鐣欎簡琛ヤ竵鏁版嵁 鉁?,鎵€浠ヨ繖鏉¤矾鏄?*鏈夊厛渚嬨€佹湁鎶婃彙**鐨?
2. **鎶婃垚鐔熷伐鍏烽泦绉绘鍒?1.21.x 鍒嗘敮**(鎷疯礉骞舵寜璇ヨ鐨勫懡鍚嶇┖闂磋皟鏁?鈥斺€旀洿褰诲簳,浣嗗伐浣滈噺澶?鑰屼笖瑕佸厛纭閭ｄ竴琛?
   鍒板簳闇€涓嶉渶瑕?1.21.x 鐨勮浇鑽峰凡鏄畼鏂瑰悕 鉁?鏈繀闇€瑕?SRG 鏀瑰悕閭ｄ竴濂?銆?

鍏堣蛋 1 鎶?1.21.4 閲嶆柊璺戦€氭嬁鍥炲熀绾?鍐嶅喅瀹氱Щ妞嶅摢浜涘伐鍏峰埌 1.21.x銆?

**璺嚎 1 鍋氬畬:1.21.4 鎷垮洖鍩虹嚎(鍚屼竴鏅?**

缁?rig 鍔犱簡 `-NoNameBridge`(瀹舵棌閭ｄ竴姝ユ湰鏉ュ氨鏈?`-NoFamilyGuard`,鎵撴々閭ｄ竴姝ュ凡鏈?`-StubMissing $false`),
`build-line.ps1` 鐨?`1214` 鏉＄洰灏辨樉寮忓０鏄?杩欎竴琛岀敤鑷繁閭ｅ宸ュ叿":`StubMissing = $false`銆?
`NoFamilyGuard = $true`銆乣NoNameBridge = $true` 鈥斺€?浜庢槸涓嶅啀璋冪敤閭ｆ潯鍒嗘敮娌℃湁鐨勪笁涓伐鍏?鉁撱€?
(杩囩▼涓洜涓洪噸澶嶉敭 `StubMissing` 鎶ヤ簡涓€娆?PowerShell 鐨?`DuplicateKeyInHashLiteral`,鏄紪杈戝け璇?宸蹭慨銆?

1.21.4 瀹炴祴(涓庡綋鍒濋偅鏉″熀绾夸竴鑷?鑰屼笖鏇村共鍑€):

| 鎸囨爣 | 1.21.4(鏈) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** |
| `Setting user`(鏍囬鐣岄潰) | 鉁?|
| `[Shaders] OpenGL` | 鉁?|
| `Connected textures` | 3 琛?鉁?|
| `Pre-stitch` | 14 琛?鉁?|
| `[OptiFine]` 鏃ュ織 | **232 琛?* 鉁?|
| `Replaced net` | **0**(杩欎竴琛屾湰鏉ュ氨鏄 OptiFine 鑷繁鐨?transformer 鎵撹ˉ涓?鉁?|
| **stderr** | **0 瀛楄妭** 鉁?|
| 鏂?crash report / 鎴浘 | **鏃?* 鉁?/ 1.15 MB 鉁?|

**鍒版鍓嶄簲琛屽凡瀹炴満楠岃瘉**:1.20.1銆?.20.2銆?.20.4銆?.20.6(1.20.x 鍒嗘敮)+ 1.21.4(1.21.x 鍒嗘敮)銆?
涓嬩竴姝ユ槸 1.21.x 鍒嗘敮涓婄殑鍏朵綑琛?1.21 / 1.21.1 / 1.21.3 / 1.21.6鈥?.21.11):瀹冧滑鐨?OptiFine jar 鍜?
瀵瑰簲鐨?NeoForge 閮借鎸?`fetch-optifine.ps1` 鐨勫姙娉曞幓鍙?瀹夎,鐒跺悗澶嶇敤 `1214` 杩欏鍙傛暟(閭ｆ潯鍒嗘敮鐨勫伐鍏烽泦銆?
ModLauncher 11銆佸畼鏂瑰悕杞借嵎)銆?

**1.21.1 鐨勫噯澶囧害(鍚屼竴鏅?涓€鍗婂埌浣?**

- **鍘熺増 1.21.1 瀹㈡埛绔?jar 鉁?*:浠庨暅鍍忓彇鐗堟湰 json 鍐嶆寜 `downloads.client.url` 浠?Mojang 鐨?piston-data 涓?
  寰楀埌 26,836,906 瀛楄妭(`versions\1.21.1\1.21.1.jar`)銆?
- **NeoForge 21.1.x 涓€鍏?243 涓瀯寤?鏈€鏂?21.1.250** 鉁?installer 涔熶笅涓嬫潵浜?浣?*瑁呭嚭鏉ョ殑涓滆タ涓嶅畬鏁?*:

```
client jar present: False
profile present: True
libraries\net\neoforged\neoforge\21.1.250\  鈫?绌?
```

涔熷氨鏄 profile 鐩綍寤轰簡銆乣neoforge-21.1.250-client.jar` 娌℃湁,鑰屼笖 `libraries` 涓嬪搴旂洰褰曟槸**绌虹殑** 鈥斺€?
杩欎笌 20.6.141 閭ｆ"瀹夎鍣ㄦ彁鍓嶅け璐?鏄悓涓€褰㈡€併€?*涓嬩竴姝?*:鍍?20.6.141 閭ｆ牱鎶?installer 鐨勮緭鍑?*瀹屾暣**璁板埌鏂囦欢
鍐嶈(杩欎竴杞剼鏈彧鎶婅緭鍑鸿拷鍔犺繘鏃ュ織銆佷絾鏃ュ織閲屾病鏈夊畠鏈熷緟鐨勬姤閿?鎵€浠ヨ鍏堟妸 stdout/stderr 閮藉瓨涓嬫潵),
鎸夊畠鎶ョ殑鍧愭爣閫愪釜鎵嬪伐鍙栧洖,鍐嶉噸璺戝畨瑁呫€?

OptiFine 渚?1.21.1 鐨勬寮忕増 jar(`OptiFine_1.21.1_HD_U_J1.jar`)鏈湴宸叉湁 鉁撱€?

**1.21.1 瑁呭ソ浜嗐€佽兘鍚姩,浣?OptiFine 娌＄敓鏁?鈥斺€?鍒嗚宸紓鐨勬暀绉戜功渚嬪瓙(鍚屼竴鏅?**

瑁呭ソ涔嬪悗(NeoForge 21.1.250,client jar 5,675,624 瀛楄妭;椤哄甫璁颁竴涓潙:installer 杩炴姤涓ゆ
`SocketTimeoutException`,渚濇鎵嬪伐鍙栧洖 `neoforge-21.1.250-universal.jar` 涓?
`net.neoforged:neoform:1.21.1-20240808.144430@zip` 鍚庢墠瑁呬笂 鉁?,杩欎竴琛?

| 鎸囨爣 | 1.21.1 |
|---|---|
| `VERDICT` | `STARTED (40s, marker: Sound engine started)` |
| `Setting user` | 鉁?|
| `[Shaders] OpenGL` / `Connected textures` / `Pre-stitch` | **0 / 0 / 0** 鉁?|
| `[OptiFine]` 鏃ュ織 | **0 琛?* 鉁?|
| stderr | **1,388,996 瀛楄妭 / 12717 琛?* 鉁?|

stderr 鐨勫ご閮ㄥ氨鏄師鍥?

```
java.io.IOException: Base resource not found: akr.class
	at LAYER SERVICE/optifine/optifine.Patcher.applyPatch(Patcher.java:148)
```

**`akr.class` 鏄贩娣嗗悕** 鈥斺€?涔熷氨鏄 **1.21.1 鐨?OptiFine 杞借嵎鏄寜 SRG/娣锋穯鍩哄悕鎵撹ˉ涓佺殑**,鑰岃繖涓€琛岀殑杩愯鏃?
浜ょ粰瀹冪殑鏄畼鏂瑰悕,浜庢槸瀹冧竴涓ˉ涓侀兘璐翠笉涓?涓?1.20.2 / 1.20.4 褰撳垵鍚屼竴涓梾)銆?.21.4 涔嬫墍浠ユ病浜?鏄洜涓?
瀹冮偅涓€鐗?OptiFine 涓庤繍琛屾椂鐨勫懡鍚?*姝ｅソ瀵瑰緱涓?*(stderr 0 瀛楄妭 鉁?銆?

**缁撹(涓嬩竴杞殑鏂瑰悜宸茬‘瀹?**:1.21.1 杩欎竴琛岄渶瑕?1.20.2/1.20.4 鐢ㄨ繃鐨?*绂荤嚎杞借嵎璺嚎** 鈥斺€?鍗?
`ShipPayload` + `SrgRemap`(SRG鈫掑畼鏂瑰悕)+ `MissingTargets`(鎵撴々)鈥斺€?鑰岃繖浜涘伐鍏?*閮戒笉鍦?1.21.x 鍒嗘敮涓?*銆?
鎵€浠ヨ矾绾?2 涓嶅啀鏄?鍙€?:**瑕佹妸鎴愮啛宸ュ叿闆嗙Щ妞嶅埌 1.21.x 鍒嗘敮**(`SrgRemap`銆乣MissingTargets`銆?
`NestedFamilyGuard`銆乣NestedNameBridge`,浠ュ強涓庤鍒嗘敮鍛藉悕绌洪棿鐩稿簲鐨勬槧灏勬枃浠?,鍚屾椂淇濈暀"璇ヨ鐢ㄨ嚜宸卞伐鍏烽泦"
鐨勫紑鍏?鍥犱负 1.21.4 鍙渶瑕佸叾涓殑涓€閮ㄥ垎銆?

**绉绘宸插畬鎴?鍚屼竴鏅?鎻愪氦鍦?1.21.x 鍒嗘敮涓?**

鎶婅繖涓€鏀己鐨勫叓涓枃浠跺甫杩囧幓浜?鎻愪氦 `e8b6de6`,璇ュ垎鏀?21db5b2 鈫?e8b6de6):

```
SrgMemberMap  SrgRemap       SRG鈫掑畼鏂瑰悕鎴愬憳琛ㄤ笌鏀瑰啓鍣?
MissingTargets               鎵撴々
NestedFamilyGuard            缂栧彿瀹舵棌(涓嶆槸鍚屼竴涓被灏变笉鎹?
NestedNameBridge             涓や釜浜х墿鎷煎啓涓嶅悓鐨勫祵濂楀悕
OptifineJarFixer/OptifineJar union 璺緞涓?SecureJarHandler 涓ゅ淇 + 鎸夊悕鍒嗘淳
loader/SetSupplier           SecureJarHandler 淇閲岃浼犵殑閭ｄ釜瀵硅薄
```

鍦ㄩ偅鏉″垎鏀笂鐢?ASM 缂栬瘧閫氳繃(鍙湁涓€鏉?deprecation 鎻愮ず)鉁撱€?*娉ㄦ剰 `OptifineJar` 涓?`OptifineJarFixer`
鏄"瑕嗙洊"鑰屼笉鏄?鏂板"鐨?* 鈥斺€?涔熷氨鏄 **1.21.4 閭ｆ潯宸查獙璇佺殑绾垮繀椤婚噸璺戜竴娆″洖褰?*(瀹冨綋鍒濇槸鐢ㄨ鍒嗘敮鑷繁閭ｇ増
杩欎袱涓被璺戦€氱殑),纭绉绘娌℃湁鏀瑰彉瀹冪殑琛屼负;杩欐槸涓嬩竴杞殑绗竴浠朵簨銆?

**1.21.1 缁仛娓呭崟**:鈶犲彇 `mcp_config-1.21.1.zip` 骞惰В鍑?`joined.tsrg`(1.20.x 渚у凡鏈?`fetch-mcp.ps1` 鉁?;
鈶1211` 鏉＄洰鏀规垚绂荤嚎璺嚎(`ShipPayload = $true`銆乣SrgRemap = $true` + 涓や釜鏄犲皠鏂囦欢銆乣StubMissing = $true`銆?
`NoFamilyGuard`/`NoNameBridge` 鍏虫帀);鈶uild+launch,鎸?1.20.2 鐨勯獙鏀跺彛寰勫彇璇併€?

**鈶?鈶?鍋氬畬鍚庣殑瀹炴祴:琛ヤ竵鍣ㄤ笉鍙簡,浣?娌′汉鎹㈢被"(鍚屼竴鏅?**

- **1.21.4 绉绘鍥炲綊 鉁?*:閲嶈窇涓€娆′粛鏄?`STARTED (40s)`銆乣Setting user` 鉁撱€?*232 琛?`[OptiFine]`** 鉁撱€?
  `Pre-stitch` 脳14 鉁撱€?*stderr 0 瀛楄妭** 鉁?鈥斺€?涓庣Щ妞嶅墠鐨勫熀绾垮畬鍏ㄤ竴鑷?璇存槑瑕嗙洊閭ｄ袱涓被娌℃湁鍓綔鐢?鉁撱€?
- `mcp1211-joined.tsrg` 宸茶В鍑?6,485,971 瀛楄妭)鉁?`1211` 鏉＄洰宸插垏鍒扮绾胯矾绾?鉁撱€傛瀯寤虹粨鏋?

```
rewrote 0 method and 0 field names          鈫?杩欎竴琛岀殑杞借嵎鏈潵灏辨槸瀹樻柟鍚?鏀瑰悕鏄┖杞?姝ｅ父)
scanned 1128 classes, 114 missing           鈫?鎵撴々璺戦€?
payload: 1128 classes, 420 moved to optifineoforge/patched
compared 230 replaced classes, 102 members to restore
VERDICT: STARTED (40s, marker: Sound engine started)
stderr: 0 瀛楄妭                              鈫?1,388,996 鈫?0,琛ヤ竵鍣ㄩ偅 12717 鏉″交搴曟秷澶?鉁?
```

浣嗗悓涓€浠芥棩蹇楅噷:

| 鎸囨爣 | 1.21.1(绂荤嚎璺嚎) |
|---|---|
| `Replaced net` | **0** 鉁?|
| `[OptiFine]` / `Shaders` / `CTM` / `Pre-stitch` | **0 / 0 / 0 / 0** 鉁?|

涔熷氨鏄**杞借嵎 ship 杩涘幓浜?鍗存病鏈変换浣曚笢瑗挎妸瀹冭涓婂幓** 鈥斺€?鍘熷洜寰堢洿鎺?**1.21.x 鍒嗘敮娌℃湁"鎹㈢被"鐨?transformer**
(`PatchedClassTransformer` 鏄?1.20.x 鍒嗘敮涓婄殑涓滆タ;1.21.x 閭ｄ竴鏀綋鍒濋潬 OptiFine **鑷繁**鐨?transformer 鎹㈢被,
鎵€浠ュ彧鏈?淇?bug 鐨?transformer",娌℃湁"鎹㈢被鐨?transformer")鉁椼€?

**涓嬩竴姝?1.21.1)**:鎶?`PatchedClassTransformer` 涔熺Щ妞嶈繃鍘?瀹冭璇?`patched-index.txt` / `stubs.txt` /
`keep-runtime.txt` 鉁?杩欎笁涓枃浠舵惉杩愭楠ゅ凡缁忎骇鍑?鉁?,骞跺湪璇ュ垎鏀殑 service 閲屾敞鍐?椤哄簭浠嶆槸"鎹㈢被鍦ㄥ墠銆?
鎴愬憳鍥炲～鍦ㄥ悗" 鉁?銆傝繖涓€鏀殑 transformer 鏈潵灏辨槸鎸?ModLauncher 11 鍐欑殑,鎵€浠ヤ笉闇€瑕?1.20.x 閭ｅ ml10/ml11 閫傞厤灞傘€?

---

## 1.21.1 鎹㈢被璺戦€?涓や釜鏍瑰洜 + 涓€鏉℃ā鍧楀眰鐨勭‖杈圭晫(2026-09-16 鍑屾櫒)

**鎹㈢被 transformer 绉绘鍚?73 鈫?313 涓被瑁呬笂,浣嗗穿鍦ㄤ袱涓洿鍚庨潰鐨勫湴鏂广€?* 涓や釜鏍瑰洜閮借瀹炴祴瀹氫綅,淇畬涔嬪悗
1.21.1 涓庡凡楠岃瘉鐨?1.21.4 鍒ゆ嵁榻愬钩銆?

### 鏍瑰洜涓€:鎵撴々鎶?`com.mojang.serialization.Codec` 褰撴垚浜?涓嶅瓨鍦ㄧ殑绫?

绗竴鐗堢Щ妞嶅悗宕╁湪 `TargetedConditionalEffect.equipmentDropsCodec`:

```
NullPointerException: Cannot invoke "Codec.fieldOf(String)" because the return value of
  "Codec.validate(Function)" is null
```

涓嶆槸 OptiFine 鐨勯棶棰?鏄?*鎵撴々姝ラ鐨勮緭鍏ヤ笉瀹屾暣**:`MissingTargets` 鐨勮繍琛屾椂鍒楄〃閲屽彧鏈?NeoForge 閭ｅ嚑涓?jar,
娌℃湁 `com.mojang:datafixerupper`,浜庢槸鎵弿鐪嬩笉鍒?`Codec`,鍒ゅ畾"杩愯鏃舵病鏈? 鈫?缁欑湡姝ｇ殑缂栬В鐮佸櫒鎹簡涓?
**杩斿洖 null 鐨勭┖瀹炵幇** 鈫?娓告垙鎭板ソ鍦ㄧ瓑涓€涓湡 Codec 鐨勫湴鏂圭偢銆備慨娉曟槸鎶婅浇鑽风湡姝ｄ細璋冨埌鐨勫簱涔熶氦缁欐壂鎻?

```
RemapRuntime = @( 鈥︹€?, com\mojang\datafixerupper\8.0.16\datafixerupper-8.0.16.jar,
                        com\mojang\brigadier\1.3.10\brigadier-1.3.10.jar )
```

**鏁欒**:鎵撴々鍒楄〃鏄?涓栫晫鏈夊澶?鐨勫畾涔?婕忎竴涓簱涓嶄細鎶ラ敊,鍙細璁╀竴涓笉璇ヨ鎵撴々鐨勭被鍙樻垚 null 宸ュ巶銆?

### 鏍瑰洜浜?杞借嵎鐨?Forge 鐖剁被鍦?NeoForge 杩愯鏃朵笉瀛樺湪 鈥斺€?鎹㈢被浼氱牬鍧忕户鎵垮叧绯?

绗簩涓穿鍦?`AttachmentSync.onChunkSent`,绂绘崲绫诲緢杩?

```
VerifyError: Bad type on operand stack
  Location: net/neoforged/neoforge/attachment/AttachmentSync.onChunkSent(...) @82: invokestatic
  Reason:   Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
            'net/neoforged/neoforge/attachment/AttachmentHolder'
```

瀹炴祴涓よ竟鐨勫０鏄?javap):

| | 鐖剁被 | 鎺ュ彛 |
|---|---|---|
| 杞借嵎(OptiFine 1.21.1,Forge 鐩爣) | `net.minecraftforge.common.capabilities.CapabilityProvider<BlockEntity>` | `net.minecraftforge.common.extensions.IForgeBlockEntity` |
| 杩愯鏃?neoforge-21.1.250-**client**.jar,宸叉墦琛ヤ竵鐨勬父鎴? | `net.neoforged.neoforge.attachment.AttachmentHolder` | `net.neoforged.neoforge.common.extensions.IBlockEntityExtension` |

涓や釜鍏抽敭娴嬮噺:

1. **娓告垙绫绘潵鑷?`neoforge-<鐗堟湰>-client.jar`**,涓嶆槸 `client-<鐗堟湰>-srg.jar` 鈥斺€?鍚庤€呴噷 `BlockEntity extends
   java.lang.Object`(鍘熺増褰㈡€?,鍓嶈€呴噷鎵嶆槸 NeoForge 鏀硅繃鐨勫舰鎬併€傛墍浠?杩愯鏃剁殑鐗堟湰"蹇呴』鍙?client jar銆?
2. **杞借嵎閲?鐖剁被鏄?Forge 绫诲瀷"鐨勭被鍙湁涓€涓?*:`BlockEntity`銆傚叾浣?32 涓彁鍒?`net/minecraftforge` 鐨勬父鎴忕被
   閮藉彧鏄湪 `implements` 閲屾彁鍒版帴鍙?鎺ュ彛鍙互闈?shim 琛ラ綈)銆傚垽鎹槸閫愮被 javap 澹版槑琛?涓嶆槸鐚溿€?

### 璇曢敊璁板綍:shim 鏀圭埗绫昏涓嶉€?妯″潡灞備笉鍏佽

绗竴鐗堜慨娉曟槸"鎶?shim 鐨勭埗绫绘敼鎺?(璁?`CapabilityProvider extends AttachmentHolder`),鏋勫缓渚у凡缁忚兘鑷姩娴嬪嚭
杩欎釜鏄犲皠(姣斿杞借嵎涓庤繍琛屾椂鐨勭埗绫?,浣嗚繍琛屾湡绔嬪埢:

```
NoClassDefFoundError: net/neoforged/neoforge/attachment/AttachmentHolder
  at cpw.mods.cl.ModuleClassLoader.loadFromModule(ModuleClassLoader.java:311)
```

鍘熷洜鍦ㄥ悓涓€浠芥棩蹇楃殑妯″潡鍥鹃噷:

```
module graph: our module is optifine, layer manager captured
module graph: GAME layer holds srg mixinextras.neoforge mixin_synthetic minecraft neoforge
module graph: optifine -> minecraft: reads it = false, 鈥︹€?exported to it = true
```

**鎴戞柟 jar(`optifine` 妯″潡)鍦?GAME 灞備箣涓?妯″潡鍙兘璇诲畠涓嬮潰鐨勫眰** 鈥斺€?鎵€浠?shim 寮曠敤娓告垙灞傜殑
`AttachmentHolder` 瑙ｆ瀽涓嶄簡;鍙嶈繃鏉?GAME 灞傜殑娓告垙绫诲紩鐢ㄦ垜鏂?shim **鏄€氱殑**(鍓嶉潰鑳芥崲 313 涓被灏辨槸璇佹嵁)銆?
缁撹:**shim 姘歌繙鍙兘鏄?鏍?(鍙?extends Object)**,瑕佷慨缁ф壙鍏崇郴鍙兘鍦?琚崲杩涙父鎴忓眰鐨勯偅浠藉瓧鑺?涓婁慨銆?

### 淇硶:鍦ㄦ崲杩涘幓鐨勭被涓婃敼鐖剁被(绂荤嚎鍑鸿鍒?+ 杩愯鏈熸敼鍐?

- 鏂板宸ュ叿 `HierarchyPlan`(`src/.../optifine/HierarchyPlan.java`):鎷?*杞借嵎 jar + 杩愯鏃?jar**(client jar
  鍦ㄥ墠,瀹冩墠鏄墦琛ヤ竵鍚庣殑娓告垙)姣斿姣忎竴瀵瑰悓鍚嶇被,瀵?杞借嵎鐖剁被鏄?`net/minecraftforge/*`,杩愯鏃剁埗绫讳笉鏄?
  Object"鐨勭被,妫€鏌ヤ袱浠朵簨 鈥斺€?杩愯鏃剁埗绫?*鏄惁鏈夊彲琚瓙绫昏皟鐢ㄧ殑鏃犲弬鏋勯€?*(`AttachmentHolder()` 鉁?銆?
  杞借嵎绫讳綋閲?*闄ゆ瀯閫犻摼涔嬪鏄惁杩樻彁鍒版棫鐖剁被** 鈥斺€?涓ゆ潯閮借繃鎵嶅啓杩?`reparent.txt`銆傚伐鍏蜂笉鑳芥斁杩涜繍琛屾湡:
  transformer 鎵嬮噷鍙湁"鍚屼竴涓被鐨勪袱浠藉瓧鑺?,鐪嬩笉鍒板畠涓婇潰鐨勯偅涓埗绫汇€?
- loader 渚?`PatchedClassTransformer.reparent(...)`:鎸夎鍒掕〃鎶?`superName` 鎹㈡垚杩愯鏃剁殑鐖剁被,骞舵妸姣忎釜鏋勯€?
  鍑芥暟閲?`invokespecial CapabilityProvider.<init>(Ljava/lang/Class;)V` 鏀规垚
  `invokespecial AttachmentHolder.<init>()V`(鍙傛暟鐢?`POP` 涓㈡帀 鈥斺€?Forge 鐨勭埗绫诲彧鏄妸 `self` 瀛樿捣鏉?
  NeoForge 鐨勭埗绫绘妸 map 鏀惧湪瀵硅薄鑷繁韬笂,涓㈠弬鏁版槸**姝ｇ‘鐨勭炕璇?*,涓嶆槸璧版嵎寰?銆?*娌℃湁璁″垝琛ㄥ氨涓嶅姩**,
  璁″垝琛ㄤ笌杩愯鏃剁埗绫讳笉涓€鑷村氨鎷掔粷鎹㈢被骞跺啓鏄庡師鍥犮€?
- 鍚屼竴杞噷鍔犵殑"鎺ュ彛骞堕泦"瀵?*绫?*涔熺敓鏁?鍘熷厛鍙鎺ュ彛):杩愯鏃剁被瀹炵幇浜?NeoForge 鐨勬墿灞曟帴鍙?
  NeoForge 鑷繁鐨勪唬鐮佷細寮鸿浆瀹?杞借嵎鐗堟病鏈夎繖涓帴鍙ｅ氨浼氬湪绗竴娆″己杞 `ClassCastException`銆?
  瀹炴祴杩欎竴鏀彧鏈?1 涓帴鍙ｈ骞惰繘鏉?`IBlockEntityExtension`),瀹冨敮涓€鐨勬娊璞℃柟娉?`getPersistentData()`
  姝ｆ槸鎴愬憳鍥炲～璁″垝宸茬粡琛ヤ笂鐨勯偅涓€涓?`BlockEntity.customPersistentData`)鉁?鎵€浠ュ苟闆嗘槸瀹夊叏鐨勩€?

### 1.21.1 鐜板湪鐨勫垽鎹?涓?1.21.4 榻愬钩)

| 鎸囨爣 | 1.21.1(neoforge-21.1.250) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** 鉁?|
| `Setting user` | 鉁?|
| `Replaced ` / 鎹㈢被鐩爣 | **313 涓被瑁呬笂** / 鐩爣 426 鉁?|
| `[OptiFine]` 鏃ュ織 | **223 琛?* 鉁?|
| `Shaders`(SMCLog 鍒濆鍖?+ 瑁呰浇閰嶇疆) | 鉁?|
| `Pre-stitch` / `Connected textures` | **14 / 3** 鉁?|
| `Caught error` | **0** 鉁?|
| stderr | **0 瀛楄妭** 鉁?|
| `BlockEntity` | 杞借嵎鐗堣涓?甯?OptiFine 鑷繁鐨?`nbtTag` / `nbtTagUpdateMs` / `requestModelDataUpdate`)鉁?|

**1.21.1 涓?1.21.4 鐨勫樊寮?璁板綍鐢?**:1.21.1 鐨?OptiFine 杞借嵎鎸?SRG/娣锋穯鍩哄悕鎵撹ˉ涓併€佽繍琛屾湡 transformer
瑕?娣锋穯鍩虹被璧勬簮"鑰岃繍琛屾湡缁欎笉鍑?`Base resource not found: akr.class` 脳12717),鎵€浠ヨ蛋**绂荤嚎鎹㈢被**;
1.21.4 鍒欐槸 OptiFine 鑷繁閭ｄ唤 transformer 鍦ㄨ繍琛屾湡琛ヤ竵(鎵撶殑鏄?NeoForge 宸叉敼杩囩殑绫?缁ф壙鍏崇郴澶╃劧淇濈暀)銆?

---

## 鎶?鏀圭埗绫?绉诲埌 1.20.x:涓€娆℃祴閲忋€佷袱娆℃嫆缁濄€佷竴鏉¤竟鐣?2026-09-16 鍑屾櫒)

1.21.1 鐨勪慨娉?`HierarchyPlan` 鍑鸿鍒?+ loader 鍦ㄦ崲杩涘幓鐨勭被涓婃敼鐖剁被)鍦?1.20.x 涓婂厛鐓ф惉浜嗕竴閬?缁撴灉鏄?
**涓ゆ潯宸查獙璇佺殑绾跨珛鍒荤粰鍑哄弽渚?*,鑰岃繖涓や釜鍙嶄緥閮藉緢鍊奸挶:瀹冧滑鎶?浠€涔堟儏鍐典笅涓嶈鏀?鍐欐垚浜嗗彲鍒ゅ畾鐨勮鍒欍€?

### 鍏堣缁撹:杩欐潯鍒嗘敮鐜板湪**涓嶅惎鐢?*鐖剁被鏀瑰啓(榛樿娌℃湁璁″垝灏辨槸涓嶆敼)

1.20.4/1.20.2 鐨勮浇鑽?`BlockEntity` 鍚屾牱 `extends net.minecraftforge.common.capabilities.CapabilityProvider`,
鑰岃繍琛屾椂鐨勭埗绫绘槸 NeoForge 鐨勬浛浠ｅ搧:

| 绾?| 杩愯鏃剁埗绫?| 鏃犲弬鏋勯€?| 杞借嵎鏄惁瑕嗗啓瀹冪殑 final 鎴愬憳 |
|---|---|---|---|
| 1.21.1 / 20.4.251 | `net.neoforged.neoforge.attachment.AttachmentHolder` | 鏈?`AttachmentHolder()`)鉁?| 娌℃湁 鉁?鈫?**鍙敼** |
| 1.20.2 / 20.2.88 | `net.neoforged.neoforge.common.capabilities.CapabilityProvider` | 娌℃湁,鍙湁 `(Class)` / `(Class,boolean)` | **鏈?* 鉁?鈫?涓嶈鏀?|

1.20.2 鐨勫疄娴嬪穿娉?鏀圭埗绫婚偅涓€鐗?:

```
IncompatibleClassChangeError: class net.minecraft.world.level.block.entity.BlockEntity
  overrides final method
  net.neoforged.neoforge.common.capabilities.CapabilityProvider.serializeCaps()Lnet/minecraft/nbt/CompoundTag;
```

鍥犱负 20.2 閭ｄ竴鐗堢殑 `CapabilityProvider` 鎶?`gatherCapabilities()` / `getCapabilities()` / `serializeCaps()` /
`deserializeCaps(CompoundTag)` 鍏ㄥ０鏄庢垚 **final**,鑰?OptiFine 鐨?`BlockEntity` 鎭板ソ瑕嗗啓浜嗗畠浠?杩欎簺鏂规硶鏄?
**鎵撴々姝ラ**琛ヨ繘杞借嵎鐨?鎵€浠ヨ鍦?*鎵撹繃妗╃殑 jar** 涓婂垽,涓嶆槸鍦ㄨˉ涓佷骇鐗╀笂鍒?鈥斺€?绗竴鐗堝垽鎹湅閿欎簡 jar,
浜庢槸"妫€鏌ュ瓨鍦ㄤ絾娌℃嫤浣?,杩欎竴鐐逛篃鏄疄娴嬪嚭鏉ョ殑)銆?

淇硶涓ゆ,閮借惤鍦ㄥ伐鍏烽噷鑰屼笉鏄潬浜鸿:

1. `HierarchyPlan` 澧炲姞涓€鏉″垽鎹?**杞借嵎澹版槑浜嗚繍琛屾椂鐖剁被澹版槑涓?final 鐨勫悓鍚嶅悓鎻忚堪绗︽垚鍛?鈫?鎷掔粷**銆?
   瀹炴祴杈撳嚭(1.20.2,鎸?rig 鐨?jar 椤哄簭):
   `no reparent for net/minecraft/world/level/block/entity/BlockEntity: it declares
   deserializeCaps(Lnet/minecraft/nbt/CompoundTag;)V, which the runtime superclass 鈥?declares final` 鉁?
2. 1.20.x 鐨?loader 閲?鐖剁被鏀瑰啓鐨?*澶辫触涓嶅啀鏄?鎷掔粷鎹㈢被",鑰屽彧鏄涓€琛屾棩蹇?*:杩欐潯鍒嗘敮鐨勫凡楠岃瘉琛屼负鏄?
   "鐓ц浇鑽疯嚜宸辩殑鐖剁被鎹㈣繘鍘?,鏀规垚鎷掔粷灏辨槸鏀逛簡宸查獙璇佺殑涓滆タ銆?.21.x 渚т繚鐣欐嫆缁?閭ｄ竴鏀殑瀹炴祴鏄?
   "鎷掔粷 = 璁颁竴琛?+ 娓告垙鐓ц窇,涓嶆嫆缁?= VerifyError 宕?,鎵€浠ユ嫆缁濇洿瀹夊叏)銆?

### 涓€娆¤鎺掗櫎鐨勫亣褰掑洜(璁板綍鐢?

1.20.4 瑁呬笂鐖剁被鏀瑰啓鐨勭涓€娆¤繍琛岄噷 stderr 鏈?14,481 瀛楄妭,閲岄潰鏄?

```
NoClassDefFoundError: net/minecraft/world/level/block/state/BlockState
Caused by: ClassNotFoundException: net.minecraft.world.level.block.state.BlockState
  at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
```

鐪嬭捣鏉ュ緢鍍忔槸杩欐鏀瑰姩鐨勫壇浣滅敤,**浣嗕笉鏄?*:鏀瑰姩涔嬪墠鐨勫熀绾胯繍琛?`logs/run-nf1204-regcheck` 鐨?stderr 鏄?
**鍚屾牱 14,481 瀛楄妭銆佸悓鏍?8 鏉￠敊璇€佸悓鏍蜂笁鏉″ご**(`BlockState` / `Caused by` / `ItemStack`)銆備篃灏辨槸璇?
1.20.4 鏃╁氨甯︾潃杩欎釜缂洪櫡鍦ㄨ窇(鍒ゆ嵁 `Setting user` 鉁撱€乣Pre-stitch` 脳13銆丱ptiFine 鐫€鑹插櫒涓?CTM 閮藉湪璺?,
瀹冩槸涓€鏉?*鐙珛鐨勫緟鍔?*,涓嶆槸杩欐鏀瑰姩寮曞叆鐨勩€?*鏁欒**:鍥炲綊姣旇緝瑕佹瘮"stderr 澶у皬 + 閿欒琛岄泦鍚?,
涓嶈兘鍙湅"鏈夋病鏈夋姤閿?銆?

### 杩欐潯鍒嗘敮鐜板湪鐨勭姸鎬?

- `HierarchyPlan` 宸茶繘 1.20.x 鐨勭绾垮伐鍏烽泦(`src/.../optifine/HierarchyPlan.java`),rig 鐨勬瀯寤烘楠や細璺戝畠骞?
  鎶婄粨鏋滀綔涓?`optifineoforge/reparent.txt` 鎵撹繘 jar;**娌℃湁杩欎釜鏂囦欢 = 涓€琛岄兘涓嶆敼**,杩欏氨鏄粯璁ゃ€?
- 1.20.2 鐨勮鍒掓槸绌?琚?final 瑕嗗啓鍒ゆ嵁鎷掔粷)鉁?1.20.4 鐨勮鍒掓槸
  `BlockEntity 鈫?AttachmentHolder via ()V` 鉁?1.20.1 鏃犺鍒?閭ｄ竴鏀殑 Forge 鍖呭氨鏄繍琛屾椂鑷繁鐨勫寘,
  涓よ竟缁ф壙鍏崇郴鏈潵灏变竴鑷?鉁撱€?

### 鍥涙潯绾跨殑鍥炲綊(2026-09-16 00:55鈥?0:59,鍚屼竴鎵硅繛缁窇)

| 绾?| VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | `Caught error` | stderr | 鐖剁被鏀瑰啓 |
|---|---|---|---|---|---|---|---|---|
| 1.21.1 / 21.1.250 | `STARTED (40s)` | 鉁?| 223 | 14 | 3 | 0 | **0 瀛楄妭** | **宸叉敼鍐?* 鉁?plan 1 鏉? |
| 1.21.4 / 21.4.149 | `STARTED (40s)` | 鉁?| 232 | 14 | 3 | 0 | **0 瀛楄妭** | 鏃犺鍒?璇ョ嚎涓嶆崲绫?OptiFine 鑷繁琛ヤ竵 474 涓洰鏍?鉁?|
| 1.20.4 / 20.4.251 | `STARTED (40s)` | 鉁?| 241 | 13 | 3 | 0 | 14,481 瀛楄妭(= 鍩虹嚎) | **宸叉敼鍐?* 鉁?plan 1 鏉? |
| 1.20.2 / 20.2.88 | `STARTED (40s)` | 鉁?| 239 | 13 | 2 | 0 | 14,631 瀛楄妭(= 鍩虹嚎褰㈢姸) | 璁″垝涓虹┖ 鉁?鏃ュ織鍐欐槑"鐓ц浇鑽疯嚜宸辩殑鐖剁被鎹? |

1.21.4 閭ｄ竴琛岀殑 232 琛?`[OptiFine]` 涓?14 娆?`Pre-stitch` 涓庡畠绉绘鍓嶇殑鍩虹嚎**瀹屽叏涓€鑷?*,璇存槑杩欎竴杞敼鍔ㄦ病鏈?
纰板埌瀹冪殑璺緞 鉁撱€?.20.2 鏄繖涓€杞敮涓€"鍏堝潖鍚庝慨"鐨勭嚎:鏀圭埗绫婚偅涓€鐗?`EXITED (10s)`,鍔犱笂 final 鍒ゆ嵁涔嬪悗鍥炲埌
`STARTED` 鉁撱€?

---

## 1.21.3 涓€娆¤窇閫?浠ュ強 1.21.9 涔嬪悗鐪熸鐨勮矾闅?2026-09-16 鍑屾櫒)

### 1.21.3(neoforge-21.3.97 + OptiFine J2)

鍑嗗杩欎竴姝ヨ繖杞鑴氭湰鍖栦簡,鍥犱负瀹冨鍓╀笅鐨勬瘡涓€鏉＄嚎閮借鍋氫竴娆?鑰屾墜宸ュ仛姣忔閮芥紡涓滆タ:

- `prepare-line.ps1`:缁欎竴涓?MC 鐗堟湰鍜?NeoForge 绾垮墠缂€,鍙栧師鐗?client jar(闀滃儚 鈫?Mojang)銆佸彇璇ョ嚎鏈€鏂扮殑
  NeoForge installer銆佽鍒?`libraries/`,**骞惰В鏋?installer 鑷繁鎶ュ嚭鏉ョ殑"涓嬭浇澶辫触鐨勫簱"鍐嶆墜宸ヨˉ榻?*銆?
- `ensure-vanilla-libs.ps1`:鎸夌増鏈?json 閫愪釜鏍稿 `libraries/` 閲屾槸鍚︾湡鏈夐偅涓簱,缂虹殑鎸?json 閲岀殑 URL 鍙栥€?

杩欎袱浠朵簨閮芥槸琚悓涓€绫诲け璐ラ€煎嚭鏉ョ殑:`NoClassDefFoundError` / `ClassNotFoundException` 鎸囧悜涓€涓?*鍘熺増搴?*
鑰屼笉鏄垜浠殑涓滆タ銆?.21.3 鐨勭涓€娆″惎鍔ㄥ氨姝诲湪

```
Caused by: java.lang.ClassNotFoundException: com.mojang.authlib.properties.Property
  at net.minecraft.SharedConstants.<clinit>(SharedConstants.java:177)
```

`authlib` 娌″湪鏈湴(profile 閲屽垪鐫€,launcher 灏卞皯瑙ｆ瀽涓€涓?jar),琛ヤ笂涔嬪悗绗簩娆″惎鍔ㄥ嵆閫氳繃銆傚彟澶?installer
涔熶細鑷繁澶辫触:`SocketTimeoutException: Connect timed out`,鑰?*鍚屼竴鏉?URL 鎴戜滑鎵嬪伐鍙栨槸閫氱殑** 鈥斺€?鎵€浠?
"installer 鎶ョ己搴?鈫?鎵嬪伐鍙?鈫?鍐嶈窇 installer"鏄竴鏉″浐瀹氱殑淇璺緞,涓嶆槸鍋跺彂銆?

1.21.3 鐨勫垽鎹?

| 鎸囨爣 | 1.21.3(neoforge-21.3.97) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** 鉁?|
| `Setting user` | 鉁?|
| 鎹㈢被 | **268 涓被瑁呬笂** / 鐩爣 445 鉁?|
| `[OptiFine]` 鏃ュ織 | **225 琛?* 鉁?|
| `Pre-stitch` / `Connected textures` / 鐫€鑹插櫒 | **14 / 3 / 鉁?* |
| `Caught error` | **0** 鉁?|
| stderr | **0 瀛楄妭** 鉁?|
| 鐖剁被鏀瑰啓 | `BlockEntity` 鈫?`AttachmentHolder` 鉁?涓?1.21.1 鍚屽舰) |

鏋勫缓渚х殑瀹炴祴:杞借嵎鐨?SRG 鏀瑰悕鏄┖杞?`rewrote 0 method and 0 field names`)鈥斺€斿拰 1.21.1 涓€鏍?杩欎竴绾跨殑
杞借嵎鏈潵灏辨槸瀹樻柟鍚?`compared 240 replaced classes 鈥?110 members to restore`;璁″垝琛?1 鏉?鐖剁被鏀瑰啓)銆?

### 1.21.9 璧锋病鏈?ModLauncher 鈥斺€?杩欐槸 1.21.9/1.21.10/1.21.11/26.1.2 鐨勭湡姝ｈ矾闅?

瀹炴祴 21.11.45 瑁呭嚭鏉ョ殑 profile(`versions/neoforge-21.11.45/neoforge-21.11.45.json`):

```
mainClass: net.neoforged.fml.startup.Client
libraries: 26 涓?鈥斺€?fancymodloader(earlydisplay/loader 10.0.36)銆乻ponge-mixin 0.16.5銆丄SM 9.8銆?
           JarJarSelector/Metadata銆乥us銆乤ccesstransformers 鈥?
           **娌℃湁 modlauncher,娌℃湁 securejarhandler,娌℃湁 bootstraplauncher**
```

涔熷氨鏄杩欎竴浠?FML **涓嶅啀鏈?ModLauncher**,鎴戜滑鐜板湪鐨勬寕杞界偣(瀹炵幇
`cpw.mods.modlauncher.api.ITransformationService` / `ITransformer<ClassNode>`銆佹妸鎴愬搧绫诲杩?GAME 灞傛ā鍧?
鍦ㄤ笂闈㈡牴鏈笉瀛樺湪;OptiFine 鑷繁閭ｄ唤 `META-INF/services/cpw.mods.modlauncher.api.ITransformationService`
涔熷悓鏍锋棤澶勫彲鎸?鈥斺€?瀹炴祴 **1.21.11 J9 鐨?jar 閲屽彧鏈夎繖涓€涓?service 鏂囦欢**,娌℃湁浠讳綍鏂?loader 鐨勫叆鍙ｃ€?

鏂?loader 鐨勬寕杞界偣鏄?`net.neoforged.neoforgespi.transformation.ClassProcessor`(ServiceLoader 娉ㄥ唽),
API 褰㈢姸鍜屾垜浠幇鏈?transformer 鍑犱箮涓€鑷?

```
public interface ClassProcessor {
  ProcessorName name();
  boolean handlesClass(SelectionContext);
  ComputeFlags processClass(TransformationContext);
  default void afterProcessing(AfterProcessingContext);
  default void link(LinkContext);
}
public abstract class SimpleClassProcessor extends BaseSimpleProcessor {
  public abstract void transform(org.objectweb.asm.tree.ClassNode, SimpleTransformationContext);
  public abstract Set<SimpleClassProcessor.Target> targets();     // 鈫?涓庣幇鏈?targets() 鍚屽舰
}
```

`SimpleClassProcessor` 鏀剁殑姝ｆ槸 ASM 鐨?`ClassNode`,鎵€浠?`PatchedClassTransformer`(鎹㈢被 + 鐖剁被鏀瑰啓 + 鎺ュ彛
骞堕泦 + 鎵撴々)涓庢垚鍛樺洖濉偅濂楅€昏緫鍙互鏁翠綋鎼繃鍘?鍙樼殑鍙槸澶栧眰鎺ュ彛涓庢敞鍐屾柟寮忋€?

**鑰?26.1.2 鏄彟涓€鏉¤矾**:瀹炴祴 OptiFine `K1_pre2` 鐨?jar 閲屽凡缁忔湁

```
META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor
META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator
optifine/OptiFineClassProcessor.class
  extends optifine.OptiFineBaseTransformerService
  implements ClassProcessor, IModFileCandidateLocator
```

鈥斺€?*鏂扮増 OptiFine 鑷繁灏辨敮鎸佹柊 loader**銆傛墍浠?26.1.2 閭ｄ竴鏉＄殑姝ｇ‘鍋氭硶涓嶆槸鎴戜滑绉绘琛ヤ竵鍣?鑰屾槸璁?
NeoForge 鎺ュ彈 OptiFine 鐨?jar 骞惰瀹冭嚜宸辩殑 processor 璺戣捣鏉?瑕佸鐞嗙殑姝ｆ槸 FML 鐨?
`IncompatibleModReason.OPTIFINE` 鎷掔粷涓?`loaderVersion` 涓€绫荤殑鍏冩暟鎹棬妲?銆傝繖姣?1.21.11 閭ｆ潯绾胯交寰楀,
浼樺厛绾т篃搴旇鏇撮珮銆?

**鍓╀笅鐨勫伐浣滃洜姝ゅ垎鎴愪袱绫?*,涓嶈兘鍐嶆寜"鐓?1.21.1 鎶勪竴閬?鏉ュ仛:

1. **ModLauncher 涓栦唬**(1.21 / 1.21.6 / 1.21.7 / 1.21.8):鐜版湁 loader 涓?rig 鐩存帴鍙敤,鎶婅繖鍥涙潯鎸?
   1.21.3 鐨勮矾瀛愰摵瀹屽嵆鍙€?
2. **FML 10 涓栦唬**(1.21.9 / 1.21.10 / 1.21.11 / 26.1.2):闇€瑕?`ClassProcessor` 褰㈡€佺殑鎸傝浇鐐广€?6.1.2 鍏堝仛
   (OptiFine 鑷甫 processor),1.21.9鈥?.21.11 鍒欒鎶婃湰椤圭洰鐨勭绾胯浇鑽锋崲瑁呴€昏緫鎼埌涓€涓?`SimpleClassProcessor`
   涓?鈥斺€?杞借嵎銆佽鍒掕〃銆佺埗绫绘敼鍐欍€佹垚鍛樺洖濉兘宸茬粡鏄绾夸骇鐗?鎼殑鏄寕杞界偣鑰屼笉鏄畻娉曘€?

---

## 1.21.8:鎹㈣鐨勫洓涓柊鍧?涓€娆′竴涓?2026-09-16 鍑屾櫒)

1.21.8(neoforge-21.8.54 + OptiFine `J6_pre16` 棰勮)鏄繖涓€杞噷"姣忎慨涓€涓潙灏卞墠杩涗竴娈?鏈€鍏稿瀷鐨勪竴鏉°€?
鍥涙鍚姩,鍥涙閮藉湪鍓嶄竴娆″畬鍏ㄤ笉鍚岀殑鍦版柟鍋滀綇,鑰屾瘡涓€娆＄殑鏍瑰洜閮借兘鍐欐垚涓€鏉￠€氱敤瑙勫垯:

| # | 鐜拌薄 | 鏍瑰洜 | 瑙勫垯 |
|---|---|---|---|
| 1 | `IllegalArgumentException: Only one quick play option can be specified`(Argument parsing) | 鍚姩鑴氭湰鎶?profile 閲屽洓涓?`${quickPlay*}` 鍗犱綅绗﹀師鏍蜂紶缁欎簡娓告垙,1.21.3 涔嬪墠瀹瑰繊銆?.21.8 涓嶅蹇?| 鍊间粛鏄?`${...}` 鍗犱綅绗︾殑鍙傛暟**鏁存潯涓㈠純**(launcher 渚? |
| 2 | `ClassFormatError: Illegal field modifiers in class BlockStateModel$Unbaked: 0x9` | 渚涗綋绫绘槸鎸?鏅€氱被"鍐欏嚭鐨?鎺ュ彛瀛楁浜庢槸鍙樻垚 `public static`(0x9,鏃?final) | **杩愯鏃剁粰鎺ュ彛鍔犺繃鎴愬憳鐨勬帴鍙ｄ笉鎹㈣**:鎺ュ彛鐨勯潤鎬佸瓧娈靛彧鑳藉湪瀹冭嚜宸辩殑 `<clinit>` 閲岃祴鍊?鎹簡绫诲氨绛変簬鎹㈡帀浜嗛偅娈佃祴鍊?|
| 3 | `NoSuchMethodError: RenderPipelines.lambda$registerCustomPipelines$0` | 杞借嵎鏄寜**鍘熺増**缂栬瘧鐨?鍘熺増娌℃湁 `registerCustomPipelines`;杩欎釜鏂规硶浠庤繍琛屾椂鍥炲～浜?浣嗗畠璋冪敤鐨?lambda 琚?鍚堟垚鎴愬憳涓€寰嬩笉鍥炲～"鐨勮€佽鍒欐尅鎺変簡 | **lambda/access 鍚堟垚鏂规硶:鍙杞借嵎閲岃繛杩欎釜鍚嶅瓧閮芥病鏈?灏卞洖濉?*(鏈夊悓鍚嶆墠璺宠繃,鍥犱负涓よ竟缂栧彿鐙珛) |
| 4 | `NullPointerException: RenderSystem.PIPELINE_MODIFIERS is null`(绗竴甯ф覆鏌撴椂) | 鍥炲～鐨?*闈欐€?*瀛楁鍙湁澹版槑娌℃湁鍊?鈥斺€?鍊煎湪杩愯鏃剁殑 `<clinit>` 閲?鑰屾崲瑁呮妸 `<clinit>` 鎹㈡垚浜嗚浇鑽风殑閭ｄ唤 | **闈欐€佸瓧娈典篃瑕佸洖濉垵濮嬪€?*:浠庤繍琛屾椂 `<clinit>` 閲屽彇鍑鸿瀛楁璧嬪€煎墠鐨勯偅娈电洿绾夸唬鐮?鍖呮垚 `optifineoforge$init$<瀛楁>()V`,鍦ㄧ洰鏍囩被 `<clinit>` 鏈熬璋冪敤 |

绗?4 鏉′慨瀹屽悗鐨勫疄娴?1.21.8):

```
Setting user 鉁?  Reloading ResourceManager: vanilla, mod_resources, mod/neoforge 鉁?
[OptiFine] 267 琛?  Pre-stitch 脳13   CTM 脳3   鐫€鑹插櫒 13 琛?  Caught error: 0
stderr 0 瀛楄妭
Initialised 1 restored static fields in com/mojang/blaze3d/systems/RenderSystem 鉁?
Left BlockStateModel$Unbaked alone: the runtime adds members to that interface 鉁?
```

**浣嗚繖鏉＄嚎杩樻病绠楅€氳繃**:杩涚▼娲诲埌 240 绉掕 harness 鍋滄帀,鑰屽畠鏈€鍚庡嚑鍒嗛挓涓€鐩村湪鍒?

```
[OptiFine] Waiting for model sprites
```

涔熷氨鏄?*鍗″湪妯″瀷璐村浘闆嗚閰嶄笂**(`Sound engine started` 娌″嚭鐜?銆傛墍浠?1.21.8 鐜板湪鐨勭姸鎬佹槸
"鑳借繘璧勬簮閲嶈浇銆佽兘寤鸿创鍥鹃泦鍓嶇紑銆佺劧鍚庢寕浣?,姣?1.21.3 宸竴姝?褰掑埌**鏈€氳繃**閲?涓嬭疆浠?
`Waiting for model sprites` 寰€涓嬫煡銆?

**鍏充簬杩欎簺鏀瑰姩鐨勫奖鍝嶉潰**:绗?2/3/4 鏉￠兘鍔ㄧ殑鏄?1.21.x 鐨?loader 涓庣绾垮伐鍏?鎵€浠?1.21.1 / 1.21.3 / 1.21.4
鍦ㄨ繖涓€杞湯灏惧悇閲嶈窇浜嗕竴娆″洖褰?1.20.x 鍒嗘敮**娌℃湁**鍚屾杩欎笁鏉?瀹冭嚜宸辩殑
`MemberRestorePlan` / `MemberRestoreTransformer` 鏄嫭绔嬪壇鏈?,绛?1.21.x 渚хǔ瀹氬悗鍐嶅甫鐫€鍥炲綊涓€璧疯繃鍘汇€?

### 涓夋潯宸查獙璇佺嚎鐨勫洖褰?涓夊鏀瑰姩涔嬪悗)

| 绾?| VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | stderr | 鍥炲～鎴愬憳 |
|---|---|---|---|---|---|---|---|
| 1.21.1 | `STARTED (40s)` | 鉁?| 223(= 鍩虹嚎) | 14 | 3 | **0 瀛楄妭** | 229 / 59 绫?鏀瑰姩鍓?102 / 38) |
| 1.21.3 | `STARTED (40s)` | 鉁?| 225(= 鍩虹嚎) | 14 | 3 | **0 瀛楄妭** | 233 / 60 绫?|
| 1.21.4 | `STARTED (40s)` | 鉁?| 232(= 鍩虹嚎) | 14 | 3 | **0 瀛楄妭** | 263 / 68 绫?|

涓夎鐨勫垽鎹笌鏀瑰姩鍓?*閫愰」涓€鑷?*,鑰屽洖濉殑鎴愬憳鏁版槑鏄惧彉澶?lambda 涓庨潤鎬佸垵鍊奸偅涓ゆ潯瑙勫垯鐨勪綔鐢?,璇存槑杩欎袱鏉?
瑙勫垯琛ョ殑鏄?鏈潵灏辨病濉笂鐨勪笢瑗?,娌℃湁鍔ㄥ埌宸查獙璇佺殑琛屼负 鉁撱€?

### 褰撳墠淇(2026-09-16 05:40)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯;1.20.2/1.20.4 甯﹀凡鐭ョ殑 Reflector 缂洪櫡) |
| 1.21.x | 1.21.1 / 1.21.3 / **1.21.8** / 1.21.4 | 21.1.250 / 21.3.97 / **21.8.54** / 21.4.149 | **宸查獙璇?*(鍥涙潯) |
| 1.21.x | 1.21 / 1.21.6 / 1.21.7 | 21.0.167 / 21.6.20-beta / 21.7.25-beta | 鍓嶇疆宸茶濂?鏈捣璺?|
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 闇€瑕?`ClassProcessor` 鎸傝浇鐐?|
| 26.x | 26.1.2 | 26.1.2.109 | 闇€瑕?`ClassProcessor` 鎸傝浇鐐?浣?OptiFine `K1_pre2` **鑷甫** processor |

### 1.21.8 閫氳繃:绗簲涓潙鏄皟鐢ㄨ矾寰?鎵惧埌瀹冭姳浜嗕笁娆＄籂姝?

`Waiting for model sprites` 鐨勬牴鍥犱笉鏄己璋冪敤,鑰屾槸**閭ｄ釜璋冪敤璧颁笉鍒?*銆備笁娆＄籂姝ｄ緷娆℃槸:

1. 鍏堟妸淇鏀捐繘**鍙傛暟鏈€澶?*鐨勯偅涓?`discoverModelDependencies`(鐞嗙敱鏄?NeoForge 鐨勮皟鐢ㄦ柟鐢ㄩ暱绛惧悕")鈥斺€?
   鎺㈤拡鏄剧ず瀹為檯琚皟鐢ㄧ殑鏄?*鍙傛暟灏戠殑閭ｄ釜**,涔熷氨鏄?OptiFine 鑷繁閭ｄ唤銆?
2. 浜庢槸璁や负"杞借嵎閭ｄ唤鏃㈢劧鍚湁杩欎釜璋冪敤灏辨病闂"鈥斺€?*璇诲瓧鑺傜爜鍙戠幇璋冪敤鍦ㄤ竴涓垎鏀悗闈?*:
   `109: ifeq 121 / 114: resolveCustomModels / 118: invokestatic collectModelSprites`,杩欎釜鏉′欢鍦ㄦ湰杩愯鏃朵笉鎴愮珛,
   浜庢槸閲囬泦姘镐笉鍙戠敓銆佹爣蹇楁案杩滀负 false銆佽创鍥鹃泦瑁呴厤姘歌繙绛変笅鍘汇€?
3. 淇硶鏀规垚**鍦ㄦ瘡涓噸杞藉紑澶存棤鏉′欢璋冪敤**涔嬪悗,绛夊緟娑堝け:鏃ュ織鍑虹幇 `CustomItems: Registering sprites`,閲嶈浇璧板畬,
   瀹㈡埛绔繘鍒版爣棰樼敾闈€?

鍚屼竴琛岃繕閫煎嚭涓ゆ潯瑙勫垯,鑰屼笖涓ゆ潯閮藉厛鍐欓敊杩囦竴娆?

- **鍥炲～鐨勯潤鎬佸瓧娈佃鏈夊€?*:鍊煎湪杩愯鏃剁殑 `<clinit>` 閲?鑰屾崲瑁呮崲鎺夌殑姝ｆ槸 `<clinit>`銆傚仛娉曟槸鎶婅瀛楁璧嬪€煎墠鐨?
  鐩寸嚎浠ｇ爜鎶芥垚鍒濆鍖栨柟娉?鍦ㄧ洰鏍囩被 `<clinit>` 鏈熬璋冪敤銆傚畠淇殑瀹炴祴澶辫触鏄?
  `RenderSystem.PIPELINE_MODIFIERS is null`(绗竴甯ф覆鏌撴椂)銆?
- **璇ュ垵濮嬪寲鏂规硶涓嶈兘瀵?绫绘湰鏉ュ氨鏈夌殑瀛楁"璋冪敤**:浠庡埆鐨勬柟娉曞啓 `static final` 鏄潪娉?
  瀹炴祴 `IllegalAccessError: Update to static final field ModelDiscovery$ModelWrapper.KEY_ADDITIONAL_PROPERTIES`銆?
  鑰屼笖鍒ゆ柇蹇呴』闂?**鍥炲～涔嬪墠**杩欎釜绫绘湁娌℃湁杩欎釜瀛楁"鈥斺€旈棶鎴?涔嬪悗"灏辨妸鎵€鏈夊垵濮嬪寲閮借烦杩囦簡(鍖呮嫭
  `PIPELINE_MODIFIERS`),浜庢槸鍙堝洖鍒板悓涓€涓?null 宕╂簝銆傝繖涓ゆ閮芥槸鑷激,璁板湪浠ｇ爜娉ㄩ噴閲屻€?

鍙﹀杩欐潯绾块渶瑕佹妸**妯″瀷鍙戠幇閭ｄ竴鏃忕暀缁欒繍琛屾椂**:NeoForge 鐨?`ModelWrapper` 鏈夊叓涓?slot 鍜屼竴涓?`slot(int)`
宸ュ巶,OptiFine 閭ｄ唤鏄寜涓冧釜缂栬瘧鐨勩€佸垵濮嬪寲鏃舵妸 7 浜ょ粰宸ュ巶,浜庢槸
`IndexOutOfBoundsException: Index 7 out of bounds for length 7`銆傝繖鏄?rig 閲岀殑涓€鏉＄嚎璁剧疆,璇佹嵁鍐欏湪鏃佽竟銆?

1.21.8 鐨勬渶缁堝垽鎹?

| 鎸囨爣 | 1.21.8(neoforge-21.8.54 + OptiFine `J6_pre16`) |
|---|---|
| `VERDICT` | **`STARTED (40s, marker: Sound engine started)`** 鉁?|
| `Setting user` | 鉁?|
| `[OptiFine]` / `Pre-stitch` / CTM / 鐫€鑹插櫒 | **337 / 13 / 3 / 鉁?* |
| `Caught error` / `Waiting for model sprites` / `PIPELINE_MODIFIERS` NPE | **0 / 0 / 0** 鉁?|
| 闈欐€佸瓧娈靛垵濮嬪寲 | **5 澶?* 鉁?|
| stderr | **0 瀛楄妭** 鉁?|
| 鎴浘 | 宸蹭繚瀛?鉁?|

**鎺㈤拡鏈韩涔熻俯杩囦袱娆″潙**,涓€骞惰涓?涓€鏄帰閽堥粯璁?*鍏崇潃**(`-Doptifineoforge.debug.reload`),
"娌℃湁 enter 琛?琚鎴?鏂规硶娌¤璋冪敤",鑰屽叾瀹炲彧鏄紑鍏虫病寮€;浜屾槸鎶婂畠鎵撳紑鍚?NativeImage 涓€绫绘帰閽?*姣忓紶璐村浘鍒蜂竴琛?*,
涓€娆¤繍琛屼笁涓囧琛屻€佺湅璧锋潵鍍忓崱浣忋€傜幇鍦ㄥ紑鍏抽粯璁ゅ叧闂?launcher 閲屽啓鏄庝綍鏃惰寮€銆?


### 1.21.8 鐨勭浜斾釜鍧?"绛惧悕琚姞闀?鎶?OptiFine 鐨勯挬瀛愭尋鍑轰簡璋冪敤璺緞
鍥涘淇涔嬪悗 1.21.8 鐨勫崱鐐逛粛鍦?`Waiting for model sprites`,杩欐鏍瑰洜鏄?*璋冪敤璺緞**鑰屼笉鏄垚鍛?

```
杞借嵎   ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos)
         鈹斺攢 璋?CustomItems.collectModelSprites(map)   鈫?鐪熸鎶婇偅涓爣蹇楃疆浣嶇殑鍦版柟
杩愯鏃?ModelManager.discoverModelDependencies(Map, LoadedModels, LoadedClientInfos,
                                              StandaloneModelLoader$LoadedModels)
         鈹斺攢 浠庤繍琛屾椂鍥炲～杩涙潵鐨?娌℃湁閭ｄ釜璋冪敤,鑰?NeoForge 鐨勮皟鐢ㄦ柟缂栬瘧鐨勬槸杩欎釜闀跨鍚?
```

**NeoForge 缁欐柟娉曞姞闀跨鍚嶄箣鍚?OptiFine 閭ｄ唤甯﹂挬瀛愮殑閲嶈浇灏卞啀涔熻皟涓嶅埌浜?* 鈥斺€?鑰岀己杩欎釜璋冪敤涓嶄細鎶ラ敊:
`CustomItems.registerIcons` 鏄湪涓€涓?鐫?100 ms銆佹瘡 50 娆℃墦涓€琛?鐨勫惊鐜噷绛夎繖涓爣蹇楃殑,浜庢槸娓告垙鑳借繘鏍囬鐢婚潰,
鐒跺悗涓€鐩村埛閭ｄ竴琛屻€備慨娉曟槸鍦ㄥ洖濉垚鍒嗕箣鍚?鎶婅浇鑽烽偅浠芥墍璋冪敤鐨勪笢瑗胯ˉ鍒拌繍琛屾椂閭ｄ唤閲?鍙傛暟鍙栦袱杈瑰叡鏈夌殑閭ｄ釜 Map)銆?

杩欎竴姝ヨ繕鐣欎笅涓€鏉?*鑷繁韪╄繃鐨勫潙**,鍊煎緱璁?杩欎釜淇鏈€鍒濆啓鎴愮嫭绔?transformer,缁撴灉"娉ㄥ唽浜嗕絾浠庝笉鐢熸晥",
鍘熷洜鏄?`ClassNode.name` 鏄?*鍐呴儴鍚?甯︽枩绾?**,鑰屾垜鎷跨偣鍙峰悕鍘绘瘮,浜庢槸姣忔璋冪敤閮藉湪绗竴琛岃繑鍥?鍞竴鐨勭棁鐘跺氨鏄?
"璇ュ嚭鐜扮殑鏃ュ織琛屾病鍑虹幇"銆傜幇鍦ㄥ畠骞跺叆鎴愬憳鍥炲～閭ｄ竴閬?椤哄簭涓婁篃蹇呴』濡傛:鍥炲～涔嬪悗鎵嶈疆鍒颁慨璋冪敤璺緞)銆?

**淇畬涔嬪悗浠嶅崱**:鏃ュ織宸茬粡鍑虹幇

```
Restored OptiFine's model sprite collection into net.minecraft.client.resources.model.ModelManager.
  discoverModelDependencies(...4 涓弬鏁?..)
```

浣?`Waiting for model sprites` 鐓ф棫 鈬?"缂鸿皟鐢?鏄湡鐨?浣嗕笉鏄叏閮?鍓╀笅鐨勬€€鐤戞槸**椤哄簭**(1.21.8 杩欎竴浠?
NeoForge 鐨勮创鍥鹃泦瑁呴厤涓庢ā鍨嬪彂鐜拌皝鍏堣皝鍚?,鍗?OptiFine 绛夊緟鐨勯偅涓爣蹇楁墍渚濊禆鐨勬楠ゆ帓鍦ㄤ簡绛夊緟涔嬪悗銆傝繖鏉＄暀浣?
1.21.8 鐨勪笅涓€姝?鍒ゆ嵁浠嶆槸 `Sound engine started` 涓?stderr 0 瀛楄妭銆?

### 26.1.2 鐨勫墠缃?涓嬩竴杞殑璧风偣)

`prepare-line.ps1 -McVersion 26.1.2 -NfPrefix 26.1.2` 璺戦€氫簡:鍘熺増 `26.1.2.jar` 鉁撱€?*NeoForge
26.1.2.109** 鐨?universal 鉁撱€佸師鐗堝簱 56 涓ˉ榻?鉁?LWJGL 3.4.1銆乤uthlib 7.0.63銆乴z4-java 绛?銆?
涓や欢浜嬩笌 ModLauncher 涓栦唬涓嶅悓,閮芥槸閲忓嚭鏉ョ殑:

1. **娌℃湁 `-client.jar` 杩欎竴姝?涔熶笉闇€瑕佸畠**:26.1.2 鐨?profile 閲?`inheritsFrom: 26.1.2`,娓告垙绫荤敱鍘熺増
   妗ｆ鎻愪緵,26 涓簱鍏ㄦ槸 FML 11 閭ｄ竴濂?`fancymodloader 11.0.15`銆乣sponge-mixin 0.17.3`銆丄SM 9.9.1,
   娌℃湁 modlauncher/securejarhandler)銆俰nstaller 鑷繁閭ｄ釜 `neoforge-<鐗堟湰>-client.jar` 涔熷洜姝ゅ缁堜笉浜у嚭
   (`maven` 涓婁篃娌℃湁杩欎釜 classifier:`neoforge-26.1.2.109-client.jar` 鍙栧洖 404),鑰岃繖涓嶅奖鍝嶈繖鏉＄嚎銆?
2. **鎸傝浇鐐瑰彧鑳芥槸 `ClassProcessor`**:涓?21.11.45 鍚屽洜 鈥斺€?娌℃湁 ModLauncher,鎴戜滑鐜版湁鐨?
   `ITransformationService`/`ITransformer` 鏃犲鍙寕銆傝€?OptiFine 26.1.2 鐨?`K1_pre2` **鑷甫**
   `optifine/OptiFineClassProcessor`(瀹炵幇 `ClassProcessor` + `IModFileCandidateLocator`,骞舵敞鍐屼簡涓や釜
   service 鏂囦欢),鎵€浠ヨ繖鏉＄嚎鐨勬纭仛娉曟槸"璁?NeoForge 鎺ュ彈 OptiFine 鐨?jar銆佽瀹冭嚜宸辩殑 processor 璺?,
   瑕佸鐞嗙殑姝ｆ槸 FML 鐨?`IncompatibleModReason.OPTIFINE` 鎷掔粷涓庡厓鏁版嵁闂ㄦ銆?

## 1.21.6 / 1.21.7 / 1.21.8 涓€娆℃敹鍙?涓や釜鍧戦兘鍦?鍥炲～"杩欐潯璺笂(2026-09-16 涓婂崍)

涓婁竴杞粨鏉熸椂 1.21.6 鎮湪 `[OptiFine] Mipmap levels: 4`:鏃ュ織 116 744 琛屻€?6.5 MB,鑰屽綋鏃剁殑缁熻鍐欑潃
`NPE=0`銆?*鍏堟妸鏃ュ織鎸?閲嶅娆℃暟"鎺掑簭**,缁撹绔嬪埢鍙樹簡 鈥斺€?閭ｄ唤鏃ュ織閲?4 428 甯у睘浜庡悓涓€涓?NPE 鐨勫爢鏍?

```
java.lang.NullPointerException: Cannot invoke "MapCodec.decode(...)" because "this.val$fallbackCodec" is null
	at neoforged/neoforge/common/util/NeoForgeExtraCodecs$1.decode(NeoForgeExtraCodecs.java:250)
	...
	at net/minecraft/client/resources/model/BlockStateModelLoader.lambda$loadBlockStates$1(BlockStateModelLoader.java:60)
2214 脳 Failed to load blockstate definition <鎵€鏈夋柟鍧?
```

涔熷氨鏄:鏂瑰潡鐘舵€?*涓€涓兘娌¤杞芥垚鍔?*,璧勬簮閲嶈浇姘歌繙璧颁笉瀹?瀹㈡埛绔仠鍦ㄥ姞杞界敾闈?鈥斺€?涓嶆槸"鍗′綇",鏄?姣忎竴姝ラ兘
鍦ㄦ姤閿?銆傚悓涓€褰㈢姸鍦?1.21.8 鐨勫洖褰掕窇閲屼篃澶嶇幇浜?`reg1218`),璇存槑瀹冧笉鏄?1.21.6 鐙湁銆?

### 鍧戜竴:`invokedynamic` 涓嶅湪鍙栧€兼父璧扮殑琛ㄩ噷

涓婁竴杞妸"鍙栭潤鎬佸瓧娈靛垵鍊?鐨勬父璧颁粠"閫€鍒颁笂涓€涓?label"鏀规垚浜?*鏁版爤**鐨勬父璧?涓轰簡淇?1.21.6 鐨?
`PIPELINE_MODIFIERS`)銆傛暟鏍堟父璧扮殑姣忎竴姝ラ兘瑕佽兘璇村嚭"杩欐潯鎸囦护鍑€浜у嚭鍑犱釜鍊?,鑰?`invokedynamic` 褰撴椂
涓嶅湪琛ㄩ噷 鈬?杩斿洖 `null` 鈬?鏁存鍙栧€艰鍒ゅ畾涓?璇翠笉娓? 鈬?闈欐€佸瓧娈?*鍙洖濉簡澹版槑銆佹病鏈夊洖濉€?*銆?

1.21.8 鐨勮浇鑽风被 `SingleVariant$Unbaked` 姝ｅソ鏄繖绉嶅舰鐘?杞借嵎鍙湁 `CODEC`,杩愯鏃跺浜?`MAP_CODEC`):

```
0: getstatic     Variant.MAP_CODEC
3: invokedynamic #1  apply:()Ljava/util/function/Function;   // lambda
8: invokedynamic #2  apply:()Ljava/util/function/Function;   // lambda
13: invokevirtual MapCodec.xmap(Function,Function)MapCodec
16: putstatic     SingleVariant$Unbaked.MAP_CODEC
```

鑰?NeoForge 鐨?`BlockStateModel$Unbaked.CODEC` 鐢?`dispatchMapOrElse(..., fallback)` 鎶?
`SingleVariant.Unbaked.MAP_CODEC` 褰撳厹搴?閭ｄ釜鍖垮悕绫诲湪**鏋勯€犳椂**灏辨妸 null 鎹曡幏杩涗簡 `val$fallbackCodec`,
浜庢槸姝ゅ悗姣忎竴娆℃柟鍧楃姸鎬佸弽搴忓垪鍖栭兘 NPE銆?

淇硶涓ゆ潯,缂轰竴涓嶅彲(绗簩鏉℃槸绗竴娆′慨瀹屾墠鍙戠幇鐨?:

| # | 浣嶇疆 | 鏀瑰姩 | 涓嶆敼浼氭€庢牱 |
|---|---|---|---|
| 1 | `MemberRestorePlan.stackDelta` / `invoke` | 璁よ瘑 `INVOKEDYNAMIC`(鏃犳帴鏀惰€?鍑€浜у嚭 = 杩斿洖闈?void ? 1 : 0 鈭?鍙傛暟涓暟) | 瀛楁鍙湁澹版槑娌℃湁鍊?|
| 2 | `MemberRestoreTransformer.copy` | 鑳藉鍒?`InvokeDynamicInsnNode`(bootstrap 涓庡弬鏁板叡浜?ASM 浼氬啓杩涙湰绫昏嚜宸辩殑 bootstrap 琛? | 鍊煎彇鍒颁簡鍗村唴鑱斾笉杩涘幓,鏃ュ織閲屽彧鐣欎竴鍙?warn |

瀹夊叏鎬ф槸鏌ヨ繃瀛楄妭鐮佹墠涓嬬粨璁虹殑:杩欎袱涓?`invokedynamic` 鐨?bootstrap 鎸囧悜 `SingleVariant$Unbaked.<init>` 涓?
`SingleVariant$Unbaked.variant()`,OptiFine 閭ｄ唤绫?*涓よ€呴兘鏈?*,鎵€浠ユ妸杩欐浠ｇ爜鎼埌杞借嵎绫婚噷鎵ц鏄悎娉曠殑銆?

**瑙勫垯(鏂板)**:闈欐€佸瓧娈靛垵鍊肩殑鍙栧€兼父璧板繀椤昏璇?`invokedynamic` 鈥斺€?"鍊肩敱 lambda 鏋勯€?鏄繖涓€浠?Minecraft
鐨勫父鎬?璁板綍绫荤殑 `xmap`/`dispatch` 鍏ㄦ槸杩欎釜褰㈢姸),涓嶆槸渚嬪銆?

### 鍧戜簩(宸ュ叿灞?:鏋勫缓鑴氭湰鐨勮繃婊ゅ櫒鎶婂伐鍏风殑鍛婅鍚炴帀浜?

涓婁竴杞殑鏋勫缓鏃ュ織閲?宸ュ叿鍏跺疄**涓€鐩村湪鎶?*:

```
      no safe static initialiser for field net/minecraft/client/renderer/block/model/BlockStateModel$Unbaked.WEIGHTED_MODEL_CODEC
```

鑰?rig 鐨勮繖涓€姝ュ彧鏀捐 `^compared` 寮€澶寸殑姹囨€昏,`no safe 鈥 杩欑被"鎴戞斁寮冧簡"鐨勫憡璀﹀叏閮ㄨ涓㈡帀 鈬?
"鏌愪釜瀛楁琚洖濉垚 null"杩欎欢浜嬪湪**鏋勫缓鏃ュ織閲屽畬鍏ㄤ笉鍙**,鍙兘鍦ㄥ惎鍔ㄥ穿婧冧箣鍚庝粠 16 MB 鐨?stdout 閲屽弽鎺ㄣ€?
杩囨护鍣ㄥ凡鏀规垚 `^compared|no safe|constructor not restorable|could not re-read|stub \(body`銆?

**瑙勫垯(鏂板)**:浠讳綍"璺宠繃 / 鏀惧純 / 闄嶇骇"鐨勫垎鏀兘瑕佽兘鍦ㄦ瀯寤烘棩蹇楅噷鐪嬪埌銆傜湅涓嶈鐨勯檷绾х瓑浜庢病鏈夐檷绾с€?

### 鍧戜笁(1.21.6 鐙湁):鍥炲～杩涙潵鐨勬瀯閫犲櫒涓嶇煡閬撹浇鑽?*鑷湁**鐨勫瓧娈?

鍧戜竴淇畬鍚?1.21.6 鍓嶈繘浜嗕竴澶ф埅,鐒跺悗鎹簡涓€涓綅缃穿:

```
NullPointerException: Cannot invoke "GpuTexture.getFormat()" because "textureIn" is null
	at GlCommandEncoder.verifyColorTexture(264) 鈫?clearColorAndDepthTextures(173)
	at RenderTargetDescriptor.prepare(23) 鈫?CrossFrameResourcePool.acquire 鈫?FrameGraphBuilder.execute
	at PostChain.process 鈫?GameRenderer.processBlurEffect 鈫?GuiRenderer.draw     // 绗竴甯х殑妯＄硦鍚庡鐞?
```

**鍏堝仛瀵圭収瀹為獙鍐嶅姩鎵?*:鍚屼竴鏉＄嚎鐢?`-NoMods` 绌鸿窇 NeoForge 21.6.20-beta 鈬?`STARTED`銆佹棤 crash銆?
鎵€浠ヨ繖鏄垜浠殑杞借嵎閫犳垚鐨?涓嶆槸 beta 鐗堢殑闂(1.21.7 鍚屼竴澶╅€氳繃,涔熻鏄庝笉鏄暣浠ｇ殑闂)銆?

閫愭潯瀛楄妭鐮佺湅涓嬫潵,鍥犳灉閾惧緢鐭?

| 绫?| 鏂规硶 | 鍏抽敭瀛楄妭鐮?|
|---|---|---|
| 杞借嵎 `RenderTarget` | `<init>(String,Z)` | `aload_0; iconst_1; putfield enabled` 鈫?OptiFine 鑷繁鐨勫瓧娈?|
| 杞借嵎 `RenderTarget` | `resize(II)` | `getfield enabled; ifne 28` 鈥斺€?`enabled == false` 鏃?*鍙啓灏哄灏?return**,涓嶅缓缂撳啿 |
| 杩愯鏃?`RenderTarget` | `<init>(String,ZZ)` | 鍥炲～杩涙潵鐨勯偅浠?璁?`label`/`useDepth`/`useStencil`,**浠庝笉纰?`enabled`** |
| 杩愯鏃?`TextureTarget` | `<init>(String,IIZZ)` | 璋冪殑灏辨槸涓夊弬鏋勯€?鈬?姣忎釜鐢卞抚鍥惧垎閰嶇殑 render target 閮芥槸 `enabled = false` |

浜庢槸 `colorTexture` 鎭掍负 null,`RenderTargetDescriptor.prepare` 鎶婂畠浜ょ粰
`clearColorAndDepthTextures` 鏃剁偢鍦?`verifyColorTexture`銆?

**瑙勫垯(鏂板)**:鍥炲～涓€涓?*鏋勯€犲櫒**鏃?杩樿鍥炲～"杞借嵎鑷湁銆佽繍琛屾椂娌℃湁"鐨勫疄渚嬪瓧娈靛垵鍊?鈥斺€?浠?*杞借嵎鑷繁**鐨?
鏋勯€犲櫒閲屽彇閭ｆ璧嬪€笺€傚垽鎹笌瀹炰緥瀛楁鍥炲～涓€鑷?鍙湪"璇ユ瀯閫犲櫒鑷繁涓嶇粰杩欎釜瀛楁璧嬪€?鏃舵墠璋冪敤,鎵€浠?OptiFine
鑷繁缂栬瘧鐨勬瀯閫犲櫒涓€涓兘涓嶅彈褰卞搷(瀹冧滑鐨?`assignedFields` 鍛戒腑)銆?

椤哄甫淇帀涓€涓?*娼滀紡**鐨勯敊璇?瀹炰緥鍒濆€兼彁鍙栫殑鍥為€€娓歌蛋浼氭妸 `putfield` 鐨?*鎺ユ敹鑰?* `aload_0` 涓€璧峰悶杩涘垏鐗?
鑰屽寘瑁呭櫒鑷繁杩樿鍐?push 涓€娆℃帴鏀惰€?

```
aload_0            <- 鍖呰鍣ㄨ嚜宸?push 鐨?
aload_0; iconst_1  <- 琚悶杩涙潵鐨勫垏鐗?鎺ユ敹鑰?+ 鍊?
putfield enabled
```

杩欐牱 `return` 鏃舵爤涓婅繕鍓╀竴涓紩鐢?**涓嶄細閫氳繃鏍￠獙**銆傜幇鍦ㄥ厛璧版暟鏍堟父璧?瀹冨ぉ鐒朵笉鍚帴鏀惰€?,鍥為€€鏃舵墠瑕佹眰
"鍒囩墖棣栨寚浠や笉鏄帴鏀惰€?push"(瑕嗙洊 `this.x = this.y` 杩欑鍊兼湰韬互 `aload_0` 寮€澶寸殑褰㈢姸)銆?
杩欐潯鏄?*鍙戣揣鍓嶇敤 javap 鐪嬩緵浣撳瓧鑺傜爜**鍙戠幇鐨?鈥斺€?绂荤嚎宸ュ叿浜у嚭鐨勭被鍊煎緱閫愪釜鐪嬭繃鍐嶅惎鍔ㄣ€?

### 涓€涓壇浣滅敤:涓€鎵瑰疄渚嬪瓧娈电幇鍦ㄧ湡鐨勬湁鍊间簡

鏂板鐨?杞借嵎鑷湁瀛楁"杩欎竴瓒熶笉鍙慨浜?`RenderTarget`,鍚屼竴鎵归噷鍏跺畠"鍙栦笉鍒板垵鍊?鐨勫疄渚嬪瓧娈典篃涓€骞跺彇鍒颁簡:
1.21.6 鐨?`Camera.roll`銆乣Gui.leftHeight/rightHeight`銆乣ClientLevel.dayTimeFraction/dayTimePerTick`銆?
`ModelManager.bakedStandaloneModels`銆乣EntityRenderState.partialTick` 绛変粠 `no safe initialiser` 鍚嶅崟涓婃秷澶便€?
鍓╀笅鐨勫彧鏈?5 涓?*闈欐€?*瀛楁(`VideoSettingsScreen` 鐨勫父閲忎笌 `FABULOUS`銆佷袱涓?`$SwitchMap` 鍚堟垚琛ㄣ€?
`BlockStateModel$Unbaked.WEIGHTED_MODEL_CODEC`)鈥斺€?鍓嶄袱绫绘槸甯搁噺涓庡悎鎴愯〃(杞借嵎鑷繁浼氬缓),
鏈€鍚庨偅涓瓧娈垫墍鍦ㄧ殑鎺ュ彛鎸?杩愯鏃剁粰鎺ュ彛鍔犺繃鎴愬憳灏变笉鎹㈣"鐨勮鍒?*鏍规湰娌℃崲瑁?*,鎵€浠ヤ笉褰卞搷銆?

### 鏈疆瀹炴祴(2026-09-16 06:44鈥?6:47,杩炵画涓夋潯)

| 绾?| NeoForge | VERDICT | `Setting user` | `[OptiFine]` | 鏃ュ織琛屾暟 | stderr | 鏈杩愯鐨?crash |
|---|---|---|---|---|---|---|---|
| 1.21.6 | 21.6.20-beta | `STARTED (40s, Sound engine started)` | 鉁?| 340 | 907 | **0 瀛楄妭** | 鏃?|
| 1.21.7 | 21.7.25-beta | `STARTED (40s, Sound engine started)` | 鉁?| 340 | 929 | **0 瀛楄妭** | 鏃?|
| 1.21.8 | 21.8.54 | `STARTED (40s, Sound engine started)` | 鉁?| 337 | 899 | **0 瀛楄妭** | 鏃?|

鍥炲綊(鍚屼竴杞敼鍔ㄤ箣鍚庨噸璺?:1.21.8 337 琛?/ 899 琛屾棩蹇椼€?.21.1 223 琛?/ 2 321 琛屾棩蹇椼€乻tderr 鍧囦负 0 瀛楄妭,
涓庢敼鍔ㄥ墠**閫愰」涓€鑷?*(1.21.6 鐨?116 744 琛?鈫?907 琛屽氨鏄潙涓€鐨勯噺绾?銆?

**rig 鐨勫彟涓€澶勮瀵?椤烘墜淇帀**:`launch-neoforge.ps1` 鏀跺熬鏃舵墦鍗扮殑鏄?`crash-reports` 閲屾渶鏂扮殑閭ｄ唤",
鑰屾父鎴忕洰褰曟槸澶嶇敤鐨?鈬?涓婁竴杞穿婧冪殑鎶ュ憡浼氳褰撴垚杩欎竴杞殑缁撹鎵撳嵃鍑烘潵(鎴戝洜姝ょ櫧杩戒簡涓€娆″洓灏忔椂鍓嶇殑
`PIPELINE_MODIFIERS`)銆傜幇鍦ㄥ彧璁?*鏈鍚姩涔嬪悗**鍐欏叆鐨勬姤鍛?鎺緸涔熸敼鎴?`no crash report from this run`銆?

### 褰撳墠淇(2026-09-16 涓婂崍)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯;1.20.2/1.20.4 甯﹀凡鐭ョ殑 Reflector 缂洪櫡;**鏈悓姝ユ湰杞?loader/宸ュ叿鏀瑰姩**) |
| 1.21.x | 1.21.1 / 1.21.3 / 1.21.4 / **1.21.6** / **1.21.7** / **1.21.8** | 21.1.250 / 21.3.97 / 21.4.149 / **21.6.20-beta** / **21.7.25-beta** / **21.8.54** | **宸查獙璇?*(鍏潯) |
| 1.21.x | 1.21 | 21.0.167 | 鍓嶇疆鏈(NeoForge 21.0.167 涓嶅湪鏈満) |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 闇€瑕?`ClassProcessor` 鎸傝浇鐐?OptiFine 渚ф病鏈? |
| 26.x | 26.1.2 | 26.1.2.109 | 闇€瑕佹寕杞界偣,浣?OptiFine `K1_pre2` **鑷甫** processor |

### FML 10/11 鎸傝浇鐐?杩欎竴杞噺鍒扮殑涓変欢浜?涓嬩竴杞捣鐐?

1. **service 鏂囦欢鍚嶄笌绫诲瀷**(OptiFine 26.1.2 `K1_pre2` 灏辨槸杩欎箞娉ㄥ唽鑷繁鐨?:
   * `META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` 鈫?`optifine/OptiFineClassProcessor`
   * `META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator`
   鑰?OptiFine **1.21.11 J9 閲屾病鏈?*杩欎袱涓?鍙湁鑰佺殑
   `META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 鈬?26.1.2 鍙互"璁?OptiFine 鑷繁鎹㈣銆?
   鎴戜滑鍙洖濉?,**1.21.9/1.21.10/1.21.11 蹇呴』鎴戜滑鑷繁鍐?ClassProcessor**(鎶婄幇鏈?`ITransformer` 鐨勯€昏緫鎼繃鍘?銆?
2. **涓ゆ潯绾跨殑 FML 浠ｆ**:1.21.11 鈫?`fancymodloader:loader:10.0.36`(FML 10),26.1.2 鈫?
   `loader:11.0.15`(FML 11);涓よ€?`mainClass` 閮芥槸 `net.neoforged.fml.startup.Client`,搴撻噷閮芥病鏈?
   modlauncher/securejarhandler銆?
3. **SPI 灏卞湪 loader jar 閲?*:`loader-10.0.36.jar` 鍚暣濂?
   `net/neoforged/neoforgespi/transformation/*`(`ClassProcessor`銆乣SimpleClassProcessor`銆?
   `BaseSimpleProcessor`銆乣SimpleFieldProcessor`銆乣SimpleMethodProcessor`銆乣ProcessorName`銆?
   `ClassProcessorProvider`),`SimpleClassProcessor` 鐨勫绾︽槸
   `void transform(ClassNode, SimpleTransformationContext)` + `Set<Target> targets()`,
   鍒殑 FML 閮ㄤ欢鐢?`ServiceLoaderUtil.loadServices(context, ClassProcessorProvider.class)` 瑁呰浇銆?
   鈬?鎴戜滑鐨?loader 缂栬瘧 classpath 鍦?FML 10 绾夸笂瑕佷粠 modlauncher 鎹㈡垚 `loader-10.0.36.jar`銆?

## 26.1.2 绗竴娆″疄鏈?涓夐噸闂?杩囦簡涓ら亾(2026-09-16 涓婂崍)

杩欎竴杞妸 26.x 浠?瀹屽叏娌¤捣璺?鎺ㄨ繘鍒?瀹㈡埛绔繘鏍囬鐢婚潰銆丱ptiFine 鐨?processor 鐪熺殑琚?FML 璋冭捣鏉ヤ簡"銆?
涓夋杩愯,姣忔涓€涓棬:

### 闂ㄤ竴:JDK銆?6.1.2 瑕?Java 25,涓嶆槸 21

鍘熺増 `26.1.2.json` 鍐欑潃 `javaVersion.majorVersion: 25`(`java-runtime-epsilon`),瀹冪殑 JVM 鍙傛暟閲屾湁
`--sun-misc-unsafe-memory-access=allow`銆傜敤鏈満榛樿鐨?JDK 21 璧?绗竴琛屽氨姝?

```
Unrecognized option: --sun-misc-unsafe-memory-access=allow
Error: Could not create the Java Virtual Machine.
```

鎹?`C:\Users\kynar\.jdks\jdk-25\bin\java.exe` 涔嬪悗姝ｅ父銆?*缁撹:26.x 绾跨殑 `Jdk` 鍙傛暟鏄?
`$jdk25`**,1.21.x 绾跨殑 21 涓嶉€傜敤銆?

### 闂ㄤ簩:FML 11 璁や负"杩欐槸 OptiFine"鐨勫垽鎹?灏辨槸涓€涓枃浠?

`IncompatibleModReason.detect(JarContents)` 鏄?*閫愭潯鎸夋枃浠跺瓨鍦ㄦ€?*鍒ょ殑;鍙嶇紪璇?`loader-11.0.15.jar`
鎷垮埌鍏ㄩ儴鍒ゆ嵁:

| 鍒ゆ嵁 | 鏂囦欢 |
|---|---|
| OLDFORGE | `mcmod.info` |
| MINECRAFT_FORGE | `META-INF/mods.toml` |
| FABRIC / QUILT / LITELOADER | `fabric.mod.json` / `quilt.mod.json` / `litemod.json` |
| **OPTIFINE** | **`optifine/Installer.class`** |
| BUKKIT | `plugin.yml` |

涔熷氨鏄 FML 鐨?鎷掔粷 OptiFine"鍙槸**鐪嬪埌瀹樻柟 jar 閲岄偅涓畨瑁呭櫒绫?*;鍘绘帀
`optifine/Installer*.class`(鍏?3 涓潯鐩?瀹冨氨涓嶅啀鎷掔粷銆傚彟涓€涓繀鏀归」鏄厓鏁版嵁:OptiFine 鑷甫
`META-INF/mods.toml` 鍐欑殑鏄?`modLoader="javafml"` + **`loaderVersion="[14,)"`**(Forge 鏃朵唬鐨勮寖鍥?,
FML 11 鐨?javafml 鏄?11.x,杩欎釜鑼冨洿杩囦笉浜?瑕佹敼鎴?`[1,)`銆?

### 闂ㄤ笁(宸茶繃):OptiFine 鑷甫鐨?processor 鍦?FML 11 涓嬬‘瀹炰細璺?

鎵撳畬琛ヤ竵鐨勬帰閽?jar(`preview_OptiFine_26.1.2_HD_U_K1_pre2.jar` 鍘绘帀 3 涓?Installer 鏉＄洰 + 鏀?
`loaderVersion`)鏀捐繘 `mods/`,FML 11 鐨勬棩蹇楁寜椤哄簭缁欏嚭:

```
mods/optifine-probe.jar
OptiFineClassProcessor.init()
OptiFineBaseTransformerService: OptiFine ZIP file: .../mods/optifine-probe.jar
OptiFineBaseTransformer: Forge JAR not available
OptiFineClassProcessor: handlesClass: <姣忎釜琚姞杞界殑绫?
```

鈬?**26.1.2 涓嶉渶瑕佹垜浠嚜宸卞啓鎸傝浇鐐?*,OptiFine 鐨?`ClassProcessor` + `IModFileCandidateLocator`
鏄椿鐨勩€傚鎴风涔熷湪 40 绉掕繘鍏ユ爣棰樼敾闈?`Sound engine started`銆乣Setting user` 鉁?銆?

### 闂ㄤ笁涔嬪悗鐨勭湡璺殰:OptiFine 鐨勮ˉ涓?*鍙栦笉鍒板師鐗堢被**

鎺㈤拡杩欐鏄?OptiFine 鎸備笂浜嗕絾涓€涓被閮芥病鎹㈡垚":stderr 836 512 瀛楄妭,6917 娆″悓涓€涓紓甯哥殑澶翠袱琛屾槸

```
java.io.IOException: Base resource not found: net/minecraft/resources/Identifier.class
	at optifine.Patcher.applyPatch(Patcher.java:148)
	at optifine.OptiFineBaseTransformer.getOptiFineResourcePatched(OptiFineBaseTransformer.java:331)
	at optifine.OptiFineClassProcessor.handlesClass(OptiFineClassProcessor.java:70)
```

`Identifier` 鏄?26.x 鐨勬柊鍚嶅瓧(鍘熸潵鍙?`ResourceLocation`),璇存槑 OptiFine 璁よ瘑 26.x 鐨勫懡鍚?闂鍦?
**瀹冨幓鍝釜 jar 閲屾壘"base"**:FML 11 鎶婃父鎴忕被鏀惧湪 `minecraft` 妯″潡閲?
(`jar(~libraries/net/neoforged/minecraft-client-patched/26.1.2.109/minecraft-client-patched-26.1.2.109.jar)`),
鑰?`Patcher.applyPatch` 鎵剧殑鏄畠鑷繁瑙ｆ瀽鍑烘潵鐨勯偅涓?鍩虹 jar 璧勬簮"闆嗗悎 鈥斺€?杩欎篃鏄笅涓€杞殑绗竴涓棶棰?
**瀹冩槸鎸夎矾寰勬壘涓嶅埌,杩樻槸鎸夊悕瀛楁壘涓嶅埌**(OptiFine 鏈熸湜鏈墦琛ヤ竵鐨勫師鐗?jar,鎴栨湡鏈?`srg/` 鍓嶇紑)銆?

椤哄甫閲忓埌鐨勪袱浠朵簨瀹?鍚庨潰閮借鐢?

* FML 11 鐨?*娓告垙绫昏繍琛屾椂 jar 鏄?`net/neoforged:minecraft-client-patched:26.1.2.109`**
  (`~libraries/...`),杩欏氨鏄?26.x 绾夸笂 `MemberRestorePlan` 瑕佸姣旂殑"杩愯鏃?閭ｄ竴渚?
  profile 鐨?`libraries` 閲?*娌℃湁** NeoForge universal 鐨勬潯鐩?FML 11 鑷繁鎸?
  `~libraries/net/neoforged/neoforge/26.1.2.109/neoforge-26.1.2.109-universal.jar` 鎵惧埌瀹?鈥斺€?
  鎵€浠?rig 鐨?launcher **涓嶇敤鏀?*灏辫兘璧疯繖鏉＄嚎(瀹炴祴)銆?
* 鏍囬鐢婚潰閭ｄ竴娆＄殑 stderr 閲岃繕鏈変竴鏉?GUI 鏂囨湰璺緞鐨勫紓甯?
  (`GuiRenderState.lambda$forEachText$0`,26.x 鏂扮殑娓叉煋鐘舵€佷綋绯?,涓?OptiFine 鐨?Font 琛ヤ竵鏈夊叧,
  绛夎ˉ涓佺湡鐨勭敓鏁堜箣鍚庡啀鍒ゅ畠銆?

### 闂ㄤ笁鐨勪笅涓€姝ュ凡缁忓畾浣?OptiFine 鐨勮ˉ涓?*浠庣郴缁?classpath 璇诲熀纭€绫?*

鎶?`OptiFineBaseTransformer` 鍙嶇紪璇戝埌鏂规硶绾?`Patcher.applyPatch` 鐨勫眬閮ㄥ彉閲忚〃鎶婂洜鏋滆寰楀緢娓呮:

```
line 140: baseName = getPatchBase(name, patterns, cfgMap)
line 146: baseIn   = resourceProvider.getResourceStream(baseName)   // 鈫?鍩虹绫诲瓧鑺?
line 154: patchStream = new ByteArrayInputStream(bytesDiff)         // 鈫?xdelta 鏁版嵁(璋冪敤鏂逛粠 zip 閲屽彇)
line 157: gdp = new GDiffPatcher(baseBytes, patchStream, outputStream)
```

鑰?`OptiFineBaseTransformer.getResourceStream(name)` 鐨勫疄鐜版槸:

```java
name = Utils.removePrefix(name, "/");
Enumeration<URL> urls = ClassLoader.getSystemClassLoader().getResources(name);   // 鈫?绯荤粺 classpath
while(urls.hasMoreElements()) { URL url = ...; if(forgeJarUrlStr != null && url.getPath().startsWith(forgeJarUrlStr)) continue; return url.openStream(); }
return null;
```

涔熷氨鏄:**OptiFine 鏈熸湜"鍩虹娓告垙绫?鍑虹幇鍦?JVM 鐨勭郴缁?classpath 涓?*(`forgeJarUrlStr` 鍙槸鐢ㄦ潵鍦ㄦ壂鎻忔椂
璺宠繃瀹冭嚜宸辫瘑鍒嚭鐨勯偅涓?jar)銆俙forgeJarUrlStr` 鏉ヨ嚜鏋勯€犲嚱鏁伴噷鐨?
`Class.forName("net.minecraft.client.Minecraft").getProtectionDomain().getCodeSource().getLocation()`,
鎷夸笉鍒板氨鎵?`Forge JAR not available`銆?

**26.x 鐨勯棬灏卞湪 rig 鐨勫惎鍔ㄥ櫒涓?*:NeoForge profile 鑷繁娌℃湁 jar(`versions/<profile>/<profile>.jar` 涓嶅瓨鍦?,
鑰岀湡瀹炲惎鍔ㄥ櫒浼氭妸 `inheritsFrom` 閭ｄ竴鐗堢殑鍘熺増 jar 鏀捐繘 classpath;rig 鐨?`launch-neoforge.ps1` 涔嬪墠鍙?
"鎶ュ憡缂哄け鐒跺悗璺宠繃",鎵€浠?`Minecraft.class` 鐨勬潵婧愭嬁涓嶅埌銆佸熀纭€绫讳篃鎵笉鍒般€傛敼鎴?鑷韩 jar 缂哄け鏃堕€€鍥?
`inheritsFrom` 鐨?jar"涔嬪悗:

| 瑙傛祴 | 鏀逛箣鍓?| 鏀逛箣鍚?|
|---|---|---|
| `OptiFineBaseTransformer` | `Forge JAR not available` | **`Forge JAR URL: file:/.../versions/26.1.2/26.1.2.jar`** |
| stderr 鐨?`Base resource not found` | **6917** 娆?| **373** 娆?373 涓笉鍚岀殑绫诲悕) |
| 瀹㈡埛绔?| 鏍囬鐢婚潰(`Sound engine started`) | 鏍囬鐢婚潰(`Sound engine started`) |
| `[OptiFine]` 琛屾暟 | 0 | 0 |

鈬?澶ч儴鍒嗙被鐨?鍩虹瀛楄妭"宸茬粡鑳藉彇鍒颁簡(6917 鈫?373),浣?**OptiFine 渚濈劧娌℃湁鐪熸鐢熸晥**(`[OptiFine]` 0 琛?,
鑰屽墿涓嬬殑 373 涓被鍚嶉噷绗竴涓氨鏄?`net/minecraft/resources/Identifier.class`(鏈€鏃╃敱 NeoForge 鐨?
`ClientHooks.<clinit>` 瑙﹀彂)銆備笅涓€杞氨浠庤繖涓や釜闂杩涘幓:

1. **閭?373 涓悕瀛椾负浠€涔堝彇涓嶅埌鍩虹瀛楄妭**:瀹冧滑纭疄閮藉湪鍘熺増 jar 閲?鎶芥煡
   `BossHealthOverlay` / `ModelPart` / `SingleVariant$Unbaked` / `GlDevice$ShaderCompilationKey` /
   `Identifier` 浜斾釜,鍘熺増涓?`minecraft-client-patched` 涓や釜 jar 閲岄兘鏈?,鎵€浠ヤ笉鏄?鏂囦欢涓嶅瓨鍦?,
   鏇村儚鏄?*鎵弿鍒扮殑 URL 琚烦杩囨垨鎵撳紑澶辫触**(鍓嶄竴鐗堥噷 `skip` 鍒ゆ嵁鐢ㄧ殑鏄?`url.getPath().startsWith(forgeJarUrlStr)`,
   鐜板湪 `forgeJarUrlStr` 闈炵┖,杩欐潯鍒ゆ嵁绗竴娆＄湡姝ｇ敓鏁?銆?
2. **`OptiFineClassProcessor` 鍒板簳瑁呮病瑁呯被**:`handlesClass` 浼氱湡鐨勫仛涓€娆¤ˉ涓佹潵鍒?鎴戣兘涓嶈兘澶勭悊",
   `processClass` 鎵嶅喅瀹氬畨瑁?鏃ュ織閲屽彧鏈?`handlesClass` 娌℃湁鍒殑,`[OptiFine]` 涓€琛岄兘娌℃湁 鈬?
   闇€瑕佽 `OptiFineClassProcessor.processClass` 鐨勮繑鍥?`ComputeFlags`)涓庡畨瑁呮潯浠躲€?

### 涓夋瀹炴祴鎶婅繖鏉¤矾璧板畬浜?26.1.2 涓嶈兘闈?杩愯鏈熻ˉ涓?,瑕佽蛋绂荤嚎

`OptiFineClassProcessor` 鐨勬柟娉曚綋鎶婂绾﹀啓姝讳簡(鍙嶇紪璇戝埌鏂规硶绾?:

```java
public boolean handlesClass(SelectionContext context) {          // line 66-72
    LOGGER.info("OptiFine: handlesClass: " + context.type().getClassName());
    String classPath = this.transformer.getSrgClassPath(name);    // "srg/<name>.class"
    return this.transformer.getOptiFineResourceStream(classPath) != null;
}
```

涔熷氨鏄:**鑳戒笉鑳藉鐞?= 鑳戒笉鑳戒粠 OptiFine 鑷繁鐨?zip 閲屽彇鍒拌繖涓被鐨勬垚鍝?*;鍙栦笉鍒板氨璧?鐜板満鎵撹ˉ涓?,
鑰岀幇鍦烘墦琛ヤ竵瑕佹寜涓婇潰閭ｆ潯瑙勫垯鍘荤郴缁?classpath 鎵惧熀纭€绫汇€備笁娆″惎鍔ㄦ妸杩欐潯璺殑涓変釜缁撳眬閮介噺鍑烘潵浜?

| 璇曢獙 | classpath 閲屾斁浜嗕粈涔?| `Forge JAR URL` | stderr `Base resource not found` | `processClass` | 缁撴灉 |
|---|---|---|---|---|---|
| 鈶?浠€涔堥兘涓嶆斁 | 鈥?| `Forge JAR not available` | **6917** | 0 | 杩涙爣棰樼敾闈?OptiFine 鏈敓鏁?|
| 鈶?鍔犲師鐗?jar | `26.1.2.jar` | `file:/.../versions/26.1.2/26.1.2.jar` | **373** | 0 | 杩涙爣棰樼敾闈?OptiFine 浠嶆湭鐢熸晥 |
| 鈶?鍔犺ˉ涓佸悗 jar(鏀炬渶鍓? | `minecraft-client-patched-鈥ar` | `file:/.../minecraft-client-patched-鈥ar`(**姝ｇ‘**) | 鏈強缁熻 | 鈥?| **FML 11 鎷掔粷鍚姩**:`NeoForge dev environment Minecraft jar does not have a Minecraft-Dists attribute` / `The patched Minecraft jar is missing` |

鈶￠噷閭?373 涓悕瀛?*涓嶆槸**"jar 閲屾病鏈?:373 涓叏閮ㄥ悓鏃跺瓨鍦ㄤ簬鍘熺増 jar 涓庤ˉ涓?jar,鑰屼笖**姣忎竴涓兘鏈夊搴旂殑
`srg/...class.xdelta` 琛ヤ竵**(鐢ㄥ搱甯岃〃閫愪釜鏌ヨ繃)銆傛墍浠ョ幇璞℃槸"鎵弿鍒扮殑 URL 琚烦杩囨垨鎵撳紑澶辫触":
`getResourceStream` 閲岄偅鏉?`url.getPath().startsWith(forgeJarUrlStr)` 鍦ㄢ憽閲岀涓€娆＄湡姝ｇ敓鏁?鑰屽畠璺宠繃鐨?
姝ｆ槸**瑁呯潃鍩虹绫荤殑閭ｄ釜 jar**(鍥犱负 `Minecraft.class` 鐜板湪浠庡畠瑙ｆ瀽鍑烘潵)銆傚墿涓?6538 涓被涓嶆姏寮傚父涔熶笉绛変簬
鎴愬姛 鈥斺€?`handlesClass` 渚濈劧杩斿洖 false銆乣processClass` 涓€娆￠兘娌¤璋冪敤(`processClass` 鐨勫瓧绗︿覆鍦ㄥ父閲忔睜閲?
鏃ュ織閲?0 娆?銆?

鈶㈡湰鍙互缁欏嚭姝ｇ‘鐨?`forgeJarUrlStr`,浣?FML 11 涓嶅厑璁告妸琛ヤ竵鍚庣殑娓告垙 jar 鏀惧湪**鍘熷 classpath** 涓?
瀹冧細鎶婇偅涓?jar 褰撴垚 mod file / dev 鐜瀹炰緥鏉ユ牎楠?鐩存帴浠?`Minecraft-Dists` 缂哄け鏀跺満銆?

**缁撹(涓嬩竴杞殑鍋氭硶)**:26.x 涓嶈兘娌跨敤"璁?OptiFine 鑷繁鍦ㄨ繍琛屾湡琛?鐨勮矾瀛?瑕佽蛋**涓庢湰椤圭洰鍏跺畠绾垮畬鍏ㄤ竴鑷寸殑
绂荤嚎璺瓙**鈥斺€攔ig 閲岀敤 OptiFine 鐨?xdelta 鎵?*鍘熺増** jar,浜у嚭 `srg/net/minecraft/**` 鐨勬垚鍝佺被,鍐嶆寜杩愯鏈熷悕瀛?
閲嶆槧灏勩€佽繛鍚屾垚鍛樺洖濉竴璧疯杩涜浇鑽枫€傝繖鏍?`getOptiFineResourceStream` 鐩存帴鍛戒腑鎴愬搧,
`getOptiFineResourceStreamPatched` 涓?鍩虹绫诲湪鍝?杩欐潯鏁撮摼閮戒笉鍐嶅弬涓庘€斺€旇繖涔熸鏄?1.20.x/1.21.x 鍚勭嚎鑳借窇閫氱殑
鍘熷洜(瀹冧滑鐨?OptiFine jar 鏈潵灏卞甫 `srg/` 鎴愬搧,rig 鐨?3c 姝?鎴愬搧鍏ャ€佽ˉ涓佹暟鎹嚭"灏辨槸骞茶繖涓殑)銆?

鍏蜂綋涓夋:

1. `OptifinePipeline` 鐢?*鍘熺増 26.1.2 jar** 浣滃熀纭€,鎶?`patch/srg/**` 鐨?xdelta 鍏ㄩ儴搴旂敤,寰楀埌鎴愬搧绫?
2. 浠?`~libraries/net/neoforged/minecraft-client-patched/26.1.2.109/minecraft-client-patched-26.1.2.109.jar`
   涓?杩愯鏃堕偅涓€渚?鍋氬悕瀛楀榻?闇€瑕?26.1.2 鐨勫悎骞舵槧灏勮〃,鍏堢‘璁?`neoform-26.1.2-*` 閲屾湁娌℃湁);
3. 鎴愬搧绫绘斁杩涢噸鎵撳寘鍚庣殑 jar(`patch/**` 涓㈡帀),淇濈暀 OptiFine 鑷繁鐨?processor 涓庝袱涓?service 鏂囦欢鏉?瑁?,
   鎴戜滑鐨?`MemberRestorePlan` 鍏堥噺涓€閬?26.1.2 鍒板簳缂哄灏戞垚鍛?鈥斺€?OptiFine 杩欎竴鐗堟槸**鐩存帴瀵圭潃 NeoForge 26.1.2
   缂栬瘧**鐨?瀹冪殑 `OptiFineBaseTransformer` 鏋勯€犲櫒閲屽氨寮曠敤浜?`net.neoforged.neoforge.client.extensions.IMinecraftExtension`),
   鎵€浠ュ洖濉噺鍙兘杩滃皬浜?1.21.x 鐨勫悇绾裤€?

## 26.1.2 閫氳繃:绂荤嚎璺嚎璧伴€?鍥涙涓€涓潙(2026-09-17)

鎸変笂闈㈤偅鏉＄粨璁鸿蛋绂荤嚎璺嚎涔嬪悗,杩欐潯绾挎槸"姣忎慨涓€澶勫氨鍓嶈繘涓€姝?鐨勫張涓€渚嬨€傚洓姝ラ噷鍓嶄竴姝ュ凡鐢变笂涓€杞暀涓?
鍚庝笁姝ユ槸杩欎竴杞仛鐨?姣忎竴姝ラ兘鍏堥噺鍒版牴鍥犲啀鏀?

| # | 鐜拌薄 | 鏍瑰洜 | 淇硶 |
|---|---|---|---|
| 1 | `NoSuchMethodError: RenderPipelines.lambda$registerCustomPipelines$0` | 杩愯鏃剁殑鏂规硶浠庝緵浣撳洖濉簡,瀹冭皟鐢ㄧ殑鍚堟垚 lambda 娌″洖濉?| 宸插湪杞借嵎閲岃ˉ涓?涓婁竴杞? |
| 2 | `IllegalStateException: Already registered modded debug entries!` | 鍙栧€兼彁鍙?鍦ㄧ被閲屼换鎰忔柟娉曟壘璧嬪€?,鎶?`registerModdedDebugEntries()` **鏂规硶浣撳唴**鐨?`MODDED_ENTRIES_REGISTERED = true` 褰撴垚浜嗙被鐨勫垵鍊?| `57713b7`:鍏堟壘 `<clinit>`,鍐嶅彧鎵?`<clinit>` 鑳藉埌杈剧殑鏂规硶(`reachedFrom`) |
| 3 | `NullPointerException: Map.size() ... "m" is null`(鍦?`RegisterDebugEntriesEvent.<init>` 閲? | `PROFILES_MUTABLE` 琚彁鍗囨垚**绌?map**:杩愯鏃剁殑 `<clinit>` 鍏?`new HashMap` 璧嬪€笺€佸啀**寰€鍚屼竴涓?map 閲?put** 涓や釜 profile;鍙惉璧嬪€煎氨鍙墿绌哄３銆侼eoForge 鐨勬瀯閫犲櫒璇?`mutableProfiles.get(DEFAULT)` 鈬?null | 鏂板 `populatedView`:褰?`<clinit>` 鍦ㄨ祴鍊煎悗**鍙堣鍚屼竴涓瓧娈?*鏃?鏀逛粠瀹冪殑鍙瑙嗗浘閲嶅缓鈥斺€旀湯灏鹃偅鍙?`Collections.unmodifiableMap(PROFILES_MUTABLE)` 鈫?`PROFILES` 璇存槑涓よ€呭唴瀹圭浉鍚?鑰?`PROFILES` 鏄浇鑽疯嚜宸?`<clinit>` 閲?鏈唬鐮佷箣鍓?灏卞凡璧嬪ソ鐨?浜庢槸鍙?`new HashMap(PROFILES)` |
| 4 | `NoSuchMethodError: Font.ellipsize(FormattedText, int)`(鍦?NeoForge 鑷繁鐨?`ExtendedButton.extractContents`) | **鎺ュ彛骞堕泦娌″仛**:杞借嵎鐨?`Font implements net.minecraftforge.client.extensions.IForgeFont`(鎴戜滑鐢熸垚鐨?*绌?shim**),杩愯鏃剁殑 `Font implements net.neoforged.neoforge.client.extensions.IFontExtension`,鑰?`ellipsize` 鍙槸鍚庤€呯殑 **default 鏂规硶** 鈥斺€?鎹㈣绛変簬鎶婃壙杞借繖涓柟娉曠殑鎺ュ彛涓㈡帀浜?| 鏂板 `ReparentPayload.unionInterfaces`:琛ヤ笂杩愯鏃堕偅浠藉疄鐜扮殑鎺ュ彛,浣?*鍙ˉ鎶借薄鏂规硶杞借嵎宸茬粡鏈夌殑**(鍚﹀垯浼氭妸 NoSuchMethodError 鍙樻垚鏇存櫄鐨?AbstractMethodError)銆?*蹇呴』鏀惧湪鎴愬憳鍥炲～涔嬪悗**:`IFontExtension` 鐨勬娊璞℃柟娉?`self()` 姝ｆ槸鍥炲～鏉ョ殑,鍏堝仛骞堕泦浼氬洜缂?`self()` 鑰屾嫆缁?|

瀹炴祴(2026-09-17 22:40,`logs\run-diag2612m`):

```
VERDICT: STARTED (40s, marker: Sound engine started)   no crash report from this run
Setting user 鉁?       [OptiFine] 3478 琛?       OptiFine: processClass: 795 娆?
ConnectedTextures 40 琛?   Shaders 101 琛?    NullPointerException 0    NoSuchMethodError 0
stderr 107 瀛楄妭 = 1 琛?"Advanced terminal features are not available in this environment"
```

鍏充簬閭?107 瀛楄妭:**鍚屼竴鏉＄嚎涓嶅甫浠讳綍 mod 鐨勫鐓ц窇(`run-ctrl2612b`)stderr 涔熸濂芥槸 107 瀛楄妭銆佸悓涓€琛?*,
鍙湁鏃堕棿鎴充笉鍚?鈬?閭ｆ槸 FML 11 鍦ㄩ噸瀹氬悜 stdio 涓嬬敱缁堢鏃ュ織缁勪欢鍐欏嚭鐨勭幆澧冨憡璀?涓嶆槸鏈?mod 鐨勮緭鍑恒€?
鍒ゆ嵁鎸?mod 鑷繁寰€ stderr 鍐欎簡 0 瀛楄妭"璁°€?

杞借嵎瑙勬ā(渚涗綋/鎺ュ彛閭ｄ袱姝ョ殑瀹炴祴):`restored 253 member(s) across 72 class(es) from 222 planned`銆?
鎺ュ彛骞堕泦琛ヤ簡 8 涓被(`Font`/`VertexConsumer`/`BlockState`/`ModelBaker` 绛?銆乣built` 鍚?
`mods-stage-2612\optifine-payload.jar` 4 034 946 瀛楄妭銆傜浜屾鐙珛澶嶈窇(`run-final2612`)鏁板瓧涓€鑷?
`[OptiFine]` 3478銆乣processClass` 795銆乣Setting user` 鉁撱€丆TM 40銆佹棤鏈 crash銆乻tderr 107 瀛楄妭銆?

### 椤哄甫淇帀涓€涓嚜宸卞紩鍏ョ殑鍥炲綊:鍘熺増 jar 涓嶈兘鍔犲湪 ModLauncher 绾跨殑 classpath 涓?

涓轰簡 26.1.2 鐨?鍩虹绫?閭ｆ潯绾?鍚姩鍣ㄦ浘鍔犺繃涓€鏉″厹搴?**profile 鑷繁娌℃湁 jar 鏃堕€€鍥?`inheritsFrom` 閭ｄ竴鐗堢殑 jar**銆?
鎸夎鐭╄窇 1.21.8 鍥炲綊鏃跺畠绔嬪埢闇插嚭鏉ヤ簡:

```
java.lang.module.ResolutionException: Module minecraft contains package com.mojang.blaze3d.buffers,
  module _1._21._8 exports package com.mojang.blaze3d.buffers to minecraft
	at cpw.mods.modlauncher.ModuleLayerHandler.buildLayer(ModuleLayerHandler.java:83)
```

鍘熺増 jar 浼氫互鑷繁鐨勮嚜鍔ㄦā鍧?`_1._21._8`)鍔犲叆妯″潡璺緞,涓庢父鎴忓眰閲岀殑 `minecraft` 妯″潡瀵煎嚭鍚屼竴涓寘 鈬?
妯″潡瑙ｆ瀽鍦?*浠讳綍 mod 鍔犺浇涔嬪墠**灏卞け璐?52 琛屾棩蹇椼€乣Setting user` 0 娆?銆備慨娉曟槸鎶婅繖鏉″厹搴?*闄愬畾鍦?
FML 10/11 鐨?profile 涓?*(`mainClass == net.neoforged.fml.startup.Client`):閭ｅ嚑鏉＄嚎鐨勬父鎴忕被鏉ヨ嚜
`minecraft-client-patched` 涓?FML 鑷繁鐨勮В鏋?鑰?ModLauncher 绾跨殑娓告垙绫绘潵鑷?libraries 閲岀殑琛ヤ竵鍚?client jar,
**涓嶉渶瑕佷篃涓嶅厑璁?*鍐嶅涓€浠藉師鐗?jar銆?

淇畬澶嶈窇:1.21.8 鍥炲埌鍩虹嚎(`lines=900 stderr=0 settingUser=1 optifine=337 sound=1 CTM=38`),
26.1.2 鏁板瓧涓嶅彉 鈬?涓よ竟閮藉共鍑€銆?*瑙勫垯(鏂板)**:鍚姩鍣ㄩ噷浠讳綍"琛ヤ竴浠芥父鎴?jar"鐨勫姩浣滈兘蹇呴』鎸変富绫诲垎浠ｆ,
ModLauncher 绾夸笌 FML 10/11 绾跨殑 classpath 涓嶈兘鍏辩敤涓€涓舰鐘躲€?

## 1.21 閫氳繃,浠ュ強瀹冨甫鍑烘潵鐨勯偅鏉″凡鐭ョ己闄疯閲忓埌浜嗘柊鐨勪竴灞?2026-09-18)

1.21 鏄湰杞?鍓嶇疆宸茶濂姐€佺洿鎺ュ缓绾?鐨勭涓€鏉°€傚畠鍜?1.21.1 / 1.21.3 鍚屽舰(ModLauncher 11.0.4銆丗ML 4.0.23銆?
`neoforge.mods.toml`銆丣DK 21),鎵€浠ョ嚎鍙傛暟娌℃湁浠讳綍鏂颁笢瑗?鈥斺€?鍞竴瑕佹寜闀滃儚瀹為檯娓呭崟纭鐨勬槸 OptiFine 杩欎竴鐗?
**1.21 鍙湁 preview 鏋勫缓**(`/optifine/1.21` 杩斿洖 8 鏉?鍏ㄩ儴鏄?`J1_pre1..pre9`,娌℃湁 release),
鎵€浠ヨ繖涓€鏉＄嚎鐢ㄧ殑鏄渶鏂扮殑 `preview_OptiFine_1.21_HD_U_J1_pre9.jar`,涓?1.21.6/1.21.7/1.21.8 鍚屾牱澶勭悊銆?

绾垮弬鏁拌惤鍦?`build-line.ps1` 鐨勬柊鏉＄洰 `'121'`(`Repo` = `OptifiNeoforge-121x`銆乣Work` = `build-121`銆?
`Out` = `mods-stage-121\optifiNeoforge-combined.jar`銆乣Profile` = `neoforge-21.0.167`銆?
`GameDir` = `game121`),`Work` 涓嬪彟寤轰簡 `optifine-mods.toml`(`neoforge` 鍖洪棿 `[21.0,)`銆乣minecraft` 鍖洪棿
`[1.21,1.21.1)`;杩欎釜妯℃澘姣忎釜 `Work` 涓€浠?鏄?`build-rig-jar.ps1` 鐨勫繀闇€杈撳叆)銆?
`datafixerupper` / `brigadier` 鎸?1.21 鑷繁鐨勭増鏈?json 鍙?**8.0.16 / 1.2.9**(涓嶆槸 1.21.3 鐨?1.3.10)銆?

鏋勫缓(2026-09-17 23:51,`logs\build-line-121-first.txt`):

```
1/5 tools 鉁?  2/5 repack 鉁?OptiFine 鏍规潯鐩?srg=1098銆乶otch=1165)
3/5 patch 鉁?  patched 6 852 376 B;4150 鏉¤繘銆?199 鏉′涪;classpath jar 2 695 865 B
3b/5 SRG鈫抩fficial:閲嶅啓 21907 涓柟娉曞悕 + 17730 涓瓧娈靛悕,88 涓В鏋愪笉鍒?13 涓洜鍚屽悕琚嫆
3b1/5 瀹舵棌璺宠繃 1 涓?com/mojang/blaze3d/vertex/VertexMultiConsumer)
3b4/5 宓屽鏀瑰悕閰嶅 3 涓?Gui$1DisplayEntry / LevelChunkSection$1BlockCounter / ParticleEngine$1ParticleDefinition)
3b2/5 鎵撴々 22 涓垚鍛?7 涓被),11 涓暀缁?loader
3c/5 杞借嵎 4 165 109 B / 1140 绫?434 涓惉鍒?optifineoforge/patched,1165 鏉?patch/notch 涓㈠純
4/5 loader 鉁? 5/5 combined jar 4 334 149 B / 3111 鏉?/ 61 涓緵浣?
```

瀹炴祴(2026-09-18 00:03,`logs\run-final121b`):

```
VERDICT: STARTED (40s, marker: Sound engine started)   鏃犳湰娆″穿婧冩姤鍛?
Setting user 鉁?  [OptiFine] 252 琛?  鎹㈣绫?315 涓?  Pre-stitch 14   CTM 38   鐫€鑹插櫒 14 琛?
stderr 14 141 瀛楄妭 = 4 鏉?CNFE(瑙佷笅)
```

`Setting user`銆乣Sound engine started`銆丱ptiFine 鐨?Config 閮藉湪璺?252 琛?`[OptiFine]`,`Pre-stitch` 14 娆?
CTM 38 琛?,**浣?stderr 涓嶆槸 0**,鑰屾槸涓?1.20.2 / 1.20.4 瀹屽叏鍚屽舰鐨?4 鏉?`NoClassDefFoundError`:

```
java.lang.NoClassDefFoundError: net.minecraft.world.item.ItemStack
  at java.base/java.lang.Class.getDeclaredMethods0(Native Method)
  at net.optifine.reflect.ReflectorMethod.getMethod(ReflectorMethod.java:238)
  at net.optifine.reflect.ReflectorMethod.getTargetMethod(ReflectorMethod.java:82)
  at net.optifine.reflect.ReflectorResolver.resolve(ReflectorResolver.java:45)
  at net.minecraft.client.renderer.GameRenderer.frameInit(GameRenderer.java:1568)
Caused by: java.lang.ClassNotFoundException: net.minecraft.world.item.ItemStack
  at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
  at cpw.mods.cl.ModuleClassLoader.loadClass(ModuleClassLoader.java:216)     鈫?涓ゅ眰
  at cpw.mods.cl.ModuleClassLoader.loadClass(ModuleClassLoader.java:216)     鈫?鐒跺悗鎵嶆槸搴旂敤鍔犺浇鍣?
```

姣忔璺戝浐瀹?4 鏉°€佺被鍚嶅湪 `ItemStack` / `BlockState` / `PoseStack` / `BlockEntityWithoutLevelRenderer`
涔嬮棿鍙?涓?1.20.2(14 631 瀛楄妭)/ 1.20.4(14 481 瀛楄妭)鐨勫瓧鑺傛暟鍚岄噺绾с€佷笁鏉″ご瀹屽叏涓€鏍枫€?

### 杩欎竴杞妸杩欐潯缂洪櫡寰€鍓嶆帹浜嗕竴灞?鏂版祴閲?涓嶆槸鐚滄祴)

涓婁竴杞瀹冪殑璁板綍鍋滃湪"涓庡姞杞藉櫒鏃犲叧,鏄嫭绔嬪緟鍔?,骞剁暀涓?鍏堥噺娓呮瀹冨埌搴曚负浠€涔堝姞杞戒笉浜?銆傝繖涓€杞湪 loader 閲?
鍔犱簡涓€涓?*鍙妯″潡鍏冩暟鎹?*鐨勬帰閽?`PatchedClassTransformer.logModulesOnce`,涓嶅姞杞戒换浣曟父鎴忕被),閲忓埌:

| 瑙傛祴 | 缁撴灉 |
|---|---|
| 鍥涗釜鍖呯殑褰掑睘 | `net.minecraft.world.level.block.state` / `com.mojang.blaze3d.vertex` / `net.minecraft.client.renderer` / `net.minecraft.world.item` **鍏ㄩ儴 owned by `minecraft` = true** |
| 鏄惁瀵煎嚭缁欒浇鑽锋ā鍧?| 瀵?`srg`(GAME 灞?`exported = true`;**瀵?`optifine` 涔?`exported = true`** |
| 璇昏竟 | `srg -> minecraft: reads it = true`;`optifine -> minecraft: reads it = **false**` |
| 杞借嵎鍦ㄥ摢 | `GAME layer holds neoforge minecraft **srg(PAYLOAD)** mixin_synthetic mixinextras.neoforge`,鍗?`net.optifine.reflect` 灞炰簬 GAME 灞傜殑 `srg` |
| 鎴戜滑鑷繁(loader 绫?鍦ㄥ摢 | `SERVICE layer holds optifine` 鈥斺€?鍚屼竴涓?jar 鍚屾椂琚?ModLauncher 褰撲綔 transformation service 瑁呰繘浜?SERVICE 灞?|

鈬?**涓嶆槸"鍖呮病瀵煎嚭"銆佷篃涓嶆槸"璇昏竟缂哄け"**:閭ｄ袱涓寘鏃㈠綊 `minecraft` 鍙堝凡瀵煎嚭缁?`srg`,鑰?`srg` 涔熻寰楀埌
`minecraft`銆傛墍浠ョ己闄蜂笉鍦ㄦā鍧楀浘涓?鑰屽湪**鍙戣捣杩欐瑙ｆ瀽鐨勯偅涓姞杞藉櫒**:鏍堥噷鏄?涓ゅ眰 `ModuleClassLoader`
閮芥病鎺ヤ綇,鏈€鍚庤惤鍒板簲鐢ㄧ被鍔犺浇鍣?銆?

鍙﹀涓ゆ潯鎺掗櫎浜?鏄垜浠崲瑁呮崲鍧忕殑":

- `ItemStack` 鍦ㄦ暣鏉＄嚎閲?*涓€娆￠兘娌¤鎹㈣**(`Replaced net.minecraft.world.item.ItemStack` 鍑虹幇 0 娆?,
  鍗寸収鏍峰嚭鐜板湪澶辫触鍚嶅崟閲?鈬?涓?鎴戜滑鎹㈣繘鍘荤殑閭ｄ唤瀛楄妭"鏃犲叧;
- `BlockState` / `PoseStack` / `BlockEntityWithoutLevelRenderer` 鍚勮嚜鍙鎹㈣ **1 娆?*(鏃ュ織鍙暟),璇存槑
  澶辫触鐨勯偅娆¤姹?*娌℃湁璧板埌浠讳綍浼氭崲瑁呯殑鍔犺浇鍣?*(鍚﹀垯鏃ュ織閲屼細鍑虹幇绗簩娆?`Replaced`)銆?

**涓嬩竴鏉＄嚎绱?涓嬩竴杞殑绗竴涓疄楠?**:鍦ㄦ帰閽堥噷鎶?`optifine`(SERVICE 灞?璇讳笉鍒?`minecraft`)鑷繁
`getPackages()` 鐨勬牱鏈墦鍑烘潵,鐪嬪悓涓€涓?jar 鏄惁鍦ㄤ袱涓眰鍚勬湁涓€浠?*鍚屽悕涓嶅悓鍖?*鐨?`Reflector*`
(渚嬪 `srg.net.optifine.reflect` 涓?`net.optifine.reflect`);鑻ユ槸,鍒欏け璐ョ殑閭ｆ鍙嶅皠鍙戠敓鍦?SERVICE 灞傞偅浠戒笂,
鑰屽畠鐨勫姞杞藉櫒鏍规湰涓嶈繛娓告垙灞?鈥斺€?淇硶灏辫惤鍦?鍒鍚屼竴鎵圭被鍦ㄤ袱澶勫悇瀹氫箟涓€娆?鎴?缁?SERVICE 灞傛ā鍧楄ˉ璇昏竟"涓?
鑰屼笉鏄户缁湪 Reflector 閲屾壘銆?

### 鍚屼竴浠?loader銆佸悓涓€寮犳ā鍧楀浘,1.21.8 鏄共鍑€鐨?鈬?缂洪櫡璺熺潃 OptiFine 鏋勫缓璧?

鏀逛簡鍏变韩鐨勫惎鍔ㄥ櫒(`launch-neoforge.ps1` 鍙鎵撲竴涓€€鍑虹爜)涓?121x 鐨?loader(澶氭墦涓€娈垫ā鍧楁帰閽?涔嬪悗,
鎸夎鐭╄窇浜?1.21.8 鍥炲綊(`logs\run-reg1218y`,2026-09-18 00:15):

```
VERDICT: STARTED (40s, Sound engine started)   [OptiFine] 337   Setting user 鉁?  Sound engine 鉁?
鎹㈣ 380 绫?  CTM 38   stderr 0 瀛楄妭   鏃犳湰娆″穿婧冩姤鍛?  919 琛?
```

涓庝笂涓€杞褰曠殑鍩虹嚎(`optifine=337 settingUser=1 sound=1 CTM=38 stderr=0`)**鏁板瓧涓€鑷?*(琛屾暟 900鈫?19,
澶氬嚭鏉ョ殑姝ｆ槸鏂板姞鐨勯偅 19 琛屾帰閽?鈬?1.21.8 鏈彈褰卞搷,涓ゆ潯鏀瑰姩閮芥槸"鍙姞鏃ュ織"銆?

鑰?*杩欏紶妯″潡鍥惧湪 1.21.8 涓婂畬鍏ㄤ竴鏍?*:`SERVICE layer holds optifine`(璇讳笉鍒?`minecraft`)銆?
`GAME layer holds ... srg(PAYLOAD) ...`銆佸洓涓寘鍚屾牱 `owned by minecraft = true, exported to srg = true`銆?
鍚屼竴浠?loader銆佸悓涓€寮犲浘,1.21.8 鐨?stderr 鏄?0,1.21 鏄?14 141 瀛楄妭 鈬?**缂洪櫡涓嶉殢鍔犺浇鍣ㄨ蛋,闅?OptiFine 鏋勫缓璧?*:
瀹冨嚭鐜板湪 1.20.2(`I7_pre1`)銆?.20.4(`I7`)銆?.21(`J1_pre9`)涓夋潯绾?鑰?1.20.1(`I6`)銆?.20.6(`J1_pre18`)銆?
1.21.1(`J1`)銆?.21.3(`J2`)銆?.21.4(`J3`)銆?.21.6/7/8(`J6_pre*`)閮芥病鏈夈€備笁鏉″甫缂洪櫡鐨勭嚎閲屼袱鏉℃槸 preview
(1.20.2 鐨勫敮涓€鏋勫缓涔熸槸 preview),浣?1.20.4 鏄?release 鈬?鍒ゆ嵁涓嶆槸 preview/release,鑰屾槸 **I7 涓?J1_pre9
杩欎竴鎵?OptiFine 鐨?`Reflector` 琛?*銆?

## 1.21.11:FML 10 鐨勬寕杞界偣棣栨鐢辨垜浠彁渚?骞剁湡鐨勮涓婁簡绫?2026-09-18)

杩欐槸鏈疆鐨勭浜屼欢浜?涔熸槸**绗竴鏉?FML 10 绾?FML 10.0.36,涓荤被 `net.neoforged.fml.startup.Client`,
classpath 閲屾病鏈?modlauncher/securejarhandler)**銆?6.1.2 閭ｆ潯绾夸笉闇€瑕佽嚜宸辩殑鎸傝浇鐐?鍥犱负 OptiFine 鐨?
K1_pre2 **鑷甫** `OptiFineClassProcessor`;鑰?1.21.9 / 1.21.10 / 1.21.11 鐨?OptiFine
(`J7_pre*` / `J9`)鍙０鏄庝簡涓€涓湇鍔℃枃浠?鈥斺€?瀹炴祴 `OptiFine_1.21.11_HD_U_J9.jar` 鐨?
`META-INF/services/` 涓嬪彧鏈?`cpw.mods.modlauncher.api.ITransformationService`,鍦?FML 10 涓?*娌℃湁瀹夸富**銆?

### 鏂颁唬鐮?26.x 鍒嗘敮 `src/fml10/java`,鐢?rig 缂栬瘧,涓嶈繘 Gradle 鏋勫缓)

| 绫?| 浣滅敤 |
|---|---|
| `OptifinePayloadClassProcessor extends SimpleClassProcessor` | 鎸傝浇鐐规湰浣?`targets()` = 杞借嵎閲?`srg/` 涓嬮偅鎵规垚鍝佹父鎴忕被;`transform()` 浠?*鑷繁杩欎釜 jar**(`getProtectionDomain().getCodeSource()`,涓嶆槸璧勬簮鍚?鈥斺€?`srg/` 杩欎釜璺緞娌℃湁浠讳綍鍖呰棰嗗畠)璇诲嚭鎴愬搧绫?鏁寸被瑕嗙洊杩愯鏃剁殑绫?|
| `OptifinePayloadLocator implements IModFileCandidateLocator` | **浠€涔堥兘涓嶆壘**,鍙负浜嗚杩欎釜 jar 琚?FML 鐨勬棭鏈熸湇鍔℃壂鎻忚鍑烘潵 |

`OptifinePayloadLocator` 鐨勫瓨鍦ㄥ畬鍏ㄦ潵鑷竴娆″疄娴?`EarlyServiceDiscovery.SERVICES` 鎭板ソ鏄?
`{IModFileCandidateLocator, IModFileReader, IDependencyLocator, GraphicsBootstrapper,
ImmediateWindowProvider}` 鈥斺€?**`ClassProcessor` 涓嶅湪閲岄潰**銆傛墍浠ュ彧澹版槑 `ClassProcessor` 鐨?jar 涓嶄細琚鍔犺浇,
`FMLLoader.createClassProcessorSet` 璺戠殑鏃跺€欏畠鐨勭被杩樹笉鍦?launch context 涓?澶勭悊鍣?*涓€娆￠兘涓嶄細琚瀯閫?*銆?
瀹炴祴:1.21.11 绗竴娆″惎鍔ㄨ繘浜嗘爣棰樼敾闈€乣mods/` 涓や釜 jar 閮借鎺ュ彈,浣?`[OptiFine]` **0 琛?*銆?
澶勭悊鍣ㄦ棩蹇椾竴琛岄兘娌℃湁銆侽ptiFine 鑷繁鐨?26.1.2 jar 涔熸槸鍚屾椂澹版槑杩欎袱涓湇鍔℃枃浠剁殑,鍘熷洜鐩稿悓銆?

### 閫愪釜鍧?姣忎釜閮芥潵鑷竴娆″惎鍔?鑰屼笉鏄浠ｇ爜)

| # | 鐜拌薄 | 鏍瑰洜 | 淇硶 |
|---|---|---|---|
| 1 | `InvalidModFileException: Missing license (optifine-payload.jar)` | 鎴戝啓鐨勮浇鑽峰厓鏁版嵁妯℃澘灏戜簡 `license` 瀛楁(FML 10 蹇呴渶) | 妯℃澘琛?`license = "All rights reserved"` |
| 2 | 澶勭悊鍣ㄦ病琚瀯閫?| `ClassProcessor` 涓嶅湪鏃╂湡鏈嶅姟闆嗗悎閲?瑙佷笂) | 鍔?`OptifinePayloadLocator` + `IModFileCandidateLocator` 鏈嶅姟鏂囦欢 |
| 3 | `IllegalArgumentException: Invalid class name: com/mojang/blaze3d/buffers/GpuBuffer$MappedView`(鍦?`NameValidation.validateClassName` 鈫?`SimpleClassProcessor$Target.<init>` 鈫?鎴戜滑鐨?`targets()`) | `Target` 鐨勬瀯閫犲櫒鐢?`ClassDesc.of` 鏍￠獙,**瑕佺殑鏄偣鍙蜂簩杩涘埗鍚?*,鎴戠粰鐨勬槸鏂滄潬鍐呴儴鍚?| `targets()` 閲?`name.replace('/', '.')`(宓屽绫荤殑 `$` 淇濇寔涓嶅彉,ASM 鐨?`Type.getClassName()` 涔熸槸杩欎釜褰㈢姸) |
| 4 | `IllegalAccessError: NeoForgeRenderTypes$Internal tried to access method 'RenderType create(String, RenderSetup)'` | 鏁寸被瑕嗙洊鎶?OptiFine 缂栬瘧鐗堥噷**鏇寸獎**鐨勬垚鍛樺彲瑙佹€т篃甯︿簡杩涙潵,鑰?NeoForge 鐨?access transformer 鎶婅繍琛屾椂閭ｄ唤鏀惧杩?NeoForge 鑷繁鐨勪唬鐮佷緷璧栨斁瀹藉悗鐨勫舰寮?| 绉绘 1.21.x 閭ｆ潯宸查獙璇佺殑瑙勫垯:绫绘爣蹇楀彇 OptiFine 鐨?**鎴愬憳鍙鎬у彇涓よ€呮洿瀹界殑**;瀛楁鍦ㄨ繍琛屾椂閭ｄ唤娌℃湁 final 鏃跺幓鎺?final;鎺ュ彛瀛楁寮哄埗 `public static final` |

### 瀹炴祴(2026-09-18 00:26,`logs\run-diag2111e`)

```
VERDICT: STARTED (40s, marker: Sound engine started)
Setting user 鉁?  Sound engine 鉁?  [OptiFine] 182 琛?
鎸傝浇鐐? reading finished classes from file:.../mods/optifine-payload.jar
鎸傝浇鐐? 568 finished game classes;installed ... [391 so far]     鈫?391 涓被鐪熺殑琚浜嗚繘鍘?
stderr 15 860 瀛楄妭(2 鏉?CNFE);鏈疆 game2111 鍑虹幇 2 浠藉穿婧冩姤鍛?瑙佷笅)
```

閾捐矾鐨勬瀯寤烘暟瀛?`build-2111-chain.ps1`,涓?26.1.2 鐨?`build-2612-chain.ps1` 鍚屽舰):姣旇緝 566 涓鏇挎崲绫?
(758 涓繍琛屾椂娌℃湁瀵瑰簲)銆佽鍒掑洖濉?367 涓垚鍛樸€佸疄闄?`restored 398 member(s) across 92 class(es)`銆?
57 涓?Forge 绫诲瀷鐢熸垚 shim(鍏朵腑 5 涓琛ヤ簡 default 鏂规硶)銆佹垚鍝佹父鎴忕被 568 涓€?
杞借嵎 `optifine-payload.jar` 4 029 634 瀛楄妭銆丱ptiFine 鑷繁鐨勭被 775 涓繘 `srg/` 鍙﹀姞 `optifine-classes.jar`
1 465 469 瀛楄妭(modId `optifineclasses`)銆?

### 杩樻病閫氳繃:涓や唤宕╂簝鎶ュ憡,涓や釜绮剧‘鐨勪笅涓€闂?

1. `crash-2026-09-18_00.26.06-fml.txt`:`Mod loading failures have occurred`,
   `TagConventionLogWarning.createForgeMapEntry` 鈫?`ExceptionInInitializerError` @ `NeoForgeMod.<init>:600`銆?
   **涓嬩竴闂?*:杩欐槸"鎴戜滑瑁呰繘鍘荤殑绫?寮曡捣鐨?杩樻槸 21.11.45 鏈韩鐨勯棶棰?鈥斺€?鍒ゆ嵁鏄悓涓€ profile **涓嶅甫鎴戜滑涓や釜 jar**
   鐨勫鐓ц窇(26.1.2 閭ｆ潯绾垮氨鏄潬鍚屼竴鎵嬫硶鎶?107 瀛楄妭 stderr 褰掔粰缁堢鐨?銆?
2. `ClassNotFoundException: net.minecraft.world.level.storage.ValueOutput`,鏍堟槸
   `ReflectorMethod.getMethods` 鈫?`ReflectorResolver.resolve` 鈫?`GameRenderer.frameInit`,
   鑰?*鍔犺浇鍣ㄦ槸 `java.net.URLClassLoader`**(甯т笂鍐?`TRANSFORMER/optifineclasses@1.0.0/鈥)鈥斺€?
   涔熷氨鏄 OptiFine 鑷繁鐨勭被(`net.optifine.**`,鍦?`optifineclasses.jar` 閲?杩欐鏄粠涓€涓?URLClassLoader 鍑烘潵鐨?
   瀹冪湅涓嶅埌娓告垙绫汇€?*涓嬩竴闂?*:FML 10 鎶?甯?metadata 鐨勬櫘閫?mod 鏂囦欢"鏀捐繘鍝竴灞傘€佺敤鍝釜鍔犺浇鍣?鈥斺€?
   26.1.2 閭ｆ潯绾跨殑鍚岀被闂鏄?鏃╂湡鏈嶅姟灞傜殑绫诲彇鍒扮殑鏄?*鍘熺増**褰掓。閲岀殑閭ｄ唤",杩欐杩炵被閮芥壘涓嶅埌,
   涓よ€呮槸涓嶆槸鍚屼竴涓師鍥犵殑涓ょ琛ㄧ幇(鍒ゆ嵁:鎵撳嵃 `net.optifine.reflect.Reflector.class.getModule()`
   涓庡畠鐨?`getClassLoader()`)銆?

娉ㄦ剰 `[OptiFine] 182 琛宍 涓?`Setting user`/`Sound engine` 鍚屾椂鍑虹幇 鈬?OptiFine 鍦?FML 10 涓?*纭疄鏄椿鐨?*,
鎸傝浇鐐硅繖涓€灞傚凡缁忎笉鏄棶棰?鍓╀笅鐨勪袱闂兘鍦?OptiFine 鑷繁鐨勭被浣忓湪鍝竴灞?鍝竴浠藉瓧鑺?涓娿€?

## 鎵撳寘/鍙戝竷:绗竴娆＄湡鐨勮窇璧锋潵(2026-09-18)

`gradlew build` 涓?`release/version.ps1` 鍒拌繖涓€杞负姝?*鍙湪 dry-run 閲屽嚭鐜拌繃**銆傝繖娆′笁鏉＄嚎鍚勮嚜鐪熻窇浜嗕竴閬?
浜х墿鍛藉悕鐭╅樀濡備笅(`release/version.ps1 show` 鐨勭湡瀹炶緭鍑?+ `build/libs/` 閲岀殑鐪熷疄鏂囦欢):

| 鍒嗘敮 | `minecraft_version` | 鐗堟湰(缁?`release/version.ps1`) | 浜х墿 | 瀛楄妭 |
|---|---|---|---|---|
| `1.20.x` | 1.20.4 | 0.2.0(鏈崌) | `OptifiNeoforge-0.2.0+mc1.20.4.jar` | **鏈骇鍑?*(瑙佷笅) |
| `1.21.x` | 1.21.4 | 0.1.0 鈫?**0.1.1**(`patch`) | `OptifiNeoforge-0.1.1+mc1.21.4.jar` | 161 231 |
| `26.x` | 26.1.2 | 0.1.0 鈫?**0.1.1**(`patch`) | `OptifiNeoforge-0.1.1+mc26.1.2.jar` | 124 172 |

鍗囩増鐞嗙敱鎸?`docs/VERSIONING.md` 鐨勬。浣嶈〃:涓ゆ潯绾块兘鏄?`patch`(鍙慨姝ｈ涓?鏂板宸叉敮鎸佺増鏈?涓嶆敼浣跨敤鏂瑰紡),
`1.0.0` 浠嶇劧淇濈暀 鈥斺€?瀹冩槸"鍔犺浇鍣ㄧ‘瀹炲湪娓告垙閲岃窇璧锋潵"鐨勬柇瑷€,鐢?rig 鐨?`VERDICT: STARTED` 鏀拺,杩欎竴娆′笉鐢辨墦鍖呭姩浣滅粰鍑恒€?

### 涓や釜鐪熷疄鐨勬嫤璺檸(閮藉凡淇?涓旂浜屼釜淇敊涓€娆?

1. **26.x:`src/main/java` 鏍规湰缂栬瘧涓嶈繃**銆傝繖鏉″垎鏀殑鐩爣鏄?FML 11(娌℃湁 ModLauncher),鑰?`src/main/java` 閲?
   鏀剧潃 ModLauncher 鏃朵唬鐨?`OptifiNeoforgeTransformationService` 涓?13 涓?transformer 鈬?100 涓敊璇?
   鍏ㄦ槸 `绋嬪簭鍖?cpw.mods.modlauncher.api 涓嶅瓨鍦╜銆備慨娉曚笉鏄垹,鏄?*鎼?*:`src/main/java` 鈫?`src/ml11/java`
   (涓?1.20.x 鍒嗘敮鏃㈡湁鐨?`src/ml10` / `src/ml11` 绾﹀畾涓€鑷?,`src/main/java` 鍙暀杩欎釜 MC 鐩爣鑳界紪璇戠殑涓滆タ
   (mod 鍏ュ彛 + 绂荤嚎宸ュ叿)銆傛惉瀹?`BUILD SUCCESSFUL`銆?
2. **涓ゆ潯绾跨殑 ASM 鐗堟湰閮芥槸鍧?鑰屼笖鏂瑰悜鐩稿弽**:
   - 26.x:NeoForge 26.1.2.109 鎶?`org.ow2.asm:asm-commons` **strictly 閽夊湪 9.9.1**,浠撳簱閲屽啓 9.10.1 鈬?
     鏁翠釜 classpath 瑙ｆ瀽涓嶄簡銆俙asm_version` 鏀?9.9.1 鍗冲彲銆?
   - 1.21.x:NeoForge 21.4.149 鎶?`org.ow2.asm:asm` **strictly 閽夊湪 9.8**,鑰?9.8 **娌℃湁** `Remapper(int)`
     鏋勯€犲櫒,`SrgRemap.Renamer` 鐢ㄧ殑姝ｆ槸瀹冦€傜涓€鐗堜慨娉曟槸鏀规垚鏃犲弬 `super()` + 鎶戝埗寮冪敤 鈥斺€?**閿欎簡,鑰屼笖閿欏緱寰堝€?*:
     瀹冪紪璇戦€氳繃,鍗?*鎮勬倓鏀逛簡宸ュ叿鐨勮緭鍑?*(1.21 杞借嵎浠?4 165 109 鍙樻垚 4 165 610 瀛楄妭),1.21 闅忓嵆
     `EXITED (15s)` 宕╁湪
     `AbstractMethodError: 鈥?ParticleEngine$$Lambda 鈥?does not define or inherit 'ParticleProvider create(SpriteSet)'`
     (`ParticleEngine.register`)銆?*瑙勫昂**:ASM 9.10.1 涓婅繖涓や釜鏋勯€犲櫒**涓嶇瓑浠?*銆?
     姝ｇ‘鐨勪慨娉曟槸 `build.gradle` 閲屽姞 `resolutionStrategy.eachDependency` 鎶?`org.ow2.asm.*` 缁熶竴鍒?
     `project.asm_version`(= 宸ュ叿琚獙璇佽繃鐨勯偅鐗?,`SrgRemap` 鍘熸牱鎭㈠銆?
     鎭㈠鍚庡璺?`logs\run-verify121back`):杞借嵎鍥炲埌 **4 165 109 瀛楄妭**銆乣VERDICT: STARTED (40s, Sound engine started)`銆?
     `Setting user` 鉁撱€佹棤鏈宕╂簝鎶ュ憡銆乻tderr 14 141 瀛楄妭(浠嶆槸閭?4 鏉″凡鐭?Reflector 寮傚父)鈬?1.21 鍥炲埌鍩虹嚎銆?

### 1.20.x 娌′骇鍑?涓嶆槸浠ｇ爜闂,鏄綉缁?

`gradlew build` 鍋滃湪 `:createMinecraftArtifacts`(绾?3鈥? 鍒嗛挓鍚庤秴鏃?,澶辫触淇℃伅鏄?
maven.neoforged.net 鐨?TLS 鎻℃墜琚柇寮€(`Remote host terminated the handshake`,涓?26.x 绗竴娆″け璐ュ悓鍥?銆?
浠ｇ爜渚ф病鏈夋敼鍔?`release/version.ps1 show` 缁欏嚭鐨勪骇鐗╁悕鏄?`OptifiNeoforge-0.2.0+mc1.20.4.jar`,
涓嬩竴杞噸璇曡繖涓换鍔″嵆鍙?鈥斺€?鍒ゆ嵁鏄?`build/libs/` 閲屽嚭鐜拌鏂囦欢銆?

### 褰撳墠淇(2026-09-18)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯;1.20.2/1.20.4 甯﹀凡鐭?Reflector 缂洪櫡) |
| 1.21.x | **1.21** / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **宸查獙璇?*(涓冩潯;**1.21 甯﹀凡鐭?Reflector 缂洪櫡**:`STARTED` + `Setting user` 鉁?+ 鏃犲穿婧?+ stderr 14 141 瀛楄妭) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **宸查獙璇?*(12/15 鏉? |
| 1.21.x | 1.21.9 / 1.21.10 / 1.21.11 | 21.9.16-beta / 21.10.64 / 21.11.45 | 闇€瑕佹垜浠嚜宸卞啓 `ClassProcessor`(OptiFine 1.21.11 J9 涓嶅惈) |

---

## 1.20.x 琛ヤ笂 26.x / 1.21.x 鐨勫伐鍏蜂笌 loader 宸ヤ綔,鍥涙潯绾块噸鏂伴噺杩?2026-09-18)

杩欎竴杞慨鐨勬槸閭ｆ潯"璇氬疄鐨勭己鍙?:1.20.x 鍒嗘敮浠庢潵娌℃湁鎷垮埌鍚庨潰涓よ疆鍦ㄨ繖涓」鐩噷闀垮嚭鏉ョ殑瑙勫垯銆傛惉鐨勬槸**瑙勫垯**,
涓嶆槸鏂囦欢 鈥斺€?姣忎竴鏉￠兘钀藉湪 1.20.x 鐪熺殑浼氳蛋鍒扮殑鍦版柟,鎼笉鍔ㄧ殑閮藉啓娓呭師鍥?鑰屼笉鏄暀涓嬭窇涓嶅埌鐨勪唬鐮併€?

### 鎼繃鍘荤殑(鍚勮嚜瀵瑰簲鍝竴澶?

| 鏉ユ簮 | 瑙勫垯 | 钀藉湪 1.20.x 鐨勫摢閲?|
|---|---|---|
| 1.21.x | 闈欐€佸瓧娈靛垵鍊?`staticInitialiser` / `staticInitialiserFrom` / `readFrom`)銆乣valueRun` 鏁版爤娓歌蛋銆乣INVOKEDYNAMIC` 鐨勮璇嗕笌澶嶅埗銆乣isReceiverPush` 鍏滃簳銆乣lambda$`/`access$` **鍙湪杞借嵎閲屾病鏈夊悓鍚嶆垚鍛樻椂**鍥炲～銆?*杞借嵎鑷湁瀹炰緥瀛楁**鐨勫垵鍊?| `MemberRestorePlan`(鏁存枃浠舵寜 26.x 鏇挎崲,鍏堟牳杩囧樊寮傛槸绾閲忕殑:1.20.x鈫?6.x 鍙湁 8 澶勮鏀圭殑琛屻€? 澶勫彧鍒犱笉琛? |
| 26.x | `reachedFrom`(鍏堟壘 `<clinit>`,鍐嶅彧鎵惧畠鑳藉埌杈剧殑鏂规硶 鈥斺€?26.1.2 鐨?`MODDED_ENTRIES_REGISTERED` 鏄柟娉?*璁剧疆**鐨勬爣蹇?涓嶆槸绫诲垵濮嬪€?銆乣populatedView`(璧嬪€煎悗鍙堣濉厖鐨勫瓧娈垫敼浠庡彧璇昏鍥鹃噸寤?鈥斺€?26.1.2 鐨?`PROFILES_MUTABLE` 琚彁鍗囨垚绌?map) | 鍚屼笂 |
| 1.21.x | 闈欐€佸垵鍊?*鍐呰仈杩?`<clinit>`**銆佹帴鍙ｄ笉璋冪敤鍒濆鍖栥€乣copy()` 璁よ瘑 `InvokeDynamicInsnNode` | loader `MemberRestoreTransformer` |
| 1.21.x | 鎺ュ彛骞堕泦**瀵圭被涔熺敓鏁?*(kept-interfaces)銆?杩愯鏃剁粰杩欎釜鎺ュ彛鍔犺繃鎴愬憳灏变笉鎹㈣"(璇?`member-restores.txt` 鐨?`RESTORED_CLASSES` 瀹堝崼) | loader `PatchedClassTransformer` |

### 鏄庣‘**娌?*鎼殑(姣忔潯閮芥湁鐞嗙敱,涓嶆槸閬楁紡)

- **鎹㈢被琚嫆鏃?`return input`**(1.21.x 鐨勮涓?:1.20.x 鐨勫凡楠岃瘉琛屼负鏄?璁颁竴琛屾棩蹇椼€佺収杞借嵎鑷繁鐨勭埗绫绘崲杩涘幓",
  1.20.2 姝ｆ槸闈犲畠 `STARTED`(閭ｄ竴鐗堢殑 `CapabilityProvider` 鎶?`serializeCaps()` 澹版槑涓?final)銆?
- **`namesOldSuper` 鍔?`TypeInsnNode` 鍒ゆ嵁**:杩欐潯鍦?1.21.x 鏄敹绱?鍦?1.20.x 浼氭妸 1.20.4 **姝ｅ湪鐢熸晥**鐨?
  `BlockEntity 鈫?AttachmentHolder` 鍙樻垚"鎷掔粷鎹㈢埗绫?銆傝鏀瑰繀椤诲崟鐙珛涓€娆℃祴閲忋€?
- **`repairSpriteCollection`(ModelManager 鐨勭簿鐏甸噰闆嗕慨澶?**:瀹冭皟鐢?`net.optifine.CustomItems.collectModelSprites`,
  閭ｆ槸 1.21.8 閭ｄ竴鐗?OptiFine 鎵嶆湁鐨勬柟娉?1.20.x 鐨勮浇鑽烽噷娌℃湁瀹?鐓ф惉灏辨槸 `NoSuchMethodError`銆?
- **`ReparentPayload` / `RestoreMembers` / `ShimInheritedMembers`**:杩欎笁涓槸 26.x **绂荤嚎璺嚎**鐨勫伐鍏?鑰?1.20.x
  鐨勮矾绾挎病鏈夊搴旂殑鏋勫缓姝ラ(瀹冪殑鐖剁被鏀瑰啓涓庢帴鍙ｅ苟闆嗗湪杩愯鏈?loader 閲?shim 鐢?`ForgeApiShims` 鐢熸垚)銆?
  `ReparentPayload.unionInterfaces` 鐨勮鍒欎互"鎺ュ彛骞堕泦"鐨勫舰寮忚惤鍦?loader 涓?`ShimInheritedMembers` 鐨?
  鏃犲悕鏉＄洰瀹堝崼鍙閭ｄ釜宸ュ叿鑷繁鐨勮緭鍏ユ湁鎰忎箟 鈥斺€?鑰屼笖宸叉牳瀵?**1.20.x 鐨?`ForgeApiShims` 涓?26.x 閫愬瓧鑺傜浉鍚?*,
  鎵€浠ラ偅涓€鏉″湪 1.20.x 鏃犲搴旂墿銆?
- **`SrgRemap` 鐨勬瀯閫犲櫒鍘熸牱涓嶅姩**(娴嬮噺杩?鏃犲弬鏋勯€犲櫒浼氭敼鍙橀噸鏄犲皠缁撴灉,1.21 閭ｆ鐨勪唬浠锋槸杞借嵎 +501 瀛楄妭涓?
  `AbstractMethodError`)銆?

### 鍥炲綊:鍥涙潯绾?姣忔潯瀵圭潃鑷繁璁板綍鐨勫熀绾?

| 绾?| VERDICT | `Setting user` | `[OptiFine]` | `Pre-stitch` | CTM | `Caught error` | stderr | 涓庡熀绾挎瘮 |
|---|---|---|---|---|---|---|---|---|
| 1.20.1 / 47.1.106 | `STARTED (40s, Sound engine started)` | 鉁?| **157** | 12 | 2 | 0 | **27 瀛楄妭** | 涓?`run-nf1201-rewrite` **閫愰」鐩稿悓**(157 / 12 / 2 / 27 瀛楄妭) |
| 1.20.2 / 20.2.88 | `STARTED (40s, Sound engine started)` | 鉁?| **239** | 13 | 2 | 0 | 14 625 瀛楄妭 | `[OptiFine]` 239 = 鍩虹嚎;stderr 鍚屽舰鐘?4 鏉?CNFE,绫诲悕鍦?`BlockState`/`ItemStack`/`PoseStack`鈥?涔嬮棿鍙?鏈潵灏辨槸鍙樼殑) |
| 1.20.4 / 20.4.251 | `STARTED (40s, Sound engine started)` | 鉁?| **241** | 13 | 3 | 0 | **14 481 瀛楄妭** | 涓?`run-final1204` **閫愰」鐩稿悓** |
| 1.20.6 / 20.6.141 | `STARTED (40s, Sound engine started)` | 鉁?| **222** | 14 | 3 | 0 | **0 瀛楄妭** | 涓?`run-nf1206-regcheck` **閫愰」鐩稿悓** |

鍥涙潯鏈閮芥病鏈夊穿婧冩姤鍛娿€?*椤哄甫寰楀埌涓€鏉℃柊娴嬮噺**:鎺ュ彛骞堕泦瀵?*绫?*涔熺敓鏁堣繖浠朵簨,鍦?1.20.4 涓婁竴鍏辨敼鍒?9 涓被銆?
鍦?1.20.1 涓?1 涓€?.20.2 涓?8 涓?鑰屽洓鏉＄嚎鐨勫垽鎹?*鍏ㄩ儴涓庡熀绾夸竴鑷?* 鈬?褰撳垵"绫荤骇骞堕泦寮勫潖浜?1.20.x"鐨勭粨璁?
鏄閭ｆ潯宸茬煡 Reflector 缂洪櫡姹℃煋浜嗙殑(1.20.4 鐨?`BlockState`/`ItemStack` 鍩虹嚎閲屾湰鏉ュ氨鏈?瀛楄妭鏁板畬鍏ㄧ浉鍚?銆?

### 鍥炲綊閲岄湶鍑烘潵鐨勪袱涓己闄?閮芥槸鏈疆淇帀鐨?

**涓€銆乣NoSuchFieldError: cache`:璁″垝缁欎竴涓?涓嶄細瑁呬笂"鐨勭被琛ヤ簡鍒濆€笺€?*

1.20.2 鍔犱笂杩欎簺瑙勫垯涔嬪悗 `EXITED (10s)`:

```
Caused by: java.lang.NoSuchFieldError: cache
  at net.minecraft.Util$9.optifineoforge$init$cache(Util.java)
  at net.minecraft.Util$9.<init>(Util.java:663)
```

鍥犳灉閾炬槸鐭殑,鑰屼笖**鏍瑰洜涓嶅湪鏂拌鍒欐湰韬?*:`Util$9` 鍦?`member-restores.txt` 閲?杞借嵎鐨勮ˉ涓佷骇鐗╂湁杩欎釜绫?
鎵€浠ヨ鍒掓瘮鍒颁簡瀹?,浣嗚浇鑽?*娌℃湁** `srg/net/minecraft/Util$9.class`(宓屽瀹舵棌閭ｄ竴姝ユ妸瀹冧涪鎺変簡),
浜庢槸鐪熸琚涓婄殑绫绘槸杩愯鏃剁殑閭ｄ釜,鑰屽畠娌℃湁 `cache` 瀛楁 鈥斺€?鑰?杞借嵎鑷湁瀹炰緥瀛楁"杩欐潯鏂拌鍒欐伆濂界粰
`cache` 閫犱簡涓€涓垵濮嬪寲鏂规硶,鎹㈣鏃惰鍐呰仈杩涙瀯閫犲櫒銆傛棫浠ｇ爜鍚屼竴鏉¤矾鍙洖濉袱涓繍琛屾椂鏂规硶(瀹冧滑鏈潵灏卞湪),
鎵€浠ヤ竴鐩存槸"纰板阀鏃犲"銆?

淇硶钀藉湪 loader 鑰屼笉鏄鍒掗噷,鍥犱负**鍙湁 loader 鐭ラ亾鍝釜绫荤湡鐨勫湪鎵嬩笂**:
`MemberRestoreTransformer` 鍦ㄦ敹闆嗗垵濮嬪寲鏂规硶鏃跺厛鐪嬪畠鍐欑殑閭ｄ釜瀛楁鍦ㄧ洰鏍囩被閲屽湪涓嶅湪,涓嶅湪灏?*涓嶆仮澶嶃€佷笉璋冪敤**,
骞跺啓鏄庡師鍥犮€傚疄娴嬪畠鍦ㄥ洓鏉＄嚎涓婂悇鍙Е鍙戜竴娆?鑰屼笖瑙﹀彂鐨勬鏄?`Util$9`:

```
Not restoring optifineoforge$init$cache in net/minecraft/Util$9: it assigns
net.minecraft.Util$9.cache Ljava/util/Map;, which this class does not have, so the payload's
copy of the class is not the one in place
```

**浜屻€乣NoSuchMethodError: SimpleJarMetadata`:涓€涓?鎸夎鎵嶆垚绔?鐨勪慨澶嶈鏃犳潯浠跺仛浜嗐€?*

1.20.1 鍔犲畬瑙勫垯鍚?`EXITED (5s)`,鑰屼笖姝诲湪 **OptiFine 鑷繁鐨勭被**閲?

```
NoSuchMethodError: 'void cpw.mods.jarhandling.impl.SimpleJarMetadata.<init>(String, String, Supplier, List)'
  at optifine.OptiFineJar.lambda$1(OptiFineJar.java)
```

`OptifineJarFixer` 鎶?OptiFine 閭ｄ釜 `Set` 褰㈢姸鐨勮皟鐢ㄦ敼鍐欐垚 `Supplier` 褰㈢姸 鈥斺€?杩欎釜淇鏄?**2026-09-16 00:01
涓?1.20.2 鍔犵殑**(`60ca008`/`a08e210`),鑰?1.20.1 鐨勪笂涓€娆″疄娴嬫槸 **2026-09-15 23:45**,涔嬪悗鍐嶆病璺戣繃銆?
鍥涙潯 profile 鐨?securejarhandler 鐗堟湰鏄噺鍑烘潵鐨?

| profile | securejarhandler | `SimpleJarMetadata` 绗笁涓弬鏁?|
|---|---|---|
| `1.20.1-forge-47.1.106` | **2.1.10** | `Set`(OptiFine 鑷繁鐨勮皟鐢ㄥ舰鐘?**涓嶉渶瑕佷慨**) |
| `neoforge-20.2.88` / `neoforge-20.4.251` | 2.1.24 | `Supplier`(闇€瑕佷慨) |
| `neoforge-20.6.141` | 3.0.8 | `Supplier`(闇€瑕佷慨) |

鎵€浠ヤ慨娉曟槸鎶婅繖鏉′慨澶嶅彉鎴?*鎸夎**鐨?`OptifineJar` 澶氫竴涓?`--old-securejarhandler`,rig 澶氫竴涓?
`OldSecureJarHandler` 寮€鍏?**鍙湁 1.20.1** 鐨勯偅鏉＄嚎鎵撳紑;榛樿(涓嶅紑)淇濇寔鍏跺畠涓夋潯绾垮凡楠岃瘉鐨勫舰鐘躲€?
**瑙勫垯(鏂板)**:鍑℃槸"鏀硅繍琛屾椂 API 褰㈢姸"鐨勪慨澶?閮借闂畠鏄繍琛屾椂鐨勬€ц川杩樻槸杩欎釜 jar 鐨勬€ц川;
鏄繍琛屾椂鐨?灏卞繀椤绘寜琛岀粰鍙傛暟銆?.20.1 淇ソ鍚?stderr 鍥炲埌 **27 瀛楄妭銆佷笌鍩虹嚎閫愬瓧鑺傜浉鍚?*銆?

## 1.21.11 鐨勪袱闂?涓€闂瓟瀹?涓€闂妸鍥犳灉閾鹃噺鍒颁簡搴?2026-09-18)

### (a) `crash-鈥?fml.txt` 鏄?*鎴戜滑鐨勭被**寮曡捣鐨?涓嶆槸 21.11.45 鑷繁鐨勯棶棰?

鍒ゆ嵁灏辨槸鎸囧畾鐨勫鐓ц窇:**鍚屼竴涓?profile銆佹妸涓や釜 jar 鎷挎帀**(`launch-neoforge.ps1 -NoMods`):

```
mods: (none)     VERDICT: STARTED (40s, marker: Sound engine started)
--- no crash report from this run ---
```

鈬?涓嶅甫鎴戜滑鐨?jar 鏃?21.11.45 骞插噣鍚姩銆傛墍浠ラ偅浠?`Mod loading failures` 鏄湰 mod 瑁呰繘鍘荤殑绫婚€犳垚鐨勩€?

椤哄甫鎶?*鍝竴涓瓧娈垫槸 null** 閲忓嚭鏉ヤ簡:宕╁湪 `TagConventionLogWarning.<clinit>` 鐨勬暟缁勫瓧闈㈤噺閲?
`LineNumberTable` 鎶婃簮鐮佽 201 鏄犲埌鍋忕Щ **2942**,鑰岄偅鏉¤鍙?鏁扮粍涓嬫爣 149)鐨勭涓変釜鍙傛暟姝ｆ槸
`getstatic net/neoforged/neoforge/common/Tags$Items.DYES_BLACK`(琛?52 璧蜂竴琛屼竴鏉?52+149=201,
涓よ竟鍚诲悎)銆俙Tags$Items` 鑷繁鐨?`<clinit>` 閲?128 涓?TagKey 瀛楁閮芥湁璧嬪€?`tag(name)` 鏄?
`ItemTags.create(Identifier.fromNamespaceAndPath("c", name))` 鈥斺€?鑰?`net.minecraft.resources.Identifier`
**姝ｆ槸 391 涓鎹㈣绫婚噷鐨勪竴涓?*銆傛墍浠ヨ繖鏄竴鏉＄嫭绔嬬殑銆佺簿纭殑涓嬩竴闂?**涓嶆槸鏈疆鐨勭粨璁?*):
瑁呬笂 OptiFine 缂栬瘧鐨?`Identifier` 涔嬪悗,`Tags$Items.DYES_BLACK` 涓轰粈涔堟槸 null 鑰屽畠鍓嶉潰鐨?`DYES` 涓嶆槸銆?

### (b) `ClassNotFoundException: ValueOutput`:**鏄垜浠敓鎴愮殑 Forge shim 钀藉湪閿欒鍔犺浇鍣ㄤ笂**

鎺㈤拡(`OptifinePayloadClassProcessor`,涓€娆′竴琛岀殑灞傚浘;鐜板湪鎸傚湪 `OPTIFINEOFORGE_DEBUG_LAYERS` 鍚庨潰),
瀹炴祴鍑烘潵鐨勫姞杞藉櫒鍥?

```
own     = URLClassLoader [optifine-payload.jar] [earlydisplay-10.0.36.jar] [loader-10.0.36.jar]
          -> AppClassLoader -> PlatformClassLoader -> bootstrap
          own 鐪嬩笉鍒?Reflector / ReflectorClass / ValueOutput / GameRenderer(鍏ㄩ儴 CNFE)
system  = AppClassLoader,鍚屾牱鍥涗釜閮界湅涓嶅埌
context = TransformingClassLoader
          -> URLClassLoader [game2111/.cache/jij/<sha>/net.neoforged.neoforge-coremods-21.11.45.jar]
          -> URLClassLoader [optifine-payload.jar] [earlydisplay-10.0.36.jar] [loader-10.0.36.jar]
          -> AppClassLoader -> PlatformClassLoader -> bootstrap
          context 鐪嬪緱鍒?Reflector -> module **optifineclasses**銆丷eflectorClass -> module optifineclasses銆?
                          ValueOutput / GameRenderer -> module **minecraft**
          (涓夎€呴兘鐢卞悓涓€涓?TransformingClassLoader 瀹氫箟)
```

涔熷氨鏄:**OptiFine 鐨勭被涓庢父鎴忕被浣忓湪鍚屼竴涓姞杞藉櫒涓?*,鎺㈤拡鐢ㄥ畠鍔犺浇 `ValueOutput` 鏄垚鍔熺殑銆?
澶辫触鐨勮В鏋愭潵鑷?URLClassLoader,鑰岄偅涓ゆ潯 URL 绫昏矾寰勪笂**娌℃湁娓告垙绫?*(涔熸病鏈?`optifine-classes.jar`)銆?
鍐嶆妸闈欐€佽瘉鎹帴涓婂幓,鏁存潯閾鹃棴鍚?

1. `net/optifine/reflect/Reflector.class` 閲屽惈瀛楃涓?**`IForgeBlockEntity`** 鈬?OptiFine 鐨?Reflector 琛ㄩ噷鏈夎繖涓€鏉?
2. 鎴戜滑鐨勬瀯寤烘妸杩欎釜鎺ュ彛浣滀负 **Forge shim 鐢熸垚**骞舵斁杩?`optifine-payload.jar`,璺緞鏄?*鐪熷寘鍚?*
   `net/minecraftforge/common/extensions/IForgeBlockEntity.class`;
3. 杩欎釜 shim 鐨勬垚鍛樼鍚嶉噷鏈夋父鎴忕被鍨?`serializeCaps(net.minecraft.world.level.storage.ValueOutput)`;
4. `optifine-payload.jar` 灏卞湪閭ｄ釜鏃╂湡 `URLClassLoader` 鐨勭被璺緞涓?涓婇潰 `own` 鐨?URL 閲?;
5. `ReflectorClass.getTargetClass()` 鏄?**`Class.forName(name)`**(鍙嶇紪璇戠湅鍒?鍙湁杩欎竴澶勩€佹病鏈夋寚瀹氬姞杞藉櫒),
   璇ュ悕瀛楀湪娓告垙妯″潡灞傞噷娌℃湁 鈬?钀藉埌鐖跺姞杞藉櫒(URLClassLoader)涓?鐢卞畠瀹氫箟杩欎釜 shim;
6. `ReflectorMethod.getMethods` 鈫?`getDeclaredMethods()` 鍦ㄩ偅涓?shim 涓婂仛,鍙傛暟绫诲瀷 `ValueOutput`
   鐢?shim 鑷繁鐨勫姞杞藉櫒瑙ｆ瀽 鈬?`URLClassLoader.findClass` 鈬?`ClassNotFoundException`銆?

杩欏氨鏄?26.1.2 閭ｄ釜"鏃╂湡鏈嶅姟灞傝В鏋愬埌鍙︿竴浠藉瓧鑺?鐨?*鍚岀被闂鐨勫彟涓€绉嶈〃鐜?*,鑰屼笖缁欏嚭浜嗗仛娉?
**shim 涓嶈兘浠ョ湡鍖呭悕寰呭湪浼氳鏃╂湡 URL 绫昏矾寰勫姞杞界殑鍦版柟** 鈥斺€?瀹冧滑瑕佸拰娓告垙绫讳竴鏍风粡 `srg/` 杩欐潯鏃犱汉璁ら鐨勮矾寰勩€?
鐢辨寕杞界偣(ClassProcessor)瑁呰繘娓告垙妯″潡灞?鐢?*鍚屼竴涓?*鍔犺浇鍣ㄥ畾涔夈€傝繖鏄竴鏉℃槑纭殑涓嬩竴姝?
鍒ゆ嵁浠嶆槸 `STARTED`銆乣Setting user`銆乻tderr 0 瀛楄妭銆佹棤鏈宕╂簝鎶ュ憡銆?

鍚屼竴娆¤繍琛岀殑鏁板瓧涓?`run-diag2111e` 瀹屽叏涓€鑷?`[OptiFine]` 182銆佽 391 涓被銆乣Setting user` 鉁撱€?
stderr 15 860 瀛楄妭),璇存槑鎺㈤拡鏈韩娌℃湁鎵板姩杩欐潯绾裤€?

## 鎵撳寘:1.20.x 鐨勪骇鐗╁嚭鏉ヤ簡(2026-09-18)

涓婁竴杞?`gradlew build` 鍙触鍦ㄧ綉缁溿€傝繖涓€杞厛閲嶈瘯浜嗕袱娆?浠嶆槸
`maven.neoforged.net`:`Remote host terminated the handshake` / `ClosedChannelException`),绗笁娆?
**瓒婅繃浜?`createMinecraftArtifacts`**,浜庢槸闇插嚭浜嗙浜屼釜鎷﹁矾铏?鈥斺€?涓?1.21.x 鍚屼竴涓?浣嗘柟鍚戜笉鍚?

```
:compileJava FAILED
  SrgRemap.java:181: 閿欒: 鏃犳硶灏嗙被 Remapper 涓殑鏋勯€犲櫒 Remapper 搴旂敤鍒扮粰瀹氱被鍨?
    闇€瑕? 娌℃湁鍙傛暟   鎵惧埌: int
```

杩欐潯绾跨殑 `gradle.properties` 鎶?`asm_version` 鍐欐垚 9.8,鑰屾湰绾?NeoForge(20.4.251)鐨?
`modDevApiElements` 閽夌殑灏辨槸閭ｄ竴鐗?9.8 鐨?`Remapper` 娌℃湁 API 鐗堟瀯閫犲櫒銆?*涓嶈兘**鏀规垚鏃犲弬鏋勯€犲櫒
(娴嬮噺杩?閭ｄ細鏀瑰彉閲嶆槧灏勭粨鏋?銆傝€?rig 缂栬瘧鍚屼竴鎵瑰伐鍏风敤鐨勬槸 `libraries/` 閲屾渶鏂扮殑 ASM = **9.10.1**,
鎵€浠ョ収 1.21.x 鐨勫仛娉?鎶?`asm_version` 瀹氫负 **9.10.1**,骞跺湪 `build.gradle` 閲岀敤
`resolutionStrategy.eachDependency` 鍙鏈」鐩嚜宸辩殑 `compileOnly` 渚濊禆缁熶竴鍒拌繖涓€鐗堛€備慨瀹?

```
BUILD SUCCESSFUL in 1m 15s     (Minecraft 1.20.4, NeoForge 20.4.251, Java 17)
build/libs/OptifiNeoforge-0.2.0+mc1.20.4.jar   159 588 瀛楄妭
```

`release/version.ps1 show` 缁欑殑浜х墿鍚嶄笌涔嬩竴鑷淬€?*娌℃湁**鎵撴爣绛俱€?*娌℃湁**寤?Release(鐢ㄦ埛鏈巿鏉冨彂甯?銆?

## 鍏变韩 rig 鏀硅繃涔嬪悗鐨勫洖褰?瑙勭煩瑕佹眰鐨勯偅涓€娆?

杩欎竴杞敼浜?`build-line.ps1` 涓?`build-rig-jar.ps1`(鍔?`OldSecureJarHandler` 涓?
`--old-securejarhandler` 鐨勪紶閫?銆傛寜瑙勭煩閲嶈窇涓€鏉″凡楠岃瘉绾?

| 绾?| VERDICT | `Setting user` | `[OptiFine]` | CTM | 鎹㈣ | stderr | 琛屾暟 |
|---|---|---|---|---|---|---|---|
| 1.21.8 / 21.8.54 | `STARTED (40s, Sound engine started)` | 鉁?| **337**(= 鍩虹嚎) | 3 | 380 | **0 瀛楄妭** | 919 |

涓庤褰曠殑鍩虹嚎(`optifine=337 settingUser=1 sound=1 CTM=38 stderr=0`)**閫愰」涓€鑷?*,骞朵笖
`no crash report from this run` 鈬?鍏变韩 rig 鐨勬敼鍔ㄦ病鏈夌鍒板凡楠岃瘉鐨勭嚎(榛樿涓嶅紑鏂板紑鍏虫椂,鍛戒护琛屼笌鏀瑰墠瀹屽叏鐩稿悓)銆?

## 1.21.9 / 1.21.10:鍓嶇疆璧颁簡涓ゆ,鍗″湪鍘熺増 jar(闀滃儚鐨?URL 鏄┖鐨?

OptiFine 閭ｄ竴鍗?*瀹屾垚**(闀滃儚鐨勫厓鏁版嵁涓庢枃浠跺悕閮芥牳杩?:

| 鐗堟湰 | 闀滃儚鍒楀嚭鐨勬瀯寤?| 宸插彇 |
|---|---|---|
| 1.21.9 | `J7_pre1`銆乣J7_pre2` | `preview_OptiFine_1.21.9_HD_U_J7_pre2.jar`(7 664 893 瀛楄妭) |
| 1.21.10 | `J7_pre2` 鈥?`J7_pre11`(10 鏉? | `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar`(7 805 444 瀛楄妭) |

NeoForge 閭ｄ竴鍗?*璧颁簡涓€鍗?*:`prepare-line.ps1 -McVersion 1.21.9 -NfPrefix 21.9` 涓?
`-McVersion 1.21.10 -NfPrefix 21.10` 閮借窇鍒颁簡鏈€鍚?鑰屼笖**瀹夎鍣ㄤ笅鏉ヤ簡** 鈥斺€?
`nf-21.9.16-beta-installer.jar`(6 344 885 瀛楄妭)銆乣nf-21.10.64-installer.jar`(4 145 753 瀛楄妭),
`versions\neoforge-21.9.16-beta\` 涓?`versions\neoforge-21.10.64\` 鐨?profile 涔熼兘鍐欏嚭鏉ヤ簡
(璇存槑 `maven.neoforged.net` 鍦?01:02 / 01:04 閭ｄ袱涓椂鍒绘槸閫氱殑,鏈疆鏃╀簺鏃跺€欏畠瓒呮椂杩?銆?

**鐪熸鍗′綇鐨勬槸鍘熺増 jar,鑰屽師鍥犲湪闀滃儚鐨?JSON 閲?*:`bmclapi2` 缁欑殑鐗堟湰 json 閲?
`downloads.client.url` 鏄?*绌哄瓧绗︿覆**(瀹炴祴 `1.21.9.json`),浜庢槸 `prepare-line.ps1` 鎷煎嚭涓€涓?
"涓绘満鍚嶄负绌?鐨?URI 骞堕噸璇?10 娆?

```
client url:
  attempt 1 failed at  : 鏃犳晥鐨?URI: 鏈兘鍒嗘瀽涓绘満鍚嶃€?
```

鈬?涓ゆ潯绾跨殑鍘熺増 jar 閮借繕鏄?False,`neoforge-21.9.16-beta-client.jar` / `-universal.jar` 涔熸槸 False
(瀹夎鍣ㄩ偅 3鈥? 娆″皾璇曟病鏈変骇鍑哄畠浠?鈥斺€?涓?21.0.167 璁板綍杩囩殑鍚屼竴绉嶅舰鐘?瀹夎鍣ㄨ嚜宸辨姤"缂哄簱",瑕佹墜宸ヨˉ榻愬啀璺?銆?
**涓嬩竴闂?*鏄剼鏈骇鐨?鑰屼笖寰堢獎:`prepare-line.ps1` 鍦ㄩ暅鍍?JSON 鐨?`downloads.client.url` 涓虹┖鏃?
搴斿綋閫€鍥?`piston-meta` 鍙栭偅涓€鏉?URL(瀹冨凡缁忓湪鐢?piston-meta 浣滄暣浠芥竻鍗曠殑鍏滃簳,鍙槸娌＄敤鍦?client url 涓?,
鐒跺悗鎸夎€佸姙娉曟墜宸ヨˉ榻愬畨瑁呭櫒鎶ョ己鐨勫簱;涔嬪悗 1.21.10 鐓у仛,鎸傝浇鐐圭洿鎺ュ鐢?1.21.11 鐨?
`OptifinePayloadClassProcessor`(鍚屼唬 FML 10.0.36)銆?

### 褰撳墠淇(2026-09-18,鏈疆涔嬪悗)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯,鏈疆鎷垮埌 26.x/1.21.x 鐨勫叏閮ㄨ鍒欏悗閲嶆柊閲忚繃;1.20.2/1.20.4 甯﹀凡鐭?Reflector 缂洪櫡) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **宸查獙璇?*(涓冩潯;1.21 甯﹀凡鐭?Reflector 缂洪櫡;1.21.8 鏈疆鍥炲綊涓€鑷? |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **宸查獙璇?*(12/15 鏉? |
| 1.21.x | 1.21.11 | 21.11.45 | 鎸傝浇鐐瑰凡瑁?391 涓被銆乣[OptiFine]` 182 琛?涓ら棶閮藉凡閲忔竻(瑙佷笂),**鏈€氳繃** |
| 1.21.x | 1.21.9 / 1.21.10 | 21.9.16-beta / 21.10.64 | OptiFine 宸插彇;NeoForge 鍗″湪 maven 涓嶅彲杈?|

**鎵撳寘**:`1.20.x` 鐨?`build/libs/OptifiNeoforge-0.2.0+mc1.20.4.jar` 宸蹭骇鍑?159 588 瀛楄妭);
`1.21.x` 涓?`26.x` 涓婁竴杞殑浜х墿涓嶅彉;**鏈彂甯?*(鏃犳爣绛俱€佹棤 Release)銆?

---

## 1.21.11 閫氳繃:涓ら棶閮戒慨鎺?鑰岀浜岄棶鐨勬牴鍥犱笉鏄?`Identifier`(2026-09-18)

涓ゆ潯绾?FML 10 鐨勬寕杞界偣 + 绂荤嚎杞借嵎)閮藉姩杩?鍒ゆ嵁涓嶅彉:`STARTED`銆乣Setting user`銆乻tderr(闄ゆ帀缁堢閭ｄ竴琛?銆?
鏃犳湰娆″穿婧冩姤鍛娿€?

### (a) Forge shim:浠庢棭鏈?classpath 鎼繘娓告垙灞?

涓婁竴杞妸鍥犳灉閾鹃噺鍒颁簡搴?杩欎竴杞彧鏀?*浣嶇疆**,鑰屼綅缃氨鏄叏閮?

- **鏀瑰墠**:56 涓?shim 鐢?`build-payload-2612.ps1 -ShimsDir` 鍐欒繘 `optifine-payload.jar` 鐨?*鐪熷寘鍚?*璺緞
  (`net/minecraftforge/**`)銆傝繖涓?jar 鍚屾椂鏄棭鏈熸湇鍔?jar(`IModFileCandidateLocator` 鏈嶅姟鏂囦欢璁╁畠琚?FML 10
  棰勫姞杞?,鎵€浠ュ畠鐨勬牴绫荤敱涓€涓櫘閫?`URLClassLoader` 瀹氫箟,鑰岄偅涓姞杞藉櫒鐪嬩笉鍒版父鎴忔ā鍧楀眰銆?
- **鏀瑰悗**:`build-2111-chain.ps1` 绗?7 姝ヤ笉鍐嶄紶 `-ShimsDir`,鏂板绗?9 姝ユ妸 `forge-shims-filled` 閲岀殑
  56 涓被鍐欒繘 **`optifine-classes.jar`**鈥斺€旈偅鏄?`mods/` 閲岀殑鏅€?mod 鏂囦欢,瀹冪殑绫荤敱娓告垙灞傞偅涓?
  `TransformingClassLoader` 瀹氫箟,鍜屾父鎴忕被鍚屼竴涓姞杞藉櫒銆傚弽灏勮В鏋?`ReflectorClass.getTargetClass()` =
  `Class.forName`,鐢ㄧ殑鏄皟鐢ㄨ€呰嚜宸辩殑鍔犺浇鍣?鑰?Reflector 灏卞湪鍚屼竴涓ā鍧楅噷)鍥犳鎷垮埌鐨勬槸鑳借В鏋愭父鎴忕被鍨嬬殑 shim銆?

鑴氭湰鑷繁缁欏嚭浜嗗垽鎹?鑰屼笉鏄潬璇绘棩蹇?

```
payload: 4017409 bytes
  finished game classes under srg/: 569; OptiFine's own under srg/: 775
  Forge shims left at real package paths in THIS jar: 0   (0 is the point ...)
classes jar: 1486796 bytes (Forge API shims written into optifine-classes.jar: 56)
```

鏁堟灉(stderr):**15 860 鈫?107 瀛楄妭**,鑰岄偅 107 瀛楄妭涓?*涓嶅甫 mod 鐨勫鐓ц窇閫愬瓧鑺傜浉鍚?*(`logs\run-ctrl2111c`
鐨?stderr 涔熸槸 107 瀛楄妭,鍐呭鍙湁 log4j 閭ｅ彞 `Advanced terminal features are not available in this
environment`)鈬?杩欎竴杞箣鍚?杩欐潯绾跨殑 stderr 閲屾病鏈変竴涓瓧鑺傛潵鑷垜浠€備慨鎺夌殑涓ゆ潯鏄?

```
java.lang.NoClassDefFoundError: net/minecraft/core/HolderLookup$Provider
  at java.lang.Class.getDeclaredMethods0(Native Method)
  at net.optifine.reflect.ReflectorMethod.getMethods(...)
Caused by: java.lang.ClassNotFoundException ... at java.net.URLClassLoader.findClass
```
琚灇涓剧殑閭ｄ釜绫绘槸 shim `net/minecraftforge/common/extensions/IForgeBlockEntity`(瀹炴祴:56 涓?shim 閲屽彧鏈夊畠
鍚屾椂鍚?`HolderLookup$Provider` 涓?`serializeCaps(...ValueOutput)` 杩欑被娓告垙绫诲瀷绛惧悕)銆?

### (b) 閭ｄ釜 null 鐨勬潵婧?`Reflector` 琛ㄩ噷鐨勪竴鏉?*娓告垙绫?*鎴愬憳,涓嶆槸 `Identifier`

涓婁竴杞妸 null 閽夊埌浜?`TagConventionLogWarning` 鐨勬暟缁勫瓧闈㈤噺绗?149 椤?婧愮爜琛?201 鈫?鍋忕Щ 2942,
`createForgeMapEntry(Registries.ITEM, "dyes/black", Tags$Items.DYES_BLACK)`)銆傝繖涓€杞厛璇?`Tags$Items`,
闂鐨勪竴鍗婂氨娌′簡:

```
DYES       = tag("dyes")              -> ItemTags.create(Identifier.fromNamespaceAndPath("c","dyes"))
DYES_BLACK = DyeColor.BLACK.getTag()  -> 琚崲瑁呯殑閭ｄ釜 DyeColor 鑷繁鐨勫瓧娈?
```

鎵€浠?涓轰粈涔?`DYES` 涓嶆槸 null 鑰?`DYES_BLACK` 鏄?鐨勭瓟妗堝緢绠€鍗?涓よ€呮牴鏈笉鏄悓涓€绉嶈〃杈惧紡銆傚啀鐪嬭鎹㈣鐨?
`DyeColor` 鏋勯€犲櫒(payload 閲岄偅浠?,閾炬潯闂悎:

```
this.dyesTag = (TagKey) Reflector.ForgeItemTags_create.call("forge", "dyes/" + translationKeyIn);
```

鑰?`Reflector.ForgeItemTags` **涓嶆槸涓€涓?Forge 绫?*:`Reflector.<clinit>` 閲屽畠鏄?
`new ReflectorClass(net.minecraft.tags.ItemTags.class)` + `makeMethod("create", String.class, String.class)`
鈥斺€斿嵆 **Forge 褰撳勾缁欐父鎴忕被 `ItemTags` 鍔犵殑鍙屽弬鏁伴噸杞?*銆侼eoForge 鐨?`ItemTags` 鍙墿
`create(Identifier)`(瀹炴祴 `javap`),浜庢槸鏌ヤ笉鍒版柟娉?`ReflectorMethod.call` 鎸夎璁?*闈欓粯杩斿洖 null**,
`dyesTag`/`dyedTag` 鍗佸叚涓鑹插叏鏄?null,`Tags$Items.DYES_BLACK` 鏄?null,NeoForge 鑷繁鐨?
`TagConventionLogWarning.<clinit>` 鎶?NPE,`ExceptionInInitializerError` 鈫?`Mod loading failures`銆?

**杩欐潯缂洪櫡鐨勫舰鐘跺€煎緱鍗曠嫭璁颁綇**:OptiFine 閫氳繃鍙嶅皠璇?Forge API,缂虹洰鏍囨椂**涓嶆姤閿?*,鎶婁竴涓?null 鍐欒繘娓告垙绫荤殑
瀛楁;璇诲畠鐨勬槸涓変釜绫讳箣澶栫殑 NeoForge 浠ｇ爜銆傝浇鍏?mod 鏃剁湅涓嶅埌,宕╂簝鐐圭鍘熷洜寰堣繙銆?

### 涓や釜鏂板伐鍏?26.x `src/main/java/.../optifine/`,鐢?rig 缂栬瘧)

| 宸ュ叿 | 浣滅敤 | 瀹炴祴鏁板瓧(1.21.11) |
|---|---|---|
| `ReflectorGaps` | 浠?`Reflector.<clinit>` 鐨勭洿绾垮瓧鑺傜爜閲?*杩樺師鏁村紶鍙嶅皠琛?*(鐩爣绫汇€佹垚鍛樺悕銆佸弬鏁扮被鍨?,鍐嶆壂杞借嵎閲屽瀹冪殑鐢ㄦ硶,鎶ュ嚭"杩愯鏃舵病鏈夎繖涓洰鏍?鐨勬潯鐩?骞舵爣鍑?*鎶婅繑鍥炲€煎啓杩涘瓧娈?*鐨勯偅浜?| 琛?242 鏉?鏂规硶 127 / 瀛楁 12 / 绫?103);鎵?568 涓浇鑽风被銆?43 澶勭敤娉?缂虹洰鏍?8 鏉?**鍏朵腑 1 鏉℃槸 stored** |
| `ForgeEraMembers` | 缁?stored 鐨勯偅浜涜ˉ**鐪熷疄鐜?*(涓嶆槸绌哄３),鍐欒繘杞借嵎 `srg/` 涓?鐢辨寕杞界偣鐓у父瑁?| `net/minecraft/tags/ItemTags.create(String,String)`:杩愯鏃剁被 16 459 鈫?16 781 瀛楄妭 |

`ItemTags.create(String namespace, String path)` 鐨勫疄鐜版槸鎶?`forge` 鏄犲皠鍒版湰杩愯鏃剁殑甯歌鍛藉悕绌洪棿 `c`,
鍏朵綑鍛藉悕绌洪棿鍘熸牱浼犻€掆€斺€旇繖涓嶆槸鐚滅殑:NeoForge 鑷繁鐨?`Tags$Items.tag(String)` 灏辨槸
`ItemTags.create(Identifier.fromNamespaceAndPath("c", name))`,vanilla 鐨?`DyeColor` 鏋勯€犲櫒鍚屾牱鐢?`"c"`,
鎵€浠ヨ繖鏍峰仛鍑烘潵鐨勫€间笌"杩欎釜杩愯鏃舵湰鏉ヤ細绠楀嚭鐨勫€?涓€鑷淬€?

### 瀹炴祴(2026-09-18)

| 杩愯 | 杞借嵎 | VERDICT | `Setting user` | `Sound engine` | `[OptiFine]` | 瑁呬笂鐨勭被 | stderr | 鏈宕╂簝鎶ュ憡 |
|---|---|---|---|---|---|---|---|---|
| `run-fix2111a` | 鏈洖濉?`-SkipRestore` 鐨勫潙,瑙佷笅) | EXITED | 鉁?| 鉁?| 26 | 167 | 107 瀛楄妭 | 瀹㈡埛绔穿婧?`NoSuchMethodError RenderTarget.<init>(String,ZZ)` |
| `run-fix2111b` | 瀹屾暣(鍥炲～ 398 涓垚鍛? | STARTED | 鉁?| 鉁?| **196** | 400 | 107 瀛楄妭 | 鏃?|
| **`run-fix2111c`(楠屾敹)** | 鍚屼笂 | **STARTED (40s, Sound engine started)** | 鉁?| 鉁?| **196** | **400** | **107 瀛楄妭**(=瀵圭収璺戠殑 107) | **鏃?* |
| `run-ctrl2111c`(鍚?profile,**涓嶅甫涓や釜 jar**) | 鈥?| STARTED (40s) | 鈥?| 鉁?| 鈥?| 鈥?| 107 瀛楄妭 | 鏃?|
| `run-reg2111fix1218`(鍥炲綊,1.21.8 / 21.8.54) | 涓婁竴杞骇鐗?| STARTED (40s) | 鉁?| 鉁?| **337**(=鍩虹嚎) | 鈥?| **0 瀛楄妭** | 鏃?|

閾捐矾鏋勫缓鏁板瓧(`build-2111-chain.ps1 -SkipPatch`,鍥炲～瀹屾暣):姣旇緝 566 涓鏇挎崲绫汇€?
璁″垝鍥炲～ 367 涓垚鍛樸€?*瀹為檯 restored 398 member(s) across 92 class(es)銆? 涓被娌℃崘浣?*銆?
57 涓?Forge 绫诲瀷 40 涓垚鍛樸€?6 涓?shim(鍏朵腑 5 涓ˉ浜?default 鏂规硶)銆佹垚鍝佹父鎴忕被 **569** 涓?
(568 + 琛ヤ笂鏉ョ殑 `ItemTags`)銆佽浇鑽?`optifine-payload.jar` **4 017 409 瀛楄妭**銆?
OptiFine 鑷繁鐨勭被 775 涓繘 `srg/` 鍙﹀姞 `optifine-classes.jar` **1 486 796 瀛楄妭**(modId `optifineclasses`)銆?

**韪╄繃鐨勫潙,璁颁笅鏉ョ渷涓嬩竴娆?*:`-SkipRestore` 浼氭妸 `$restored` 璁惧洖 `$repatched`(鍗?*鏈洖濉?*鐨?jar),
鎵€浠ラ偅娆￠摼鏄?鍚堟硶鍦?鐢ㄦ湭鍥炲～鐨勭被瑁呯殑杞借嵎,浜庢槸鍑虹幇
`NoSuchMethodError: void RenderTarget.<init>(String, boolean, boolean)`(`MainTarget.<init>` 鈫?
`ClientHooks.instantiateMainTarget`)鈥斺€旇€屽洖濉鍒掗噷鏄庢槑鏈夎繖涓€鏉?
(`M com/mojang/blaze3d/pipeline/RenderTarget <init> (Ljava/lang/String;ZZ)V`),
鍥炲～鍚庣殑绫婚噷涔熺湡鐨勬湁銆傞偅涓紑鍏崇殑鍚箟鏄?璺宠繃鍥炲～",涓嶆槸"娌跨敤涓婃鍥炲～鐨勪骇鐗?銆?

### 杩樻病淇?鍙﹀ 7 鏉?gap(宸查噺鍑?涓嶅湪鏈疆缁撹閲?

`ReflectorGaps` 鎶ョ殑 8 鏉￠噷,鍙湁 1 鏉℃妸杩斿洖鍊煎啓杩涘瓧娈?鍓╀笅 7 鏉￠兘鏄?*璋冪敤鍚庣珛鍒荤敤**鐨?鎵€浠ヤ笉浼氬穿,
浣嗛兘闈欓粯缁欏嚭榛樿鍊?false/null/void):

| 鏉＄洰 | 鐩爣(杩愯鏃剁己) | 璋佸湪璋?|
|---|---|---|
| `ChunkAccess_getWorldForge` | `ChunkAccess.getWorldForge` | `ChunkMap` |
| `ForgeBlockElementFace_data` | `BlockElementFace.data` | `FaceBakery` |
| `ForgeEntity_isInWaterOrSwimmable` | `Entity.isInWaterOrSwimmable` | `LivingEntityRenderer` |
| `ForgeItemBlockRenderTypes_isFancy` | `ItemBlockRenderTypes.isFancy` | `SingleVariant` |
| `ForgeKeyBinding_setKeyConflictContext` | `KeyMapping.setKeyConflictContext` | `Options` |
| `ForgeParticleResources_getProvider` | `ParticleResources.getProvider` | `ParticleEngine` |
| `TerrainParticle_updateSprite` | `TerrainParticle.updateSprite` | `ClientLevel` |

**涓嬩竴闂?*(绐勪笖鍙垽):杩?7 鏉″悇鑷?榛樿鍊?涓庣湡鍊煎樊澶氬皯 鈥斺€?渚嬪 `ParticleResources.getProvider` 杩斿洖 null
浼氫笉浼氳 `ParticleEngine` 灏戜竴璺矑瀛愩€乣isFancy` 鎭?false 浼氫笉浼氳蹇?绮捐嚧妯″瀷閫夋嫨璧伴敊銆?
鍒ゆ嵁鏄瘡涓€鏉＄粰鍑?OptiFine 渚х殑鍙瀵熷樊寮?(涓嶆槸"娌″穿灏辩畻杩?)銆?

### 1.21.9 / 1.21.10:鍘熺増 jar 鎷垮埌浜?NeoForge 瑁呭畬浜?

涓婁竴杞崱鍦ㄩ暅鍍忕殑 `downloads.client.url` 涓虹┖;杩欎竴杞疄娴嬪彂鐜版瘮"绌?鏇寸碂:**闀滃儚閭ｄ唤 version json
PowerShell 5.1 鏍规湰瑙ｆ瀽涓嶄簡**(`Invalid JSON primitive: 4 97 114 103 ...`,姝ｆ槸 `"Arguments"` 鐨勫瓧鑺?,
鑰?*鍚姩鍣ㄨ璇荤殑姝ｆ槸杩欎釜鏂囦欢**(libraries / assetIndex / arguments),鎵€浠ュ畠涓嶅彧鏄笅杞芥簮鐨勯棶棰樸€?

`prepare-line.ps1` 鐨勪慨娉?绐?涓夋潯):

1. 鍙栦笉鍒?client url 鏃堕€€鍥?`piston-meta`(`Get-PistonVersionJson`:娓呭崟 鈫?鐗堟湰 json 鈫?`downloads.client`),
   骞朵笖**鎶?piston-meta 鐨勯偅浠?json 鍐欏洖鍘熻矾寰?*(闀滃儚閭ｄ唤鐣欎綔 `<version>.json.mirror`)鈥斺€斿洜涓轰笅杞?URL 鍙槸
   杩欎釜鏂囦欢閲岀殑涓€涓瓧娈?
2. 涓嬭浇鍚?*鏍稿 sha1**(涓や釜鏉ユ簮鏄笉鍚屼富鏈?涓嶆牳瀵圭殑璇濆悗闈㈡墍鏈夋祴閲忛兘娌℃湁鎰忎箟);
3. 鏂板 `Test-Fml10Install`:杩欎竴浠ｅ畨瑁呭櫒**涓嶄骇鍑?`-client.jar`**,瀹冭窇 `PROCESS_MINECRAFT_JAR` 鍐欏嚭
   `minecraft-client-patched-<ver>.jar` 骞舵墦鍗?`Successfully installed client into launcher`,鑰岃剼鏈師鏉ユ嵁姝ゆ姤
   "client jar present: False" 骞?*鐧借窇鍥涙瀹夎鍣?*(1.21.10 涓婂疄娴?銆傜幇鍦ㄦ寜"profile 瀛樺湪 + mainClass 鏄?
   `net.neoforged.fml.startup.Client` + patched jar 瀛樺湪"鍒ゅ畾銆?

瀹炴祴:

| 绾?| 鍘熺増 jar | sha1 | 瀹夎鍣?| 缁撴灉 |
|---|---|---|---|---|
| 1.21.10 / 21.10.64 | 30 592 168 瀛楄妭 | 涓?piston-meta 涓€鑷?| 绗?1 娆″嵆瀹屾垚;鎵嬪伐琛?4 涓簱(earlydisplay 10.0.32 360 274銆乴oader 10.0.32 660 036銆乶eoform 1.21.10-20251010.172816 mappings 541 275銆乽niversal 21.10.64 3 805 764) | profile 鉁撱€乣minecraft-client-patched-21.10.64.jar` 32 903 514 瀛楄妭 鉁撱€乽niversal 鉁撱€?*鏈唬鏃?`-client.jar`** |
| 1.21.9 / 21.9.16-beta | 30 591 861 瀛楄妭(`ce92fd8d鈥) | 涓?piston-meta 涓€鑷?| 绗?1 娆℃姤缂?5 涓簱骞舵墜宸ヨˉ榻?earlydisplay/loader 10.0.14銆乻ponge-mixin 0.16.4銆乶eoform 1.21.9-20250930.151910銆乽niversal 21.9.16-beta),绗?2 娆″畬鎴?| profile 鉁撱€乣neoforge-21.9.16-beta-client.jar` 鉁撱€乽niversal 鉁?|

涓ゆ潯绾跨殑 OptiFine jar 涓婁竴杞凡鍙?1.21.9 `J7_pre2` 7 664 893 瀛楄妭銆?.21.10 `J7_pre11` 7 805 444 瀛楄妭)銆?
**涓嬩竴姝?*鏄繖涓ゆ潯绾垮鐢?1.21.11 鐨勬寕杞界偣:鍚屼竴浠?FML 10,浣?`OptifinePayloadClassProcessor` 鐩墠鎸?
`loader-10.0.36` 缂栬瘧銆乣build-2111-chain.ps1` 鎶?10.0.36 鐨勮矾寰勫啓姝讳簡,鑰?21.10 鐢?loader **10.0.32**銆?
21.9 鐢?**10.0.14** 鈬?宸簨鏄妸杩欎笁涓矾寰勫弬鏁板寲(鎸傝浇鐐规湰韬笉闅忚鍙?,鍐嶅悇璺戜竴娆￠摼銆?

**"鎸傝浇鐐逛笉闅忚鍙?杩欏彞鏄噺杩囩殑,涓嶆槸鎺ㄦ柇鐨?*:`src/fml10/java` 閭ｄ袱涓簮鏂囦欢**涓€涓瓧鑺傛病鏀?*,
鍒嗗埆瀵圭潃 `loader-10.0.32.jar` 涓?`loader-10.0.14.jar` 缂栬瘧,**涓ゆ閮芥槸 exit 0銆佸悇浜у嚭 2 涓被**(瀹炶窇)銆?

### 褰撳墠淇(2026-09-18,鏈疆涔嬪悗)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯;1.20.2/1.20.4 甯﹀凡鐭?Reflector 缂洪櫡) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **宸查獙璇?*(涓冩潯;1.21 甯﹀凡鐭?Reflector 缂洪櫡;鏈疆 1.21.8 鍥炲綊 337/0 瀛楄妭,涓庡熀绾夸竴鑷? |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **宸查獙璇?* |
| 1.21.x | **1.21.11** | 21.11.45 | **宸查獙璇?*(`STARTED` + `Setting user` + `Sound engine` + `[OptiFine]` 196 琛?+ 瑁?400 涓被 + stderr 107 瀛楄妭=瀵圭収璺戠殑 107 + 鏃犲穿婧冩姤鍛?鈬?**13/15 鏉?* |
| 1.21.x | 1.21.9 / 1.21.10 | 21.9.16-beta / 21.10.64 | OptiFine 鉁撱€佸師鐗?jar 鉁撱€丯eoForge 瑁呭畬 鉁?宸?鍙傛暟鍖栨寕杞界偣缂栬瘧鐢ㄧ殑 loader 鐗堟湰"鍚庡悇璺戜竴娆￠摼 |

**鎵撳寘**:浜х墿涓嶅彉(1.20.x `OptifiNeoforge-0.2.0+mc1.20.4.jar` 159 588銆?.21.x 涓?26.x 涓婁竴杞骇鐗?;
**鏈彂甯?*(鏃犳爣绛俱€佹棤 Release)銆?








## 1.21.10 涓?1.21.9:涓€鏉￠摼绠′笁鏉＄嚎,鍥涗釜缂洪櫡,15/15(2026-09-18)

杩欎袱鏉＄嚎鐨?鍓嶇疆"鍦ㄤ笂涓€杞氨绠楀畬鎴愪簡(鍘熺増 jar 涓?sha1銆佸畨瑁呭櫒涓?profile銆丱ptiFine 涓や釜 preview 鏋勫缓),鏈疆鍋氱殑鏄?
**鎶婂畠浠湡鐨勮窇璧锋潵**,鑰屼袱鏉＄嚎鍚勮嚜鏆撮湶鍑哄悓涓€鎵圭己闄?鈥斺€?鍥犱负 1.21.9 / 1.21.10 / 1.21.11 璧扮殑鏄悓涓€鏉￠摼銆佸悓涓€涓寕杞界偣,
宸埆鍙湁 loader 鐗堟湰銆丯eoForge 鐗堟湰鍜屽師鐗?jar 涓変釜鍚嶅瓧銆?

### 涓€銆佸厛琛ラ綈涓ゆ潯绾垮悇鑷己鐨勪袱浠朵簨(閮芥槸瀹炴祴)

1. **鏈満 libraries 閲岀己涓や釜搴?鑰?rig 鐨勫惎鍔ㄥ櫒涓嶄細鎶?*銆俙1.21.9.json` / `1.21.10.json` 閲岃
   `com.mojang:jtracy:1.0.36` 涓?`io.netty:netty-codec-http:4.1.118.Final`,涓や唤閮戒笉鍦ㄣ€?
   缂?jtracy 鐨勫悗鏋滀笉鏄?灏戜釜鍔熻兘",鑰屾槸**杩?pre-bootstrap 閮借繃涓嶅幓**:

   ```
   java.lang.NoClassDefFoundError: com/mojang/jtracy/TracyClient
     at net.minecraft.client.main.Main.main(Main.java:115)
     at net.neoforged.fml.startup.Client.main(Client.java:19)
   Description: Pre-bootstrap
   ```

   鎸夌増鏈?json 閲岀殑 url 鍙栧洖骞堕€愬瓧鑺傛牳瀵?sha1:`jtracy-1.0.36.jar` 12 876 瀛楄妭(`20a63d06鈥)銆?
   `jtracy-1.0.36-natives-windows.jar` 47 599 瀛楄妭(`b42bc771鈥)銆?
   `netty-codec-http-4.1.118.Final.jar` 674 362 瀛楄妭(`eda08a71鈥)銆?
   椤哄甫璁颁竴绗?rig 鐨勭己鍙?鍚姩鍣ㄥ彧鍦?*profile 鑷繁**鐨?libraries 缂哄け鏃舵墦鍗?`!! N libraries not found`,
   浠?`inheritsFrom` 閭ｄ竴鐗堢户鎵挎潵鐨勫簱缂哄け鏃舵槸**闈欓粯璺宠繃**,鎵€浠ヨ繖涓€绫婚棶棰樺彧鑳介潬鑷繁姣斿鐗堟湰 json 鍙戠幇銆?

2. **FML 10 绾跨殑鏃╂湡绐楀彛:灞炴€у悕鏄?ModLauncher 鏃朵唬鐨?鍦?FML 10 涓婃槸绌烘皵**銆?
   rig 鐨勫惎鍔ㄥ櫒缁欐瘡鏉＄嚎閮藉姞 `-Dfml.earlyprogresswindow=false`,浣?FML 10.0.32 / 10.0.14 鍙娓告垙鐩綍閲?
   `config/fml.toml` 鐨?`earlyWindowProvider`;鏂板缓鐨勬父鎴忕洰褰曢粯璁ゆ槸 `fmlearlywindow`銆傚疄娴?鍚屼竴浠借浇鑽枫€佸悓涓€涓洰褰?
   鍙樊杩欎竴琛?:

   | `earlyWindowProvider` | 瑙傛祴 | 缁撴灉 |
   |---|---|---|
   | `fmlearlywindow`(榛樿) | 鏃ュ織:`Loading ImmediateWindowProvider fmlearlywindow`;绾?8-10 绉掑悗 `IllegalStateException: Already building.` 钀藉湪 `SimpleBufferBuilder.begin 鈫?RenderContext.renderText/blitTextureRegion 鈫?PerformanceElement.render 鈫?LoadingScreenRenderer.renderToFramebuffer 鈫?DisplayWindow.renderToFramebuffer 鈫?NeoForgeLoadingOverlay.render`,鏈杩愯鐨?`crash-鈥?client.txt` 涓€浠?| **EXITED(15s)**,鏍囬鐢婚潰閮芥病鍒?|
   | `none` | 鏃ュ織:`Failed to find ImmediateWindowProvider none, disabling` | **STARTED(40s)** |

   涓夋甯?`fmlearlywindow` 鐨勫惎鍔ㄩ兘宕╁湪鍚屼竴澶?`run-final2110a`銆乣run-fresh2110a`銆乣run-ab2110on`),
   鏀?`none` 涔嬪悗鍥涙鍏ㄩ儴姝ｅ父銆傚凡鏍稿 1.21.11 涓?1.21.8 鐨勬父鎴忕洰褰曢噷鏈潵灏辨槸 `none`
   鈥斺€?涔熷氨鏄?*杩欎笉鏄柊缂洪櫡,鏄繖涓ゆ潯绾跨殑鐩綍杩樻病琚杩?*銆?
   涓嬩竴闂?绐?:rig 鐨勫惎鍔ㄥ櫒瑕佷箞鎸?FML 浠ｆ鍐欒繖涓€琛?瑕佷箞鎶婇偅鏉″け鏁堢殑 `-Dfml.earlyprogresswindow` 娉ㄩ噴鏀规帀銆?

### 浜屻€乣build-2111-chain.ps1` 鍙傛暟鍖?涓夋潯绾夸竴鏉￠摼

| 鍙傛暟 | 1.21.11(榛樿,宸查獙璇侀偅鏉? | 1.21.10 | 1.21.9 |
|---|---|---|---|
| `-McVersion` | `1.21.11` | `1.21.10` | `1.21.9` |
| `-NeoForgeVersion` | `21.11.45` | `21.10.64` | `21.9.16-beta` |
| `-LoaderVersion`(鎸傝浇鐐圭紪璇戠敤鐨?loader jar) | `10.0.36` | `10.0.32` | `10.0.14` |
| `-RuntimeJar`(姣斿/鍥炲～鐨?杩愯鏃堕偅涓€渚?) | `minecraft-client-patched-21.11.45.jar`(榛樿鎺ㄥ) | `minecraft-client-patched-21.10.64.jar`(榛樿鎺ㄥ) | **`neoforge-21.9.16-beta-client.jar`**(鏄惧紡) |

`src/fml10` 涓や釜婧愭枃浠朵竴涓瓧鑺傛病鏀?鍙崲 loader jar銆?.21.9 鐨勮繍琛屾椂閭ｄ竴渚ц鏄惧紡缁?鏄洜涓?*閭ｄ竴浠ｅ畨瑁呭櫒
鐢ㄧ殑鏄€佺殑 `--clean`/`--apply` 璺緞**,浜у嚭鐨勬槸 `neoforge-21.9.16-beta-client.jar`(鍙湁绫?,鑰屼笉鏄?
`minecraft-client-patched-*.jar`;FML 10.0.14 璁よ繖涓舰鐘?`GameLocator.locateProductionMinecraft` 鍦?
`minecraft-client-patched` 缂哄け鏃跺洖閫€鍒?`net.minecraft:client:<mc>-<neoform>:srg` + `:extra` +
`net.neoforged:neoforge:<ver>:client`,鍚姩鏃ュ織閲屼篃鑳界湅鍒?`minecraft (composite(jar(鈥?srg.jar), jar(鈥?extra.jar), jar(鈥?client.jar)))`)銆?
瀹冧綔涓?琛ヤ竵鍚庣殑娓告垙绫?杩欎竴渚х殑鏇夸唬鏄噺杩囩殑:srg jar 9969 涓被銆乧lient jar 9981 涓被,瀵圭О宸彧鏈?1 / 13 涓?閮芥槸琛ヤ竵鏂板鐨勫悎鎴愮被)銆?
涓や釜 metadata 妯℃澘鐜板湪**缂哄垯鐢熸垚銆佸湪鍒欎笉瑕嗙洊**,1.21.11 閭ｄ袱涓凡楠岃瘉鐨勬ā鏉挎案杩滀笉浼氳閲嶅啓銆?

### 涓夈€佸洓涓己闄?姣忎竴涓兘鐢变竴娆″惎鍔ㄩ噺鍑烘潵

| # | 鐜拌薄 | 鏍瑰洜(瀹炴祴) | 淇硶 |
|---|---|---|---|
| 1 | `NoClassDefFoundError: net/minecraft/resources/Identifier`(`DyeColor.<clinit>` 鈫?鎴戜滑渚涚粰鐨?`ItemTags.create`),闅忓悗 38 娆?`Cowardly refusing to send event 鈥?to a broken mod state`,OptiFine 鐨勭簿鐏甸噰闆嗘案涓嶅彂鐢?瀹㈡埛绔案杩滄墦鍗?`[OptiFine] Waiting for model sprites` | `ForgeEraMembers` 鎶?26.x 鐨?*绫诲悕鍐欐**鎴?`Identifier`,鑰?1.21.10 / 1.21.9 杩樺彨 `ResourceLocation`銆傚疄娴?`Identifier.class` 鍙湪 21.11.45 涓?26.1.2.109 鐨勮ˉ涓佸悗 jar 閲?21.10.64 涓?21.9 鐨?client jar 閲屾槸 `ResourceLocation.class` | 宸ュ叿鏀规垚**浠庤繍琛屾椂 jar 閲岃В鏋?*杩欎釜鍚嶅瓧(涓や釜閮藉湪鏃跺彇鏂板悕 鈬?1.21.11 鐨勮緭鍑洪€愬瓧鑺備笉鍙?;涓や釜閮芥病鏈夊氨鎷掔粷鍐?|
| 2 | `VerifyError: Operand stack underflow`,`Location: BlockModelWrapper.<init>(List, List, ModelRenderProperties)V @12: invokestatic`;闅忓悗 `Caught error loading resourcepacks, removing all selected resourcepacks`,瀹㈡埛绔仠鍦ㄥ姞杞界敾闈?| 鎭㈠杩欎釜绫荤殑涓や釜瀹炰緥瀛楁鍒濆€?`renderType`銆乣modelLocation`)鏃?**鍙负鏁翠覆璋冪敤 push 浜嗕竴娆?`aload_0`**,绗簩涓?`invokestatic`(鎻忚堪绗?`(L鈥lockModelWrapper;)V`)鎷垮埌绌烘爤 | `RestoreMembers`:姣忔璋冪敤鍚?push 涓€娆℃帴鏀惰€呫€?*杩欐槸宸ュ叿閲屼竴鐩村瓨鍦ㄧ殑缂洪櫡**,鍏跺畠绾夸笂娌℃湁浠讳綍鏋勯€犲櫒闇€瑕佷袱涓垵鍊?鎵€浠ヤ粠娌″彂浣?|
| 3 | `ExceptionInInitializerError 鈫?IndexOutOfBoundsException: Index 7 out of bounds for length 7`,鏍堟槸 `ModelDiscovery$ModelWrapper.slot 鈫?<clinit> 鈫?ModelDiscovery.<init> 鈫?ModelManager.discoverModelDependencies` | 鎭㈠ NeoForge 鍔犵殑绗叓涓Ы `KEY_ADDITIONAL_PROPERTIES` 鏃?鎶?`slot(7)` 鏀捐繘浜嗕竴涓?*浠嶇劧鍐欑潃 7 鐨勭被**:`SLOT_COUNT=7`銆乣slot(int)` 鐨勭晫鏄?7銆佹瀯閫犲櫒閲岀殑 `AtomicReferenceArray` 涔熸槸 7(OptiFine 杩欎唤鏄寜 7 妲界殑鍘熺増缂栫殑) | 鏂板伐鍏?`SlotLayoutRepair`:鎷胯繍琛屾椂鐨?`SLOT_COUNT`,鍙敼杩欎笁澶勬暟瀛?7 鈫?8),涓夊缂轰换浣曚竴澶勫氨鎷掔粷鍐?闃叉"鍗婂榻?鐨勭被鍦ㄥ埆澶勭偢 |
| 4 | 涓婁竴杞?1.21.10 鍋滃湪 `Waiting for model sprites`(淇ソ #1 涔嬪悗浠嶅湪) | 涓?1.21.x 绾夸笂閲忓埌杩囩殑**鍚屼竴涓己闄风殑绂荤嚎鐗?*:`CustomItems.registerIcons` 绛?`modelSpritesUpdated`,鍞竴鍐欏畠鐨勬槸 `CustomItems.collectModelSprites`,鑰岃浇鑽烽噷鐨勮繖涓皟鐢ㄨ涔堝湪 NeoForge 鎵╁杩囩殑鍥涘弬閲嶈浇閲?浠庤繍琛屾椂鎭㈠,浣撳唴娌℃湁杩欎釜璋冪敤),瑕佷箞鍦?OptiFine 鑷繁閭ｄ唤鐨?`if(Config.isCustomItems())` 鍚庨潰 | 鏂板伐鍏?`SpriteCollectionRepair`:鍦ㄤ袱涓噸杞界殑**澶撮儴**鍚勬彃涓€娆?`CustomItems.collectModelSprites(map)`(涓?1.21.x loader 鐨?`repairSpriteCollection` 鍚屼竴鏉¤鍒? |

鏂规硶绾х殑璇佹嵁(姣忔潯閮藉彲鍦ㄦ湰杞骇鐗╅噷澶嶇幇):

* #2:`javap` 淇墠 `8: aload_0; 9: invokestatic optifineoforge$init$renderType; 12: invokestatic optifineoforge$init$modelLocation`,淇悗 `12: aload_0; 13: invokestatic 鈥?modelLocation`銆?
* #3:杩愯鏃?`slot(int)` 鏄?`bipush 8; Objects.checkIndex`,杞借嵎鏄?`bipush 7`;淇悗杞借嵎 `SLOT_COUNT: 7 鈫?8`銆佺晫 8銆佹暟缁?8銆?
* #4:绾跨▼杞偍(`logs/jstack-2110.txt`)鎶婄瓑寰呯殑閭ｄ釜浜洪拤鍦?
  `net.optifine.Config.sleep 鈫?net.optifine.CustomItems.registerIcons 鈫?net.optifine.util.TextureUtils.registerCustomSprites
  鈫?net.minecraft.client.renderer.texture.TextureAtlas.preStitch 鈫?SpriteLoader.lambda$loadAndStitch$7
  鈫?SimpleReloadInstance.lambda$prepareTasks$0`;
  淇悗鍚屼竴娆℃棩蹇楅噷 `CustomItems: Collecting model sprites` 2 娆°€乣Registering sprites` 1 娆°€乣Waiting for model sprites` **0** 娆°€?

### 鍥涖€佸疄娴?2026-09-18)

| 绾?| NeoForge | 杞借嵎 | VERDICT | `Setting user` | `Sound engine` | `[OptiFine]` | 瑁呬笂鐨勭被 | `Pre-stitch` | stderr | 鏈 crash |
|---|---|---|---|---|---|---|---|---|---|---|
| **1.21.10** | 21.10.64 | 3 882 296 B | `STARTED (40s, Sound engine started)` | 鉁?| 鉁?| **280** | **394** | 13 | **0 瀛楄妭** | 鏃?|
| 1.21.10 瀵圭収(鍚?profile/鍚岀洰褰?鏃?mod) | 21.10.64 | 鈥?| `STARTED (40s)` | 鉁?| 鉁?| 0 | 鈥?| 鈥?| **0 瀛楄妭** | 鏃?|
| **1.21.9** | 21.9.16-beta | 3 807 587 B | `STARTED (40s, Sound engine started)` | 鉁?| 鉁?| **289** | **383** | 13 | **0 瀛楄妭** | 鏃?|
| 1.21.9 瀵圭収(鍚?profile/鍚岀洰褰?鏃?mod) | 21.9.16-beta | 鈥?| `STARTED (40s)` | 鉁?| 鉁?| 0 | 鈥?| 鈥?| **0 瀛楄妭** | 鏃?|
| 1.21.9 棣栨鍚姩(鐩綍閲屽凡鏈?`options.txt`銆佽繕娌℃湁 `optionsof.txt`) | 21.9.16-beta | 鍚屼笂 | `STARTED (40s)` | 鉁?| 鉁?| 290 | 383 | 13 | 673 瀛楄妭(瑙佷簲-1) | 鏃?|
| 鍥炲綊 **1.21.11** | 21.11.45 | 4 017 419 B | `STARTED (40s, Sound engine started)` | 鉁?| 鉁?| **197**(鍩虹嚎 196) | **400**(=鍩虹嚎) | 15 | **107 瀛楄妭**(=瀹冪殑瀵圭収) | 鏃?|

* 1.21.11 鐨?`[OptiFine]` 196 鈫?197 涓庢棩蹇楄鏁?708 鈫?709 閮藉彧澶氫竴琛?姝ｆ槸 #4 閭ｆ潯淇璁?
  `CustomItems: Collecting model sprites` 鍦ㄥ洓鍙傞噸杞介噷涔熸墦鍗颁竴娆?瑁呬笂鐨勭被浠嶆槸 400銆乻tderr 浠嶆槸 107 瀛楄妭銆?

閾捐矾鏋勫缓鏁板瓧:

| 绾?| 姣旇緝鐨勮鏇挎崲绫?| 璁″垝鍥炲～ | 瀹為檯鍥炲～ | reparent | 鎴愬搧娓告垙绫?| payload | classes jar | Reflector 琛?/ gap |
|---|---|---|---|---|---|---|---|---|
| 1.21.10 | 549(720 鏃犲搴? | 349 | **384 / 89 绫?* | 1 | 552 | 3 882 296 B | 1 431 539 B | 247 / 8(1 stored) |
| 1.21.9 | 515(718 鏃犲搴? | 342 | **375 / 88 绫?* | 1 | 518 | 3 807 587 B | 1 427 651 B | 246 / 6(1 stored) |
| 1.21.11(鍥炲綊) | 566 | 365 | **398 / 92 绫?* | 1 | 569 | 4 017 419 B | 1 486 796 B | 247 / 8(1 stored) |

涓ゅ鏂板伐鍏风殑鑷垜鎶ュ憡(1.21.10 涓?1.21.9 瀹屽叏鍚屽舰):

```
7d/9 putting OptiFine's sprite collection back on the call path the game uses
      collectModelSprites added to the head of discoverModelDependencies(鈥? 鍙傗€?(OptiFine's own guarded call is still there)
      collectModelSprites added to the head of discoverModelDependencies(鈥? 鍙傗€?(the restored runtime overload, which had no call at all)
7e/9 aligning a restored member with the class layout the runtime grew
      ModelDiscovery$ModelWrapper SLOT_COUNT: 7 -> 8 / 鐣?8 / 鏁扮粍 8
```

1.21.11 閭ｆ潯绾夸笂 7e 鎶ョ殑鏄?杩欎釜绫讳笉鍦ㄨ浇鑽烽噷"(瀹冪殑 OptiFine 鏋勫缓涓嶆浛鎹㈣繖涓被),杩欐槸**缁撴灉鑰屼笉鏄け璐?*,
宸ュ叿鎸夌己甯鐞嗗苟鍘熸牱鍐欏洖銆?

### 浜斻€佽繕娌′慨鐨?浠ュ強绮剧‘鐨勪笅涓€闂?

1. **棣栧惎涓€娆℃€х殑 stderr,涓嶆槸鏈疆鐨勭粨璁?*銆侽ptiFine 鐨?`Options.loadOfOptions` 瀵规枃浠剁殑姣忎竴琛屽仛
   `String[] parts = line.split(":"); key = parts[0]; value = parts[1];`,**娌℃湁闀垮害妫€鏌?*;鍘熺増
   `options.txt` 閲屾湁涓€琛?`lastServer:`(`"lastServer:".split(":")` 鍦?Java 閲岄暱搴︽槸 1),浜庢槸
   `ArrayIndexOutOfBoundsException` 杩涗簡 stderr(1.21.10 679 瀛楄妭銆?.21.9 673 瀛楄妭),
   寮傚父琚?OptiFine 鑷繁鍚炴帀銆佹父鎴忕収甯歌繘鏍囬鐢婚潰銆傚畠鍙湪"鐩綍閲屾湁 `options.txt`銆佽繕娌℃湁 `optionsof.txt`"鐨?
   **绗竴娆?*鍚姩鍑虹幇(閭ｆ鍚姩閲?OptiFine 灏变細鍐欏嚭 `optionsof.txt`,绗簩娆¤捣 stderr 鍥炲埌 0)銆?
   **1.21.11 绾夸笂娼滀紡鐫€鍚屼竴涓己闄?* 鈥斺€?瀹冪殑鐩綍閲屾棭灏辨湁 `optionsof.txt`,鎵€浠ヤ粠娌¤Е鍙戣繃,鏈疆鐨?107 瀛楄妭涓庡畠鏃犲叧銆?
   涓嬩竴闂?绐勪笖鍙垽):瑕佷笉瑕佸湪杞借嵎閲岀粰杩欎釜鏂规硶琛ヤ竴涓暱搴﹀畧鍗?鍒ゆ嵁:棣栨鍚姩鐨?stderr 涔熷洖鍒?0),浠ュ強
   1.21.x / 1.20.x 鍚勭嚎鐨勫悓涓€娈典唬鐮侀暱浠€涔堟牱(瀹冧滑鐨勭洰褰曞悓鏍锋槸澶嶇敤鐨?缂洪櫡鍙兘鍙槸娌¤瑙﹀彂)銆?
2. **1.21.x 鐨?loader 閲屽悓涓€澶?`aload_0` 缂洪櫡**銆俙MemberRestoreTransformer` 閲岄偅娈?缁欐瘡涓瀯閫犲櫒鎻掑垵鍊艰皟鐢?鐨勪唬鐮?
   涓?`RestoreMembers` 淇墠閫愬瓧鐩稿悓(涓€娆?`aload_0` + N 娆?`invokestatic`)銆傚畠鍦ㄥ凡楠岃瘉鐨?7 鏉?1.21.x 绾夸笂娌℃湁鍙戜綔
   (姣忔潯绾夸笂姣忎釜鏋勯€犲櫒鏈€澶氶渶瑕?1 涓垵鍊?,浣嗗畠鏄悓涓€涓己闄枫€備笅涓€闂?鐓?26.x 鐨勪慨娉曟敼鎺?骞堕噸璺戜竴鏉″凡楠岃瘉绾?
   鍒ゆ嵁鏄€愰」绛変簬鍩虹嚎(鏈疆娌℃湁鏀瑰畠,鎵€浠ラ偅 7 鏉＄嚎鐨勭姸鎬佷笉鍙?銆?
3. 1.21.11 鍓╀笅鐨勪袱闂粛鍦ㄥ師鍦?鏈疆娌℃湁鍔?涓冩潯 Reflector gap 鍚勮嚜鐨勫彲瑙傚療浠ｄ环,浠ュ強 196/400 涓庡凡鐭ヨ壇濂借繍琛?
   鐨勯€愰」瀵圭収銆?

### 鍏€佸綋鍓嶄慨璁?2026-09-18,鏈疆涔嬪悗)

| 绾?| 鐗堟湰 | NeoForge | 鐘舵€?|
|---|---|---|---|
| 1.20.x | 1.20.1 / 1.20.2 / 1.20.4 / 1.20.6 | 47.1.106 / 20.2.88 / 20.4.251 / 20.6.141 | **宸查獙璇?*(鍥涙潯;1.20.2/1.20.4 甯﹀凡鐭?Reflector 缂洪櫡;鏈疆鏈姩) |
| 1.21.x | 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 21.0.167 / 21.1.250 / 21.3.97 / 21.4.149 / 21.6.20-beta / 21.7.25-beta / 21.8.54 | **宸查獙璇?*(涓冩潯;1.21 甯﹀凡鐭?Reflector 缂洪櫡;鏈疆鏈姩) |
| 26.x | 26.1.2 | 26.1.2.109(FML 11 + JDK 25) | **宸查獙璇?* |
| 1.21.x | 1.21.9 / 1.21.10 / **1.21.11** | 21.9.16-beta / 21.10.64 / 21.11.45 | **宸查獙璇?*(涓夋潯;鍧?`STARTED` + `Setting user` + `Sound engine` + stderr = 鍚勮嚜瀵圭収 + 鏈鏃犲穿婧冩姤鍛? |

鈬?**15 / 15 鏉＄嚎鍏ㄩ儴宸查獙璇?*銆?.21.11 鏈疆鎸夊洖褰掗噸璺?`[OptiFine]` 197銆佽涓?400銆乻tderr 107 瀛楄妭 = 瀹冪殑瀵圭収)銆?

`optionsof.txt` 閭ｆ潯棣栧惎缂洪櫡鏄墍鏈?FML 10 绾垮叡鏈夌殑宸茬煡椤?涓嶅奖鍝嶅惎鍔?,鍐欏湪浜?1銆?
**鏈彂甯?*(鏃犳爣绛俱€佹棤 Release)銆?
### 涓冦€侀『鎵嬪叧鎺夌殑涓€涓綔浼忕己闄?1.21.x loader 閲屽悓涓€涓?`aload_0`(2026-09-18)

涓婁竴鑺備簲-2 璁扮殑閭ｅ"鍚屼竴娈典唬鐮?鏈疆鏀规帀浜?骞舵寜瑕佹眰閲嶈窇浜嗕竴鏉″凡楠岃瘉绾裤€傛敼鍔ㄦ槸鎶?
"涓€娆?`aload_0` + N 娆?`invokestatic`"鏀规垚"姣忔璋冪敤鍚?push 涓€娆℃帴鏀惰€?(`MemberRestoreTransformer`),
瀵逛竴涓彧鎭㈠ 1 涓垵鍊肩殑鏋勯€犲櫒鏉ヨ**鐢熸垚鐨勫瓧鑺傜爜閫愬瓧鑺備笉鍙?*,鎵€浠ュ畠瀵规湰杞殑 7 鏉?1.21.x 绾挎槸"鍏虫帀鍦伴浄"鑰屼笉鏄敼琛屼负銆?
鍒ゆ嵁鏄噸璺?1.21.8(`build-line.ps1 -Line 1218 -Launch -Tag reg1218aload -TimeoutSec 150`):

| 椤?| 鍩虹嚎 | 鏈 |
|---|---|---|
| VERDICT | `STARTED (40s, Sound engine started)` | `STARTED (40s, Sound engine started)` |
| `Setting user` / `Sound engine` | 鉁?/ 鉁?| 鉁?/ 鉁?|
| `[OptiFine]` 琛屾暟 | 337 | **337** |
| CTM 琛屾暟 | 38 | **38** |
| stderr | 0 瀛楄妭 | **0 瀛楄妭** |
| 鏃ュ織琛屾暟 | 919 | **919** |
| 鎹㈣鐨勭被 | 380 | 380 |
| 鏈 crash | 鏃?| 鏃?|

鈬?閫愰」涓€鑷淬€傚垎鏀?`1.21.x` 鐨勫ご鍥犳浠?`094952b` 鍓嶈繘涓€鏍?瑙佹彁浜?,閭?7 鏉＄嚎鐨勭姸鎬佷笉鍙樸€?

## 1.0.0:绗竴娆＄湡瀹炲彂甯?2026-09-18)

### 涓€銆佸彂甯冨喅瀹?鐓?`docs/VERSIONING.md` 涓?`docs/PUBLISHING.md` 璧?

- `docs/VERSIONING.md` 璇村緱寰堟竻妤?**`0.x` 鐨勫惈涔夋槸"鍔犺浇鍣ㄨ繕娌″湪鐪熷疄娓告垙閲岃窇璧锋潵",绗竴涓窇璧锋潵鐨勪骇鐗╂墠鍗囧埌
  `1.0.0`**銆?5 / 15 鏉＄嚎鐜板湪閮芥湁瀹炴満璁板綍(鍒ゆ嵁瑙佷笂闈㈢殑閫愯疆瀹炴祴),鎵€浠ヤ笁鏉″垎鏀悇鑷敤
  `release/version.ps1 patch -Base 1.0.0` 鍗囩増 鈥斺€?鑴氭湰閲?`-Base` 灏辨槸涓?绗竴娆＄湡璺戣捣鏉?鍑嗗鐨勯偅鏉¤矾,
  娌℃湁鎵嬫敼 `gradle.properties`;姣忎釜鍒嗘敮鐨?`gradle.properties` 涓?涓轰粈涔堝崌杩欎竴妗?鐨勮鏄庢斁鍦ㄥ悓涓€涓彁浜ら噷銆?
- **涓€涓?MC 鐗堟湰涓€涓骇鐗?涓€涓骇鐗╀竴涓爣绛俱€?* 鍒ゆ嵁鏄疄娴嬬殑:jar 閲岀殑 `META-INF/neoforge.mods.toml` 鎶?
  `minecraft` 渚濊禆鍐欐垚闂尯闂?1.21.4 鐨?jar 閲屾槸 `[1.21.4,1.21.4]`),鎵€浠?`+mc1.21.4` 鐨?jar 鍦?1.21.8 涓?
  涓嶄細琚姞杞姐€傛爣绛惧悕涓庝骇鐗╁悕涓€鑷?`v1.0.0+mc<MC 鐗堟湰>`),杩欐槸 PUBLISHING.md 绗?5 姝ヨ姹傜殑"鏌愪釜鐗堟湰鍙峰鏌愪釜浜х墿"銆?

### 浜屻€佸凡鍙戝竷(10 鏉?姝ｆ枃鍐欏湪鍚勮嚜鐨?Release 閲?

| 绾?| MC / NeoForge | 鏍囩 | 浜х墿(瀛楄妭) | SHA-256(鍓?12) | 楠屾敹(瀹炴満璁板綍) |
|---|---|---|---|---|---|
| 26.x | 26.1.2 / 26.1.2.109 | `v1.0.0+mc26.1.2` | 159 635 | `14890f4b8ad5` | `STARTED`銆乣Setting user` 鉁撱€乣[OptiFine]` 3478銆乻tderr 107 B(= 鏃?mod 瀵圭収璺?銆佹湰娆℃棤 crash |
| 1.20.x | 1.20.4 / 20.4.251 | `v1.0.0+mc1.20.4` | 159 589 | `45aa72956e9a` | `STARTED`銆佲湏銆?41銆乻tderr 14 481 B鹿銆佹湰娆℃棤 crash |
| 1.20.x | 1.20.6 / 20.6.141 | `v1.0.0+mc1.20.6` | 159 708 | `46daf38b0913` | `STARTED`銆佲湏銆?22銆乻tderr 0 B銆佹湰娆℃棤 crash |
| 1.21.x | 1.21 / 21.0.167 | `v1.0.0+mc1.21` | 161 233 | `ea88795872ea` | `STARTED`銆佲湏銆?52銆乻tderr 14 141 B鹿銆佹湰娆℃棤 crash |
| 1.21.x | 1.21.1 / 21.1.250 | `v1.0.0+mc1.21.1` | 161 234 | `b8032c557d15` | `STARTED`銆佲湏銆?23銆乻tderr 0 B銆佹湰娆℃棤 crash |
| 1.21.x | 1.21.3 / 21.3.97 | `v1.0.0+mc1.21.3` | 161 235 | `bbbd37fb498d` | `STARTED`銆佲湏銆?25銆乻tderr 0 B銆佹湰娆℃棤 crash |
| 1.21.x | 1.21.4 / 21.4.149 | `v1.0.0+mc1.21.4` | 161 235 | `b7125668db37` | `STARTED`銆佲湏銆?32銆乻tderr 0 B銆佹湰娆℃棤 crash |
| 1.21.x | 1.21.6 / 21.6.20-beta | `v1.0.0+mc1.21.6` | 161 238 | `de29a28b13e6` | `STARTED`銆佲湏銆?40銆乻tderr 0 B銆佹湰娆℃棤 crash(**涓嶅惈鍚敤鍏夊奖鍖?*) |
| 1.21.x | 1.21.7 / 21.7.25-beta | `v1.0.0+mc1.21.7` | 161 239 | `48fb0e7edb87` | `STARTED`銆佲湏銆?40銆乻tderr 0 B銆佹湰娆℃棤 crash(**涓嶅惈鍚敤鍏夊奖鍖?*) |
| 1.21.x | 1.21.8 / 21.8.54 | `v1.0.0+mc1.21.8` | 161 235 | `09582cc096bf` | `STARTED`銆佲湏銆?37銆乻tderr 0 B銆佹湰娆℃棤 crash |

鹿 1.20.2 / 1.20.4 / 1.21 涓夋潯绾垮甫鐨勯偅鏉″凡鐭?Reflector 缂洪櫡(姣忔鍚姩 4 鏉?`NoClassDefFoundError`);1.20.2 鏈疆
娌℃湁浜у嚭 jar(瑙佷笅),浣嗗畠鐨勮繖鏉＄己闄蜂笌 1.20.4 鍚屽舰銆?

**鍙戝竷鐨勬槸浠€涔?*:鍚勫垎鏀嚜宸辩殑 Gradle 鏋勫缓浜х墿,**鍔犺浇鍣ㄤ晶**(瀹炴祴:姣忎釜 jar 閲?`net/optifine/**` 鏉＄洰鏁颁负 0),
涓嶅惈 OptiFine 鐨勭被銆佷篃娌℃湁闅忓寘鍒嗗彂 OptiFine銆傝兘鐩存帴鏀捐繘 `mods/` 鐨勬垚鍝佷粛瑕佹寜 rig 鐨勬祦姘寸嚎鐢?*鐢ㄦ埛鑷繁鐨?*
OptiFine jar 鍚堟垚 鈥斺€?涓婇潰姣忎竴鏉￠獙鏀舵暟瀛楅兘鏄偅鏍烽噺鍑烘潵鐨勩€?

### 涓夈€佸凡楠岃瘉浣嗚繖涓€杞病鏈夊彂甯?5 鏉?鍘熷洜閮芥槸鏋勫缓鑰屼笉鏄獙璇?

| 绾?| 鍘熷洜(瀹炴祴) |
|---|---|
| 1.20.1 / 47.1.106 | 閭ｄ竴浠?NeoForge 鏄棫鐨?`net.neoforged:forge` 鍧愭爣,Gradle 鎻掍欢鎶?`Could not find net.neoforged:neoforge:1.20.1-47.1.106`(`gradle.properties` 閲屾棭灏辫鐫€杩欐潯) |
| 1.20.2 / 20.2.88 | `20.2.88` 鍦?Maven 涓婃病鏈?ModDevGradle 闇€瑕佺殑 `neoforge-moddev-bundle` 鍙樹綋:`Unable to find a variant 鈥?with the requested capability` |
| 1.21.9 / 1.21.10 / 1.21.11 | 杩欎笁鐗?NeoForge 宸茬粡娌℃湁 ModLauncher,鑰屾湰鍒嗘敮鐨?`src/main` 缂栬瘧鐨勬槸 `cpw.mods.modlauncher.api.ITransformationService`銆?*瀹炴祴 1.21.11**(21.11.45):`BUILD FAILED in 11m 15s`,姝诲湪 `:compileJava`,`閿欒: 绋嬪簭鍖卌pw.mods.modlauncher.api涓嶅瓨鍦╜(MemberRestoreTransformer 绛?;**1.21.10 鍚屾牱瀹炴祴涓€娆?*(21.10.64,`BUILD FAILED in 14m`,`SortProbeFix` 涓?`MemberRestoreTransformer` 鍚屼竴閿欒);1.21.9 鏈崟鐙皾璇?鍚屼竴鏉¤矾)銆傝繖涓夋潯鐨勬寕杞界偣鏄垜浠嚜宸辩殑 `ClassProcessor`(26.x 鍒嗘敮 `src/fml10`,鐢?rig 缂栬瘧**杩涜浇鑽?jar**),鑰岃浇鑽?jar 鍚?OptiFine 鐨勭被銆佷笉鍒嗗彂 |

### 鍥涖€佽繖涓€杞殑缃戠粶瀹炲喌(涓轰粈涔堟湁鐨勭嚎瑕侀噸璇?

姣忎釜**闈為粯璁?*鐩爣閮借璧颁竴閬?neoform 娴佹按绾?閲嶆柊涓嬭浇 MC銆丯eoForge 涓庢暣濂楀簱),鑰岃繖涓€杞?
`maven.neoforged.net` / `libraries.minecraft.net` 鍙嶅鏂繛:1.21.8 绗竴娆?`BUILD FAILED in 15m 10s`銆?
1.20.6 绗竴娆?10m 28s(log4j 鐨?`maven-metadata.xml` 鍙栦笉鍒?銆?.21.6 绗竴娆?4m 40s
(`fancymodloader:securejarhandler:9.0.2` 瑙ｆ瀽涓嶅埌)銆?.21 涓夋鍏ㄨ触銆?

**閲嶈瘯鏄湁鏁堢殑,鑰屼笖绗簩娆￠€氬父寰堝揩**:澶辫触鐨勯偅涓€娆″凡缁忔妸缁濆ぇ澶氭暟浜х墿钀借繘缂撳瓨,1.21.8 閲嶈瘯 2m51s銆?
1.20.6 閲嶈瘯 40s銆?.21.6 閲嶈瘯 9m18s銆?*1.21 閲嶈瘯(绗洓娆?4m34s** 閮借繃浜嗐€傛墍浠?鏌愪竴杞瀯寤哄け璐?鍦ㄦ湰椤圭洰閲岄鍏堟槸涓€涓?
缃戠粶浜嬪疄,涓嶆槸浠ｇ爜浜嬪疄 鈥斺€?涓婇潰绗笁鑺傞噷鐪熸灞炰簬"鏋勫缓涓嶅嚭鏉?鐨勫彧鏈?1.20.1銆?.20.2 涓?1.21.9/1.21.10/1.21.11 浜旀潯,
鑰?1.21 閭ｆ潯鍙槸缃戠粶,閲嶈瘯涔嬪悗灏卞彂甯冧簡銆?

### 浜斻€佽繖涓€杞敼浜嗗摢浜涙枃妗?閮藉湪鍚勮嚜鍒嗘敮涓婃彁浜ゅ苟鎺ㄩ€?

- 涓夋潯鍒嗘敮鐨?`gradle.properties`(0.1.1 / 0.1.1 / 0.2.0 鈫?1.0.0)涓庡悇鑷?README 鐨勫疄娴嬬姸鎬?
- `26.x` 鐨?`docs/PUBLISHING.md`("鐜扮姸"涓€鑺傛敼鎴?宸茬粡鍙戝竷杩囦竴娆?,骞跺啓鏄庡彂甯冪殑 10 鏉?鏈彂甯冪殑 5 鏉?浠ュ強
  "鏋勫缓澶辫触鍏堥噸璇曞苟鍖哄垎缃戠粶涓庣湡鏋勫缓涓嶅嚭鏉?杩欐潯杩欎竴杞噺鍑烘潵鐨勮鐭?;
- `26.x` 鐨?`docs/MATRIX.md` 椤堕儴鍔犱簡"杩欎唤鍓湰宸茶繃鏃?杩愯鏃ュ織鍦?`1.20.x`"鐨勬寚寮?
- `main` 鐨勮惤鍦伴〉浠?楠ㄦ灦闃舵"鏀规垚 15/15 宸插疄鏈洪獙璇?+ 瀹炴祴鐭╅樀 + 浜х墿鍛藉悕瑙勫垯 + 璇佹嵁鍦ㄥ摢;
- 鏍囩鍏ㄩ儴鎵撳湪"鍗囩増閭ｄ竴鎻愪氦"涓?26.x `bf3de0d`銆?.21.x `1340e58`銆?.20.x `48dd023`),娌℃湁鎻愪氦浠讳綍鏋勫缓浜х墿銆?
  OptiFine jar 鎴?`test-downloads/`銆?

### 鍏€佸彂甯冩湰韬殑璁板綍

10 涓?GitHub Release 閫氳繃 GitHub API 鍒涘缓,璧勪骇鐢?`Invoke-RestMethod` 涓婁紶,姣忎釜 Release 闄?*涓€涓?* jar銆?
姝ｆ枃閲屽啓璇ョ嚎鐨勯獙鏀舵暟瀛椾笌宸叉帴鍙楃己闄枫€傞€愪釜 Release 鐨?URL銆佸瓧鑺傛暟涓?SHA-256 瑙佷笂琛?
(Release URL 褰㈠ `https://github.com/Kynarain/OptifiNeoforge/releases/tag/v1.0.0+mc1.21.4`)銆?
鏍囩鍏ㄩ儴鏄檮娉ㄦ爣绛?`git tag -a`),娑堟伅閲屽甫浜х墿鍚嶃€佸瓧鑺傛暟涓?SHA-256;`git push origin <tag>` 閫愪釜鎺ㄩ€併€?

## 2026-09-18(涓嬪崐):浜旀潯"鏋勫缓涓嶅嚭鏉?鐨勭嚎鍏ㄩ儴缁撴竻

涓婁竴鑺傛妸瀹冧滑鐨勫叡鍚岀偣鍐欐垚浜嗕竴鍙ヨ瘽:"鐪熸灞炰簬'鏋勫缓涓嶅嚭鏉?鐨勫彧鏈?1.20.1銆?.20.2 涓?
1.21.9/1.21.10/1.21.11 浜旀潯"銆傝繖涓€杞妸浜旀潯閮芥墦閫氫簡銆?

### 涓€銆?.21.9 / 1.21.10 / 1.21.11:鎸傝浇鐐规簮鐮佹牴鎸変唬娆″垎寮€(`1.21.x`)

澶嶇幇:`-Pmc=1.21.11 -Pneoforge=21.11.45` 姝诲湪 `:compileJava`,**100 涓敊璇?*,鍏ㄩ儴鏄?
`閿欒: 绋嬪簭鍖卌pw.mods.modlauncher.api涓嶅瓨鍦╜,钀藉湪瀹炵幇 ModLauncher 鎺ュ彛鐨勯偅鎵圭被涓?鈥斺€?閭ｆ壒绫绘斁鍦?
`src/main`,鑰岃繖涓夌増 NeoForge 宸茬粡娌℃湁 ModLauncher銆?

鍋氭硶涓?`1.20.x` 鏃╁氨鐢ㄨ繃鐨?`src/ml10` / `src/ml11` 鍚屽舰:15 涓被绉诲埌 `src/ml11/java`,鐢辨柊鐨?
`-Pmountpoint` 鍐冲畾缂栦笉缂?鈥斺€?`modlauncher`(1.21 鈥?1.21.8)缂?`fml10`(1.21.9 璧?涓嶇紪;闈為粯璁ょ洰鏍?
**蹇呴』**缁欒繖涓紑鍏?涓庢棦鏈夌殑 `-Pneoforge` 鍚屼竴鏉¤鐭┿€?

瀹炴祴(姣忔潯鍚勬瀯寤轰竴娆?:1.21.9(21.9.16-beta)銆?.21.10(21.10.64)銆?.21.11(21.11.45)鍏ㄩ儴
`BUILD SUCCESSFUL`,浜х墿 39 椤广€?09 765 瀛楄妭(1.21.9 澶?4 瀛楄妭,鏄厓鏁版嵁閲屾洿闀跨殑 NeoForge 涓?,
`loader/**` **0 涓被**銆佺绾垮伐鍏?32 涓被,鍗?鍔犺浇鍣ㄤ晶宸ュ叿 + mod 楠ㄦ灦"銆傞粯璁ょ洰鏍?1.21.4 鏈彈褰卞搷:
161 235 瀛楄妭銆?6 涓?loader 绫?涓庢湰杞箣鍓嶅悓灏哄銆?*杩欐壒绫荤殑绉诲姩鏄函鏀瑰悕,15 涓枃浠跺悇 0 琛屾敼鍔ㄣ€?*

### 浜屻€?.20.1 涓?1.20.2:缁曞紑 ModDevGradle,鎸夋樉寮忕被璺緞缂栬瘧(`1.20.x`)

1.20.1 鐨勭粨娉曞氨鍦ㄦ洿鏃╀袱鑺傜殑鍊欓€夊悕鍗曢噷("an explicit dependency on net.neoforged:forge with the
plugin's resolver out of the way")銆傛湰杞妸瀹冨仛鎴?杩欎袱涓洰鏍?*涓嶅簲鐢?* ModDevGradle,鐢?`java` 鎻掍欢鍔犱竴寮?
鏄惧紡 `compileOnly` 娓呭崟鏋勫缓銆傚潗鏍囧彇鑷悇鐗堣嚜宸辩殑鍏冩暟鎹?鈥斺€?20.2.88 鐨?POM 涓?1.20.1-47.1.106 鐨?userdev
`config.json` 閮界偣鍚?`cpw.mods:modlauncher:10.0.9`,FML 鍒嗗埆鏄?fancymodloader 1.0.16 涓?47.2.2銆?

**杩欎袱涓洰鏍囦笉闇€瑕?Minecraft**:鏈垎鏀簮鐮侀噷娌℃湁涓€澶?`net.minecraft` / `com.mojang` 鐨?import銆傛墍浠ユ棦娌℃湁
Minecraft 涓嬭浇,涔熸病鏈夊弽缂栬瘧涓?NeoForm,鏋勫缓 1 鈥?6 绉掑畬鎴?浠ｄ环鏄病鏈?`runClient` 寮€鍙戣繍琛屻€?

娓呭崟閲屾湁涓ゅ鏄俯鍑烘潵鐨?
- `@Mod` 娉ㄨВ**涓嶅湪 loader jar 閲?*,瀹冨湪 `language-java`(javafml 璇█鎻愪緵鑰?閲?`ModContainer` 鍦?`core` 閲屻€?
  鍙垪 `loader` 鏃?`net.neoforged.fml.common.Mod` 鎵句笉鍒?鈥斺€?鏄€愪釜 jar 鏌ヤ簡鎵嶅畾涓嬫潵鐨勩€?
- `DonorVerifier` 鐢ㄤ簡 `CheckClassAdapter`,鍗?**`asm-util`**銆傚湪 ModDevGradle 鐩爣涓婂畠鏄粠 NeoForge 鍙戣
  浼犻€掕繘鏉ョ殑銆佷粠鏉ユ病琚偣鍚嶈繃,鍦ㄨ繖鏉℃樉寮忔竻鍗曢噷蹇呴』鍐欏嚭鏉?鍚﹀垯 `绋嬪簭鍖卭rg.objectweb.asm.util涓嶅瓨鍦╜)銆?
  杩欎竴鏉″鍥涙潯绾块兘鎴愮珛,宸茬粡琛ヨ繘 `build.gradle` 鐨勫叕鍏变緷璧栥€?

### 涓夈€佹柊鏌ュ嚭鏉ョ殑绗笁澶勫垎鐣?鐧昏鐢ㄧ殑 mod id 闅忕増鏈彉

1.20.1 鐨?NeoForge 47.1.106 鍦ㄨ嚜宸辩殑 `META-INF/mods.toml` 閲岀櫥璁颁负 **mod id `forge`**(displayName 浠嶆槸
"NeoForge")銆佺増鏈?`47.1.106`,鑰屽畠鐨勪骇鐗╁潗鏍囨槸 `net.neoforged:forge:1.20.1-47.1.106`銆?0.2.88 璧锋墠鏄?
`neoforge` 涓斾笌鍧愭爣鍚屽悕銆備緷璧栧潡鐓ф妱鍒殑鐗堟湰鍐?`neoforge`,鍦?1.20.1 涓婁細鍘昏涓€浠介偅浠藉彂琛岄噷**鏍规湰娌℃湁**鐨?mod銆?

鍚屼竴杞妸**鍏冩暟鎹枃浠跺悕**鐨勫垏鎹㈢偣閲忔竻浜?`docs/VERSIONS.md` 鎸傜潃鐨?寰呯‘璁?涔嬩竴):

| 鐩爣 | 璇ョ増 NeoForge 鑷繁鐨勫厓鏁版嵁鏂囦欢 | 鐧昏 mod id | FML loader 璇荤殑鍚嶅瓧 |
|---|---|---|---|
| 1.20.1 (47.1.106) | `META-INF/mods.toml` | `forge` | `mods.toml` |
| 1.20.2 (20.2.88) | `META-INF/mods.toml` | `neoforge` | `mods.toml` |
| 1.20.4 (20.4.251) | `META-INF/mods.toml` | `neoforge` | `mods.toml` |
| 1.20.6 (20.6.141) | `META-INF/neoforge.mods.toml` | `neoforge` | 涓や釜鍚嶅瓧閮借 |

涓よ矾鍒ゆ嵁涓€鑷?鍚勭増 FML loader jar 閲岀殑瀛楅潰甯搁噺,**浠ュ強**鍚勭増 NeoForge 鑷繁浜х墿閲岀殑鏂囦欢鍚嶃€備袱涓?NeoForge
universal jar 閲屼袱绉嶅瓧闈㈤噺閮芥病鏈?鎵€浠ュ仛鍐冲畾鐨勭‘瀹炴槸 loader jar,涓嶆槸瀹冦€?

**椤哄甫鏀规涓€澶?*:1.20.4 鐨?jar 浠ュ墠甯︾殑鏄?`neoforge.mods.toml`,鑰?20.4.251 鐨?FML 鍙 `mods.toml`,
閭ｄ唤鍏冩暟鎹瀹冪瓑浜庝笉瀛樺湪銆傜幇鍦ㄥ洓涓増鏈啓鍑虹殑鍚嶅瓧閮戒笌璇ョ増鑷繁鐨勪骇鐗╀竴鑷淬€傝繖鏄?*鏋勫缓灞傞潰**鐨勬敼姝?
**娌℃湁閲嶈窇瀹炴満鍚姩**,鎵€浠ュ凡鍙戝竷 jar 鐨勬鏂囪鏄庢病鏈夎窡鐫€鍔ㄣ€?

杩樻湁涓€澶勬湰杞噺浜嗐€佷絾**涓嶆槸鍏冩暟鎹€屾槸婧愮爜**鐨勫垎鐣?FML 鐨?API 鍖呭悕 鈥斺€?1.20.1 鏄?`net.minecraftforge.fml` +
`net.minecraftforge.eventbus.api`,1.20.2 璧锋槸 `net.neoforged.fml` + `net.neoforged.bus.api`銆備竴浠芥簮鐮佽法涓嶈繃鍘?
鎵€浠ユ寜鐩爣閫夋簮鐮佹牴(`src/forge` 涓?`src/neoforged`),涓?`src/ml10`/`src/ml11` 鍚屽舰銆傚疄娴?1.20.1 鐨?
`OptifiNeoforge.class` 甯搁噺姹犻噷鍙湁 `net/minecraftforge/**`,娌℃湁涓€澶?`net/neoforged`銆?

### 鍥涖€佽繖涓€杞殑缃戠粶瀹炲喌:鍧忕殑鏄?IPv4 閭ｄ竴渚?

涓婁竴鑺傛妸鏋勫缓澶辫触褰掔粰"缃戠粶鎴愭鏂繛",杩欎竴杞噺鍒颁簡鏇村叿浣撶殑鍘熷洜:

`maven.neoforged.net` 鍦?CDN77 鍚庨潰,鍚屾椂鏈?AAAA 涓?A 璁板綍銆傚湪杩欏彴鏈哄櫒涓?**IPv4 閭ｄ竴渚ф槸鏈夋崯鐨?*:
`curl -4` 杩炲彂浜旀,涓ゆ鎻℃墜鐩存帴澶辫触銆佷袱娆℃垚鍔熴€佷竴娆?20 绉掕秴鏃?鎴愬姛閭ｄ袱娆″悇绾?15 鈥?17 绉?;鍚屼竴 URL
`curl -6` **1.7 绉?200**銆侸ava 鐨?HttpClient(NeoForm 鐨勪笅杞藉櫒)璧扮殑姝ｆ槸鍧忕殑閭ｄ竴渚?鎵€浠ユ姤鐨勬槸
`SSLHandshakeException: Remote host terminated the handshake` 涓?`ConnectException`銆?

涓夊鍏蜂綋琛ㄧ幇:
- `mergetool-2.0.3-fatjar.jar` 鍙嶅鍙栦笉涓嬫潵,鎶?鏈嶅姟鍣ㄥ彲鑳戒笉鏀寔瀹㈡埛绔姹傜殑 TLS 鐗堟湰",鑰?curl 涓€鍙栧氨鏈?
- `asm-commons-9.8.jar` 鏇撮殣钄?缂撳瓨鐩綍
  `~/.gradle/caches/neoformruntime/artifacts/org/ow2/asm/asm-commons/9.8/` **瀛樺湪浣嗘槸绌虹殑**,浜庢槸姣忔鏋勫缓
  閮芥鍦ㄥ悓涓€涓枃浠朵笂銆傛寜璇ヨ矾寰勭敤 IPv6 琛ョ鍚庢瀯寤虹珛鍒婚€氳繃;
- 1.20.4 鐨?`minecraft_1.20.4_client_mappings.txt` 涓嬪埌 2 390 577 瀛楄妭鍚?*褰诲簳鍋滀綇**(杩炵画 35 绉掑瓧鑺傛暟涓嶅彉),
  鍚屾牱 IPv6 鍙栧洖(8 897 012 瀛楄妭)鍚庨噸璇曟垚鍔熴€?

澶勭疆:`JAVA_TOOL_OPTIONS=-Djava.net.preferIPv6Addresses=true`(瀵规墍鏈?JVM 鐢熸晥,鍖呮嫭 NeoForm fork 鍑烘潵鐨?
閭ｄ釜 `java.exe`),浠ュ強瀵瑰仠浣忕殑涓嬭浇鎸夌紦瀛樿矾寰勭洿鎺ヨˉ绉嶃€?*缁撹:杩欑被鎶ラ敊鍏堢湅 IPv4/IPv6,鍐嶈皥"閲嶈瘯"銆?*

鍙︿竴鏉′笌缃戠粶鏃犲叧浣嗗悓鏍疯垂鏃堕棿鐨?PowerShell 閲?`-P` 鍙傛暟**蹇呴』鍔犲紩鍙?*銆傝８鍐?`-Pmc=1.20.6` 浼氳鎷嗗紑,
Gradle 鎶?`Task '.20.6' not found in root project`(`cmd.exe` 涓嬩笉鍔犲紩鍙蜂篃鍙互)銆傛枃妗ｇず渚嬪凡鏀规垚甯﹀紩鍙枫€?

### 浜斻€佽繖涓€杞殑缁撴灉:鍗佷簲鏉＄嚎鐜板湪閮借兘鏋勫缓

姣忎竴鏉?*鍚勬瀯寤轰竴娆?*銆備骇鐗╃粨鏋勪笌鍚屼竴鍒嗘敮鍐呯殑鍒嗙晫涓€鑷?涓嬮潰鏄叏閮ㄥ崄浜旀潯:

| 鍒嗘敮 | 鐩爣 | 缁撴灉 | 浜х墿 |
|---|---|---|---|
| `1.20.x` | 1.20.1 / 1.20.1-47.1.106 | `BUILD SUCCESSFUL` | 57 椤广€?59 735 瀛楄妭銆?8 涓?loader 绫汇€乣mods.toml` |
| `1.20.x` | 1.20.2 / 20.2.88 | `BUILD SUCCESSFUL` | 57 椤广€?59 741 瀛楄妭銆?8 涓?loader 绫汇€乣mods.toml` |
| `1.20.x` | 1.20.4 / 20.4.251(榛樿) | `BUILD SUCCESSFUL` | 57 椤广€?59 744 瀛楄妭銆?8 涓?loader 绫汇€乣mods.toml` |
| `1.20.x` | 1.20.6 / 20.6.141 | `BUILD SUCCESSFUL` | 57 椤广€?59 959 瀛楄妭銆?8 涓?loader 绫汇€乣neoforge.mods.toml` |
| `1.21.x` | 1.21 / 21.0.167 | `BUILD SUCCESSFUL` | 55 椤广€?61 233 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.1 / 21.1.250 | `BUILD SUCCESSFUL` | 55 椤广€?61 234 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.3 / 21.3.97 | `BUILD SUCCESSFUL` | 55 椤广€?61 235 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.4 / 21.4.149(榛樿) | `BUILD SUCCESSFUL` | 55 椤广€?61 235 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.6 / 21.6.20-beta | `BUILD SUCCESSFUL` | 55 椤广€?61 238 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.7 / 21.7.25-beta | `BUILD SUCCESSFUL` | 55 椤广€?61 239 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.8 / 21.8.54 | `BUILD SUCCESSFUL` | 55 椤广€?61 235 瀛楄妭銆?6 涓?loader 绫?|
| `1.21.x` | 1.21.9 / 21.9.16-beta | `BUILD SUCCESSFUL` | 39 椤广€?09 769 瀛楄妭銆?*0 涓?loader 绫?* |
| `1.21.x` | 1.21.10 / 21.10.64 | `BUILD SUCCESSFUL` | 39 椤广€?09 765 瀛楄妭銆?*0 涓?loader 绫?* |
| `1.21.x` | 1.21.11 / 21.11.45 | `BUILD SUCCESSFUL` | 39 椤广€?09 765 瀛楄妭銆?*0 涓?loader 绫?* |
| `26.x` | 26.1.2 / 26.1.2.109 | `BUILD SUCCESSFUL` | 52 椤广€?59 635 瀛楄妭銆?5 涓伐鍏风被銆? 涓?loader 绫?|

瀛楄妭鏁扮殑宸紓鏉ヨ嚜鍏冩暟鎹噷閭ｅ嚑琛屽瓧绗︿覆鐨勯暱鐭?MC 鐗堟湰銆丯eoForge 鐗堟湰銆乵od id),涓嶆槸浠ｇ爜宸紓:`1.21.x` 閭ｇ粍
16 涓?loader 绫荤殑鍗佷竴鏉″郊姝ゅ彧宸嚑瀛楄妭,鑰?`1.21.9` 姣斿悓缁勭殑鍙﹀涓ゆ潯澶?4 瀛楄妭,姝ｆ槸 `21.9.16-beta` 姣?
`21.10.64` / `21.11.45` 闀?4 涓瓧绗︺€?

**杈圭晫,濡傚疄鍐欎笅**:鏈疆**鍙姩浜嗘瀯寤?*銆傝繖鍙版満鍣ㄤ笂娌℃湁 `optifineoforge-test` 杩欎釜 rig,鎵€浠?*娌℃湁閲嶈窇浠讳綍
涓€娆″疄鏈哄惎鍔?* 鈥斺€?涓婅〃鍗佷簲琛岀殑瀹炴満鍒ゆ嵁浠嶇劧鍙湁鏈枃浠舵洿鏃╅偅浜涜褰?浜旀潯鍘熸湰"娌″彂"鐨勭嚎涔熶粛鐒?*娌℃湁鍙戝竷**
(鍙戝竷鏄嫭绔嬬殑涓€姝?闇€瑕佷竴鏉″惎鍔ㄨ褰曚笌 Release 姝ｆ枃)銆傛瀯寤洪€氳繃涓嶇瓑浜庤兘璺?杩欎竴鏉′笉鍙樸€?

## 2026-09-19:鎶?rig 浠庨浂寤鸿捣鏉?浠ュ強 1.21.4 鐨勭涓€娆＄湡鏈哄鐓?

涓婁竴鑺傜殑杈圭晫鏄?杩欏彴鏈哄櫒涓婃病鏈?rig,鎵€浠ユ病鏈夐噸璺戝疄鏈哄惎鍔?銆傝繖涓€杞厛鎶?rig 寤鸿捣鏉?鐒跺悗璺戠涓€鏉＄嚎銆?

### 涓€銆乺ig 鏄噸寤虹殑,涓嶆槸鎵惧洖鏉ョ殑

`optifineoforge-test` 鍦ㄨ繖鍙版満鍣ㄤ笂涓嶅瓨鍦?鎸夊悕瀛楁悳杩?C:/D:/E:,涔熸悳杩?`build-rig-jar.ps1`銆乣*-chain.ps1`銆?
`launch*.ps1`),git 鍘嗗彶閲屼篃娌℃湁:`git log --all --diff-filter=A --name-only` 閲屽彧鏈?`release/version.ps1`銆?
鎵€浠ヤ笅闈㈣繖浜涙槸杩欎竴杞?*鏂板啓鐨?*,鏀惧湪 `I:\mods\optifineoforge-test\`(浠撳簱澶?:

| 鑴氭湰 | 浣滅敤 |
|---|---|
| `fetch-libraries.ps1` | 璇?`versions\*\*.json`,鎸?Mojang 鐨?rules 鍙栨湰鏈鸿鐢ㄧ殑搴?缂虹殑鐢?`curl -6` 涓嬪埌 `libraries\`,骞舵妸 `:natives-windows` 閭ｇ被瑙ｅ埌 `natives\` |
| `seed-installer-toolchain.ps1` | 鎶?NeoForge 瀹夎鍣ㄨ嚜宸辫鐢ㄧ殑 NeoForm 宸ュ叿閾?neoform zip銆乥inarypatcher銆丄utoRenamingTool銆丼pecialSource銆乮nstallertools銆乯arsplitter)鍏堜笅濂?|
| `build-jars.ps1` | 鐢ㄤ粨搴撹嚜宸辩殑绂荤嚎宸ュ叿瑁呴厤涓や釜 jar:缁?loader 鐨?OptiFine jar,鍜屽甫鏈嶅姟娉ㄥ唽 + 鎴愬憳杩樺師璁″垝鐨?loader jar |
| `launch.ps1` | 鎸?profile JSON 鍚堝苟鐖跺瓙銆佸睍寮€甯?rules 鐨勫弬鏁般€佹浛鎹㈠崰浣嶇銆佸幓閲?classpath銆佸惎鍔ㄣ€侀檺鏃躲€佺劧鍚庡垽 `Setting user` / `Sound engine started` / 鏈宕╂簝鎶ュ憡 / stderr 瀛楄妭鏁?|

**瀹夎鍣ㄤ负浠€涔堝繀椤诲厛鍠傞ケ**:NeoForge 鐨勫畨瑁呭櫒涓嶅彧鏄笅涓€涓?profile JSON 鈥斺€?瀹冨湪鏈湴璺?NeoForm 娴佹按绾?
鎶?`libraries\net\neoforged\neoforge\21.4.149\neoforge-21.4.149-client.jar`(1637 涓被)閫犲嚭鏉?閭ｆ墠鏄繍琛屾椂
閭ｄ唤琚?NeoForge 鏀硅繃鐨勬父鎴忕被銆傚畠鑷繁浼?`java.net.preferIPv4Stack=true`,浜庢槸涓嬭浇鎴愮墖瓒呮椂
(`SocketTimeoutException: Connect timed out`)鈥斺€斾笌鏈枃浠朵笂涓€鑺傞噺鍒扮殑 IPv4 鏈夋崯鏄悓涓€浠朵簨銆傚厛鎸夋棩蹇楅噷鐨?
52 涓潗鏍囩敤 IPv6 琛ラ綈,瀹夎鍣ㄥ氨涓€娆￠€氳繃(`Successfully installed client into launcher.`)銆?

### 浜屻€佸鐓х粍:涓嶅甫浠讳綍 mod,鍒ゅ畾鏍囧噯蹇呴』鎴愮珛

NeoForge 21.4.149 / 1.21.4,game dir 骞插噣,涓嶅甫 mod:

```
===== VERDICT: STARTED =====
  Setting user         : True
  Sound engine started : True
  new crash reports    : 0
  stderr bytes         : 0
```

涔熷氨鏄 rig 鏈韩鏄兘澶嶇幇鏈」鐩垽鎹殑:鐪熺殑璧蜂簡瀹㈡埛绔€佺湡鐨勮繘浜嗘爣棰樼敾闈€佸０闊冲紩鎿庣湡鐨勮捣鏉ヤ簡銆佹湰娆℃病鏈夊穿婧?
鎶ュ憡銆乻tderr 涓€瀛楁湭鍐欍€備笅闈㈠鐓ч兘鍦ㄨ繖涓熀纭€涓娿€?

### 涓夈€佽涓?OptiFine 涓庢湰椤圭洰鐨?loader 涔嬪悗鐨勫洓姝?浠ュ強鍗″湪鍝?

1. **鍙斁涓や釜 jar(OptiFine + Gradle 浜у嚭鐨?loader),宕╁湪 NeoForge 鑷繁鐨勬柟娉曚笂**:
   `java.lang.NoSuchMethodError: 'void net.minecraft.client.gui.Gui.initModdedOverlays()'`,
   鏍堟槸 `net.neoforged.neoforge.client.ClientHooks.initClientHooks` 鈫?`Minecraft.<init>`銆?
   鍘熷洜鏄?OptiFine 鐨?1.21.4 鏋勫缓鐢?*瀹冭嚜宸辩紪璇戠殑**娓告垙绫婚《鏇胯繍琛屾椂閭ｄ唤,鑰屽畠缂栬瘧鏃舵病鏈?NeoForge 鍚庡姞鐨勬垚鍛樸€?
   杩欐鏄湰浠撳簱 `MemberRestoreTransformer` 瀛樺湪鐨勭悊鐢?鈥斺€?**鑰岄偅浠借鍒?Gradle 浜х墿閲屾病鏈?蹇呴』鐢?rig 鐢熸垚**銆?

2. **鐢ㄤ粨搴撹嚜宸辩殑宸ュ叿鐢熸垚杩樺師璁″垝**:`OptifinePipeline <mc jar> <optifine jar> <workdir>` 鎵撹ˉ涓?
   鍐?`MemberRestorePlan <patched jar> <runtime jar> <out> [donor dir]` 鍑鸿鍒掋€傝繍琛屾椂閭ｄ唤娓告垙绫绘槸
   `client-鈥?srg.jar` 鍙犱笂 `neoforge-鈥?client.jar`,鍙犲畬 8866 椤广€?9928455 瀛楄妭銆傚疄娴嬭鍒?
   **316 琛屻€?3 涓被銆?3 涓?donor**,鍏朵腑灏辨湁鍑轰簨鐨?`M Gui initModdedOverlays ()V`銆?
   娉ㄦ剰 `OptifinePipeline` 蹇呴』鍠?*娣锋穯**鐨勫師鐗堝鎴风:鍠?SRG/瀹樻柟鍚嶉偅浠戒細
   `OptiFine's patcher failed ... Base resource not found: fdp.class`銆?

3. **璁″垝瑁呰繘鍘讳箣鍚?`initModdedOverlays` 閭ｄ釜宕╂簝娑堝け浜?鏈嶅姟涔熺湡鐨勮娉ㄥ唽浜?*銆傛棩蹇楅噷鑳借鍒?
   `OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, fml, OptifiNeoforge鈥`銆?
   `OptiFineTransformer: Targets: 474`銆乣Member restore plan: 314 members across 82 classes`,
   浠ュ強閫愮被鐨?`Restored N members in 鈥?from its donor`銆傛湰娆℃病鏈夊穿婧冩姤鍛娿€?*stderr 0 瀛楄妭**銆?00 琛?`[OptiFine]`銆?

4. **鐒跺悗鍗″湪 FML 鐨?early window 涓?鑰屼笖鏄湰椤圭洰宸茬粡璁板綍杩囩殑閭ｄ竴涓?*:
   `java.lang.IllegalStateException: Already building.`,鏍堟槸
   `fml_earlydisplay.SimpleBufferBuilder.begin` 鈫?`DisplayWindow.paintFramebuffer` 鈫?`NeoForgeLoadingOverlay.render`
   鈫?`GameRenderer.render`銆傛湰鏂囦欢鏇存棭鐨?1.20.4 閭ｆ潯璁扮殑姝ｆ槸杩欎釜缂洪櫡,骞剁粰鍑?`earlyWindowProvider = "none"`銆?
   **杩欎竴杞妸瀹冪殑閫傜敤鑼冨洿鎵╁埌浜?21.4.149**:榛樿鐨?`fmlearlywindow` 鍦ㄨ繖閲屽悓鏍峰繀宕┿€?

5. **鑰?`none` 鍦?21.4.149 涓婁笉鏄瓑浠风殑鏇夸唬**:鎹㈡垚 `none` 涔嬪悗涓嶅啀宕?浣嗗鎴风**鍗″湪鍔犺浇鐣岄潰**銆?
   涓ゆ绾跨▼杞偍(鐩搁殧绾?40 绉?`jstack`)閮芥槸鍚屼竴涓舰鐘?Render 绾跨▼鍦?
   `Minecraft.runTick 鈫?RenderSystem.limitDisplayFPS 鈫?glfwWaitEventsTimeout` 閲岀┖杞?**涓嶆槸**鍗′綇;
   16 涓?`Worker-Main-*` 鍏ㄩ儴 `WAITING (parking)`銆丗orkJoinPool 闃熷垪涓虹┖銆丆PU 涓嶅啀澧為暱;鏃ュ織鍋滃湪
   OptiFine 鐨?28 琛?`Pre-stitch:` 涔嬪悗涓嶅啀鍓嶈繘銆備篃灏辨槸璇撮噸杞芥病鏈夊崱鍦ㄩ攣涓?鑰屾槸**娌℃湁浠讳綍浜哄湪鍋氫簨**,
   鍔犺浇鐣岄潰鐨勭Щ闄や篃灏辨案杩滀笉鍙戠敓銆俙earlyWindowControl = false` 涓€璧疯涓?缁撴灉鐩稿悓銆?

### 鍥涖€侀『甯﹂噺鍒扮殑涓や欢涓庢湰绾挎棤鍏充絾浼氳瀵肩殑浜?

- **鏈€灏忓寲绐楀彛 + 鍨傜洿鍚屾浼氳 Render 绾跨▼姝诲湪 `glfwSwapBuffers`**:绗竴娆¤浆鍌ㄦ槸
  `Window.updateDisplay 鈫?RenderSystem.flipFrame 鈫?glfwSwapBuffers`銆傚幓鎺夌獥鍙ｆ渶灏忓寲骞舵妸
  `enableVsync:false` 鍐欒繘 `options.txt` 涔嬪悗,閭ｆ鍗℃娑堝け(杞偍鍙樻垚涓婇潰鐨?`glfwWaitEventsTimeout`)銆?
  杩欎笌 `OptiFabric` 閭ｄ唤 DEVELOPMENT 璁扮殑鎴愬洜涓€鑷淬€?
- **`optionsof.txt` 灏鹃殢鎹㈣浼氳 OptiFine 鑷繁鐨勮В鏋愬櫒鎶涘紓甯?*:
  `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at `Options.loadOfOptions`銆?
  鏂囦欢闈炵┖銆?8 琛屻€佹瘡琛岄兘鏈夊啋鍙?鍞竴寮傚父涔嬪鏄粨灏惧涓€涓崲琛?鎸?`\r?\n` 鍒囦細澶氬嚭涓€涓┖琛?銆?
  鍚庣画鍑犳杩愯 stderr 鏄?0 瀛楄妭,璇存槑杩欎笉鏄瘡娆￠兘鏈夈€?

### 浜斻€佺敱姝ゅ彂鐜扮殑涓€涓?*宸插彂甯冧骇鐗?*鐨勭己闄?鏈疆宸蹭慨)

loader 鐨勭被瀹炵幇 `cpw.mods.modlauncher.api.ITransformationService`,浣?*娌℃湁浠讳綍涓滆タ澹版槑瀹?*:
鍥涗釜鍒嗘敮(`main` / `1.20.x` / `1.21.x` / `26.x`)鐢?`git ls-tree` 閮芥壘涓嶅埌
`META-INF/services/cpw.mods.modlauncher.api.ITransformationService`,鏋勫缓鍑烘潵鐨?jar 閲?
`META-INF/services/**` 鏉＄洰鏁版槸 **0**銆侻odLauncher 鍙€氳繃杩欎釜鏂囦欢鍙戠幇杞崲鏈嶅姟,鎵€浠?*宸插彂甯冪殑 9 涓?ModLauncher loader jar**
(`1.20.x` 鐨?2 涓€乣1.21.x` 鐨?7 涓?瀹為檯涓婃槸鎯版€х殑 鈥斺€?鍔犺浇渚ф牴鏈笉浼氳璋冪敤(`26.1.2` 閭ｄ竴绾夸笉鍙楀奖鍝?
瀹冪殑 Gradle 浜х墿閲屾病鏈?loader 绫?鎸傝浇鐐规槸 OptiFine 鑷繁鐨?`ClassProcessor`)銆?
瀹炴祴瀵圭収:鎵嬪伐琛ヤ笂杩欎釜鏂囦欢涔嬪悗,鏃ュ織閲岀珛鍒诲嚭鐜?`OptifiNeoforgeTransformationService.onLoad` 閭ｄ竴琛?
涓嶈ˉ,鍚屼竴浠藉惎鍔ㄩ噷鏈」鐩殑鍔犺浇渚т竴涓瓧閮芥病鏈夈€?

淇硶鎸夋湰绾垮凡鏈夌殑"姣忎釜浠ｆ涓€涓簮鐮佹牴"鐨勫啓娉?`1.21.x` 涓?`-Pmountpoint=modlauncher` 鏃跺姞
`src/ml11/resources`,`fml10` 鏃朵笉鍔?`1.20.x` 涓婃寜 `-Pmodlauncher` 鍔?`src/ml10/resources` 鎴?
`src/ml11/resources`銆傛敼瀹屽疄娴?`1.21.4` 涓?`1.20.4` / `1.20.6` / `1.20.2` 鐨?jar 閲屽悇 **1 鏉?*,
`1.21.11`(fml10)浠嶆槸 **0 鏉?*銆?

### 鍏€佽繖涓€杞殑杈圭晫(鍝簺**娌℃湁**鎴愮珛)

- **1.21.4 杩欎竴鏉℃病鏈夐€氳繃**,鍗″湪绗笁鑺傜 5 姝?瀹㈡埛绔椿鐫€銆佹病宕┿€乻tderr 骞插噣銆乣Setting user` 涔熷埌浜?
  浣?`Sound engine started` 娌″嚭鐜?鍔犺浇鐣岄潰娌℃湁绉婚櫎銆?*鎵€浠ユ湰鏂囦欢鏇存棭閭ｆ潯"1.21.4 宸插疄鏈洪獙璇?鐨勮褰?
  杩欎竴杞病鏈夊鐜板嚭鏉?*,鑰岃繖涓嶆槸"璺戝緱涔呬竴鐐?鐨勯棶棰?涓ゆ 300 绉掋€佷竴娆?450 绉掔殑瑙傚療褰㈢姸鐩稿悓)銆?
- 宸紓鍙兘涓庤繕鍘熻鍒掔殑**鏉ユ簮**鏈夊叧:鏈疆鐨勮鍒掓槸浠?*绂荤嚎**琛ヤ竵浜х墿绠楀嚭鏉ョ殑,鑰?1.21.4 涓婇《鏇挎父鎴忕被鐨勬槸
  OptiFine 鐨?*杩愯鏈?* transformer銆傝繍琛屾湡閭ｄ唤鏄墦鍦?NeoForge 宸茬粡鏀硅繃鐨勭被"涓婄殑,瀹冭嚜甯?NeoForge 鐨勬垚鍛?
  鑰岀绾块偅浠戒笉甯?鈥斺€?浜庢槸鎸夌绾夸骇鐗╃畻鍑烘潵鐨勮鍒掍細鎶婁竴鎵规湰鏉ュ氨鍦ㄧ殑鎴愬憳鍐嶈ˉ涓€閬嶃€傝鍒掗噷
  `Stitcher`(logger 瀛楁 + 涓や釜 lambda)銆乣TextureAtlas.getTextures`銆乣SpriteResourceLoader.loadSprite`
  閮藉湪璐村浘鎷兼帴杩欐潯璺笂,鍊煎緱涓嬩竴涓洖鍚堜粠杩欓噷鏌ャ€?
- 鏈疆**娌℃湁**璺戝叾瀹?14 鏉＄嚎,涔?*娌℃湁**鍙戝竷浠讳綍涓滆タ銆?

## 2026-09-19(涓嬪崐):1.21.4 閫氳繃浜?鑰屽崱浣忓畠鐨勪笉鏄姞杞藉櫒鑰屾槸 shim 鐨勫舰鐘?

涓婁竴鑺傜殑缁撳熬鎶?1.21.4 鐨勫仠婊炲綊缁?杩樺師璁″垝鏄粠绂荤嚎浜х墿绠楃殑",骞惰涓嬩竴鍥炲悎浠庤创鍥炬嫾鎺ラ偅鏉¤矾鏌ャ€?*閭ｄ釜鐚滄祴鏄敊鐨?*:
闂涓嶅湪璁″垝,鍦?*鎴戜滑鑷繁鐨?`ForgeApiShims` 鐢熸垚鐨?Forge API stub 鏄帴鍙ｈ€屼笉鏄被**銆?

### 涓€銆佽繖涓€鍒荤殑璇佹嵁

1.21.4 / NeoForge 21.4.149 / OptiFine `OptiFine_1.21.4_HD_U_J3`,rig 鐨勫垽瀹?

```
===== VERDICT: STARTED =====
  Setting user         : True
  Sound engine started : True     鈫?L353,[net.minecraft.client.sounds.SoundEngine/]: Sound engine started
  new crash reports    : 0
  stderr bytes         : 0
```

鏃ュ織閲屽悓涓€涓洰褰曡繕鏈?**232 琛?`[OptiFine]`**銆?*14 鏉?`Created: minecraft:textures/atlas/鈥**(鍋滄粸鏃舵槸 0 鏉?銆?
`Caught error loading resourcepacks` 0 娆°€乣NoSuchFieldError` / `NoSuchMethodError` /
`IncompatibleClassChangeError` / `InstantiationError` 鍚?0 娆°€傛湰娆¤繍琛屾病鏈夊穿婧冩姤鍛?鐩綍閲岄偅涓や唤鏄?00:48 涓?00:57 鐨?
閮藉湪杩欎釜淇ソ涔嬪墠)銆俙FATAL/ERROR` 鍙墿涓ゆ潯,閮芥槸绂荤嚎娴嬭瘯璐﹀彿蹇呯劧鐨?Realms 401 涓?`Failed to fetch user properties`,
瀵圭収璺戦噷涓€妯′竴鏍枫€?

**232 琛屻€乻tderr 0 瀛楄妭銆佹棤宕╂簝鎶ュ憡** 鈥斺€?涓庢湰鏂囦欢鏇存棭涓?1.21.4 璁颁笅鐨勯偅涓€琛岄€愰」鐩稿悓,鎵€浠ヨ繖鏉＄嚎鐨勫疄鏈哄垽鎹繖涓€杞?
鏄?*琚嫭绔嬪鐜?*浜嗕竴娆?鑰屼笉鏄収鎶勩€?

### 浜屻€佷笁涓け璐?涓€涓牴鍥?shape

瑁呬笂 OptiFine 涓庢湰椤圭洰鐨?loader 涔嬪悗,澶辫触鏄?*涓€娆′竴涓?*鍦板線鍓嶈蛋,姣忎慨鎺変竴涓氨闇插嚭涓嬩竴涓?

| # | 鐥囩姸 | 鏍瑰洜 | 澶勭疆 |
|---|---|---|---|
| 1 | `NoSuchMethodError: 'void net.minecraft.client.gui.Gui.initModdedOverlays()'` 鈫?`ClientHooks.initClientHooks` | OptiFine 鐢ㄥ畠鑷繁缂栬瘧鐨勬父鎴忕被椤舵浛杩愯鏃堕偅浠?鑰屽畠缂栬瘧鏃舵病鏈?NeoForge 鍚庡姞鐨勬垚鍛?| 鐢ㄤ粨搴撹嚜宸辩殑 `OptifinePipeline` + `MemberRestorePlan` 鐢熸垚杩樺師璁″垝(**316 琛屻€?3 涓被銆?3 涓?donor**),瑁呰繘 loader jar |
| 2 | `NoSuchFieldError: net.minecraftforge.client.RenderTypeGroup 娌℃湁鎴愬憳 EMPTY`,浜庢槸 `Caught error loading resourcepacks`,鍐嶆槸 `Failed to wait for future Registration events` | stub 鍙粠 **OptiFine jar** 鎵嚭鏉?鑰?OptiFine jar 鍙偣鍑?**3** 涓垚鍛?琚《鏇跨殑**琛ヤ竵绫?*杩樼偣鍑?**51** 涓?`EMPTY` 灏卞湪閲岄潰 | 鐢熸垚 stub 鏃舵妸**琛ヤ竵 jar 涓€璧?*鍠傝繘鍘?瀹炴祴 3 鈫?**54** 涓垚鍛? |
| 3 | `IncompatibleClassChangeError: BlockEntity 浠ユ帴鍙?CapabilityProvider 浣滀负鐖剁被`;淇帀鍚?`InstantiationError: RenderTypeGroup`;鍐嶄慨鎺夊悗 `InstantiationError` 鍑鸿嚜 stub 鑷繁鐨?`<clinit>` | `ForgeApiShims` 鐢?OptiFine 鑷繁閭ｄ唤鎷疯礉"鍒ゆ柇绫诲瀷鏄帴鍙ｈ繕鏄被,鑰屽畠鎵剧殑鏄?`notch/<Forge 绫诲瀷>.class` 鈥斺€?**Forge 绫诲瀷鍦?OptiFine 閲屾牴鏈笉鍙兘鏈夎繖浠芥嫹璐?*(OptiFine 鍙戠殑鏄父鎴忕被,涓嶆槸 Forge 鐨?,鎵€浠ユ煡鎵炬案杩滆惤绌?鎺ュ彛杩欎釜鍏滃簳姘歌繙鐢熸晥,**姣忎釜 stub 閮芥垚浜嗘帴鍙?* | 鏀规垚**鎸夌敤娉?*鍐冲畾(瑙佺涓夎妭) |

绗?3 鏉￠噷"`new` 涓€涓帴鍙?鐨勯偅涓€姝ユ渶瀹规槗琚璇?鎶ラ敊鏍堥《鏄?
`at net.minecraftforge.client.RenderTypeGroup.<clinit>`,涔熷氨鏄宕╃殑涓嶆槸璋冪敤鏂?鑰屾槸 **stub 鑷繁鐨勯潤鎬佸垵濮嬪寲鍣?* 鈥斺€?
瀹冭缁?`EMPTY` 璧嬪€煎氨寰楁瀯閫犱竴涓疄渚?鑰屾帴鍙ｄ笉鑳芥瀯閫犮€?

### 涓夈€乣ForgeApiShims` 鐨勪慨娉?宸叉彁浜?`1.21.x` `f6eeec2`)

褰㈢姸鏀逛负鍦?*璇诲紩鐢ㄧ被鐨勬椂鍊?*璁板綍涓嬫潵,鍙涓ょ"鍙湁绫绘墠鍏佽"鐨勭敤娉?

- 鏈夌被**缁ф壙**瀹?`ClassVisitor.visit` 鐨?`superName`);
- 鏈夌被**鏋勯€?*瀹?`visitTypeInsn` 鐨?`NEW`,鎴?`visitMethodInsn` 鐨?`<init>`);
- 浠ュ強**鑷繁澹版槑浜嗚嚜宸辩被鍨嬬殑瀛楁**(`declaresItself`)鈥斺€?鍥犱负缁欒繖绉嶅瓧娈佃祴鍊煎氨瑕佹瀯閫犲疄渚?鑰屾帴鍙ｄ笉鑳姐€?
  `RenderTypeGroup.EMPTY` 姝ｆ槸杩欎竴绉嶃€?

鑰佺殑閭ｆ潯鏌ユ壘鐣欑潃,鍙綔涓?娌℃湁浠讳綍杩欑被鐢ㄦ硶"鏃剁殑鍏滃簳銆傛敼瀹屽疄娴?`CapabilityProvider` 涓?`RenderTypeGroup` 鍙樻垚
**class**,鑰岀湡鐨勮鏄帴鍙ｇ殑 `RenderType`銆乣ChunkRenderTypeSet` 浠嶇劧鏄?**interface**銆?

鍙﹀涓ゆ潯涓嶅湪杩欒疆鐨勪唬鐮佹敼鍔ㄩ噷銆佷絾灞炰簬鍚屼竴涓?rig 缂哄彛鐨?

- stub 鐨勬壂鎻忚寖鍥磋鍖呭惈**琛ヤ竵绫?*(瑙佷笂琛ㄧ 2 鏉?,杩欐槸璋冪敤鏂圭殑浜?rig 鐜板湪涓よ竟閮藉杺銆?
- **`HierarchyPlan` 鐨?`reparent.txt` 鍦?1.21.4 涓婁笉璧蜂綔鐢?*,鍘熷洜鍊煎緱鍗曠嫭璁?`OptiFineTransformer` 涓庢湰椤圭洰鐨?
  `PatchedClassTransformer` **鍚勮嚜閮藉０鏄庝簡鍚屼竴鎵?474 涓洰鏍?*,鑰?OptiFine 鐨勬帓鍦ㄥ墠闈?鈥斺€?浜庢槸鎴戜滑鐨?
  `reparent(patched, input)` 鎷垮埌鐨勬槸**宸茬粡琚?OptiFine 鎹㈣繃鐨?* `input`,瀹冪殑 `superName` 涓?payload 鐨勭浉鍚?
  `sameName` 鍒ょ湡,鏁存 reparent 琚烦杩囥€?*缁撹:鍦?1.21.4 杩欐潯绾夸笂,绂荤嚎杞借嵎涓?OptiFine 鑷繁鐨?transformer 涓嶈兘鍚屾椂鐢?*
  鈥斺€?杩欎笌鏈垎鏀?README 鏃╁氨鍐欎笅鐨?1.21.4 璧?OptiFine 鑷繁鐨勮繍琛屾湡琛ヤ竵"涓€鑷淬€備笂闈㈤偅娆￠€氳繃鐨勮繍琛屽氨鏄?*涓嶅甫杞借嵎**鐨?
  loader jar 鍙湁 0.28 MB(璁″垝 + donor + 55 涓?stub),娓告垙绫荤敱 OptiFine 鐨?transformer 椤舵浛銆?

### 鍥涖€佸彟澶栦袱娆?鐪嬭捣鏉ュ儚鍗℃"鍏跺疄鍚勬湁鍘熷洜

- **`IllegalStateException: Already building.`**(`fml_earlydisplay.SimpleBufferBuilder.begin` 鈫?
  `DisplayWindow.paintFramebuffer` 鈫?`NeoForgeLoadingOverlay.render`):鏈枃浠朵负 1.20.4 璁拌繃鐨勯偅涓?early window 閲嶅叆,
  杩欎竴杞湪 **21.4.149** 涓婁篃閲忓埌浜嗐€傚畠杩樻槸**绔炴€?*:鍚屼竴浠介厤缃湁涓€娆″穿銆佹湁涓€娆℃病宕┿€?
- **鎹㈡垚 `earlyWindowProvider = "none"` 涔嬪悗涓嶅啀鏄穿,鑰屾槸鍋滃湪鍔犺浇鐣岄潰**銆備袱娆?`jstack` 鐩搁殧绾?40 绉掑舰鐘剁浉鍚?
  Render 绾跨▼鍦?`Minecraft.runTick 鈫?RenderSystem.limitDisplayFPS 鈫?glfwWaitEventsTimeout` 閲岀┖杞?**涓嶆槸**鍗′綇;
  16 涓?`Worker-Main-*` 鍏ㄩ儴 `WAITING (parking)`銆丗orkJoinPool 闃熷垪涓虹┖銆傜獥鍙ｆ埅鍥句笌瀵圭収璺戝姣旀槸**鍒ゅ畾鎬х殑**:
  瀵圭収璺?鏍囬鐢婚潰)鏁村箙鍧囧€?RGB 鏄?(78,78,75) 鐨勭伆銆佸竷灞€鏄爣蹇楀湪涓?鎸夐挳灞呬腑;鍋滄粸閭ｆ鏁村箙 **94% 鏄钩鐨?(224,64,64) 绾?*,
  涓棿涓€鍧楁枃瀛楀姞涓€鏉℃í鍚戣繘搴︽潯 鈥斺€?閭ｆ槸 NeoForge 鐨勫姞杞界晫闈?涓嶆槸鏍囬鐢婚潰銆?*娉ㄦ剰绐楀彛鏍囬涓嶈兘褰撳垽鎹?*:
  瀵圭収璺戝拰鍋滄粸璺戦兘鍙?`Minecraft NeoForge* 1.21.4`,閭ｄ釜鏄熷彿涓庡姞杞芥棤鍏炽€?
- 椤哄甫:`-WindowStyle Minimized` 鍔犲瀭鐩村悓姝ヤ細璁?Render 绾跨▼姝诲湪 `glfwSwapBuffers`;涓嶆渶灏忓寲骞舵妸
  `enableVsync:false` 鍐欒繘 `options.txt` 涔嬪悗娑堝け銆傝繖涓?`OptiFabric` 閭ｄ唤 DEVELOPMENT 璁扮殑鎴愬洜涓€鑷淬€?

### 浜斻€佽竟鐣?

- 杩欎竴杞彧璁?**1.21.4 涓€鏉＄嚎**閫氳繃,鍏朵綑 14 鏉?*娌℃湁璺?*銆傚畠浠悇鑷蛋鐨勮矾涓嶅悓(1.21.1 / 1.21.3 / 1.21.8 绂荤嚎鎹㈢被銆?
  1.20.x 鐨?SRG 閲嶆槧灏勩€?.21.9 鈥?1.21.11 鐨?FML 10 鎸傝浇鐐广€?6.1.2 鐨?OptiFine 鑷甫 `ClassProcessor`),
  涓嶈兘鐢辫繖涓€鏉″鎺ㄣ€?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**,宸插彂甯冪殑 10 涓?jar 涔熸病鏈夐噸鏂版瀯寤恒€?
- 閫氳繃鐨勬槸**鍒ゅ畾鏍囧噯**(鏍囬鐢婚潰 + 澹伴煶寮曟搸 + 鏃犲穿婧冩姤鍛?+ stderr 0 瀛楄妭),涓嶆槸"鍔熻兘瀹屽":鍏夊奖鍖呫€佹姉閿娇銆?
  杩涗笘鐣岃繖浜涢兘杩樻病娴嬨€?

## 2026-09-19(绗笁娈?:寰€绗簩鏉＄嚎璧?鈥斺€?1.21.8 璧板埌鏈€鍚庝竴閬撳潕

1.21.4 閫氳繃涔嬪悗鎸夊悓鏍风殑璺暟鍋?**1.21.8**(NeoForge 21.8.54,OptiFine `J6_pre16`,鍚屾牱鏄?*涓嶅甫绂荤嚎杞借嵎**鐨勯厤缃?
娓告垙绫荤敱 OptiFine 鐨?transformer 椤舵浛,loader jar 鍙甫璁″垝銆乨onor銆乻tub銆乺eparent)銆傝繖涓€杞负浜嗕笉鍐嶉潬鎵嬫暡鍛戒护,
鎶婃瘡涓嚎閮借閲嶅鐨勯偅涓€娈靛啓鎴愪簡 rig 閲岀殑 `prepare-line.ps1`(鍙犺繍琛屾椂瑙嗗浘 鈫?鎵撹ˉ涓?鈫?杩樺師璁″垝 鈫?stub 鈫?鍒嗗眰璁″垝),
鍏朵綑浠嶆棫鏄?`build-jars.ps1` + `launch.ps1`銆傚畠涓€娆¤窇閫?浜у嚭:杩愯鏃惰鍥?9498 椤广€佽ˉ涓佺被 **516**(441 net/minecraft)銆?
杩樺師璁″垝 **353 琛?/ 88 涓?donor**銆?*60 涓?stub**銆乺eparent 1 鏉°€?

### 涓€銆佸張韪╁埌 `ForgeApiShims` 鐨勪袱涓舰鐘剁己闄?閮藉凡淇苟鎺ㄩ€?

1. **鍚屼竴涓瀯閫犲櫒鍐欎簡涓ら亶**銆俿tub 鍏堟棤鏉′欢鍐欎竴涓?`()V`,鍚庨潰閭ｄ釜"鎶婅褰曞埌鐨勬垚鍛橀兘鍐欏嚭鏉?鐨勫惊鐜張鍐欎竴閬?浜庢槸:

   ```
   ClassFormatError: Duplicate method name "<init>" with signature "()V" in class file
     net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities
   ```

   鐜板湪鍙湁褰撹褰曢噷娌℃湁 `<init> ()V` 鏃舵墠鍐欓粯璁ら偅涓€傝繖鏄繖涓敓鎴愬櫒**绗洓涓?*闈?鐪熺殑鎶?shim 鏀捐繘瀹㈡埛绔窇"鎵?
   鏆撮湶鍑烘潵鐨勫舰鐘剁己闄?鍓嶄笁涓槸鎺ュ彛/绫荤殑鍒ゆ柇銆佽嚜宸辩被鍨嬬殑瀛楁銆佷互鍙婃壂鎻忚寖鍥?銆?

2. 椤哄甫鎶婁笂涓€娈电殑鎺ュ彛/绫讳慨姝?*绉绘鍒?`1.20.x` 涓?`26.x`**:杩欎袱涓垎鏀殑 `ForgeApiShims.java` 涓?1.21.x 淇箣鍓?
   鏄€愬瓧鑺傜浉鍚岀殑鍓湰,鎵€浠ョ敤 `git diff` 鍑虹殑琛ヤ竵鐩存帴 `git apply`(瀹炴祴 `--check` 閫氳繃),涓嶅仛鎵嬫敼銆?

### 浜屻€?.21.8 涓婇噺鍒扮殑涓や欢浜?

- **`optionsof.txt` 涓嶅湪鏃剁殑绗竴娆″惎鍔ㄤ細鎶涘紓甯?*:
  `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at `Options.loadOfOptions`
  (1.21.8 鏄?`Options.java:3108`,1.21.4 涓婃槸 3071,涓ょ増鍚屼竴涓己闄?銆傛枃浠跺瓨鍦ㄤ笖鍐呭鍚堟硶鏃朵笉鍐嶅嚭鐜?鈥斺€?
  涔熷氨鏄杩欐槸 **OptiFine 鑷繁鍦?鏂囦欢杩樹笉瀛樺湪"杩欎竴鏀笂鐨勯棶棰?*,涓嶆槸鎴戜滑鎹㈢被鎹㈠潖鐨勩€傚疄鏈鸿〃鐜?绗竴娆?stderr
  2268 瀛楄妭,绗簩娆?**0 瀛楄妭**,鑰?`[OptiFine]` 琛屾暟浠?58 鍙樻垚 56(娌℃湁鍒殑鍙樺寲)銆俽ig 鍥犳鍙互鐓?
  `OptiFabric` 閭ｄ唤 launch 鑴氭湰鐨勫仛娉?鍦ㄥ缓 game dir 鏃跺厛鍐欎竴浠藉悎娉曠殑 `optionsof.txt`銆?
- **`GpuTexture.isStencilEnabled()` 缂哄け**(褰撳墠鐨勫潕):

  ```
  java.lang.NoSuchMethodError: 'boolean com.mojang.blaze3d.textures.GpuTexture.isStencilEnabled()'
    at com.mojang.blaze3d.opengl.GlCommandEncoder.clearColorTexture(GlCommandEncoder.java:196)
    at net.minecraft.client.renderer.LightTexture.<init>(LightTexture.java:79)
    at net.minecraft.client.renderer.GameRenderer.<init>(GameRenderer.java:181)
    at net.minecraft.client.Minecraft.<init>(Minecraft.java:599)
  ```

  杩欐槸**鍙嶆柟鍚?*鐨勭己澶?璋冪敤鏂规槸 OptiFine 鎹㈣繃鐨勭被,鑰岃璋冪敤鐨?`GpuTexture` 鏄?*杩愯鏃惰嚜宸辩殑**绫?鈥斺€?
  鎵€浠?`member-restores.txt` 甯笉涓?瀹冨彧寰€琚崲鎺夌殑绫婚噷琛ユ垚鍛?,瑕侀潬 loader 鐨?`stubs.txt`(寰€杩愯鏃剁被閲岃ˉ)銆?
  `MissingTargets --stub` 杩欎竴杞?*宸茬粡璺戦€氬苟浜у嚭 804 鏉?*,浣嗚繖涓€娆″穿婧冩病鏈夎瀹冭鐩?鍘熷洜鏄畠鑷繁涔熸妸
  **杞借嵎**绱㈠紩杩涘幓浜?瀹冪殑鏂囨。鍐欏緱寰堟竻妤?鈥斺€?OptiFine 浼氱粰瀹冩浛鎹㈢殑绫?*鍔?*鎴愬憳,鎵€浠?杩愯鏃舵病鏈夈€佽€岃浇鑽锋湁"鐨勫紩鐢?
  琚涓烘槸鍙弧瓒崇殑銆傝繖閲?`GpuTexture` 鐨勬儏褰㈡槸:杞借嵎閲屾湁杩欎釜鎴愬憳,浣?*鐪熸琚姞杞界殑閭ｄ唤娌℃湁**(杩欎竴绫诲湪 1.21.8 涓?
  娌℃湁琚崲瑁?,浜庢槸宸ュ叿娌℃姤銆佷篃灏辨病杩?`stubs.txt`銆?*涓嬩竴闂?*:瀵?璋冪敤鏂硅鎹€佽璋冪敤鏂规病琚崲"鐨勫紩鐢ㄥ崟鐙蛋涓€閬?
  鎴栬€呮妸 `--stub` 鐨勮烦杩囧墠缂€锛忚浇鑽风储寮曟寜"瀹為檯浼氳鎹㈣鐨勭被闆嗗悎"缁欏畾,鑰屼笉鏄寜琛ヤ竵浜х墿鐨勫叏闆嗐€?

### 涓夈€佽繖涓€娈电殑杈圭晫

- **1.21.8 杩樻病鏈夐€氳繃**:瀹冨埌浜?`Setting user`銆乻tderr 0 瀛楄妭,浣?`Sound engine started` 娌″嚭鐜?骞朵笖鍐欎簡涓€浠?
  宕╂簝鎶ュ憡(`GpuTexture.isStencilEnabled`)銆?*1.21.4 鐨勯€氳繃娌℃湁鍙楀埌褰卞搷**(閭ｄ竴杞箣鍚庢病鏈夊啀鍔ㄨ繃瀹?銆?
- 涓夋潯鍒嗘敮鐨勮繖涓€杞敼鍔ㄩ兘宸叉彁浜ゅ苟鎺ㄩ€?`1.21.x` 涓変釜鎻愪氦(鏈嶅姟娉ㄥ唽銆乻him 褰㈢姸銆佹瀯閫犲櫒閲嶅)銆乣1.20.x` 涓変釜鎻愪氦
  (鍏冩暟鎹?鏋勫缓 + 褰㈢姸淇绉绘 + 鏈枃浠?銆?*娌℃湁鍙戝竷浠讳綍涓滆タ銆?*

## 2026-09-19(绗洓娈?:1.21.8 鐨?shim 琛ラ綈,浠ュ強鍗′綇瀹冪殑閭ｄ竴姝ヤ笉鏄崲绫昏€屾槸鍒嗗眰

涓婁竴娈垫妸 1.21.8 鍋滃湪"`GpuTexture.isStencilEnabled` 缂哄け",骞剁寽 `MissingTargets` 娌¤鐩栧埌瀹冦€傝繖涓€杞妸 shim
杩欎竴灞傝ˉ榻愪簡(`1.21.x` `5bbe9dd`),**`[OptiFine]` 琛屾暟浠?62 鍙樻垚 442**,浣嗛偅鏉＄嚎浠嶆湭閫氳繃 鈥斺€?鑰屽崱浣忓畠鐨勪笢瑗挎崲浜嗐€?

### 涓€銆乣ForgeApiShims` 鍙堣ˉ浜嗕袱澶?閮芥槸"璺戣捣鏉ユ墠鐭ラ亾"

1. **鎴愬憳涓嶈兘鍙粠璋冪敤鐐规帹**銆傚師鏉ョ殑澹冲彧澹版槑"OptiFine 鐨勭被鍦ㄨ繖涓被鍨嬩笂鐐瑰悕鐨勬垚鍛?,浣?*璋冪敤鍙互鍐欏湪瀛愮被鍨嬩笂銆?
   缁忚繃鐖剁被鍨嬭В鏋?* 鈥斺€?浜庢槸澹充笉瀹屾暣銆傚疄娴?`IForgeGpuTexture.isStencilEnabled` 鏄互
   `GpuTexture.isStencilEnabled` 鐨勫舰寮忚璋冪敤鐨?鎵€浠ュ３閲屾病鏈夊畠,鑰?**OptiFine 鑷甫鐨?`GpuTexture` 宸茬粡瑁呬笂浜?*
   涔熺収鏍峰穿銆傜幇鍦ㄦ妸 OptiFine 鑷甫閭ｄ唤鎷疯礉鐨?*澹版槑**璇昏繘鏉ャ€?
   娉ㄦ剰**涓嶆惉瀛楄妭**:绗竴鐗堝氨鏄収鎼?缁撴灉鏍藉湪鍙︿竴澶?鈥斺€?OptiFine 鑷甫鐨勭鍚嶅啓鍦ㄥ畠鑷繁鐨?*娣锋穯**鍛藉悕绌洪棿閲?
   浜庢槸 `NoClassDefFoundError: avs`銆傚垽鎹槸"鎻忚堪绗﹂噷鐨勭被鍨嬫槸鍚﹀湪鍖呭唴":娣锋穯鐨勬父鎴忕被鏄崟娈靛悕(`avs`銆乣fmk`),
   鑰岃兘瑙ｆ瀽鍒扮殑閮芥湁鏂滄潬銆?
2. **绫诲瀷鐨勯泦鍚堜笉鑳藉彧闈犲紩鐢ㄦ壂鎻?*銆傛壂鎻忓彧鐪嬭鍐欒繘鎻忚堪绗﹀拰鎸囦护鐨勫悕瀛?鑰?ModLauncher 鍦ㄥ彉鎹竴涓被鏃?*瑕佽蛋瀹冪殑
   鐖剁被涓庢帴鍙ｉ摼**,閭ｄ釜绫诲瀷蹇呴』瀛樺湪銆佸摢鎬曟病鏈変竴澶勫瓧鑺傜爜鐐瑰悕瀹冦€傚疄娴?`Cannot find class
   net/minecraftforge/common/extensions/IForgeEntity` 鈫?`TransformerClassWriter.computeHierarchyFromFile`,
   鑰岃繖涓被鍨嬫棦涓嶅湪鎵弿鍑虹殑 60 涓噷,涔熶笉鍦ㄥ３閲屻€傜幇鍦?*OptiFine 鑷甫鐨?Forge 绫诲瀷鍏ㄩ儴绾冲叆**,闆嗗悎浠?60 鍙樻垚 **89**銆?
3. 椤哄甫:璁板綍鍒扮殑 `<clinit>` 浼氬拰澹宠嚜宸卞啓鐨勯偅浠芥挒杞?`ClassFormatError: Duplicate method name "<clinit>"`),
   鐜板湪鏋勯€犲櫒涓庨潤鎬佸垵濮嬪寲鍣ㄩ兘涓嶅啀浠庢嫹璐濋噷璁般€?

### 浜屻€?.21.8 鐜板湪鍋滃湪鍝?鍒嗗眰,鑰屼笖**閭ｄ竴姝ユ牴鏈病璺?*

鏂扮殑宕╂簝涓嶅啀鏄己鎴愬憳,鑰屾槸:

```
java.lang.VerifyError: Bad type on operand stack
  Location: net/neoforged/neoforge/client/network/ClientPayloadHandler.handle(...)
  Reason: Type 'net/minecraft/world/level/block/entity/BlockEntity' is not assignable to
          'net/neoforged/neoforge/attachment/AttachmentHolder'
```

**杩欐鏄湰浠撳簱 `PatchedClassTransformer` 鑷繁鐨勬敞閲婇噷璁拌繃鐨勯偅涓€鏉?*(娉ㄩ噴閲岃繛 `AttachmentSync.onChunkSent`
@82 鐨勫師鏂囬兘鎶勪簡),涔熷氨鏄瀹冨睘浜?`reparent.txt` 瑕佸鐞嗙殑鎯呭舰銆傝繖涓€杞噺鍒扮殑浜嬪疄:

| 浜嬪疄 | 鍒ゆ嵁 |
|---|---|
| 杩愯鏃?`BlockEntity` 缁ф壙 `net.neoforged.neoforge.attachment.AttachmentHolder` | `javap` 杩愯鏃惰鍥?|
| OptiFine 鐨?`BlockEntity` 缁ф壙鐨勬槸 **`net.minecraftforge.common.capabilities.CapabilityProvider$BlockEntities`**(宓屽绫?涓嶆槸 `CapabilityProvider`) | `javap` 琛ヤ竵浜х墿 |
| 璁″垝鐨勭 3 涓瓧娈典笌杩愯鏃朵竴鑷?`()V`),璁″垝鏈韩姝ｇ‘ | `reparent.txt` 鍐呭 + `Hierarchy rewrites planned: 1` |
| shim 榻愬叏(`CapabilityProvider$BlockEntities.class` 鍦?loader jar 閲? | 璧勬簮妫€鏌?|
| **reparent 涓€娆′篃娌℃垚鍔熸墽琛?* | 鏃ュ織閲?`Re-parented` **0 琛?* |
| 涔?*涓嶆槸琚嫆缁?* | 鏃ュ織閲?`Left ... BlockEntity alone` **0 琛?*(鍙︽湁 3 涓被琚嫆,閮戒笉鏄畠) |

涓や欢浜嬮兘涓洪浂,鍙墿涓€绉嶈В閲?`if(!sameName(patched.superName, input.superName))` 杩欎釜**鍓嶇疆鍒ゆ柇娌℃垚绔?* 鈥斺€?
涔熷氨鏄?transformer 鏀跺埌鐨?`input`(瀹冭涓虹殑"杩愯鏃堕偅浠?)鐨勭埗绫?**宸茬粡绛変簬** payload 鐨勭埗绫?鍗抽偅涓?Forge 绫诲瀷銆?
鎵€浠ユ暣娈?reparent 琚烦杩?瑁呰繘鍘荤殑 `BlockEntity` 浠嶇劧鎸傜潃 Forge 鐨勭埗绫?VerifyError 闅忎箣鑰屾潵銆?

鍙︿竴澶勫€煎緱骞舵帓鐪嬬殑浜嬪疄:**1.21.8 涓?`OptiFineTransformer` 娌℃湁鎵撳嵃 `Targets:`**(1.21.4 涓婃墦鍗?`Targets: 474`),
鏈」鐩殑 `Patched-class targets: 516` 鏄敮涓€鐨勩€傛墍浠ヨ繖涓€鏉＄嚎涓婂苟涓嶅瓨鍦?涓や釜 transformer 鎶㈠悓涓€鎵圭被"鐨勯棶棰?
`input` 涓轰粈涔堝凡缁忔槸 Forge 鐗堟湰,鏄?*涓嬩竴闂?*銆?

### 涓夈€乺ig 鐨勪竴鏉℃搷浣滀簨瀹?鐪佷笅涓€娆″悓鏍风殑鍥版儜)

鏂板缓鐨?game dir 閲?`config/fml.toml` 鏄?*榛樿鐨?`fmlearlywindow`**,浜庢槸浼氭挒涓婃湰鏂囦欢璁拌繃鐨?
`IllegalStateException: Already building.`(1.21.8 涓婅繖娆″嚭鐜板湪 `PerformanceElement.render` 鈫?杩涘害鏉?銆?
`launch.ps1` 鐨?`-EarlyWindowProvider skip` 鏄?鍒姩杩欎釜鏂囦欢",鎵€浠ユ柊鐩綍绗竴娆¤窇瑕佷箞涓嶄紶杩欎釜寮€鍏炽€佽涔堝厛鍐?
`earlyWindowProvider = "none"`銆?

### 鍥涖€佽竟鐣?

- **1.21.8 浠嶆湭閫氳繃**;`[OptiFine]` 442 琛岃鏄庡畠宸茬粡璧板埌寰堝悗闈?1.21.4 閫氳繃涓庡畠鍚岄噺绾?銆?*1.21.4 鐨勯€氳繃涓嶅彈褰卞搷銆?*
- 杩欎竴杞彧鍔ㄤ簡鐢熸垚 shim 鐨勫伐鍏?娌℃湁纰?loader 鐨勬崲绫婚€昏緫;**reparent 閭ｄ竴闂病鏈夌粨璁?*,鍙妸"瀹冩病璺?涓?
  "涓轰粈涔堟病璺?鐨勮竟鐣岄噺娓呮浜嗐€?
- 鍏朵綑 13 鏉＄嚎娌℃湁璺?**娌℃湁鍙戝竷浠讳綍涓滆タ**銆?

### 浜斻€佽ˉ鍏?閭ｄ竴闂凡缁忕瓟浜?reparent 涓轰粈涔堟病璺?

涓婁竴鑺傛妸"`input` 涓轰粈涔堝凡缁忔槸 Forge 鐗堟湰"鐣欐垚浜嗕笅涓€闂€傝繖涓€杞粰 `PatchedClassTransformer` 鍔犱簡涓€琛屾棩蹇?
(`1.21.x` `0af9601`)鈥斺€?鍥犱负"璁″垝瑕嗙洊鐨勭被"琚潤榛樿烦杩囨椂,`Re-parented` 涓?`Left ... alone` **鍚屾椂涓洪浂**,
鑰岃繖涓?璁″垝鏍规湰娌¤璇诲埌"鏄悓涓€涓舰鐘?鍒嗕笉娓呭氨瑕佸鑺变竴杞€傛棩蹇楀疄娴嬭緭鍑?

```
Reparent plan covers net.minecraft.world.level.block.entity.BlockEntity:
  the payload extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities,
  the class handed over extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities
  - equal, so nothing is rewritten and the copy is installed as it is
```

涔熷氨鏄?*鍒ゆ嵁鏈韩**,涓嶇敤鍐嶇寽:浜ょ粰 transformer 鐨勯偅浠?`input`,鐖剁被**宸茬粡鏄?* Forge 绫诲瀷銆備簬鏄繖涓€鏉＄嚎涓?
`BlockEntity` 濮嬬粓鎸傜潃 Forge 鐖剁被,鍥犳灉閾炬槸瀹屾暣鐨?

1. reparent 琚烦杩?`1.21.x` 涓?`Re-parented` 0 琛屻€乣Left ... BlockEntity alone` 0 琛? 鈫?
2. `VerifyError: Bad type on operand stack`,`BlockEntity` 涓嶈兘璧嬬粰 `AttachmentHolder`
   (`ClientPayloadHandler.handle`)鈫?FML 鎶?`Failed to wait for future Mod Construction` 鈫?
3. FML 闅忓悗瀵规敞鍐屼簨浠?`Cowardly refusing to send event ...`,鑰?OptiFine 鍗″湪
   `[OptiFine] Waiting for model sprites`(180 绉掗噷鍒蜂簡 33 娆?娌℃湁鍥鹃泦銆佹病鏈夊０闊冲紩鎿?銆?

杩欎竴杞姞鐨勬槸**鏃ュ織涓嶆槸淇硶**:淇硶鍙栧喅浜?涓轰粈涔堜氦杩囨潵鐨勯偅浠藉凡缁忔槸 OptiFine 鐨?,杩欎竴鐐逛粛鏈畾銆?

### 鍏€佷竴鏉℃柟娉曡鏁欒(rig 宸偣缁欏嚭涓€娆″亣閫氳繃)

鎶?mod jar 鍚嶅瓧鍐欑┖鏃?`launch.ps1` 浼氭妸**鐩綍**褰撴垚 jar 澶嶅埗杩?`mods/`,浜庢槸閭ｄ竴娆¤繍琛屾姤鍑?
`VERDICT: STARTED`銆乣Setting user` 鉁撱€乣Sound engine started` 鉁撱€? 宕╂簝鎶ュ憡銆乻tderr 0 瀛楄妭 鈥斺€?鑰?
**`[OptiFine]` 琛屾暟鏄?0**,涔熷氨鏄?OptiFine 鏍规湰娌″姞杞姐€傚垽瀹氳剼鏈幇鍦ㄤ細鎷掔粷闈炴枃浠剁殑 mod 璺緞銆?

**璁拌繖涓€鏉＄殑鍘熷洜姣斿畠鏈韩閲嶈**:鏈」鐩妸"`[OptiFine]` 琛屾暟"褰撻獙鏀舵暟瀛椾箣涓€,鑰岃繖涓€娆″畠鎭板ソ鏄敮涓€鑳芥埑鐮?
鍋囬€氳繃鐨勯偅涓€鍒椼€傚垽鎹噷鐨勬瘡涓€鍒楅兘涓嶆槸瑁呴グ銆?

## 2026-09-19(绗簲娈?:reparent 淇ソ浜?鑰屽畠闇插嚭鐨勬槸涓€鏉℃洿娣辩殑鍒嗘

### 涓€銆乺eparent 鐜板湪鐪熺殑鎵ц浜?`1.21.x` `6f66c27`)

涓婁竴鑺傞噺鍒?浜ょ粰 transformer 鐨勯偅浠?`input`,鐖剁被宸茬粡鏄?Forge 绫诲瀷"銆傛嵁姝ゆ敼鎺夐偅涓墠缃垽鏂?璁″垝瑕嗙洊鐨勭被
**鍗充娇涓や唤鐨勭埗绫荤浉鍚屼篃鐓у仛**,鑰?杩欎唤鏄笉鏄繍琛屾椂閭ｄ唤"鏀圭敱"瀹冩槸涓嶆槸宸茬粡鎸傜潃 Forge 鐖剁被"鏉ュ垽瀹?鍒ゅ畾涓?
涓嶅彲鐢ㄦ椂,`reparent()` 璺宠繃瀹冨凡缁忔棤娉曞仛鐨勯偅娆℃瘮瀵?璁″垝鏈潵灏辨槸 `HierarchyPlan` 鐢ㄥ悓涓€鎵?jar 閲忓嚭鏉ョ殑),
骞朵笖**涓ょ缁撴灉閮藉悇鎵撲竴琛屾棩蹇?*銆?

瀹炴祴(1.21.8):

```
Re-parented net.minecraft.world.level.block.entity.BlockEntity from the Forge type
  net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities onto the runtime's 鈥?
```

`VerifyError: Bad type on operand stack` **浠庢棩蹇楅噷娑堝け浜?*,`Failed to wait for future Mod Construction` 涔熶笉鍐嶅嚭鐜般€?
杩欐潯绾?*浠嶆湭閫氳繃**,浣嗗崱鐐规崲鍒颁簡涓嬩竴澶勩€?

### 浜屻€佹柊鍗＄偣鐨?*鏍瑰洜宸茬粡鏌ュ埌鏁版嵁灞?*,涓嶆槸鐚滅殑

```
ClassFormatError: Illegal field modifiers in class
  net/minecraft/client/renderer/block/model/BlockStateModel$Unbaked: 0x9
```

`0x9` = `public static`(缂?`final`),鑰?*鎺ュ彛閲岀殑瀛楁蹇呴』鏄?`public static final`**銆備袱杈圭殑瀹炴祴:

| | 褰㈡€?| 閭ｄ袱涓瓧娈?|
|---|---|---|
| 杩愯鏃剁殑绫?`runtime-1.21.8.jar`) | `public interface 鈥 | `public static final 鈥?SINGLE_MODEL_CODEC` / `WEIGHTED_MODEL_CODEC` |
| **donor**(`plan\donors\鈥lockStateModel$Unbaked.class`) | `public class 鈥?implements 鈥 鈥斺€?**琚啓鎴愭櫘閫氱被** | `public static 鈥(**娌℃湁 final**) |

涔熷氨鏄:**`MemberRestorePlan` 鎶?donor 鍐欐垚鏅€氱被銆佸苟鎶婂瓧娈电殑 `final` 鍘绘帀**(鍘绘帀鏄负浜嗚杩愯鏃惰兘鐢?
`optifineoforge$init$鈥 濉€?,鑰?`MemberRestoreTransformer` **鍘熸牱**鎶婂瓧娈靛鍒惰繘鐩爣绫?鈥斺€?鐩爣鏄帴鍙?浜庢槸
`0x9` 闈炴硶銆俙PatchedClassTransformer` 閲?*鏈?*杩欐潯瑙勫垯(鎺ュ彛瀛楁涓€寰嬪己鍒?`public static final`,鏈枃浠跺湪 26.x 鐨?
娉ㄩ噴閲屼篃鍐欒繃"鎺ュ彛鐨勫瓧娈靛繀椤绘槸 public static final,鍚﹀垯 JVM 鐩存帴浠?ClassFormatError 鎷掔粷"),浣?
`MemberRestoreTransformer` 閲?*娌℃湁**銆?

### 涓夈€佺敱姝ら湶鍑虹殑鏇存繁涓€灞?涓嬩竴闂?

鎶婁笁浠朵簨骞舵帓鐪?

1. 1.21.8 涓?`OptiFineTransformer` **涓嶆墦鍗?* `Targets:`(1.21.4 涓婃墦鍗?`474`),浣嗛偅浠?`input` 鐨勭埗绫?*宸茬粡鏄?
   Forge 绫诲瀷** 鈥斺€?璇存槑**鍏堟崲绫荤殑鏄?OptiFine**,鎴戜滑鐨?transformer 鎷垮埌鐨勬槸瀹冩崲杩囩殑閭ｄ竴浠?
2. 鍥犳鏈枃浠剁殑鍒ゆ柇閲?"淇濇寔杩愯鏃剁殑鐗堟湰"杩欎竴绫诲喅瀹?閭?3 涓?`Left ... alone`)鍦?1.21.8 涓?*骞朵笉浼氱湡鐨?
   鎷垮埌杩愯鏃剁殑鐗堟湰**,鑰屾槸鐣欏湪 OptiFine 鐨勯偅涓€浠戒笂;
3. 涓婇潰绗簩鑺傜殑宕╂簝姝ｆ槸杩欎竴绫荤殑鍚庢灉涔嬩竴銆?

**鎵€浠ヤ笅涓€闂笉鏄煇涓瓧娈电殑鏍囧織浣?鑰屾槸**:loader 闇€瑕佹嬁鍒?*鐪熸鐨勮繍琛屾椂绫?*(瀹冨凡缁忔崟鑾蜂簡 module layer manager,
`OptifiNeoforgeTransformationService.layers()` 灏辨槸),鑰屼笉鏄妸 `input` 褰撴垚杩愯鏃堕偅浠姐€?

### 鍥涖€佽竟鐣?

- **1.21.4 浠嶇劧閫氳繃**(杩欎竴杞病纰板畠鐨勮矾寰?;**1.21.8 浠嶆湭閫氳繃**,浣嗗畠宸茬粡瓒婅繃鎹㈣涓?mod 鏋勯€犻樁娈点€?
- 杩欎竴杞敼浜?`PatchedClassTransformer` 鐨勫垽瀹氫笌 `reparent` 鐨勭┖寮曠敤瀹瑰繊;**娌℃湁**鍔?`MemberRestoreTransformer`
  (绗簩鑺傞偅鏉′慨娉曡繕娌″仛)銆?
- 鍏朵綑 13 鏉＄嚎娌℃湁璺?**娌℃湁鍙戝竷浠讳綍涓滆タ**銆?

## 2026-09-19(缁?:缁?1.20.x 鐨?loader 鍔?鍒犻櫎鎴愬憳"杩欎竴妗?1.20.4 鐨勭被瀹氫箟鍥犳淇ソ

涓婁竴鏉℃妸 1.20.4 鐨勯樆濉炲畾姝诲湪涓€涓被涓?杞借嵎鐨?`AbstractClientPlayer` 甯︿簡涓変釜瀵?final 鏂规硶鐨勯噸鍐欍€傛湰杞妸杩欎竴妗ｄ慨娉曞仛鍑烘潵浜?
骞朵笖**瀹炴祴纭疄鎶婅繖涓€澶勪慨濂戒簡** 鈥斺€?浣?1.20.4 浠嶇劧**娌℃湁閫氳繃**,鏂扮殑闃诲璁板湪涓嬮潰绗簩鑺傘€?

### 涓€銆佸姞浜嗕粈涔?浠ｇ爜鍦ㄦ湰鍒嗘敮,涓嶅湪 rig)

`PatchedClassTransformer` 鏂板绗笁涓鍒掓枃浠?`optifineoforge/drop-members.txt`,琛屾牸寮?
`owner<TAB>name<TAB>desc`,璇箟鏄?*杩欎釜鎴愬憳涓嶈鍑虹幇鍦ㄤ氦浠樺嚭鍘荤殑绫婚噷**銆傚墠涓や釜璁″垝閮借〃杈句笉浜嗚繖涓€妗?

- `keep-runtime.txt`(宸叉湁)鏄妸**娓告垙渚х殑鏂规硶浣?*鎹㈠洖杞借嵎鎴愬憳涓?鈥斺€?鍓嶆彁鏄繍琛屾椂閭ｄ釜绫?*鏈?*杩欎釜鎴愬憳;
- `member-restores.txt`(宸叉湁)鏄ˉ涓?杩愯鏃?*鏈?*銆佽浇鑽锋病鏈?鐨勬垚鍛?
- 鑰岃繖閲岃鐨勬槸"杞借嵎鏈夈€佽繍琛屾椂娌℃湁銆佷笖瀛樺湪灏变細璁╃被瀹氫箟涓嶄簡",鍙兘**鍒犳帀**銆?

璁″垝鍦?`dropMembers(ClassNode)` 閲岀敓鏁?鎺掑湪 `keepRuntimeBodies` **涔嬪悗**(鏈€鍚庤璇?,鏂规硶涓庡瓧娈甸兘瑕嗙洊,姣忓垹涓€涓垚鍛橀兘浼?
鎵撲竴琛屾棩蹇椼€俽ig 鐨?`build-jars.ps1` 鐩稿簲鏂板 `-DropMembersFile`(宓屽叆 `optifineoforge/drop-members.txt`),1.20.4 鐨勮鍒掓枃浠舵槸
rig 閲岀殑 `drop-members-1.20.4.txt`,鍐呭灏辨槸閭ｄ笁琛?`net/minecraft/client/player/AbstractClientPlayer` 涓婄殑
`getX/getY/getZ ()D`銆?

**鏁堟灉(瀹炴祴)**:`IncompatibleClassChangeError` 娑堝け,`AbstractClientPlayer` 姝ｅ父瀹氫箟;鍚屼竴娆″惎鍔ㄩ噷 `[OptiFine]` 琛屾暟浠?
**0鈥? 琛?*鎺ㄨ繘鍒?**10 琛?*,杞藉叆杩囩▼璧板埌 `Minecraft.<init>` 閲岄潰(鏃ュ織閲岃兘鐪嬪埌 `GlDebug`銆乣ClientBrandRetriever`銆?
`AbstractTexture` 绛夌被琚崲瑁呭苟鍋氭垚鍛樻仮澶?銆?

### 浜屻€?.20.4 鐜板湪鍋滃湪鍝?鏂伴樆濉?宸查噺鍒拌瘉鎹?

鍚屼竴娆″惎鍔ㄥ湪 `Minecraft.<init>` 閲屾姏寮傚父,鑰?*鍘熷寮傚父鐪嬩笉鍒?*,鍥犱负宕╂簝澶勭悊閭ｆ潯璺嚜宸卞厛姝讳簡:

```
at TRANSFORMER/srg/net.optifine.CrashReporter.extendCrashReport(CrashReporter.java:127)
at TRANSFORMER/srg/net.optifine.CrashReporter.onCrashReport(CrashReporter.java:42)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.CrashReport.getFriendlyReport(CrashReport.java:172)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.client.Minecraft.crash(Minecraft.java:949)
at TRANSFORMER/minecraft@1.20.4/net.minecraft.client.main.Main.main(Main.java:165)
Caused by: java.lang.ExceptionInInitializerError
Caused by: java.lang.NullPointerException: Cannot read field "gameDirectory" because the return value of
   "net.minecraft.client.Minecraft.getInstance()" is null
	at TRANSFORMER/srg/net.optifine.shaders.Shaders.<clinit>(Shaders.java:603)
```

涔熷氨鏄:`Main.main:165` 鏄?`catch` 鍧?**鐪熸鐨勫師濮嬪紓甯歌 `Minecraft.crash` 鍚冩帀浜?*,鑰?crash 鎶ュ憡鍦?
`CrashReporter 鈫?Shaders.<clinit>` 澶勫洜涓?`Minecraft.getInstance()` 杩樻槸 null 鍐嶆鎶涢敊銆備负浜嗘妸鍘熷寮傚父鎸栧嚭鏉?璇曚簡
JVM 鐨?`-Xlog:exceptions=trace`(87 938 琛屾棩蹇?:閲岄潰鏈夊穿婧冨鐞嗚嚜韬偅鏉￠摼(`RenderSystem.assertOnRenderThread` 鈫?
`SystemReport.setDetail` 鈫?`CrashReporter` 鈫?`Shaders.<clinit>`),**浣嗘病鏈?*鍘熷寮傚父鐨勪骇鐢熻褰?JVM 鑷繁鐢熸垚鐨?helpful
NPE 涓嶈蛋杩欎釜鏃ュ織鏍囩),鎵€浠ヨ繖涓€杞?*娌℃湁鎷垮埌鍘熷 throwable**銆?

椤哄甫纭浜嗕袱浠朵簨,閬垮厤鎶婄幆澧冮棶棰樿鍒ゆ垚鎴戜滑鐨勯棶棰?

- **鍚屼竴鍙?rig銆佸悓涓€涓疄渚嬨€佷笉鍔犱换浣?mod** 鐨勫鐓ц窇鍒颁簡 `VERDICT: STARTED`(`Setting user` 鉁撱€佸０闊冲紩鎿?鉁撱€?
  0 宕╂簝銆乻tderr 0 瀛楄妭)鈥斺€?鎵€浠?GL/鍘熺敓搴?`earlyWindowProvider` 杩欏鐜鏄ソ鐨?涓婇潰鐨勫穿婧冪‘瀹炴潵鑷垜浠繖涓€渚?
- rig 鑷韩鏈変袱涓潙涔熶慨鎺変簡(鑴氭湰鍦?rig 閲?:`natives\` 鏄?*鎵€鏈夌嚎鍏辩敤**鐨勪竴涓洰褰?鑰?1.20.1鈥?.20.4 瑕?LWJGL **3.3.2**銆?
  1.20.6 璧疯 **3.3.3**,鍚庤窇鐨勭嚎浼氭妸鍓嶄竴鏉＄嚎鐨?DLL 鐣欏湪閭ｉ噷(瀹炴祴鎶?`[LWJGL] Incompatible Java and native library
  versions detected`),鐜板湪鐢ㄦ柊澧炵殑 `natives-for.ps1 -Lwjgl 3.3.2` 鍦ㄥ惎鍔ㄥ墠閲嶅缓;鍙﹀鍚姩宕╄繃鐨?JVM **浼氱暀鍦ㄥ悗鍙?*鍗犵潃
  `glfw.dll`,閲嶈 natives 鍓嶅繀椤诲厛鏀舵帀(`launch.ps1` 鐨?"stopped the JVM" 骞朵笉鎬昏兘瑕嗙洊宕╂簝璺緞)銆?

`launch.ps1` 鍙﹀姞浜嗕竴鏉?`RIG_EXTRA_JVM`(鐢?`|` 鍒嗛殧):褰㈠ `-Xlog:...` 鐨勫弬鏁版病娉曢€氳繃 `-File` 浼犺繘鍘?鈥斺€?PowerShell 浼氭妸瀹?
褰撴垚鍙傛暟鍚?鎶?`MissingArgument`銆?

### 涓夈€佷簩鍒嗘硶鐨勭涓€娆″皾璇?`-Doptifineoforge.skipPayload=true`(缁撴灉鏈夌敤,浣?*杩欎竴闂娣锋穯浜?*)

缁?1.20.x 鐨?transformer 鍔犱簡涓€涓皟璇曞紑鍏?`-Doptifineoforge.skipPayload=true`:瀹冭**杞借嵎涓€涓被閮戒笉鎶曢€?*(stub 鐓ф棫璺?
鍥犱负鏈鎹㈣鐨勮繍琛屾椂绫婚渶瑕佸畠浠?,鐢ㄦ潵鎶婂け璐ヤ簩鍒嗗埌"鎹㈣"杩樻槸"鍏朵綑閮ㄥ垎"銆傜涓€娆¤繍琛?杞借嵎鍏抽棴)鐨勭粨鏋?*涓嶆槸**鍚屼竴涓穿婧?
鑰屾槸鍥炲埌浜嗘洿鏃╅偅鏉?

```
Caused by: java.lang.NoSuchMethodError:
  'net.minecraft.network.chat.MutableComponent net.minecraft.network.chat.Component.m_237115_(java.lang.String)'
  at net.minecraft.resources.ResourceLocation.<clinit>
  ...
  at net.minecraft.client.main.Main.main(Main.java:61)
```

鍘熷洜涔熼噺鍒颁簡:**閲嶆墦鍖呭悗鐨?OptiFine jar 閲屼粛鐒跺甫鐫€鍏ㄩ儴 4948 涓?`patch/` 鏉＄洰**(`patch/srg/**` 涓?`patch/notch/**` 閮藉湪,
鍙﹀ `net/optifine/**` 鏄?0 鏉?鈥斺€?OptiFine 鑷繁鐨勭被鍦?loader jar 閲?鏈嶅姟鏂囦欢
`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 鍦?OptiFine jar 閲?銆備篃灏辨槸璇?**OptiFine 鑷繁鐨?
transformer 浠嶅湪瑁呰浇鏈熸寜 `patch/srg` 鎵撹ˉ涓併€佸苟鎶?SRG 鍚嶅啓杩涙父鎴忕被**,杩欐鏄?1.21 閭ｆ潯绾胯褰曡繃鐨勭幇璞?1.21 鐢?
`SrgNameTable` + 瑁呰浇鏈熸敼鍚?`-Doptifineoforge.renameSrg`)鍘嬩綇瀹?鑰?*閭ｆ潯鑳藉姏鍦?1.20.x 杩欐潯绾夸笂娌℃湁**銆傛墍浠ヨ浇鑽蜂竴鍏?
娌′汉鍘昏鐩?OptiFine 瑁呰浇鏈熸墦鍑烘潵鐨勯偅浜涚被,SRG 鍚嶅氨婕忓嚭鏉ヤ簡 鈥斺€?杩欎竴闂?*涓嶈兘**鐢ㄦ潵鍒?宕╂簝鏄笉鏄崲瑁呭紩璧风殑"銆?

鍙嶈繃鏉ヨ,杩欐潯涔熻В閲婁簡鎴戜滑**榛樿**(杞借嵎寮€鍚?閭ｆ涓轰粈涔堣兘璧板埌 `Minecraft.<init>`:鎴戜滑鐨勬姇閫掓妸閭?427 涓被瑕嗙洊鎺変簡,
SRG 鍚嶉殢涔嬫秷澶便€備笅涓€姝ュ洜姝ゆ敹鏁涙垚涓や欢浜掔浉鐙珛鐨勪簨:(a) 鎶?1.21.x 鐨?*瑁呰浇鏈熸敼鍚?*绉绘鍒?1.20.x(瀹冨悓鏃惰兘璁?skipPayload
杩欎釜寮€鍏冲彉寰楁湁鎰忎箟);(b) 鎴栬€呰閲嶆墦鍖呴樁娈?*涓嶅甫琛ヤ竵鏁版嵁**,浣?OptiFine 鐨?transformer 鏃犳硶鍐嶈嚜琛屾墦琛ヤ竵銆佽浇鑽峰彧鑳界敱鎴戜滑鎶曢€?
鈥斺€?杩欓渶瑕?rig 渚ф敮鎸?涓㈡帀鍏ㄩ儴 patch 鏉＄洰"(鐜版湁 `--unpatched <file>` 鏄寜绫讳涪,1.20.x 鐨?`OptifineJar` 閲屼篃杩樻病鏈夎繖涓€夐」)銆?

### 鍥涖€佸張涓€涓鎺掗櫎鐨勫珜鐤?OptiFine jar 閲岄偅 4948 鏉¤ˉ涓佹暟鎹?*涓嶆槸**杩欐宕╂簝鐨勫師鍥?

鎶婇噸鎵撳寘鍚庣殑 OptiFine jar 閲?`patch/**` 鍏ㄩ儴鍘绘帀(淇濈暀 906 鏉″叾瀹冩潯鐩?鎷锋垚涓€涓?`optifine-1.20.4-nopatches.jar`,
鍐嶈蛋涓€閬?`build-jars.ps1`),閲嶆柊鍚姩:**澶辫触鐐逛竴妯′竴鏍?* 鈥斺€?宕╂簝鎶ュ憡浠嶆鍦?
`CrashReporter 鈫?Shaders.<clinit>` 閭ｆ潯閾句笂,`[OptiFine]` 琛屾暟 8(甯﹁ˉ涓佹暟鎹椂鏄?10)銆傛墍浠ヨ杞芥湡琛ヤ竵鏁版嵁鍙В閲?
`skipPayload` 閭ｆ鐪嬪埌鐨?SRG 婕忓嚭,**涓嶈В閲?*榛樿杩欏嚑娆＄殑鏋勯€犳湡宕╂簝銆?

鍚屼竴鎵规棩蹇楁妸宕╂簝浣嶇疆澶瑰緱鏇寸揣:鏈€鍚庝笁琛屾案杩滄槸

```
[OptifiNeoforge/]: Replaced net.minecraft.client.renderer.texture.AbstractTexture with OptiFine's patched version (...)
[OptifiNeoforge/]: Initialised 1 restored fields in net/minecraft/client/renderer/texture/AbstractTexture
[OptifiNeoforge/]: Restored 3 members in net/minecraft/client/renderer/texture/AbstractTexture from its donor
```

涔熷氨鏄:**鎹㈣涓庢垚鍛樻仮澶嶉兘鎴愬姛杩斿洖浜?*,宕╂簝鍙戠敓鍦?杩欎釜琚崲瑁呯殑绫荤涓€娆¤娓告垙浣跨敤/鍒濆鍖?鐨勬椂鍊欍€?

### 浜斻€佹妸浣嶇疆澶瑰埌"璋佸湪鏋勯€犳湡鍒濆鍖栦簡 OptiFine 鐨勭潃鑹插櫒绫?(骞朵笖鏇存浜嗕笂涓€鐗堢殑涓€涓敊鍒?

鐢?`-Xlog:class+init=info` 鍐嶈窇涓€娆?宕╂簝鍓?*鏈€鍚庡垵濮嬪寲鐨勭被**鏄繖涓€涓?4.714鈥?.716 绉?绱ч殢 `AbstractTexture`
鎹㈣涔嬪悗):

```
net/optifine/shaders/ProgramStage
net/optifine/shaders/Program
net/optifine/shaders/ProgramStack
net/optifine/shaders/config/Property
net/optifine/shaders/config/PropertyDefaultTrueFalse
```

涔熷氨鏄,**鍦?`Minecraft` 鐨勬瀯閫犺繃绋嬮噷,OptiFine 鐨勭潃鑹插櫒鏈哄埗灏卞凡缁忚鍒濆鍖栦簡**,鑰?`Shaders.<clinit>`(瀹冨幓璇?
`Minecraft.getInstance().gameDirectory`)鍦ㄩ偅鏃跺繀鐒舵槸 null 鈥斺€?鎵€浠ラ偅涓?NPE **寰堝彲鑳藉氨鏄師濮嬪紓甯告湰韬?*,涓嶅彧鏄穿婧?
鎶ュ憡璺緞涓婄殑娆＄敓閿欒(鏃堕棿涔熷惢鍚?绫诲垵濮嬪寲鍦?4.71 绉?宕╂簝鎶ュ憡鐩稿叧鐨勫紓甯稿湪 5.04 绉掍箣鍚?銆?

**鏇存涓婁竴鐗堢殑涓€涓敊鍒?*:鎴戝厛鍓嶅啓"鎴愬憳鎭㈠浼氬啓闈欐€佸瓧娈点€佷粠鑰屽己鍒?`X.<clinit>`"鈥斺€?璇讳唬鐮佸悗**涓嶆垚绔?*銆?.20.x 鐨?
`MemberRestoreTransformer` 骞朵笉鍦ㄨ繍琛屾湡鍐欓潤鎬佸瓧娈?瀹冩槸鎶?donor 鐨勯潤鎬佸垵濮嬪寲**鍐呰仈杩涜鎹㈣绫荤殑 `<clinit>`**
(婧愮爜閲岄偅鏉℃敞閲婂啓鐫€"value is inlined into the class's own static initialiser rather than called through a separate
method");鏃ュ織閲岀殑 `Initialised N restored static fields in X` 鍙槸**鎹㈣鏈?*鎵撶殑瀛?涓嶄唬琛ㄨ繍琛屾湡鎻愬墠鍒濆鍖栦簡 X銆?

鎵€浠ヤ笅涓€姝ョ殑闂鍙樺緱寰堝叿浣?**涓婃父 OptiFine 鏄潬浠€涔堜繚璇?`Shaders` 涓嶅湪鏋勯€犳湡琚垵濮嬪寲,鑰屾垜浠繖鏉℃祦绋嬩负浠€涔堟彁鍓嶇鍒颁簡瀹?* 鈥斺€?
鍊欓€夋槸"琚崲瑁呯殑鏌愪釜绫诲湪鏋勯€犳湡灏辫皟鍒颁簡 `net/optifine/Config`"(杞借嵎閲?`AbstractTexture` 鐨勫瓧鑺傜爜閲岀‘瀹炴湁
`net/optifine/Config.getMipmapType()`),鑰?`Config.<clinit>` 浼氫笉浼氶摼鍒扮潃鑹插櫒绫?鏄笅涓€姝ヨ鐢?
`-Xlog:class+init=debug`(甯﹀垵濮嬪寲涓婁笅鏂?鎴栫洿鎺?`javap -c` 鐪?`Config` 鐨?`<clinit>` 鏉ュ畾鐨勩€?

### 鍏€佽Е鍙戦摼宸茬粡閲忓嚭鏉?`Config.<clinit>` 鈫?`Shaders.<clinit>` 鈫?NPE,鑰屼笖杩欐潯閾炬槸 OptiFine 鑷繁鐨?

鍚屼竴浠?`-Xlog:class+init` 鏃ュ織鎸夋椂闂存帓鍑烘潵鏄繖鏍?鏈満瀹炴祴):

```
5.019  com/mojang/blaze3d/platform/GlDebug          <- 鎴戜滑鎹㈣杩囩殑绫?
5.021  net/minecraft/client/ClientBrandRetriever   <- 鎴戜滑鎹㈣杩囩殑绫?
5.022  com/mojang/blaze3d/platform/GlUtil
5.025  net/optifine/Config                          <- OptiFine 鑷繁鐨勭被
5.034  net/optifine/shaders/Shaders                 <- 9 姣鍚?
   ...  闅忓悗鏄?ProgramStage / Program / ProgramStack / Property / ...
```

鍐嶅杞借嵎閲岄偅浠?`net/optifine/shaders/Shaders.class` 鍋?`javap -p -c`:瀹冪殑 `<clinit>` 鏈?**2074 琛?*瀛楄妭鐮?鍏朵腑
鍋忕Щ 2922/2925 灏辨槸

```
invokestatic  net/minecraft/client/Minecraft.getInstance()
getfield      net/minecraft/client/Minecraft.gameDirectory
```

涔熷氨鏄 **`Shaders` 杩欎釜绫诲湪 `Minecraft` 瀹炰緥瀛樺湪涔嬪墠鏍规湰鏃犳硶鍒濆鍖?* 鈥斺€?涓婃父 OptiFine 蹇呯劧鏄湪瀹炰緥瀛樺湪涔嬪悗鎵嶇涓€娆＄鍒?
瀹冦€傛垜浠繖鏉℃祦绋嬮噷,鏋勯€犳湡琚崲瑁呯殑 `GlDebug`/`ClientBrandRetriever` 杩欑被绫诲厛鍒濆鍖?椤哄甫鎶?OptiFine 鐨?`Config` 鎷夎捣鏉?
`Config.<clinit>` 鍐嶉摼鍒?`Shaders.<clinit>`,浜庢槸蹇呯劧 NPE銆?*杩欐潯閾炬湰韬槸 OptiFine 鑷繁鐨勭被涔嬮棿鐨勯摼**(涓嶆槸鎴戜滑鎷煎嚭鏉ョ殑),
鎵€浠ユ垜浠兘鍋氱殑鏄?*鏀瑰彉"浠€涔堟椂鍊欑鍒板畠"**,鑰屼笉鏄敼 `Shaders`銆?

椤哄甫鎶?鎹㈠彟涓€涓?1.20.4 鏋勫缓"杩欐潯涔熼噺浜?涓や釜鏋勫缓鍦ㄨ繖涓ゅ**瀹屽叏涓€鏍?* 鈥斺€?閮芥病鏈?`Entity`/`blv` 鐨勮ˉ涓侀」,
`AbstractClientPlayer` 鐨?srg 琛ヤ竵閮芥槸 7997 瀛楄妭銆傛墍浠ユ崲 `I8_pre4` 娌℃湁鐞嗙敱鏀瑰彉杩欐潯閾?杩欏彧璇存槑"涓嶅お鍙兘",涓嶆槸"涓嶄細")銆?

### 涓冦€佷笅涓€姝?鍙墽琛岀殑椤哄簭)

1. 鎵惧嚭**鍏蜂綋鏄摢涓€涓鎹㈣鐨勭被**鍦ㄦ瀯閫犳湡纰板埌 `Config`:鎶?`-Xlog:class+init=trace` 涓?`-Xlog:class+load` 瀵归綈,鎴栧
   鍊欓€夌被(`GlDebug`銆乣ClientBrandRetriever`銆乣GlUtil`)閫愪釜鐢?`javap -c` 鐪嬪畠浠殑 `<clinit>` 閲屾湁娌℃湁
   `net/optifine/Config` 璋冪敤銆?
2. 鑻ラ攣瀹氬埌鏌愪竴涓被,鍐嶇敤鏈垎鏀凡鏈夌殑鑳藉姏鎶婂畠鎺掗櫎鍦ㄦ崲瑁呬箣澶?鏁寸被淇濈暀杩愯鏃剁増鏈?鈥斺€?娉ㄦ剰褰撳墠 `keep-runtime.txt` 鐨勮В鏋?
   鍙涓夊垪 `owner|name|desc`,鏁寸被閭ｆ潯褰㈠紡鍦ㄨ繖鏉＄嚎涓婅繕**娌℃湁**瀹炵幇,闇€瑕佸厛琛ヤ笂),鐪嬪穿婧冩槸鍚﹂殢涔嬫秷澶便€?
3. 鍙︿竴鏉′簰涓嶆帓鏂ョ殑璺?璁?OptiFine 鐨?`Config`/`Shaders` 鍦?`Minecraft` 瀹炰緥灏辩华涔嬪悗鍐嶅垵濮嬪寲,鍗虫妸"璋佸厛纰板畠"鐨勯『搴?
   鏀瑰洖鏉?鈥斺€?杩欓渶瑕佸湪 loader 渚ф壘涓€涓洿鏅氱殑鎸傜偣,鑰屼笉鏄敼 OptiFine銆?

### 鍏€佽繖涓€杞張鎺掗櫎浜嗕袱鏉?骞剁粰 loader 鍔犱簡涓€涓?鍒濆鍖?璋冪敤鐐硅拷韪?寮€鍏?

1. **`AbstractTexture.setFilter` 涓嶆槸瑙﹀彂鐐?瀹炴祴)銆?* 鐢ㄦ垚鍛樼骇 keep 璁″垝
   (`net/minecraft/client/renderer/texture/AbstractTexture<TAB>setFilter<TAB>(ZZ)V`,鏃ュ織纭
   `Kept the game's body of ...` 鐢熸晥)鎶婅浇鑽烽偅涓€娈?`Config.getMipmapType()` 璋冪敤鎹㈠洖娓告垙渚у疄鐜板悗,**宕╂簝鐐规病鍙?*
   (浠嶆槸 `CrashReporter 鈫?Shaders.<clinit>`,10 琛?`[OptiFine]`)銆?
2. **loader 杩戒笉鍒?OptiFine 鑷繁鐨勭被(瀹炴祴,寰堥噸瑕?銆?* 鏂板姞鐨?
   `-Doptifineoforge.traceInit=<鍐呴儴鍚?[,<鍚?...]` 浼氬湪琚拷韪被鐨?`<clinit>` 鍜?鍚?`net/optifine/Config` 璋冪敤鐨勬柟娉?
   寮€澶存彃鍏?`new Throwable().printStackTrace()`銆傚 `net/optifine/Config` 鐢ㄨ繖涓紑鍏?**鏃㈡病鏈?`Replaced
   net.optifine.Config` 閭ｈ鎹㈣鏃ュ織,涔熸病鏈変换浣曡拷韪緭鍑?* 鈥斺€?璇存槑 `net/optifine/**` 鏍规湰涓嶇粡杩囨垜浠殑 transformer,
   瀹冧滑鏄綔涓?*妯″潡**(鏍堝抚閲岀殑 `srg` 灏辨槸妯″潡鍚?鐩存帴鍔犺浇鐨勩€傚洜姝?鍦?OptiFine 鑷繁鐨勭被閲屾彃妗?杩欎欢浜?loader 渚у仛涓嶅埌,
   鍙兘绂荤嚎鏀瑰啓杞借嵎 jar銆?
3. 鐢?`-Doptifineoforge.traceInit=*`(杩借釜**姣忎竴涓?*浼氳皟鐢?`Config` 鐨勪氦浠樼被)璺戜竴娆?52 涓被琚彃妗╂垚鍔?浣?*宕╂簝鍓?
   娌℃湁浠讳綍涓€涓彃妗╃偣琚墽琛?* 鈬?**鏋勯€犳湡绗竴娆＄ `Config` 鐨勪笉鏄垜浠氦浠樼殑浠讳綍娓告垙绫?*銆傝繖涓€鏉℃妸鑼冨洿鏀跺緱寰堢獎:
   瑙﹀彂鏉ヨ嚜 OptiFine 鑷繁鐨勭被鍦ㄥ惎鍔ㄩ樁娈电殑鏌愭鍒濆鍖?璋冪敤(鍊欓€夊 3.758 绉掑氨鍒濆鍖栦簡鐨?
   `net/optifine/reflect/Reflector` 閭ｄ竴濂楀弽灏勮В鏋?鈥斺€?`Class.forName(String)` 鏄細鍒濆鍖栫洰鏍囩被鐨?銆?

**缁撹**:瑕佸啀寰€鍓嶆帹,鍙兘**绂荤嚎**鍦ㄨ浇鑽?jar 閲岀粰 `srg/net/optifine/Config.class` 鎻掓々(闇€瑕佷竴涓皬 ASM 宸ュ叿,
鎶婃爤鎵撳埌 `System.err`/娓告垙鏃ュ織),鎴栬€呮崲涓€鏉＄嚎銆傝繖涓ゆ潯閮借鍦ㄤ笅闈㈢殑"涓嬩竴姝?閲屻€?

### 鍗併€佸師濮嬪穿婧冪粓浜庣湅鍒颁簡 鈥斺€?鑰屼笖鏄垜浠嚜宸辨祦姘寸嚎閫犳垚鐨?1.20.4 鐨勫叧閿彂鐜?

鍓嶉潰閭ｄ簺 `Config`/`Shaders` 鐨?NPE **鍏ㄩ兘鍙戠敓鍦ㄥ穿婧冩姤鍛婇噷**(杩借釜鏍堟樉绀哄畠浠殑璋冪敤鑰呮槸
`Minecraft.fillSystemReport 鈫?SystemReport.setDetail 鈫?GlDebug.<clinit> 鈫?GlDebug.makeIgnoredErrors`,浠ュ強
`CrashReporter.extendCrashReport`),鎵€浠ュ畠浠彧鏄?*娆＄敓閿欒** 鈥斺€?杩欎篃瑙ｉ噴浜嗕负浠€涔堝穿婧冩姤鍛婁竴鐩村啓涓嶅嚭鏉ャ€備负浜嗙粫寮€杩欐潯
姝诲惊鐜?鏂板姞浜嗕袱涓皟璇曞紑鍏?

- `-Doptifineoforge.traceInit=<鍚?[,<鍚?|*]`:鍦ㄨ杩借釜绫荤殑 `<clinit>` 涓庡叾"璋冪敤/璁块棶 `net/optifine/Config`"鐨勬柟娉曞紑澶?
  鎵撳嵃鏍囪琛屼笌鏍?**娉ㄦ剰杈撳嚭钀藉湪杩涚▼ stderr**,涓嶆槸 `latest.log` 鈥斺€?鎴戝厛鍓嶆寜 `latest.log` 鎵?璇垽鎴?娌℃湁鎻掓々鐐硅繍琛?);
- `-Doptifineoforge.traceCrash=true`:鍦?`CrashReport.forThrowable` 寮€澶存墦鍗伴偅涓?throwable,缁曞紑 OptiFine 鐨勫穿婧冨洖璋冦€?

`traceCrash` 涓€鎶婂氨鎶?*鍘熷寮傚父**鎷夸簡鍑烘潵:

```
java.lang.RuntimeException: java.lang.IncompatibleClassChangeError:
  Expected static method 'com.mojang.serialization.Codec
  net.minecraft.world.level.block.state.BlockState.codec(com.mojang.serialization.Codec, java.util.function.Function)'
  at net.minecraft.world.level.block.state.BlockState.<clinit>(BlockState.java:19)
  ... Blocks.<clinit> 鈫?FireBlock.bootStrap 鈫?Bootstrap.bootStrap 鈫?Main.main:157
```

**鏍瑰洜涔熼噺鍑烘潵浜?鑰屼笖鍦ㄦ垜浠繖涓€渚?*(閫愰樁娈?`javap` 瀵圭収):

| 闃舵 | `BlockState` 閲岀殑鐩稿叧鎴愬憳 |
|---|---|
| OptiFine 琛ヤ竵杈撳嚭(`optifine-patched.jar`) | 鍙湁瀛楁 `f_61039_`,**娌℃湁** `m_61127_` |
| 鎴戜滑鐨?`MissingTargets --stub` 涔嬪悗(`optifine-patched-stubbed.jar`) | 澶氬嚭涓€涓?**`public`(闈?static)** `Codec m_61127_(Codec, Function)` |
| 鏀瑰悕鍚庝氦缁?loader 鐨勯偅涓€浠?| `CODEC` 瀛楁 + **闈?static** 鐨?`codec(Codec, Function)` |

涔熷氨鏄:**`MissingTargets --stub` 缁欎竴涓?杞借嵎鑷繁鐨勭被"琛ヤ簡涓€涓垚鍛?鑰屼笖琛ユ垚浜嗗疄渚嬫柟娉?*,鑰岃绫昏嚜宸辩殑 `<clinit>`
鏄敤 `invokestatic` 璋冨畠鐨?鈬?`IncompatibleClassChangeError: Expected static method` 鉁斻€傝繖姝ｆ槸浠庣涓€杞捣灏辨尅浣?1.20.4
鐨勯偅娆″穿婧冦€?

**涓嬩竴姝?鏄庣‘)**:璁?stub 闃舵涓嶈缁欒浇鑽疯嚜宸辩殑绫昏ˉ鎴愬憳(鎴栬嚦灏戜繚鐣?`static` 鏍囧織),鍐嶉噸璺戣閰嶄笌鍚姩銆傝繖鏉′慨濂戒箣鍚?
1.20.4 鎵嶆湁缁х画寰€鍓嶇殑鍙兘銆?

椤哄甫璁颁笅鏈疆鍙︿竴涓柊鑳藉姏:**鏁寸被淇濈暀杩愯鏃剁増鏈?*(`keep-runtime.txt` 鐨?`owner<TAB>*` 褰㈠紡)宸插疄鐜板苟鍦?1.20.4 涓婂疄娴嬬敓鏁?
(鏃ュ織 `Kept the runtime's whole com.mojang.blaze3d.platform.GlDebug ...`),浣嗗畠**娌℃湁**鏀瑰彉宕╂簝 鈥斺€?鍥犱负鐪熸鐨勯樆濉炲湪鍒(瑙佷笂)銆?


### 鍗佷簩銆?.20.4 鐨勯噸澶ц繘灞?瑁呴厤椤哄簭閿欎簡(宸蹭慨),骞舵妸"璁块棶璁″垝"绉绘杩?loader

鏈疆鎶?1.20.4 浠?鍚姩鍗冲穿銆佸彧鏈?0鈥?0 琛?`[OptiFine]`"鎺ㄥ埌 **`Setting user` 鉁撱€?4 琛?`[OptiFine]`銆乻tderr 0 瀛楄妭**,
骞朵笖**绗竴娆℃嬁鍒颁簡鍙鐨勫穿婧冩姤鍛?*銆備袱澶勬牴鍥犻兘鍦ㄦ垜浠嚜宸辫繖杈?閮藉凡淇苟瀹炴祴:

1. **瑁呴厤椤哄簭**:`SrgRemap`(SRG鈫掑畼鏂瑰悕)蹇呴』鍦?*浠讳綍璁″垝鐢熸垚涔嬪墠**璺?鑰屼笖瑕佸悓鏃朵綔鐢ㄤ簬**杞借嵎 jar 涓庨噸鎵撳寘鐢ㄧ殑 OptiFine jar**銆?
   鍏堝墠椤哄簭鏄?鍏?`MissingTargets --stub`銆佸悗鏀瑰悕",浜庢槸 SRG 杞借嵎瀵圭潃瀹樻柟鍚嶈繍琛屾椂鎶ュ嚭 **3178/43162 鏉″紩鐢ㄧ己澶?*,
   `--stub` 寰€杞借嵎鑷繁鐨勭被涓婅ˉ浜?**228 涓?*鍋囨垚鍛?鍏朵腑 `BlockState.codec` 鐢ㄥ疄渚嬫柟娉曠洊浣忎簡缁ф壙鏉ョ殑 static 鏂规硶,
   灏辨槸涓婁竴杞偅鏉?`IncompatibleClassChangeError`)銆傛敼鎴?鍏堟敼鍚?涔嬪悗:缂哄け寮曠敤 **63/43162**銆乻tub **46 涓?* 鉁斻€?
   鍙﹀閲嶆墦鍖呯敤鐨?OptiFine jar 涔熷繀椤诲厛鏀瑰悕(瀹炴祴:鏈敼鍚嶆椂 `srg/net/optifine/CrashReporter.class` 閲岃繕鐣欑潃 SRG 鍚?
   鍚姩鐩存帴 `NoSuchMethodError: CrashReport.m_127524_()`)銆?
   杩欎袱姝ュ凡缁忓啓杩?rig 鐨?`prepare-line.ps1`(鏂板 `-SrgMappings` / `-ObfOfficial`)涓?`add-line.ps1`(閫忎紶)銆?
2. **璁块棶璁″垝鍦?1.20.x 鐨?loader 閲屾牴鏈病琚**:`build-jars` 涓€鐩存妸 `runtime-access.txt` 宓岃繘 jar,浣嗚繖涓垎鏀?
   `PatchedClassTransformer` 鐨勮祫婧愬父閲忛噷娌℃湁瀹?鈥斺€?浜庢槸 NeoForge 鑷繁鏀惧杩囩殑鎴愬憳鍦ㄤ氦浠樺嚭鍘荤殑绫讳笂浠嶇劧鏄獎鐨?
   鍚姩姝诲湪 `IllegalAccessError: NeoForgeRenderTypes$Internal tried to access method 'RenderType$...'`銆?
   鏈疆鎶婅繖涓€妗ｈˉ涓婁簡(`RUNTIME_ACCESS` 璧勬簮甯搁噺 + `applyAccessPlan`,鐢ㄤ笌鎹㈣鍚屼竴濂?鍙栨洿瀹藉彲瑙佹€?鐨勮鍒?,
   瀹炴祴鍚姩浠?60 琛屾帹杩涘埌 **74 琛?*銆佽 `IllegalAccessError` 娑堝け銆?

**鐜板湪鍋滃湪涓嬩竴澶?宸插畾浣?**:`NoSuchMethodError` 鎸囧悜 `SpriteResourceLoader.create(...)`銆傞€愪釜 `javap` 瀵圭収鍚庣湅鍒?
涓や釜鐗堟湰鐨?`create(Collection)` 閮藉湪,浣?**绉佹湁 lambda 鐨勫舰鐘朵笉鍚?* 鈥斺€?杩愯鏃堕偅浠芥槸
`lambda$create$0(Collection, ResourceLocation, Resource, SpriteContentsConstructor)`(NeoForge 澶氫竴涓弬鏁?,
杞借嵎閭ｄ唤鏄?vanilla 鐨勪笁鍙傛暟鐗堟湰銆備篃灏辨槸璇?璋冪敤鏂硅鐨勭鍚嶅彧鏈夎浇鑽锋湁"杩欎竴绫婚棶棰樺湪 1.20.x 涓婁篃瀛樺湪 鈥斺€?
1.21 閭ｆ潯绾挎槸鐢?*鎶婅浇鑽风殑鎴愬憳琛ュ埌浜や粯鍑哄幓鐨勭被涓?*(`addPayloadMembers` 閭ｄ竴妗?瑙ｅ喅鐨?鑰岃繖涓€鍒嗘敮**杩樻病鏈?*杩欎竴妗ｃ€?

**涓嬩竴姝?*:鎶?鎶婅浇鑽锋垚鍛樿ˉ杩涗氦浠樼被"杩欎竴妗ｇЩ妞嶅埌 1.20.x(鎴栧厛鏌ユ竻 `SpriteResourceLoader` 鍒板簳鏈夋病鏈夎鎹㈣銆佷负浠€涔堟崲瑁呭悗涓や釜绛惧悕涓嶅叡瀛?,
鍐嶈窇 1.20.4銆傝繖宸茬粡鏄?*鏈€鍚庡嚑澶勪箣涓€**:鍒ゆ嵁閲?`Setting user` 宸茬粡涓虹湡,缂虹殑鏄?`Sound engine started` 涓?0 宕╂簝鎶ュ憡"銆?

### 鍗佸洓銆乣SpriteResourceLoader.create` 杩欎竴澶勯噺鍒扮殑缁嗚妭(浠嶆湭瑙ｅ喅)

鎶婄幇鍦洪€愬眰鎵掑紑鍚?浜嬪疄鏄繖鏍风殑(鍏ㄩ儴鏈満瀹炴祴):

- 鎶ラ敊鍘熸枃:`NoSuchMethodError: '...atlas.SpriteResourceLoader net.minecraft.client.renderer.texture.atlas.SpriteResourceLoader.create(java.util.Collection)'`;
- **杩愯鏃堕偅浠?`SpriteResourceLoader` 鏄帴鍙?*,骞朵笖**纭疄澹版槑**浜?`public static create(Collection)`;
- 杞借嵎閭ｄ唤鏄?*绫?*(OptiFine 鐨勮ˉ涓佹棭浜庡畠鍙樻垚鎺ュ彛),鎵€浠ユ垜浠殑 loader **鏁呮剰涓嶆崲瑁?*瀹?鐞嗙敱鍐欏湪鏃ュ織閲?
  `Left ... SpriteResourceLoader alone: the runtime adds members to that interface, and installing OptiFine's copy would replace the static initialiser that fills them` 鈥斺€?杩欎釜鍒ゆ柇鏈韩鏄鐨?鎶婃帴鍙ｆ崲鎴愮被浼氱洿鎺ュ潖鎺?;
- 鍏跺悗 loader 鍙堝瀹冨仛浜嗕竴娆℃垚鍛樻仮澶?`Restored 1 members ... from its donor`),鑰屽畠鍦?jar 閲岄偅浠?donor 鏄?*瑁佸壀杩囩殑鍓湰**(閲岄潰鏍规湰娌℃湁 `create`);
- 浜や粯鍑哄幓鐨勯偅浠?鍦?loader jar 閲?`optifineoforge/patched/...`)**甯︾潃** `public static create(Collection)`,
  璋冪敤鏂?琚崲瑁呯殑 `SpriteLoader`)鐢ㄧ殑涔熸槸 `InterfaceMethodref ... create:(Ljava/util/Collection;)...`銆?

涔熷氨鏄"涓や釜绛惧悕閮藉湪"鍗翠粛鐒?`NoSuchMethodError` 鈥斺€?鎵€浠?*闂涓嶅湪绛惧悕涓嶅尮閰?*,鑰屽湪"杩愯鏈熷疄闄呰В鏋愬埌鐨勯偅浠界被閲屾病鏈夎繖涓柟娉?銆?
涓嬩竴姝ヨ鍋氱殑璇婃柇寰堝叿浣?**鎶婃崲瑁?鎭㈠涔嬪悗鐨勬渶缁堢被 dump 鍑烘潵鐪?*(杩欎竴鍒嗘敮杩樻病鏈?dump 寮€鍏?1.21.x 鏈?,纭鎺ュ彛涓婄殑 `create` 鏄惁琚垚鍛樻仮澶嶆垨璁块棶璁″垝鏀瑰啓鎺?
绗簩涓€欓€夋槸瑙ｆ瀽鍙戠敓鍦ㄥ彟涓€涓ā鍧?鍙︿竴浠藉壇鏈笂銆傝繖涓€姝ユ病鍋氬畬,鎵€浠?1.20.4 浠嶅仠鍦?1 浠藉穿婧冩姤鍛婁笂銆?

### 鍗佸叚銆佹牴鍥犻攣瀹?浜や粯鍑哄幓鐨勯偅浠芥帴鍙ｅ甫鐨勬槸 **SRG 鍚?*(dump 寮€鍏抽噺鍒扮殑)

鏈疆缁?1.20.x 鐨?loader 鍔犱簡 `-Doptifineoforge.dump=<dir>`(鎶?*鎵€鏈夌粡杩囧叏閮ㄥ鐞嗕箣鍚?*鐪熸浜や粯鐨勭被鍐欏嚭鏉?,
瀹冧竴鎶婂氨鎶婁笂涓€杞偅涓?涓や釜绛惧悕閮藉湪鍗?`NoSuchMethodError`"瑙ｅ紑浜嗐€傚 `SpriteResourceLoader` 鐨?dump 鏄?

```
public interface net.minecraft.client.renderer.texture.atlas.SpriteResourceLoader {
  public static final org.slf4j.Logger f_260482_;              <- SRG 鍚?
  public static ... m_292996_(java.util.Collection<...>)       <- SRG 鍚?杩欏氨鏄?create)
  public abstract ... m_294584_(ResourceLocation, Resource)    <- SRG 鍚?杩欏氨鏄?loadSprite)
  private static ... lambda$create$0(Collection, ResourceLocation, Resource)
  static {};
}
```

鑰?*纾佺洏涓婃瘡涓€浠?*閮藉啓鐨勬槸瀹樻柟鍚?閫愪釜 `javap` 瀵圭収):

| 鏉ユ簮 | 鎴愬憳鍚?|
|---|---|
| `client-1.20.4-鈥?srg.jar` | `LOGGER` / `create` / `loadSprite`(瀹樻柟鍚? |
| `neoforge-20.4.251-client.jar` | `LOGGER` / `create` / `loadSprite` + NeoForge 鍔犵殑 `loadSprite(鈥? SpriteContentsConstructor)` |
| 鎴戜滑鑷繁缁勭殑 `runtime-1.20.4.jar` | 鍚屼笂 |

涔熷氨鏄:**杩愯鏈熷疄闄呭畾涔夌殑閭ｄ唤鎺ュ彛琚汉鎹㈡垚浜?SRG 鍚嶇増鏈?*,鑰岃皟鐢ㄦ柟(鎴戜滑浜や粯鐨勩€佸凡鏀瑰悕鎴愬畼鏂瑰悕鐨?`SpriteLoader`)
鎸夊畼鏂瑰悕鍘昏В鏋?鈬?`NoSuchMethodError` 鉁斻€傛垜浠殑 loader 鏃ュ織鏄剧ず瀹?*鏁呮剰娌℃崲瑁?*杩欎釜绫?
(鎺ュ彛 + 杩愯鏃剁粰瀹冨姞浜嗘垚鍛?,鎵€浠ヨ繖涓?SRG 鍚嶇増鏈笉鏄垜浠姇閫掔殑 鈥斺€?瀹冩潵鑷?*瑁呰浇鏈?*:OptiFine 鑷繁鐨?transformer 浼氭寜
`patch/srg/**` 鍦ㄥ姞杞芥椂閲嶆柊鎵撹ˉ涓?鑰岄偅浠借ˉ涓侀噷鐨勫悕瀛楀氨鏄?SRG(1.21 绾胯褰曡繃鐨勫悓涓€涓幇璞?銆?.21 鐢?
**瑁呰浇鏈熸敼鍚?*(`SrgNameTable` + `-Doptifineoforge.renameSrg`,榛樿寮€)鍘嬩綇瀹?鑰?*杩欎竴鍒嗘敮娌℃湁杩欎竴妗?*銆?

椤哄甫鎶婁竴涓€欓€変慨娉曞惁鎺変簡:**鎶婇噸鎵撳寘 jar 閲岀殑 `patch/**` 鍏ㄩ儴鍒犳帀**(淇濈暀 1620 鏉″叾瀹冩潯鐩?涔嬪悗鍚姩,`[OptiFine]` 琛屾暟浠?
74 **鎺夊洖 60** 鈥斺€?璇存槑 OptiFine 瑁呰浇鏈熻ˉ涓佹湰韬槸**鏈夌敤鐨?*(鍘绘帀瀹冨弽鑰屾洿宸?,鎵€浠ヨ淇殑鏄?*鍚嶅瓧**,涓嶆槸"绂佹瀹冩墦琛ヤ竵"銆?

**涓嬩竴姝?*:鎶?1.21.x 鐨?*瑁呰浇鏈熸敼鍚?*杩欎竴妗ｇЩ妞嶅埌 1.20.x(鎴栬€呴€€涓€姝?瀵规垜浠槑纭煡閬撲細浠?SRG 鍚嶅嚭鐜扮殑绫?鍦ㄨ杞芥湡鎶婂悕瀛楁敼鍥炴潵)銆?
杩欐槸 1.20.4 鐜板湪鍞竴鐨勯樆濉炪€?

### 鍗佸叓銆佹斁寮€"鍚岀被鎺ュ彛鎹㈣"涔嬪悗:1.20.4 **杩涘埌娓叉煋寰幆浜?*(730 琛?`[OptiFine]`)

涓婁竴杞噺鍒?浜や粯鍑哄幓鐨勬帴鍙ｅ甫 SRG 鍚嶃€佽€岃浇鑽烽偅浠藉叾瀹炴槸瀹樻柟鍚嶇殑鍚屽舰鎺ュ彛"涔嬪悗,鏈疆鎶?loader 閲岄偅鏉℃嫆缁濊鍒?*鏀剁獎鍒扮湡姝ｄ細鍧忕殑鎯呭舰**
(`object` 渚ф槸鎺ュ彛銆佽浇鑽蜂晶鏄?*绫?*,鎴栬€呮樉寮?`-Doptifineoforge.strictInterfaceKeep=true`),鍚岀被鎹㈣鏀逛负鍏佽
(杩愯鏃剁粰璇ユ帴鍙ｅ姞鐨勬垚鍛樹粛鐢辨垚鍛樻仮澶嶈ˉ鍥?鏃ュ織閲岄偅鏉?`Restored 1 members` 灏辨槸瀹?銆傚疄娴嬬粨鏋?

| 鎸囨爣 | 涔嬪墠 | 鏈疆 |
|---|---|---|
| `[OptiFine]` 琛屾暟 | 74 | **730** |
| 杞藉叆闃舵 | `Minecraft.<init>` | **娓叉煋寰幆**(`Minecraft.run 鈫?runTick 鈫?GameRenderer.render 鈫?frameInit`) |
| 宕╂簝鎶ュ憡 | 1 | 1 |

涔熷氨鏄 1.20.4 鐜板湪**宸茬粡杩涙父鎴忎富寰幆骞跺湪鐢荤涓€甯?*,`SpriteResourceLoader.create` 閭ｆ潯 `NoSuchMethodError` 闅忎箣娑堝け 鉁斻€?

**鏂扮殑(涔熸槸鏇撮潬鍚庣殑)闃诲**浠嶇劧鏄悓涓€绫诲悕瀛楅棶棰?浣嗗嚭鐜板湪鍙︿竴涓被涓?

```
java.lang.NoSuchMethodError: 'void com.mojang.blaze3d.systems.RenderSystem$AutoStorageIndexBuffer$IndexGenerator.m_157487_(it.unimi.dsi.fastutil.ints.IntConsumer...)'
  at RenderSystem$AutoStorageIndexBuffer.m_157476_(RenderSystem.java:1373)
  at VertexBuffer.m_231223_(VertexBuffer.java:164) -> LevelRenderer.createStars(LevelRenderer.java:791)
```

椤哄甫閲忓埌涓€涓叧閿暟瀛?`SrgMemberMap --emit`,鏈満):**浜や粯鐨勮浇鑽烽噷杩樻湁 114 涓垚鍛樻槸 SRG 鍚?*
(鍏朵腑 90 涓?琛ㄩ噷娌℃湁瀵瑰簲椤?銆?3 涓?绫诲湪琛ㄩ噷浣嗘垚鍛樹笉鍦?),鏍锋湰濡?
`net/minecraft/client/gui/Gui$DisplayEntry.f_302553_`銆乣DebugScreenOverlay.m_280186_`銆?
杩欒鏄?*绂荤嚎鏀瑰悕骞舵病鏈夋妸杞借嵎鏀瑰共鍑€**,鍓╀笅鐨勮繖浜涘悕瀛楀氨鏄帴涓嬫潵姣忚蛋鍒颁竴涓柊绫婚兘浼氭挒涓婄殑涓滆タ銆?

**涓嬩竴姝?*:鎶婇偅 114 涓垚鍛橀€愪釜瀵圭潃 joined.tsrg / obf-official 涓ゅ紶琛ㄦ煡娓呮"涓轰粈涔堟病琚敼"(鎻忚堪绗?宓屽绫?鍚堟垚鎴愬憳涓夌鍙兘),
鎶婄绾挎敼鍚嶈ˉ鍒拌兘瑕嗙洊瀹冧滑;鎴栬€呮妸瑁呰浇鏈熸敼鍚?1.21 閭ｄ竴妗?鎸?澹版槑+寮曠敤閮芥敼"鐨勫舰寮忕Щ妞嶈繃鏉ャ€備慨瀹岃繖涓€妗?1.20.4 灏卞彧鍓?
`sound engine` 涓?0 宕╂簝鎶ュ憡"涓ゆ潯鍒ゆ嵁浜嗐€?

### 浜屽崄銆佹妸 rig 鐨勪袱寮犺〃淇ソ:杞借嵎閲屽墿涓嬬殑 SRG 鍚嶄粠 114 闄嶅埌 28

涓婁竴杞噺鍒?杞借嵎閲岃繕鏈?114 涓?SRG 鍚嶆垚鍛?,鏈疆鏌ュ埌鏍瑰洜**鍦?rig 鐨勮〃鐢熸垚涓?*,涓嶅湪浠撳簱鐨勪唬鐮侀噷:

- `proguard-to-tsrg.ps1` 鎶?Mojang 鏄犲皠閲岀殑**宓屽绫?*褰撴垚浜嗗灞傜被鐨?*瀛楁**鏉ュ啓(`Inner -> a:` 杩欑琛岃褰撴垚鎴愬憳琛?,
  浜庢槸姣忎釜宓屽绫荤殑鎴愬憳鍦?obf鈫抩fficial 琛ㄩ噷閮芥病鏈夋潯鐩?鈥斺€?鑰?114 涓畫鐣欓噷**鏍锋湰鍏ㄦ槸宓屽绫?*
  (`GlStateManager$BlendState.f_84577_`銆乣GlStateManager$BooleanState.m_84589_`銆乣RenderSystem$AutoStorageIndexBuffer$IndexGenerator.m_157487_`);
- 骞跺垪鐨勭浜屼釜灏忛敊:obf 閭ｄ竴鍒楀鏈贩娣嗙殑 `com.mojang.*` 绫诲悕**娌℃湁鎶婄偣鎹㈡垚鏂滄潬**,浜庢槸琛ㄩ噷鐨?
  `com.mojang.blaze3d.platform.GlStateManager$a` 涓?joined.tsrg 閲屽悓鍚嶅悓褰㈢殑閿涓嶄笂銆?

涓ゅ閮戒慨濂戒箣鍚?瀹炴祴,鍚屼竴濂楄緭鍏?:

| 鎸囨爣 | 淇箣鍓?| 淇箣鍚?|
|---|---|---|
| `MissingTargets` 鎶ョ殑缂哄け寮曠敤 | 63 / 43162 | **23 / 43162** |
| `--stub` 琛ョ殑鍋囨垚鍛?| 46 | **18** |
| `SrgMemberMap` 鏁板嚭鐨?杞借嵎閲屼粛鏄?SRG 鍚嶇殑鎴愬憳" | **114** | **28** |

涔熷氨鏄 1.20.4 鐨勮浇鑽风幇鍦ㄥ拰鑳借窇閫氱殑 1.20.6 澶勫湪鍚屼竴涓噺绾?23 瀵?23)鉁斻€?*浣嗗惎鍔ㄧ粨鏋滄病鏈夊彉**:浠嶇劧鏄?730 琛?
`[OptiFine]`銆佸穿鍦ㄥ悓涓€澶?`GameRenderer.frameInit:1759`),鑰屽穿婧冩姤鍛婇噷鐨勫悕瀛楁樉绀?*浜や粯鍑哄幓鐨?
`RenderSystem$AutoStorageIndexBuffer` 鑷繁杩樺甫鐫€ SRG 鏂规硶鍚?*(`m_157476_`銆乣m_221946_`)鈥斺€?
杩欎竴绫诲悕瀛椾笉鏄垜浠姇閫掔殑閭ｄ唤(杞借嵎閲岄偅 28 涓箣澶栫殑閮ㄥ垎宸茬粡鏀瑰共鍑€,`IndexGenerator.accept` 灏辨槸鏈疆鏀规垚鍔熺殑鏍锋湰),
鑰屾槸**瑁呰浇鏈?*鍑虹幇鐨?OptiFine 鑷繁鐨?transformer 浼氭寜 `patch/srg/**` 閲嶆柊鎵撹ˉ涓?瑙佺鍗佸叚鑺?銆?

**涓嬩竴姝?*:鎶婇偅 28 涓垚鍛橀€愪釜鏌ユ竻(鍝簺鏄〃浠嶇己鐨勩€佸摢浜涙槸瑁呰浇鏈熸墠鍑虹幇鐨?,骞舵妸"瑁呰浇鏈熸敼鍚?杩欎竴妗ｈˉ涓?
(1.21.x 鏈夈€佽繖涓€鍒嗘敮娌℃湁),杩欎篃鏄?1.20.4 鐜板湪鍞竴鐨勯樆濉炪€?

### 浜屽崄浜屻€?.20.4 鐜板湪婊¤冻**鏂囨。閲岀殑鍥涙潯鍒ゆ嵁** 鈥斺€?浣嗘埅鍥炬樉绀哄畠鍋滃湪鍔犺浇閬僵涓?

鏈疆瀹炴満璺戜簡涓夋,鍒ゆ嵁閫愰」(rig 鐨?`launch.ps1` 杈撳嚭):

| 鍒ゆ嵁 | 璁板綍 | 鏈満瀹炴祴(涓夋) |
|---|---|---|
| `VERDICT` | `STARTED` | `STARTED` 鉁?|
| `Setting user` | 鉁?| 鉁?`Setting user: Dev`) 鉁?|
| 鏈杩愯鐨勫穿婧冩姤鍛?| 0 | **0** 鉁?|
| stderr | 14 481 瀛楄妭 | **14 481** / 14 643 / **14 481** |
| `[OptiFine]` 琛屾暟 | 241 | **730**(rig 鎵撳嵃鐨勬槸 stdout+stderr+latest.log 鍚堝苟鍊?= 2脳) |

- stderr 鐨勫唴瀹规鏄枃妗ｅ啓杩囩殑**宸茬煡 Reflector 缂洪櫡**:鍥涙潯 `NoClassDefFoundError`
  (`BlockEntityWithoutLevelRenderer` 脳3 + `BlockState` 脳1,鍚勫甫涓€鏉?`Caused by: ClassNotFoundException`),
  寮傚父琚?OptiFine 鍚炴帀銆佷笉闃绘柇鍚姩 鈥斺€?涓?`docs/VERSIONS.md` 閲?1.20.2 / 1.20.4 鍏辨湁"鐨勯偅鏉′竴鑷?鉁斻€?
  涓夋閲屼袱娆′笌璁板綍鐨?14 481 **閫愬瓧鑺傜浉鍚?*,绗笁娆?14 643(鐩稿樊 162 瀛楄妭)銆?
- `[OptiFine]` 琛屾暟涓庤褰曞樊寰楄繙(730 鍚堝苟 = 365 鍘熷,璁板綍 241)銆?*杩欐潯宸粠鍝潵娌℃湁鏌ユ槑**,鎸変箣鍓嶅悇绾跨殑瑙勫緥
  (1.21/1.21.1 +9銆?.21.6/1.21.7/1.21.8 +7)鐪嬫洿鍍忕幆澧?璁剧疆宸紓,浣嗚繖娆″樊璺濆ぇ寰楀,鎵€浠ュ彧鑳借"鏈煡鏄?銆?

**浣嗗繀椤诲啓娓呮鐨勪竴浠朵簨**:鍒ゆ嵁鍏ㄧ豢**涓嶇瓑浜?*"杩涘埌鏍囬鐣岄潰"銆傛湰杞姄浜嗕袱娆＄獥鍙ｆ埅鍥?60 绉掍笌 130 绉掑悇涓€娆?,
涓ゆ閮芥槸鍚屼竴褰㈡€?**鍧囧€?`233,73,8x`銆佹渶澶ч鑹叉《鍗?84%銆佽繎榛勮壊(splash)鍍忕礌 0鈥?2 涓?* 鈥斺€?涔熷氨鏄?
**Mojang 鐨勫姞杞介伄缃?*,鑰屼笉鏄爣棰樼晫闈?鏍囬鐣岄潰鐨勫舰鎬佸湪鏃╁厛鐨勮褰曢噷鏄?292 涓鑹叉《銆佹渶澶ф《 11%銆佸惈 splash 鍍忕礌")銆?
涔熷氨鏄杩欏彴鏈哄櫒涓婄殑 1.20.4 **婊¤冻鏂囨。鍒ゆ嵁,鍗存病鏈夌湡姝ｈ繘鍒版爣棰樼晫闈?*;鏂囨。閲岀殑鍒ゆ嵁鏈韩涓嶆鏌ヨ繖涓€鐐?
鑰?1.20.4 鐨勮褰曚篃鏄湪鍚屼竴濂楀垽鎹笅鍐欑殑,鎵€浠?*杩欐潯璁板綍鍚屾牱甯︾潃杩欎釜鏈娓呯殑杈圭晫**銆?
鎶婂畠鍐欏湪杩欓噷,鏄负浜嗕笉璁?鍒ゆ嵁鍏ㄧ豢"琚鎴?鏍囬鐣岄潰宸茬‘璁?銆?

**涓嬩竴姝?*:鏌?鍔犺浇閬僵涓轰粈涔堜竴鐩翠笉娑堝け"(1.21 閭ｆ潯绾挎槸璧勬簮閲嶈浇鐨勬爡鏍忓崱浣?杩欓噷杩樻病鏈夋煡),
浠ュ強閭?28 涓畫鐣?SRG 鍚?绗簩鍗佽妭)涓?`[OptiFine]` 琛屾暟宸紓鐨勬潵婧愩€?

### 浜屽崄鍥涖€?.20.4 鍔犺浇閬僵閭ｄ欢浜?绾跨▼鏍堜笌鏃ュ織缁欏嚭鐨勪笁浠朵簨

鏈疆鎶撲簡鍗′綇鏃剁殑绾跨▼鏍?`jstack`,绾?110 绉?骞惰浜嗗悓涓€鏃跺埢鐨勬棩蹇?閲忓埌:

1. **涓嶆槸璧勬簮閲嶈浇鏍呮爮鍗′綇**(杩欎笌 1.21 閭ｆ潯绾跨殑褰㈡€佷笉鍚?:娓叉煋绾跨▼鏄?**RUNNABLE**,
   鏍堟槸 `Minecraft.runTick 鈫?RenderSystem.limitDisplayFPS 鈫?glfwWaitEventsTimeout` 鈥斺€?瀹冨湪**姝ｅ父璺戝抚寰幆**,鍙槸琚?FPS 闄愬埗鑺傛祦;
   鎵€鏈?`Worker-Main-*` 閮界┖闂?`ForkJoinPool.awaitWork`)鉁?娌℃湁閲嶈浇浠诲姟鍦ㄩ銆?
2. 鏃ュ織灏鹃儴鍑虹幇浜?*绗簩娆?* `Reloading ResourceManager: mod_resources...`(18:31:51),绱ц窡鐫€鐨勬槸
   `SimpleReloadInstance.lambda$new$3` / `ResourceManagerReloadListener.reload` /
   `BlockEntityRenderDispatcher.onResourceManagerReload` 杩欎簺甯?鍚屼竴娈甸噷杩樻湁鏍囬鐣岄潰鎵嶄細瑙﹀彂鐨?
   `RealmsAvailability` 妫€鏌?瀹冭繛涓嶄笂 Realms 鏄父瑙佺殑鏃犲閿欒)銆備篃灏辨槸璇?閲嶈浇鍦ㄨ窇銆佽€屼笖鏈夌洃鍚櫒鍦ㄩ噷闈㈡姏杩囦笢瑗?
   浣?*娌℃湁瀵瑰簲鐨?ERROR 琛?*(鍏ㄦ棩蹇楀彧鏈?4 鏉?ERROR,閮芥槸 Realms 杩炴帴澶辫触)銆?
3. 涓ゆ绐楀彛鎴浘(60 绉掋€?30 绉?閮芥槸**鍘熺増閭ｄ釜绾㈣壊鍔犺浇閬僵**:鍧囧€?`233,73,8x`銆佸崟涓€棰滆壊妗跺崰 **84%** 鈥斺€?
   鑰屾爣棰樼晫闈㈢殑褰㈡€佹槸"292 涓壊妗躲€佹渶澶ф《 11%銆佸惈 splash 鍍忕礌"銆傛墍浠?*杩欏彴鏈哄櫒涓婄殑 1.20.4 纭疄娌℃湁杩涘埌鏍囬鐣岄潰**,
   灏界鏂囨。鍒ゆ嵁鍥涢」鍏ㄧ豢(绗簩鍗佷簩鑺?銆?

椤哄甫鏂板浜嗕竴涓皟璇曞紑鍏?`-Doptifineoforge.traceScreen=true`(寰€ `Minecraft.setScreen` 娉ㄥ叆涓€琛屾墦鍗?鐩存帴鍥炵瓟"褰撳墠鏄摢涓晫闈?)銆?
**浣嗗畠鏈疆娌℃湁鐢熸晥**:鏃ュ織閲岃繛"Tracing every screen switch"閮芥病鏈?鈥斺€?鍘熷洜鏄?`net/minecraft/client/Minecraft`
**涓嶅湪杞借嵎閲?*,鑰岃繖涓垎鏀殑 transformer 鍙"杞借嵎绱㈠紩 鈭?鍚勮鍒掔殑 owner"杩欐壒绫昏繍琛?鎵€浠ュ畠鍘嬫牴娌¤璋冪敤鍒般€?
杩欎竴鐐硅鍦ㄨ繖閲?涓嬩竴杞涔堟妸杩借釜鐩爣骞惰繘鐩爣闆嗗悎,瑕佷箞鎹㈠埆鐨勫姙娉?鍦ㄥ畠鐢熸晥涔嬪墠,"褰撳墠鐣岄潰鏄摢涓?鍙兘闈犳埅鍥句笌鏃ュ織鎺ㄦ柇銆?

**涓嬩竴姝?*:鏌ラ偅绗簩娆￠噸杞戒负浠€涔堟敹涓嶄簡灏?鐩戝惉鍣ㄦ姏鍑虹殑涓滆タ娌℃湁钀藉埌鏃ュ織閲?鍙互鎶?`-Doptifineoforge.traceCrash` 鎴?
瀵?`SimpleReloadInstance` 鍔犲悓鏍风殑娉ㄥ叆);浠ュ強鎶?`traceScreen` 鐨勭洰鏍囩被琛ヨ繘鐩爣闆嗗悎,鎶?褰撳墠鐣岄潰"鍙樻垚鐩存帴娴嬮噺銆?

### 浜屽崄鍏€佹洿姝ｄ笂涓€鑺?1.20.4 **纭疄**杩涘埌浜嗘爣棰樼晫闈?鈥斺€?鎴浘缁欑殑鏄繃鏈熷抚

涓婁竴鑺傛垜鏍规嵁鎴浘鍐欎簡"瀹冨仠鍦ㄥ姞杞介伄缃┿€佹病鏈夎繘鏍囬鐣岄潰"銆傛湰杞妸鐣岄潰杩借釜寮€鍏充慨濂戒箣鍚?杩欐潯**琚洿鎺ユ祴閲忔帹缈?*浜?

`-Doptifineoforge.traceScreen=true` 绗竴娆℃病鐢熸晥,鍘熷洜鏄?*涓や釜鍙犲湪涓€璧风殑鍧?*,閮藉凡淇?

1. transformer 鍙"杞借嵎绱㈠紩 鈭?鍚勮鍒?owner"杩欐壒绫昏璋冪敤,鑰?`net/minecraft/client/Minecraft` 涓嶅湪杞借嵎閲?鈬?杩借釜鐩爣蹇呴』鏄惧紡骞惰繘鐩爣闆嗗悎;
2. 骞惰繘鍘讳箣鍚庝粛鐒朵笉鐢熸晥 鈥斺€?鍥犱负 `TARGETS` 杩欎釜闈欐€佸瓧娈?*鍏堜簬**杩借釜寮€鍏抽偅鍑犱釜甯搁噺鍒濆鍖?璇诲埌鐨勮繕鏄粯璁ゅ€?鈬?鐩爣鏁板仠鍦?427銆?
   鏀规垚鍦?`loadTargets()` 閲岀洿鎺ヨ灞炴€т箣鍚?鐩爣鏁?**428** 鉁?鏃ュ織閲屽嚭鐜?`Tracing every screen switch in Minecraft.setScreen` 鉁斻€?

淇ソ鍚庣殑瀹炴祴杈撳嚭(1.20.4,`stderr`):

```
OPF-SCREEN net.minecraft.client.gui.screens.GenericDirtMessageScreen
OPF-SCREEN net.minecraft.client.gui.screens.TitleScreen
```

**褰撳墠鐣岄潰灏辨槸 `TitleScreen`,涔嬪悗鍐嶆病鏈夊垏杩囩晫闈?* 鉁斺湐銆備篃灏辨槸璇?

- 鍒ゆ嵁鍥涢」鍏ㄧ豢(绗簩鍗佷簩鑺?涓?杩涘埌鏍囬鐣岄潰"鏄?*鍚屾椂鎴愮珛**鐨?
- 鑰岀獥鍙ｆ埅鍥剧粰鍑虹殑鏄?*杩囨湡甯?*:鍚屼竴杞噷鐩搁殧绾?40 绉掓姄涓ゆ,缁熻**瀹屽叏鐩稿悓**(鍧囧€?`233,73,83`銆?9 涓壊妗躲€?
  鏈€澶ф《 84%銆乻plash 鍍忕礌 0)鈥斺€?涓€涓瘡甯ч兘鍦ㄩ噸缁樼殑绐楀彛涓嶅彲鑳戒袱娆′竴妯′竴鏍?鎵€浠ラ偅鏄?绐楀彛娌¤鑱氱劍銆佸唴瀹规病鏈夋洿鏂?鐨勮〃鐜?
  (rig 鐨勬埅鍥捐剼鏈湰韬篃浼氭墦鍗?`is foreground` 鏉ユ彁绀鸿繖涓€鐐?銆?
- 鍥犳涓婁竴鑺傞偅鍙ョ粨璁轰綔搴?**"鏍囬鐣岄潰"杩欎竴鏉＄幇鍦ㄧ敱娓告垙鑷繁鐨?`setScreen` 璋冪敤纭,涓嶇敱鍍忕礌纭**,鑰屽儚绱犱箣鎵€浠ヤ笉閰嶅悎,
  鍘熷洜涔熼噺鍒颁簡(涓ゆ鎶撳抚閫愰」鐩稿悓)銆?

椤哄甫鎶婂垽鎹張璺戜簡涓ら亶:`VERDICT: STARTED`銆乣Setting user` 鉁撱€乣Sound engine started` 鉁撱€?*0 宕╂簝鎶ュ憡**銆?
stderr 14 481 / 14 608 / 14 643 / 14 481 瀛楄妭(璁板綍鏄?14 481,涓ゆ閫愬瓧鑺傜浉鍚?銆?

### 浜屽崄鍏€佹湰杞敹鍙?杩欏彴鏈哄櫒涓婂埌搴曞疄娴嬩簡浠€涔堛€佽繕宸粈涔堛€佹€庝箞鎺ョ潃鍋?

**杩欏彴鏈哄櫒瀹炴祴閫氳繃鐨勭嚎(鍒ゆ嵁 = `VERDICT: STARTED` + `Setting user` + 澹伴煶寮曟搸 + 鏈杩愯 0 宕╂簝鎶ュ憡 + stderr 涓庤褰曚竴鑷?:**

| 绾?| 璇佹嵁 | `[OptiFine]` 琛屾暟(璁板綍 vs 瀹炴祴) | 鏍囬鐣岄潰 |
|---|---|---|---|
| 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 1.21.x 鍒嗘敮鐨?`README.md` / `docs/MATRIX.md`(鍚屼竴鏉?rig,鏃╁厛閮ㄥ垎) | 232/223銆?25/225銆?32/232銆?47/340銆?47/340銆?44/337 | 1.21.8 鏈夋埅鍥?鍏朵綑鎸夊垽鎹?|
| **1.20.6** | 鏈垎鏀?`docs/MATRIX.md` 2026-09-19 涓€鑺?+ 鎴浘(292 鑹叉《銆佹渶澶ф《 11%銆佸惈 splash) | 222 vs **231** | 鉁?鍍忕礌纭 |
| **1.20.4** | 绗簩鍗佷簩 / 浜屽崄鍏妭 | 241 vs **730**(rig 鎵撳嵃鐨勬槸 2脳 鍚堝苟鍊?鈬?365 鍘熷) | 鉁?鐢?`setScreen` 杩借釜纭(鍍忕礌鎶撳抚鏄繃鏈熷抚) |

**娌℃湁瀹炴祴鐨勭嚎**:1.20.1銆?.20.2銆?.21銆?.21.9銆?.21.10銆?.21.11銆?6.1.2銆?

**杩欏彴鏈哄櫒涓婄殑 rig(`I:\mods\optifineoforge-test`,涓嶅湪浠撳簱閲?鍖呭惈**:
`fetch-libraries.ps1`(鍚В鏋?`@ext` 鍒嗙被鍣?銆乣get-optifine.ps1`(adloadx 鎹?token 鍐?downloadx)銆?
`natives-for.ps1`(鎸?LWJGL 鐗堟湰閲嶅缓 `natives\`)銆乣prepare-line.ps1`(鐜版敮鎸?`-SrgMappings` / `-ObfOfficial`,
鍦ㄧ敓鎴愪换浣曡鍒?*涔嬪墠**鍋?SRG鈫掑畼鏂瑰悕鏀瑰悕)銆乣build-jars.ps1`(`-KeepRuntimeFile` / `-DropMembersFile` /
`-InterfaceFile` / `-AccessFile` / `-SrgTableFile`)銆乣proguard-to-tsrg.ps1`(宸蹭慨宓屽绫讳笌 obf 鍒楁枩鏉?銆?
`SrgMemberMap --emit`銆乣launch.ps1`(鏀寔 `RIG_EXTRA_JVM`,鍥犱负浠?`-` 寮€澶寸殑鍙傛暟娌℃硶璧?`-File`)銆?
`capture-window.ps1`(浼氭墦鍗?`is foreground`,杩囨湡甯ч棶棰樺氨闈犲畠璇嗗埆)銆乣add-line.ps1`(绔埌绔?涓嬭浇銆佽 NeoForge銆?
璺戠绾跨绾裤€佹瀯寤轰袱涓?jar銆佸彲閫?`-ModLauncher` / `-TargetJavaVersion` / `-InstallerArtifact` / `-SrgMappings`)銆?

**鎺ョ潃鍋氫竴鏉℃柊绾?SRG 杞借嵎绾?濡?1.20.2)鐨勯『搴?*(鍏ㄩ儴瀹炴祴杩?:

1. 鎷垮埌璇ョ増鏈?MCPConfig 鐨?`joined.tsrg`(Forge maven 鐨?`mcp_config` 鐩綍,鐗堟湰涓?*涓嶆槸** NeoForm 鐨勬椂闂存埑);
2. 鐢?`proguard-to-tsrg.ps1` 浠庤鐗堟湰鐨?client mappings 鐢熸垚 obf鈫抩fficial 琛?
3. `add-line.ps1 ... -ModLauncher 10 -SrgMappings <joined> -ObfOfficial <tsrg>`(Java 17,瀹夎鍣ㄥ潗鏍囨寜鐗堟湰);
4. 鑻ュ惎鍔ㄦ椂鎶?`IncompatibleClassChangeError: ... overrides final method`,鐢?`drop-members.txt` 鍒犳帀閭ｄ簺閲嶅啓;
5. 鑻ユ姤 `IllegalAccessError: ... tried to access method`,纭璁块棶璁″垝宸茬敓鎴愬苟宓屽叆(`runtime-access.txt`);
6. 鍚姩鍚庡鏋滅湅涓嶅埌绐楀彛鍐呭,鍒€ョ潃鍒?鍗′綇"鈥斺€斿厛鐢?`-Doptifineoforge.traceScreen=true` 闂父鎴忚嚜宸卞湪鍝釜鐣岄潰
   (鎴浘鍦ㄧ獥鍙ｆ湭鑱氱劍鏃朵細缁欒繃鏈熷抚);宕╂簝鎶ュ憡鍐欎笉鍑烘潵鏃剁敤 `-Doptifineoforge.traceCrash=true` 鐩存帴鎷垮師濮?throwable銆?

**杩欏彴鏈哄櫒涓婂繀椤昏浣忕殑涓変釜鍧?*:`natives\` 鏄墍鏈夌嚎鍏辩敤銆佷細涓?LWJGL 鐗堟湰(1.20.1鈥?.20.4 鏄?3.3.2,1.20.6+ 鏄?3.3.3);
宕╄繃鐨?JVM 浼氱暀鍦ㄥ悗鍙板崰鐫€ `glfw.dll`,閲嶈 natives 鍓嶈鏀舵帀;`[OptiFine]` 琛屾暟鍦?rig 鐨勫悎骞惰緭鍑洪噷鏄?`latest.log` 鐨?**2 鍊?*銆?

### 涓夊崄銆佸紑濮嬬涓夋潯绾?1.20.2(瑁呴厤涓€娆¤繃,鍚姩杩樺樊涓€姝?

鎸夌浜屽崄鍏妭閭ｅ recipe 璧?1.20.2(NeoForge `20.2.88`銆丮odLauncher 10銆丣ava 17銆佺敤鎴疯嚜宸辩殑
`preview_OptiFine_1.20.2_HD_U_I7_pre1.jar`),瑁呴厤**绗竴娆″氨杩?*:缂哄け寮曠敤 **22 / 42845**銆佸亣 stub **17**銆?
璁块棶璁″垝 106 琛屻€佹帴鍙ｈ鍒?15 琛屻€佹垚鍛樻仮澶?220 鏉?鈥斺€?涓?1.20.4 鍚屼竴閲忕骇(23 / 18)鉁斻€?

鍚姩閬囧埌鐨勭涓€涓潙鏄?*鑰侀棶棰樼殑鏂板疄渚?*:`NoSuchMethodError: 'java.lang.Throwable net.minecraft.CrashReport.m_127524_()'`,
鏉ヨ嚜 OptiFine 鑷繁鐨?`CrashReporter` 鈥斺€?涔熷氨鏄?*閲嶆墦鍖呯敤鐨勯偅浠?OptiFine jar 涔熷繀椤诲仛 SRG鈫掑畼鏂瑰悕鏀瑰悕**
(1.20.4 涓婃垜鏄墜宸ュ仛鐨?杩欐鎶婂畠鍐欒繘浜?`add-line.ps1`,缁?`-SrgMappings` 閭ｆ潯璺緞鑷姩鐢熸垚 `downloads\of-<mc>-official.jar` 鉁?銆?
鏀瑰悕涔嬪悗(SrgRemap 鎶?`rewrote 3445 method and 1378 field names, 0 could not be resolved`)鍚姩鎺ㄨ繘鍒?**6 琛?`[OptiFine]`** 鉁斻€?

鐜板湪鍋滃湪涓嬩竴澶?宸查噺鍒?:

```
java.lang.VerifyError: Bad <init> method call
  at net.minecraft.Util.memoize(Util.java:972)
  at net.minecraft.world.level.block.PinkPetalsBlock.<clinit>(PinkPetalsBlock.java:31)
  鈫?Blocks.<clinit> 鈫?FireBlock.bootStrap 鈫?Bootstrap.bootStrap 鈫?Main.main:156
```

`net/minecraft/Util` 鐨?`memoize(Function)` / `memoize(BiFunction)` 鍦ㄨ浇鑽蜂笌杩愯鏃?*涓よ竟閮藉湪銆侀兘鏄?static** 鉁?
`stubs-full.txt` 涓庢垚鍛樻仮澶嶈鍒掗噷涔?*娌℃湁** `Util` 鐨勬潯鐩?鉁?鈥斺€?鎵€浠ラ棶棰樺湪**浜や粯鍑哄幓鐨勯偅浠?`Util` 閲屾煇涓柟娉曠殑瀛楄妭鐮?*
(楠岃瘉鏄寜绫诲仛鐨?鎶ラ敊鍙槸纰板阀钀藉湪 `memoize` 杩欎竴甯т笂)銆傝涔堟槸鏀瑰悕鎶婃煇涓?`<init>` 璋冪敤鏀归敊浜嗙洰鏍?瑕佷箞鏄埆鐨勬垚鍛樿鏇挎崲鍚庡舰鐘朵笉瀵广€?
**涓嬩竴姝?*:鐢?`-Doptifineoforge.dump=` 鎶婁氦浠樼殑 `Util` 鎷垮嚭鏉?涓庤繍琛屾椂鐨?`Util` 閫愭柟娉?`javap -c` 瀵圭収,鎵惧嚭閭ｆ潯鍧忕殑 `<init>` 璋冪敤銆?

### 涓夊崄浜屻€?.20.2 涔熼€氳繃浜?鈥斺€?杩欐潯绾挎枃妗ｉ噷鏈€钖勭殑涓€鐜?

1.20.2 鍦?`docs/VERSIONS.md` 閲岃鏍囦负"鏈嚎鏈€钖勭殑涓€鐜?(OptiFine 鍙湁**涓€涓?* preview 鏋勫缓),鏈疆鎶婂畠璺戦€氫簡銆?
鍒ゆ嵁(`launch.ps1`,涓ゆ鐙珛杩愯):

| 鍒ゆ嵁 | 璁板綍 | 鏈満瀹炴祴 |
|---|---|---|
| `VERDICT` | `STARTED` | `STARTED` 鉁?|
| `Setting user` | 鉁?| 鉁?鉁?|
| 澹伴煶寮曟搸 | 鉁?| 鉁?鉁?|
| 鏈杩愯鐨勫穿婧冩姤鍛?| 0 | **0** 鉁?|
| stderr | 14 625 瀛楄妭 | **14 625** 鉁?甯?`traceScreen` 閭ｆ 14 682,澶氬嚭鏉ョ殑 57 瀛楄妭姝ｆ槸杩借釜鑷繁鎵撳嵃鐨勯偅涓€琛? |

鏍囬鐣岄潰鍚屾牱鐢?`setScreen` 杩借釜鐩存帴娴嬪埌:`OPF-SCREEN net.minecraft.client.gui.screens.TitleScreen` 鉁斻€?
`[OptiFine]` 琛屾暟 **494**(鍚堝苟鍊?= 247 鍘熷)瀵硅褰?239 鈥斺€?鍙堟槸閭ｄ釜**鏈煡鏄?*鐨勫樊寮?涓?1.20.4 鍚屼竴鎬ц川銆?

**璁╁畠璺戦€氱殑鏄笁澶?*(閮藉湪鏈疆瀹炴祴):

1. **閲嶆墦鍖呯敤鐨?OptiFine jar 涔熻鍋?SRG鈫掑畼鏂瑰悕鏀瑰悕** 鈥斺€?鍚﹀垯 OptiFine 鑷繁鐨?`CrashReporter` 浼氬幓鎵?
   `CrashReport.m_127524_()`:`NoSuchMethodError`銆傝繖涓€姝ュ凡缁忓啓杩?rig 鐨?`add-line.ps1`(`-SrgMappings` 璺緞鑷姩鐢熸垚
   `downloads\of-<mc>-official.jar`)銆?
2. **鏁翠釜 `net/minecraft/Util` 瀹舵棌蹇呴』鏉ヨ嚜鍚屼竴渚?*銆?.20.2 杩欎釜鍞竴鐨?preview 杞借嵎鍐呴儴**鑷浉鐭涚浘**:
   瀹冪殑 `Util` 璋?`Util$5.<init>(Ljava/nio/file/Path;)V`,鑰屽畠鐨?`Util$5` 鍙０鏄庢棤鍙傛瀯閫?鎹曡幏瀛楁鐢氳嚦杩樺彨
   `val$pathIn`,鏉ヨ嚜鍙︿竴娆＄紪璇?鈬?`VerifyError: Bad <init> method call`;鎶婅繍琛屾椂閭ｄ唤 `Util` 鍗曠嫭淇濈暀銆佽€?`Util$9`
   浠嶆潵鑷浇鑽?鍙堝彉鎴?`VerifyError: Bad type on operand stack`(淇濈暀鐨?`Util` 瀵?`new Util$9` 璋?Thread 鐨勬柟娉?銆?
   浜庢槸鎶婅繍琛屾椂閭?**16** 涓?`Util`/`Util$*` 绫?*鏁寸被淇濈暀** 鉁?鏈垎鏀柊鍔犵殑 `owner<TAB>*` 褰㈠紡鍒氬ソ鑳借〃杈捐繖浠朵簨)銆?
   娉ㄦ剰:杩?*涓嶆槸**鎴戜滑鏀瑰悕鐨勪骇鐗?鈥斺€?鏀瑰悕鍓嶇殑 patcher 杈撳嚭閲屽氨宸茬粡鏄敊鐨?閫愰樁娈?`javap` 瀵圭収杩?銆?
3. **鏂囨。閲屾棭鍐欒繃鐨勯偅鏉?ModelPart 淇**銆傚穿鍦?`IllegalStateException: Failed to create model for minecraft:skull`
   (`BlockEntityRenderDispatcher.onResourceManagerReload`)鈥斺€?姝ｆ槸 `PatchedClassTransformer` 閲岄偅娈垫敞閲婃弿杩扮殑鎯呭舰:
   OptiFine 鐨?`ModelPart.getChild` 鎸夊畠鑷繁鐑樼剻鏃惰缃殑 id 鏌ュ瓙鑺傜偣,鑰岃繖鏉＄嚎涓?OptiFine 鏍规湰涓嶈ˉ `PartDefinition`,
   浜庢槸 id 姘歌繙鏄?0銆佹煡鎵炬案杩滆繑鍥?null 鈬?淇濈暀**娓告垙渚у師鏈殑 `children.get(name)`** 灏辨槸淇硶(`keep-runtime-1.20.2.txt`
   閲岄偅涓€琛屾垚鍛樼骇鏉＄洰)銆?

### 涓夊崄鍥涖€?.20.1 鐨勫墠涓ゅ闃诲(宸茶瘖鏂?鏈€氳繃)

1.20.1 鏄繖鏉＄嚎涓婂敮涓€璧?`net.neoforged:forge:1.20.1-47.1.106` 鍧愭爣鐨勭増鏈?Java 17銆丮odLauncher 10),
鏈疆鎶婂伐鍏烽摼瑁呬笂(瀹夎鍣ㄧ収鏍疯 seed:绗竴杞?25 涓潗鏍?銆佽閰嶄篃璧伴€氫簡 鈥斺€?浣嗗惎鍔ㄦ湁涓ゅ鏃╁け璐?閮藉凡瀹氫綅:

1. **瑁呴厤闃舵鐨勪竴涓?*line 鍒嗙晫 **琚噺浜嗗嚭鏉?*:`SrgRemap` 鐩存帴**鎷掔粷**鏀瑰悕骞剁粰鍑虹悊鐢?鈥斺€?
   `refusing to rewrite: this runtime is SRG-named (49689 members match m_/f_), so the payload needs no rewriting on this line`銆?
   涔熷氨鏄 **1.20.1 鐨勮繍琛屾椂鏈韩杩樻槸 SRG 鍚?*(Forge 鏃朵唬),杞借嵎涔熸槸 SRG 鈬?**杩欐潯绾夸笉闇€瑕佹敼鍚?*
   (`add-line.ps1` 鐨?`-SrgMappings` 鍙€傜敤浜?1.20.2 鍙婁互鍚庣殑 1.20.x)銆傝閰嶆湰韬洜姝ゅ緢骞插噣:
   42355 鏉″紩鐢ㄩ噷鍙湁 **3** 鏉＄己澶便€乣--stub` 琛?0 涓?3 鏉＄暀缁?loader)銆?
2. **绗竴娆″惎鍔ㄦ鍦ㄦā鍧楄В鏋?*:`java.lang.module.ResolutionException: Modules OptifiNeoforge.mc1._20._1.registered and
   net.minecraftforge.eventbus export package net.minecraftforge...` 鈥斺€?鎴戜滑鍦?loader jar 閲屽浜?**Forge API 妗?*
   (`ForgeApiShims` 浜у嚭鐨?`net/minecraftforge/**`),鑰?1.20.1 鐨?FML 鑷繁灏辨湁鐪熺殑 `net.minecraftforge.**`(瀹冭繕瀵煎嚭浜?
   鍚屼竴涓寘)鈬?鎷嗘帀 `-StubDir` 涔嬪悗杩欎竴鏉℃秷澶?鉁斻€?
3. **绗簩娆″惎鍔ㄦ鍦?OptiFine 鑷繁鐨?jar 澶勭悊浠ｇ爜涓?Forge 鐗堟湰鐨勯敊閰?*:
   `NoSuchMethodError: 'void cpw.mods.jarhandling.impl.SimpleJarMetadata.<init>(String, String, Supplier, ...)'`
   鈫?`optifine.OptiFineJar.<init>`(LAYER SERVICE/optifine)銆備篃灏辨槸杩欎唤 OptiFine 1.20.1 鏋勫缓鎵€渚濊禆鐨?
   **securejarhandler / fancymodloader 姣?Forge `47.1.106` 鑷甫鐨勬柊**銆備笅涓€姝ユ槸鎹㈡垚 `47.2.x` 鐨勫畨瑁呭櫒鍐嶈瘯
   (鍒嗘敮鏂囨。閲屼篃鎻愯繃 47.2.x 甯?`fancymodloader 47.2.2`),杩欐槸**鏈畬鎴?*鐨勪竴椤广€?

### 涓夊崄鍏€?.20.1 鐨勭涓夊闃诲:securejarhandler 鐗堟湰(宸蹭慨),浠ュ強绗洓澶?涓?1.20.2 鍚岀被)

鎺ョ潃涓婁竴鑺?

3. **`SimpleJarMetadata` 閭ｄ釜閿欓厤鐨勬牴鍥犻噺鍑烘潵浜?* 鈥斺€?涓変釜 securejarhandler 鐗堟湰鐨勬瀯閫犲櫒鍚勪笉鐩稿悓(閫愪釜 `javap`):

   | jar | `SimpleJarMetadata` 鐨勬瀯閫犲櫒 |
   |---|---|
   | `2.1.10` | `(String, String, Set<String>, List<Provider>)` |
   | **`2.1.24`** | `(String, String, **Supplier<Set<String>>**, List<Provider>)` 鈫?**OptiFine 1.20.1 I6 瑕佺殑鏄繖涓?* |
   | `9.0.14` | `(String, String, JarContents)` |

   鑰?Forge 鐨?profile 鎶?`securejarhandler 2.1.10` 閽変綇浜?47.1.106 涓?47.4.23 閮芥槸)鈬?鎶ョ殑灏辨槸
   `NoSuchMethodError: SimpleJarMetadata.<init>(String, String, Supplier, List)`銆備慨娉?瀹炴祴鏈夋晥):
   鎶?**profile 閲屼袱澶?*閮芥崲鎺?鈥斺€?JVM 鍙傛暟涓查噷鐨?`securejarhandler/2.1.10/...` 鍜?`libraries[].downloads.artifact.path`
   閲岀殑 `cpw/mods/securejarhandler/2.1.10/...`(鍙敼鍓嶈€呬笉璧蜂綔鐢?launch.ps1 鏄寜 `artifact.path` 瑙ｆ瀽 jar 鐨?銆?
   鎹㈡帀涔嬪悗 `optifine.OptiFineJar` 閭ｆ潯 `NoSuchMethodError` 娑堝け 鉁斻€?
4. **鎹㈣繃鍘讳箣鍚庨湶鍑烘潵鐨勪笅涓€澶勬槸 `VerifyError: Bad <init> method call`** 鈥斺€?涓?1.20.2 涓婇偅涓?`net/minecraft/Util$N`
   瀹舵棌閿欓厤**鍚屼竴绫?*(瑙佺涓夊崄浜岃妭绗?2 鏉?:OptiFine 杩欎唤杞借嵎鐨勫尶鍚嶇被涓庤繍琛屾椂鐨勭紪璇戜骇鐗╀笉閰嶅銆?
   涓嬩竴姝ュ氨鏄妸 1.20.2 涓婄敤鐨勯偅鎷?鏁寸被淇濈暀杩愯鏃剁殑 `Util` 瀹舵棌)鐓ф惉鍒?1.20.1 鈥斺€?杩欎竴姝?*杩樻病鍋?*銆?

### 涓夊崄鍏€?.20.1 涔熸弧瓒冲垽鎹簡(绗洓澶勯樆濉炲氨鏄?1.20.2 閭ｄ竴鎷?

鎶?1.20.2 涓婄殑鍋氭硶鐓ф惉鍒?1.20.1 鈥斺€?杩愯鏃堕偅 16 涓?`net/minecraft/Util` / `Util$*` 绫绘暣绫讳繚鐣?鍐嶅姞鏂囨。閲岄偅鏉?
`ModelPart.getChild` 鎴愬憳绾т繚鐣?鈥斺€?涔嬪悗:

| 鍒ゆ嵁 | 璁板綍 | 鏈満瀹炴祴 |
|---|---|---|
| `VERDICT` | `STARTED` | `STARTED` 鉁?|
| `Setting user` | 鉁?| 鉁?鉁?|
| 澹伴煶寮曟搸 | 鉁?| 鉁?鉁?|
| 鏈杩愯鐨勫穿婧冩姤鍛?| 0 | **0** 鉁?|
| stderr | **27 瀛楄妭** | **0 瀛楄妭** 鉁?|
| `[OptiFine]` 琛屾暟 | **157** | 310(鍚堝苟鍊?= **155 鍘熷**) |

涓ゆ鐙珛杩愯缁撴灉涓€鑷?鉁?閮?`STARTED`銆? 宕╂簝銆乻tderr 0)銆?

**濡傚疄鏍囨敞涓ょ偣**:

1. **stderr 涓嶅尮閰?*:璁板綍鏄?27 瀛楄妭,鏈満涓ゆ閮芥槸 **0**銆備竴涓彲鑳界殑鍘熷洜鏄褰曢偅娆＄敤鐨勬槸**鍙︿竴涓?OptiFine 鏋勫缓**
   鈥斺€?杩欐潯绾夸笂 1.20.1 鏈変袱绫绘瀯寤?姝ｅ紡鐗?`HD_U_I6` 涓庢湰杞敤鐨勮繖涓?浠ュ強 preview `HD_U_I6_pre6`),鑰?1.20.6 閭ｆ潯绾?
   璁板綍鐢ㄧ殑灏辨槸 preview銆?*鏈疆娌℃湁楠岃瘉杩欎釜鐚滄祴**(preview 娌℃湁涓嬭浇),鎵€浠ュ彧鑳藉啓鎴?鏈煡鏄?銆?
2. **鏍囬鐣岄潰娌℃湁鐩存帴娴嬪埌**:`-Doptifineoforge.traceScreen=true` 鍦?1.20.1 涓?*娌℃湁浜х敓浠讳綍杈撳嚭** 鈥斺€?
   loader jar 閲岀‘瀹炴湁杩欐浠ｇ爜(`PatchedClassTransformer.class` 閲岃兘鏌ュ埌瀛楃涓?銆佺洰鏍囨暟涔熷彉鎴愪簡 413,
   浣嗘棦娌℃湁 `Tracing every screen switch` 涔熸病鏈?`OPF-SCREEN`銆備篃灏辨槸璇磋繖鏉＄嚎涓?褰撳墠鐣岄潰"杩欎竴闂粛鐒跺彧鏈?
   **鍒ゆ嵁**(`Setting user` + 澹伴煶寮曟搸 + 0 宕╂簝)鍙敤,涓嶅儚 1.20.2 / 1.20.4 閭ｆ牱鏈夌洿鎺ユ祴閲忋€?

### 鍥涘崄銆?.20.1 閭ｆ潯 stderr 宸紓:鏋勫缓鍋囪**琚疄娴嬪惁鎺?*

绗笁鍗佸叓鑺傚啓杩囦竴涓寽娴?璁板綍閲?1.20.1 鐨?stderr 鏄?27 瀛楄妭鑰屾湰鏈烘槸 0,鍙兘鏄洜涓鸿褰曠敤鐨勬槸 **preview 鏋勫缓**
(`preview_OptiFine_1.20.1_HD_U_I6_pre6.jar`)銆傛湰杞妸瀹冧笅杞戒笅鏉?瑙佷笅)骞?*鐢ㄥ悓涓€濂楃粍瑁呴噸璺?*:

| 鐢ㄧ殑 OptiFine 鏋勫缓 | `VERDICT` | 宕╂簝鎶ュ憡 | stderr | `[OptiFine]` 鍚堝苟琛屾暟 |
|---|---|---|---|---|
| `OptiFine_1.20.1_HD_U_I6.jar`(姝ｅ紡鐗? | `STARTED` | 0 | **0 瀛楄妭** | 310(155 鍘熷) |
| `preview_OptiFine_1.20.1_HD_U_I6_pre6.jar` | `STARTED` | 0 | **0 瀛楄妭** | 310(155 鍘熷) |

涔熷氨鏄**涓や釜鏋勫缓缁欏嚭鐨勭粨鏋滀竴鏍?*,鐚滄祴**涓嶆垚绔?* 鈥斺€?27 瀛楄妭涓嶆槸鏋勫缓宸紓甯︽潵鐨勩€傞偅 27 瀛楄妭鍒板簳鏉ヨ嚜浠€涔?
**浠嶆湭鏌ユ槑**(鍙兘鏄師 rig 鐜閲岀殑涓€鏉℃棤鍏宠鍛?銆傞『甯﹂噺鍒?`[OptiFine]` 鍘熷琛屾暟 155 瀵硅褰?157 鏄?**-2**,
涓庡叾浠栫嚎涓婄殑 +7/+9 鏂瑰悜鐩稿弽 鈥斺€?鍚屼竴涓?琛屾暟宸紓"闂鐨勫張涓€闈€?

**椤哄甫涓や欢 rig 浜嬪疄**:鈶?`preview_OptiFine_1.20.1_HD_U_I6_pre6.jar` 鍦?**optifine.net 涓婁笅涓嶅埌浜?* 鈥斺€?
adloadx 鎷垮緱鍒?token,浣?downloadx 杩斿洖 16 瀛楄妭鐨?`File not found.`(HTTP 200),鑰屾瀯寤哄垪琛ㄩ噷瀹冭繕鍦?
浠撳簱鏂囨。閲屾彁鍒扮殑绗笁鏂归暅鍍?`bmclapi2.bangbang93.com/optifine/1.20.1/HD_U_I6/pre6` 浠嶇劧鏈?7 145 056 瀛楄妭)銆?
鈶?浣嗛偅涓暅鍍?*鍙兘璧?IPv4**:`curl -6` 杩炰富鏈洪兘瑙ｆ瀽涓嶄簡(`Could not resolve host`),鑰?rig 鐨勪笅杞借剼鏈竴寰嬪甫 `-6`
(閭ｆ槸涓?maven.neoforged.net 鍔犵殑)鈬?宸茬粡缁?`get-optifine.ps1` 鍔犱簡"16 瀛楄妭浠ュ閮界畻澶辫触"鐨勫垽鏂笌璇存槑銆?

### 鍥涘崄涓€銆佽竟鐣?

- 鏈垎鏀洓鏉＄嚎閮芥弧瓒虫枃妗ｅ垽鎹?**1.20.6**銆?*1.20.4**銆?*1.20.2**銆?*1.20.1**
  (1.20.2 / 1.20.4 鏈?`setScreen` 鐩存帴纭鏍囬鐣岄潰;1.20.1 鐨?stderr 鏄?0 鑰岃褰曟槸 27 鈥斺€?鏈疆宸茶瘉鏄庤繖涓?
  鐢ㄥ摢涓?OptiFine 鏋勫缓鏃犲叧;鍥涙潯绾跨殑 `[OptiFine]` 琛屾暟涓庤褰曢兘涓嶅悓,宸紓**鏈煡鏄?*)銆?
- 鏈窇:1.21(1.21.x 鍒嗘敮鏃╁厛宸插疄娴嬪叚鏉°€佷粛鍗″湪璧勬簮閲嶈浇)銆?.21.9 / 1.21.10 / 1.21.11銆?6.1.2銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 鏈垎鏀洓鏉＄嚎鍏ㄩ儴婊¤冻鏂囨。鍒ゆ嵁:**1.20.6**銆?*1.20.4**銆?*1.20.2**銆?*1.20.1**
  (1.20.2 / 1.20.4 鍙︽湁 `setScreen` 鐩存帴纭鏍囬鐣岄潰;1.20.1 鐨?stderr 涓庤褰曚笉绗︺€佹爣棰樼晫闈㈡湭鐩存帴娴嬪埌;
  鍥涙潯绾跨殑 `[OptiFine]` 琛屾暟涓庤褰曢兘涓嶅悓,宸紓**鏈煡鏄?*)銆?
- 鏈窇:1.21(1.21.x 鍒嗘敮,鏃╁厛宸插疄娴嬪叚鏉″苟璁板湪閭ｈ竟銆佷粛鍗″湪璧勬簮閲嶈浇)銆?.21.9 / 1.21.10 / 1.21.11銆?6.1.2銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 瀹炴祴閫氳繃:**1.20.6**銆?*1.20.4**銆?*1.20.2**(鍚庝袱鏉℃爣棰樼晫闈㈢敱 `setScreen` 杩借釜纭)銆?
- **1.20.1**:瑁呴厤 3/42355 缂哄け,鍚姩宸茶秺杩?妯″潡瑙ｆ瀽"涓?OptiFine jar 澶勭悊"涓ら亾鍧?鍚庤€呴潬鎶?profile 閲岀殑
  securejarhandler 鎹㈡垚 **2.1.24**),褰撳墠闃诲鏄浇鑽风殑 `VerifyError: Bad <init> method call`,
  涓嬩竴姝ョ収鎼?1.20.2 鐨?`Util` 瀹舵棌鏁寸被淇濈暀;**鏈€氳繃**銆?
- 1.21 / 1.21.9 / 1.21.10 / 1.21.11 / 26.1.2 鏈窇銆?
- rig 渚ф湰杞張鏀逛簡涓夊:瀹夎鍣?URL 涓や釜 host 閮借瘯(Forge 鏃朵唬 47.1.106 鍦?neoforged銆?7.4.23 鍙湪
  minecraftforge)銆乤rtifact 鐩綍涓や釜 group 閮芥壘銆佷互鍙婁笂闈㈢殑 securejarhandler 鏇挎崲銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 瀹炴祴閫氳繃:**1.20.6**銆?*1.20.4**銆?*1.20.2**(鍚庝袱鏉℃爣棰樼晫闈㈢敱 `setScreen` 杩借釜纭)銆?
- **1.20.1**:瑁呴厤宸茶繃(3/42355 缂哄け寮曠敤),鍚姩鍋滃湪 OptiFine 涓?Forge `47.1.106` 鐨?securejarhandler 閿欓厤,
  涓嬩竴姝ユ崲 `47.2.x`;**鏈€氳繃**銆?
- 1.21 / 1.21.9 / 1.21.10 / 1.21.11 / 26.1.2 鏈窇銆?
- rig 渚ф湰杞敼浜嗕笁澶?閲嶆墦鍖呯敤鐨?OptiFine jar 鑷姩鏀瑰悕(1.20.2 璧?銆乣prepare-line`/`add-line` 鐨?
  universal/client jar 璺緞鏀规垚璺熼殢 `-InstallerArtifact`(1.20.1 鐨?`forge` 鍧愭爣)銆佷互鍙?`-InstallOnly`銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 鏈垎鏀疄娴嬮€氳繃:**1.20.6**銆?*1.20.4**銆?*1.20.2**(鍚庝袱鏉＄殑鏍囬鐣岄潰鐢?`setScreen` 杩借釜鐩存帴娴嬪埌)銆?
- **1.20.1** 灏氭湭灏濊瘯(瀹冩槸杩欐潯绾夸笂鍞竴璧?`net.neoforged:forge:1.20.1-47.1.106` 鍧愭爣銆丣ava 17銆丮odLauncher 10 鐨勭増鏈?
  搴旇鑳藉鐢?1.20.2/1.20.4 杩欏 recipe)銆?
- 1.21 / 1.21.9 / 1.21.10 / 1.21.11 / 26.1.2 鏈窇銆?
- `[OptiFine]` 琛屾暟涓庤褰曠殑宸紓**浠嶆湭鏌ユ槑**(1.20.2: 247 鍘熷 vs 239;1.20.4: 365 vs 241;1.20.6: 231 vs 222)銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 瀹炴祴閫氳繃:**1.20.6**(鍍忕礌纭鏍囬鐣岄潰)銆?*1.20.4**(鍒ゆ嵁 + `setScreen` 纭,鍍忕礌鎶撳抚鏄繃鏈熷抚)銆?
- **1.20.2**:瑁呴厤宸茶繃銆佸惎鍔ㄦ帹杩涘埌 6 琛?`[OptiFine]`,褰撳墠闃诲鏄?`Util` 鐨?`VerifyError`(鏍瑰洜鏈粨)銆?
- 鏈窇:1.20.1銆?.21銆?.21.9銆?.21.10銆?.21.11銆?6.1.2銆?
- `[OptiFine]` 琛屾暟涓庤褰曠殑宸紓**鏈煡鏄?*;`add-line.ps1` 鏂板浜?閲嶆墦鍖呯敤 OptiFine jar 鑷姩鏀瑰悕"杩欎竴姝?1.20.2 璧?銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- 鏈垎鏀疄娴嬮€氳繃:**1.20.6**(鍍忕礌纭鏍囬鐣岄潰)涓?**1.20.4**(鍒ゆ嵁 + `setScreen` 纭,鍍忕礌鎶撳抚鏄繃鏈熷抚)銆?
- 鏈疄娴?1.20.1 / 1.20.2 / 1.21 / 1.21.9 / 1.21.10 / 1.21.11 / 26.1.2銆?
- `[OptiFine]` 琛屾暟涓庤褰?*鏅亶涓嶄竴鑷?*(1.20.4 宸緱灏ゅ叾澶?730 鍚堝苟 = 365 鍘熷 vs 璁板綍 241),鍘熷洜**鏈煡鏄?*,
  鍙鐫€"杩欏彴鏈哄櫒鐨勬暟瀛楁洿楂?;stderr 鍦?1.20.4 涓婁袱娆′笌璁板綍**閫愬瓧鑺傜浉鍚?*銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- **1.20.4 鐜板湪绠楅€氳繃**:鍒ゆ嵁鍥涢」鍏ㄧ豢,涓?褰撳墠鐣岄潰 = `TitleScreen`"鐢?`setScreen` 杩借釜鐩存帴娴嬪埌;鍞竴娌″仛鍒扮殑鏄?
  **鍍忕礌绾ф埅鍥剧‘璁?*(绐楀彛鏈仛鐒︽椂鎶撳埌鐨勫抚涓ゆ瀹屽叏鐩稿悓,鏄繃鏈熷抚)鈥斺€?杩欎竴鐐硅繛鍚屽師鍥犲啓鍦ㄤ笂鑺?涓嶅綋浣?宸茬‘璁?銆?
- 1.20.6 鏃╁厛宸茬敱鎴浘纭鏍囬鐣岄潰;**杩欎袱鏉℃槸鏈垎鏀疄娴嬮€氳繃鐨勭嚎**銆?.20.1 / 1.20.2 鏈窇;1.21.x / 26.x 鏈疆鏈姩銆?
- 鏈疆鏂板/淇:鐣岄潰杩借釜寮€鍏宠繛鍚?杩借釜鐩爣蹇呴』骞惰繘鐩爣闆嗗悎銆佷笖灞炴€ц鍦?`TARGETS` 涔嬪墠璇?杩欎袱澶?宸叉彁浜ゃ€?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- **瀹炴祴婊¤冻鏂囨。鍥涙潯鍒ゆ嵁鐨勭嚎鏄袱涓?*:1.20.6(鎴浘纭鏍囬鐣岄潰)涓?1.20.4(鍒ゆ嵁鍏ㄧ豢銆乻tderr 涓ゆ閫愬瓧鑺傜瓑浜庤褰?
  浣?*鎴浘纭瀹冨仠鍦ㄥ姞杞介伄缃?*,娌℃湁杩涙爣棰樼晫闈?;1.20.1 / 1.20.2 鏈窇銆?
- 鏈疆鏂板 `-Doptifineoforge.traceScreen=true`(宸插疄鐜般€?*灏氭湭鐢熸晥**,鍘熷洜瑙佷笂鑺?;1.21.x 涓?26.x 绾挎湰杞病鍔ㄣ€?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- **瀹炴祴婊¤冻鏂囨。鍥涙潯鍒ゆ嵁鐨勭増鏈幇鍦ㄦ槸涓や釜**:1.20.6 涓?**1.20.4**(鍚庤€呭垽鎹叏缁裤€乻tderr 涓ゆ閫愬瓧鑺傜瓑浜庤褰曠殑
  14 481,浣嗘埅鍥炬樉绀哄仠鍦ㄥ姞杞介伄缃?**娌℃湁**瑙嗚纭鏍囬鐣岄潰);1.20.1 / 1.20.2 鏈窇銆?
- 1.21.x 绾胯繖涓€杞病鍔?鍏潯宸插疄娴嬬嚎 + 1.21 浠嶅崱鍦ㄨ祫婧愰噸杞?;26.x 绾挎湭璺戙€?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


- **1.20.6 浠嶆槸鏈垎鏀敮涓€瀹炴祴閫氳繃鐨勭増鏈?*;1.20.4 鍋滃湪"杩涘埌娓叉煋寰幆銆?30 琛?`[OptiFine]`銆乣Setting user` 鉁撱€?
  stderr 0 瀛楄妭銆? 浠藉穿婧冩姤鍛?,瑁呴厤璐ㄩ噺鏈疆鏄捐憲鍙樺ソ(缂哄け寮曠敤 63鈫?3銆佸亣 stub 46鈫?8銆佽浇鑽锋畫鐣欏悕 114鈫?8),
  浣嗗惎鍔ㄧ粨鏋滄湭鍙?1.20.1 / 1.20.2 鏈窇銆?
- 鏈疆鏀逛簡 loader 涓€鏉″垽瀹?鍚岀被鎺ュ彛鎹㈣鍏佽銆乣-Doptifineoforge.strictInterfaceKeep=true` 鍙€€鍥炴棫琛屼负),骞舵柊澧?
  `-Doptifineoforge.dump=<dir>` 璋冭瘯寮€鍏?浠ュ強淇ソ rig 鐨勪袱寮犳槧灏勮〃(宓屽绫?+ obf 鍒楁枩鏉?;杩欎簺閮戒笉鍙備笌楠屾敹鍒ゆ嵁銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?

## 2026-09-19(鏅氶棿):鍦ㄦ湰鏈洪噸寤虹殑 rig 涓婅窇 1.20.6(瀹炴祴涓庤褰曠殑宸紓),浠ュ強 1.20.4 鐨勫疄娴嬮樆濉?

鍘熷 rig(`optifineoforge-test`)鍦ㄨ繖鍙版満鍣ㄤ笂**涓嶅瓨鍦?*,鎵€浠ヨ繖涓€杞槸**浠庨浂閲嶅缓**涓€涓瓑浠?rig:涓嬭浇鍘熺増瀹㈡埛绔?
涓?NeoForge 瀹夎鍣ㄣ€佹妸鏈粨搴撶殑绂荤嚎娴佹按绾?`kynarain.cn.optifineoforge.optifine`)璺戝湪**鐢ㄦ埛鑷繁鐨?* OptiFine jar
涓娿€佸啀鐢?rig 鐨?`launch.ps1` 璧蜂竴娆＄湡瀹炲鎴风銆侽ptiFine 涓嶉殢浠撳簱鍒嗗彂,鎵€浠ヨ浇鑽峰繀椤荤幇鍦虹敓鎴?鈥斺€?杩欎竴杞粠澶村埌灏?
娌℃湁鐢ㄨ繃浠讳綍鍒汉棰勬墦鍖呯殑 OptiFine 杞借嵎銆?

### 涓€銆?.20.6:鍒ゆ嵁閫愰」(璁板綍 vs 鏈満瀹炴祴)

鐩爣:`1.20.6` / NeoForge `20.6.141`(ModLauncher 11銆丣ava 21)/ `preview_OptiFine_1.20.6_HD_U_J1_pre18.jar`銆?

| 鍒ゆ嵁 | 璁板綍 | 鏈満瀹炴祴 | 缁撹 |
|---|---|---|---|
| `VERDICT` | `STARTED` | `STARTED` | 涓€鑷?|
| `Setting user` | 鉁?| 鉁?`Setting user: Dev`) | 涓€鑷?|
| 鏈杩愯鐨勫穿婧冩姤鍛?| 0 | 0 | 涓€鑷?|
| stderr | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| `[OptiFine]` 琛屾暟(`latest.log`) | 222 | **231** | **涓嶄竴鑷?+9)** |

- 231 鏄?*鍙鐜?*鐨?涓ゆ鐙珛杩愯閮芥槸 231(rig 鎵撳嵃鐨?462 鏄悎骞惰緭鍑?= stdout+stderr+latest.log,姝ｅソ 2脳)銆?
- 杩?+9 **涓嶆槸**杩欐潯绾跨嫭鏈?鍚屼竴鍙版満鍣ㄤ笂 1.21 涓?1.21.1 鏄?+9銆?.21.6 / 1.21.7 / 1.21.8 鏄?+7,鑰?1.21.3 涓?
  1.21.4 涓庤褰?*瀹屽叏鐩哥瓑**銆?*璁板綍閲岀殑 `latest.log` 涓嶅湪浠撳簱閲?*,鎵€浠?澶氬嚭鏉ョ殑鏄摢 9 琛?鏃犳硶浠庤繖杈瑰綊鍥?
  鎸夌幇鏈夎瘉鎹湅鏇村彲鑳芥槸鐜宸紓(椹卞姩/璧勬簮鍖呮灇涓?,涓嶆槸杞借嵎宸紓銆?
- 鏈満杩?231 琛岀殑鏋勬垚(鎸夋秷鎭舰鐘跺垎缁?:`(Reflector) Class not present` 40銆乣Scaled non power of` 18銆?
  `(Reflector) Method not present` 17銆乣Multitexture: false` 14銆乣Animated sprites` 14銆乣Scaled too small
  texture` 12銆乣(Reflector) Field not present` 12銆乣Unknown resource pack type` 11,鍏朵綑鏄?CTM 涓庤创鍥鹃泦绫汇€?
- 绐楀彛鍙︾敤 rig 鐨?`capture-window.ps1` 鎶撹繃涓€娆?`PrintWindow=True`,绐楀彛鏍囬 `Minecraft* 1.20.6`)銆傚畠涓庝袱鏉?
  **宸茬‘璁ゆ槸鏍囬鐣岄潰**鐨勬埅鍥?鎺у埗缁勩€?.21.8)鍚岀被:292 涓鑹叉《銆佹渶澶ф《鍙崰 11%銆佸惈 0.09% 杩戦粍鑹插儚绱?
  (鏍囬鐣岄潰鐨?splash 鏂囧瓧);鑰?1.21 鍗″湪鍔犺浇閬僵鏃舵姄鍒扮殑褰㈡€佹槸 87 涓《銆佹渶澶ф《鍗?84%銆佸潎鍊?`233,73,83`銆?
  鍒ゆ嵁閲岀殑 `Sound engine started` 涔熶负鐪?鈥斺€?1.21 鍗″湪閬僵鏃?*娌℃湁**杩欎竴琛屻€?

### 浜屻€佽繖鏉＄嚎涓?1.20.6 璧蜂笉鍐嶉渶瑕?鐨勪笢瑗?鏈疆鏄疄娴嬪埌鐨?

1. **杞借嵎鍛藉悕绌洪棿**:`MissingTargets` 鎵繖浠借浇鑽锋槸 **43918 鏉℃父鎴忔垚鍛樺紩鐢ㄣ€佸彧鏈?23 鏉″湪杩愯鏃堕噷鎵句笉鍒?*(0.05%),
   鑰屽悓涓€宸ュ叿鍦ㄥ悓涓€鍙版満鍣ㄤ笂鎵?**1.20.4** 鐨勮浇鑽锋槸 **43162 鏉￠噷 3178 鏉℃壘涓嶅埌**(7.4%)銆傛墍浠?1.20.4 鐨勮浇鑽锋槸
   SRG 鍚嶃€?.20.6 鐨勮浇鑽峰凡缁忔槸瀹樻柟鍚?鈥斺€?涓庢湰鏂囨。"1.20.6+ 鑷劧绌鸿浆"鐨勫垽鏂竴鑷?杩欐鏄噺鍑烘潵鐨?涓嶆槸鎺ㄧ殑銆?
   鐩存帴鍚庢灉:1.20.6 杩欎竴鏉＄嚎**涓嶉渶瑕?* `SrgNameTable` / `SrgRemap` 杩欎竴姝?1.20.4 闇€瑕?瑙佺鍥涜妭)銆?
2. **keep 璁″垝涓虹┖**:`PayloadDrift` 涓?1.20.6 鎻愬嚭鐨?`keep-runtime.proposed.txt` 鏄?0 琛?1.21 閭ｆ潯绾胯鎵嬪啓),
   鎺ュ彛璁″垝 13 琛屻€佽闂鍒?104 琛屻€佹垚鍛樻仮澶?285 鏉?/ 77 涓?donor銆乣reparent.txt` 1 琛屻€佽繍琛屾椂 stub 3 鏉°€?

### 涓夈€乣optionsof.txt` 鍦?1.20.6 涓婁篃纭疄鏄繀闇€鐨?瀹炴祴)

鎶婃父鎴忕洰褰曢噷鐨?`optionsof.txt` 绉昏蛋鍚庨噸璺?瀹㈡埛绔湪 OptiFine 鑷繁鐨?Options 鍔犺浇澶勬姏寮傚父(鏃ュ織灏鹃儴鏈夋爤),
`[OptiFine]` 琛屾暟鍙樻垚 233,鍗?*杩欎笉鏄竴娆″共鍑€杩愯**銆傛斁鍥炶鏂囦欢鍚庡悓涓€濂?jar 鍥炲埌 `STARTED`銆傛墍浠?鏌愪簺鏋勫缓鐨勭┖
娓告垙鐩綍浼氱偢"杩欐潯宸茬煡闄愬埗,鍦?1.20.6 / J1_pre18 涓婂悓鏍锋垚绔嬨€?

### 鍥涖€?.20.4:瑁呴厤鎴愬姛,鍚姩姝诲湪涓€澶?鍘熷洜宸插畾浣?

NeoForge `20.4.251`(ModLauncher 10銆丣ava 17)+ `OptiFine_1.20.4_HD_U_I7.jar`:瑁呴厤鏈韩璧伴€?杞借嵎 427 涓父鎴忕被銆?
鎴愬憳鎭㈠ 6009 鏉?/ 371 涓被銆佹帴鍙ｈ鍒?12 琛屻€佽闂鍒?1 琛屻€乻tub 228 鏉?+ 2035 鏉＄暀缁?loader),浣嗗惎鍔ㄥ湪
`net.minecraft.commands.BrigadierExceptions.<clinit>` 澶勬:

```
java.lang.NoSuchMethodError: 'net.minecraft.network.chat.MutableComponent net.minecraft.network.chat.Component.m_237115_(java.lang.String)'
```

杩欐槸**杞借嵎閲岀殑 SRG 寮曠敤娌℃敼鍚?*鐨勫吀鍨嬪舰鎬?绗簩鑺傞噺鍒扮殑 7.4% 灏辨槸瀹?銆?

**闅忓悗灏辨妸杩欎竴姝ュ仛浜?* 鈥斺€?杩欐槸鏈疆 1.20.4 鐨勮繘灞?浠?Forge maven 鍙栧埌 MCPConfig `1.20.4-20231207.112700`
鐨?`joined.tsrg`(娉ㄦ剰鐗堟湰涓?*涓嶆槸** NeoForm 鐨?`20240627.114801`,鎷垮悗鑰呭幓涓嬭浇鏄?404),鐢?rig 鐨?
`proguard-to-tsrg.ps1` 鎶?Mojang 鐨?`client-1.20.4-鈥?mappings.txt` 杞垚 obf鈫抩fficial 鐨?tsrg2(7787 绫?/
35 236 瀛楁 / 68 037 鏂规硶),鍐嶅**杞借嵎 jar 涓庨噸鎵撳寘鍚庣殑 OptiFine jar 鍚勮窇涓€娆?* `SrgRemap`:OptiFine 閭ｄ晶鏀逛簡
3462 涓柟娉曞悕涓?1400 涓瓧娈靛悕銆? 涓棤娉曡В鏋?杞借嵎閭ｄ晶鏈?252 涓瓧娈典笌 137 涓柟娉?琛ㄩ噷娌℃湁瀵瑰簲椤?銆?2 涓?鎴愬憳褰㈢姸
鍙樹簡"銆傞噸鏂扮粍瑁呭悗鍚姩,**涓婁竴鏉?`NoSuchMethodError` 娑堝け**,杞藉叆闃舵鎺ㄨ繘鍒颁笅涓€澶?

```
java.lang.IncompatibleClassChangeError: class net.minecraft.client.player.AbstractClientPlayer
  overrides final method net.minecraft.world.entity.Entity.getY()D
```

鐢?`javap` 閲忓埌鐨勫洓浠朵簨(鍏ㄩ儴鍦ㄦ湰鏈?,鍏朵腑绗?4 浠舵妸杩欐潯闃诲**鍦ㄦ垜浠殑 loader 涔嬪**涔熻瘉姝讳簡:

1. 杞借嵎閲岀殑 `AbstractClientPlayer` 澹版槑浜?**涓変釜** 瀵?final 鏂规硶鐨勯噸鍐?`m_20185_()` / `m_20186_()` /
   `m_20189_()`,鍗虫敼鍚嶅悗鐨?`getX()` / `getY()` / `getZ()`;杩欎笁琛屽湪**鏈敼鍚嶇殑杞借嵎閲屽氨鏈?*,鎵€浠ユ槸 OptiFine
   鑷繁琛ヤ竵甯︾殑鏂规硶,涓嶆槸鏀瑰悕閫犵殑(鏀瑰悕鏈韩鏄鐨?joined.tsrg 鏄?`tsrg2 obf srg id` 涓夊垪鏍煎紡,`blv` 瀵?
   `net/minecraft/src/C_507_`,Entity 鐨?`dr/dt` 鏂规硶姝ｆ槸 `m_20185_`/`m_20186_`,鑰屾垜鐨?obf鈫抩fficial 琛ㄧ粰鍑?
   `getX`/`getY` 鈥斺€?涓よ竟涓€鑷?銆?
2. 杩愯鏃剁殑 `AbstractClientPlayer` **涓€涓兘娌℃湁**(`getX/getY/getZ` 鍦?1.20.4 鍙湪 `Entity` 涓婂０鏄?;
3. 鑰?`Entity` 鐨勮繖涓変釜瀛樺彇鍣ㄥ湪 1.20.4 涓婃槸 **final**:鍘熺増娣锋穯 jar 鐨?`blv`(= Entity)鏄?`public final double
   dt();`,NeoForge 鐨?`neoforge-20.4.251-client.jar` 涓?`client-鈥?srg.jar` 涔熼兘鏄?`public final double getX/getY/getZ()`銆?
   椤哄甫閲忎簡 1.20.6 浣滀负瀵圭収:瀹冪殑 `Entity` **鍚屾牱**鏄?final,浣?**1.20.6 鐨勮浇鑽烽噷鏍规湰娌℃湁 `AbstractClientPlayer`
   杩欎釜绫?* 鈥斺€?杩欐鏄偅鏉＄嚎鑳借繃鐨勫師鍥犱箣涓€銆?
4. **鎶?loader 瀹屽叏鎺掗櫎鍦ㄥ鐨勯獙璇?*:浠庤浇鑽烽噷鍙栧嚭杩欎竴涓?class 鏂囦欢銆佹寜鐪熷疄绫诲悕鏀惧埌涓存椂鐩綍,鐢?`jshell`
   浠ャ€岃浇鑽?jar + `runtime-1.20.4.jar` + NeoForge universal銆嶄负 classpath 鐩存帴 `Class.forName`(闇€瑕?universal 鏄洜涓?
   鎺ュ彛 `IPlayerExtension` 鐢?loader 娉ㄥ叆)銆侸VM 鐨勫師璇濅笌娓告垙閲岄偅涓€琛?*閫愬瓧鐩稿悓**:
   `IncompatibleClassChangeError: class net.minecraft.client.player.AbstractClientPlayer overrides final method
   net.minecraft.world.entity.Entity.getY()D`銆備篃灏辨槸璇?**杩欎唤 OptiFine 杞借嵎鐨勮繖涓被,鍦?1.20.4 杩愯鏃朵笂鎸夊師鏍?
   灏辨槸涓嶅彲瀹氫箟鐨?*,涓庢垜浠殑 transformer銆乲eep 璁″垝銆乵ember restore 閮芥棤鍏炽€?

椤哄甫鎶?鎷挎湭淇敼鐨?OptiFine jar 鍋氬鐓?杩欐潯涔熻窇浜?1.20.4 涓?FML **鍚屾牱鎷掔粷**瀹?
(`InvalidLauncherSetupException: Invalid Services found OptiFine`),鎵€浠ラ偅鏉″鐓ц矾璧颁笉閫?涓婇潰鐨勭 4 浠舵墠鏄彲鐢ㄧ殑璇佹嵁銆?

**鍐嶅線涓嬮噺浜嗕竴灞?鎶婁竴涓湅浼煎悎鐞嗙殑瑙ｉ噴涔熷惁鎺変簡**(杩欎笁鏉″悓鏍烽兘鍦ㄦ湰鏈?:

- "OptiFine 鐨?1.20.4 杞借嵎鏄负 **Forge** 杩愯鏃惰€岀紪鐨勩€丗orge 鍘绘帀浜嗛偅涓変釜 `final`" 鈥斺€?**鍚︽帀**:鎶?Forge
  `1.20.4-49.0.50` 鐨?userdev 鎷変笅鏉?3 049 360 瀛楄妭),`patches/net/minecraft/world/entity/Entity.java.patch`
  644 琛岄噷**娌℃湁涓€澶?* `getX()` / `getY()` / `position()`;鎵€浠?Forge 鐨?Entity 鍚屾牱淇濈暀 final,杩欐潯瑙ｉ噴涓嶆垚绔嬨€?
- 涓変釜閲嶅啓涔?*涓嶆槸浠庡熀绫荤户鎵挎潵鐨?*:鍘熺増娣锋穯鐨?`AbstractClientPlayer`(`fsg`)閲?`dr()/dt()/du()` 涓€涓兘娌℃湁 鈥斺€?
  涔熷氨鏄瀹冧滑鏄?OptiFine 鎵撹ˉ涓佷箣鍚?*鍑虹幇鍦ㄨ浇鑽疯繖涓€浠介噷**鐨勩€?
- 鎹竴涓?1.20.4 鐨?OptiFine 鏋勫缓涔熶笉瑙ｅ喅闂:涓や釜鏋勫缓**閮?*甯?
  `patch/srg/net/minecraft/client/player/AbstractClientPlayer.class.xdelta`(`I7` 4948 鏉¤ˉ涓侀」銆乣I8_pre4` 4956 鏉?
  涓や唤鏉＄洰琛ㄥ凡閫愪釜鏁拌繃)銆?
- **鏇存(鍚屼竴杞唴鑷煡)**:涓嬮潰杩欐潯 md5 瑙傚療**涓嶆瀯鎴愯瘉鎹?*,涓嶈褰撲綔"鍩虹被鍠傞敊浜?鐨勪緷鎹€傛妸涓ゅ琛ヤ竵鐨?md5 閮芥媺鍑烘潵姣旇繃:
  OptiFine 鐨?`patch/notch/` 閭ｄ竴濂?鍗冲杺鍘熺増娣锋穯 jar 鏃惰蛋鐨勯偅涓€濂?瀵?**1.20.4 鍜?1.20.6 涓ょ増鍚勬娊 400 鏉?*鍋氬鐓?
  **鍖归厤 0 鏉°€佷笉鍖归厤 400 鏉?*(1.20.4 渚?`fns.class` 鏈熸湜 `0be75f7e鈥銆佸疄闄?`55120f89鈥;1.20.6 渚?`ggf.class`
  鏈熸湜 `1c312056鈥銆佸疄闄?`3736f2a3鈥)銆?.20.6 閭ｆ潯绾挎槸**鑳借窇閫氱殑**,鎵€浠?`.md5` 鏄剧劧涓嶆槸"鍘熷 class 鏂囦欢鐨勫搱甯?
  (鏇村儚鏄?OptiFine 鑷繁瀵瑰熀绫诲仛褰掍竴鍖栦箣鍚庣殑鏍￠獙鍊?鎴栬€呭湪 `Patcher.process` 杩欐潯璺笂鏍规湰涓嶇敓鏁?銆備篃灏辨槸璇?
  `OptifinePipeline` 鍠傜粰 `optifine.Patcher` 鐨勫熀绫?*娌℃湁璇佹嵁鏈夐棶棰?*,鎴戝厛鍓嶉偅鍙?杩欎唤鍩虹被鍦ㄧ鐩樹笂娌℃湁瀵瑰簲鐗?
  鍙槸璇銆?
- 鍙﹀鎶?OptiFine 鏄嬁鍝竴濂楄ˉ涓佹墦鎴戜滑鐨勮緭鍏?涔熺‘璁や簡:jar 閲?`patch/notch/` 涓?`patch/srg/` **涓ゅ閮藉湪**
  (1.20.4 鐨?`AbstractClientPlayer` 灏辨槸 `patch/notch/fsg.class.xdelta` 5431 瀛楄妭 + `patch/srg/鈥 7997 瀛楄妭鍚勪竴浠?,
  鑰屾垜浠杺杩涘幓鐨勬槸**鍘熺増娣锋穯 jar**,鎵€浠ヨ蛋鐨勬槸 notch 閭ｄ竴濂?鈥斺€?杩欎竴濂楀湪 `optifine.Patcher.process` 閲屽拰 OptiFine
  鑷繁鐨勫畨瑁呭櫒鐢ㄧ殑鏄悓涓€浠戒唬鐮併€佸悓鏍风殑涓や釜鍏ュ弬銆?
- 鍥犳缁撹鏀剁獎鎴愪竴鍙?**杞借嵎杩欎竴浠?`AbstractClientPlayer` 灏辨槸 OptiFine 閭ｄ唤琛ヤ竵鐨勪骇鐗?*(涓嶆槸鎴戜滑鏄犲皠閿欑殑),
  鑰屽畠鍦?1.20.4 杩愯鏃朵笂涓嶅彲瀹氫箟銆備慨娉曞彧鑳藉湪鎴戜滑杩欎竴渚?瑙佷笅)銆?

### 浜斻€佽竟鐣?

- 鏈疆**瀹炴祴閫氳繃**鐨勬槸 1.20.6(鍒ゆ嵁鍥涢」閲屼笁椤逛笌璁板綍閫愬瓧涓€鑷?`[OptiFine]` 琛屾暟 +9 宸插瀹炲啓鏄?;**1.20.4 鏈€氳繃**,
  SRG 鏀瑰悕鍋氬畬涔嬪悗鍋滃湪 `AbstractClientPlayer overrides final method Entity.getY()` 鈥斺€?骞朵笖宸茬敤鑴辩 loader 鐨?
  `jshell` 娴嬭瘯璇佹槑杩欎唤杞借嵎鐨勯偅涓被鍦?1.20.4 杩愯鏃朵笂鎸夊師鏍蜂笉鍙畾涔?1.20.1 / 1.20.2 杩欎竴杞?*娌℃湁璺?*銆?
- 1.21.x 绾挎湰杞湪鍙︿竴浠借褰曢噷鎺ㄨ繘(1.21 鈥?1.21.8 鍏潯绾垮疄娴嬨€?.21 鐨勮祫婧愰噸杞介樆濉炰粛鏈В),26.x 绾胯繖涓€杞病纰般€?
- 涓轰簡鑳介噸璺?rig 琚敼鍔ㄧ殑鍦版柟(鍏ㄩ儴鍦?rig 鍐呫€佷笉鍦ㄤ粨搴撻噷):`add-line.ps1` 澧炲姞浜?`-ModLauncher`(1.20.x 鐢?
  `-Pmodlauncher` 鑰屼笉鏄?`-Pmountpoint`)銆乣-TargetJavaVersion`(1.20.6 蹇呴』 `21`,鍚﹀垯 Gradle 鎶?
  "No matching variant of net.neoforged:neoform:1.20.6-鈥?compatible with Java 17")銆佸畨瑁呭櫒鍧愭爣鐨?`-InstallerArtifact`
  (1.20.1 鏄?`forge`)銆?Gradle 鏋勫缓鎴愬姛鎵嶇畻浜х墿"鐨勬鏌?涓€娆″け璐ョ殑鏋勫缓浼氱暀涓嬪悓鍚嶆棫 jar,鐢ㄥ畠鍚姩绛変簬娴嬮敊瀵硅薄),
  浠ュ強**娌℃湁** SRG 琛ㄧ殑绾夸笉鍐嶇‖濉?`-SrgTableFile`;鍙︽柊澧?`get-optifine.ps1`(OptiFine 鐨勪笅杞借鍏堢敤 adloadx 椤?
  鎹㈠彇涓€娆℃€?token,鍐嶆墦 `downloadx`)銆?
- **娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆?


## 2026-09-19/20:鏁翠釜浼氳瘽鐨勬敹鍙?璇昏繖涓€鑺傚嵆鍙煡閬撶幇鐘?

### 涓€銆佹湰鏈哄疄娴?*閫氳繃鏂囨。鍒ゆ嵁**鐨勭嚎(鍏?10 鏉?

鍒ゆ嵁 = VERDICT: STARTED + Setting user + 澹伴煶寮曟搸 + 鏈杩愯 0 宕╂簝鎶ュ憡(**澶栧姞** stderr 涓庤褰曞鐓?銆?

| 绾?| 鍒嗘敮/璁板綍浣嶇疆 | stderr 瀵圭収 | [OptiFine] 琛屾暟(璁板綍 vs 瀹炴祴) | 鏍囬鐣岄潰 |
|---|---|---|---|---|
| 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 | 1.21.x 鍒嗘敮鐨?README / MATRIX | 涓庤褰曚竴鑷存垨鏋佽繎 | +9 / 0 / 0 / +7 / +7 / +7 | 1.21.8 鏈夋埅鍥?|
| **1.20.6** | 鏈枃浠躲€屾櫄闂淬€嶄竴鑺?| **0 瀛楄妭 = 璁板綍** | 222 vs 231 | 鉁?鎴浘纭 |
| **1.20.4** | 绗簩鍗佷簩 / 浜屽崄鍏妭 | **14 481 = 璁板綍**(涓ゆ閫愬瓧鑺傜浉鍚? | 241 vs 730(鍚堝苟鍊? | 鉁?setScreen 纭 |
| **1.20.2** | 绗笁鍗佷簩鑺?| **14 625 = 璁板綍** | 239 vs 494(鍚堝苟鍊? | 鉁?setScreen 纭 |
| **1.20.1** | 绗笁鍗佸叓 / 鍥涘崄鑺?| **0 瀛楄妭,璁板綍 27** 鉁?| 157 vs 310(鍚堝苟鍊? | 鏈洿鎺ユ祴鍒?|

### 浜屻€佹湭瀹炴祴鐨?5 鏉＄嚎,浠ュ強鍚勮嚜**纭垏鐨?*鍗＄偣

| 绾?| 鐘舵€?| 鍗＄偣(閮芥槸瀹炴祴鍑烘潵鐨?涓嶆槸鐚滅殑) |
|---|---|---|
| 1.21 | 鍒ゆ嵁鏈繃 | 璧勬簮閲嶈浇璧板埌 28 涓?listener銆?7 涓埌杈炬爡鏍忓悗涓嶅啀鍓嶈繘;Sound engine started 缂哄け銆佺敾闈㈠仠鍦ㄥ姞杞介伄缃?|
| 1.21.9 / 1.21.10 / 1.21.11 | 鏈繃 | **鍚姩鏂瑰紡宸茶В鍐?*(rig 鐨?launch-fml10.ps1,鏃?mod 瀵圭収 STARTED)銆?*杞借嵎鎶曢€掑凡瑙ｅ喅**(fml10 鐨?ClassProcessor + locator 鏈嶅姟 + srg/** 鎴愬搧绫?鏃ュ織閲?517 涓被琚€愪釜瀹夎);**鍞竴鍗＄偣**鏄?OptifinePayloadClassProcessor.transform 鈫?copy(finished, node):鍙瀹冪湡鐨勮鐩栫被,FML 10 灏?Closing FML Loader(鏃犲紓甯搞€乻tderr 0),涓や釜涓€琛屽疄楠屽凡鎶婅寖鍥撮拤姝诲湪杩欎竴姝?|
| 26.1.2 | 鏈窇 | 闇€瑕?26.x 鍒嗘敮鐨勯偅濂?涓?1.21.9 鍚屼竴褰㈢姸),鍙鐢ㄤ笂闈㈢殑 FML 10 缁撹 |

### 涓夈€佽繖涓€浼氳瘽鏂板銆佷笖**閫氱敤**鐨勪笢瑗?

**浠撳簱(鏈垎鏀?**:drop-members.txt 璁″垝銆乲eep-runtime.txt 鐨勬暣绫讳繚鐣欏舰寮忋€?*璁块棶璁″垝**鍦?1.20.x loader 閲岀殑搴旂敤銆?
鍚岀被鎺ュ彛鎹㈣鐨勬斁寮€(-Doptifineoforge.strictInterfaceKeep=true 鍙€€鍥?銆佷笁涓皟璇曞紑鍏?
-Doptifineoforge.{traceInit,traceCrash,traceScreen,dump,skipPayload}銆?

**rig(涓嶅湪浠撳簱閲?**:prepare-line.ps1 鏀寔"鍏?SRG鈫掑畼鏂瑰悕鍐嶇敓鎴愯鍒?(-SrgMappings/-ObfOfficial)銆?
dd-line.ps1 鐨?-ModLauncher/-TargetJavaVersion/-InstallerArtifact/-InstallOnly 涓?閲嶆墦鍖?jar 鑷姩鏀瑰悕"銆?
proguard-to-tsrg.ps1(淇簡宓屽绫讳笌 obf 鍒楁枩鏉?銆?
atives-for.ps1銆乬et-optifine.ps1銆乴aunch.ps1(RIG_EXTRA_JVM)銆?
**launch-fml10.ps1(FML 10 鐨勫惎鍔ㄨ矾寰?**銆?

### 鍥涖€佷粛鐒?*鏈煡鏄?*鐨?

- [OptiFine] 琛屾暟涓庤褰曠殑宸紓(1.20.6 +9銆?.20.2 +8銆?.20.4 宸緱鏈€澶氥€?.20.1 鏄?**-2**),鍥涙潯绾块兘鏈?**鍘熷洜鏈煡**;
- 1.20.1 璁板綍閲岄偅 27 瀛楄妭 stderr 鐨勬潵婧?宸茬敤涓や釜 OptiFine 鏋勫缓瀵圭収,**璇佹槑涓庢瀯寤烘棤鍏?*);
- 1.20.4 鐨勬爣棰樼晫闈㈠彧鏈?setScreen 璇佹嵁,**鍍忕礌鎶撳抚鏄繃鏈熷抚**(绐楀彛鏈仛鐒︽椂涓嶉噸缁?涓ゆ鎶撳抚缁熻瀹屽叏鐩稿悓)銆?

### 浜斻€佹病鏈夊仛鐨勪簨

**娌℃湁鍙戝竷浠讳綍涓滆タ**;宸插彂甯冪殑 jar 娌℃湁閲嶅缓銆傛湰浼氳瘽鐨勪骇鐗╁叏閮ㄦ槸鏂囨。銆乴oader 鑳藉姏涓?rig 宸ュ叿銆?
## 鍏夊奖鍖呭疄娴?2026-09-20,1.20.4 绾?Java 17)

鐢ㄦ埛瑕佹眰涓嬭浇瀹夎澶氫釜鍏夊奖鍖呮祴璇曘€備笁涓寘浠?Modrinth 鍙栧埌 rig 鐨?`shaderpacks-1.20.4\`,
鐢?rig 渚ц剼鏈?`test-shaderpack.ps1 -Launcher modlauncher -JavaHome <jdk-17>` 閫愬寘璺戜竴娆＄湡鏈?

| 鍏夊奖鍖?| 鍥涢」鍒ゆ嵁 | 鍖呮槸鍚﹀姞杞?| `[OptiFine]` 琛?| `[Shaders]` 琛?| GLSL 閿欒 | GL 閿欒 | stderr |
|---|---|---|---|---|---|---|---|
| Complementary Reimagined r5.9.3 | 鍏ㄤ腑 | 鏄?| 810 | 141 | 0 | 0 | 14 481 瀛楄妭 |
| BSL v10.1.5 | 鍏ㄤ腑 | 鏄?| 448 | 82 | 0 | 0 | 14 481 瀛楄妭 |
| MakeUp UltraFast 9.5e | 鍏ㄤ腑 | 鏄?| 3497 | 86 | 0 | 0 | 14 481 瀛楄妭 |

"鍥涢」鍒ゆ嵁"= `VERDICT: STARTED` + `Setting user` + `Sound engine started` + 鏈杩愯鏃犲穿婧冩姤鍛?
"鍖呮槸鍚﹀姞杞?鍙栬嚜鏃ュ織閲?OptiFine 鑷繁鐨?`[Shaders] Loaded shaderpack: <鍖呭悕>`銆?
**stderr 鐨?14 481 瀛楄妭涓庢湰绾挎鍓嶈褰曠殑鍊间竴鑷?*(閭ｆ槸杩欎竴绾挎棤 mod 瀵圭収璺戠殑鏁板瓧),涓夋閮戒竴鏍?鈥斺€?涔熷氨鏄
涓変釜鍏夊奖鍖呴兘娌℃湁寰€ stderr 澶氬啓涓€涓瓧鑺傘€?

閫変腑鍝釜鍖呯敱 `<娓告垙鐩綍>\optionsshaders.txt` 鐨?`shaderPack=` 鍐冲畾(閿悕涓庢枃浠跺悕閮藉彇鑷?OptiFine 鑷繁鐨勭被:
`EnumShaderOption.SHADER_PACK.getPropertyKey()` 涓?`Shaders` 閲岀殑 `optionsshaders.txt` 甯搁噺)銆?

## 2026-09-20:15 鏉＄嚎鍏ㄩ儴杩囧垽鎹€佷袱涓姞杞藉櫒缂洪櫡缁撴竻,浠ュ強"杩涗笘鐣?杩欎欢浜嬬殑濡傚疄杈圭晫

### 涓€銆侀獙鏀惰〃琛ラ綈:15 鏉＄嚎鍏ㄩ儴杩囧洓椤瑰垽鎹?

鍒ゆ嵁 = `VERDICT: STARTED` + `Setting user` + `Sound engine started` + 鏈杩愯鏃犲穿婧冩姤鍛?**澶栧姞** stderr 涓庤褰曞鐓с€?
杩欎竴杞妸 15 鏉＄嚎鍦?*鍚屼竴澶?*閲嶈窇浜嗕竴閬?浠婂ぉ鐨勬暟鎹湪 rig 鐨?`logs` 鐩綍閲?11 鏉″湪
`retest-11lines-postfix.txt`,1.20.x 鐨勪袱鏉″湪 `retest-120x-clean2.txt`,1.20.1 涓?1.20.6 涓ゆ潯鍚勮嚜鐨?
`retest-1.20.?.out.log` 鏄悓涓€鎵广€?

| 绾?| 鐗堟湰 / NeoForge | 鍥涢」鍒ゆ嵁 | `[OptiFine]` 琛?瀹炴祴) | stderr 瀹炴祴 | stderr 璁板綍 | 瀵圭収 |
|---|---|---|---|---|---|---|
| 1.20.x | 1.20.1 / 47.1.106 | 鍏ㄤ腑 | 155 | 0 瀛楄妭 | 27 瀛楄妭 | **鏃╁凡璁板綍鐨勫樊寮?*(瑙佺涓夊崄鍏?/ 鍥涘崄鑺?,鏈疆娌℃湁鍙樺寲 |
| 1.20.x | 1.20.2 / 20.2.88 | 鍏ㄤ腑 | 247 | **14 625** | 14 625 | 涓€鑷?|
| 1.20.x | 1.20.4 / 20.4.251 | 鍏ㄤ腑 | 365 | **14 481** | 14 481 | 涓€鑷?|
| 1.20.x | 1.20.6 / 20.6.141 | 鍏ㄤ腑 | 231 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21 / 21.0.167 | 鍏ㄤ腑 | 377 | **14 141** | 14 141 | 涓€鑷?鍚屼竴鏉?4 鏉?`NoClassDefFoundError` 缂洪櫡) |
| 1.21.x | 1.21.1 / 21.1.250 | 鍏ㄤ腑 | 232 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.3 / 21.3.97 | 鍏ㄤ腑 | 225 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.4 / 21.4.149 | 鍏ㄤ腑 | 232 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.6 / 21.6.20-beta | 鍏ㄤ腑 | 347 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?**涓嶅惈鍚敤鍏夊奖鍖?*,涓婃父缂洪櫡) |
| 1.21.x | 1.21.7 / 21.7.25-beta | 鍏ㄤ腑 | 347 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?**涓嶅惈鍚敤鍏夊奖鍖?*,涓婃父缂洪櫡) |
| 1.21.x | 1.21.8 / 21.8.54 | 鍏ㄤ腑 | 344 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.9 / 21.9.16-beta | 鍏ㄤ腑 | 365 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.10 / 21.10.64 | 鍏ㄤ腑 | 356 | 0 瀛楄妭 | 0 瀛楄妭 | 涓€鑷?|
| 1.21.x | 1.21.11 / 21.11.45 | 鍏ㄤ腑 | 273 | 107 瀛楄妭 | 107 瀛楄妭 | 涓€鑷?|
| 26.x | 26.1.2 / 26.1.2.109 | 鍏ㄤ腑 | 352 | **104 瀛楄妭** | 107 瀛楄妭 | **浠婂ぉ鍞竴鐨勫樊寮?瑙佷笅** |

**26.1.2 閭?104 vs 107 璁版垚涓€澶勫樊寮?涓嶆姌杩?stderr 涓€鑷?**:宸殑灏辨槸**鍚屼竴琛?log4j 鐨勬椂闂存埑浣嶆暟涓嶅悓**
(涓嶆槸灏戜簡涓€琛屻€佷篃涓嶆槸鎹簡鍐呭),鎵€浠ュ畠涓嶆槸缂洪櫡,浣嗕篃涓嶈兘鍐欐垚"涓庤褰曠浉鍚? 鈥斺€?璁板綍閲屽畠鏄?107,浠婂ぉ閲忓埌 104銆?
鎸夊悓涓€鍙ｅ緞,闈為浂鐨?stderr 涓€鍏卞彧鏈変簲涓€?`14 625`(1.20.2)銆乣14 481`(1.20.4)銆乣14 141`(1.21)銆?
`107`(1.21.11)銆乣104`(26.1.2),鍏朵綑鍏ㄩ儴鏄?0 瀛楄妭銆?

### 浜屻€佽繖涓€杞粨娓呯殑涓や釜鍔犺浇鍣ㄧ己闄?閮藉湪鏈垎鏀?

**(a)`356c427`:鍔犺浇鍣ㄤ粠鏉ユ病鏈夎杩?`optifineoforge/runtime-interfaces.txt`銆?*
鍚庢灉鏄?涓€鏃︽妸 OptiFine 閭ｄ唤 `BlockState` 瑁呬笂,杩愯鏈熸敞鍏ヨ繘鍘荤殑 `IBlockStateExtension` 灏?*涓簡**,鑰?
`canSustainPlant` 鍦ㄩ偅涓帴鍙ｄ笂鏄?`public default`;浜庢槸**寤哄瓨妗?*鏃朵笘浠ｇ敓鎴愮洿鎺ユ鍦?
`NoSuchMethodError: BlockState.canSustainPlant(...)`,`BushBlock.canSurvive` 鎶涘嚭鏉ョ殑銆傛妸杩欎竴姝ユ帴涓婁箣鍚?
鍚屼竴涓笘鐣屾祴璇曢噷**閲嶅缓鐨?1.20.2 jar 浠?1 涓柊宕╂簝鎶ュ憡鍙樻垚 0 涓?*銆?

**(b)`41b4bb4`:绂荤嚎 SRG鈫掑畼鏂瑰悕鐨勬敼鍐欐紡浜?102 涓垚鍛樸€?*
鏍瑰洜涓嶅湪琛ㄦ湰韬?鑰屽湪**杩炴帴閿?*:`SrgMemberMap` 鎷挎弿杩扮鍋氳繛鎺ラ敭鏃?*閫愬瓧鑺?*姣旇緝,鑰?rig 鐨?
`proguard-to-tsrg.ps1` 鎶?class-map 鐨?*鍊?*鍐欐垚浜嗙偣鍙疯€屼笉鏄枩鏉?鈥斺€?
`Lcom.mojang.blaze3d.systems.RenderSystem$a;` 瀵?`Lcom/mojang/blaze3d/systems/RenderSystem$a;`,
宸殑**鍙槸涓€涓?0x2f / 0x2e 瀛楄妭**銆備袱杈瑰洜姝ゅ涓嶄笂,102 涓垚鍛?*闈欓粯**婕忔敼,琛ㄧ幇鏄疄浣撲竴 tick 灏?
`NoSuchMethodError: Level.m_7654_()`銆?

淇畬鐨勬牳瀵?`javap` + 鎸囦护绾ф壂鎻?:

| 鏍稿椤?| 缁撴灉 |
|---|---|
| `Level.getServer` | 鍦?|
| `ServerLevel.getServer` | 鍦?|
| `GameRules$BooleanValue.set` | 鍦?|
| `uploadIndexBuffer` | 鍦?|
| 娈嬬暀鐨?`m_*` 鎸囦护寮曠敤 | **0** |
| 娈嬬暀璁℃暟 | **3 LIVE 鈫?0** |

### 涓夈€佹洿姝?閭ｆ潯 SRG 瀹舵棌**涓嶅奖鍝?1.20.1**

**1.20.1 涓嶅彈 (b) 杩欎竴绫诲奖鍝?*:瀹冪殑**杩愯鏈熻嚜宸卞氨澹版槑** `m_7654_()`,鎵€浠ュ湪閭ｄ竴鐗堜笂杞借嵎閲岀殑 SRG 鍚?
**灏辨槸姝ｇ‘鐨勫悕瀛?*,鏀瑰啓鍣?*鎸夎璁℃嫆缁?*閭ｄ竴琛屻€傛洿鏃╅偅娆?*鎸夊瓧绗︿覆鎵弿**璁颁笅鐨?1.20.1 鏉＄洰鏄?*璇姤** 鈥斺€?
"杞借嵎閲屽嚭鐜颁簡 `m_` 寮€澶寸殑鍚嶅瓧"杩欎欢浜嬪湪 1.20.1 涓婁笉绛変簬缂洪櫡銆?

### 鍥涖€佸瀹炲啓涓嬬殑杈圭晫:杩欐潯 rig 涓婃病鏈変换浣曚竴鏉＄嚎鐨勫鎴风鐪熸杩涜繃涓栫晫

杩欎竴鐐硅**鍗曠嫭鍐欐竻妤?*,鍥犱负瀹冨喅瀹氭湰鏂囦欢閲屽緢澶氬彞瀛愯鎬庝箞璇?

* 灞忓箷杩借釜(鏈垎鏀姞杞藉櫒鐨?`-Doptifineoforge.traceScreen=true`)浼氭墦鍗版瘡涓€娆?`Minecraft.setScreen`銆?
  **quick play 鍔犲叆**鐨勯偅鏉″簭鍒?*鍋滃湪 `LevelLoadingScreen`,鍚庨潰浠€涔堥兘娌℃湁**;
* `PlayerList` 閲屽惈 `logged in with entity id` 杩欎釜瀛楃涓?鑰?*鏁翠釜 rig 鐨勪换浣曟棩蹇楅噷閮芥病鏈夊嚭鐜拌繃瀹?*;
* 1.20.6 閭ｆ杩愯鍋滃湪**閰嶇疆闃舵**,鎶涚殑鏄?`NullPointerException ... ConfigSync.syncConfigs`
  (`configData` 涓?null,`ModConfig.getFullPath`)銆?

鎵€浠ユ湰鏂囦欢(浠ュ強鏇存棭鐨勬潯鐩?鍑℃槸鍐?涓栫晫鍔犺浇鎴愬姛"鐨勫湴鏂?鎰忔€濋兘鏄?*闆嗘垚鏈嶅姟鍣ㄦ妸涓栫晫鐢熸垚鍑烘潵浜?*:
涓栫晫鏍囪銆乺egion 鏂囦欢涓庤鏀瑰啓鐨?`level.dat` **鍏ㄦ槸鏈嶅姟鍣ㄤ晶**鐨勪俊鍙?涓嶆槸"瀹㈡埛绔繘浜嗕笘鐣?鐨勮瘉鎹€?

杩欎篃灏辨剰鍛崇潃 **OptiFine 鐨?FXAA 鐜板湪杩樻病娉曢噺**:瀹冪殑鍚庡鐞嗛摼鍙湪**瀛樺湪鍏冲崱**鐨勯偅鏉℃覆鏌撳垎鏀噷璺戙€?
鎵€浠?FXAA 鍒扮洰鍓嶄负姝㈢殑缁撴灉鏄?**INCONCLUSIVE**,鍘熷洜姝ｆ槸杩欎竴鏉?鈥斺€?涓嶆槸"娌℃祴",鏄?*褰撳墠鍒ゆ嵁涓嬫祴涓嶄簡**銆?

### 浜斻€?026-09-21:瀹㈡埛绔涓€娆＄湡鐨勮繘浜嗕笘鐣?1.20.6),浠ュ強闅忎箣鏆撮湶鐨勪袱浠舵柊浜?

#### 1. 涓婁竴鑺?娌℃湁浠讳綍涓€鏉＄嚎鐨勫鎴风鐪熸杩涜繃涓栫晫"**宸茬粡杩囨湡**

1.20.6,jar `jars-1.20.6\OptifiNeoforge-1.0.0+mc1.20.6-registered.jar` 1744373 瀛楄妭
(SHA-256 `AAEC8AF69B7C03A08DA1B9A206F1E15E38253E8801BF57CF94E797F73E60B506`,鍚湰鑺傜 2 鏉＄殑淇):

```
07:00:45.663 [Server thread/INFO] PlayerList: Dev[local:E:f8ce5789] logged in with entity id 9 at (10.5, 87.0, -5.5)
07:00:45.684 [Server thread/INFO] MinecraftServer: Dev joined the game
07:00:46.108 [Render thread/INFO] OptifiNeoforge: Replaced 鈥enderChunkRegion with OptiFine's patched version (7 fields, 16 methods)
07:00:46.110 [Render thread/INFO] OptifiNeoforge: Initialised 2 restored fields in 鈥enderChunkRegion
```

鍚屼竴 jar 涓婂彟涓€娆¤繍琛?07:09:48 杩涘叆銆?7:14:22 浠嶅湪鍑哄抚,杩炵画娓叉煋绾?**4.7 鍒嗛挓**;涓ゆ杩愯閲?
**`VerifyError` 鍑虹幇 0 娆?*銆傛墍浠?杩涗笘鐣?鐜板湪鏈?*瀹㈡埛绔晶**璇佹嵁:绐楀彛鏍囬
`Minecraft NeoForge* 1.20.6 - Singleplayer`銆乣saves\RigWorld\session.lock` 琚寔鏈夈€乣player joined: yes`,
浠ュ強**鍍忕礌绾ч潤姝㈢殑鎴浘**(鍚屼竴閰嶇疆涓嬬浉闅?15 s 涓?30 s 鐨勪笁甯?0.0% 鍐呴儴鍍忕礌鍙樺寲)銆?

#### 2. 棣栧抚宕╂簝鐨勬垚鍥犲啓姝?鎺ユ敹鑰呬笉鏄?缂?,鏄**鍏辩敤**

宕╂簝鎶ュ憡鑷繁鐨勫瓧鑺傜爜 `2a2b 1c1d 1904 0101 03b7 001a 2ab8 001e b800 21b1` 瑙ｅ嚭鏉ユ槸
`invokespecial`(9) 鈫?**`aload_0`(12)** 鈫?`invokestatic`(13) 鈫?`invokestatic`(16) 鈫?`return`(19),
鎶ラ敊鍦?**@16 绗簩涓皟鐢?*:`MemberRestoreTransformer` 姣忎釜 `RETURN` 鍓嶅彧鏀?*涓€涓?* `aload_0`,鐒跺悗**姣忎釜**
寰呭垵濮嬪寲瀛楁鏀句竴涓?`INVOKESTATIC`;`RenderChunkRegion` 鐨?4 鍙傛瀯閫犺**涓や釜**,浜庢槸绗竴涓皟鐢ㄥ悆鎺変簡鍞竴鐨?
鎺ユ敹鑰呫€傝嫢鐪熺殑娌℃湁鎺ユ敹鑰?鎶ラ敊浼氳惤鍦?@13銆?

淇硶涓庢牳瀵?鏀逛负**姣忔璋冪敤鍚勬斁涓€涓?`aload_0`**(骞朵笖姣忔 `RETURN` 鐢?*鏂扮殑** `InsnList`),鍐嶅姞涓€鏉＄湡瀹炵殑
鏍堟晥鏋滄帴鍙楄鍒?鈥斺€?涓嶆弧瓒冲氨**璁版棩蹇椼€佽瀛楁鍋滃湪榛樿鍊?*,鑰屼笉鏄氦浠樹竴涓姞杞藉嵆琚獙璇佸櫒鎷掔粷鐨勭被銆?
绂荤嚎娓呭崟(瀵?`member-restores.txt` 閲屽叏閮?76 涓被璺?*鐪熷疄**杞崲鍣?:淇鍓?5 涓敞鍏ヨ皟鐢?2 涓被,
鍏朵腑 **1 涓棤鎺ユ敹鑰?*;淇鍚?**0 涓?*銆傜嫭绔嬬浜岄獙璇佸櫒(瀵?*浜や粯瀛楄妭**鍋?ASM `BasicVerifier` 鏁版嵁娴?:
淇鍓嶅悓涓€鏋勯€犱笂鎶?2 澶?淇鍚?76 涓被 0 澶?鏁村彧 jar 612 涓被 0 澶勩€?

**杩欐潯璺緞鍦?1.20.1/1.20.2/1.20.4 涓婃槸浼戠湢鐨?*:瀹冭姹傚悓涓€涓被涓婃湁 鈮? 涓疄渚嬪垵濮嬪寲鍣?鍙湁 1.20.6 鏈?
(`RenderChunkRegion` 鐨?`posFrom` + `modelDataSnapshot`)銆傞偅涓夋潯绾块噺鍒扮殑閮芥槸 0 涓棤鎺ユ敹鑰呰皟鐢ㄣ€?

#### 3. 鏂扮己闄蜂竴:鏀诲嚮/鐮村潖鏂瑰潡鍗冲穿(杞借嵎鐨?shim 搴旂瓟 null)

```
java.lang.NullPointerException: Cannot invoke "鈥ClientBlockExtensions.addHitEffects(鈥?" because the return
    value of "鈥ClientBlockExtensions.of(BlockState)" is null
  at ParticleEngine.addBlockHitEffects(ParticleEngine.java:823) <- Minecraft.continueAttack (1715) <- handleKeybinds (2107)
```

閲忓埌鐨勪簨瀹?浜や粯鐨?`optifineoforge/patched/鈥?ParticleEngine.class`(59210 瀛楄妭)鍙湪**涓ゅ**寮曠敤 Forge 鍖呭悕鐨?
`IClientBlockExtensions`(`destroy` 涓?`addBlockHitEffects`),鑰?jar 閲岄偅涓帴鍙ｆ槸 shim,`of(BlockState)` 灏辨槸
`aconst_null; areturn`(668 瀛楄妭);**杩愯鏈熻嚜宸辩殑** `ParticleEngine` 鏃㈡病鏈?`addBlockHitEffects`,涔熶笉寮曠敤浠讳竴
鍖呯殑鎵╁睍鎺ュ彛 鈥斺€?鎵€浠ヨ繖涓€浠?**keep plan 淇笉浜?*(娌℃湁杩愯鏈熸柟娉曞彲淇?銆?*灏氭湭淇?*,瀹冩槸鍙戝竷闃诲椤?浠讳綍鐜╁
鏀诲嚮鏂瑰潡閮戒細鎾炰笂,鑰屼笖瀹冧細鍦ㄤ换鎰忔椂鍒绘墦姝讳竴娆℃埅鍥捐繍琛?涓嬮潰 FXAA 鐨勭涓€娆￠厤瀵瑰氨鏄繖涔堝簾鎺夌殑)銆?

#### 4. 鏂扮己闄蜂簩(rig 鑷繁鐨?:灞忓箷杩借釜浼氬湪杩涗笘鐣屽悗绾?1 绉掓墦姝诲鎴风

```
ReportedException: Ticking screen <- Screen.wrapScreenError <- ReceivingLevelScreen.tick -> onClose
  -> ClientHooks.popGuiLayer -> Minecraft.setScreen -> NullPointerException:
     Cannot invoke "Object.getClass()" because "<parameter1>" is null
```

`PatchedClassTransformer.traceScreen`(绾?955 琛?鍦?`Minecraft.setScreen` 澶撮儴鎻掑叆鐨勬墦鍗?
(`aload_1; Object.getClass(); Class.getName()`)瀵?`screen` **娌℃湁鍒ょ┖**,鑰?NeoForge 鐨?`popGuiLayer` 鍦ㄥ脊鎺?
鏈€鍚庝竴灞?GUI 鏃?*鍚堟硶鍦?*璋冪敤 `setScreen(null)` 鈥斺€?閭ｆ鏄?quick play 杩涗笘鐣屾椂 `ReceivingLevelScreen.onClose`
鍋氱殑浜?鍚屼竴娆¤繍琛岀殑 stderr 閲?`OPF-SCREEN 鈥eceivingLevelScreen` 姝ｅソ鍑虹幇涓ゆ)銆?*鎵€浠ユ湰鏂囦欢閲屾墍鏈?杩借釜鍋滃湪
`ReceivingLevelScreen`"鐨勮褰曢兘鏄?rig 鐨勭己闄?涓嶆槸 mod 鐨?*;淇硶鏄閭ｆ鎵撳嵃鍒ょ┖(`String.valueOf`),鍦ㄥ甫璇?
淇鐨?jar 閲嶅缓涔嬪墠,鎴浘杩愯涓€寰?*涓嶅甫**杩借釜銆?*灏氭湭淇?*銆?

#### 5. FXAA 绗竴娆℃湁浜嗕竴瀵?*鍙**鐨勫抚

缁嗚妭瑙?rig 鐨?`logs\fxaa-findings-2026-09-21.md`銆傞厤缃?`shaderPack=`銆乣ofAaLevel:0`銆乣ofClouds:3`銆?
涓栫晫鐢?`pin-save-state.ps1` 閽変綇,`optionsshaders.txt` 鐨?`antialiasingLevel` 鍙?0 涓?4,鍚勫彇涓夊抚(+0/+15/+30 s):

| 姣旇緝 | 鍦烘櫙宸紓 | 骞冲潎杈圭紭鑳介噺鍙樺寲 | 纭竟(姊害>48)鍙樺寲 | 鍒ゅ畾 |
|---|---|---|---|---|
| `=0` 杩愯鑷韩(1 vs 2) | **0.0%** | 0.0% | 0.0% | 甯у唴闈欐 |
| `=4` 杩愯鑷韩(1 vs 2/3) | **0.0%** | 0.0% | 0.0% | 甯у唴闈欐 |
| `=0` vs `=4`(鍚屼竴鏃跺埢,涓夊抚鍚勪竴娆? | 3.6% | **-0.6%**(8.6078鈫?.6594) | **-12.7%**(18711鈫?6334) | 涓绘寚鏍?NOT VISIBLE |
| 绗竴娆￠厤瀵?宸插簾) | 60.2% | -22.4% | -37.1% | INCONCLUSIVE(閭ｆ `=0` 杩愯鐨勫鎴风姝ｅ湪姝? |

璇氬疄璇绘硶:涓ゆ杩愯鍚勮嚜甯у唴**瀹屽叏闈欐**(杩欐槸浠ュ墠浠庢湭鏈夎繃鐨勫熀绾?,涓ゅ満鏅浉宸?3.6%(闂ㄦ 10%),鎵€浠ヨ繖涓€瀵?
**鍙互璇?*;浣嗕富鎸囨爣 -0.6% 鏃綆浜?2% 闂ㄦ銆佹柟鍚戣繕鐩稿弽,鍙湁"纭竟鍍忕礌鏁?涓€鑷村湴闄?12.7%銆傝€屼笖閭ｄ笁甯ф槸**鍚屼竴鐢婚潰
鐨勯噸澶?*,涓嶆槸涓変釜鐙珛鏍锋湰銆傛墍浠?*鏃笉鑳借"FXAA 纭鐢熸晥",涔熶笉鑳借"FXAA 鏃犳晥"**;瑕佽惤瀹?鍙渶鍐嶈窇涓€娆＄ǔ瀹氱殑
`=0` 杩愯,鎶?3.6% 鍙樻垚"閫愭鍣０"鐨勫疄娴嬪€笺€?

**琛ヨ(鍚屾棩 07:47 瀹炴祴):閭ｄ釜"鍐嶈窇涓€娆?娌℃湁缁欏嚭鍣０鍊?鑰屾槸缁欏嚭浜嗗彟涓€浠朵簨銆?* 绗簩娆?`=0` 杩愯
(`fxaa5-off`,鍚屼竴閽変綇鐨勪笘鐣屻€佸悓涓€閰嶇疆銆佸抚鍐呬緷鏃ч潤姝?317857 / 317916 / 318002 瀛楄妭)涓庡墠涓€娆＄殑甯?
**鐩稿樊 75%,涓夊抚鍑犱箮閫愬儚绱犱笉鍚?*,鑰屼笖甯уぇ灏忎笁鑰呯浉宸偓娈?`=0` 鏃╁厛 124 KB銆乣=4` 197 KB銆佽繖娆?318 KB)銆?
鍘熷洜:`pin-save-state.ps1` 閽夌殑鏄?*涓栫晫**(鏃堕棿銆佸ぉ姘斻€佸仠姝㈡棩鍑轰笌鍒锋€殑娓告垙瑙勫垯銆佷簯鍏抽棴),**涓嶉拤鐜╁鐨勪綅缃笌鏈濆悜**
鈥斺€?閭ｄ袱椤瑰湪 `level.dat` 閲?鑰屾瘡娆¤寮烘潃鐨勫鎴风閮戒細鎶婂畠浠啓鍥炲幓銆俙=4` 閭ｆ(07:14)涓庤繖娆?07:47)涔嬮棿杩樻湁浜旀
楠岃瘉杩愯杩涜繃閭ｄ釜涓栫晫,鎵€浠ョ浉鏈哄Э鍔垮凡缁忎笉鏄悓涓€涓€傜粨璁?`off3` vs `on4` 閭ｄ竴瀵逛粛鐒舵垚绔?鑳岄潬鑳屻€?.6% 鍦烘櫙宸紓),
浣?*"纭竟闄?12.7%"鐩墠鍙缓绔嬪湪涓€瀵瑰抚涓?涓嶆槸閲嶅閲忔祴**;瑕佹嬁鍣０鍊?寰楀厛鎶婂Э鍔夸篃閽変綇(`level.dat` 鐨?
Pos/Rotation 鍐欏叆,鎴栦竴鏉?teleport 鍛戒护),鎴栬€呬弗鏍艰儗闈犺儗鍦版垚瀵硅繍琛屻€?

#### 6. 鐙珛瀹¤:15 鏉＄嚎鐨?*瑁呰浇瀛楄妭鐮?*

rig 鏂板 `tools-src\StackAudit.java`(瀵?jar 閲屾瘡涓被鐨勬瘡涓柟娉曡窇 ASM `Analyzer` + `BasicVerifier`),
缁撴灉琛ㄥ湪 `logs\stackaudit-2026-09-21.md`銆傝鐐?

* **6 鏉＄嚎鐨?jar 閲屽甫鐫€楠岃瘉鍣ㄦ嫆缁濈殑 donor 鍒濆鍖栧姪鎵?*:1.20.2銆?.20.4銆?.21銆?.21.1銆?.21.3銆?.21.4,
  褰㈢姸閮芥槸 `SectionRenderDispatcher$RenderSection.optifineoforge$init$buffers(L鈥enderSection;)V` 鈥斺€?
  0: `aload_0` 涔嬪悗鐩存帴 `invokestatic Collectors.toMap`(瑕佷袱涓弬鏁?,鎻愬崌鏃舵妸浜х敓閭ｄ袱涓弬鏁扮殑鎸囦护涓簡;
  1.21 杩樺鍥涗釜(`Options`銆乣ClientChunkCache`銆乣ModelBlockRenderer$SizeInfo`銆乣ItemOverrides`)銆?
  杩欎簺鍔╂墜浼氳杞崲鍣?*鍘熸牱鎷疯繘浜や粯鐨勭被**,鎵€浠ユ槸"绫诲姞杞藉嵆澶辫触",涓嶆槸"璋冪敤鍑洪敊"銆?
* **`f56dd5b` 鐨勭敓鎴愬櫒淇鑳戒慨濂藉畠**:鍦?1.20.2 鑷繁鐨勮緭鍏ヤ笂閲忓埌,鏃?donor 1 澶勮繚瑙?鈫?鐢ㄥ綋鍓嶅垎鏀簮鐮?
  閲嶆柊鐢熸垚鍚?**0 澶?*,鐢熸垚鍣ㄦ敼鍙ｆ墦鍗?`no safe initialiser for field 鈥enderSection.buffers`(鎶婂瓧娈电暀鍦ㄩ粯璁ゅ€?
  鑰屼笉鏄氦浠橀潪娉曞瓧鑺?銆?
* **1.21.x 鍒嗘敮鐨?`MemberRestorePlan` 閲屾病鏈?`stackEffect`/`callEffect`**:瀹冭繕鏄 `f56dd5b` 鎹㈡帀鐨勯偅涓?
  `valueRun`,鎵€浠ラ偅 9 鏉＄嚎鐨勭敓鎴愬櫒缂洪櫡**杩樺湪**,绉绘**鏈仛**銆?.21.x 鐨?`MemberRestoreTransformer` 涔熼渶瑕?
  绉绘鏈妭鐨勭 2 鏉°€?
* **`rebuild-120x-line.ps1` 浼氬鐢ㄦ棫 donor**:`work\<mc>\optifine-patched.jar` 宸插瓨鍦ㄦ椂瀹冭烦杩?`prepare-line`,
  浜庢槸 `plan\donors` 涓?`plan\member-restores.txt` 杩樻槸涓婁竴娆＄殑 鈥斺€?鎴戠涓€娆￠噸寤?1.20.2 鏃堕潪娉曞姪鎵嬩粛鍦?灏辨槸
  杩欎釜鍘熷洜銆傚彂甯冨€欓€夋瀯寤哄繀椤昏涔堝厛鍒犻偅涓?jar,瑕佷箞鎵嬪伐閲嶈窇鐢熸垚鍣ㄩ偅涓€姝?骞朵笖**瀹¤缁撴灉**鑰屼笉鏄浉淇￠厤鏂广€?

#### 7. 1.20.2 绾跨殑鐜扮姸(join 淇宸插啓濂?浣?*灏氭湭瀹炴祴**)

閲嶅缓涓ゆ;鐜板湪鐨?jar 1730798 瀛楄妭,SHA-256 `AC835B122E206A71F850063BA51AACB633699731EAEF2560DBDB547A34794221`,
瀹¤ 0 findings,keep plan 18 琛?鏂板鐨勪竴琛屾槸
`net/minecraft/client/server/IntegratedServer<TAB>initServer<TAB>()Z`(rig 渚?`keep-runtime-1.20.2.txt`銆?
`keep-runtime-1.20.4.txt` 閮藉凡鍔犱笂骞跺啓鏄庝簡鍚勮嚜鐨勯噺娴嬩緷鎹?杩愯鏈?`javap -p` 閮芥湁
`public boolean initServer()`,鑰岃浇鑽风殑 `initServer` 鎶?lifecycle 璋冪敤璺敱鍒?OptiFine 鐨?Reflector,
绫诲悕鐢ㄧ殑鏄?Forge 鐨?`net.minecraftforge.server.ServerLifecycleHooks`,鏈繍琛屾湡涓嶅瓨鍦?銆?.20.2/1.20.4 鐨?
杩欎竴淇**閮借繕娌℃湁鐪熸満璺戣繃**,1.21.x 鍚勭嚎杩?keep plan 閮借繕娌″姞銆?

### 鍏€?026-09-21:鍏ヤ笘鍚庣殑涓や欢鏂颁簨閮戒慨浜嗗苟璺戣繃鐪熸満,鍚屾椂閲忓埌涓€涓?澶栧３绉嶇被"缂洪櫡

鏈疆鏀瑰姩(1.20.x 鍒嗘敮):
`optifine/ForgeApiShims.java` 涓?`loader/PatchedClassTransformer.java`銆傛祴璇?jar:
1748019 瀛楄妭,SHA-256 `22E162451A2BA417E8B9ACFE45CE8E1BA3A07C84E46051988F3D9089DC71AC5A`,
07:28:37 閲嶅缓,`StackAudit` 612鈫?17 涓被 0 findings銆?

#### 1. Forge shim:鑷綔闈欐€佸伐鍘備笉鍐嶅洖绛?null(淇敾鍑?鐮村潖鏂瑰潡鍗冲穿)

閲忓埌鐨勪簨瀹?浜や粯鐨?`ParticleEngine` 鍙湪涓ゅ鐢?Forge 鍖呭悕鐨勬墿灞曟帴鍙?鑰?jar 閲岀殑 shim
`IClientBlockExtensions.of(BlockState)` 灏辨槸 `aconst_null; areturn`(668 瀛楄妭),浜庢槸
`of(...)` 鐨勮繑鍥炲€艰绔嬪埢瑙ｅ紩鐢?鈥斺€?鏀诲嚮涓€娆″氨 `NullPointerException`銆?

鏀规硶:鍑℃槸**杩斿洖鑷繁杩欎釜绫诲瀷鐨勯潤鎬佸伐鍘?*,涓嶅啀 `aconst_null`;鎺ュ彛鍒?*鍦ㄦ梺杈圭敓鎴愪竴涓┖瀹炵幇**
`<interface>$Noop`(瀹炵幇 OptiFine 瀹為檯璋冪敤杩囩殑鏂规硶),宸ュ巶杩斿洖瀹冪殑鏂板疄渚?绫诲澹冲垯杩斿洖鑷韩鐨勬柊瀹炰緥銆?
瀹炴祴:0 涓?4 鐨?stub 闆嗗悎浠?61 涓被鍙樻垚 66 涓?澶氬嚭浜斾釜 Noop:block / fluid / item / mob-effect /
item-font),`of` 鍙樻垚 `new IClientBlockExtensions$Noop; dup; invokespecial <init>; areturn`,776 瀛楄妭銆?
鍚屼竴鏉¤鍒欒鐩?`IClientFluidTypeExtensions.of`(涓や釜閲嶈浇)銆乣IClientItemExtensions.of`銆?
`IClientMobEffectExtensions.of`銆?

#### 2. 灞忓箷杩借釜鍒ょ┖(淇?杩涗笘鐣屽悗 1 绉掕嚜鏉€")

娉ㄥ叆鐨勬墦鍗颁粠 `aload_1; Object.getClass(); Class.getName()` 鏀规垚 `aload_1; String.valueOf(Object)`:
NeoForge 鐨?`ClientHooks.popGuiLayer` 鍚堟硶鍦颁紶 null,鏃у啓娉曠洿鎺ユ姏 NPE銆?*宸插湪鐪熸満鏍稿疄**:
鍥涙杩愯閮借繘涓栫晫,tracer 閮芥墦鍗?10 琛屽苟瓒婅繃鏃х殑 `ReceivingLevelScreen` 澶╄姳鏉?鍏朵腑

```
OPF-SCREEN ...ReceivingLevelScreen@465d9cce
OPF-SCREEN ...ReceivingLevelScreen@6ed3fecb
OPF-SCREEN null
OPF-SCREEN null
```

鍥涙杩愯 `NullPointerException` 0 娆°€佸穿婧冩姤鍛?0 涓€?

#### 3. 鏀诲嚮璺緞**娌℃湁琚繖鍑犳杩愯纭瘉**,濡傚疄璁颁笅

绗?3 娆¤繍琛屾妸 8 绉掔殑鏀诲嚮鎸変綇閫佸埌浜?*鐪熸鐨勬父鎴忕獥鍙?*(`Minecraft NeoForge* 1.20.6`,瀹㈡埛鍖?854x480,
`responding=True`,鍧愭爣鍦ㄥ鎴峰尯鍐?,缁撴灉 0 涓?null 鎵╁睍 NPE銆? 涓穿婧冩姤鍛?浣嗙 4 娆?*瀹屽叏涓嶅彂杈撳叆**鐨?
瀵圭収杩愯鍚屾牱涓簡瀹㈡埛绔?鎵€浠?鐐硅繃涔嬪悗娌″穿"涓?鐐逛箣鍓嶅鎴风灏卞凡缁忔病浜?鍒嗕笉寮€銆?
(绗?2 娆¤繍琛岀偣鍑荤殑鏄?*鎺у埗鍙扮獥鍙?*鈥斺€旀爣棰樻槸 java.exe 璺緞銆佸鎴峰尯 960x480鈥斺€旈偅娆′粈涔堜篃璇佹槑涓嶄簡;
鑴氭湰鐜板湪瑕佹眰鏍囬閲屽嚭鐜?Minecraft 鎵嶇偣鍑?杩欎釜闄烽槺鍐欒繘浜嗚剼鏈敞閲娿€?

#### 4. 閭ｆ鏃犲０閫€鍑?璁颁负**鏈В閲?*,涓嶈涓?閫氳繃"

绗?1銆?銆? 娆¤繍琛岄兘鍦?*鍏ヤ笘鍚庣害 7 绉?*瀹㈡埛绔秷澶?娓告垙鏃ュ織鍋滃湪鍗婂抚涓?鏈€鍚庡嚑琛屾槸
`Replaced ... RenderChunkRegion` / `BufferBuilder$SortState`),娌℃湁宕╂簝鎶ュ憡銆佹病鏈?NPE銆佹病鏈?
`VerifyError`銆佹病鏈?`hs_err_pid*.log`,涔熸病鏈夋甯稿叧闂殑 `Stopping server` / `Saving worlds`銆?
鍞竴鐨勫叧鑱旀槸:杩欏嚑娆￠兘甯?`-AllowConcurrent`,鏈哄櫒涓婂悓鏃舵湁鍙︿竴涓」鐩殑 Minecraft 瀹㈡埛绔?
(`optilithium`,Fabric 1.21.1,07:21:18 鍚姩);鑰屽綋澶╂洿鏃╃殑涓ゆ FXAA 杩愯(07:09-07:21,鍦ㄩ偅涓鎴风
鍚姩涔嬪墠)鍦ㄤ笘鐣岄噷寰呬簡 4.7 鍒嗛挓,鍙湁鑷繁鐨勮剼鏈墠鍋滄帀瀹冦€?*杩欐槸涓や釜瑙傛祴涔嬮棿鐨勭浉鍏?涓嶆槸闅旂瀹為獙**:
杩樻病鏈変汉鍦?鏈哄櫒涓婃病鏈夊埆鐨勫鎴风"鐨勬潯浠朵笅鎶婂悓涓€鍙?jar 鐪嬫弧浜斿垎閽熴€傚湪姝や箣鍓?鍑嗙‘鐨勮〃杩版槸
"鍙︿竴涓鎴风鍦ㄨ窇鏃?瀹㈡埛绔湪鍏ヤ笘绾?7 绉掑悗鏃犲０娑堝け"銆?

#### 5. 鐢辨閲忓埌鐨勪笅涓€涓己闄?澶栧３鐨?绉嶇被"(class vs interface)

杞借嵎鎶?Forge 鎵╁睍绫诲瀷褰?*鎺ュ彛**璋冪敤:

```
3:  invokestatic  InterfaceMethod .../IClientBlockExtensions.of:(...)...IClientBlockExtensions;
20: invokeinterface .../IClientBlockExtensions.addHitEffects:(...)Z
```

鑰?1.20.2銆?.20.4銆?.21.8 浜や粯鐨勫澹虫槸**绫?*:

```
public class net.minecraftforge.client.extensions.common.IClientBlockExtensions {
  public static net.minecraftforge.client.extensions.common.IClientBlockExtensions DUMMY;
```

甯搁噺姹犻噷鏄?`InterfaceMethodref` 鑰岃В鏋愬埌鐨勬槸绫?浼氭姏
`IncompatibleClassChangeError: Found class ..., but interface was expected`,涔熷氨鏄偅鍑犳潯绾夸笂绗竴娆℃敾鍑?
/ 鐮村潖鏂瑰潡棰勮浼氫互 ICCE 宕╂帀(鍙栦唬鏈疆淇帀鐨?NPE)銆?.20.6 鏄澹虫纭嚭鎴愭帴鍙ｇ殑閭ｆ潯绾?`javap` 宸叉牳),
鎵€浠ュ畠鐨勪慨澶嶇湡鐨勬垚绔嬨€傜绫诲垽鏂簲璇ユ潵鑷?*璁板綍涓嬫潵鐨勮皟鐢ㄧ偣**(ASM 鐨?`visitMethodInsn` 鏈潵灏辩粰浜?
`isInterface`),鑰屼笉鏄幇鍦ㄧ殑"shape 閲屽０鏄庝簡鑷韩绫诲瀷鐨勫瓧娈?鈥斺€旀帴鍙ｅ畬鍏ㄥ彲浠ュ湪鑷繁鐨?`<clinit>` 閲岀敤
Noop 瀹炰緥濉繖绉嶅瓧娈点€?*灏氭湭淇?*,杩欐槸涓嬩竴杞殑绗竴浠朵簨銆?

### 涓冦€?026-09-21:澶栧３**绉嶇被**鎸夎皟鐢ㄧ偣鍐冲畾(宸蹭慨),浠ュ強 1.20.2 绗竴娆″叆涓栧悗褰撳満鏆撮湶鐨勪笅涓€浠剁己闄?

#### 1. 绉嶇被缂洪櫡:閲忓埌鐨勬牱瀛?

浜や粯鐨勮浇鑽锋妸 Forge 鎵╁睍绫诲瀷褰?*鎺ュ彛**璋冪敤(1.20.2 鑷繁鐨?`ParticleEngine`,`javap -p -c`):

```
3:  invokestatic  InterfaceMethod .../IClientBlockExtensions.of:(...)L.../IClientBlockExtensions;
20: invokeinterface .../IClientBlockExtensions.addHitEffects:(...)Z
```

鑰?*褰撴椂浜や粯鐨?jar** 閲屽悓涓€涓被鍨嬫槸**绫?*(1.20.2 / 1.20.4 / 1.21.8 閮戒竴鏍?:

```
public class net/minecraftforge/client/extensions/common/IClientBlockExtensions     914 瀛楄妭
  public static IClientBlockExtensions DUMMY;
  public IClientBlockExtensions();
  public static IClientBlockExtensions of(BlockState);
```

甯搁噺姹犲啓鐨勬槸 `InterfaceMethodref`銆佽В鏋愬埌鐨勬槸绫?鈫?绗竴娆℃敾鍑?鐮村潖鏂瑰潡棰勮
`IncompatibleClassChangeError: Found class ..., but interface was expected`銆?

#### 2. 淇硶涓庨噺娴?

`ForgeApiShims`:`MethodReference` 澧炲姞 `interfaceRef` 瀛楁(`visitMethodInsn` 鐨?`isInterface`,鏉ヨ嚜杞借嵎鑷繁
鐨勮皟鐢ㄧ偣),`Shape.callsThroughInterface()` 姹囨€?`generate()` 鐨勭绫昏鍒欐敼鎴?

```java
asInterface = shape.mustBeClass() ? false
        : (shape.callsThroughInterface() || (!declaresItself(shape, name) && isInterface(zip, name)));
```

涔熷氨鏄:**璋冪敤鐐硅鏄帴鍙?灏卞繀椤绘槸鎺ュ彛**;`declaresItself()`(shape 閲屽０鏄庝簡鑷韩绫诲瀷鐨勯潤鎬佸瓧娈?
Forge 鐨?`DUMMY` 灏辨槸)涓嶅啀鎶婃暣涓被鍨嬪帇鎴愮被 鈥斺€?鎺ュ彛鍙互鍦ㄨ嚜宸辩殑 `<clinit>` 閲岀敤 Noop 瀹炰緥濉偅涓瓧娈?
`needsNoop()` 涔熷洜姝ゅ悓鏃剁湅"鑷被鍨嬮潤鎬佸伐鍘?鍜?鑷被鍨嬮潤鎬佸瓧娈?銆傛帴鍙ｄ笉鍐嶅啓鏋勯€犲櫒銆?

閲忔祴(绂荤嚎,鍚屼竴瀵硅緭鍏?:

| | 澶栧３ | Noop | `<clinit>` |
|---|---|---|---|
| 淇墠(1.20.2 jar 鍐? | `class`,914 瀛楄妭,甯?`DUMMY` + 鍖垮悕 `$1` | 鏃?| 閫犺嚜韬疄渚?|
| 淇悗(1.20.2 閲嶆柊鐢熸垚) | **`interface`**,776 瀛楄妭 | `IClientBlockExtensions$Noop` 687 瀛楄妭 | `new ...$Noop` 鈫?`putstatic DUMMY` |
| 淇悗(1.21.8 閲嶆柊鐢熸垚) | **`interface`**,921 瀛楄妭 | 浜斾釜 Noop | 鍚屼笂 |

涓ゅ stub 閮借繃浜嗘暟鎹祦瀹¤(1.20.2 54 涓被銆?.21.8 92 涓被,0 findings)銆?

#### 3. 1.20.2 閲嶅缓 + 绗竴娆″叆涓?

閲嶅缓鍚庣殑 jar 1721113 瀛楄妭,SHA-256
`5757CFDFD697CB61FC2CD4794650336E55EBA323012801D46D79AC41403356F0`,瀹¤ 603 涓被 0 findings,
keep plan 18 琛?鍚?`IntegratedServer initServer ()Z`),杞借嵎閲屾槸**鎺ュ彛**澶栧３ + Noop銆?

鐪熸満:杩欎竴鏉＄嚎**绗竴娆?*璺戝嚭鍏ヤ笘琛?鈥斺€?

```
07:44:53.485 PlayerList: Dev[local:E:d62d77a2] logged in with entity id 115 at (-7.5, 76.0, 8.5)
07:44:53.505 MinecraftServer: Dev joined the game
```

涔熷氨鏄 join keep plan 鍦?*绗簩鏉＄嚎**涓婃垚绔?1.20.6 涔嬪鐨勭涓€鏉?,閰嶇疆闃舵涓嶅啀姝诲湪
`ConfigSync.syncConfigs`銆?

#### 4. 绱ф帴鐫€鏆撮湶鐨勪笅涓€浠剁己闄?鏈慨):**绗竴甯ц null 鐨勭帺瀹?*

鍏ヤ笘涓€绉掑悗瀹㈡埛绔穿鍦?*瀹㈡埛绔晶鐨勪笘鐣屾覆鏌?*涓?`crash-2026-09-21_07.44.55-client.txt`):

```
java.lang.NullPointerException: Cannot read field "oSpinningEffectIntensity" because "this.minecraft.player" is null
  at net.minecraft.client.renderer.GameRenderer.renderLevel(GameRenderer.java:1619)
```

浜や粯绫婚噷閭ｆ潯璇绘病鏈夊垽绌?`javap -p -c` 鑷?jar 鍐呯殑
`optifineoforge/patched/net/minecraft/client/renderer/GameRenderer.class`):

```
233: aload_0
234: getfield minecraft
237: getfield Minecraft.player        <- 娌℃湁鍒ょ┖
240: getfield LocalPlayer.oSpinningEffectIntensity:F
```

`minecraft.player` 涓?null 鐨勬椂鍒绘鏄鎴风杩樺仠鍦?`GenericDirtMessageScreen`銆佸鎴风鍏冲崱宸插瓨鍦ㄤ絾鐜╁瀹炰綋
杩樻病鍒扮殑鏃跺€?浜庢槸涓栫晫鐨?*绗竴甯?*灏辨銆傚穿婧冨墠涓€鍏?11 涓悓褰㈢姸鐨?`NullPointerException`
(`player.getAbilities()`銆乣LocalPlayer.getInventory()`銆乣getRecipeBook()`銆乣position()`銆?
`getDeltaMovement()`),璇存槑鏈夎嫢骞叉潯 OptiFine 鏀硅繃鐨勬覆鏌?tick 璺緞閮芥棤鏉′欢瑙ｅ紩鐢ㄧ帺瀹躲€?.20.6 娌℃湁鏆撮湶杩欎竴鏉?
(瀹冪殑杞借嵎杩炴覆浜?4.7 鍒嗛挓),涓ゆ潯绾跨殑 `GameRenderer` 鍦ㄨ繖閲屼笉涓€鏍枫€?*灏氭湭淇?*,1.20.2 鐨勪笅涓€浠?
1.20.4 寰堝彲鑳藉悓褰?鏈祴)銆?

**涓轰粈涔堝垽绌烘病浜?浠ュ強淇湪鍝竴灞?*:杩愯鏈熻嚜宸卞甫杩欎釜鍒ょ┖,浜や粯鐨勭被娌℃湁 鈥斺€?杩愯鏈?
`client-1.20.2-20231019.002635-srg.jar` 鐨勫悓涓€涓〃杈惧紡鏄?

```
471: aload_0 / 472: getfield minecraft / 475: getfield Minecraft.player
478: ifnull 571            <- 杩愯鏈熺殑鐜╁鍒ょ┖
481: fload_1 / 482-489: minecraft.player.oSpinningEffectIntensity / 492-499: ...spinningEffectIntensity
```

鑰屼氦浠樼殑 `renderLevel` 鍦?233-250 鐩存帴璇诲悓鏍蜂袱涓瓧娈?鍓嶉潰娌℃湁浠讳綍 `ifnull`銆備篃灏辨槸璇磋涓婄殑鏄?*OptiFine 鑷繁
缂栬瘧鐨?*杩欎釜鏂规硶(瀹冩棤鏉′欢绠楄繖涓?lerp),杩愯鏈熷甫鐨勯偅閬撳垽绌轰笉鍦ㄩ噷闈€?

淇殑浣嶇疆搴斿綋鏄?*绂荤嚎**鐨?`OptifineJarFixer`,涓嶆槸鍔犺浇鏈熻浆鎹㈠櫒:閭ｄ釜绫绘湰鏉ュ氨鍦ㄩ噸鍐?OptiFine 鑷繁鐨?class,
鑰屼笖宸茬粡鍦ㄩ噷闈㈡彃鍒嗘敮(`new LabelNode()` / `new JumpInsnNode(Opcodes.IFLT, ...)`),
鍐欏洖鏃剁敤 `SafeClassWriter(reader, COMPUTE_FRAMES | COMPUTE_MAXS)`;鍔犺浇鏈熼偅鏉¤矾鍒绘剰閬垮紑鍒嗘敮,瀹冭嚜宸辩殑娉ㄩ噴
灏卞啓鐫€"String.valueOf ... needs no branch (so no stack map frames have to be computed)"銆?
褰㈢姸:鍦ㄤ氦浠樹綋 offset 232(`fload_1` 涔嬪墠)鎻掍竴閬?`if (minecraft.player != null)`,鎶?232-262 鍖呰捣鏉?
鐜╁涓?null 鏃惰璇ュ瓧娈典繚鎸佸師鍊?鎻掑畬鍏堝閲嶅缓鐨?jar 璺?`StackAudit`,鍐嶄笂鐪熸満銆?

**琛ヨ:涓や釜瀹堝崼鍦?rig 渚ц瘯杩囦簡,缁撹鏄繖涓€鏉′笉鏄?澶氬璇绘病鍒ょ┖",鑰屾槸"鐜╁瀹炰綋鏍规湰娌″埌"銆?*
rig 渚у啓浜嗕袱涓绾垮伐鍏锋敼 `work\1.20.2\optifine-patched.jar`(杞借嵎),鍚勮嚜閲嶅缓 + 涓婄湡鏈?

1. `GuardSpinningReads` 鎶?`renderLevel` 閲岄偅涓ゅ
   `GETFIELD Minecraft.player; GETFIELD LocalPlayer.<spinning 瀛楁>:F` 鎹㈡垚鍚屼竴涓被閲屾柊鍔犵殑
   `optifineoforge$spinningOrZero` / `$oldSpinningOrZero`(鐜╁涓?null 鏃惰繑鍥?0.0f)銆傛爤褰㈢姸涓嶅彉,澶ф柟娉曠殑甯?
   涓嶇敤閲嶇畻(helper 鑷甫鎵嬪啓 `F_SAME`)銆傜粨鏋?payload 涓庨噸寤?jar 閮借繃瀹¤(2238 / 603 涓被 0 findings),
   鐪熸満涓?`renderLevel:1619` 鐨勯偅娆″穿婧?*娌′簡**,瀹㈡埛绔線鍓嶄竴姝ユ鍦?
   `renderLevel:1639 -> Camera.setup(Camera.java:46)`:`renderViewEntity` 涓?null銆?
2. `GuardRenderLevelEnter` 鏀瑰湪鍏ュ彛:`render(FJZ)V` 閲屽 `renderLevel(FJL鈥oseStack;)V` 鐨勮皟鐢ㄦ敼鎸囧悜鏂扮敓鎴愮殑
   `optifineoforge$renderLevelIfPlayer` 鈥斺€?鐜╁涓?null 灏辩洿鎺ヨ繑鍥?鍚﹀垯鐢ㄥ悓鏍风殑瀹炲弬璋冪敤鍘熸柟娉曘€傝皟鐢ㄧ偣鏍堝舰鐘?
   涓嶅彉,鍙湁鏂版柟娉曞甫鍒嗘敮(鎵嬪啓甯?銆傜粨鏋?payload 涓庨噸寤?jar 閮藉共鍑€(2238 / 603 涓被 0 findings,jar
   1721257 瀛楄妭,SHA-256 `2480D95B929320B7324B304BE4D7D7F782C2CEA14DCBD4520496B6EA68E0081A`),
   鐪熸満 60 绉掓棤杈撳叆杩愯:**娌℃湁宕╂簝鎶ュ憡,瀹㈡埛绔椿鍒版渶鍚?*(鐢辫剼鏈仠鎺?,**浣?60 绉掗噷 34098 涓?
   `NullPointerException`**(绾︽瘡甯?13 涓?`jumpableVehicle()`銆乣getInventory()`銆乣getVehicle()`銆?
   `oSpinningEffectIntensity`銆乣isScoping()`銆乣getAttackStrengthScale(float)` 鈥?,tracer 鏄剧ず瀹冨仠鍦?
   `GenericDirtMessageScreen -> ProgressScreen -> LevelLoadingScreen -> ProgressScreen`,骞舵病鏈夎繘涓栫晫銆?

鎵€浠?1.20.2 涓?绗竴甯у穿婧?鍙槸**鐥囩姸**:闆嗘垚鏈嶅姟鍣ㄥ叆涓栦簡,鑰?*鐜╁瀹炰綋濮嬬粓娌″埌**,浜庢槸姣忎竴甯у湪鍗佸嚑澶?
瑙ｅ紩鐢?null 鐜╁銆傛妸娓叉煋鎸′綇鑳借宕╂簝娑堝け,浣嗗鎴风灏卞仠鍦?`ProgressScreen` 涓?鈥斺€?鏄繘灞?涓嶆槸閫氳繃,涔熶笉璇ュ湪
娌″紕娓呯帺瀹朵负浠€涔堟病鍒颁箣鍓嶅氨鐣欏湪鍙戝竷 jar 閲屻€傝繖鏉＄嚎涓嬩竴涓闂殑闂鏄?
**"1.20.2 涓婄帺瀹跺疄浣撲负浠€涔堟病鍒?**(涓婇潰閭ｄ覆 NPE 姝ｅソ鐐瑰悕浜嗚繖鏈熼棿鍦ㄨ窇鍝簺 tick)銆?
涓や釜宸ュ叿閮藉彧鍦?rig 渚?鏈繘浠撳簱;杩欑被"娓告垙绫?鐨勪慨澶嶅湪浠撳簱閲岃鏀惧湪鍝竴灞?鍐?`optifine-patched.jar` 鐨勮浇鑽?
pipeline,鍥犱负 `OptifineJarFixer` 淇殑鏄?OptiFine 鑷繁鐨勭被)浠嶆槸鏈畾椤广€?

### 鍏€?026-09-21 鏅?1.20.2 **杩涗簡涓栫晫** 鈥斺€?鐜╁瀹炰綋涓轰粈涔堟病鍒扮殑鏍瑰洜涓庝慨娉?

#### 1. 鏍瑰洜(瀹㈡埛绔棩蹇楄嚜宸辩粰鐨勭瓟妗?

```
ReportedException: Registering texture
  at TextureManager.loadTexture <- TextureManager.register
  at net.optifine.player.CapeUtils.downloadCape(CapeUtils.java:71)
  at net.minecraft.client.player.AbstractClientPlayer.<init> <- LocalPlayer.<init>
  <- MultiPlayerGameMode.createPlayer <- ClientPacketListener.handleLogin
Caused by: java.lang.NoSuchMethodError:
    'java.util.concurrent.ExecutorService net.minecraft.Util.getCapeExecutor()'
  at HttpTexture.getExecutor(HttpTexture.java:346)
```

OptiFine 鍦?*鐜╁鏋勯€犲嚱鏁伴噷**涓嬭浇鎶,鑰岃繖鏉¤矾瑕?`Util.getCapeExecutor()`銆傚紓甯歌 `handleLogin` 鍦?
`Minecraft.player` 琚祴鍊?*涔嬪墠**灏变腑姝?浜庢槸涔嬪悗姣忎釜鍖呭鐞嗛兘鍦ㄨВ寮曠敤 null 鐜╁:60 绉?**32774 涓?
`NullPointerException`**(姣忓抚 13 涓?,瀹㈡埛绔仠鍦?`ProgressScreen`銆傚墠闈㈤偅浜?绗竴甯у穿婧?鍏ㄦ槸杩欎釜鐘舵€佺殑鐥囩姸銆?

缂虹殑鎴愬憳鍦ㄥ摢杈?鍥涗釜鍊欓€夐兘閲忚繃(`javap`):

| 绫?| `Util.getCapeExecutor()` |
|---|---|
| `libraries\...\client-1.20.2-...-srg.jar` 鐨?`net/minecraft/Util` | 娌℃湁 |
| `work\1.20.2\runtime-1.20.2.jar` 鐨?`net/minecraft/Util` | 娌℃湁 |
| `work\1.20.2\optifine-patched.jar` 鐨?`srg/net/minecraft/Util` | **鏈?* |
| 璋冪敤鏂?`srg/.../HttpTexture` | 璋冨畠 |

涔熷氨鏄**杞借嵎鐨?`HttpTexture` 涓庤浇鑽风殑 `Util` 鏄竴瀵?*,鑰?keep plan 鎸夎璁℃妸 `net.minecraft.Util`
(杩炲悓 `Util$1..$11`銆乣IdentityStrategy`銆乣OS`)鏁寸被淇濇垚杩愯鏈熺殑,鎶婅繖涓€瀵规媶寮€浜嗐€傞偅涓暣绫?keep 鏈夊畠鑷繁鐨?
閲忔祴鐞嗙敱(OptiFine 鐨勮浇鑽峰湪杩欎釜瀹舵棌閲屾贩浜嗕袱浠界紪璇?`Util$5.<init>(Path)` 鍦ㄥ畠鑷繁鐨?`Util$5` 閲屾牴鏈笉瀛樺湪),
鎵€浠ヤ慨娉曚笉鏄彇娑?keep,鑰屾槸**鎶婂彧鏈夎浇鑽峰０鏄庛€佽€岃浇鑽疯嚜宸辩殑绫昏鐢ㄧ殑鎴愬憳琛ュ洖琚繚鐨勭被**銆?

#### 2. 淇硶:`PatchedClassTransformer.carryPayloadOnlyMembers`(宸叉彁浜?

琚暣绫讳繚涓嬫潵鐨勭被,琛ヤ笂杞借嵎鐙湁鐨?*瀛楁涓庢柟娉?*;闈欐€佸瓧娈佃繛鍚?*杞借嵎鑷繁閭ｆ潯鍒濆鍖栬鍙?*涓€璧锋惉
(鍦ㄨ浇鑽?`<clinit>` 閲屼互鍐欏畠鐨?`PUTSTATIC` 缁撳熬鐨勯偅娈垫寚浠?;鏋勯€犲櫒涓?`<clinit>` 鏁翠綋**姘镐笉**鎼繍 鈥斺€?
閭ｆ鏄袱浠界紪璇戜簰鐩镐笉涓€鑷寸殑鍦版柟銆傛帓闄ら」閮芥槸閲忓嚭鏉ョ殑:

| 鎼粈涔?| 涓轰粈涔?|
|---|---|
| 杞借嵎鐙湁鐨勬柟娉?| `Util.getCapeExecutor()` 鍙湁杞借嵎鏈?鑰岃浇鑽疯嚜宸辩殑 `HttpTexture` 璋冨畠 |
| 杞借嵎鐙湁鐨勯潤鎬佸瓧娈?+ 瀹冪殑鍒濆鍖栬鍙?| `CAPE_EXECUTOR` 鏄?`makeExecutor("Cape")`(杩愯鏈熻嚜宸辨湁 `makeExecutor`),涓嶆惉鍒濆鍖栧氨鏄?null |
| **涓嶆惉** `optifineoforge$init$` 鐢熸垚鍔╂墜 | 瀹冧滑缁欒浇鑽疯嚜宸辩殑绫诲啓瀛楁;鎼埌琚繚鐨勭被涓婄洿鎺ユ姏 `IllegalAccessError: Update to non-static final field net.minecraft.Util$9.cache ... from a different method (optifineoforge$init$cache)`(`Util$9.<init>` 璧峰氨姝? |
| **涓嶆惉** 闈為潤鎬?final 瀛楁 | 鍙湁绫昏嚜宸辩殑鏋勯€犲櫒鑳藉啓瀹?鑰屾瀯閫犲櫒涓嶆惉,鍚屾牱 `IllegalAccessError` |
| 鍒囩墖閲岀殑鏍囩/琛屽彿/鏍堝抚绛変吉鎸囦护 | 褰撲綔"鏃犳硶澶嶅埗"浼氳 `CAPE_EXECUTOR` 閭ｆ潯璇彞琚暣鏉′涪鎺?瀛楁鐣?null,鎶涓嬭浇闅忓嵆 `NullPointerException at CompletableFuture.screenExecutor` |

璇曡繃浣?*涓嶉噰鐢?*鐨勬浛浠ｆ柟妗?涓€骞惰涓?`keep-runtime-1.20.2.txt` 閲屾浘缁忓姞杩?
`net/minecraft/client/renderer/texture/HttpTexture	*`(鎶婅皟鐢ㄦ柟鎹㈡垚杩愯鏈熺殑),缁撴灉鏄悓涓€鏉￠摼寰€鍚庝竴姝ユ鍦?
`NoSuchFieldError: pipeline`(`CapeUtils.downloadCape` 璇荤殑瀛楁杩愯鏈熺殑 `HttpTexture` 娌℃湁);璇ヨ宸插垹闄?
娉ㄩ噴淇濈暀鍦ㄨ鍒掓枃浠堕噷銆?

#### 3. 缁撴灉:1.20.2 杩涗簡涓栫晫,鑰屼笖**涓嶉渶瑕?*浠讳綍 rig 渚у畧鍗?

鎶?rig 渚ч偅涓や釜瀹堝崼宸ュ叿閮芥挙鎺?杞借嵎鎭㈠鎴愭湭瀹堝崼鐨勭増鏈?,鍙暀浠撳簱鑷繁鐨勬敼鍔?join keep plan + 鏈 carry),
閲嶅缓鍚庡璁?0 findings,鐪熸満 75 绉掓棤杈撳叆:

```
OPF-SCREEN ...GenericDirtMessageScreen -> ProgressScreen -> LevelLoadingScreen -> ProgressScreen
OPF-SCREEN ...ReceivingLevelScreen
OPF-SCREEN null
OPF-SCREEN null
22:40:28.482 PlayerList: Dev[local:E:d74f630b] logged in with entity id 195 ...
```

* 鏈€鍚庝竴閬撳睆鏄?**null**(鍦ㄤ笘鐣岄噷),涓嶆槸鍔犺浇灞?
* 鏁磋疆鍙湁 **2** 涓?`NullPointerException`(閮芥槸闈炶嚧鍛界殑 `ModelDataManager.getAt`),鑰屼慨涔嬪墠鏄?32774 / 40404;
* 宕╂簝鎶ュ憡 0銆乣VerifyError` 0,瑙傚療绐楀彛缁撴潫鏃跺鎴风杩樻椿鐫€(鐢辫剼鏈仠鎺?;
* 鑰屼笖**涓嶅啀闇€瑕?* rig 渚ч偅涓や釜瀹堝崼 鈥斺€?璇存槑"绗竴甯у穿婧?纭疄鍙槸缂虹帺瀹剁殑鐥囩姸銆?

鑷虫**涓ゆ潯绾跨湡鐨勬覆鏌撳嚭浜嗕笘鐣?*:1.20.6(4.7 鍒嗛挓)涓?1.20.2(鏈)銆?

### 涔濄€?026-09-21 娣卞:1.20.2 涓婄殑 FXAA 閲忔祴(鍚岄€夐」瀵圭収鎶婂綊鍥犲畾浣忎簡)

1.20.2 杩涗笘鐣屼箣鍚?鍦ㄥ悓涓€浼氳瘽閲屽仛浜嗕笁瀵?`antialiasingLevel` 0 vs 4 鐨勬埅鍥?鏃犵潃鑹插櫒鍖呫€乣ofAaLevel=0`銆?
浜戝叧闂€佷笘鐣岄拤浣?,澶栧姞涓や釜**鍚岄€夐」瀵圭収**(杩欐槸鍏抽敭):

| 姣旇緝 | 鍦烘櫙宸紓 | 骞冲潎杈圭紭鑳介噺 | 纭竟 |
|---|---|---|---|
| `=4`(23:07) vs `=4`(23:2x),**鍚岄€夐」** | **0.7%** | -6.2% | -3.2% |
| `=0`(23:12) vs `=0`(23:2x),**鍚岄€夐」** | **0.7%** | +4.0% | +1.6% |
| `=0` vs `=4`(23:2x),閫夐」缈昏浆 | **86.5%** | +3.2% | +11.8% |
| `=0` vs `=4`(23:12/23:07),閫夐」缈昏浆 | 86.5% | +12.4% | +15.8% |

涔熷氨鏄**杩愯鏈韩鏄彲澶嶇幇鐨?*(鍚岄€夐」涓ゆ鐩稿樊 0.7%,閲忎簡涓ゆ),鑰?86.5% 杩欎釜宸紓**鍙湪閫夐」鍙樺寲鏃跺嚭鐜?* 鈥斺€?
鎵€浠ュ畠褰掑洜浜庨€夐」,涓嶆槸鍦烘櫙婕傜Щ銆傛寜杩欎釜璇绘硶:1.20.2 涓?`antialiasingLevel=4` 鏀瑰姩浜?**86.5% 鐨勫唴閮ㄥ儚绱?*,
骞冲潎杈圭紭鑳介噺闄?**3.2%**銆佺‖杈归檷 **11.8%**,鏂瑰悜涓?1.20.6 閭ｄ竴瀵?纭竟 -12.7%)涓€鑷?鑰岃繖鏉＄嚎鐨勯€愭鍣０鐜板湪
鏄?*閲忓嚭鏉ョ殑 0.7%**,涓嶆槸鍋囪鐨勩€?

涓ゆ潯闄勫甫璇存槑涓€璧疯涓?(a) 86% 鐨勫儚绱犲彉鍔?*杩滃浜?鎶瑰钩杈圭紭"**,浠呭嚟杈圭紭鑳介噺璇翠笉娓呰繕鏀逛簡浠€涔?涓嶅悓鐨勬覆鏌?
璺緞鎴栦寒搴﹀亸绉诲湪杩欎釜鎸囨爣涓婄湅璧锋潵涓€鏍?;(b) `fxaa-check.ps1` 鐨勫満鏅棬闄愬亣璁?閫夐」鏀瑰姩鏄粏寰殑",浜庢槸鎶婅繖涓ゆ
璺ㄩ€夐」姣旇緝鍒ゆ垚"涓嶆槸鍚屼竴涓満鏅?,鍙堟妸**鍚岄€夐」**瀵圭収鍦?0.7% 鍦烘櫙宸紓涓婂垽鎴?"FXAA VISIBLE" 鍜?"REVERSED" 鈥斺€?
鏈変簡鍚岄€夐」鍩虹嚎涔嬪悗,杩欎釜闂ㄩ檺搴斿綋璇讳綔"閫夐」寮曡捣鐨勫彉鍔ㄥぇ浜庡櫔澹?,鑰屼笉鏄満鏅垽鎹€?

灏氬緟瑙ｅ喅(宸茶涓?:瑕佸湪**鍚屼竴濮垮娍**涓嬪仛缁濆姣旇緝,濮垮娍蹇呴』鍦?*娓告垙鍐?*鎺у埗 鈥斺€?瀛樻。绾х殑 `-Yaw/-Pitch` 閽変笉浣?
鍥犱负鐜╁鏈濆悜鐢辩櫥褰?鍑虹敓鍖呭喅瀹?瀹炴祴:閽夎繘 `playerdata` 鐨?0 鍦ㄤ笅涓€杞張琚鎴风鍐欏洖 149.375)銆?
rig 宸叉湁 `send-chat.ps1`,杩涗笘鐣屽悗鍙戜竴鏉?`/tp @s <x> <y> <z> <yaw> <pitch>` 鍗冲彲銆?

### 鍗併€?.20.4:鎵€鏈変慨澶嶉兘杩涗簡 jar,涓栫晫娴嬭瘯寰呭仛

`jars-1.20.4\OptifiNeoforge-1.0.0+mc1.20.4-registered.jar`,1736876 瀛楄妭,
SHA-256 `CE08480B05BCE393AC8DC5D6B6507D68A332AEF5698C536BA09F8D967B15E24A`,瀹¤ 0 findings,鍐呭惈:
join keep plan(3 琛?`GlDebug`銆乣AbstractTexture.setFilter`銆乣IntegratedServer.initServer`)銆?
鐢ㄤ慨濂界殑鐢熸垚鍣ㄩ噸鍋氱殑 donor銆佺敤绉嶇被淇閲嶅仛鐨?Forge stub(鎺ュ彛澶栧３ + Noop)銆乨rop plan(3 琛?浠ュ強鏈鐨?
carry 淇銆俙--quickPlaySingleplayer` 鍦?1.20.4 涓?*鏃犳晥**(鏃╁墠瀹炴祴),鎵€浠ヨ繖鏉＄嚎鐨勪笘鐣屾祴璇曡闈?
`click-at.ps1 -FindButtons` 椹卞姩鑿滃崟,鑰屼笉鏄惎鍔ㄥ弬鏁般€?

**琛ヨ(2026-09-21 23:35-00:05,rig 渚?`world-test-1204.ps1`):**

* **鏍囬鐣岄潰纭鍙敤涓斿彲浜や簰**:tracer 姣忔閮借蛋鍒?`GenericDirtMessageScreen -> TitleScreen`
  (绐楀彛 `Minecraft NeoForge* 1.20.4`,瀹㈡埛鍖?854x480,`responding=True`);鍦?*绐楀彛澶勪簬鍓嶅彴**鐨勯偅娆¤繍琛岄噷,
  (427,200) 鐨勭偣鍑荤湡鐨勭敓鏁堜簡 鈥斺€?`TitleScreen -> SelectWorldScreen`銆備篃灏辨槸璇?鏍囬鐣岄潰鑳界敤"杩欎竴鏉″湪杩欐潯绾夸笂
  鏄?*瀹炴祴閫氳繃**鐨?鑰屼笉鍙槸鐢诲嚭鏉ヤ簡銆?
* **涓栫晫鍏ュ彛浠嶆湭椹卞姩鎴愬姛**:涓栫晫鍒楄〃閲岄偅涓€琛岃瘯浜嗗洓涓綅缃?`225,70`/`225,110`/`225,150`/`300,80`),
  鍙屽嚮閮芥病鏈夋墦寮€涓栫晫(`session.lock` 濮嬬粓涓嶅瓨鍦ㄣ€佹棩蹇楅噷娌℃湁涓栫晫琛?銆?
* 杩欎竴杞负姝や粯浜?*浜斾釜 rig 缂洪櫡**鐨勪唬浠?閮藉凡淇苟鍐欒繘鑴氭湰娉ㄩ噴:
  (1) `-Mods` 鐢ㄧ浉瀵硅矾寰勪細澶辫触(`launch.ps1` 鎸夎嚜宸辩殑宸ヤ綔鐩綍瑙ｆ瀽),鑰屼笖澶辫触鍚庢埅鍥句細鎶撳埌**鍒殑浼氳瘽**鐨勫鎴风;
  (2) `-TitleMatch` 蹇呴』鏄父鎴忔爣棰樼墖娈佃€屼笉鏄?profile id(`neoforge-20.4.251` 姘歌繙鍖归厤涓嶅埌
  `Minecraft NeoForge* 1.20.4`);(3) tracer 鐨?stderr 鍦?`logs\launch-<VersionId>.err.log`,璇婚敊鏂囦欢浼氳灞?
  骞曞垪琛ㄥ叏绌恒€佹瘡娆＄偣鍑婚兘鍍忔病鐢熸晥;(4) **鎶曢€掔粰鍚庡彴绐楀彛鐨勭偣鍑讳細琚涪寮?* 鈥斺€?GLFW 鐨?Minecraft 鍦ㄨ嚜宸变笉璁や负澶勪簬
  鍓嶅彴鏃朵細蹇界暐榧犳爣娑堟伅(瀹炴祴閭ｆ `is foreground: False`,鏍囬鐣岄潰绾逛笣涓嶅姩),`click-at.ps1` 鍥犳鍔犱簡 `-Focus`
  (AttachThreadInput + SetForegroundWindow + SW_RESTORE),鍔犲畬鍚屼竴涓潗鏍囧氨鎵撳紑浜?`SelectWorldScreen`;
  (5) **`PrintWindow` 鍦ㄨ繖鏉＄嚎涓婅繑鍥炵殑鏄繃鏈熺殑鍚庡彴瀛樻。**,鎴浘涓嶈兘鐢ㄦ潵瀹氫綅鎸夐挳 鈥斺€?涓栫晫鍒楄〃鐣岄潰鏃舵姄鐨勫浘
  `world1204-list.png` 閲屼寒鐨勮繕鏄爣棰樼晫闈㈢殑鎸夐挳甯?image y 216-280),涓?`world1204-title.png` 鍚屼负 12566 瀛楄妭,
  杩欎篃鏄?`-FindButtons` 鍦ㄨ繖閲屾姤"none found"鐨勫師鍥?涓ゆ鐐瑰嚮鍥犳閮戒互 tracer 涓哄噯,鑰屼笉鏄互鍥句负鍑€?
* 鏈熼棿鏈哄櫒涓婃湁**鍙︿竴涓細璇濈殑瀹㈡埛绔?*(`Minecraft* 1.21.11 - Singleplayer`,pid 13228):鎸夐挳鏌ユ壘鍥犳鎷掔粷鍦?
  涓や釜 `Minecraft` 绐楀彛涔嬮棿鐚?绗笁娆￠噸璇曠殑涓夋鐐瑰嚮涔熼兘娌＄敓鏁?鑰屽悓涓€鍧愭爣鍦ㄥ畨闈欒繍琛屾椂鏄湁鏁堢殑銆?
  璇ヨ繘绋嬫湭琚Е纰般€?
* 杩欐潯绾胯杩囦笘鐣屾祴璇?寰楀湪**瀹夐潤鏈哄櫒**涓娿€佷互 tracer 涓哄垽鎹€愮偣璇曚笘鐣屽垪琛ㄩ偅涓€琛?鎴栬€呯粰 harness 涓€鏉′笉缁忚繃 GUI
  鐨勫叆涓栭€斿緞銆?

### 鍗佷竴銆?026-09-22 鍑屾櫒:涓ゆ潯绾跨湡鐨勫姞杞戒簡鍏夊奖鍖?闅忓嵆鍙戠幇涓€鏉?*鍙戝竷绾?*缂洪櫡;浠ュ強涓€娆¤璇佷吉鐨勬敹瀹?

#### 1. 鐢ㄦ埛瑕佺殑"鍦ㄥ缓妗ｉ噷璺戝厜褰辨祴璇?鍦ㄤ袱鏉＄嚎涓?*閫氳繃**

`run-save-shaders-all.ps1 -Pack 'MakeUp-UltraFast-9.5e.zip' -AaLevel 0`(涓栫晫鐢辨ā鏉跨敓鎴愩€乹uick play 杩涘叆銆?
鍏夊奖鍦ㄦ。鍐呰姹?,300 绉?

| 绾?| 鍒ゅ畾 | 澹伴煶 | 涓栫晫 | 宕╂簝 | 鍏夊奖鍖?| FXAA |
|---|---|---|---|---|---|---|
| 1.20.6 | **STARTED** | yes | yes,**10 涓?region 鏂囦欢 + level.dat 琚湰娆¤繍琛屾敼鍐?* | 0 | **`[Shaders] Loaded shaderpack: MakeUp-UltraFast-9.5e.zip`** | 璇锋眰 0,鏃ュ織鏃?FXAA 琛?|
| 1.20.2 | **STARTED** | yes | yes,**10 涓?region 鏂囦欢 + level.dat** | 0 | **鍚屼笂** | 鍚屼笂 |

涔熷氨鏄:涓栫晫鏄湡寤哄嚭鏉ョ殑(region 涓?level.dat 鐢辫娆¤繍琛屽啓鍑?,OptiFine 鐨勫厜褰辩绾跨湡鐨勮捣鏉ヤ簡(瀹冭嚜宸辩殑鏃ュ織琛?
鐐瑰悕浜嗗寘鍚?,鍥涢」楠屾敹妫€鏌ヤ緷鏃у叏涓?涓旀病鏈夊穿婧冩姤鍛娿€?*杩欐槸绗竴娆＄敱 harness 纭"鍏夊奖鍖呭凡鍔犺浇"**,鑰屼笉鏄彧纭
"璇锋眰浜?鈥斺€?026-09-20 閭ｆ `save-shaders-1.20.2-nopack-aa0.out.log` 璁扮殑鏄?`VERDICT: EXITED` 鍔犱竴浠芥湇鍔＄宕╂簝
鎶ュ憡,瀹冪殑 "world loaded (markers)" 鍙槸鏈嶅姟绔俊鍙枫€?

鍙﹀閲忓埌涓€鏉?*閰嶇疆瑙勫緥**(涓嶆槸缂洪櫡):`-Pack 鈥?-AaLevel 4` 鍦ㄤ袱鏉＄嚎涓婇兘寰楀埌 OptiFine 鑷繁鐨勪竴琛?
`[Shaders] Shaders can not be loaded, Antialiasing is enabled: 4x` 鈥斺€?`antialiasingLevel != 0` 浼氳
`GLX.isUsingFBOs()` 涓哄亣銆乣Shaders.loadShaderPack` 鎷掔粷銆傛墍浠?FXAA 鍙湪**涓嶅甫鍖?*鏃舵祴(鎴浘瀵?,鍏夊奖鍙湪
**涓嶅紑 FXAA** 鏃舵祴銆?

#### 2. 鏂扮己闄?鏈慨,鍙戝竷闃诲):**鎬墿鍏ユ按鍗冲穿鏈嶅姟绔?*

涓や唤宕╂簝鎶ュ憡(`crash-2026-09-22_00.32.53-server.txt` 1.20.6銆乣00.38.33-server.txt` 1.20.2):

```
java.lang.NoSuchMethodError: 'void net.minecraft.world.entity.Mob.lambda$jumpInFluid$3(
    net.neoforged.neoforge.fluids.FluidType)'
  at net.minecraft.world.entity.Mob.jumpInFluid(Mob.java:1578) <- LivingEntity.aiStep <- Mob.aiStep
  <- Monster.aiStep <- LivingEntity.tick <- Mob.tick <- Creeper.tick <- ServerLevel.tickNonPassenger
```

閲忓埌鐨勪簨瀹?

* 浜や粯鐨?`Mob` **鑷唇**:鐢?`-Doptifineoforge.dump` 鎶撲笅鏉ュ啀鐢?`javap -v` 璇?
  `jumpInFluid(Lnet/minecraftforge/fluids/FluidType;)V` 鐨?`invokedynamic` 鍏?bootstrap `MethodHandle` 鎸囧悜
  `Mob.lambda$jumpInFluid$3:(Lnet/minecraftforge/fluids/FluidType;)V`,鑰岀被閲屽氨澹版槑鐫€杩欎釜鏂规硶,涓旇绫荤殑姣忎竴澶?
  `FluidType` 閮芥槸 Forge 鍖呭悕;
* **杩愯鏈熻嚜宸辩殑 `Mob` 鏍规湰娌℃湁杩欎釜 lambda**,`jumpInFluid` 鏄?OptiFine 鍔犵殑(`IForgeLivingEntity.jumpInFluid`,
  鑰岃鎺ュ彛鐨?shim 鏄?172 瀛楄妭鐨勬娊璞″０鏄?;
* 鎵€浠ラ敊璇噷閭ｄ釜 NeoForge 鍖呭悕鐨勬弿杩扮**涓嶅彲鑳芥潵鑷湰鍔犺浇鍣ㄤ氦浠樼殑瀛楄妭**,鍙兘鏉ヨ嚜**瀹冧箣鍚?*鐨勬煇涓?pass銆?
  杩欎竴鏉″瀹炶涓?閲忓埌鐨勭姸鎬?,涓嶆槸宸茶瘉鏄庣殑鏈哄埗銆?

瀹舵棌鍏崇郴:杩欎笌 2026-09-20 閭ｆ `Level.m_7654_()`(`Mob.serverAiStep`)鏄悓涓€鏃?涓€涓被閲屾贩浜嗕袱浠界紪璇?,閭ｆ鐢?
SRG 娈嬬暀淇缁撴竻;鐜板湪鍓嶇嚎绉诲埌浜?fluid-jump 閽╁瓙銆?

**鏀跺灏濊瘯(宸茶瘉浼?**:缁欎袱鏉＄嚎鐨?keep plan 鍔?`net/minecraft/world/entity/Mob	*`,閲嶅缓(1.20.6 1751844 瀛楄妭銆?
1.20.2 1724874 瀛楄妭,瀹¤鍧?0 findings)鍚庤繍琛?鈥斺€?涓ゆ潯绾?*杩炴爣棰樼晫闈㈤兘鍒颁笉浜?*:

* 1.20.2 宕╁湪 `EntityType.<clinit>` 鈫?`Items.<clinit>` 鈫?`Blocks.<clinit>` 鈫?`Bootstrap.bootStrap`,鏂规硶浣撴槸鍧忕殑
  (`2a2b b708 e1b1` = `aload_0; aload_1; invokestatic; areturn`),鍗?*杞借嵎 Mob 閲屽埆鐨勪氦浠樼被瑕佺敤鐨勬垚鍛樹笉鍦?*;
* 1.20.6 瀹炶川涓婁竴鏍?鑰屼笖宕╂簝鎶ュ憡閭ｆ潯璺篃鎺ョ潃澶辫触(`Shaders.<clinit>` 閲?`Minecraft.getInstance()` 涓?null,
  鐢?`CrashReporter.extendCrashReport` 璧板埌)鈥斺€?涓?keep plan 鑷繁鐨勬敞閲婇噷 `GlDebug` 閭ｆ鍚屼竴绉?鎶ュ憡閮借鎺╃洊"銆?

鎵€浠?鏁寸被淇濇垚杩愯鏈?鍦?`Mob` 涓婅涓嶉€?鑰?carry 淇**娌¤兘瑕嗙洊**杞借嵎鍏跺畠绫诲 `Mob` 鐨勮皟鐢ㄣ€傝琛屽凡浠庝袱涓鍒掗噷
鍒犻櫎,涓ゆ潯绾块噸寤哄苟澶嶉獙(楠屾敹 STARTED / user / sound / 0 宕╂簝)銆?*缂洪櫡淇濇寔鏈慨**,涓婇潰鎵€鏈夐噺娴嬮兘鐣欐。,涓や釜鍊欓€夋柟鍚?
(1) 鎵惧嚭鎶?Forge 寮曠敤鏀瑰啓鎴?NeoForge 鐨勯偅涓?pass(閿欒閲岀殑鎻忚堪绗︽槸**璋冪敤渚?*),璁╁畠杩炵鏈?lambda 涓€璧锋敼,鎴栬€?
涓や釜閮戒笉鏀?(2) 涓嶇敤"鏁寸被淇?,鑰屾槸鎶婃惡甯?Forge lambda 鐨?*閭ｅ嚑涓垚鍛?*淇濇垚杩愯鏈熺殑,鍚屾椂淇濊瘉杞借嵎鍏跺畠鎴愬憳杩樺湪 鈥斺€?
涔熷氨鏄?carry 淇鏈潵璇ユ彁渚涚殑閭ｄ唤闂寘銆?

#### 3. 涓ゆ潯 harness 缂洪櫡(宸蹭慨)

1. **harness 鐨勮緭鍑烘枃浠惰閲嶅閲嶅畾鍚?*:`run-save-shaders-all.ps1` 鎶婃瘡鏉＄嚎鐨勮緭鍑烘姄鍒?
   `logs\save-shaders-<mc>-<tag>.out.log`,鑰岃繖姝ｆ槸 `test-save-shaders.ps1` 閲嶅畾鍚戝惎鍔ㄥ櫒杈撳嚭鐢ㄧ殑鍚屼竴涓枃浠?鈥斺€?
   鍏朵腑涓€涓噸瀹氬悜澶辫触(`The process cannot access the file ... because it is being used by another process`),
   鍒ゅ畾鍧楁暣娈电己澶?浜庢槸姣忎竴琛岄兘璇绘垚 `FAILED`銆佸厜褰变笌 FXAA 涓ゅ垪绌恒€?*鍚屽舰鐘剁殑鏃ф棩蹇椾篃鏄鎴柇鐨?*,涔熷氨鏄
   浠ュ墠閭ｅ紶"save+shaders"琛ㄨ鐨勬槸娈嬬己鏂囦欢銆傜幇鍦ㄦ敼鍐?`鈥?aa0.harness.log`銆?
2. **琛ㄦ牸鐨勬鍒欑己 `(?m)`**,`shader pack loaded : 鈥 姘歌繙鍖归厤涓嶄笂(瀹冧笉鍦ㄦ暣娈垫枃鏈湯灏?,鎵€浠ュ嵆浣垮厜褰辩湡鐨勫姞杞戒簡,
   涓ゅ垪浠嶇劧鏄剧ず `?`銆?

#### 4. 濡傚疄璁颁笅鐨勬柊宸紓:1.20.6 鐨?stderr 鍩虹嚎

鍚屼竴杞噷 1.20.6 鐨勯獙鏀朵粛鐒跺洓椤瑰叏涓€? 宕╂簝,浣?stderr 鏄?**17856 瀛楄妭**,鑰岃褰曞€兼槸 **0**(`[OptiFine]` 琛屾暟涔熶粠
231 鍙樻垚 216)銆傚唴瀹归噺鍒扮殑鏄?**6 娈?*鍚屽舰鐘剁殑 OptiFine 鍙嶅皠 NPE:

```
java.lang.NullPointerException: Cannot invoke "java.lang.Class.getDeclaredFields()" because "cls" is null
  at net.optifine.reflect.FieldLocatorName.getDeclaredField <- ReflectorField.resolve
  <- ReflectorResolver.resolve <- GameRenderer.frameInit
```

鍙鐜?杩炵画涓夋鍚屼竴鏁板€?,骞朵笖宸茬粡**鎺掗櫎**浜嗕袱涓珜鐤?鎶?`shaderpacks` 鐩綍娓呯┖鍚庝粛鏄?17856;鎶?Forge stub 鎹㈠洖
淇鍓嶇殑 61 涓被閲嶅缓鍚庝粛鏄?17856銆傚墿涓嬬殑瀚岀枒鏄湰娆＄殑 carry 淇鎴?gamedir 閲岀疮绉殑鐘舵€?saves 绛?,**灏氭湭褰掑洜**,
鎵€浠ヨ繖鏉＄畻"鍩虹嚎宸紓銆佸師鍥犳湭鏄?,涓嶆槸閫氳繃銆?

### 鍗佷簩銆?026-09-22 鍑屾櫒(缁?:鎬墿鍏ユ按宕╂簝鐨?*鏍瑰洜涓庝慨澶?* 鈥斺€?鍦ㄨ鍒掔敓鎴愬櫒閲?涓旂湡鏈哄楠岄€氳繃

涓婁竴鑺傜殑缂洪櫡宸茬粡瀹氫綅骞朵慨濂?淇湪涓€涓?*宸茬粡瀛樺湪鐨勮鍒?*涓?`MemberRestorePlan.missingMethods` 瀵硅繍琛屾湡鐨?
`synthetic`(`lambda$`/`access$`)閲囧彇鐨勬槸"鍙**鍚嶅瓧**鍦ㄨ浇鑽烽噷鍑虹幇杩囧氨璺宠繃",娉ㄩ噴閲屽啓鏄庝簡瀹冪殑鐞嗙敱(鍚屽悕鍙兘鏄?
涓や釜涓嶅悓鐨勬柟娉?杩欐椂杞借嵎鑷繁鐨勪綋鎵嶆槸瀵圭殑閭ｄ釜)銆傛湰妗堟鏄偅涓悊鐢辩殑**鍙嶉潰**:鍚嶅瓧鍑虹幇浜?浣?*鎻忚堪绗︿笉鍚?* 鈥斺€?
OptiFine 鐨?`lambda$jumpInFluid$3(Lnet/minecraftforge/fluids/FluidType;)V` 瀵硅繍琛屾湡鐨?
`lambda$jumpInFluid$3(Lnet/neoforged/neoforge/fluids/FluidType;)V`銆備簬鏄?

* 杩愯鏈熺殑 `jumpInFluid` **琚仮澶?*浜?杞借嵎閭ｄ唤鏄?Forge 绫诲瀷鐨勯挬瀛?鑰岃繍琛屾湡鐨勮皟鐢ㄦ柟瑕?NeoForge 閭ｄ唤),
* 浣嗗畠鐨勪綋瑕佽皟鐢ㄧ殑閭ｄ釜 lambda **鏃笉鍦ㄨ鍒掗噷銆佷篃娌″啓杩?donor**(鍥犱负鍚屽悕),
* 浜や粯鍑哄幓鐨勭被灏卞甫鐫€涓€涓?璋冪敤涓嶅瓨鍦ㄧ殑鎴愬憳"鐨勪綋 鈥斺€?绗竴娆℃湁鎬墿鍏ユ按,鏈嶅姟绔綋鍦烘銆?

淇硶:杩愯鏈熺殑 synthetic 鍦?鍚嶅瓧鍦ㄨ浇鑽烽噷瀛樺湪銆佷絾**涓嶆槸杩欎釜鎻忚堪绗?*"鏃朵篃瑕佹仮澶?涓や釜 helper 鍚屽悕涓嶅悓鎻忚堪绗﹀湴
鍏卞瓨鏄悎娉曠殑,鍚勮嚜璋冪敤鍚勮嚜閭ｄ唤銆?

閲忓埌鐨勬晥鏋?鐢熸垚鍣?:1.20.6 璁″垝 282 鈫?**297** 鏉°€乨onor 鐨?`Mob` 1381 鈫?**1463** 瀛楄妭(鍚屾椂澹版槑
`jumpInFluid` 涓?`lambda$jumpInFluid$3`);1.20.2 璁″垝 263 鈫?**278** 鏉°€乨onor 1044 瀛楄妭(甯?
`lambda$jumpInFluid$4`)銆傞噸寤?1.20.6 1757023 瀛楄妭(SHA-256 `CFB3D52F5CD97ACC鈥)銆?
1.20.2 1730257 瀛楄妭(SHA-256 `3054F69257D80F54鈥),瀹¤鍧?0 findings銆傞殢鍚庡湪**鍚屾牱鐨勯厤缃?*(400 绉掋€佸甫
MakeUp 鍏夊奖鍖呫€佷笉寮€ FXAA)涓婂璺?

| 绾?| 鍒ゅ畾 | 澹伴煶 | 涓栫晫 | 宕╂簝 | 鍏夊奖鍖?| 鏃ュ織閲岀殑 `jumpInFluid` |
|---|---|---|---|---|---|---|
| 1.20.6 | **STARTED** | yes | yes(6 涓?region 鏂囦欢 + level.dat) | **0** | 宸插姞杞?| 鏃?|
| 1.20.2 | **STARTED** | yes | yes(6 涓?region 鏂囦欢 + level.dat) | **0** | 宸插姞杞?| 鏃?|

鑰屽嚑鍒嗛挓鍓嶅悓涓€閰嶇疆鍦ㄤ袱鏉＄嚎涓婇兘宕?`crash-鈥?1.22.40-server.txt` 鐨?`Mob.java:1578`銆?
`crash-鈥?1.29.22-server.txt` 鐨?`Mob.java:1492`)銆備箣鍓嶈瘯杩囩殑"`Mob` 鏁寸被淇?鏄敊鐨勫伐鍏?宸茶瘉浼苟鎾ゅ洖);
**璁″垝鐢熸垚鍣ㄦ墠鏄鐨勫湴鏂?*銆?

### 鍗佷笁銆?.20.6 stderr 閭?17856 瀛楄妭鐨?*瀹氫綅**(浠嶆湭淇?浣嗕笉鏄己闄?:OptiFine 鍙互鐐瑰彿瀛楃涓叉彁鍒扮殑閭?8 涓?Forge 绫?

绗崄涓€鑺傞噷閭ｄ釜"鍩虹嚎宸紓"杩欒疆寰€涓嬫帹浜嗕竴姝?閲忓埌鐨勬槸:

* 閭?6 娈?NPE 鐨勬潵婧?**OptiFine 鑷繁鐨勫弽灏勮〃閲岀偣鍚嶇殑 Forge 绫讳笉瀛樺湪**銆傚畠鑷繁鐨勬棩蹇楁妸杩欎欢浜嬭寰楀緢娓呮
  (stdout,鍚勪竴琛?:`[OptiFine] (Reflector) Class not present: net.minecraftforge.common.extensions.IForgeEntity`
  / `net.minecraftforge.logging.CrashReportExtender` / `net.minecraftforge.client.ForgeHooksClient` /
  `net.minecraftforge.client.settings.KeyConflictContext` / `KeyModifier` / `net.minecraft.launchwrapper.Launch` /
  `net.minecraftforge.versions.forge.ForgeVersion` / `net.minecraftforge.internal.BrandingControl` /
  `net.minecraftforge.fml.loading.ImmediateWindowHandler`;
  闅忓悗 `GameRenderer.frameInit` 瑙ｆ瀽杩欎簺瀛楁鏃?*娌℃湁鍒ょ┖**(`FieldLocatorName.getDeclaredField` 鐩存帴瀵?null 璋?
  `getDeclaredFields()`),浜庢槸姣忔瑙ｆ瀽閮藉湪 stderr 涓婄暀涓?6 娈?NPE銆?
* **涓轰粈涔堢幇鍦ㄧ殑 shim 瑕嗙洊涓嶅埌瀹冧滑**:杩?8 涓悕瀛楀彧浠?*鐐瑰彿瀛楃涓?*鍑虹幇鍦?OptiFine 鐨勫弽灏勮〃閲?鑰?
  `ForgeApiShims.referencedTypes` 鎵殑鏄?*甯搁噺姹犻噷鐨勭被鍨嬪紩鐢?*(鏂滄潬褰㈠紡),鎵€浠ュ畠浠棦涓嶅湪 66 涓?stub 閲?涔?
  娌¤鎵弿鍒?鈥斺€?宸插湪 jar 閲屾牳瀵?杩?8 涓?`net/minecraftforge/...class` 鍏ㄩ儴 **ABSENT**(鑰?stub 鎬绘暟鏄?66)銆?
* **椤烘墜閲忓埌鐨勮竟鐣?*:鎶婃壂鎻忔敼鎴?涔熻鐐瑰彿鍚?涔嬪悗,绫诲瀷鏁颁粠 59 娑ㄥ埌 **99**,浣嗛噷闈㈡贩杩涗簡鎴柇鍨冨溇
  (`net/minecraftforge/cli`銆乣cliHent/model/data/ModelDataManager`,鏉ヨ嚜甯搁噺姹犻噷琚嫾鎺ョ殑瀛楃涓?,鎵€浠ヨ繖涓?
  鏀规硶**娌℃湁閲囩敤**(宸叉挙鍥?,姝ｇ‘鐨勫舰鐘舵槸**鍙彇鍙嶅皠琛ㄩ噷閭ｄ簺瀹屾暣绫诲悕**鐨勬湁鐣屽垪琛ㄣ€?

缁撹:杩欐潯宸紓**涓嶆槸缂洪櫡銆佷篃涓嶈嚧鍛?*(鍥涢」妫€鏌ュ叏涓€佸穿婧冩姤鍛?0),瀹冩槸 OptiFine 鑷繁鐨勫弽灏勫缂哄け绫讳笉鍒ょ┖鍦?
stderr 涓婄暀涓嬬殑鏃ュ織;瑕佹竻闆跺氨寰楄ˉ涓婇偅 8 涓?鎴栨洿澶?鐐瑰彿鍚?stub 鈥斺€?璁颁负涓嬩竴姝?鑰屼笉鏄湰杞『鎵嬪仛鐨勪簨銆?

### 鍗佸洓銆?026-09-22 鍑屾櫒(鍐嶇画):**1.20.1 涔熻繘涓栫晫浜?*(绗笁鏉?,浠ュ強 1.20.4 鐨勮彍鍗曢┍鍔ㄧ幇鐘?

#### 1. 1.20.1:楠屾敹 + 寤烘。鍏夊奖娴嬭瘯閮介€氳繃

鐢?*鍚屼竴濂椾慨澶?*閲嶅缓鍚?閲嶆柊鐢熸垚 donor:`MemberRestorePlan` 鐨?synthetic 瑙勫垯 + stackEffect;鍏朵綑淇鏈氨鍦ㄥ垎鏀噷):
jar **1778641** 瀛楄妭,SHA-256 `ACDAEFB46C1AF5E344A159F9DE875560DA420846ECE64B90C4FB92815B5F4491`,
瀹¤ 619 涓被 0 findings,keep plan 17 琛?璁″垝 904 鏉?donor 157 涓€?

| 娴嬭瘯 | 缁撴灉 |
|---|---|
| 鍥涢」楠屾敹 | STARTED / user yes / sound yes / 宕╂簝 0 / stderr **0** = 璁板綍鍊?|
| 寤烘。 + quick play(鏃犲寘) | **STARTED / sound yes / 涓栫晫 yes(10 涓?region 鏂囦欢 + level.dat)/ 宕╂簝 0** |
| 寤烘。 + MakeUp 鍏夊奖鍖?| **STARTED / sound yes / 涓栫晫 yes(10 涓?region 鏂囦欢 + level.dat)/ 宕╂簝 0 / `[Shaders] Loaded shaderpack: MakeUp-UltraFast-9.5e.zip`** |

鍚屼竴閰嶇疆鍦ㄦ湰杞紑濮嬬殑**鏃?jar** 涓婃槸宕╃殑(`crash-2026-09-22_01.58.53-client.txt`;
`NullPointerException: Cannot read field "f_108590_" because "this.f_109059_.f_91074_" is null` at
`GameRenderer.m_109089_`,鍗?鐜╁杩樻病鍒板氨娓叉煋"閭ｄ竴鏃?,閲嶅缓鍚庡氨娌′簡 鈥斺€?涓?1.20.2 涓婂悓涓€涓牴鍥?杞借嵎鑷繁鐨?
`Util`/鎶閾?琚?carry 淇鎺ヤ笂鍚庣帺瀹剁湡鐨勫埌鍦虹殑缁撹涓€鑷淬€?

**杩欐潯绾跨殑閲嶅缓鍛戒护鏈変袱涓潙,閮借鍦ㄨ繖閲?*(`rebuild-120x-line.ps1` 鐨勬敞閲婃病鍐?:

* 蹇呴』 `-InstallerArtifact forge`:1.20.1 鐨勫畨瑁呬骇鐗╁湪 `libraries\net\minecraftforge\forge\1.20.1-47.4.23\`,
  榛樿鐨?`neoforge` 浼氳鑴氭湰鍦?runtime classpath"涓€姝ユ姏 `no installed artifacts ...`;
* **涓嶈兘**浼?`-SrgMappings`/`-ObfOfficial`:杩欎竴绾跨殑杩愯鏈熸湰韬槸 SRG 鍛藉悕鐨?`SrgRemap` 浼?*鎸夎璁℃嫆缁?*
  ("refusing to rewrite: this runtime is SRG-named (49686 members match m_/f_), so the payload needs no
  rewriting on this line",閫€鍑虹爜 2),鑴氭湰闅忓悗鎶?"the SRG remap produced no ...",jar 淇濇寔鏃х増鏈?鈥斺€?涓€娆?
  "閲嶅缓鎴愬姛"鐨勫亣璞?鏃ュ織閲?Gradle 閭ｈ鏄?BUILD SUCCESSFUL)銆?

#### 2. 1.20.4:鏍囬鐣岄潰鍙氦浜掑凡瀹炴祴,浣嗚彍鍗曢┍鍔?*杩樻病璧伴€?*

杩欒疆鎶婅繖鏉＄嚎鐨勯┍鍔ㄥ仛寰楁洿鍙潬浜?浣嗕笘鐣屽垪琛ㄩ偅涓€琛屼粛鏈懡涓?

* `click-at.ps1 -Focus`(AttachThreadInput + SetForegroundWindow)涔嬪悗,(427,200) 鐨勭偣鍑?*纭疄鐢熸晥**
  (title 鈫?SelectWorldScreen,澶氭澶嶇幇);
* 鏂板姞鐨?`capture-window.ps1 -Screen` 鐢?*灞忓箷鎷疯礉**浠ｆ浛 PrintWindow(绐楀彛鍏堣鎶埌鍓嶅彴),
  鍥犱负杩欐潯绾夸笂 PrintWindow 杩斿洖鐨勬槸**杩囨湡鍚庡彴瀛樻。**(涓栫晫鍒楄〃鐣岄潰鏃舵姄鐨勫浘涓庢爣棰樼晫闈㈠悓灏哄鍚屼寒甯?;
* 浣?*鐐瑰嚮鐨勭敓鏁堟槑鏄炬粸鍚?*:涓夋 (427,200) 鐐瑰嚮鍚勮嚜绛変簡鏈€澶?45 绉掍粛鏈湅鍒?SelectWorldScreen,鑰屽埌鍚庨潰璇?tracer
  鏃跺畠宸茬粡鏄?SelectWorldScreen 鈥斺€?涔熷氨鏄?鎴浘閲岀殑灞忓箷"鎬昏惤鍚庝簬"tracer 閲岀殑灞忓箷",浜庢槸鎸夋埅鍥惧潗鏍囧幓鍙屽嚮涓栫晫琛?
  鏃?娓告垙杩樺湪鏍囬鐣岄潰銆傚凡鎶婄瓑寰呮敼鎴?*杞 tracer**,骞跺姞浜?浠庢椿鎴浘閲岃嚜鍔ㄦ壘鏈€浜í甯﹀綋涓栫晫琛?鐨勯€昏緫(璁板綍鍦ㄨ剼鏈噷),
  浠嶉渶瑕佸湪**瀹夐潤鏈哄櫒**涓娿€佷互 tracer 涓哄噯閫愭鎺ㄨ繘;鏈熼棿鏈哄櫒涓婂彟鏈夊埆浜虹殑瀹㈡埛绔?
  (`Minecraft* 1.21.6 - Singleplayer`銆乣Minecraft* 26.1.2`),鎸夐挳鏌ユ壘浼?姝ｇ‘鍦?鎷掔粷鍦ㄤ袱涓尮閰嶇獥鍙ｄ箣闂寸寽銆?

### 鍗佷簲銆?026-09-22 鍑屾櫒(涓夌画):**1.20.4 涔熻繘涓栫晫浜?*(绗洓鏉?;椤哄甫鎶撳埌骞朵慨鎺変竴涓?*鍏夊奖涓嬬湡鏈哄穿婧?*(寮€鍏宠〃)

杩欎竴杞殑璧风偣鏄袱涓?*閲囬泦宸ュ叿缂洪櫡**(閮藉凡閲忔祴銆侀兘宸蹭慨),淇ソ涔嬪悗 1.20.4 鐨勮彍鍗曢┍鍔ㄤ竴娆″氨璺戦€氫簡銆?

#### 1. 鎶撳浘鎶撳埌鐨勬槸**琚伄鎸＄獥鍙?*鐨勭敾闈?

`logs\world1204-title.png` 涓?`logs\world1204-list.png` 鐩搁殧绾?60 绉?鐣岄潰鍒嗗埆鏄爣棰樼晫闈笌涓栫晫鍒楄〃,鍐呭鍗?
**鍑犱箮涓€鑷?*:鎸?3 鍍忕礌鎶芥牱 5 涓囦釜鐐瑰彧鏈?**2242** 鐐逛笉鍚?閫愯浜甫杈圭晫瀹屽叏鐩稿悓銆傚師鍥犱笉鏄?PrintWindow 鐨勮繃鏈?
鍚庡彴瀛樻。,鑰屾槸 `CopyFromScreen` 璇荤殑鏄?*灞忓箷**:娓告垙绐楀彛褰撴椂骞朵笉鍦ㄦ渶鍓?鎶撳埌鐨勫氨鏄帇鍦ㄥ畠涓婇潰鐨勯偅涓獥鍙ｃ€?

淇硶(`capture-window.ps1 -Screen`):鍏堝弽澶嶆姮绐?*骞舵牳瀵?* `GetForegroundWindow`,`SetForegroundWindow` 娌＄敓鏁堟椂
閲嶈瘯 5 娆?鎶笉涓婂幓灏?*涓嶆姄灞忓箷**,鏀硅蛋 `PrintWindow` 骞舵妸 `OCCLUDED: ... treat these pixels as unverified`
鏄庣‘鎵撹繘鎶ュ憡;鎶撶殑鏄?`GetClientRect` 鐨?*瀹㈡埛鍖?*(姝ゅ墠鐢ㄧ殑鏄惈鏍囬鏍?杈规鐨勭獥鍙ｇ煩褰?灏哄瀵逛笉涓婄偣鍑诲潗鏍?銆?
淇ソ鍚庣殑鍚屼竴浣嶇疆鎶撳浘 **476448** 瀛楄妭(姝ゅ墠 14407),`click-at.ps1 -FindButtons` 绗竴娆＄湡鐨勬姤鍑轰簡鏍囬鐣岄潰鐨勬寜閽?
(`426,223` 绛?,鑰屼笉鍐嶆姤鍑鸿竟妗嗕笌妗岄潰浠诲姟鏍忋€?

#### 2. 鐐瑰嚮鐨?*钀界偣**涓嶆槸娑堟伅閲岀殑鍧愭爣

Minecraft 鐨?`MouseHandler` 鐢ㄧ殑鏄?GLFW 鍏夋爣浣嶇疆鍥炶皟绱姞鍑烘潵鐨勫潗鏍?**涓嶆槸** `WM_LBUTTONDOWN` 鐨?lParam銆?
鎵€浠?`PostMessage` 鍙戝嚭鐨勭偣鍑讳細钀藉湪**鐪熷疄鍏夋爣鎵€鍦ㄧ殑浣嶇疆**:杩欐濂借В閲?鍚屼竴涓?(427,200) 杩欐鎵撳紑浜?
SelectWorldScreen銆佷笅娆′粈涔堥兘娌″彂鐢?鑰屼袱娆℃姤鍛婇兘鏄剧ず绐楀彛鍦ㄥ墠鍙?銆?

淇硶(`click-at.ps1`):鏂板 `-RealClick` / `-RealDoubleClick`(`ClientToScreen` + `SetCursorPos` + `mouse_event`,
骞舵墦鍗扮湡瀹炲厜鏍囪惤鍦ㄥ睆骞曞摢涓€鐐广€佺獥鍙ｆ槸鍚﹀湪鍓嶅彴)涓?`-Key`(VK 搴忓垪,`keybd_event`,鑷姩鏌?scancode)銆?
涓栫晫娴嬭瘯閲?*鐪熷疄鍏夋爣鐐瑰嚮浼樺厛**,PostMessage 淇濈暀涓哄鐓с€?

#### 3. 涓栫晫鍒楄〃鐨勫潗鏍囦笉鍐嶉潬鐚?浠庡鎴风鑷繁鐨勫瓧鑺傜爜閲岃鍑烘潵

`javap -p -c` 1.20.4 瀹㈡埛绔?`SelectWorldScreen.init` / `WorldSelectionList` / `AbstractSelectionList` /
`CommonInputs`)缁欏嚭鍏ㄩ儴甯冨眬(鍗曚綅鏄?GUI 缂╂斁鍧愭爣):

| 鍏冪礌 | 瀹炴祴甯冨眬 |
|---|---|
| 鍒楄〃鎺т欢 | `x=0, y=48, w=灞忓, h=灞忛珮-112, itemHeight=36` |
| 绗?i 琛屼腑蹇?| `y = 52 + 36*i + 18` |
| 琛岀殑鍙€?x | 鍙湪 `[rowLeft, rowRight]` 鍐?鍏朵腑蹇?= **灞忓箷姘村钩涓偣** |
| Play Selected World | `(width/2-154, height-52, 150, 20)`,涓績 `(width/2-79, height-42)` |
| 杩涘叆涓栫晫鐨勬寜閿?| `WorldSelectionList.keyPressed` 瀵?ENTER(257)/KP_ENTER(335)/SPACE(32) 鐩存帴 `joinWorld()` |

`options.txt` 鏄?`guiScale:0`(鑷姩),854x480 涓嬭嚜鍔ㄥ€间负 **2**(3 浼氬緱鍒?284x160,浣庝簬 320x240 涓嬮檺)銆?
鍏堝墠閭ｆ壒鍊欓€?`225,70/110/150`銆乣300,80`)姝ｆ槸**鍗婁釜閮借惤鍦ㄨ澶?*:220 瀹界殑琛屼粠缂╂斁鍚庣殑 105 寮€濮嬨€?

#### 4. 1.20.4 **杩涗簡涓栫晫**(绗洓鏉?

2026-09-22 02:50 閭ｆ(`logs\world-1204-run13.txt`):鏍囬鐣岄潰 鈫?鐐?Singleplayer `(426,223)` 鈫?SelectWorldScreen 鈫?
鐪熷疄鍏夋爣鍗曞嚮鍒楄〃琛?`(427,140/212/284/356)` 涔嬩竴 鈫?鐪熷疄鐐瑰嚮 Play Selected World `(269,396)` 鈫?
`GenericDirtMessageScreen` x3 鈫?`ProgressScreen` 鈫?`LevelLoadingScreen` 鈫?鈥?鈫?`null` 鈫?`PauseScreen`;
`joined=yes`銆乣session.lock` 宸插啓鍏ャ€?*0** 宕╂簝鎶ュ憡銆? NullPointerException銆? VerifyError銆?

甯﹀厜褰卞寘鐨勫悓涓€鏉¤矾(`logs\world-1204-run15-pack-switchmap.txt`,MakeUp-UltraFast-9.5e):涓栫晫 yes銆?
`[Shaders] Loaded shaderpack: MakeUp-UltraFast-9.5e.zip`銆佸嚭鐜?`Saving and pausing game`(鑷姩淇濆瓨)銆佸穿婧?0銆?

#### 5. 杩欐潯绾夸笂淇帀鐨勪袱涓己闄?

**(a) `ModelPart.getChild` 鈥斺€?涓?1.20.1/1.20.2 鍚屼竴鏉′慨澶?杩欐潯绾挎紡浜?宸蹭慨)**

淇墠姣忔杩愯 `logs\latest.log` 閲岄兘鏈変竴娆?
`IllegalStateException: Failed to create model for minecraft:skull`,鏍瑰洜鏄?
`NullPointerException: Cannot invoke "ModelPart.getChild(String)" because "this.head" is null`
at `DragonHeadModel.<init>` 鈫?`SkullBlockRenderer.createSkullRenderers`;鍚庢灉鏄?*鏁翠釜澶撮/澶撮娓叉煋鍣ㄨ涓嶄笂**銆?
`javap` 璇佹嵁:杩欐潯绾跨殑杞借嵎鍙湁 `ModelPart`(鍏?`getChild` 鎸?`child.getId()` 鍖归厤,鑰?`setId` 鏃犱汉璋冪敤),
**娌℃湁** `PartDefinition`(1.20.6/1.21/1.21.1/1.21.8 鐨勮浇鑽烽兘鏈?,鎵€浠ヨ繍琛屾湡鐑樼剻鍑烘潵鐨勯儴浠?id 鍏ㄦ槸 null銆?
鏌ユ壘鍏ㄨ繑鍥?null銆備慨娉曞氨鏄?keep plan 閲岄偅涓€琛?淇濈暀杩愯鏈熻嚜宸辩殑 `children.get(name)`)銆備慨鍚?鍚屾牱鎼滅储 0 娆?
`latest.log` 閲?**0 涓?ERROR**銆?

**(b) `ModelBlockRenderer$1` 鐨勫紑鍏宠〃 鈥斺€?鍏夊奖涓?*鐪熸満宕╂簝**(宸蹭慨,褰卞搷 9 鏉＄嚎)**

```
java.lang.NullPointerException: Cannot load from int array because
  "net.minecraft.client.renderer.block.ModelBlockRenderer$1.$SwitchMap$net$neoforged$neoforge$common$util$TriState" is null
  at net.minecraft.client.renderer.block.ModelBlockRenderer.tesselateBlock(ModelBlockRenderer.java:72)
  at net.minecraft.client.renderer.entity.FallingBlockRenderer.render(FallingBlockRenderer.java:41)
  at net.net.optifine.shaders.ShadersRender.renderShadowMap(ShadersRender.java:527)
```
(`game\neoforge-20.4.251\crash-reports\crash-2026-09-22_02.54.24-client.txt`)

鏍瑰洜:杞借嵎鐨?`ModelBlockRenderer.tesselateBlock` 瀵?NeoForge 鐨?`TriState` 鍋?switch,缂栬瘧鍣ㄧ敓鎴愮殑鏄犲皠琛ㄤ綇鍦?
`ModelBlockRenderer$1` 閲?鑰岃浇鑽疯嚜宸遍偅浠?`ModelBlockRenderer$1` 鍙０鏄?`$SwitchMap$...Direction` 涓€寮犺〃銆?
浜庢槸 `MemberRestorePlan` 鎶婄己鐨勯偅寮犺〃**褰撴櫘閫氶潤鎬佸瓧娈佃ˉ杩?donor** 鈥斺€?`javap` 鍑烘潵灏变竴琛?
`public static int[] $SwitchMap$net$neoforged$neoforge$common$util$TriState;`;**鍒濆鍖栧畠鐨?`<clinit>` 鍦ㄨ繍琛屾湡閭ｄ唤绫婚噷**,
donor 甯︿笉杩囨潵 鈬?瀛楁鍦ㄣ€佸€间负 null 鈬?鎶曞奖璐村浘閲屼竴纰板埌瀹炰綋鏂瑰潡灏卞穿銆?

淇硶:鏁寸被淇濈暀杩愯鏈熺殑 `ModelBlockRenderer$1`(杩愯鏈熼偅浠戒袱寮犺〃閮芥湁,鏄浇鑽烽偅浠界殑**瓒呴泦**,鎵€浠ュ畨鍏?銆?
`build-jars` 鍥犳鎶婅绫荤殑 patch 鏉＄洰**涓㈡帀**,杈撳嚭閲岃兘鐪嬪埌
`patch entries dropped for 1 class(es): [net/minecraft/client/renderer/block/ModelBlockRenderer$1]`銆?

澶嶉獙:鍚屼竴瀛樻。銆佸悓涓€鍏夊奖鍖呫€佽繖娆¤窇 150 绉?鍚竴娆¤嚜鍔ㄤ繚瀛?鈬?涓栫晫 yes / 鍏夊奖鍖?loaded / **宕╂簝 0 / NPE 0**銆?
**濡傚疄杈圭晫**:宕╃殑閭ｆ鏄嚜鐒剁敓鎴愮殑钀藉湴鐮傜牼(`Falling Gravel` at 22,2,4),涓嶆槸琚己鍒剁殑瑙﹀彂鐐?鎵€浠ヨ繖鏄?
"鍚屽満鏅鐜?1 娆°€佷慨鍚庡悓鍦烘櫙 0 娆?鐨勮瘉鎹?**涓嶆槸**纭畾鎬х殑 A/B 瀵圭収銆?

褰卞搷闈?閫?jar 閲忔祴):鍚屾牱鐨?donor 鍑虹幇鍦?1.20.6 / 1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8 鐨?
`registered.jar` 閲?1.20.1 / 1.20.2 鐨勮繍琛屾湡娌℃湁 TriState 寮€鍏?涓嶅彈褰卞搷)銆傛湰杞凡鎶婂悓涓€鏉?keep 鍔犲埌
1.20.4銆?.20.6 涓庢湰鍒嗘敮鍏朵綑绾跨殑璁″垝閲?1.20.6 宸查噸寤?楠屾敹 STARTED/user/sound/宕╂簝 0,stderr 浠嶆槸閭ｄ釜宸茬煡鐨?
17856,涓幝у崄涓変竴鑷?,1.21.x 閭?7 鏉＄嚎鐨勯噸寤轰笌鐪熸満澶嶉獙鎺掑湪鍚庨潰銆?

**杩欎粛鏄敓鎴愬櫒鐨勪竴涓己鍙?*:琛ヨ繘鍘荤殑闈欐€佸瓧娈垫病鏈夊垵濮嬪寲璇彞,鑰岀敓鎴愬櫒涓嶄細鎶婅繖绉嶆儏鍐佃嚜鍔ㄦ敼鍒や负"鏁寸被淇濈暀杩愯鏈?銆?
鏈疆鐨勫仛娉曟槸**姣忔潯绾夸竴鏉?keep**(杩炲悓閲忔祴渚濇嵁鍐欏湪璁″垝娉ㄩ噴閲?,鐢熸垚鍣ㄤ晶鐨勮嚜鍔ㄥ垽瀹?*灏氭湭鍋?*銆?

### 鍗佸叚銆?026-09-22 鍑屾櫒(鍥涚画):**鎭㈠璁″垝鏄敓鎴愮墿鍗磋闈欓粯澶嶇敤**(鐪熸満宕╄繃),浠ュ強涓€涓粛鏈慨鐨?`ModelDataManager` 绌哄€?

#### 1. 鏍瑰洜:`rebuild-120x-line.ps1` / `add-line.ps1` 鍙湪**杞借嵎涓嶅瓨鍦?*鏃舵墠璺?`prepare-line`

`work\<mc>\plan\member-restores.txt` 涓?`work\<mc>\plan\donors\` 閮芥槸 `MemberRestorePlan` **鐢熸垚**鐨?鍗村洜涓?
`prepare-line` 琚烦杩囪€岃闈欓粯澶嶇敤銆?.20.4 涓婇噺鍒扮殑鍚庢灉:jar 閲岄偅浠借鍒掓槸 **2026-09-21 23:02** 鐨?鏃╀簬
`da8b09d`(synthetic lambda 瑙勫垯)鐨勬彁浜?浜庢槸**娌℃湁**杩欎竴鏉?

```
M net/minecraft/world/entity/Mob lambda$jumpInFluid$4 (Lnet/neoforged/neoforge/fluids/FluidType;)V
```

鐪熸満涓婅〃鐜颁负(鍚屼竴鍙版満鍣ㄣ€佸悓涓€鏉＄嚎,鍙槸閭ｆ璺戝緱涔?:

```
java.lang.NoSuchMethodError: 'void net.minecraft.world.entity.Mob.lambda$jumpInFluid$4(net.neoforged.neoforge.fluids.FluidType)'
  at net.minecraft.world.entity.Mob.jumpInFluid(Mob.java:1496)
  at net.minecraft.world.entity.LivingEntity.aiStep(LivingEntity.java:2646)
  at net.minecraft.world.entity.monster.Creeper.tick(Creeper.java:161)
  at net.minecraft.server.level.ServerLevel.tickNonPassenger(ServerLevel.java:755)
```
(`game\neoforge-20.4.251\crash-reports\crash-2026-09-22_03.16.11-server.txt`,闆嗘垚鏈嶅姟绔穿婧?鈫?瀹㈡埛绔綋鍦烘姤涓€浠藉穿婧?

淇硶鏈変袱灞?

* **瀵硅繖鏉＄嚎**:鐢?*褰撳墠鍒嗘敮婧愮爜**閲嶆柊缂栬瘧鐢熸垚鍣ㄥ苟閲嶈窇涓€娆?璁″垝浠?264 鏉?/ 72 donor 鍙樻垚 **282 鏉?/ 75 donor**,
  涓婇潰閭ｆ潯 lambda 涓?donor 閲岀殑鍚屽悕鏂规硶閮藉湪浜?`javap` donor 鍙 `public void lambda$jumpInFluid$4(...)`)銆?
* **瀵瑰伐鍏烽摼**:`rebuild-120x-line.ps1` 閲屽姞浜?姣忔閮戒粠鏈垎鏀簮鐮佺紪璇戠敓鎴愬櫒鍐嶉噸绠楄鍒?鐨勪竴姝?鐢熸垚鍣ㄧ紪璇戜笉鍑烘潵
  灏?*鎶涢敊**鑰屼笉鏄户缁祵鏃ц鍒?銆傞『甯﹁涓€涓?PowerShell 5.1 鐨勫潙:`javac ... -d $dir @toolSrc` 杩欑鍚屼竴琛岄噷娣风敤
  splat 鐨勫啓娉曚細鎶?classpath 浼犲潖,javac 鎶?`鏃犳晥鐨勬爣璁? :`;鏀规垚鍏堟嫾 `$javacArgs` 鏁扮粍鍐?`@javacArgs` 灏卞ソ銆?
  `add-line.ps1`(1.21.x 鐢?涓?`prepare-fml10-line.ps1`(FML 10 涓夋潯绾跨敤)**杩樻病鏈?*杩欎竴姝?瀹冧滑涓嬫閲嶅缓鍓嶅繀椤?
  鎸夊悓鏍峰姙娉曢噸绠楄鍒?鈥斺€?杩欎竴鐐瑰凡鍐欒繘鏈疆浜ゆ帴璇存槑銆?

閲嶇畻鍚庣殑 1.20.4:楠屾敹 STARTED/user/sound/宕╂簝 0/stderr **14481** = 璁板綍鍊?甯?MakeUp 鍏夊奖鍖呯殑 180 绉掍笘鐣岃窇
(鍚屼竴鏉¤彍鍗曡矾)= 涓栫晫 yes銆佸厜褰卞寘 loaded銆?*宕╂簝 0**銆乣VerifyError` 0銆乣NoSuchMethodError` 0銆?

#### 2. 浠嶆湭淇?1.20.4 涓?`Level.getModelDataManager()` 杩斿洖 null(宸插畾浣?闈炶嚧鍛?

鍚屼竴娆?180 绉掕窇閲?`latest.log` 鐣欎笅 **1** 娆?

```
Caused by: java.lang.NullPointerException: Cannot invoke
  "net.neoforged.neoforge.client.model.data.ModelDataManager$Active.getAtOrEmpty(net.minecraft.core.BlockPos)"
  because the return value of "net.minecraft.world.level.Level.getModelDataManager()" is null
  at net.minecraft.client.renderer.block.BlockModelShaper.getTexture(BlockModelShaper.java:31)
  at net.minecraft.client.particle.TerrainParticle.updateSprite(TerrainParticle.java:107)
  at net.optifine.reflect.Reflector.call(Reflector.java:1111)
```

`javap` 瀵圭収宸茬粡缁欏嚭鏈哄埗,鍜屼笂闈袱浠舵槸鍚屼竴鏃?杞借嵎缂鸿繍琛屾湡鐨勬垚鍛?鍒濆鍖?:

| | 瀛楁 | 鏋勯€犲櫒 |
|---|---|---|
| 杩愯鏈?`ClientLevel` | `private final net.neoforged.neoforge.client.model.data.ModelDataManager$Active modelDataManager;` | 鍋忕Щ 139-147:`new ModelDataManager$Active(this)` + `putfield` |
| 杞借嵎 `ClientLevel` | `private final net.minecraftforge.client.model.data.ModelDataManager modelDataManager;`(**Forge 绫诲瀷**) | 鑷繁鐨勫亸绉?139-147:鏋勯€?Forge 鐗堝苟鍐欒嚜宸辩殑鍚屽悕瀛楁 |

donor 閲岀‘瀹炲甫浜嗚繍琛屾湡閭ｄ竴浠?`public ... ModelDataManager$Active modelDataManager;`銆?
`public static void optifineoforge$init$modelDataManager(ClientLevel);`銆佷袱涓?`getModelDataManager()`),涔熷氨鏄?
**娉ㄥ叆鐨勫垵濮嬪寲璋冪敤娌℃湁鎶婅繍琛屾湡閭ｅ崐杈圭殑瀛楁鍐欎笂**,浜庢槸 NeoForge 渚ц鍒?null銆傚畠琚?OptiFine 鐨?
`Reflector.call` 鍚炴帀骞惰鎴愭棩蹇?鎵€浠ヤ笉宕?浣?鐮村潖鏂瑰潡鐨勫湴褰㈢矑瀛愬彇涓嶅埌璐村浘"鏄湡鐨勩€?

**濡傚疄杈圭晫**:杩欐潯鏃ュ織鍙湪 1.20.4 涓婂嚭鐜拌繃涓€娆?20.2 / 20.6 鐨勬棩蹇楅噷 0 娆?,鑰屼笁鑰呯殑杞借嵎 `ClientLevel` 閮藉０鏄?
浜?Forge 绫诲瀷鐨勫悓鍚嶅瓧娈?鈥斺€?鎵€浠?鏄繖鏉＄嚎鐨勬敞鍏ユ病鐢熸晥"涓?鍙︿袱鏉＄嚎鍙槸娌¤蛋鍒拌繖涓矑瀛愯矾寰?杩欎袱绉嶈В閲?*灏氭湭鍖哄垎**,
鏈疆娌℃湁鍋氶偅涓尯鍒嗗疄楠屻€傜敓鎴愬櫒渚х殑鑷姩鍒ゆ柇(琛ヤ笉浜嗗垵濮嬪寲鐨勫瓧娈垫敼鍒や负鏁寸被淇濈暀)鍚屾牱浠嶆湭鍋氥€?

### 鍗佷竷銆?026-09-22 鍑屾櫒(浜旂画):1.20.4 鐨?**FXAA 閰嶅鏈疆浠嶆湭娴嬪嚭鏉?*(宸ュ叿鏈韩鏄ǔ鐨?鏄袱娆¤窇鐨?*鐢婚潰**涓嶆槸鍚屼竴涓?,浠ュ強 1.21.x 涓冩潯绾跨殑绂荤嚎绉绘瀹屾垚

#### 1. FXAA:harness 绋炽€佸満鏅笉绋?鈥斺€?濡傚疄璁颁笅"鏈祴鍑?鑰屼笉鏄‖鎶ヤ竴涓柟鍚?

1.20.4 鍙兘璧拌彍鍗曡矾杩涗笘鐣?鎵€浠ヨ繖鏉＄嚎鐨?FXAA 閰嶅鐢?`world-test-1204.ps1 -Frames <dir>` 鍦?*涓栫晫鍐?*鎸夊浐瀹氶棿闅?
鎶撳抚(鍏堟寜 `run-fxaa-capture.ps1` 鐨勯厤鏂?pin:DayTime/GameTime 6000銆佹棤澶╂皵銆佸喕缁撲笘鐣屻€乣-Yaw 0 -Pitch 45`銆?
`ofClouds:3`)銆傜涓夋灏濊瘯(FXAA 0 涓?FXAA 2 鍚?4 甯?`logs\fxaa-1204c-*`)鐨勭粨鏋?

| 閲?| FXAA off | FXAA on |
|---|---|---|
| 甯у唴 12 绉掔殑鍦烘櫙婕傜Щ | **0.0%**(甯?2 vs 甯?3,杈圭紭鑳介噺 鈭?.1%) | **0.1%**(甯?3 vs 甯?4,杈圭紭鑳介噺 0.0%) |
| 甯т寒搴﹀潎鍊?| 188.7 | 45.1 |
| 浜儚绱?>200)鍗犳瘮 | 66.4% | 4.3% |
| 杈圭紭鑳介噺 / 纭竟(甯?4) | 17.40 / 51741 | 4.98 / 10615 |
| 璺ㄩ€夐」閰嶅(off 甯?4 vs on 甯?4) | 鍦烘櫙宸?**92.6%** 鈬?`fxaa-check.ps1` 鍒?**INCONCLUSIVE** | |

涔熷氨鏄:**鍚屼竴娆¤窇鍐呴儴鐢婚潰鏄喕浣忕殑**(12 绉掑唴 0.0%/0.1%,璇存槑鎶撳抚涓庢椂鏈洪兘娌￠棶棰?,浣?*涓ゆ璺戠殑鐢婚潰涓嶆槸鍚屼竴涓?*
(浜害鍒嗗竷 66.4% 浜?vs 4.3% 浜?銆俙fxaa-check.ps1` 鍥犳姝ｇ‘鍦版嫆缁濇妸 71.4% 鐨勮竟缂樿兘閲忓樊璇绘垚 FXAA 鈥斺€?閭ｆ鏄繖涓?
宸ュ叿瀛樺湪鐨勬剰涔?瀹冨湪 1.20.2 涓婂氨鎶撹繃涓€娆″悓绫荤殑鍋囧垽)銆傛満鍒朵笌 1.20.2 閭ｆ潯澶囨敞涓€鑷?`pin-save-state.ps1` 鍐欒繘
`playerdata` 鐨?Rotation **涓嶆槸瀹㈡埛绔疄闄呬娇鐢ㄧ殑閭ｄ唤**(瀹㈡埛绔細鎶婅嚜宸遍偅浠藉啓鍥炲幓),鎵€浠ヤ袱娆¤窇鏈濆悜涓嶅悓;瑕佹妸杩欐潯
绾跨殑 FXAA 娴嬪嚭鏉?闇€瑕佸湪**涓栫晫鍐呮帶鍒跺Э鍔?*(`send-chat.ps1` + `/tp @s ~ ~ ~ 0 45`,杩欒姹傝瀛樻。寮€鐫€浣滃紛)銆?
鏈疆鍒版涓烘,缁撹灏辨槸**"1.20.4 鐨?FXAA 灏氭湭娴嬪嚭"**,涓嶅啓鎴?閫氳繃"涔熶笉鍐欐垚"澶嶇幇澶辫触"銆?

#### 2. 1.21.x 涓冩潯 ModLauncher 绾?1.20.x 鐨勫姞杞藉櫒淇宸?*绂荤嚎**绉绘銆佸苟宸查噸寤?鐪熸満鏈獙)

杩欎竴姝ョ敱涓€涓瓙浠ｇ悊瀹屾垚(鍏ㄩ儴缁撹閮芥潵鑷?`javap` / `StackAudit` / 鐢熸垚鍣ㄨ緭鍑?瀹?*娌℃湁**鍚姩浠讳綍瀹㈡埛绔?:

* **绉绘**:`MemberRestorePlan` 鐨?synthetic 鍚屽悕涓嶅悓鎻忚堪绗﹁鍒欍€佸垵濮嬪寲瀛楄妭鐮佸悎鎴?`stackEffect`/`callEffect` 绛?銆?
  `MemberRestoreTransformer` 鐨?姣忔娉ㄥ叆璋冪敤鍚勮嚜鍘嬩竴涓帴鏀惰€?+ 鏍堟晥搴旈獙鏀?+ 鎻忚堪绗︽鏌?銆乣PatchedClassTransformer`
  鐨?鏁寸被淇濈暀鏃惰ˉ鍥炶浇鑽风嫭鏈夋垚鍛?+ 鎼哄甫闈欐€佸垵濮嬪寲"銆佷互鍙婂睆骞曡拷韪?杩欐潯鍒嗘敮姝ゅ墠**瀹屽叏娌℃湁** tracer)銆?
* **涓ゅ鐢?A/B 鎶撳嚭鏉ョ殑绉绘鑷韩缂洪櫡**(涓嶆槸鎺ㄧ悊鍑烘潵鐨?鏄悓涓€缁勮緭鍏ュ璺戝嚭鏉ョ殑):ASM 鐨?`InsnList.add(node)`
  浼氭妸鑺傜偣**绉诲姩**鍑鸿繍琛屾湡绫?瀵艰嚧姣忔潯绾夸涪鎺?3/3/3/6/6/7 涓垵濮嬪寲鍣?浠ュ強 1.20.x 鐨勯獙鏀堕棬鏄?缁撴瀯寮?鐨勩€佹瘮瀹冭嚜宸?
  娉ㄩ噴閲屾弿杩扮殑妯℃嫙鏇寸獎,鎶婂悎娉曞舰鎬?`MAP_CODEC = Variant.MAP_CODEC.xmap(f,f)`銆乣StackWalker.getInstance(opt)`銆?
  `Component.translatable(...)`)鍒ゆ銆備袱鑰呴兘宸蹭慨,骞惰鍦ㄦ彁浜ら噷銆?
* **璁″垝鏉℃暟/donor 鏁?*(姣忔潯绾?绉绘鍓?鈫?绉绘鍚?:1.21 363鈫?81 / 97鈫?9;1.21.1 293鈫?09 / 79鈫?1;
  1.21.3 293鈫?14 / 78鈫?2;1.21.4 316鈫?36 / 83鈫?6;1.21.6 345鈫?75 / 83鈫?7;1.21.7 355鈫?85 / 87鈫?1;
  1.21.8 353鈫?83 / 88鈫?2銆備竷鏉＄嚎鐨?`StackAudit` findings 5/1/1/1/0/0/0 鈫?**鍏?0**銆?
* **閲嶅缓**:涓冩潯绾块兘閲嶆柊瑁呴厤,涓旀瘡鏉￠兘鎵撳嵃浜?
  `patch entries dropped for 1 class(es): [net/minecraft/client/renderer/block/ModelBlockRenderer$1]`(鍗充笂涓€鑺傜殑
  鍏夊奖宕╂簝淇纭疄杩涗簡杩欎簺 jar);`1.21.4` 涓?`1.21.8` 鐨?jar 宸插鍒跺埌 rig 瀹為檯鍚姩鐢ㄧ殑
  `jars-1.21.4-new` / `jars-1.21.8-payload`(鏃ф枃浠剁暀 `.pre-port-20260922`)銆?
* **鍚屾椂鍙戠幇鐨勪袱涓?rig 绾у潙**(涓?1.20.4 閭ｆ鍚屾簮):`work\1.21.4\optifine-patched.jar` 鏄竴娆?*琚埅鏂殑 239 瀛楄妭**
  鍐欏叆鑰?`add-line.ps1` 浼氬鐢ㄥ畠;`tools-classpath.txt` 浠?`tools-patch-keepfix` 鎵撳ご,鑰岄偅閲岄潰鐨?
  `MemberRestorePlan.class` 鏄?*鍙︿竴鏉＄嚎**鐨勭増鏈?浜庢槸鎵€鏈?`$tools` 璋冪敤(鍖呮嫭 `prepare-line` 绗?3 姝?鐢ㄧ殑閮戒笉鏄?
  鏈垎鏀殑浠ｇ爜 鈥斺€?9/19 閭ｆ壒璁″垝灏辨槸杩欎箞鐢熸垚鐨勩€傝繖涓ょ偣閮藉凡璁板綍,閲嶅缓鏃舵寜"鏄惧紡鍓嶇疆鏈垎鏀紪璇戝嚭鐨勭被"澶勭悊銆?
* **濡傚疄杈圭晫**:杩欎竷鏉＄嚎**浠嶇劧娌℃湁鐪熸満楠岃瘉**(娌℃湁瀹㈡埛绔繘杩囦笘鐣?,鎵€浠ュ畠浠棦涓嶆槸"缁?,涔熶笉鑳界畻鏈疆鍙彂甯?
  涓嬩竴姝ユ槸閫愮嚎楠屾敹 + 杩涗笘鐣?+ 寤烘。鍏夊奖 + FXAA銆?### 鍗佸叓銆?026-09-22 鏃?璁″垝閲岀殑杩愯鏈熸垚鍛樻々鍦?*鎹㈣鐨勭被**涓婅涓㈡帀(1.21.x 涓夋潯绾垮洜瀹冨惎鍔ㄥ氨宕?鏈垎鏀悓涓€澶勫舰鐘跺凡淇苟澶嶉獙)

`decide()` 鎶撳彇杞借嵎**涔嬪墠**鍏堟棤鏉′欢璋?`stubMissing()`(鍥犱负鏈変簺妗╃殑瀹夸富鏄案涓嶆崲瑁呯殑杩愯鏈熺被),浣嗙揣鎺ョ潃
`input.methods = methods` 浼氱敤杞借嵎鐨勬垚鍛樿〃**鏁翠綋鏇挎崲**杩愯鏈熼偅浠?鈥斺€?鍒氬姞杩涘幓鐨勬々璺熺潃涓€璧锋病浜嗐€?`stubMissing()` 鍙ˉ缂虹殑(骞傜瓑),鎵€浠ュ湪鏇挎崲涔嬪悗**鍐嶈皟涓€娆?*灏辨槸淇硶銆?
**杩欐槸鍦?1.21.x 鍒嗘敮涓婇噺鍒扮殑**(鐥囩姸銆佸穿婧冩姤鍛婁笌 `javap` 璇佹嵁閮借鍦ㄩ偅杈圭殑 `docs/PLAN.md` 閲?:
淇璁″垝鎶?`BlockEntity` 鏀规寕鍒?`net/neoforged/neoforge/attachment/AttachmentHolder`,杞借嵎鐨?`BlockEntity.<init>` 璋?`this.gatherCapabilities()`(鍘熸湰鏉ヨ嚜琚敼鎸備涪鎺夌殑 Forge 鐖剁被,杩愯鏈熺殑
`BlockEntity` 鑷繁涔熸病鏈?,璇ユ垚鍛?*宸茬粡鍦?*浜や粯 jar 鐨?`optifineoforge/stubs.txt` 閲屻€佺涓€娆?`stubMissing()` 涔熺‘瀹炲姞浜?鍗磋閭ｅ彞璧嬪€兼墧鎺?鈬?`Initializing game` 闃舵
`NoSuchMethodError: BlockEntity.gatherCapabilities()`銆備慨鍚?1.21 / 1.21.1 / 1.21.3 涓夋潯绾垮洓椤归獙鏀跺叏缁?(1.21 鐨?stderr 鍥炲埌璁板綍鍊?14141),OptiFine 鏃ュ織琛屾暟浠?28/27/27 鍙樻垚 377/232/225銆?
**鏈垎鏀悓涓€澶勫舰鐘?*(`stubMissing` 鍦?`input.methods = methods` 涔嬪墠)宸叉寜鍚屾牱鏂瑰紡淇帀,鍥涙潯绾块噸寤哄苟澶嶉獙:

| 绾?| 鍒ゅ畾 | user | sound | 宕╂簝 | stderr |
|---|---|---|---|---|---|
| 1.20.1 | STARTED | yes | yes | 0 | **0 = 璁板綍鍊?* |
| 1.20.2 | STARTED | yes | yes | 0 | **14625 = 璁板綍鍊?* |
| 1.20.4 | STARTED | yes | yes | 0 | **14481 = 璁板綍鍊?* |
| 1.20.6 | STARTED | yes | yes | 0 | 17856(搂鍗佷笁 宸插畾浣嶇殑閭ｄ釜宸茬煡宸紓) |

**椤哄甫璁颁竴涓?rig 绾ч厤鏂逛簨瀹?*(杩欐鐪熺殑韪╁埌浜?:1.20.1 鐨勯噸寤哄繀椤诲甫 `-SkipForgeStubs`,鍚﹀垯
`ForgeApiShims` 浼氬線 loader jar 閲屽 `net/minecraftforge/**`,涓?FML 鑷繁鐨勫悓鍚嶅寘鍐茬獊,鍚姩鐩存帴姝诲湪
`java.lang.module.ResolutionException: Module OptifiNeoforge.mc1._20._1.registered contains package
net.minecraftforge.eventbus.api, module net.minecraftforge.event ...`(stderr 1721 瀛楄妭銆丱ptiFine 0 琛?銆?鍙﹀ `-ProfileId 1.20.1-forge-47.4.23` 蹇呴』鏄惧紡缁?杩欑嚎 profile 鍚嶄笉绛変簬 Maven 鐗堟湰鍙?,鍚﹀垯鑴氭湰鍦?"runtime classpath" 涓€姝ユ姏 `no profile json at ...`銆?
