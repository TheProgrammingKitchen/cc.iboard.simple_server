package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response
import cc.iboard.html.Html

class Index : Endpoint() {

    override fun respond(request: Request)
            : Response = Response(Response.HTTP_OK, renderResponse())

    private fun renderResponse()
            : String = Html.renderPage(DEFAULT_TITLE, Html.lorem())

    companion object {
        private val DEFAULT_TITLE: String? = null
    }
}
