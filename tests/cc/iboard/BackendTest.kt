package cc.iboard

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

import org.junit.jupiter.api.Test

import cc.iboard.backend.Response
import cc.iboard.backend.TestBackend
import cc.iboard.html.Html

internal class BackendTest {

    @Test
    fun testServerResponseToUnknownCommand() {
        val response = TestHelper.send(backend, "unknown command")
        assertTrue(response.body().contains("404 - Page Not Found"))
        assertEquals(404, response.status())
    }

    @Test
    fun testTestBackendImplementation() {
        // Because Java is stupid and we have to overwrite
        // abstract methods even if not needed.
        backend.start()
        backend.stop()
        assertTrue(true)
    }

    @Test
    fun testNotExistingEndpointsRespondWith404() {
        val response = TestHelper.send(backend, "/notExistingEndpoint")
        assertEquals(Response.HTTP_NOT_FOUND, response.status())
    }

    companion object {

        private val backend = TestBackend()
    }

}
