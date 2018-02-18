package uk.co.crawler.attributes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ATagAttributeTest {

    @Test
    public void testSelector() {
        final ATagAttribute attribute = new ATagAttribute();
        assertThat(attribute.selector()).isEqualTo("a[href]");
    }

    @Test
    public void testAttribute() {
        final ATagAttribute attribute = new ATagAttribute();
        assertThat(attribute.attribute()).isEqualTo("abs:href");
    }

    @Test
    public void testType() {
        final ATagAttribute attribute = new ATagAttribute();
        assertThat(attribute.type()).isEqualTo(ELinkType.LINK);
    }

}