package com.example.english.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.english.R
import com.example.english.adapter.DetailsHorizontalAdapter
import com.example.english.entity.StatisticBean
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.transformer.AlphaPageTransformer
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_profile_statistics.*

class ProfileFragment : Fragment() {

    private var mRootView: View? = null

    private var mHasNetwork = true
    private var cnt = 0

    companion object {
        lateinit var mDataList: MutableList<StatisticBean>
    }

    private lateinit var mHorizontalAdapter: DetailsHorizontalAdapter

    init {
        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_profile, null)
        }
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("生命周期", "profile - onActivityCreated")

        initView()
    }

    override fun onStart() {
        super.onStart()
        Log.i("生命周期", "profile - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("生命周期", "profile - onResume")
        if (mHasNetwork) {
            banner_profile_details.visibility = View.VISIBLE
            btn_profile_refresh.visibility = View.GONE
            tv_profile_task_num.text = 233.toString()
            tv_profile_time_num.text = 2233.toString()
            tv_profile_word_num.text = 666.toString()
            tv_profile_sentence_num.text = 666.toString()
        } else {
            banner_profile_details.visibility = View.INVISIBLE
            btn_profile_refresh.visibility = View.VISIBLE
            tv_profile_task_num.text = "--"
            tv_profile_time_num.text = "--"
            tv_profile_word_num.text = "--"
            tv_profile_sentence_num.text = "--"
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("生命周期", "profile - onPause")
        cnt++
        mHasNetwork = cnt % 2 != 0
    }

    private fun initView() {
        mHorizontalAdapter = DetailsHorizontalAdapter(mDataList)

        banner_profile_details.addBannerLifecycleObserver(this)
            .setAdapter(mHorizontalAdapter)
            .setBannerGalleryEffect(20, 0)
            .setIndicator(RectangleIndicator(mRootView?.context))
            .setIndicatorRadius(0)
            .setIndicatorHeight(6)
            .setIndicatorSelectedWidth(20)
            .setIndicatorNormalWidth(10)
            .setIndicatorMargins(IndicatorConfig.Margins(0, 0, 0, 0))
            .setPageTransformer(AlphaPageTransformer())
            .isAutoLoop(true)
    }

    private fun initData() {
        val grade = "全国小学二年级"
        val myHomework = "我的作业"
        val mySelfLearn = "我的自学"
        val time = "时长"
        val word = "词汇"
        val sentence = "句子"

        val data1 = StatisticBean(title = myHomework + "成绩", grade = grade)
        val data2 = StatisticBean(title = myHomework + time, grade = grade)
        val data3 = StatisticBean(title = myHomework + word, grade = grade)
        val data4 = StatisticBean(title = myHomework + sentence, grade = grade)
        val data5 = StatisticBean(title = mySelfLearn + time)
        val data6 = StatisticBean(title = mySelfLearn + word)
        val data7 = StatisticBean(title = mySelfLearn + sentence)

        mDataList = mutableListOf(data1, data2, data3, data4, data5, data6, data7)
    }

}