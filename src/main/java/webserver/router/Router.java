package webserver.router;

import webserver.parser.HtmlParser;
import webserver.response.HttpResponse;
import webserver.request.HttpRequest;
import webserver.response.HttpStatusCode;

import static webserver.pages.Page.ERROR_HTML;
import static webserver.pages.Page.TEXT_HTML;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;

import java.io.IOException;
import java.util.ArrayList;

public class Router {
    private ArrayList<Route> routes;

    public Router() {
        this.routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public HttpResponse route(HttpRequest httpRequest) throws IOException {
        String htmlContent;
        for (Route route : routes) {
            if (route.getPath().equals(httpRequest.getPath())) return createResponse(httpRequest, route.getBody(), OK);
        }
        htmlContent = HtmlParser.parseHtml(ERROR_HTML, true);
        return createResponse(httpRequest, htmlContent, NOT_FOUND);
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    private HttpResponse createResponse(HttpRequest httpRequest, String content, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
