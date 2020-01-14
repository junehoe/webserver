package webserver.request;

public class HttpRequest {
    private final String method;
    private final String path;
    private final String httpVersion;

    public HttpRequest(String method, String path, String httpVersion) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
