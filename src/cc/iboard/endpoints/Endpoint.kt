package cc.iboard.endpoints

import cc.iboard.backend.Request
import cc.iboard.backend.Response

/**
 * Derive from class `Endpoint` to handle your requests.
 * Example:
 *
 * If you want to have an endpoint for
 * `http://localhost/MyRequest`, then derive a class
 * `MyRequest` from `Endpoint` and implement the `respond` function.
 *
 * @see Index
 *
 * @see State
 */
abstract class Endpoint {
    abstract fun respond(request: Request): Response
}
