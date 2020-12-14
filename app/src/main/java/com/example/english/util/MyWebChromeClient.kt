package com.example.english.util

import android.net.Uri
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog


class MyWebChromeClient(private val mOpenFileChooserCallBack: OpenFileChooserCallBack): WebChromeClient() {

    override fun onJsAlert(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        // 要显示自定义dialog返回true，且需调用result.confirm()，否则中断后续JavaScript
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(view.context).apply {
            setTitle(url)
            setMessage(message)
            setPositiveButton("Ok") { dialog, which ->
                result.confirm()
            }
            setCancelable(false)
            create()
        }
        alertDialog.show()
        return true
    }

    //针对 Android 5.0+
    override fun onShowFileChooser(
        webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        mOpenFileChooserCallBack.openFileChooser5CallBack(
            webView,
            filePathCallback,
            fileChooserParams
        )
        return true
    }

}