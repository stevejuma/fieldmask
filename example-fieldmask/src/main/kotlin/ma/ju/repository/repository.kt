package ma.ju.repository

import ma.ju.models.Album
import ma.ju.models.Artist
import ma.ju.models.Fan
import ma.ju.models.Like
import ma.ju.models.Song
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class Repository {
    private val artists = mutableSetOf<Artist>()
    private val albums = mutableSetOf<Album>()
    private val songs = mutableSetOf<Song>()
    private val fans = mutableSetOf<Fan>()
    private val likes = mutableSetOf<Like>()

    fun findAllArtists() = artists.toList()
    fun findAllAlbums() = albums.toList()
    fun findAllSongs() = songs.toList()
    fun findAllFans() = fans.toList()
    fun findAllLikes() = likes.toList()

    fun findArtistById(id: Long) = artists.firstOrNull { it.id == id }
    fun findAlbumById(id: Long) = albums.firstOrNull { it.id == id }
    fun findSongById(id: Long) = songs.firstOrNull { it.id == id }
    fun findFansById(id: Long) = fans.firstOrNull { it.id == id }
    fun findLikesByFanId(id: Long) = likes.firstOrNull { it.fanId == id }
    fun findLikesByEntityId(id: Long) = likes.firstOrNull { it.entityId == id }

    @PostConstruct
    fun init() {
        val taylorSwift = Artist(1, "Taylor Swift")
        val fearlessAlbum = Album(1, "FearLess")
        taylorSwift.addAlbums(fearlessAlbum)
        fearlessAlbum.addSongs(
            Song(1, "FearLess"),
            Song(2, "Fifteen"),
            Song(3, "Love Story"),
            Song(4, "Hey Stephen"),
            Song(5, "White Horse"),
            Song(6, "You Belong with Me"),
            Song(7, "Breathe"),
            Song(8, "Tell Me Why"),
            Song(9, "You're Not Sorry"),
            Song(10, "The Way I Loved You"),
            Song(12, "Forever & Always"),
            Song(13, "The Best Day"),
            Song(14, "Change"),
        )

        val avrilLavigne = Artist(2, "Avril Lavigne")
        val letGoAlbum = Album(2, "Let Go")
        avrilLavigne.addAlbums(letGoAlbum)
        letGoAlbum.addSongs(
            Song(15, "Losing Grip"),
            Song(16, "Complicated"),
            Song(17, "Sk8er Boi"),
            Song(18, "I'm with You"),
            Song(19, "Mobile"),
            Song(20, "Unwanted"),
            Song(21, "Tomorrow"),
            Song(22, "Anything But Ordinary")
        )

        val fanI = Fan(1, "Fan I")
        fanI.like(letGoAlbum)
        fanI.like(*fearlessAlbum.songs.filter { it.id <= 5 }.toTypedArray())

        val fanII = Fan(2, "Fan II")
        fanII.like(*letGoAlbum.songs.filter { it.id >= 19 }.toTypedArray())

        artists.addAll(listOf(taylorSwift, avrilLavigne))
        albums.addAll(listOf(fearlessAlbum, letGoAlbum))
        songs.addAll(fearlessAlbum.songs.toList().plus(letGoAlbum.songs))
        fans.addAll(listOf(fanI, fanII))
        likes.addAll(fanI.likes.toList().plus(fanII.likes))
    }
}
