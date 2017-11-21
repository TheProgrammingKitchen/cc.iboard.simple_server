package cc.iboard.backend

object BackendHelper {

    fun upcaseFirstChar(path: String): String {
        val req = stripFirstSlash(path)
        val first = path.substring(0, 1) // get the first character
        return first.toUpperCase() + req   // Upcase first character and append the rest
    }

    fun stripFirstSlash(string: String): String {
        return if (string.first() == '/')
          string.substring(1)     // ignore slash and first character
        else
          string
    }
}
