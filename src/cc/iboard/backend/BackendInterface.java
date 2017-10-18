package cc.iboard.backend;

/**
 * The `BackendInterface` is responsible to `start` and `stop` the backend.
 * The method `respond(requestString)` is responsible to return the response
 * using the `Resonder`.
 *
 * @see Responder
 * @see TestBackend
 */
public interface BackendInterface {
    public void start();
    public String handle(String requestString);
    public void stop();
}
