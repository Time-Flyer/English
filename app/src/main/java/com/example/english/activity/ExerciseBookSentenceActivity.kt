package com.example.english.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.english.R
import com.example.english.util.MyActionBar
import kotlinx.android.synthetic.main.activity_exercise_book_sentence.*

class ExerciseBookSentenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_book_sentence)

        initExtras()
        initView()
        initEvents()
    }

    private fun initExtras() {
        val name = intent.getStringExtra("name")
//        Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT).show()
    }

    private fun initEvents() {
        mab_exercise_book_sentence.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                val intent = Intent().apply {
                    putExtra("result", "hi, english")
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })
    }

    private fun initView() {
        mab_exercise_book_sentence.setTitle(getString(R.string.exercise_book_sentence))
        mab_exercise_book_sentence.setTitleColor(Color.BLACK)
    }
}