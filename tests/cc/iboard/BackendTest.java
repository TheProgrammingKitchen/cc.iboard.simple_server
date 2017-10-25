package cc.iboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;
import cc.iboard.html.Html;

class BackendTest {

    private static final TestBackend backend = new TestBackend();

    @Test
    void testServerResponseToUnknownCommand() {
    	    Response response = send("unknown command");
        assertTrue(response.body().contains("404 - Page Not Found"));
        assertEquals(404, response.status());
    }

    @Test
    void testStateResponse() {
        Response response = send("State");
        assertTrue(response.body().contains("Running"));
    }
    @Test
    void testIndexPageResponse() {
        Response response = send("Index");
        assertTrue(response.body().contains("<html>"));
        assertTrue(response.body().contains(Html.title()));
    }

    @Test
    void testDefaultPageResponse() {
        Response response = send("/");
        assertTrue(response.body().contains("<html>"));
        assertTrue(response.body().contains(Html.title()));
    }

    @Test
    void testTestBackendImplementation() {
        // Because Java is stupid and we have to overwrite
        // abstract methods even if not needed.
        backend.start();
        backend.stop();
        assertTrue(true);
    }

    private Response send(String path) {
        return backend.handle("GET", path);
    }
}
