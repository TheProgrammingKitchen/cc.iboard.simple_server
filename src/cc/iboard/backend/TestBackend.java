package cc.iboard.backend;

/**
 * `start` and `stop` of `TestBackend` are NOOPs.
 * `handle(requestStr)` delegates the request to
 * the `Responder.getBody` method.
 *
 * @see Responder
 */
public class TestBackend implements BackendInterface {
    private Responder responder = new Responder();

    @Override
    public void start() {
      // NOOP
    }

    @Override
    public String handle(String requestString) {
        return responder.getBody(requestString);
    }

    @Override
    public void stop() {
      // NOOP
    }
}
