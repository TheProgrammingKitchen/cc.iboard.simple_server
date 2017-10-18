package cc.iboard;

import cc.iboard.backend.TestBackend;
import cc.iboard.html.Html;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BackendTest {

    private static TestBackend backend = new TestBackend();

    @Test
    void testServerResponseToUnknownCommand() {
        assertEquals("404 No endpoints found for: Unknown Command", send("Unknown Command"));
    }

    @Test
    void testStateResponse() {
        String response = send("State");
        assertTrue(response.contains("Running"));
    }
    @Test
    void testIndexPageResponse() {
        String response = send("Index");
        assertTrue(response.contains("<html>"));
        assertTrue(response.contains(Html.title()));
    }

    @Test
    void testDefaultPageResponse() {
        String response = send("/");
        assertTrue(response.contains("<html>"));
        assertTrue(response.contains(Html.title()));
    }

    private String send(String msg) {
        return backend.handle(msg);
    }
}
