package com.example.english

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.example.english.adapter.GuidePageViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mIsLogin = false

    private val imgs = arrayOf(
        R.drawable.begin1,
        R.drawable.begin2,
        R.drawable.begin3,
        R.drawable.begin4
    )

    lateinit var viewList: List<View>

    private lateinit var mAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
        welcome()
    }

    private fun welcome() {
        val view1 = LayoutInflater.from(this).inflate(R.layout.view_guide1, null)
        val view2 = LayoutInflater.from(this).inflate(R.layout.view_guide2, null)
        val view3 = LayoutInflater.from(this).inflate(R.layout.view_guide3, null)
        val view4 = LayoutInflater.from(this).inflate(R.layout.view_guide4, null)
        viewList = listOf(view1, view2, view3, view4)

        mAdapter = GuidePageViewPagerAdapter(viewList)

        vp_welcome.adapter = mAdapter // TODO error fix: LoopViewPager is default!


    }

    private fun getData() {

    }
}