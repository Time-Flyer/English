package com.example.english.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bigkoo.alertview.AlertView
import com.example.english.BuildConfig
import com.example.english.databinding.ActivityWebviewBinding
import com.example.english.util.FileUtil
import com.example.english.util.MyWebChromeClient
import com.example.english.util.OpenFileChooserCallBack
import kotlinx.android.synthetic.main.activity_exercise.*
import java.text.SimpleDateFormat
import java.util.*


class WebViewActivity : AppCompatActivity(), OpenFileChooserCallBack {

    companion object {
        const val CAMERA_ID = 0
        const val ALBUM_ID = 1

        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.SIMPLIFIED_CHINESE)
    }

    private val mActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                var result = activityResult.data?.data
                if (result == null) {
                    if (photoUri != null) {
                        result = photoUri
                    }
                }
                Log.i("WebView666", result.toString())
                receiveFile(arrayOf(result ?: Uri.EMPTY))
            } else if (activityResult.resultCode == RESULT_CANCELED) {
                receiveFile(arrayOf())
            }
        }

    private lateinit var mBinding: ActivityWebviewBinding

    private lateinit var mOptionsDialog: AlertView

    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    private var photoUri: Uri? = null

    private var pageUrl: String? = null


    class JavaScripObject(private val mContext: Context) {
        private val normal = "JS call Native\n"

        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, "$normal\"$toast\" is from JS", Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun getDataFromNative(): String {
            return "$normal\"Hello JS\" is native data."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initExtras()
        initView()
        initEvents()
    }

    private fun initExtras() {
        pageUrl = intent.getStringExtra("web_page")
    }

    private fun initEvents() {

        mOptionsDialog = AlertView(
            "选择图片", null, "取消", arrayOf("拍照", "从相册中选择"), null,
            this, AlertView.Style.ActionSheet
        ) { _, position ->
            when (position) {
                CAMERA_ID -> {
                    gotoCamera()
                }
                ALBUM_ID -> {
                    gotoPhoto()
                }
                else -> {
                    receiveFile(arrayOf())
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    private fun initView() {

        val settings = mBinding.webView.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true

        if (pageUrl == null) {

            mBinding.webView.addJavascriptInterface(JavaScripObject(this), "android")

            mBinding.webView.webChromeClient = MyWebChromeClient(this)
            mBinding.webView.loadUrl("file:///android_asset/test.html")

            mBinding.webView.postDelayed({
                mBinding.webView.loadUrl("javascript:callJS(\"Hello JS\")") // 参数是字符串，需要用转义符
            }, 1000)

            mBinding.btnWebview1.setOnClickListener {
                mBinding.webView.post {
                    mBinding.webView.loadUrl("javascript:clickPrompt()")
                }
            }

            mBinding.btnWebview2.setOnClickListener {
                mBinding.webView.evaluateJavascript("javascript:add(3, 4)") { result ->
                    Toast.makeText(this@WebViewActivity, result, Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            mBinding.btnWebview1.visibility = View.GONE
            mBinding.btnWebview2.visibility = View.GONE

            mBinding.webView.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                Log.i("WebView666", "Download Url = $url")
                if (url.toString().contains(".apk")) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url.toString())))
                }
            }

            mBinding.webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    Log.i("WebView666", "Override Url = ${request?.url}")
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }

            mBinding.webView.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK && mBinding.webView.canGoBack()) {
                            mBinding.webView.goBack()
                            return true
                        }
                    }
                    return false
                }
            })

            mBinding.webView.loadUrl(pageUrl ?: "")
        }
    }

    private fun setWebViewInitialScale() {
        val wm = this@WebViewActivity.getSystemService(WINDOW_SERVICE) as WindowManager
        val width = wm.defaultDisplay.width
        when {
            width > 972 -> {
                mBinding.webView.setInitialScale(280)
            }
            width > 864 -> {
                mBinding.webView.setInitialScale(260)
            }
            width > 756 -> {
                mBinding.webView.setInitialScale(240)
            }
            width > 324 -> {
                mBinding.webView.setInitialScale(220)
            }
            else -> {
                mBinding.webView.setInitialScale(200)
            }
        }
    }

    override fun openFileChooser5CallBack(
        webView: WebView,
        valueCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: WebChromeClient.FileChooserParams
    ) {
        mFilePathCallback = valueCallback
        mOptionsDialog.show()
    }

    private fun receiveFile(array: Array<Uri>) {
        if (mFilePathCallback != null) {
            mFilePathCallback?.onReceiveValue(array)
            mFilePathCallback = null
        }
    }

    private fun gotoPhoto() {
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        mActivityLauncher.launch(Intent.createChooser(albumIntent, "请选择图片来源"))
    }

    private fun gotoCamera() {
        val date = Date(System.currentTimeMillis())
        val fileName = "IMG_" + dateFormat.format(date) + ".jpg"
        val tmpFile = FileUtil.createFile(FileUtil.PHOTO_PATH, fileName)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            cameraIntent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            photoUri = FileProvider.getUriForFile(
                this@WebViewActivity,
                BuildConfig.APPLICATION_ID + ".fileProvider",
                tmpFile
            )
        } else {
            photoUri = Uri.fromFile(tmpFile)
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        mActivityLauncher.launch(Intent.createChooser(cameraIntent, "拍照"))
    }

}

/*
    AlertView.Builder().setContext(this)
            .setStyle(AlertView.Style.ActionSheet)
            .setTitle("选择操作")
            .setMessage(null)
            .setDestructive("拍照", "从相册中选择")
            .setCancelText("取消")
            .setOthers(null)
            .setOnItemClickListener { _, position ->
                if (position == CAMERA_ID) {
                    gotoCamera()
                } else if (position == ALBUM_ID) {
                    gotoPhoto()
                }
            }
            .build()
            .show()
 */