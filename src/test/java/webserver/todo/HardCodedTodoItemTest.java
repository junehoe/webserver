package webserver.todo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HardCodedTodoItemTest {
    @Test
    public void returnsTheHardCodedTodoItem1Name() {
        assertEquals(HardCodedTodoItem.ITEM_1.getTitle(), "Go skydiving");
    }

    @Test
    public void returnsTheHardCodedTodoItem2Name() {
        assertEquals(HardCodedTodoItem.ITEM_2.getTitle(), "Buy groceries from Whole Foods");
    }

    @Test
    public void returnsTheHardCodedTodoItem3Name() {
        assertEquals(HardCodedTodoItem.ITEM_3.getTitle(), "Implement PUT and DELETE in webserver");
    }

    @Test
    public void returnsTheHardCodedTodoItem4Name() {
        assertEquals(HardCodedTodoItem.ITEM_4.getTitle(), "Write a blog post about GOOS");
    }

    @Test
    public void returnsTheHardCodedTodoItem5Name() {
        assertEquals(HardCodedTodoItem.ITEM_5.getTitle(), "Exercise at the gym");
    }
}
