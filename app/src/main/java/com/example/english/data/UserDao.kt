package com.example.english.data

import androidx.room.*


@Dao
interface UserDao {

    @Query("select * from users")
    fun loadAll(): List<User>

    @Query("select * from users where uid in(:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("select * from users where uid = :uid")
    fun findById(uid: Long): User

    @Query("select * from users where user_name like :name")
    fun findByName(name: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(vararg users: User): Int

    @Update
    fun update(vararg users: User): Int


    @Transaction
    @Query("select * from users")
    fun getUsersAndLibraries(): List<UserAndLibrary>

    @Transaction
    @Query("select * from users")
    fun getUsersWithPlaylists(): List<UserWithPlaylists>

    @Transaction
    @Query("select * from users where uid = :uid")
    fun getUsersWithPlaylistsById(uid: Long): UserWithPlaylists

    @Transaction
    @Query("select * from playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("select * from playlist where playlist_id = :playlistId")
    fun getPlaylistsWithSongsById(playlistId: Long): PlaylistWithSongs

    @Transaction
    @Query("select * from song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>

    @Transaction
    @Query("select * from song where song_id = :songId")
    fun getSongsWithPlaylistsById(songId: Long): SongWithPlaylists

    @Transaction
    @Query("select * from users")
    fun getUserWithPlaylistsAndSongs(): List<UserWithPlaylistsAndSongs>

    @Transaction
    @Query("select * from users where uid = :uid")
    fun getUserWithPlaylistsAndSongsById(uid: Long): UserWithPlaylistsAndSongs
}