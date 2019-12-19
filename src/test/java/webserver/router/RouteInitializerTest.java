package webserver.router;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.HtmlBuilder;
import webserver.todo.TodoListBuilder;

import static org.mockito.Mockito.*;
import static webserver.pages.ServerPages.*;
import static webserver.todo.TodoItems.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteInitializerTest {
    @Mock
    Router router;

    @Test
    public void createsServerRoutes() throws IOException {
        HashMap<String, String> indexDescriptors = HtmlBuilder.createPageDescriptors(INDEX_TITLE, INDEX_BODY);
        String indexHtmlString = HtmlBuilder.createHtmlString(indexDescriptors);
        HashMap<String, String> healthDescriptors = HtmlBuilder.createPageDescriptors(HEALTH_TITLE, HEALTH_BODY);
        String healthHtmlString = HtmlBuilder.createHtmlString(healthDescriptors);

        RouteInitializer.createServerRoutes(router);

        verify(router, times(1)).addRoute(INDEX_PATH, indexHtmlString);
        verify(router, times(1)).addRoute(HEALTH_PATH, healthHtmlString);
    }

    @Test
    public void createsTodoListRoute() throws IOException {
        HashMap<String, String> todoListDesc = HtmlBuilder.createPageDescriptors(
                TODO_TITLE, TodoListBuilder.buildList()
        );
        String htmlString = HtmlBuilder.createHtmlString(todoListDesc);

        RouteInitializer.createTodoListRoutes(router);

        verify(router, times(1)).addRoute(TODO_PATH, htmlString);
    }

    @Test
    public void createsTodoItemsRoute() throws IOException {
        HashMap<String, String> todo1Desc = HtmlBuilder.createPageDescriptors(
                TODO_1_TITLE, TodoListBuilder.buildItem(TODO_1_TITLE)
        );
        HashMap<String, String> todo2Desc = HtmlBuilder.createPageDescriptors(
                TODO_2_TITLE, TodoListBuilder.buildItem(TODO_2_TITLE)
        );
        HashMap<String, String> todo3Desc = HtmlBuilder.createPageDescriptors(
                TODO_3_TITLE, TodoListBuilder.buildItem(TODO_3_TITLE)
        );
        HashMap<String, String> todo4Desc = HtmlBuilder.createPageDescriptors(
                TODO_4_TITLE, TodoListBuilder.buildItem(TODO_4_TITLE)
        );
        HashMap<String, String> todo5Desc = HtmlBuilder.createPageDescriptors(
                TODO_5_TITLE, TodoListBuilder.buildItem(TODO_5_TITLE)
        );
        String todo1String = HtmlBuilder.createHtmlString(todo1Desc);
        String todo2String = HtmlBuilder.createHtmlString(todo2Desc);
        String todo3String = HtmlBuilder.createHtmlString(todo3Desc);
        String todo4String = HtmlBuilder.createHtmlString(todo4Desc);
        String todo5String = HtmlBuilder.createHtmlString(todo5Desc);

        RouteInitializer.createTodoListRoutes(router);

        verify(router, times(1)).addRoute(TODO_1_PATH, todo1String);
        verify(router, times(1)).addRoute(TODO_2_PATH, todo2String);
        verify(router, times(1)).addRoute(TODO_3_PATH, todo3String);
        verify(router, times(1)).addRoute(TODO_4_PATH, todo4String);
        verify(router, times(1)).addRoute(TODO_5_PATH, todo5String);
    }

    @Test
    public void createsCustomTodoItemsRoute() throws IOException {
        String directory = "public/test";
        String htmlString1 = "This is fake file 1.";
        String htmlString2 = "This is fake file 2.";
        String htmlString3 = "This is fake file 3.";

        RouteInitializer.createTodoListRoutes(router, directory);

        verify(router, times(1)).addRoute(TODO_1_PATH, htmlString1);
        verify(router, times(1)).addRoute(TODO_2_PATH, htmlString2);
        verify(router, times(1)).addRoute(TODO_3_PATH, htmlString3);
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
