package webserver.todo;

import java.io.File;

public class TodoItem {
    private final String path;
    private final String title;
    private final File file;

    public TodoItem(String path, String title, File file) {
        this.path = path;
        this.title = title;
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public File getFile() {
        return file;
    }
}
