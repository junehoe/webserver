package webserver.controller;

import webserver.MustacheAPI;
import webserver.database.DatabaseHandler;
import webserver.parser.HttpRequestParser;
import webserver.parser.QueryParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.validator.HttpRequestValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static webserver.router.HttpVerb.GET;
import static webserver.router.HttpVerb.POST;
import static webserver.router.HttpVerb.PUT;
import static webserver.pages.Page.*;
import static webserver.response.HttpStatusCode.*;
import static webserver.response.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

public class TodoController {
    private DatabaseHandler databaseHandler;
    private TodoList todoList;

    public TodoController(TodoList todoList, DatabaseHandler databaseHandler) {
        this.todoList = todoList;
        this.databaseHandler = databaseHandler;
    }

    public Function<HttpRequest, HttpResponse> showTodoList = (request) -> {
        Map<String, Object> context = new HashMap<>();
        context.put("todos", todoList.getTodoList());
        return createResponse(MustacheAPI.createHtml(context, MAIN_LISTING_PAGE), request, OK);
    };

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) -> {
        if (request.getMethod().equals(POST)) {
            return postTodoItem(request);
        }
        Map<String, Object> context = new HashMap<>();
        context.put("pageName", "Create New Todo Item");
        context.put("pagePath", "/todo");
        return createResponse(MustacheAPI.createHtml(context, FORM_PAGE), request, OK);
    };

    public Function<HttpRequest, HttpResponse> showTodoItem = (request) -> {
        int id = Integer.parseInt(request.getPath().replace("/todo/", ""));
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getId() == id) {
                return createResponse(MustacheAPI.createHtml(item, TODO_ITEM_PAGE), request, OK);
            }
        }
        return createResponse(NOT_FOUND);
    };

    public Function<HttpRequest, HttpResponse> toggleTodoItem = (request) -> {
        int id = HttpRequestParser.getIdFromPath(request.getPath());
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
        Map<String, Object> context = new HashMap<>();
        context.put("filteredTodos", todoList.getFilteredTodoList());
        return createResponse(MustacheAPI.createHtml(context, FILTERED_TODO_LISTING_PAGE), request, OK);
    };

    public Function<HttpRequest, HttpResponse> editTodoItem = (request) -> {
        if (request.getMethod().equals(PUT)) {
            return putTodoItem(request);
        }
        int id = HttpRequestParser.getIdFromPath(request.getPath());
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getId() == id) {
                return createResponse(MustacheAPI.createHtml(item, EDIT_TODO_ITEM_PAGE), request, OK);
            }
        }
        return createResponse(NOT_FOUND);
    };

    private HttpResponse putTodoItem(HttpRequest httpRequest) {
        String contentType = HttpRequestParser.getContentTypeFrom(httpRequest.getHeaders());
        if (HttpRequestValidator.isUnsupportedMediaType(contentType)) {
            return createResponse(UNSUPPORTED_MEDIA_TYPE);
        }

        if (HttpRequestValidator.isInvalidValue(httpRequest.getBody())) {
            return createResponse(BAD_REQUEST);
        }

        String title = HttpRequestParser.getTitle(httpRequest.getBody()).replace("%20", " ");
        int id = HttpRequestParser.getIdFromPath(httpRequest.getPath());
        TodoItem updatedItem = updateTodoItem(id, title);
        return createResponse(MustacheAPI.createHtml(updatedItem, TODO_ITEM_PAGE),
                httpRequest,
                OK);
    }

    private TodoItem updateTodoItem(int id, String title) {
        databaseHandler.updateTodoItem(id, title);
        for (TodoItem item : todoList.getTodoList()) {
            if (item.getId() == id) {
                item.setTitle(title);
                return item;
            }
        }
        return null;
    }

    private HttpResponse createResponse(HttpStatusCode httpStatusCode) {
        String content = MustacheAPI.createHtml(httpStatusCode, ERROR_PAGE);
        return new HttpResponse.Builder(GET)
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
    }

    private HttpResponse createResponse(String location, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(POST)
                .withStatusCode(httpStatusCode)
                .withLocation(location)
                .build();
    }

    private HttpResponse createResponse(String content, HttpRequest httpRequest, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType("text/html")
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
        int id = databaseHandler.insertTodoItem(title);
        todoList.add(new TodoItem(id, title));
        return createResponse("/todo/" + id, SEE_OTHER);
    };
}
