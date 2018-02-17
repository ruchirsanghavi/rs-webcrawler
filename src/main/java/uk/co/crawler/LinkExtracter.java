package uk.co.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.crawler.interfaces.ILinkAttribute;
import uk.co.crawler.interfaces.ILinkAttributeProvider;
import uk.co.crawler.interfaces.ILinkExtracter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rsanghavi.
 */
@Component
public class LinkExtracter implements ILinkExtracter {

    @Autowired
    private ILinkAttributeProvider linkAttributeProvider;

    @Override
    public List<String> extract(final String url) throws IOException {
        final ArrayList<String> result = new ArrayList<>();
        final Document document = Jsoup.connect(url).get();

        final List<ILinkAttribute> allAttributes = this.linkAttributeProvider.getAllAttributes();
        for(final ILinkAttribute linkAttribute : allAttributes) {
            final Elements elements = document.select(linkAttribute.selector());
            for(final Element element : elements) {
                result.add(element.attr(linkAttribute.attribute()));
            }
        }

        return result;
    }
}
