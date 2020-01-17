package webserver.todo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemTest {
    private TodoItem todoItem;

    @Mock
    File file;

    @Before
    public void initialize() {
        todoItem = new TodoItem("/todo/3", "Todo Item 3", file);
    }

    @Test
    public void getsThePath() {
        assertEquals(todoItem.getPath(), "/todo/3");
    }

    @Test
    public void getsTheTitle() {
        assertEquals(todoItem.getTitle(), "Todo Item 3");
    }

    @Test
    public void getsTheFile() {
        when(file.getName()).thenReturn("Hello");

        assertEquals(todoItem.getFile().getName(), "Hello");
    }
}
