package cc.iboard;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticFileTest {

    private static final TestBackend backend = new TestBackend();

    @Test
    void testStaticFile() {
        String expected = "For now, this file is for testing purposes only.";
        Response response = TestHelper.send(backend,"/assets/README.md");
        String current = response.body();
        assertTrue( current.contains(expected));
    }

    @Test
    void testNotExistingFile() {
        String expectedBody = "File '/assets/not_existing_file' doesn't exist or is not readable.";
        Response response = TestHelper.send(backend,"/assets/not_existing_file");
        assertEquals(Response.HTTP_NOT_FOUND, response.status());
        assertEquals(response.body(), expectedBody );
    }

}
