package com.example.english.util

import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.example.english.MyApplication
import java.io.File

object FileUtil {

    @JvmStatic
    val SDCARD_PATH: String = Environment.getExternalStorageDirectory().absolutePath

    @JvmStatic
    val PHONE_PATH: String = MyApplication.getInstance().applicationContext.filesDir.absolutePath

    // 包名
    @JvmStatic
    val PACKAGE_NAME: String = MyApplication.getInstance().applicationContext.packageName

    @JvmStatic
    val BASE_PATH: String = getRootFile()

    // 图片路径
    @JvmStatic
    val PHOTO_PATH = BASE_PATH + PACKAGE_NAME + "/pic" + File.separator

    @JvmStatic
    private fun hasSDCard(): Boolean {
        val sdState: String = Environment.getExternalStorageState()
        if (sdState == Environment.MEDIA_MOUNTED) {
            val file = File(SDCARD_PATH)
            return if (!file.exists()) {
                file.mkdirs()
            } else {
                true
            }
        }
        return false
    }

    @JvmStatic
    private fun getRootFile(): String {
        Log.i("WebView666", "SDCARD_PATH = $SDCARD_PATH")
        Log.i("WebView666", "PATH_PHONE = $PHONE_PATH")
        return if (hasSDCard()) {
            SDCARD_PATH + File.separator
        } else {
            PHONE_PATH + File.separator
        }
    }

    @JvmStatic
    private fun checkDirPath(dirPath: String): String {
        if (TextUtils.isEmpty(dirPath)) {
            return ""
        }
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        return dirPath
    }


    @JvmStatic
    fun createFile(dirPath: String, child: String): File = File(checkDirPath(dirPath), child)
}