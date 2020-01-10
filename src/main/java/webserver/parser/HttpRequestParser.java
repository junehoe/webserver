package webserver.parser;

import java.util.HashMap;
import java.util.Map;

import static webserver.response.HttpStatusCode.UNSUPPORTED_MEDIA_TYPE;

public class HttpRequestParser {
    private static final int TITLE_INDEX = 0;
    private static final int TITLE_VALUE_INDEX = 1;
    private static final String CONTENT_TYPE = "Content-Type";

    public static String getTitle(String body) {
        String[] params = body.split("&");
        String[] title = params[TITLE_INDEX].split("=");
        return title[TITLE_VALUE_INDEX].replace("+", " ");
    }

    public static String getContentTypeFrom(HashMap<String, String> headers) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            if (header.getKey().equals(CONTENT_TYPE)) {
                return header.getValue();
            }
        }
        return UNSUPPORTED_MEDIA_TYPE.getStatusString();
    }
}
