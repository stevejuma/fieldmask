package ma.ju.fieldmask.core

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import java.time.Instant
import java.util.Date
import java.util.Stack
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.jvmErasure

/**
 * Checks if the specified [property] is publicly accessible
 * @return boolean indicating the field visibility
 */
fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
    return property.javaGetter?.modifiers?.let { !Modifier.isPrivate(it) } ?: false
}

/**
 * Checks if the specified [any] object is a primitive value
 * @return boolean indicating if the object is a primitive
 */
fun isPrimitive(any: Any?): Boolean {
    any ?: return true
    val clazz = if (any is Class<*>) any else any.javaClass
    val primitives = setOf(
        String::class.java, Number::class.java, java.lang.Boolean::class.java, Char::class.java,
        Date::class.java, Double::class.java, Long::class.java, Integer::class.java, Byte::class.java, Character::class.java,
        Short::class.java, Integer::class.java, Float::class.java, Void::class.java, Instant::class.java,
        java.lang.Boolean.TYPE, java.lang.Byte.TYPE, Character.TYPE, java.lang.Short.TYPE,
        Integer.TYPE, java.lang.Long.TYPE, java.lang.Double.TYPE, java.lang.Float.TYPE, Void.TYPE
    )
    return clazz.isPrimitive ||
        clazz.isEnum ||
        primitives.any { it == clazz } ||
        primitives.any { it.isAssignableFrom(clazz) }
}

/**
 * FieldResolver is used to resolve properties for fields of type T. Resolvers always
 * take precedence over fields defined in the object. The name of the functions defined
 * in the resolver should match the name of the property. E.g for a property named `songCount`
 * the code will search for methods of the following signature (in order):
 *
 *  - `fun songCount/getSongCount(artist: Artist, context: Context)`
 *  - `fun songCount/getSongCount(artist: Artist)`
 *  - `fun songCount/getSongCount()`
 *
 * ```kotlin
 *  class ArtistResolver : FieldResolver<Artist> {
 *      fun songCount(artist: Artist): Long = artist.songs.size
 *  }
 * ```
 *
 * @property T The object whose fields will be resolved
 */
interface FieldResolver<T> {
    /**
     * Fetches the Type for this resolver
     */
    @Suppress("UNCHECKED_CAST")
    fun dataType(): Class<T> {
        javaClass.genericInterfaces.forEach { iface ->
            if (iface is ParameterizedType) {
                iface.actualTypeArguments.forEach {
                    try {
                        return it as Class<T>
                    } catch (e: Exception) {
                    }
                }
            }
        }
        throw IllegalArgumentException("Undefined generic type")
    }
}

/**
 * Interface for data model
 * @property T the underlying type for the model
 */
interface FieldModel<T> {
    /**
     * The underlying data for this model
     */
    fun model(): T
}

/**
 * Generic interface for a List backed model
 * @property T the underlying list
 */
interface ListModel<T> : FieldModel<T> {
    /**
     * Adds the [value] to the list
     */
    fun add(value: Any?)

    /**
     * Creates a new [MapModel] and adds it to the list
     */
    fun createMap(): MapModel<*>
}

/**
 * Generic interface for a Map backed model
 * @property T the underlying Map
 */
interface MapModel<T> : FieldModel<T> {
    /**
     * Adds the [value] to the map with the specified [key]
     */
    fun add(key: String, value: Any?)

    /**
     * Creates a new model with the specified [name]
     */
    fun createMap(name: String): MapModel<*>

    /**
     * Creates a new list model with the [name]
     */
    fun createList(name: String): ListModel<*>
}

/**
 * An empty model that returns null
 */
class NullModel : FieldModel<Nothing?> {
    override fun model() = null
}

/**
 * Default implementation of the [ListModel] using a [MutableList]
 * @property list The list that will back this model
 */
class DefaultListModel(private val list: MutableList<Any?> = mutableListOf()) : ListModel<MutableList<Any?>> {
    override fun model() = list

    override fun add(value: Any?) {
        list.add(value)
    }

    override fun createMap(): MapModel<*> {
        val child = DefaultMapModel()
        add(child.model())
        return child
    }
}

