package cc.iboard.endpoints;

import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class Index extends Endpoint {
    @Override
    public Response respond() {
		return new Response(Response.HTTP_OK, Html.getHeader() + buildHTMLBody());
    }

    private String buildHTMLBody() {
        return Html.html(
          Html.body(
              Html.h1( Html.title() )
              .concat(Html.p(Html.lorem()))
          )
        );
    }
}
