package uk.co.crawler.interfaces;

import java.util.List;

/**
 * A provider interface to get hold of different link attributes.
 *
 * @author rsanghavi.
 */
public interface ILinkAttributeProvider {

    /**
     * Derives and gets all the available attributes that are registered with the application.
     *
     * @return a list of relevant link attributes.
     */
    List<ILinkAttribute> getAllAttributes();

}
