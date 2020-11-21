package com.example.english.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.english.R
import com.example.english.adapter.CLASS_PAGE
import com.example.english.adapter.MainViewPagerAdapter
import com.example.english.adapter.PROFILE_PAGE
import com.example.english.adapter.STUDY_PAGE
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.btn_navigation.*

class HomeActivity : AppCompatActivity() {

    private var curSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initView()
        initEvents()
    }

    private fun initEvents() {
        vp_nav.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    STUDY_PAGE -> navSelect(position)
                    CLASS_PAGE -> navSelect(position)
                    PROFILE_PAGE -> navSelect(position)
                }
            }
        })

        ll_study.setOnClickListener {
            if (curSelected != STUDY_PAGE) {
                vp_nav.currentItem = STUDY_PAGE
                navSelect(STUDY_PAGE)
            }
        }

        ll_class.setOnClickListener {
            if (curSelected != CLASS_PAGE) {
                vp_nav.currentItem = CLASS_PAGE
                navSelect(CLASS_PAGE)
            }
        }

        ll_profile.setOnClickListener {
            if (curSelected != PROFILE_PAGE) {
                vp_nav.currentItem = PROFILE_PAGE
                navSelect(PROFILE_PAGE)
            }
        }
    }

    private fun initView() {

        vp_nav.adapter = MainViewPagerAdapter(supportFragmentManager)
        vp_nav.currentItem = curSelected

        navSelect(curSelected)

        /*
        val item1 = AHBottomNavigationItem("学习", R.drawable.ic_tabbar_study_selected_icon)
        val item2 = AHBottomNavigationItem("班级", R.drawable.ic_tabbar_class_icon)
        val item3 = AHBottomNavigationItem("我的", R.drawable.ic_tabbar_profile_icon)

        bottom_nav.addItem(item1)
        bottom_nav.addItem(item2)
        bottom_nav.addItem(item3)

        bottom_nav.currentItem = 0

        bottom_nav.setOnTabSelectedListener { pos, selected ->
            Toast.makeText(this, "点击了" + pos + "item", Toast.LENGTH_SHORT).show()
            bottom_nav.getItem(pos).setDrawable(R.drawable.bg_homepage)
            true
        }
        */
    }

    private fun navSelect(selected: Int) {
        curSelected = selected
        when (selected) {
            STUDY_PAGE -> {
                ib_study_icon.setImageResource(R.drawable.ic_tabbar_study_selected_icon)
                tv_study_title.setTextColor(ContextCompat.getColor(this, R.color.active))
                ib_class_icon.setImageResource(R.drawable.ic_tabbar_class_icon)
                tv_class_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                ib_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_icon)
                tv_profile_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
            }
            CLASS_PAGE -> {
                ib_study_icon.setImageResource(R.drawable.ic_tabbar_study_icon)
                tv_study_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                ib_class_icon.setImageResource(R.drawable.ic_tabbar_class_selected_icon)
                tv_class_title.setTextColor(ContextCompat.getColor(this, R.color.active))
                ib_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_icon)
                tv_profile_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
            }
            PROFILE_PAGE -> {
                ib_study_icon.setImageResource(R.drawable.ic_tabbar_study_icon)
                tv_study_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                ib_class_icon.setImageResource(R.drawable.ic_tabbar_class_icon)
                tv_class_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                ib_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_selected_icon)
                tv_profile_title.setTextColor(ContextCompat.getColor(this, R.color.active))
            }
        }
    }
}
