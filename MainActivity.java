package com.bridalrentals.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.parseColor("#5e1a3b"));
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);      // keeps customer records in phone memory
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient()); // enables alert/confirm dialogs
        web.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        // Back button closes the customer form first, then exits the app
        web.evaluateJavascript("(function(){var f=document.getElementById('form');"
            + "if(f.classList.contains('show')){f.classList.remove('show');return 'closed'}return 'exit'})()",
            v -> { if (v != null && v.contains("exit")) finish(); });
    }
}
