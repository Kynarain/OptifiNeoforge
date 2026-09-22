# �������̱�(1.21.x ��)

## Ŀ��

�� OptiFine �� **NeoForge** �Ϲ���,������ OptiFabric �� Fabric Loader ��һ��:��ȥ����ʵ�� OptiFine,����

1. �� OptiFine �Դ��Ĳ����������Լ��Ĳ������ԭ��ͻ���;
2. �ؽ��������ﱻ���ߵ� lambda;
3. ��Ŀ��汾�������ռ�ѽ������;
4. �Ѵ�������� Minecraft �ཻ�� NeoForge ����ת������,�����Ƕ���ԭ���ࡣ

OptiFabric �� Fabric ���ߵ��� `GameTransformer.patchedClasses`��NeoForge ���Ӧ��λ�û�û������ȷ��,���Ǳ��ߵ�һ��Ҫ���������(����)��

����Ҫ���� **1.21��1.21.1��1.21.3��1.21.4��1.21.6��1.21.7��1.21.8��1.21.9��1.21.10��1.21.11** ʮ�� MC �汾��ʮ���汾����ʮ�� NeoForge ��,������̱���ÿһ����Ҫ��汾�о�,������һ���汾�Ľ�����ơ�

## ���ߵĶ������

| ���� | 1.21.x �ߵ���� |
|---|---|
| NeoForge ���� | ʮ�����ʮ����(`21.0` �C `21.11`);���� **`21.6`��`21.7`��`21.9` ֻ�� beta ����**(���� `21.6.20-beta` / `21.7.25-beta` / `21.9.16-beta`),������ֻ���� beta �� NeoForge �ϲ� |
| Java | ȫ�� **21**,CI һ�׼��� |
| mod Ԫ�����ļ� | Ԥ��ȫ���� `META-INF/neoforge.mods.toml`;�����ֶ�Ҫ��Ҫ��汾�˶� |
| �����������ռ� | Ԥ�����ǹٷ�(Mojang)��,������ SRG;**ȷ���л����ȷ��**(����Լ���� 1.20.5/1.21 ǰ��,������������ڸ���) |
| OptiFine ���� | 1.21.2 �� 1.21.5 ��ȫû�� OptiFine;1.21��1.21.6��1.21.7��1.21.8��1.21.9��1.21.10 ����ֻ�� preview |
| ����ȱ�� | **1.21.6 / 1.21.7 ���ù�Ӱ���ر�**,���� OptiFine Ԥ����������(ȱһ��ǰ�õ� `setParentTexture` ����),�߸�������Ϊһ�� |
| OptiFine ϵ�к� | `J6` ��� 1.21.6 �C 1.21.8,`J7` ��� 1.21.9 �C 1.21.10,ͬ��ϵ�еĹ����ܷ������δ��֤ |

## �� Fabric �ߵĹؼ�����

| ���� | OptiFabric(Fabric) | ����Ŀ(NeoForge) |
|---|---|---|
| ����ʱ�� | Fabric Loader �� `GameTransformer`,�� Mixin ֮ǰ | ModLauncher / NeoForge ��ת������,˳����Ҫ��ʵ |
| OptiFine ���� | ���ֽ��벹�� + �Լ�����,û�� loader ���� | **�Դ� Forge ʱ���� loader ����**(`optifine.OptiFineTransformationService`) |
| Ԫ���� | ���漰 | OptiFine �� jar ���� `META-INF/mods.toml`,NeoForge �����Լ��� `neoforge.mods.toml` |
| �����ռ� | official �� intermediary | ������Ԥ���ǹٷ�(Mojang)��,����Ҫ��ӳ��;��ĳ���汾���� SRG һ��,��Ҫ���汾��֧ |
| ���������� | ֻ�� Fabric API �� mixin | NeoForge �Լ�Ҳ���ԭ����,������Ҫ�ϲ� |
| NeoForge ���� | ���漰 | 1.21.6 / 1.21.7 / 1.21.9 ֻ�� beta ����,�ɲ��Ա������� |

## ������ѡ·��

**·�� A ���� �� OptiFine �Լ��� ModLauncher ������������**
OptiFine �� `optifine.OptiFineTransformationService` ֻ���� `cpw.mods.modlauncher.*`(`ITransformationService`��`ITransformer<ClassNode>`��`SecureJar`),������ `net.minecraftforge.*`��������ֻҪ�� NeoForge ���ֲ�����������񡢲�������Ԫ�����޺�,�������̾���ԭ��������������:˳��ͶƱ(`castVote`)���� NeoForge ��������ĺϲ��������������

**·�� B ���� �Լ��ܲ�����,�Լ�����������(�� OptiFabric)��**
�� `preLaunch` �׶ε��� `optifine.Patcher` �򲹶����ؽ� lambda�����������ռ�,Ȼ��ѽ������ NeoForge ��ת�� API���ɿ������,�����ǹ�������,����Ҫ��Ū�� NeoForge �ʲ��������ඥ�档**�����߱� 1.20.x ����һ��**:��������Ԥ�ھ��ǹٷ���,���� SRG ������һ�� ���� ǰ���������ռ�Ĵ�ȷ���Ԥ���䶨��

�Ǽܽ׶�����������:**������С������֤·�� A �ܲ��ܳ���**(����"�ܲ�����"������),ͬʱ��·�� B ����̬��֯����(���������á����桢fixer ��ܶ����� `core` ��,������������ص�)��

## ��̱�

| # | ���� | ����о� |
|---|---|---|
| M0 | �Ǽ�:������֧���汾���󡢹������á��ĵ� | ���ύ(�ĵ�����)�����Ĺ��������ύ |
| M1 | ·�� A ������:�޺� OptiFine jar ��Ԫ����,�� NeoForge ���� | **��汾**��������������,��־���ܿ��� OptiFine ��ת�����񱻼���;���� 1.21.1 �� 1.21.11 ����������ʽ�� OptiFine �İ汾 |
| M2 | ��������:���� `optifine.Patcher`,��������(`<��ϷĿ¼>/.optifine/<OptiFine �汾>/`) | �״������ɲ���,��������߻��� |
| M3 | ������ע�� + fixer ���:���ر����ߵķ����������� NeoForge ����������ص� | �����粻��,����/��Ʒ/������Ⱦ���� |
| M4 | ��������:��Ӱ��������ݡ���������;������ģ��(�������� NeoForge ��Ⱦ���ӵ�) | �� OptiFabric �� Fabric �ϵ����տھ�����;**1.21.6 / 1.21.7 �����տھ�Ҫ�ֿ�д**:������ֻ�ܰ�"�����ù�Ӱ��"�о�,��Ӱȱ������ OptiFine ��,�ȹ��� |
| M5 | ����:�汾�ű�������˵����CurseForge / Modrinth Ԫ���� | ��һ���������������,ʮ��������Դ��;ֻ�� beta NeoForge ������Ҫ�ڷ���˵����д�� |

ÿ���߰�ͬ������̱��ƽ�,�����Զ������ա����ߵ� M1 ����� 1.21.1 �� 1.21.11 ����:�������� OptiFine ����ʽ��,�Ȱѹ�����ͨ,����ֻ�� preview �İ汾���̡�

## ����ȱ�������Ĵ���߽�

1.21.6 �� 1.21.7 ���߸� OptiFine ���������ù�Ӱ��ʱ�ر�(��ָ��,`multiTex` Ϊ null,���� `net.optifine.shaders.ShadersTex.initDynamicTextureNS`),��������ЩԤ������ע��ĵ���**ȱ��ǰ�õ� `setParentTexture` ����**������ OptiFine �����������������,��������޹�,����:

- ����Ŀ**���������� NeoForge �����ʧ��**,M4 �������水"�����ù�Ӱ��"����;
- ����ĿҲ**����ŵ**ȥ����:��Ҫ��,�����ڱ���Ŀ���д OptiFine �Ĳ������(���� fixer ��ְ��Χ),Ҫ������ֵ��ֵ��;
- ��ʡ�µ�·���ǵ� OptiFine ���¹���;�¹�������ǰ,�������״̬���ĵ��ﱣ��"��֪ȱ��"������"��֧��"��

## �ύ����

- ��֧�������:`1.20.x`��`1.21.x`��`26.x` �������Լ��� `README`�����󡢹������ú��ĵ�,�������֧�ĺϲ�,Ҳ�����߸��ư汾�š�
- ����ҲҪ���汾����:ʮ���汾�Ľ��۲��ܻ��ඥ��,д�ĵ�ʱҪ˵������һ���汾;`J6` / `J7` ����ͬ��ϵ���������׻졣
- �ĵ���ʵ��ھ�Ҫһ��:û����ʵ��Ϸ����֤���Ķ���д�ɼƻ�,��д�ɽ��ۡ�
- OptiFine �� jar �����ֿ�,Ҳ�������ַ���

## 2026-09-19/20:1.21.9 �����ߵ������(����ʵ��)

��**�����ؽ��� rig** �����������µ�ȷ��,������"��������һ���Ѿ�����,ȱ������������":

1. **�������������Թ�**:�ڱ���֧��ִ��

   .\gradlew jar -Pmc=1.21.9 -Pneoforge=21.9.16-beta -Pmountpoint=fml10

   �õ� uild/libs/OptifiNeoforge-1.0.0+mc1.21.9.jar(129 356 �ֽ�)���� Ҳ���Ǳ��ļ������Ƕ�ע��˵��
   "loader-side tools and the mod skeleton alone",��Ԥ��һ�� ?��
2. **ȱ������**:
   a. **FML 10 �� ClassProcessor Ҫ������غ� jar**:26.x ��֧�� src/fml10 ֻ��������
      (OptifinePayloadClassProcessor / OptifinePayloadLocator),��ע������Ҫ�� rig **������غ� jar**
      (���� OptiFine ����),�����Ǳ���������� jar ���� rig Ŀǰû����һ����
   b. **1.21.9+ û�� ModLauncher**,�� rig �� launch.ps1 ��Χ�� ModLauncher д��(module path��-p��
      --launchTarget��ignoreList ��)���� Ҫ����������,�ø� rig ��һ�� FML 10 �����·����

�������¶�**û����**,���� 1.21.9 / 1.21.10 / 1.21.11 ��Ȼ��δʵ��ġ�
### ����(ͬһ�ֵ���һ��):FML 10 �� ClassProcessor ȷʵ�ܱ����(ʵ��)

- **SPI ������**:�� rig �� libraries ��ɨ 515 �� jar,**ֻ��һ��**����
  
et/neoforged/neoforgespi/transformation/ClassProcessor.class ����
  libraries\net\neoforged\fancymodloader\loader\10.0.14\loader-10.0.14.jar(�� NeoForge 21.9.16-beta װ������)��
- **�����䷽(ʵ��ͨ��)**:26.x ������Դ�� + ������� loader jar + libraries\net\neoforged\neoforgespi\**
  + log4j-api + rig �� ASM jar ? **2 �� class / 7470 �ֽڵ� jar** ?��Ҳ����˵"�� ClassProcessor �����"��һ��
  û���κ�δ֪����
- **��Ȼȱ��**:�� �����ļ������ע��,��������Ҫ�� OptiFine ����һ��**����غ� jar**(rig û����һ��);
  �� **1.21.9+ �����·��**(û�� ModLauncher,launch.ps1 ���� module path / --launchTarget ��������)��
  ������������Ψһ�Ĵ��,�������̸"ʵ��"��
### �ٲ���:FML 10 �����·�����(ʵ�⵽����,��δ��ͨ)

װ�� NeoForge **21.9.16-beta** ֮�������Լ��� profile,����:

- mainClass �� **
et.neoforged.fml.startup.Client**(���� cpw.mods.bootstraplauncher.BootstrapLauncher),
  **û�� ModLauncher**,Ҳ**û��** --launchTarget / module path ���� ֻ������ --fml.* ����
  (
eoForgeVersion / mcVersion / 
eoFormVersion),���ఴ�� profile(1.21.9.json)�ĳ�����Ϸ��������
- ������·���ֹ�ƴ classpath ����һ��**�� mod �Ķ���**:
  ����**�����������ų��� 90 ��**,Ҳд���� logs/latest.log ? ���� �� FML �Լ��ĺ�̨ɨ�豨
  java.lang.IllegalStateException: zip file closed(BackgroundScanHandler �� CompositeJarContents.visitContent
  �� Scanner.scan),��"ɨ��ĳ�� jar ʱ���Ѿ����ص�"��Ҳ����˵**�ֹ�ƴ�� classpath �������� FML 10 �ӹ���Щ jar**,
  �ٷ��������Ȼ�ǰ���һ�ַ�ʽ�� jar �������ġ�
- ˳������һ���پ���:�� profile ���� classpath ʱ�ῴ�� 30 ��"ȱʧ"�� jar,����**ȫ�Ǳ��ƽ̨�� natives**
  (linux / macos ?),rig �� etch-libraries.ps1 �����Ͱ�������������,Windows �ϲ���Ҫ��

**����**:1.21.9+ ��������Ҫ��ʵ��,����"�� FML 10 �����ķ�ʽ׼�������"����� ���� ����û��ͨ,
���� 1.21.9 / 1.21.10 / 1.21.11 ��Ȼ��**δʵ��**��
### FML 10 ���ĵ�����ʵ��:����ʵ�ߵú�Զ,Ȼ���� 0.8 �����Լ��ص�

�����������ȫ(--gameDir/--assetsDir/--assetIndex/--username ��,ֻ�� libraries �� classpath ��,���ٰ� client jar
����ȥ)֮��,FML 10 ����־��ʾ:

- Starting FancyModLoader version 10.0.14 (CLIENT in PROD) ?
- Loading ImmediateWindowProvider fmlearlywindow + **GL info: AMD Radeon RX 7800 XT GL version 3.3.0 Core**
  ���� Ҳ����˵**����Ŀ������ڴ��ڲ��õ��� GL ��Ϣ** ?
- Mod List: Minecraft 1.21.9 (minecraft) / NeoForge 21.9.16-beta (neoforge) ?
- Building game content classloader: minecraft (composite(jar(client-1.21.9-...-srg.jar))) ... ?
- **�����һ�о��� Closing FML Loader** ���� ��������ر�ֻ��Լ **0.8 ��**,����֮ǰ**û���κ� ERROR**;
  ���� An error occurred scanning file ...client-1.21.9-...-srg.jar(zip file closed)��**�ر�֮��**�ų��ֵ�,
  Ҳ����**���������ԭ��**��

˳�����һ������ǰ�Ĳ²�:"client jar Ҳ�� classpath ��"���³�ͻ ���� ȥ��֮��(ֻ�� libraries)**������ȫһ��** ?��

**��һ��**(��¼������,���ڽ�����):�� -Xlog:exceptions=trace �����߳��Ǹ�"�����˳�"��ԭ��ץ����
(����ַ��ڱ��Ự���Ѿ��óɹ���һ��:1.20.4 ��ԭʼ�쳣������ô�ڳ�����),���߶��� NeoForge �ٷ������
�� profile ֮�⻹����ʲô��
### �ش�һ��:FML 10 �����·��**��ͨ��**(�� mod �����ѽ����������·��)

��һ�ڼǵ�"0.8 �밲���˳�"�ҵ�ԭ���� ���� ��**��©�� profile ��� JVM ����**,���� FML �� OptiFine ������:

- �� profile(1.21.9.json)�� rguments.jvm �����ĸ� **natives �������**:
  -Djava.library.path��-Djna.tmpdir��-Dorg.lwjgl.system.SharedLibraryExtractPath��-Dio.netty.native.workdir
  (��ָ�� rig �� 
atives),���� -Xss1M �� -Dminecraft.launcher.brand/version;
- �� profile(NeoForge)����� --add-opens java.base/java.lang.invoke=ALL-UNNAMED ��
  --add-exports jdk.naming.dns/com.sun.jndi.dns=java.naming��

����Щ**ȫ��**����֮��(����ͬǰһ��:ֻ�� profile �� libraries �Ž� -cp��-DlibraryDirectory ָ�� rig ��
libraries��mainClass �� 
et.neoforged.fml.startup.Client����Ϸ�����ø� profile ����),
**�� mod ������������**:

`
[Render thread/INFO] [net.minecraft.client.Minecraft/]: Setting user: Dev
[Render thread/INFO] [net.minecraft.client.sounds.SoundEngine/SOUNDS]: Sound engine started
`

���̻���(70 ���������������)����־ 12 010 �ֽ� ?��Ҳ����˵ **1.21.9 �����ߵ������ʽ������δ֪����** ?
���� ���� 1.21.9 / 1.21.10 / 1.21.11 �����ߴ�ǰ������·ʯ��

**ע��߽�**:����**�� mod ����**(û�� OptiFine��û�б���Ŀ���غ�),�������Ȳ�����"��������ͨ��",Ҳ���ı�
��������"δʵ��"��״̬���������������:�� �� 26.x �� src/fml10(������,��ʵ��ɱ���)��ע��**����غ� jar**;
�� �� rig ����������ײ����̻���һ�� **FML 10 �����·��**(launch.ps1 Ŀǰֻ�� ModLauncher)��
### rig �������� FML 10 �����·��(ʵ��:�� mod ���� VERDICT: STARTED)

���� optifineoforge-test\launch-fml10.ps1(ModLauncher �������� launch.ps1)�������ļ���:

1. ˳�� inheritsFrom �� profile ��������(NeoForge profile �� ԭ�� profile),��"�����Ӻ󡢺��߸���"�ϲ� libraries,
   ������ƴ -cp ����**���� client jar �Ž�ȥ**(ʵ��:�Ž�ȥ�������һ��,FML �Լ����� -DlibraryDirectory
   �� --fml.neoFormVersion �ҵ���Ϸ jar);
2. չ������ rguments.jvm �� rguments.game ���ռλ��,����**���� rule ��Ŀ**
   (ʵ��:��һ���汾�հ��� -XstartOnFirstThread ���� macOS ר�ù���,JVM ֱ�Ӿܾ����:
   Unrecognized option: -XstartOnFirstThread);
3. �� 
et.neoforged.fml.startup.Client + ������Ϸ�������,֧�� -Mods(���� mods/)��-Fresh��-Seconds;
4. ��ӡ�� launch.ps1 **ͬ����״**�� VERDICT ��(Setting user / �������� / �±������� / stderr �ֽ� /
   [OptiFine] ����),��������·���Ľ��ֱ�Ӷ��ա�

ʵ��(1.21.9 / NeoForge 21.9.16-beta,�� mod):

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 0  (latest.log, not doubled)
`

Ҳ����˵ **1.21.9+ ��"���"��һ��������**�������߻�������һ�����ǰ� 26.x �� src/fml10(��ʵ��ɱ���)
�� OptiFine ����һ��**���� FML 10 �ᷢ�ֵ��Ǹ��غ� jar**,Ȼ��������ű�ȥ�ܡ�
### �غ� jar ����Ҫװʲô(�� src/fml10 �������˵�����������,������)

��һ��"��� fml10 �غ�"���䷽,���ڲ��ǲµ��� ���� �������Լ����ĵ�д���� FML 10 �ķ��ֻ���:

- OptifinePayloadClassProcessor **ͨ�� META-INF/services/net.neoforged.neoforgespi.transformation.ClassProcessor
  ע��**(FMLLoader.createClassProcessorSet �� ServiceLoaderUtil.loadServices),���������ǰ�**�Ѿ���ò�������Ϸ��
  ���ǵ���Ϸ�Լ��Ƿ���**;����Щ��Ʒ�ఴԼ������**ͬһ�� jar �� srg/ ��**(rig ���Ȱ� patch/srg/** Ӧ�õ�ԭ��鵵
  �Ľ��,Ҳ���Ǳ��ֿ����߹��� optifine-patched.jar �Ĳ���)��
- OptifinePayloadLocator ֮���Դ���,����Ϊ
  
et.neoforged.fml.loading.EarlyServiceDiscovery.SERVICES ����ֻ��
  {IModFileCandidateLocator, IModFileReader, IDependencyLocator, GraphicsBootstrapper, ImmediateWindowProvider} ����
  **ClassProcessor ��������**������"ֻ���� ClassProcessor �� mods/ jar"���ᱻԤ���ء���������Զ���ᱻ����;
  �Ƕ�ע�ͻ�����ʵ������:���� jar �ܵ��˱������,ȴ**���� [OptiFine]����־������������û��**��
  ������ jar **��Ҫ**����һ�� IModFileCandidateLocator(�����ļ�
  META-INF/services/net.neoforged.neoforgespi.locating.IModFileCandidateLocator),����Ҫ���Լ���Ԫ����
  ����ͨ�� mods/ ɨ�跢�֡�

**���� 1.21.9 ���غ� jar = ���߹��߲����� srg/** ��Ʒ�� + ������ fml10 �� + ���������ļ� + META-INF/neoforge.mods.toml��**
(����ֻ������䷽������;**��װ�ű���ûд**��)
### ��һ�������� 1.21.9 �غ�:����ȫ������ƹ���,��Ϸ��װ����֮�󰲾��˳�

�������ɲ�ʵ����������:

1. **�غ� jar ��װ�ɹ�**(����һ���Ƿ��䷽,��ʱ�ű�����):work\1.21.9\optifine-patched.jar ���
   **1233 �� srg/** ��Ʒ��** + ���� fml10 �� + ���������ļ� + META-INF/neoforge.mods.toml
   ? jars-1.21.9\optifine-payload-fml10.jar(**2 999 556 �ֽ�**)��
2. **FML 10 ȷʵ�������غɳ���ȥ��**(launch-fml10.ps1 ����־):
   - mods/optifine-payload-fml10.jar ������ ?
   - OptifiNeoforge: early service jar recognised; the payload jar itself is disco...(locator ������ ?)
   - OptifiNeoforge: OptifinePayloadClassProcessor constructed (FML 10 mount point) ?
   - OptiFine payload: 517 finished game classes ?,���һ����
     OptiFine payload: installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far] ��(�� 16 ��ʱ��־�ж�)
3. **Ȼ����Ȼ�ǰ����˳�**:Closing FML Loader �� Clearing ModLoader,**û�� ERROR��û���쳣��stderr 0 �ֽ�**,
   Setting user û���֡�ע��:**�� mod �Ķ��������ܵ� Setting user ��**(�� 56 ��),�����������װ��ȥ������,
   �����������ʽ��

**��һ��**(��ȷ):���������� -Xlog:exceptions=trace(���Ự�������������ڳ�"������"��ԭ��),�����߳���
"װ��ʮ����ʮ����"֮�󵽵�����ʲô ���� ������ֻ��**����������**���غ������֡�
### ���ֵĽ��:����"ĳ���໵",����"�غ�һ��װ�� FML �͹ص��Լ�"

- �� -Xlog:exceptions=trace ץ:�����غ��������־**���һ���쳣**�����޹ص� AWT/��������(2.8 �봦),
  **û���κ��쳣�����Ǵιر�** ���� �� mod �Ķ���Ҳ��ͬ������β,�����ܼ����ߵ� Setting user��
- ����������:���غ�����**ֻ��һ����**(srg/net/minecraft/util/Mth.class,jar �� 19 016 �ֽ�),
  OptiFine payload: 1 finished game classes �� installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far]
  �� **�������Ȼ�� Closing FML Loader**��
- Ҳ����˵:**����ĳ�������ϷŪ��**,����"�غ�һ����װ��,FML �Ͱ��Լ��ص�"��
  ��һ��Ҫ�����Ǵ������� FML 10 ��������һ��(�������ڰ�װʱ�Ƿ��� FML ����ɨ����Ǹ� jar ���� ֮ǰ���ֹ�
  zip file closed �ı�Թ,���ܾ���������������һ��),�����Ǽ�����С��ķ�Χ��
### ��λ��Ψһһ��:�� ClassProcessor �İ�װ����,���� jar / locator / Ԫ���� / �౾��

ͬһ���غ� jar,**ֻȥ������ ClassProcessor �ķ����ļ�**(���ࡪ��locator ����
eoforge.mods.toml���Ǹ���Ʒ�ࡪ��
��ȫһ��),�������ͱ��:

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  [OptiFine] lines    : 0
`