/**
 * Default implementation of the [MapModel] using a [MapModel]
 * @property map The map that will back this model
 */
class DefaultMapModel(private val map: MutableMap<String, Any?> = linkedMapOf()) : MapModel<MutableMap<String, Any?>> {
    override fun createMap(name: String): MapModel<*> {
        val child = DefaultMapModel()
        map[name] = child.model()
        return child
    }

    override fun createList(name: String): ListModel<*> {
        val child = DefaultListModel()
        map[name] = child.model()
        return child
    }

    override fun add(key: String, value: Any?) {
        map[key] = value
    }

    override fun model() = map
}

/**
 * Bean masking options
 */
data class MaskOptions(
    /**
     * Property indicating whether we should be validating the field masks provided
     * against the POJO being masked
     */
    val validateMasks: Boolean = true,
    /**
     * Field resolvers defined
     */
    val resolvers: List<FieldResolver<*>> = listOf(),
    /**
     * Bean path fetch options
     */
    val pathOptions: PathOptions = PathOptions(),
    /**
     * Handler for working with loaded futures
     */
    val futureHandler: (List<CompletableFuture<*>>) -> Unit = { }
) {
    /**
     * A map of resolvers with the dataType as the key and the resolver
     * as a value
     */
    val resolversMap = resolvers.map { it.dataType() to it }.toMap()
}

/**
 * BeanMask is responsible for masking and unmasking objects
 */
@Suppress("UNCHECKED_CAST")
object BeanMask {

    /**
     * The Context used when parsing the field masks
     *
     * @property mask The list of paths to either mask or copy
     * @property root The parent [Path] for this context
     * @property options The options for the field processing
     * @property futures The list of futures that are waiting to be loaded
     * @property properties A map with generic properties that Loaders can use to store state
     */
    data class Context(
        val mask: FieldMask,
        val root: Path = Path(),
        val options: MaskOptions = MaskOptions(),
        val futures: MutableList<CompletableFuture<*>> = mutableListOf(),
        val properties: MutableMap<String, Any?> = mutableMapOf(),
        val depth: Stack<String> = Stack()
    ) {
        /**
         * Checks if the Loaded mask matches the specified [path] segment
         */
        fun matches(path: Segment) = mask.matches(root.join(path))

        /**
         * Returns a copy of this context with the [root] joined to the specified [path]
         */
        fun withRoot(path: Segment): Context {
            return copy(root = root.copy().join(path))
        }
    }

    /**
     * Copies all the properties from [source] to [target] that match the specified mask [query]
     * with the specified [options]
     *
     * @param source The object to copy the properties from
     * @param target The object to copy the properties to
     * @param query The field mask query to use to select the fields to copy
     * @param options The options to use to apply the properties
     */
    fun <T : Any> apply(source: T, target: T, query: String, options: MaskOptions = MaskOptions()) {
        apply(source, target, Context(FieldMask.matcherFor(query), Path(), options))
    }

    /**
     * Copies all the properties from [source] to [target] that match the specified [context]
     *
     * @param source The object to copy the properties from
     * @param target The object to copy the properties to
     * @param context The context ot use to apply the properties
     */
    fun <T : Any> apply(source: T, target: T, context: Context) {
        if (!source::class.java.isAssignableFrom(target::class.java)) {
            throw java.lang.IllegalArgumentException("source class: ${source.javaClass} is not assignable from ${target.javaClass}")
        }
        applyPojo(source, target, context)
    }

    private fun applyField(sourceValue: Any, targetValue: Any, property: KProperty1<Any?, *>, context: Context) {
        val source = property.get(sourceValue)
        val target = property.get(targetValue)

        if (property !is KMutableProperty<*>) return

        if (target == null || source == null || isPrimitive(source)) {
            property.setter.call(targetValue, source)
            return
        }

        when (source) {
            is List<*> -> {
                val data = target as MutableList<Any?>
                applyList(source, data, context)
            }
            is Map<*, *> -> {
                val data = target as MutableMap<Any?, Any?>
                applyMap(source, data, context)
            }
            else -> {
                applyPojo(source, target, context)
            }
        }
    }

