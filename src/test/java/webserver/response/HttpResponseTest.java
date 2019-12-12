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
