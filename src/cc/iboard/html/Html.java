package cc.iboard.html;

/**
 * Some HTML-Helper methods.
 */
public class Html {

    /**
     * @return String html head with stylesheets
     */
    public static String getHeader() {
        return head(
                stylesheet( "https://s3.eu-central-1.amazonaws.com/iboard.core/static/iboard.css"  )
        );

    }

    /**
     * Wrapper for {@code<head>....</head>}
     * @param headers everythin in between
     * @return String
     */
    private static String head(String headers) {
        return "<head>\n" + headers + "\n" + "</head>\n";
    }

    /**
     * Return a link rel for the given css file
     * @param url of the css-file
     * @return String
     */
    private static String stylesheet(String url) {
        return "<link rel=\"stylesheet\" href=\"" + url + "\" />";

    }

    /**
     * @return String – the title of the html-page
     */
    public static String title() {
        return "iboard.cc - index";
    }

    /**
     * Wrapper for h1 tags
     * @param text inside H1
     * @return String with text inside H1 tag
     */
    public static String h1(String text) {
        return "<h1>" + text + "</h1>";
    }

    /**
     * Body Wrapper
     * @param content everything in between
     * @return the body of the html-page
     */
    public static String body(String content) {
        return "<body>\n" + content + "\n</body>";
    }

    /**
     * Wrapper for the entire html-page
     * @param content of the page
     * @return The entire html-respond body
     */
    public static String html(String content) {
        return "<html>\n" + content + "\n</html>";
    }

    /**
     * Wrapper for p-tags
     * @param text the text inside <p>...</p>
     * @return the paragraph string
     */
    public static String p(String text) {
        return "<p>" + text + "</p>\n";
    }

    /**
     * @return some longer text to be used for mockups.
     */
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

	public static String a(String title, String link) {
		return "<a href=\"" + link + "\">" + title + "</a>"; 
	}
}