Ҳ����˵:jar ����û���⡢locator û���⡢Ԫ����û���⡢��Ҳû����,**Ψһ�� FML 10 �ر��Լ��ľ���"���������ȥװ��"��һ��**��
ʣ��Ҫ��ľ�ֻ�� OptifinePayloadClassProcessor �İ�װʵ���� FML 10 ɨ��/���� jar �ķ�ʽ֮����໥����
(���Լ�ȥ��ͬһ�� jar ���Ƕδ���������ɵĵط�,֮ǰ���� zip file closed Ҳָ������)��
### 1.21.9 ��һ���տ�(��һ����ʵ���Ѿ�ָ��)

�Ѿ�վס�Ĳ���:

- **�����ʽ** ?:launch-fml10.ps1(rig),�� mod ���� VERDICT: STARTED;
- **�غ�Ͷ��** ?:�غ� jar �� FML 10 ����,locator ����
eoforge.mods.toml��srg/** ��Ʒ�඼����ƹ���
  (OptiFine payload: 517 finished game classes �� ���� installed ...);
- **Ψһ����** ?:ֻҪ���� ClassProcessor �����ļ���,������һ��ʼװ��,FML 10 �� Closing FML Loader
  (���쳣��stderr 0;�ѷ����ļ�ȥ����һ�������� Setting user ���� ��һ���Ѿ��ѷ�Χ������)��

��һ�ָ�����ʵ��(����������):

1. **�ѷ����ļ�����,���� 	argets() ���ؿռ�**:����������"FML 10 ��ϲ��**�д�����ע��**"��
   "FML 10 ��ϲ��**���������װ��**"�������� ���� ���ļ��� 	argets() ����״��ֱ��(���� payload().keySet()
   �� Target),��һ�о�����;
2. ����һ��֤����"װ��"����,RU ���ɴ��� 	ransform ��ѳ�Ʒ ClassNode ���ǵ� 
ode �ϵ��Ƕ�
   (FML 10 ��ɨ���߳̿������ڶ�ͬһ�� jar),�������С;
3. ��һ�������ų��·:��ע�ᴦ����,����**���Ȱѳ�Ʒ�า�ǽ���Ϸ jar**(rig ��������),���������� 1.20.x/1.21.x
   ����"������Ҳ����",������ʧȥ�������ڻ�װ��
### һ��ʵ��Ľ���:FML 10 ����"ע�ᴦ����",��������"���װ��"

�� 	argets() �ĳɷ��ؿռ�(**�������վ�ע��,�����ļ��վ���**,ֻ��һ���඼������),����ȫ������:

`
===== VERDICT: STARTED =====
Setting user        : True
Sound engine started: True
new crash reports   : 0
`

��־�� OptifinePayloadClassProcessor constructed (FML 10 mount point) ���� ?,�� installed ... һ����û�� ?��
Ҳ����˵:

- **"�� ClassProcessor ע��"������ȫû����**;
- ���⾫ȷ������**����������װ��**��һ�� ���� �� 	argets() �������� 517 ����,�Լ� 	ransform ��
  copy(finished, node) �ѳ�Ʒ���ǻ�ȥ���ǶΡ�

**��һ�ε�һ��ʵ��**(�������Χ����һ��):���� 	argets() �ճ�����,���� 	ransform ֻ����־��**ʲô������**
(�� copy ��ִ��)����
- ���Ի�ر� ? ������"��������Щ��"��һ��(FML 10 �Դ�����������Ĵ���);
- ������������ ? ������� copy(���� ClassNode �ľ�������,FML 10 ��ɨ���߳̿����ڶ�ͬһ���ṹ)��
### second bisect:���ⲻ��"������",����"��İѳ�Ʒ������ȥ"��һ��

���� 	argets() �ճ������Ǹ���(FML ������ѯ����),���� 	ransform **ʲô������**(ȡ payload() �����иĳ�
ֱ���ÿ�,���Ƿ������̷���):

`
===== VERDICT: STARTED =====
Setting user        : True
Sound engine started: True
new crash reports   : 0
`

? **������Щ��û����,copy �ǲ�����Ԫ��**������һ��ʵ��������ѷ�Χ������Ψһһ��:
OptifinePayloadClassProcessor.transform �� copy(finished, node)(�ѳ�Ʒ ClassNode �����ݸ��ǵ� FML ������

ode ��)��

**��һ��Ҫ�Եľ������**(������������):

1. **�͵ظ� FML �������Ǹ� ClassNode ���ܲ��� FML 10 ����** ���� ��Ϊ����һ���µ� ClassNode ��������Ϊ���
   ����ȥ(��� SimpleClassProcessor ����Լ֧��)�������ٲ������ֶ�/�����б�;
2. copy ���Ǵ�"ȡ����ɼ���"�ĺϲ���ӿ��ֶι�һ��,���ܲ��� FML 10 ��У�鲻���ܵĶ���(1.20.x/1.21.x �ļ�����
   �� ModLauncher �� NodeTransformer,��Լ��ͬ);
3. ���׷���(��� 2 �ּǹ�������һ��):��ע�ᴦ����,��Ϊ**���Ȱѳ�Ʒ��ֱ�Ӹ��ǽ���Ϸ jar**,��������"�����ڲ���װ"��
### third bisect:����"�ϲ��߼�",����"�滻�����������"

�� copy(finished, node) ����**��򵥵ġ���ȫ���ϲ�**�ĸ�ֵ(superName / interfaces / fields / methods / access /
version / signature ֱ��ȡ�Գ�Ʒ),����:

`
OptiFine payload: installed net.minecraft.util.Mth (34 fields, 109 methods) [1 so far]
Closing FML Loader 27329d2a
`

**�����ر�**������һ��ʵ��������:

| ���� | ��� |
|---|---|
| �����ļ��ڡ�	argets() �ռ� | STARTED |
| 	argets() �ճ�������	ransform �ղ��� | STARTED |
| 	ransform ����**��С**�����滻(���ϲ�) | **Closing FML Loader** |

? **���ⲻ�����ǵĺϲ��߼�**,����"**�� FML 10 �Ĵ���������滻���������������**"����±���
(�ܿ����� SimpleClassProcessor ����Լ:���������ø� 
ode,��Ӧͨ�������ĵ� API �����滻;
�������ԼҪ�� FML �Լ���Դ��Ϊ׼,����û��ȥ��)��

**����߶��׷���**:��ע�ᴦ����,��Ϊ**�� rig �������Ȱѳ�Ʒ�า�ǽ���Ϸ jar**
(libraries\net\minecraft\client\1.21.9-��\client-1.21.9-��-srg.jar �ĸ���),
����������"�����ڲ���װ",�� 1.20.x/1.21.x �����ջ����ȼ�(��������֧�Ļ�װ�������ڵ�,�����տ�������Ϸ�ܲ�������)��
### ���׷�����ʵ����:������,�� OptiFine ��"��"��

�����׷�������:���غ��� 1233 �� srg/** ��Ʒ��д����Ϸ jar �ĸ���(�� libraries\net\minecraft\client\1.21.9-��\
client-1.21.9-��-srg.jar,ԭ����� .rig-original),ʵ�� **�滻 515 ����Ϸ�ࡢ׷�� 716 �� OptiFine �������**
(��� 23 289 537 �ֽ�),Ȼ�󲻴��κ� mod ���:

`
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 0
`

Ҳ����˵:**��Ϸ������,����־��һ�� optifine ������û��** ���� OptiFine �Ĵ������û�б����
ԭ�������:�� ModLauncher �Ǽ�������,�� **OptiFine �Լ��� transformation service** �������������;
���� FML 10 ��·����û���κζ���ȥ�����,���**�����������Ϸ��**�Ž�ȥ�������� OptiFine ���ܡ�
���Զ��׷���**������**����"������ͨ��"������ ���� ��ֻ֤��"Ԥ���ǲ�ը",��֤�� OptiFine ��Ч��

**����(��һ��)**:����Ҫ�޴�������һ�� ���� ȥ�� FML 10 SimpleClassProcessor / SimpleTransformationContext
����Լ(����û��),�����"�滻"Ӧ����ô����(�����Ǿ͵ظ� 
ode);������һ������ ModLauncher �����
��� OptiFine �����ʼ���ķ�ʽ��
### �� FML 10 �Ĵ�������Լ(���ֶ����Ķ���)

�� rig �� libraries\net\neoforged\fancymodloader\loader\10.0.14\loader-10.0.14.jar �� javap ����:

- SimpleClassProcessor:bstract void transform(ClassNode, SimpleTransformationContext) +
  bstract Set<Target> targets();handlesClass �� **processClass ���� final** ? ���������ʱ,
  **������û�еط�����"����������д��ʽ"**��
- SimpleTransformationContext ֻ�� 	ype() / empty() / initialSha256() ���� **û��**"�����滻"֮��� API,
  ���Ծ͵ظ� 
ode ȷʵ������÷�(ǰ������"�͵ظ��ǲ��ǲ�������"�Ĳ²⵽�˿��Ի���)��
- �� ClassProcessor �ӿڱ����� **ComputeFlags processClass(TransformationContext)**,��
  ClassProcessor ��ȡֵ��:**NO_REWRITE / SIMPLE_REWRITE / COMPUTE_MAXS / COMPUTE_FRAMES**��

**�����һ���ܾ���ġ�����֤�ļ���**:���Ǽ̳� SimpleClassProcessor,������ processClass ���� final ?
FML �����ڲ��̶������д��;����Ǹ�������� COMPUTE_FRAMES,������**�������滻**���ֽ���(֡���),
д�����������֡��һ�µ� ���� ֢״���ÿ�����"FML �ڴ��������ֱ�ӷ���,����ʲô������ӡ"��
**��һ��**:�ĳ�ֱ��ʵ�� ClassProcessor �ӿ�,�� processClass �ﷵ�� ComputeFlags.COMPUTE_FRAMES
(�Լ� handlesClass ��ͬһ��Ŀ����),�ٿ����������Ƿ���������
## 1.21.9 ����һ��:FML 10 �ϵ�"��Ĭʧ��"������,���� OptiFine �����������

��һ�ڰ�������(1.21.9 / 1.21.10 / 1.21.11)�� FML 10 ���ص��"������û��һ�д���"�Ƶ���"OptiFine �Ѿ�����"��
ȫ�����۶�����һ������̨��������������,д�����һ���Ʒ���ǰ��Ĳ²⡣

### ���Ʒ�һ��:`ComputeFlags` ����ԭ��

��һ�ְ� `SimpleClassProcessor` �� `processClass` �� `final`���� `ClassProcessor` �ӿ���
`ComputeFlags processClass(...)` ������Ҫ���ɡ�`javap -c` ֱ�Ӷ������Ǽٵ�:

```
public final ClassProcessor$ComputeFlags processClass(ClassProcessor$TransformationContext);
   0: aload_0
   1: aload_1
   2: invokevirtual  ClassProcessor$TransformationContext.node()Lorg/objectweb/asm/tree/ClassNode;
   5: aload_1
   6: invokevirtual  transform:(Lorg/objectweb/asm/tree/ClassNode;LSimpleTransformationContext;)V
   9: getstatic      ClassProcessor$ComputeFlags.COMPUTE_FRAMES
  12: areturn
```

��**������**���� `COMPUTE_FRAMES`(û�з�֧,û�� `empty()` �ж�)������"����������̫��"�Ǵ��,
�ĳ�ֱ��ʵ�ֽӿڲ���ı��κ��¡��������˽�����

### "û���κδ���"�Ļ���:FML ���쳣�ͽ���һ��ģ̬�Ի���

`net.neoforged.fml.startup.Client.main` ���ֽ�����:

```
 10: invokestatic Entrypoint.startup([Ljava/lang/String;ZLnet/neoforged/api/distmarker/Dist;Z)LFMLLoader;
 25: invokestatic Entrypoint.createMainMethodCallable(LFMLLoader;Ljava/lang/String;)Ljava/lang/invoke/MethodHandle;
 31: invokevirtual MethodHandle.invokeExact([Ljava/lang/String;)V
 46: invokevirtual FMLLoader.close()V          <- "Closing FML Loader" ��������
 ...
 76: astore_1 / 77: invokestatic FatalErrorReporting.reportFatalError(Throwable;)V / 81: System.exit(1)
```

�� `FatalErrorReporting.reportFatalError(String)` ��:

```
 0: ldc "java.awt.headless" / 2: ldc "false" / 4: System.setProperty   <- ǿ�Ʒ� headless
 8: GraphicsEnvironment.isHeadless() / 11: ifne 21
14: showErrorUsingSwing(String)      -> JOptionPane.showMessageDialog(...)   <- ģ̬,����
21: ... TinyFileDialogs.tinyfd_messageBox(...)
51: System.exit(1)
```

**���� FML 10 ����������쳣�ı�����**:stderr 0 �ֽڡ�û�� crash-report����־���һ����
`Closing FML Loader`��JVM һֱ���˳�(�Ի����ڵ��˵�ȷ��)���ⲻ��"FML ����������",
��һ��û�˿��Ĵ��ڡ�Ϊ��ȷ�ϲ��ǲµ�,�Ѹ� JVM �Ķ��㴰��ö�ٳ���:

```
1508380|vis|SunAwtDialog|Fatal Error          <- ������
 2625878|vis|GLFW30|Minecraft: NeoForge Loading...
```

**�� rig ������������**(���ڲֿ���,`optifineoforge-test\`):

* `diagnostic\kynarain\cn\optifineoforge\rig\DiagnosticClient.java` ���� �̳�
  `net.neoforged.fml.startup.Entrypoint`(`startup` �� `createMainMethodCallable` ���� `protected static`,
  �������),��**ͬһ��** FML �ܵ�,���� throwable �� stderr,Ȼ�� `Runtime.halt(1)`��
  ֻ��ʧ�ܿɼ���,�����κα任·����
* `launch-fml10.ps1 -MainClass <fqcn> -ExtraClasspath <dir>` ���� ����ڵ㻻����������ࡣ
* `capture-hwnd.ps1` ���� ������ץһ�����㴰��(PrintWindow,ʧ���� CopyFromScreen),���ű���;
  ����ץ��������ͼ,����ǰģ�Ͷ�����ͼ��,�����������������������Ǹ���ڵ㡣

### ����"��С�滻"̽��Ϊʲô����:**̽�����û�� OptiFine �Լ�����**

���������ڵ��,��һ������ stderr ���� 2036 �ֽ�,ԭ��һĿ��Ȼ:

```
java.lang.NoClassDefFoundError: Could not initialize class net.minecraft.util.Mth
	at com.mojang.blaze3d.buffers.Std140SizeCalculator.align(...)
Caused by: java.lang.ExceptionInInitializerError: Exception java.lang.NoClassDefFoundError:
        net/optifine/util/MathUtils [in thread "main"]
	at net.minecraft.util.Mth.<clinit>(Mth.java:55)
```

OptiFine ��������� `Mth` �� `<clinit>` **���� OptiFine �Լ��� `net.optifine.util.MathUtils`**,
���Ǹ�̽��������û���κ� `net/optifine/**`���������ʼ��ʧ�� �� ǰ�����������ַ��õ��Ľ���
("�� FML 10 �ܵ����滻һ���౾��ͻ�ը")**�Ǵ��**,����:ը����ȱ��,�����滻��

### �����Ŀ�:�������(Early Service jar)�ļ�������������Ϸ��

�� OptiFine �Լ�����**�Ž�ͬһ�� payload ����**����(`srg/net/optifine/**` ֮�������ͨ·����
`net/optifine/**`),FML ����־˵�ú����:

```
Found 1 early service jars (out of 1)
Loading FML Early Services:  - mods/optifine-payload-fml10-withown.jar
```

Ҳ����˵������ӱ�����"���ڷ����",���������һ����ͨ `URLClassLoader` ����,���Ǹ�������
**һ����Ϸ�඼������**:

```
java.lang.NoClassDefFoundError: net/minecraft/world/level/chunk/ChunkAccess
	at FML Early Services//net.optifine.reflect.Reflector.<clinit>(Reflector.java:143)
Caused by: java.lang.ClassNotFoundException: net.minecraft.world.level.chunk.ChunkAccess
	at java.net.URLClassLoader.findClass(URLClassLoader.java:445)
```

(`Client` ���������ע�ͼǵ� 1.21.11 �ϵ� `ValueOutput` ʧ��,����������,����"ĳ��������ǡ��û���"��)

**�޷�:������� mod �ļ�**

1. `optifine-payload-fml10.jar` ���� �������ڷ����:������������ + ���� service �ļ� +
   `META-INF/neoforge.mods.toml` + `srg/**`(Ҫװ����Ϸ���ϵĳ�Ʒ��,1233 ��)��
2. `optifine-own-classes.jar` ���� **��ͨ** mod �ļ�(`neoforge.mods.toml`,modId `optifine`),
   װ OptiFine �Լ��� `net/optifine/**`(716 ��)��`assets/minecraft/**`���Լ�
   `net/minecraftforge/**` ׮�ࡣ

��һ��֮��,`Reflector` ��� `TRANSFORMER/optifine@1.0.0/net.optifine.reflect.Reflector`,
������������Ϸ��,`net.minecraft.util.Mth.<clinit>` Ҳ�õ��� `MathUtils`��

### ����һ��:Forge API ׮��������ǵڶ���������

��֮��ĵ�һ��ʧ����֡����׶�Ҫȥ����һ�������ڵ���:

```
Caused by: java.lang.RuntimeException: Cannot find class net/minecraftforge/common/extensions/IForgeLivingEntity
	at net.neoforged.fml.classloading.transformation.TransformerClassWriter.computeHierarchyFromFile(...:149)
	at org.objectweb.asm.Frame.merge(...)
	at ...ClassTransformer.transform(...:126)
	at TRANSFORMER/optifine@1.0.0/net.optifine.reflect.Reflector.<clinit>(Reflector.java:172)
```

�� rig ���Ѿ�׼���õ� `work\1.21.9\stubs`(87 �� `net/minecraftforge/**` ׮��)�ӽ�
`optifine-own-classes.jar` �ͺ��ˡ�ע������ 1.20.1 �ǴβȵĿӷ����෴:�Ǵΰ�׮������
**��������**����ģ�� `ResolutionException`,�������ȷλ����**��ͨ mod �ļ�**��

### ���:1.21.9 �� OptiFine �Ѿ�������

```
===== VERDICT: FAILED =====
  Setting user        : True
  [OptiFine] lines    : 32
```

`latest.log` ���������� OptiFine ����:

```
[OptiFine] OptiFine_1.21.9_HD_U_J7_pre2
[OptiFine] Build: 20251002-002421
[OptiFine] LWJGL: 3.4.0 Win32 WGL Null EGL OSMesa VisualC DLL
[OptiFine] OpenGL: AMD Radeon RX 7800 XT, version 3.3.0 Core Profile Context 25.12.1.251128
[OptiFine] Maximum texture size: 16384x16384
[OptiFine] Checking for new version
```

Ҳ����˵:���������� OptiFine �Լ��Ĵ�����ı����ء�����ʼ�������Ҷ������Կ���Ϣ��
**�ⲻ������ͨ��**(���滹��������·��),������"FML 10 �� OptiFine �ܲ��ܻ�"�����������"��"��

### ʣ�µ�������·ʯ(���Ѷ�λ)

1. **`Options.loadOfOptions` ����Խ��**(stderr,��Ⱦ�߳�):
   `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1`
   at `net.minecraft.client.Options.loadOfOptions(Options.java:3174)`��
   ��ϷĿ¼����һ�� 1826 �ֽڵ� `optionsof.txt`,����ǰĳ���汾д�µ�;�Ȼ�����,����ٿ���
2. **NeoForge ����Ϸ�ಹ�ĳ�Ա�������滻�Ե���**:
   `NoSuchMethodError: 'java.util.List net.minecraft.server.packs.resources.ReloadableResourceManager.getListeners()'`
   at `net.neoforged.neoforge.client.event.AddClientReloadListenersEvent.<init>`��
   �ִ� NeoForge �ǰѲ���ֱ�Ӵ����Ϸ���,OptiFine �Ƿݱ������ﵱȻû�����������
   ������ 1.20.x �������� `keep-runtime.txt` Ҫ�ɵ���,**FML 10 �Ĵ�������û�����׼ƻ�**����
   ��һ�����Ǹ�����һ�� 1.21.9 �� keep-runtime �ƻ����Ѽƻ�֧�ֲ�����������
## 1.21.9 ��:�ƻ����ƽ�����,OptiFine �Ѿ��ܵ�"Setting user: True"

��һ�ڰ� OptiFine �����ͽ�����Ϸ�������;��һ�ڰ�**��Ա/�㼶**�������´�"����ʱ�ӵĴֱ�����"
���ɲֿ��Լ��ļƻ�����,���ѽ���Ƶ� `Setting user: True` + 32 �� `[OptiFine]`��

### �ȰѴ���������ֿ��Լ��Ĺ���

��ǰ FML 10 �Ĵ�����ֻ�� 26.x ��֧��Դ��,rig �� `javac` �ֱ�;Ҳ����˵**�����Ե��Ǹ� jar �����,
�������κ�һ���ύ**������:

* `src/fml10/java/kynarain/cn/optifineoforge/fml10/` ���� ������� 26.x ȡ��,���ϱ���֧;
* `src/fml10/resources/META-INF/services/` ���� ���� service �ļ�(ClassProcessor ��
  IModFileCandidateLocator),�� `src/ml11/resources` ͬ��������;
* `build.gradle` �� `-Pmountpoint=fml10` ʱ�������� source root �ӽ� `sourceSets.main`;
* ������ʽ(ע�� `JAVA_HOME` ������ JDK 21,���� Gradle �Լ��� Groovy ������
  `Unsupported class file major version 71`):

  ```
  gradlew.bat -Pmc=1.21.9 -Pneoforge=21.9.16-beta -Pmountpoint=fml10 compileJava
  ```

rig ������ `build-fml10-payload.ps1`:�� `build\classes\java\main` ȡ��������,��
`work\<line>\optifine-patched.jar` ȡ 1233 �� `srg/**` ��Ʒ��,�� service �� `neoforge.mods.toml`,
�ٰ� `work\<line>\plan\reparent.txt` �ŵ� `optifineoforge/reparent.txt`��

**˳�ֲȵ�һ������������Ŀ�**:`ZipFile.CreateFromDirectory` �� PowerShell 5.1(.NET Framework)��
д����Ŀ����**��б��**�������Ĺ��� FML ֱ�Ӿܾ�:

```
WARN [ne.ne.fm.lo.mo.ModDiscoverer/SCAN]: Skipping jar. File mods/optifine-payload-fml10.jar is not a valid mod file
```

֢״��������:��Ϸ**�������**��`Setting user: True`��0 �������桢stderr 0 �ֽ� ���� Ψһ���쳣�ź���
`[OptiFine] lines: 0`���ű��ĳ��ֹ�д��Ŀ��һ���� `/`��

### ��������:������ʱ���еĳ�Ա,���ƻ�������

**��һ��(��Ա)**:�ִ� NeoForge ��**�Ѳ��������Ϸ��**��,��������ʱ��������� OptiFine �Ƿݱ���
�����û�еĳ�Ա��ʵ�⵽����һ��:

```
NoSuchMethodError: 'java.util.List net.minecraft.server.packs.resources.ReloadableResourceManager.getListeners()'
	at net.neoforged.neoforge.client.event.AddClientReloadListenersEvent.<init>(...:29)
	at net.neoforged.neoforge.client.ClientHooks.initClientHooks(...:973)
```

����������**��������ʱ���С�����û�е��ֶ��뷽��**(�� name+desc ����),�������������־��

**�ڶ���(�㼶)**:`BlockEntity` ����ĸ��಻һ�� ���� �����Ƿ� extends
`net.minecraftforge.common.capabilities.CapabilityProvider$BlockEntities`(OptiFine �� Forge ʱ�����,
�Ǹ����� shim),����ʱ�Ƿ� extends `net.neoforged.neoforge.attachment.AttachmentHolder`�����߸���һ��,
**���ζ�����֤�����**,���Ҵ����ͬ:

* ֻ���Ա����������:`VerifyError: Bad invokespecial instruction: current class isn't assignable to
  reference class` at `BlockEntity.setData @7` ���� NeoForge �� `setData` ������
  `invokevirtual setChanged()V` Ȼ�� `invokespecial AttachmentHolder.setData(...)`;
* ֻ�Ѹ��໻������ʱ��:`VerifyError: Bad <init> method call ... Type
  'net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities' is not assignable to
  'net/minecraft/world/level/block/entity/BlockEntity'` ���� �����Լ��Ĺ��������� chain �� Forge ����Ĺ�������

�������һ��,�������� ModLauncher ��������õ� `reparent.txt` �ƻ�(��ʽ
`reparent <��> <����ʱ����> <������>`)������������**���ƻ�**,û�мƻ��Ͳ����㼶;
���ƻ��� `()V` ��дʱ��ջ�ϵ�ʵ�� POP ��(�� `PatchedClassTransformer.reparent` ͬһ������)��

�ƻ��ɲֿ��Լ��� `HierarchyPlan` ���ɡ�**���������и������ϵ�����**:`readRuntime` ��"�ȵ��ȵ�",
���Ե�һ�� jar ������**����������ʱ��ͼ**(���� NeoForge �� `-client.jar`,��Ϸ�౻������),
���� vanilla �� `BlockEntity`(������ Object)������ǵ�,���Ǽƻ�**��Ĭ�ؿ�**:

```
# ����(0/0,��Ĭ): runtime �� client-��-srg.jar
# ��ȷ:            runtime �� neoforge-21.9.16-beta-client.jar
reparent net.minecraft.world.level.block.entity.BlockEntity onto net/neoforged/neoforge/attachment/AttachmentHolder via ()V (the payload extends net/minecraftforge/common/capabilities/CapabilityProvider$BlockEntities)
reparent plan: 1 class(es) movable, 0 refused
```

1 ����,�� 1.21.4 �������ϼǵ�"shape ������һ����"��ȫһ�¡�

### ��һ�ֵĽ��

```
===== VERDICT: FAILED =====
  Setting user        : True
  new crash reports   : 1
  stderr bytes        : 698
  [OptiFine] lines    : 32
```

OptiFine ���������桢`Reflector` ��ʼ���ɹ�����Ϸ���û����ö������ˡ�ʣ������,���Ѷ�λ����:

1. **`Gui.layerManager` �� null**:
   `NullPointerException: Cannot invoke "��GuiLayerManager.initModdedLayers()" because "this.layerManager"
   is null` at `Gui.initModdedOverlays(Gui.java:1604)` �� `ClientHooks.initClientHooks`��
   ����ֶ��� NeoForge �ӵ�,��**���ڲֿ��Լ�Ϊ 1.21.9 ���ɵ� `member-restores.txt` ��**:
   `F net/minecraft/client/gui/Gui layerManager Lnet/neoforged/neoforge/client/gui/GuiLayerManager;`,
   ���� rig ���Ѿ������׵� `donors/`��Ҳ����˵**"�����Ա"ֻ�����һ��**:�ֶ���������(���Բ���
   `NoSuchFieldError`),�����ֶθ�ֵ�ĳ�ʼ���� NeoForge �� `Gui.<init>` ��,���Ǹ��������� OptiFine
   �ĸ��������滻���ˡ���һ�����ǰ� `member-restores.txt` + donors �ӽ� FML 10 �Ĵ�����
   (ModLauncher ������ `MemberRestoreTransformer` ���� donor ����)��
2. **`Options.loadOfOptions` ����Խ��**(stderr,698 �ֽ�):
   `ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1` at
   `net.minecraft.client.Options.loadOfOptions(Options.java:3174)`��
   ʵ�����������:�� ɾ�� `optionsof.txt` ������Ȼ����;�� ��������Ϸ������д������ļ�(88 ��,
   ȫ�� `key:value`,û��һ��ȱð��)������"�����ɰ汾д���ļ�"����²�**վ��ס**,
   �����ٿ���һ�е�������ʲô��
## 1.21.9 ����:��Ա�ָ�������,OptiFine �ܵ� 167 ��,���� FML �Լ��ļ��ػ�����

### ��һ�ּӽ���������������

**1. ��Ա�ָ�(�ƻ� + donor)��** ��һ��ͣ�� `Gui.layerManager` Ϊ null:�ֶα�"��������ʱ��Ա"��������,
��������ֵ�ĳ�ʼ���� NeoForge �Ĺ�������,���Ǹ��������� OptiFine �ĸ���ȡ���ˡ����������ڶ�
`/optifineoforge/member-restores.txt` �� `/optifineoforge/donors/<��>.class`,�� donor ���С���Ʒ����û�е�
�ֶ�/��������ȥ,���� donor ��ĺϳɳ�ʼ������**����**:��̬�Ľ����� `<clinit>`,ʵ���Ľ�ÿ��
"�Լ�û�и�������ֶ�"�Ĺ�������

����������������,����ѡ��:

* **��̬��������,���ܵ���**����̬ final �ֶ�ֻ����**����ĳ�ʼ������**�︳ֵ,����һ�� helper ȥд�ᱻ��:
  `IllegalAccessError: Update to static final field ... attempted from a different method than the initializer method`��
* **ʵ��Ҳ��������**���Ȱ� ml11 ����"ÿ����������һ�� helper"��,1.21.9 ֱ�Ӹ���:
  ```
  IllegalAccessError: Update to non-static final field com.mojang.blaze3d.opengl.GlDevice.deviceProperties
    attempted from a different method (optifineoforge$init$deviceProperties) than the initializer method <init>
      at com.mojang.blaze3d.opengl.GlDevice.optifineoforge$init$deviceProperties(GlDevice.java)
      at com.mojang.blaze3d.opengl.GlDevice.<init>(GlDevice.java:95)
  ```
  ������������֮��,ͬһ�� `putfield` �ͺϷ���,����**��������Ȼ����**:donor �ĳ�ʼ�������Ѷ�����ھֲ� 0,
  `<init>` Ҳ�ǡ�
* **ָ���¡��ȱ `VarInsnNode`** ���� ���� `Gui.layerManager` һ����Ȼ�� null ��ֱ��ԭ��,��־��д�ú����:
  `cannot inline optifineoforge$init$layerManager: an instruction of kind VarInsnNode has no copy here`��
  ֻ����ֲ� 0(����ֲ��ڹ�����������β�ײ��),����һ�ɾܾ������ǲ¡�

**2. һ������Ե��޸�:`ReloadableResourceManager` �ļ������б�����ᡣ** ���� NeoForge �� OptiFine ��
**�Ⱥ�˳��**��ͻ,���ߵ�������û��:

* NeoForge �� `Minecraft` ��������ͨ�� `AddClientReloadListenersEvent` �ռ�������,������**���ɸ�**����
  `ReloadListenerSort.sort` �����һ���� `Collections.unmodifiableList`(��
  `neoforge-21.9.16-beta-universal.jar` ���������),`ReloadableResourceManager.updateListenersFrom` ����ֱ��
  �����ֶ�;
* OptiFine �Լ��Ĳ�����ͬһ���������**�����**(��ע�뵽 `Window.setDefaultErrorCallback` ���Ǵε���)
  ע��һ��������,����ײ�϶�����б�:
  ```
  UnsupportedOperationException
    at java.util.Collections$UnmodifiableCollection.add
    at net.minecraft.server.packs.resources.ReloadableResourceManager.registerReloadListener(...:43)
    at net.optifine.util.TextureUtils.registerResourceListener(TextureUtils.java:412)
  ```
  NeoForge �Լ�֮����ע��,������������ڴ� NeoForge �￴��������

  ����**�����**������ `registerReloadListener`:�� `updateListenersFrom` ���� `ReloadListenerSort.sort` ֮���
  `new ArrayList<>(list)`(`NEW/DUP_X1/SWAP/INVOKESPECIAL`),���ֶλָ� vanilla ������������Լ(һ������
  �����ӵ� List),NeoForge �������˳��һ�㲻�����ֶ�������ɸ�ֵ,����Ϊ�����������ͻ��
  ����ʱ�� final ���ֶε� `final` ����������ֶ��������(���ر�� `final`,NeoForge ��û��)��

### ���:167 �� `[OptiFine]`,Ȼ���� FML �Լ��ļ��ػ�����

```
===== VERDICT: FAILED =====
  Setting user        : True
  new crash reports   : 1 -> crash-��22.07.08-client.txt
  stderr bytes        : 0
  [OptiFine] lines    : 167
```

OptiFine ���������ڹ���(`[OptiFine] Scaled non power of 2: minecraft:leaf_3, 5 -> 10` ������ͼ�����Ѿ���������),
��ϷҲ������ѭ��,��ͣ�� **NeoForge �ļ��ظ��ǲ�**��:

```
java.lang.IllegalStateException: Already building.
	at net.neoforged.fml.earlydisplay.render.SimpleBufferBuilder.begin(SimpleBufferBuilder.java:185)
	at ��RenderContext.renderText(RenderContext.java:91)
	at ��PerformanceElement.render(PerformanceElement.java:75)
	at ��LoadingScreenRenderer.renderToFramebuffer(LoadingScreenRenderer.java:280)
	at TRANSFORMER/neoforge@21.9.16-beta/��NeoForgeLoadingOverlay.render(NeoForgeLoadingOverlay.java:68)
	at TRANSFORMER/minecraft@1.21.9/net.minecraft.client.renderer.GameRenderer.render(GameRenderer.java:811)
	at TRANSFORMER/minecraft@1.21.9/net.minecraft.client.Minecraft.runTick(Minecraft.java:1330)
```

������**����һ��Ƭ GL ����**���ֵ���:ͬһ����־�� `OpenGL API ERROR: 1167` ֮�๲ **865 ��**,��һ�γ�����
OptiFine �տ�ʼ������ͼ֮��,ʧ�ܵĵ����� `glClear`��

**�ⲻ�ǻ�������,�Ƕ�������������**:ͬ���� rig��ͬ���� 90 �롢**�����κ� mod** ��һ��:

```
===== VERDICT: STARTED =====    Setting user: True, Sound engine started: True, ���� 0, stderr 0
��־������ 76,OpenGL API ERROR 0 ��,Already building 0 ��
```

Ҳ����˵���� GL ������**����װ���������**:OptiFine ����Դ�����ڼ�� GL ������״̬Ū����(����ĳ��
������� `GlStateManager`/`GlDevice` ��һ·��ȱ����),FML ��������ʾ�ڻ���� GL ״̬�ϻ�,һ�� `draw`
��;�״�Ͱ� `SimpleBufferBuilder.building` ���� true,��һ֡ `begin` ֱ���� "Already building"��

**��һ��**:�� 1.21.8 ����������ù����Ǽ��ݼƻ��� `add-line.ps1` ��˳���� ���� `PayloadDrift`
(keep-runtime / runtime-interfaces / access)�� `MissingTargets --stub`,�ٰ����ǽӽ� FML 10 �Ĵ�����;
1.21.8 ��"GL ״̬��һ·"���ǿ��⼸�ݼƻ��Ź��ġ�
## 1.21.9 ������:��Դ������ͨ��(Sound engine started),���� NeoForge �Լ���Լ����ǩ���

### ��һ�ֽӽ��������Ķ���,ȫ�����Բֿ��Լ������߹���

�� `add-line.ps1` ��˳��� 1.21.9 �����˼ƻ�:

* **`MissingTargets --stub`**:ɨ 1281 ���ࡢ43634 ����Ϸ��Ա����,**13 ��������ʱ������**,�� 4 ����
  (`GpuTexture`��`BlockModelPart`��`BlockStateModel`��`BlockEntity`)���� 12 ����Ա,1 �������������
  ���� `optifine-patched-stubbed.jar` ���ھ��� payload �� `srg/**` ��Դ(`build-fml10-payload.ps1` ��������)��
* **`PayloadDrift`**:`constant drift: 2 class(es)`,������
  `net/minecraft/client/renderer/MappableRingBuffer`(`BUFFER_COUNT: payload=5 runtime=3`)��
  `net/minecraft/client/resources/model/ModelDiscovery$ModelWrapper`(`SLOT_COUNT: payload=7 runtime=8`)��
  д���� `keep-runtime.proposed.txt` ����"����������������ʱ��"������ 15 �� interface �ƻ���164 �� access �ƻ���
* ���������ڶ� `/optifineoforge/keep-runtime.txt`(�� proposed ����ʽ��);���ƻ�����**����װ**,��־��˵
  "is kept as the runtime's own class",����"����"һ��д�ڼƻ��ļ��

### ������֢״��λ������������״�޵�

1. **ģ�;��鼯��**:�ͻ��˽���Դ���غ�**ʲô������**,ֻÿ 5 ���һ��
   `[OptiFine] Waiting for model sprites`(��Զ)������ OptiFine ��ͼ��ƴװ�����Լ��ı�־λ��payload ���Ǵε���
   ��,��**���ڷ�֧����**(���ֽ��������):
   ```
   106: invokestatic net/optifine/Config.isCustomItems:()Z
   109: ifeq 121
   118: invokestatic net/optifine/CustomItems.collectModelSprites:(Ljava/util/Map;)V
   ```
   �޷��� 1.21.8 ������һ��:�ѵ��÷ŵ� `discoverModelDependencies` ÿ��"��һ�������� Map"�����ص�**��ͷ**,
   ���������������Ǿֲ� 0(��̬����,����������ճ�����ml11 �Ƿ�������ѹ�ֲ� 0,ֻ�ھ�̬ʱ�ų���)��
2. **FML ���ڼ��ػ����� OptiFine ��ͬһ�� GL ������**:�������ڴ���ʱ,GL ����ˢ��(һ���� 7195 ��),
   Ȼ�� `SimpleBufferBuilder.begin` �� `Already building`,��������д "Rendering overlay"��**������**֤�����뻷���޹�:
   ͬ�� rig��90 �롢**���� mod**,76 ����־��0 �� GL ����STARTED��rig ������ `-NoEarlyWindow`
   (д FML �Լ��� `earlyWindowControl=false`),��������±����� **rig ����**������,��Ϊ�����ߵ��ܷ������ǿ��ŵġ�
3. **��Ա�ָ�������"��������"������"���ܳ�"**:��̬��ʼ���������������� `<clinit>`��ʵ����(���ֲ� 0 ������)
   ������ÿ��������;`<clinit>` ����**�Ȳ��� donor ����Ҳ��������ʱ����**����`BreezeWindLayer` ���Ƿ���:
   payload �Ƿ����� `private ResourceLocation TEXTURE_LOCATION;`(ʵ��,�������� putfield),
   ����ʱ�Ƿ�ͬ��ͬ���������� `static final`,����ʱ�� `<clinit>` �� `GETSTATIC` ����,
   ���� `IncompatibleClassChangeError: Expected static field ... TEXTURE_LOCATION`,�ڶ�����Դ����ֱ������
   `EntityRenderers.createEntityRenderers`��

### ���

```
===== VERDICT: FAILED =====
  Setting user        : True
  Sound engine started: True      <- ��Դ������һ����������
  new crash reports   : 1 -> crash-��22.30.08-fml.txt
  stderr bytes        : 0
  [OptiFine] lines    : 283
```

**��һ���Ѿ���λ������һ��**,������ NeoForge �Լ��Ĵ���:

```
Exception message: java.lang.NullPointerException: Cannot invoke "net.minecraft.tags.TagKey.toString()"
    because "tag2" is null
	at net.neoforged.neoforge.common.TagConventionLogWarning.createForgeMapEntry(TagConventionLogWarning.java:556)
	at net.neoforged.neoforge.common.TagConventionLogWarning.<clinit>(TagConventionLogWarning.java:201)
	at net.neoforged.neoforge.common.NeoForgeMod.<init>(NeoForgeMod.java:585)
```

`createForgeMapEntry(ResourceKey, String, TagKey)` �ĵ�����������**���÷���������**,��
`TagConventionLogWarning.<clinit>` ��ĳ����ǩ��̬�ֶ�**�� null**��������һ��"`<clinit>` ���ܳ�"��**��һ��**:
Ϊ���� `BreezeWindLayer` �Ұ�����ʱ `<clinit>` ����������,����"��������ʱ��Ա"��������**��̬�ֶ�**���ǿ�����ֵ�ġ�
**��һ���Ĺ���**Ӧ���ǰѶ���ĳ�**����������**:ɨ����ʱ `<clinit>` ��ÿ�� `owner == ����` ��
`GETSTATIC`/`PUTSTATIC`,Ҫ���Ʒ�����Ǹ��ֶ�ͬ��ͬ������**��Ҳ�� static**;ȫ������ͱ��������ʼ������,
��һ��������Ͷ���(������־)������ `BreezeWindLayer` ������Ȼ����ס,����ǩ��̬�ֶ����õ�ֵ��
**��һ���Ѿ��鵽�����ֶ���**(��һ��ֱ�Ӵ������):�� `TagConventionLogWarning.<clinit>` ��
`LineNumberTable` ������,`line 201` ��Ӧ�ֽ���ƫ�� `2942`,�ô�����:

```
2930: sipush 149
2933: getstatic Registries.ITEM
2936: ldc_w   "dyes/black"
2939: getstatic net/neoforged/neoforge/common/Tags$Items.DYES_BLACK : Lnet/minecraft/tags/TagKey;
2942: invokestatic createForgeMapEntry(ResourceKey;String;TagKey)
```

Ҳ����˵**Ϊ null ���� `net.neoforged.neoforge.common.Tags$Items.DYES_BLACK`����NeoForge �Լ��ľ�̬��ǩ�ֶ�**,
��������װ���κ��ࡣ�Ѻ˶Ե�:���� mod ������**��û�� `net/neoforged/**` ��Ŀ**(���Բ������ǰ� NeoForge ����
�ڱε���)�������һ��Ҫ�����:`Tags$Items` �� `<clinit>` Ϊʲôû�и�����ֶθ�ֵ��������ܵķ�������ȡ��
ֵ������Ϸ���ﱻ���ǻ���ľ�̬����(��־�� OptiFine �Լ�Ҳ����
`[OptiFine] (Reflector) Method not present: net.minecraft.tags.ItemTags.create`),�� `<clinit>` ��ĸ�ֵ
���쳣/��֧�����ˡ�

�������һ��"�������� `<clinit>`"��ʵ���������:**��û�н����һ��**(��������,��Ȼ��ͬһ�� NPE),
����"��������ʱ `<clinit>`"�Ȳ��ǳ������Ҳ���ǳ���޷�;��һ���ĸ�����������������,���� `<clinit>` ��ȡ�ᡣ
## 1.21.9 ͨ������:�ļ����ȫ��,������������

### ���һ������:OptiFine �÷�����һ�� 1.21.9 �Ѿ�û�еķ���

��һ��ͣ�� `Tags$Items.DYES_BLACK` Ϊ null����������һ��ȫ����������:

NeoForge �� `Tags$Items.<clinit>` ��
```
657: getstatic   net/minecraft/world/item/DyeColor.BLACK
660: invokevirtual DyeColor.getTag:()Lnet/minecraft/tags/TagKey;
663: putstatic   DYES_BLACK
```
�� `getTag()` ���ص��� `DyeColor` �������︳���Ǹ��ֶ�,payload ���Ǵθ�ֵ������:
```
47: getstatic     net/optifine/reflect/Reflector.ForgeItemTags_create
57: ldc           "forge"                                  <- Forge ʱ���������ռ�
70: invokevirtual net/optifine/reflect/ReflectorMethod.call([Ljava/lang/Object;)Ljava/lang/Object;
73: checkcast     net/minecraft/tags/TagKey
76: putfield      dyesTag:Lnet/minecraft/tags/TagKey;
```
OptiFine �� `Reflector.<clinit>` ���Ǹ������
`ForgeItemTags_create = ForgeItemTags.makeMethod("create", String.class, String.class)`,
ָ�� `net.minecraft.tags.ItemTags.create(String, String)` ���� **�� 1.21.9 ������ʱֻ�� `create(ResourceLocation)`**,
���� `ReflectorMethod.call` ���� null(��־���Ǿ� `[OptiFine] (Reflector) Method not present:
net.minecraft.tags.ItemTags.create` ������),`dyesTag` Ϊ null,`DYES_BLACK` Ϊ null,`TagConventionLogWarning`
���Լ��� `<clinit>` ��ը��,NeoForge ��"has failed to load correctly"��

**ע����һ�� `MissingTargets --stub` �ṹ�Ͽ�����**:�ǲ���һ���ֽ��������Ϸ��Ա������,���Ƿ������ֶ����һ��
**�ַ���**�������޷��Ǵ�����**����**�������:�� `ItemTags` �ϼ�
`public static TagKey<Item> create(String namespace, String path)`,�ڲ��� Forge ʱ���� `forge` ӳ���
NeoForge �� `c`(�� ModLauncher ���� `ConventionTags` ͬһ�׹���),��ί�и�����ʱ�� `create(ResourceLocation)`��
���ɵ��ֽ����� `LDC "forge"; ALOAD 0; String.equals; IFEQ; LDC "c"; GOTO; ALOAD 0; ...`��

`ItemTags` **���� payload ��**(payload û�������ĸ���),���Դ�����������һ��"ֻ�޲���"��Ŀ�꼯��
`REPAIR_ONLY_TARGETS`:`targets()` ��������,`transform()` ��û�� payload �ֽ�ʱֻ���޸���

### ���:��������ȫ��,�ҿ��ظ�

ͬһ������������,����������ͬ:

```
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 365
```

��־����֤��:`OpenGL API ERROR` **0** �С�`NeoForge mod loading, version 21.9.16-beta` �ɹ���
`[OptiFine] OptiFine_1.21.9_HD_U_J7_pre2` ������������**�������ȷʵ����**����
`RealmsNotificationsScreen.<init>` ��� `RealmsAvailability.get()`,���Ǹ�����ֻ�� `TitleScreen` ����
(���Լ��� `inTitleScreen()` �жϵ�ǰ������ `TitleScreen`),��־������������:
`[IO-Worker-1/ERROR] [com.mojang.realmsclient.RealmsAvailability/]: Couldn't connect to realms`
(���߻������������,���Ǵ���)��

### ����һ��������Ŀھ�

* **`-NoEarlyWindow` �� rig ����**:FML �����ڼ��ػ����� OptiFine ����ͼ������ͬһ�� GL ������,
  �������ͻ��˻����� `SimpleBufferBuilder "Already building"`���ص����� FML �Լ��Ŀ���
  (`earlyWindowControl=false`),��**�����ߵ��ܷ������ǿ��ŵ�**�������������ߵĳɼ�Ҫ�����ǰ�����
* �������� rig �������ڵ� `DiagnosticClient`(ͬһ�� `Entrypoint.startup` �ܵ�,ֻ�ǰ��쳣��
  stderr)��������ԭ��д�� `launch-fml10.ps1` �� `DiagnosticClient` ��ע����:�ٷ���ڵ�
  `net.neoforged.fml.startup.Client` ���쳣����һ��**ģ̬�Ի���**,ʲôҲ����ӡ��
* �����ߵ� payload ����ֿⷢ��(`srg/**` �� OptiFine �����������Ϸ��),rig ����
  `build-fml10-payload.ps1` �òֿ��Լ������߹��߲�����
## 1.21.10 / 1.21.11 ��׼��:һ�� FML 10 �ߵ������䷽(rig ���ѽű���)

1.21.9 ͨ��֮��,ʣ������ FML 10 ��(1.21.10 / 1.21.11)�õ���**ͬһ�����ص㡢ͬһ�׼ƻ�**,
���԰���������һ�������ֵ�á�rig �����������ű�,������������Դ��д�ڽű�ͷ��:

* **`prepare-fml10-line.ps1`**(`-Mc` / `-NeoForge` / `-OptifineJar`):
  1. **runtime ��ͼ** = NeoForge �� `-client.jar`(����)+ NeoForm �� `client-*-srg.jar`;
     ע�� `HierarchyPlan` ����**��**�� overlay,���� vanilla �� `BlockEntity` ��� NeoForge �ĸǵ��
     �ƻ���ĬΪ��(1.21.9 �ϲȹ�,0/0)��
  2. `OptifinePipeline <obf ԭ��ͻ���> <OptiFine jar> <work>` ���� �ֿ��Լ������߲������̡�
  3. `MemberRestorePlan`(��Ա�ָ��ƻ� + donors)��
  4. `HierarchyPlan`(Ҫ��������� + Ҫ chain �Ĺ�����)��
  5. `MissingTargets --stub`(**�����ȫ����ʱ classpath**:��Ϸ + universal + ÿ���� jar;�� argfile ����,
     ��Ϊ�⼸���ߵĿ� jar �м��ٸ�,�����лᱻ Windows �ܵ�)��
  6. `PayloadDrift`(keep-runtime / interfaces / access ���ݼƻ�)��
  7. Gradle `-Pmountpoint=fml10` ������ص�,�ٲ������� jar��
* **`build-fml10-own-classes.ps1`**:�ڶ��� mod jar(OptiFine �Լ����� + Forge API ׮ + ���Լ���
  `neoforge.mods.toml`)��׮��Ŀ¼Ϊ��ʱ��**������ `ForgeApiShims` ����**,���߶������ OptiFine jar
  **��**�򲹶��������ȡ(ֻ�� OptiFine jar ��©��Ա)��
  ���� jar �������ֹ��� `/` �ָ�д zip ��Ŀ:`ZipFile.CreateFromDirectory` �� PowerShell 5.1 ��д��б��,
  FML ��ֱ����"not a valid mod file",��֢״��**�ͻ������������ mod ����ûװ**(1.21.9 �ϲȹ�)��

1.21.9 �� OptiFine ������ 1.21.10 �Ķ��ӵ��������� IPv4 ȡ��;1.21.11 ��**��ʽ��**�ھ�����·������
(���� 9 �ֽ� "Not Found"),���� `get-optifine.ps1` �� optifine.net ���� token ����ȡ����
(`OptiFine_1.21.11_HD_U_J9.jar`,8 045 116 �ֽ�)�������ߵ� jar ��ֻ���� rig �� `downloads\` ��,�����ֿ⡣
### 1.21.10:��װȱ����������,�Ѷ�λ����������

Ϊ�˰� 1.21.10 Ҳ������,��һ��������Щ(������ rig ��,�����ֿ�):

* **OptiFine jar ȡ����������**:1.21.10 �� `preview_OptiFine_1.21.10_HD_U_J7_pre11.jar`(7 805 444 �ֽ�,
  ���������� IPv4);1.21.11 ��**��ʽ��** `OptiFine_1.21.11_HD_U_J9.jar`(8 045 116 �ֽ�)����
  ����������·������(���� 9 �ֽ� "Not Found"),���� `get-optifine.ps1` �� optifine.net ���� token ����ȡ����
* **NeoForge 21.10.64 װ����**(universal jar��1577 ���⡢126 �� native),`versions\1.21.10\1.21.10.jar`
  (30 592 168 �ֽ�,����ԭ��ͻ���)Ҳ�ڡ�
* **���� rig �ű�д�ò��﷨�Լ�ͨ��**:`prepare-fml10-line.ps1`(runtime ��ͼ �� OptifinePipeline ��
  MemberRestorePlan �� HierarchyPlan �� MissingTargets --stub �� PayloadDrift �� ���� jar)��
  `build-fml10-own-classes.ps1`(�ڶ��� mod jar,�� Forge API ׮�İ�������)��
* **����**:`libraries\net\minecraft\client\1.21.10-��` �µ� slim/extra/srg ���� NeoForm �ͻ��˱���,
  �Լ� `libraries\net\neoforged\neoforge\21.10.64\neoforge-21.10.64-client.jar`(NeoForge �򲹶���Ŀͻ��˸��ǲ�)
  **��������**;��װÿ�ζ��� `libraries: fetched 1, already present 1577, failed 1`��
  ֱ��ȡ������ʱ **`maven.neoforged.net:443` ������**(curl 72 �볬ʱ),���ǻ���/��������,�����������⡣
  �Ѿ��� Gradle ģ�黺����� `neoform-1.21.10-20251010.172816.zip`(889 425 �ֽ�)**����**�� rig ��
  `libraries\net\neoforged\neoform\1.21.10-20251010.172816\`,���˶��� `neoforge-21.10.64-userdev.jar`:
  ������ `patches/**`��`ats/accesstransformer.cfg`��`config.json`,**���������Ŀͻ�����**,���Ը��ǲ������
  NeoForm ����(neoform zip + ��Щ����)����,������ֱ�����صĳ�Ʒ��

**��һ��**:����ָ������� `add-line.ps1 -InstallOnly`(neoform zip �Ѿ�λ,���ܾ��ܲ��������ͻ��˱���),
���� `prepare-fml10-line.ps1 -Mc 1.21.10 -NeoForge 21.10.64 -OptifineJar <jar>`,��󰴽ű�ĩβ��ӡ�����������
����ű����ÿһ������ 1.21.9 ��������ͬһ����,����ʣ�µķ��ռ�����"��װ�ܲ��ܲ���"��һ���ϡ�
## 1.21.10 ͨ������:ͬһ�׼ƻ�,��Ķ�

```
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 0
  [OptiFine] lines    : 356
```

�����ܷ�����һ��(`[OptiFine] OptiFine_1.21.10_HD_U_J7_pre11`��`OpenGL API ERROR` 0 �С���������־
`RealmsAvailability` ���ܡ��ޱ������桢stderr 0 �ֽ�)��**������û��Ϊ����һ�д���������**:
��Ա�ָ���reparent �ƻ���keep-runtime��stub �ƻ������鼯���޸�������ʽ tag creator ȫ��ԭ����Ч,
�����ǰ���Щ��������"�ƻ�����"�ļ�ֵ��

### ��һ��Ϊ 1.21.10 �����������

1. **��װȱ������һ��·**��maven.neoforged.net ����̨������������,��װ��ʼ�ձ�
   `libraries: fetched 1, already present 1577, failed 1`,`libraries\net\minecraft\client\1.21.10-��` ��
   slim/extra/srg �� `neoforge-21.10.64-client.jar` ���ò�����**ModDevGradle �Լ��� NeoForm ����ʱ��Ͱ�
   �ȼ����������**:
   `~/.gradle/caches/neoformruntime/intermediate_results/compiledWithNeoForge_<hash>_output.jar`
   ���� һ�� jar ��ͬʱ�д򲹶������Ϸ��(`net/minecraft/**`)�� NeoForge �Լ�����
   (`net/neoforged/neoforge/**`),13323 ����Ŀ,�� "overlay + srg client" �ĳ�����
   `prepare-fml10-line.ps1` ��˼��� `-RuntimeJar`:������������װ����������Ʒ��ͬ����,
   neoform zip Ҳ�� Gradle ģ�黺�油�ֽ� `libraries\net\neoforged\neoform\1.21.10-20251010.172816\`��
   **�����û�����ĵȼ۲������ȱʧ����,����������Ϊ������"�ٷ���װ"����·��**
2. **�����ڵ�Ҫ�� FML �汾**��FML 10.0.32(NeoForge 21.10.64)�� API ����:
   `startup(...)` ���� `Entrypoint$StartupResult`(������ `FMLLoader`)��
   `createMainMethodCallable(StartupResult, String)`���ر��� `StartupResult.close()`��
   �� 10.0.14 �����������һ�о���:
   `NoSuchMethodError: 'FMLLoader �� .startup(String[], boolean, Dist, boolean)'`��
   �µ� `DiagnosticClientAny` **�������κ� FML API**,ȫ���÷����ҷ���,���������߹���һ���ࡣ
3. **`-NoEarlyWindow` ͬ������**:�������ڼ��ػ���ʱ,1.21.10 ������ 1.21.9 һģһ����
   `SimpleBufferBuilder "Already building"`(FML ���ڻ����� OptiFine ����ͼ������ GL ״̬)��
   ���� `-NoEarlyWindow` ����Ҫ�� `config\fml.toml` �Ѵ��ڡ�����һ�������д��,��������Ҫ�Ȳ������������һ�Ρ�

### 1.21.11 ��״̬:ȱһ��װ���˼�

OptiFine ��ʽ�� jar ���� rig ��(`OptiFine_1.21.11_HD_U_J9.jar`,8 045 116 �ֽ�),Gradle ģ�黺����Ҳ��
`neoforge-21.11.45-universal.jar` / `-userdev.jar`,��**û�� `neoforge-21.11.45-installer.jar`**,
�� `versions\neoforge-21.11.45\neoforge-21.11.45.json` ��� profile(���嵥�� JVM/��Ϸ����)ֻ�а�װ��������;
`maven.neoforged.net:443` ��һ����Ȼ������(25 �볬ʱ)������ 1.21.11 ����**����**��,���Ǵ�����:
����ָ����� `add-line.ps1 -InstallOnly -Mc 1.21.11 -NeoForge 21.11.45 -MountPoint fml10`,
���� 1.21.10 ͬ���� `-RuntimeJar` ·��(�� NeoForm ����ȡ)�� `prepare-fml10-line.ps1`,���ɰ�ͬһ��������֤��
## 1.21.11 ͨ������:FML 10 ������ȫ����ͨ

```
===== VERDICT: STARTED =====
  Setting user        : True
  Sound engine started: True
  new crash reports   : 0
  stderr bytes        : 107      <- �������� mod ���������ֽ���ͬ(ͬһ�� log4j �����澯)
  [OptiFine] lines    : 273
```

### ����ֻ�� 1.21.11 �ű�¶�����Ķ���

1. **`ResourceLocation` ����һ��� `Identifier`**������ʽ tag creator ��ί��Ŀ��ԭ��д����
   `create(ResourceLocation)`,���Ǵ������Լ�����־��˵��ʵ��:
   `net.minecraft.tags.ItemTags has no create(ResourceLocation) to delegate to; OptiFine's reflective tag
   lookup stays unresolved and every DyeColor tag stays null`,�ͻ����漴������ 1.21.9 һģһ����
   `TagConventionLogWarning` NPE �ϡ��ĳ�**��̬��ί��**:�� `ItemTags` �����Ǹ�"һ������������ `TagKey`
   �ľ�̬ `create`",ȡ���Ĳ��������� `fromNamespaceAndPath`,���� 1.21.9 �ҵ� `ResourceLocation`��
   1.21.11 �ҵ� `Identifier`,ͬһ���޸����������ߡ�
2. **��װ������,��ȱһ������**:`maven.neoforged.net` �� **IPv4 ·���ǻ���**(curl 25 �볬ʱ),
   **IPv6 ͨ**(`curl -6` �õ� 200)���� -6 ȡ�� 21.11.45 �İ�װ���� neoform zip ֮��,
   ��װ����־����� `Successfully installed client into launcher`,����������
   `libraries\net\neoforged\minecraft-client-patched\21.11.45\minecraft-client-patched-21.11.45.jar`
   ���� **FML 10 ����Ϸ jar ������**����ֻ����Ϸ��(29191 ����Ŀ,û�� `net/neoforged/**`),��������ʱ��ͼ��
   NeoForge �Լ������ `-universal.jar` ������(`net/neoforged/neoforge/attachment/AttachmentHolder` ��������),
   ��ݺϲ������Ϊ `-RuntimeJar` ���� `prepare-fml10-line.ps1`��
   ˳��˵��:��֮ǰ��"ȱʡ��װ������"����������(`client-*-srg.jar`��`neoforge-<ver>-client.jar`)
   �� FML 10 ��**���Ǳ����**�������������ص��� `minecraft-client-patched-<ver>.jar`��

### FML 10 �����ߵ����ճɼ�(��ǰ�������޶�,ͬһ�׼ƻ�)

| �� | NeoForge | �о� | `[OptiFine]` �� | stderr | ��ע |
|---|---|---|---|---|---|
| 1.21.9 | `21.9.16-beta` | ����ȫ�� | 365 | 0 �ֽ� | ����һ�� |
| 1.21.10 | `21.10.64` | ����ȫ�� | 356 | 0 �ֽ� | δΪ���߸�һ�д��������� |
| 1.21.11 | `21.11.45` | ����ȫ�� | 273 | 107 �ֽ� | ���� mod ������**���ֽ���ͬ** |

�����߹�ͬ�� rig ǰ��:**�ص� FML �����ڼ��ػ���**(`earlyWindowControl=false`)���������������߶�������
FML �Լ��� `SimpleBufferBuilder "Already building"`(�� OptiFine ����ͼ������ͬһ�� GL ������);
�������� `DiagnosticClientAny`(�������κ� FML API��ȫ����,���ͬʱ���� loader 10.0.14 �� 10.0.32,
���ߵ� `startup` ���� `Entrypoint$StartupResult` ������ `FMLLoader`)��
## 26.1.2 ���ؽ���� rig �ϵĽ���(��һ�ڹ��� `26.x` ��,������������Ϊ���������ڱ��ֵ� rig ��)

GitHub �� `26.x` �� README ���д�� 26.1.2"ʵ����֤"(`[OptiFine]` 3478 �С�`processClass` 795 ��)��
������**�ؽ���� rig** �ϵ�һ�����ȥ������,�õ�����һ����ʵ���м�״̬:

**�Ѿ�������**

* rig װ���� Minecraft 26.1.2 �� NeoForge `26.1.2.109`(FML **11.0.15**��Java **25**),
  ԭ��ͻ��� `versions\26.1.2\26.1.2.jar` 38,113,927 �ֽ�;
  ���������� `net.neoforged.fml.startup.Client`,���� `launch-fml10.ps1` �õ���������
  Ϊ������ `-JavaExe`(��һ��Ҫ JDK 25,1.21.x Ҫ 21)��
* **����**(mods ��ֻ���޹�Ԫ���ݵ� OptiFine):`VERDICT: STARTED` + `Setting user` + `Sound engine started`
  + �ޱ������� + stderr 107 �ֽڡ�Ҳ����˵��һ���**ʵ������û����**��
* OptiFine �� `K1_pre2` �Դ� `OptiFineClassProcessor`(ͬʱ�� `IModFileCandidateLocator`),
  ������ `META-INF/mods.toml` �� Forge ʱ����,���뻻�� `neoforge.mods.toml`;
  **`optifine/Patcher`��`optifine/xdelta/**`��`optifine/json/**` ���Բ���ɾ**����
  ��һ��ɾ������,`OptiFineBaseTransformer.<init>` ����
  `NoClassDefFoundError: optifine/Patcher`,����������ʵ����������
  (`ServiceConfigurationError: Provider optifine.OptiFineClassProcessor could not be instantiated`)��
* ������Ҫ��"������"�� NeoForge ��������ʱ���ò���:���ѳ�Ʒ��Ž����� jar ʱ,����ÿ��Ŀ�궼��
  `java.io.IOException: Base resource not found: net/minecraft/��`(һ���� 866,068 �ֽ� stderr),
  ���� OptiFine ȫ�̲���Ч(0 �� `[OptiFine]`)���Ѳֿ�������ˮ�߲����� `srg/**` ��Ʒ��
  (566 ��,���� net/minecraft 486)���� OptiFine �� jar ֮��,�������ſ�ʼ������װ���ǡ�

**һ���� 1.21.9 ����ͬԴ�ġ��µļ���������**

`optifine-own-classes.jar`(OptiFine �Լ����� + Forge shim)һ��ʼ�� FML **�������ڷ����**:

```
Found 4 early service jars (out of 103)
Loading FML Early Services:
 - ��/loader-11.0.15.jar
 - ��/earlydisplay-11.0.15.jar
 - mods/optifine-26.1.2-neoforge.jar
 - mods/optifine-own-classes.jar          <- ������������
```

ԭ������ˮ�߲���� classpath jar **ԭ�������� `META-INF/services/**`**,�� OptiFine ��
`net.neoforged.neoforgespi.transformation.ClassProcessor` / `...locating.IModFileCandidateLocator`
���������ļ��������档���ڷ��������һ��**��������Ϸ��**�ļ���������,����
`net.optifine.reflect.Reflector.<clinit>` ��
`NoClassDefFoundError: net.minecraft.world.level.chunk.ChunkAccess`,
�ձ���������� `net.minecraft.util.Mth.<clinit>` ���� `net/optifine/util/MathUtils` �Ҳ�����
�ӵڶ���������ȥ�������������ļ�֮��,`[OptiFine]` ��ʼ��ӡ(5 ��),stderr �ص� 107 �ֽڡ�

**����ʲô(��һ�ִ������)**

OptiFine �Ĵ�������ʼ��װ��֮��,�ͻ�������**������**��һ��,������ FML 10 ����һģһ��:

```
VerifyError: Bad type on operand stack
  Location: net/neoforged/neoforge/attachment/AttachmentSync.onChunkSent(...)V @82: invokestatic
  Reason: Type 'net.minecraft.world.level.block.entity.BlockEntity' ... is not assignable to
          'net/neoforged/neoforge/attachment/AttachmentHolder'
```

1.21.9 �������ɴ�����**�ڼ�����**�� `reparent.txt` �޵�;26.1.2 �Ĺ��ص��� OptiFine �Լ��Ĵ�����,
�������������,������һ��(������ + ������ chain ��д����Ա���shim)**���������߽׶ξ�д��
���� OptiFine jar �� `srg/**` ��Ʒ����**���������� `26.x` �ĵ���д��"����Ŀ���׵���������ˮ��"��
���ֻ�û������һ��,��� 26.1.2 **û��ͨ������**,ֻ�Ǵ�"��ȫ����"�ƽ�����"OptiFine ���ܡ����ڻ�����"��
### ����:��һ��˵ 26.1.2 ���ڻ�����,�Ǹ��Ѿ�������

����ǰ���"26.1.2 ����ʲô"д��ͬһ����һЩ��ʱ��֮��������һ��������,26.1.2 **�����о�ȫ��ͨ��**:
`VERDICT: STARTED` + `Setting user` + `Sound engine started` + �ޱ������� + stderr 107 �ֽ�(= ��������һ��),
`processClass` **795 ��**(�� `26.x` ����ļ�¼һ��),`[OptiFine]` ������һ���� 352(���Ƿݼ�¼�� 3478 ��ͬ,
**û�н���**,��ʵд�� `26.x` �� `docs/DEVELOPMENT.md` �� README ��)��

������һ�׵�����:�ֿ��Լ��� `OptifinePipeline` �� `HierarchyPlan` �� `ReparentPayload` ��
`MemberRestorePlan` �� `RestoreMembers`,�ٰѳ�Ʒ **`srg/**` �� `assets/**` һ��**���� OptiFine �� jar
(ֻ���಻����Դ��ˢ 13,367 �ֽڵ� `Base resource not found: assets/...`),��ӵڶ��� mod ����
`optifine-own-classes.jar`(OptiFine �Լ����� + shim,�ұ����޳� `META-INF/services/net.neoforged.**`,
�������ᱻ�������ڷ���ޡ�������һ����������Ϸ��ļ���������)�������䷽�� `26.x` ��
`docs/DEVELOPMENT.md`��

���� **15 ����ȫ���ڱ����ؽ���� rig ���ܹ������о�**(1.20.1 / 1.20.2 / 1.20.4 / 1.20.6��
1.21 / 1.21.1 / 1.21.3 / 1.21.4 / 1.21.6 / 1.21.7 / 1.21.8��1.21.9 / 1.21.10 / 1.21.11��26.1.2),
�����������ڸ��Ե� README ���֧�ĵ���,���� FML 10 ���� 26.1.2 �� rig ǰ��(�ص� FML ���ڼ��ػ��桢
�����ڵ㡢�Լ�"��װ���ò���ʱ�� NeoForm ����������"�����)Ҳ��д��ͬһ����
## ��Ӱ��ʵ��(FML 10 ������):1.21.10 ͨ��,1.21.9 ��δ���͵Ĳ���

�û�Ҫ�����ذ�װ�����Ӱ�����ԡ�������(Complementary Reimagined r5.9.3��BSL v10.1.5��Photon v1.3b��
MakeUp UltraFast 9.5e��Rethinking Voxels r0.1-beta9��Solas V3.7b)�� Modrinth API ȡ�� rig ��
`shaderpacks\`,�� rig ��ű� `test-shaderpack.ps1` ���"װ�� �� д `optionsshaders.txt` �� ��� �� ����־ȡ��"��

**��ôѡ��һ����**:`<��ϷĿ¼>\optionsshaders.txt`,�� `shaderPack`,ֵ�� `shaderpacks\` �����Ŀ��(�� `.zip`)��
���Ǵ� OptiFine �Լ��������������(`Shaders` �� `optionsshaders.txt` + `new File(Minecraft.getInstance()
.gameDirectory, ...)`,�������� `EnumShaderOption.SHADER_PACK.getPropertyKey()`),���ǲµġ�

### ���

| �� | ��Ӱ�� | �����о� | ���Ƿ���� | `[OptiFine]` �� | `[Shaders]` �� | GLSL ���� |
|---|---|---|---|---|---|---|
| **1.21.10** | MakeUp-UltraFast 9.5e | ȫ�� | **��** | 3441 | 76 | 0 |
| **1.21.10** | Photon v1.3b | ȫ�� | **��** | 1199 | 180 | 0 |
| 1.21.9 | MakeUp-UltraFast 9.5e | ȫ�� | **��** | 365 | 9 | 0 |

(1.21.10 �������ĸ����Ľ�����ڱ���ĩβ;26.1.2 ��������ȫ��ͨ��,��¼�� `26.x` ��
`docs/DEVELOPMENT.md`��)

### 1.21.9 �Ĳ���:��ʵ��Ϊ"δ����"

ͬһ�����ͬһ�� `optionsshaders.txt`(�������ֽ���ͬ:`shaderPack=<��>` + `antialiasingLevel=0`)��
ͬһ̨����:

* 1.21.10 ����־:`[Shaders] Load shaders configuration.` �� 2 �����
  `[Shaders] Loaded shaderpack: photon_v1.3b.zip`;
* 1.21.9 ����־:`[Shaders] Load shaders configuration.` ֮��**ʲô��û��**,ֱ�ӽ���Դ���ء�

�Ѿ��ų���(������):

* **�����ļ���ʽ/λ��**:���ļ����ڡ�����ͨ�ļ�(`Archive`,59 �ֽ�)��������ȷ;
  �� OptiFine �Լ��� `net.optifine.util.PropertiesOrdered` ���߽���ͬһ���ļ�,�õ�
  `shaderPack=[(debug)]`(��ֵҲ�õõ�),���� `key=value` �� `:value` ����д�����ܱ�������
* **����·�����첻ͬ**:`javap` ���ֽڱȽ� 1.21.9(`J7_pre2`)�� 1.21.10(`J7_pre11`)����������
  `Shaders` ��ʼ������,`new File(Minecraft.getInstance().gameDirectory, "optionsshaders.txt")` ��
  `shaderpacks` ����**��ȫһ��**��
* **��������װ�� `Minecraft`**:1.21.9 ���غ���**û��** `net/minecraft/client/Minecraft.class`
  (����ʱ�Ƿݴ� `public final File gameDirectory`),���Զ���������ʱ���ֶΡ�
* **���� antialiasing / Fabulous ����**:��������֧���Ի��һ�� `[Shaders]` ˵��,��־�ﶼû��;
  ���� `antialiasingLevel=0` �� `=2` ����ȡֵ���Թ�,��Ϊ���䡣
* **���ǰ��ļ��Ŵ�Ŀ¼**:��ͬ���������ֵ� 6 ����ѡλ��(��ϷĿ¼����ϷĿ¼�µ� `run\`��`game\`��
  rig ����`.minecraft`���û�Ŀ¼)����,`[Shaders]` ����Ȼû���κ�һ���ᵽ����������
* **`Config` ��Ĭ��ֵ**:���� `[Shaders]` ���ȡֵ������Ϊ"�����մ�",�� `loadConfig()` ��
  `Properties.load()` ֮ǰ�� `setProperty("shaderPack","")` ���Ǹ�ֵ����Ҳ����˵�ļ���ȡ��һ��
  �� 1.21.9 ��Ҫô���˱��̵���쳣��Ҫô���Ĳ����Ǹ��ļ���**��������һ��û�ж��ۡ�**

��һ�ֵ�����ֶ��Ѿ����:�� ASM �� **1.21.9 �Ƿ�** `net/optifine/shaders/Shaders.loadConfig()` ��ͷ��һ��
̽��,��ӡ `configFile`��`configFile.exists()` ������ `shadersConfig.getProperty("shaderPack","(unset)")`,
�ٰ�̽�� jar �Ž� `mods\` ��һ�Ρ�����"·��/������/������ֵ"������һ�����塣
### 1.21.10 ����������ȫ�����(���ϱ�)

| ��Ӱ�� | �����о� | ���Ƿ���� | `[OptiFine]` �� | `[Shaders]` �� | GLSL ���� | GL ���� | stderr |
|---|---|---|---|---|---|---|---|
| MakeUp-UltraFast 9.5e | ȫ�� | �� | 3441 | 76 | 0 | 0 | 0 |
| Complementary Reimagined r5.9.3 | ȫ�� | �� | 592 | 104 | 0 | 0 | 0 |
| BSL v10.1.5 | ȫ�� | �� | 393 | 68 | 0 | 0 | 0 |
| Photon v1.3b | ȫ�� | �� | 1199 | 180 | 0 | 0 | 0 |
| Rethinking Voxels r0.1-beta9 | ȫ�� | �� | 570 | 94 | 0 | 0 | 0 |
| Solas V3.7b | ȫ�� | �� | 3390 | 80 | 0 | 0 | 0 |

ÿ��������**һ��������**:`VERDICT: STARTED` + `Setting user` + `Sound engine started` + �ޱ�������,
������־���� OptiFine �Լ��� `[Shaders] Loaded shaderpack: <����>`��"GLSL ����"ͳ�Ƶ���
`Error compiling|Error linking|SMCLog.severe` �����ƥ����,���ζ��� 0;`GL ����`ͳ��
`OpenGL API ERROR`,Ҳ���� 0��
## ������Ϸ�� FXAA ʵ��(1.21.10,Java 21)

�û����˷�������ַ���޶��˶�����Ϸ�����տھ�:**ֻ��"������ + ���ƶ�"**,�����������⡣

### ��ô��û�� GUI ���������������

1.21.10 �� `--server`/`--port` �Ѿ�������(`Main` ��ѡ�����û��),Ψһ������������� quick play��
ʵ��һ���пӵ�ϸ��:`-GameArgs '--quickPlayMultiplayer','8.148.31.159:25565'` ����**�ո�ָ�**��ʽ�ᱻ
`Main` ԭ����ӡ�� `Completely ignored arguments: [--quickPlayMultiplayer,8.148.31.159:25565]`,
���Ӳ��ᷢ��;����**�� token �� `--quickPlayMultiplayer=8.148.31.159:25565`** �ű�������
rig ��Ϊ�˸� `launch-fml10.ps1` ���� `-GameArgs` ͸��������

### ���:������,�����ƶ��ɲ�

```
[Render thread/INFO] [net.minecraft.client.gui.screens.ConnectScreen/]: Connecting to 8.148.31.159, 25565
[Render thread/INFO] [net.minecraft.client.gui.components.ChatComponent/]: [System] [CHAT] Use /register <password> <password> to claim this account.
[Render thread/WARN] [net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl/]: Client disconnected with reason: Authentication time has expired
```

* **���ӳɹ�**:`Connecting to 8.148.31.159, 25565` ֮���������,������ÿ 10 ����һ��
  `Use /register ...`(AuthMe ������δ��¼��ʾ);
* **Լ 70 ��󱻷�����������**,ԭ���� `Authentication time has expired`:���Ǹ����߿����ͻ���
  (�û��� `Dev`��accessToken 0),����**û�����û��ķ�������ע���˺�**(����Ҫ `/register`,���ڶ��û�������
  ��д����,��������������);
* �� 70 �봰���ڿͻ��� **0 �������桢stderr 0 �ֽ�**;
* **�ƶ�**:�� rig �½ű� `move-check.ps1` ��������֡�Աȷ�,����"������"ʱ�εı仯��������,
  ����"��ס W"ʱ�εı仯��:

| ʱ��(�� 12 ��) | ƽ�������ز� | �仯���ر��� |
|---|---|---|
| ��ֹ(����) | 7.32 | 9.55% |
| ��ס W | 19.67 | **19.68%** |

��ֵ **2.69x**,�ж� **MOVED**����ʵ˵�������߽�:�������о���**���**��(��·��ƽ����������,��ֹֻ��
��/ˮ/����),��һ��ű��õ���������д�µ�"�仯���� ��25%"������ֵ,6 ���Ǵ����� 21.18% ���г� NOT SHOWN
������ֵ�ĳ���Եĺ����ܲŵõ����������,��һ��д�ڽű�ע����;�����ط�֤������"�����ڴ���仯",
����ֱ�Ӷ�������,��������**֤��**�������Ǳ������

### ˳���޵�һ���� bug:������Ϸ������ͱ�

��һ�δ���Ӱ/���ӽ�������������,�ͻ��˼����ھ�д�˱�������:

```
java.lang.NoSuchMethodError: 'it.unimi.dsi.fastutil.ints.Int2ObjectMap
    net.minecraft.client.particle.ParticleResources.getProviders()'
  at ParticleEngine.makeParticle(ParticleEngine.java:76)
  at ClientLevel.doAddParticle(ClientLevel.java:930)
  at WeatherEffectRenderer.tickRainParticles(WeatherEffectRenderer.java:228)
```

������**ͬһ���µ�������״��ͬ**:OptiFine �� `ParticleEngine` ����ʱ�õ���
`ParticleResources.getProviders()` ���� **int ���� `Int2ObjectMap`**(���� `Registry.getId(type)` ��ȡֵ),
�� NeoForge ������ʱ�����ĳ��� **`Map<ResourceLocation, ParticleProvider<?>>`**;
OptiFine ��������һ����(ͨ������� Forge ʱ���� `ParticleResources.getProvider(ParticleType)`),
����־�Լ�˵�� `(Reflector) Method not present: ...ParticleResources.getProvider`,���Ժ�Ҳ�ǿյġ�

�޷�**����**�� `ParticleEngine` ��������ʱ�Ƿ�(��һ������ OptiFine ���Զ���������ɫ
`updateTerrainParticleColor`/`CustomColors`,����Ͷ�����),�����ڴ�������**��д������õ����״**:
`getProviders()` ���������ĳɷ��� `Map`��`Registry.getId(Object)I` �ĳ� `Registry.getKey(Object)ResourceLocation`��
`Int2ObjectMap.get(I)` �ĳ� `Map.get(Object)` ���� ȡ���Ļ���ͬһ�� provider(ע��� id ����Դλ��ָ��ͬһ��Ŀ)��
��������������:**ͬ�������硢ͬ�� 150 ��,0 ��������**��

**�����޸��ĸ��Ƿ�Χ**:1.21.9 / 1.21.10 / 1.21.11 �����߶������ǵĴ������ڼ����ڸ�д,����ֻҪ�ؽ� payload
����Ч(1.21.11 �� payload ���ؽ�);**26.1.2 �Ĺ��ص��� OptiFine �Դ��Ĵ�����**,�����ڲ���д,��Ҫ��
���߽׶ζԲ��� OptiFine jar �� `srg/**` ��ͬ����������д(�Ѻ˶�:26.1.2 �� payload ��
`ParticleEngine` ͬ������ `Int2ObjectMap`),����»�û������

### FXAA

OptiFine �Ŀ����**����** `optionsshaders.txt`,���� `optionsof.txt` �� **`ofAaLevel`**(��һ������������:
�� `optionsshaders.txt` ��� `antialiasingLevel=2` д��ȥ,��־����һ�� `Antialiasing` ��û��;
�� `optionsof.txt` �� `ofAaLevel` �ż�Ч)��

| ���� | ��־֤�� | �����о� | �������� | stderr |
|---|---|---|---|---|
| `ofAaLevel:2` | `[Shaders] Shaders can not be loaded, Antialiasing is enabled: 2x` | ȫ�� | 0 | 0 |
| `ofAaLevel:4` | `[Shaders] Shaders can not be loaded, Antialiasing is enabled: 4x` | ȫ�� | 0 | 0 |

���ζ� `VERDICT: STARTED` + `Setting user` + `Sound engine started`,û�б�������,stderr 0 �ֽڡ�
**��ʵд�ı߽�**:������֤������"FXAA 2x/4x ���ͻ��������Ҳ�����";`Shaders can not be loaded` ��һ����
OptiFine �Լ��Ĺ���(��������Ӱ������),�� FXAA �۸��ϵı�Եƽ��û�������ؼ��Ա�(����Ҫ��ϸ��
��Ե���,���ڱ��ַ�Χ)��

### rig ����������������

* `launch-fml10.ps1 -GameArgs ...`:�Ѷ�����Ϸ����׷���� profile �Լ�����֮��(quick play ������ô����);
* `slp-ping.ps1`:��С Server-List-Ping �ͻ���(���� + status),��������Ϸ֮���ʷ�����"����˭�����߼���";
* `move-check.ps1`:��֡�Աȷ����ƶ�,��"��ֹ����"��"����ʱ��"��������һ���ӡ������

## 2026-09-20:1.21 �ĳ�ʼ��Դ���ؿ��������غ����һ���Ե��ñ������� OptiFine �Լ��ķ���(����)

**һ�仰**:1.21(`21.0.167`,ModLauncher ·��,JDK 21)����������о�һֱ�ǹ���,����ʼ��Դ���ش���
û������������������������ڵȴ��̡�Ҳ�����̳߳ض���,����**������ OptiFine �Լ���һ���ȴ���־��**;
���������ǵ� SRG ��д���غ�**�Լ�����**��һ�ε��ø�������ͬ�������һ������������ `1f24f23`��

### ֢״:�������ܶ�ͣ��ͬһ���ط�(�ɸ���)

1.21 ������**���ߵ� tick loop**,����ʼ��Դ����**����û���**:

* �������� `VERDICT: STARTED` �� `Setting user` **��ͨ��**,����Ҳһֱ����;
* ���ζ�**һ�� `Created: ...-atlas` ��û��**(������,����ȫû��),����**����û��** `Sound engine started`;
* ���ζ�**û��д��������**,Ҳû���Լ��˳� ���� ����������,ֱ�� rig �ļ�ʱ��������ɱ��;
* stderr ���ζ�ǡ���Ǽ�¼�� **14 141 �ֽ�**,���滹�Ǳ�����֪���� 4 �� `NoClassDefFoundError`(OptiFine
  `J1_pre9` �� Reflector ȱ��,�쳣�� OptiFine �̵�)��

�����Ϊʲô"����о� + stderr ����"���׿ھ�**ץ������**:�о�ȫ����stderr ������ͬ,��������Զͣ�ڼ���
���ֺ��档**��ѵ**:stderr ��ͬ��������Ϊ��ͬ,�о��������һ��"������������"��**����**֤�� ���� ���߾���
`Sound engine started` �� `Created: ...-atlas`��

### �ֳ�:��ֹʱ�̵��߳�ת��

* `Render thread` �� `Minecraft.runTick` �� `RenderSystem.limitDisplayFPS`:�ͻ���**������**,����������֡;
* ֻ��һ�������߳��ڸɻ�:`Worker-Main-3`,`TIMED_WAITING (sleeping)`,ջ��
  `net.optifine.Config.sleep` �� `net.optifine.CustomItems.updateIcons` ��
  `net.optifine.util.TextureUtils.registerCustomSprites` ��
  `net.minecraft.client.renderer.texture.TextureAtlas.preStitch`;
* **���๤���߳�ȫ������** ���� �����ⲻ���̳߳ر�ռ��,������һ���߳���**��һ����Զ���ᱻ���ϵı�־**��

### �ֽ���:���ڵ�˭,˭����ȥ���Ǹ���־

��**�غ����Ƿ�**��**����ʱ�Ƿ�**���ֽ���:

* `CustomItems.updateIcons` ����һ������:`while (!modelsLoaded.get()) Config.sleep(100);`
* �Ǹ���־��**Ψһд����**�� `CustomItems.loadModels(ModelBakery)`;
* �� `loadModels` �Ǵ� `TextureUtils.registerCustomModels` ��ȥ��,������**����װ���Ƿ�** `ModelBakery`
  ��**���캯��ĩβ**���á�

### ��λ�ֶ�:��׮���� + �ֶ��� `jdk.JavaExceptionThrow` �� JFR

���β����ѷ�Χ����:

1. **��׮����**:�����캯���� `registerCustomModels` ����һ��̽��,������˳��˵�����캯��**ȷʵ����**,
   Ȼ��**�����ڲ�������**;
2. **JFR ¼��**:JDK �Դ��� `profile.jfc` �� `jdk.JavaExceptionThrow` **�ص���**,������һ����**�ֶ���**֮��
   ��¼���ġ��쳣����:`NullPointerException` at `java.util.List.of` �� `BlockStateModelLoader.<init>` �� 77 ��
   �� `ModelBakery.<init>` �� 107 �� �� `ModelManager.lambda$reload$0`��

### ����:һ�θ�д�������غ��Լ�������

`renameSrgMembers` ��д��һ��**�������غ��Լ�����**������:

* �غɵ� `ModelBakery` **ͬʱ**��������������:`private m_119364_(ResourceLocation)`(**ԭ��**��һ��,��ʶ
  `builtin/*` �� `BUILTIN_MODELS`)�� `public loadBlockModel(ResourceLocation)`(**OptiFine �Լ���**������,
  ���Լ��̵�ʧ�ܲ����� **null**);
* ӳ����� `m_119364_` �� `loadBlockModel`,���Ǹ�����**���캯�����Ǵ�"ȡȱʧģ��"�ĵ���**ָ������һ��������;
* �����ص� null һ·�ߵ�����ʱ�� `BlockStateModelLoader`,����� `List.of(missingModel)` **�ܾ� null**;
* ���캯��������**�� `modelsLoaded` ֮ǰ**�������� ? ���� `updateIcons` ����Ǹ��߳�**��Զ˯��ȥ**��

ͬһ���ﱻ**֤α**�����ֽ���:

* **"װ������һ�ݸ���"** ���� ����;
* **"�ڶ������ذѱ�־������"** ���� ����;
* **"Reflector ��һ������Ū����"** ���� ����:OptiFine �� `Reflector.call` �Լ����� null ��**�̵� `Throwable`**,
  ��������쳣�׵����캯������;
* **"����ƻ�(keep plan)������"** ���� ����:���������Ƿݼƻ���**�յ�**��

### �޷�:��Ա��"�� jar ��װ���Ƿ�"����ʱ,����д

�ڴ���������� `declaredByInstalledPayload` / `declaredNames`:�������õĳ�Ա**���Ǳ� jar ��װ���Ƿ���
�Լ�������**ʱ��,�������������,����һ�� `Kept N SRG name(s)` �����´γ�顣

**��������һ���޷�**(������,�����һ������һ��):�� OptiFine �Ǹ��ȴ�**������**����ȷʵ�ܻ���"��������",
��������**�Զ�����Ʒ��ͼ** ���� �Ǹ��ȴ����ڵ�������Ǳ�����Щ��ͼ;�ó�ʱ�����Ƕ���,������һ�����ܻ�һ���оݡ�

### �޺��ʵ��(�ɾ�����:�޲�׮���� JFR)

| �о� | ��ǰ(��������) | �޺� |
|---|---|---|
| `VERDICT: STARTED` | ͨ�� | ͨ�� |
| `Setting user` | ͨ�� | ͨ�� |
| `Sound engine started` | **��ͨ��** | **true** |
| �������������������� | 0 | **0** |
| `Created: ...-atlas` ���� | **0** | **18** |
| stderr �ֽ��� | 14 141 | **14 141**(ͬ���� 4 �� `NoClassDefFoundError`) |
| `Error loading model: minecraft:builtin/missing` | �� | **û��** |

stderr һ���ֽڶ�û��,˵������޵���**��Ϊ**,���ǰ��쳣������ĵط�ȥ��

### ������Ǵμ�¼�Ĳ���(�����ֲ�����)

�����Ǵμ�¼��д���� `[OptiFine]` **252 ��**��`Pre-stitch` **14**;����ĸɾ���ʵ���� **377 ��**��**28** �Ρ�
ԭ����**����������������** ���� ������Щ OptiFine �׶�(ͼ��Ԥƴ����������ɼ���)��ǰ**����û�л���ִ��**,
������������"�����",����**��ǰ������**���������ֶ������ĵ���:ԭ��¼ 252 / 14,����ʵ�� 377 / 28,
���߰��ύʱ��Ժ�������

## 2026-09-21:�� 1.20.x ��ֲ�� Forge shim �޸�,�Լ���**�������**����һ��(�������δ��)

1.20.x �����߽��������������һ��ȱ��:������ `ParticleEngine` ֻ�� `destroy` ��
`addBlockHitEffects` ������ Forge ��������չ�ӿ�,�� jar ��� shim ��
`IClientBlockExtensions.of(BlockState)` д�� `aconst_null; areturn`,���õ����Ž������� ����
**����һ�η���� `NullPointerException`**(1.20.6 ʵ��,07:02 ���һ�� FXAA ���ж���������)��
�޷���ͨ�õ�,����ֲ������֧�� `optifine/ForgeApiShims.java`:**�����Լ�������͵ľ�̬�������ٷ���
null**,�ӿ����Ա�����һ����ʵ�� `<interface>$Noop`,��������������ʵ��(������򷵻�������ʵ��)��

����֧��**����֤��**(���� jar ����,���������е���Ϸ��):

```
3:  invokestatic  InterfaceMethod .../IClientBlockExtensions.of:(...)...IClientBlockExtensions;
20: invokeinterface .../IClientBlockExtensions.addHitEffects:(...)Z
```

`jars-1.21.8-payload\OptifiNeoforge-1.0.0+mc1.21.8-registered.jar` �� 1.20.2/1.20.4 �� jar ��,ͬһ��
���ͽ�������**��**�����ǽӿ�:

```
public class net.minecraftforge.client.extensions.common.IClientBlockExtensions {
  public static net.minecraftforge.client.extensions.common.IClientBlockExtensions DUMMY;
```

���������� `InterfaceMethodref`����������ȴ���� �� Ԥ��
`IncompatibleClassChangeError: Found class ..., but interface was expected`��**�����������ֲֻ�����
`of()` ���� null ��һ��**;ʣ�µ�һ������ǵ�**����**�ж�:������
`shape.mustBeClass() || declaresItself(shape, name)` ����,������ֻ����Ϊ Forge �� API ����һ������
���͵ľ�̬�ֶ�(`DUMMY`)�Ͱ��������������ࡣ��ȷ���о���**��¼�����ĵ��õ�** ���� ASM ��
`visitMethodInsn` �����͸��� `isInterface` ���� һ���е��õ��ýӿڷ�ʽ������,������;�**����**�ǽӿ�;
�ӿ��Լ��� `<clinit>` ��ȫ������ Noop ʵ��ȥ�����־�̬�ֶΡ�

**δ��,Ҳδ�� 1.21.x �κ�һ�������ܹ�**:������֧�����ڻ�û���κ�һ��"�ͻ�����Ľ�������",
�������� ICCE ��**���ֽ����Ƴ�����Ԥ��**,����ʵ�⡣��������һ�ֵĵ�һλ,��
`MemberRestorePlan` �� `stackEffect`/`callEffect` ��ֲ��`MemberRestoreTransformer` ��"ÿ�ε��ø�һ��
������"һ��,���� 1.21.x ������֮ǰ��������ص������¡�

### ��������Ѿ�����(��������,��δ���)

�ķ��� 1.20.x һ��:`MethodReference` ���� `interfaceRef`(���� `visitMethodInsn` �� `isInterface`),
`Shape.callsThroughInterface()` ����,`generate()` ��Ϊ

```java
asInterface = shape.mustBeClass() ? false
        : (shape.callsThroughInterface() || (!declaresItself(shape, name) && isInterface(zip, name)));
```

**���õ�˵�ǽӿ�,�ͱ����ǽӿ�**;`declaresItself()`(Forge �� `DUMMY` ��������"�������͵ľ�̬�ֶ�")
���ٰ�����ѹ���� ���� �ӿ����Լ��� `<clinit>` ���� Noop ʵ������,`needsNoop()` ���ͬʱ�������;�̬����
�������;�̬�ֶΡ�

�ñ���֧ 1.21.8 ������������������,ͬһ�����͵�����Ա�:

| | ��� | Noop |
|---|---|---|
| ��ǰ(`jars-1.21.8-payload\...registered.jar` ��) | `class`,914 �ֽ�,�� `DUMMY` + ���� `$1` | �� |
| �޺�(ͬһ��������������) | **`interface`**,921 �ֽ�;`of()` ���� Noop,`<clinit>` �� `new ...$Noop` �� `putstatic DUMMY` | ��� Noop(block / fluid / item / mob-effect / item-font),ȫ�� 92 �������������� 0 findings |

**��δ���������֤**:������֧��û��һ�οͻ��˽�����,����"�ӿ���� + Noop �ǲ��Ǿ�ͨ��"��Ȼ�Ǵ��ֽ���
�� 1.20.x �����ߵ�ʵ���Ƴ����ġ�1.21.x ����֮ǰ��Ҫ�����������������ֲ(`stackEffect`/`callEffect`��
ÿ�ε��ø�һ��������),Ȼ���ؽ��������ܡ�

## 2026-09-22:�� 1.20.x ��ֲ���(ȫ����������),���������� A/B �ﱻ����"�ճ�������";����������֧������

1.20.x �����߽���������Ͻ���������ȱ��,����֧�������,�õ�����"������ȱ��һ������û�С�����������"��
�����ÿ�����ֶ������������,����д��ÿ����;���һ��δ��(���ֲ�����κοͻ���)��

�����õĻ��߹̶���һ��:**ͬһ������**(`work\<mc>\optifine-patched.jar` + `work\<mc>\runtime-<mc>.jar`),
�ֱ���**��ֲǰ��������**(`git show HEAD~5:.../MemberRestorePlan.java` �����������һ��)��**���ڵ�Դ��**��
һ��,����� donor �ࡢ��� `optifineoforge$init$` ����ǩ���Աȡ�����"��ֲ�ı���ʲô"����ӡ��

### һ���ƻ�������:ͬ������������ͬ�� synthetic ҲҪ�ָ�(1.20.x `da8b09d`)

�����"�������غ�����ֹ�������"�ĳ�"����**��**�����������غ��������"�������ߵ�����(�ƻ���Ŀ / donor ��):

| �� | �ƻ���Ŀ | donor �� | ���� synthetic(`lambda$`)�� |
|---|---|---|---|
| 1.21 | 363 �� **381** | 97 �� 99 | 205 |
| 1.21.1 | 293 �� **309** | 79 �� 81 | 185 |
| 1.21.3 | 293 �� **314** | 78 �� 82 | 183 |
| 1.21.6 | 345 �� **375** | 83 �� 87 | 188 |
| 1.21.7 | 355 �� **385** | 87 �� 91 | 188 |
| 1.21.8 | 353 �� **383** | 88 �� 92 | 192 |

������ȫ�������� synthetic �ķ�����,��ÿ�����Լ���
`lambda$jumpInFluid$N(Lnet/neoforged/neoforge/fluids/FluidType;)V`(1.21.8 �� `$5`,1.21 �� `$3`)����
�� 1.20.6 �Ǹ�"������ˮ����"�İ���ͬ��ͬ��,ֻ���������ϻ�û����ˮ�������������һ����
`TitleScreen.lambda$render$15`��`ModelManager.lambda$reload$0`��`BlockStateModel$Unbaked.lambda$static$N` �ȡ�

### �����ƻ�������:ջЧ��ģ�� + װ��բ(1.20.x `f56dd5b`)�����Լ�**��ֲ���������ȱ��**

��ֲ������:`stackEffect`/`callEffect`/`slots`(��������ʵ�ζ�����,long/double ռ����)����"�������ֵ"�ߵ�
`valueRun`��`objectCreationRun`(�����һ�� `new` ������)��բ `assemblesOneValue`����̬·����
`initialiserFrom`/`staticInitialiserFrom` ͳһ��բ��

**�ճ�����������,��ͬһ������� A/B ��������,�����ƶ�:**

1. **���ֻ�����������ָ����ߡ�** ASM �� `InsnList` ������ʽ����,`instructions.add(node)` ��ѽڵ��
   ԭ�����б���**ժ����**,�����ֵ�ֵƬ�����Ǵ���������Ĺ�����/`<clinit>` ��ȡ�ġ�ժ��֮��,ͬһ����
   ������ֶξ���һ�����Ĺ��Ĺ����������Լ��� store��1.21.8 ʵ��:`ClientLevel.dayTimeFraction` ������
   ���������ʧ(����������),ͬ��Ļ��� `VideoSettingsScreen.TITLE`��`SingleVariant$Unbaked.MAP_CODEC`��
   `SynchedEntityData.STACK_WALKER`��`RenderSystem.PIPELINE_MODIFIERS` �� ���� �����ߺϼƶ���
   3/3/3/6/6/7 ����ʼ����,ÿһ������"�ֶα��ָ���������û��ֵ"��
   �޷�:`copyOf(insn)` ���� Ƭ��һ��**����**������(����ʶ��ָ������ֱ�Ӿܾ�),�������಻�ٱ��ġ�

2. **�������о��ʴ������⡣** 1.20.x ��բ�ǽṹʽ��("һ������һ��ֵ��ָ��"��"new/dup/`<init>` һ��"),
   �����Լ���ע��д����"ģ��ջ��Ҫ��Ч��ǡ��һ��ֵ"�����߲���ͬһ������:����ֵ�ĵ��������Ķ��ڲ���
   ʱ**�����Ǹ���**,����

   ```
   MAP_CODEC     = Variant.MAP_CODEC.xmap(f, f)     -> getstatic; invokedynamic; invokedynamic; invokevirtual
   STACK_WALKER  = StackWalker.getInstance(option)  -> getstatic; invokestatic
   TITLE         = Component.translatable("...")    -> ldc; invokestatic
   ```

   ȫ��"������������״"������ ���� �����ļ��� `invoke()` ��ע�����ü��� `MAP_CODEC` Ϊ�ջ��� 1.21.8 ��ÿ��
   ����״̬ NPE��ͬʱ `valueRun` �� `hasValueAt`("���һ��ָ��Ҫ����ֵ")���Ѿ������� `new/dup/<init>` ��
   Ҳ�߹�ȥ��(�������������),���� `RenderSystem.PIPELINE_MODIFIERS` �� 1.21.6/1.21.8 ��û��ֵ ����
   ���Ǳ��ļ�ע��������"�ͻ�������֡����"���ֶΡ�
   �޷�:`runProblem(Ƭ��)` �����ǵ�ģ��(ȡ�ó���ջ�߼��ܡ�����ʱ����ǡ��һ��ֵ������ͣ��δ�����������),
   **���ܹ�����"��Ƭ��"���߷�������**;�ṹʽ������Ϊ��ʽ�����ĵ�һ�����α����`staticInitialiser` ����
   ʵ��·���������е� `objectCreationRun` ���ˡ����һ��:��̬����û�оֲ�����,Ƭ��������κ�
   `VarInsnNode` һ�ɾܾ� ���� 1.21.6/1.21.7 ʵ��,������ `RenderSystem.enableStencil` ��"���Լ����β�
   ��ֵ"���Ƕΰ�� `aload_0; putstatic STENCIL_TEST`,��֤����
   `Trying to get an inexistant local variable 0`��

����֮���ͬһ�� A/B(��ֲǰ vs ����):

| �� | �ƻ���Ŀ | donor �� | ���� donor �� | �������� | �������� |
|---|---|---|---|---|---|
| 1.21 | 363 �� 381 | 97 �� 99 | 0 | 1 | 23 |
| 1.21.1 | 293 �� 309 | 79 �� 81 | 0 | 1 | 6 |
| 1.21.3 | 293 �� 314 | 78 �� 82 | 0 | 1 | 6 |
| 1.21.6 | 345 �� 375 | 83 �� 87 | 0 | 0 | 8 |
| 1.21.7 | 355 �� 385 | 87 �� 91 | 0 | 0 | 8 |
| 1.21.8 | 353 �� 383 | 88 �� 92 | 0 | 0 | 8 |

Ψһ"����"�������� `SectionRenderDispatcher$RenderSection.optifineoforge$init$buffers`(1.21/1.21.1/1.21.3),
���������� 1.20.6 ʵ�����Ǹ�����֤���ܾ���Ƭ��:

```
aload_0; invokestatic Collectors.toMap; invokeinterface Stream.collect; checkcast; putfield
```

**�ܾ������ǶԵ�**����֤:������ donor ���ϸ���ѹ�� jar ���� `tools-src\StackAudit.java`(ASM ��
`BasicVerifier`),��ֲǰ�� 1.21/1.21.1/1.21.3 �� 1 �� `INJECTED` ��ջ������ 0;������**������ȫ�� 0 findings**��

### ����ע�����:ÿ�ε��ø���һ�������� + ���н��ܹ��� + ���������(1.20.x `203da0e`)

����֧**�Ѿ���һ��**:�����ֽ���ÿ�ε��ø���һ�� `aload_0`,ÿ�� `RETURN` ���µ� `InsnList`��ȱ���ǽ��ܹ���,
��β���:`initialiserCalls(owner, names)` �����С�`sequenceProblem(InsnList)` ģ����(ÿ�����õ�ʵ�ζ�Ҫ��
ջ�ϡ����ν���ʱջ�߻ص�ԭ��),������ͼ���־���������ע��;ʵ����ʼ�����ķ����"���� `()V` ����"�ĳ�
ֻ�� `(L<owner>;)V`,�����״��һ�����档

֤��(1.21.8 �� registered jar,�� jar ���**��ʵ** `PatchedClassTransformer` + `MemberRestoreTransformer`
�ܼƻ����ȫ�� 92 ����,�� rig ������ `tools-src\TransformerAudit121.java`,�ٽ��� StackAudit):

* 92 ����ȫ������֤,**0 findings**;
* �����ֽ��ﹲ 20 ��ע��ĳ�ʼ������,**���������� 2 ��**,����һ�����Ǳ���֧�Ķ�Ӧ��:

```
net.minecraft.client.renderer.chunk.RenderSectionRegion.<init>(Level,int,int,int,SectionCopy[])
   12: aload_0
   13: invokestatic optifineoforge$init$modelDataSnapshot:(LRenderSectionRegion;)V
   16: aload_0
   17: invokestatic optifineoforge$init$sectionPos:(LRenderSectionRegion;)V
   20: return
```

���� �� 1.20.x �޵���Ƕ�(`@16` �ϵڶ��� `invokestatic` ����)���ֽڶ�Ӧ,������ÿ�����ö����Լ��Ľ����ߡ�

### �ġ����ౣ��������:�����غɶ��еĳ�Ա�����ǵľ�̬��ʼ��(1.20.x `8db6879`)

����֧�� `addPayloadMembers` �Ѿ�����"���غɶ��еĳ�Ա������",ȱ���������ų�(�Ǿ�̬ final �ֶΡ�
`optifineoforge$init$` �������֡��������� `<clinit>`)��һ������(��̬�ֶ����غ��Լ�������ʼ�����һ���,
��Ƭ��ı�ǩ/�к�/ջ֡���������ǵ���"�޷�����")��֤��(1.21.8,��ʵ�任�� + �����Ǹ���ƹ���):

* `ModelDiscovery$ModelWrapper`(keep plan ������)���ڻ��
  `Not carrying the payload's final field ...ModelWrapper.id/.wrapped/.fixedSlots/.modelBakeCache`,Ȼ��
  `Gave ... the payload's 1 field(s) and 1 method(s)`;�����ֽ� 11745 �� **11847**,�������

  ```
  private net.minecraftforge.client.model.geometry.ModelContext context;
  public net.minecraftforge.client.model.geometry.IGeometryBakingContext getContext();
  ```

  ���� jar ��ͬһ��ֻ�� 11520 �ֽڡ�������Ա��û��(`getContext` ���������������һ������� `context`)��
* `ModelBlockRenderer$1` �� keep plan �����಻��(1249 �� 1249 �ֽ�),`build-jars` ���
  `patch entries dropped for 1 class(es): [net/minecraft/client/renderer/block/ModelBlockRenderer$1]`��

### �塢��Ļ׷��(1.20.x `ff0aefa` �ĺ��)��������֧**����û��**

�� 1.21.x ����ʷ�� `OPF-SCREEN` / `traceScreen` һ�ζ�û���ֹ��������ֲ����,��������һ��**ʵ�����**�޷�:
��ӡ�� `aload_1; String.valueOf(Object)`,������ `getClass().getName()`��`setScreen` �Ϸ��ػᱻ�� null
(NeoForge �� `ClientHooks.popGuiLayer` �����һ�� GUI ������ʱ������ô����),��д���������ɹ���Լ 1 ����
NPE �ѿͻ��˴��� ���� ������ 1.20.6 ��������"׷��ͣ�� ReceivingLevelScreen"��ԭ��ͬʱ��
`net.minecraft.client.Minecraft` �����Դ�ʱ�Ǽǳɱ任Ŀ��(�������� payload ����������,�任���������ᱻ
����,׷��������������)��

֤��(ͬһ������ + `-Doptifineoforge.traceScreen=true`):��־���� `Tracing every screen switch in
Minecraft.setScreen`,������ `setScreen` ��ͷ��

```
 0: getstatic System.err ; 3: new StringBuilder ; 10: ldc "OPF-SCREEN "
16: aload_1 ; 17: invokestatic java/lang/String.valueOf(Object)String
20: invokevirtual StringBuilder.append ; 26: invokevirtual PrintStream.println
```

�������������֤�� 0 findings��**��δ���������**(������֧��û�пͻ�����Ľ�������,������׷��ֻ���ڽ�����
����ʱ�ſ��ó���ֵ)��

### ������������֧������,����Ҫ��ֲ

* **Forge ��ǵ������;�̬�������������**(`43f0d62` + `345ef82`):�� 1.21.8 ��ͬһ������������������,
  `IClientBlockExtensions` ���� **`interface`**(�� `static final DUMMY`),`of(BlockState)` ������
  `new IClientBlockExtensions$Noop; dup; invokespecial <init>; areturn`,�ĸ� Noop
  (block / fluid / item / mob-effect)���÷�֧��ǰԴ�����±������ `tools-classpath.txt` ���Ƿ� jar,
  ���**��ȫһ��**,˵����һ��ȷʵ�Ѿ������
  ��**�������Ǿɵ�**:`work\<mc>\stubs` �� 9/19 ���ɵ�,�������ǻ���**��**(`public class
  IClientBlockExtensions { public static ... DUMMY; ... }`,89 ���ļ�)�������ؽ�ǰ���÷�֧��ǰ����
  �����������(ÿ�� +4 �� Noop,1.21.8 �� 89 �� 92 ���ļ�;����һ�� `IG.class`,���Ǿɹ��ߵ����ֽضϲ���)��

* **����ʱ�ӿ�ע��**(1.20.x `356c427`):�ȼ����� `injectPlannedInterfaces` + `loadTargets` ���
  `addFirstField(targets, RUNTIME_INTERFACES)`,���õ��� `decide()` ��ǰ�桢����������ǰ return��
  ʵ��:�� `ModelBaker` ��ʱ�ӽ� keep plan(�����õ���ʱ jar),��־��
  `Left net.minecraft.client.resources.model.ModelBaker alone: the keep plan keeps this runtime's copy whole`,
  ����������**������** `net/neoforged/neoforge/client/extensions/ModelBakerExtension` ���� ��"�����ౣ��������
  Ҳ���õ��ƻ�Ҫ�������ʱ�ӿ�"��1.21.8 �Լ��� keep plan �� interface �ƻ�û�н���,��������·���������
  ��û����Ȼ��������

### �ߡ�û����һ��(��ʵ��)

1.20.x ������ `staticInitialiser` �Ѻ�ѡ������"���ÿһ������"��խ��"`<clinit>` ���������õ��ı��෽��"
(`reachedFrom`),��Ϊ"�ȸ�ֵ�������"���ֶμ��� `isReadLater`/`populatedView`��**����û����ֲ**:

* ��խ�������� 1.21.x ��**������**:���ļ�ע��˵ `RenderSystem.PIPELINE_MODIFIERS` ���� synthetic ������
  ��ֵ�ġ����Ա�����ÿ������ ���� ʵ��(javap `runtime-1.21.6.jar` / `runtime-1.21.8.jar`)����
  `putstatic PIPELINE_MODIFIERS` ���� `<clinit>` ��(���� `putstatic STENCIL_TEST`,����֮��û�б�ǩ),
  �� `lambda$static$0` ֻ�Ǳ� `invokedynamic` �� bootstrap �������õ���һ������;
* `isReadLater`/`populatedView` ���� 26.x,�������"�ȷſ���������"�������ֶ�(`PROFILES`),Ҫ��
  26.1.2 ����,�����ڱ���;
* ��һ��Ҫ����,�о���"��������û�������ֶ�",������"1.20.x ��û����δ���"��

### �ˡ��ؽ�(���� ModLauncher 1.21.x ��)

ÿ���߶���"**���õ�ǰԴ���������ɼƻ�** �� ���� `add-line.ps1` ����"��˳��,��Ϊ `add-line.ps1` ֻ��
payload ������ʱ���� `prepare-line`,���� `plan\member-restores.txt` �� `plan\donors\` �ᱻ**��Ĭ����**
(���Ự�� 1.20.4 ��������ͬһ������,�������һֻ����ǰ�ļƻ��������)��

| �� | registered jar �ֽ� | SHA-256(ǰ 16) | donor �� | �ƻ���Ŀ | Ƕ��� payload �� | Forge ��� | `patch entries dropped` |
|---|---|---|---|---|---|---|---|
| 1.21 | 1916959 | 83D29414B1751233 | 99 | 381 | 440 | 90 | `[ModelBlockRenderer$1]` |
| 1.21.1 | 1793475 | 96E830BEF4D4F423 | 81 | 309 | 425 | 100 | `[ModelBlockRenderer$1]` |
| 1.21.3 | 1815461 | 7FE65E2F67A837AD | 82 | 314 | 440 | 96 | `[ModelBlockRenderer$1]` |
| 1.21.4 | 1860750 | C4F57764BEF54D00 | 86 | 336 | 474 | 60 | `[ModelBlockRenderer$1]` |
| 1.21.6 | 1932104 | A9981BC138A0C92A | 87 | 375 | 487 | 87 | `[ModelBlockRenderer$1]` |
| 1.21.7 | 1959277 | EAE41B3C64B4AADF | 91 | 385 | 500 | 87 | `[ModelBlockRenderer$1]` |
| 1.21.8 | 2004734 | 618362DC1C6B27CE | 92 | 383 | 516 | 92 | `[ModelBlockRenderer$1]` |

��ֻ jar ȫ���� `StackAudit`(ASM ��������֤��)**0 findings**(�ؽ�ǰ�� 5/1/1/1/0/0/0);
ÿֻ�� `net.minecraftforge.client.extensions.common.IClientBlockExtensions` ���� **interface**��
`of(...)` ���嶼�� `new ...$Noop`,1.21.4 �� 5 �� Noop������ 6 ���� 4 ��;
ÿֻ��Ӧ�� prepared OptiFine jar �� `ModelBlockRenderer$1.class.xdelta` / `.md5` ���� **0 ��**(keep plan ��Ч)��

1.21.4 / 1.21.8 ��ֻ jar �Ѹ��Ƶ� rig ���������Ŀ¼(`jars-1.21.4-new` / `jars-1.21.8-payload`),
���ļ���� `*.pre-port-20260922`��

### �š�rig �������뱾���޹ص������˵�����(�����һ��)

* **`work\1.21.4\optifine-patched.jar` �Ǹ� 239 �ֽڵĽض�д��**(ֻ�� manifest,9/19 0:52),
  �� `add-line.ps1` �����ļ����ھ����� `prepare-line`,���ǰ������� payload ���� `PayloadDrift` ��
  `MissingTargets`,����ֱ���� "the stub pass produced no ...-stubbed.jar"�����ΰ����ƿ�
  (`*.aborted-239-bytes`)������׼�������� `prepare-line.ps1` �Լ��ĵ� 1 ����
  `ZipFile.Open(path, 'Create')` д `runtime-<mc>.jar`,�ļ��Ѵ���ʱ��
  "The file ... already exists" ���� ����׼��һ����֮ǰ�����ȰѾɵ� runtime jar Ų�ߡ�
* **`tools-classpath.txt` ���Լ������ˡ�** ����ͷ������ `tools-patch-keepfix` �� `tools-patch-srgfix`,
  �� `tools-patch-keepfix\kynarain\cn\optifineoforge\optifine\MemberRestorePlan.class` ��**��һ����֧
  ��һ��**��������(ǩ�� `staticInitialiser(ClassNode, ClassNode, String, FieldNode)`,��
  `reachedFrom` / `isReadLater` / `populatedView`)���� �����ڲֿ� jar ǰ��,����**�κ��� `$tools` ��
  `MemberRestorePlan`(�� `prepare-line.ps1` �� 3 ��)�õĶ�����,�����Ǳ���֧�Ĵ���**��9/19 ���ɵ���Щ
  �ƻ�������ô����(1.21.4 = 316 ��Ŀ / 83 donor,����һ��һ��)�����������ؽ�����ʽ��"�ӵ�ǰԴ������
  ��һ��"���� `-cp` ��ǰ��,���Խ����ļƻ�ȷʵ���Ա���֧;�������屾��û�ж�(������Ȩ��Χ��)��

### ��ֲ���**�������**:�������� 4 �� 3 ����(��ʵ��¼,δ����)

�������ֲȫ������������(`javap` / `StackAudit` / ���������)�����������ϰ���Ŀ�Լ����������ձ�׼
��������һ��(`retest-all.ps1 -Only 1.21,1.21.1,1.21.3,1.21.4,1.21.6,1.21.7,1.21.8`,
��¼�� rig �� `logs\retest-121x-after-port.txt`),�����:

| �� | �ж� | user | sound | �������� | stderr |
|---|---|---|---|---|---|
| 1.21 | **FAILED** | yes | **NO** | **1** | 0(��¼ֵ 14141) |
| 1.21.1 | **FAILED** | yes | **NO** | **1** | 0 = ��¼ֵ |
| 1.21.3 | **FAILED** | yes | **NO** | **1** | 0 = ��¼ֵ |
| 1.21.4 | STARTED | yes | yes | 0 | 0 = ��¼ֵ |
| 1.21.6 | STARTED | yes | yes | 0 | 0 = ��¼ֵ |
| 1.21.7 | STARTED | yes | yes | 0 | 0 = ��¼ֵ |
| 1.21.8 | STARTED | yes | yes | 0 | 0 = ��¼ֵ |

����ʧ���ߵı�������ȫһ��(1.21 / 1.21.1 / 1.21.3,`crash-2026-09-22_03.38/03.40/03.42-client.txt`):

```
Description: Initializing game
java.lang.NoSuchMethodError: 'void net.minecraft.world.level.block.entity.BlockEntity.gatherCapabilities()'
  at net.minecraft.world.level.block.entity.BlockEntity.<init>(BlockEntity.java:59/60)
  at net.minecraft.world.level.block.entity.BaseContainerBlockEntity.<init>(BaseContainerBlockEntity.java:33)
  at net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity.<init>(...)
  at net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity.<init>(...)
  at net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer.lambda$static$0(...)
```

**�Ѿ������Ĳ���**:�غɵ� `BlockEntity` �� Forge ʱ���ı������
(`extends net.minecraftforge.common.capabilities.CapabilityProvider implements net.minecraftforge.common.extensions.IForgeBlockEntity`),
���Ĺ��������**�Լ�**���� `gatherCapabilities()`(�÷����� Forge �Ǹ������ṩ��);��������ȥ��
donor �� `BlockEntity` �Ѿ��� NeoForge ����״
(`extends net.neoforged.neoforge.attachment.AttachmentHolder implements IBlockEntityExtension`)����
Ҳ����˵��������������ڵļ̳�����û�������䡣Forge ��Ŀ���**����**ȱ��:���� jar ��
`net/minecraftforge/common/capabilities/CapabilityProvider` ������**��** `gatherCapabilities()`
(`javap` ���,`work\1.21\stubs` �� `stubs.pre-port` ����,86 �� 90 ����)��

**��δ�����Ĳ���**:����"��ֲ�����"����"�ؽ�ʱ�������ɿ�/�ƻ���������",����û���� A/B ����
(���е�����:�� `jars-1.21\OptifiNeoforge-1.0.0+mc1.21-registered.jar.pre-port-20260922` ���������
�������,������ `BlockEntity` �ĳ�����/�ӿ��� `reparent` �ƻ�)���ڲ���֮ǰ,**������֧������**;
�����ߵľ� jar ������ `*.pre-port-20260922`,������ʱ���ջ���ˡ�

#### ������ʧ���ߵĻ���(�����Ƶ�����Զ��)���ѡ�޷�

`MissingTargets --stub` �ж�"ĳ����Ա�Ƿ�ȱʧ"ʱ,�õ���**�غ��Լ��ļ̳���**��1.21 / 1.21.1 / 1.21.3 ���غ���
`BlockEntity extends net.minecraftforge.common.capabilities.CapabilityProvider`,���� `BlockEntity.<init>` ���Ǿ�
`this.gatherCapabilities()` �����"�Ӹ���̳еõ�,��ȱʧ",ֻ�� **Forge ����** `CapabilityProvider` ������ʵ��
(���� jar ��ȷʵ��,`javap` ���)����װ��ʱ�����߰������**�Ĺ�**���������ڵļ̳�����
(`extends net.neoforged.neoforge.attachment.AttachmentHolder implements IBlockEntityExtension`),
�����Ǿ������**������**������û���κ����� ���� 1.21 �������� `BlockEntity` �Լ�**Ҳû��** `gatherCapabilities()`
(`javap work\1.21\runtime-1.21.jar` ���Ѳ���),����� `NoSuchMethodError` ����Դ��

**һ�仰**:��(stub)�ǰ��غɵĸ������,�Ĺ�֮���໻�� ���� ����"�غɴ� Forge ����̳С����������¸���û��"�ĳ�Ա,
�����벹��**���Լ�**����(�����Ǹ�������)��

**��ѡ�޷�**(����δ��,�����һ��,˳������):
1. �ô����һ����**�Ĺ�֮��**�ļ̳���������һ��ȱʧ(`HierarchyPlan`/`reparent.txt` �� `MissingTargets` ������
   ��������ͬһ����ˮ����),�������Ա�䵽 `stubs-full.txt` ����װ����ע��;���������֤����
   `BlockEntity.gatherCapabilities()V` ������ `work\<mc>\plan\stubs-full.txt` ���ؽ��������ʧ��
2. ���߸� `reparent.txt` �������ಹһ����Ա������/��׮����ʽ��Ŀ(�� 1.20.x �� `keep-runtime` ͬ˼·)��
3. �ٲ�Ȼ���������ߵ� `reparent` �ƻ��� `BlockEntity` ����һ��,����һ������(������ NeoForge ����չ�ӿڶ�ʧ)��

��������,**�������߻ص���������ȫ��֮ǰ������**;�� jar ���� `*.pre-port-20260922`��

### ��:�ƻ���������ڳ�Ա׮��**��װ����**�ϱ�����(�������ߵ�����ԭ��)

`decide()` ץȡ�غ�**֮ǰ**���������� `stubMissing()`,��Ϊ��Щ׮��������������װ���������� ���� �������
`input.methods = methods` ��һ������غɵĳ�Ա��**�����滻**�������Ƿ�,�ռӽ�ȥ��׮����һ��û�ˡ�
`stubMissing()` ֻ��ȱ��(�ݵ�),�������滻֮��**�ٵ�һ��**�����޷�(���ύ)��

����(2026-09-22,�����߱�����ͬ):

* �޸��ƻ��� `net/minecraft/world/level/block/entity/BlockEntity` �Ĺҵ�
  `net/neoforged/neoforge/attachment/AttachmentHolder`;�غɵ� `BlockEntity.<init>` ��
  `this.gatherCapabilities()`,�÷���ԭ�����Ա��ĹҶ���� Forge ����(�����ڵ� `BlockEntity` �Լ�Ҳû��);
* �ó�Ա**ȷʵ��**����װ������׮����(���� jar �� `optifineoforge/stubs.txt` ����һ��,`javap` ���),
  ��һ�� `stubMissing()` Ҳȷʵ�����ӵ��������ڽڵ���,Ȼ���Ǿ丳ֵ�ӵ� ���� ����"׮�� jar ��ȴ��Ȼ
  NoSuchMethodError" ;
* ֢״:`Initializing game` �׶�
  `NoSuchMethodError: 'void net.minecraft.world.level.block.entity.BlockEntity.gatherCapabilities()'`
  at `BlockEntity.<init>(BlockEntity.java:59/60)`;
* �޺������ߵ���������:**1.21** STARTED/user yes/sound yes/���� 0/stderr **14141 = ��¼ֵ**;
  **1.21.1**��**1.21.3** ͬ��ȫ��(0 ������stderr 0 = ��¼ֵ)����ǰͬһ�� OptiFine ֻ�ߵ� 28/27/27 ��,
  �޺� **377/232/225** �� ���� Ҳ����˵֮ǰ����������ʵ��"��� 1 ��ͱ�"��
* 1.20.x ��֧��ͬһ����״(`stubMissing` �ڻ�װ��ֵ֮ǰ),���ڸ÷�֧�����޲����顣

### ���� + ��Ӱ�� ������ȫ��(2026-09-22):��ʵ�����������ȱ��

`run-save-shaders-all.ps1 -Pack MakeUp-UltraFast-9.5e.zip`(quick play ���浵,RigSession,1.20.4 ģ������):

| �� | �ж� | sound | ���� | ���� | ��Ӱ�� | ֤�� |
|---|---|---|---|---|---|---|
| 1.21 | STARTED | yes | **NO** | 0 | loaded | 0 region �ļ�;tracer ֻ�� `GenericMessageScreen -> TitleScreen`,**quick play ʲô��û��** |
| 1.21.1 | STARTED | yes | **yes** | 0 | loaded | 4 region + level.dat |
| 1.21.3 | STARTED | yes | **yes** | 0 | loaded | 4 region + level.dat |
| 1.21.4 | STARTED | yes | **yes** | 0 | loaded | 4 region + level.dat |
| 1.21.6 | STARTED | yes | **yes** | 0 | **not loaded** | OptiFine �Լ��� FXAA post chain JSON ����ʧ��(����) |
| 1.21.7 | STARTED | yes | **yes** | 0 | **not loaded** | ͬ�� |
| 1.21.8 | STARTED | yes | **yes** | **0(�޺�)** | loaded | 10 region + level.dat |

Ҳ����˵:**���������� 6 ����Ľ�������**,���� 4 ��ͬʱ�����˹�Ӱ�� ���� ����������֧��һ���пͻ��˽����硣

#### ��:1.21.8 �� `Cannot get config value before config is loaded`(���޲�����)

��ǰ�ı���(`crash-2026-09-22_04.54.13-server.txt`,�����һ�� tick ʵ��ʱ):

```
java.lang.IllegalStateException: Cannot get config value before config is loaded.
  at net.neoforged.neoforge.common.ModConfigSpec$ConfigValue.getRaw(ModConfigSpec.java:1235)
  at net.minecraft.world.level.Level.guardEntityTick(Level.java:593)
```
������ 1.20.2/1.20.4/1.20.6 �ϵ���һ��ͬһ��:OptiFine ����� `IntegratedServer.initServer()` ��������������������
���ö��������Լ��� reflector,�� reflector �� **Forge ������**(`net.minecraftforge.server.ServerLifecycleHooks`)
ȥ����,����������ֻ�� `net/neoforged/neoforge/server/ServerLifecycleHooks` ? ���ӴӲ����� ? NeoForge �ķ�����
���ôӲ����� ? ��һ�������ĵط����ס��޷����� 1.20.x �Ѿ����õ���һ�г�Ա������
(`net/minecraft/client/server/IntegratedServer<TAB>initServer<TAB>()Z`,�������Լ���ͬ���������������ù���),
�ؽ�ʱ�ܿ��� `patch entries dropped for 1 class(es): [net/minecraft/client/server/IntegratedServer]`��
����:ͬһ�� quick play + MakeUp ��Ӱ�� ? ���� yes��**���� 0**��10 �� region �ļ� + level.dat����Ӱ�� loaded��

#### ��δ��(����,���Ѷ�λ��֢״����һ��)

1. **1.21 �� quick play ��������**(�� 1.21.1/1.21.3/1.21.4/1.21.6/1.21.7/1.21.8 ͬһ�� rig ������)��
   tracer ��ʵ:�ͻ���ֻ�ߵ� `GenericMessageScreen -> TitleScreen` ��ͣס,resource reload ֮��**û���κ�**
   ���������־(�� "Preparing spawn area"���� "Failed to load level"),region �ļ� 0��������������
   `IntegratedServer` ����֮��**û�б仯**,������һ������ͬһ��������һ��(1.20.4 �Ѿ��ù����ɹ��İ취):
   ������������**�˵�����**������(1.20.4 �� `world-test-1204.ps1` ����Ϊ��д��:��ʵ����� + ���ֽ��������
   �����б����),�����Ǽ����� quick play��
2. **1.21.6 / 1.21.7 �Ĺ�Ӱ�����ز���**(�����ܽ���sound yes������ 0),��־���� OptiFine �Լ��� post chain ��
   �°������������:
   `Failed to parse post chain at minecraft:post_effect/fxaa_of_2x.json | JsonSyntaxException: No key
   fragment_shader / No key vertex_shader`,��� `[OptiFine] Resource not found: minecraft:shaders/post/fxaa_of_2x.json`��
   1.21.5 ��������� `shaders/post/*.json`(`vertex`/`fragment` ��)�ĳ� `post_effect/*.json`
   (`vertex_shader`/`fragment_shader`),�������ߵ� OptiFine �������Ǿɸ�ʽ ���� �⿴������ **OptiFine �汾�� MC
   �汾֮��Ĳ�����**,�������Ǽ����������;Ҫ��ʵ���ۻ���һ�ζ���(��ͬһ�� OptiFine ֱ�ӷŽ�ͬ�汾 NeoForge
   ��һ��,���Ƿ�ͬ������),����û����

#### 1.21 �� quick play ��Ĭ:һ����֤α�ļ���,��ʵ����

��������"1.21 �� quick play ʲô������"��������"�浵�Ǿɵ�/���µİ汾"����:�Ǹ� gameDir ��
`saves\RigSession\level.dat` ��ʱ����� **2025-12-13 17:51:43**,����������һ���汾���µĴ浵��**�����Ⲣ֤α**:
ɾ������ `saves\RigSession` ����һ��,harness ��־д����
`save: created RigSession from ...\templates\level-1.20.4.dat`,���½������� `level.dat` �����Ǹ� 2025-12-13
ʱ��� ���� ��ֻ��**ģ���ļ��Լ��� mtime**(`Copy-Item` ����ԭʱ���),�浵����ÿ�ζ��Ǵ�ģ���½��ġ�
DataVersion ���� 1.20.4 ��,��������������ȫһ����������� `world NO`��0 region �ļ���

����:**�������Ͷ����ų�**(join ����ȱʧ���浵�汾),��һ�����������еġ�δ���͵� quick play ��Ĭ;
��һ���� 1.20.4 �Ѿ��ɹ��İ취�������ò˵�����������(��ʵ����� + �ӿͻ����ֽ�������������б����),
�����Ǽ����� quick play �ϻ�ʱ�䡣

### 1.21 ���������:�ĸ����ŵ�ȱ��,�޵�����;������һ��"����ϵͳ����ʵ�ʴ���"�ĸĶ�

1.21(21.0.167)�����ߵ� quick play **ʲô������**(tracer ֻ�� `GenericMessageScreen -> TitleScreen`),
���Ը���**�˵�����**(���� `world-test-121.ps1`,�� 1.20.4 �Ƿݸ�д;1.21 �� `SelectWorldScreen` ������
`javap` ����� 1.20.4 ������ͬ:������ `52+36i+18`��Play ��ť `(w/2-154,h-52,150,20)`)���Ĳ˵�֮��,�������
һ·�����ű�¶�ĸ�ȱ��,�޵�����(�������֤��):

1. **`ModelPart.getChild`(�� 1.20.4 ͬһ��)** ���� �غɵ� `getChild` �� `child.getId()` ƥ��,�������ߵ��غ���Ȼ
   ���� `PartDefinition`,���� `bake` ��ֻ `new ModelPart(List, Map)`��**�Ӳ� `setId`**,���� id ȫ null��
    `Failed to create model for minecraft:skull` �� `Caught error loading resourcepacks, removing all selected
   resourcepacks`���޷�:������֧�����߶����� `ModelPart getChild` �ĳ�Ա������(�����ڵ� `children.get(name)`),
   ÿ���ؽ�ʱ����ӡ `patch entries dropped ... [ModelPart]`;**����������ȫ���ص���¼ֵ**(1.21 = 14141,���� 0)��
2. **`IntegratedServer` ���������ౣ��,����ֻ�����Ա**��ֻ���� `initServer()Z` ʱ,�غ��Ƿ����Ա�װ��ȥ,
   ��**���Ĺ�����**���� `this.m_236740_(GameProfile)` ���� �غɺ���������ͼ���������������,����
   `Description: Starting integrated server` �׶�
   `NoSuchMethodError: void net.minecraft.client.server.IntegratedServer.m_236740_(com.mojang.authlib.GameProfile)`
   (`crash-2026-09-22_06.00.51-client.txt`)���ĳ� `IntegratedServer<TAB>*` ���ౣ���,�������Խ������һ����
3. **���Ĺҵ� `BlockEntity` ����ȱ���� Forge �����Ա**:`gatherCapabilities()V`(����� `NoSuchMethodError`,
   ������һ����)�������걩¶ `getCapabilities()Lnet/minecraftforge/common/capabilities/CapabilityDispatcher;`
   (���ֵ�ʱ `[Worker-Main-12/ERROR] ... NoSuchMethodError`,spawn area ���� 0%);Forge �� `CapabilityProvider`
   һ����������������Ա,��������һ�𲹽� `stub-additions-1.21.txt`(��ͬ����ͬ���Ĺҵ� 1.21.1/1.21.3)��
4. **`MinecraftServer.m_195518_()Z`** ��� vanila getter ��������ͼ��ֻ���ֶ� `isSaving` û����,�غ� `Gui`
   �� `tickAutosaveIndicator` ����� �� ����ռ������
   `NoSuchMethodError: boolean net.minecraft.server.MinecraftServer.m_195518_()`(`crash-2026-09-22_06.14.06`);
   ��׮���� false(= δ�ڱ���)����ȷ��Ĭ��ֵ,�Ѽǽ� `stub-additions-1.21.txt`��

#### һ�α��Լ�������"ϵͳ���޸�",������������´�����һ��

������һ��"��һ����ðһ��"�ĸ�Դ�������Ǵ����һ���� classpath:����ǰ�õ��� rig ��**����** jar(583 ��),
`MissingTargets` ������������Ա,��� MC �汾/��������ͬ����Ա����"ȱʧ"�������������ǰ�����խ��
"�������Լ��� version json + profile json + universal + runtime ��ͼ"(114 �� jar,�������� 1.20.x ��
`rebuild-120x-line.ps1`)�������**ȷʵ**�� 4 ��ȱʧ�ǵ� **46** ��,���������һ��**��������ͼ�Լ�ȱ�� vanilla
SRG ��Ա**:`ServerLevel.m_7654_()`(`Level.getServer()`)��`MinecraftServer.m_195518_()`(`isSaving()`)��
`m_7570_(Z)` �ȡ�����Щ������"Ĭ����"�����޸�����**����**:`getServer()` ���� null ��ȱʧ��������
ʵ�����������������խ�Ǵ��ؽ�֮����������(`logs\world-121-run6.txt`)�������ж�û���С�һ�������Ƕ�û�С�
���������խ**�ѻ���**(��������������˵��������),`add-line.ps1` �����ÿ� classpath + ����
`stub-additions-<mc>.txt`��**������ȱ������������ͼ�� vanilla SRG ��Ա������**,����һ���������졣

#### ��״(��ʵ)

1.21 �������ߵ�"���������"(�˵�:���� �� Singleplayer �� ������ �� Play;run4/run5 �� `Preparing spawn area`��
`Time elapsed`��`Loading recipes/advancements` �����ֹ�,`session.lock` �� region �ļ���д),�� run6/run7 ��
û�������� ���� Ҳ����˵**�˵���������·����ڱ�������ȶ�**(�²���"���Ƿ�ѡ��"�й�:Play ��ť��ûѡ����ʱ
�ǽ��õ�)��**��� 1.21 Ŀǰ�Ȳ��ܼǳ�"������",Ҳ���ܼǳ�"ȷ��ʧ��"**;��һ���ǰ�"��ȷ���б�ѡ��
(�ü��� Down ѡ�к��ڽ�ͼ/tracer ��ȷ��)�ٵ� Play"����ȷ���Բ���,���� `Down+Enter` ����������·��Ϊ��·��
������������ ModelPart/IntegratedServer �Ķ���û���ܽ�����Ӱ(1.21.8 Ҳ�������ౣ��)��

### 1.21 �Ѿ�**����������**(joined the game),���漴����ͬһ��"ȱ vanilla SRG ��Ա"�ļ����� ���� ������岻���ò�׮����

��������ȶ���:���˵���������"**û��ȷ�������б���������˾Ͳ���ʼ�ڶ���**"�ĵȴ�(`world-test-121.ps1` ��
step 1b;1.20.4 �Ƿ�ͬһ������Ҳһ������),��Ϊ������"���б�ĵ���ڵ�һ������ѯ����֮��ű�����,
���ǵڶ�����ȫ�����Զ����ڱ��������"�������������֮���һ������(`logs\world-121-run8.txt`):

* �����б��Ѿ��� �� �е�� + Play �� `GenericMessageScreen` x3 �� `ProgressScreen` �� `LevelLoadingScreen`;
* ���:`Preparing spawn area` / `Preparing start region` / `Time elapsed` / `Loaded recipes+advancements` /
  `Changing view distance to` / `Generating keypair`,���� **`joined the game` = True**;
* ��Ӱ�� `MakeUp-UltraFast-9.5e.zip` **loaded**;NullPointerException 0;
* ����� **2 �ݱ�������**,���Ҷ���ͬһ������:

```
[Server thread] Description: Exception in server tick loop
java.lang.NoSuchMethodError: 'int net.minecraft.server.MinecraftServer.m_7186_(int)'
  at net.minecraft.server.level.ChunkMap$TrackedEntity.scaledRange(ChunkMap.java:1541)
  at ...ChunkMap.addEntity -> ServerChunkCache.addEntity

[Render thread] Description: Unexpected error
java.lang.NoSuchMethodError: 'boolean net.minecraft.client.server.IntegratedServer.m_129918_()'
  at net.minecraft.client.renderer.GameRenderer.tryTakeScreenshotIfNeeded(GameRenderer.java:1236)
  at ...GameRenderer.render
```

������һ�ֵ� `MinecraftServer.m_195518_()`(`isSaving()`),���Ѿ���**����**ͬ���Ա:���� **vanilla �� SRG ����**��
����**�������Լ�����**(`ChunkMap$TrackedEntity`��`GameRenderer`)����,�����ʱ�õ��Ƿ�����������**��û��**��
���ǲ��ڿ� classpath ��׮����(��� MC �汾�� jar ��"ȱʧ"��������),������խ classpath ��һ����������**���г���
��**(`m_7186_` �������� 46 �����嵥��)���� �����û���ӡ֤:**ȱ�ݲ����غ�,������������õ���������ͼ/������ͼ
������**��

**�����һ�಻�������׮**,����������:��׮������"Ĭ����",��������������Ĭ��ֵ��ѹ�������Ū�� ����
`m_7186_(I)I` ��ʵ��׷�پ��������(`scaledRange`),���� 0 ����ʵ��׷��ֱ��ʧЧ;`m_129918_()Z` ��
`isPublished` һ���״̬��ѯ,���� false Ҳ������ʵ��**��ȷ�޷�������������Ĵ�����Щ��Ա**(�����غ�����������
�����϶���),�����ڱ�"ÿ����һ�Ų�׮��"������޸�,����Ϊ���ֵĽ�������һ���������

**��״С��(��ʵ)**:1.21 �����������硢�� join���ܼ��ع�Ӱ��,������ join ֮��� tick ѭ������ȱ��Ա����,
����**���ܼǳ���**���������� 1.21.x ���� ModelPart / IntegratedServer �Ķ�֮��û���ܽ�����Ӱ��

### ��λ����������Ҫ�Ľ���:**������֧���غ��� SRG ����,������õ��������಻��** ���� 1.20.2/1.20.4 ���꿿 `SrgRemap` �����ͬһ����,1.21.x ��û����

��һ��������ȱʧ��Ա(`MinecraftServer.m_7186_(I)I`��`m_195518_()Z`��`IntegratedServer.m_129918_()Z`)
һֱ������"���ǵ���������ͼ������"����һ�ְ����鵽�˵�,��������:

| ��� | ��� |
|---|---|
| �����ߵĹ�����ͼ `work\<mc>\runtime-<mc>.jar`(`javap`) | 1.21 / 1.21.1 / 1.21.3 / 1.21.8 **��û��**��������Ա |
| ����õĿͻ��� jar `libraries\net\minecraft\client\1.21-20240613.152323\client-1.21-...-srg.jar` | �� `MinecraftServer`��`IntegratedServer`,�� **ͬ��û��** `m_7186_` / `m_195518_` / `m_129918_` |
| ͬһ������ֶ��� | `private volatile boolean isSaving` ���� **Mojang ��������**,���� SRG �� `f_..._` |
| NeoForge universal jar | ������������(��ֻ�ǲ�����) |
| ���� jar ����Щ�����Դ | `optifineoforge/patched/.../ChunkMap$TrackedEntity.class`��`GameRenderer.class`��`IntegratedServer.class` ���� **�غ��Լ��ı�����ﱻװ�˽�ȥ** |

Ҳ����˵:��������������(`ChunkMap$TrackedEntity.scaledRange` �� `MinecraftServer.m_7186_(I)I`��
`GameRenderer.tryTakeScreenshotIfNeeded` �� `IntegratedServer.m_129918_()Z`)**�����غ��Լ�����**,���غ��ǰ�
**SRG ��Ա��**�����(OptiFine 1.21 �Ĺ��������),����õ�����������Щ��Աȴ�� **Mojang ��**���ⲻ��"��ͼȱ��Ա",
����**��������û�ж���** ���� Ҳ���� 1.20.2 / 1.20.4 �������������һ��:`SrgRemap` ���غɵ� SRG ����д�������ڵ�
official ��(`add-line.ps1` �� `-SrgMappings` + `-ObfOfficial`,���� `proguard-to-tsrg.ps1` ���� obf��official ��)��
**1.21.x ����Щ��ȫ������û����һ��������½���**,����:
* �����**�غ��Լ�����֮��**�ĵ�������(ͬһ���������,����һ��),�����Ϊʲô��Щ�������ܽ�����;
* һ���غɵ�**������**ȥ��**������**�� vanilla ��Ա(�����ǰ� SRG ��д��),�ͻ� `NoSuchMethodError` ����
  ������ȡ�����ܵ���������·��,���Ա���Ϊ"�е��߿���û�¡��е��� join ֮���"��

**��һ��(��һ�ֵĵ�һ����)**:�� 1.21.x �� `SrgMappings`(MCPConfig �� joined tsrg)�� `ObfOfficial`
(`proguard-to-tsrg.ps1` �� Mojang client mappings ����,1.20.x ���� `obf-official-1.20.4.tsrg` ������),
�� `add-line.ps1 -SrgMappings ... -ObfOfficial ...` �ؽ�����һ����(���� 1.21.4 �� 1.21.8,������������+��Ӱ
��¼�ɶ���),Ȼ�����������뽨����Ӱ��**�����һ������,��Ӧ��һ����������ȱ��Ա����**,�����Ǽ���һ����Աһ����Ա�ز�׮
���� ������֧������������ `stub-additions` ��������Ա��

#### ����һ�ڽ��۵�**��խ**(ͬһ���ڵ����Ҹ���,�����ں�)

��һ��д��"������֧���غ��� SRG �������������� Mojang ����"��**���˵��̫ǿ,��������խ**:���غ�
`ChunkMap$TrackedEntity` ���ֽ��������� `m_`/`f_` ��ʽ�ĵ��ó��������һ�� ���� һ��ֻ�� **2 ��**
(`ServerLevel.m_7654_`��`MinecraftServer.m_7186_`),����������õĿͻ��� jar �ﶼ**������**;�����������
������ SRG ����Ҳ����˵�ⲻ��"�����غɰ� SRG ����",����**�غ������������ SRG ������**(���ֹ۲쵽�����
һ�� 3 ����Ա),����������һ���� Mojang �� ���� ����û���롣

��ȷ����һ����˸�����,���� rig ��**�Ѿ����ֳɵĹ���**:

1. �� rig �Լ��� `SrgResidue` ����⼸�� 1.21.x �غ�(1.20.4 ���ܹ�,������ `logs\srgresidue-1.20.4-*.txt`),
   ��"���ж��� SRG ������ֱ����ļ�����"������,�����ǿ�����һ����ײ;
2. 1.20.x �����߽�����������������:`SrgRemap` ���غɵ� SRG ����д����������(`add-line.ps1` ��
   `-SrgMappings` + `-ObfOfficial`),���� `41b4bb4` �޹�"���Ӽ����������û�й�һ"����ò���©����ԭ��
   ���� 1.21.x ��δ������һ��,Ҳû�ж�Ӧ�� `obf-official-1.21*.tsrg`;
3. ����**һ��**��������+��Ӱ��¼��������(1.21.4 �� 1.21.8),�������� + ������Ӱ,�����ж�"������ɾ�֮��
   ��������Ƿ���ʧ"��**������֧������������ `stub-additions` ��������Ա**(Ĭ�����ѹ�������Ū��)��

### SRG �����**Ԥ�������**��������:1.21 ������ 114 ������(��������������Ա����������),������ 0 ��

��һ�ְ�"�غ���������� SRG ��"�����������һ�롣��һ���� rig ��**����е�** `SrgResidue` ���߰�����������:
�ȸ� 1.21 ����(neoform zip ��� `config/joined.tsrg` + `proguard-to-tsrg.ps1` �� Mojang mappings ���ɵ�
`obf-official-1.21.tsrg`,8269 �� / 37906 �ֶ� / 73419 ����),Ȼ����:

```
java -cp <tools> kynarain.cn.optifineoforge.optifine.SrgResidue joined-1.21.tsrg <neoform-merged> \
     work\1.21\optifine-patched.jar work\1.21\runtime-1.21.jar
```

| �� | SRG ��״���� | �غ��Լ������� SRG �� | residue |
|---|---|---|---|
| **1.21** | **306** | **84** | **114 distinct**(ȫ�� "no table entry") |
| 1.21.4 | 0 | 0 | **none - every SRG reference resolves to a runtime member** |

�ؼ���:��������ϱ����������Ա**����������** ����
`Gui -> MinecraftServer.m_195518_()Z`��`GameRenderer -> IntegratedServer.m_129918_()Z`��
`ChunkMap$TrackedEntity -> MinecraftServer.m_7186_(I)I`��Ҳ����˵������**�����ڱ����Ѽ����г���**
(�����嵥�� `logs\srgresidue-1.21.txt`,306 ������ / 114 ��ȥ��,ȥ�غ��ѡ 63 ��)��
**���� 1.21.4 �Ǹɾ���** ���� �����ⲻ��������֧������,����**1.21.0 �������Լ��� OptiFine ����
(`OptiFine_1.21_HD_U_J1_pre9`)�Ǹ������:�󲿷ֳ�Ա�� official ��,ȴ���������� SRG ������**��

#### ����"����������"��·,��������**���**��

1. **���ɸ��µ� OptiFine ����** ���� ����:1.21.0 ����ʽ�� `OptiFine_1.21_HD_U_J1.jar` �� optifine.net ��
   ���¼�(`File not found.`),����(`bmclapi2`)�� `1.21/HD_U_J1` �� `1.21/HD_U_J1_pre9` ���� 404��
2. **�� 1.20.2/1.20.4 ������ `SrgRemap`** ���� ����,���������**��������**����:`prepare-line -SrgMappings
   joined-1.21.tsrg -ObfOfficial obf-official-1.21.tsrg` ����������
   `rewrote 0 method and 0 field names, 34261 could not be resolved, 0 renames refused`;���غɻ���С��
   (5420805 �ֽ�),plan ���ǵ� **6167 �� / 388 donor**(����ԭ���� 381/99)������Ϊ `SrgRemap` ������ٶ���
   obf/SRG �������غ�,�������ߵ��غ�**�󲿷��Ѿ��� official ��**,����û�ж�Ӧ��,����"ʲô��û��"��
   ���ʵ����غ��� runtime ��ͼ**�ѻ���**(`*.pre-srgremap`)��

#### ʣ�µ�·(��һ�ֵĺ�ѡ,����������)

1. **��Բ��������**:114 ��ȥ������һ������**�غ��Լ��ڲ���֮���������**
   (`RenderSystem$AutoStorageIndexBuffer` ���Լ��� `f_157469_` �� 9 ����`GlStateManager$*` һ�塢
   `ParticleEngine$ParticleDefinition` һ��),ֻҪ�غ��Ƿ��౻װ��ȥ�����Լ�����;�����絽**������**��
   ����������������:`Gui`��`ChunkMap$TrackedEntity`��`GameRenderer`��`DebugScreenOverlay`��`TitleScreen`��
   `ClientLevel`��`ClientLevel$EntityCallbacks`��`VertexBuffer`/`VertexConsumer`��`ModelPart`
   (`ModelPart.m_171324_` ���� `getChild`,�������Ѿ��Ѹ������ݻ��������ڵ���)��
   ���е�������**�� owner ���������д**(��ͬ�� rig ��� `SrgMemberMap`,���غ�����Щ `m_*/f_*` ���ø�д��
   �����ڵ� official ��),�����ǰ� obf ���ֿռ����� remap ���� ��Ҳ�� `SrgResidue` �� `classify()` �Ѿ��������ж�,
   ֻ��һ��д�ز��衣
2. ���߶��б����Ǽ���"OptiFine �������ǹؼ�"����(�� `ChunkMap$TrackedEntity`)�Ȱ����ౣ�����,�ѱ�������С,
   ����������ʣ�� ���� ������**Ȩ��**,����� `Gui` ���ֱ�ȻҪװ�غɵ��ࡣ

#### ��β״̬��һ��**�µĲ���**(��ʵ��,��һ�ֱ����)

������������·�������˺ۼ�,�����������:

* `SrgRemap` �Ǵ�ʵ��������غ��� runtime ��ͼ**�ѻ���**(`work\1.21\*.pre-srgremap`),ʧ��ʵ��д�µ�
  plan(6167 �� / 388 donor)**����������**:�ñ���֧Դ���������������� + ��� donor Ŀ¼������,
  ������ **plan 381 �� / donor 99 �� / keep plan 3 �� / interface plan 13 �� / access plan 255 ��**,
  �������ߴ�ǰ���õ���һ��ͬ����,registered jar ���ؽ�(`add-line.ps1 -SkipInstall`)��
* �ؽ��� 1.21 ������������Ȼͨ��(STARTED / user yes / sound yes / ���� 0),**�� stderr �Ӽ�¼ֵ
  **14141** ��� **46767** �ֽ�**([OptiFine] 241 ��)������һ��**�µġ���δ���͵Ĳ���**,�����Ȳ�����̸����������
  ���� ����ܵ�ԭ����:��һ�ε� plan/donor ����**����֧Դ��**�������������,����ǰ�Ǵ��õ���
  `tools-classpath.txt` ���Ƿ�**��ķ�֧ʱ��**��������(`tools-patch-keepfix`,��һ���Ѽ�¼��"���Լ�������"),
  ���������������ļƻ���ͬ ? �غ��ﱻ"������"�ĳ�Ա��ͬ ? OptiFine �� Reflector ����(��������־)��֮�仯��
  ����������һ����һ�� A/B ���ܶ�(ͬһ���غ�,��������������һ�� plan,�ֱ��ؽ���� stderr ��
  `logs\srgresidue-1.21.txt` ����������)��

#### ���˺��һ�θ���:��Ϊ������ǰһ��(��ʵ��)

�û��˲��ؽ���� 1.21 jar ������һ�β˵�����(`logs\world-121-run9.txt`):�����б����,����һ��**û��������**
(`world row click: never landed`,��������),**���� 0��NPE 0** ���� ��֮ǰ�� run6/run7 ͬ��(run8 �ɹ���������),
Ҳ����˵**�������������û�иı���Ϊ����**,ֻ�ǰѱ���Ⱦ�ļƻ���������������(381/99)����ڵ�"��ʱ����ʱ����"
����������Ȼ��������,��һ����Ҫ�Ȱ�"ѡ���� �� �� Play"����ȷ���Բ���(������֪������:Play ��δѡ����ʱ�ǽ��õ�,
���ҵ��е��ֻ��һ������ѡ��)��
1.21 �� stderr ����(46767 vs ��¼ 14141)�Թ���,���Ƽ������һ��,δ�� A/B��

### �� `SrgResidue` ���嵥**������ 1.21 �ı���**:�����޺ò�����,����������˴�����޷�

���������嵥(����һ��),��һ�ֲ��ٿ�����ײ,�������嵥��:

1. **`ChunkMap$TrackedEntity` ���ౣ�������ڰ汾** ���� �غ��Ƿݵ��� `MinecraftServer.m_7186_(I)I`
   (SRG ��,������û��),����ʵ��׷�ٵ� tick ·�����ؽ�ʱ��ӡ
   `patch entries dropped for 1 class(es): [net/minecraft/server/level/ChunkMap$TrackedEntity]`,**�÷������������ʧ**
   (���� `logs\world-121-run11.txt`:��������� **2 �ݽ��� 1 ��**)��
2. **`IntegratedServer.m_129918_()Z` ���ɷ��� false**(= "not published")���� �غ� `GameRenderer` ��
   `tryTakeScreenshotIfNeeded` �����,�� `GameRenderer` �ǹ�Ӱ���ӡ�**����**���ౣ�����׮����һ�����ٱ���
3. **������(`m_182649_()Ljava/util/Optional;`,ͬһ���������ŵ�һ��)������"��
   `tryTakeScreenshotIfNeeded` ��������屣�������ڰ汾"���� ����޷��������ѻ���**,��Ϊ����
   `build-jars` �����**��������� patch ��Ŀ����**
   (`patch entries dropped for 1 class(es): [net/minecraft/client/renderer/GameRenderer]`),
   Ҳ���� **OptiFine �� `GameRenderer`(��Ӱ���)�������ᱻװ��ȥ** ���� �ǲ���"�޲���",����**���Ķ����Ӱ֧��**��
   ��������Ϊ����д�� keep �ƻ���ע���
   �����һ���о�:**�� `Optional` ���෵�ض���ķ�������׮�᷵�� null**,���÷� `Optional.isEmpty()` ֱ�� NPE,
   ������һ�岻���ò�׮����ȥ��

**��һ�ֵ�ֱ�ӽ���**:`tryTakeScreenshotIfNeeded` �������� SRG ����(�Լ� `DebugScreenOverlay` ��
`IntegratedServer.m_306290_/m_304767_/m_129880_` �ĵ���)ֻ�ܿ�**�����д**(���غ�����Щ `m_*` ���øĳ�������
official ��,��ͬ�� rig ��� `SrgMemberMap`,`SrgResidue.classify()` �Ѿ���"�ٷ�����ʲô"�������)����
"����ȷ�������׮"����,������һ�ֵĵ�һ���¡�1.21 ���ڵ�״̬��:�������硢�� join���ܼ��ع�Ӱ��,
**ʣ�� 1 �ݿͻ��˱���**(`m_182649_`)�����δ�����Ĳ����

### 1.21 **��һ��������ͨ"���� + ��Ӱ��"�������**(join �� �Զ����� �� 0 ����),�Լ���һ���õ��о�

������һ�ڵ��嵥��������,��һ�ְ�ʣ�µ���������,���ҸĶ���һ��**����������**:

1. **������:`Optional` �������Ͳ��ܲ� null**��`defaultBody()` ���ڶ� `java/util/Optional` ����
   `Optional.empty()` ������ `aconst_null` ���� ��������������:���÷�һ��д `isPresent()/isEmpty()/orElse()` һ���д��,
   null ֻ�ǰ�"ȱ��Ա"���һ�� NPE(`GameRenderer.java:1238` �Ǵ���������)������**��Ʒ�����**,����ÿ���ߵĲ�����
2. `IntegratedServer.m_182649_()Ljava/util/Optional;` �� `m_129921_()I`(����ͬһ����������,SrgResidue ���й�)����׮��:
   ǰ�������������������򷵻� `Optional.empty()`,���߷��� 0��
3. `BakedModel.useAmbientOcclusion(BlockState, RenderType)Z`(NeoForge ��������չ��ʽ,�غ�
   `ModelBlockRenderer.tesselateBlock:86` ����)��׮���� false��

**����(`logs\world-121-run13.txt`,�˵����� + MakeUp ��Ӱ��)**:

```
reached a world : True      joined : True
world markers   : Preparing spawn area, Preparing start region, Time elapsed, Loaded recipes/advancements,
                  Changing view distance to, Saving and pausing game, Generating keypair
