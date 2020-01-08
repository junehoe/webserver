package webserver.pages;

import webserver.HtmlBuilder;

public class ServerPages {
    public static final String INDEX_PATH = "/";
    public static final String INDEX_TITLE = "Todo List";
    public static final String INDEX_BODY = "<header><a href='/todo'>Click to view todo list</a></header>";

    public static final String ERROR_TITLE = "Error";
    public static final String ERROR_BODY = "404 Not Found";

    public static final String HEALTH_PATH = "/health-check";
    public static final String HEALTH_TITLE = "Health Check";
    public static final String HEALTH_BODY = "<h1>Everything is good!</h1>";

    public static final String TODO_PATH = "/todo";
    public static final String TODO_TITLE = "Todo List";

    public static final String TODO_ITEM_PATH = "/todo/[0-9]+";

    public static final String CREATE_TODO_ITEM_PATH = "/todo/new";
    public static final String CREATE_TODO_ITEM_TITLE = "Create Todo Item";
    public static final String CREATE_TODO_ITEM_BODY = todoFormBody();

    public static final String FILTERED_TODO_PATH = "/todo(.*?)";

    private static String todoFormBody() {
        StringBuilder todoFormBodyBuilder = new StringBuilder();
        todoFormBodyBuilder.append("Todo Item: " + HtmlBuilder.createInput("text", "todo-name"));
        todoFormBodyBuilder.append(HtmlBuilder.createBreak());
        todoFormBodyBuilder.append(HtmlBuilder.createInput("submit"));

        return HtmlBuilder.createForm("/todo", "POST", todoFormBodyBuilder.toString());
    }
}
