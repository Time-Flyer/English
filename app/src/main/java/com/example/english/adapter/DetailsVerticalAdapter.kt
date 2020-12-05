package com.example.english.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.entity.StatisticBean
import kotlinx.android.synthetic.main.item_profile_rv_main.view.*

class DetailsVerticalAdapter(private val data: MutableList<StatisticBean>)
    : RecyclerView.Adapter<DetailsVerticalAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int) {
            itemView.tv_profile_rv_title.text = data[position].title
            itemView.tv_profile_rv_grade.text = data[position].grade
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile_rv_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
        holder.itemView.tv_profile_rv_all_details.visibility = View.GONE
        // 由于共用同一个布局，而外部使用的 ViewPager2 要求 match_parent，所以里面动态设置为 wrap_content
        holder.itemView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // 由于外部设置了画廊效果，所以里面动态调整布局
        val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.setMargins(32, 8, 32, 8)
    }

    override fun getItemCount(): Int = data.size
}