package com.example.english.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.english.R
import com.example.english.adapter.DetailsVerticalAdapter
import com.example.english.fragment.ProfileFragment.Companion.mDataList
import com.example.english.util.MyActionBar
import kotlinx.android.synthetic.main.activity_statictic_details.*

class StudyDetailsActivity: AppCompatActivity() {

    private lateinit var mAdapter: DetailsVerticalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statictic_details)

        initView()
    }

    private fun initView() {
        mab_study_details.setTitle("我的学情")
        mab_study_details.setTitleColor(Color.BLACK) // TODO bug: 设置为 R.color.black 时黑色不深
        mab_study_details.setBackIcon(R.drawable.ic_back_black)
        mab_study_details.setOnBackClickListener(object : MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })

        mAdapter = DetailsVerticalAdapter(mDataList)
        rv_profile_all_details.adapter = mAdapter
        rv_profile_all_details.layoutManager = LinearLayoutManager(this)
    }

}