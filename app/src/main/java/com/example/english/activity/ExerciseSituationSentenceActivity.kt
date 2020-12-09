package com.example.english.activity

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.english.R
import com.example.english.adapter.SituationSentenceAdapter
import com.example.english.databinding.ActivityExerciseSituationSentenceBinding
import com.example.english.util.MyActionBar
import com.example.english.viewmodel.LiveDataVMFactory
import com.example.english.viewmodel.SituationSentenceViewModel
import kotlinx.android.synthetic.main.activity_exercise_situation_sentence.*

class ExerciseSituationSentenceActivity : AppCompatActivity() {

    private val viewModel: SituationSentenceViewModel by viewModels { LiveDataVMFactory }

    private lateinit var mBinding: ActivityExerciseSituationSentenceBinding

    private val mAdapter = SituationSentenceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExerciseSituationSentenceBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        initEvents()
    }

    private fun initEvents() {
        mab_exercise_situation_sentence.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })
    }

    private fun initView() {
        mBinding.mabExerciseSituationSentence.setTitle(getString(R.string.exercise_situation_sentence))
        mBinding.mabExerciseSituationSentence.setTitleColor(Color.BLACK)

        mBinding.rvExerciseSituationSentence.adapter = mAdapter

        viewModel.apply {
            items.observe(this@ExerciseSituationSentenceActivity, { bean ->
                bean?.let {
                    mAdapter.setData(it.list)
                }
            })
            getSituationSentence()
        }
    }
}