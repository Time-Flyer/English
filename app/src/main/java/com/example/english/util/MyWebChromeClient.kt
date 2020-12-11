package com.example.english.util

import android.net.Uri
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

class MyWebChromeClient(private val mOpenFileChooserCallBack: OpenFileChooserCallBack): WebChromeClient() {

    override fun onJsAlert(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        return true
    }

    //针对 Android 5.0+
    override fun onShowFileChooser(
        webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        mOpenFileChooserCallBack.openFileChooser5CallBack(webView, filePathCallback, fileChooserParams)
        return true
    }

}