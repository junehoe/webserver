package webserver.todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import webserver.HtmlBuilder;

public class TodoPageCreator {
    public static void createTodoPage(String path, String title) throws IOException {
        String body = TodoListBuilder.buildItem(title);
        HashMap<String, String> pageDescriptors = HtmlBuilder.createPageDescriptors(title, body);
        String htmlString = HtmlBuilder.createHtmlString(pageDescriptors);
        PrintWriter writer = new PrintWriter( path + "/" + title + ".html", "UTF-8");
        writer.println(htmlString);
        writer.close();
    }
}
