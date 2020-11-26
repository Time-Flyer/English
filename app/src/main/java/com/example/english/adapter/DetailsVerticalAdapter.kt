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
        holder.itemView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        holder.itemView.tv_profile_rv_all_details.visibility = View.GONE
    }

    override fun getItemCount(): Int = data.size
}