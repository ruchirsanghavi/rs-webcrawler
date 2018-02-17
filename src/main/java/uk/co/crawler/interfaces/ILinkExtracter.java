package uk.co.crawler.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * @author rsanghavi.
 */
public interface ILinkExtracter {

    List<String> extract(final String url) throws IOException;

}
