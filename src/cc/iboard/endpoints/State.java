package cc.iboard.endpoints;

import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class State extends Endpoint {
    @Override
    public Response respond() {
        return new Response(
        		Response.HTTP_OK, 
        		Html.getHeader()
                .concat("Running")
        );
    }
}
