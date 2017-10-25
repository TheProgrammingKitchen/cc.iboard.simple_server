package cc.iboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cc.iboard.backend.Request;

class RequestTest {

    @Test
    void testRequestInitialization() {
        Request request = new Request("GET", "/somepath?param1=p1&param2=p2");
        assertEquals("GET", request.method());
        assertEquals("/somepath", request.path());
    }
}