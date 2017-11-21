package cc.iboard.backend

object BackendHelper {

    fun upcaseFirstChar(path: String): String {
        val req = stripFirstSlash(path)
        val first = req.first()
        return first.toUpperCase() + req.substring(1)
    }

    fun stripFirstSlash(string: String): String {
        return if (string.first() == '/')
          string.substring(1)
        else
          string
    }
}
