package webserver.todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TodoList {
    private ArrayList<TodoItem> todoList;

    public TodoList() {
        this.todoList = new ArrayList<>();
    }

    public void add(TodoItem todoItem) {
        todoList.add(todoItem);
    }

    public ArrayList<TodoItem> getTodoList() {
        return todoList;
    }

    public void initializeHardCodedList(String pathString) throws IOException {
        Path path = Paths.get(pathString, "/todo");
        if (!Files.exists(path)) {
            createHardCodedPages(path.toString());
        }
        File folder = new File(path.toString());
        File[] files = folder.listFiles();
        initializeList(files);
    }

    public void initializeCustomList(File[] customFiles) {
        initializeList(customFiles);
    }

    private void initializeList(File[] files) {
        int index = 1;
        Arrays.sort(files, comparator);
        for (File file : files) {
            add(new TodoItem("/todo/" + index, getFileName(file.getName())));
            index++;
        }
    }

    private String getFileName(String fullFileName) {
        return fullFileName.replaceFirst("[.][^.]+$", "");
    }

    private void createHardCodedPages(String path) throws IOException {
        new File(path).mkdirs();
        TodoPageCreator.createTodoPage(path, "Do a barrel roll");
        TodoPageCreator.createTodoPage(path, "Buy groceries for the week");
        TodoPageCreator.createTodoPage(path, "Pretend to be a tree for a day");
        TodoPageCreator.createTodoPage(path, "Adopt a kitten");
        TodoPageCreator.createTodoPage(path, "Create a todo list");
    }

    private Comparator<File> comparator = Comparator.comparing(file -> {
        try {
            return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).creationTime();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    });
}
