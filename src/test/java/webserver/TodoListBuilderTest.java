package webserver;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TodoListBuilderTest {
    @Test
    public void createsDefaultTodoList() {
        String expected = "<header><h1>Todo List</h1></header>";
        expected += "<section><a rel='item' href='/todo/1'>Do a barrel roll</a></section>";
        expected += "<section><a rel='item' href='/todo/2'>Buy groceries for the week</a></section>";
        expected += "<section><a rel='item' href='/todo/3'>Pretend to be a tree for a day</a></section>";
        expected += "<section><a rel='item' href='/todo/4'>Adopt a kitten</a></section>";
        expected += "<section><a rel='item' href='/todo/5'>Create a todo list</a></section>";

        assertEquals(TodoListBuilder.buildList(), expected);
    }

    @Test
    public void createsTodoListItem() {
        String title = "Hello World";
        String expected = "<header><h1>Hello World</h1></header>";
        expected += "<footer><a rel='collection' href='/todo'>Go Back</a></footer>";

        assertEquals(TodoListBuilder.buildItem(title), expected);
    }

    @Test
    public void createsCustomTodoList() {
        File folder = new File("/Users/briansung/Documents/projects/java/webserver/public/todo");
        File[] customFiles = folder.listFiles();
        String expected = "<header><h1>Todo List</h1></header>";
        expected += "<section><a rel='item' href='/todo/1'>feed-cat</a></section>";
        expected += "<section><a rel='item' href='/todo/2'>feed-dog</a></section>";
        expected += "<section><a rel='item' href='/todo/3'>feed-me</a></section>";
        expected += "<section><a rel='item' href='/todo/4'>do-a-cool-thing</a></section>";

        assertEquals(TodoListBuilder.buildList(customFiles), expected);
    }
}
