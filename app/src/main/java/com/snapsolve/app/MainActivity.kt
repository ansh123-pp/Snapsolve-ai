package com.snapsolve.app

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.snapsolve.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var webView: WebView
    private var rewardedAd: RewardedAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Mobile Ads SDK
        MobileAds.initialize(this)

        // Setup WebView
        setupWebView()

        // Load initial rewarded ad
        loadRewardedAd()
    }

    private fun setupWebView() {
        webView = binding.webView

        // Configure WebView settings
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        // Set WebViewClient to handle navigation within the WebView
        webView.webViewClient = WebViewClient()

        // Add JavaScript interface for ad control
        webView.addJavascriptInterface(AndroidInterface(), "Android")

        // Load web content
        webView.loadUrl(Constants.DEFAULT_WEB_URL)
    }

    private fun loadRewardedAd() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(this, Constants.REWARDED_AD_UNIT_ID,
            adRequest, object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    rewardedAd = ad
                }

                override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                    rewardedAd = null
                }
            })
    }

    /**
     * JavaScript Interface for WebView
     * Allows web content to call native Android functions
     */
    inner class AndroidInterface {
        @JavascriptInterface
        fun showRewardedAd() {
            runOnUiThread {
                if (rewardedAd != null) {
                    rewardedAd?.show(this@MainActivity) { rewardItem ->
                        // Handle reward when user completes the ad
                        val rewardAmount = rewardItem.amount
                        val rewardType = rewardItem.type

                        // Notify web page of reward
                        webView.evaluateJavascript(
                            "javascript:if(typeof onRewardEarned === 'function') { onRewardEarned('$rewardType', $rewardAmount); }",
                            null
                        )

                        // Load next ad
                        loadRewardedAd()
                    }
                } else {
                    // Ad not ready, notify web page
                    webView.evaluateJavascript(
                        "javascript:if(typeof onRewardAdNotReady === 'function') { onRewardAdNotReady(); }",
                        null
                    )

                    // Try loading again
                    loadRewardedAd()
                }
            }
        }
    }

    override fun onBackPressed() {
        // Handle back navigation in WebView
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
