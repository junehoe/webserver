package webserver.todo;

public enum HardCodedTodoItem {
    ITEM_1 ("Go skydiving"),
    ITEM_2 ("Buy groceries from Whole Foods"),
    ITEM_3 ("Implement PUT and DELETE in webserver"),
    ITEM_4 ("Write a blog post about GOOS"),
    ITEM_5 ("Exercise at the gym");

    private final String title;

    HardCodedTodoItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
