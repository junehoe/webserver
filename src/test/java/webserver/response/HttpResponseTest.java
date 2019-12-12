package webserver.response;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import org.junit.Test;
import static org.junit.Assert.*;

public class HttpResponseTest {
    private final String CRLF = "\r\n";
    private final String CONTENT_TYPE = "text/html";
    private final int OK = 200;
    private final int NOT_FOUND = 404;

    @Test
    public void buildsAndOutputsA200GetResponse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter output = new PrintWriter(outContent, true);
        String content = "<html><body><h1>Hey there!</h1></body></html>";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(OK)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = String.format(
                "HTTP/1.1 %s OK%sContent-Length: %s%sContent-Type: %s; charset=utf-8%s%s%s\n",
                OK, CRLF, content.length(), CRLF, CONTENT_TYPE, CRLF, CRLF, content
        );

        httpResponse.send(output);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void outputsA200HeadResponse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter output = new PrintWriter(outContent, true);
        String content = "<html><body><h1>Hey there!</h1></body></html>";
        HttpResponse httpResponse = new HttpResponse.Builder("HEAD")
                .withStatusCode(OK)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = String.format(
                "HTTP/1.1 %s OK%sContent-Length: %s%sContent-Type: %s; charset=utf-8%s%s\n",
                OK, CRLF, content.length(), CRLF, CONTENT_TYPE, CRLF, CRLF
        );

        httpResponse.send(output);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void buildsAndOutputsA404GetResponse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter output = new PrintWriter(outContent, true);
        String content = "<html><body><h1>Not Found!</h1></body></html>";
        HttpResponse httpResponse = new HttpResponse.Builder("GET")
                .withStatusCode(NOT_FOUND)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = String.format(
                "HTTP/1.1 %s Not Found%sContent-Length: %s%sContent-Type: %s; charset=utf-8%s%s%s\n",
                NOT_FOUND, CRLF, content.length(), CRLF, CONTENT_TYPE, CRLF, CRLF, content
        );

        httpResponse.send(output);

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void outputsA404HeadResponse() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter output = new PrintWriter(outContent, true);
        String content = "<html><body><h1>Not Found!</h1></body></html>";
        HttpResponse httpResponse = new HttpResponse.Builder("HEAD")
                .withStatusCode(NOT_FOUND)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
        String expected = String.format(
                "HTTP/1.1 %s Not Found%sContent-Length: %s%sContent-Type: %s; charset=utf-8%s%s\n",
                NOT_FOUND, CRLF, content.length(), CRLF, CONTENT_TYPE, CRLF, CRLF
        );

        httpResponse.send(output);

        assertEquals(expected, outContent.toString());
    }
}
