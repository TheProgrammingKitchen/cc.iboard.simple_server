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

    fun method(): String = method
    fun path(): String = path
    fun parameters(): Parameters = parameters

    // IMPLEMENTATION

    private fun extractPath(queryString: String): String {
        return queryString
                .split("\\?".toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()
                .first()
    }

}