    private fun applyList(source: List<*>, target: MutableList<Any?>, context: Context) {
        if (source.isEmpty()) {
            target.clear()
            return
        }

        for ((index, srcValue) in source.withIndex()) {
            if (index < target.size) {
                val targetValue = target[index]
                if (srcValue == null || targetValue == null || isPrimitive(srcValue)) {
                    target[index] = srcValue
                } else {
                    when (srcValue) {
                        is List<*> -> {
                            applyList(srcValue, targetValue as MutableList<Any?>, context)
                        }
                        is Map<*, *> -> {
                            applyMap(srcValue, targetValue as MutableMap<Any?, Any?>, context)
                        }
                        else -> {
                            applyPojo(srcValue, targetValue, context)
                        }
                    }
                }
            } else {
                target.add(srcValue)
            }
        }
    }

    private fun applyMap(source: Map<*, *>, target: MutableMap<Any?, Any?>, context: Context) {
        if (applyCached(source, context)) return
        for (entry in source.entries) {
            val field = entry.key.toString()
            val m = context.matches(Segment(field))
            if (m.paths.isEmpty()) continue
            val sourceValue = entry.value
            val targetValue = target[field]

            if (targetValue == null || sourceValue == null || isPrimitive(sourceValue)) {
                target[field] = targetValue
            } else {
                apply(sourceValue, targetValue, context.withRoot(Segment(field)))
            }
        }
    }

    private fun applyCached(source: Any?, context: Context): Boolean {
        val cacheKey = "${source?.javaClass?.name}:${System.identityHashCode(source)}"
        if (context.properties.containsKey(cacheKey)) return true
        context.properties[cacheKey] = true
        return false
    }

    private fun applyPojo(source: Any, target: Any, context: Context) {
        if (applyCached(source, context)) return
        val properties = source::class.memberProperties.filter {
            val accessible = isFieldAccessible(it)
            if (context.options.pathOptions.includePrivate) {
                if (!accessible) {
                    it.isAccessible = true
                }
                true
            } else {
                accessible
            }
        }
        for (property in properties) {
            val p = property as KProperty1<Any?, *>
            val m = context.matches(Segment(p.name))
            if (m.paths.isEmpty()) continue

            if (p is KMutableProperty<*>) {
                applyField(source, target, p, context.withRoot(Segment(p.name)))
            }
        }
    }

    /**
     * Masks all the fields in [instance] that match the specified [query] with the given [options]
     * @return [FieldModel] containing only the properties that matched the [query]
     *
     * @param instance The object to apply the masks to
     * @param query The field mask query to parse
     * @param options The options to use to mask the properties
     */
    fun mask(instance: Any?, query: String, options: MaskOptions = MaskOptions()): FieldModel<*> {
        return mask(
            instance, Context(FieldMask.matcherFor(query), Path(), options)
        )
    }

    /**
     * Masks all the fields in [instance] that match the specified [context]
     *
     * @param instance The object to apply the masks to
     * @param context The context to use to mask the fields
     */
    fun mask(instance: Any?, context: Context): FieldModel<*> {
        if (instance == null) return NullModel()
        val data = when (instance) {
            is Iterable<*> -> {
                val model = DefaultListModel()
                visitList(instance, model, context)
                model
            }
            is Map<*, *> -> {
                val model = DefaultMapModel()
                visitMap(instance, model, context)
                model
            }
            else -> {
                val model = DefaultMapModel()
                visitPojo(instance, model, context)
                model
            }
        }
        if (context.futures.isNotEmpty()) {
            context.options.futureHandler(context.futures)
        }
        return data
    }

    private fun visitList(instance: Iterable<*>?, model: ListModel<*>, context: Context) {
        if (instance == null) return
        for ((i, child) in instance.withIndex()) {
            context.depth.push(i.toString())
            if (child == null || isPrimitive(child)) {
                model.add(child)
            } else if (child is Map<*, *>) {
                visitMap(child, model.createMap(), context)
            } else {
                visitPojo(child, model.createMap(), context)
            }
            context.depth.pop()
        }
    }

