package webserver.controller;

import webserver.HtmlBuilder;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

import static webserver.pages.Page.TEXT_HTML;
import static webserver.pages.ServerPages.ERROR_TITLE;
import static webserver.pages.ServerPages.ERROR_BODY;
import static webserver.response.HttpStatusCode.NOT_FOUND;

public class AppController extends Controller {
    public static Function<HttpRequest, HttpResponse> error = (request) ->
            createResponse(ERROR_TITLE, ERROR_BODY, NOT_FOUND);

    private static String htmlString(String title, String body) {
        HashMap<String, String> descriptors = HtmlBuilder.createPageDescriptors(title, body);
        return HtmlBuilder.createHtmlString(descriptors);
    }

    private static HttpResponse createResponse(String title, String body, HttpStatusCode httpStatusCode) {
        String content = htmlString(title, body);
        return new HttpResponse.Builder("GET")
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(TEXT_HTML)
                .withContent(content)
                .build();
    }
}
