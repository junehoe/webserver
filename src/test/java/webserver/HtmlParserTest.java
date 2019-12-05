package webserver;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class HtmlParserTest {
    @Test
    public void returnsParsedHtmlStringForHtmlThatExists() throws IOException {
        String htmlPath = "index.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<!DOCTYPE html>"));
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
