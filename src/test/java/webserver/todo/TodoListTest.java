package webserver.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoListTest {
    private TodoList todoList;

    @Before
    public void initialize() {
        todoList = new TodoList();
    }

    @Test
    public void addsTodoItemToTheTodoList() {
        TodoItem todoItem = new TodoItem("/path", "Title");

        todoList.add(todoItem);

        assertEquals(todoList.getTodoList().size(), 1);
    }

    @Test
    public void initializesTheTodoListWithFiveHardCodedTodoItems() throws IOException {
        todoList.initializeHardCodedList("public/test/hardcoded-test");

        ArrayList<TodoItem> list = todoList.getTodoList();

        assertEquals(list.size(), 5);
    }

    @Test
    public void initializesTheTodoListWithTodoItemsFromSpecifiedDirectory() {
        File folder = new File("public/test/fake-files");
        File[] customFiles = folder.listFiles();

        todoList.initializeCustomList(customFiles);

        ArrayList<TodoItem> list = todoList.getTodoList();

        assertEquals(list.size(), 3);
    }
}
