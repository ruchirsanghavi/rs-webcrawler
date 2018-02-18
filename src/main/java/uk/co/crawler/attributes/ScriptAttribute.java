package uk.co.crawler.attributes;

import uk.co.crawler.interfaces.ILinkAttribute;

/**
 * Refers to the <script> attribute in HTML pages.
 *
 * @author rsanghavi
 */
public class ScriptAttribute implements ILinkAttribute {

    @Override
    public String selector() {
        return "script[src]";
    }

    @Override
    public String attribute() {
        return "abs:src";
    }

    @Override
    public ELinkType type() {
        return ELinkType.IMPORT;
    }
}
