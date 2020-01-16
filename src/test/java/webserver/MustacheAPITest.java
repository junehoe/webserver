package webserver;

import org.junit.Test;
import webserver.todo.TodoItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static webserver.pages.Page.TODO_ITEM_PAGE;

public class MustacheAPITest {
    @Test
    public void createsAnHtmlPageBasedOnContext() {
        TodoItem todoItem = new TodoItem(1, "Hello World");
        String testPath = "./public/test/mustache-api-test/creates-an-html-page-based-on-context.html";
        String expected = readHtml(testPath);

        assertEquals(expected, MustacheAPI.createHtml(todoItem, TODO_ITEM_PAGE));
    }

    private String readHtml(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str + "\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
