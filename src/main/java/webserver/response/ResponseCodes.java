package webserver.response;

import java.util.HashMap;

public class ResponseCodes {
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    static final HashMap<Integer, String> RESPONSE_CODES;

    static {
        RESPONSE_CODES = new HashMap<>();
        RESPONSE_CODES.put(OK, "OK");
        RESPONSE_CODES.put(NOT_FOUND, "Not Found");
    }
}
