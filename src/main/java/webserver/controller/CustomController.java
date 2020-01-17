package webserver.controller;

import webserver.MustacheAPI;
import webserver.parser.FileParser;
import webserver.parser.HttpRequestParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static webserver.pages.Page.CUSTOM_LISTING_PAGE;
import static webserver.pages.Page.ERROR_PAGE;
import static webserver.response.HttpStatusCode.*;

public class CustomController {
    private final String HTML = ".html";
    private final String JSON = ".json";
    private final String TXT = ".txt";

    private final String TEXT_HTML = "text/html";
    private final String APPLICATION_JSON = "application/json";
    private final String TEXT_PLAIN = "text/plain";

    private File[] customFiles;

    public CustomController(File[] customFiles) {
        this.customFiles = customFiles;
    }

    public Function<HttpRequest, HttpResponse> index = (request) -> {
        Map<String, Object> context = new HashMap<>();
        context.put("customFiles", customFiles);
        return createResponse(MustacheAPI.createHtml(context, CUSTOM_LISTING_PAGE), TEXT_HTML, request, OK);
    };

    public Function<HttpRequest, HttpResponse> showFile = (request) -> {
        String fileName = HttpRequestParser.getFileNameFromPath(request.getPath());
        String extension = FileParser.getFileExtension(fileName);
        for (File file : customFiles) {
            if (file.getName().equals(fileName)) {
                return respondWithAppropriateContentType(file, request, extension);
            }
        }
        return createResponse(MustacheAPI.createHtml(BAD_REQUEST, ERROR_PAGE), TEXT_HTML, request, BAD_REQUEST);
    };

    private static HttpResponse createResponse(String content,
                                               String contentType,
                                               HttpRequest httpRequest,
                                               HttpStatusCode httpStatusCode) {
        return new HttpResponse.Builder(httpRequest.getMethod())
                .withStatusCode(httpStatusCode)
                .withContentLength(content.length())
                .withContentType(contentType)
                .withContent(content)
                .build();
    }

    private HttpResponse respondWithAppropriateContentType(File file, HttpRequest httpRequest, String extension) {
        switch (extension) {
            case HTML:
                return createResponse(MustacheAPI.createHtml(null, file.getAbsolutePath()),
                        TEXT_HTML,
                        httpRequest,
                        OK);
            case JSON:
                return createResponse(MustacheAPI.createHtml(null, file.getAbsolutePath()),
                        APPLICATION_JSON,
                        httpRequest,
                        OK);
            case TXT:
                return createResponse(MustacheAPI.createHtml(null, file.getAbsolutePath()),
                        TEXT_PLAIN,
                        httpRequest,
                        OK);
        }
        return createResponse(MustacheAPI.createHtml(UNSUPPORTED_MEDIA_TYPE,
                ERROR_PAGE),
                TEXT_HTML,
                httpRequest,
                UNSUPPORTED_MEDIA_TYPE);
    }
}
