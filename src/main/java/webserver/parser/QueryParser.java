package webserver.parser;

public class QueryParser {
    private static final int VALUE_INDEX = 1;

    public static String getFilterKeyword(String requestPath) {
        String[] words = requestPath.split("=");
        return words[VALUE_INDEX];
    }

    public static String getTitle(String body) {
        String[] title = body.split("=");
        return title[VALUE_INDEX].replace("+", " ");
    }
}
