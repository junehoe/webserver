package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.database.DatabaseHandler;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;

import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static webserver.router.RoutePath.CREATE_TODO_ITEM_PATH;
import static webserver.router.RoutePath.TODO_PATH;

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
        todoList.add(new TodoItem(1, "Hello World"));
        todoController = new TodoController(todoList, databaseHandler);
    }

    @Test
    public void returnsGetResponseForTodoList() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(TODO_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoList.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsHeadResponseForTodoList() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(TODO_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoList.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsGetResponseForCreateTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsHeadResponseForCreateTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsPostResponseForCreateTodoPath() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getBody()).thenReturn("title=This+is+cool");
        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "POST");
        assertEquals(httpResponse.getStatusCode(), 303);
    }

    @Test
    public void createsA415UnsupportedMediaTypeResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/xml");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = todoController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
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

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 400);
    }

    @Test
    public void returnsGetResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }


    @Test
    public void returnsHeadResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns404ResponseForIncorrectTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/todo/2")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 404);
    }

    @Test
    public void returnsPostResponseForTodoItemToggle() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo/1/toggle")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.toggleTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "POST");
        assertEquals(httpResponse.getStatusCode(), 303);
    }

    @Test
    public void returnsBadRequestResponseForIncorrectTodoItemToggle() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo/2/toggle")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.toggleTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 400);
    }

    @Test
    public void returnsGetResponseForFilteredTodoList() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/todo?filter=Hi+There")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showFilteredTodoList.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsHeadResponseForFilteredTodoList() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo?filter=Hi+There")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = todoController.showFilteredTodoList.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }
}
