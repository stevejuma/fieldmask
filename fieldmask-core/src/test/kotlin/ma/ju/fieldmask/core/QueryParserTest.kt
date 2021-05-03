package ma.ju.fieldmask.core

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
}
