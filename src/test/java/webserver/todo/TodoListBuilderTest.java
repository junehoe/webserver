package webserver.todo;

import org.junit.Test;
import org.mockito.Mock;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class TodoListBuilderTest {
    @Test
    public void createsTodoList() {
        TodoList todoList = new TodoList();
        TodoItem todoItem1 = new TodoItem(1, "Do a barrel roll");
        TodoItem todoItem2 = new TodoItem(2, "Buy groceries for the week");
        TodoItem todoItem3 = new TodoItem(3, "Pretend to be a tree for a day");
        TodoItem todoItem4 = new TodoItem(4, "Adopt a kitten");
        TodoItem todoItem5 = new TodoItem(5, "Create a todo list");

        todoList.add(todoItem1);
        todoList.add(todoItem2);
        todoList.add(todoItem3);
        todoList.add(todoItem4);
        todoList.add(todoItem5);

        StringBuilder expected = new StringBuilder();
        expected.append("<header><h1>Todo List</h1></header>");
        expected.append("<section><a rel='item' href='/todo/1'>Do a barrel roll</a></section>");
        expected.append("<section><a rel='item' href='/todo/2'>Buy groceries for the week</a></section>");
        expected.append("<section><a rel='item' href='/todo/3'>Pretend to be a tree for a day</a></section>");
        expected.append("<section><a rel='item' href='/todo/4'>Adopt a kitten</a></section>");
        expected.append("<section><a rel='item' href='/todo/5'>Create a todo list</a></section>");
        expected.append("<br><br><footer><a href='/todo/new'>Create new todo item</a>");
        expected.append("<br><br><form action='/todo' method='GET'>");
        expected.append("Keyword: <input type='text' name='filter'><br><input type='submit'></form></footer>");

        assertEquals(TodoListBuilder.buildList(todoList.getTodoList()), expected.toString());
    }

    @Test
    public void createsTodoListItem() {
        String title = "Hello World";
        String expected = "<header><h1>Hello World</h1></header>";
        expected += "<form action='/' method='POST'>";
        expected += "Completed: <input type='checkbox' name='status'>";
        expected += "</form>";
        expected += "<footer><a rel='collection' href='/todo'>Go Back</a></footer>";

        assertEquals(TodoListBuilder.buildItem(title), expected);
    }

    @Test
    public void createsFilteredTodoList() {
        TodoList todoList = new TodoList();
        TodoItem todoItem1 = new TodoItem(1, "Do a barrel roll");
        TodoItem todoItem2 = new TodoItem(2, "Buy groceries for the week");

        todoList.add(todoItem1);
        todoList.add(todoItem2);

        StringBuilder expected = new StringBuilder();
        expected.append("<header><h1>Filtered Todo List</h1></header>");
        expected.append("<section><a rel='item' href='/todo/1'>Do a barrel roll</a></section>");
        expected.append("<section><a rel='item' href='/todo/2'>Buy groceries for the week</a></section>");
        expected.append("<br><br><footer><a href='/todo'>Go Back</a></footer>");

        assertEquals(TodoListBuilder.buildFilteredList(todoList.getTodoList()), expected.toString());
    }
}
