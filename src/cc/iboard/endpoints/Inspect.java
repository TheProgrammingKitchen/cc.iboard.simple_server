package cc.iboard.endpoints;

import java.util.List;
import java.util.Map.Entry;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.html.Html;

public class Inspect extends Endpoint {
    @Override
    public Response respond(Request request) {
		return new Response(Response.HTTP_OK, Html.getHeader() + buildHTMLBody(request));
    }

    private String buildHTMLBody(Request request) {
        return Html.html(
          Html.body(
              Html.h1( Html.title() )
              .concat(Html.p("Request: " + request.path()))
              .concat(Html.p("Parameters:"))
              .concat(listParameters(request))
          )
        );
    }
    
    private String listParameters(Request request) {
      List<Entry<String, String>> params = request.parameters();
      StringBuilder output = new StringBuilder();
      params.forEach( (Entry<String, String> param) -> {
        output.append(
          Html.p(param.getKey() + " = " + param.getValue().toString())
        );
      });
      return output.toString();
    }
}
