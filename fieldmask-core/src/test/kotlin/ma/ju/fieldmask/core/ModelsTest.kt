package ma.ju.fieldmask.core

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.util.concurrent.CompletableFuture

data class TestClass(
    var name: String,
    val age: Int,
    val props: MutableMap<String, Any> = mutableMapOf(),
    val friends: List<TestClass> = listOf()
) {
    private val privateProperty = "private"
}

class TestClassResolver : FieldResolver<TestClass> {
    fun name(data: TestClass): CompletableFuture<String> {
        return CompletableFuture.completedFuture("Mr. ${data.name}: ${data.age}")
    }
}

data class Song(
    var id: Long,
    var title: String,
    @JsonBackReference
    var artist: Artist? = null,
    @JsonBackReference
    var album: Album? = null
) {
    override fun toString(): String = "Song<$title>"
    override fun hashCode() = id.hashCode()
}

data class Album(
    var id: Long,
    var title: String,
    @JsonBackReference
    var artist: Artist? = null,
    @JsonManagedReference
    var songs: MutableList<Song> = mutableListOf(),
    var released: Boolean = false
) {
    override fun toString(): String = "Album<$title> | Songs[$songs]"
    override fun hashCode() = id.hashCode()
}

data class Artist(
    var id: Long,
    var name: String,
    val nullProperty: String? = null,
    val nullList: List<Artist>? = null,
    val nullMap: Map<*, *>? = null,
    @JsonManagedReference
    var albums: MutableList<Album> = mutableListOf()
) {
    override fun toString(): String = "Artist<$name> | Albums[$albums]"
    override fun hashCode() = id.hashCode()
}

class ModelsTest {
    private val artistI: Artist = Artist(1, "Taylor Swift").let { artist ->
        Album(1, "FearLess", artist).let { album ->
            album.songs.addAll(
                listOf(
                    Song(1, "Fifteen", artist, album),
                    Song(2, "Love Story", artist, album),
                    Song(3, "White Horse", artist, album)
                )
            )
            artist.albums.add(album)
        }
        artist
    }

    private val artistII: Artist = Artist(2, "Avril Lavigne").let { artist ->
        Album(2, "Let Go", artist).let { album ->
            album.songs.addAll(
                listOf(
                    Song(4, "Complicated", artist, album),
                    Song(5, "Sk8er Boi", artist, album),
                    Song(6, "I'm With You", artist, album)
                )
            )
            artist.albums.add(album)
        }
        artist
    }

    private val hook = TestClass(
        name = "Captain Hook",
        age = 28,
        props = mutableMapOf(
            "sex" to "Male"
        )
    )
    private val peter = TestClass(
        name = "Peter Pan",
        age = 16,
        props = mutableMapOf(
            "sex" to "Male",
            "occupation" to "Pirate",
            "friends" to listOf("Tinker Bell", "Captain Hook"),
            "nemesis" to listOf(hook)
        ),
        friends = mutableListOf(hook)
    )

    @Test
    fun `returns partial responses`() {
        val mapper = ObjectMapper()
        mapOf(
            "nullProperty,nullList,nullMap" to """[{"nullList":null,"nullMap":null,"nullProperty":null},{"nullList":null,"nullMap":null,"nullProperty":null}]""",
            "name,albums/*(id,title)" to """[{"albums":[{"songs":[{"id":1,"title":"Fifteen"},{"id":2,"title":"Love Story"},{"id":3,"title":"White Horse"}]}],"name":"Taylor Swift"},{"albums":[{"songs":[{"id":4,"title":"Complicated"},{"id":5,"title":"Sk8er Boi"},{"id":6,"title":"I'm With You"}]}],"name":"Avril Lavigne"}]""",
            "name,albums/*/*" to """[{"albums":[{"songs":[{"id":1,"title":"Fifteen"},{"id":2,"title":"Love Story"},{"id":3,"title":"White Horse"}]}],"name":"Taylor Swift"},{"albums":[{"songs":[{"id":4,"title":"Complicated"},{"id":5,"title":"Sk8er Boi"},{"id":6,"title":"I'm With You"}]}],"name":"Avril Lavigne"}]""",
            "albums(released)" to """[{"albums":[{"released":false}]},{"albums":[{"released":false}]}]""",
            "name,albums/songs" to """[{"albums":[{"songs":[{"id":1,"title":"Fifteen"},{"id":2,"title":"Love Story"},{"id":3,"title":"White Horse"}]}],"name":"Taylor Swift"},{"albums":[{"songs":[{"id":4,"title":"Complicated"},{"id":5,"title":"Sk8er Boi"},{"id":6,"title":"I'm With You"}]}],"name":"Avril Lavigne"}]""",
            "name,albums/songs(artist/name,title)" to """[{"albums":[{"songs":[{"artist":{"name":"Taylor Swift"},"title":"Fifteen"},{"artist":{"name":"Taylor Swift"},"title":"Love Story"},{"artist":{"name":"Taylor Swift"},"title":"White Horse"}]}],"name":"Taylor Swift"},{"albums":[{"songs":[{"artist":{"name":"Avril Lavigne"},"title":"Complicated"},{"artist":{"name":"Avril Lavigne"},"title":"Sk8er Boi"},{"artist":{"name":"Avril Lavigne"},"title":"I'm With You"}]}],"name":"Avril Lavigne"}]""",
        ).forEach { (q, v) ->
            val data = BeanMask.mask(listOf(artistI, artistII), q).model()
            assertEquals(v, mapper.writeValueAsString(data), q)
        }

        mapOf(
            "name,props(nemesis(name),friends)" to "{name=Peter Pan, props={friends=[Tinker Bell, Captain Hook], nemesis=[{name=Captain Hook}]}}",
        ).forEach { (q, v) ->
            assertEquals(v, BeanMask.mask(peter, q).model().toString(), q)
        }
    }

