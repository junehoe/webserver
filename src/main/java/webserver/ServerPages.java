package webserver;

public class ServerPages {
    static final String INDEX_PATH = "/";
    static final String INDEX_TITLE = "Todo List";
    static final String INDEX_BODY = "<header><a href='/todo'>Click to view todo list</a></header>";

    static final String HEALTH_PATH = "/health-check";
    static final String HEALTH_TITLE = "Health Check";
    static final String HEALTH_BODY = "<h1>Everything is good!</h1>";

    static final String TODO_PATH = "/todo";
    static final String TODO_TITLE = "Todo List";
}
