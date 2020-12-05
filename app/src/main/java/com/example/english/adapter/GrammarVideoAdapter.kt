package com.example.english.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.english.databinding.ItemExerciseGrammarVideoHeadBinding
import com.example.english.databinding.ItemExerciseGrammarVideoMainBinding
import com.example.english.entity.GrammarVideoBean

const val VIEW_TYPE_HEAD = 2
const val VIEW_TYPE_MAIN = 1

class GrammarVideoAdapter(private var list: List<GrammarVideoBean>)
    : RecyclerView.Adapter<GrammarVideoAdapter.ViewHolder>() {

    open inner class ViewHolder(itemBinding: ConstraintLayout) : RecyclerView.ViewHolder(itemBinding) {
        open fun bindView(pos: Int) {}
    }

    inner class ViewHolderHead(private val itemBinding: ItemExerciseGrammarVideoHeadBinding) : ViewHolder(itemBinding.root) {
        override fun bindView(pos: Int) {
            itemBinding.tvExerciseGrammarVideoLevel.text = "L-".plus(list[pos].level)
            itemBinding.tvExerciseGrammarVideoLevelTitle.text = list[pos].levelTitle
        }
    }

    inner class ViewHolderMain(private val itemBinding: ItemExerciseGrammarVideoMainBinding) : ViewHolder(itemBinding.root) {
        override fun bindView(pos: Int) {
            itemBinding.tvExerciseGrammarVideoTitle.text = list[pos].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEAD -> {
                ViewHolderHead(ItemExerciseGrammarVideoHeadBinding.inflate(inflater, parent, false))
//                ViewHolderHead(inflater.inflate(R.layout.item_exercise_grammar_video_head, parent, false))
            }
            else -> {
                ViewHolderMain(ItemExerciseGrammarVideoMainBinding.inflate(inflater, parent, false))
//                ViewHolderMain(inflater.inflate(R.layout.item_exercise_grammar_video_main, parent, false))
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