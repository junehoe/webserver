package webserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.controller.GetController;
import webserver.parser.HtmlParser;
import webserver.router.Route;
import webserver.router.Router;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;

import static webserver.pages.ServerPages.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpHandlerTest {
    private final String CRLF = "\r\n";
    private ByteArrayOutputStream outContent;
    private GetController getController;
    private Router router;
    private TodoList todoList;

    @Mock
    Socket clientSocket;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        todoList = new TodoList();
        router = new Router();
        getController = new GetController(todoList);

        router.get("/", getController.index);
        router.get("/health-check", getController.healthCheck);
        router.get("/todo", getController.showTodoList);
        router.get("/todo/1", getController.showTodoItem);
        router.get("/todo/2", getController.showTodoItem);
        router.get("/todo/3", getController.showTodoItem);
        router.get("/todo/4", getController.showTodoItem);
        router.get("/todo/5", getController.showTodoItem);
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
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(INDEX_TITLE, INDEX_BODY);
        String htmlContent = HtmlBuilder.createHtmlString(descriptors);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputs404Page() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(ERROR_TITLE, ERROR_BODY);
        String htmlContent = HtmlBuilder.createHtmlString(descriptors);
        String expected = "";
        expected += "HTTP/1.1 404 Not Found";
        expected += CRLF;
        expected += "Content-Length: " + htmlContent.length();
        expected += CRLF;
        expected += "Content-Type: text/html; charset=utf-8";
        expected += CRLF + CRLF;
        expected += htmlContent + "\n";

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsHealthCheckPage() throws IOException {
        String inputString = "GET /health-check HTTP/1.1\r\n";
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(HEALTH_TITLE, HEALTH_BODY);
        String htmlContent = HtmlBuilder.createHtmlString(descriptors);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testServerOutputsTodoListPage() throws IOException {
        String inputString = "GET /todo HTTP/1.1\r\n";
        String todoBody = TodoListBuilder.buildList(todoList.getTodoList());
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(TODO_TITLE, todoBody);
        String htmlContent = HtmlBuilder.createHtmlString(descriptors);
        String expected = createExpectedResponse(htmlContent);

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expected, outContent.toString());
    }

    //@Test
    //public void testServerOutputsTodoItem1Page() throws IOException {
    //    String inputString = "GET /todo/1 HTTP/1.1\r\n";
    //    String title = "Do a barrel roll";
    //    String body = HtmlBuilder.createTodoDetailHtml(title);
    //    HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
    //    String htmlContent = HtmlBuilder.createHtmlString(descriptors);
    //    String expected = createExpectedResponse(htmlContent);

    //    when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
    //    HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

    //    httpHandler.run();

    //    assertEquals(expected, outContent.toString());
    //}

    //@Test
    //public void testServerOutputsTodoItem2Page() throws IOException {
    //    String inputString = "GET /todo/2 HTTP/1.1\r\n";
    //    String htmlPath = "/todo-item-2.html";
    //    String htmlContent = HtmlParser.parseHtml(htmlPath, true);
    //    String expected = createExpectedResponse(htmlContent);

    //    when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
    //    HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

    //    httpHandler.run();

    //    assertEquals(expected, outContent.toString());
    //}

    //@Test
    //public void testServerOutputsTodoItem3Page() throws IOException {
    //    String inputString = "GET /todo/3 HTTP/1.1\r\n";
    //    String htmlPath = "/todo-item-3.html";
    //    String htmlContent = HtmlParser.parseHtml(htmlPath, true);
    //    String expected = createExpectedResponse(htmlContent);

    //    when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
    //    HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

    //    httpHandler.run();

    //    assertEquals(expected, outContent.toString());
    //}

    //@Test
    //public void testServerOutputsTodoItem4Page() throws IOException {
    //    String inputString = "GET /todo/4 HTTP/1.1\r\n";
    //    String htmlPath = "/todo-item-4.html";
    //    String htmlContent = HtmlParser.parseHtml(htmlPath, true);
    //    String expected = createExpectedResponse(htmlContent);

    //    when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
    //    HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

    //    httpHandler.run();

    //    assertEquals(expected, outContent.toString());
    //}

    //@Test
    //public void testServerOutputsTodoItem5Page() throws IOException {
    //    String inputString = "GET /todo/5 HTTP/1.1\r\n";
    //    String htmlPath = "/todo-item-5.html";
    //    String htmlContent = HtmlParser.parseHtml(htmlPath, true);
    //    String expected = createExpectedResponse(htmlContent);

    //    when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
    //    HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

    //    httpHandler.run();

    //    assertEquals(expected, outContent.toString());
    //}

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
