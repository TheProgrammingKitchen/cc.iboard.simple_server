package cc.iboard.backend;

import cc.iboard.endpoints.Endpoint;
import cc.iboard.endpoints.EndpointFactory;
import cc.iboard.endpoints.Index;

/**
 *  <p>
 *      The `Responder` tries to create a `Endpoint` from the path given
 *  as a parameter.
 *  </p>
 *  <p>
 *      The default endpoints is `Index` for "/" and "".
 *  If a path is given, this path is passed to the `EndpointFactory` which
 *  returns a `Endpoint` for the given path.
 *  </p>
 *  <p>
 *      Finally, when a `Endpoint` was found, the `Responder` calls `respond()`
 *  on this `Endpoint` to return the calculated "body".
 *  </p>
 *
 *  Example:
 *  <pre>
 *     private Responder responder = new Responder();
 *     ....
 *     return responder.getBody(path);
 *  </pre>
 */
public class Responder {

    /**
     * @param path    the first part of the path, eg `/index` is used to find the proper `Endpoint`.
     * @return String the body returned from the `Endpoint.respond()` function.
     * @see Endpoint
     * @see Responder
     */
    public String getBody(String path) {
        Endpoint endpoint = null;

        if (path.equals("/") || path.equals(""))
            endpoint = new Index();
        else try {
            endpoint = EndpointFactory.INSTANCE.createHandler(path);
        } catch (Exception e) {
            return "404 No endpoints found for: " + path;
        }

        return endpoint.respond();
    }

}
