package ma.ju.fieldmask.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

data class DemoBean(
    val id: Long,
    val nested: Set<NestedDemoBean>,
    val intList: List<Int>,
    val strList: List<String>,
    val objList: List<Any?>,
    val and: MutableList<DemoBean> = mutableListOf(),
)

data class NestedDemoBean(
    val uuid: Long = 0
)

class BeanPathTest {
    @Test
    fun `fetches the correct paths from a bean`() {
        val paths = BeanPaths.paths(DemoBean::class.java)
        assertEquals("id,and/id,intList,nested/uuid,objList,strList", "${paths.joinToString(",")}")
    }
}
