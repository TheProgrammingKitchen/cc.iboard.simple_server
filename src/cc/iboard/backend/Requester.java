package cc.iboard.backend;

import cc.iboard.endpoints.Endpoint;
import cc.iboard.endpoints.EndpointFactory;
import cc.iboard.endpoints.Index;
import cc.iboard.html.Html;

/**
 *  <p>
 *      The `Requester` creates an `Endpoint` from the request-string given
 *  as a parameter.
 *  <p>
 *      The default Endpoint is `Index` for "/" and "".
 *  If a request-string is given, this path is passed to the `EndpointFactory` which
 *  returns an `Endpoint` for the given string.
 *  <p>
 *      Finally, when an `Endpoint` was found, the `Requestor` calls `request()`
 *  on this `Endpoint` and returns the `Response`
 *  <p>
 *      If no Endpoint defined for the given request-string, a 404-response is
 *  created.
 *
 *  <p>
 *  Example:
 *  <pre>{@code
 *        private Requester requester = new Requester();
 *        //....
 *        Response response = requester.request("GET", "/resource?param=value");
 *        println(response.body());   // Some text
 *        println(response.status()); // 200, 404, ...
 *  }</pre>
 *  
 *  @see Endpoint
 *  @see Response
 */
class Requester {

    /**
     * @param path    the first part of the path, eg `/index` is used to find the proper `Endpoint`.
     * @param msg 
     * @return String the body returned from the `Endpoint.respond()` function.
     * @see Endpoint
     * @see Requester
     */
    public Response request(String method, String path) {
        Endpoint endpoint;
        Request request = new Request(method,path);

        if (isRootPath(path))
            endpoint = new Index();
        else try {
            endpoint = EndpointFactory.INSTANCE.createEndpoint(path);
        } catch (Exception e) {
            return new Response(Response.HTTP_NOT_FOUND,render404(path));
        }

        return endpoint.respond(request);
    }

	private boolean isRootPath(String path) {
		return path.equals("/") || path.equals("");
	}

	private String render404(String path) {
		return Html.html(
			       Html.getHeader() +
			       Html.body(
			         Html.h1("404 - Page Not Found") +
			         Html.p("There is no page at " + path + ".<br/> " +
			           Html.a("Goto the Homepage", "/"))
			          )
			   );
	}

}