    private fun visitMap(instance: Map<*, *>?, model: MapModel<*>, context: Context) {
        if (instance == null) return
        for (entry in instance.entries) {
            val field = entry.key.toString()
            context.depth.push(field)
            val m = context.matches(Segment(field))
            if (m.paths.isEmpty()) continue
            val value = entry.value
            if (value != null) {
                addField(m.paths.last(), value, model, context, isPrimitive(value))
            }
            context.depth.pop()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun visitPojo(instance: Any?, model: MapModel<*>, context: Context) {
        if (instance == null) return
        val klass = if (instance is Class<*>) instance else instance.javaClass
        val properties = instance::class.memberProperties.filter {
            val accessible = isFieldAccessible(it)
            if (context.options.pathOptions.includePrivate) {
                if (!accessible) {
                    it.isAccessible = true
                }
                true
            } else {
                accessible
            }
        }.sortedBy { it.name }
        val resolverMethods = extractMethod(context.options.resolversMap[klass], instance, context)

        if (context.options.validateMasks) {
            val paths = mutableListOf<Path>()
            paths.addAll(
                BeanPaths.paths(
                    instance.javaClass,
                    BeanPaths.Context(options = context.options.pathOptions)
                )
            )
            resolverMethods.keys.forEach { m ->
                paths.add(Path(m.name))
                if (m.name.startsWith("get")) {
                    paths.add(Path(m.name.replaceFirst("get", "").decapitalize()))
                }
            }
            paths.addAll(resolverMethods.keys.map { Path(it.name) })
            var beanPaths = FieldMask(paths)
            val invalid =
                context.mask.withPrefix(context.root, true).values().filter { beanPaths.matches(it).paths.isEmpty() }
            if (paths.isNotEmpty() && invalid.isNotEmpty()) {
                throw UnknownFieldMaskException(invalid, beanPaths.values())
            }
        }

        val props = mutableSetOf<String>()
        for (property in properties) {
            val p = property as KProperty1<Any?, *>
            val m = context.matches(Segment(p.name))
            if (m.paths.isEmpty()) continue
            var value: Any? = p.get(instance)
            val methodNames = setOf(p.name, "get${p.name}", "get${p.name.capitalize()}")
            val annotations = p.javaField?.annotations?.map { it.annotationClass.simpleName }?.toSet() ?: setOf()
            val ignore = annotations.contains("JsonBackReference") || annotations.contains("JsonIgnore")
            var resolved = false
            resolverMethods.filter {
                methodNames.contains(it.key.name)
            }.maxByOrNull { it.value.second.size }?.let { (method, args) ->
                value = method.invoke(args.first, *args.second.toTypedArray())
                resolved = true
            }
            val nested = context.mask.values().any { it.startsWith(m.paths) && it.size > m.size }
            if (m.paths.last().value == "*" && nested && isPrimitive(value)) {
                continue
            }

            if (ignore && !resolved && !context.mask.contains(context.root.join(Segment(p.name)))) continue
            if (annotations.contains("JsonIgnore") && (!resolved)) {
                continue
            }

            if (value != null) {
                context.depth.push(m.paths.last().toString())
                val field = m.paths.last()
                addField(field, value!!, model, context, isPrimitive(value!!))
                props.add(m.paths.last().value)
                context.depth.pop()
            }
        }

        for ((method, args) in resolverMethods) {
            val path = Segment(method.name)
            val m = context.matches(path)
            if (props.contains(method.name) || m.paths.isEmpty()) continue
            val value = method.invoke(args.first, *args.second.toTypedArray())
            if (value != null) {
                context.depth.push(m.paths.last().toString())
                addField(m.paths.last(), value, model, context, isPrimitive(value))
                context.depth.pop()
            }
        }
    }

    private fun extractMethod(
        resolver: FieldResolver<*>?,
        instance: Any?,
        context: Context
    ): Map<Method, Pair<FieldResolver<*>, List<Any?>>> {
        val resolverMethods = mutableMapOf<Method, Pair<FieldResolver<*>, List<Any?>>>()
        resolver ?: return resolverMethods
        val methods = mapOf(
            listOf<Class<*>>(resolver.dataType(), Context::class.java) to listOf(instance, context),
            listOf<Class<*>>(resolver.dataType()) to listOf(instance)
        )
        for ((args, values) in methods) {
            for (name in resolver.javaClass.declaredMethods.map { it.name }) {
                try {
                    val method = resolver.javaClass.getMethod(name, *args.toTypedArray())
                    resolverMethods[method] = Pair(resolver, values)
                } catch (e: NoSuchMethodException) {
                }
            }
        }
        return resolverMethods
    }

    private fun addField(
        field: Segment,
        value: Any,
        model: MapModel<*>,
        context: Context,
        primitive: Boolean
    ) {
        if (primitive) {
            model.add(field.field, value)
            return
        }

        var data = value
        if (value is CompletableFuture<*>) {
            if (value.isDone) {
                data = value.get()
            } else {
                value.thenAccept {
                    addField(field, it, model, context, isPrimitive(it))
                }
                context.futures.add(value)
                return
            }
        }

        if (isPrimitive(data)) {
            model.add(field.field, data)
            return
        }

        val ctx = context.withRoot(Segment(field.value))

        when (data) {
            is Iterable<*> -> {
                visitList(data, model.createList(field.field), ctx)
            }
            is Map<*, *> -> {
                visitMap(data, model.createMap(field.field), ctx)
            }
            else -> {
                visitPojo(data, model.createMap(field.field), ctx)
            }
        }
    }
}

data class PathOptions(
    /**
     * Property indicating whether we should include private methods as part of the
     * bean properties
     */
    val includePrivate: Boolean = false
)

object BeanPaths {
    data class Context(
        val cache: MutableMap<Pair<PathOptions, Class<*>>, List<Path>> = mutableMapOf(),
        /**
         * Property indicating whether we should include private methods as part of the
         * bean properties
         */
        val options: PathOptions = PathOptions()
    )

    private var defaultContext = Context()

    fun paths(klass: KClass<*>, ctx: Context = defaultContext.copy()) = paths(klass.java, ctx)

    fun paths(klass: Class<*>, ctx: Context = defaultContext.copy()): List<Path> {
        val cacheKey = Pair(ctx.options, klass)
        if (defaultContext.cache.containsKey(cacheKey)) {
            return defaultContext.cache[cacheKey]!!
        }
        ctx.cache.putAll(defaultContext.cache)
        val fields = mutableListOf<Path>()
        visitPojo(klass, fields, Path(), ctx)
        return fields
    }

    private fun visitPojo(klass: Class<*>, fields: MutableList<Path>, root: Path, context: Context) {
        if (isPrimitive(klass) || klass == Any::class.java) {
            fields.add(root)
            return
        }
        val base = Path()
        val paths = mutableListOf<Path>()
        val cacheKey = Pair(context.options, klass)
        if (!context.cache.containsKey(cacheKey)) {
            context.cache[cacheKey] = paths
            val classes = mutableMapOf<Segment, Class<*>>()
            klass.kotlin.memberProperties.filter {
                val accessible = isFieldAccessible(it)
                if (context.options.includePrivate) {
                    if (!accessible) {
                        it.isAccessible = true
                    }
                    true
                } else {
                    accessible
                }
            }.sortedBy { it.name }.forEach { p ->
                val t = p.javaField?.type ?: p.returnType.jvmErasure.java
                when {
                    Iterable::class.java.isAssignableFrom(t) -> {
                        p.returnType.arguments.firstOrNull()?.type?.let {
                            classes[Segment(p.name)] = it.jvmErasure.java
                        }
                    }
                    Map::class.java.isAssignableFrom(t) -> {
                        paths.add(base.join(Segment(p.name), Segment("*")))
                    }
                    else -> {
                        paths.add(base.join(Segment(p.name)))
                    }
                }
            }
            classes.forEach { (k, v) -> visitPojo(v, paths, base.join(k), context) }
            context.cache[cacheKey] = paths.toList()
        }
        fields.addAll(context.cache[cacheKey]!!.map { root.join(it) })
    }
}
