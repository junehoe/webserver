package webserver.router;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.todo.TodoList;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RouteInitializerTest {
    private Router router;
    private TodoList todoList;

    @Before
    public void initialize() {
        router = new Router();
        todoList = new TodoList();
    }

    @Test
    public void createsServerRoutes() throws IOException {
        RouteInitializer.createServerRoutes(router);

        assertEquals(7, router.getRoutes().size());
    }

    @Test
    public void createsTodoListRoute() throws IOException {
        todoList.initializeHardCodedList("./public/test");
        RouteInitializer.createTodoListRoutes(router, todoList.getTodoList());

        assertEquals(12, router.getRoutes().size());
    }

    @Test
    public void createsCustomTodoItemsRoute() throws IOException {
        String directory = "public/test/fake-files";
        File folder = new File(directory);
        File[] customFiles = folder.listFiles();
        todoList.initializeCustomList(customFiles);

        RouteInitializer.createTodoListRoutes(router, todoList.getTodoList());

        assertEquals(8, router.getRoutes().size());
    }

    @Test
    public void throwsNullPointerExceptionIfDirectoryIsInvalid() {
        try {
            String directory = "obviously/fake/path";
            File folder = new File(directory);
            File[] customFiles = folder.listFiles();
            todoList.initializeCustomList(customFiles);

            RouteInitializer.createTodoListRoutes(router, todoList.getTodoList());
        } catch (IOException | NullPointerException e) {
            System.out.println("NullPointerException was thrown");
        }
    }
}
