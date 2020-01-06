package webserver.response;

import org.junit.Test;
import static org.junit.Assert.*;

import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;

public class HttpResponseTest {
    @Test
    public void returnsResponseMethod() {
        HttpResponse httpResponse = new HttpResponse.Builder("HEAD")
                .build();

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsStatusCode() {
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(OK)
                .build();

        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsStatusString() {
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND)
                .build();

        assertEquals(httpResponse.getStatusString(), "Not Found");
    }

    @Test
    public void returnsContentLength() {
        String content = "Hello there";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND)
                .withContentLength(content.length())
                .build();

        assertEquals(httpResponse.getContentLength(), 11);
    }

    @Test
    public void returnsContentType() {
        String contentType = "text/html";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND)
                .withContentType(contentType)
                .build();

        assertEquals(httpResponse.getContentType(), "text/html");
    }

    @Test
    public void returnsContent() {
        String content = "This is exclusive content";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND)
                .withContent(content)
                .build();

        assertEquals(httpResponse.getContent(), "This is exclusive content");
    }
}