shader pack     : [Shaders] Loaded shaderpack: MakeUp-UltraFast-9.5e.zip
crash reports   : 0         NullPointerException : 0
```

����������**��һ��������ͨ**"������ �� join �� �Զ����� �� �˳�ǰ�����",���ҹ�Ӱ��ȷʵ���ء���������ͬʱ����ͨ��
(STARTED / user yes / sound yes / ���� 0)��

**��ʵ������һ���޷��Ĵ�����߽�**(�����ö�����Ϊ 1.21 �Ѿ��ɾ�):
* `useAmbientOcclusion(...)` ��׮�ش� **false**,���Ǹ�ģ��**�����������ڱ�** ���� ����**�ɼ����Ӿ�ƫ��**,���Ǳ�����
  ����"������"��׮(����Ĭ��ֵ)ֻ�������������ܲ�,**����**�����޷�;
* �������޷�����**�����д**:���غ�����Щ�絽�����ڵ� `m_*`/��չ��Ա���ø�д���������Լ��ĳ�Ա
  (`SrgResidue.classify()` �Ѿ�����ٷ���,`SrgMemberMap` �� rig ��),��һ����δ��;
* 1.21 �� **stderr ��¼��������**:������ͨ��,�� stderr �� **46767** �ֽ�(��¼ֵ 14141),���Ƽ���(�������汾��ͬ)
  ��δ A/B;
* �������� 1.21.x ��**��û���ܽ�����Ӱ**(������ ModelPart / IntegratedServer �����Ķ�֮����Ҫ����),
  �����Ǹ����Ƿ�Ҳ�������ߵ� `SrgResidue` �嵥֮���ȱʧ��Ա,Ҫ��ͬһ�װ취��������

### �����װ취�̵�����������:5/7 ������"������ + ��Ӱ������ + 0 ����"

��һ���޺� 1.21 ֮��,��ͬһ�װ취�������ߵ����ȱʧ��Ա**�̵�����������**
(`stub-additions-<mc>.txt`;װ����ֻ�ڸó�Աȷʵȱʧʱ��ע��,�����ڲ���Ҫ�������Ƕ��Ե�),�����ؽ�������
���� + ��Ӱ��(quick play,RigSession,MakeUp-UltraFast-9.5e):

| �� | �̿�ǰ | �̿��� |
|---|---|---|
| 1.21.1 | **FAILED**(���� 1;���� yes����Ӱ loaded) | **STARTED / world yes / ��Ӱ loaded / ���� 0** |
| 1.21.3 | **FAILED**(ͬ��) | **STARTED / world yes / ��Ӱ loaded / ���� 0** |
| 1.21.4 | STARTED / world yes / ��Ӱ loaded / ���� 0 | ͬ��(�̿��Ƕ��Ե�) |
| 1.21.8 | STARTED / world yes / ��Ӱ loaded / ���� 0 | ͬ�� |
| 1.21.6 | STARTED / world yes / **��Ӱ��û����** | ͬ��(�뱾���޹صĶ���ȱ��) |
| 1.21.7 | STARTED / world yes / **��Ӱ��û����** | ͬ�� |
| 1.21(�˵�·) | join + ��Ӱ loaded + ���� 0 | ͬ�� |

������ϸ��:
* 1.21.1 / 1.21.3 ��ǰ�ı�������**ͬһ����Ա**(�������̿�ǰû���⼸����׮),�̿������߶���� 0 ���� ���� ��Ҳ
  ������˵��"�����嵥�Ǳ��غɼ���Ĺ���,���� 1.21 ������";
* `BakedModel.useAmbientOcclusion(...)` ��׮��Ȼ�ش� false,��**�Ǹ�ģ�Ͳ����������ڱ�**,�������������ϵ�
  **�ɼ��Ӿ�����**,������Ӧ��"�����д"���(����һ��)��

**��δͨ������������ԭ��**(��ʵ):1.21.6 / 1.21.7 ���������Ӱ��Ҫôȱһ:
`Failed to parse post chain at minecraft:post_effect/fxaa_of_2x.json | JsonSyntaxException: No key
fragment_shader / No key vertex_shader` ���� 1.21.5 ���������ʽ�� `shaders/post`(vertex/fragment)����
`post_effect`(vertex_shader/fragment_shader),�������ߵ� OptiFine �������Ǿɸ�ʽ;Ҫ�������Ի�Ƿһ�ζ���
(ͬһ�� OptiFine �Ž�ͬ�汾��**����**���Ǽ������� NeoForge ��һ��,���Ƿ�ͬ������ ���� �ǲŽ�"OptiFine ��汾������",
���ڵ�˵��ֻ��"�غ����������ƥ��")��

### 1.21.6 / 1.21.7 ��Ӱ������ʧ��**��λ�������ֶ�**:�� OptiFine �Ƿ� JSON �� schema ����ڸð汾,���Ǽ�����

�������汾�� OptiFine jar(1.21.4 ���á�1.21.6/1.21.7 ������)�����ǵ��غ� jar ����ȽϺ�,��ʵ��:
**���� jar �����ĸ���Դһģһ��**(`assets/minecraft/post_effect/fxaa_of_{2,4}x.json` �� 620 �ֽڡ�
`assets/minecraft/shaders/post/fxaa_of_{2,4}x.json` �� 719/443 �ֽ�),Ҳ����˵**��Դû�б����ǵ���ˮ�߶���**,
�����������Դ������� 620 �ֽ��Ƿݴ򿪿�,���� schema ��:

```json
{ "targets": { "swap": {} },
  "passes": [ { "program": "minecraft:post/fxaa_of_2x", "inputs": [ { "sampler_name": "In", "target": "minecraft:main" } ], "output": "swap" },
              { "program": "minecraft:post/blit",        "inputs": [ { "sampler_name": "In", "target": "swap" } ],         "output": "minecraft:main" } ] }
