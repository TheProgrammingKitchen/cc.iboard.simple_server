package cc.iboard.endpoints;

import cc.iboard.backend.Request;
import cc.iboard.backend.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Assets extends Endpoint {

    @Override
    public Response respond(Request request) {
        try {
            return new Response(Response.HTTP_OK, renderResponse(request));
        } catch (IOException _e) {
            return new Response(Response.HTTP_NOT_FOUND, renderError(request));
        }
    }

    private String renderError(Request request) {
        return String.format("File '%s' doesn't exist or is not readable.", request.path());
    }

    private String renderResponse(Request request) throws IOException {
        String filePath = extractFilePath(request);
        return readFile(filePath);
    }

    private String readFile(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(".", filePath );
        return fileContent(path);
    }

    private String fileContent(Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        StringBuilder content = new StringBuilder();
        BufferedReader reader = Files.newBufferedReader(path, charset);
        String line = null;
        while ((line = reader.readLine()) != null) {
            content.append(line + "\n");
        }
        return content.toString();
    }

    private String extractFilePath(Request request) {
        String[] parts = request.path().split("\\/");
        String path = rejoinPathWithoutEndpoint(parts);
        return path;
    }

    private String rejoinPathWithoutEndpoint(String[] parts) {
        List<String> list = makeStringList(parts);
        return joinList(list,"/");
    }

    private List<String> makeStringList(String[] parts) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            if ( ! parts[i].equals("") )
                list.add(parts[i]);
        }
        return list;
    }

    private String joinList(List<String> list, String glue) {
        StringBuilder buffer = new StringBuilder();
        list.forEach( part -> { buffer.append(glue).append(part); });
        return buffer.toString();
    }
}

