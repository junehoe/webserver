package webserver;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class HtmlParserTest {
    @Test
    public void returnsParsedHtmlStringForIndexHtml() throws IOException {
        String htmlPath = "public/index.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Hello World!</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForErrorHtml() throws IOException {
        String htmlPath = "public/error.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>404 Page Not Found</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForHealthCheckHtml() throws IOException {
        String htmlPath = "public/health-check.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Everything is good!</h1>"));
    }

    @Test
    public void throwsErrorIfHtmlDoesNotExist() {
        String htmlPath = "obviouslyFakePath.html";
        try {
            HtmlParser.parseHtml(htmlPath);
            System.out.println("This does not get printed");
        } catch (IOException e) {
            System.out.println("Exception was thrown");
        }
    }
}
