package cc.iboard.backend;

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
