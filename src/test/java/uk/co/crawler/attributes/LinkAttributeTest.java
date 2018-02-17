package uk.co.crawler.attributes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkAttributeTest {

    @Test
    public void testSelector() {
        final LinkAttribute attribute = new LinkAttribute();
        assertThat(attribute.selector()).isEqualTo("link[href]");
    }

    @Test
    public void testAttribute() {
        final LinkAttribute attribute = new LinkAttribute();
        assertThat(attribute.attribute()).isEqualTo("abs:href");
    }

    @Test
    public void testType() {
        final LinkAttribute attribute = new LinkAttribute();
        assertThat(attribute.type()).isEqualTo(EAttributeType.IMPORT);
    }

}