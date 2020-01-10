package webserver.router;

import webserver.controller.AppController;
import webserver.controller.TodoController;
import webserver.database.DatabaseHandler;
import webserver.request.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouterTest {
    private AppController appController;
    private TodoController todoController;
    private Router router;
    private TodoList todoList;

    @Mock
    private HttpRequest httpRequest;

    @Mock
    DatabaseHandler databaseHandler;

    @Before
    public void initialize() {
        router = new Router();
        todoList = new TodoList();
        appController = new AppController();
        todoController = new TodoController(todoList, databaseHandler);
    }

    @Test
    public void addsGetRoute() {
        ArrayList<Route> routes = router.getRoutes();
        router.get("/", appController.index);

        assertEquals(routes.get(0).getMethod(), "GET");
    }

    @Test
    public void addsHEADRoute() {
        ArrayList<Route> routes = router.getRoutes();
        router.head("/", appController.index);

        assertEquals(routes.get(0).getMethod(), "HEAD");
    }

    @Test
    public void addsPostRoute() {
        ArrayList<Route> routes = router.getRoutes();
        router.post("/", todoController.createTodoItem);

        assertEquals(routes.get(0).getMethod(), "POST");
    }

    @Test
    public void return200ResponseObjectIfRouteExists() {
        String path = "/";

        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn("/");
        router.get(path, appController.index);

        HttpResponse httpResponse = router.route(httpRequest);

        assertEquals(200, httpResponse.getStatusCode());
    }

    @Test
    public void returns404ResponseObjectIfNotInRoute() {
        String path = "/";

        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn("/fake-path");
        router.get(path, appController.error);

        HttpResponse httpResponse = router.route(httpRequest);

        assertEquals(404, httpResponse.getStatusCode());
    }

    @Test
    public void getsTheRoutes() {
        Route route1 = new Route("GET", "/route1", todoController.showTodoItem);
        Route route2 = new Route("HEAD", "/route2", todoController.showTodoItem);

        router.addRoute(route1);
        router.addRoute(route2);

        assertEquals(2, router.getRoutes().size());
    }

    @Test
    public void returns303PostResponseObjectForPost() {
        String path = "/todo/new";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getMethod()).thenReturn("POST");
        when(httpRequest.getHeaders()).thenReturn(headers);
        when(httpRequest.getPath()).thenReturn("/todo/new");
        when(httpRequest.getBody()).thenReturn("todo-name=Hello");
        router.post(path, todoController.createTodoItem);

        HttpResponse httpResponse = router.route(httpRequest);

        assertEquals(303, httpResponse.getStatusCode());
    }
}
