package uk.co.crawler.pages;

import org.junit.Test;
import uk.co.crawler.attributes.ELinkType;
import uk.co.crawler.interfaces.IWebPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebPageTest {

    private static final String TEST_URL_1 = "http://bbc.co.uk/";
    private static final String TEST_URL_2 = "http://bbc.co.uk/news";
    private static final String TEST_URL_3 = "http://bbc.co.uk/news/article?id=23";
    private static final String TEST_URL_4 = "http://wiprodigital.co.uk";
    private static final String TEST_URL_5 = "http://bbc.co.uk/news#abc";

    @Test
    public void testGetFullUrl() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_1);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.getFullUrl()).isEqualTo(testUrl);
    }

    @Test
    public void testGetLinkType() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_1);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.getLinkType()).isEqualTo(ELinkType.LINK);
    }

    @Test
    public void testDeriveNavigableUrl() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_1);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.deriveNavigableUrl()).isEqualTo("http://bbc.co.uk/");
    }

    @Test
    public void testDeriveNavigableUrlWithPath() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_2);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.deriveNavigableUrl()).isEqualTo("http://bbc.co.uk/news");
    }

    @Test
    public void testDeriveNavigableUrlWithPathAndQuery() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_3);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.deriveNavigableUrl()).isEqualTo("http://bbc.co.uk/news/article");
    }

    @Test
    public void testDeriveNavigableUrlWithoutPath() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_4);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.deriveNavigableUrl()).isEqualTo(TEST_URL_4);
    }

    @Test
    public void testChildWebPagesNull() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_1);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        assertThat(webPage.getChildWebPages()).isNull();
    }

    @Test
    public void testAddChildPages() throws MalformedURLException {
        final URL testUrl = new URL(TEST_URL_1);
        final WebPage webPage = new WebPage(testUrl, ELinkType.LINK);
        final List<IWebPage> childPages = new ArrayList<>();
        final WebPage childWebPage1 = new WebPage(new URL(TEST_URL_2), ELinkType.LINK);
        final WebPage childWebPage2 = new WebPage(new URL(TEST_URL_3), ELinkType.MEDIA);
        final WebPage childWebPage3 = new WebPage(new URL(TEST_URL_4), ELinkType.IMPORT);
        childPages.add(childWebPage1);
        childPages.add(childWebPage2);
        childPages.add(childWebPage3);
        webPage.addChildPages(childPages);

        assertThat(webPage.getChildWebPages()).isNotNull();
        assertThat(webPage.getChildWebPages()).isEqualTo(childPages);
    }

    @Test
    public void testIsSameDomainForSameDomain() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_1);
        final URL testUrl2 = new URL(TEST_URL_2);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        final WebPage webPage2 = new WebPage(testUrl2, ELinkType.LINK);
        assertThat(webPage1.isSameDomain(webPage2)).isTrue();
    }

    @Test
    public void testIsSameDomainForSameLink() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_1);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        assertThat(webPage1.isSameDomain(webPage1)).isTrue();
    }

    @Test
    public void testIsSameDomainDifferentDomains() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_1);
        final URL testUrl2 = new URL(TEST_URL_4);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        final WebPage webPage2 = new WebPage(testUrl2, ELinkType.LINK);
        assertThat(webPage1.isSameDomain(webPage2)).isFalse();
    }

    @Test
    public void testEqualsSameUrl() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_1);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        assertThat(webPage1.equals(webPage1)).isTrue();
    }

    @Test
    public void testEqualsSameNavigableUrl() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_2);
        final URL testUrl2 = new URL(TEST_URL_5);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        final WebPage webPage2 = new WebPage(testUrl2, ELinkType.LINK);
        assertThat(webPage1.equals(webPage2)).isTrue();
    }

    @Test
    public void testEqualsFalseDifferentObjects() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_2);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        assertThat(webPage1.equals(testUrl1)).isFalse();
    }

    @Test
    public void testHashCode() throws MalformedURLException {
        final URL testUrl1 = new URL(TEST_URL_2);
        final WebPage webPage1 = new WebPage(testUrl1, ELinkType.LINK);
        assertThat(webPage1.hashCode()).isEqualTo(webPage1.deriveNavigableUrl().hashCode());
    }
}
