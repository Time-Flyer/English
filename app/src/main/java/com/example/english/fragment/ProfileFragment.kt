package com.example.english.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.english.R
import com.example.english.adapter.DetailsHorizontalAdapter
import com.example.english.entity.StatisticBean
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.transformer.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private var mRootView: View? = null

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

        initView()
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
        val grade = "全国小学五年级"
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