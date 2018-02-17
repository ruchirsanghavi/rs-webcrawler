package uk.co.crawler.interfaces;

import uk.co.crawler.pages.WebPage;

import java.io.IOException;
import java.util.List;

/**
 * An interface which allows for URL web page to be extracted for its links. It also identifies child pages on a given web page.
 *
 * @author rsanghavi.
 */
public interface ILinkExtracter {

    /**
     * Extracts all relevant links that are found on the URL web page, in the form of a list of {@link WebPage}.
     *
     * @param url - the URL to extract.
     * @return list of web pages, which contain details of links within each web page.
     * @throws IOException - may throw exception if a URL is no reachable or invalid.
     */
    List<WebPage> extract(final String url) throws IOException;

}
