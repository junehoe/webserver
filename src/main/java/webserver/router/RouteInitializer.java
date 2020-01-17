package webserver.router;

import webserver.controller.*;
import webserver.todo.TodoList;

import static webserver.pages.ServerPages.*;

public class RouteInitializer {
    private AppController appController;
    private TodoController todoController;

    public RouteInitializer(TodoList todoList) {
        this.appController = new AppController();
        this.todoController = new TodoController(todoList);
    }

    public void createServerRoutes(Router router) {
        createAppRoutes(router);
        createTodoRoutes(router);
    }

    private void createAppRoutes(Router router) {
        router.get(INDEX_PATH, appController.index);
        router.head(INDEX_PATH, appController.index);
        router.get(HEALTH_PATH, appController.healthCheck);
        router.head(HEALTH_PATH, appController.healthCheck);
    }

    private void createTodoRoutes(Router router) {
        router.get(TODO_PATH, todoController.showTodoList);
        router.head(TODO_PATH, todoController.showTodoList);
        router.post(TODO_PATH, todoController.createTodoItem);
        router.get(CREATE_TODO_ITEM_PATH, todoController.createTodoItem);
        router.head(CREATE_TODO_ITEM_PATH, todoController.createTodoItem);
        router.get(TODO_ITEM_PATH, todoController.showTodoItem);
        router.head(TODO_ITEM_PATH, todoController.showTodoItem);
    }
}
