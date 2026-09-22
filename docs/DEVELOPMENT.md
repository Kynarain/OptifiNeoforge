# 瀵偓閸欐垼顔囪ぐ?26.x 缁?

閺堫剚鏋冩禒鎯邦唶瑜版洘婀扮痪鑳殰瀹歌京娈戠€圭偞绁?娴犮儱寮风捄銊у殠閸忚京鏁ら惃鍕⒈娑擃亣鐨熼惍鏃傜波鐠佽櫣娈戦崗銉ュ經:`docs/RESEARCH-optifine.md`(OptiFine jar 閻ㄥ嫮绮ㄩ弸鍕瑢鐞涖儰绔甸張鍝勫煑,7 娑擃亝鐎娲偓鎰嚋閹峰棗绱戦惇?娑?`docs/RESEARCH-neoforge.md`(NeoForge/FML 娓氀勭槨娑擃亞澧楅張顒€鍘戠拋闀愮矆娑?閵?
## 閹簼绠炵拠?OptiFine 閻?jar

`src/main/java/kynarain/cn/optifineoforge/optifine/OptifineJar.java` 娑?`OptifineConfig.java` 閸欘亙绶风挧?JDK,娑撳秳绶风挧?Minecraft閵嗕腐eoForge 閹存牔鎹㈡担?loader,閹碘偓娴犮儱褰叉禒銉ф纯閹?`javac` 缂傛牞鐦ч崥搴☆嚠閻偓閻喎鐤?jar 鐠?

```powershell
javac -d out src\main\java\kynarain\cn\optifineoforge\optifine\*.java
java -cp out kynarain.cn.optifineoforge.optifine.OptifineJar <OptiFine jar 鐠侯垰绶?
java -cp out kynarain.cn.optifineoforge.optifine.OptifineConfig <OptiFine jar 鐠侯垰绶?
```

OptiFine 閻?jar 娑撳秷绻樻禒鎾崇氨(`test-downloads/` 瀹告彃鎷烽悾?閵嗗倷绗呮潪鍊熻泲缁楊兛绗侀弬褰掓殔閸?濞夈劍鍓?*鐎瑰啳绻戦崶鐐垫畱 302 闁?`Location` 閺勵垳娴夌€电鐭惧?*,`curl -L` 娑撳秳绔寸€规俺绐″妞剧瑓閸?閻╁瓨甯撮悽銊╂殔閸嶅繒娈?maven 鐠侯垰绶為弴瀵盖?

```powershell
curl.exe -sSL -o preview_OptiFine_26.1.2_HD_U_K1_pre2.jar `
  "https://bmclapi2.bangbang93.com/maven/com/optifine/26.1.2/preview_OptiFine_26.1.2_HD_U_K1_pre2.jar"
```

## 鐎圭偞绁?7 娑擃亝鐎铏规畱缂佹挻鐎?2026-09-14)

| OptiFine 閺嬪嫬缂?| 婢堆冪毈 | 閺夛紕娲?| 閸忓啯鏆熼幑?| ModLauncher 閺堝秴濮?| `notch/` | `srg/` | `patch/` |
|---|---|---|---|---|---|---|---|
| 1.20.1 HD_U_I6 | 7,145,205 | 6493 | `mods.toml` | 閺?| 703 | 635 | 4888 |
| 1.20.4 HD_U_I7 | 7,232,045 | 6568 | `mods.toml` | 閺?| 711 | 643 | 4948 |
| 1.21.1 HD_U_J1 | 7,322,249 | 6594 | `mods.toml` | 閺?| 736 | 660 | 4924 |
| 1.21.6 HD_U_J6_pre3 | 7,518,992 | 6928 | `mods.toml` | 閺?| 773 | 709 | 5172 |
| 1.21.7 HD_U_J6_pre7 | 7,587,441 | 6986 | `mods.toml` | 閺?| 776 | 712 | 5224 |
| 1.21.11 HD_U_J9 | 8,045,105 | 7351 | `mods.toml` | 閺?| 827 | 756 | 5496 |
| 26.1.2 HD_U_K1_pre2 | 7,797,229 | 7356 | `mods.toml` | 閺?| 829 | 759 | 5490 |

1. **閸忋劑鍏橀弰顖氱暔鐟佸懎娅掕ぐ銏♀偓?*:鐢?`patch/`(xdelta 瀹割喖鍨庨崠?+ 閸氬苯鎮?`.md5`)娑?`optifine/Installer`閵?2. **閸忓啯鏆熼幑顔荤瀵板妲?Forge 閻?* `META-INF/mods.toml`(`modLoader="javafml"`,`loaderVersion="[14,)"`,`modId="optifine"`),**濞屸剝婀佹稉鈧稉顏呯€鍝勭敨 `neoforge.mods.toml`** 閳ユ柡鈧?閸栧懏瀚?26.1.2閵?3. **濮ｅ繋閲滈弸鍕紦闁姤鏁為崘?ModLauncher 閺堝秴濮?*:`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 閳?`optifine.OptiFineTransformationService`,鏉烆剙鐎烽崳銊︽Ц `optifine.OptiFineTransformer`(26.1.2 闁插本鏁奸崥宥勮礋 `OptiFineBaseTransformer`,鐢悂鍣洪崐闂寸瑝閸?,閸欘亙绶风挧?ModLauncher閵嗕竸SM 娑?log4j閵?4. **`patch/` 娑撳婀佹稉澶夐嚋閸撳秶绱?*,娑撳秵妲告稉鈧稉?`patch/srg/`閵嗕梗patch/notch/`閵嗕梗patch/assets/`(1.20.1:824 / 822 / 3242 閺?閵嗗倽娴嗛崹瀣珤閸欘亣顕?`patch/srg/` 娑?`srg/`,**娴犲簼绗夌喊?`notch/`**閵?5. **鐞涖儰绔电拹鐔绘祰閻ㄥ嫬鎳￠崥宥団敄闂傛潙婀?1.20.6 閹广垼绻冩稉鈧▎?*:1.20.1 / 1.20.2 / 1.20.4 閻ㄥ嫯绀嬫潪浠嬪櫡閺?*閻喐顒滈惃?SRG 閹存劕鎲抽崥?*(`f_127499_` 娑斿琚?,OptiFine 閼奉亜绻侀惃?`srg/net/optifine/**` 娑旂喐妲?娴?**1.20.6** 鐠х柉绀嬫潪鎴掔瑢閼奉亜顔嶇猾濠氬櫡**娑撯偓娑?SRG 閹存劕鎲抽柈鑺ョ梾閺?*,瀹歌尙绮￠弰?Mojang 鐎规ɑ鏌熼崥?閳ユ柡鈧?`srg/` 娴犲酣鍋呴弮鎯版崳閸欘亝妲稿▽璺ㄦ暏閻ㄥ嫮娲拌ぐ鏇炴倳閵?6. **26.1.2 鐠ф澘绶遍弴纾嬬箼**:`patch/srg` 娑?`patch/notch` 閻?566 娑擃亜鎮撻崥宥堢鏉?*闁劕鐡ч懞鍌滄祲閸?*,鐠愮喕娴囬柌灞剧梾閺堝鎹㈡担鏇熻穿濞ｅ棗鎮?`patch2.cfg` 閹?srg 閺勭姴鐨犻幋鎰航缁?鐎瑰啫鎮撻弮?*閼奉亜鐢?NeoForge 閼奉亜绻侀惃?SPI**:`META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor` 娑?`...locating.IModFileCandidateLocator` 闁姤瀵氶崥?`optifine.OptiFineClassProcessor`,manifest 闁插矁绻曢張?`FMLModType: LIBRARY`閵?
## 26.1.2:鐎广垺鍩涚粩?jar 娑撳海顬囩痪鑳夋稉浣哥杽濞?2026-09-14)

閺堫剚婧€娴?Mojang 鐎规ɑ鏌熷〒鍛礋閸欐牕鍩?26.1.2 閻?client jar(38,113,927 鐎涙濡?30,675 娑擃亝娼惄?,绾喛顓荤€?*閺堫亝璐╁ǎ?*:`net/minecraft/client/Minecraft.class` 鐎涙ê婀?瑜般垹顩?`a.class` 閻ㄥ嫭璐╁ǎ鍡欒 **0 娑?*閵?
閻劌鎮撴稉鈧總妤€寮界亸鍕殶閻劌顕€瑰啳绐?OptiFine 閻ㄥ嫯藟娑撲礁娅?

| 妞ゅ湱娲?| 缂佹挻鐏?|
|---|---|
| 閼版妞?| 1.5 缁?|
| 鏉堟挸鍤?| 7,898,538 鐎涙濡?4611 娑擃亝娼惄?|
| 鏉堟挸鍤惃鍕窗瑜版洘鐎幋?| `assets/` 1785閵嗕梗notch/` 1395閵嗕梗srg/` 1325閵嗕梗optifine/` 53閵嗕梗doc/` 39,閸欙附婀?`patch.cfg` / `patch2.cfg` |
| 鐞氼偉藟娑撲胶娈戝〒鍛婂灆缁?| 972 娑?閸氬秴鐡ч柈鑺ユЦ鐎规ɑ鏌熼崥?娑?濞撳憡鍨欓張顏呰穿濞?娑撯偓閼? |
| 閹躲儵鏁?| 閺?闁偓閸戣櫣鐖?0 |

娑旂喎姘ㄩ弰顖濐嚛:**閺堫亝璐╁ǎ鍡欐畱 26.1.2 娑?OptiFine 閼奉亜绻侀惃鍕夋稉浣告珤閸欘垯浜掑锝呯埗瀹搞儰缍?娴溠冨毉閻ㄥ嫯藟娑撲胶琚鑼病閺勵垵绻嶇悰灞炬埂閻劎娈戠€规ɑ鏌熼崥?閳ユ柡鈧?鏉╂瑤绔寸痪澶哥瑝闂団偓鐟曚椒鎹㈡担鏇㈠櫢閺勭姴鐨?*閵嗗倽绻栭弰顖涙殻閺夛繝鎽兼稉濠勵儑娴滃奔閲滅悮顐ゎ瀲缁惧潡鐛欑拠浣烘畱閻滎垵濡?缁楊兛绔存稉顏呮Ц 1.20.1,鐟?`docs/RESEARCH-optifine.md` 娑撳簼绗傜悰?閵?
## 鐞涖儰绔垫禍褏澧块惃鍕閸?2026-09-14)

鐞涖儰绔甸崳銊ф畱鏉堟挸鍤潻妯款洣閹峰棙鍨氭稉銈勫敜,`OptifinePipeline.split` 閸嬫俺绻栨禒鏈电皑:娑撯偓娴犵晫绮?classpath(OptiFine 閼奉亜绻侀惃鍕娑撳氦绁┃?,娑撯偓娴犺姤妲哥悮顐ニ夋稉浣烘畱濞撳憡鍨欑猾?閹稿鍞撮柈銊ユ倳缁便垹绱?閻劋绨い鑸垫禌閸樼喓澧?閵嗗倷琚遍弶陇顫夐崚?

- **閸欘亜褰?`srg/` 闁絼绔存禒钘夊綁娴?* 閳ユ柡鈧?鏉烆剙鐎烽崳銊嚢閻ㄥ嫬姘ㄩ弰顖氱暊,`notch/` 闁絼鍞ゆ稉銏犵磾,閸忓秴绶辨径姘毉娑撯偓婵傛鎮撻崥宥囨畱缁眹鈧?- **娑撱垺甯€鐎瑰顥婇崳?*:`optifine/Installer*`閵嗕梗optifine/Patcher*`閵嗕梗optifine/Differ*`閵嗕梗optifine/xdelta/**`閵嗕梗optifine/json/**` 閳ユ柡鈧?閹存垳婊戦懛顏勭箒鐠烘垼藟娑撲礁娅?鏉╂劘顢戦張鐔剁瑝闂団偓鐟曚礁鐣犳禒?閼?loader 閸欘亣顩﹂惇瀣潌 `optifine/Installer.class` 鐏忓彉绱伴幏鎺旂卜閺佺繝閲?jar(鐟?`docs/RESEARCH-neoforge.md`)閵嗗倸鍙炬担娆庣閸?OptiFine 閻?`assets/`閵嗕梗doc/`閵嗕梗META-INF/services/**`)閸樼喐鐗辨穱婵堟殌閵?
鐎圭偞绁?

| | 26.1.2 | 1.20.1 |
|---|---|---|
| 鐞涖儰绔甸懓妤佹 | 1.5 缁?| 1.2 缁?|
| 鐞涖儰绔甸崳銊ㄧ翻閸?| 7,898,538 鐎涙濡?/ 4611 閺夛紕娲?| 6,638,739 鐎涙濡?/ 4049 閺夛紕娲?|
| 閹峰棗鍨庨崥?classpath jar | 2,789,483 鐎涙濡?/ 2616 閺夛紕娲?`assets/` 1785閵嗕梗net/optifine/` 759閵嗕梗doc/` 39閵嗕焦鐗?`optifine/` 19閵嗕梗META-INF/` 5) | 2,638,138 鐎涙濡?/ 2489 閺夛紕娲?|
| 鐞氼偉藟娑撲胶娈戝〒鍛婂灆缁?| **566**(閸忔湹鑵?`net/minecraft` 486) | **412**(閸忔湹鑵?`net/minecraft` 354) |
| `optifine/Installer.class` | 娑撳秴鐡ㄩ崷?瀹告彃澧ч梽? | 娑撳秴鐡ㄩ崷?瀹告彃澧ч梽? |

閹峰棗鍤弶銉ф畱缁粯妲搁崥?*閸欘垶鐛欑拠?*(JVM 閺嶏繝鐛欓柅姘崇箖)鏉╂ɑ鐥呭ù?閳ユ柡鈧?闁綁娓剁憰?ASM 閹?`ClassLoader` 鐏炲倿娼伴惃鍕梾閺?娴犮儱寮烽惇鐔告簚閸旂姾娴囬妴?
## 妫ｆ牗顐奸惇鐔告簚鐎圭偞绁?NeoForge 21.4.149(Minecraft 1.21.4,2026-09-14)

閺堫剚婧€鐟佸懎銈芥禍?JDK 21 娑?25(`C:\Users\kynar\.jdks\`),娴滃孩妲搁崣顖欎簰閻喓娈戦崥顖氬З娑撯偓濞?NeoForge閵嗗倻鏁ら惃鍕Ц閻劍鍩涢崥顖氬З閸ｃ劑鍣峰鍙夋箒閻?`1.21.4-NeoForge_21.4.149`(FML 6.0.18閵嗕府odLauncher 11.0.4),闁板秳绔存稉顏嗗缁斿娈戝〒鍛婂灆閻╊喖缍嶆稉搴ｅ缁斿娈?`mods/`,娑撳秴濮╅悽銊﹀煕閼奉亜绻侀惃鍕帳缂冾喓鈧倹绁寸拠鏇″壖閺堫剙婀禒鎾崇氨婢舵牜娈?`I:\mods\optifineoforge-test\launch-neoforge.ps1`閵?
**鐎靛湱鍙?閺冪姵膩缂?**:閸氼垰濮╅崚鐗堢垼妫版鏅棃?40 缁?`Sound engine started`,閺冪姴绌垮┃鍐╁Г閸?閳ユ柡鈧?濞村鐦崣鐗堟拱闊偄褰查悽銊ｂ偓?
**閹跺﹪鍣搁弬鐗堝ⅵ閸栧懓绻冮惃?OptiFine 1.21.4(J3)閺€鎹愮箻 `mods/`**,娓氭繃顐奸崙铏瑰箛娑撳閲滈梻顕€顣?閸撳秳琚辨稉顏勫嚒鐟欙絽鍠?

1. **`NoSuchFileException: ...optifine-1.21.4-for-neoforge.jar#214`** 閳ユ柡鈧?OptiFine 閻劏鍤滃杈╄閻?code source 閹?jar(`getProtectionDomain().getCodeSource().getLocation()`),閸?NeoForge 娑撳绻栭弰?union 閺傚洣娆㈢化鑽ょ埠鐠侯垰绶?`union:/...jar%23214!/`,鐎瑰啫褰ч崢缁樺竴缂佹挸鐔惃?`!`,閻ｆ瑤绗?`#214`,娴滃孩妲?`ZipFile` 閹垫挷绗夊鈧?ModLauncher 閻╁瓨甯撮崚銈呯暰 `InvalidLauncherSetupException: Invalid Services found OptiFine`閵?   閳?**娣囶喗纭?*:`OptifineJarFixer` 闁插秴鍟?`optifine.OptiFineTransformationService.toFile(URI)`,閹?`!` 娑斿鎮楁稉?`#` 娑斿鎮楅惃鍕劥閸掑棔绔撮獮鑸靛焻閹?闁插秴鍟撻崥搴ｆ畱閺傝纭剁憰渚€鍣哥粻妤佺垽鐢?閸氾箑鍨?JVM 閹?`Expected stackmap frame at this location`)閵嗗倹澧﹂崠鍛閼奉亜濮╂惔鏃傛暏閵?2. 娣囶喖銈芥稊瀣倵,**OptiFine 閼奉亜绻侀惃鍕祮閹广垺婀囬崝鈥虫躬 NeoForge 娑撳﹨绐囩挧閿嬫降娴?*:

   ```
   [optifine.OptiFineTransformationService]: OptiFine ZIP file: ...\mods\optifine-1.21.4-for-neoforge.jar
   [optifine.OptiFineTransformer]: Target.PRE_CLASS is available
   [optifine.OptiFineTransformer]: Forge JAR not available
   [optifine.OptiFineTransformationService]: OptiFineTransformationService.transformers
   [optifine.OptiFineTransformer]: Targets: 474
   ```

   閸?閺堝秴濮熺悮顐㈠絺閻滆埇鈧汞ar 鐞氼偅澧﹀鈧妴?*474 娑擃亣藟娑撲胶娲伴弽鍥ㄦ暈閸愬本鍨氶崝?*,ModLauncher 娑撳秴鍟€閹锋帞绮烽妴鍌濈箹閺勵垱鏆ｉ弶陇鐭剧痪璺ㄦ畱缁楊兛绔存稉顏嗘埂閺堥缚鐦夐幑顔衡偓?3. **娑撳绔存稉顏呭鐠侯垳娈戦梻顕€顣?鐏忔碍婀憴锝呭枀)**:FML 閻ㄥ嫭妫張鐔虹崶閸欙綁妯佸▓?`DisplayWindow.updateModuleReads` 娴兼艾骞撻幍顐ｆ煙濞夋洜顒烽崥?閼?OptiFine 閻ㄥ嫮琚鏇犳暏娴?**Forge 閻?API 缁鐎?* `net.minecraftforge.client.extensions.IForgeVertexConsumer` 閳ユ柡鈧?NeoForge 娑撳﹨绻栨稉顏勫瘶娑撳秴鐡ㄩ崷?`net.neoforged.neoforge.*`),娴滃孩妲?`ClassNotFoundException` 鐠佲晛鎯庨崝銊よ厬濮?鐟欎椒绗呮稉鈧懞?閵?
## Forge API 娑撳孩銆呯猾?2026-09-14)

**閸忔娊鏁崣鎴犲箛:瀵洜鏁ゆ稉宥呮躬缁鍣?閼板苯婀悰銉ょ鐠愮喕娴囬柌灞烩偓?* `IForgeVertexConsumer` 鏉╂瑤閲滈崥宥呯摟閸︺劍鏆ｆ稉?jar 閻?`.class` 闁插奔绔村▎锟犲厴娑撳秴鍤悳?閸欘亜鍤悳鏉挎躬 `patch/srg/com/mojang/blaze3d/vertex/VertexConsumer.class.xdelta` 閳ユ柡鈧?娑旂喎姘ㄩ弰?**OptiFine 閻ㄥ嫯藟娑撲浇顔€閸樼喓澧?`VertexConsumer` 閸樿鐤勯悳?Forge 閻ㄥ嫭甯撮崣?*閵嗗倹澧嶆禒銉ュ涧閹殿偆琚弬鍥︽閺勵垯绗夋径鐔烘畱,韫囧懘銆忔潻?`patch/**` 娑撯偓鐠ч攱澹傞妴?
婢跺嫮鎮婇崝鐐寸《(`ForgeApiShims` + `OptifineJar` 娑撱倕顦?:

- 閹?jar 闁?*閹碘偓閺?*閺夛紕娲?鐠哄疇绻?`assets/`閵嗕梗doc/` 娑?`notch/`),閺€鍫曟肠閸忋劑鍎?`net/minecraftforge/**` 閸氬秴鐡?閳ユ柡鈧?1.21.4 J3 娑撳﹥妲?**55 娑?*;
- 娑撶儤鐦℃稉顏勬倳鐎涙鏁撻幋鎰娑?*缁岀儤銆呯猾?*(閹恒儱褰涙潻妯绘Ц缁?閹?OptiFine 閼奉亜鐢惃鍕亝娴?`notch/<閸氬苯鎮?` 閻ㄥ嫯顔栭梻顔界垼韫囨鍠呯€?,閹垫捁绻樻禍銈囩舶 loader 閻?jar;
- 妞ゅ搫鐢?*娑撱垺甯€閺佺繝閲?`notch/` 閺?*(1.21.4 娑撳﹥妲?768 娑擃亝娼惄?:闁絾妲稿ǎ閿嬬┋閸涜棄鎮曠粚娲？閻ㄥ嫬褰夋担?鏉烆剙鐎烽崳銊ょ矤娑撳秷顕扮€?閼板苯鐣犻惃鍕劮閸氬秵瀵氶崥鎴炶穿濞ｅ棗鎮楅惃鍕埗閹村繒琚崹?`gng`閵嗕梗akv`),閻ｆ瑧娼冮崣顏冪窗閹跺﹤鎮撴稉鈧稉顏勩亼鐠愩儲灏撻崚棰佺瑓娑撯偓娑擃亞琚崹瀣ㄢ偓?
**缂佹挻鐏?閸氼垰濮╃搾濠呯箖娴滃棛顒烽崥宥嗗閹诲繘妯佸▓?OptiFine 閻喓娈戠捄鎴ｆ崳閺夈儰绨?*:

```
[OptiFine] (Reflector) Class not present: net.minecraftforge.common.extensions.IForgeEntity
[OptiFine] OptiFine_1.21.4_HD_U_J3
[OptiFine] Build: 20250209-131348
[OptiFine] OS: Windows 11 (amd64) version 10.0
[OptiFine] Java: 21.0.12.1, Eclipse Adoptium
[OptiFine] OpenGL: NVIDIA GeForce RTX 4060 Laptop GPU/PCIe/SSE2, version 3.2.0 NVIDIA 591.86
```

**娑撳绔存稉顏勩亼鐠?瀹告彃鐣炬担?閺堫亣袙閸?**:濞撳憡鍨欓崷?`Minecraft.<init>` 瀹?

```
java.lang.NoSuchMethodError: 'void com.mojang.blaze3d.pipeline.RenderTarget.<init>(boolean, boolean)'
	at com.mojang.blaze3d.pipeline.MainTarget.<init>(MainTarget.java:22)
```

閺傜懓鎮?OptiFine 閻ㄥ嫯藟娑撲焦鏁兼潻?`RenderTarget` 閻ㄥ嫭鐎柅鐘插毐閺?閼?`MainTarget` 鏉╂瑤绔存笟褌绮涢弰顖氬斧閻楀牆鑸伴悩?閳ユ柡鈧?娑撱倛鈧懓顩?*閹存劕顕?*鎼存梻鏁ら妴鍌氬讲閼宠姤妲哥悰銉ょ鎼存梻鏁ゆい鍝勭碍(OptiFine 閻ㄥ嫯娴嗛崹瀣珤娑?NeoForge 閼奉亜绻侀惃鍕祮閸ㄥ娅掗崷銊ユ倱娑撯偓閹靛湱琚稉濠傚帥閸氬氦绻嶇悰?閵嗕椒绡冮崣顖濆厴閺屾劒閲?xdelta 濞屸剝婀侀幋鎰鎼存梻鏁ら妴鍌欑瑓娑撯偓濮濄儱姘ㄩ弰顖涘Ω鏉╂瑤閲滈柊宥咁嚠闂傤噣顣介弻銉︾濡ゆ哎鈧?
### 閺屻儲绔绘禍?OptiFine 閻ㄥ嫭鏆ｇ猾缁樻禌閹诡澀绱版稉銏″竴 NeoForge 娓氀嗙箷閸︺劎鏁ら惃鍕灇閸?
鐎佃鐦稉澶夊敜 `RenderTarget`(闁姤妲?javap 閸戠儤娼甸惃?:

| 閺夈儲绨?| 閺嬪嫰鈧姴鍤遍弫?|
|---|---|
| NeoForge 鏉╂劘顢戦弮?NeoForm `client-1.21.4-...-srg.jar`) | `RenderTarget(boolean)`閵嗕梗RenderTarget(boolean, boolean)` 閳ユ柡鈧?娑撯偓閸欏倿鍋呮稉顏勫涧閺勵垵娴嗙拫鍐х癌閸?|
| NeoForge 閻?`MainTarget` | 鐠?`super(ZZ)` |
| OptiFine 鐞涖儰绔垫禍褏澧?`srg/com/mojang/blaze3d/pipeline/RenderTarget.class`) | **閸欘亝婀?`RenderTarget(boolean)`** |

娑旂喎姘ㄩ弰顖濐嚛:OptiFine 閻?*閼奉亜绻佺紓鏍槯閻ㄥ嫪绔存禒?* RenderTarget 妞よ埖甯€娴?NeoForge 閻ㄥ嫰鍋呮禒?閼板矂鍋呮禒鑺ユЦ閹?Forge 閻ㄥ嫬鑸伴悩鍓佺椽閻?鐏忔垳绨?NeoForge 鐠嬪啰鏁ら弬纭咁洣閻ㄥ嫰鍣告潪濮愨偓鍌氭倱娑撯偓娑擃亝膩瀵繗绻曟导姘丢鐎涙顔?閳ユ柡鈧?娣囶喗甯€閺嬪嫰鈧姴鍤遍弫棰佺閸?娑撳绔存稉顏堟晩鐠囶垰姘ㄩ弰?NeoForge 閻?`MainTarget.allocateDepthAttachment` 閹靛彞绗夐崚鏉跨摟濞?`useStencil`(OptiFine 闁絼鍞ら幎濠傜暊閺€鐟版倳閹存劒绨?`stencilEnabled`)閵?
**婢跺嫮鎮婇崝鐐寸《**:閸旂姳绔存稉顏呭灉娴狀剝鍤滃杈╂畱 ModLauncher 鏉烆剙鐎烽張宥呭(`OptifiNeoforgeTransformationService` + `RenderTargetFix`),閸?`TargetType.CLASS` 闂冭埖顔岄幎?OptiFine 娑撱垺甯€閻ㄥ嫭鍨氶崨妯克夐崶鐐插箵(閻╊喖澧犻弰顖炲亝娑擃亙绨╅崣鍌涚€柅鐘插毐閺?鏉烆剝鐨熸稉鈧崣?閵嗗倸鐤勫ù?

```
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, fml, OptifiNeoforge]
[OptifiNeoforge/]: OptifiNeoforgeTransformationService.transformers
```

閺堝秴濮熸稉?OptiFine 閻ㄥ嫭婀囬崝鈥虫倱閺冩儼顫﹂崝鐘烘祰,`NoSuchMethodError: RenderTarget.<init>(ZZ)` 濞戝牆銇?婢惰精瑙﹂悙瑙勫腹鏉╂稑鍩屾稉瀣╃娑擃亞宸辨径杈ㄥ灇閸?`NoSuchFieldError: ... MainTarget does not have member field 'boolean useStencil'`)閵?*缂佹捁顔?娣囶喖顦查弬鐟版倻鐎甸€涚啊,閸欘亝妲哥憰渚€鈧劙銆嶇悰銉╃秷 OptiFine 閺囨寧宕查幒澶屾畱閹存劕鎲?*(鐎涙顔?+ 閺傝纭?,鏉╂瑤绔寸猾璁虫叏婢跺秴褰叉禒銉ф埛缂侇厽瀵滈崥灞剧壉閺傜懓绱￠崝鐘偓?
### 鏉╂瑦妲告稉鈧稉顏勫讲闁插秴顦查惃?鐞涖儲鍨氶崨?濞翠焦鎸夌痪?
娣囶喗甯€閺嬪嫰鈧姴鍤遍弫鏉挎倵,閸氬奔绔存稉顏吥佸蹇撳嫉閸戣櫣骞囨禍鍡曡⒈濞?濮ｅ繑顐奸柈鑺ユЦ娑撯偓娑?NeoForge 娓氀勬煀婢х偟娈戦幋鎰喅鐞?OptiFine 閻ㄥ嫭娴涢幑銏㈠閺堫兛娑幒?

| # | 閹躲儵鏁?| 娑撱垹銇戦惃鍕灇閸?| 閹存垳婊戦惃鍕叏婢?|
|---|---|---|---|
| 1 | `NoSuchMethodError: RenderTarget.<init>(ZZ)` | 娴滃苯寮弸鍕偓鐘插毐閺?| 鐞涖儰绔存稉顏囨祮鐠嬪啩绔撮崣鍌滄畱閺嬪嫰鈧姴鍤遍弫?|
| 2 | `NoSuchFieldError: MainTarget does not have member field 'boolean useStencil'` | 鐎涙顔?`useStencil` | 鐞涖儱鐡у▓?楠炶泛婀悰銉ュ毉閺夈儳娈戦弸鍕偓鐘插毐閺佷即鍣风挧瀣偓?|
| 3 | `NoSuchMethodError: ReloadableResourceManager.getListeners()` | 鐠佸潡妫堕崳?`getListeners()` | 鐞涖儰绔存稉顏囩箲閸?`listeners` 鐎涙顔岄惃?getter(鐎涙顔岄崥宥勭瑢缁鐎烽柈鑺ョ梾閸?閹碘偓娴犮儲妲哥划鍓р€樻潻妯哄斧) |
| 4 | `NoSuchMethodError: ReloadableResourceManager.updateListenersFrom(SortedReloadListenerEvent)` | 閹烘帒绨弴瀛樻煀閺傝纭?| 閹?NeoForge 閻ㄥ嫬鐤勯悳鎷屾祮鐠?`ReloadListenerSort.sort(event)`;OptiFine 闁絼鍞ら幎?`listeners` 婢圭増妲戦幋?final,`putfield` 娴兼俺顫﹂弽锟犵崣閸ｃ劍瀚嗙紒?閹碘偓娴犮儲鏁奸幋鎰窔閸樼喎鍨悰銊╁櫡 `addAll` |
| 5(瑜版挸澧? | `NoSuchMethodError: net.minecraft.client.gui.Gui.initModdedOverlays()` | NeoForge 缂?`Gui` 閸旂姷娈戦弬瑙勭《 | 閺堫亜顦╅悶?|

濮ｅ繋鎱ㄦ稉鈧稉?閸氼垰濮╃亸鍗炵窔閸撳秷铔嬫稉鈧?15 缁?閳?20 缁?閳?45 缁?,**鐠囧瓨妲戦弬鐟版倻濮濓絿鈥橀妴浣风瑬婢惰精瑙﹂悙瑙勬Ц閺堝妾洪惃?*閵嗗倷绗呮稉鈧潪顔款嚉閸嬫氨娈戦弰顖涘Ω"闁劒閲滃畷鈹库偓渚€鈧劒閲滄穱?閹广垺鍨?*閹存劖澹掗幒銊ヮ嚤**:閸掓鍤?OptiFine 閺囨寧宕查幒澶屾畱閹碘偓閺堝琚?鐞涖儰绔电拹鐔绘祰闁?`patch/srg/**` 閻ㄥ嫰鍋呮禍?,闁劒閲滄稉搴ょ箥鐞涘本妞?A=NeoForm 閻?client jar)鐎佃鐦幋鎰喅闂嗗棗鎮?閼奉亜濮╅崚妤€鍤?鏉╂劘顢戦弮鑸垫箒閻ㄥ嫨鈧副ptiFine 闁絼鍞ゅ▽鈩冩箒閻?閹存劕鎲冲〒鍛礋,閸愬秵瀵滈崥灞肩婵傛顫夐崚娆愬闁插繗藟姒?鐎涙顔岄惄瀛樺复鐞涖儯鈧宫etter/setter 閹稿鎮撻崥宥呯摟濞堜絻藟閵嗕礁鍙炬担娆忓帥鐞涖儲濮忓鍌氱埗閻ㄥ嫭銆呴獮鎯邦唶瑜?閵?
- **FML 6 娑撳秵甯撮崣?`mods/` 闁插本鐥呴張澶婂帗閺佺増宓侀惃?jar**(閺冦儱绻?`not a valid mod file`)閳ユ柡鈧?transformer jar 娑撳秹娓剁憰?mod 閸忓啯鏆熼幑?鏉╂瑦娼懓浣筋潐閻晛婀潻娆撳櫡娑撳秵鍨氱粩瀣ㄢ偓鍌涚ゴ鐠囨洖褰撮惃鍕粵濞夋洘妲搁幎濠冨灉娴狀剛娈戦張宥呭缁顢ｆ潻娑㈠亝娑擃亜鍑＄紒蹇氼潶閹恒儱褰堥惃?OptiFine jar,楠炶埖濡搁張宥呭閺傚洣娆?*鏉╄棄濮?*娑撯偓鐞?娑撯偓娑擃亝婀囬崝鈩冩瀮娴犺泛褰叉禒銉ュ灙婢舵矮閲滅€圭偟骞?閵?- **PowerShell 閸欐﹢鍣洪崥宥勭瑝閸栧搫鍨庢径褍鐨崘?*:`$Out` 娑?`$out` 閺勵垰鎮撴稉鈧稉顏勫綁闁?閼存碍婀伴柌灞芥倱閺冨墎鏁ゆ担婊嗙翻閸戦缚鐭惧鍕瑢濞翠礁顕挒鈩冩娴兼矮绨伴惄姝岊洬閻╂牓鈧?- **.NET Framework 閻?`ZipFile.CreateFromDirectory` 閸愭瑥鍤惃鍕蒋閻╊喖鎮曢悽銊ュ冀閺傛粍娼?*,閼?SecureJar 閻?union 閺傚洣娆㈢化鑽ょ埠閸欘亣顓诲锝嗘灘閺?鐞涖劎骞囨稉?`UnionFileSystem$NoSuchFileException: kynarain/cn/.../RenderTargetFix.class`閵嗗倹澧滃銉﹀瘻 `/` 閸愭瑦娼惄顔煎祮閸欘垬鈧?
## 鐏忔碍婀涵顔款吇

- 娑撳﹪娼扮粭?3 閺夆€茬婢舵牞绻曢張澶婎樋鐏?`net.minecraftforge.*` 瀵洜鏁ら棁鈧憰浣割槱閻?1.21.4 閻?OptiFine 闁插本婀佹稉鈧幍?閵?- 1.20.6 閳?1.21.8 鏉╂瑦娼崠娲？閺勵垰鎯侀柈钘夊剼 1.21.4 娑撯偓閺嶇柉鍏樼悮?NeoForge 閹恒儱褰?1.20.6 鐠?FML 鏉╂ü绱版０婵嗩樆閹锋帞绮?閸樼喓澧?OptiFine jar",閹存垳婊戝鎻掑ⅶ闂勩倕鐣ㄧ憗鍛珤閸忋儱褰?娴ｅ棙婀柅鎰嚋鐎圭偞绁?閵?- 鐞涖儰绔电猾鏄忣潶 NeoForge 鎼存梻鏁ら崥?OptiFine 鏉╂劘顢戦張鐔烘畱 MD5 閺嶏繝鐛欓弰顖氭儊娴犲秶鍔ч柅姘崇箖閵?- 1.21.9 閸欏﹣浜掗崥?ModLauncher 瀹歌弓绗夌€涙ê婀?韫囧懘銆忛弨鍦暏 NeoForge 閻?`ClassProcessor`,鏉╂瑦娼捄顖濈箷濞屄ょ槸閵?
## 鏉╂瑥顕崝鐘烘祰閸ｃ劍鍓伴崨宕囨絻娴犫偓娑?
| MC | OptiFine 鐠愮喕娴囬惃鍕嚒閸氬秶鈹栭梻?| NeoForge 鏉╂劘顢戦張鐔锋嚒閸氬秶鈹栭梻?| 缂佹捁顔?|
|---|---|---|---|
| 1.20.1 | SRG | SRG(`net.neoforged:forge:47.x`) | 鐠烘垼藟娑撲礁娅掗崥搴ｆ暏 `srg/` 闁絼鍞ら崡鍐插讲,**娑撳秹娓剁憰渚€鍣搁弰鐘茬殸** |
| 1.20.2 / 1.20.4 | SRG | 鐎规ɑ鏌熼崥?20.2 鐠? | 鐠烘垼藟娑撲礁娅掗崥搴ゎ洣**閹?SRG 闁插秵妲х亸鍕灇鐎规ɑ鏌熼崥?* |
| 1.20.6 閳?1.21.8 | 鐎规ɑ鏌熼崥?| 鐎规ɑ鏌熼崥?| 鐠愮喕娴囬惄瀛樺复閸欘垳鏁?閸忓啯鏆熼幑顔肩箑妞ょ粯鏁奸崘?**鏉╂瑤绔撮崠娲？娴犲秷鍏樻径宥囨暏 OptiFine 閼奉亜绻侀惃?ModLauncher 鏉烆剚宕查張宥呭**(閳?FML 9) |
| 1.21.9 閳?1.21.11 | 鐎规ɑ鏌熼崥?| 鐎规ɑ鏌熼崥?| **ModLauncher 瀹歌尪顫?NeoForge 缁夊娅?*,閼板矁绻栨稉澶屽 OptiFine 閺冦垺鐥呴張?`ClassProcessor` 娑旂喐鐥呴張澶婂讲閻劎娈戦崗銉ュ經 閳?閸欘亣鍏橀悽杈ㄥ灉娴狀剝绐囩悰銉ょ閸ｃ劌鑻熺€圭偟骞?`ClassProcessor` |
| 26.1.2 | 鐎规ɑ鏌熼崥?閺堫亝璐╁ǎ? | 鐎规ɑ鏌熼崥?閺堫亝璐╁ǎ? | OptiFine **閼奉亜鐢?NeoForge 閻?`ClassProcessor`**,閹存垳婊戦惃鍕紣娴ｆ粍妲搁柌宥嗘煀閹垫挸瀵?jar(閸忓啯鏆熼幑?+ 閸樼粯甯€鐎瑰顥婇崳銊ュ弳閸?楠炶埖濡哥悰銉ょ缁粯甯存稉?|

娑撱倖娼鑼叀閻ㄥ嫮鈥栫痪锔芥将(鐟?`docs/RESEARCH-neoforge.md`):娴?**1.20.6** 鐠?FML 閺堝绔撮弶锟犳嫛鐎电懓甯悧?OptiFine jar 閻ㄥ嫭瀚嗙紒婵婎潐閸?閹恒垽鎷￠弰?`optifine/Installer.class`),閹碘偓娴犮儱绻€妞ゅ鍣搁弬鐗堝ⅵ閸?**1.21.3** 鐠?OptiFine 婢圭増妲戦惃?`loaderVersion` 娴兼碍鐗庢灞姐亼鐠?閸忓啯鏆熼幑顔肩箑妞ょ粯鏁奸崘娆嶁偓?
## 鐏忔碍婀涵顔款吇

- `notch/net/minecraftforge/**`(70 娑擃亞琚?閹槒顓哥痪?56閳?4 KB)閺勵垰鐣弫鏉戠杽閻滄媽绻曢弰顖涖€?1.21.11 閻?`srg/net/optifine/shaders/ShadersRender` 闁插本婀?*绾剙绱╅悽?* `net/minecraftforge/client/event/ViewportEvent$ComputeCameraAngles`,閼?NeoForge 娑撳﹥妲?`net.neoforged.neoforge.*` 閳ユ柡鈧?鏉╂瑦妲稿鑼叀閻ㄥ嫮顑囨稉鈧稉顏勭箑閻掓儼顩︽穱顔炬畱閻愬箍鈧?- 1.20.6 鐠?鐠愮喕娴囧鍙夋Ц鐎规ɑ鏌熼崥?鏉╂瑦娼憴鍕灟閺勵垯绮?7 娑擃亝鐎鍝勵樆閹恒劎娈?`1.21`閵嗕梗1.21.3`閵嗕梗1.21.4`閵嗕梗1.21.8`閵嗕梗1.21.9`閵嗕梗1.21.10` 閺堫亪鈧劒閲滅涵顔款吇閵?- OptiFine 鏉╂劘顢戦張鐔剁窗閻?MD5 閺嶏繝鐛欑悰銉ょ缂佹挻鐏?閼?NeoForge 閼奉亜绻佹稊鐔剁窗閺€鐟板斧閻楀牏琚?閳ユ柡鈧?娑撱倛鈧懎褰旈崝鐘叉倵閺嶏繝鐛欓弰顖氭儊鏉╂﹢鈧俺绻?閸欘亣鍏橀崷銊ф埂閺堣桨绗傞惇瀣ㄢ偓?- 閺堫剚婧€濞屸剝婀?JDK 25,26.1.2 閻?NeoForge 鐎圭偘绶ユ潻妯挎崳娑撳秵娼?閸ョ姵顒?閼虫垝绗夐懗鍊熺箻濞撳憡鍨?鏉╂瑤绔寸仦鍌氱毣閺堫亪鐛欑拠浣碘偓?
### 鎴愭壒琛ラ綈:132 涓垚鍛?涓€娆′慨瀹?2026-09-14)

鎶?閫愪釜宕┿€侀€愪釜淇?鎹㈡垚浜嗙绾挎帹瀵?+ 杩愯鏃舵壒閲忚ˉ榻?

- `MemberRestorePlan`(绂荤嚎宸ュ叿)鎷?*琛ヤ竵浜х墿**涓?*杩愯鏃?*鐨?NeoForm client jar 閫愪釜绫绘瘮瀵规垚鍛橀泦鍚?杈撳嚭"杩愯鏃舵湁鐨勩€丱ptiFine 閭ｄ唤娌℃湁鐨?娓呭崟;
- 瀹炴祴:**姣斿 248 涓鏇挎崲鐨勭被,寰楀埌 134 涓緟琛ユ垚鍛?*(`Gui` 涓€涓被灏卞崰 16 涓?;
- 娓呭崟浣滀负璧勬簮鎵撹繘 jar(`optifineoforge/member-restores.txt`),`MemberRestoreTransformer` 鍦?`TargetType.CLASS` 闃舵鎸夋竻鍗曡ˉ:瀛楁鎸夊師绫诲瀷琛?鏂规硶琛ヤ竴涓繑鍥炵被鍨嬮粯璁ゅ€肩殑妗?**姣忎釜妗╅兘鎵撴棩蹇?*(鎵€浠?鍝簺鍙槸涓嶅穿銆佸摢浜涙槸鐪熻繕鍘?濮嬬粓鍙);
- 闇€瑕佺湡瀹炶涔夌殑涓や釜鎴愬憳(`getListeners`銆乣updateListenersFrom`)鎺掗櫎鍦ㄦ壒閲忎箣澶?浠嶇敱鍚勮嚜鐨?fix 绮剧‘杩樺師,鍥犳涓嶄緷璧?ModLauncher 鐨勮浆鍨嬪櫒鎵ц椤哄簭銆?

**缁撴灉:娓告垙瓒婅繃浜嗘暣涓?`Minecraft.<init>`,杩涘叆鍔犺浇鐣岄潰鐨勬覆鏌撳惊鐜?*(`NeoForgeLoadingOverlay.render` 鈫?`DisplayWindow.render`),鍚姩鏃堕暱浠?45 绉掓帹鍒?60 绉掋€傚綋鍓嶅け璐ョ偣鏄?FML 鑷繁鐨勬棭鏈熺獥鍙ｆ覆鏌撳櫒鎶?`IllegalStateException: Already building.` 鈥斺€?浣嶇疆鍦?FML 鍐呴儴,鏂瑰悜鏄煇涓琛ユ垚榛樿鍊肩殑妗?鎴?OptiFine 鏇挎崲鎺夌殑娓叉煋鐩稿叧绫?璁╁畠杩涗簡涓嶄竴鑷寸姸鎬併€備笅涓€姝?鎶婃々娓呭崟涓庤繖涓け璐ョ偣瀵逛笂,浼樺厛鎶婁笌娓叉煋/缂撳啿鍖烘湁鍏崇殑鎴愬憳浠?榛樿鍊兼々"鍗囩骇鎴愮湡瀹炲疄鐜般€?

### 褰撳墠闃诲鐐?琚ˉ鎴?榛樿鍊?鐨勬々涓嶅,FML 鐨勬棭鏈熸樉绀鸿繘浜嗕笉涓€鑷寸姸鎬?2026-09-14)

鎵归噺琛ラ綈涔嬪悗鍚姩璧板埌浜?`NeoForgeLoadingOverlay.render` 鈫?FML 鐨?`DisplayWindow.render`,鐒跺悗鎶?

```
java.lang.IllegalStateException: Already building.
	at net.neoforged.fml.earlydisplay.SimpleBufferBuilder.begin(SimpleBufferBuilder.java:152)
	at net.neoforged.fml.earlydisplay.RenderElement.renderText(RenderElement.java:252)
```

`-Dfml.earlyprogresswindow=false` 娌℃湁鏁堟灉(NeoForge 21.4 浠嶇劧寤鸿嚜宸辩殑鍔犺浇瑕嗙洊灞?銆傛煡浜嗘竻鍗曢噷涓庢覆鏌撶浉鍏崇殑鏉＄洰,涓€鍏?7 涓?鍏朵腑 4 涓槸 NeoForge 鐨?GL 鐘舵€佸浠藉姛鑳?

```
M com/mojang/blaze3d/platform/GlStateManager _backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/platform/GlStateManager _restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/systems/RenderSystem backupGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/systems/RenderSystem restoreGlState (Lnet/neoforged/neoforge/client/GlStateBackup;)V
M com/mojang/blaze3d/vertex/VertexFormatElement$Usage getExtensionInfo ()Lnet/neoforged/fml/common/asm/enumextension/ExtensionInfo;
M com/mojang/blaze3d/vertex/VertexFormatElement findNextId ()I
M net/minecraft/client/renderer/chunk/SectionCompiler compile (...)
```

**鍒ゆ柇**:鎶婅繖鍥涗釜澶囦唤/鎭㈠鏂规硶琛ユ垚"浠€涔堥兘涓嶅仛鐨勬々"鏄笉瀵圭殑 鈥斺€?瀹冧滑鏄覆鏌撶姸鎬佹満鐨勪竴閮ㄥ垎銆備笅涓€姝ヤ笉璇ョ户缁姞妗?鑰屾槸**鎶婃々鎹㈡垚鐪熻韩**:

> 鍦ㄧ绾块樁娈典粠杩愯鏃剁被閲屾妸缂哄け鎴愬憳鐨?*鍘熷瀛楄妭鐮?*鎶藉嚭鏉?瀛楁 + 鏂规硶浣?,鎵撴垚"渚涗綋(donor)绫?闅?jar 涓€璧峰彂;杩愯鏃惰浆鍨嬪櫒鎶婅繖浜涙垚鍛樿繛鍚屾柟娉曚綋鏁翠綋澶嶅埗杩?OptiFine 鐨勬浛鎹㈢増鏈€傝繖鏍?琛ュ洖鍘?灏辨槸鐪熺殑琛ュ洖鍘?鑰屼笉鏄?璁╁畠涓嶅穿"銆傚皯鏁版柟娉曚綋鐨勫父閲忔睜浼氭寚鍚?OptiFine 鏀瑰悕杩囩殑鎴愬憳鑰屽け鏁?杩欑被浼氬湪鏃ュ織閲屾樉褰?鍐嶅崟鐙鐞嗐€?

杩欐潯鏄笅涓€杞殑绗竴浠朵簨銆?

### 渚涗綋绫?鎶?琛ユ々"鎹㈡垚"琛ョ湡韬?(2026-09-14)

`MemberRestorePlan` 鐜板湪涓嶅彧杈撳嚭娓呭崟,杩?*涓烘瘡涓彈褰卞搷鐨勭被鐢熸垚涓€涓緵浣?donor)绫?*:閲岄潰鍙斁 OptiFine 涓㈡帀鐨勯偅浜涙垚鍛?鈥斺€?瀛楁甯﹀０鏄?鏂规硶甯?*鍘熷瀛楄妭鐮?*銆備緵浣撶殑鍐呴儴鍚嶅氨鏄淇被鐨勫悕瀛?鎵€浠ュ鍒惰繃鍘荤殑鏂规硶浣撻噷鐨?`this` 瀛楁璁块棶鑳藉涓?,浣嗗瓨鏀惧湪 `optifineoforge/donors/<绫诲悕>.class`,涓嶄細琚綋鎴愰偅涓被鍔犺浇銆?.21.4 涓婃槸 **57 涓緵浣撱€?06 KB**銆?

`MemberRestoreTransformer` 鏀逛负浠庝緵浣撳鍒舵垚鍛?瀛楁 + 鏂规硶浣?,涓嶅啀閫犻粯璁ゅ€兼々銆?

**浣嗙洿鎺ュ鍒朵細鍑洪棶棰?*:澶嶅埗杩囨潵鐨勬柟娉曚綋鍙兘寮曠敤 OptiFine 鏀瑰悕杩囩殑鎴愬憳,杩欐椂鏁翠釜绫婚兘杩囦笉浜嗘牎楠?`VerifyError: Error exists in the bytecode`)銆傛墍浠ョ敓鎴愪緵浣撴椂鍔犱簡涓€閬?*闈欐€佹牎楠?*:閬嶅巻鏂规硶浣撻噷鎵€鏈夋寚鍚戞湰绫荤殑瀛楁/鏂规硶寮曠敤,鍙鏈変竴涓湪鏇挎崲鐗堟湰閲屼笉瀛樺湪,灏?*闄嶇骇鎴愰粯璁ゅ€兼々骞舵墦鏃ュ織**銆傚疄娴嬮檷绾х殑鏈?`Gui.initModdedOverlays`銆乣Gui.renderHealthLevel`銆乣Camera.getRoll`銆乣ClientLevel.getModelData` 绛?鍏卞崄鍑犲),鍏朵綑鐓ф妱鐪熻韩銆?

**娴嬭瘯鍙拌繕宸竴姝?*:渚涗綋浣滀负璧勬簮瑕佽兘琚?`getResourceAsStream` 鎵惧埌,鑰?union 鏂囦欢绯荤粺闇€瑕?*鐩綍鏉＄洰**(涔嬪墠缁欐湇鍔＄被韪╄繃鍚屼竴涓潙)銆傚綋鍓嶆棩蹇楅噷鎴愮墖鐨?`No donor class for ...` 灏辨槸杩欎釜鍘熷洜 鈥斺€?鎵撹繘 combined jar 鏃惰涓轰緵浣撹矾寰勮ˉ鐩綍鏉＄洰銆備慨濂借繖涓€姝ユ墠鑳界湅鍑轰緵浣撳鍒剁殑鐪熷疄鏁堟灉銆?

### 渚涗綋鐨勭涓€娆″疄娴?琛ョ湡韬苟涓嶆€绘槸鏇村ソ(2026-09-14)

渚涗綋鎵撳寘淇ソ涔嬪悗(鐩綍鏉＄洰 + 鍛戒护琛岄粯璁よ蛋 `prepareForLoader` 鐨勫畬鏁存祦绋?,渚涗綋鐪熺殑琚鍒颁簡(鏃ュ織閲屼笉鍐嶆湁 `No donor class for ...`),`RenderSystem`銆乣Gui`(16 涓?銆乣ClientLevel`(9 涓?绛夌被鐨勬垚鍛橀兘鏄?*甯﹀師濮嬫柟娉曚綋**琛ヨ繘鍘荤殑銆?

浣嗗嚭鐜?*鍥為€€**:

```
java.lang.IllegalStateException: Rendersystem called from wrong thread
```

`RenderSystem.backupGlState` / `restoreGlState` 鐨勫師濮嬫柟娉曚綋閲屾湁绾跨▼鏂█,鑰岃繖閲屾槸鍦ㄦ棭鏈熷惎鍔ㄧ殑涓荤嚎绋嬩笂琚皟鐢ㄧ殑 鈥斺€?浜庢槸"鐪熻韩"姣?浠€涔堥兘涓嶅仛鐨勬々"**鏇存棭**鎶婂惎鍔ㄦ墦鏂?妗╃増鏈兘璺戝埌 60 绉?渚涗綋鐗堟湰 10 绉掑氨姝?銆?

**缁撹:琛ヤ粈涔堛€佹€庝箞琛?瑕佹寜鎴愬憳鍖哄垎**,涓嶈兘涓€鍒€鍒?

- 鍏滃簳鍋氭硶(榛樿鍊兼々)鍦?鍙槸琚汉璇讳竴涓?鐨勬垚鍛樹笂鏄鐨?
- 渚涗綋鐪熻韩鍦?鏈夊疄闄呭壇浣滅敤銆佷笖璋冪敤鐜涓€鑷?鐨勬垚鍛樹笂鏄鐨?
- 鑰屽甫绾跨▼/鐜鏂█鐨勬垚鍛?濡?`RenderSystem` 鐨勪袱涓姸鎬佹柟娉?涓よ竟閮戒笉瀵?妗╃牬鍧忕姸鎬佹満,鐪熻韩鏂█澶辫触銆傝繖绫婚渶瑕?*鎸夎皟鐢ㄧ幆澧冩敼鍐?*(鎶婃柇瑷€鍘绘帀,鎴栨敼鎴?`GlStateManager` 鐨勭洿鎺ヨ皟鐢?銆?

涓嬩竴姝?缁欎緵浣撶敓鎴愬姞涓€鏉?*鎸夋垚鍛樺垎绫?*鐨勮鍒?鈥斺€?鏂规硶浣撻噷鍑虹幇 `assertOnRenderThread` 涓€绫荤幆澧冩柇瑷€鐨?鍏堝墺鎺夎繖灞傛柇瑷€鍐嶅鍒?鑰屼笉鏄€€鍥炴々),骞舵妸杩欐壒鏂规硶鍦ㄦ棩蹇楅噷鍗曞垪銆?

### 瀹氫綅涓€娆¤鍒?"wrong thread" 鍏跺疄鏄鐢熼敊璇?2026-09-14)

鍔犱簡鏂█鍓ョ(澶嶅埗鏂规硶浣撳墠鍘绘帀 `RenderSystem.assert*` 杩欑被鏃犲弬鐜鏂█)涔嬪悗,鍚姩浠嶆槸 10 绉掗€€鍑?鎶ョ殑杩樻槸閭ｅ彞 `Rendersystem called from wrong thread`銆?*浣嗚繖娆℃妸瀹屾暣鏍堢炕鍑烘潵浜?鍙戠幇瀹冧笉鏄牴鍥?*:

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

`Main.main` 璧拌繘 `fillReport` 璇存槑**鍦ㄥ畠涔嬪墠灏卞凡缁忔湁涓€娆″紓甯?*,鑰岃繖娆℃柇瑷€澶辫触鍙戠敓鍦?*缁勮宕╂簝鎶ュ憡**鐨勬椂鍊?涓荤嚎绋嬭 GL 鑳藉姏瀛楃涓?銆備篃灏辨槸璇?鐪熸瑕佹煡鐨勬槸瀹冧箣鍓嶉偅涓紓甯?鑰岃繖涓?wrong thread"鍙槸瀹冪殑褰卞瓙銆?

鏁欒:澶辫触鐨勬爤瑕?*浠庡ご鐪?*,涓嶈兘鍙湅鏈€鍚庝竴鏉℃秷鎭?鈥斺€?涓婁竴姝ユ嵁姝ゅ緱鍑虹殑"渚涗綋鐪熻韩鏇村樊"鐨勭粨璁洪渶瑕侀噸鏂伴獙璇併€備笅涓€姝?鍦ㄥ畬鏁?stderr 閲屾壘 `Main.main` 涔嬪墠鐨勭涓€澶勫紓甯?鍙兘鍦?mod 鍔犺浇鎴?`Minecraft.run` 鐨勬棭鏈?,鍐嶅喅瀹氭垚鍛樼殑琛ユ硶銆?

### 鏇存:骞舵病鏈?渚涗綋鏇村樊"杩欏洖浜?2026-09-14)

鎶婁袱娆¤繍琛岀殑宕╂簝鎶ュ憡鎸夋椂闂村榻愪箣鍚?涓婁竴鑺傜殑缁撹鏄敊鐨?

| 杩愯 | 鍚姩 | 宕╂簝鎶ュ憡 | 鎻忚堪 |
|---|---|---|---|
| 22:50:43 妗╃増鏈?| 鈫?2:51:37 | crash-鈥?2.51.37 | `Rendering overlay` / `Already building.` |
| 22:54:53 渚涗綋鐗堟湰 | 鈫?2:55:06 | crash-鈥?2.55.06 | `Rendering overlay` / `Already building.` |

**涓ゆ閮藉埌杈惧悓涓€涓樆濉炵偣**(FML 鏃╂湡鏄剧ず鐨?`SimpleBufferBuilder.begin`),鍙槸鍒拌揪瀹冪殑鑰楁椂涓嶅悓(55 绉?vs 14 绉?宸埆鏉ヨ嚜璧勬簮/搴撳姞杞界殑娉㈠姩,涓嶆槸琛ユ硶瀵艰嚧)銆傛墍浠?

- **"渚涗綋鐪熻韩璁╁惎鍔ㄤ粠 60 绉掗€€鍖栧埌 10 绉?鏄笉鎴愮珛鐨?*,閭ｆ槸鎶?鏈€鍚庝竴鏉℃棩蹇楁秷鎭?褰撴垚浜嗘牴鍥?
- 鏂█鍓ョ涓?4 涓?GL 鐘舵€佹垚鍛樼殑鎺掗櫎浠嶇劧淇濈暀(瀹冧滑鍚勮嚜鏈変緷鎹?,浣?*褰撳墠鍞竴鐨勯樆濉炵偣鑷鑷崇粓娌″彉**:FML 鍔犺浇瑕嗙洊灞傜殑缂撳啿鍖虹姸鎬佹満銆?

椤哄甫纭涓や欢浜?

- `-Dfml.earlyprogresswindow=false` 鍦?NeoForge 21.4 涓?*鏃犳晥**(jar 閲屼篃鎵句笉鍒板彲鐢ㄧ殑寮€鍏冲瓧绗︿覆),鎵€浠ユ病娉曠敤閰嶇疆缁曡繃鏃╂湡鏄剧ず;
- FML 鐨勬棭鏈熸樉绀?*瀹屽叏鐢ㄨ嚜宸辩殑绫?*(`SimpleBufferBuilder` / `SimpleFont` / `Format` / `Mode`)涓?LWJGL,涓嶇敤 Minecraft 鐨?`VertexFormat`/`GlStateManager` 鈥斺€?鍥犳闂涓嶅湪"OptiFine 鏇挎崲浜嗗摢涓覆鏌撶被",鑰屽湪**鏌愪竴甯х殑缁樺埗涓€旀姏浜嗗紓甯?*,鎶?`building` 鐣欐垚浜?true(涓嬩竴甯х殑绗竴娆?`begin` 灏辩偢)銆備笅涓€杞鎶撶殑鏄?*绗竴甯ч噷琚悶鎺夌殑閭ｄ釜寮傚父**銆?

### 渚涗綋鏍￠獙鍐嶈ˉ涓€鏉?涓嶈澶嶅埗 `super` 璋冪敤(2026-09-14)

瀹屾暣鏃ュ織缈诲嚭浜?*鐪熸鐨勭涓€澶勫紓甯?* 鈥斺€?涓嶆槸 `Already building`,鑰屾槸**绫绘牎楠屽け璐?*:

```
java.lang.VerifyError: Bad invokespecial instruction: current class isn't assignable to reference class.
Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.VerifyError ...
Caused by: java.lang.NoClassDefFoundError: Could not initialize class net.optifine.reflect.Reflector
```

鍑洪敊鐨勫瓧鑺傜爜鏄?`aload_0; invokevirtual 鈥? aload_0; aload_1; aload_2; invokespecial 鈥? areturn` 鈥斺€?鍏稿瀷鐨?澶嶅埗杩囨潵鐨勬柟娉曚綋閲屾湁涓€鍙?`super.xxx(...)`"銆?*OptiFine 閭ｄ唤鐨勭埗绫绘湭蹇呭拰杩愯鏃堕偅浠戒竴鏍?*,浜庢槸涓€鍙?`invokespecial` 鎸囧悜鐨勭埗绫诲涓嶄笂,鏁翠釜绫昏繃涓嶄簡鏍￠獙銆?

淇硶:渚涗綋鏍￠獙鍐嶅姞涓€鏉?鈥斺€?鏂规硶浣撻噷鍑℃槸 `INVOKESPECIAL` 涓?owner 涓嶆槸鏈被鐨?鍗?`super` 璋冪敤),鍙湁鍦?*鏇挎崲鐗堟湰鐨勭埗绫讳笌杩愯鏃朵竴鑷?*鏃舵墠鍏佽澶嶅埗,鍚﹀垯闄嶇骇鎴愭々銆?

淇帀涔嬪悗 `VerifyError` 娑堝け,鍚姩鍥炲埌鍚屼竴涓樆濉炵偣(FML 鍔犺浇瑕嗙洊灞傜殑 `Already building.`),鑰屼笖杩欐鏄?*姝ｅ父鐨勫穿婧冩姤鍛?*,涓嶆槸杩為攣鐨勫垵濮嬪寲澶辫触銆?*鐩墠 1.21.4 涓婂敮涓€鐨勯樆濉炵偣灏辨槸瀹冦€?*

### 绐佺牬:FML 鏃╂湡鏄剧ず鍙互鐢ㄩ厤缃叧鎺?娓告垙闅忓嵆璺戣繘璧勬簮鍔犺浇(2026-09-14)

缈?`FMLConfig$ConfigValue` 鐨勬灇涓惧父閲忔椂鍙戠幇瀹冮櫎浜嗙獥鍙ｅ昂瀵?杩樻湁涓や釜寮€鍏?`earlyWindowControl` 涓?`earlyWindowProvider`,鑰岄厤缃潵鑷?*娓告垙鐩綍鐨?`config/fml.toml`**銆傚啓涓?

```toml
earlyWindowControl = false
```

鍐嶅惎鍔?閭ｄ釜绾犵紶浜嗗崄鍑犺疆鐨?`SimpleBufferBuilder.begin -> Already building.` **鐩存帴娑堝け**,澶辫触鐐规暣涓崲浜嗕竴灞?

```
Description: Rendering overlay
java.lang.NullPointerException: Cannot invoke "BakedModel.getParticleIcon()" because the return value
  of "BlockModelShaper.getBlockModel(BlockState)" is null
	at LiquidBlockRenderer.setupSprites(LiquidBlockRenderer.java:43)
	at BlockRenderDispatcher.onResourceManagerReload(BlockRenderDispatcher.java:157)
	at ResourceManagerReloadListener...
	at Minecraft.runTick(Minecraft.java:1211) -> Minecraft.run
```

涔熷氨鏄:**娓告垙宸茬粡鍦ㄨ窇涓诲惊鐜€佸湪閲嶈浇璧勬簮銆佸湪鐑樼剻妯″瀷**浜?鈥斺€?涓嶅啀鏄惎鍔ㄦ棭鏈熺殑绫诲姞杞?绛惧悕鎵弿闂,鑰屾槸"鏌愪釜妯″瀷鏌ヤ笉鍒?銆傛柟鍚戝緢鏄庣‘:娓呭崟閲屽氨鏈?`ModelManager`(4 涓垚鍛?銆乣BlockRenderDispatcher`(4 涓?銆乣LiquidBlockRenderer`(3 涓?銆乣SimpleBakedModel`(5 涓?杩欎簺涓庢ā鍨嬬儤鐒欑洿鎺ョ浉鍏崇殑绫?瀹冧滑鐨勬垚鍛樻槸**妗?*(杩斿洖 null)杩樻槸鐪熻韩,鍐冲畾浜嗚繖鏉￠摼鑳戒笉鑳介€?鈥斺€?姝ｆ槸"鎸夋垚鍛樺尯鍒嗚ˉ娉?瑕佽В鍐崇殑閭ｇ被銆?

缁撹杩?`docs/DEVELOPMENT.md`,骞惰涓嬫祴璇曞彴瑕佹眰:`config/fml.toml` 閲?`earlyWindowControl = false` 鏄窇 1.21.4 鐨勫繀瑕佹潯浠躲€?

### 妯″瀷閾剧殑鐜扮姸:璇ヨˉ鐨勬垚鍛樺ぇ澶氶檷绾ф垚浜嗘々(2026-09-14)

鍏虫帀鏃╂湡绐楀彛涔嬪悗鏆撮湶鍑烘潵鐨勯偅鎵?null 妯″瀷,鍜屾竻鍗曢噷"闄嶇骇鎴愭々"鐨勬垚鍛橀珮搴﹂噸鍚堛€傛煡浜嗕竴閬?`ModelBlockRenderer` / `BlockRenderDispatcher` / `LiquidBlockRenderer` / 鍚?`blockentity` 娓叉煋鍣ㄤ笂**鍗佸嚑涓垚鍛橀兘娌¤兘澶嶅埗鐪熻韩**,鍘熷洜鏄悓涓€鍙?

```
stub (body would not verify): net/minecraft/client/renderer/block/ModelBlockRenderer.tesselateBlock(...)
stub (body would not verify): net/minecraft/client/renderer/block/BlockRenderDispatcher.renderBatched(...)
stub (body would not verify): net/minecraft/client/renderer/block/LiquidBlockRenderer.shouldRenderFace(...)
...
```

涔熷氨鏄:**杩欎簺鏂规硶浣撳紩鐢ㄤ簡鍒殑銆佸悓鏍疯 OptiFine 涓㈡帀鐨勬垚鍛?*,鎵€浠ユ牎楠屼笉杩?鍙兘閫€鍖栨垚"杩斿洖榛樿鍊?銆傞€愪釜鎵嬭ˉ涓嶅彲鎸佺画銆?

**涓嬩竴姝ョ殑鍋氭硶(浼犻€掗棴鍖?**:濡傛灉鏂规硶浣?X 寮曠敤浜嗘湰绫婚噷缂哄け鐨勬垚鍛?Y,鑰?Y 鍦ㄨ繍琛屾椂绫婚噷瀛樺湪,閭ｅ氨**鎶?Y 涔熶竴璧疯ˉ杩涙潵**,鍐嶉噸鏂版牎楠?鈥斺€?鍙嶅鐩村埌涓嶅啀鏈夋柊鐨勫紩鐢ㄦ垨杈惧埌涓婇檺銆傝繖鏍?鏍￠獙涓嶈繃"灏变笉鍐嶇瓑浜?閫€鍖栨垚妗?,鑰屾槸"鎶婅繖鏉′緷璧栭摼琛ラ綈"銆傜湡姝ｇ殑杈圭晫鏉′欢鏄幆寮曠敤涓庝笂闄?涓よ€呴兘瑕佹樉寮忓鐞嗗苟鎵撴棩蹇椼€?

### 闂寘鐨勭涓€鐗堝彧璺熶簡鏈被寮曠敤 鈥斺€?鑰岃繖鎵瑰け璐ユ槸**璺ㄧ被**鐨?2026-09-14)

鎸変笂涓€鑺傜殑鏂规鍔犱簡浼犻€掗棴鍖?鏂规硶浣撳紩鐢ㄦ湰绫荤己澶辨垚鍛樻椂,鎶婇偅涓垚鍛樹篃琛ヨ繘鏉?鍙嶅鐩村埌绋冲畾)銆傛瀯寤轰笌鍚姩閮芥甯?**浣嗘竻鍗曞ぇ灏忔病鍙?浠嶆槸 132 涓?,涔熸病鏈夋墦鍗颁换浣?`closure added`** 鈥斺€?璇存槑杩欎簺"鏍￠獙涓嶈繃"鐨勬柟娉曚綋,**寮曠敤鐨勬湰绫绘垚鍛樺叾瀹為兘宸插湪娓呭崟閲?*銆?

浜庢槸鍘熷洜鍙兘鏄彟涓€绫?**瀹冧滑寮曠敤鐨勬槸鍒殑绫讳笂鐨勬垚鍛?鑰岄偅浜涚被鍚屾牱琚?OptiFine 鏇挎崲杩囥€佸悓鏍风己鎴愬憳**銆備緥濡?`ModelBlockRenderer.tesselateBlock` 浼氬幓璋?`BlockRenderDispatcher`/`BakedModel` 涓婄殑鏂规硶,OptiFine 鐗堢殑閭ｄ簺绫婚噷鏈繀鏈夈€?

鎵€浠ラ棴鍖呰**璺ㄧ被**鍋?

1. 鍏堟妸"琛ヤ竵浜х墿閲岀殑姣忎釜绫?涓?杩愯鏃跺搴旂殑绫?鍚勫缓涓€浠芥垚鍛樿〃;
2. 閬嶅巻鎵€鏈夊緟琛ユ垚鍛樼殑鏂规硶浣?鏀堕泦瀹冨紩鐢ㄧ殑**(owner, 鍚嶅瓧, 鎻忚堪绗?** 鈥斺€?涓嶉檺鏈被;
3. 鍙 owner 鍦ㄨˉ涓佷骇鐗╅噷(鍗充篃鏄鏇挎崲鐨勭被)銆佽€屽畠鐨勬浛鎹㈢増鏈己杩欎釜鎴愬憳銆佽繍琛屾椂閲屽張鏈?灏?*鎶婅鎴愬憳鍔犲埌閭ｄ釜绫荤殑娓呭崟閲?*;
4. 閲嶅鐩村埌涓嶅啀鍙樺寲銆?

杩欎竴鐗堥棴鍖呬唬鐮佺暀鍦ㄤ粨搴撻噷(瀹冩槸璺ㄧ被鐗堟湰鐨勫墠缃?,涓嬩竴杞妸瀹冧粠"鍙湅鏈被"鎵╂垚"璧版暣寮犲紩鐢ㄥ浘"銆?

### 璺ㄧ被闂寘鍋氬ソ浜?浣嗗畠涔熶笉鏄師鍥?2026-09-14)

鎶婇棴鍖呬粠"鍙湅鏈被"鏀规垚"璺熺潃寮曠敤鐨?**owner** 璧?(绗竴鐗堟妸寮曠敤涓€寰嬪綋鎴愯惤鍦ㄦ湰绫?绛変簬娌¤法绫?,鐜板湪浼氱湡鐨勮法绫昏ˉ榻愪簡 鈥斺€?瀹炴祴 `closure added 1`,娓呭崟浠?132 鍙樻垚 133銆?

**浣嗘ā鍨嬮摼鐨?null 渚濇棫**,澶辫触鐐逛竴瀛楁湭鍙樸€傛墍浠ラ偅浜?鏍￠獙涓嶈繃"鐨勬柟娉曚綋,**涓嶆槸鍥犱负寮曠敤鐨勬垚鍛樼己澶?*:

- 瀹冧滑寮曠敤鐨勬垚鍛?瑕佷箞鍦ㄦ浛鎹㈢増鏈噷鏈潵灏辨湁,瑕佷箞鍦ㄨ繍琛屾椂绫婚噷鏍规湰鎵句笉鍒?鎵€浠ラ棴鍖呮棤浠庡彲琛?;
- 閭ｅ氨鎰忓懗鐫€澶辫触鏉ヨ嚜鍒 鈥斺€?鎻忚堪绗?鐖剁被/瀛楁鍙鎬?璋冪敤鐐瑰舰鎬?鎴栬€呮柟娉曚綋閲岀敤浜?OptiFine 閭ｄ唤绫?*涓嶅叿澶囩殑娉涘瀷绛惧悕涓庢帴鍙?*銆?

**涓嬩竴杞鍋氱殑鏄妸杩欎釜鍒ゆ柇鎹㈡垚璇佹嵁**:鎷夸竴涓叿浣撶殑"鏍￠獙涓嶈繃"鐨勬柟娉?渚嬪 `ModelBlockRenderer.tesselateBlock`),鎶?*澶嶅埗鍚庣殑绫?*浜ょ粰鏍￠獙鍣?ASM 鐨?`CheckClassAdapter`,鎴栫洿鎺?`ClassLoader.defineClass` 鐪?VerifyError 鐨勮缁嗕俊鎭?,"鍒板簳鏄粈涔堣瀹冧笉杩?灏变笉鍐嶉潬鐚溿€?

### 鐢ㄦ牎楠屽櫒鍙栬瘉:鐪熻韩鍏跺疄鑳借繃,鏄垜浠殑鍒ゆ嵁澶弗(2026-09-14)

鏂板伐鍏?`DonorVerifier` 鎶?鏇挎崲鐗堟湰 + 鍏ㄩ儴缂哄け鎴愬憳(甯﹀師濮嬫柟娉曚綋)"鍚堟垚涓€涓被,浜ょ粰 ASM 鐨?`CheckClassAdapter` 鏍￠獙,鐩存帴鎶?涓轰粈涔堜笉杩?鎵撳嚭鏉ャ€傜涓€鏉＄粨璁哄氨寰堝叧閿?

```
### net/minecraft/client/renderer/block/LiquidBlockRenderer
  verifies clean with every body copied in          鈫?鍏ㄩ儴澶嶅埗杩涘幓鏄兘杩囩殑!
### net/minecraft/client/renderer/block/ModelBlockRenderer
  ClassNotFoundException: net.minecraftforge.client.extensions.IForgeBakedModel   鈫?鍙槸鏍￠獙鍣ㄧ湅涓嶅埌鎴戜滑鐨勬々绫?
```

涔熷氨鏄,`referencesOnlyExisting` 鐨勫垽鎹?瑕佹眰琚紩鐢ㄧ殑鎴愬憳**宸茬粡瀛樺湪浜庢浛鎹㈢増鏈?*)杩囦簬涓ユ牸:瀹為檯杩愯鏃?閭ｄ簺鎴愬憳**鏈潵灏变細琚鍒掕ˉ杩涘幓**(闂寘琛ョ殑),鎵€浠ユ柟娉曚綋鑳借繃銆傛敼鎴?瀵圭潃**琛ュ畬璁″垝涔嬪悗鐨勭被**鍒ゆ柇"鍚?涓€鎵瑰師鏈檷绾ф垚妗╃殑鏂规硶閲嶆柊鍙樻垚浜嗙湡韬€?

### 鏂版毚闇茬殑闂:琛ュ洖鏉ョ殑**瀛楁**娌′汉鍒濆鍖?

澶辫触鐐归殢鍗冲線鍓嶇Щ浜嗕竴鏍?杩欐鏄?`Initializing game`):

```
java.lang.NullPointerException: Cannot invoke "GuiLayerManager.initModdedLayers()" because "this.layerManager" is null
	at net.minecraft.client.gui.Gui.initModdedOverlays(Gui.java:1457)
	at net.neoforged.neoforge.client.ClientHooks.initClientHooks(ClientHooks.java:1015)
```

`layerManager` 鏄?NeoForge 缁?`Gui` 鍔犵殑瀛楁 鈥斺€?鎴戜滑**琛ヤ簡瀛楁**(鎵€浠ヤ笉鍐嶆槸 `NoSuchFieldError`),浣?*娌′汉鍦ㄦ瀯閫犲嚱鏁伴噷鍒濆鍖栧畠**銆備箣鍓嶅畠鏄々鏂规硶銆佹牴鏈笉瑙ｅ紩鐢?鎵€浠ョ湅涓嶅嚭鏉?鎹㈡垚鐪熻韩涔嬪悗绔嬪埢鏄惧舰銆?

**涓嬩竴姝?*:琛ュ瓧娈垫椂涓€骞惰ˉ"鍒濆鍖? 鈥斺€?鍦ㄨ繍琛屾椂绫荤殑鏋勯€犲櫒閲屾壘鍒扮粰璇ュ瓧娈?`putfield` 鐨勯偅娈垫寚浠?鎶婂畠鐓ф惉杩涙浛鎹㈢増鏈殑鏋勯€犲櫒(鑰屼笉鏄寽涓€涓粯璁ゅ€?銆傝繖鍜?鏋勯€犲嚱鏁伴噸杞?鏄悓涓€绫婚棶棰樼殑涓や釜闈€?

### 琛ュ瓧娈电殑鍒濆鍖?2026-09-14)

鎸変笂涓€鑺傜殑鏂规鍋氫簡:渚涗綋閲屼负姣忎釜琛ュ洖鏉ョ殑**瀹炰緥瀛楁**澶氬甫涓€涓潤鎬佹柟娉?

```
public static void optifineoforge$init$<瀛楁>(<绫? self)
```

瀹冪殑鏂规硶浣撳氨鏄?*浠庤繍琛屾椂绫荤殑鏋勯€犲櫒閲屽師鏍锋惉鍑烘潵鐨勯偅娈靛垵濮嬪寲鎸囦护**(渚嬪 `self.layerManager = new GuiLayerManager()`),鍙惉"鐩寸嚎娈?鈥斺€旈亣鍒拌烦杞?鏍囩/switch 灏辨斁寮?娑夊強闄?slot 0 浠ュ灞€閮ㄥ彉閲忕殑涔熸斁寮?鍚﹀垯鎼繃鍘诲氨涓嶆垚绔?,鏀惧純鏃舵墦鍗?`no safe initialiser for field ...`銆?

杞瀷鍣ㄦ妸杩欐壒鍒濆鍖栨柟娉曚竴璧锋斁杩涚被閲?骞跺湪**姣忎釜鏋勯€犲嚱鏁扮殑姣忎釜 `RETURN` 涔嬪墠**鎻掑叆璋冪敤銆傚疄娴嬬‘瀹炵敓鏁?

```
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/gui/Gui
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/gui/Font
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/multiplayer/ClientLevel
[OptifiNeoforge]: Initialised 1 restored fields in net/minecraft/client/resources/model/ModelManager
```

**褰撳墠鏂伴棶棰?鏈В鍐?**:杞瀷鍣ㄥ湪璇诲彇鏌愪釜渚涗綋鏃舵姏寮傚父(`MemberRestoreTransformer.donor:161`),鎶婅繖娆″惎鍔ㄦ墦鏂簡 鈥斺€?闇€瑕佺湅瀹屾暣娑堟伅纭鏄摢涓緵浣撱€佷互鍙婃槸涓嶆槸鏂板姞鐨勫垵濮嬪寲鏂规硶璁╅偅涓被鏂囦欢鍐欏潖浜?渚嬪琚惉鐨勬寚浠ゆ鍏跺疄涓嶅畬鏁?銆?

### 涓や釜渚涗綋渚х殑淇:鏍堝抚涓?final 瀛楁(2026-09-14)

**鈶?鎼垵濮嬪寲鎸囦护鏃朵笉鑳藉甫涓婃爤甯с€?* 鎶婃瀯閫犲櫒閲岀殑鍒濆鍖栫墖娈垫惉鍒颁緵浣撶殑闈欐€佹柟娉曢噷鏃?鍘熺墖娈靛彲鑳藉す鐫€ `FrameNode`;鎼繃鍘讳箣鍚庡抚涓庢柊鐨勬柟娉曚笂涓嬫枃瀵逛笉涓?**鍐欏叆鏃剁洿鎺ユ姏 `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`**,鎶婃暣涓惎鍔ㄦ墦鏂€傜幇鍦ㄩ亣鍒?`FrameNode` 灏卞綋浣滅墖娈佃竟鐣?鐩寸嚎娈垫湰鏉ヤ笉闇€瑕佸抚)銆?

椤哄甫鍋氫簡涓€浠朵竴鐩寸己鐨勪簨:**绂荤嚎鏍￠獙鍣?*銆俙CheckDonors`(鍗曟枃浠?Java,鐢?ASM 鎶婁緵浣撻噷姣忎釜鏂规硶鍗曠嫭鍐欎竴閬?涓€娆℃€ф寚鍑烘槸 `SimpleBakedModel` 鐨勪緵浣撳潖浜?鈥斺€?杩欑被闂鏈潵瑕佽窇涓€娆″畬鏁村惎鍔ㄦ墠鑳界湅鍒?鐜板湪绂荤嚎鍑犵灏辫兘瀹氫綅銆?

**鈶?琛ュ洖鏉ョ殑瀛楁涓嶈兘甯?`final`銆?* 鍒濆鍖栫敱鎴戜滑鐨勯潤鎬佹柟娉曟墽琛?鑰?JVM 瑙勫畾**闈為潤鎬?final 瀛楁鍙兘鍦ㄦ湰绫荤殑 `<init>` 閲岃祴鍊?*:

```
IllegalAccessError: Update to non-static final field ModelManager.modelBakery attempted from a different
  method (optifineoforge$init$modelBakery) than the initializer method <init>
```

琛ュ瓧娈垫椂鍘绘帀 `final` 鍗冲彲(璇箟涓婁粛鏄?鏋勯€犳湡璧嬪€间竴娆?)銆?

淇畬杩欎袱澶?鍚姩鍥炲埌**鍚屼竴涓ā鍨嬮摼闃诲鐐?*,浣嗚矾闈㈠凡缁忔槑鏄句笉鍚?鐪熻韩姣斾緥澶у箙鎻愰珮(`Gui` 17 涓垚鍛?+ 1 涓瓧娈靛垵濮嬪寲銆乣ModelManager` 5 涓垚鍛?+ 1 涓瓧娈靛垵濮嬪寲銆乣ClientLevel` 10 涓垚鍛樷€︹€?,鑰屼笖**涓嶅啀鏈?VerifyError / IllegalAccessError / 渚涗綋璇诲彇澶辫触**杩欑被缁撴瀯鎬ч敊璇€?

### 涓や釜鐪熸鐨勭涓€鍥?Forge 鏍囩鍔╂墜涓?union 鏂囦欢绯荤粺(2026-09-14 鏅?

妯″瀷閾剧殑 null 涓€鐩翠笉鏄涓€鍥?涓嬮潰涓ゆ潯鎵嶆槸 鈥斺€?涓ゆ潯閮?*涓嶆槸**"缂烘垚鍛?,鎵€浠ユ垚鍛樿ˉ鍏ㄨ繖涓€姝ユ案杩滅湅涓嶅埌瀹冧滑銆?

**鈶?`ItemTags.create(String, String)`:Forge 鐨勫姪鎵?NeoForge 娌℃湁銆?* 瀹屾暣鏍堢涓€娆¤瀹屾墠鐪嬫竻:

```
Failed to create mod instance. ModID: neoforge, class net.neoforged.neoforge.common.NeoForgeMod
Caused by: NullPointerException: Cannot invoke "TagKey.toString()" because "tag2" is null
  at TagConventionLogWarning.createForgeMapEntry(TagConventionLogWarning.java:557)
  at TagConventionLogWarning.<clinit>(TagConventionLogWarning.java:200)
```

`<clinit>` 绗?200 琛屾槸 `Tags.Items.DYES_BLACK`,鑰屽畠鏄?`DyeColor.BLACK.getTag()`銆侽ptiFine 鐨?DyeColor 鏋勯€犲櫒閲屽啓鐫€:

```
Reflector.ForgeItemTags_create.call("forge", "dyes/" + name)  ->  this.dyesTag
```

`ForgeItemTags_create` 鏄?`net.minecraft.tags.ItemTags.create(String, String)`,Forge 缁?`ItemTags` 琛ョ殑鍔╂墜銆侼eoForge 鐨?`ItemTags` 鍙湁 `create(ResourceLocation)`,鍙嶅皠鎵句笉鍒版柟娉曞氨杩斿洖 null,浜庢槸涓や釜鏍囩瀛楁閮芥槸 null銆侼eoForge 鑷繁鎶婅繖涓€煎綋浣滅害瀹氭爣绛?闈欐€佸垵濮嬪寲鐩存帴 NPE,`neoforge` 杩欎釜 mod 鏋勯€犲け璐?鎺ヤ笅鏉ュ氨鏄笂鐧捐

```
Cowardly refusing to send event net.neoforged.neoforge.client.event.* to a broken mod state
```

NeoForge 鐨勫鎴风浜嬩欢鍏ㄩ儴涓嶅啀娲惧彂(妯″瀷鐑樼剻鐩稿叧鐨勪簨浠朵篃鍦ㄥ叾涓?,鎵€浠?妯″瀷鏄?null"鏄繖鏉￠摼鐨勬湯绔棁鐘躲€?

淇硶鏄?*鎶婃柟娉曡ˉ鍥炲幓**,鑰屼笉鏄敼 OptiFine 鐨勮皟鐢ㄧ偣(璋冪敤鐐瑰彲鑳戒笉姝竴澶?鑰屽弽灏勫彧闇€瑕佹柟娉曞瓨鍦?:鏂板 `TagHelperFix`,鍚?`net.minecraft.tags.ItemTags` 娉ㄥ叆 `create(String, String)`,鍐呴儴濮旀墭 NeoForge 宸叉湁鐨?`create(ResourceLocation)`;鍛藉悕绌洪棿 `forge` 浜ょ粰 `ConventionTags` 鎸夌増鏈槧灏?`1.21+` 鈫?`c`, `1.20.x` 鈫?`forge`),杩欐牱 OptiFine 鎷垮埌鐨勬爣绛句笌 NeoForge 鑷繁鐨?`Tags` 瀹屽叏涓€鑷淬€備慨澶嶅悗 `Failed to create mod instance` 涓?`Cowardly refusing` 鍏ㄩ儴娑堝け,鍚姩绗竴娆¤秺杩?mod 鏋勯€犮€?

**鈶?`Path.toFile()`:鍦?union 鏂囦欢绯荤粺涓婁細鎶涖€?* 涓嬩竴涓爤鏄?

```
java.lang.UnsupportedOperationException: Path not associated with default file system.
  at java.nio.file.Path.toFile(Path.java:772)
  at net.optifine.util.ResUtils.collectFiles(ResUtils.java:104)
  at net.optifine.CustomItems.update(CustomItems.java:168)
  at net.optifine.util.TextureUtils.resourcesPreReload(TextureUtils.java:315)
```

`ResUtils.collectFiles` 鎸夊寘绫诲瀷鍒嗘祦:鐩綍鍖呰蛋 `File` 閬嶅巻,zip 鍖呰蛋瑙ｅ帇,鑰?`PathPackResources` 鍙仛 `root.toFile()`銆侳orge 鏃朵唬 mod 璧勬簮灏辨槸纾佺洏鐩綍,杩欎竴鍙ユ垚绔?NeoForge 鐨?mod 璧勬簮鍦?jar 鐨?union 鏂囦欢绯荤粺閲?杩欎竴鍙ョ洿鎺ユ姏寮傚父,鑰屼笖鍙戠敓鍦?*鍒濆璧勬簮閲嶈浇**閲?娓告垙鍦ㄦ瀯閫?`Minecraft` 鏃跺氨姝讳簡銆?

淇硶:鏂板 `PackRootsFix`(鍙敼璋冪敤鐐?淇濈暀 OptiFine 鍏朵綑鍒嗘祦閫昏緫)涓?`PackRoots.toFile`,渚濇灏濊瘯涓夋潯璺?鈥斺€?`path.toFile()`;浠?union 瀛楃涓?`union:/.../mod.jar%23214!/assets`)杩樺師鍑哄簳灞?jar 鎴栫洰褰曞苟浜ょ粰 OptiFine 鐨?zip 鍒嗘敮;閮戒笉琛屽氨鎶婃暣妫靛瓙鏍戝鍒跺埌涓存椂鐩綍(閫€鍑烘椂娓呯悊)銆傚叏澶辫触杩斿洖 null,OptiFine 瑙嗕负"杩欎釜鍖呮病鏈夊彲鐢ㄦ枃浠? 鈥斺€?涓竴涓嚜瀹氫箟鐗╁搧鐩綍杩滃ソ杩囨嫆缁濆惎鍔ㄣ€傚叏 jar 鎵弿纭 OptiFine 閲屽彧鏈変袱涓被璋冪敤 `Path.toFile`:`ResUtils` 涓?`OptiFineTransformationService`(鍚庤€呯殑璋冪敤鍦ㄩ噸鎵撳寘闃舵宸茶 `OptifineJarFixer` 鏀瑰啓)銆?

淇畬杩欎袱鏉?rig 鍒ゅ畾鏍囪绗竴娆¤蛋鍒?**`Sound engine started`**,璧勬簮閲嶈浇璺戝畬,宕╂簝鐐规帹杩涘埌娓叉煋鍔犺浇鐣岄潰銆?

**鏂扮殑绗竴鍥?闅忓悗淇帀,瑙佷笅涓€鑺?:`ClientLanguage.componentStorage` 琛ュ洖鏉ヤ簡浣嗘病鏈夊€笺€?* 璁″垝涓?`net/minecraft/client/resources/language/ClientLanguage` 琛ヤ簡瀛楁 `componentStorage` 鍜屼袱涓柟娉?`appendFrom`銆乣getComponent`,閮芥潵鑷?NeoForge 瀹㈡埛绔?jar 鐨勪緵浣?,浣?`Initialised ... restored fields` 閲屾病鏈夎繖涓被:渚涗綋鐨勮祴鍊煎湪 3 鍙傛瀯閫犲櫒閲屾槸 `this.componentStorage = <绗?3 涓弬鏁?`,鐗囨璇讳簡灞€閮ㄥ彉閲?鍙傛暟),鎸夌幇鏈?鍙厑璁?slot 0"鐨勮鍒欒鎷?瀛楁淇濇寔 null,渚涗綋鐗?`getComponent` 涓€璇诲氨 NPE:

```
NullPointerException: Cannot invoke "java.util.Map.get(Object)" because "this.componentStorage" is null
  at ClientLanguage.getComponent(ClientLanguage.java:103)
```

鍊煎緱璁颁笅鐨勬槸 OptiFine 鐨?`ClientLanguage` 鏄?**1.21.1 褰㈢姸**(鍙湁 2 鍙傛瀯閫犲櫒銆乣appendFrom(String, List, Map)`銆乣getLanguageData()`),鏃㈡病鏈?`componentStorage` 涔熸病鏈?`loadFromJson` 鈥斺€?璇存槑杩欎袱涓槸 NeoForge 琛ヤ竵鎴愬憳,鏁版嵁婧愬湪 NeoForge 鑷繁鐨勮杞借矾寰勯噷銆傞『甯︾‘璁や簡"绌?map 榛樿鍊?鏄畨鍏ㄧ殑:NeoForge 鐨?`TranslatableContents.decompose` 鎷垮埌 null 灏遍€€鍥炲瓧绗︿覆鏌ユ壘(`getOrDefault`),骞朵笉浼氬洜姝ゅ嚭閿欍€?

### 琛ュ瓧娈电殑绗笁涓潵婧?濮旀墭鏋勯€犲櫒閲岀殑榛樿鍊?2026-09-14 鏅?

涓婇潰閭ｆ潯鏈€鍚庨€変簡鏇村皬鐨勫仛娉?**鍊肩洿鎺ヤ粠"鏈被鑷繁鐨勭煭鏋勯€犲櫒"閲屽彇**銆侼eoForge 鐨?`ClientLanguage` 鏈変袱涓瀯閫犲櫒,3 鍙傜殑閭ｄ釜 `this.componentStorage = <绗?3 涓弬鏁?`,鑰?2 鍙傜殑閭ｄ釜鐢?`Map.of()` 濮旀墭杩囧幓 鈥斺€?涔熷氨鏄**褰撶被閲屾病鏈?3 鍙傛瀯閫犲櫒鏃?NeoForge 鑷繁璁ゅ畾鐨勯粯璁ゅ€煎氨鏄?`Map.of()`**銆?

`MemberRestorePlan` 鐜板湪杩欐牱澶勭悊:璧嬪€煎彸杈?*姝ｅソ鏄竴涓弬鏁板姞杞?*鏃?鍏朵綑鎯呭舰浠嶈蛋鍘熸潵鐨?鐩寸嚎娈垫惉杩?),鍘绘壘鍚岀被鐨勫彟涓€涓瀯閫犲櫒瀵瑰悓涓€涓?`<init>` 鐨勫鎵樿皟鐢?鎶婇偅涓綅缃笂鐨勫疄鍙傚彇鍑烘潵褰撻粯璁ゅ€笺€傚疄鍙傚彧鎺ュ彈"鍗曟潯銆佷笉闇€瑕佷粠鏍堜笂鍙栦笢瑗?鐨勬寚浠?甯搁噺銆乣GETSTATIC`銆乣NEW`銆佸眬閮ㄥ彉閲忓姞杞姐€侀浂鍙?`INVOKESTATIC`),鍚﹀垯鏀惧純骞朵繚鎸佸師鏍?鈥斺€?瀹佸彲鐣欑┖涔熶笉鐚溿€傚 `ClientLanguage` 鐢熸垚鐨勬鏄?

```
public static void optifineoforge$init$componentStorage(ClientLanguage self) {
    self.componentStorage = Map.of();
}
```

杩欓噷杩樻湁涓€涓皬鍧戝€煎緱璁?**灞€閮ㄥ彉閲忓姞杞藉繀椤荤畻浣?鍘嬫爤鑰屼笉寮规爤"**銆傜涓€鐗堟妸 `ILOAD/ALOAD` 涓€寰嬪垽涓轰笉鍚堟牸,浜庢槸"鍏堣鍙傛暟鍐嶅鎵?鐨勬瀯閫犲櫒鏁翠釜琚惁鎺?`Map.of()` 閭ｄ竴璺牴鏈病琚湅鍒?鈥斺€?琛ㄧ幇涓轰緵浣撻噷灏辨槸娌℃湁鍒濆鍖栨柟娉曘€傛煡杩欑闂涓嶉渶瑕佸惎鍔ㄦ父鎴?`javap` 渚涗綋绫绘枃浠朵竴鐪煎氨鑳界湅鍒般€?

### 鐜扮姸:娓告垙鑳藉惎鍔ㄥ苟鍋滃湪涓诲惊鐜?2026-09-14 鏅?

琛ヤ笂杩欐潯涔嬪悗 rig 鐨勫垽瀹氱涓€娆℃槸 **`STARTED`**(涓嶆槸宕╂簝):`OpenAL initialized` 鈫?`Sound engine started`,璐村浘闆嗛€愪釜寤烘垚骞朵即闅?OptiFine 鑷繁鐨勬棩蹇?**`[OptiFine] Animated sprites: 0`**銆乣[OptiFine] Scaled too small texture: minecraft:missingno`),杩涚▼鎸佺画杩愯鍒?rig 涓诲姩缁撴潫,骞剁暀涓嬩簡鎴浘銆?

棣栬疆璧勬簮閲嶈浇浠嶇劧鎶ヤ竴娆￠敊,浣?*娌℃湁鎶婃父鎴忔墦鎺?*:

```
NullPointerException: BakedModel.getParticleIcon() ... BlockModelShaper.getBlockModel(BlockState) is null
  at LiquidBlockRenderer.setupSprites(LiquidBlockRenderer.java:43)
  at BlockRenderDispatcher.onResourceManagerReload(BlockRenderDispatcher.java:157)
  at ResourceManagerReloadListener.lambda$reload$0(ResourceManagerReloadListener.java:16)
```

闅忓悗鏄?`Caught error loading resourcepacks, removing all selected resourcepacks`,娓告垙鑷繁閲嶈瘯绗簩杞噸杞?绗簩杞创鍥鹃泦姝ｅ父寤哄畬 鈥斺€?涔熷氨鏄**妯″瀷纭疄琚儤鐒欒繃,鍙槸绗竴杞噷 `BlockRenderDispatcher` 鐨勫簲鐢ㄩ樁娈佃窇鍦ㄤ簡 `ModelManager.apply` 涔嬪墠**(涓よ竟鐨?`missingModel` 閮藉彧鍦?`apply(ReloadState, ProfilerFiller)` 閲岃祴鍊?`javap` 宸茬‘璁?,`getModel` 浜庢槸鎷?`missingModel` 杩欎釜 null 褰撳厹搴曡繑鍥炪€傜浜岃疆鑳藉ソ,璇存槑闂鍑哄湪**绗竴杞殑鐩戝惉鍣ㄩ泦鍚?椤哄簭**涓?鑰屼笉鏄ā鍨嬫暟鎹湰韬€?

杩欐潯鏄笅涓€杞殑绗竴浠朵簨:鎶?`ReloadableResourceManager`(OptiFine 涔熸浛鎹簡瀹?`ReloadableResourceManagerFix` 琛ョ殑姝ｆ槸 NeoForge 鐨?`getListeners`/`updateListenersFrom`)鍦ㄩ杞噸杞芥椂瀹為檯浣跨敤鐨勭洃鍚櫒椤哄簭鎵撳嚭鏉?涓?`ModelManager`/`BlockRenderDispatcher` 鐨勫簲鏈夋搴忓鐓с€?

### 绌烘ā鍨嬬殑鐪熷洜:鐑樼剻鍑烘潵鐨勭己澶辨ā鍨嬫湰韬氨鏄?null(2026-09-14 娣卞)

涓婁竴鑺傞偅鏉＄寽娴?*琚疄娴嬫帹缈?*:椤哄簭娌￠棶棰?鐑樼剻涔熺‘瀹炶窇浜嗐€備负浜嗙湅娓呰繖涓€鐐瑰姞浜嗕袱涓?*榛樿鍏抽棴**鐨勬帰閽?鎵撳紑鏂瑰紡:`-Doptifineoforge.debug.reload=true` 鎴栫幆澧冨彉閲?`OPTIFINEOF..._DEBUG_RELOAD=true`):

- `ReloadProbeFix`:鍦?`ReloadableResourceManager.createReload` 璇?`listeners` 鐨勫湴鏂规彃涓€鏉?`DUP` + 璋冪敤,鎶婅繖娆￠噸杞?*灏嗚鎸夊簭鎵ц鐨勭洃鍚櫒娓呭崟**鎵撳嚭鏉?
- `ModelProbeFix`:鍦?`ModelManager.apply` 鎵?enter/leave(閫€鍑烘椂鍙嶅皠璇?`missingModel` 涓?`blockStates`),鍦?`ModelBakery.bakeModels` 鎵撹繘鍘绘椂鏈儤鐒欐ā鍨嬨€佽繑鍥炴椂鐑樼剻缁撴灉,鍦?`BlockRenderDispatcher.onResourceManagerReload` 鎵?enter銆?

瀹炴祴(1.21.4,`logs/run-probe7-1214`):

```
reload 1: 50 listeners
8   net.minecraft.client.resources.model.ModelManager          <- bakes the models
11  net.minecraft.client.renderer.block.BlockRenderDispatcher  <- asks for baked models
...
result ModelBakery (unbaked): missingModel=BlockModel
result ModelBakery.bakeModels: missingModel=null, blockStates=27870
enter BlockRenderDispatcher.onResourceManagerReload
```

涓夋潯缁撹:

1. **椤哄簭姝ｇ‘**:`ModelManager` 鍦ㄧ 8 浣嶃€乣BlockRenderDispatcher` 鍦ㄧ 11 浣?`apply` 涔熺湡鐨勬墽琛屼簡(鑰屼笖鎶?27870 涓柟鍧楃姸鎬佽杩涗簡 `bakedBlockStateModels`)銆傛墍浠?搴旂敤闃舵璺戝湪鐑樼剻涔嬪墠"涓嶆垚绔嬨€?
2. **`missingModel` 鍦ㄧ儤鐒欓偅涓€鍒诲氨宸茬粡鏄?null**:鍚屼竴鎵?worker 绾跨▼閲?`ModelBakery` 鑷繁鐨勬湭鐑樼剻缂哄け妯″瀷鏄?`BlockModel`(姝ｅ父),浣?`bakeModels` 杩斿洖鐨?`BakingResult.missingModel` 鏄?null;`ModelManager.apply` 鍙槸鎶婅繖涓?null 鍘熸牱鎼繘瀛楁銆備簬鏄?`getModel` 瀵?*涓嶅湪閭?27870 椤归噷鐨勭姸鎬?*(渚嬪娴佷綋)杩斿洖 null 鈥斺€?`LiquidBlockRenderer.setupSprites` 涓€鍙?`getParticleIcon()` 灏?NPE銆?
3. **鐩戝惉鍣ㄦ竻鍗曢噷姣忎釜鍘熺増鐩戝惉鍣ㄥ嚭鐜颁簡涓ゆ**(0鈥?1 涓?24鈥?5 鏄悓涓€鎵?48/49 鎵嶆槸 OptiFine 鐨?銆備篃灏辨槸 NeoForge 鎺掑簭鍚庣殑娓呭崟琚?*杩藉姞**鍒颁簡鍘熺増娓呭崟鍚庨潰,鑰屼笉鏄浛鎹€傝繖鏈韩灏辨槸瑕佷慨鐨勭己闄?姣忎釜鍘熺増鐩戝惉鍣ㄤ細璺戜袱閬?鐑樼剻涔熺‘瀹炶窇浜嗕袱閬?涓や釜 worker 鍚勬墦浜嗕竴娆?銆?

椤哄甫璁颁竴涓?*璇婃柇闄烽槺**,瀹冩氮璐逛簡涓ゆ杩愯:`ITransformer.transform` 鎷垮埌鐨?`ClassNode.name` 鏄?*鏂滄潬鍐呴儴鍚?*,鑰?ModLauncher 鐨?`Target.targetClass` 鐢ㄧ殑鏄?*鐐瑰彿鍚?*銆傛妸涓よ€呮贩鐢ㄤ細璁╂帰閽堥潤榛樺け鏁?鏃ュ織閲?`probed 0`),鐪嬭捣鏉ュ氨鍍?杩欎釜绫绘牴鏈病琚浆鎹?銆傚垽鎹槸鍚屼竴琛屾棩蹇楅噷鎵撳嵃鐨勭被鍚?鈥斺€?甯︽枩鏉犵殑灏辨槸鍐呴儴鍚嶃€?

涓嬩竴姝ュ緢鏄庣‘:`bakeWithTopModelValues`(NeoForge 缁?`UnbakedModel` 鍔犵殑闈欐€佸姪鎵?浼氭妸璋冪敤杞粰 `IUnbakedModelExtension.bake(TextureSlots, ModelBaker, ModelState, boolean, boolean, ItemTransforms, ContextMap)`(涓冧釜鍙傛暟銆佹渶鍚庝竴涓?`ContextMap`,鐢?`UnbakedModel` 缁ф壙鐨?NeoForge 鎵╁睍鎺ュ彛澹版槑)銆侽ptiFine **娌℃湁**鏇挎崲 `BlockModel`(琛ヤ竵娓呭崟閲屾病鏈夊畠鐨?xdelta),鎵€浠?null 鏄粠 NeoForge 杩欐潯璺笂鍑烘潵鐨?鈥斺€?瑕佽鐨勬槸 `IUnbakedModelExtension` 閲岄偅涓粯璁ゅ疄鐜扮殑鍑芥暟浣?瀹冧笉鍦?`neoforge-...-client.jar` 閲?寰楀幓鍒殑 NeoForge 浜х墿閲屾壘),纭瀹冨湪浠€涔堟潯浠朵笅杩斿洖 null銆?

### 绌烘ā鍨嬬殑鐪熷洜鎵惧埌浜?琚垜浠嚜宸?鎵撴垚妗?鐨勬柟娉?2026-09-14 娣卞)

椤虹潃涓婁竴鑺傜户缁墦鎺㈤拡,`BlockModel.bake`(涓冨弬銆佸甫 `ContextMap`)姣忎竴娆¤繑鍥為兘鏄?`null`:

```
value BlockModel.bake (...ContextMap;)Lnet/minecraft/client/resources/model/BakedModel;: null
value UnbakedModel.bakeWithTopModelValues (...): null
```

涔熷氨鏄 null 涓嶆槸"浼犱涪浜?,鑰屾槸浠庣儤鐒欓噷鍑烘潵鐨勩€俙IUnbakedModelExtension`(鍦?`neoforge-...-universal.jar` 閲?鐨勯粯璁ゅ疄鐜板彧鏄€佽€佸疄瀹炶浆璋冨叚鍙?`UnbakedModel.bake`,NeoForge 鐨?`BlockModel.bake(涓冨弬)` 鏈€鍚庝竴琛屾槸 `SimpleBakedModel.bakeElements(...)` 鈥斺€?浜庢槸闂钀藉湪杩欎釜**闈欐€佸姪鎵?*涓娿€?

鏌ヤ緵浣撳氨涓€鐩簡鐒?

```
public static BakedModel bakeElements(...);
Code:
0: aconst_null
1: areturn
```

**瀹冩槸琚?`MemberRestorePlan` 鎵撴垚妗╃殑**:`referencesOnlyExisting` 鍒ゅ畾"鍑芥暟浣撳紩鐢ㄤ簡涓嶅瓨鍦ㄧ殑鎴愬憳"鏃?浼氬啓涓€涓繑鍥為粯璁ゅ€肩殑妗?鑰屾々瀵瑰璞¤繑鍥炵被鍨嬪氨鏄?`aconst_null`銆傝繖鏉¤矾寰勪竴涓ā鍨嬮兘娲讳笉涓嬫潵 鈥斺€?杩欐棦鏄┖妯″瀷鐨勭湡鍥?涔熸槸"琛ュ洖鏉ョ殑鎴愬憳鐪嬬潃瀵逛簡銆佸叾瀹炲叏鏄┖澹?杩欑被闂鐨勬牱鏈€?

**涓轰粈涔堜細琚垽涓嶅悎鏍?** 瑙勫垯閲屾湁涓€鏉?super 璋冪敤鍙湁鍦ㄨ澶嶅埗杩涘幓鐨勭被鐨勭埗绫荤浉鍚屾椂鎵嶆垚绔?(闃叉 `VerifyError: Bad invokespecial`)銆備絾瀹冪殑鍐欐硶鎶?*鎵€鏈?* `INVOKESPECIAL` 閮藉綋鎴?super 璋冪敤,鍖呮嫭 `new SimpleBakedModel$Builder(...)` 杩欑**鏋勯€犲埆鐨勭被**鐨勮皟鐢?鈥斺€?鑰?`SimpleBakedModel$Builder` 鎭板ソ琚?OptiFine 鏇挎崲杩?浜庢槸杩欎竴鍙ヨ璇垽,杩涜€屾妸鏁翠釜 `bakeElements` 鎵撴垚浜嗘々銆?

淇硶鍙湁涓€琛岃涔?鏋勯€犲櫒璋冪敤涓嶆槸 super 璋冪敤(`invokespecial` 浣嗗悕瀛楁槸 `<init>`,浣滅敤鍦ㄥ垰鍒嗛厤鍑烘潵鐨勫埆鐨勭被鐨勫璞′笂,璺熻澶嶅埗杩涘幓鐨勭被鐨勭埗绫绘棤鍏?銆傛敼瀹屼箣鍚?

- 鏋勫缓杈撳嚭閲?**`stub (body would not verify)` 涓€琛岄兘娌℃湁浜?*(姝ゅ墠鏈?鍙槸娌¤鎴戞墦鍗板嚭鏉ョ湅);
- 渚涗綋閲岀殑 `SimpleBakedModel.bakeElements` 鏄湡姝ｇ殑鍑芥暟浣?浼?`new SimpleBakedModel$Builder(...)`銆侀€愪釜 `bakeFace`銆佹渶鍚?`builder.build(...)`)銆?

杩欏"闈欓粯鎵撴々"鍊煎緱褰撲綔涓€鏉℃暀璁涓嬫潵:**妗╂槸鏈€鍚庣殑鍏滃簳,浣嗗畠鐨勫け璐ユ柟寮忔槸闈欓粯鐨?*,鍙湁鎷垮埌鍏蜂綋鐥囩姸(妯″瀷鍏ㄧ┖)鎵嶆毚闇层€備互鍚庢瘡娆℃瀯寤洪兘璇ョ湅涓€鐪兼湁鍝簺妗?鑰屼笉鏄瀹冩倓鎮勫瓨鍦ㄣ€?

淇畬鍚庢棫鐥囩姸娑堝け(`BlockModelShaper.getBlockModel` 杩斿洖 null 鐨?NPE 涓嶅啀鍑虹幇銆乣Cowardly refusing ... broken mod state` 涔熸病浜?,浣嗙揣鎺ョ潃鎹㈡垚涓€涓?*鏄庣‘寰楀**鐨勯敊璇?鈥斺€?OptiFine 鏇挎崲杩囩殑 `SimpleBakedModel$Builder` 鏋勯€犲櫒閲岃浜?Forge 绫?

```
java.lang.NoSuchFieldError: Class net.minecraftforge.client.RenderTypeGroup does not have member
  field 'net.minecraftforge.client.RenderTypeGroup EMPTY'
  at net.minecraft.client.resources.model.SimpleBakedModel$Builder.<init>(SimpleBakedModel.java:215)
  at net.minecraft.client.resources.model.SimpleBakedModel.bakeElements(SimpleBakedModel.java:100)
```

鎴戜滑涓?Forge 绫荤敓鎴愮殑**绌哄３**(`ForgeApiShims`)鍙湁绫诲悕銆佹病鏈夋垚鍛?鑰?OptiFine 鐨勪唬鐮佽璇?`RenderTypeGroup.EMPTY` 杩欎釜闈欐€佸瓧娈点€傝繖灏辨槸涓嬩竴姝?璁?Forge 绌哄３甯︿笂 OptiFine 鐪熸寮曠敤鐨勬垚鍛?瀛楁缁欏父閲?鍗犱綅瀹炰緥,鏂规硶缁欓粯璁よ繑鍥?,鑰屼笉鏄彧鏈変竴涓┖绫?鈥斺€?鍚﹀垯姣忎慨濂戒竴澶勫氨浼氭挒涓婁笅涓€涓€?

鍙﹀璁颁竴鍙ュソ娑堟伅:杩欎竴杞?OptiFine 鐨勫姛鑳藉凡缁忓湪璺?鈥斺€?鏃ュ織閲屾湁 `[OptiFine] ConnectedTextures: optifine/ctm/default/00_glass_white/glass_white.properties`銆乣CustomItems: Registering sprites`銆乣BetterGrass: Parsing default configuration`,璐村浘闆嗕篃鍦ㄦ甯告嫾鎺ャ€?

### 涓夊杩炵潃鐨勪慨澶?妯″瀷缁堜簬鐪熺殑鐑樼剻鍑烘潵浜?2026-09-15 鍑屾櫒)

**鈶?Forge 绌哄３涓嶅啀鏄┖澹炽€?* `ForgeApiShims` 鐜板湪闄や簡绫诲悕,杩樺甫涓?OptiFine 鐨勭被**鐪熸璇诲啓鐨勬垚鍛?*:鎵?OptiFine jar 閲屾瘡涓?class 鐨勫父閲忔睜(ASM `visitFieldInsn`/`visitMethodInsn`),鎶?owner 鏄?`net/minecraftforge/**` 鐨勫瓧娈典笌鏂规硶鎸夌鍚嶆敹闆嗚捣鏉?鐢熸垚鍒板搴旂┖澹抽噷 鈥斺€?瀛楁缁欓粯璁ゅ€?null/0/false),鏂规硶缁欓粯璁よ繑鍥?鎺ュ彛鐨勫疄渚嬫柟娉曚繚鎸佹娊璞?璋庣О鑳界瓟涓嶅璇寸瓟涓嶄簡),寮曠敤鍒扮殑鏋勯€犲櫒琛ヤ竴涓彧璋?`super()` 鐨勩€?

杩欓噷鏈変釜**鍏抽敭鐨勯『搴忛棶棰?*:闇€瑕佹垚鍛樼殑寮曠敤鍦?*鎵撳畬琛ヤ竵鐨勬父鎴忕被**閲?渚嬪 OptiFine 鏇挎崲鍚庣殑 `SimpleBakedModel$Builder` 璇?`RenderTypeGroup.EMPTY`),鑰岃ˉ涓佽緭鍑烘槸鍦ㄩ噸鎵撳寘涔嬪悗鎵嶆湁鐨勩€傛墍浠?rig 鐨勬瀯寤鸿剼鏈敼鎴愬湪 patch 姝ラ涔嬪悗銆佺敤 `ForgeApiShims <杈撳嚭鐩綍> <patched jar> <OptiFine jar>` **閲嶆柊鐢熸垚**涓€閬嶇┖澹?骞跺湪鍚堝苟鏃朵涪鎺夐噸鎵撳寘闃舵鐢熸垚鐨勯偅鎵圭┖澹?鍚﹀垯浼氳"鍙坊鍔犱笉瑕嗙洊"鐨勯€昏緫鎸′綇)銆傚疄娴?55 涓?Forge 绫诲瀷銆?4 涓垚鍛?`RenderTypeGroup` 鐜板湪鏈?`EMPTY`銆乣isEmpty()`銆乣block()`銆?

**鈶?鏋勯€犲櫒涔熻琛ャ€?* 淇畬 鈶?涓嬩竴涓敊璇槸:

```
NoSuchMethodError: 'void SimpleBakedModel.<init>(List, Map, boolean, boolean, boolean,
  TextureAtlasSprite, ItemTransforms, net.neoforged.neoforge.client.RenderTypeGroup)'
```

杩欐槸 NeoForge 缁?`SimpleBakedModel` 鍔犵殑閲嶈浇(绗叓涓弬鏁版槸 **NeoForge** 鐨?`RenderTypeGroup`),OptiFine 鐨勬浛鎹㈢増鏈彧鏈?**Forge** 绫诲瀷鐨勯偅涓悓鍚嶉噸杞?鑰屾垜浠ˉ鍥炴潵鐨?`SimpleBakedModel$Builder.build(...)` 姝ｆ槸瑕佽皟 NeoForge 杩欎釜銆傝鍒掗噷鍘熷厛涓€鍙?`continue` 鎶婃墍鏈?`<init>` 閮借烦杩囦簡("constructors are handled by the targeted fixes"),鐜板湪鏀规垚**鏋勯€犲櫒涔熻繘璁″垝**,浣嗘湁涓€鏉＄‖瑙勫垯:**鏋勯€犲櫒涓嶅悎鏍煎氨涓嶈ˉ,缁濅笉鎵撴々** 鈥斺€?绌烘瀯閫犲櫒涓嶄細杩?`super`,鏁翠釜绫婚兘杩囦笉浜嗛獙璇?琛ヤ竴涓潖鐨勮繕涓嶅涓嶈ˉ(浼氭墦鍗?`constructor not restorable`)銆傝鍒掓垚鍛樻暟浠?124 娑ㄥ埌 132(鍚繖涓瀯閫犲櫒)銆?

椤哄甫淇簡涓€涓細**浜掔浉鎵撴灦**鐨勫湴鏂?琛ュ瓧娈电殑鍒濆鍖栨柟娉曟鍓嶄細琚彃杩?*姣忎釜**鏋勯€犲櫒鐨勬瘡涓?`RETURN` 涔嬪墠,鍖呮嫭鍒氳ˉ杩涙潵鐨勯偅涓瀯閫犲櫒 鈥斺€?鑰岄偅涓瀯閫犲櫒鑷繁宸茬粡璧嬩簡鍊?鍒濆鍖栨柟娉曞啀鎶婇粯璁ゅ€煎啓鍥炲幓,绛変簬鎶婂€兼姽鎺夈€傜幇鍦ㄥ彧瀵归偅浜?*娌℃湁鑷繁璧嬪€?*鐨勫瓧娈垫彃鍒濆鍖栬皟鐢ㄣ€?

**缁撴灉(瀹炴祴)**:妯″瀷閾惧交搴曢€氫簡 鈥斺€?

```
result ModelBakery.bakeModels: missingModel=SimpleBakedModel, blockStates=27870
leave ModelManager.apply: missingModel=SimpleBakedModel, blockStates=27870
[OptiFine] Animated sprites: 2
Created: 1024x1024x4 minecraft:textures/atlas/blocks.png-atlas
Sound engine started
```

`missingModel` 鏄竴涓湡鐨?`SimpleBakedModel`(涓嶅啀鏄?null),鏂瑰潡璐村浘闆?1024x1024 涔熷缓鍑烘潵浜?rig 鍒ゅ畾 **`STARTED`**銆?

**鏂扮殑绗竴鍥?鏈В鍐?**:璐村浘闆嗘嫾鎺ユ姤閿?娓告垙闅忓悗鑷鎭㈠:

```
net.minecraft.ReportedException: Stitching texture atlas
Caused by: java.lang.IllegalStateException: Image is not allocated.
```

杩欐潯澶ф鐜囧湪 OptiFine 鐨勮嚜瀹氫箟璐村浘/杩炴帴绾圭悊璺緞涓?`CustomItems`/`ConnectedTextures` 杩欎竴杞凡缁忓湪璺?,涓嬩竴杞粠杩欓噷寮€濮嬨€?

### 璐村浘闆嗕笂浼犲け璐ョ殑瀹氫綅(2026-09-15 鍑屾櫒)

鍙堝姞浜嗕竴涓帰閽?`NativeImageProbeFix`,榛樿鍏抽棴):鍦?`NativeImage.close` 鎵撲竴鏉″甫**鐭爤**鐨?trace,骞跺湪 `NativeImage.upload` 鎵?enter銆備竴娆¤繍琛屾嬁鍒?31402 娆?`close`銆?0283 娆?`upload`,鎸夎皟鐢ㄨ€呭垎缁?

```
  30634  net.minecraft.client.renderer.texture.SpriteContents.close
    230  SpriteContents$InterpolationData.close
    176  SpriteContents.rescale
    160  atlas.sources.LazyLoadedImage.release
```

鍐嶅 `SpriteContents.close` 鐨勮皟鐢ㄨ€呭垎缁?鍙湁涓€涓?

```
  30634  net.minecraft.client.renderer.texture.TextureAtlas.clearTextureData
```

瀹屾暣鏍堥暱杩欐牱(涓€娆″疄渚?:

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

涔熷氨鏄:**`TextureAtlas.upload` 鍦?`clearTextureData()` 閲屽叧鎺夌殑,姝ｆ槸瀹冩帴涓嬫潵瑕佷笂浼犵殑閭ｆ壒绮剧伒** 鈥斺€?`clearTextureData` 鏈剰鏄噴鏀?*涓婁竴鎵?*绮剧伒鐨?ticker 涓庢彃鍊兼暟鎹?杩欓噷鍗村叧鍒颁簡鏂扮殑閭ｆ壒,浜庢槸 `SpriteContents.uploadFirstFrame` 涓€涓婁紶灏辨挒涓?`Image is not allocated.`(30k 杩欎釜鏁伴噺绾т篃鍜屾柟鍧楄创鍥鹃泦鐨勭簿鐏垫暟鍚诲悎)銆?

鏈€鍙兘鐨勫師鍥犲氨鏄笂涓€鑺傝涓嬬殑閭ｄ釜缂洪櫡:**鐩戝惉鍣ㄦ竻鍗曢噷姣忎釜鍘熺増鐩戝惉鍣ㄥ嚭鐜颁簡涓ゆ**銆傚悓涓€鎵圭簿鐏佃涓よ疆涓婁紶/娓呯悊浜ゅ弶澶勭悊鏃?绗簩杞殑 `clearTextureData` 鍏虫帀鐨勬鏄涓€杞垰寤哄ソ鐨勭簿鐏点€備笅涓€杞涓€浠朵簨:鏌ユ竻杩欎唤娓呭崟涓轰粈涔堜細閲嶅 鈥斺€?闇€瑕佸鐓?OptiFine 鏇挎崲鍚庣殑 `Minecraft`(Forge 鏃朵唬閭ｇ増)涓?NeoForge 鐨勬敞鍐岄『搴?浠ュ強 `ReloadableResourceManager.updateListenersFrom` 鍒板簳璇ユ浛鎹㈣繕鏄拷鍔犮€?

### 閲嶅娓呭崟鐨勬潵婧愮缉鍒?NeoForge 鑷繁鐨勬帓搴忛噷(2026-09-15 鍑屾櫒)

鎺㈤拡鎵╁埌 `registerReloadListener`(鎵撳嵃琚敞鍐岀殑鐩戝惉鍣?涓?`updateListenersFrom`(鏇挎崲鍓嶅悗鍚勬墦鍗颁竴娆℃竻鍗曢暱搴?銆傚疄娴嬪簭鍒楅潪甯稿共鍑€:

```
registerReloadListener: LanguageManager 鈥?PeriodicNotificationManager   鈫?22 涓師鐗堢洃鍚櫒
size before updateListenersFrom: 22
size after  updateListenersFrom: 48      鈫?纭疄鏄?鏇挎崲",涓嶆槸杩藉姞
registerReloadListener: (鍖垮悕绫? 脳2                                      鈫?OptiFine 鐨?TextureUtils$1/$2
reload 1: 50 listeners
```

浜庢槸鍏堝墠鐨勭寽娴嬭鎺掗櫎涓ゆ潯,涔熷畾浣嶅埌鐪熸鐨勮寖鍥?

- **`updateListenersFrom` 鏄鐨?*(鏇挎崲鑰岄潪杩藉姞)銆傜绾胯渚涗綋瀛楄妭鐮佸氨鏄繖涓? `this.listeners = ReloadListenerSort.sort(event)` 鈥斺€?渚涗綋鏄?NeoForge 鑷繁鐨勫疄鐜?涓€琛屼笉宸€?
- **`Minecraft` 娌℃湁琚?OptiFine 鏇挎崲**(琛ヤ竵娓呭崟閲屾病鏈夊畠),鎵€浠ラ偅 22 娆℃敞鍐屽氨鏄父鎴忚嚜宸辩殑銆?
- **閲嶅鍙戠敓鍦ㄦ帓搴忕粨鏋滃唴閮?*:鏇挎崲鍚庣珛鍒诲氨鏄?48 鏉?鑰?48 = 鍘熺増 22 + 2 涓?NeoForge lambda + **鍘熺増 22 鍙堜竴閬?* + ObjLoader/AnimationLoader;鏈€鍚?OptiFine 鐨?2 涓尶鍚嶇洃鍚櫒鏄湪鏇挎崲涔嬪悗鍐嶆敞鍐岀殑,浜庢槸鍙樻垚 50銆?

璋冪敤閾句篃璇绘竻浜?`ClientHooks.initClientHooks(Minecraft, ReloadableResourceManager)` 閲?`new AddClientReloadListenersEvent(resourceManager)` 鈫?`ModLoader.postEvent(event)` 鈫?`resourceManager.updateListenersFrom(event)`,鑰岃繖涓簨浠剁殑鏋勯€犲櫒灏辨槸鎶?`ReloadableResourceManager.getListeners()`(閭?22 涓?浜ょ粰 `SortedReloadListenerEvent`銆?

涔熷氨鏄?**褰?`getListeners()` 閲屽凡缁忔湁鍘熺増鐩戝惉鍣ㄦ椂,NeoForge 鐨勮繖鏉℃帓搴忚矾寰勪細鎶婂畠浠啀绠椾竴閬?*銆備笅涓€杞璇荤殑鏄?`SortedReloadListenerEvent` 鐨勫浘/娉ㄥ唽琛ㄦ瀯寤轰笌 `ReloadListenerSort.sortListeners`,纭鍘熺増閭ｄ竴缁勬槸浠庡摢閲岀浜屾杩涙潵鐨?鍐嶅喅瀹氫慨鍦ㄦ垜浠繖杈?渚嬪浜嬩欢鏋勯€犲墠璁?`getListeners()` 鍙粰鍑烘ā缁勬柊澧炵殑閭ｄ簺)杩樻槸鍦ㄦ浛鎹㈢被涓婅ˉ涓€涓瓑浠风墿銆?

### 鎺掑簭鍐呴儴:涓や釜寰呴獙鐨勬満鍒?2026-09-15 鍑屾櫒)

涓婇潰涓ゆ潯璺兘璇昏繃浜?缁撹鏄?*瀹冧滑閮戒笉鍙兘鍑┖浜х敓閲嶅**,浜庢槸鎶婅寖鍥村帇鍒颁袱绉嶆満鍒朵箣涓€,涓嬩竴杞敤涓€涓帰閽堝氨鑳藉垎寮€:

- `SortedReloadListenerEvent` 鐨勬瀯閫犲櫒(`neoforge-...-universal.jar`)瀵逛紶杩涙潵鐨勬竻鍗曢€愪釜璋冪敤
  `addListener(nameLookup.apply(listener), listener)`,鑰?`addListener` 寰€ `LinkedHashMap registry` 閲屾斁 鈥斺€?**鍚屼竴涓?key 鍙細瑕嗙洊,涓嶄細閲嶅**;
- `ReloadListenerSort.sortListeners` 鏈€鍚庢槸
  `TopologicalSort.topologicalSort(graph, comparingInt(order))`,杈撳叆鏄?guava 鐨?`MutableGraph`,鑰?guava 鍥炬槸**鑺傜偣鐨勯泦鍚?閲嶅鑺傜偣浼氳鍚堝苟**銆?

涔熷氨鏄,瑕佽鎺掑簭缁撴灉閲屽嚭鐜颁袱閬嶅師鐗堢洃鍚櫒,鍙兘鏄笅闈簩鑰呬箣涓€:

1. **涓ら亶鐨?key 涓嶅悓**(渚嬪 `getNameForClass` 鍓嶅悗杩斿洖涓嶅悓鐨勫悕瀛?,浜庢槸 `registry` 涓庡浘涓悓鏃剁暀涓嬩袱缁勩€佹瘡缁?22 涓?
2. **NeoForge 鍐呴儴杩樻湁涓€浠藉師鐗堢洃鍚櫒鐨勬潵婧?*(渚嬪 `VanillaClientListeners` 鑷繁寰€鍥鹃噷娉ㄥ唽浜嗕竴閬?,涓庢父鎴忔敞鍐岀殑閭?22 涓悇鑷垚缁勩€?

鍖哄垎鍔炴硶寰堢洿鎺?鍦?`updateListenersFrom` 杩斿洖澶勬妸缁撴灉娓呭崟**鎸夊璞¤韩浠?*(`System.identityHashCode`)鎵撳嚭鏉?鈥斺€?濡傛灉涓ょ粍 22 涓殑 identity 鐩稿悓,灏辨槸鏈哄埗 1;濡傛灉 identity 涓嶅悓,灏辨槸鏈哄埗 2(涓ょ粍鏄笉鍚屽疄渚?銆傝繖姣旂户缁瀛楄妭鐮佸揩寰楀銆?

### 瀹炴祴:鏄?*鍚屼竴涓璞?*鍑虹幇浜嗕袱娆?2026-09-15 鍑屾櫒)

鎺㈤拡鎵撲笂浜?identity,缁撴灉寰堝共鍑€:

```
8   net.minecraft.client.resources.model.ModelManager  @2007522934   <- bakes the models
32  net.minecraft.client.resources.model.ModelManager  @2007522934   <- bakes the models
```

涓ょ粍閲屽搴斾綅缃殑 identity **瀹屽叏鐩稿悓** 鈥斺€?鎵€浠ヤ笉鏄?涓や唤涓嶅悓鐨勫疄渚?,鑰屾槸**鍚屼竴鎵瑰璞¤鎺掕繘浜嗙粨鏋滀袱娆?*:鏈哄埗 1 閭ｄ竴渚с€?0 鏉￠噷鍙湁 28 涓笉鍚岀殑绫诲悕,涔熷嵃璇佷簡杩欎竴鐐广€?

缁撳悎鍓嶉潰璇诲埌鐨勪袱娈靛疄鐜?浜嬩欢鏋勯€犲櫒鐢?`LinkedHashMap` 娉ㄥ唽銆乣sortListeners` 璧?guava 鍥?,鏈€鍚堢悊鐨勮В閲婃槸:**缁撴灉 = 銆屾寜鍘熺増鍚嶅瓧鎺掑垪鐨勯偅涓€缁勩€嶅墠缂€ + 銆岃蛋鎷撴墤鎺掑簭寰楀埌鐨勯偅涓€缁勩€?*,鑰屽悗鑰呮湰鏉ヤ篃搴旇鍖呭惈妯＄粍鐩戝惉鍣?褰撲簨浠舵瀯閫犳椂浼犺繘鏉ョ殑 `getListeners()` **宸茬粡鍚偅 22 涓師鐗堢洃鍚櫒**鏃?瀹冧滑灏卞悓鏃跺嚭鐜板湪杩欎袱閮ㄥ垎閲屻€?

涔熷氨鏄,闂鍙兘涓嶅湪"璋佹敞鍐屼簡涓ゆ",鑰屽湪**浜嬩欢鏋勯€犵殑鏃跺埢**:杩欎竴涓繍琛岄噷 22 娆℃敞鍐屽彂鐢熷湪 `updateListenersFrom` 涔嬪墠銆備笅涓€杞鍋氫竴娆?*瀵圭収娴嬮噺**:鐢ㄥ悓涓€濂楁帰閽?鍦?*鍙 NeoForge銆佷笉鎵?OptiFine 琛ヤ竵**鐨勬儏鍐典笅璺戜竴閬?闇€瑕佷竴涓彧鍚?loader 涓庢帰閽堛€佷笉鍚?OptiFine 鐨?jar),鐪嬪師鐗?NeoForge 鍦ㄨ繖涓椂鍒?`getListeners()` 鏄笉鏄┖鐨勩€乣updateListenersFrom` 鍓嶅悗鍚勬槸澶氬皯 鈥斺€?杩欒兘鐩存帴鍒ゅ畾"椤哄簭琚皝鏀逛簡",鑰屼笉蹇呯户缁寽銆?

**椤哄簭杩欐潯绾跨储涔熸柇浜嗐€?* 璇?NeoForge 鑷繁鐨?`Minecraft` 瀛楄妭鐮?22 娆?`registerReloadListener` 鐨勫亸绉绘槸 971鈥?916,鑰?`ClientHooks.initClientHooks` 鍦?**2192** 鈥斺€?涔熷氨鏄**鍘熺増 NeoForge 涔熸槸鍏堟敞鍐屻€佸悗寤轰簨浠?*,鍜屾垜浠湅鍒扮殑瀹屽叏涓€鏍枫€傛墍浠?椤哄簭琚敼鍔?涓嶆垚绔?閲嶅鏄湪**鍘熺増灏变細鍙戠敓鐨勯偅鏉¤矾寰?*閲屼骇鐢熺殑,宸埆鍙彲鑳藉湪**杈撳叆鐨勫唴瀹?*涓?`VanillaClientListeners` 璁ゅ緱閭ｄ簺鍘熺増鐩戝惉鍣ㄧ被鏃舵墠浼氭妸瀹冧滑鎺掗櫎鍦ㄦ嫇鎵戞帓搴忎箣澶?鍙湁鍦ㄥ畠璁や笉鍑烘椂鎵嶄細琚帓绗簩閬嶃€備簬鏄鐓ф祴閲忎粛鐒跺€煎緱鍋?浣嗚鐪嬬殑鏄?璇嗗埆"鑰屼笉鏄?椤哄簭"銆?

## Control measurement 2026-09-15: the duplicate listener list is OptiFine's doing

The same probes, the same code, on the same profile, with only the loader jar in
mods/ and no OptiFine at all (built by build-loader-jar.ps1, launched with
-ModsFrom; note that -NoMods drops the probe jar too, which made the first
attempt a run with no instrumentation):

    register x22 vanilla listeners
    size before updateListenersFrom: 22
    size after  updateListenersFrom: 26      <- 22 + 2 NeoForge lambdas + ObjLoader + AnimationLoader
    reload 1: 26 listeners

With OptiFine installed the same three lines read 22, 22 and 48, and the reload
runs 50 listeners. Same input list, same code path, different result - so the
duplication is caused by OptiFine being present, not by NeoForge's ordinary
sorting, and the earlier question of who registers twice is answered: nobody
does. What differs is how the sort treats the listeners, and ReloadListenerSort
.sortListeners shows where that decision is made: for every entry in the registry
it calls needsToBeLinkedToVanilla(nameLookup, graph, listener) and adds an edge
when the answer is yes, then topologically sorts the graph. A listener whose name
cannot be resolved is therefore linked into the graph a second time, which is how
one object ends up in the reload twice.

The next measurement is that name resolution: ReloadProbe now prints
vanillaName=<resolved> per listener by calling VanillaClientListeners
.getNameForClass reflectively, and reports the failure reason instead of a bare
"?" - on the control run the call itself fails, so the first thing to fix is the
probe's own reflective lookup (wrong method signature or visibility), and then
compare the resolved names between the two runs.
## 2026-09-15: the name lookup resolves fine, so that is not the cause either

ReloadProbe now prints the vanilla name NeoForge's sort resolves for every
listener (asked reflectively through the listener's own class loader, because the
loader jar cannot see NeoForge's classes). Both runs, same probe:

    control, no OptiFine:  0 ClientNeoForgeMod$$Lambda vanillaName=null
                           1 BrandingControl$$Lambda  vanillaName=null
                           2 LanguageManager          vanillaName=minecraft:language_manager
                           ...  22 before updateListenersFrom, 26 after, reload 26
                           (4 nulls: the two NeoForge lambdas + ObjLoader + AnimationLoader)

    with OptiFine:         0 LanguageManager          vanillaName=minecraft:language_manager
                           1 TextureManager           vanillaName=minecraft:texture_manager
                           ...  22 before updateListenersFrom, 48 after, reload 50
                           (6 nulls)

So every vanilla listener resolves to the same name in both runs, and the
hypothesis that needsToBeLinkedToVanilla misjudges them because their name does
not resolve is wrong. The input to the sort is the same 22 entries, the lookups
agree, and yet the sorted result carries the vanilla block twice. A topological
sort over 24-26 nodes cannot produce 48 entries unless the graph it is handed is
shaped differently, and the graph is built in SortedReloadListenerEvent from the
passed list plus the dependencies added for it, with sortListeners linking each
registry entry to getLastVanillaListener() when needsToBeLinkedToVanilla says so.

So the next measurement is that anchor rather than the names: log what
getLastVanillaListener() returns (and the registry size) in both runs. With
OptiFine that answer is the one thing that can still differ, since everything else
in the path has now been shown to agree.
## 2026-09-15: identical inputs, different output - the difference is inside the sort

SortProbeFix logs what ReloadListenerSort.sort is handed, and the two runs agree
on every input:

    control, no OptiFine:  size before 22, registry 26, lastVanillaListener PeriodicNotificationManager, after 26
    with OptiFine:         size before 22, registry 26, lastVanillaListener PeriodicNotificationManager, after 48

Same registry (26 entries), same anchor class, same 22-entry list going in, and
the sorted result is 26 in one run and 48 in the other. A topological sort over
the same 26-node registry cannot return 48 entries, so the difference is not in
what the sort is given but in how it treats it: sortListeners asks
needsToBeLinkedToVanilla(nameLookup, graph, listener) per registry entry and edges
the graph when the answer is yes, and since the name lookups were already shown to
agree between the runs, the remaining variable is the graph - the dependencies
registered before the sort ran.

So the next probe belongs on the graph rather than on the listeners: node count,
edge count, and needsToBeLinkedToVanilla's answer per entry. Printing the anchor's
class was one step short of printing its identity, which should be included too -
the class matched, and only identity rules out a different instance of it.
## 2026-09-15: 26 nodes cannot sort into 48 entries

Both runs now have their sort inputs measured:

    control:   before 22, registry 26, anchor PeriodicNotificationManager, graph nodes=26 edges=23, after 26
    OptiFine:  before 22, registry 26, anchor PeriodicNotificationManager, (graph line missing), after 48

The control's graph has 26 nodes - exactly the registry - and sorts into 26
entries. With OptiFine the registry is the same 26 and the result is 48, so the
extra 22 entries are not extra nodes: a topological sort over 26 nodes cannot
return 48 of them unless the traversal appends a node more than once, which is
what a recursive implementation does when the graph contains a cycle.

So the next measurement is the shape that only OptiFine produces: whether
sortListeners' linking step creates a cycle (it asks
needsToBeLinkedToVanilla per registry entry and edges the entry to the anchor when
the answer is yes), and whether FML's TopologicalSort reports or silently walks
one. The graph line itself did not appear in the OptiFine run, so the probe needs
one more look there too - it printed on the control, so the insertion is sound and
something about that run skipped it.
## 2026-09-15: FML's sort is Kahn's algorithm, so it cannot emit 48 from 26

TopologicalSort.topologicalSort (loader-6.0.18.jar) computes in-degrees, queues
every node whose in-degree is zero, then repeatedly removes one from the queue,
adds it to the result, decrements its successors' in-degrees and enqueues each
successor that reaches zero - a Map.remove immediately after the Queue.add keeps a
node from being enqueued twice. Every node therefore lands in the result at most
once, and a cycle cannot be walked silently: the method declares
IllegalArgumentException and has a throwCyclePresentException helper for it.

So the previous round's reading is wrong as well: the 48 entries cannot be the
sort emitting nodes more than once. Either the sort is handed a graph with more
than 26 nodes, or the 48 entries are not the sort's return value at all. The next
probe separates those two by logging the size of the list sort() itself returns,
inside sort(), and by getting the graph numbers out of the OptiFine run - that
line printed on the control and not there, which is itself unexplained and needs
to be fixed before its absence is read as anything.
## 2026-09-15: with OptiFine, two probes inside sort() print nothing while the others do

SortProbeFix now inserts three calls at the head of ReloadListenerSort.sort - the
anchor, the registry size, the graph shape - and one before its return, the size of
the list it hands back. On the control run all four print. On the OptiFine run only
the first two do:

    OptiFine run:  value lastVanillaListener: PeriodicNotificationManager
                   count registry: 26
                   (no graph line, no sort-result line)
                   size before updateListenersFrom: 22
                   size after updateListenersFrom: 48

The graph probe logs on both its success and its failure path, and the sort-result
probe cannot throw on a list, so neither can be silent by accident: with OptiFine
present those two insertions are not running at all, while two insertions in the
same instruction list, in the same method, do run. That is now the most informative
thing in the picture and it has to be explained before anything else is inferred
from it - including whether the 48-entry result really comes from this method.

A plausible direction, not yet checked: the class may be loaded and transformed
twice in that run (once early, before all of this service's transformers are in
place, once later), leaving the running copy with only part of the insertions. That
is testable cheaply by logging which insertions each transformed copy received
(the transform method already logs the class it saw), instead of guessing from the
absence of output.
## 2026-09-15: 22 + 26 = 48 - the running updateListenersFrom appends

The four probes inside ReloadListenerSort.sort all print once the combined jar is
rebuilt (they had been missing because that jar predated them - worth remembering
before reading silence as evidence):

    with OptiFine:  graph before sorting: nodes=26, edges=23
                    list sort result: 26 of UnmodifiableRandomAccessList
                    size after updateListenersFrom: 48

The graph is identical to the control's and the sort returns the same 26 entries.
The difference appears inside updateListenersFrom itself: the list goes from 22 to
48, which is 22 + 26 - the sorted result appended to the list that was already
there, rather than replacing it. The donor body this project plans for that method
is NeoForge's own, "this.listeners = sort(event)", which replaces.

The likely reason the donor body is not the one running: MemberRestoreTransformer
copies a donor member only when the class does not already have it, and OptiFine's
replacement of ReloadableResourceManager appears to bring its own
updateListenersFrom - so the copy is skipped and the appending variant survives.

That closes the chain measured over the last rounds: an appended list holds every
vanilla listener twice, the same sprites are then loaded and cleared in two
interleaved passes, and TextureAtlas.clearTextureData closes the sprites the same
upload call is about to upload, which is the "Image is not allocated" failure.

The fix to make next: restore a planned member by replacing an existing body of the
same name and descriptor, not only adding it when absent.
## 2026-09-15: two fixes, and the first clean resource reload

Fix 1 - ReloadableResourceManagerFix wrote updateListenersFrom with addAll, which
appended the sorted list to the listeners NeoForge had already registered (22 -> 48)
instead of replacing them. Clearing first measures 22 before, 26 returned by the
sort, 26 after, and the reload runs 28 listeners instead of 50. The reason the
method could not simply assign the field is real and stays: OptiFine's copy declares
it final, so the contents are replaced rather than the field assigned.

Fix 2 - the Forge shell for net.minecraftforge.client.RenderTypeGroup exposed EMPTY
as null, and OptiFine calls isEmpty() on it. ForgeApiShims now gives every static
field whose type is its own class an instance in <clinit> (that is what such a
constant is), and a shell that is asked isEmpty() records which constructor produced
it: the no-argument one means it stands for the empty constant, any other means a
group OptiFine built, so isEmpty() answers correctly instead of guessing.

Measured on the 1.21.4 rig after both fixes: reload 1 runs 28 listeners, the
1024x1024 block atlas is created, Sound engine started, and the log holds no
"Caught error loading resourcepacks", no NullPointerException, no "Failed to wait"
and no "Image is not allocated" - the atlas failure that started this whole line of
investigation is gone.

## 2026-09-15:1.20.4 鍩虹嚎鑳藉惎鍔?浠ュ強涓や晶鍛藉悕绌洪棿鐨勫瓧鑺傜爜绾у疄娴?

瀹夎鍣ㄨ繖娆＄ 1 杞氨瀹屾垚,涓変釜娲剧敓 jar(`-srg` / `-slim` / `-extra`)涓?`neoforge-20.4.251-client.jar`
閮藉凡鐢熸垚銆傚熀绾垮惎鍔?

    launch-neoforge.ps1 -Profile neoforge-20.4.251 -NoMods -JavaExe "<jdk-17>\bin\java.exe"
    鈫?VERDICT: STARTED (40s, marker: Sound engine started)
      ModLauncher 10.0.9+10.0.9+main.dcd20f30 / Java 17.0.15
      NeoForge mod loading, version 20.4.251, for MC 1.20.4 with MCP 20240627.114801

涔熷氨鏄 1.20.4 杩欎竴琛岀殑**瀹炰緥鏈韩娌℃湁闂**,鍚庨潰鍐嶅嚭闂閮芥槸 mod 鐨勯棶棰樸€?

鍛藉悕绌洪棿杩欎竴娆￠噺鐨勬槸甯搁噺姹?涓嶆槸鐩綍鍚嶃€傚 `srg/net/optifine/Config.class` 鍙?`javap -v -p` 杈撳嚭,
鎸夊父閲忔睜鏉＄洰绫诲瀷缁?`m_\d{4,6}_` / `f_\d{4,6}_` 鍒嗙被:

| 鏋勫缓 | `Methodref`/`Fieldref` | `String` |
|---|---|---|
| 1.20.1 I6 | 49 | 0 |
| 1.20.4 I7 | 47 | 0 |
| 1.21.4 J3 | 0 | 0 |

甯?SRG 鍚嶇殑鏄?*寮曠敤**涓嶆槸瀛楃涓?鎵€浠?1.20.4 鐨勭被纭疄鏄収 SRG 鍛藉悕鐨?Minecraft 缂栬瘧鐨?
涓嶆槸"鑷甫涓€寮犲鐓ц〃"銆傚闈?NeoForge 20.4 鐨勮繍琛屾椂鍙?

    javap -p client-1.20.4-20240627.114801-srg.jar 閲岀殑 net.minecraft.world.item.Item
    鈫?getId / byId / builtInRegistryHolder / onUseTick / BY_BLOCK / MAX_STACK_SIZE

**鍏ㄦ槸瀹樻柟鍚?涓€涓?`m_` 閮芥病鏈?*;鏂囦欢鍚嶉噷鐨?`srg` 鍙槸瀹夎鍣ㄩ偅涓€姝ョ暀涓嬬殑鍚嶅瓧,涓嶄唬琛ㄥ唴瀹广€?

椤哄甫鍚﹁瘉浜嗕竴鏉℃嵎寰?鎯崇敤 `patch/srg/*.class.md5` 鍙嶆帹"琛ヤ竵鏄拡瀵瑰摢涓?jar 鎵撶殑",427 涓?md5 瀵?
NeoForm 瀹樻柟 jar銆乿anilla 娣锋穯 jar銆乻lim銆乪xtra銆乣neoforge-*-client.jar`銆乣neoforge-*-universal.jar`
鍏ㄩ儴 0 鍛戒腑,`patch/notch` 瀵?vanilla 娣锋穯 jar 涔熸槸 0/426 鈥斺€?**杩欎簺 md5 涓嶆槸杈撳叆鐨勬牎楠屽拰**,涓嶈兘鐢ㄥ畠鍒ゅ畾鍛藉悕绌洪棿銆?

NeoForm 1.20.4 鑷甫鐨勪袱浠芥槧灏勪篃鏌ヤ簡:`-mappings.txt` 鏄?`tsrg2 obf srg id` 浣?srg 鍒椾笌 obf 鍒?*閫愬瓧鐩稿悓**
(`cuz cuz 1657`)涓斿叏鏂囨棤 `m_` 鍚?`-mappings-merged.txt` 鏄?`tsrg2 left right` = obf鈫掑畼鏂瑰悕銆?
鎵€浠ラ噸鏄犲皠瑕佺敤鐨?SRG鈫攐fficial 琛?*涓嶅湪 NeoForm 閲?*,鏉ユ簮闂瑙?`docs/MATRIX.md` 鏈妭銆?

## 2026-09-15:SRG鈫抩fficial 琛ㄥ缓鎴愬苟鍏ㄩ噺楠岃瘉閫氳繃(3844/3844)

鏂板绂荤嚎宸ュ叿 `SrgMemberMap`,杈撳叆 MCPConfig 鐨?`config/joined.tsrg`(`tsrg2 obf srg id`)涓?NeoForm 鐨?
`-mappings-merged.txt`(`tsrg2 obf official`),浠?*娣锋穯鍚?*涓鸿繛鎺ラ敭鍚堟垚 SRG鈫抩fficial 鎴愬憳琛?

    table: 5742 owners with fields, 7428 with methods (35232 + 65393 names);
           0 classes and 4 members had no counterpart

杩炴帴鐜囨槸 0 缂哄彛,璇存槑涓や唤鏄犲皠瑕嗙洊鍚屼竴鎵规贩娣嗗悕銆傜劧鍚庣敤瀹冨幓鏀瑰啓 OptiFine 1.20.4 **鍏ㄩ儴**绫婚噷鐨勬瘡涓€涓?
SRG 寮曠敤,鍐嶉€愪釜闂?NeoForge 20.4 杩愯鏃舵槸鍚︾湡鏈夎繖涓垚鍛?

    runtime index: 69571 methods, 35583 fields, 7794 classes with a superclass
    SRG references: 3844
    resolved:       3844 (100.00%)  = 3248 on the referenced class + 596 through a superclass
    unresolved:     0
    wrote 1330 rewrite pairs to build-tools/srg1204.tsv (86412 bytes)

**100% 鍛戒腑,鑰屼笖鍙渶瑕?1330 鏉℃敼鍐欏 / 86 KB** 鈥斺€?瀹屾暣琛ㄦ湁 10 涓囦釜鍚嶅瓧,鐪熸琚紩鐢ㄧ殑鍙湁鍗冧綑鏉?
鎵€浠ヨ繖寮犺〃鍙互闅?jar 鍙戝嚭鍘?涓嶅繀鍦ㄧ敤鎴锋満鍣ㄤ笂鐢熸垚銆?

杩囩▼涓俯鍒颁袱涓潙,閮借涓嬫潵,鍥犱负瀹冧滑閮芥槸"鐪嬭捣鏉ュ"鐨勯敊璇?

1. **杩炴帴閿繀椤诲甫鎻忚堪绗︺€?* 娣锋穯鏂规硶鍚嶅彧鍦?鍚嶅瓧 + 鎻忚堪绗?涓婂敮涓€,涓€涓被閲屼袱涓噸杞介兘鍙?`a` 鏃?
   鍙寜鍚嶅瓧杩炴帴灏变細浜掔浉瑕嗙洊銆傝〃鐜版槸 `m_118316_`(瀹為檯鏄?`getSprite`)琚В鏋愭垚浜?`dumpContents`,
   浜庢槸 841 鏉″紩鐢ㄦ姤"瀹夸富绫诲湪銆佹垚鍛樹笉鍦?銆備袱浠芥枃浠剁殑鎻忚堪绗﹂兘鍦ㄦ贩娣嗗懡鍚嶇┖闂?`(Lahg;)Lgen;`),
   鍙互鐩存帴姣旇緝銆?
2. **寮曠敤鐨?owner 涓嶄竴瀹氭槸澹版槑绫汇€?* `invokevirtual BlockState.m_60734_` 寮曠敤涓€涓０鏄庡湪鐖剁被閲岀殑鎴愬憳
   鏄悎娉曠殑,鑰岃〃鏄寜澹版槑绫诲缓鐨?鎵€浠ユ煡涓嶅埌鏃惰娌跨户鎵块摼涓婃函銆備慨濂藉悗姝ｅソ琛ヤ笂閭?596 鏉?
   (鍏堝墠鍏ㄩ儴璁″叆"琛ㄩ噷娌℃湁杩欎釜鏉＄洰")銆?

楠岃瘉鍣ㄩ噷鐨勭户鎵块摼涓婃函鍙槸**娴嬮噺**鐢ㄧ殑;鐪熸鍙戠粰 loader 鐨勮〃鎸?*寮曠敤閲屽啓鐨?owner** 寤洪敭
(鏀瑰啓鍙敼鎴愬憳鍚?涓嶅姩 owner),杩欐牱 loader 渚т笉闇€瑕佺被灞傛銆佷篃灏变笉浼氭彁鍓嶅姞杞戒换浣曠被銆?

compile/run 鏂瑰紡(ASM 9.8 鍙栬嚜 Gradle 缂撳瓨,鐢?jdk-21):

    javac -cp asm-9.8.jar;asm-tree-9.8.jar -d build-tools/srgmap src/main/java/.../SrgMemberMap.java
    java -cp "build-tools/srgmap;<asm>" kynarain.cn.optifineoforge.optifine.SrgMemberMap \
      --emit build-tools/srg1204.tsv <mcp1204-joined.tsrg> <...-mappings-merged.txt> \
      OptiFine_1.20.4_HD_U_I7.jar client-1.20.4-...-srg.jar neoforge-20.4.251-client.jar

**涓嬩竴姝?*:鎶?emit 鎺ヨ繘 `OptifinePipeline`(鎵撹ˉ涓佷箣鍚庤窇,鍥犱负琛ヤ竵浜х墿 `srg/net/minecraft/**` 鑷繁涔熷甫
SRG 寮曠敤),鍐嶅湪 loader 鐨?transformer 閲岀敤 ASM `Remapper` 鎸夎繖寮犺〃鏀瑰啓鎴愬憳鍚嶃€?.20.2 鐢?
`mcp_config-1.20.2.zip` 鍚屾牱鐢熸垚涓€浠?宸茬‘璁よ鍧愭爣瀛樺湪,HTTP 200)銆?

## 2026-09-15:鏀瑰啓鍣ㄨ窇瀹?1.20.4 杞借嵎閲岀殑 SRG 鍚嶅綊闆?

涓婇潰閭ｅ彞"鍦?loader 鐨?transformer 閲屾敼鍐?鏄?*閿欑殑鏂规,宸叉斁寮?*銆傝繖涓」鐩湰鏉ュ氨鍦ㄧ绾块樁娈甸噸鎵撳寘
OptiFine 鐨?jar銆佺绾胯窇 `optifine.Patcher`,闇€瑕佹敼鍚嶇殑绫诲湪娓告垙鍚姩鍓嶅氨鍏ㄩ兘鍦ㄦ墜涓?鑰?ModLauncher 鐨?
transformer 蹇呴』**棰勫厛澹版槑 targets**,闇€瑕佹敼鐨勭被鏈夊嚑鍗冧釜,澹版槑涓嶇幇瀹炪€傛墍浠ユ敼鍚嶅仛鎴?*鏋勫缓姝ラ**
(`SrgRemap`),loader 渚т竴琛岄兘涓嶇敤鏀?鑰屼笖瀵规瘡鏉＄嚎閮借兘鍚屾牱鍦拌窇(1.20.6 璧风殑绾挎壘涓嶅埌 SRG 鍚?鍘熸牱澶嶅埗)銆?

鐢?`ClassRemapper` 鑰屼笉鏄墜鏀瑰紩鐢?鍘熷洜鏄?*杞借嵎閲岀殑 SRG 鍚嶅悓鏃跺嚭鐜板湪澹版槑鍜屽紩鐢ㄤ袱澶?*:

    before: SRG references: 3844        SRG-named members the payload still declares: 108
    after : SRG references: 0           SRG-named members the payload still declares: 0
    rewrote 3457 method and 1400 field names, 0 could not be resolved
    0 SRG-shaped string constants were left alone

閭?108 涓０鏄庢鏄彧鏀瑰紩鐢ㄤ細婕忔帀鐨勪笢瑗?OptiFine 鏇挎崲鎺夌殑娓告垙绫?*鑷繁澹版槑**鎴愬憳,`net.optifine.BlockPosM`
閲屽氨鏈?`m_123341_()I` 瑕嗙洊 `Vec3i.getX()`銆傚彧鎶婅皟鐢ㄧ偣鏀规帀鑰岀暀鐫€ SRG 鐨勫０鏄?绛変簬鎶?瑕嗙洊"鎮勬倓鍙樻垚"鍙︿竴涓?
鏂规硶",缂栬瘧寰楅€氥€佽繍琛屼笉鎶ラ敊銆佽涓洪敊銆俙ClassRemapper` 瀵瑰０鏄庡拰寮曠敤璧板悓涓€涓柟娉?鎵€浠ヤ袱杈瑰繀鐒朵竴鑷淬€?

**韪╁埌鐨勫潙:鍚屼竴涓被鍚嶅湪 jar 閲屾湁涓や唤銆?* OptiFine 鐨?jar 缁欒嚜瀹舵瘡涓被閮藉瓨浜嗕袱浠?鈥斺€?`srg/**` 瀵圭潃閲嶅懡鍚?
杩囩殑娓告垙,`notch/**` 瀵圭潃娣锋穯杩囩殑娓告垙銆傜涓€鐗堝彧鎸夌被鍚嶅缓瓒呯被琛?浜庢槸涓や唤浜掔浉瑕嗙洊,`notch/` 閭ｄ唤璧簡:

    superOf(net/optifine/BlockPosM) = hx        鈫?娣锋穯鍚?缁ф壙閾惧埌姝ゆ柇鎺?
    hierarchy(net/optifine/BlockPosM) = [net/optifine/BlockPosM, hx]

缁撴灉鏄?371 涓悕瀛楁姤"琛ㄩ噷娌℃湁杩欎釜鏉＄洰",鑰岃〃閲屽叾瀹為兘鏈?鈥斺€?`BlockPosM extends net.minecraft.core.BlockPos`
杩欎竴璺虫牴鏈病璧板埌 `Vec3i`銆俙SrgRemap.isUnusedNamespace` 鐜板湪鏄惧紡璺宠繃 `notch/**`,杩欎竴鏉′篃鍐欒繘浜嗘敞閲?
鍥犱负"鍚屼竴浠芥暟鎹湪 jar 閲屾湁绗簩浠姐€佽€屼笖浼氭倓鎮勮耽"涓嶆槸鑳戒粠浠ｇ爜鐪嬪嚭鏉ョ殑浜嬨€?

**杩樺墿浠€涔?*:鎶?`SrgRemap` 鎺ヨ繘 rig 鐨勬瀯寤烘祦绋?鍦ㄤ涪鎺?`notch/` 涔嬪悗銆佸悎骞朵箣鍓嶈窇),鐢?1.20.4 鎵撳嚭
鍚堝苟 jar 骞跺疄鏈哄惎鍔ㄣ€傛敼鍚嶆湰韬凡缁忛獙璇佸畬姣?涓嶅啀鏄湭鐭ユ暟銆?

鍚屼竴澶╄ˉ鐨勪竴涓繚闄?鏀瑰啓鍣ㄧ幇鍦ㄤ細鍏堟暟**杩愯鏃?*閲岃繕鏈夊灏戞垚鍛樻槸 SRG 鍚?澶т簬 0 灏辨嫆缁濇敼鍐欏苟閫€鍑?2銆?
鐞嗙敱鏄繖鏉″懡浠よ繜鏃╀細琚『鎵嬬敤鍦?1.20.1 閭ｄ竴琛屼笂,鑰岄偅涓€琛屼袱杈规湰鏉ュ氨閮芥槸 SRG 鍚?鏀瑰啓浼氭妸姣忎釜鎴愬憳鏀规垚
閭ｄ竴琛岀殑娓告垙鏍规湰娌℃湁鐨勫悕瀛?鈥斺€?鎶ラ敊浼氬嚭鐜板湪寰堜箙涔嬪悗,鍜屽師鍥犲畬鍏ㄤ笉鍍忋€傚疄娴?

    杈撳叆 OptiFine_1.20.1_HD_U_I6.jar + 1.20.1 杩愯鏃?client-1.20.1-...-srg.jar + forge-1.20.1-47.1.106-client.jar)
    鈫?refusing to rewrite: this runtime is SRG-named (49667 members match m_/f_)
    鈫?閫€鍑虹爜 2,娌℃湁浜х敓杈撳嚭 jar

杩欎釜鏁板瓧椤轰究浠庡彟涓€涓柟鍚戝嵃璇佷簡鏁存潯缁撹:1.20.1 杩愯鏃堕噷鏈?**49667** 涓?SRG 鍚嶆垚鍛?1.20.4 杩愯鏃堕噷鏄?
**0** 涓€傛墍璋?1.20.2/1.20.4 杩欎竴甯︿袱杈逛笉鍚屾瀯",鍦ㄤ袱涓柟鍚戜笂閮芥槸閲忓嚭鏉ョ殑銆?

## 2026-09-15:鎵撹ˉ涓佽鐨勬槸**娣锋穯**jar,涓嶆槸 SRG jar(淇涓婁竴鏉℃帹鏂?

鎶?1.20.4 鎺ヨ繘 rig 鏃剁涓€姝ュ氨鍋滀綇:

    OptiFine's patcher failed on client-1.20.4-20240627.114801-srg.jar
    Caused by: java.io.IOException: Base resource not found: eon.class
        at optifine.Patcher.applyPatch(Patcher.java:148)

`eon` 鏄?*娣锋穯鍚?*(`eon` = `com/mojang/blaze3d/pipeline/RenderTarget`),鑰岀粰杩涘幓鐨勬槸瀹樻柟鍚嶇殑 NeoForm jar,
鎵€浠ュ畠鎵句笉鍒?base銆備箣鍓嶉偅鍙?琛ヤ竵闇€瑕佽鐗堟湰鐨?SRG 鍛藉悕 jar"鏄?*閿欑殑**:OptiFine 鐨?`Patcher.applyPatch`
鐢?`getPatchBase(...)` 鎶婅ˉ涓佹潯鐩悕鎹㈢畻鎴?*娣锋穯 base 鍚?*,鍐嶅湪杈撳叆 jar 閲屾壘 鈥斺€?瀹冭鐨勬槸**鍘熺増娣锋穯 jar**,
涔熷氨鏄惎鍔ㄥ櫒 `versions/1.20.4/1.20.4.jar` 閭ｄ竴浠?OptiFine 鑷繁鐨勫畨瑁呭櫒灏辨槸寰€鐗堟湰 jar 閲屾墦琛ヤ竵)銆?

鎶婅緭鍏ユ崲鎴愬師鐗堟贩娣?jar 涔嬪悗,鍚屼竴涓ˉ涓佹楠ょ珛鍒婚€氳繃:

    patched in 1271 ms -> optifine-patched.jar (6,724,767 bytes)
    roots: {=9, META-INF/=3, assets/=1787, doc/=39, notch/=1137, optifine/=49, srg/=1070}
    patched game classes: 427 (369 net/minecraft)

椤哄甫璇存槑 1.21.4 閭ｄ竴琛屼箣鍓嶄负浠€涔堟病鏆撮湶杩欎釜闂:`test-downloads/1.21.4-client.jar` 鍏跺疄鏄?*鍘熺増娣锋穯 jar**
(5723 涓袱瀛楁瘝鏍圭被鍚?`net/minecraft/*` 鍙湁 29 鏉?,涓€鐩村氨鏄鐨勮緭鍏ャ€?*涓よ竟涓€鑷?涓嶆槸鐗逛緥銆?*

## 2026-09-15:琛ヤ竵浜х墿涔熸槸 SRG 鍚?鎵€浠ユ敼鍚嶈璺戝湪琛ヤ竵涔嬪悗

`optifine-patched.jar` 閲?`srg/com/mojang/blaze3d/pipeline/RenderTarget.class` 鐨勬垚鍛樻槸
`f_166194_`銆乣f_83915_`銆乣f_83919_` 鈥斺€?**琛ヤ竵浜х墿(427 涓父鎴忕被)鏄?SRG 鍚?*,鍜?OptiFine 鑷绫讳竴鏍枫€?
鎵€浠?`SrgRemap` 蹇呴』浣滅敤鍦?*琛ヤ竵浜х墿**涓?鑰屼笖杩欐槸涓绘垬鍦?

    rewrote 21089 method and 17455 field names, 107 could not be resolved
    0 SRG-shaped string constants were left alone

瀵规瘮鍙敼 OptiFine 鑷绫绘椂鐨?3457 + 1400,琛ヤ竵绫昏础鐚簡 **85% 鐨勬敼鍚嶉噺**銆傝繖涔熸槸涓『搴忕害鏉?
`MemberRestorePlan` 鏄嬁琛ヤ竵浜х墿鍜?NeoForge 瀹樻柟鍚嶈繍琛屾椂姣旂殑,鎵€浠?*鏀瑰悕蹇呴』鎺掑湪璁″垝涔嬪墠**,鍚﹀垯鏁翠釜璁″垝
閮芥槸鎷?SRG 鍚嶅幓姣斿畼鏂瑰悕,缁撹娌℃湁鎰忎箟銆?

鍓╀笅 107 涓?38544 閲岀殑 0.28%)宸茬粡鏌ュ埌鍏蜂綋鍘熷洜,涓嶆槸琛ㄧ殑闂:

    candidate 椤哄簭 net/minecraft/core/BlockPos$MutableBlockPos -> below
                  net/minecraft/core/BlockPos                 -> below
                  net/minecraft/core/Vec3i                    -> below

鍚屼竴涓?SRG 鍚嶄細琚?*鍗忓彉瑕嗗啓**鐨勫涓０鏄庡叡鐢?`Vec3i`/`BlockPos`/`MutableBlockPos` 鐨?`below()` 閮藉彨
`m_7495_`,鍙槸 id 涓嶅悓),鎵€浠?鍚嶅瓧鍦ㄨ〃閲屻€佷絾璇ョ被骞朵笉澹版槑瀹?涓嶆槸缁堢偣,蹇呴』缁х画寰€涓婅蛋涓€灞傘€備慨鎺変箣鍚?
閭?49 鏉?鎴愬憳褰㈢姸鍙樹簡"閲岃繕鍓?48 鏉″睘浜庡彟涓€绫?瑙佷笅銆?

**鍙︿竴绫?瀛楁鏃?鏄被鍚嶄笉涓€鑷?*:Mojang 鐨勬槧灏勬妸灞€閮?record 鍙?`net/minecraft/client/gui/Gui$1DisplayEntry`
(`$1` 鍓嶇紑),鑰?OptiFine 鎵撳嚭鏉ョ殑琛ヤ竵绫绘槸 `Gui$DisplayEntry`銆傚杩愯鏃堕獙涓€涓?

    Gui$1DisplayEntry.class            鍦ㄨ繍琛屾椂: 鏈?
    Gui$DisplayEntry.class             鍦ㄨ繍琛屾椂: 鏃?
    MultiBufferSource$BufferSource     鍦ㄨ繍琛屾椂: 鏈?
    MultiBufferSource$1BufferSource    鍦ㄨ繍琛屾椂: 鏃?

涓よ竟鍚勬湁鍚勭殑鍙硶,涓嶆槸鍗曞悜鍋忓樊銆傝妯′篃涓嶅ぇ:369 涓ˉ涓佽繃鐨?`net/minecraft` 绫婚噷,鍙湁 **5 涓?*鐨勫悕瀛?
杩愯鏃舵病鏈?鈥斺€?`Util$3`銆乣Util$4`銆乣Gui$DisplayEntry`銆乣ParticleEngine$ParticleDefinition`銆?
`LevelChunkSection$BlockCounter`銆?

**涓嬩竴姝?*:鏋朵竴搴х被鍚嶆ˉ 鈥斺€?琛ヤ竵鏉＄洰鍚?`patch/srg/<OptiFine 鐨勫悕瀛?.class.xdelta`)涓庡畠鎹㈢畻鍑虹殑娣锋穯 base
鍚嶆槸涓€瀵?鎶婂畠鍜?merged 鐨勬贩娣嗏啋瀹樻柟鍚嶄覆璧锋潵,灏卞緱鍒?OptiFine 鍚?鈫?杩愯鏃跺悕";鐢ㄥ畠淇帀閭?5 涓被銆佸苟璁?
琛ㄦ寜杩愯鏃剁殑绫诲悕鏌ャ€傜劧鍚庡氨鑳芥帴鐫€璺?`MemberRestorePlan`銆丗orge shims銆乴oader 骞跺疄鏈哄惎鍔ㄣ€?

rig 閭ｈ竟鍚屾椂淇簡涓€澶?`build-rig-jar.ps1` 鍘熸潵鎵嬪啓鍥涗釜 ASM jar(9.8),娌℃湁 `asm-commons`,浜庢槸
`ClassRemapper`/`Remapper` 鐩存帴缂栬瘧涓嶈繃;鐜板湪鎸夋ā鍧楀悕瑙ｆ瀽,鑷姩甯︿笂 `asm-commons`,鐗堟湰涔熻窡
`gradle.properties` 鐨?`asm_version=9.10.1` 涓€鑷淬€?

## 2026-09-15:1.20.4 绗竴娆″疄鏈哄惎鍔?鈥斺€?璧锋潵浜?鑰屼笖鏄?OptiFine 鍦ㄥ満"璧锋潵鐨?

rig 鐜板湪鑳藉湪 1.20.4 涓婅窇瀹屾暣鏉℃瀯寤洪摼(鏂板绗?3b 姝?鎵撳畬琛ヤ竵銆佸仛璁″垝**涔嬪墠**鍏堣窇 `SrgRemap`),浜у嚭

    mods-stage-1204/optifiNeoforge-combined.jar  5,868,899 bytes, 5940 entries, 24 donors, 48 shims

鏋勫缓鍚勬鐨勮緭鍑?

    patched in 1223 ms -> optifine-patched.jar (6,724,767 bytes)
    patched game classes: 427 (369 net/minecraft)
    rewrote 21471 method and 17455 field names, 107 could not be resolved
    compared 235 replaced classes (835 without a runtime counterpart), 52 members to restore
    48 referenced Forge types in 16 packages, 44 members named on them  -> 48 stubs

`48 referenced Forge types` 璇存槑杩欎竴琛?*闇€瑕?* Forge shims(涓?1.20.1 鐩稿弽 鈥斺€?閭ｄ竴琛岀殑 NeoForge 鑷甫
Forge API,鎵€浠?`-ForgeStubs $false`;20.4 鐨勮繍琛屾椂閲?`net/minecraftforge` 鏄?0 涓?銆?

鍚姩缁撴灉:

    VERDICT: STARTED (40s, marker: Sound engine started)
      OptiFineTransformationService.onLoad / OptiFine ZIP file: mods/optifiNeoforge-combined.jar
      OptiFineTransformer: Targets: 427
      OptifiNeoforgeTransformationService.onLoad, alongside [mixin, OptiFine, mixin-synthetic-package, fml, OptifiNeoforge]
      Member restore plan: 52 members across 24 classes
      Initialised 1 restored fields in net/minecraft/client/multiplayer/ClientLevel
      Restored 1 members in net/minecraft/client/multiplayer/ClientLevel from its donor
      NeoForge mod loading, version 20.4.251, for MC 1.20.4

**浣?stderr 鏈?12575 琛屽紓甯?鑰屼笖鍘熷洜姝ｆ槸杩欎竴琛岄瑷€杩囩殑閭ｄ釜**:

    java.io.IOException: Base resource not found: fcn.class
        at optifine.Patcher.applyPatch(Patcher.java:148)
        at optifine.OptiFineTransformer.getOptiFineResourcePatched(OptiFineTransformer.java:441)
        at optifine.OptiFineTransformer.transform(OptiFineTransformer.java:195)
    (fcn銆乬dy銆乫cn$a銆乬eg銆乪qf銆乪wu ... 涓€涓被涓€鏉?鐩村埌 12575 琛?

`fcn` 鏄?*娣锋穯鍚?*銆備篃灏辨槸璇?OptiFine 鐨?*杩愯鏃?* transformer 涔熷湪鎸?娣锋穯 base 鍚?鎵捐祫婧?鑰?NeoForge
鐨勮繍琛屾椂缁欏畠鐨勭被鏄?*瀹樻柟鍚?*鐨?鈥斺€?鍜岀绾挎墦琛ヤ竵鏃堕偅涓?`Base resource not found: eon.class` 鏄悓涓€涓師鍥?
鍙槸杩欐鍙戠敓鍦ㄧ被鐨勫姞杞借矾寰勪笂銆傚紓甯歌 ModLauncher 閫愮被鍚炴帀,鍘熺被鐓х敤,鎵€浠ユ父鎴忚兘璧锋潵,浣?*OptiFine 鐨?
琛ヤ竵瀹為檯娌℃湁鐢熸晥**:瀹冨彧鏄鍔犺浇浜?娌℃湁琚粐杩涘幓銆?

杩欎竴姝ユ妸 1.20.2/1.20.4 鍓╀笅鐨勮璁￠棶棰橀棶娓呮浜?**涓嶈兘鍐嶆寚鏈?OptiFine 鐨勮繍琛屾椂 transformer 鍘绘墦琛ヤ竵**銆?
琛ヤ竵蹇呴』鐢辨垜浠湪绂荤嚎闃舵鎵撳畬銆佹敼鍚嶅畬,鐒跺悗鎶?*缁撴灉绫?*鏀捐繘 jar,璁?`OptiFineTransformer` 鐩存帴鍙栧埌鎴愬搧
(瀹冨彧鏈夊湪瀛樺湪 `patch/srg` 鏉＄洰鏃舵墠浼氬幓 `getOptiFineResourcePatched`,鎵€浠ユ帴涓嬫潵瑕侀獙鐨勬槸:鎶?
`patch/srg/**` 浠庡彂甯?jar 閲屽幓鎺夈€佹妸 `srg/<official path>` 鎹㈡垚鎴戜滑鏀瑰ソ鍚嶇殑鎴愬搧绫?瀹冩槸鍚︿細鐩存帴杩斿洖鎴愬搧)銆?
杩欎篃姝ｆ槸鍓嶉潰鍑犺疆鎶婄绾挎敼鍚嶅仛鎵庡疄鐨勭敤澶?鈥斺€?閭ｆ潯璺幇鍦ㄦ垚浜嗗敮涓€鍙蛋鐨勮矾銆?

鍙﹀璁颁袱澶?rig 鐨勫潙,閮芥浘鎶?宸ュ叿鍐?stderr"鍙樻垚"鐪嬭捣鏉ュ儚缂栬瘧澶辫触":
`javac` 鐨?deprecation note 鍜?`git worktree add` 鐨勮繘搴﹁緭鍑洪兘浼氳 `$ErrorActionPreference='Stop'` 鐩存帴
涓鏋勫缓;鐜板湪 rig 鐢?`Continue` + 鏄惧紡妫€鏌?`$LASTEXITCODE`,骞朵笖 `SrgRemap` 鏀圭敤 `Remapper(Opcodes.ASM9)`
鏋勯€犲櫒(鏃犲弬鏋勯€犲湪 ASM 9.10 閲屽凡寮冪敤)銆俙-Out` 鐨勭埗鐩綍鐜板湪涔熶細鑷姩鍒涘缓 鈥斺€?缂虹洰褰曚互鍓嶈〃鐜颁负 zip 鍐欏叆鍣?
娣卞鐨?NullReferenceException,鑰屼笉鏄?鐩綍涓嶅瓨鍦?銆?

## 2026-09-15:鎶婃垚鍝佺被瑁呰繘 jar 鈥斺€?寮傚父椋庢毚娑堝け,浣嗘挒涓婃ā鍧楀寘鍐茬獊

鎸変笂涓€鑺傜殑缁撹鍋?鏋勫缓鍑?鎴愬搧杞借嵎" = 鎵撳畬琛ヤ竵骞舵敼濂藉悕鐨勭被(`srg/` 1070 涓?鍚?427 涓父鎴忕被),鍘绘帀
`notch/**`,浣滀负缁勫悎 jar 鐨勫簳銆傝繖涓€姝?*纭疄瑙ｅ喅浜嗚繍琛屾椂鎵撹ˉ涓佺殑闂**:

    鏀瑰悕鍓?绗?68 杞偅绉嶈娉?: stderr 12575 琛?Base resource not found 閫愮被鍒峰睆
    鎴愬搧杞借嵎瑁呮硶:              stderr 8 琛?  Base resource not found = 0

涔熷氨鏄:OptiFine 鐨勮繍琛屾椂 transformer 涓嶅啀鍘绘墦閭ｄ簺鎵撲笉涓婄殑琛ヤ竵,娓告垙閲岀敤鐨勫氨鏄垜鏂规敼濂藉悕鐨勭被銆?

浣嗗惎鍔ㄦ崲鎴愪簡鍙︿竴涓け璐?鑰屼笖鍒嗕袱姝ユ墠鐪嬫竻:

1. `NoSuchFileException: ...optifiNeoforge-combined.jar#177` 鈫?`Invalid Services found OptiFine`銆?
   璇诲嚭 `OptiFineTransformationService.onLoad` 鐨勫瓧鑺傜爜,绗?103鈥?10 琛屾槸:
   `ofZipFileUrl = codeSource.getLocation()` 鈫?鏃ュ織鎵撳嵃 URL 鈫?`ofZipFileUrl.getPath()` 鈫?鍘绘帀 `file:` 鈫?
   `toFile(URI)` 鈫?`new File(...)` 鈫?**绗?109 琛?`new ZipFile(path)`**銆俇RL 鏄?
   `union:/.../jar%23177!/`,URI 瑙ｇ爜鍚庡彉鎴?`...jar#177`,ZipFile 鑷劧鎵撲笉寮€銆?
   杩欐鏄?`OptifineJarFixer` 瀛樺湪鐨勫師鍥?瀹冩妸 `toFile` 鏀规垚鍏堝壀鎺?`%23N` 鍜屽悗缂€ `!/`);绗?68 杞殑搴曟槸
   **閲嶆墦鍖呰繃鐨?* jar,`OptifineJar` 宸茬粡鎶婇偅涓被淇ソ浜?鑰屾垜杩欐鐨勫簳鏉ヨ嚜**鎵撹繃琛ヤ竵鐨?* jar,閲岄潰閭ｄ唤
   `optifine/OptiFineTransformationService.class` 鏄?*鍘熺増**鐨勩€傛妸淇ソ鐨勯偅浠戒粠閲嶆墦鍖?jar 閲岃鐩栧洖鍘讳箣鍚?

       OptiFine ZIP file: C:\...\optifiNeoforge-combined.jar     鈫?onLoad 杩囦簡

2. 绱ф帴鐫€,绫诲姞杞藉眰璧蜂笉鏉?

       java.lang.module.ResolutionException: Modules srg and minecraft export package
       net.minecraft.client.renderer.block to module mixinsynthetic

   鎶?*娓告垙绫?*鏀捐繘 mod jar,鎴戞柟妯″潡灏卞绉板鍑?`net.minecraft.*` 杩欎簺鍖?鑰?`minecraft` 妯″潡宸茬粡瀵煎嚭浜?
   瀹冧滑,妯″潡瑙ｆ瀽鍣ㄧ洿鎺ユ嫆缁濄€傝繖鍜?1.20.1 閭ｆ `Module optifine contains package
   net.minecraftforge.eventbus.api` 鏄悓涓€绫婚棶棰?jar 鐨勬ā鍧椾笉鑳介噸澶嶅鍑烘父鎴忓寘銆?

**鎵€浠?鎶婃垚鍝佺被骞抽摵杩?jar"杩欐潯璺蛋涓嶉€?*,浣嗙 1 鏉″凡缁忚瘉鏄庡彟涓€浠朵簨:鍙绫昏兘琚姞杞?**琛ヤ竵涓嶅啀闇€瑕?
OptiFine 鐨勮繍琛屾椂 transformer**銆備笅涓€姝ユ嵁姝よ皟鏁?鎴愬搧绫绘敼鏀惧湪**涓嶄細涓庢父鎴忓寘鍐茬獊鐨勮矾寰?*涓?渚嬪
`optifineoforge/patched/net/minecraft/...`),鐢?*鎴戞柟 loader 鐨?transformer** 鍦ㄦ父鎴忕被鍔犺浇鏃舵妸鍐呭鎹㈡垚
鎴愬搧(OptiFine 鏇挎崲绫荤殑閭ｅ鏈哄埗鏈潵灏卞湪杩欎釜椤圭洰閲?,杩欐牱鏃㈤伩寮€妯″潡鍐茬獊,鍙堜笉鍐嶄緷璧栧畠鑷繁鐨?patch 璺緞銆?

椤哄甫涓€涓惁瀹氱粨鏋?璇曡繃 `-KeepPatchData` 鎯宠"淇濈暀 patch 鏁版嵁"鍜?涓嶄繚鐣?鍋氬姣?缁撴灉涓ゆ浜у嚭**瀛楄妭鏁板畬鍏?
鐩稿悓** 鈥斺€?鍥犱负 `Patcher.process` 鐨勮緭鍑?jar 閲?*鏍规湰娌℃湁 `patch/` 鐩綍**(瀹冪殑 roots 鍙湁
`assets, doc, notch, optifine, srg`)銆傝繖涓紑鍏抽噺涓嶅埌浠讳綍涓滆タ,宸插湪 rig 娉ㄩ噴閲屽啓鏄庛€?

## 2026-09-15:鎴愬搧绫绘敼鏀惧畨鍏ㄨ矾寰?+ 鎴戞柟 transformer 鎹㈣ 鈥斺€?琛ヤ竵缁堜簬鐪熺殑鐢熸晥浜?

鎸変笂涓€鑺傜殑缁撹鎹簡瑁呮硶:鎴愬搧绫讳笉鍐嶅钩閾哄湪 `srg/` 涓?鑰屾槸鏀惧埌 `optifineoforge/patched/`
(**427 涓?*),`srg/` 鍙暀 OptiFine 鑷繁鐨勭被(643 涓?,鍙﹀啓涓€浠?`optifineoforge/patched-index.txt`
(鍥犱负 ModLauncher 鐨?transformer 蹇呴』**鍏?*澹版槑 targets)銆傛柊澧?`PatchedClassTransformer`(鍦?`loader`
鍖呴噷,娉ㄥ唽鍦?*鏈€鍓?*,杩欐牱鍚庨潰鐨?`MemberRestoreTransformer` 鏄湪鎹㈣鍚庣殑绫讳笂琛?NeoForge 鐨勬垚鍛?:

    Patched-class targets: 427
    Replaced net.minecraft.client.gui.GuiGraphics with OptiFine's patched version (10 fields, 84 methods)
    Replaced net.minecraft.client.renderer.RenderType with OptiFine's patched version (68 fields, 114 methods)
    ... 瀹為檯鎹㈣ 112 涓被,stderr 0 琛?OptiFineTransformer: Targets: 0(瀹冭嚜宸变笉鍐嶉渶瑕佹墦琛ヤ竵)

**涓や釜鍧?閮芥槸"澶辫触琚潤榛?鐨勭被鍨?**

1. 绱㈠紩鏈€鍒濆啓鎴?*甯?`.class` 鐨勬潯鐩矾寰?*,鑰?`Target.targetClass` 瑕佺殑鏄被鍚?浜庢槸娌℃湁浠讳綍 target 鍖归厤,
   `transform()` **涓€娆￠兘娌¤璋冪敤**,鏃ュ織閲屼篃鐪嬩笉鍑哄紓甯?"Replaced" 0 鏉?銆傜幇鍦ㄧ储寮曞啓瑁哥被鍚?骞朵笖
   璇诲彇绔篃鍏煎 `.class` 鍚庣紑;绱㈠紩缂哄け鏃舵敼鎴愭墦涓€鏉?info(浠ュ墠鏄潤榛?`Set.of()`)銆?
2. `SrgRemap` 浼氶€犲嚭**閲嶅鏂规硶**銆傚疄娴?`ModelPart`:

       鏀瑰悕鍓? getChild(String) 鍑虹幇 1 娆?鍙︽湁 getChildModelDeep/getChildDeep)
       鏀瑰悕鍚? getChild(String) 鍑虹幇 2 娆?  鈫?ClassFormatError: Duplicate method name "getChild"

   鍘熷洜鏄?OptiFine 鐨勮浇鑽锋槸**娣风殑**:涓€閮ㄥ垎鎴愬憳宸茬粡鏄繍琛屾椂鐨勫悕瀛?鍙︿竴閮ㄥ垎杩樻槸 SRG 鍚?浜庢槸鏀瑰悕鍙兘钀藉埌
   绫婚噷宸茬粡瀛樺湪鐨勫悕瀛椾笂銆傜幇鍦ㄦ敼鍐欏櫒浼氭鏌?鐩爣鍚嶅瓧 + 鎻忚堪绗︽槸鍚﹀凡琚湰绫诲０鏄?,鏄垯**鎷掔粷杩欐鏀瑰悕**骞惰鏁?

       rewrote 21435 method and 17449 field names, 107 could not be resolved,
       42 renames refused because the class already has that name

**鎺ョ潃鎾炰笂鐨勬槸鏂伴棶棰?鑰屼笖鍙湁琛ヤ竵鐪熺殑鐢熸晥浜嗘墠浼氶亣鍒?*:

    java.lang.IllegalAccessError: class net.optifine.config.SliderableValueSetInt cannot access its
    superinterface net.minecraft.client.OptionInstance$SliderableValueSet
    (net.optifine.config.SliderableValueSetInt is in module srg ...;
     net.minecraft.client.OptionInstance$SliderableValueSet is in module minecraft@1.20.4 ...)

OptiFine 鐨勭被瀹炵幇浜嗘父鎴忛噷鐨勬帴鍙?鑰屼袱鑰呭垎灞?*涓嶅悓妯″潡**:鎴戜滑鐨?jar 鏄?`srg` 妯″潡,娓告垙鏄?`minecraft`
妯″潡銆備箣鍓嶅嚑杞涓嶅埌瀹?鏄洜涓洪偅鏃?OptiFine 鐨勮ˉ涓佹牴鏈病鐢熸晥(绗?68 杞弽鍊掓槸"骞插噣鍚姩"),杩欐潯璺緞
(OptiFine 鐨?`OptionInstance` 琛ヤ竵浠ｇ爜鍦?`Options.<init>` 閲岃璋冪敤)浠ュ墠浠庝笉鎵ц銆?

### 杩欎竴杞妸鍙兘鐨勫師鍥犻€愪釜鎺掓帀浜?鍙墿妯″潡瀵煎嚭杩欎竴鏉?

- **涓嶆槸"鎴戜滑鐨?jar 娌¤ FML 褰撴垚 mod"**:瀹炴祴涓夋潯绾跨殑鏃ュ織閲屾垜浠殑 jar 閮戒笉鍦?
  `Found mod file` 鍒楄〃閲?鈥斺€?1.21.4 閭ｆ潯**宸查獙璇佸彲鐢?*鐨勭嚎涔熶竴鏍?瀹冨彧鏈?2 鏉?`Found mod file`,
  閮芥槸娓告垙 jar 鍜?neoforge universal),鑰屽畠鐨?`OptiFineTransformer: Targets: 474` 姝ｅ父宸ヤ綔銆?
  鎵€浠?涓嶅湪 mod 鍒楄〃"鏄湰椤圭洰鐨勫父鎬?涓嶆槸杩欐鐨勭梾鍥犮€?
- **涓嶆槸 manifest**:鍘熺増 OptiFine 1.20.4 涓庢垜浠墦鍑烘潵鐨?jar(1.20.4 涓?1.21.4 涓や唤)鐨?
  `META-INF/MANIFEST.MF` 閮芥槸 `Automatic-Module-Name: optifine`銆?*浣嗘姤閿欓噷鐨勬ā鍧楀悕鏄?`srg`**,
  鑰?`srg` 姝ｅソ鏄?jar 閲岄偅涓?union 鏍圭洰褰曠殑鍚嶅瓧 鈥斺€?SecureJar 鎶?`srg/` 褰撴垚鍖呮牴,妯″潡鍚嶉殢涔嬭€屾潵銆?
- **涓嶆槸娈嬬暀鐨勬棫 jar**:`game1204/mods/` 閲屽彧鏈夋垜浠偅涓€涓?jar銆?
- **涓嶆槸 1.20.4 鐙湁**:鍥涗釜 OptiFine 鏋勫缓(1.20.1 / 1.20.4 / 1.21.1 / 1.21.4)閮藉甫
  `srg/net/optifine/config/SliderableValueSetInt.class`,鑰屼笖**閮藉甫
  `patch/srg/net/minecraft/client/OptionInstance$SliderableValueSet.class.xdelta`** 鈥斺€?涔熷氨鏄
  OptiFine 杩炶繖涓帴鍙ｈ嚜宸遍兘瑕佹墦琛ヤ竵銆傚樊鍒彧鍦ㄤ簬 1.20.4 杩欐潯绾跨幇鍦ㄧ湡鐨勬妸琛ヤ竵鏀捐繘鍘讳簡,浜庢槸杩欐潯璺緞
  绗竴娆¤鎵ц鍒般€?
- **ModLauncher 鐨勫叕寮€ API 缁欎笉鍑哄鍑?*:`IModuleLayerManager` 鍙湁 `getLayer(Layer)`,
  `ModuleLayerHandler` 鏈?`updateLayer(Layer, Consumer<LayerInfo>)`,浣?
  `ModuleLayerHandler$LayerInfo` 鏄釜鍖呯鏈?record,瀛楁鍙湁 `layer` 鍜?`cl` 鈥斺€?**娌℃湁
  `ModuleLayer.Controller`**,鎵€浠ユ嬁涓嶅埌 `addExports`銆傝€?`--add-exports` 鍛戒护琛屽弬鏁板彧浣滅敤浜?boot 灞?
  鐨勮В鏋?瀵?ModLauncher 鍔ㄦ€佸缓鍑烘潵鐨?GAME 灞傛棤鏁堛€?

**涓嬩竴姝?鎸夊彲琛屾€ф帓搴?**:鈶犲弽灏勬嬁 `ModuleLayer` 鐨勭鏈?`controller` 瀛楁(`Unsafe` 鎴?
`privateLookupIn`)鍐?`addExports(gameModule, pkg, ourModule)`,鍦ㄧ涓€涓被琚?transform 鏃跺仛涓€娆?
鈶℃兂鍔炴硶璁?FML 鎶婃垜浠殑 jar 褰撴垚 mod 妯″潡(FML 浼氱粰 mod 妯″潡瀵煎嚭娓告垙鍖?,闇€瑕佺湅 FML 2.0.17 鍒板簳鎸変粈涔?
鏉′欢瀵煎嚭;鈶㈢‘璁?FML 鏄惁鍙粰 GAME 灞傜殑妯″潡瀵煎嚭,鑰?transformer jar 钀藉湪 PLUGIN 灞?鈥斺€?鑻ユ槸,鍒欏簲鎶?
鎴愬搧绫荤殑瀹夸富鎹㈠埌 mod 灞傘€?*杩欎竴杞病鏈夎惤鍦颁慨澶?鍙妸鑼冨洿鏀剁獎鍒拌繖涓夋潯銆?*

### 绱ф帴涓€杞?涓夋潯鍊欓€夐噷涓ゆ潯琚惁鎺?骞朵笖鎵惧埌浜嗗叧閿殑涓嶅绉?

- **鈶犲弽灏?layer 鐨?controller:涓嶅彲鑳姐€?* `javap -p java.lang.ModuleLayer`(JDK 17)鏄剧ず瀹冪殑瀛楁鍙湁
  `cf / parents / nameToModule / allLayers / modules / servicesCatalog / CLV` 鈥斺€?**鏍规湰娌℃湁
  `controller` 瀛楁**銆侰ontroller 鍙湪 `ModuleLayer.defineModules*` 杩斿洖鏃跺瓨鍦?涔嬪悗鏃犲鍙彇,杩欐槸
  JDK 鐨勮璁°€俙Module.addExports` 鍙堟槸 `java.lang` 鍖呯鏈?娌℃湁 `--add-opens java.base/java.lang`
  灏卞埌涓嶄簡銆傗憼浣滃簾銆?
- **鈶¤ FML 鎶婃垜浠綋 mod:鍔犲厓鏁版嵁涓嶅銆?* 寰€ jar 閲岃ˉ浜嗕竴浠?`META-INF/neoforge.mods.toml`
  (涓?`mods.toml` 鍚屽唴瀹?鍚庣収鏃у惎鍔?`Found mod file` 浠嶇劧鍙湁閭?3 鏉?鎶ラ敊涓€瀛椾笉鍙樸€傛墍浠?娌¤褰撴垚
  mod"涓嶆槸鏌愪釜鍏冩暟鎹枃浠跺悕鐨勯棶棰樸€?
- **FML 2.0.17 鑷繁浠庝笉璋冪敤 `addExports`銆?* 鎶?`loader-2.0.17.jar` 閲?*姣忎竴涓?* class 鐨勫瓧鑺傛壂涓€閬嶆壘
  `addExports`:鍛戒腑 **0** 涓€備篃灏辨槸璇磋繖鏉＄嚎鐨?FML 鏍规湰娌℃湁"缁欒皝瀵煎嚭娓告垙鍖?杩欎竴姝ャ€?

**鍏抽敭鐨勪笉瀵圭О(涓嬩竴杞殑鎶撴墜)**:1.21.4 閭ｆ潯**宸查獙璇?*鐨勭嚎涓?OptiFine 鐨勮ˉ涓佹槸鐢熸晥鐨?
(`Targets: 474`),鑰屽畠鐨?`OptionInstance` 琛ヤ竵**鍚屾牱寮曠敤浜?* `SliderableValueSetInt`:

    [1.21.4-patched] srg/net/minecraft/client/OptionInstance.class: references SliderableValueSetInt = True
    [1.20.4-patched] srg/net/minecraft/client/OptionInstance.class: references SliderableValueSetInt = True

鑰?1.21.4 鐨勬棩蹇楅噷 `IllegalAccessError`=0銆?*鍚屼竴娈?OptiFine 浠ｇ爜鍦?21.4 涓婅兘杩囨ā鍧楁鏌ャ€佸湪 2.0.17 涓?
涓嶈兘**,鎵€浠ヨ繖涓嶆槸"OptiFine 鐨勫啓娉曟湁闂",鑰屾槸**涓や釜 FML 鐗堟湰缁欑殑妯″潡璁块棶涓嶅悓** 鈥斺€?21.x 缁欎簡,2.0.17 娌＄粰,
鑰屼笖缁欑殑鍦版柟涓嶅湪 `loader-2.0.17.jar` 閲?涓嬩竴涓鎵殑鏄?`securejarhandler` 涓?`neoforge` 閭ｄ袱涓?jar)銆?

**涓嬩竴杞殑绗竴姝?娴嬮噺,涓嶆槸鐚?**:鍦?loader 閲屽姞涓€涓帰閽?鐓?`ReloadProbe` 閭ｅ,鐢ㄧ郴缁熷睘鎬у紑鍏?,
鎶?*杩愯鏃剁湡瀹炵殑妯″潡鍥?*鎵撳嚭鏉?娓告垙妯″潡鐨勫寘鍒楄〃銆乣gameModule.isExported(pkg, ourModule)` 鐨勭粨鏋溿€?
`ourModule.canRead(gameModule)`銆佷互鍙婃垜浠ā鍧楃殑鍚嶅瓧涓庢墍鍦ㄥ眰銆傜幇鍦ㄧ己鐨勬鏄繖涓?鈥斺€?鍓嶉潰鍏ㄦ槸"浠庨敊璇秷鎭?
鍙嶆帹"銆傛湁浜嗗畠,鎵嶇煡閬撹琛?export 杩樻槸璇ヨˉ read,杩樻槸璇ユ崲涓眰鏀炬垚鍝佺被銆?

### 鍐嶄竴杞?鎺ュ彛鐨勫彲瑙佹€т笉鏄棶棰?闂鍥炲埌"璋佺粰鎴戜滑 export",骞舵壘鍒颁竴鏉″彲鍋氱殑璺?

缁х画閲忎簡涓変欢浜?

1. **`OptionInstance$SliderableValueSet` 鍦ㄥ師鐗堣繍琛屾椂閲屾槸鍖呯鏈夌殑**
   (`interface net.minecraft.client.OptionInstance$SliderableValueSet<T>`,娌℃湁 `public`),
   1.20.4 涓?1.21.4 閮戒竴鏍枫€?
2. **OptiFine 鐨勮ˉ涓佹妸瀹冩敼鎴愪簡 `public`**,鑰屼笖鎴戜滑鐨勬垚鍝佺被纭疄鎹笂浜嗗畠:

       1.20.4-patched(raw)  : public interface net.minecraft.client.OptionInstance$SliderableValueSet<T>
       1.20.4-shipped       : public interface net.minecraft.client.OptionInstance$SliderableValueSet<T>
       1.21.4-patched       : public interface net.minecraft.client.OptionInstance$SliderableValueSet<T>
       index 閲?OptionInstance 鐩稿叧鏉＄洰:... OptionInstance$SliderableValueSet, ...

   鎵€浠?*涓嶆槸鍙鎬ч棶棰?*;瀹炵幇涓€涓?public 鎺ュ彛,鍗′綇鐨勫彧鑳芥槸鍖呭鍑恒€?
3. **杩欐潯绾夸笂娌℃湁浠讳綍缁勪欢璋冪敤 `addExports`**:`loader-2.0.17.jar` 閲?0 鍛戒腑,`securejarhandler`銆?
   `neoforge`銆乣coremods`銆乣JarJarFileSystems`銆乣accesstransformers` 閲屼篃鏄?0 鍛戒腑,鍙湁 ModLauncher 鐨?
   **TestingLaunchHandlerService** 鎻愬埌杩囧畠銆備篃灏辨槸璇存父鎴忔ā鍧楃殑瀵煎嚭**涓嶆槸闈犺皟鐢?API 鍔犵殑**銆?

**鐢辨寰楀埌鐨?杩樺緟楠岃瘉鐨?缁撹涓庡仛娉?*:FML 2.0.17 澶ф鐜囨槸鍦?*鏋勯€犳父鎴忔ā鍧楃殑 ModuleDescriptor 鏃?*
鎶婂悇涓寘 `exports` 缁?瀹冭璇嗙殑 mod 妯″潡"(鎵€浠ヤ唬鐮侀噷鐪嬩笉鍒?`addExports`)銆傛垜浠殑 jar 鍥犱负鍦?
`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 閲屽０鏄庝簡鏈嶅姟,琚?ModLauncher 褰撲綔
**transformer jar** 鏀惰蛋,FML 浜庢槸浠庝笉鎶婂畠褰?mod 鐪?鈥斺€?杩欎篃瑙ｉ噴浜嗕负浠€涔堝線 jar 閲岃ˉ
`neoforge.mods.toml` 姣棤浣滅敤:**涓嶆槸鏂囦欢鍚嶇殑闂,鏄繖涓?jar 鏍规湰涓嶅湪 mod 鎵弿鐨勭粨鏋滈噷**銆?

**涓嬩竴杞鍋氱殑瀹為獙(鎷嗘垚涓や釜 jar)**:

- **杞借嵎 mod jar**:OptiFine 鑷繁鐨勭被鏀惧湪**鑷劧璺緞**(`net/optifine/**`,涓嶈 `srg/` 鑱斿悎鏍?銆佸甫
  `META-INF/mods.toml`銆?*涓嶆斁浠讳綍鏈嶅姟鏂囦欢** 鈥斺€?杩欐牱瀹冩槸涓€涓函绮圭殑 mod,FML 浼氭妸瀹冨綋 mod 妯″潡骞剁粰瀹?
  瀵煎嚭娓告垙鍖?`net.optifine.config.SliderableValueSetInt` 瀹炵幇 `net.minecraft.client` 鐨勬帴鍙ｄ究鍚堟硶;
- **transformer jar**:鍙斁鎴戜滑鐨?loader 绫讳笌 `optifineoforge/patched/**`(杩欎簺绫诲彧琚綋**瀛楄妭**璇?
  浠庝笉浣滀负绫讳粠閭ｉ噷鍔犺浇)+ 鏈嶅姟鏂囦欢銆?

杩欎釜瀹為獙鑳戒竴娆¤娓?瀵煎嚭鏄笉鏄寜 mod 妯″潡缁欑殑":濡傛灉閿欒娑堝け,缁撹鎴愮珛涓旈棶棰樹竴璧疯В鍐?濡傛灉閿欒鍙樻垚鍒?
鐨勬牱瀛?閭ｄ篃鑳界珛鍒绘帓闄よ繖鏉＄嚎銆?

### 鍐冲畾鎬х殑涓€杞?鎺㈤拡鍚︽帀浜嗘ā鍧楃悊璁?鐪熸鐨勭梾鍥犳槸**璁块棶鏍囧織娌¤澶嶅埗**

鍏堢収璁″垝鍔犱簡妯″潡鍥炬帰閽?`PatchedClassTransformer.logModulesOnce`,鍙妯″潡鍏冩暟鎹€佷笉鍔犺浇浠讳綍娓告垙绫?,
绗竴娆″惎鍔ㄥ氨鎶婄粨璁烘墦鍑烘潵浜?

    module graph: our module is optifine, layer manager captured
    module graph: GAME layer holds minecraft srg mixinsynthetic neoforge mixinextras.neoforge
    module graph: optifine -> minecraft: reads it = false, net.minecraft.client is exported to it = true
    module graph: srg      -> minecraft: reads it = true,  net.minecraft.client is exported to it = true
    module graph: neoforge -> minecraft: reads it = true,  net.minecraft.client is exported to it = true

**鎵胯浇閭ｄ釜绫荤殑妯″潡 `srg` 鏃㈣兘璇?`minecraft`,涔熸嬁鍒颁簡 `net.minecraft.client` 鐨勫鍑?* 鈥斺€?涓ゆ潯杈归兘榻?
JVM 鍗翠粛鐒舵姤 `IllegalAccessError`銆傛ā鍧楃悊璁哄洜姝よ鑷繁鐨勬祴閲忓惁鎺?鍓嶄袱杞偅浜?璋佺粰 export"鐨勬帹鏂叏閮ㄤ綔搴?
骞稿ソ娌℃湁鐓х潃瀹冧滑鏀逛唬鐮?銆?

鐪熸鐨勭梾鍥犳槸 `PatchedClassTransformer` **鍙崲浜?superName / interfaces / signature / fields / methods,
娌℃湁鎹?`access`**:OptiFine 鐨勮ˉ涓佹妸 `OptionInstance$SliderableValueSet` 浠庡寘绉佹湁鏀规垚 `public`,姝ｆ槸涓轰簡璁?
瀹冭嚜宸?`net.optifine.config` 閲岀殑绫昏兘瀹炵幇瀹?涓嶅鍒惰闂爣蹇?鎹㈣鍚庣殑鎺ュ彛浠嶆槸鍖呯鏈?浜庢槸"瀹炵幇涓€涓寘绉佹湁
鎺ュ彛"闈炴硶 鈥斺€?鑰?JVM 鐨勬姤閿欏湪涓や晶妯″潡涓嶅悓鍚嶆椂**涓€瀹氫細甯︿笂妯″潡淇℃伅**,鎶婃垜寮曞悜浜嗛敊璇殑鏂瑰悜銆?

鏀规硶鍒嗕袱姝?鑰岀浜屾鎭板ソ鐢辩涓€姝ョ殑鎶ラ敊鑷繁鎸囧嚭鏉?

1. 鍏堝彧澶嶅埗鍙鎬т笁浣?鈫?`IllegalAccessError` 娑堝け(鎹㈣鏁?112 鈫?119),绱ф帴鐫€鏄暅鍍忛棶棰?
   `IncompatibleClassChangeError: class net.optifine.config.SliderPercentageOptionOF cannot inherit from
   final class net.minecraft.client.OptionInstance` 鈥斺€?OptiFine 鐨勮ˉ涓佽繕鎶?`final` 鍘绘帀浜嗐€?
2. 浜庢槸鏁村瓧澶嶅埗 `input.access = patched.access`(OptiFine 鐨勯偅浠界紪璇戝氨鏄杩愯鏃朵笅杩欎釜绫荤殑鐗堟湰)銆?

缁撴灉:

    swapped: 182 涓被(61 鈫?112 鈫?119 鈫?182),stderr 0 琛?

**涓嬩竴涓敊璇诞鍑烘按闈?鑰屽畠鎸囧悜鐨勬槸鎴戞柟鐨勯仐鐣?*:涓€涓?*娌¤鏀瑰悕鐨?SRG 鍚?*娲讳簡涓嬫潵:

    java.lang.NoSuchMethodError: 'net.minecraft.client.renderer.texture.TextureManager
      net.minecraft.client.Minecraft.m_91097_()'
        at TRANSFORMER/srg/net.optifine.Config.getTextureManager(Config.java:1187)

`m_91097_` 鏈琚?`SrgRemap` 鏀规垚 `getTextureManager`銆傚畠灞炰簬閭?107 涓湭瑙ｆ瀽閲岀殑涓€绫?涓嬩竴杞洿鎺ヤ粠
"涓轰粈涔?`Minecraft.m_91097_` 娌¤鏀瑰啓"鏌ヨ捣(琛ㄩ噷鏈夊畠鍚?鏄纰版挒淇濇姢鎷掍簡,杩樻槸绫诲埆鍚嶄笉涓€鑷撮偅涓€鏃?,
杩欐瘮缁х画杩芥柊閿欒鏇村€笺€?

### 缁撴灉灏辨槸纰版挒淇濇姢鍐欓敊浜嗚寖鍥?宸蹭慨),鑰屼笖 OptiFine 鐪熺殑璺戣捣鏉ヤ簡

`m_91097_` 鐨勭瓟妗堝湪涓婁竴杞偅娈靛爢鏍堥噷灏卞啓鐫€:`net.optifine.Config.getTextureManager` 鈥斺€?**OptiFine 鑷繁涔?
鏈変釜鍙?`getTextureManager` 鐨勬柟娉?*銆傜鎾炰繚鎶ゆ湰鎰忔槸"涓嶈鎶婁竴涓垚鍛樻敼鍚嶆垚杩欎釜绫诲凡缁忔湁鐨勫悕瀛?,浣嗗畠琚?
`mapMethodName` 鍦?*寮曠敤**涓婁篃浼氳皟鐢?鑰屽紩鐢ㄤ笌澹版槑鍙兘闈?owner 鍖哄垎:澹版槑鏃?owner 鏄鍦ㄦ敼鍐欑殑绫?寮曠敤鏃?
owner 鏄璋冪敤鐨勭被銆傚綋鏃剁殑瀹炵幇鍙湅"褰撳墠绫绘湁娌℃湁杩欎釜鍚嶅瓧",浜庢槸鎶?`Config` 閲屽
`Minecraft.m_91097_()` 鐨?*璋冪敤**涔熸嫆浜?鐣欎笅浜嗕竴涓繍琛屾椂骞朵笉瀛樺湪鐨?SRG 鍚嶃€備慨娉曟槸涓€琛屾潯浠?
`occupied()` 鍙湪 `node.name.equals(owner)` 鏃舵墠鍒ょ鎾炪€?

    refused: 42 鈫?10(鍏朵腑 32 鏉℃槸鏈潵涓嶈鎷掔殑寮曠敤),rewrote 21461 鏂规硶鍚?

鍚姩缁撴灉:**swapped 184(stderr 0 琛?**,鑰屼笖 OptiFine 宸茬粡鐪熺殑鍦ㄨ窇浜?鈥斺€?瀹冨嚭鐜板湪鑷繁鐨勫穿婧冩姤鍛婇噷:

    OptiFine Version: OptiFine_1.20.4_HD_U_I7
    OptiFine Build: 20240317-172634
    Shaders: null

涓嬩竴涓敊璇崲浜嗕竴绫?鑰屼笖杩欐**涓嶆槸"璁″垝婕忎簡",鑰屾槸"杩愯鏃舵牴鏈病鏈?**:

    java.lang.NoSuchMethodError: 'void net.minecraft.world.level.block.entity.BlockEntity.gatherCapabilities()'
      at net.minecraft.world.level.block.entity.BlockEntity.<init>(BlockEntity.java:47)

閲忎簡涓よ竟:OptiFine 閭ｄ唤 `BlockEntity` 閲?`gatherCapabilities`/`setData`/`removeData` **涓€涓兘娌℃湁**,鑰?
NeoForge 杩愯鏃堕噷**鍙湁 `setData`/`removeData`,娌℃湁 `gatherCapabilities`**銆傚師鍥犳槸 NeoForge 20.4 鐢?
attachment 浣撶郴(`setData`/`removeData`)鍙栦唬浜?Forge 鐨?capability 浣撶郴,`gatherCapabilities` 琚垹鎺変簡,
鑰?OptiFine 閭ｄ唤鏄収 **Forge** 缂栬瘧鐨?`<init>` 閲岃繕鍦ㄥ彨瀹冦€傝鍒掓病鎭㈠瀹冩槸**瀵圭殑** 鈥斺€?杩愯鏃舵病鏈夌殑涓滆タ
鏃犳硶浣滀负 donor銆傝繖鏄?`ForgeApiShims` 閭ｄ竴绫婚棶棰?Forge API 鍦?NeoForge 涓婁笉瀛樺湪),涓嶆槸鎴愬憳鎭㈠闂銆?

**涓嬩竴杞殑璧风偣(鍏蜂綋鍒板彲鎵ц)**:瀵?*鏀瑰悕鍚?*鐨勮浇鑽峰仛涓€閬嶅紩鐢ㄦ壂鎻?鍒楀嚭"owner 鏄父鎴忕被銆佽€岃鎴愬憳鍦?
杩愯鏃朵笉瀛樺湪"鐨勫叏閮ㄥ紩鐢?鈥斺€?閭ｄ唤娓呭崟灏辨槸杩欎竴绫诲緟澶勭悊椤圭殑瀹屾暣闆嗗悎(`gatherCapabilities` 鍙槸鍏朵腑绗竴鏉?,
鍐嶅喅瀹氭槸鍔犵┖瀹炵幇杩樻槸鎶婅皟鐢ㄧ偣鍘绘帀銆?

### 娓呭崟鍋氬嚭鏉ヤ簡,鑰屼笖鏀跺緱寰堝皬:10 鏉″紩鐢ㄣ€? 涓?owner

鏂板绂荤嚎宸ュ叿 `MissingTargets`:鎵繃杞借嵎閲?*姣忎竴鏉?*瀵规父鎴忔垚鍛樼殑寮曠敤(`SrgRemap` 鐪嬩笉鍒拌繖绫?鍥犱负瀹冨彧
鐪?`m_`/`f_` 褰㈢姸鐨勫悕瀛?鑰?Forge API 鎴愬憳鐢ㄧ殑鏄櫘閫氬悕瀛?,娌跨户鎵块摼闂繍琛屾椂鏈夋病鏈夈€備袱娆℃敹鏁涘緢鍏抽敭:

   鍙储寮曡繍琛屾椂:            1118 涓被銆?3162 鏉″紩鐢?**1259** 鏉?缂哄け"
   鍐嶆妸杞借嵎鑷繁鐨勭被涔熺储寮?  148 鏉?
     鈥斺€?宸鏄?OptiFine **鑷繁鍔犺繘琚浛鎹㈢被閲岀殑鎴愬憳**(ModelPart.getChildModelDeep銆?
        Options.ofClouds銆乀extureAtlasSprite.spriteNormal ...):杩愯鏃舵病鏈?浣嗘崲瑁呭悗鐨勭被閲屾湁,
        璋冪敤瀹屽叏鎴愮珛銆備笉绱㈠紩杞借嵎灏变細鎶婂畠浠叏鎶ユ垚闂銆?
   鍐嶇储寮曞畬鏁村簱 classpath + 鎶?JDK 绁栧厛瑙嗕负瀛樺湪:  **10 鏉?*
     鈥斺€?宸鏄?datafixers/Codec/LogUtils/authlib 杩欎簺**鍒殑 jar**閲岀殑绫?涓嶅湪瀹㈡埛绔?jar 閲?,
        浠ュ強 `Direction.ordinal()` 杩欑被浠?`java.lang.Enum` 缁ф壙鏉ョ殑鏂规硶銆?

鏈€缁?10 鏉?鍒嗘垚涓夌粍,姣忕粍鎬ц川涓嶅悓:

   8x  net/minecraft/world/level/block/entity/BlockEntity
         gatherCapabilities()V / invalidateCaps()V / deserializeCaps(...)V / serializeCaps()... 
         getCapabilities()Lnet/minecraftforge/common/capabilities/CapabilityDispatcher;
         requestModelDataUpdate()V
       鈫?Forge 鐨?capability 浣撶郴,NeoForge 20.4 鐢?attachment 鍙栦唬鍚庡垹鎺変簡;`getCapabilities` 鐨勮繑鍥?
         绫诲瀷鐢氳嚦灏辨槸 **`net/minecraftforge`** 鐨勭被銆傝繖灞炰簬 `ForgeApiShims` 閭ｄ竴绫?Forge API 鍦?
         NeoForge 涓婁笉瀛樺湪),涓嶆槸鎴愬憳鎭㈠銆?
   2x  net/minecraft/launchwrapper/LaunchClassLoader.registerTransformer(String)V
       鈫?鏉ヨ嚜 `optifine/OptiFineForgeTweaker`,閭ｆ槸 OptiFine 缁?Forge 瀹夎鍣ㄧ敤鐨?tweaker 绫?鍦?
         NeoForge 涓婃牴鏈笉浼氳鍔犺浇(manifest 閲岀殑 `TweakClass` 鎸囧悜瀹?銆傚彲浠ユ棤瑙?涔熷彲浠ュ湪閲嶆墦鍖呮椂
         涓?installer 绫讳竴璧峰垹鎺夈€?
   5x  com/mojang/blaze3d/platform/NativeImage$WriteCallback
         free()V / address()J / getData(JI)Ljava/nio/ByteBuffer;
       鈫?杩欎竴缁勮繕娌″畾鎬?涓嬩竴杞鍏堢湅鏄帴鍙ｅ湪涓よ竟澹版槑涓嶅悓,杩樻槸 OptiFine 閭ｄ唤缂栬瘧瀵圭潃鍒殑鐗堟湰銆?

**涓嬩竴杞?*:缁?BlockEntity 閭?8 涓垚鍛樺嚭**绌哄疄鐜?*(璧?`ForgeApiShims` 閭ｆ潯璺?鍥犱负杩愯鏃舵病鏈?donor),
椤烘墜鐪?`NativeImage$WriteCallback` 閭?5 鏉″埌搴曟槸浠€涔?鐒跺悗缁х画鍚姩銆?

### 绌哄疄鐜皊tep + 鎴愬憳鍙鎬у彇瀹借€?鈥斺€?1.20.4 璺戣捣鏉ヤ簡

`MissingTargets` 鍔犱簡 `--stub` 妯″紡:鎶?*杩愯鏃跺拰杞借嵎閮芥病鏈?*鐨勬垚鍛樻寜杩斿洖绫诲瀷琛ヤ竴涓粯璁ゅ€煎疄鐜?
(void鈫扲ETURN銆佸璞♀啋ACONST_NULL銆佹暟瀛椻啋0)銆?*鎺ュ彛閲岃ˉ鐨勬槸 default 鏂规硶鑰屼笉鏄娊璞℃柟娉?* 鈥斺€?缁欐帴鍙ｅ姞鎶借薄
鏂规硶浼氭妸涓€娆＄己璋冪敤鍙樻垚鍒鐨?AbstractMethodError銆俽ig 閲屾垚涓?3b2 姝?

    scanned 1118 classes, 43162 references, 10 missing
    stubbed 9 members on {NativeImage$WriteCallback=3, BlockEntity=6},
            1 left alone because their owner is a runtime class rather than a payload class

鎺ョ潃鎹㈣鏁颁粠 184 娑ㄥ埌 224,鑰屾柊閿欒姝ｅソ鏄?*涓婁竴杞偅鏉℃暀璁殑鍙嶉潰**:

    IllegalAccessError: NeoForgeRenderTypes$Internal tried to access method 'RenderType.create(...)'

涓婁竴杞鍒?璁块棶鏍囧織瑕佽窡鐫€ OptiFine 璧?,浣嗛偅鍙**绫?*鎴愮珛;瀵?*鎴愬憳**,娓告垙鐨勭増鏈彲鑳借 access
transformer 鏀惧杩?NeoForge 鑷繁鐨勪唬鐮佷緷璧栭偅涓斁瀹姐€傛暣瀛楃収鎼?OptiFine 鐨?`create()` 鎶婂畠narrow鍥炲幓,
浜庢槸 NeoForge 璋冧笉鍔ㄤ簡銆傜幇鍦ㄦ槸:**绫绘爣蹇楀彇 OptiFine 鐨?鎴愬憳鍙鎬у彇涓よ€呬腑鏇村鐨勯偅涓?*
(`rank`:public 3 > protected 2 > package 1 > private 0)銆?

鏀瑰畬涔嬪悗杩欎竴杞殑缁撴灉鏄?*璐ㄥ彉**:

    swapped: 271 涓被
    reloads: 2                      鈫?璧勬簮閲嶈浇璺戝埌绗?2 娆?
    stdout: 14874 琛?鍏朵腑 OpenGL 琛?7067銆丼haders 14銆乧onnected textures 6)
    VERDICT: TIMEOUT (261s)         鈫?涓嶆槸宕╂簝,鏄祻瑙堝櫒绐楀彛涓€鐩存椿鐫€琚鏃跺櫒鎺愭帀
    crash-reports 鐩綍閲屾渶鏂扮殑涓€浠戒粛鏄笂涓€杞?08:22 鐨?鈥斺€?**杩欎竴杞病鏈変骇鐢熷穿婧冩姤鍛?*

stderr 鍙墿 4 鏉?`NoClassDefFoundError`(鍏朵腑涓€鏉℃槸 `net.minecraft.world.item.ItemStack`),鍔犱笂 stdout 閲?
1 鏉?`Caught error`;閮戒笉鑷村懡,娓告垙缁х画璺戙€傝繖鏄?1.20.4 杩欐潯绾跨涓€娆¤繘鍏?鑳界帺鐨勭姸鎬?,鑰屼笉鏄?鍚姩鍗冲穿"銆?

**涓嬩竴杞鍋氱殑**:鎶婇偅 4 鏉?NoClassDefFoundError 鐨勫畬鏁村爢鏍堣鍑烘潵(鐜板湪鍙湅鍒板熬閮?,纭瀹冧滑鏄笉鏄?
鍚屼竴绫婚棶棰?鏌愪釜鎹㈣绫荤殑瀛楁鎻忚堪绗﹀湪妯″潡灏氭湭灏辩华鏃惰瑙ｆ瀽);鐒跺悗鎸?1.21.4 閭ｆ潯绾跨殑楠屾敹娓呭崟鏍稿
(璐村浘闆嗐€佹ā鍨嬬儤鐒欍€佽祫婧愰噸杞芥棤閿?,鎶?1.20.4 浠?鑳藉惎鍔?鎺ㄨ繘鍒?琛屼负涓庡凡楠岃瘉绾夸竴鑷?銆?

### 鍓╀笅閭ｄ釜鐪熼棶棰樻煡娓呬簡:Forge **鎵╁睍鎺ュ彛**涓婄户鎵挎潵鐨勬垚鍛樺湪 shim 閲屼涪浜?

4 鏉″紓甯歌瀹?鍏ㄦ槸鍚屼竴褰㈢姸涓?*涓嶈嚧鍛?* 鈥斺€?OptiFine 鐨勫弽灏?`ReflectorMethod.getMethod`)鍦ㄨВ鏋?
`BlockState`/`ItemStack` 鏃剁粡 builtin loader 鎶涘嚭 `ClassNotFoundException`,琚悶鎺夈€佽鍙€夌壒鎬ц烦杩囥€?
鐪熸鐨勯棶棰樺湪 stdout 閲岄偅涓€鏉?`Caught error loading resourcepacks`(鏁存壒璧勬簮鍖呰绉婚櫎,涔熷氨鏄?*妯″瀷鐑樼剻娌′簡**):

    NoSuchMethodError: 'BakedModel ModelBaker.bake(ResourceLocation, ModelState, Function)'
      at net.minecraft.client.renderer.block.model.MultiVariant.bake(MultiVariant.java:71)
      at ...multipart.MultiPart.bake
      at ...model.ModelBakery$ModelBakerImpl.bake

涓よ竟閮介噺浜?

- 杩愯鏃剁殑 `ModelBaker` 鏄?**NeoForge 鐗?*:鍙湁 `getModel(ResourceLocation)` 鍜?
  `bake(ResourceLocation, ModelState)`(涓ゅ弬鏁?;
- 鎴戜滑鍙戝嚭鍘荤殑 `ModelBaker`(`ModelBaker` 纭疄琚崲瑁呬簡,鏃ュ織閲屾湁杩欎竴琛?鏄?OptiFine 鐗?
  `public interface ModelBaker extends net.minecraftforge.client.extensions.IForgeModelBaker`,
  鑷繁鍙０鏄庨偅涓や釜鏂规硶 鈥斺€?**涓夊弬鏁扮殑 `bake` 鏄粠閭ｄ釜 Forge 鎵╁睍鎺ュ彛缁ф壙鏉ョ殑**;
- 鑰岄偅涓?shim 鏄?*绌虹殑**:

      105  net/minecraftforge/client/extensions/IForgeModelBaker.class
       99  net/minecraftforge/client/extensions/IForgeFont.class
      104  net/minecraftforge/client/extensions/IForgePoseStack.class

  涓€鐧炬潵瀛楄妭 = 娌℃湁鎴愬憳鐨勬帴鍙ｃ€俙ForgeApiShims` 鐨勮鍒欐槸"姣忎釜琚紩鐢ㄧ殑 Forge 绫诲瀷鍑轰竴涓?stub,鎴愬憳鍙?
  **OptiFine 鐨勭被鐩存帴鐐瑰悕鍦ㄥ畠涓婇潰鐨?*瀛楁涓庢柟娉?;鑰岃繖閲?OptiFine 璋冪敤鐨勬槸
  `ModelBaker.bake(涓夊弬鏁?`(owner 鏄?`ModelBaker`),**浠庢病鍦?`IForgeModelBaker` 涓婄偣杩囧悕**,浜庢槸瀹冩槸绌虹殑,
  閭ｆ潯缁ф壙鏉ョ殑鏂规硶灏辨秷澶变簡 鈥斺€?缁撴灉 `ModelBakery$ModelBakerImpl`(鎹㈣鍚?*纭疄**鏈夐偅涓笁鍙傛暟鏂规硶)
  涔熸晳涓嶄簡 `invokeinterface`,鍥犱负鎺ュ彛閲屾煡涓嶅埌銆?

**瑕佷慨浠€涔?涓嬩竴杞?**:璁╄繖浜?shim **鎺ュ彛**琛ヤ笂"杞借嵎閲岀殑瀹炵幇绫绘彁渚涗簡銆佽€岃繍琛屾椂鎺ュ彛娌℃湁"鐨勫叕寮€鏂规硶
(鎸夊疄鐜扮被鍘诲弽鎺?,杩欐牱 `bake(涓夊弬鏁?` 浼氬嚭鐜板湪鎺ュ彛涓?璋冪敤鍙В鏋愩€傝繖鏄釜閫氱梾,涓嶆 `ModelBaker` 涓€澶?
鍑℃槸 OptiFine 閫氳繃 Forge 鎵╁睍鎺ュ彛缁ф壙鎴愬憳鐨勫湴鏂归兘浼氳繖鏍枫€備慨瀹屽簲褰撹兘鎶?`Caught error loading
resourcepacks` 鍘绘帀,鎭㈠妯″瀷鐑樼剻 鈥斺€?閭ｆ鏄?1.21.4 閭ｆ潯绾块獙鏀舵椂鐢ㄨ繃鐨勫垽鎹箣涓€銆?

椤哄甫璁颁笅 shim 鍚嶅崟閲岀殑涓€澶勫彲鐤戠幇璞?`net/minecraftforge/cl.class`銆乣cli.class`銆乣clien.class`銆?
`client/m.class` 杩欑被**鍚嶅瓧鏄庢樉琚埅鏂?*鐨勬潯鐩篃鍦?48 涓?shim 閲屻€傚畠浠悓鏍峰彧鏈変竷鍗佹潵瀛楄妭,涓嶅奖鍝嶆湰娆?
璇婃柇(鐪熸鐩稿叧鐨?`IForgeModelBaker` 鍚嶅瓧鏄畬鏁寸殑),浣嗕笅涓€杞『鎵嬬‘璁や竴涓嬫槸鍚︽槸鐢熸垚鏃舵埅鏂€?

### 鐪熺浉涓嶆槸 shim 绌?鑰屾槸**鎹㈣鎺ュ彛鏃舵妸瓒呮帴鍙ｅ垪琛ㄦ暣涓崲鎺変簡**

缁х画鏌ヤ笅鍘诲彂鐜板墠闈㈢殑鎺ㄦ柇杩樺樊涓€灞傘€傛妸涓や唤杩愯鏃剁殑 `ModelBaker` 閮?javap 鍑烘潵瀵规瘮:

    NeoForm 鐨?  interface ModelBaker { getModel(ResourceLocation); bake(ResourceLocation, ModelState); }
    NeoForge 鐨? interface ModelBaker extends net.neoforged.neoforge.client.extensions.IModelBakerExtension
                 { getModel(ResourceLocation); bake(ResourceLocation, ModelState); }

**涓夊弬鏁扮殑 `bake` 鍦?NeoForge 閭ｈ竟鏄粠瀹冭嚜宸辩殑鎵╁睍鎺ュ彛 `IModelBakerExtension` 缁ф壙鏉ョ殑**,鑰?OptiFine 閭ｄ唤
`ModelBaker` 缁ф壙鐨勬槸 **Forge** 鐨勫搴旂墿 `IForgeModelBaker`(鎴戜滑鐨勭┖ shim)銆備袱杈瑰悇鑷?鍊?浜嗕笉鍚屾墿灞曟帴鍙ｇ殑
鏂规硶,鑰屾垜鍦ㄦ崲瑁呮椂 `input.interfaces = patched.interfaces` 鈥斺€?**鎶婅繍琛屾椂鐨勮秴鎺ュ彛鍒楄〃鏁翠釜鏇挎崲鎺変簡**,
浜庢槸杩愯鏃惰嚜宸辩殑璋冪敤鏂?`MultiVariant.bake` 鏄寜 `IModelBakerExtension` 缂栬瘧鐨?鍐嶄篃鎵句笉鍒伴偅鏉¤矾:

    NoSuchMethodError: 'BakedModel ModelBaker.bake(ResourceLocation, ModelState, Function)'

淇硶涓庡墠闈㈠嚑鏉″悓婧愨€斺€?*鍙栧苟闆嗚€屼笉鏄浛鎹?*(鍙湪鎺ュ彛涓婂仛:缁欑被鍔犺秴鎺ュ彛浼氳姹傚畠瀹炵幇閭ｄ簺鎶借薄鏂规硶,
鑰屾崲瑁呭悗鐨勭被浣撴湭蹇呮湁):

    if((patched.access & ACC_INTERFACE) != 0) {
        merged = patched.interfaces + input.interfaces 涓己鐨勯偅浜?
        input.interfaces = merged
    }

缁撴灉:**`Caught error: 0`** 鈥斺€?璧勬簮鍖呬笉鍐嶈鏁存壒绉婚櫎,妯″瀷鐑樼剻杩欎竴鍏宠繃浜?鎹㈣鏁?271 鈫?274)銆?

绱ф帴鐫€鍐掑嚭鐨勬槸鏂扮殑涓€灞?鑰屼笖杩欐鏄?*鐪熷穿婧?*(涓婁竴杞偅娆℃病鏈夊穿婧冩姤鍛?:

    java.lang.IllegalStateException: Already building.
      at fml_earlydisplay/SimpleBufferBuilder.begin
      at ...DisplayWindow.paintFramebuffer
      at neoforge/NeoForgeLoadingOverlay.render(NeoForgeLoadingOverlay.java:84)
      Description: Rendering overlay

鍘熷洜鏂瑰悜寰堟竻妤?**OptiFine 鎹㈣浜?`LoadingOverlay`**(鏃ュ織閲屾湁 `Replaced ...LoadingOverlay ... (25 fields,
12 methods)`),瀹冪殑鍔犺浇鐢婚潰浠ｇ爜涓?NeoForge 鐨?early-display 鍙犲湪涓€璧?`SimpleBufferBuilder` 琚噸鍏ャ€?
杩欎篃瑙ｉ噴浜嗕负浠€涔堜笂涓€杞?璺戝緱涔呭嵈娌″穿":閭ｆ椂 OptiFine 鐨勫姞杞界敾闈㈣ˉ涓佸洜鎺ュ彛瑙ｆ瀽澶辫触娌℃湁鐪熸鎵ц銆?
**涓嬩竴杞?鏈€鍚庝竴杞?**:浠?`NeoForgeLoadingOverlay` 涓?OptiFine 閭ｄ唤 `LoadingOverlay` 鐨勫叧绯诲叆鎵?
鍏堢‘璁ゆ槸涓嶆槸鍚屼竴甯ч噷涓ゆ潯娓叉煋璺緞閮借繘浜?early display;骞舵妸杩欎竴杞殑缁撹銆佸綋鍓嶄笁鏉＄嚎鐨勭姸鎬佷笌鍓╀綑
鑼冨洿鐨勫疄璇濆啓娓呮銆?

## 2026-09-15(缁?:1.20.4 璺戣捣鏉ヤ簡,鑰屼笖 OptiFine 鐨勭潃鑹插櫒绯荤粺鍦ㄥ垵濮嬪寲

杞姌鐐规槸**鍙戠幇浜嗘垜鑷繁宸ュ叿閲岀殑涓€涓?bug**,鑰屼笉鏄柊鐭ヨ瘑銆?

鎶?`LoadingOverlay` 鐣欏湪杩愯鏃堕偅鐗堜箣鍚?娓告垙姝诲湪:

    NoSuchMethodError: 'void net.minecraft.client.gui.screens.LoadingOverlay.update()'
      at GameRenderer.render(GameRenderer.java:1311)

璇诲瓧鑺傜爜鎵嶇湅娓呰繖鏄竴瀵?

    880: instanceof  LoadingOverlay
    900: invokevirtual LoadingOverlay.update:()V     鈫?鍦?OptiFine 鐗堢殑 GameRenderer 閲?

`update()` 鍙瓨鍦ㄤ簬 **OptiFine 閭ｇ増** `LoadingOverlay`(杩愯鏃朵袱浠介兘娌℃湁),鎵€浠?鐣欒繍琛屾椂閭ｇ増"绛変簬鎷嗕簡杩欎竴
瀵广€傛纭仛娉曟槸鎶婅繖瀵归噷缂虹殑鏂规硶**鍦ㄧ被鍔犺浇鏃惰ˉ鍥炶繍琛屾椂閭ｇ増** 鈥斺€?姝ｆ槸涓婁竴杞垰鎼ソ銆佸嵈涓€鐩存病鐢熸晥鐨?
"寤惰繜 stub"鏈哄埗銆傚畠娌＄敓鏁堢殑鍘熷洜鏈変袱灞?閮藉湪宸ュ叿閲?

1. **鍒嗘瀽璇荤殑 jar 涓庡彂甯冪殑 jar 涓嶆槸鍚屼竴涓?*:stub pass 璺戝湪鎺掗櫎涔嬪墠,绱㈠紩閲岃繕鐣欑潃 OptiFine 閭ｇ増
   `LoadingOverlay`(甯?`update()`),寮曠敤浜庢槸"鐪嬭捣鏉ヨ兘婊¤冻"銆傜幇鍦?`MissingTargets` 鎺ュ彈璺宠繃鍓嶇紑
   (`--stub <in> <out> <stubs> <skip> ...`),绱㈠紩銆佹壂鎻忋€乻tub 涓夊鍏辩敤鍚屼竴浠芥竻鍗?涓嶅彂甯冪殑绫讳篃涓嶅啀琚壂鎻忋€?
2. **涓€鏉℃妸鏁翠釜宸ュ叿搴熸帀鐨勮鍒?*:JDK 绁栧厛琚綋鎴?涓€瀹氭湁"銆傛湰鎰忔槸鍒妸 `Direction.ordinal()`(缁ф壙鑷?
   `java.lang.Enum`)璇姤,浣?*姣忎釜绫讳笂闈㈤兘鏈?`java/lang/Object`**,璧伴摼涓€纰板埌瀹冨氨杩斿洖"瀛樺湪"鈥斺€旀姤鍛婂洜姝?
   闀挎湡鍙湁 10 鏉?`LoadingOverlay.update()` 浠庢湭琚姤鍑恒€佷篃浠庢湭琚?stub銆傛敼娉曚笉鏄啀璋冨惎鍙戝紡,鑰屾槸**鐪熺殑鎶?
   JDK 绱㈠紩杩涙潵**:閫氳繃 `jrt:/` 璇?`java.base`/`java.desktop`/`java.logging`(12998 涓被),
   `declares()` 閲岄偅鏉＄壒渚嬫暣涓垹鎺夈€?

淇畬鍓嶅悗鐨勫姣斿緢鑳借鏄庨棶棰?

    閿欒瑙勫垯涓?68 鏉?缂哄け",鍏朵腑 ordinal()/getMessage()/add() 杩欑被 JDK 缁ф壙鐨勫叏鏄亣闃虫€?
    姝ｇ‘绱㈠紩鍚?24 鏉?鐪熸鐨勯棶棰樻墠娴嚭鏉?
               BakedModel.getQuads(...,ModelData,...) / getRenderTypes(...,ModelData) /
               useAmbientOcclusion(...)   鈫?Forge 褰㈢姸鐨勬ā鍨?API
               BlockEntity 鐨?capability 鎴愬憳銆丯ativeImage$WriteCallback 鐨勪笁涓柟娉?
               LoadingOverlay.update()V 涓?isFadeOut()Z   鈫?灏辨槸涓婇潰閭ｄ竴瀵圭己鐨?

鏈疆缁撴灉:

    3b2/5  stubbed 21 members on {NativeImage$WriteCallback=3, BakedModel=9, ModelBaker=2,
                                  BlockEntity=6, BlockState=1}, 3 left for the loader
    load   Runtime stubs to add: 3 members across 2 classes
           Stubbed net.minecraft.client.gui.screens.LoadingOverlay.update()V
           Stubbed net.minecraft.client.gui.screens.LoadingOverlay.isFadeOut()Z

鍚姩:**STARTED (41s, marker: Sound engine started)**,鑰屼笖 rig 杩欐**鎴埌浜嗗浘**(`screen.png`,70 KB 鈥斺€?
鍓嶉潰鍑犺疆涓€鐩存姤 `handle is invalid`,鍥犱负鏍规湰娌℃湁绐楀彛)銆傞噺鍖?

    swapped: 282 涓被   reloads: 2   stdout: 21132 琛?鍏朵腑 OpenGL 10153)
    [Shaders] OpenGL Version: 3.2.0 NVIDIA 591.86 / GL_MAX_DRAW_BUFFERS: 8   鈫?OptiFine 鐫€鑹插櫒绯荤粺鍦ㄥ垵濮嬪寲
    18 鏉?"Created:"銆丼haders 14 琛屻€丆onnected textures 6 琛?

鍓╀笅涓€涓け璐?姣斾箣鍓嶅叿浣撳緱澶?

    IllegalStateException: Failed to create model for minecraft:skull
      鈫?Caught error loading resourcepacks(鏁存壒璧勬簮鍖呰绉婚櫎)

涔熷氨鏄**妯″瀷鐑樼剻澶т綋閫氫簡**,鍙墿 `minecraft:skull` 涓€涓ā鍨嬨€?*涓嬩竴杞?*:鏌ュ畠涓轰粈涔堝け璐?瀹冧笌 OptiFine
鎹㈣鐨?`SkullBlockRenderer`/`SkullModel` 鏈夊叧),骞剁‘璁?`BakedModel` 閭?9 涓?Forge 褰㈢姸鎴愬憳杩斿洖榛樿鍊兼槸鍚?
姝ｆ槸鍘熷洜 鈥斺€?閭?9 涓?stub 璁╄皟鐢?鑳借繃",浣嗚涔変笂鏈繀瀵广€?

## 2026-09-15(鍐嶇画):skull 妯″瀷淇ソ,鑰岀湡姝ｇ殑鎷﹁矾铏庢槸 **FML 鐨?early window**

### skull 鐨勭梾鍥?OptiFine 鐨?`getChild` 鎸?id 鎵惧瓙鑺傜偣,鑰?id 娌′汉璁?

    IllegalStateException: Failed to create model for minecraft:skull
      at SkullBlockRenderer.createSkullRenderers:66
    Caused by: NullPointerException: ... "this.head" is null
      at net.minecraft.client.model.dragon.DragonHeadModel.<init>:21

璇诲瓧鑺傜爜:`DragonHeadModel` 鐨勬瀯閫犲嚱鏁版槸 `this.head = root.getChild("head"); this.jaw = this.head.getChild("jaw")`,
鑰?**OptiFine 閭ｇ増 `ModelPart.getChild(String)` 涓嶆槸 `children.get(name)`** 鈥斺€?瀹冮亶鍘?`children.keySet()`,
鐢?`name.equals(child.getId())` 鍖归厤!`getId()` 璇荤殑鏄?OptiFine 鏂板姞鐨勪竴涓?`id` 瀛楁,鍙湁**瀹冭嚜宸遍偅濂楃儤鐒?
浠ｇ爜**鎵嶄細 `setId`;鑰?*OptiFine 鏍规湰娌℃湁 patch `PartDefinition`**(鏌ヨ繃,`patch/srg` 閲屾病鏈夊畠)銆?
浜庢槸鍦ㄨ繖鏉＄嚎涓?杩愯鏃剁儤鐨勬ā鍨?鈫?娌′汉璁?id 鈫?姣忔 `getChild` 閮借繑鍥?null 鈫?绗竴涓鍙栧瓙妯″瀷鐨?
`minecraft:skull` 灏辩偢浜嗐€侳orge 涓婅繖瀵规槸閰嶅鐨?杩欓噷涓嶆槸銆?

淇硶鏄妸"**淇濈暀娓告垙鑷繁鐨勫疄鐜?*"鍋氭垚鏈哄埗(涓?stub 鍒楄〃姝ｅソ浜掍负闀滃儚):`keep-runtime.txt` 鍒楀嚭
`owner/name/desc`,loader 鍦ㄦ崲瑁呭悗鎶婅繖浜涙垚鍛樼殑韬綋鎹㈠洖杩愯鏃堕偅鐗堛€?

    3c/5  keep-runtime: 1 members keep the game's body
    load  Members keeping the game's body: 1
    鈫?'Caught error': 0(璧勬簮鍖呬笉鍐嶈绉婚櫎,skull 寤哄緱鍑烘潵)

### 鐒跺悗鐪熸鐨勬嫤璺檸娴嚭鏉?FML 鐨?early display 閲嶅叆

妯″瀷涓€淇ソ,娓告垙灏辫蛋鍒版洿鍚庨潰,鎾炰笂:

    IllegalStateException: Already building.
      at fml_earlydisplay/SimpleBufferBuilder.begin
      at ...DisplayWindow.paintFramebuffer 鈫?DisplayWindow.render
      at neoforge/NeoForgeLoadingOverlay.render(NeoForgeLoadingOverlay.java:84)

**杩欎竴涓插抚閲屾病鏈変竴琛?OptiFine 浠ｇ爜** 鈥斺€?鍏ㄦ槸 FML/NeoForge 鑷繁鐨勩€傛煡 rig 鐨勫惎鍔ㄨ剼鏈彂鐜板畠**鏃╁氨鎯冲叧鎺?
杩欎釜绐楀彛**,浣嗗叧閿欎簡寮€鍏?

    -Dfml.earlyprogresswindow=false        鈫?杩欐槸 Forge 鏃朵唬鐨勫睘鎬?FML 2.0.17 涓嶈

FML 2.0.17 鎶婂畠鍋氭垚浜?*閰嶇疆鏂囦欢**:`config/fml.toml` 閲岀殑 `earlyWindowProvider`(榛樿 `"fmlearlywindow"`)銆?
鏀规垚 `"none"` 涔嬪悗:

    VERDICT: STARTED (40s, marker: Sound engine started)     鈫?鎴浘杩欐 1.2 MB
    swapped: 313 涓被   'Caught error': 0   宕╂簝鎶ュ憡:鏃?姣?22:10 鏇存櫄鐨勪竴浠介兘娌℃湁)
    Sound engine started 1 / Setting user 1 / Created: 13 / Shaders 14 琛?/ Connected textures 3 琛?
    early-display 甯?0

涔熷氨鏄 1.20.4 鐜板湪**杩涘埌鏍囬鐢婚潰銆丱ptiFine 鐨勭潃鑹插櫒涓庤繛鎺ユ潗璐ㄥ湪璺戙€佹病鏈変换浣曞穿婧?*銆傝繖涔熻В閲婁簡鍓嶉潰鍑犺疆
閭ｄ簺"浣嶇疆涓嶅畾鐨?overlay 宕╂簝":閭ｆ墖绐椾竴鐩存槸寮€鐫€鐨?鍙槸姣忔鎾炰笂鍘荤殑鏃舵満涓嶅悓銆?

**杩欐潯绾跨殑杩愯瑕佹眰鍐欒繘 `docs/MATRIX.md`**:1.20.2/1.20.4 闇€瑕?`config/fml.toml` 閲?
`earlyWindowProvider = "none"`(FML 鐨?early window 涓?OptiFine 鎹㈣鐨勬覆鏌撶被鍦ㄥ悓涓€甯ч噷浜掔浉閲嶅叆)銆?
杩欎笉鏄垜浠兘鍦?mod 閲岃鐨?鈥斺€?FMLConfig 鍦?mod 涔嬪墠灏辫瀹屼簡銆?

**椤哄甫涓€涓弽澶嶈俯鍒扮殑鍧?*:PowerShell 鐨?`Set-Content -Encoding UTF8` 浼氬啓 BOM,鑰?TOML 瑙ｆ瀽鍣ㄧ洿鎺ユ姤
`Invalid bare key: \ufeffEarly`銆傛敼閰嶇疆/婧愮爜涓€寰嬬敤 `[System.IO.File]::WriteAllText(..., UTF8Encoding($false))`銆?
## 2026-09-19(閲嶅缓鍚庣殑 rig):26.1.2 鍦ㄦ湰鏈虹涓€娆″璺?鍋滃湪鍝竴姝?

鏈妭鐨勬暟瀛楅兘鏉ヨ嚜鏈疆鍦?*閲嶆柊鎼捣鏉ョ殑** rig(`optifineoforge-test`)涓婄湡璺戠殑涓ゆ;GitHub 涓?README 閲?
"3478 琛?`[OptiFine]`銆乣processClass` 795 娆?閭ｆ璁板綍鏉ヨ嚜鏇存棭鐨勪細璇?鏈疆娌℃湁澶嶇幇鍒伴偅涓€姝ャ€?

**宸茬粡閲忓埌鐨?*

* rig 瑁呬笂 Minecraft 26.1.2 + NeoForge `26.1.2.109`(FML **11.0.15**銆丣ava **25**),
  鍘熺増瀹㈡埛绔?38,113,927 瀛楄妭;杩欎竴鐗堢殑 profile 涓荤被浠嶆槸 `net.neoforged.fml.startup.Client`,
  鎵€浠?1.21.x 閭ｅ FML 10 鍚姩鍣ㄥ彲浠ョ洿鎺ョ敤(缁欏畠鍔犱簡 `-JavaExe`,杩欎竴绾胯 JDK 25)銆?
* **鍩虹嚎**(mods 閲屽彧鏈変慨杩囧厓鏁版嵁鐨?OptiFine):`VERDICT: STARTED` + `Setting user` + `Sound engine started`
  + 鏃犲穿婧冩姤鍛?+ stderr 107 瀛楄妭 鈥斺€?涓?涓嶅甫浠讳綍 mod"鐨勫鐓ц窇鐩稿悓銆?*瀹炰緥鏈韩鍙敤**銆?
* 鍏冩暟鎹?OptiFine 鐨?`META-INF/mods.toml` 蹇呴』鎹㈡垚 `neoforge.mods.toml`(loaderVersion `[1,)`);
  **`optifine/Patcher`銆乣optifine/xdelta/**`銆乣optifine/json/**` 涓嶈兘鍒?* 鈥斺€?鍒犱簡浠ュ悗
  `OptiFineBaseTransformer.<init>` 鎶?`NoClassDefFoundError: optifine/Patcher`,澶勭悊鍣ㄥ疄渚嬪寲澶辫触
  (`ServiceConfigurationError: Provider optifine.OptiFineClassProcessor could not be instantiated`),
  OptiFine 瀹屽叏涓嶇敓鏁堛€?
* 澶勭悊鍣ㄩ渶瑕?鍩哄簳绫?:涓嶆妸鎴愬搧绫绘斁杩涘畠鐨?jar 鏃?瀹冨姣忎釜鐩爣鎶?
  `java.io.IOException: Base resource not found: net/minecraft/鈥(涓€娆¤窇 866,068 瀛楄妭 stderr)銆?
  鎶婃湰浠撳簱绂荤嚎娴佹按绾夸骇鍑虹殑 `srg/**` 鎴愬搧绫?566 涓?net/minecraft 486 鈥斺€?涓?`docs/DEVELOPMENT.md`
  璁扮殑 566 / 486 涓€鑷?骞惰繘 OptiFine 鐨?jar 涔嬪悗,澶勭悊鍣ㄥ紑濮嬬湡鐨勫畨瑁呭畠浠?`[OptiFine]` 寮€濮嬫墦鍗般€?

**涓€鏉℃柊鐨勫姞杞藉櫒闄烽槺(涓?1.21.9 閭ｈ疆鍚屾簮)**

`optifine-own-classes.jar`(OptiFine 鑷繁鐨勭被 + Forge shim)琚?FML 褰撴垚**鏃╂湡鏈嶅姟缃?*:

```
Found 4 early service jars (out of 103)
Loading FML Early Services:
 - 鈥?loader-11.0.15.jar
 - 鈥?earlydisplay-11.0.15.jar
 - mods/optifine-26.1.2-neoforge.jar
 - mods/optifine-own-classes.jar          <- 涓嶈鍦ㄨ繖閲?
```

鍥犱负娴佹按绾挎媶鍑虹殑 classpath jar **鍘熸牱淇濈暀 `META-INF/services/**`**,鑰?OptiFine 鐨勪袱涓?NeoForge SPI
鏈嶅姟鏂囦欢姝ｅ湪鍏朵腑銆傛棭鏈熸湇鍔＄殑绫荤敱涓€涓?*鐪嬩笉瑙佹父鎴忕被**鐨勫姞杞藉櫒鍔犺浇,浜庢槸
`net.optifine.reflect.Reflector.<clinit>` 鎶?`NoClassDefFoundError: net.minecraft.world.level.chunk.ChunkAccess`,
鍒氭墦琛ヤ竵鐨?`net.minecraft.util.Mth.<clinit>` 鎶?`net/optifine/util/MathUtils` 鎵句笉鍒般€?
浠庣浜屼釜缃愬瓙閲屽墧闄よ繖涓や釜鏈嶅姟鏂囦欢鍚?`[OptiFine]` 寮€濮嬫墦鍗?5 琛?,stderr 鍥炲埌 107 瀛楄妭銆?

**鍗″湪鍝?鎹㈢埗绫?*

```
net.neoforged.fml.ModLoadingException: Loading errors encountered:
	- NeoForge (neoforge) has failed to load correctly
	  java.lang.VerifyError: Bad type on operand stack
	    Location: net/neoforged/neoforge/attachment/AttachmentSync.onChunkSent(...)V @82: invokestatic
	    Reason: Type 'net.minecraft.world.level.block.entity.BlockEntity' ... is not assignable to
	            'net/neoforged/neoforge/attachment/AttachmentHolder'
```

1.21.9 / 1.21.10 / 1.21.11 涓婅繖涓€姝ョ敱**鍔犺浇鏈?*璇?`reparent.txt` 淇?26.1.2 鐨勬寕杞界偣鏄?OptiFine 鑷繁鐨?
澶勭悊鍣?瀹冧笉鍋氳繖浠朵簨,鎵€浠?*鎹㈢埗绫?鍚瀯閫犲櫒 chain 閲嶅啓)銆佹垚鍛樺洖濉€乻him 閮藉繀椤诲湪绂荤嚎闃舵鍐欒繘
骞惰繘 OptiFine jar 鐨?`srg/**` 鎴愬搧绫?*銆傝繖灏辨槸鏈」鐩湪杩欎竴绾夸笂鐨勯偅浠藉伐浣?鏈疆杩樻病鏈夊仛,
鎵€浠?26.1.2 **娌℃湁閫氳繃楠屾敹**銆?
## 2026-09-19(閲嶅缓鍚庣殑 rig):26.1.2 鍦ㄨ繖涓€鐗堜笂璺戦€?閰嶆柟涓庡疄娴嬫暟瀛?

涓婁竴娆¤褰曟槸鏇存棭浼氳瘽閲岀殑 3478 琛?/ `processClass` 795 娆°€傝繖涓€杞湪**閲嶆柊鎼捣鏉ョ殑** rig 涓婁粠澶磋閰嶄簡涓€閬?
**鍥涢」鍒ゆ嵁鍏ㄩ儴澶嶇幇**,骞朵笖 `processClass` 娆℃暟涓庨偅娆¤褰?*瀹屽叏涓€鑷?*:

```
===== VERDICT: STARTED (started: Setting user + Sound engine started)
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 107      <- 涓?涓嶅甫浠讳綍 mod"鐨勫鐓ц窇鍚屼竴琛屻€佸悓闀垮害
  [OptiFine] lines    : 352
  processClass 琛屾暟    : 795     <- 涓庢洿鏃╃殑璁板綍鐩稿悓
  handlesClass 琛屾暟    : 7369
  OpenGL API ERROR    : 0 琛?
```

**濡傚疄鍐欎笅鐨勪袱鐐瑰樊寮?*:鈶?`[OptiFine]` 琛屾暟杩欎竴娆℃槸 **352**,涓嶆槸璁板綍閲岀殑 3478 鈥斺€?鎴戞病鏈夎В閲婅繖涓樊鍒?
(娌℃湁閭ｄ唤鏃ф棩蹇楀彲姣?;`processClass` 涓?`handlesClass` 鐨勬鏁板垯鏄寰椾笂鐨勩€?
鈶?瀵艰埅鏍忛噷鐨?`[OptiFine] OptiFine_26.1.2_...` 鐗堟湰妯箙杩欎竴娆?*娌℃湁**鍑虹幇(1.21.9/10/11 閭ｄ笁鏉＄嚎閮芥墦浜?,
浣嗗鐞嗗櫒鐨?`processClass` 鏄庣‘鍦ㄨ窇(瑙佷笅)銆俿tderr 鐨?107 瀛楄妭鏄?log4j 鐨?
`Advanced terminal features are not available in this environment` 涓€琛?**鏈ā缁勮嚜宸卞啓 0 瀛楄妭**;
瀵圭収璺戝悓鏍峰彧鏈夎繖涓€琛?鍙︿竴娆″鐓ч噺鍒?104,宸埆鍙槸鏃堕棿鎴冲熬鏁扮殑浣嶆暟)銆?

### 杩欎竴杞閰嶅嚭鏉ョ殑閰嶆柟(姣忎竴姝ラ兘鏄噺鍑烘潵鐨?

1. **NeoForge `26.1.2.109`** 鐢ㄥ畨瑁呭櫒瑁?Java **25**,FML **11.0.15**)銆傛敞鎰忚繖鍙版満鍣ㄤ笂
   `maven.neoforged.net` 鐨?**IPv4 杩炰笉涓娿€両Pv6 鑳借繛**(`curl -6` 鎷垮埌 200),瀹夎鍣ㄨ嚜宸辩殑涓嬭浇鍣ㄧ敤 IPv4
   鏃朵細鎶?`failed 1`銆?
2. **OptiFine `preview_OptiFine_26.1.2_HD_U_K1_pre2.jar`**(7,797,229 瀛楄妭,涓?`docs/DEVELOPMENT.md`
   璁扮殑瀛楄妭鏁颁竴鑷?浠庣涓夋柟闀滃儚鍙栥€?
3. **鍏冩暟鎹?*:`META-INF/mods.toml`(Forge 鏃朵唬,`loaderVersion="[14,)"`)鎹㈡垚 `neoforge.mods.toml`
   (`loaderVersion="[1,)"`);`META-INF/services/cpw.mods.modlauncher.api.ITransformationService` 鍘绘帀
   (杩欎竴鐗堟病鏈?ModLauncher)銆?*`optifine/Patcher`銆乣optifine/xdelta/**`銆乣optifine/json/**` 蹇呴』淇濈暀** 鈥斺€?
   鍒犳帀瀹冧滑,`OptiFineBaseTransformer.<init>` 绔嬪埢 `NoClassDefFoundError: optifine/Patcher`,
   澶勭悊鍣ㄥ疄渚嬪寲澶辫触,OptiFine 瀹屽叏涓嶇敓鏁堛€?
4. **绂荤嚎娴佹按绾?*(鍏ㄩ儴鏉ヨ嚜鏈粨搴?`src/main/java/kynarain/cn/optifineoforge/optifine/`):
   `OptifinePipeline` 鈫?566 涓垚鍝佺被(net/minecraft 486);
   `HierarchyPlan` 鈫?`reparent.txt`(1 涓被:`BlockEntity` 鍒?`AttachmentHolder`,鏋勯€犲櫒 `()V`);
   `ReparentPayload` 鈫?鎹㈢埗绫?+ 鏋勯€犲櫒 chain 閲嶅啓 + 淇濈暀杩愯鏃剁殑鎺ュ彛(宸ュ叿鑷繁鐨勬棩蹇楅€愭潯鍒楀嚭);
   `MemberRestorePlan` 鈫?224 鏉¤鍒?/ 73 涓?donor;`RestoreMembers` 鈫?**256 涓垚鍛?/ 72 涓被**銆?
5. **鎶婅繖浜涙垚鍝佸苟杩?OptiFine 鐨?jar**:`srg/**`(鎴愬搧绫?**鍜?`assets/**`**(OptiFine 鎵撹繃鐨勮祫婧?銆?
   涓よ€呯己涓€涓嶅彲,鑰屼笖閮芥槸閲忓嚭鏉ョ殑:
   * 鍙苟绫讳笉骞惰祫婧?鈫?姣忎釜璧勬簮閮借璧?`optifine.Patcher.applyPatch`,浜庢槸
     `java.io.IOException: Base resource not found: assets/minecraft/models/block/template_glass_pane_*.json`
     鍒?13,367 瀛楄妭 stderr(鍒ゆ嵁瑕佹眰 stderr 涓庡鐓т竴鑷?鎵€浠ラ偅涓€娆′笉绠楅€氳繃);骞朵笂 `assets/**` 涔嬪悗鍥炲埌 107 瀛楄妭銆?
   * 涓嶅苟浠讳綍涓滆タ 鈫?姣忎釜绫婚兘鎶?`Base resource not found: net/minecraft/鈥(涓€娆¤窇 866,068 瀛楄妭 stderr),
     OptiFine 鍏ㄧ▼涓嶇敓鏁?0 琛?`[OptiFine]`)銆?
6. **绗簩涓?mod 缃愬瓙 `optifine-own-classes.jar`**(OptiFine 鑷繁鐨勭被 + Forge shim),骞朵笖**蹇呴』鍓旈櫎
   `META-INF/services/net.neoforged.**`** 鈥斺€?閭ｄ袱涓湇鍔℃枃浠朵竴鍦?FML 灏辨妸杩欎釜缃愬瓙褰?*鏃╂湡鏈嶅姟缃?*,
   鍏朵腑鐨勭被鐢变竴涓湅涓嶈娓告垙绫荤殑鍔犺浇鍣ㄥ姞杞?浜庢槸 `net.optifine.reflect.Reflector.<clinit>` 鎶?
   `NoClassDefFoundError: net.minecraft.world.level.chunk.ChunkAccess`銆佸垰鎵撹ˉ涓佺殑
   `net.minecraft.util.Mth.<clinit>` 鎶?`net/optifine/util/MathUtils` 鎵句笉鍒般€?
   (`OptifinePipeline` 鎷嗗嚭鐨?classpath jar 鍘熸牱淇濈暀 `META-INF/services/**`,鎵€浠ヨ繖涓€姝ユ槸蹇呴』鐨勩€?
7. **鍚姩**:杩欎竴鐗?profile 鐨勪富绫讳粛鏄?`net.neoforged.fml.startup.Client`,鎵€浠?1.21.x 閭ｅ
   `launch-fml10.ps1` 鐩存帴鐢?缁欏畠鍔犱簡 `-JavaExe`,杩欎竴绾胯 JDK 25;鍏ュ彛鐐圭敤鍏ㄥ弽灏勭殑
   `DiagnosticClientAny`,鍥犱负瀹樻柟鍏ュ彛鐐规妸鍚姩鏈熷紓甯镐氦缁欎竴涓粈涔堥兘涓嶆墦鍗扮殑妯℃€佸璇濇)銆?
   mods 閲屾斁鐨勫氨鏄笂闈㈤偅涓や釜缃愬瓙 鈥斺€?**鏈」鐩殑鍔犺浇鍣ㄤ笉鍦ㄥ叾涓?*,鎸傝浇鐐规槸 OptiFine 鑷甫鐨?
   `OptiFineClassProcessor`銆?
## 2026-09-20:鍏夊奖鍖呭疄娴?鍏釜鍖?鍏ㄩ儴閫氳繃)

鐢ㄦ埛瑕佹眰"鑷繁涓嬭浇瀹夎澶氫釜鍏夊奖鍖呮祴璇?銆傚仛娉曚笌缁撹濡備笅,鍏ㄩ儴鏄湰鏈虹湡鏈鸿窇鍑烘潵鐨勩€?

### 鎬庝箞瑁呫€佹€庝箞閫?

鍏夊奖鍖呬粠 **Modrinth API**(`api.modrinth.com`,杩欏彴鏈哄櫒 IPv4/IPv6 閮介€?鍙?
鎸?`versions:26.1.2` 杩囨护鍚庡彇鍚勯」鐩渶鏂扮増,鏀捐繘 `<娓告垙鐩綍>\shaderpacks\`銆傞€変腑鍝釜鍖呯敱
**`<娓告垙鐩綍>\optionsshaders.txt`** 鍐冲畾鈥斺€旇繖鏄粠 OptiFine 鑷繁鐨勭被閲岃鍑烘潵鐨?涓嶆槸鐚滅殑:
`net/optifine/shaders/Shaders` 閲屾湁 `optionsshaders.txt` 甯搁噺銆佷互
`new File(Minecraft.getInstance().gameDirectory, "optionsshaders.txt")` 鏋勯€?
閿悕鏉ヨ嚜 `net/optifine/shaders/config/EnumShaderOption.SHADER_PACK`(`getPropertyKey()` =
**`shaderPack`**,榛樿鍊肩┖涓?,鍊煎氨鏄?`shaderpacks\` 閲岀殑鏉＄洰鍚?鍚?`.zip`)銆?
rig 渚ц剼鏈?`test-shaderpack.ps1` 鍋?瑁呭寘 鈫?鍐欓厤缃?鈫?鍚姩 鈫?浠庢棩蹇楀彇鏁?杩欎竴鏁存潯,骞堕€愬寘鎵撳嵃鍒ゆ嵁銆?

### 26.1.2 涓婂叚涓寘鐨勭粨鏋?鍧囦负鐪熸満涓€娆¤繍琛?

| 鍏夊奖鍖?| 鐗堟湰 | 鍒ゆ嵁(鍥涢」) | 鍖呮槸鍚﹀姞杞?| `[OptiFine]` 琛?| `[Shaders]` 琛?| GLSL 缂栬瘧閿欒 | GL 閿欒 |
|---|---|---|---|---|---|---|---|
| MakeUp-UltraFast | 9.5e | 鍏ㄤ腑 | 鏄?| 3436 | 80 | 0 | 0 |
| Complementary Reimagined | r5.9.3 | 鍏ㄤ腑 | 鏄?| 569 | 106 | 0 | 0 |
| BSL Shaders | v10.1.5 | 鍏ㄤ腑 | 鏄?| 388 | 72 | 0 | 0 |
| Photon | v1.3b | 鍏ㄤ腑 | 鏄?| 1194 | 185 | 0 | 0 |
| Rethinking Voxels | r0.1-beta9 | 鍏ㄤ腑 | 鏄?| 566 | 98 | 0 | 0 |
| Solas Shader | V3.7b | 鍏ㄤ腑 | 鏄?| 3386 | 84 | 0 | 0 |

"鍒ゆ嵁鍏ㄤ腑"= `VERDICT: STARTED` + `Setting user` + `Sound engine started` + **鏈杩愯鏃犲穿婧冩姤鍛?*;
鍏鐨?stderr 閮芥槸 **107 瀛楄妭**,鍗充笌"涓嶅甫浠讳綍 mod"鐨勫鐓ц窇鍚屼竴琛?log4j 鐜鍛婅銆?
"鍖呮槸鍚﹀姞杞?鍙栬嚜鏃ュ織閲?OptiFine 鑷繁鐨勯偅琛?`[Shaders] Loaded shaderpack: <鍖呭悕>`銆?

**椤哄甫鎶婁竴涓箣鍓?娌℃湁瑙ｉ噴"鐨勬暟瀛楄В閲婃帀浜?*:README 閲?26.1.2 鐨勬棫璁板綍鍐欑殑鏄?`[OptiFine]` **3478 琛?*,
鑰屾垜鍦ㄦ棤鍏夊奖鍖呮椂閲忓埌 352 琛屻€佸綋鏃惰涓?宸埆娌℃湁瑙ｉ噴"銆傝涓婂厜褰卞寘鍚庤繖涓€绾块噺鍒?**3436 琛?* 鈥斺€?
3478 灞炰簬"寮€鐫€鍏夊奖鍖?鐨勯偅娆¤褰?352 灞炰簬"娌″紑"鐨勯偅娆°€備袱鑰呬笉鏄煕鐩?鏄袱绉嶆儏鍐点€?## 26.1.2 绂荤嚎琛ヤ笂绮掑瓙鎻愪緵鑰呮煡鎵?鏂板伐鍏?`ParticleProviderRepair`)

杩欐潯绾垮彂鐨勬槸**鎴愬搧杞借嵎**銆佽蛋 OptiFine 鑷繁鐨勭被澶勭悊鍣?娌℃湁鍔犺浇鏈熷彉鎹?鎵€浠?`ParticleEngine.makeParticle`
閭ｄ釜缂洪櫡鍙兘鍦ㄨ浇鑽?jar 閲岀绾挎敼銆傜己闄锋湰韬凡鍦?1.21.10 鑱旀満瀹炴祴鍒?杩涗笘鐣屽嚑绉掑悗涓嬮洦绮掑瓙涓€鐢熸垚瀹㈡埛绔氨姝?

```
NoSuchMethodError: 'Int2ObjectMap ParticleResources.getProviders()'
  at ParticleEngine.makeParticle(ParticleEngine.java:76)
  at ClientLevel.doAddParticle(...)   <- WeatherEffectRenderer.tickRainParticles
```

OptiFine 鐨勫壇鏈寜"娉ㄥ唽琛?id 绱㈠紩鐨?`Int2ObjectMap`"鍙栧€?鑰岃繍琛屾椂鐨?`ParticleResources` 鍙墿
`Map<ResourceLocation, ParticleProvider<?>> getProviders()`銆傚伐鍏锋妸瀹冩敼鎴愭寜绮掑瓙绫诲瀷鐨勮祫婧愬悕鍙栧€?

| 鍘?| 鏀逛负 |
|---|---|
| `ParticleResources.getProviders()Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;` | `ParticleResources.getProviders()Ljava/util/Map;` |
| `Registry.getId(Object)I` | `Registry.getKey(Object)Lnet/minecraft/resources/Identifier;` |
| `Int2ObjectMap.get(I)Object` | `java/util/Map.get(Object)Object` |

鍙栧埌鐨勬槸鍚屼竴涓?provider(娉ㄥ唽琛?id 涓庤祫婧愬悕鎸囧悜鍚屼竴鏉?,鎵€浠?OptiFine 鐨勮嚜瀹氫箟绮掑瓙棰滆壊/璐村浘浠嶅湪閾捐矾涓?鈥斺€?杩欎篃鏄负浠€涔堜笉鏄洿鎺ヤ涪寮冭繖涓被銆佹敼鐢ㄨ繍琛屾椂鐨勫壇鏈€傛敞鎰?26.x 鐨勮繑鍥炵被鍨嬫槸 `Identifier`(璇ョ増鏈凡鎶?`ResourceLocation` 鏀瑰悕),姝ゅ蹇呴』鎸夋湰鐗堢湡瀹炵鍚嶅啓,涓嶈兘鐓ф妱鍒嚎鐨勬弿杩扮銆?
**褰㈢姸**鐓?`SpriteCollectionRepair`(鍚屼负绂荤嚎鍙岃優鑳?:`main(<payload jar> [--dry-run])`,`ZipFile` 璇汇€?鍙敼鍐?`srg/net/minecraft/client/particle/ParticleEngine.class` 涓€涓潯鐩€佸叾浣欏師鏍峰啓鍥炰复鏃舵枃浠跺啀
`REPLACE_EXISTING`;杞借嵎閲屾病鏈夎绫汇€佹垨娌℃湁璇ュ舰鐘剁殑鏂规硶鏃?**鏄庣‘鎵撳埌 stdout 骞跺師鏍峰啓鍥?*,涓嶉潤榛樿烦杩囥€?
**宸叉牳瀹?*(鐖朵細璇濈嫭绔嬪鏍?涓嶅彧鐪嬪伐鍏疯嚜璇?:

- 涓庡浠介€愭潯鐩姣?涓よ竟閮?9536 鏉?鏃犲鏃犲垹,**鍙湁閭ｄ竴涓被鐨勫唴瀹逛笉鍚?*銆傚洜姝?jar 浣撶Н
  `10018170 -> 10170792` 鐨勫彉鍖栨槸閲嶆柊鍘嬬缉,涓嶆槸澶氬浜嗗唴瀹?鈥斺€?jar 浣撶Н涓嶈兘褰撴湁鏁堟€у垽鎹?閫愭潯鐩?diff 鎵嶆槸銆?- `javap` 璇ョ被鐨勪笁澶勮皟鐢ㄧ‘宸叉敼鍐?瑙佷笂琛?,涓旂敤鐨勬槸 26.x 鐪熷疄鐨?`Identifier`銆?
**寰呭姙(涓嶈褰撴垚宸插畬鎴?**:

- **杩愯鏈熼偅涓€鍗婅繕娌¤窇**:鐪熸満杩?26.1.2 涓栫晫銆佽闆ㄧ矑瀛愮敓鎴?杩欐潯缂洪櫡鎵嶇畻鍦ㄨ繖鏉＄嚎涓婇獙瀹屻€?- **蹇呴』鎶婅宸ュ叿鎺ヨ繘杩欐潯绾跨殑瑁呴厤姝ラ**:鐜板湪杩欐潯绾跨殑杞借嵎鏄墜宸ヨ閰嶇殑(瑙佷笂鏂囬厤鏂?,涓嶆妸杩欎竴姝ュ啓杩?  閰嶆柟,閲嶅缓鏃惰繖涓慨澶嶅氨浼氫涪銆傛帴绾垮悗瑕佸湪鏈枃浠堕噷琛ヤ笂瀹為檯璋冪敤鐨勫懡浠や笌瀹冪殑杈撳嚭銆?- 澶囦唤鐣欏湪 `jars-26.1.2\optifine-26.1.2-neoforge.jar.before-particle-fix`,渚夸簬閫愭潯鐩绠椼€?### 鍙戝竷鍓嶇殑涓€椤规牎楠?26.1.2 杞借嵎)

`ParticleProviderRepair` 鏄箓绛夌殑,鑰屼笖**涓嶉潤榛?*:杞借嵎宸茬粡淇ソ鏃?瀹冧細鏄庤"娌℃湁鍙慨鐨勫湴鏂?,骞舵姤鍑?`makeParticle` 褰撳墠鐨勫舰鐘躲€傚疄娴?rig 渚?`repair-26.1.2-payload.ps1 -DryRun`):

```
no method of ... ParticleEngine reads the particle provider through
ParticleResources.getProviders()/Registry.getId()/Int2ObjectMap.get(),
so nothing was repaired and the payload is left as it is
makeParticle calls ParticleResources.getProviders()Ljava/util/Map;
```

鎵€浠?*閲嶅缓杩欐潯绾跨殑杞借嵎涔嬪悗銆佸彂甯冧箣鍓?*,鍏堣窇涓€娆?`repair-26.1.2-payload.ps1 -DryRun`:

- 鏈熸湜鐪嬪埌 "nothing was repaired" + `makeParticle calls ...getProviders()Ljava/util/Map;`
  鈥斺€?杩欐墠璇存槑杩欐閲嶅缓鎶婁慨澶嶅甫涓婁簡;
- 濡傛灉瀹冨弽杩囨潵鎶?淇簡涓夊",璇存槑杩欐閲嶅缓**涓簡**淇,蹇呴』鍏堢湡璺戜竴娆?骞跺绠楅€愭潯鐩?diff 鍙湁
  `srg/net/minecraft/client/particle/ParticleEngine.class` 涓€涓潯鐩笉鍚?,鎵嶈兘缁х画鍙戝竷銆?
璇ヨ剼鏈湪鏀瑰啓鍚庝細鑷繁鍑鸿瘉鎹?涓?`.before-particle-fix` 鐨勯€愭潯鐩姣?鏉＄洰鏁颁竴鑷淬€佹棤澧炲垹銆佸彧鑳藉樊涓€涓?鏉＄洰,鍚﹀垯浠ラ潪闆堕€€鍑烘槑纭垽"涓嶆槸骞插噣鐨勪慨澶?),澶栧姞璇ョ被鐨?`javap`銆?*jar 浣撶Н涓嶆槸鍒ゆ嵁**:棣栨鏀瑰啓鏃?10018170 -> 10170792 瀛楄妭鐨勫彉鍖栫函绮规潵鑷噸鏂板帇缂┿€