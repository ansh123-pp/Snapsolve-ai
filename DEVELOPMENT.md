# Snapsolve Android WebView App - Development Notes

## Quick Start

1. **Install Dependencies**: Android Studio will auto-sync Gradle dependencies
2. **Configure AdMob**: Update constants and AndroidManifest.xml with your AdMob credentials
3. **Set WebView URL**: Update the URL in MainActivity.kt
4. **Build**: `./gradlew assembleDebug`
5. **Run**: `./gradlew installDebug`

## Key Configuration Points

### AdMob Integration
- App ID: Requires AdMob account setup
- Test Ad Units: Already configured for development
- Banner Ad: Positioned at bottom of screen
- Expandable: Can add interstitial and rewarded ads

### WebView Customization
- URL: Set in MainActivity.loadUrl()
- JavaScript: Enabled by default
- Local Storage: Enabled for web app functionality
- Mixed Content: Allowed (HTTP + HTTPS)

### Build Configuration
- Min SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Java: 11
- Architecture: ARM v8 (64-bit)

## GitHub Actions Pipeline

The workflow:
1. Triggers on push to main branch
2. Builds Debug APK (always)
3. Builds Release APK (best effort, doesn't block on failure)
4. Uploads artifacts for 90 days

## ProGuard/R8 Obfuscation

- Debug builds: Not obfuscated
- Release builds: Obfuscated with custom rules
- Protected: Google Play Services and AdMob SDK

## Testing on Device

```bash
# Debug install
./gradlew installDebug

# Debug with logcat
./gradlew installDebug && adb logcat SnapsolveApp:* *:E
```

## Device Requirements

- Android 7.0 or newer (API 24+)
- Internet connection
- ~50MB storage for app + dependencies
