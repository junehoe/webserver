package webserver.controller;

import webserver.HtmlBuilder;
import webserver.HttpRequestValidator;
import webserver.InputValidator;
import webserver.parser.HttpRequestParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;
import webserver.todo.TodoPageCreator;

import java.util.HashMap;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.pages.ServerPages.*;
import static webserver.response.HttpStatusCode.*;
import static webserver.response.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

public class TodoController {
    private TodoList todoList;

    public TodoController(TodoList todoList) {
        this.todoList = todoList;
    }

    public Function<HttpRequest, HttpResponse> showTodoList = (request) ->
            createResponse(htmlString(TODO_TITLE, TodoListBuilder.buildList(todoList.getTodoList())), request, OK);

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) -> {
        if (request.getMethod().equals("POST")) {
            return postTodoItem(request);
        }
        return createResponse(htmlString(CREATE_TODO_ITEM_TITLE, CREATE_TODO_ITEM_BODY), request, OK);
    };

    public Function<HttpRequest, HttpResponse> showTodoItem = (request) -> {
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getPath().equals(request.getPath())) {
                String title = item.getTitle();
                String body = HtmlBuilder.createTodoDetailHtml(title);
                return createResponse(htmlString(title, body), request, OK);
            }
        }
        return createResponse(htmlString(ERROR_TITLE, ERROR_BODY), request, NOT_FOUND);
    };

    private String htmlString(String title, String body) {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
        return HtmlBuilder.createHtmlString(descriptors);
    }

    private HttpResponse createResponse(HttpStatusCode httpStatusCode) {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(
                httpStatusCode.getStatusString(),
                httpStatusCode.getStatusCode() + " " + httpStatusCode.getStatusString()
        );
        String content = HtmlBuilder.createHtmlString(descriptors);
        return new HttpResponse.Builder("GET")
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }

    private HttpResponse createResponse(String location, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder("POST")
                .withStatusCode(httpStatusCode)
                .withLocation(location)
                .build();
    }

    private HttpResponse createResponse(String content, HttpRequest httpRequest, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }

    private HttpResponse postTodoItem(HttpRequest httpRequest) {
        String contentType = HttpRequestParser.getContentTypeFrom(httpRequest.getHeaders());
        if (HttpRequestValidator.isUnsupportedMediaType(contentType)) {
            return createResponse(UNSUPPORTED_MEDIA_TYPE);
        }

        if (HttpRequestValidator.isInvalidValue(httpRequest.getBody())) {
            return createResponse(BAD_REQUEST);
        }

        String title = HttpRequestParser.getTitle(httpRequest.getBody());
        String indexedPath = "/todo/" + (todoList.getTodoList().size() + 1);
        todoList.add(new TodoItem(indexedPath,
                title,
                TodoPageCreator.createTodoPage(todoList.getDirectory(), title)));
        return createResponse(indexedPath, SEE_OTHER);
    };
}
