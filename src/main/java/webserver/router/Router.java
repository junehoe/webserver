package webserver.router;

import webserver.HtmlBuilder;
import webserver.parser.HtmlParser;
import webserver.response.HttpResponse;
import webserver.request.HttpRequest;
import webserver.response.HttpStatusCode;
import webserver.todo.TodoPageCreator;
import webserver.todo.TodoItem;
import webserver.todo.TodoList;
import webserver.todo.TodoListBuilder;

import static webserver.pages.Page.ERROR_HTML;
import static webserver.pages.Page.TEXT_HTML;
import static webserver.response.HttpStatusCode.CREATED;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;
import static webserver.response.HttpStatusCode.SEE_OTHER;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Router {
    private ArrayList<Route> routes;
    private String path;

    public Router() {
        this.routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpResponse route(HttpRequest httpRequest, TodoList todoList) throws IOException {
        ArrayList<TodoItem> todoItemList = todoList.getTodoList();
        if (httpRequest.getMethod().equals("POST")) {
            String title = getTitle(httpRequest.getBody());
            for (TodoItem item : todoItemList) {
                if (item.getTitle().equals(title)) {
                    return createPostResponse(httpRequest, SEE_OTHER, "/todo/error");
                }
            }
            String indexedPath = "/todo/" + (todoItemList.size() + 1);
            TodoPageCreator.createTodoPage(path, title);
            String body = TodoListBuilder.buildItem(title);
            HashMap<String, String> pageDescriptors = HtmlBuilder.createPageDescriptors(title, body);
            addRoute(new Route("GET", indexedPath, HtmlBuilder.createHtmlString(pageDescriptors)));
            addRoute(new Route("HEAD", indexedPath, HtmlBuilder.createHtmlString(pageDescriptors)));
            todoList.add(new TodoItem(indexedPath, title));
            updateRoute("/todo", TodoListBuilder.buildList(todoItemList));
            return createPostResponse(httpRequest, SEE_OTHER, indexedPath);
        }

        String htmlContent;
        for (Route route : routes) {
            if (route.getPath().equals(httpRequest.getPath())) return createResponse(httpRequest, route.getBody(), OK);
        }
        htmlContent = HtmlParser.parseHtml(ERROR_HTML, true);
        return createResponse(httpRequest, htmlContent, NOT_FOUND);
    }

    private String getTitle(String body) {
        String[] params = body.split("&");
        String[] title = params[0].split("=");
        return title[1].replace("+", " ");
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    private void updateRoute(String path, String newBody) {
        for (Route route : routes) {
            if (route.getPath().equals(path)) {
                route.setBody(newBody);
            }
        }
    }

    private HttpResponse createResponse(HttpRequest httpRequest, String content, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }

    private HttpResponse createPostResponse(HttpRequest httpRequest, HttpStatusCode httpStatusCode, String path) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withLocation(path)
                .build();
    }
}
