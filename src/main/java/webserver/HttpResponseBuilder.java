package webserver;

public class HttpResponseBuilder {
    private int statusCode;
    private String statusString;
    private String contentType;
    private String content;

    public HttpResponseBuilder withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.statusString = ResponseCodes.RESPONSE_CODES.get(statusCode);

        return this;
    }

    public HttpResponseBuilder withContentType(String contentType) {
        this.contentType = contentType;

        return this;
    }

    public HttpResponseBuilder withContent(String content) {
        this.content = content;

        return this;
    }

    public String build() {
        final String CRLF = "\r\n";
        String response = "";
        response += "HTTP/1.1 " + this.statusCode + " " + this.statusString;
        response += CRLF;
        response += "Content-Type: " + this.contentType + "; ";
        response += "charset=utf-8" + CRLF + CRLF;
        response += this.content;

        return response;
    }
}
