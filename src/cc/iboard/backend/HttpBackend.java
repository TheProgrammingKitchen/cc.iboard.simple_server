/*
   The HTTP-Backend is responsible to extract the request from
   the http-input, create a Endpoint and execute it.

   We trust in com.sun.net.httpserver and do not test this simple
   responsibility inside this class. Just because it's to hard
   and all the methods we call from this application are tested anyways.

 */
package cc.iboard.backend;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Tag;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


// IGNORE COVERAGE IN THIS FILE - See description above


/**
 * The `HttpBackend` starts a simple http-server and
 * a handler for path /*
 * the path is passed to `Responder.handle(path)`
 *
 * The server will not be stopped and blocks until
 * Ctrl-C is pressed by the user or the process is killed.
 */
 @Tag("DoNotTest")
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
    public Response handle(String _method, String requestString) {
        return directCallWarning(requestString);
    }

    @Override
    public void stop() {
        server.stop(0);
    }



    // IMPLEMENTATION

    private static void sendResponse(HttpExchange t, Response response) throws IOException {
        t.sendResponseHeaders(response.status(), response.body().length());
        OutputStream os = t.getResponseBody();
        os.write(response.body().getBytes());
        os.close();
    }

    private static String getHandlerName(HttpExchange t) {
        String path = extractPathWithQuery(t);
        if ( isRootPath(path) )
            return "Index";
        
		return upcaseFirstChar(path);
    }


	private static boolean isRootPath(String path) {
	    if (path == null)
	      return true;
		return path.equals("/");
	}
    
    private static String extractMethod(HttpExchange t) {
		return t.getRequestMethod().toUpperCase();
	}

	private static String upcaseFirstChar(String path) {
        String req = path.substring(2);     // ignore slash and first character
        String first = path.substring(1,2); // get the first character
        return first.toUpperCase() + req;   // Upcase first character and append the rest
    }

    private static String extractPathWithQuery(HttpExchange t) {
        URI uri = t.getRequestURI();
        return uri.getPath() + "?" + uri.getQuery();
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

    private Response directCallWarning(String requestString) {
        String msg = "Backend Handle was called directly in HttpBackend with: " +
                requestString;
        logger.log(Level.WARNING,msg);
        return new Response(Response.HTTP_FORBIDDEN, msg);
    }

    static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            Response response = serve(extractMethod(t), getHandlerName(t));
            sendResponse(t, response);
        }

        Response serve(String method, String path) {
            return responder.respondTo(method,path);
        }

    }

}
