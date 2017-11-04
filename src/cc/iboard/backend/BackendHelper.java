package cc.iboard.backend;

public class BackendHelper {

    public static String upcaseFirstChar(String path) {
        String req = stripFirstSlash(path);
        String first = path.substring(0,1); // get the first character
        return first.toUpperCase() + req;   // Upcase first character and append the rest
    }

    public static String stripFirstSlash(String string) {
        return string.substring(1);     // ignore slash and first character
    }
}
