package cc.iboard;

import cc.iboard.backend.HttpBackend;

public class Application {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        HttpBackend backend = new HttpBackend(PORT);
        System.out.println("Starting server at port " + PORT);
        System.out.println("You can open http://localhost:" + PORT);
        backend.start();
    }


}
