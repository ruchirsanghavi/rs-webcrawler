package uk.co.crawler.pages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.crawler.attributes.ELinkType;
import uk.co.crawler.interfaces.IPageParser;
import uk.co.crawler.interfaces.IWebPage;
import uk.co.crawler.pages.LinkExtracter;
import uk.co.crawler.pages.WebPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LinkExtracterTest {

    private static final String PARENT_PAGE_URL = "http://bbc.co.uk/";
    private static final String CHILD_PAGE_1_URL = "http://wiprodigital.co.uk";
    private static final String CHILD_PAGE_2_URL = "http://bbc.co.uk/news";
    private static final String CHILD_PAGE_3_URL = "http://bbc.co.uk";

    @Mock
    private IPageParser pageParser;

    @InjectMocks
    private LinkExtracter extracter;

    private IWebPage parentPage;
    private IWebPage childPage1;
    private IWebPage childPage2;
    private IWebPage childPage3;
    private IWebPage childPage4;

    @Before
    public void setUp() throws MalformedURLException {
        this.parentPage = new WebPage(new URL(PARENT_PAGE_URL), ELinkType.LINK);
        this.childPage1 = new WebPage(new URL(CHILD_PAGE_1_URL), ELinkType.MEDIA);
        this.childPage2 = new WebPage(new URL(CHILD_PAGE_2_URL), ELinkType.LINK);
        this.childPage3 = new WebPage(new URL(CHILD_PAGE_3_URL), ELinkType.LINK);
        this.childPage4 = new WebPage(new URL(CHILD_PAGE_1_URL), ELinkType.LINK);
    }

    @Test
    public void testExtractWithDifferentTypeOfLink() {
        final List<IWebPage> childPagesLevelA = Arrays.asList(this.childPage1, this.childPage2);
        when(this.pageParser.parse(this.parentPage)).thenReturn(childPagesLevelA);

        final List<IWebPage> childPagesLevelB = Arrays.asList(this.childPage3);
        when(this.pageParser.parse(this.childPage2)).thenReturn(childPagesLevelB);

        final List<IWebPage> childPagesLevelC = Collections.emptyList();
        when(this.pageParser.parse(this.childPage3)).thenReturn(childPagesLevelC);

        final List<IWebPage> extract = this.extracter.extract(parentPage);

        assertThat(extract).isNotNull();
        assertThat(extract.size()).isEqualTo(2);
        assertThat(extract.get(0)).isEqualTo(this.childPage1);
        assertThat(extract.get(1)).isEqualTo(this.childPage2);
        assertThat(extract.get(1).getChildWebPages().size()).isEqualTo(1);
        assertThat(extract.get(1).getChildWebPages().get(0)).isEqualTo(this.childPage3);
    }

    @Test
    public void testExtractWithDifferentDomain() {
        final List<IWebPage> childPagesLevelA = Arrays.asList(this.childPage4, this.childPage2);
        when(this.pageParser.parse(this.parentPage)).thenReturn(childPagesLevelA);

        final List<IWebPage> childPagesLevelB = Arrays.asList(this.childPage3);
        when(this.pageParser.parse(this.childPage2)).thenReturn(childPagesLevelB);

        final List<IWebPage> childPagesLevelC = Collections.emptyList();
        when(this.pageParser.parse(this.childPage3)).thenReturn(childPagesLevelC);

        final List<IWebPage> extract = this.extracter.extract(parentPage);

        assertThat(extract).isNotNull();
        assertThat(extract.size()).isEqualTo(2);
        assertThat(extract.get(0)).isEqualTo(this.childPage4);
        assertThat(extract.get(1)).isEqualTo(this.childPage2);
        assertThat(extract.get(1).getChildWebPages().size()).isEqualTo(1);
        assertThat(extract.get(1).getChildWebPages().get(0)).isEqualTo(this.childPage3);
    }

    @Test
    public void testExtractPageVisited() {
        final List<IWebPage> childPagesLevelA = Arrays.asList(this.childPage1, this.childPage2);
        when(this.pageParser.parse(this.parentPage)).thenReturn(childPagesLevelA);

        final List<IWebPage> childPagesLevelB = Arrays.asList(this.childPage3);
        when(this.pageParser.parse(this.childPage2)).thenReturn(childPagesLevelB);

        final List<IWebPage> childPagesLevelC = Arrays.asList(this.childPage3);
        when(this.pageParser.parse(this.childPage3)).thenReturn(childPagesLevelC);

        final List<IWebPage> extract = this.extracter.extract(parentPage);

        assertThat(extract).isNotNull();
        assertThat(extract.size()).isEqualTo(2);
        assertThat(extract.get(0)).isEqualTo(this.childPage1);
        assertThat(extract.get(1)).isEqualTo(this.childPage2);
        assertThat(extract.get(1).getChildWebPages().size()).isEqualTo(1);
        assertThat(extract.get(1).getChildWebPages().get(0)).isEqualTo(this.childPage3);
    }

}