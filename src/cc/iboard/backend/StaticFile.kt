package cc.iboard.backend

import java.io.IOException
import java.io.InputStream
import java.nio.file.FileSystems
import java.nio.file.Path

object StaticFile {
    /**
     * Reads a static file from 'Request.path()'
     * Throws an IOException if file is not found or not readable.
     * @param request
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun readFile(request: Request): String {
        val filePath = request.path()
        val path = FileSystems.getDefault().getPath(".", filePath)
        return fileContent(path)
    }

    // IMPLEMENTATION

    @Throws(IOException::class)
    private fun fileContent(path: Path): String {
        val inputStream: InputStream = path.toFile().inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }


}
