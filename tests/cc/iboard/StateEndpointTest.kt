package cc.iboard

import cc.iboard.backend.Response
import cc.iboard.backend.TestBackend
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StateEndpointTest {

    @Test
    fun testStateResponse() {
        val response = TestHelper.send(backend, "/state")
        assertTrue(response.body().contains("Running"))
    }

    companion object {

        private val backend = TestBackend()
    }

}
