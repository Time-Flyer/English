package com.example.english.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.english.R
import com.example.english.activity.ExerciseActivity
import com.example.english.activity.WebViewActivity
import com.example.english.adapter.StudyAdapter
import com.example.english.entity.StudyTaskBean
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_study.*
import kotlinx.android.synthetic.main.layout_study_btn_group.*

class StudyFragment : Fragment() {

    private var mRootView: View? = null

    private val mTaskList = ArrayList<StudyTaskBean>()
    private lateinit var mAdapter: StudyAdapter

    companion object {
        private val REQUEST_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    private val mRequestForActivityResult = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        var granted = true
        for (permission in REQUEST_PERMISSIONS) {
            if (it[permission] == false) {
                granted = false
                break
            }
        }
        if (granted) {
            val intent = Intent(mRootView?.context, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    init {
        initTask()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("生命周期", "study - onCreateView")
        if (mRootView == null) {
            Log.i("生命周期", "study - onCreateView 新建RootView")
            mRootView = inflater.inflate(R.layout.fragment_study, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("生命周期", "study - onViewCreated")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i("生命周期", "study - onActivityCreated")
        initView()
        initEvents()
    }

    override fun onStart() {
        super.onStart()
        Log.i("生命周期", "study - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("生命周期", "study - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("生命周期", "study - onPause")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.i("生命周期", "study - onHiddenChanged")
        Log.i("生命周期", "$userVisibleHint")
        Log.i("生命周期", "hidden = $hidden")
    }

    private fun checkRequiredPermission(): Boolean {
        val deniedPermissions = mutableListOf<String>()
        for (permission in REQUEST_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(mRootView!!.context, permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission)
            }
        }
        if (deniedPermissions.isEmpty().not()) {
            mRequestForActivityResult.launch(deniedPermissions.toTypedArray())
        }
        return deniedPermissions.isEmpty()
    }

    private fun initEvents() {
        tv_study_photo.setOnClickListener {
            if (checkRequiredPermission()) {
                val intent = Intent(mRootView?.context, WebViewActivity::class.java)
                startActivity(intent)
            }
        }

        tv_study_exercise.setOnClickListener {
            val intent = Intent(mRootView?.context, ExerciseActivity::class.java)
            startActivity(intent)
        }

        tv_study_dictionary.setOnClickListener {
            val intent = Intent(mRootView?.context, WebViewActivity::class.java).apply {
                putExtra("web_page", "http://m.youdao.com/translate?vendor=fanyi.web")
            }
            startActivity(intent)
        }

        rv_study.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                rv_study.refreshComplete()
            }

            override fun onLoadMore() {
                rv_study.loadMoreComplete()
            }

        })
    }

    private fun initView() {

        val linearLayoutManager = LinearLayoutManager(mRootView?.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_study.layoutManager = linearLayoutManager

        mAdapter = StudyAdapter(mTaskList)
        rv_study.adapter = mAdapter

        rv_study.setArrowImageView(R.drawable.ic_down_arrow)
        rv_study.defaultRefreshHeaderView.setRefreshTimeVisible(true)
        rv_study.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        rv_study.setLoadingMoreProgressStyle(ProgressStyle.BallRotate)

        val footView = LayoutInflater.from(mRootView!!.context)
                .inflate(R.layout.item_study_rv_example, rv_study.parent as ViewGroup, false)

        rv_study.setFootView(footView, object: CustomFooterViewCallBack {
            override fun onLoadingMore(yourFooterView: View?) {
            }

            override fun onLoadMoreComplete(yourFooterView: View?) {
            }

            override fun onSetNoMore(yourFooterView: View?, noMore: Boolean) {
            }
        })

        rv_study.footView.setOnClickListener {
            Toast.makeText(it.context, "🐻", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initTask() {
        for (i in 0 until 3) {
            val task = StudyTaskBean(
                day = "11月20日 星期五",
                isFirst = i == 0,
                title = "二年级词汇句型[休闲时光]",
                type = "跟读",
                grade = "全国小学二年级",
                beginTime = "11月22日开始"
            )
            mTaskList.add(task)
        }
        for (i in 0 until 3) {
            val task = StudyTaskBean(
                day = "11月19日 星期四",
                isFirst = i == 0,
                title = "二年级对话练习[休闲时光]",
                type = "对话",
                grade = "全国小学二年级",
                beginTime = "11月19日开始"
            )
            mTaskList.add(task)
        }
    }
}