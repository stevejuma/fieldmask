package ma.ju.fieldmask.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FieldMatcherTest {
    @Test
    fun `matches the requested fields`() {
        mapOf(
            "a/b(c/x,d(*(f,g)))" to mapOf(
                "x" to false,
                "a/b/c/x/y/z" to true,
                "a/b/c" to true,
                "a/*/c" to true,
                "a/b/d/f" to true,
                "a/b/d/zz" to true,
                "a/b/d/zz/f" to true,
                "a/b/d/zz/y" to false
            )
        ).forEach { (q, expected) ->
            val matcher = FieldMask.matcherFor(q)
            expected.forEach { (k, v) ->
                assertEquals(v, matcher.matches(k).paths.isNotEmpty(), "$q MATCHES $k => $v")
            }
        }
    }
}
