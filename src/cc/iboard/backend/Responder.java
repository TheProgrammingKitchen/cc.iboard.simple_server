package cc.iboard.backend;

import cc.iboard.endpoints.Endpoint;
import cc.iboard.endpoints.EndpointFactory;
import cc.iboard.endpoints.Index;
import cc.iboard.html.Html;

/**
 *  <p>
 *      The `Responder` tries to create a `Endpoint` from the path given
 *  as a parameter.
 *  <p>
 *      The default endpoints is `Index` for "/" and "".
 *  If a path is given, this path is passed to the `EndpointFactory` which
 *  returns a `Endpoint` for the given path.
 *  <p>
 *      Finally, when a `Endpoint` was found, the `Responder` calls `respond()`
 *  on this `Endpoint` to return the calculated "body".
 *
 *  <p>
 *  Example:
 *  <pre>{@code
 *        private Responder responder = new Responder();
 *        //....
 *        return responder.getBody(path);
 *  }</pre>
 */
class Responder {

    /**
     * @param path    the first part of the path, eg `/index` is used to find the proper `Endpoint`.
     * @param msg 
     * @return String the body returned from the `Endpoint.respond()` function.
     * @see Endpoint
     * @see Responder
     */
    public Response respondTo(String _method, String path) {
        Endpoint endpoint;

        if (path.equals("/") || path.equals(""))
            endpoint = new Index();
        else try {
            endpoint = EndpointFactory.INSTANCE.createHandler(path);
        } catch (Exception e) {
            return new Response(Response.HTTP_NOT_FOUND,render404(path));
        }

        return endpoint.respond();
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
