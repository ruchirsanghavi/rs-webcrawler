package uk.co.crawler.interfaces;

import java.util.List;

/**
 * An interface will allows for {@link IWebPage}'s to be parsed.
 *
 * @author rsanghavi
 */
public interface IPageParser {

    /**
     * Parse a given web page and return any child web pages that it contains.
     *
     * @param webPage - the page to parse
     * @return the list of child web pages attached to the given page.
     */
    List<IWebPage> parse(final IWebPage webPage);
}
