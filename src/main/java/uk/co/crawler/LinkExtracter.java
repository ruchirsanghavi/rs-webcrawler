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
    public List<WebPage> extract(final String url) throws IOException {
        // If we have already visited this page before, then return.
        if (this.visitedPages.contains(url)) {
            return null;
        }

        LOGGER.trace("Extracting: " + url);
        this.visitedPages.add(url);

        final ArrayList<WebPage> result = new ArrayList<>();
        final Document document;
        try {
            document = Jsoup.connect(url).get();
            final List<ILinkAttribute> allAttributes = this.linkAttributeProvider.getAllAttributes();
            for (final ILinkAttribute linkAttribute : allAttributes) {
                final Elements elements = document.select(linkAttribute.selector());
                for (final Element element : elements) {
                    final String attr = element.attr(linkAttribute.attribute());
                    final URL attributeUrl = new URL(attr);
                    final WebPage webPage;
                    final URL pageUrl = new URL(url);
                    if (EAttributeType.LINK.equals(linkAttribute.type()) && pageUrl.getHost().equals(attributeUrl.getHost())) {
                        webPage = new WebPage(attributeUrl, extract(attr));
                    } else {
                        webPage = new WebPage(attributeUrl, null);
                    }
                    result.add(webPage);
                }
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to parse URL: " + url);
        }

        return result;
    }
}