    @Test
    fun `returns or hides private properties`() {
        mapOf(
            "privateProperty" to "{privateProperty=private}"
        ).forEach { (q, v) ->
            assertEquals(
                v,
                BeanMask.mask(peter, q, MaskOptions(pathOptions = PathOptions(includePrivate = true))).model()
                    .toString(),
                q
            )
            assertThrows<UnknownFieldMaskException> {
                BeanMask.mask(peter, q, MaskOptions(pathOptions = PathOptions(includePrivate = false)))
            }
        }
    }

    @Test
    fun `throws exception on unknown field mask`() {
        assertThrows<UnknownFieldMaskException> {
            BeanMask.mask(peter, "unknown")
        }
    }

    @Test
    fun `ignores exception on unknown field mask when option set`() {
        assertDoesNotThrow {
            BeanMask.mask(peter, "unknown", MaskOptions(validateMasks = false))
        }
    }

    @Test
    fun `returns expected bean paths`() {
        assertIterableEquals(
            listOf("age", "name", "props/*", "friends/age", "friends/name", "friends/props/*"),
            BeanPaths.paths(TestClass::class).map { it.toString() }
        )
    }

    @Test
    fun `uses value from resolver`() {
        assertEquals(
            "{name=Mr. Peter Pan: 16}",
            BeanMask.mask(
                peter, "name",
                MaskOptions(
                    validateMasks = false,
                    resolvers = listOf(TestClassResolver())
                )
            ).model().toString()
        )
    }

    @Test
    fun `returns a future value`() {
        val completableFuture = CompletableFuture<String>()
        val data = mapOf(
            "name" to "Jack Sparrow",
            "occupation" to completableFuture
        )
        assertEquals(
            "{occupation=Hello}",
            BeanMask.mask(
                data, "occupation",
                MaskOptions(validateMasks = false) { futures ->
                    futures.forEach {
                        completableFuture.complete("Hello")
                        it.get()
                    }
                }
            ).model().toString()
        )
    }

    @Test
    fun `applies field mask to pojo`() {
        withArtists { a, b ->
            BeanMask.apply(a, b, "albums")
            assertEquals("[Album<Album A1> | Songs[[Song<Song A1-S1>, Song<Song A1-S2>]]]", b.albums.toString())
        }

        withArtists { a, b ->
            BeanMask.apply(b, a, "albums/songs")
            assertEquals("[Album<Album A1> | Songs[[Song<Song A2-S1>, Song<Song A1-S2>]]]", a.albums.toString())
        }

        withArtists { a, b ->
            BeanMask.apply(a, b, "*")
            assertEquals(a.toString(), b.toString())
        }

        withArtists { a, b ->
            BeanMask.apply(b, a, "*")
            assertEquals(
                "Artist<Artist II> | Albums[[Album<Album A2> | Songs[[Song<Song A2-S1>, Song<Song A1-S2>]]]]",
                a.toString()
            )
        }
    }

    private fun withArtists(fn: (Artist, Artist) -> Unit) {
        val pair = Pair(
            Artist(1, "Artist I").let { artist ->
                Album(2, "Album A1", artist).let { album ->
                    album.songs.addAll(
                        listOf(
                            Song(3, "Song A1-S1", artist, album),
                            Song(4, "Song A1-S2", artist, album)
                        )
                    )
                    artist.albums.add(album)
                }
                artist
            },
            Artist(5, "Artist II").let { artist ->
                Album(6, "Album A2", artist).let { album ->
                    album.songs.addAll(
                        listOf(
                            Song(7, "Song A2-S1", artist, album),
                        )
                    )
                    artist.albums.add(album)
                }
                artist
            }
        )

        fn(pair.first, pair.second)
    }
}
