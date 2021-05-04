package ma.ju.fieldmask.core

import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import java.time.Instant
import java.util.Date
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.jvmErasure

fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
    return property.javaGetter?.modifiers?.let { !Modifier.isPrivate(it) } ?: false
}

fun isPrimitive(any: Any): Boolean {
    val clazz = if (any is Class<*>) any else any.javaClass
    return (
        clazz.isPrimitive || clazz.isEnum || Number::class.java.isAssignableFrom(clazz) ||
            String::class.java.isAssignableFrom(clazz) || Boolean::class.java.isAssignableFrom(clazz) ||
            Char::class.java.isAssignableFrom(clazz) || Date::class.java.isAssignableFrom(clazz)
        ) ||
        Instant::class.java.isAssignableFrom(clazz)
}

class UnknownFieldMaskException(
    val fields: List<Path>,
    expected: List<Path> = listOf(),
    message: String = "invalid field masks provided: $fields expected one of $expected"
) : RuntimeException(message)

interface FieldResolver<T> {
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

interface FieldModel<T> {
    fun model(): T
}

interface ListModel<T> : FieldModel<T> {
    fun add(value: Any?)
    fun createMap(): MapModel<*>
}

interface MapModel<T> : FieldModel<T> {
    fun add(key: String, value: Any?)
    fun createMap(name: String): MapModel<*>
    fun createList(name: String): ListModel<*>
}

class NullModel : FieldModel<Nothing?> {
    override fun model() = null
}

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
    val resolversMap = resolvers.map { it.dataType() to it }.toMap()
}

@Suppress("UNCHECKED_CAST")
object FieldMask {

    data class Context(
        val mask: PathList,
        val root: Path = Path(),
        val options: MaskOptions = MaskOptions(),
        val futures: MutableList<CompletableFuture<*>> = mutableListOf(),
        val properties: MutableMap<String, Any?> = mutableMapOf()
    ) {
        fun matches(path: Segment) = mask.matches(root.join(path))
        fun withRoot(path: Segment): Context {
            return copy(root = root.copy().join(path))
        }
    }

    fun <T : Any> apply(source: T, target: T, query: String, options: MaskOptions = MaskOptions()) {
        apply(source, target, Context(PathList.matcherFor(query), Path(), options))
    }

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

    private fun applyCached(source: Any, context: Context): Boolean {
        val cacheKey = "${source.javaClass.name}:${source.hashCode()}"
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

    fun mask(instance: Any?, query: String, options: MaskOptions = MaskOptions()): FieldModel<*> {
        return mask(
            instance, Context(PathList.matcherFor(query), Path(), options)
        )
    }

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

    fun mask(instance: Any?, model: FieldModel<*>, context: Context) {
        if (instance == null) return
        when (instance) {
            is Iterable<*> -> {
                visitList(instance, model as ListModel<*>, context)
            }
            is Map<*, *> -> {
                visitMap(instance, model as MapModel<*>, context)
            }
            else -> {
                visitPojo(instance, model as MapModel<*>, context)
            }
        }
    }

    private fun visitList(instance: Iterable<*>?, model: ListModel<*>, context: Context) {
        if (instance == null) return
        for (child in instance) {
            if (child == null || isPrimitive(child)) {
                model.add(child)
            } else if (child is Map<*, *>) {
                visitMap(child, model.createMap(), context)
            } else {
                visitPojo(child, model.createMap(), context)
            }
        }
    }

    private fun visitMap(instance: Map<*, *>?, model: MapModel<*>, context: Context) {
        if (instance == null) return
        for (entry in instance.entries) {
            val field = entry.key.toString()
            val m = context.matches(Segment(field))
            if (m.paths.isEmpty()) continue
            val value = entry.value
            if (value != null) {
                addField(m.paths.last(), value, model, context, isPrimitive(value))
            }
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
            var beanPaths = PathList(paths)
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
            resolverMethods.filter {
                methodNames.contains(it.key.name)
            }.maxByOrNull { it.value.second.size }?.let { (method, args) ->
                value = method.invoke(args.first, *args.second.toTypedArray())
            }

            if (value != null) {
                addField(m.paths.last(), value!!, model, context, isPrimitive(value!!))
                props.add(m.paths.last().value)
            }
        }

        for ((method, args) in resolverMethods) {
            val path = Segment(method.name)
            val m = context.matches(path)
            if (props.contains(method.name) || m.paths.isEmpty()) continue
            val value = method.invoke(args.first, *args.second.toTypedArray())
            if (value != null) {
                addField(m.paths.last(), value, model, context, isPrimitive(value))
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
        if (isPrimitive(klass)) return
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
