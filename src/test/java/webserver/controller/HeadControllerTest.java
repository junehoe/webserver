package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import static webserver.pages.ServerPages.*;
import static org.junit.Assert.assertEquals;

public class HeadControllerTest {
    private HeadController headController;
    private TodoList todoList;

    @Before
    public void initialize() {
        todoList = new TodoList();
        headController = new HeadController(todoList);
    }

    @Test
    public void returnsHeadResponseForIndexPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(INDEX_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = headController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsHeadResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = headController.healthCheck.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsHeadResponseForTodoPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(CREATE_TODO_ITEM_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = headController.showTodoList.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsHeadResponseForTodoItemPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/todo/1")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = headController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }
}
