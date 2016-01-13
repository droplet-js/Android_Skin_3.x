# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Java\Android_SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 为了方便查看外部崩溃 LOG 信息，便于定位修正 BUG
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes Deprecated
-keepattributes SourceFile
-keepattributes LineNumberTable
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes *JavascriptInterface*

# 关闭 Log日志输出
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# android layoutlib
-dontwarn com.android.internal.**
-keep class com.android.internal.**

# WebView 兼容 2.1 Ssl 验证
-keep class * extends android.webkit.WebViewClient { *;}

# WebView 的 JavaScript 支持
-keep interface com.v7lin.android.env.webkit.JSAccessor { *; }
-keep interface * extends com.v7lin.android.env.webkit.JSAccessor { *;}
-keep class * implements com.v7lin.android.env.webkit.JSAccessor { *;}

-dontwarn com.v7lin.android.env.**
-keep class com.v7lin.android.env.** { *;}
