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
import webserver.controller.AppController;
import webserver.controller.TodoController;
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
    private AppController appController;
    private TodoController todoController;
    private Router router;
    private TodoList todoList;

    @Mock
    Socket clientSocket;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        todoList = new TodoList();
        router = new Router();
        appController = new AppController();
        todoController = new TodoController(todoList);

        router.get("/", appController.index);
        router.get("/health-check", appController.healthCheck);
        router.get("/todo", todoController.showTodoList);
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
