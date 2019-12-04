package webserver;

import org.junit.Test;
import static org.junit.Assert.*;

public class HttpResponseTest {
    private final String CRLF = "\r\n";

    @Test
    public void outputsA200Response() {
        int statusCode = 200;
        String contentType = "text/html";
        String content = "<html><body><h1>Hey there!</h1></body></html>";

        String expected = String.format(
                "HTTP/1.1 %s OK%sContent-Type: %s%s%s%s",
                statusCode, CRLF, contentType, CRLF, CRLF, content
        );

        assertEquals(HttpResponse.response(statusCode, contentType, content), expected);
    }

    @Test
    public void outputsA404Response() {
        int statusCode = 404;
        String contentType = "text/html";
        String content = "<h1>Not found</h1>";

        String expected = String.format(
                "HTTP/1.1 %s Not Found%sContent-Type: %s%s%s%s",
                statusCode, CRLF, contentType, CRLF, CRLF, content
        );

        assertEquals(HttpResponse.response(statusCode, contentType, content), expected);
    }
}
