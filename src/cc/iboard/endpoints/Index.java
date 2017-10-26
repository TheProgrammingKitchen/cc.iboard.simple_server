package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class Index extends Endpoint {
    private static final String DEFAULT_TITLE = null;

	@Override
    public Response respond(Request request) {
		return new Response(Response.HTTP_OK, renderResponse());
    }

    private String renderResponse() {
      return Html.renderPage(DEFAULT_TITLE, Html.lorem());
    }
}
