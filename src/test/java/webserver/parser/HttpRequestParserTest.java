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
    public void getsTheCaseInsensitiveContentTypeHeaderFromHashMapOfHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("cOnTeNt-TypE", "It works");

        assertEquals(HttpRequestParser.getContentTypeFrom(headers), "It works");
    }

    @Test
    public void returnsUnsupportedMediaTypeIfContentTypeDoesNotExist() {
        HashMap<String, String> headers = new HashMap<>();

        assertEquals(HttpRequestParser.getContentTypeFrom(headers), "Unsupported Media Type");
    }

    @Test
    public void returnsTheIdFromARequestPath() throws NumberFormatException {
        String path = "/todo/1/toggle";

        assertEquals(1, HttpRequestParser.getIdFromPath(path));
    }

    @Test
    public void returnsErrorIfPathIdIsNotParsable() {
        try {
            String path = "/todo/not-gonna-work/edit";
            int id = HttpRequestParser.getIdFromPath(path);
        } catch (NumberFormatException e) {
            System.out.println("This will be printed");
        }
    }
}
