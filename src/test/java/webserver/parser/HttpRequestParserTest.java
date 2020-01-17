package webserver.parser;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HttpRequestParserTest {
    @Test
    public void getsTheTitleFromTheRequestBody() {
        String body = "title=This+is+awesome";
        String expected = "This is awesome";

        assertEquals(HttpRequestParser.getTitle(body), expected);
    }

    @Test
    public void getsTheContentTypeFromHashMapOfHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "This is the content type");

        assertEquals(HttpRequestParser.getContentTypeFrom(headers), "This is the content type");
    }

    @Test
    public void returnsUnsupportedMediaTypeIfContentTypeDoesNotExist() {
        HashMap<String, String> headers = new HashMap<>();

        assertEquals(HttpRequestParser.getContentTypeFrom(headers), "Unsupported Media Type");
    }
}
