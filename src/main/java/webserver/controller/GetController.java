package webserver.controller;

import webserver.HtmlBuilder;
import webserver.parser.HtmlParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;

import java.io.File;
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
            createResponse(htmlString(INDEX_TITLE, INDEX_BODY), OK);

    public Function<HttpRequest, HttpResponse> healthCheck = (request) ->
            createResponse(htmlString(HEALTH_TITLE, HEALTH_BODY), OK);

    public Function<HttpRequest, HttpResponse> showTodoList = (request) ->
            createResponse(htmlString(TODO_TITLE, TodoListBuilder.buildList(todoList.getTodoList())), OK);

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) ->
            createResponse(htmlString(CREATE_TODO_ITEM_TITLE, CREATE_TODO_ITEM_BODY), OK);

    public Function<HttpRequest, HttpResponse> showTodoItem = (request) -> {
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getPath().equals(request.getPath())) {
                File file = item.getFile();
                String content = HtmlParser.parseHtml(file.getAbsolutePath(), false);
                return createResponse(content, OK);
            }
        }
        return createResponse(htmlString(ERROR_TITLE, ERROR_BODY), NOT_FOUND);
    };

    private String htmlString(String title, String body) {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
        return HtmlBuilder.createHtmlString(descriptors);
    }

    private HttpResponse createResponse(String content, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder("GET")
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
