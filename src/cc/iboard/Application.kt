package cc.iboard

import cc.iboard.backend.HttpBackend

/**
 * Start an HttpBackend from the command-line
 * @see HttpBackend
 * see `run/run.sh`
 */
internal object Application {

    private val PORT = 4000

    @JvmStatic
    fun main(args: Array<String>) {
        val backend = HttpBackend(PORT)
        try {
            println("Starting server at port " + PORT)
            backend.start()
        } catch (e: NullPointerException) {
            println("Can't start new server.")
        }

        println("You can open http://localhost:" + PORT)
    }


}

