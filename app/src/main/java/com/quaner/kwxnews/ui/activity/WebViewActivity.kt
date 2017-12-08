package com.quaner.kwxnews.ui.activity

import android.os.Bundle
import android.webkit.WebSettings
import com.quaner.kwxnews.R
import com.quaner.wxnews.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by wenxin on 2017/12/8.
 */

class WebViewActivity : BaseActivity() {

    var url: String? = null
    var title: String? = null

    override fun initBundleExtras(extras: Bundle) {
        url = extras.getString("url")
        title = extras.getString("title")
    }

    override fun inject() {

    }

    override fun init() {
        initToolbar()
        initWebView()
    }

    private fun initToolbar() {
        toolbar.setTitleText(title)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initWebView() {
        val webSettings = webview.settings
        webSettings.builtInZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webSettings.setAppCacheEnabled(true)
        if (!url.isNullOrEmpty())
            webview.loadUrl(url)
    }

    override fun loadData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun onResume() {
        super.onResume()
        webview.onResume()
    }

    override fun onPause() {
        super.onPause()
        webview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        webview.removeAllViews()
        webview.destroy()
    }
}
