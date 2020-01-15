package webserver.controller;

import webserver.HtmlBuilder;
import webserver.HttpRequestValidator;
import webserver.database.DatabaseHandler;
import webserver.parser.HttpRequestParser;
import webserver.parser.QueryParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;

import java.util.HashMap;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.pages.ServerPages.*;
import static webserver.response.HttpStatusCode.*;
import static webserver.response.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

public class TodoController {
    private DatabaseHandler databaseHandler;
    private TodoList todoList;

    public TodoController(TodoList todoList, DatabaseHandler databaseHandler) {
        this.todoList = todoList;
        this.databaseHandler = databaseHandler;
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
        int id = Integer.parseInt(request.getPath().replace("/todo/", ""));
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getId() == id) {
                HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(
                        item.getTitle(),
                        HtmlBuilder.createTodoDetailHtml(item.getTitle(), item.getId(), item.isComplete())
                );
                return createResponse(HtmlBuilder.createHtmlString(descriptors), request, OK);
            }
        }
        return createResponse(htmlString(ERROR_TITLE, ERROR_BODY), request, NOT_FOUND);
    };

    public Function<HttpRequest, HttpResponse> toggleTodoItem = (request) -> {
        String idToggle = request.getPath().replace("/todo/", "");
        int id = Integer.parseInt(idToggle.replace("/toggle", ""));
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getId() == id) {
                databaseHandler.toggleTodoItemStatus(item);
                return createResponse("/todo/" + id, SEE_OTHER);
            }
        }
        return createResponse(BAD_REQUEST);
    };

    public Function<HttpRequest, HttpResponse> showFilteredTodoList = (request) -> {
        String keyword = QueryParser.getFilterKeyword(request.getPath());
        todoList.setFilteredTodoList(keyword);
        return createResponse(htmlString(TODO_TITLE,
                TodoListBuilder.buildFilteredList(todoList.getFilteredTodoList())),
                request,
                OK);
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
            return new HttpResponse.Builder("GET")
                    .withStatusCode(UNSUPPORTED_MEDIA_TYPE)
                    .withContentLength(UNSUPPORTED_MEDIA_TYPE.getStatusString().length())
                    .withContentType(TEXT_HTML)
                    .withContent(UNSUPPORTED_MEDIA_TYPE.getStatusString())
                    .build();
        }

        if (HttpRequestValidator.isInvalidValue(httpRequest.getBody())) {
            return new HttpResponse.Builder("GET")
                    .withStatusCode(BAD_REQUEST)
                    .withContentLength(BAD_REQUEST.getStatusString().length())
                    .withContentType(TEXT_HTML)
                    .withContent(BAD_REQUEST.getStatusString())
                    .build();
        }

        String title = HttpRequestParser.getTitle(httpRequest.getBody());
        int id = databaseHandler.insertTodoItem(title);
        todoList.add(new TodoItem(id, title));
        return createResponse("/todo/" + id, SEE_OTHER);
    };
}
