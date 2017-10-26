package cc.iboard;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParametersTest {

	@Test
	void testBasicParsing() {
	    String[] expectedKeys = {"param1", "param2"};
	    String[] expectedValues = {"1","2"};
	    Parameters params = new Parameters("/path/resource/id?param1=1&param2=2");
	    assertArrayEquals(expectedKeys, params.keys());
	    assertArrayEquals(expectedValues, params.values());

	}
	
	@Test
	void testGetParamByKey() {
	    Parameters params = new Parameters("/path/resource/id?param1=1&param2=2");
        assertEquals("1", params.key("param1"));
        assertEquals("2", params.key("param2"));
	}
	
	@Test
	void testEmptyParams() {
	    Parameters params = new Parameters("/path/resource/id?");
	    assertEquals(0, params.keys().length);
	}

	@Test
	void testNoParams() {
	    Parameters params = new Parameters("/path/resource/id");
	    assertEquals(0, params.keys().length);
	}

}
