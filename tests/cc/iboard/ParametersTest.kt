package cc.iboard

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test

internal class ParametersTest {

    @Test
    fun testBasicParsing() {
        val expectedKeys = arrayOf("param1", "param2")
        val expectedValues = arrayOf("1", "2")
        val params = Parameters("/path/resource/id?param1=1&param2=2")
        assertArrayEquals(expectedKeys, params.keys())
        assertArrayEquals(expectedValues, params.values())

    }

    @Test
    fun testGetParamByKey() {
        val params = Parameters("/path/resource/id?param1=1&param2=2")
        assertEquals("1", params.key("param1"))
        assertEquals("2", params.key("param2"))
    }

    @Test
    fun testEmptyParams() {
        val params = Parameters("/path/resource/id?")
        assertEquals(0, params.keys().size)
    }

    @Test
    fun testNoParams() {
        val params = Parameters("/path/resource/id")
        assertEquals(0, params.keys().size)
    }

}
