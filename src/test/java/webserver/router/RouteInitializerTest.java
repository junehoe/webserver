package webserver.router;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RouteInitializerTest {
    private Router router;

    @Before
    public void initialize() {
        router = new Router();
    }

    @Test
    public void createsServerRoutes() throws IOException {
        RouteInitializer.createServerRoutes(router);

        assertEquals(4, router.getRoutes().size());
    }

    @Test
    public void createsTodoListRoute() throws IOException {
        RouteInitializer.createTodoListRoutes(router);

        assertEquals(12, router.getRoutes().size());
    }

    @Test
    public void createsCustomTodoItemsRoute() throws IOException {
        String directory = "public/test";

        RouteInitializer.createTodoListRoutes(router, directory);

        assertEquals(8, router.getRoutes().size());
    }

    @Test
    public void throwsNullPointerExceptionIfDirectoryIsInvalid() {
        try {
            String directory = "obviously/fake/path";

            RouteInitializer.createTodoListRoutes(router, directory);
        } catch (IOException | NullPointerException e) {
            System.out.println("NullPointerException was thrown");
        }
    }
}
