package webserver;

import org.junit.Test;
import static org.junit.Assert.*;

public class HttpResponseBuilderTest {
    @Test
    public void buildsAResponseString() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        final String CRLF = "\r\n";
        int statusCode = 200;
        String contentType = "text/html";
        String content = "This is fake content";
        String expected = String.format(
                "HTTP/1.1 %s OK%sContent-Type: %s; charset=utf-8%s%s%s",
                statusCode, CRLF, contentType, CRLF, CRLF, content
        );

        httpResponseBuilder.withStatusCode(statusCode);
        httpResponseBuilder.withContentType(contentType);
        httpResponseBuilder.withContent(content);

        assertEquals(httpResponseBuilder.build(), expected);
    }
}
