// TODO: Review this untested code

/*
   The HTTP-Backend is responsible to extract the request from
   the http-input, create a Endpoint and execute it.

   We trust in com.sun.net.httpserver and do not test this simple
   responsibility inside this class. Just because it's to hard
   and all the methods we call from this application are tested anyways.

 */
package cc.iboard.backend;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;


// IGNORE COVERAGE IN THIS FILE - See description above


/**
 * The `HttpBackend` starts a simple http-server and
 * a handler for path /*
 * the path is passed to `Responder.handle(path)`
 *
 * The server will not be stopped and blocks until
 * Ctrl-C is pressed by the user or the process is killed.
 */
public class HttpBackend implements BackendInterface {

    private int port = 8000 ;
    private static HttpServer server;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static final Responder responder = new Responder();

    public HttpBackend(int port) {
        this.port = port;
        createHttpServer();
    }


    // PUBLIC API

    @Override
    public void start() {
        startHttpServer();
    }

    @Override
    public String handle(String requestString) {
        return directCallWarning(requestString);
    }

    @Override
    public void stop() {
        server.stop(0);
    }



    // IMPLEMENTATION

    private static void sendResponse(HttpExchange t, String response) throws IOException {
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String buildRequest(HttpExchange t) {
        String path = extractPath(t);
        if ( path.equals("/") )
            return "Index";
        else
            return upcaseFirstChar(path);
    }

    private static String upcaseFirstChar(String path) {
        String req = path.substring(2);
        String first = path.substring(1,2);
        return first.toUpperCase() + req;
    }

    private static String extractPath(HttpExchange t) {
        URI uri = t.getRequestURI();
        return uri.getPath();
    }

    private void createHttpServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.out.println("Server can't be started. Error: " + e.getMessage());
        }
    }

    private void startHttpServer() {
        server.createContext("/", new RootHandler());
        server.start();
    }

    private String directCallWarning(String requestString) {
        String msg = "Backend Handle was called directly in HttpBackend with: " +
                requestString;
        logger.log(Level.WARNING,msg);
        return msg;
    }

    static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = serve(buildRequest(t));
            sendResponse(t, response);
        }

        String serve(String msg) {
            return responder.getBody(msg);
        }

    }

}
