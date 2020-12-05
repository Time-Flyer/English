package com.example.english.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.entity.ClassBroadcastBean
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.item_class_broadcast.view.*

class ClassBroadcastAdapter(private val data: List<ClassBroadcastBean>) :
    BannerAdapter<ClassBroadcastBean, ClassBroadcastAdapter.ViewHolder>(data) {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(pos: Int) {
            itemView.tv_class_broadcast_name.text = data[pos].name
            itemView.tv_class_broadcast_content.text = data[pos].content
        }
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_class_broadcast, parent, false))
    }

    override fun onBindView(holder: ViewHolder, data: ClassBroadcastBean?, position: Int, size: Int) {
        holder.bindView(position)
    }
}