package cc.iboard.backend;

/**
 * `start` and `stop` of `TestBackend` are NOOPs.
 * `handle(requestStr)` delegates the request to
 * the `Responder.getBody` method.
 *
 * @see Requester
 */
public class TestBackend implements BackendInterface {
    private final Requester responder = new Requester();

    @Override
    public void start() {
      // NOOP
    }

    @Override
    public Response request(String method, String requestString) {
        return responder.request(method, requestString);
    }

    @Override
    public void stop() {
      // NOOP
    }
}
