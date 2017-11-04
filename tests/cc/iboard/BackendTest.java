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
        Response response = TestHelper.send(backend,"unknown command");
        assertTrue(response.body().contains("404 - Page Not Found"));
        assertEquals(404, response.status());
    }

    @Test
    void testStateResponse() {
        Response response = TestHelper.send(backend,"/state");
        assertTrue(response.body().contains("Running"));
    }
    @Test
    void testIndexPageResponse() {
        Response response = TestHelper.send(backend,"/index");
        assertTrue(response.body().contains("<html>"));
        assertTrue(response.body().contains(Html.title()));
    }

    @Test
    void testDefaultPageResponse() {
        Response response = TestHelper.send(backend,"/");
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

}
