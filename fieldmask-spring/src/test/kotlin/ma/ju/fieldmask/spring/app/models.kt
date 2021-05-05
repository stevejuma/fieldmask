package ma.ju.fieldmask.spring.app

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Service

data class Artist(var id: Long, var name: String) {
    override fun equals(other: Any?) = (other is Artist?) && id == other?.id
    override fun hashCode() = id.hashCode()
}

data class Album(
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("title")
    var title: String,
    @JsonProperty("artistId")
    var artistId: Long,
    @JsonProperty("year")
    var year: Long
) {
    override fun equals(other: Any?) = (other is Album?) && id == other?.id
    override fun hashCode() = id.hashCode()
}

data class Song(var id: Long, var title: String, var albumId: Long, var artistId: Long) {
    override fun equals(other: Any?) = (other is Song?) && id == other?.id
    override fun hashCode() = id.hashCode()
}

@Service
class MusicRepository {
    private val artists = listOf(
        Artist(id = 100, name = "Avril Lavigne"),
        Artist(id = 101, name = "Taylor Swift")
    )

    private val albums = listOf(
        Album(id = 200, title = "Let Go", year = 2002, artistId = 100),
        Album(id = 201, title = "FearLess", year = 2002, artistId = 101)
    )

    private val songs = listOf(
        Song(301, "Losing Grip", 200, 100),
        Song(302, "Complicated", 200, 100),
        Song(303, "Sk8er Boi", 200, 100),
        Song(304, "I'm with You", 200, 100),
        Song(305, "FearLess", 201, 101),
        Song(306, "Fifteen", 201, 101),
        Song(307, "Love Story", 201, 101),
        Song(308, "Hey Stephen", 201, 101),
    )

    fun findAllArtists() = artists.toList()
    fun findAllArtistsById(ids: List<Long>) = artists.filter { ids.contains(it.id) }
    fun findArtistById(id: Long) = artists.firstOrNull { it.id == id }
    fun countAlbumsByArtistId(id: Long) = artists.filter { it.id == id }.size

    fun findAllAlbums() = albums.toList()
    fun findAllAlbumsByIds(ids: List<Long>) = albums.filter { ids.contains(it.id) }
    fun findAllAlbumsByArtistIds(ids: List<Long>) = albums.filter { ids.contains(it.artistId) }
    fun findAlbumById(id: Long) = albums.firstOrNull { it.id == id }

    fun findAllSongs() = songs.toList()
    fun findAllSongsByAlbumIds(albumIds: List<Long>) = songs.filter { albumIds.contains(it.albumId) }
    fun findAllSongsByArtistIds(artistIds: List<Long>) = songs.filter { artistIds.contains(it.artistId) }

    fun findSongById(id: Long) = songs.firstOrNull { it.id == id }
}
