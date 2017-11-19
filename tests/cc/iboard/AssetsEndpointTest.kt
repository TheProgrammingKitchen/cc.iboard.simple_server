package cc.iboard

import cc.iboard.backend.Response
import cc.iboard.backend.TestBackend
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AssetsEndpointTest {

    @Test
    fun testStaticFile() {
        val expected = "For now, this file is for testing purposes only."
        val response = TestHelper.send(backend, "/assets/README.md")
        val current = response.body()
        assertTrue(current.contains(expected))
    }

    @Test
    fun testNotExistingFile() {
        val expectedBody = "File '/assets/not_existing_file' doesn't exist or is not readable."
        val response = TestHelper.send(backend, "/assets/not_existing_file")
        assertEquals(Response.HTTP_NOT_FOUND, response.status())
        assertEquals(response.body(), expectedBody)
    }

    companion object {

        private val backend = TestBackend()
    }

}
