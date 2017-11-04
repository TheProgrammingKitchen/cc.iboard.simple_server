package cc.iboard;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateEndpointTest {

    private static final TestBackend backend = new TestBackend();

    @Test
    void testStateResponse() {
        Response response = TestHelper.send(backend,"/state");
        assertTrue(response.body().contains("Running"));
    }

}
