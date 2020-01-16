package webserver.todo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TodoItemTest {
    private TodoItem todoItem;

    @Before
    public void initialize() {
        todoItem = new TodoItem(1, "Todo Item 1");
    }

    @Test
    public void getsTheId() {
        assertEquals(todoItem.getId(), 1);
    }

    @Test
    public void getsTheTitle() {
        assertEquals(todoItem.getTitle(), "Todo Item 1");
    }

    @Test
    public void getsTheStatus() {
        assertFalse(todoItem.isComplete());
    }

    @Test
    public void setsTheStatus() {
        todoItem.setStatus(true);

        assertTrue(todoItem.isComplete());
    }

    @Test
    public void todoItemCanBeInitializedAsComplete() {
        TodoItem anotherTodoItem = new TodoItem(4, "Hello World", true);

        assertEquals(anotherTodoItem.getId(), 4);
        assertEquals(anotherTodoItem.getTitle(), "Hello World");
        assertTrue(anotherTodoItem.isComplete());
    }

    @Test
    public void setsTheTitle() {
        todoItem.setTitle("This is a new title");

        assertEquals(todoItem.getTitle(), "This is a new title");
    }
}
