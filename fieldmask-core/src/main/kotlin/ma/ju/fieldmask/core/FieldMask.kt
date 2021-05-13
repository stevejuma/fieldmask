package ma.ju.fieldmask.core

/**
 * FieldMask holds a collection of [Path].
 *
 * @param fields The list of paths to add to this object
 * @param separator The separator to use for the path segments
 */
open class FieldMask(fields: List<Path> = listOf(), private val separator: String = "/") {
    private val paths = mutableMapOf<String, Path>()

    init {
        fields.forEach { path ->
            repeat(path.paths.size) {
                val keys = path.paths.subList(0, it + 1)
                val key = keys.joinToString(separator)
                if (key.isNotBlank()) {
                    paths[key] = Path(keys.toMutableList(), separator)
                }
            }
        }
    }

    fun contains(key: String) = paths.containsKey(key)

    fun contains(path: Path) = paths.containsKey(path.toString())
    /**
     * Checks if this collection is empty
     */
    fun empty() = paths.isEmpty()

    /**
     * Checks if the specified [path] matches the defined [Path] in this collection
     * @param path The path to check for a match
     */
    fun matches(path: String) = matches(path.split(separator))

    /**
     * Checks if the specified [path] matches the defined [Path] in this collection
     * @param path The path to check for a match
     * @return boolean if there is a match
     */
    fun matches(path: Path) = matches(path.paths)

    /**
     * Checks if the specified strings in [parts] match the defined [Path] in this collection
     * @param parts The list of parts to check for a match
     * @return boolean if there is a match
     */
    @JvmName("matchesString")
    fun matches(parts: List<String>) = matches(Segment.from(parts))

    /**
     * Checks if the specified list of [parts] matches the specified [Path] in this collection
     * @param parts The list of segments to check for a match
     * @return boolean if there is a match
     */
    fun matches(parts: List<Segment>): Path {
        if (parts.isEmpty()) return Path()
        if (paths.containsKey("*") || parts == listOf(Segment("*"))) return Path(parts.toMutableList())
        if (paths.containsKey(parts.joinToString(separator))) return paths[parts.joinToString(separator)]!!.copy()
        var matches = listOf<List<Segment>>()
        var matchedKey = Path()
        for (i in 1..parts.size) {
            val keys = parts.subList(0, i)
            val key = keys.joinToString(separator)
            if (keys.isNotEmpty() && key.isNotBlank()) {
                var values =
                    paths.filter { k -> k.value.startsWith(keys) && k.value.size > keys.size }.values.map { it.paths }
                if (matches.isEmpty() && values.isNotEmpty()) matches = values
                matches = matches.filter { m -> m.size >= keys.size }

                if (!paths.containsKey(key)) {
                    val sb = mutableListOf<Segment>()
                    var matched = matches.any { m ->
                        sb.clear()
                        var valid = true
                        for (idx in keys.indices) {
                            if (m[idx] == keys[idx]) {
                                sb.add(m[idx])
                                continue
                            }
                            if (m[idx].value == "*" || keys[idx].value == "*") {
                                val field = if (m[idx].value == "*") keys[idx] else m[idx]
                                sb.add(Segment("*", field.value))
                                continue
                            }
                            valid = false
                            break
                        }
                        valid
                    }
                    if (!matched) {
                        paths.values.firstOrNull { it.startsWith(matchedKey.paths) && it.size >= parts.size }?.let {
                            return Path()
                        }
                        if (paths.values.any { it.startsWith(matchedKey.paths) }) {
                            return Path(parts.toMutableList())
                        }
                        return Path()
                    } else {
                        matchedKey = Path(sb)
                        continue
                    }
                }
                matchedKey = paths[key]!!
            }
        }
        return matchedKey
    }

    /**
     * Removes the [prefix] from the paths defined in this collections
     * @return New [FieldMask] with paths that only contain the [prefix] with
     * the prefix removed
     */
    fun trimPrefix(prefix: Path): FieldMask {
        val fields = paths.values.filter { it.startsWith(prefix.paths) }.map { it.trimPrefix(prefix.paths) }
        return FieldMask(fields, separator)
    }

    /**
     * Fetches only the paths that match the specified prefix
     * @param prefix The prefix to check for patches
     * @param trim Boolean indicating whether the prefix matched should be trimmed
     * @return New [FieldMask] with the matching prefixes
     */
    fun withPrefix(prefix: Path, trim: Boolean = false): FieldMask {
        if (prefix.paths.isEmpty()) {
            return copy()
        }
        val fields = paths.values.filter { it.startsWith(prefix.paths) }.map {
            if (trim) it.trimPrefix(prefix.paths) else it
        }
        return FieldMask(fields, separator)
    }

    /**
     * Fetches all the paths in the collection
     */
    fun values() = paths.values.toList()

    /**
     * Creates a copy of this collection with the specified properties
     */
    fun copy() = FieldMask(paths.values.toMutableList(), separator)

    override fun toString(): String = "separator: $separator paths: ${paths.values}"

    companion object {
        /**
         * Creates a [FieldMask] for the specified [query] with the given [separator]
         * @return The [FieldMask] for the specified query
         */
        fun matcherFor(query: String, separator: String = "/"): FieldMask {
            if (query.isBlank()) return FieldMask()
            val parser = FieldQueryParser()
            return FieldMask(parser.parse(query), separator)
        }
    }
}
