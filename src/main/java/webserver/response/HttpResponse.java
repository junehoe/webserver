package webserver.response;

public class HttpResponse {
    private String method;
    private HttpStatusCode httpStatusCode;
    private int contentLength;
    private String contentType;
    private String content;
    private String location;

    private HttpResponse() {}

    public String getMethod() {
        return this.method;
    }

    public int getStatusCode() {
        return this.httpStatusCode.getStatusCode();
    }

    public String getStatusString() {
        return this.httpStatusCode.getStatusString();
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

    public String getLocation() {
        return this.location;
    }

    public static class Builder {
        private String method;
        private HttpStatusCode httpStatusCode;
        private int contentLength;
        private String contentType;
        private String content = "";
        private String location;

        public Builder(String method) {
            this.method = method;
        }

        public Builder withStatusCode(HttpStatusCode httpStatusCode) {
            this.httpStatusCode = httpStatusCode;

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

        public Builder withLocation(String location) {
            this.location = location;

            return this;
        }

        public HttpResponse build() {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.method = this.method;
            httpResponse.httpStatusCode = this.httpStatusCode;
            httpResponse.contentLength = this.contentLength;
            httpResponse.contentType = this.contentType;
            httpResponse.content = this.content;
            httpResponse.location = this.location;

            return httpResponse;
        }
    }
}
