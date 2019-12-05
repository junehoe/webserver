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
    private ByteArrayOutputStream outContent;

    @Mock
    Socket clientSocket;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
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
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket);
        String htmlContent = HtmlParser.parseHtml(htmlPath);
        String expected = new HttpResponseBuilder()
                .withStatusCode(200)
                .withContentType("text/html")
                .withContent(htmlContent)
                .build() + "\n";

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputs404Page() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        String htmlPath = "error.html";
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket);
        String htmlContent = HtmlParser.parseHtml(htmlPath);
        String expected = new HttpResponseBuilder()
                .withStatusCode(404)
                .withContentType("text/html")
                .withContent(htmlContent)
                .build() + "\n";

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }
}
