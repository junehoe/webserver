package webserver.todo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import webserver.HtmlBuilder;

public class TodoPageCreator {
    public static File createTodoPage(String path, String title) {
        File file = new File(path + "/" + title + ".html");
        try {
            String body = TodoListBuilder.buildItem(title);
            HashMap<String, String> pageDescriptors = HtmlBuilder.createPageDescriptors(title, body);
            String htmlString = HtmlBuilder.createHtmlString(pageDescriptors);
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(htmlString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
