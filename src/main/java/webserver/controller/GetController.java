package webserver.controller;

import webserver.HtmlBuilder;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.pages.ServerPages.*;
import static webserver.response.HttpStatusCode.OK;
import static webserver.response.HttpStatusCode.NOT_FOUND;

public class GetController extends Controller {
    private TodoList todoList;

    public GetController(TodoList todoList) {
        this.todoList = todoList;
    }

    public Function<HttpRequest, HttpResponse> index = (request) ->
            createResponse(INDEX_TITLE, INDEX_BODY, OK);

    public Function<HttpRequest, HttpResponse> healthCheck = (request) ->
            createResponse(HEALTH_TITLE, HEALTH_BODY, OK);

    public Function<HttpRequest, HttpResponse> showTodoList = (request) ->
            createResponse(TODO_TITLE, TodoListBuilder.buildList(todoList.getTodoList()), OK);

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) ->
            createResponse(CREATE_TODO_ITEM_TITLE, CREATE_TODO_ITEM_BODY, OK);

    public Function<HttpRequest, HttpResponse> showTodoItem = (request) -> {
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getPath().equals(request.getPath())) {
                String title = item.getTitle();
                String body = HtmlBuilder.createTodoDetailHtml(title);
                return createResponse(title, body, OK);
            }
        }
        return createResponse(ERROR_TITLE, ERROR_BODY, NOT_FOUND);
    };

    private String htmlString(String title, String body) {
        try {
            HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
            return HtmlBuilder.createHtmlString(descriptors);
        } catch (IOException e) {
            e.printStackTrace();
            return "Not Found";
        }
    }

    private HttpResponse createResponse(String title, String body, HttpStatusCode httpStatusCode) {
        String content = htmlString(title, body);
        return new HttpResponse.Builder("GET")
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
