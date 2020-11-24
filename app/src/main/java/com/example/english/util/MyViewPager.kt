package com.example.english.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class MyViewPager: ViewPager {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    private var canScroll = true
    private var smoothScroll = true

    /**
     * @param canScroll: true 可以滑动换页; false 不能滑动换页
     */
    fun setCanScroll(canScroll: Boolean) {
        this.canScroll = canScroll
    }

    /**
     * @param smoothScroll: true 平滑滚动到新页面; false 立即切换到新页面
     */
    fun setSmoothScroll(smoothScroll: Boolean) {
        this.smoothScroll = smoothScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return canScroll && super.onInterceptTouchEvent(ev)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, this.smoothScroll)
    }
}