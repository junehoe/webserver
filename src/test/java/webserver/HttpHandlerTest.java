package webserver;

import java.io.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.controller.AppController;
import webserver.controller.TodoController;
import webserver.database.DatabaseHandler;
import webserver.router.Router;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;

import static webserver.pages.Page.*;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpHandlerTest {
    private ByteArrayOutputStream outContent;
    private AppController appController;
    private TodoController todoController;
    private Router router;
    private TodoList todoList;

    @Mock
    Socket clientSocket;

    @Mock
    DatabaseHandler databaseHandler;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
        todoList = new TodoList();
        router = new Router();
        appController = new AppController();
        todoController = new TodoController(todoList, databaseHandler);

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
        String content = MustacheAPI.createHtml(null, INDEX_PAGE);
        String header = "HTTP/1.1 200 OK\r\n";
        String contentLengthHeader = "Content-Length: " + content.length() + "\r\n";
        String contentTypeHeader = "Content-Type: text/html; charset=utf-8\r\n\r\n";

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(header);
        expectedBuilder.append(contentLengthHeader);
        expectedBuilder.append(contentTypeHeader);
        expectedBuilder.append(content);
        expectedBuilder.append("\n");

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expectedBuilder.toString(), outContent.toString());
    }

    @Test
    public void testServerOutputs404Page() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        String content = MustacheAPI.createHtml(NOT_FOUND, ERROR_PAGE);
        String header = "HTTP/1.1 404 Not Found\r\n";
        String contentLengthHeader = "Content-Length: " + content.length() + "\r\n";
        String contentTypeHeader = "Content-Type: text/html; charset=utf-8\r\n\r\n";

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(header);
        expectedBuilder.append(contentLengthHeader);
        expectedBuilder.append(contentTypeHeader);
        expectedBuilder.append(content);
        expectedBuilder.append("\n");

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expectedBuilder.toString(), outContent.toString());
    }

    @Test
    public void testServerOutputsHealthCheckPage() throws IOException {
        String inputString = "GET /health-check HTTP/1.1\r\n";
        String content = MustacheAPI.createHtml(null, HEALTH_PAGE);
        String header = "HTTP/1.1 200 OK\r\n";
        String contentLengthHeader = "Content-Length: " + content.length() + "\r\n";
        String contentTypeHeader = "Content-Type: text/html; charset=utf-8\r\n\r\n";

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(header);
        expectedBuilder.append(contentLengthHeader);
        expectedBuilder.append(contentTypeHeader);
        expectedBuilder.append(content);
        expectedBuilder.append("\n");

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expectedBuilder.toString(), outContent.toString());
    }

    @Test
    public void testServerOutputsTodoListPage() throws IOException {
        String inputString = "GET /todo HTTP/1.1\r\n";
        todoList.add(new TodoItem(1, "Hello World"));

        String content = readHtml("./public/test/http-handler-test/todo-list-page.html");
        String header = "HTTP/1.1 200 OK\r\n";
        String contentLengthHeader = "Content-Length: " + content.length() + "\r\n";
        String contentTypeHeader = "Content-Type: text/html; charset=utf-8\r\n\r\n";

        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(header);
        expectedBuilder.append(contentLengthHeader);
        expectedBuilder.append(contentTypeHeader);
        expectedBuilder.append(content);
        expectedBuilder.append("\n");

        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);

        httpHandler.run();

        assertEquals(expectedBuilder.toString(), outContent.toString());
    }

    private String readHtml(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str + "\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
