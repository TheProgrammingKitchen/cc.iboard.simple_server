package cc.iboard

import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test

import cc.iboard.backend.Request

internal class RequestTest {

    @Test
    fun testRequestInitialization() {
        val request = Request("GET", "/somepath?param1=p1&param2=p2")
        assertEquals("GET", request.method())
        assertEquals("/somepath", request.path())
    }
}