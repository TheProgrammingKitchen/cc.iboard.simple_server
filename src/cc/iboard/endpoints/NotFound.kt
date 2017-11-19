package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response
import cc.iboard.html.Html

class NotFound : Endpoint() {

    override fun respond(request: Request): Response {
        return Response(Response.HTTP_NOT_FOUND, renderResponse(request))
    }

    private fun renderResponse(request: Request): String {
        return Html.renderPage("404 - Page Not Found", notFound(request))
    }

    private fun notFound(request: Request): String {
        return "There is no page at " + request.path() + ".<br/> " +
                Html.a("Goto the Homepage", "/")
    }
}
