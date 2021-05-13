package ma.ju.fieldmask.spring.app

import ma.ju.fieldmask.core.BeanMask
import ma.ju.fieldmask.core.FieldMask
import ma.ju.fieldmask.core.FieldResolver
import ma.ju.fieldmask.spring.DefaultFieldMaskContextBuilder
import ma.ju.fieldmask.spring.FieldMaskProperties
import ma.ju.fieldmask.spring.FieldMaskResponseBody
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
class RequestScopedController(private val repository: MusicRepository) {
    @RequestMapping("/scoped/artists", method = [RequestMethod.GET])
    fun ListScopedArtists(): ResponseEntity<List<*>> {
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
class ArtistFieldResolver(private val repository: MusicRepository) : FieldResolver<Artist> {
    fun albumCount(artist: Artist): Int {
        return repository.countAlbumsByArtistId(artist.id)
    }

    fun songs(artist: Artist, context: BeanMask.Context): CompletableFuture<List<Song>> {
        val registry = context.properties["dataLoaderRegistry"] as DataLoaderRegistry
        val loader: DataLoader<Long, List<Song>> = registry.getDataLoader("ArtistSongsLoader")
        return loader.load(artist.id)
    }

    fun albums(artist: Artist, context: BeanMask.Context): CompletableFuture<List<String>> {
        val registry = context.properties["dataLoaderRegistry"] as DataLoaderRegistry
        val loader: DataLoader<Long, List<String>> = registry.getDataLoader("ArtistAlbumsLoader")
        return loader.load(artist.id)
    }
}

@Component
class ArtistSongsLoader(private val repository: MusicRepository) : FieldDataLoader<Long, List<Song>> {
    override val name = "ArtistSongsLoader"
    override fun loader() = DataLoader<Long, List<Song>> { ids ->
        CompletableFuture.supplyAsync {
            var response = ids.map { it to mutableListOf<Song>() }.toMap().toMutableMap()
            repository.findAllSongsByArtistIds(ids).forEach { entity ->
                response[entity.artistId]?.let { arr -> arr.add(entity) }
            }
            ids.map { response[it] ?: mutableListOf() }
        }
    }
}

@Component
class ArtistAlbumLoader(private val repository: MusicRepository) : FieldDataLoader<Long, List<Album>> {
    override val name = "ArtistAlbumsLoader"
    override fun loader() = DataLoader<Long, List<Album>> { ids ->
        CompletableFuture.supplyAsync {
            var response = ids.map { it to mutableListOf<Album>() }.toMap().toMutableMap()
            repository.findAllAlbumsByArtistIds(ids).forEach { entity ->
                response[entity.artistId]?.let { arr -> arr.add(entity) }
            }
            ids.map { response[it] ?: mutableListOf() }
        }
    }
}

@Component("fieldMaskContextBuilder")
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
                    registry.dataLoaders.map { it.dispatchAndJoin() }
                    futures.map { it.get() }
                }
            ),
            properties = mutableMapOf("dataLoaderRegistry" to registry)
        )
    }
}
