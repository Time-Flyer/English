package com.example.english.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.activity.StudyDetailsActivity
import com.example.english.entity.StatisticBean
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.item_profile_rv_main.view.*

class DetailsHorizontalAdapter(private val data: MutableList<StatisticBean>) :
    BannerAdapter<StatisticBean, DetailsHorizontalAdapter.ViewHolder>(data) {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int) {
            itemView.tv_profile_rv_title.text = data[position].title
            itemView.tv_profile_rv_grade.text = data[position].grade
        }
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile_rv_main, parent, false))
    }

    override fun onBindView(holder: ViewHolder, data: StatisticBean?, position: Int, size: Int) {
        holder.bindView(position)
        holder.itemView.tv_profile_rv_all_details.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudyDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }
}
