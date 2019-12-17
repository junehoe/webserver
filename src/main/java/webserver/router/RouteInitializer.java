package webserver.router;

import webserver.HtmlBuilder;
import webserver.todo.TodoListBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import static webserver.pages.ServerPages.*;
import static webserver.todo.TodoItems.*;

public class RouteInitializer {
    public static void createServerRoutes(Router router) throws IOException {
        createRoute(router, INDEX_PATH, INDEX_TITLE, INDEX_BODY);
        createRoute(router, HEALTH_PATH, HEALTH_TITLE, HEALTH_BODY);
    }

    public static void createTodoListRoutes(Router router) throws IOException {
        createRoute(router, TODO_PATH, TODO_TITLE, TodoListBuilder.buildList());
        createRoute(router, TODO_1_PATH, TODO_1_TITLE, TodoListBuilder.buildItem(TODO_1_TITLE));
        createRoute(router, TODO_2_PATH, TODO_2_TITLE, TodoListBuilder.buildItem(TODO_2_TITLE));
        createRoute(router, TODO_3_PATH, TODO_3_TITLE, TodoListBuilder.buildItem(TODO_3_TITLE));
        createRoute(router, TODO_4_PATH, TODO_4_TITLE, TodoListBuilder.buildItem(TODO_4_TITLE));
        createRoute(router, TODO_5_PATH, TODO_5_TITLE, TodoListBuilder.buildItem(TODO_5_TITLE));
    }

    public static void createTodoListRoutes(Router router, String directory) throws IOException {
        File folder = new File(directory);
        String[] customFiles = folder.list();
        String todoList = TodoListBuilder.buildList(customFiles);
        createCustomRoutes(router, directory, customFiles);
        createRoute(router, TODO_PATH, TODO_TITLE, todoList);
    }

    private static void createCustomRoutes(Router router, String directory, String[] customFiles) throws IOException {
        Arrays.sort(customFiles);
        for (int i = 0; i < customFiles.length; i++) {
            String path = "/todo/" + (i + 1);
            String fullPath = directory + "/" + customFiles[i];
            router.addRoute(path, HtmlBuilder.createHtmlString(fullPath));
        }
    }

    private static void createRoute(Router router, String path, String title, String body) throws IOException {
        HashMap<String, String> descriptors = HtmlBuilder.createPageHashMap(title, body);
        String htmlString = HtmlBuilder.createHtmlString(descriptors);
        router.addRoute(path, htmlString);
    }
}
