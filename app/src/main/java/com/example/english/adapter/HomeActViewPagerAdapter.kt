package com.example.english.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.english.fragment.ClassFragment
import com.example.english.fragment.ProfileFragment
import com.example.english.fragment.StudyFragment

const val STUDY_PAGE = 0
const val CLASS_PAGE = 1
const val PROFILE_PAGE = 2

class HomeActViewPagerAdapter(fm : FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()

    init {
        fragmentList.add(StudyFragment())
        fragmentList.add(ClassFragment())
        fragmentList.add(ProfileFragment())
    }


    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }
}