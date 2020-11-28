package com.example.english.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.english.R
import com.example.english.util.MyActionBar
import kotlinx.android.synthetic.main.activity_exercise_situation_sentence.*

class ExerciseSituationSentenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_situation_sentence)

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
        mab_exercise_situation_sentence.setTitle(getString(R.string.exercise_situation_sentence))
        mab_exercise_situation_sentence.setTitleColor(Color.BLACK)
    }
}