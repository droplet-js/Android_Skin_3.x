# 前言
基于 Android Skin 2.x 修改而来，替换换肤解决方案，让换肤变得更加简单。
无须对项目作出太大修改，就可以完美实现换肤功能。
支持 Android 2.1(API>=7)，支持 AppCompat，支持普通的 new View（需手动添加换肤代码）。

# 特别申明
使用该代码用于生产时，请勿变更其类包名

# 原理
Android_Skin 是基于资源引用的唯一性而搭建的替换同名同类型资源的换肤支持。
通过重写LayoutInflater方法，在 xml 布局里面的视图初始化后，对其做相应的换肤功能的补充，得以根据资源引用的唯一性实现替换视图里面若干属性的。

# 缺陷
1.最致命的缺陷是对普通的 new View 的支持，稍显不足。
不过对 new CompatView 的支持力，绝对是杆杆的。
在替换法里面 CompatView 是用来替换 View 的，因为换肤的实现是在 CompatView 里面实现的。

2.对工程里的自定义视图，建议是改变继承，继承相对应的 CompatView，这样可以直接让自定义视图拥有换肤功能。

3.要实现对 Library 之外的视图属性换肤功能比较麻烦。
首先要继承相应视图，实现 EnvCallback 和 XUICall 两个接口；其次重写相应换肤属性的函数，以实现换肤资源引用的替换和移除；最后可能还要写一个 EnvUIChanger。

# 效果
1.编译 demo，查看效果

2.下载体验麦潮(http://www.varicom.im/)。

# BUG 修复
2015.6.10 - 2015.6.16：修正若干BUG（“麦潮”App的Android研发反馈）

2015.6.18：兼容Android5.1（“麦潮”App的Android研发反馈）

2015.9.2：ClassLoader 换肤模式有 BUG，换回 LayoutInflater 换肤模式（“睡啊”App的Android研发反馈）

2015.9.10：修正 TextView 和 ListView 在动态换肤上出现的 BUG （“睡啊”App的Android研发反馈）

2015.11.3：修正 5.x 上资源映射错误，并兼容 6.x 上取 Color 和 ColorStateList 资源（“麦潮”App的Android研发反馈）

# 重大版本迭代
2015.7.3：Library 由 2.2 升级到 2.4。新增EnvViewMap。支持额外视图替换（AppCompat的支持不在话下）。

2016.1.6：Library 由 2.x 升级到 3.x。去除重写LayoutInflater、Context。

# SDK 应用
1.环境设置 EnvSetup: Library 提供了三种环境设置 NormalEnvSetup、NullEnvSetup 和 SharedPrefSetup。推荐使用 SharedPrefSetup

eg.
```Java
EnvResManager.getGlobal().setEnvSetup(SharedPrefSetup.getGlobal());
```

2.皮肤校验机制 SkinChecker: 受限于Library的安全机制，皮肤包校验机制一定要去校验皮肤包的包名。Library 提供了两种 NormalSkinChecker（校验皮肤包名） 和 VerSkinChecker（校验皮肤包名和皮肤版本号）。推荐使用 VerSkinChecker。

eg.
```Java
EnvResManager.getGlobal().setSkinChecker(VerSkinChecker.newInstance(1000, false));
```

3.继承EnvSkinActivity，实现动态换肤

eg.
```Java
List<SkinData> skinDatas = EnvExtraHelper.loadSkinDatas(this, getEnvResBridge(), PathUtils.getSkinDir(this), SkinFilter.DEFAULT_SKIN_FILTER, new SkinExtraCreator());
SkinData data = skinDatas.get(new Random().nextInt(skinDatas.size()));
SharedPrefSetup.getGlobal().setSkinPath(MainActivity.this, data.getSkinPath());
scheduleSkin();
```

4.添加额外视图支持

1).实现已有属性换肤功能，可以参照 CompatStatusBar，简单继承 Compat 视图即可

2).添加额外属性换肤功能，可以参照 CompatSwitchCompat，继承 SwitchCompat 添加额外属性换肤功能，且需使用 EnvViewMap 类中若干方法，注册需要换肤的视图


5.使用注意

1).Library 原生只支持 xml 布局里的视图换肤，且这些视图都是Android_SDK 提供的视图，非 Android_SDK 提供的视图，需要继承 Library 里的 Compat*** 视图。其他的换肤，需要重写 scheduleSkin() 补充所需换肤内容。

2).替换 Library 里面记住的引用的资源，请调用相应视图里面的与resid相关的属性设置函数。去除 Library 里面记住的引用资源，请调用相应视图里面的与resid无关的属性设置函数。

eg.

调用 setBackgroundResource(int resid) 函数可以将记住“可换肤的A资源”变成记住“可换肤的B资源”。

调用 setBackgroundDrawable(Drawable background) 函数可以将记住“可换肤的A资源”，变成不记住任何资源。

3).凡是 xml 类型的Color或Drawable资源，不论是 selector 还是 shape 等，有引用到 @color/*** 的需要换肤的颜色资源或@drawable/*** 的需要换肤的图片资源，这个 xml 需要拷贝一份到皮肤包里。这里涉及到 Resource 的缓存机制知识。

4).若出现下面这种情况，则引用到 color_2 的视图，其实际换肤里保存的是 color_1 的引用，即改变 color_1 才会使得应用 color_2 的视图得以换肤。在 drawable 里同样适用。
   
5).皮肤包的后缀名不要用 .apk，用其他任意后缀名都可以，防止用户点击安装皮肤包。另外，皮肤包里不需要任何类，导皮肤包时，最好是导未签名包。

# 其他
![](https://github.com/v7lin/Android_Skin_3.x/raw/master/art/Android_Skin_QQ_Group.png)

# License

   Copyright LinHenglong

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
