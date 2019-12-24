package webserver.parser;

import java.io.BufferedReader;
import java.io.IOException;
import webserver.request.HttpRequest;

public class HttpRequestParser {
    private final String SPACE = " ";
    private final int METHOD_INDEX = 0;
    private final int PATH_INDEX = 1;
    private final int HTTP_VERSION_INDEX = 2;
    private String method;
    private String path;
    private String httpVersion;

    public HttpRequest parse(BufferedReader input) throws IOException {
        parseRequestLine(input);

        return new HttpRequest(method, path, httpVersion);
    }

    private void parseRequestLine(BufferedReader input) throws IOException {
        String line = input.readLine();
        String[] startLine = line.split(SPACE);

        method = startLine[METHOD_INDEX];
        path = startLine[PATH_INDEX];
        httpVersion = startLine[HTTP_VERSION_INDEX];
    }
}
