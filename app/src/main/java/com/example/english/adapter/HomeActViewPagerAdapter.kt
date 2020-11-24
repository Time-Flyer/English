package com.example.english.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeActViewPagerAdapter(private val fm : FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mFragmentList = mutableListOf<Fragment>()


    override fun getCount(): Int = mFragmentList.size

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    fun setFragments(container: ViewGroup, fragments: MutableList<Fragment>) {
        for (i in fragments.indices) {
            val fragment = findFragment(container.id, i) // 根据 id 查找是否有缓存的 Fragment
            if (fragment != null) { // 如果有就替换
                fragments[i] = fragment
            }
        }
        mFragmentList = fragments
    }

    private fun findFragment(viewId: Int, pos: Int): Fragment? {
        val name = makeFragmentName(viewId, getItemId(pos))
        return fm.findFragmentByTag(name)
    }

    private fun makeFragmentName(viewId: Int, id: Long): String {
        return "android:switcher:$viewId:$id"
    }
}