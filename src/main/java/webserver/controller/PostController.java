package webserver.controller;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoPageCreator;

import java.util.function.Function;

import static webserver.response.HttpStatusCode.SEE_OTHER;

public class PostController {
    private TodoList todoList;
    private final int TITLE_INDEX = 0;
    private final int TITLE_VALUE_INDEX = 1;

    public PostController(TodoList todoList) {
        this.todoList = todoList;
    }

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) -> {
        String indexedPath = "/todo/" + (todoList.getTodoList().size() + 1);
        String title = getTitle(request.getBody());
        todoList.add(new TodoItem(indexedPath,
                title,
                TodoPageCreator.createTodoPage(todoList.getDirectory(), title)));
        return createResponse(indexedPath, SEE_OTHER);
    };

    private String getTitle(String body) {
        String[] params = body.split("&");
        String[] title = params[TITLE_INDEX].split("=");
        return title[TITLE_VALUE_INDEX].replace("+", " ");
    }

    private HttpResponse createResponse(String location, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder("POST")
                .withStatusCode(httpStatusCode)
                .withLocation(location)
                .build();
    }
}
