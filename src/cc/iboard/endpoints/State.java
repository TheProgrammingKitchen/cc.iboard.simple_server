package cc.iboard.endpoints;

import cc.iboard.html.Html;

public class State extends Endpoint {
    @Override
    public String respond() {
        return Html
                .getHeader()
                .concat("Running");
    }
}
