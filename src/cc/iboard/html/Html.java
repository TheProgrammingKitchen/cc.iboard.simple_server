package cc.iboard.html;

public class Html {
    public static String getHeader() {
        return head(
                stylesheet( "https://s3.eu-central-1.amazonaws.com/iboard.core/static/iboard.css"  )
        );

    }

    public static String head(String headers) {
        return "<head>\n" + headers + "\n" + "</head>\n";
    }

    public static String stylesheet(String url) {
        return "<link rel=\"stylesheet\" href=\"" + url + "\" />";

    }

    public static String title() {
        return "iboard.cc - index";
    }

    public static String h1(String text) {
        return "<h1>" + text + "</h1>";
    }

    public static String body(String content) {
        return "<body>\n" + content + "\n</body>";
    }

    public static String page(String content) {
        return "<html>\n" + content + "\n</html>";
    }

    public static String p(String text) {
        return "<p>" + text + "</p>\n";
    }

    public static String lorem() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                "In convallis euismod quam sit amet lobortis. Ut auctor " +
                "urna at ante ultrices, eget pellentesque sapien ultrices. " +
                "In aliquet odio non suscipit fringilla. Donec pretium " +
                "luctus turpis vitae ullamcorper. Vestibulum condimentum, " +
                "purus in euismod porttitor, lacus quam commodo risus, nec " +
                "volutpat lectus elit malesuada purus. Aliquam sed magna " +
                "euismod, cursus lectus non, accumsan urna. Donec pellentesque " +
                "sapien non nisl blandit elementum. In hac habitasse platea "+
                "dictumst. Aenean pharetra vel mi ac ornare. Proin sed turpis "+
                "eleifend, dapibus dolor vel, sagittis lacus. Aliquam faucibus "+
                "rutrum consectetur. Sed imperdiet nec neque vitae vestibulum. "+
                "In faucibus, est ut cursus rutrum, massa augue interdum nulla, "+
                "in tempus ex erat ac neque. Phasellus laoreet lacus sed "+
                "ultrices auctor. Integer diam massa, commodo sed metus ac, "+
                "lacinia porta lectus.\n" +
                "\n";
    }
}
