package com.example.english.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.english.R
import com.example.english.util.MyActionBar
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        initView()
        initEvents()
    }

    private fun initEvents() {
        mab_exercise.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })

        cv_exercise_situation_sentence.setOnClickListener {
            val intent = Intent(this, ExerciseSituationSentenceActivity::class.java)
            startActivity(intent)
        }

        cv_exercise_grammar_video.setOnClickListener {
            val intent = Intent(this, ExerciseGrammarVideoActivity::class.java)
            startActivity(intent)
        }

        cv_exercise_word_spell.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("english://com.english.word?uid=110&name=张三"))
//            val uri = Uri.parse("english://com.english.word").buildUpon()
//            Log.d("${javaClass.simpleName} 测试", "uri = $uri")
//            val intent = Intent(Intent.ACTION_VIEW, uri.build()).apply {
//                putExtra("uid", "110")
//                putExtra("name", "张三")
//            }
            startActivity(intent)
        }
    }

    private fun initView() {
        mab_exercise.setTitle("选择任务类型")
        mab_exercise.setTitleColor(Color.WHITE) // TODO bug: 参数为 R.color.white 时显示的是 蓝紫色 !!
        mab_exercise.setBackIcon(R.drawable.ic_back_white)
    }
}