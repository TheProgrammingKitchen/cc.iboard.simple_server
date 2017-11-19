package cc.iboard.backend

import cc.iboard.endpoints.Endpoint
import cc.iboard.endpoints.EndpointFactory
import cc.iboard.endpoints.EndpointNotFound
import cc.iboard.endpoints.Index
import cc.iboard.endpoints.NotFound

/**
 *
 *
 * The `Requester` creates an `Endpoint` from the request-string given
 * as a parameter.
 *
 *
 * The default Endpoint is `Index` for "/" and "".
 * If a request-string is given, this path is passed to the `EndpointFactory` which
 * returns an `Endpoint` for the given string. If no Endpoint defined for the given
 * request-string a new `NotFound` endpoint will be created.
 *
 *
 * Finally, the `Requestor` calls `request()` on this `Endpoint` and returns the `Response`
 *
 *
 * Example:
 * <pre>`private Requester requester = new Requester();
 * //....
 * Response response = requester.request("GET", "/resource?param=value");
 * println(response.body());   // Some text
 * println(response.status()); // 200, 404, ...
`</pre> *
 *
 * @see Endpoint
 *
 * @see Response
 *
 * @see NotFound
 */
internal class Requester {

    /**
     * @param path  the first part of the path, eg `/index` is used to find the proper `Endpoint`.
     * @return String the body returned from the `Endpoint.respond()` function.
     *
     * @see Endpoint
     *
     * @see Requester
     */
    fun request(method: String, path: String): Response {
        val endpoint = determineEndpoint(path)
        val request = Request(method, path)

        return endpoint.respond(request)
    }


    // IMPLEMENTATION

    private fun determineEndpoint(path: String): Endpoint {
        var endpoint: Endpoint
        if (isRootPath(path))
            endpoint = Index()
        else
            try {
                endpoint = EndpointFactory.INSTANCE.createEndpoint(path)
            } catch (e: EndpointNotFound) {
                endpoint = NotFound()
            }

        return endpoint
    }

    private fun isRootPath(path: String)
            : Boolean = path == "/" || path == ""

}
