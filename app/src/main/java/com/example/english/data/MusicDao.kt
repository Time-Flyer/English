package com.example.english.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MusicDao {

    @Insert
    fun insertSong(vararg song: Song)

    @Query("select * from song where song_id = :songId")
    fun selectSongById(songId: Long): Song

    @Insert
    fun insertPlaylist(vararg playlist: Playlist)

    @Query("select * from playlist where playlist_id = :playlistId")
    fun selectPlaylistById(playlistId: Long): Playlist

    @Insert
    fun createPlaylist(vararg crossRef: PlaylistSongCrossRef)
}