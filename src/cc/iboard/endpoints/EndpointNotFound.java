package cc.iboard.endpoints;

public class EndpointNotFound extends Exception {

    public EndpointNotFound(String endpoint, Exception e) {
      super("Endpoint not found: " + endpoint +
            "Error: " + e.getMessage());
    }

    private static final long serialVersionUID = 1L;
}
