package webserver;

import java.io.File;
import static webserver.TodoItems.*;

public class TodoListBuilder {
    public static String buildList() {
        String todoList = HtmlBuilder.createHeader("Todo List");
        todoList += HtmlBuilder.createSection(TODO_1_PATH, TODO_1_TITLE);
        todoList += HtmlBuilder.createSection(TODO_2_PATH, TODO_2_TITLE);
        todoList += HtmlBuilder.createSection(TODO_3_PATH, TODO_3_TITLE);
        todoList += HtmlBuilder.createSection(TODO_4_PATH, TODO_4_TITLE);
        todoList += HtmlBuilder.createSection(TODO_5_PATH, TODO_5_TITLE);
        return todoList;
    }

    public static String buildList(File[] customFiles) {
        String todoList = HtmlBuilder.createHeader("Todo List");
        for (int i = 0; i < customFiles.length; i++) {
            if (customFiles[i].isFile()) {
                String path = "/todo/" + (i + 1);
                String fileName = getFileName(customFiles[i].getName());
                todoList += HtmlBuilder.createSection(path, fileName);
            }
        }
        return todoList;
    }

    public static String buildItem(String title) {
        String htmlBody = "";
        htmlBody += "<header><h1>" + title + "</h1></header>";
        htmlBody += "<footer><a rel='collection' href='/todo'>Go Back</a></footer>";
        return htmlBody;
    }

    private static String getFileName(String fullFileName) {
        return fullFileName.replaceFirst("[.][^.]+$", "");
    }
}
