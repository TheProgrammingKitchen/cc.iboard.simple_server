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
    void testTestBackendImplementation() {
        // Because Java is stupid and we have to overwrite
        // abstract methods even if not needed.
        backend.start();
        backend.stop();
        assertTrue(true);
    }

    @Test
    void testNotExistingEndpointsRespondWith404() {
        Response response = TestHelper.send(backend,"/notExistingEndpoint");
        assertEquals(Response.HTTP_NOT_FOUND, response.status());
    }

}
