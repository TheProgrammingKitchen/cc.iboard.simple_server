package cc.iboard

import cc.iboard.backend.BackendHelper
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BackendHelperTest {

    @Test
    fun testServerResponseToUnknownCommand() {
        val given = "all small"
        val expected = "All small"

        assertEquals(expected, BackendHelper.upcaseFirstChar(given))
    }

    @Test
    fun testRemoveFirstSlash() {
        val givenWithSlash = "/path/subpath"
        val givenWithoutSlash = "path/subpath"
        val expected = "path/subpath"

        assertEquals(expected, BackendHelper.stripFirstSlash(givenWithSlash))
        assertEquals(expected, BackendHelper.stripFirstSlash(givenWithoutSlash))
    }

}

