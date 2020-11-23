package com.example.english.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.english.R
import com.example.english.adapter.StudyAdapter
import com.example.english.entity.StudyTask
import com.jcodecraeer.xrecyclerview.CustomFooterViewCallBack
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_study.*
import kotlinx.android.synthetic.main.item_study_btn_group.*

class StudyFragment : Fragment() {

    private var mRootView: View? = null

    private val mTaskList = ArrayList<StudyTask>()
    private lateinit var mAdapter: StudyAdapter

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1
        private val REQUEST_PERMISSIONS = arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    init {
        initTask()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("调用", "create view")
        if (mRootView == null) {
            Log.i("调用", "create view 内")
            mRootView = inflater.inflate(R.layout.fragment_study, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            Log.i("调用", "view created")
            initView()
            initEvents()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i("调用", "activity created")

    }

    private fun goCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    private fun checkRequiredPermission(): Boolean {
        val deniedPermissions = mutableListOf<String>()
        for (permission in REQUEST_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(mRootView!!.context,permission) == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission)
            }
        }
        if (deniedPermissions.isEmpty().not()) {
            requestPermissions(deniedPermissions.toTypedArray(), REQUEST_PERMISSION_CODE)
        }
        return deniedPermissions.isEmpty()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {

            }
        }
    }

    private fun initEvents() {
        tv_study_photo.setOnClickListener {
            requestPermissions(REQUEST_PERMISSIONS, REQUEST_PERMISSION_CODE)
            if (checkRequiredPermission()) {
                goCamera()
            }
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
//        rv_study.defaultFootView.removeAllViews()
//        rv_study.defaultFootView.addView(footView)
//        rv_study.setFootViewText("更多", "没有了")

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
            val task = StudyTask(
                day = "11月20日 星期五",
                title = "二年级词汇句型[休闲时光]",
                type = "跟读",
                grade = "全国小学二年级",
                time = "11月22日开始"
            )
            mTaskList.add(task)
        }
        for (i in 0 until 3) {
            val task = StudyTask(
                day = "11月19日 星期四",
                title = "二年级对话练习[休闲时光]",
                type = "对话",
                grade = "全国小学二年级",
                time = "11月19日开始"
            )
            mTaskList.add(task)
        }
    }
}