package com.example.english.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
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
        setupData()
    }

    private var showDesk = false
    private var showBadge = false

    private fun setupData() {
        intent?.let {
            val uid1 = it.getStringExtra("uid")
            val name1 = it.getStringExtra("name")
            tv_holder1.text = "Extra: uid = $uid1, name = $name1"
            it.data?.run {
                val uid2 = getQueryParameter("uid")
                val name2 = getQueryParameter("name")
                tv_holder2.text = "Query: uid = $uid2, name = $name2"
            }
        }
    }

    private fun initEvents() {
        mab_exercise_book_sentence.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })

        btn_desk_control.setOnClickListener {
            if (showDesk) {
                tv_security_desk.visibility = View.GONE
                btn_desk_control.text = "显示安全书桌"
            } else {
                tv_security_desk.visibility = View.VISIBLE
                btn_desk_control.text = "隐藏安全书桌"
            }
            showDesk = !showDesk
        }
        btn_badge_control.setOnClickListener {
            if (showBadge) {
                iv_report_notification.visibility = View.GONE
                btn_badge_control.text = "显示小红点"
            } else {
                iv_report_notification.visibility = View.VISIBLE
                btn_badge_control.text = "隐藏小红点"
            }
            showBadge = !showBadge
        }
    }

    private fun initView() {
        mab_exercise_book_sentence.setTitle(getString(R.string.exercise_book_sentence))
        mab_exercise_book_sentence.setTitleColor(Color.BLACK)
    }
}