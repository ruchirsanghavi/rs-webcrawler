package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * Refers to the <img> attribute in HTML pages.
 *
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

    @Override
    public ELinkType type() {
        return ELinkType.MEDIA;
    }
}
