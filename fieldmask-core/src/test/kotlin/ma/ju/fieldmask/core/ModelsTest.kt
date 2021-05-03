package ma.ju.fieldmask.core

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("title")
    var title: String,
    @JsonProperty("artist")
    var artist: Artist? = null,
    @JsonProperty("album")
    var album: Album? = null
) {
    override fun toString(): String = "Song<$title>"
    override fun hashCode() = id.hashCode()
}

data class Album(
    @JsonProperty("id")
    var id: Long,
    @JsonProperty("title")
    var title: String,
    @JsonProperty("artist")
    var artist: Artist? = null,
    @JsonProperty("songs")
    var songs: MutableList<Song> = mutableListOf()
) {
    override fun toString(): String = "Album<$title> | Songs[$songs]"
    override fun hashCode() = id.hashCode()
}

data class Artist(var id: Long, var name: String, var albums: MutableList<Album> = mutableListOf()) {
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
            "name,albums/songs(artist/name,title)" to """[{"albums":[{"songs":[{"artist":{"name":"Taylor Swift"},"title":"Fifteen"},{"artist":{"name":"Taylor Swift"},"title":"Love Story"},{"artist":{"name":"Taylor Swift"},"title":"White Horse"}]}],"name":"Taylor Swift"},{"albums":[{"songs":[{"artist":{"name":"Avril Lavigne"},"title":"Complicated"},{"artist":{"name":"Avril Lavigne"},"title":"Sk8er Boi"},{"artist":{"name":"Avril Lavigne"},"title":"I'm With You"}]}],"name":"Avril Lavigne"}]""",
        ).forEach { (q, v) ->
            val data = FieldMask.mask(listOf(artistI, artistII), q).model()
            assertEquals(v, mapper.writeValueAsString(data), q)
        }

        mapOf(
            "name,props(nemesis(name),friends)" to "{name=Peter Pan, props={friends=[Tinker Bell, Captain Hook], nemesis=[{name=Captain Hook}]}}",
        ).forEach { (q, v) ->
            assertEquals(v, FieldMask.mask(peter, q).model().toString(), q)
        }
    }

    @Test
    fun `returns or hides private properties`() {
        mapOf(
            "privateProperty" to "{privateProperty=private}"
        ).forEach { (q, v) ->
            assertEquals(
                v,
                FieldMask.mask(peter, q, MaskOptions(pathOptions = PathOptions(includePrivate = true))).model()
                    .toString(),
                q
            )
            assertThrows<UnknownFieldMaskException> {
                FieldMask.mask(peter, q, MaskOptions(pathOptions = PathOptions(includePrivate = false)))
            }
        }
    }

    @Test
    fun `throws exception on unknown field mask`() {
        assertThrows<UnknownFieldMaskException> {
            FieldMask.mask(peter, "unknown")
        }
    }

    @Test
    fun `ignores exception on unknown field mask when option set`() {
        assertDoesNotThrow {
            FieldMask.mask(peter, "unknown", MaskOptions(validateMasks = false))
        }
    }

    @Test
    fun `returns correct type`() {
        val entity: Album = Album(2, "Album A1").let { album ->
            album.songs.addAll(
                listOf(
                    Song(3, "Song A1-S1"),
                    Song(4, "Song A1-S2")
                )
            )
            album
        }
        val mask = FieldMask.mask(entity, "*")
        val result: Album = mask.value()
        assertEquals(entity, result)
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
            FieldMask.mask(
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
            FieldMask.mask(
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
            FieldMask.apply(a, b, "albums")
            assertEquals("[Album<Album A1> | Songs[[Song<Song A1-S1>, Song<Song A1-S2>]]]", b.albums.toString())
        }

        withArtists { a, b ->
            FieldMask.apply(b, a, "albums/songs")
            assertEquals("[Album<Album A1> | Songs[[Song<Song A2-S1>, Song<Song A1-S2>]]]", a.albums.toString())
        }

        withArtists { a, b ->
            FieldMask.apply(a, b, "*")
            assertEquals(a.toString(), b.toString())
        }

        withArtists { a, b ->
            FieldMask.apply(b, a, "*")
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