package cc.iboard;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;
import cc.iboard.html.Html;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexEnpointTest {

    private static final TestBackend backend = new TestBackend();
    
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

}
