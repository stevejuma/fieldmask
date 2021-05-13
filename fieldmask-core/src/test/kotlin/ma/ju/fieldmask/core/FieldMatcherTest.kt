package ma.ju.fieldmask.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FieldMatcherTest {
    @Test
    fun `matches the requested fields`() {
        mapOf(
            "a/b(c/x,d(*(f,g)))" to mapOf(
                "a/b/d/zz/y" to false,
                "a/b/d/zz" to true,
                "x" to false,
                "a/b/c/x/y/z" to true,
                "a/b/c" to true,
                "a/*/c" to true,
                "a/b/d/f" to true,
                "a/b/d/zz/f" to true
            ),
            "name,albums/*/id" to mapOf(
                "name" to true,
                "albums/songs/id" to true,
                "albums/id" to true
            )
        ).forEach { (q, expected) ->
            val matcher = FieldMask.matcherFor(q)
            expected.forEach { (k, v) ->
                assertEquals(v, matcher.matches(k).paths.isNotEmpty(), "$q MATCHES $k => $v")
            }
        }
    }
}
