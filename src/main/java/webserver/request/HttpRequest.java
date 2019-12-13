package webserver.request;

import webserver.socket.SocketIO;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private String methodHeader;
    private static final int METHOD_INDEX = 0;
    private static final int PATH_INDEX = 1;
    private static final String SPACE = "\\s+";

    public HttpRequest(BufferedReader input) {
        try {
            this.methodHeader = SocketIO.readFromInputStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return this.methodHeader.split(SPACE)[METHOD_INDEX];
    }

    public String getPath() {
        return this.methodHeader.split(SPACE)[PATH_INDEX];
    }
}
