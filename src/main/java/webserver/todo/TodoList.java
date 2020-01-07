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
    private String directory;
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

    public void initializeHardCodedList(String pathString) {
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
        Arrays.sort(files, byCreationTime);
        for (File file : files) {
            add(new TodoItem("/todo/" + index, getFileName(file.getName()), file));
            index++;
        }
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    private String getFileName(String fullFileName) {
        return fullFileName.replaceFirst("[.][^.]+$", "");
    }

    private void createHardCodedPages(String path) {
        new File(path).mkdirs();
        for (HardCodedTodoItem item : HardCodedTodoItem.values()) {
            TodoPageCreator.createTodoPage(path, item.getTitle());
        }
    }

    private Comparator<File> byCreationTime = Comparator.comparing(file -> {
        try {
            return Files.readAttributes(Paths.get(file.toURI()), BasicFileAttributes.class).creationTime();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    });
}
