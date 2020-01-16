package webserver.router;

public class RoutePath {
    public static final String INDEX_PATH = "/";
    public static final String HEALTH_PATH = "/health-check";
    public static final String TODO_PATH = "/todo";
    public static final String TODO_ITEM_PATH = "/todo/[A-Za-z0-9]+";
    public static final String CREATE_TODO_ITEM_PATH = "/todo/new";
    public static final String FILTERED_TODO_PATH = "/todo\\?filter=(.*?)";
    public static final String TOGGLE_PATH = "/todo/[0-9]+/toggle";
    public static final String EDIT_TODO_ITEM_PATH = "/todo/[0-9]+/edit";
    public static final String CUSTOM_PATH = "/custom";
}
