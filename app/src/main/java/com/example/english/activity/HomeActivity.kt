package com.example.english.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.english.R
import com.example.english.adapter.CLASS_PAGE
import com.example.english.adapter.HomeActViewPagerAdapter
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

        ll_nav_study.setOnClickListener {
            if (curSelected != STUDY_PAGE) {
                vp_nav.currentItem = STUDY_PAGE
                navSelect(STUDY_PAGE)
            }
        }

        ll_nav_class.setOnClickListener {
            if (curSelected != CLASS_PAGE) {
                vp_nav.currentItem = CLASS_PAGE
                navSelect(CLASS_PAGE)
            }
        }

        ll_nav_profile.setOnClickListener {
            if (curSelected != PROFILE_PAGE) {
                vp_nav.currentItem = PROFILE_PAGE
                navSelect(PROFILE_PAGE)
            }
        }
    }

    private fun initView() {

        vp_nav.adapter = HomeActViewPagerAdapter(supportFragmentManager) // TODO error null pointer
        vp_nav.currentItem = curSelected

        navSelect(curSelected)
    }

    private fun navSelect(selected: Int) {
        curSelected = selected
        when (selected) {
            STUDY_PAGE -> {
                iv_study_icon.setImageResource(R.drawable.ic_tabbar_study_selected_icon)
                tv_study_icon_title.setTextColor(ContextCompat.getColor(this, R.color.active))
                iv_class_icon.setImageResource(R.drawable.ic_tabbar_class_icon)
                tv_class_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                iv_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_icon)
                tv_profile_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
            }
            CLASS_PAGE -> {
                iv_study_icon.setImageResource(R.drawable.ic_tabbar_study_icon)
                tv_study_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                iv_class_icon.setImageResource(R.drawable.ic_tabbar_class_selected_icon)
                tv_class_icon_title.setTextColor(ContextCompat.getColor(this, R.color.active))
                iv_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_icon)
                tv_profile_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
            }
            PROFILE_PAGE -> {
                iv_study_icon.setImageResource(R.drawable.ic_tabbar_study_icon)
                tv_study_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                iv_class_icon.setImageResource(R.drawable.ic_tabbar_class_icon)
                tv_class_icon_title.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                iv_profile_icon.setImageResource(R.drawable.ic_tabbar_profile_selected_icon)
                tv_profile_icon_title.setTextColor(ContextCompat.getColor(this, R.color.active))
            }
        }
    }
}

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
/*
            STUDY_PAGE -> {
                tv_nav_study.setTextColor(ContextCompat.getColor(this, R.color.active))
                tv_nav_class.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                tv_nav_profile.setTextColor(ContextCompat.getColor(this, R.color.inactive))

            }
            CLASS_PAGE -> {
                tv_nav_study.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                tv_nav_class.setTextColor(ContextCompat.getColor(this, R.color.active))
                tv_nav_profile.setTextColor(ContextCompat.getColor(this, R.color.inactive))
            }
            PROFILE_PAGE -> {
                tv_nav_study.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                tv_nav_class.setTextColor(ContextCompat.getColor(this, R.color.inactive))
                tv_nav_profile.setTextColor(ContextCompat.getColor(this, R.color.active))
            }
 */
