package webserver.router;

import webserver.controller.GetController;
import webserver.controller.HeadController;
import webserver.controller.PostController;
import webserver.todo.TodoList;

import static webserver.pages.ServerPages.*;

public class RouteInitializer {
    private GetController getController;
    private HeadController headController;
    private PostController postController;

    public RouteInitializer(TodoList todoList) {
        this.getController = new GetController(todoList);
        this.headController = new HeadController(todoList);
        this.postController = new PostController(todoList);
    }

    public void createServerRoutes(Router router) {
        router.get(INDEX_PATH, getController.index);
        router.head(INDEX_PATH, headController.index);
        router.get(HEALTH_PATH, getController.healthCheck);
        router.head(HEALTH_PATH, headController.healthCheck);
        router.get(TODO_PATH, getController.showTodoList);
        router.head(TODO_PATH, headController.showTodoList);
        router.get(CREATE_TODO_ITEM_PATH, getController.createTodoItem);
        router.head(CREATE_TODO_ITEM_PATH, headController.createTodoItem);
        router.post(CREATE_TODO_ITEM_PATH, postController.createTodoItem);
        router.get(TODO_ITEM_PATH, getController.showTodoItem);
        router.head(TODO_ITEM_PATH, headController.showTodoItem);
    }
}
