package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import static org.junit.Assert.assertEquals;
import static webserver.pages.ServerPages.*;

public class GetControllerTest {
    private GetController getController;
    private TodoList todoList;

    @Before
    public void initialize() {
        todoList = new TodoList();
        getController = new GetController(todoList);
    }

    @Test
    public void returnsGetResponseForIndexPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(INDEX_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = getController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsGetResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = getController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsGetResponseForTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = getController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsGetResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = getController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }
}
