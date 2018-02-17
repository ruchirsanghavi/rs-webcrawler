package uk.co.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.crawler.attributes.EAttributeType;
import uk.co.crawler.interfaces.ILinkAttribute;
import uk.co.crawler.interfaces.ILinkAttributeProvider;
import uk.co.crawler.interfaces.ILinkExtracter;
import uk.co.crawler.interfaces.IWebPage;
import uk.co.crawler.pages.WebPage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An implementation of {@link ILinkExtracter} which recursively extracts all links on a given web page.
 *
 * @author rsanghavi.
 */
@Component
public class LinkExtracter implements ILinkExtracter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkExtracter.class);

    @Autowired
    private ILinkAttributeProvider linkAttributeProvider;

    private final Set<String> visitedPages = new HashSet<>();

    @Override
    public List<IWebPage> extract(final IWebPage webPage) throws IOException {
        // If we have already visited this page before, then return empty list.
        if (this.visitedPages.contains(webPage.getUrl().toString())) {
            return null;
        }

        LOGGER.trace("Extracting: " + webPage.getUrl());
        this.visitedPages.add(webPage.getUrl().toString());

        final ArrayList<IWebPage> result = new ArrayList<>();
        final Document document;
        try {
            document = Jsoup.connect(webPage.getUrl().toString()).get();
            final List<ILinkAttribute> allAttributes = this.linkAttributeProvider.getAllAttributes();
            for (final ILinkAttribute linkAttribute : allAttributes) {
                final Elements elements = document.select(linkAttribute.selector());
                for (final Element element : elements) {
                    final IWebPage childWebPage = new WebPage(new URL(element.attr(linkAttribute.attribute())));
                    if (EAttributeType.LINK.equals(linkAttribute.type()) && webPage.isSameDomain(childWebPage)) {
                        final List<IWebPage> childWebPages = extract(childWebPage);
                        if (null != childWebPages) {
                            webPage.addChildPages(childWebPages);
                        }
                    }
                    result.add(childWebPage);
                }
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to parse URL: " + webPage.getUrl());
        }

        return result;
    }
}