```

�� 1.21.6/7 �Ľ�����Ҫ����**ÿ�� pass �Դ� `vertex_shader` / `fragment_shader`**(����ԭ�ľ���
`No key fragment_shader in MapLike[{"program":"minecraft:post/blit","inputs":[...],"output":"minecraft:main"}]`
�Լ�ͬһ����� `No key vertex_shader`)���� Ҳ���� 1.21.5 �� 1.21.6 ֮��**������ schema �ָ���һ��**,�������ߵ�
OptiFine Ԥ�������԰� 1.21.5 ��д����**����ⲻ�����Ǽ�������ȱ��**(ͬһ����Դ��ԭʼ jar ���������),
����"�غ��������ڰ汾�� schema ��ƥ��"��

**��ѡ�޷�(���嵽��������,��һ����)**:�����ǵ� jar �ṩһ�ݸ�д���� schema �ĸ�����Դ
(`assets/minecraft/post_effect/fxaa_of_{2,4}x.json`,��ÿ�� pass �� `"program": X` ����
`"vertex_shader": X, "fragment_shader": X`,���� `inputs`/`output` ����;���� program ��
`minecraft:post/fxaa_of_2x`��`minecraft:post/blit` ������ OptiFine �ľɸ�ʽ�ļ��ﱻ����,˵�����Ǵ���)��
�ж���׼�ܼ�:�ؽ����� 1.21.6/1.21.7 �����ܽ�����Ӱ,�� `[Shaders] Loaded shaderpack:` �Ƿ���֡�
`Failed to parse post chain` �Ƿ���ʧ��

#### ����"�����ǵ���Դ�������� post chain",**û��Ч**(��ʵ��,��������һ����Ӳ�ĺ�ѡ)

����һ�ڵĺ�ѡ�޷�,�Ѹ�д���� schema �� `assets/minecraft/post_effect/fxaa_of_{2,4}x.json`(ÿ�� pass ��
`vertex_shader`/`fragment_shader`)�Ž�����֧�� `src/main/resources`,�ؽ� 1.21.6 / 1.21.7 ������:

* ��Դ**ȷʵ���˽��� jar**(`javap`/zip ���:`assets/minecraft/post_effect/fxaa_of_2x.json`,724 �ֽ�);
* �������־**һ��δ��**,����������**������**(`{"program":"minecraft:post/blit", ...}`),
  Ҳ���ǿͻ���**��Ȼ������ OptiFine �Ƿ�**,���ǵ� mod ��Դû��Ӯ����(�غ��Ƿ�����"��װ����Ϸ jar ����Դ"��ݴ��ڵ�,
  ���ȼ����� mod ��Դ��)��**��������·�߰����ڵ�����������**��

**��һ����ѡ��(��Ӳ������,��һ����)**:�Ѹ�д**ֱ������������ OptiFine jar**(�� `build-jars`/
`OptifineJarFixer` װ����һ��,�� `assets/minecraft/post_effect/fxaa_of_{2,4}x.json` �滻���� schema),
����"˭�ڶ�"��"���ķ�"����ͬһ���ļ�,���������ȼ�����;�ж���׼���� 1.21.6 / 1.21.7 ��
`[Shaders] Loaded shaderpack:` �Ƿ���֡�`Failed to parse post chain` �Ƿ���ʧ��
��:��������**����ʼ���Ǻõ�**(world yes������ 0��4 �� region �ļ�),����ȱ��ֻ����һ����

### ������β:ʮһ�� ModLauncher �ߵ���������**ȫ������**(��ǰ jar),���� stderr ������ʵ����

`retest-all.ps1 -Only 1.21,1.21.1,1.21.3,1.21.4,1.21.6,1.21.7,1.21.8,1.20.1,1.20.2,1.20.4,1.20.6`
(`logs\retest-modlauncher-round154.txt`):

| �� | �ж� | user | sound | ���� | stderr | ���¼ֵ |
|---|---|---|---|---|---|---|
| 1.21 | STARTED | yes | yes | 0 | 46767 | **����**(��¼ 14141) |
| 1.21.1 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.21.3 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.21.4 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.21.6 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.21.7 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.21.8 | STARTED | yes | yes | 0 | 0 | = ��¼ |
| 1.20.6 | STARTED | yes | yes | 0 | 17856 | ��֪����(��ʮ�� �Ѷ�λ) |
| 1.20.4 | STARTED | yes | yes | 0 | 14481 | = ��¼ |
| 1.20.2 | STARTED | yes | yes | 0 | 14631 | **���� 6 �ֽ�**(��¼ 14625) |
| 1.20.1 | STARTED | yes | yes | 0 | 0 | = ��¼ |

�������춼Ҫ�ڷ���ǰ�����д��:
* **1.21:46767 vs 14141** ���� ���Ƽ���(����֧�������� `tools-classpath.txt` ��ɷ�֧�����������ļƻ���ͬ)δ�� A/B;
* **1.20.2:14631 vs 14625** ���� ֻ�� **6 �ֽ�**,ͬ������δ���͵�С����(�ܿ���Ҳ�Ǽƻ�/�������汾�����������־�в�),
  �����߱���Ҳ�ؽ����ƻ�,���������¿���ͬԴ��

### 工程目录搬到 `I:\mods`(环境变更,已复验),以及一次**被我改坏又还原**的文档编码事故

* 三个仓库(`OptifiNeoforge` / `-120x` / `-26x`)与 rig(`optifineoforge-test`)整体移到
  **`I:\mods\...`**;`.gradle` 按选择**留在 C:** 不动(其它项目不受影响)。C: 释放约 6.3 GB(现 31.0 GB 空闲),
  I: 余 577.7 GB。
* 脚本/配置里的绝对路径已同步改写(14 个文本文件);**两个 worktree 用 `git worktree repair` 修好**
  (它们的 `.git` 原来指向 `C:\...\OptifiNeoforge\.git\worktrees\...`),现在 `git worktree list` 显示
  三条线都在新位置、分支未变(`1.21.x` / `1.20.x` / `26.x`),工作区干净。
* **复验**:在新位置跑 `retest-all.ps1 -Only 1.20.4` ⇒ STARTED / user yes / sound yes / 崩溃 0 /
  stderr **14481 = 记录值**(natives 也从 `I:\mods\optifineoforge-test\natives` 解析);rig 的十个主要脚本
  PowerShell 解析 0 错误。
* **事故(如实记)**:我做路径改写时用 `Get-Content -Raw` 读、`Set-Content -Encoding UTF8` 写,而 PowerShell 5.1
  的 `Get-Content` **默认按 ANSI(GBK)解码**,于是两份分支文档(`1.20.x` 的 `docs/MATRIX.md`、
  `26.x` 的 `docs/DEVELOPMENT.md`)被"UTF-8→GBK→UTF-8"破坏成乱码并少了 9 行。**已用 git 精确还原并推送**
  (`1.20.x` a4b08d7、`26.x` 3e64e89,提交信息写明了原因),两份文档现在与改写前**逐字节一致**;rig 侧也做了一次
  反向解码。教训写在这里:**这台机器上读写 UTF-8 文本必须显式给 `-Encoding UTF8`**(读也一样)。
