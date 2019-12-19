package webserver.response;

import org.junit.Test;
import static org.junit.Assert.*;

public class HttpResponseTest {
    private final int OK_CODE = 200;
    private final int NOT_FOUND_CODE = 404;

    @Test
    public void returnsResponseMethod() {
        HttpResponse httpResponse = new HttpResponse.Builder("HEAD")
                .build();

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsStatusCode() {
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(OK_CODE)
                .build();

        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsStatusString() {
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND_CODE)
                .build();

        assertEquals(httpResponse.getStatusString(), "Not Found");
    }

    @Test
    public void returnsContentLength() {
        String content = "Hello there";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND_CODE)
                .withContentLength(content.length())
                .build();

        assertEquals(httpResponse.getContentLength(), 11);
    }

    @Test
    public void returnsContentType() {
        String contentType = "text/html";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND_CODE)
                .withContentType(contentType)
                .build();

        assertEquals(httpResponse.getContentType(), "text/html");
    }

    @Test
    public void returnsContent() {
        String content = "This is exclusive content";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND_CODE)
                .withContent(content)
                .build();

        assertEquals(httpResponse.getContent(), "This is exclusive content");
    }
}
