package uk.co.crawler.attributes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageAttributeTest {

    @Test
    public void testSelector() {
        final ImageAttribute attribute = new ImageAttribute();
        assertThat(attribute.selector()).isEqualTo("img[src]");
    }

    @Test
    public void testAttribute() {
        final ImageAttribute attribute = new ImageAttribute();
        assertThat(attribute.attribute()).isEqualTo("abs:src");
    }

    @Test
    public void testType() {
        final ImageAttribute attribute = new ImageAttribute();
        assertThat(attribute.type()).isEqualTo(ELinkType.MEDIA);
    }

}