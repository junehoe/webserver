package webserver;

public class HttpResponse {
    public static String response(int statusCode, String contentType, String content) {
        return new HttpResponseBuilder()
                .withStatusCode(statusCode)
                .withContentType(contentType)
                .withContent(content)
                .build();
    }
}
