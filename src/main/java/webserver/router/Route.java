package webserver.router;

public class Route {
    private final String method;
    private final String path;
    private final String body;

    public Route(String method, String path, String body) {
        this.method = method;
        this.path = path;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }
}
