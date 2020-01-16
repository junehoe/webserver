package webserver.controller;

import webserver.MustacheAPI;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;

import java.util.function.Function;

import static webserver.pages.Page.ERROR_PAGE;
import static webserver.pages.Page.HEALTH_PAGE;
import static webserver.pages.Page.INDEX_PAGE;
import static webserver.response.HttpStatusCode.NOT_FOUND;
import static webserver.response.HttpStatusCode.OK;

public class AppController {
    public Function<HttpRequest, HttpResponse> index = (request) ->
            createResponse(MustacheAPI.createHtml(null, INDEX_PAGE), request, OK);


    public Function<HttpRequest, HttpResponse> healthCheck = (request) ->
            createResponse(MustacheAPI.createHtml(null, HEALTH_PAGE), request, OK);

    public static Function<HttpRequest, HttpResponse> error = (request) ->
            createResponse(MustacheAPI.createHtml(NOT_FOUND, ERROR_PAGE), request, NOT_FOUND);

    private static HttpResponse createResponse(String content, HttpRequest httpRequest, HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType("text/html")
                .withContent(content)
                .build();
    }
}
