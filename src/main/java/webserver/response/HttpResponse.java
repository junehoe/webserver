package webserver.response;

public class HttpResponse {
    private String method;
    private int statusCode;
    private String statusString;
    private int contentLength;
    private String contentType;
    private String content;

    private HttpResponse() {}

    public String getMethod() {
        return this.method;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusString() {
        return this.statusString;
    }

    public int getContentLength() {
        return this.contentLength;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getContent() {
        return this.content;
    }

    public static class Builder {
        private String method;
        private int statusCode;
        private String statusString;
        private int contentLength;
        private String contentType;
        private String content = "";

        public Builder(String method) {
            this.method = method;
        }

        public Builder withStatusCode(int statusCode) {
            this.statusCode = statusCode;
            this.statusString = ResponseCodes.RESPONSE_CODES.get(statusCode);

            return this;
        }

        public Builder withContentLength(int contentLength) {
            this.contentLength = contentLength;

            return this;
        }

        public Builder withContentType(String contentType) {
            this.contentType = contentType;

            return this;
        }

        public Builder withContent(String content) {
            this.content = content;

            return this;
        }

        public HttpResponse build() {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.method = this.method;
            httpResponse.statusCode = this.statusCode;
            httpResponse.statusString = this.statusString;
            httpResponse.contentLength = this.contentLength;
            httpResponse.contentType = this.contentType;
            httpResponse.content = this.content;

            return httpResponse;
        }
    }
}
