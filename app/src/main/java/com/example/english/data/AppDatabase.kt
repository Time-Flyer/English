package com.example.english.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [User::class, Library::class, Playlist::class, Song::class,
    PlaylistSongCrossRef::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun musicDao(): MusicDao
}

