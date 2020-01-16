package webserver.response;

import static webserver.router.HttpVerb.GET;
import static webserver.router.HttpVerb.HEAD;
import static webserver.router.HttpVerb.POST;
import static webserver.router.HttpVerb.PUT;

public class HttpResponseFormatter {
    private static final String CRLF = "\r\n";
    private static final String SEMICOLON_SEPARATOR = "; ";

    public static String format(HttpResponse res) {
        switch (res.getMethod()) {
            case GET:
            case PUT:
                return formatGet(res);
            case HEAD:
                return formatHead(res);
            case POST:
                return formatPost(res);
        }
        return "Something went wrong";
    }

    private static String formatGet(HttpResponse res) {
        StringBuilder getBuilder = new StringBuilder();
        getBuilder.append(initialHeaderLine(res.getStatusCode(), res.getStatusString()));
        getBuilder.append(CRLF);
        getBuilder.append(contentLengthHeader(res.getContentLength()));
        getBuilder.append(CRLF);
        getBuilder.append(contentTypeHeader(res.getContentType()));
        getBuilder.append(SEMICOLON_SEPARATOR);
        getBuilder.append(charsetHeader("utf-8"));
        getBuilder.append(CRLF);
        getBuilder.append(CRLF);
        getBuilder.append(contentBody(res.getContent()));
        return getBuilder.toString();
    }

    private static String formatHead(HttpResponse res) {
        StringBuilder headBuilder = new StringBuilder();
        headBuilder.append(initialHeaderLine(res.getStatusCode(), res.getStatusString()));
        headBuilder.append(CRLF);
        headBuilder.append(contentLengthHeader(res.getContentLength()));
        headBuilder.append(CRLF);
        headBuilder.append(contentTypeHeader(res.getContentType()));
        headBuilder.append(SEMICOLON_SEPARATOR);
        headBuilder.append(charsetHeader("utf-8"));
        headBuilder.append(CRLF);
        headBuilder.append(CRLF);
        return headBuilder.toString();
    }

    private static String formatPost(HttpResponse res) {
        StringBuilder postBuilder = new StringBuilder();
        postBuilder.append(initialHeaderLine(res.getStatusCode(), res.getStatusString()));
        postBuilder.append(CRLF);
        postBuilder.append(locationHeader(res.getLocation()));
        postBuilder.append(CRLF);
        return postBuilder.toString();
    }

    private static String initialHeaderLine(int statusCode, String statusString) {
        return String.format("HTTP/1.1 %d %s", statusCode, statusString);
    }

    private static String locationHeader(String location) {
        return String.format("Location: %s", location);
    }

    private static String contentLengthHeader(int contentLength) {
        return String.format("Content-Length: %d", contentLength);
    }

    private static String contentTypeHeader(String contentType) {
        return String.format("Content-Type: %s", contentType);
    }

    private static String charsetHeader(String value) {
        return String.format("charset=%s", value);
    }

    private static String contentBody(String body) {
        return body;
    }
}
