package webserver.response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static webserver.response.HttpStatusCode.CREATED;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;

public class HttpResponseFormatterTest {
    @Test
    public void returnsTheGetResponseString() {
        String content = "CoNtEnT!";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(OK)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = "HTTP/1.1 200 OK\r\nContent-Length: 8\r\n";
        expected += "Content-Type: text/html; charset=utf-8\r\n\r\n";
        expected += content;

        assertEquals(HttpResponseFormatter.format(httpResponse), expected);
    }

    @Test
    public void returnsTheHeadResponseString() {
        String content = "CoNtEnT!";
        HttpResponse httpResponse = new HttpResponse.Builder("HEAD")
                .withStatusCode(NOT_FOUND)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = "HTTP/1.1 404 Not Found\r\nContent-Length: 8\r\n";
        expected += "Content-Type: text/html; charset=utf-8\r\n\r\n";

        assertEquals(HttpResponseFormatter.format(httpResponse), expected);
    }

    @Test
    public void returnsThePostResponseString() {
        HttpResponse httpResponse = new HttpResponse.Builder("POST")
                .withStatusCode(CREATED)
                .withLocation("/")
                .build();
        String expected = "HTTP/1.1 201 Created\r\nLocation: /\r\n";

        assertEquals(HttpResponseFormatter.format(httpResponse), expected);
    }

    @Test
    public void returnsThePutResponseString() {
        String content = "Test content";
        HttpResponse httpResponse = new HttpResponse.Builder("PUT")
                .withStatusCode(OK)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = "HTTP/1.1 200 OK\r\nContent-Length: 12\r\n";
        expected += "Content-Type: text/html; charset=utf-8\r\n\r\n";
        expected += content;

        assertEquals(HttpResponseFormatter.format(httpResponse), expected);
    }
}
