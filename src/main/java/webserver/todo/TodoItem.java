package webserver.todo;

public class TodoItem {
    private int id;
    private String title;
    private boolean isComplete;

    public TodoItem(int id, String title) {
        this(id, title, false);
    }

    public TodoItem(int id, String title, boolean isComplete) {
        this.id = id;
        this.title = title;
        this.isComplete = isComplete;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setStatus(boolean status) {
        this.isComplete = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
