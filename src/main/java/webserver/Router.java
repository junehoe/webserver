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

    public HttpResponse route(HttpRequest httpRequest) throws IOException {
        String htmlContent;
        if (routes.containsKey(httpRequest.getPath())) {
            htmlContent = HtmlParser.parseHtml(routes.get(httpRequest.getPath()));
            return new HttpResponse.Builder(httpRequest.getMethod())
                    .withStatusCode(OK)
                    .withContentLength(htmlContent.length())
                    .withContentType("text/html")
                    .withContent(htmlContent)
                    .build();
        }
        htmlContent = HtmlParser.parseHtml("public/error.html");
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(NOT_FOUND)
                .withContentLength(htmlContent.length())
                .withContentType("text/html")
                .withContent(htmlContent)
                .build();
    }
}
