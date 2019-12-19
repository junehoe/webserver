package webserver.response;

import java.util.HashMap;

public class ResponseCodes {
    public static final HashMap<Integer, String> RESPONSE_CODES;

    static {
        RESPONSE_CODES = new HashMap<>();
        RESPONSE_CODES.put(200, "OK");
        RESPONSE_CODES.put(404, "Not Found");
    }
}
