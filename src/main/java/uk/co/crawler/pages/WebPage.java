package uk.co.crawler.pages;

import uk.co.crawler.attributes.EAttributeType;
import uk.co.crawler.interfaces.IWebPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A Web Page domain object which allows for storage of the URL and any child pages that it contains.
 */
public class WebPage implements IWebPage {

    private final URL url;
    private final EAttributeType attributeType;
    private List<IWebPage> childWebPages;

    public WebPage(final URL url, final EAttributeType attributeType) {
        this.url = url;
        this.attributeType = attributeType;
        this.childWebPages = null;
    }

    @Override
    public URL getFullUrl() {
        return this.url;
    }

    @Override
    public String deriveNavigableUrl() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.url.getProtocol());
        builder.append("://");
        builder.append(this.url.getHost());
        if (null != this.url.getPath() && !this.url.getPath().isEmpty()) {
            builder.append(this.url.getPath());
        }
        return builder.toString();
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
        return this.url.getHost().equals(webPage.getFullUrl().getHost());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WebPage) {
            final WebPage other = (WebPage) obj;
            return deriveNavigableUrl().equals(other.deriveNavigableUrl());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return deriveNavigableUrl().hashCode();
    }
}
