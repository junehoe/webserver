package webserver.todo;

import webserver.validator.InputValidator;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<TodoItem> todoList;
    private ArrayList<TodoItem> filteredTodoList;

    public TodoList() {
        this.todoList = new ArrayList<>();
    }

    public void add(TodoItem todoItem) {
        todoList.add(todoItem);
    }

    public ArrayList<TodoItem> getTodoList() {
        return todoList;
    }

    public void setFilteredTodoList(String keyword) {
        filteredTodoList = new ArrayList<>();
        ArrayList<TodoItem> unfilteredTodoList = getTodoList();
        for (TodoItem item : unfilteredTodoList) {
            if (InputValidator.isCaseInsensitiveStringContained(keyword, item.getTitle())) {
                filteredTodoList.add(item);
            }
        }
    }

    public ArrayList<TodoItem> getFilteredTodoList() {
        return filteredTodoList;
    }
}
