# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# signAndroidRelease and signAndroidDebug tasks respectively.

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
# -keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
# }
