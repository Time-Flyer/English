package com.example.english.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.databinding.ItemExerciseSituationSentenceBinding
import com.example.english.entity.SituationSentenceItemBean

class SituationSentenceAdapter: RecyclerView.Adapter<SituationSentenceAdapter.ViewHolder>() {

    companion object {
        val map = hashMapOf(
            1 to "一", 2 to "二",
            3 to "三", 4 to "四",
            5 to "五", 6 to "六",
        )
        val colors = mutableListOf(
            R.color.ss_grade_one, R.color.ss_grade_two,
            R.color.ss_grade_three, R.color.ss_grade_four,
            R.color.ss_grade_five, R.color.ss_grade_six,
        )
    }

    private val list = mutableListOf<SituationSentenceItemBean>()

    inner class ViewHolder(private val itemBinding: ItemExerciseSituationSentenceBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(pos: Int) {
            val drawable = itemBinding.root.background as GradientDrawable
            drawable.mutate() // 避免影响到其他使用了同一 shape 的布局
            drawable.setColor(ContextCompat.getColor(itemBinding.root.context, colors[pos]))
            itemBinding.tvSituationSentenceGrade.text = "${map[list[pos].grade]}年级"
            itemBinding.tvSituationSentenceSum.text = "(${list[pos].sum}组)"
        }
    }
    
    fun setData(data: MutableList<SituationSentenceItemBean>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemExerciseSituationSentenceBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = list.size

}