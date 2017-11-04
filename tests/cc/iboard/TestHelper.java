package cc.iboard;

import cc.iboard.backend.Response;
import cc.iboard.backend.TestBackend;

public class TestHelper {
    public static Response send(TestBackend backend, String path) {
        return backend.request("GET", path);
    }
}
