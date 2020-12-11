package com.example.english.util

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient.FileChooserParams
import android.webkit.WebView

interface OpenFileChooserCallBack {
    
    fun openFileChooser5CallBack(
        webView: WebView, valueCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    )
}