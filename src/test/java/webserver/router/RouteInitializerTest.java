package webserver.router;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.todo.TodoList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RouteInitializerTest {
    private RouteInitializer routeInitializer;
    private Router router;
    private TodoList todoList;

    @Before
    public void initialize() {
        router = new Router();
        todoList = new TodoList();
        routeInitializer = new RouteInitializer(todoList);
    }

    @Test
    public void createsServerRoutes() {
        routeInitializer.createServerRoutes(router);

        assertEquals(13, router.getRoutes().size());
    }
}
