package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

/**
 * Renders the current status of the running application.
 */
public class State extends Endpoint {
    @Override
    public Response respond(Request request) {
        return new Response(
          Response.HTTP_OK, 
          Html.renderPage("Status","Running")
        );
    }
}
