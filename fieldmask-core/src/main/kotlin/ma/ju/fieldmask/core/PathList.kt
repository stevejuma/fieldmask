package ma.ju.fieldmask.core

open class PathList(fields: List<Path> = listOf(), private val separator: String = "/") {
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

    fun empty() = paths.isEmpty()

    fun matches(path: String) = matches(path.split(separator))

    fun matches(path: Path) = matches(path.paths)

    @JvmName("matchesString")
    fun matches(parts: List<String>) = matches(Segment.from(parts))

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
                    paths.filter { k -> k.key.startsWith(key) && k.value.size > keys.size }.values.map { it.paths }
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
                                sb.add(m[idx])
                                continue
                            }
                            valid = false
                            break
                        }
                        valid
                    }

                    if (!matched) {
                        val m = matchedKey.paths.joinToString(separator)
                        if (m.isNotBlank() && !paths.keys.any { it.startsWith("$m$separator") }) {
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

    fun trimPrefix(prefix: Path): PathList {
        val fields = paths.values.filter { it.startsWith(prefix.paths) }.map { it.trimPrefix(prefix.paths) }
        return PathList(fields, separator)
    }

    fun withPrefix(prefix: Path, trim: Boolean = false): PathList {
        if (prefix.paths.isEmpty()) {
            return copy()
        }
        val fields = paths.values.filter { it.startsWith(prefix.paths) }.map {
            if (trim) it.trimPrefix(prefix.paths) else it
        }
        return PathList(fields, separator)
    }

    fun values() = paths.values.toList()

    fun copy() = PathList(paths.values.toMutableList(), separator)

    override fun toString(): String = "separator: $separator paths: ${paths.values}"

    companion object {
        fun matcherFor(query: String, separator: String = "/"): PathList {
            if (query.isBlank()) return PathList()
            val parser = FieldQueryParser()
            return PathList(parser.parse(query), separator)
        }
    }
}
