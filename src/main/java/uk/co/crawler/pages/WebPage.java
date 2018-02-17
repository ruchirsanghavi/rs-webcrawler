package uk.co.crawler.pages;

import uk.co.crawler.interfaces.IWebPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A Web Page domain object which allows for storage of the URL and any child pages that it contains.
 */
public class WebPage implements IWebPage {

    private final URL url;
    private List<IWebPage> childWebPages;

    public WebPage(final URL url) {
        this.url = url;
        this.childWebPages = null;
    }

    @Override
    public URL getUrl() {
        return this.url;
    }

    @Override
    public List<IWebPage> getChildWebPages() {
        return this.childWebPages;
    }

    @Override
    public void addChildPages(final List<IWebPage> childWebPages) {
        if (this.childWebPages == null) {
            this.childWebPages = new ArrayList<>();
        }
        this.childWebPages.addAll(childWebPages);
    }

    @Override
    public boolean isSameDomain(final IWebPage webPage) {
        return this.url.getHost().equals(webPage.getUrl().getHost());
    }
}
