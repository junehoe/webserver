package webserver.parser;

public class QueryParser {
    private static final int VALUE_INDEX = 1;

    public static String getFilterKeyword(String requestPath) {
        String keyword = "";
        try {
            String[] keyValue = requestPath.split("=");
            keyword = keyValue[VALUE_INDEX].replace("+", " ");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return keyword;
    }

    public static String getTitle(String body) {
        String[] title = body.split("=");
        return title[VALUE_INDEX].replace("+", " ");
    }
}
