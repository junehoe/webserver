package webserver.router;

import webserver.HtmlBuilder;
import webserver.todo.TodoItem;
import webserver.todo.TodoListBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static webserver.pages.ServerPages.*;

public class RouteInitializer {
    public static void createServerRoutes(Router router) throws IOException {
        createRoute(router, INDEX_PATH, INDEX_TITLE, INDEX_BODY);
        createRoute(router, HEALTH_PATH, HEALTH_TITLE, HEALTH_BODY);
        createRoute(router, CREATE_TODO_ITEM_PATH, CREATE_TODO_ITEM_TITLE, CREATE_TODO_ITEM_BODY);
        router.addRoute(new Route("POST", CREATE_TODO_ITEM_PATH));
    }

    public static void createTodoListRoutes(Router router, ArrayList<TodoItem> todoList) throws IOException {
        createRoute(router, TODO_PATH, TODO_TITLE, TodoListBuilder.buildList(todoList));
        for (TodoItem item : todoList) {
            createRoute(router, item.getPath(), item.getTitle(), TodoListBuilder.buildItem(item.getTitle()));
        }
    }

    private static void createRoute(Router router, String path, String title, String body) throws IOException {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
        String htmlString = HtmlBuilder.createHtmlString(descriptors);
        addRoutes(router, path, htmlString);
    }

    private static void addRoutes(Router router, String path, String htmlString) {
        router.addRoute(new Route("GET", path, htmlString));
        router.addRoute(new Route("HEAD", path, htmlString));
    }
}
