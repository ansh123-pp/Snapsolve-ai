# Snapsolve Android App

A basic Android WebView app with AdMob integration for displaying web content with advertisements.

## Project Structure

- **app/**: Main Android application module
  - `src/main/java/com/snapsolve/app/MainActivity.kt`: Main activity with WebView and AdMob setup
  - `src/main/res/`: Android resources (layouts, strings, themes, colors)
  - `AndroidManifest.xml`: App configuration and permissions
  - `build.gradle`: App-level Gradle configuration with dependencies

- **.github/workflows/**: GitHub Actions CI/CD pipelines
  - `build-apk.yml`: Automated APK building workflow

## Features

- **WebView Integration**: Display web content directly in the app
- **AdMob Integration**: Banner ads support
- **Material Design**: Modern Android UI components
- **Back Navigation**: Proper WebView back button handling
- **Automated Builds**: GitHub Actions workflow for CI/CD

## Prerequisites

- Android Studio (latest version)
- JDK 11 or higher
- Android SDK 24+ (API level 24 minimum)
- Gradle 8.4+

## Setup Instructions
### 1. Clone the Repository

```bash
git clone https://github.com/ansh123-pp/Snapsolve-ai.git
cd Snapsolve-ai
```

### 2. Configure AdMob

Edit `app/src/main/AndroidManifest.xml` and replace:
- `ca-app-pub-xxxxxxxxxxxxxxxx~zzzzzzzzzz` with your actual AdMob App ID

### 3. Update WebView URL

Edit `app/src/main/java/com/snapsolve/app/MainActivity.kt`:
- Replace `https://www.example.com` with your actual web URL

### 4. Add Ad Unit IDs

Update the following in `app/src/main/res/layout/activity_main.xml`:
- Replace `ca-app-pub-3940256099942544/6300978111` with your Banner Ad Unit ID
- Note: `ca-app-pub-3940256099942544/6300978111` is the test banner ad unit ID for development

### 5. Build the Project

Using Android Studio:
1. Open the project in Android Studio
2. Let it sync Gradle files
3. Click "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"

Or using command line:

```bash
./gradlew assembleDebug      # Build debug APK
./gradlew assembleRelease    # Build release APK
```

### 6. Run on Device/Emulator

```bash
./gradlew installDebug
```

## AdMob Test Ad Units

For development and testing, use these test ad unit IDs:

- **Banner Ads**: `ca-app-pub-3940256099942544/6300978111`
- **Interstitial Ads**: `ca-app-pub-3940256099942544/1033173712`
- **Rewarded Ads**: `ca-app-pub-3940256099942544/5224354917`

## Building with GitHub Actions

The project includes a automated GitHub Actions workflow that:

1. Triggers on push to `main` branch and PRs
2. Sets up Java 11 environment
3. Builds debug and release APKs
4. Uploads artifacts for download

APK artifacts are available in the Actions tab after successful builds.

## Project Configuration

### Target SDK Details
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Compile SDK**: API 34

### Dependencies
- AndroidX Core, AppCompat, Material Design
- Google Mobile Ads SDK (v22.6.0)
- JUnit, Espresso for testing

## Manifest Permissions

The app requires:
- `INTERNET`: For web content loading and ads
- `ACCESS_NETWORK_STATE`: For network status detection

## Proguard Rules

Release builds include ProGuard obfuscation. Custom rules are configured in `app/proguard-rules.pro`.

## Configuration Files

- `build.gradle`: Project-level Gradle configuration
- `app/build.gradle`: App module dependencies and build settings
- `settings.gradle`: Gradle project structure
- `gradle.properties`: Gradle runtime properties
- `.gitignore`: Git ignore patterns

## Customization

### Changing App Name
Edit `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">Your App Name</string>
```

### Custom Styling
Update `app/src/main/res/values/themes.xml` with your brand colors and styles.

### WebView Settings
Modify WebView configuration in `MainActivity.kt`:
```kotlin
webView.settings.apply {
    javaScriptEnabled = true
    domStorageEnabled = true
    databaseEnabled = true
}
```

## Building Release APK

### Generate Signing Config

1. In Android Studio: Build → Generate Signed Bundle / APK
2. Fill in keystore details (save for future builds)
3. Choose APK and Release variant
4. Complete the signing process

### Build Release APK via Command Line

```bash
./gradlew assembleRelease
```

The release APK will be located at: `app/build/outputs/apk/release/app-release.apk`

## Troubleshooting

### Gradle Sync Issues
```bash
./gradlew clean build --refresh-dependencies
```

### Build Cache Issues
```bash
./gradlew clean
```

### Dependency Conflicts
Check `app/build.gradle` and ensure all dependencies are compatible with API 34.

## Documentation References

- [Android WebView Guide](https://developer.android.com/guide/webapps/webview)
- [Google Mobile Ads SDK](https://developers.google.com/admob/android/quick-start)
- [Android Gradle Build System](https://developer.android.com/build)

## License

This project is provided as-is for educational and development purposes.

## Support

For issues related to:
- **AdMob**: Visit [Google AdMob Help Center](https://support.google.com/admob)
- **Android Development**: Check [Android Developers Documentation](https://developer.android.com)
- **GitHub Actions**: See [GitHub Actions Documentation](https://docs.github.com/en/actions)