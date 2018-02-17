package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * @author rsanghavi.
 */
public class ImageAttribute implements ILinkAttribute {

    @Override
    public String selector() {
        return "img[src]";
    }

    @Override
    public String attribute() {
        return "abs:src";
    }
}
