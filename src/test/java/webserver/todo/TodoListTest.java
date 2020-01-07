package webserver.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class TodoListTest {
    private TodoList todoList;

    @Mock
    File file;

    @Before
    public void initialize() {
        todoList = new TodoList();
    }

    @Test
    public void addsTodoItemToTheTodoList() {
        TodoItem todoItem = new TodoItem("/path", "Title", file);
        ArrayList<TodoItem> list = todoList.getTodoList();

        todoList.add(todoItem);

        assertEquals(list.get(0).getTitle(), "Title");
    }

    @Test
    public void initializesTheTodoListWithFiveHardCodedTodoItems() {
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

    @Test
    public void setsAndGetsTheDirectoryOfTheTodoList() {
        todoList.setDirectory("/this-is-a-fake-directory");

        assertEquals(todoList.getDirectory(), "/this-is-a-fake-directory");
    }
}
