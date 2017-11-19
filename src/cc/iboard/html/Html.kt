package cc.iboard.html

/**
 * Some HTML-Helper methods.
 */
object Html {

    private val CSS_URL = "https://s3.eu-central-1.amazonaws.com/iboard.core/static/iboard.css"

    /**
     * @return String html head with stylesheets
     */
    val header: String
        get() = head(stylesheet(CSS_URL))

    /**
     * Wrapper for {@code<head>....</head>}
     *
     * @param headers
     * everythin in between
     * @return String
     */
    private fun head(headers: String): String {
        return "<head>\n$headers\n</head>\n"
    }

    /**
     * Return a link rel for the given css file
     *
     * @param url
     * of the css-file
     * @return String
     */
    private fun stylesheet(url: String): String {
        return "<link rel=\"stylesheet\" href=\"$url\" />"

    }

    /**
     * @return String – the title of the html-page
     */
    fun title(): String {
        return "iboard.cc - index"
    }

    /**
     * Wrapper for h1 tags
     *
     * @param text
     * inside H1
     * @return String with text inside H1 tag
     */
    fun h1(text: String): String {
        return "<h1>$text</h1>"
    }

    /**
     * Body Wrapper
     *
     * @param content
     * everything in between
     * @return the body of the html-page
     */
    fun body(content: String): String {
        return "<body>\n$content\n</body>"
    }

    /**
     * Wrapper for the entire html-page
     *
     * @param content
     * of the page
     * @return The entire html-respond body
     */
    fun html(content: String): String {
        return "<html>\n$content\n</html>"
    }

    /**
     * Wrapper for p-tags
     *
     * @param text
     * the text inside
     *
     *
     * ...
     *
     * @return the paragraph string
     */
    fun p(text: String): String {
        return "<p>$text</p>\n"
    }

    /**
     * @return some longer text to be used for mockups.
     */
    fun lorem(): String {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit."   +
        "In convallis euismod quam sit amet lobortis. Ut auctor "           +
        "urna at ante ultrices, eget pellentesque sapien ultrices. "        +
        "In aliquet odio non suscipit fringilla. Donec pretium "            +
        "luctus turpis vitae ullamcorper. Vestibulum condimentum, "         +
        "purus in euismod porttitor, lacus quam commodo risus, nec "        +
        "volutpat lectus elit malesuada purus. Aliquam sed magna "          +
        "euismod, cursus lectus non, accumsan urna. Donec pellentesque "    +
        "sapien non nisl blandit elementum. In hac habitasse platea "       +
        "dictumst. Aenean pharetra vel mi ac ornare. Proin sed turpis "     +
        "eleifend, dapibus dolor vel, sagittis lacus. Aliquam faucibus "    +
        "rutrum consectetur. Sed imperdiet nec neque vitae vestibulum. "    +
        "In faucibus, est ut cursus rutrum, massa augue interdum nulla, "   +
        "in tempus ex erat ac neque. Phasellus laoreet lacus sed "          +
        "ultrices auctor. Integer diam massa, commodo sed metus ac, "       +
        "lacinia porta lectus.\n"                                           +
        "\n"
    }

    /**
     * Return a html-link
     * @param title visible text
     * @param link url-string
     * @return
     */
    fun a(title: String, link: String): String {
        return "<a href=\"$link\">$title</a>"
    }

    /**
     * Wrap string with <pre>
     * @param string
     * @return
    </pre> */
    fun pre(string: String): String {
        return "<pre>$string</pre>\n"
    }

    /**
     * Render a standard page with the given title.
     * If title is null, the default title, defined in this class will be used.
     * @param titleString
     * @param text
     * @return
     */
    fun renderPage(titleString: String?, text: String): String {
        val title = titleString ?: title()
        return header + html(
                Html.body(
                        Html.h1(title) + Html.p(text)
                )
        )
    }
}
