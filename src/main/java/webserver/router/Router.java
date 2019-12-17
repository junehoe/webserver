package webserver.router;

import webserver.parser.HtmlParser;
import webserver.response.HttpResponse;
import webserver.request.HttpRequest;

import static webserver.pages.Page.ERROR_HTML;
import static webserver.pages.Page.TEXT_HTML;
import static webserver.response.ResponseCodes.NOT_FOUND;
import static webserver.response.ResponseCodes.OK;

import java.io.IOException;
import java.util.HashMap;

public class Router {
    private HashMap<String, String> routes;

    public Router() {
        this.routes = new HashMap<>();
    }

    public void addRoute(String path, String html) {
        this.routes.put(path, html);
    }

    public HttpResponse route(HttpRequest httpRequest) throws IOException {
        String htmlContent;
        if (routes.containsKey(httpRequest.getPath())) {
            return createResponse(httpRequest, routes.get(httpRequest.getPath()), OK);
        }
        htmlContent = HtmlParser.parseHtml(ERROR_HTML);
        return createResponse(httpRequest, htmlContent, NOT_FOUND);
    }

    private HttpResponse createResponse(HttpRequest httpRequest, String content, int statusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(statusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
