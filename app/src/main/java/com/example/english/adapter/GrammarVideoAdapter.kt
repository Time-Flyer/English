package com.example.english.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.entity.GrammarVideoBean
import kotlinx.android.synthetic.main.item_exercise_grammar_video_head.view.*
import kotlinx.android.synthetic.main.item_exercise_grammar_video_main.view.*

const val VIEW_TYPE_HEAD = 2
const val VIEW_TYPE_MAIN = 1

class GrammarVideoAdapter(private var list: List<GrammarVideoBean>)
    : RecyclerView.Adapter<GrammarVideoAdapter.ViewHolder>() {

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        open fun bindView(pos: Int) {}
    }

    inner class ViewHolderHead(itemView: View) : ViewHolder(itemView) {
        override fun bindView(pos: Int) {
            itemView.tv_exercise_grammar_video_level.text = "L-".plus(list[pos].level)
            itemView.tv_exercise_grammar_video_level_title.text = list[pos].levelTitle
        }
    }

    inner class ViewHolderMain(itemView: View) : ViewHolder(itemView) {
        override fun bindView(pos: Int) {
            itemView.tv_exercise_grammar_video_title.text = list[pos].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEAD -> {
                ViewHolderHead(inflater.inflate(R.layout.item_exercise_grammar_video_head, parent, false))
            }
            else -> {
                ViewHolderMain(inflater.inflate(R.layout.item_exercise_grammar_video_main, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isHead) {
            VIEW_TYPE_HEAD
        } else {
            VIEW_TYPE_MAIN
        }
    }
}