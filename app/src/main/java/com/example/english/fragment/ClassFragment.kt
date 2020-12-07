package com.example.english.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.english.R
import com.example.english.adapter.ClassBroadcastAdapter
import com.example.english.entity.ClassBroadcastBean
import com.youth.banner.Banner
import com.youth.banner.transformer.*
import kotlinx.android.synthetic.main.fragment_class.*
import kotlinx.android.synthetic.main.item_class.*

class ClassFragment : Fragment() {

    private var mRootView: View? = null

    private var mIsJoinClass = true
    private var mHasNetwork = true
    private var cnt = 0

    private lateinit var mDataList: List<ClassBroadcastBean>

    init {
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_class, null)
        }
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("生命周期", "class - onActivityCreated")

        initView()
    }

    override fun onStart() {
        super.onStart()
        Log.i("生命周期", "class - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("生命周期", "class - onResume")
        if (mIsJoinClass && mHasNetwork) {
            layout_class.visibility = View.VISIBLE
            layout_class_empty.visibility = View.GONE
        } else {
            layout_class.visibility = View.GONE
            layout_class_empty.visibility = View.VISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("生命周期", "class - onPause")
        cnt++
        mHasNetwork = cnt % 2 != 0
    }

    private fun initView() {

        banner_class_broadcast.addBannerLifecycleObserver(this)
            .setAdapter(ClassBroadcastAdapter(mDataList))
            .setOrientation(Banner.VERTICAL)
            .setUserInputEnabled(false)
            .setPageTransformer(AlphaPageTransformer())

    }

    private fun initData() {
        val bc1 = ClassBroadcastBean(
            name = "韩梅梅",
            content = "已完成学习任务二年级感恩节活动[感恩节聚会]",
        )
        val bc2 = ClassBroadcastBean(
            name = "李雷",
            content = "已完成学习任务二年级对话练习[休闲时光]",
        )
        val bc3 = ClassBroadcastBean(
            name = "李子明",
            content = "新生报到，一起进步吧",
        )
        val bc4 = ClassBroadcastBean(
            name = "李华",
            content = "已完成写作任务：假如你是李华，你的朋友唐纳德·特朗普(Donald Trump)",
        )
        mDataList = listOf(bc1, bc2, bc3, bc4)
    }

}