package cc.iboard.endpoints;

import cc.iboard.html.Html;

public class Index extends Endpoint {
    @Override
    public String respond() {
        return new Html().getHeader() + getHTMLBody();
    }

    private String getHTMLBody() {
        return Html.page(
          Html.body(
              Html.h1( Html.title() )
              .concat(Html.p(Html.lorem()))
          )
        );
    }
}
