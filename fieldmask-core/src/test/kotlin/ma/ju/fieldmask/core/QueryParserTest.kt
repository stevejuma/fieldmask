package ma.ju.fieldmask.core

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class QueryParserTest {
    private val parser = FieldQueryParser()

    @Test
    fun `parses the requested fields`() {
        mapOf(
            "a" to "a",
            "/a" to "a",
            "a(b)" to "a/b",
            "a/b/c" to "a/b/c",
            "/a/b/c" to "a/b/c",
            "a/b/c(d)" to "a/b/c/d",
            "a/b/c(d,e)" to "a/b/c/d,a/b/c/e",
            "a(b(c,d))" to "a/b/c,a/b/d",
            "a/b(c/d)" to "a/b/c/d",
            """ "a(b(c))" """ to """a(b(c))""",
            """ 'a(b(c))' """ to """a(b(c))""",
            """ `a(b(c))` """ to """a(b(c))""",
            """"abc\""""" to """abc"""",
            "a,b/c(d,e),e(f/g)" to "a,b/c/d,b/c/e,e/f/g",
            "albums/songs(title,artist/name)" to "albums/songs/title,albums/songs/artist/name"
        ).forEach { (q, expected) ->
            var fields = listOf<Path>()
            assertDoesNotThrow(q) {
                fields = parser.parse(q)
            }
            assertEquals(expected, fields.joinToString(","), q)
        }
    }

    @Test
    fun `parses segment arguments`() {
        val mapper = ObjectMapper()
        mapOf(
            "empty" to "[]",
            "map[int: 0 float: 0.35 string: 'string' term: term true: true false:false]/to/path" to """[{"map":{"int":0,"float":0.35,"string":"string","term":"term","true":true,"false":false}}]""",
            "/path/to/list[array:[0 0.35 'string']]" to """[{"list":{"array":[0,0.35,"string"]}}]"""
        ).forEach { (q, expected) ->
            val fields = parser.parse(q)
            val args = mutableListOf<Any>()
            for (field in fields) {
                for (segment in field.paths) {
                    if (segment.arguments.isNotEmpty()) {
                        args.add(mapOf(segment.value to segment.arguments))
                    }
                }
            }
            assertEquals(expected, mapper.writeValueAsString(args), q)
        }
    }
}
