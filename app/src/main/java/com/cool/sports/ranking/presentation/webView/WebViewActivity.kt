package com.cool.sports.ranking.presentation.webView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import com.cool.sports.ranking.R
import com.cool.sports.ranking.common.sharedPreference.SPApp

class WebViewActivity : AppCompatActivity() {
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val sp = SPApp(this)
        sp.web_was_opened = true

        try {
            webView = findViewById(R.id.webvieww)
            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()
            webView.settings.javaScriptEnabled = true
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.settings.domStorageEnabled = true
            val url = sp.URL
            webView.loadUrl(url)
        } catch (e: Exception) {
            println(e.message)
        }

        val back_btn= findViewById<ImageView>(R.id.back_btn_web)
        back_btn.setOnClickListener {
            sp.web_was_opened = false
            finish()
        }

        if(sp.init)
        {
            back_btn.visibility= View.GONE
            sp.init= false
        }
        else
            back_btn.visibility= View.VISIBLE

    }


    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            finishAffinity()
    }
}

