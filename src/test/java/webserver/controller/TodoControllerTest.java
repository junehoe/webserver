package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.database.Database;
import webserver.database.DatabaseHandler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static webserver.pages.ServerPages.CREATE_TODO_ITEM_PATH;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {
    private TodoList todoList;
    private TodoController todoController;

    @Mock
    HttpRequest httpRequest;

    @Mock
    DatabaseHandler databaseHandler;

    @Before
    public void initialize() {
        todoList = new TodoList();
        todoController = new TodoController(todoList, databaseHandler);
    }

    @Test
    public void returnsGetResponseForTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsGetResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsHeadResponseForTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsHeadResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void createsAPostResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getBody()).thenReturn("title=This+is+cool");
        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "POST");
    }

    @Test
    public void createsA415UnsupportedMediaTypeResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/xml");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getStatusCode(), 415);
    }

    @Test
    public void createsA400BadRequestResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getHeaders()).thenReturn(headers);
        when(httpRequest.getBody()).thenReturn("title=this is so cool");

        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getStatusCode(), 400);
    }
}
