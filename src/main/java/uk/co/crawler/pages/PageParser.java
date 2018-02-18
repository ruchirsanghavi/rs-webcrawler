package uk.co.crawler.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.crawler.interfaces.ILinkAttribute;
import uk.co.crawler.interfaces.ILinkAttributeProvider;
import uk.co.crawler.interfaces.IPageParser;
import uk.co.crawler.interfaces.IWebPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of page parser which uses the JSOUP library.
 *
 * @author rsanghavi
 */
@Component
public class PageParser implements IPageParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageParser.class);

    @Autowired
    private ILinkAttributeProvider linkAttributeProvider;

    @Override
    public List<IWebPage> parse(final IWebPage page) {
        final String navigableUrl = page.deriveNavigableUrl();
        final ArrayList<IWebPage> result = new ArrayList<>();
        final Document document;
        try {
            LOGGER.trace("Extracting: " + navigableUrl);
            document = Jsoup.connect(navigableUrl).get();
            final List<ILinkAttribute> allAttributes = this.linkAttributeProvider.getAllAttributes();
            for (final ILinkAttribute linkAttribute : allAttributes) {
                final Elements elements = document.select(linkAttribute.selector());
                for (final Element element : elements) {
                    final IWebPage childWebPage = new WebPage(new URL(element.attr(linkAttribute.attribute())), linkAttribute.type());
                    result.add(childWebPage);
                }
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to parse URL: " + navigableUrl);
        }
        return result;
    }


}
