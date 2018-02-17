package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
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
}
