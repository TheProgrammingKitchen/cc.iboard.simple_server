package cc.iboard.backend;

/**
 * The `BackendInterface` is responsible to `start` and `stop` a backend.
 * The method `respond(requestString)` is responsible to return the response
 * using the `Resonder`.
 *
 * `start` and `stop` may be a NOOP (eg in TestBackend)
 *
 * @see Responder
 * @see TestBackend
 */
interface BackendInterface {
    void start();
    String handle(String requestString);
    void stop();
}
