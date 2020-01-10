package webserver.controller;

import webserver.HtmlBuilder;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.pages.ServerPages.*;
import static webserver.pages.ServerPages.HEALTH_BODY;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;

public class AppController {
    public Function<HttpRequest, HttpResponse> index = (request) ->
            createResponse(htmlString(INDEX_TITLE, INDEX_BODY), request, OK);

    public Function<HttpRequest, HttpResponse> healthCheck = (request) ->
            createResponse(htmlString(HEALTH_TITLE, HEALTH_BODY), request, OK);

    public static Function<HttpRequest, HttpResponse> error = (request) ->
            createResponse(htmlString(ERROR_TITLE, ERROR_BODY), request, NOT_FOUND);

    private static String htmlString(String title, String body) {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
        return HtmlBuilder.createHtmlString(descriptors);
    }

    private static HttpResponse createResponse(String content, HttpRequest httpRequest, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
