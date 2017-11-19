package cc.iboard.backend

/**
 * `start` and `stop` of `TestBackend` are NOOPs.
 * `handle(requestStr)` delegates the request to
 * the `Responder.getBody` method.
 *
 * @see Requester
 */
class TestBackend : BackendInterface {
    private val responder = Requester()

    override fun start() { /* NOOP */
    }

    override fun stop() { /* NOOP */
    }

    override fun request(method: String, requestString: String): Response {
        return responder.request(method, requestString)
    }

}
