package com.example.english.data

import androidx.room.*


data class Address(
    val state: String = "",
    val province: String = "",
    val city: String = "",
    val county: String = ""
)

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val userId: Long = 0,
    @ColumnInfo(name = "user_name") val userName: String = "",
    val gender: String? = "",
    val age: Int? = null,
    val telephone: String = "",
    @Embedded val address: Address = Address()
) {
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "uid")
//    val id: Long = 0
}


@Entity(tableName = "library")
data class Library(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "library_id") val libraryId: Long = 0,
    @ColumnInfo(name = "owner_id") val ownerId: Long,
    @ColumnInfo(name = "library_name") val libraryName: String
)

data class UserAndLibrary(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "owner_id"
    )
    val library: Library
)


@Entity(tableName = "playlist")
data class Playlist(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "playlist_id") val playlistId: Long = 0,
    @ColumnInfo(name = "creator_id") val creatorId: Long,
    @ColumnInfo(name = "playlist_name") val playlistName: String = ""
)

data class UserWithPlaylists(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "creator_id"
    )
    val playlists: List<Playlist>
)

@Entity(tableName = "song")
data class Song(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "song_id") val songId: Long = 0,
    @ColumnInfo(name = "song_name") val songName: String = "",
    val artist: String = ""
)

@Entity(primaryKeys = ["playlist_id", "song_id"])
data class PlaylistSongCrossRef(
    @ColumnInfo(name = "playlist_id") val playlistId: Long = 0,
    @ColumnInfo(name = "song_id") val songId: Long = 0
)

data class PlaylistWithSongs(
    @Embedded val playlist: Playlist,
    @Relation(
        parentColumn = "playlist_id",
        entityColumn = "song_id",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)

data class SongWithPlaylists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "song_id",
        entityColumn = "playlist_id",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playlists: List<Playlist>
)

data class UserWithPlaylistsAndSongs(
    @Embedded val user: User,
    @Relation(
        entity = Playlist::class,
        parentColumn = "uid",
        entityColumn = "creator_id"
    )
    val playlists: List<PlaylistWithSongs>
)