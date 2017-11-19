package cc.iboard

import cc.iboard.backend.Response
import cc.iboard.backend.TestBackend

object TestHelper {
    fun send(backend: TestBackend, path: String): Response {
        return backend.request("GET", path)
    }
}
