package webserver.pages;

public class ServerPages {
    public static final String INDEX_PATH = "/";
    public static final String INDEX_TITLE = "Todo List";
    public static final String INDEX_BODY = "<header><a href='/todo'>Click to view todo list</a></header>";

    public static final String HEALTH_PATH = "/health-check";
    public static final String HEALTH_TITLE = "Health Check";
    public static final String HEALTH_BODY = "<h1>Everything is good!</h1>";

    public static final String TODO_PATH = "/todo";
    public static final String TODO_TITLE = "Todo List";
}
