package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response
import cc.iboard.backend.StaticFile

import java.io.IOException

class Assets : Endpoint() {

    override fun respond(request: Request): Response {
        try {
            return Response(Response.HTTP_OK, renderResponse(request))
        } catch (_e: IOException) {
            return Response(Response.HTTP_NOT_FOUND, renderError(request))
        }

    }

    // IMPLEMENTATION

    @Throws(IOException::class)
    private fun renderResponse(request: Request): String {
        return StaticFile.readFile(request)
    }

    private fun renderError(request: Request): String {
        return String.format(FILE_NOT_FOUND_FORMAT_STRING, request.path())
    }

    companion object {

        val FILE_NOT_FOUND_FORMAT_STRING = "File '%s' doesn't exist or is not readable."
    }

}

