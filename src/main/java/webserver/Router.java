package webserver;

import webserver.response.HttpResponse;
import webserver.request.HttpRequest;

import java.io.IOException;
import java.util.HashMap;

public class Router {
    private HashMap<String, String> routes;
    private static final int OK = 200;
    private static final int NOT_FOUND = 404;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void addRoute(String path, String html) {
        this.routes.put(path, html);
    }

    public String route(HttpRequest httpRequest) throws IOException {
        if (routes.containsKey(httpRequest.getPath())) {
            return HttpResponse.response(
                    httpRequest.getMethod(),
                    OK,
                    "text/html",
                    HtmlParser.parseHtml(routes.get(httpRequest.getPath()))
            );
        }
        return HttpResponse.response(
                httpRequest.getMethod(),
                NOT_FOUND,
                "text/html",
                HtmlParser.parseHtml("error.html")
        );
    }
}
