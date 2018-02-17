package uk.co.crawler.attributes;

/**
 * An Enum which describes different types of attributes for categorisation, such that they can be acted upon accordingly.
 *
 * @author rsanghavi
 */
public enum EAttributeType {
    /**
     * This type refers to either images or CCS files etc.
     */
    MEDIA,

    /**
     * This type refers to imported JS scripts or any other links which are linked.
     */
    IMPORT,

    /**
     * This type refers to links which might be valid.
     */
    LINK
}
