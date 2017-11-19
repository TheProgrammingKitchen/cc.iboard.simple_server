package cc.iboard.backend

class Response(private val status: Int, private val body: String) {

    fun body(): String = body

    fun status(): Int = status

    companion object {
        val HTTP_OK = 200
        val HTTP_NOT_FOUND = 404
        val HTTP_FORBIDDEN = 403
    }

}
