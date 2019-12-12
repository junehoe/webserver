package webserver.response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpResponseFormatterTest {
    @Test
    public void returnsTheGetResponseString() {
        String content = "CoNtEnT!";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(200)
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
                .withStatusCode(404)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = "HTTP/1.1 404 Not Found\r\nContent-Length: 8\r\n";
        expected += "Content-Type: text/html; charset=utf-8\r\n\r\n";

        assertEquals(HttpResponseFormatter.format(httpResponse), expected);
    }
}
