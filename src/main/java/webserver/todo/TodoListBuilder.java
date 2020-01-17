package webserver.todo;

import java.util.ArrayList;
import webserver.HtmlBuilder;

public class TodoListBuilder {
    public static String buildList(ArrayList<TodoItem> todoList) {
        StringBuilder listBuilder = new StringBuilder();
        listBuilder.append(HtmlBuilder.createHeader("Todo List"));
        for (TodoItem item : todoList) {
            listBuilder.append(HtmlBuilder.createSection(item.getPath(), item.getTitle()));
        }
        listBuilder.append("<br><br><footer><a href='/todo/new'>Create new todo item</a>");
        listBuilder.append("<br><br><form action='/todo' method='GET'>Keyword: <input type='text' name='filter'><br><input type='submit'></form></footer>");
        return listBuilder.toString();
    }

    public static String buildFilteredList(ArrayList<TodoItem> todoList) {
        StringBuilder listBuilder = new StringBuilder();
        listBuilder.append(HtmlBuilder.createHeader("Filtered Todo List"));
        for (TodoItem item : todoList) {
            listBuilder.append(HtmlBuilder.createSection(item.getPath(), item.getTitle()));
        }
        listBuilder.append("<br><br><footer><a href='/todo'>Go Back</a></footer>");
        return listBuilder.toString();
    }

    public static String buildItem(String title) {
        StringBuilder itemBuilder = new StringBuilder();
        itemBuilder.append(HtmlBuilder.createHeader(title));
        itemBuilder.append(HtmlBuilder.createForm("/", "POST",
                "Completed: " + HtmlBuilder.createInput("checkbox", "status")));
        itemBuilder.append(HtmlBuilder.createGoBackFooter());
        return itemBuilder.toString();
    }
}
