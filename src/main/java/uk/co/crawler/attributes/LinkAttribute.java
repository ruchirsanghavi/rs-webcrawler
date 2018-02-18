package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * Refers to the <link> attributes in HTML pages.
 *
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

    @Override
    public ELinkType type() {
        return ELinkType.IMPORT;
    }
}
