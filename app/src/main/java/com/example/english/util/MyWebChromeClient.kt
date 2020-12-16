package com.example.english.util

import android.net.Uri
import android.webkit.*
import android.widget.Toast
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

    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        val uri = Uri.parse(message)
        if (uri.scheme.equals("js")) {
            if (uri.authority.equals("demo")) {
                Toast.makeText(view?.context, "JS 调用本地方法", Toast.LENGTH_SHORT).show()

                val paramsCollection = uri.queryParameterNames
                val list = mutableListOf<Int>()
                for (i in paramsCollection) {
                    list.add(uri.getQueryParameter(i)?.toInt()!!)
                }
                result?.confirm("JS 成功调用本地方法\n$list")
                return true
            }
        }
        return super.onJsPrompt(view, url, message, defaultValue, result)
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