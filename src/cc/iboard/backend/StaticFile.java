package cc.iboard.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StaticFile {
    public static final String FILE_ENCODING = "UTF-8";
    public static final String PATH_DELIMITER = "\\/";
    public static final String NEWLINE = "\n";

    /**
     * Reads a static file from 'Request.path()'
     * Throws an IOException if file is not found or not readable.
     * @param request
     * @return
     * @throws IOException
     */
    public static String readFile(Request request) throws IOException {
        String filePath = extractFilePath(request);
        Path path = FileSystems.getDefault().getPath(".", filePath );
        return fileContent(path);
    }

    // IMPLEMENTATION

    private static String fileContent(Path path) throws IOException {
        BufferedReader reader = getReader(path);
        StringBuilder content = readContent(reader);
        return content.toString();
    }

    private static StringBuilder readContent(BufferedReader reader) throws IOException {
        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null)
            content.append(line + NEWLINE);
        return content;
    }

    private static BufferedReader getReader(Path path) throws IOException {
        Charset charset = Charset.forName(FILE_ENCODING);
        return Files.newBufferedReader(path, charset);
    }

    private static String extractFilePath(Request request) {
        String[] parts = request.path().split(PATH_DELIMITER);
        String path = rejoinPathWithoutEndpoint(parts);
        return path;
    }

    private static String rejoinPathWithoutEndpoint(String[] parts) {
        List<String> list = makeStringList(parts);
        return joinList(list,"/");
    }

    private static List<String> makeStringList(String[] parts) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            if ( ! parts[i].equals("") )
                list.add(parts[i]);
        }
        return list;
    }

    private static String joinList(List<String> list, String glue) {
        StringBuilder buffer = new StringBuilder();
        list.forEach( part -> { buffer.append(glue).append(part); });
        return buffer.toString();
    }
}
