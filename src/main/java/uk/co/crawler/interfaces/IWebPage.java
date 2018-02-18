package uk.co.crawler.interfaces;

import uk.co.crawler.attributes.ELinkType;

import java.net.URL;
import java.util.List;

/**
 * Represents operations and actions that can be performed a web page.
 *
 * @author rsanghavi
 */
public interface IWebPage {

    /**
     * Get the full URL of this web page.
     *
     * @return - URL of this web page.
     */
    URL getFullUrl();

    /**
     * Derives a clean navigable version of the {@link IWebPage#getFullUrl()} as String
     *
     * @return - a clean string representation of the full URL for this web page that should be navigable.
     */
    String deriveNavigableUrl();

    /**
     * Get all child web pages from this web page.
     *
     * @return - a list of child pages
     */
    List<IWebPage> getChildWebPages();

    /**
     * Gets the {@link ELinkType} of this web page.
     * @return - the type of link this web page is.
     */
    ELinkType getLinkType();

    /**
     * Add any additional web pages as children of this web page.
     *
     * @param childWebPages - the child pages to be added for this web page.
     */
    void addChildPages(List<IWebPage> childWebPages);

    /**
     * Checks whether a given page is in the same domain as the current web page.
     *
     * @param webPage - the web page to check against.
     * @return true if the given web page is part of the same domain.
     */
    boolean isSameDomain(IWebPage webPage);
}
