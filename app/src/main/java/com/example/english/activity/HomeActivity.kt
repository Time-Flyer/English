package com.example.english.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.english.R
import com.example.english.adapter.HomeActViewPagerAdapter
import com.example.english.fragment.ClassFragment
import com.example.english.fragment.ProfileFragment
import com.example.english.fragment.StudyFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        const val STUDY_PAGE = 0
        const val CLASS_PAGE = 1
        const val PROFILE_PAGE = 2
    }

    private val mFragments = mutableListOf(
            StudyFragment(),
            ClassFragment(),
            ProfileFragment()
    )

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
                    STUDY_PAGE -> bottom_nav.selectedItemId = R.id.bottom_nav_study
                    CLASS_PAGE -> bottom_nav.selectedItemId = R.id.bottom_nav_class
                    PROFILE_PAGE -> bottom_nav.selectedItemId = R.id.bottom_nav_profile
                }
            }
        })

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_study -> vp_nav.currentItem = STUDY_PAGE
                R.id.bottom_nav_class -> vp_nav.currentItem = CLASS_PAGE
                R.id.bottom_nav_profile -> vp_nav.currentItem = PROFILE_PAGE
            }
            true
        }

    }

    private fun initView() {

        val adapter = HomeActViewPagerAdapter(supportFragmentManager)
        adapter.setFragments(vp_nav, mFragments)

        vp_nav.adapter = adapter
        vp_nav.offscreenPageLimit = mFragments.size // TODO fix error: 切换 fragment 时避免 createView ...

        bottom_nav.itemIconTintList = null // TODO fix error: disable tint effect of selected item icon.
    }
}