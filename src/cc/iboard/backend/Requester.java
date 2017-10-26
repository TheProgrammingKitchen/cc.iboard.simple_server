package cc.iboard.backend;

import cc.iboard.endpoints.Endpoint;
import cc.iboard.endpoints.EndpointFactory;
import cc.iboard.endpoints.EndpointNotFound;
import cc.iboard.endpoints.Index;
import cc.iboard.endpoints.NotFound;

/**
 *  <p>
 *      The `Requester` creates an `Endpoint` from the request-string given
 *  as a parameter.
 *  <p>
 *      The default Endpoint is `Index` for "/" and "".
 *  If a request-string is given, this path is passed to the `EndpointFactory` which
 *  returns an `Endpoint` for the given string. If no Endpoint defined for the given 
 *  request-string a new `NotFound` endpoint will be created.
 *  <p>
 *      Finally, the `Requestor` calls `request()` on this `Endpoint` and returns the `Response`
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
 *  @see NotFound
 */
class Requester {

    /**
     * @param path  the first part of the path, eg `/index` is used to find the proper `Endpoint`.
     * @return String the body returned from the `Endpoint.respond()` function.
     * 
     * @see Endpoint
     * @see Requester
     */
    public Response request(String method, String path) {
        Endpoint endpoint = determineEndpoint(path);
        Request request = new Request(method,path);

        return endpoint.respond(request);
    }


    // IMPLEMENTATION
    
    private Endpoint determineEndpoint(String path) {
        Endpoint endpoint;
        if (isRootPath(path))
            endpoint = new Index();
        else try {
            endpoint = EndpointFactory.INSTANCE.createEndpoint(path);
        } catch (EndpointNotFound e) {
            endpoint = new NotFound();
        }
        return endpoint;
    }

	private boolean isRootPath(String path) {
		return path.equals("/") || path.equals("");
	}

}
