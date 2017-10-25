package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class State extends Endpoint {
    @Override
    public Response respond(Request request) {
        return new Response(
          Response.HTTP_OK, 
          Html.html(
            Html.getHeader().
            concat(
              Html.body(
                Html.p("Running")
              )
            )
          )
        );
    }
}
