package webserver.controller;

import webserver.HtmlBuilder;
import webserver.InputValidator;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoPageCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.response.HttpStatusCode.BAD_REQUEST;
import static webserver.response.HttpStatusCode.SEE_OTHER;
import static webserver.response.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

public class PostController {
    private TodoList todoList;
    private final int TITLE_INDEX = 0;
    private final int TITLE_VALUE_INDEX = 1;
    private final String CONTENT_TYPE = "Content-Type";
    private final String NOT_FOUND = "Not Found";

    public PostController(TodoList todoList) {
        this.todoList = todoList;
    }

    public Function<HttpRequest, HttpResponse> createTodoItem = (request) -> {
        String contentType = getContentTypeFrom(request.getHeaders());
        if (InputValidator.isUnsupportedMediaType(contentType)) {
            return createResponse(UNSUPPORTED_MEDIA_TYPE);
        }

        if (InputValidator.isInvalidValue(request.getBody())) {
            return createResponse(BAD_REQUEST);
        }

        String title = getTitle(request.getBody());
        String indexedPath = "/todo/" + (todoList.getTodoList().size() + 1);
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

    private String getContentTypeFrom(HashMap<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            if (header.getKey().equals(CONTENT_TYPE)) {
                return header.getValue();
            }
        }
        return NOT_FOUND;
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
}
