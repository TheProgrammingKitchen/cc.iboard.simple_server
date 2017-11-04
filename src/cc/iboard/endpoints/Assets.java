package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;
import cc.iboard.backend.StaticFile;

import java.io.IOException;

public class Assets extends Endpoint {

    public static final String FILE_NOT_FOUND_FORMAT_STRING = "File '%s' doesn't exist or is not readable.";

    @Override
    public Response respond(Request request) {
        try {
            return new Response(Response.HTTP_OK, renderResponse(request));
        } catch (IOException _e) {
            return new Response(Response.HTTP_NOT_FOUND, renderError(request));
        }
    }

    // IMPLEMENTATION

    private String renderResponse(Request request) throws IOException {
        return StaticFile.readFile(request);
    }

    private String renderError(Request request) {
        return String.format(FILE_NOT_FOUND_FORMAT_STRING, request.path());
    }

}

