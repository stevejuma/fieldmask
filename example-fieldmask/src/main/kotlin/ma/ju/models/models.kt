package ma.ju.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

enum class LikeType { SONG, ARTIST, ALBUM, FAN }
data class Like(val fanId: Long, val entityId: Long, val type: LikeType)

data class Fan(
    val id: Long,
    val name: String,
    val likes: MutableSet<Like> = mutableSetOf()
) {
    override fun equals(other: Any?) = (other is Fan?) && id == other?.id
    override fun hashCode() = id.hashCode()

    fun like(vararg data: Artist) = data.map { Like(id, it.id, LikeType.ARTIST) }.apply { likes.addAll(this) }
    fun like(vararg data: Song) = data.map { Like(id, it.id, LikeType.SONG) }.apply { likes.addAll(this) }
    fun like(vararg data: Album) = data.map { Like(id, it.id, LikeType.ALBUM) }.apply { likes.addAll(this) }
    fun like(vararg data: Fan) = data.map { Like(id, it.id, LikeType.FAN) }.apply { likes.addAll(this) }
}

data class Artist(
    val id: Long,
    var name: String,
    @JsonManagedReference
    val albums: MutableSet<Album> = mutableSetOf()
) {
    override fun equals(other: Any?) = (other is Artist?) && id == other?.id
    override fun hashCode() = id.hashCode()

    fun addAlbums(vararg data: Album) {
        data.forEach {
            it.artist = this
            albums.add(it)
        }
    }
}

data class Album(
    val id: Long,
    var title: String,
    @JsonBackReference
    var artist: Artist? = null,
    @JsonManagedReference
    var songs: MutableSet<Song> = mutableSetOf()
) {
    override fun equals(other: Any?) = (other is Album?) && id == other?.id
    override fun hashCode() = id.hashCode()

    fun addSongs(vararg data: Song) {
        data.forEach {
            it.album = this
            artist?.let { a -> it.artist = a }
            songs.add(it)
        }
    }
}

data class Song(
    val id: Long,
    val title: String,
    var artist: Artist? = null,
    @JsonBackReference
    var album: Album? = null
) {
    override fun equals(other: Any?) = (other is Song?) && id == other?.id
    override fun hashCode() = id.hashCode()
}
