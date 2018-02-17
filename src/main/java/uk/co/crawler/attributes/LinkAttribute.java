package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * @author rsanghavi.
 */
public class LinkAttribute implements ILinkAttribute{

    @Override
    public String selector() {
        return "link[href]";
    }

    @Override
    public String attribute() {
        return "abs:href";
    }
}
