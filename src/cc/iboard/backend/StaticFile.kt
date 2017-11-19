package cc.iboard.backend

import cc.iboard.backend.StaticFile.rejoinPathWithoutEndpoint
import java.io.BufferedReader
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.util.ArrayList

object StaticFile {
    val FILE_ENCODING = "UTF-8"
    val PATH_DELIMITER = "\\/"
    val NEWLINE = "\n"

    /**
     * Reads a static file from 'Request.path()'
     * Throws an IOException if file is not found or not readable.
     * @param request
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    fun readFile(request: Request): String {
        val filePath = extractFilePath(request)
        val path = FileSystems.getDefault().getPath(".", filePath)
        return fileContent(path)
    }

    // IMPLEMENTATION

    @Throws(IOException::class)
    private fun fileContent(path: Path): String {
        val reader = getReader(path)
        val content = readContent(reader)
        return content.toString()
    }

    @Throws(IOException::class)
    private fun readContent(reader: BufferedReader): StringBuilder {
        var line: String
        val content = StringBuilder()

        reader.lines().forEach( fun(line) {
            content.append(line + NEWLINE)
          }
        )
        return content
    }

    @Throws(IOException::class)
    private fun getReader(path: Path): BufferedReader {
        val charset = Charset.forName(FILE_ENCODING)
        return Files.newBufferedReader(path, charset)
    }

    private fun extractFilePath(request: Request): String {
        val parts = request.path().split(PATH_DELIMITER.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return rejoinPathWithoutEndpoint(parts)
    }

    private fun rejoinPathWithoutEndpoint(parts: Array<String>): String {
        val list = makeStringList(parts)
        return joinList(list, "/")
    }

    private fun makeStringList(parts: Array<String>): List<String> {
        val list = ArrayList<String>()
        for (i in parts.indices) {
            if (parts[i] != "")
                list.add(parts[i])
        }
        return list
    }

    private fun joinList(list: List<String>, glue: String): String {
        val buffer = StringBuilder()
        list.forEach { part -> buffer.append(glue).append(part) }
        return buffer.toString()
    }
}
