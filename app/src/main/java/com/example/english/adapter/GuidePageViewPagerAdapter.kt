package com.example.english.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.example.english.activity.HomeActivity
import kotlinx.android.synthetic.main.layout_btn_login.view.*

// var views: Array<Int>, var activity: AppCompatActivity
class GuidePageViewPagerAdapter(var views: List<View>) :PagerAdapter() {

    override fun getCount(): Int = views.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val view = LayoutInflater.from(activity).inflate(R.layout.view_guide1, null) as RelativeLayout
//        view.iv_background.setImageResource(views[position])
//        container.addView(view)
//        return view
        if (views[position].parent != null) {
            val viewParent = views[position].parent as ViewGroup
            viewParent.removeView(views[position])
        }
        views[position].btn_login_as_student.setOnClickListener {
            val intent = Intent(container.context, HomeActivity::class.java)
            container.context.startActivity(intent)
        }
        views[position].btn_login_as_teacher.setOnClickListener {
            Toast.makeText(container.context, "敬请期待", Toast.LENGTH_SHORT).show()
        }
        container.addView(views[position])
        return views[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
    }
}