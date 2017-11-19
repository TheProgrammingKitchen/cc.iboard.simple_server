package cc.iboard

import cc.iboard.backend.Response
import cc.iboard.backend.TestBackend
import cc.iboard.html.Html
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class IndexEnpointTest {

    @Test
    fun testIndexPageResponse() {
        val response = TestHelper.send(backend, "/index")
        assertTrue(response.body().contains("<html>"))
        assertTrue(response.body().contains(Html.title()))
    }

    @Test
    fun testDefaultPageResponse() {
        val response = TestHelper.send(backend, "/")
        assertTrue(response.body().contains("<html>"))
        assertTrue(response.body().contains(Html.title()))
    }

    companion object {

        private val backend = TestBackend()
    }

}
