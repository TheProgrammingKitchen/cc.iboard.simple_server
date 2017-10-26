package cc.iboard;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;

class EchoEndpointTest {

    private static final TestBackend backend = new TestBackend();
    
	@Test
	void testEchoResponseWithOneParameter() {
	  Response response = send("Inspect?p1=param1");
	  System.out.println( response.body() );
      assertTrue(response.body().contains("Request: Inspect"));	
      assertTrue(response.body().contains("Parameters:"));	
      assertTrue(response.body().contains("p1 = param1"));	
      assertFalse(response.body().contains("p2 = param2"));	
	}
	
	@Test
	void testEchoResponseWithTwoParameters() {
	  Response response = send("Inspect?p1=param1&p2=param2");
	  System.out.println( response.body() );
      assertTrue(response.body().contains("Request: Inspect"));	
      assertTrue(response.body().contains("Parameters:"));	
      assertTrue(response.body().contains("p1 = param1"));	
      assertTrue(response.body().contains("p2 = param2"));	
	}
	
	
	
	
    private Response send(String path) {
        return backend.request("GET", path);
    }
}
