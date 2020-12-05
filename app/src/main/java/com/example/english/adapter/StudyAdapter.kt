package com.example.english.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.entity.StudyTaskBean
import kotlinx.android.synthetic.main.item_study_rv_main.view.*

class StudyAdapter(private val list: List<StudyTaskBean>)
    : RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            var pre = ""
        }

        fun bindView(taskBean: StudyTaskBean, pos: Int) {
            Log.i("调用", "bind view $pre")
            itemView.tv_study_rv_day.text = taskBean.day
            if (taskBean.isFirst) {
                itemView.tv_study_rv_day.visibility = View.VISIBLE
            } else {
                itemView.tv_study_rv_day.visibility = View.GONE
            }
            pre = taskBean.day
            itemView.tv_study_rv_title.text = taskBean.title
            itemView.tv_study_rv_type.text = taskBean.type
            itemView.tv_study_rv_grade.text = taskBean.grade
            itemView.tv_study_rv_time.text = taskBean.beginTime

            itemView.cv_study_rv_item.setOnClickListener {
                Toast.makeText(itemView.context, "点击了第" + pos + "个item", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("调用", "create view holder")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_study_rv_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}