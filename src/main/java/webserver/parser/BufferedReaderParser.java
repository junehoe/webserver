package webserver.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import webserver.request.HttpRequest;

public class BufferedReaderParser {
    private final String SPACE = " ";
    private final int METHOD_INDEX = 0;
    private final int PATH_INDEX = 1;
    private final int HTTP_VERSION_INDEX = 2;
    private String method;
    private String path;
    private String httpVersion;
    private HashMap<String, String> headers = new HashMap<>();
    private String body;

    public HttpRequest parse(BufferedReader input) throws IOException {
        parseRequestLine(input);
        parseHeaders(input);
        parseBody(input);

        return new HttpRequest.Builder(method)
                .withPath(path)
                .withHttpVersion(httpVersion)
                .withHeaders(headers)
                .withBody(body)
                .build();
    }

    private void parseRequestLine(BufferedReader input) throws IOException {
        String line = input.readLine();
        String[] startLine = line.split(SPACE);

        method = startLine[METHOD_INDEX];
        path = startLine[PATH_INDEX];
        httpVersion = startLine[HTTP_VERSION_INDEX];
    }

    private void parseHeaders(BufferedReader input) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            if (line.equals("")) break;
            parseHeader(line);
        }
    }

    private void parseBody(BufferedReader input) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        String contentLength = headers.get("Content-Length");
        if (contentLength != null) {
            int length = Integer.parseInt(contentLength);
            for (int i = 0; i < length; i++) {
                requestBody.append((char) input.read());
            }
        }
        body = requestBody.toString();
    }

    private void parseHeader(String line) {
        String[] header = line.split(": ");
        if (header.length == 2) {
            headers.put(header[0], header[1]);
        }
    }
}
