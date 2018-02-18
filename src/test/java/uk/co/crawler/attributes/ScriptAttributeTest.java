package uk.co.crawler.attributes;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScriptAttributeTest {

    @Test
    public void testSelector() {
        final ScriptAttribute attribute = new ScriptAttribute();
        assertThat(attribute.selector()).isEqualTo("script[src]");
    }

    @Test
    public void testAttribute() {
        final ScriptAttribute attribute = new ScriptAttribute();
        assertThat(attribute.attribute()).isEqualTo("abs:src");
    }

    @Test
    public void testType() {
        final ScriptAttribute attribute = new ScriptAttribute();
        assertThat(attribute.type()).isEqualTo(ELinkType.IMPORT);
    }

}