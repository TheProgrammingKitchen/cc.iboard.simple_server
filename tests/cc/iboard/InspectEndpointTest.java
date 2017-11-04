package cc.iboard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;

class InspectEndpointTest {

    private static final TestBackend backend = new TestBackend();

    @Test
    void testInspectResponseWithOneParameter() {
        Response response = TestHelper.send(backend,"Inspect?p1=param1");
        assertTrue(response.body().contains("Request: Inspect"));
        assertTrue(response.body().contains("Parameters:"));	
        assertTrue(response.body().contains("p1 = param1"));	
        assertFalse(response.body().contains("p2 = param2"));
    }

    @Test
    void testInspectResponseWithTwoParameters() {
        Response response = TestHelper.send(backend,"Inspect?p1=param1&p2=param2");
        assertTrue(response.body().contains("Request: Inspect"));
        assertTrue(response.body().contains("Parameters:"));	
        assertTrue(response.body().contains("p1 = param1"));	
        assertTrue(response.body().contains("p2 = param2"));	
    }

    //TODO: Test /inspect/some/part?param=1 should see some = part

}
