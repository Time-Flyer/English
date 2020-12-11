package com.example.english.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.english.R
import com.example.english.util.MyActionBar
import kotlinx.android.synthetic.main.activity_exercise_book_sentence.*

class ExerciseBookSentenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_book_sentence)

        initView()
        initEvents()
    }

    private fun initEvents() {
        mab_exercise_book_sentence.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })
    }

    private fun initView() {
        mab_exercise_book_sentence.setTitle(getString(R.string.exercise_book_sentence))
        mab_exercise_book_sentence.setTitleColor(Color.BLACK)
    }
}