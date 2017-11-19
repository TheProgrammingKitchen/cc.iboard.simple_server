package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response
import cc.iboard.html.Html

/**
 * Renders the current status of the running application.
 */
class State : Endpoint() {
    override fun respond(request: Request): Response {
        return Response(
                Response.HTTP_OK,
                Html.renderPage("Status", "Running")
        )
    }
}
