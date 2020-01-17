package webserver.router;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.util.function.Function;

public class Route {
    private final String method;
    private final String path;
    private Function<HttpRequest, HttpResponse> callback;

    public Route(String method, String path, Function<HttpRequest, HttpResponse> callback) {
        this.method = method;
        this.path = path;
        this.callback = callback;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Function<HttpRequest, HttpResponse> getCallback() {
        return callback;
    }
}
