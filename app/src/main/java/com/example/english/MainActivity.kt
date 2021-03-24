package com.example.english

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.viewpager.widget.PagerAdapter
import com.example.english.adapter.GuidePageViewPagerAdapter
import com.example.english.data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao
    private lateinit var musicDao: MusicDao
    private lateinit var db: AppDatabase

    private var mIsLogin = false

    private val imgs = arrayOf(
        R.drawable.begin1,
        R.drawable.begin2,
        R.drawable.begin3,
        R.drawable.begin4
    )

    lateinit var viewList: List<View>

    private lateinit var mAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
        welcome()
    }

    private fun welcome() {
        val view1 = LayoutInflater.from(this).inflate(R.layout.view_guide1, null)
        val view2 = LayoutInflater.from(this).inflate(R.layout.view_guide2, null)
        val view3 = LayoutInflater.from(this).inflate(R.layout.view_guide3, null)
        val view4 = LayoutInflater.from(this).inflate(R.layout.view_guide4, null)
        viewList = listOf(view1, view2, view3, view4)

        mAdapter = GuidePageViewPagerAdapter(viewList)

        vp_welcome.adapter = mAdapter // TODO error fix: LoopViewPager is default!
    }

    private fun getData() {
        initDb()
        CoroutineScope(Dispatchers.IO).launch {
            writeUserAndReadInList()
        }
    }

    private fun initDb() {
        db = Room.databaseBuilder(
            this, AppDatabase::class.java, "test_db"
        ).build()
        userDao = db.userDao()
        musicDao = db.musicDao()
    }

    private fun writeUserAndReadInList() {
//        userDao.insertAll(User(userName = "张三"), User(userName = "李四"), User(userName = "王五"))
//        musicDao.insertPlaylist(Playlist(creatorId = 1, playlistName = "民谣"), Playlist(creatorId = 1, playlistName = "纯音乐"), Playlist(creatorId = 1, playlistName = "流行"))
//        musicDao.insertSong(Song(songName = "南山南", artist = "马頔"), Song(songName = "安和桥", artist = "宋冬野"), Song(songName = "晚安", artist = "丢火车乐队"))
//        musicDao.createPlaylist(PlaylistSongCrossRef(playlistId = 1, songId = 1), PlaylistSongCrossRef(playlistId = 1, songId = 2), PlaylistSongCrossRef(playlistId = 1, songId = 3))
//        musicDao.createPlaylist(PlaylistSongCrossRef(playlistId = 3, songId = 1))

        val userWithPlaylist = userDao.getUsersWithPlaylistsById(1)
        Log.i("测试", "用户${userDao.findById(1).userName}的播放列表 = ${userWithPlaylist.playlists}")

        val playlistWithSongs = userDao.getPlaylistsWithSongsById(1)
        Log.i("测试", "播放列表${musicDao.selectPlaylistById(1).playlistName}的歌曲 = ${playlistWithSongs.songs}")

        val songWithPlaylists = userDao.getSongsWithPlaylistsById(1)
        Log.i("测试", "歌曲${musicDao.selectSongById(1).songName}所在的播放列表 = ${songWithPlaylists.playlists}")

        val userWithPlaylistsAndSongs = userDao.getUserWithPlaylistsAndSongsById(1)
        Log.i("测试", "用户${userDao.findById(1).userName}的播放列表的歌曲 = ${userWithPlaylistsAndSongs.playlists}")
    }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("alter table 'users' add column 'gender' text")
        }
    }

    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("alter table 'users' add column 'age' Integer")
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}