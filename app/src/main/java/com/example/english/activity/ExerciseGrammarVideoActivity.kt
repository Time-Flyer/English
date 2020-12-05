package com.example.english.activity

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.english.R
import com.example.english.adapter.GrammarVideoAdapter
import com.example.english.adapter.VIEW_TYPE_HEAD
import com.example.english.adapter.VIEW_TYPE_MAIN
import com.example.english.databinding.ActivityExerciseGrammarVideoBinding
import com.example.english.entity.GrammarVideoBean
import com.example.english.util.MyActionBar
import com.google.android.material.tabs.TabLayout


class ExerciseGrammarVideoActivity : AppCompatActivity() {

    private val mDataList: List<GrammarVideoBean> = listOf(
        GrammarVideoBean(true, 1, "热门词法"),
        GrammarVideoBean(false, title = "人称代词"),
        GrammarVideoBean(false, title = "物主代词"),
        GrammarVideoBean(false, title = "名词分类"),
        GrammarVideoBean(false, title = "名词所有格"),
        GrammarVideoBean(false, title = "冠词"),
        GrammarVideoBean(false, title = "时间介词"),
        GrammarVideoBean(false, title = "地点介词"),
        GrammarVideoBean(true, 2, "重点时态"),
        GrammarVideoBean(false, title = "一般现在时"),
        GrammarVideoBean(false, title = "一般过去时"),
        GrammarVideoBean(false, title = "一般将来时"),
        GrammarVideoBean(false, title = "现在进行时"),
        GrammarVideoBean(false, title = "过去进行时"),
        GrammarVideoBean(false, title = "现在完成时1"),
        GrammarVideoBean(false, title = "现在完成时2"),
        GrammarVideoBean(true, 3, "黄金句型"),
        GrammarVideoBean(false, title = "There be句型"),
        GrammarVideoBean(false, title = "宾语从句"),
        GrammarVideoBean(false, title = "感叹句"),
        GrammarVideoBean(false, title = "特殊疑问句"),
        GrammarVideoBean(false, title = "时间状从"),
        GrammarVideoBean(false, title = "条件状从"),
        GrammarVideoBean(false, title = "It系列句型")
    )

    private val levelPos = listOf(0, 8, 16)

    private val mAdapter = GrammarVideoAdapter(mDataList)

    private val mGridLayoutManager = GridLayoutManager(this, 2).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (mAdapter.getItemViewType(position) == VIEW_TYPE_HEAD) {
                    VIEW_TYPE_HEAD
                } else {
                    VIEW_TYPE_MAIN
                }
            }
        }
    }

    private var mIsScrolling = false

    private var mCurrentSelectTab = 0

    private lateinit var mBinding: ActivityExerciseGrammarVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityExerciseGrammarVideoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        initEvents()
    }

    private fun RecyclerView.smoothSnapToPosition(
        position: Int,
        snapMode: Int = LinearSmoothScroller.SNAP_TO_START
    ) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }

    private fun initEvents() {
        mBinding.mabExerciseGrammarVideo.setOnBackClickListener(object :
            MyActionBar.OnBackClickListener {
            override fun backClick() {
                finish()
            }
        })

        mBinding.tlExerciseGrammarVideo.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                setTabTextNormal(mCurrentSelectTab)
                mCurrentSelectTab = tab.position
                if (!mIsScrolling) {
                    mBinding.rvExerciseGrammarVideo.smoothSnapToPosition(levelPos[mCurrentSelectTab])
                }
                setTabTextBold(mCurrentSelectTab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Log.i("recyclerview", "onTabUnselected")
                setTabTextNormal(tab.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                mBinding.rvExerciseGrammarVideo.smoothSnapToPosition(levelPos[tab.position])
            }
        })

        mBinding.rvExerciseGrammarVideo.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    mIsScrolling = true
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mIsScrolling = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (mIsScrolling) {
                    setTabTextNormal(mCurrentSelectTab)
                    val itemPos: Int
                    if (dy > 0) {
                        itemPos = mGridLayoutManager.findLastVisibleItemPosition()
                        Log.i("recyclerview", "itemPos = $itemPos")
                        if (itemPos == 12) {
                            mCurrentSelectTab = 1
                        } else if (itemPos == 20) {
                            mCurrentSelectTab = 2
                        }
                    } else {
                        itemPos = mGridLayoutManager.findFirstVisibleItemPosition()
                        Log.i("recyclerview", "itemPos = $itemPos")
                        if (itemPos == 13) {
                            mCurrentSelectTab = 1
                        } else if (itemPos == 5) {
                            mCurrentSelectTab = 0
                        }
                    }
                    mBinding.tlExerciseGrammarVideo.setScrollPosition(mCurrentSelectTab, 0f, true)
                    setTabTextBold(mCurrentSelectTab)
                }
            }
        })
    }

    private fun initView() {
        mBinding.mabExerciseGrammarVideo.setTitle("新概念语法课")
        mBinding.mabExerciseGrammarVideo.setTitleColor(Color.BLACK)

        mBinding.tlExerciseGrammarVideo.addTab(
            mBinding.tlExerciseGrammarVideo.newTab().setText("Level 1")
        )
        mBinding.tlExerciseGrammarVideo.addTab(
            mBinding.tlExerciseGrammarVideo.newTab().setText("Level 2")
        )
        mBinding.tlExerciseGrammarVideo.addTab(
            mBinding.tlExerciseGrammarVideo.newTab().setText("Level 3")
        )

        setTabTextBold(mCurrentSelectTab)

        mBinding.rvExerciseGrammarVideo.layoutManager = mGridLayoutManager
        mBinding.rvExerciseGrammarVideo.adapter = mAdapter
    }

    private fun setTabTextBold(tabPos: Int) {
        val textView = ((mBinding.tlExerciseGrammarVideo.getChildAt(0) as LinearLayout)
            .getChildAt(tabPos) as TabLayout.TabView)
            .getChildAt(1) as TextView
        textView.typeface = Typeface.DEFAULT_BOLD
    }

    private fun setTabTextNormal(tabPos: Int) {
        val textView = ((mBinding.tlExerciseGrammarVideo.getChildAt(0) as LinearLayout)
            .getChildAt(tabPos) as TabLayout.TabView)
            .getChildAt(1) as TextView
        textView.setTextAppearance(R.style.tabLayoutAppearance)
    }

}

/*
    private fun TabLayout.Tab.selectText(selected: Boolean) {
        this.apply {
            val fieldView = javaClass.getDeclaredField("view")
            fieldView.isAccessible = true
            val view = fieldView.get(this)
            val fieldText = view.javaClass.getDeclaredField("textView")
            fieldText.isAccessible = true
            if (selected) {
                tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            } else {
                tabSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            }
            tabSelect.text = this.text
        }
    }
 */