# Snapsolve App - Production Implementation Guide

## ✅ Production Status

This Android app is now configured for production with:
- ✅ Real AdMob App ID: `ca-app-pub-4662364870005689~7615646657`
- ✅ Real Rewarded Ad Unit ID: `ca-app-pub-4662364870005689/6417446173`
- ✅ Production WebView URL: `https://snapsolvev1.netlify.app`
- ✅ JavaScript Interface: `Android` bridge
- ✅ All test ad unit IDs removed
- ✅ Banner ads completely removed
- ✅ Production-ready code

## JavaScript Interface: Android Bridge

The app exposes a JavaScript interface named `Android` that allows your web app to trigger native ad functionality.

### Available Functions

#### `Android.showRewardedAd()`

Triggers the AdMob rewarded ad from your web application.

```javascript
// Call from your web app to show a rewarded ad
Android.showRewardedAd();
```

### Callback Functions

Your web app can define callback functions to handle ad events:

#### `onRewardEarned(rewardType, rewardAmount)`

Called when the user completes watching a rewarded ad.

```javascript
// Define this function in your web app
function onRewardEarned(rewardType, rewardAmount) {
    console.log(`Reward earned: ${rewardAmount} ${rewardType}`);
    // Handle the reward
    // - Update user balance
    // - Unlock features
    // - Grant premium content
    // etc.
}
```

**Parameters:**
- `rewardType` (string): The type of reward (set in AdMob console)
- `rewardAmount` (number): The amount of reward points/currency

#### `onRewardAdNotReady()`

Called when a rewarded ad is not available to show.

```javascript
// Define this function in your web app
function onRewardAdNotReady() {
    console.log("Rewarded ad is loading. Please try again in a moment.");
    // Show a message to the user
    // Disable the reward button temporarily
    // etc.
}
```

## Implementation Examples

### React/JavaScript Example

```javascript
// Define callback functions
window.onRewardEarned = (rewardType, rewardAmount) => {
    console.log(`User earned ${rewardAmount} ${rewardType}`);
    updateUserBalance(rewardAmount);
    showRewardNotification(`+${rewardAmount} rewarded!`);
};

window.onRewardAdNotReady = () => {
    console.log("Ad loading, please try again soon");
    showMessage("Ad is loading. Please try again shortly.");
};

// Trigger ad from a button click
const handleWatchAdClick = () => {
    if (window.Android) {
        window.Android.showRewardedAd();
    } else {
        console.warn("Android interface not available");
    }
};
```

### HTML Example

```html
<!DOCTYPE html>
<html>
<head>
    <title>Snapsolve App</title>
</head>
<body>
    <button onclick="watchAd()">Watch Ad - Earn Rewards</button>

    <script>
        // Define reward callbacks
        function onRewardEarned(rewardType, rewardAmount) {
            alert(`You earned ${rewardAmount} ${rewardType}!`);
        }

        function onRewardAdNotReady() {
            alert("Please wait for the ad to load...");
        }

        // Show rewarded ad
        function watchAd() {
            if (typeof Android !== 'undefined') {
                Android.showRewardedAd();
            } else {
                console.log("Not running in Android WebView");
            }
        }
    </script>
</body>
</html>
```

## Security Considerations

1. **JavaScript Interface Restrictions**
   - The `Android` interface is only available in the WebView context
   - Web browser access is blocked for safety
   - Only the `showRewardedAd()` method is exposed

2. **Ad Integrity**
   - AdMob handles fraud detection and prevention
   - Multiple ad shows trigger loading delays (by default)
   - Invalid clicks/interactions are filtered by AdMob

3. **User Privacy**
   - No personal data is exposed through the JavaScript interface
   - AdMob compliance with GDPR and other regulations
   - Ads are personalized based on AdMob settings

## Building and Deployment

### Build Debug APK
```bash
./gradlew assembleDebug
```

### Build Release APK
```bash
./gradlew assembleRelease
```

### Signing Configuration
Create a keystore file and configure it in `build.gradle`:

```gradle
signingConfigs {
    release {
        storeFile file("path/to/keystore.jks")
        storePassword "your_store_password"
        keyAlias "your_key_alias"
        keyPassword "your_key_password"
    }
}
```

## GitHub Actions Workflow

The project includes `.github/workflows/build-apk.yml` which:
- Automatically builds debug and release APKs on push
- Uploads artifacts for download
- Runs on every commit to main branch

## Testing

### Local Testing
1. Build the debug APK: `./gradlew assembleDebug`
2. Install on device: `./gradlew installDebug`
3. Open the app and test the rewarded ad button

### Web Testing
Before deploying to production:
1. Test all JavaScript callbacks in the WebView
2. Verify ad loading and display
3. Test reward callback functionality
4. Check error handling when ads fail to load

## Configuration Files Reference

| File | Purpose |
|------|---------|
| [Constants.kt](app/src/main/java/com/snapsolve/app/Constants.kt) | Production credentials |
| [MainActivity.kt](app/src/main/java/com/snapsolve/app/MainActivity.kt) | Main activity with ad logic |
| [AndroidManifest.xml](app/src/main/AndroidManifest.xml) | App manifest with AdMob ID |
| [activity_main.xml](app/src/main/res/layout/activity_main.xml) | UI layout |
| [build.gradle](app/build.gradle) | Dependencies and build config |

## Troubleshooting

### Ads Not Showing
- Verify AdMob account is in good standing
- Check that ad unit ID matches the app ID
- Ensure app is registered in AdMob console
- Wait 24 hours after first app publish

### JavaScript Interface Not Working
- Verify `javaScriptEnabled = true` in WebView settings
- Ensure callbacks are defined before calling `Android.showRewardedAd()`
- Check browser console for errors
- Use `typeof Android !== 'undefined'` to detect availability

### Build Errors
- Run `./gradlew clean` to clear build cache
- Check Java version is 11 or higher
- Ensure all dependencies are downloaded: `./gradlew build --refresh-dependencies`

## Next Steps

1. ✅ Update your website at `https://snapsolvev1.netlify.app` with the ad reward implementation
2. ✅ Deploy the app to Google Play Store
3. ✅ Monitor AdMob console for ad performance
4. ✅ Update reward logic based on user feedback

## Support Resources

- [Android WebView Documentation](https://developer.android.com/guide/webapps/webview)
- [Google Mobile Ads SDK](https://developers.google.com/admob/android/quick-start)
- [JavaScript in WebView](https://developer.android.com/guide/webapps/webview#BindingJavaScript)
- [AdMob Help Center](https://support.google.com/admob)
