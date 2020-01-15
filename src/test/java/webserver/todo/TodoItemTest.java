package webserver.todo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoItemTest {
    private TodoItem todoItem;

    @Before
    public void initialize() {
        todoItem = new TodoItem("/todo/3", "Todo Item 3");
    }

    @Test
    public void getsThePath() {
        assertEquals(todoItem.getPath(), "/todo/3");
    }

    @Test
    public void getsTheTitle() {
        assertEquals(todoItem.getTitle(), "Todo Item 3");
    }
}
