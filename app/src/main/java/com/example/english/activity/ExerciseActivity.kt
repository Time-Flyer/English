package com.example.english.activity

import android.graphics.Color
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
    }

    private fun initView() {
        mab_exercise.setTitle("选择任务类型")
        mab_exercise.setTitleColor(Color.WHITE) // TODO bug: 参数为 R.color.while 时显示的是 蓝紫色 !!
        mab_exercise.setBackIcon(R.drawable.ic_back_white)
        mab_exercise.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })
    }
}