package webserver;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import static webserver.pages.Page.BODY;
import static webserver.pages.Page.ERROR_HTML;
import static webserver.pages.Page.TITLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlBuilderTest {
    HashMap<String, String> hashMap;

    @Before
    public void initialize() {
        hashMap = HtmlBuilder.createPageDescriptors("Hello", "World");
    }

    @Test
    public void createsAHashMap() {
        assertTrue(hashMap instanceof HashMap);
    }

    @Test
    public void createsAHashMapWithGivenTitle() {
        String expectedTitle = "Hello";

        assertEquals(hashMap.get(TITLE), expectedTitle);
    }

    @Test
    public void createsAHashMapWithGivenBody() {
        String expectedBody = "World";

        assertEquals(hashMap.get(BODY), expectedBody);
    }

    @Test
    public void createsHtmlStringBasedOnPageHashMap() throws IOException {
        String htmlString = HtmlBuilder.createHtmlString(hashMap);

        assertTrue(htmlString.contains("Hello"));
        assertTrue(htmlString.contains("World"));
    }

    @Test
    public void createsCustomHtmlStringBasedOnPath() throws IOException {
        String htmlString = HtmlBuilder.createHtmlString(ERROR_HTML);

        assertTrue(htmlString.contains("404 Page Not Found"));
    }

    @Test
    public void createsHtmlHeaderString() {
        String headerTitle = "This is the header title";
        String expected = "<header><h1>This is the header title</h1></header>";

        assertEquals(HtmlBuilder.createHeader(headerTitle), expected);
    }

    @Test
    public void createsHtmlSectionString() {
        String path = "/test-path";
        String contents = "This is the content";
        String expected = "<section><a rel='item' href='" + path + "'>" + contents + "</a></section>";

        assertEquals(HtmlBuilder.createSection(path, contents), expected);
    }
}
