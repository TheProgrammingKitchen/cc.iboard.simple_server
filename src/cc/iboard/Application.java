package cc.iboard;

import cc.iboard.backend.HttpBackend;

public class Application {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        HttpBackend backend = new HttpBackend(PORT);
        try {
            System.out.println("Starting server at port " + PORT);
            backend.start();
        } catch (NullPointerException e) {
            System.out.println("Can't start new server.");
        }
        System.out.println("You can open http://localhost:" + PORT);
    }


}

