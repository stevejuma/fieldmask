package ma.ju.controllers

import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.FieldResolver
import ma.ju.fieldmask.spring.DefaultFieldMaskContextBuilder
import ma.ju.fieldmask.spring.FieldMaskProperties
import ma.ju.fieldmask.spring.FieldMaskResponseBody
import ma.ju.models.Artist
import ma.ju.repository.Repository
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@FieldMaskResponseBody
class ContextController(private val repository: Repository) {
    @RequestMapping("/context/artists", method = [RequestMethod.GET])
    fun listCustomArtists(): Any {
        return ResponseEntity.ok(repository.findAllArtists())
    }
}

interface FieldDataLoader<K, V> {
    /**
     * The name of this loader
     */
    val name: String

    /**
     * The actual loader responsible for batch fetching the data
     */
    fun loader(): DataLoader<K, V>
}

@Component
class ArtistFieldResolver : FieldResolver<Artist> {
    fun albumCount(artist: Artist): Int {
        return artist.albums.size
    }

    fun songs(artist: Artist, context: BeanMask.Context): CompletableFuture<List<String>> {
        val registry = context.properties["dataLoaderRegistry"] as DataLoaderRegistry
        val loader: DataLoader<Long, List<String>> = registry.getDataLoader("ArtistSongsLoader")
        return loader.load(artist.id)
    }
}

@Component
class ArtistSongNameLoader(private val repository: Repository) : FieldDataLoader<Long, List<String>> {
    override val name = "ArtistSongsLoader"
    override fun loader() = DataLoader<Long, List<String>> { ids ->
        CompletableFuture.supplyAsync {
            var response = ids.map { it to mutableListOf<String>() }.toMap().toMutableMap()
            repository.findAllSongs().forEach { song ->
                song.artist?.let {
                    response[it.id]?.let { arr -> arr.add(song.title) }
                }
            }
            ids.map { response[it] ?: mutableListOf() }
        }
    }
}

@Component
class ContextBuilder(
    properties: FieldMaskProperties
) : DefaultFieldMaskContextBuilder(properties) {

    @Autowired(required = false)
    private val resolvers: List<FieldResolver<*>> = listOf()

    @Autowired(required = false)
    private val dataLoaders: List<FieldDataLoader<*, *>> = listOf()

    private fun buildRegistry(): DataLoaderRegistry {
        val registry = DataLoaderRegistry()
        dataLoaders.forEach { registry.register(it.name, it.loader()) }
        return registry
    }

    override fun build(paths: FieldMask, properties: FieldMaskProperties): BeanMask.Context {
        val ctx = super.build(paths, properties)
        val registry = buildRegistry()
        return ctx.copy(
            options = ctx.options.copy(
                resolvers = resolvers,
                futureHandler = { futures ->
                    registry.dispatchAll()
                    futures.map { it.get() }
                }
            ),
            properties = mutableMapOf("dataLoaderRegistry" to registry)
        )
    }
}
