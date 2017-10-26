package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

/**
 * Inspect-Endpoint responds with an inspection of the request.
 * It shows the query, extracted parameters, and body.
 */
public class Inspect extends Endpoint {
    @Override
    public Response respond(Request request) {
        return new Response(Response.HTTP_OK, renderResponse(request));
    }

    // IMPLEMENTATION

    private String renderResponse(Request request) {
        return Html.renderPage("Inspect Request", 
                ("Request: " + request.path())
                .concat(Html.p("Query Parameters:"))
                .concat(listParameters(request))
                );
    }

    private String listParameters(Request request) {
        StringBuilder output = new StringBuilder();
        request.parameters().map().forEach( (k,v) -> {
            output.append(
                    Html.pre(k + " = " + v)
                    );
        });
        return output.toString();
    }
}
