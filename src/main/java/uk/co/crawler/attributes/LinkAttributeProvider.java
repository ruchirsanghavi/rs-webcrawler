package uk.co.crawler.attributes;

import org.springframework.stereotype.Component;
import uk.co.crawler.interfaces.ILinkAttribute;
import uk.co.crawler.interfaces.ILinkAttributeProvider;

import java.util.Arrays;
import java.util.List;

/**
 * @author rsanghavi.
 */
@Component
public class LinkAttributeProvider implements ILinkAttributeProvider {

    @Override
    public List<ILinkAttribute> getAllAttributes() {
        final ATagAttribute tagAttribute = new ATagAttribute();
        final ImageAttribute imageAttribute = new ImageAttribute();
        final LinkAttribute linkAttribute = new LinkAttribute();
        return Arrays.asList(tagAttribute, imageAttribute, linkAttribute);
    }
}
