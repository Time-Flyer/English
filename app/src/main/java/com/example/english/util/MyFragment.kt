package com.example.english.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class MyFragment: Fragment() {

    /**
     * 获取布局文件
     */
    protected abstract fun getContentViewLayoutId(): Int

    /**
     * 第一次显示
     */
    protected abstract fun onFirstVisible()

    /**
     * 每次显示
     */
    protected abstract fun onVisible()

    /**
     * 每次隐藏
     */
    protected abstract fun onInvisible()

    private var isFirstVisible = true
    private var isPrepared = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getContentViewLayoutId() != 0) {
            inflater.inflate(getContentViewLayoutId(), container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initPrepare()
    }

    override fun onResume() {
        super.onResume()
        if (isFirstVisible) {
            initPrepare()
            isFirstVisible = false
        } else {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisible()
    }

    @Synchronized
    private fun initPrepare() {
        if (isPrepared) {
            onFirstVisible()
        } else {
            isPrepared = true
        }
    }
}