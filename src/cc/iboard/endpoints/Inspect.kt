package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response
import cc.iboard.html.Html

/**
 * Inspect-Endpoint responds with an inspection of the request.
 * It shows the query, extracted parameters, and body.
 */
class Inspect : Endpoint() {
    override fun respond(request: Request): Response {
        return Response(Response.HTTP_OK, renderResponse(request))
    }

    // IMPLEMENTATION

    private fun renderResponse(request: Request): String {
        return Html.renderPage("Inspect Request",
                "Request: " + request.path() + Html.p("Query Parameters:") + listParameters(request)
        )
    }

    private fun listParameters(request: Request): String {
        val output = StringBuilder()
        request.parameters().map().forEach { k, v ->
            output.append(
                    Html.pre(k + " = " + v)
            )
        }
        return output.toString()
    }
}
