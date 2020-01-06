package webserver.response;

public enum HttpStatusCode {
    OK (200, "OK"),
    NOT_FOUND (404, "Not Found");

    private final int statusCode;
    private final String statusString;

    HttpStatusCode(int statusCode, String statusString) {
        this.statusCode = statusCode;
        this.statusString = statusString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusString() {
        return statusString;
    }
}
