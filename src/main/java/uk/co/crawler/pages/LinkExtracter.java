package uk.co.crawler.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.crawler.attributes.ELinkType;
import uk.co.crawler.interfaces.ILinkExtracter;
import uk.co.crawler.interfaces.IPageParser;
import uk.co.crawler.interfaces.IWebPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of {@link ILinkExtracter} which recursively extracts all links on a given web page.
 *
 * @author rsanghavi.
 */
@Component
public class LinkExtracter implements ILinkExtracter {

    @Autowired
    private IPageParser pageParser;

    private final Set<IWebPage> visitedPages = new HashSet<>();

    @Override
    public List<IWebPage> extract(final IWebPage webPage) {

        // If we have already visited this page before, then return null.
        if (this.visitedPages.contains(webPage)) {
            return null;
        }

        // Record this page as being visited
        this.visitedPages.add(webPage);

        // Start extraction of this page
        final List<IWebPage> result = this.pageParser.parse(webPage);

        // Find any valid child pages (only if they are in the same domain)
        final List<IWebPage> childWebPages = result.stream().filter(childWebPage -> ELinkType.LINK.equals(childWebPage.getLinkType()) && webPage.isSameDomain(childWebPage)).collect(Collectors.toList());

        // Populate links for each valid child page
        childWebPages.forEach(childWebPage -> childWebPage.addChildPages(extract(childWebPage)));

        return result;
    }
}
