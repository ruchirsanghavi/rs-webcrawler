package uk.co.crawler.interfaces;

import uk.co.crawler.attributes.EAttributeType;

/**
 * An interface which describes the characteristics of a link attribute.
 *
 * @author rsanghavi.
 */
public interface ILinkAttribute {

    /**
     * Describes the CCS selector statement that related to this attribute.
     *
     * @return the selector statement
     */
    String selector();

    /**
     * Describes the actual attribute to use to get hold of the value of this link.
     *
     * @return the attribute that gives you the link.
     */
    String attribute();

    /**
     * Describes the link attribute type of this link. See {@link EAttributeType} for further information.
     *
     * @return an attribute type
     */
    EAttributeType type();

}
