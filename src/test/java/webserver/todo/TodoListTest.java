package webserver.todo;

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
        TodoItem todoItem = new TodoItem(1, "Title 1");
        ArrayList<TodoItem> list = todoList.getTodoList();

        todoList.add(todoItem);

        assertEquals(list.get(0).getTitle(), "Title 1");
    }

    @Test
    public void setsAndGetsTheFilteredTodoList() {
        TodoItem item1 = new TodoItem(1, "Hello");
        TodoItem item2 = new TodoItem(2, "Testing");
        TodoItem item3 = new TodoItem(3, "The Best Title");
        TodoItem item4 = new TodoItem(4, "The Worst Title");
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

    @Test
    public void removesItemFromTodoList() {
        TodoItem goodItem = new TodoItem(1, "Good Item");
        TodoItem badItem = new TodoItem(2, "Bad Item");

        todoList.add(goodItem);
        todoList.add(badItem);

        todoList.removeTodoItem(badItem);

        assertEquals(todoList.getTodoList().size(), 1);
    }
}
