package webserver;

public class HttpResponse {
    private static final String CRLF = "\r\n";

    public static String response(int statusCode, String contentType, String content) {
        return String.format(
                "HTTP/1.1 %s %s%sContent-Type: %s%s%s%s",
                statusCode, ResponseCodes.RESPONSE_CODES.get(statusCode), CRLF, contentType, CRLF, CRLF, content
        );
    }
}
