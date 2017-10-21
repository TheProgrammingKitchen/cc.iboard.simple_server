package cc.iboard.backend;

/**
 * `start` and `stop` of `TestBackend` are NOOPs.
 * `handle(requestStr)` delegates the request to
 * the `Responder.getBody` method.
 *
 * @see Responder
 */
public class TestBackend implements BackendInterface {
    private final Responder responder = new Responder();

    @Override
    public void start() {
      // NOOP
    }

    @Override
    public Response handle(String method, String requestString) {
        return responder.respondTo(method, requestString);
    }

    @Override
    public void stop() {
      // NOOP
    }
}
