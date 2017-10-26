package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class NotFound extends Endpoint {

    @Override
    public Response respond(Request request) {
        return new Response(Response.HTTP_NOT_FOUND, renderResponse(request));
    }

    private String renderResponse(Request request) {
        return Html.renderPage("404 - Page Not Found", notFound(request));
    }

    private String notFound(Request request) {
        return ("There is no page at " + request.path() + ".<br/> " +
                Html.a("Goto the Homepage", "/")
                );
    }
}
