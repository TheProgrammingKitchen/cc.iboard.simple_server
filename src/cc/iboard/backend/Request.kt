package cc.iboard.backend

import cc.iboard.Parameters

class Request
// CONSTRUCTOR

(private val method: String, requestString: String) {
    private val parameters: Parameters
    private val path: String

    init {
        this.path = extractPath(requestString)
        this.parameters = Parameters(requestString)
    }

    // API

    fun method(): String {
        return method
    }

    fun path(): String {
        return path
    }

    fun parameters(): Parameters {
        return parameters
    }

    // IMPLEMENTATION

    private fun extractPath(queryString: String): String {
        val parts = queryString.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return parts[0]
    }

}
