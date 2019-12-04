package webserver;

public class HttpRequest {
    private String method;
    private String path;

    public HttpRequest(String request) {
        this.method = parseMethod(request);
        this.path = parsePath(request);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    private String parseMethod(String request) {
        return request.split("\\s+")[0];
    }

    private String parsePath(String request) {
        return request.split("\\s+")[1];
    }
}
