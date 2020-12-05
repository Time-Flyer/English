package com.example.english.util

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.english.R
import kotlinx.android.synthetic.main.layout_action_bar.view.*

class MyActionBar: ConstraintLayout {

    private lateinit var mOnBackClickListener: OnBackClickListener

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.layout_action_bar, this)

        ib_action_bar_back.setOnClickListener { mOnBackClickListener.backClick() }
    }

    /**
     * ActionBar 标题
     */
    fun setTitle(title: String) {
        if (TextUtils.isEmpty(title).not()) {
            tv_action_bar_title.text = title
        }
    }

    /**
     * ActionBar 标题颜色
     */
    fun setTitleColor(color: Int) {
        tv_action_bar_title.setTextColor(color)
    }

    /**
     * ActionBar 返回按钮
     */
    fun setBackIcon(id: Int) {
        ib_action_bar_back.setImageResource(id)
    }

    fun setOnBackClickListener(onBackClickListener: OnBackClickListener) {
        mOnBackClickListener = onBackClickListener
    }

    interface OnBackClickListener {
        fun backClick()
    }
}