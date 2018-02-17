package uk.co.crawler.pages;

import uk.co.crawler.attributes.EAttributeType;

import java.net.URL;
import java.util.List;

/**
 * A Web Page domain object which allows for storage of the URL and any child pages that it contains.
 */
public class WebPage {

    private final URL url;
    private final List<WebPage> childWebPages;

    public WebPage(final URL url, final List<WebPage> childWebPages) {
        this.url = url;
        this.childWebPages = childWebPages;
    }

    public URL getUrl() {
        return this.url;
    }

    public List<WebPage> getChildWebPages() {
        return this.childWebPages;
    }
}
