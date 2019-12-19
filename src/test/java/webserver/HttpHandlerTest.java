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
import webserver.parser.HtmlParser;
import webserver.router.Router;

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
        router.addRoute("/", HtmlParser.parseHtml("/index.html", true));
        router.addRoute("/health-check", HtmlParser.parseHtml("/health-check.html", true));
        router.addRoute("/todo", HtmlParser.parseHtml("/todo-list.html", true));
        router.addRoute("/todo/1", HtmlParser.parseHtml("/todo-item-1.html", true));
        router.addRoute("/todo/2", HtmlParser.parseHtml("/todo-item-2.html", true));
        router.addRoute("/todo/3", HtmlParser.parseHtml("/todo-item-3.html", true));
        router.addRoute("/todo/4", HtmlParser.parseHtml("/todo-item-4.html", true));
        router.addRoute("/todo/5", HtmlParser.parseHtml("/todo-item-5.html", true));
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
        String htmlPath = "/index.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputs404Page() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        String htmlPath = "/error.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
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

    @Test
    public void testServerOutputsHealthCheckPage() throws IOException {
        String inputString = "GET /health-check HTTP/1.1\r\n";
        String htmlPath = "/health-check.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoListPage() throws IOException {
        String inputString = "GET /todo HTTP/1.1\r\n";
        String htmlPath = "/todo-list.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoItem1Page() throws IOException {
        String inputString = "GET /todo/1 HTTP/1.1\r\n";
        String htmlPath = "/todo-item-1.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoItem2Page() throws IOException {
        String inputString = "GET /todo/2 HTTP/1.1\r\n";
        String htmlPath = "/todo-item-2.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoItem3Page() throws IOException {
        String inputString = "GET /todo/3 HTTP/1.1\r\n";
        String htmlPath = "/todo-item-3.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoItem4Page() throws IOException {
        String inputString = "GET /todo/4 HTTP/1.1\r\n";
        String htmlPath = "/todo-item-4.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoItem5Page() throws IOException {
        String inputString = "GET /todo/5 HTTP/1.1\r\n";
        String htmlPath = "/todo-item-5.html";
        String htmlContent = HtmlParser.parseHtml(htmlPath, true);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    public String createExpectedResponse(String content) {
        String response = "";
        response += "HTTP/1.1 200 OK";
        response += CRLF;
        response += "Content-Length: " + content.length();
        response += CRLF;
        response += "Content-Type: text/html; charset=utf-8";
        response += CRLF + CRLF;
        response += content + "\n";
        return response;
    }
}
