package webserver.router;

import webserver.controller.*;
import webserver.database.DatabaseHandler;
import webserver.todo.TodoList;

import java.io.File;

import static webserver.router.RoutePath.*;

public class RouteInitializer {
    private AppController appController;
    private CustomController customController;
    private TodoController todoController;

    public RouteInitializer(TodoList todoList, DatabaseHandler databaseHandler) {
        this.appController = new AppController();
        this.todoController = new TodoController(todoList, databaseHandler);
    }

    public RouteInitializer(TodoList todoList, DatabaseHandler databaseHandler, File[] customFiles) {
        this.appController = new AppController();
        this.customController = new CustomController(customFiles);
        this.todoController = new TodoController(todoList, databaseHandler);
    }

    public void createServerRoutes(Router router) {
        createAppRoutes(router);
        createTodoRoutes(router);
    }

    public void createCustomRoutes(Router router, File[] customFiles) {
        router.get(CUSTOM_PATH, customController.index);
        router.head(CUSTOM_PATH, customController.index);
        for (File customFile : customFiles) {
            router.get(createCustomPath(customFile.getName()), customController.showFile);
            router.head(createCustomPath(customFile.getName()), customController.showFile);
        }
    }

    private void createAppRoutes(Router router) {
        router.get(INDEX_PATH, appController.index);
        router.get(HEALTH_PATH, appController.healthCheck);

        router.head(INDEX_PATH, appController.index);
        router.head(HEALTH_PATH, appController.healthCheck);
    }

    private void createTodoRoutes(Router router) {
        router.get(CREATE_TODO_ITEM_PATH, todoController.createTodoItem);
        router.get(EDIT_TODO_ITEM_PATH, todoController.editTodoItem);
        router.get(FILTERED_TODO_PATH, todoController.showFilteredTodoList);
        router.get(TODO_ITEM_PATH, todoController.showTodoItem);
        router.get(TODO_PATH, todoController.showTodoList);

        router.head(CREATE_TODO_ITEM_PATH, todoController.createTodoItem);
        router.head(EDIT_TODO_ITEM_PATH, todoController.editTodoItem);
        router.head(FILTERED_TODO_PATH, todoController.showFilteredTodoList);
        router.head(TODO_ITEM_PATH, todoController.showTodoItem);
        router.head(TODO_PATH, todoController.showTodoList);

        router.post(TODO_PATH, todoController.createTodoItem);
        router.post(TOGGLE_PATH, todoController.toggleTodoItem);

        router.put(TODO_ITEM_PATH, todoController.editTodoItem);

        router.delete(TODO_ITEM_PATH, todoController.deleteTodoItem);
    }

    private String createCustomPath(String fileName) {
        return "/custom/" + fileName;
    }
}
