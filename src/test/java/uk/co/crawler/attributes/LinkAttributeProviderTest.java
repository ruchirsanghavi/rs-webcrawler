package uk.co.crawler.attributes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkAttributeProviderTest {

    @Test
    public void testGetAllAttributes() {
        final LinkAttributeProvider linkAttributeProvider = new LinkAttributeProvider();
        assertThat(linkAttributeProvider.getAllAttributes()).isNotNull();
        assertThat(linkAttributeProvider.getAllAttributes().size()).isEqualTo(4);
    }
}
