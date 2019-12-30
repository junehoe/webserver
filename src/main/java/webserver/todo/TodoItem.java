package webserver.todo;

public class TodoItem {
    private final String path;
    private final String title;

    public TodoItem(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }
}
