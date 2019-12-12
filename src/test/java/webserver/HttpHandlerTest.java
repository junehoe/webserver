package webserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpHandlerTest {
    private final String CRLF = "\r\n";
    private ByteArrayOutputStream outContent;
    private Router router;

    @Mock
    Socket clientSocket;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        router = new Router();
        router.addRoute("/", "index.html");
    }

    @Before
    public void runBefore() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServerOutputsIndexPage() throws IOException {
        String inputString = "GET / HTTP/1.1\r\n";
        String htmlPath = "index.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath);
        String expected = "";
        expected += "HTTP/1.1 200 OK";
        expected += CRLF;
        expected += "Content-Length: " + htmlContent.length();
        expected += CRLF;
        expected += "Content-Type: text/html; charset=utf-8";
        expected += CRLF + CRLF;
        expected += htmlContent + "\n";

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputs404Page() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        String htmlPath = "error.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath);
        String expected = "";
        expected += "HTTP/1.1 404 Not Found";
        expected += CRLF;
        expected += "Content-Length: " + htmlContent.length();
        expected += CRLF;
        expected += "Content-Type: text/html; charset=utf-8";
        expected += CRLF + CRLF;
        expected += htmlContent + "\n";

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }
}
