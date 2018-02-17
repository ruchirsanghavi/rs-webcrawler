package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * Refers to the <a> attribute in HTML pages.
 *
 * @author rsanghavi.
 */
public class ATagAttribute implements ILinkAttribute {

    @Override
    public String selector() {
        return "a[href]";
    }

    @Override
    public String attribute() {
        return "abs:href";
    }

    @Override
    public EAttributeType type() {
        return EAttributeType.LINK;
    }
}
