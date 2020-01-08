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

    @Test
    public void setsAndGetsTheFilteredTodoList() {
        TodoItem item1 = new TodoItem("/path1", "Hello", file);
        TodoItem item2 = new TodoItem("/path2", "Testing", file);
        TodoItem item3 = new TodoItem("/path3", "The Best Title", file);
        TodoItem item4 = new TodoItem("/path4", "The Worst Title", file);
        ArrayList<TodoItem> expectedList = new ArrayList<>();
        expectedList.add(item3);
        expectedList.add(item4);

        todoList.add(item1);
        todoList.add(item2);
        todoList.add(item3);
        todoList.add(item4);
        todoList.setFilteredTodoList("Title");

        assertEquals(todoList.getFilteredTodoList(), expectedList);
    }
}
