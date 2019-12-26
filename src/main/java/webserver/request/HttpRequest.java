package webserver.request;

import java.util.HashMap;

public class HttpRequest {
    private String method;
    private String path;
    private String httpVersion;
    private HashMap<String, String> headers;
    private String body;

    private HttpRequest() {}

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private String method;
        private String path;
        private String httpVersion;
        private HashMap<String, String> headers;
        private String body;

        public Builder(String method) {
            this.method = method;
        }

        public HttpRequest.Builder withPath(String path) {
            this.path = path;

            return this;
        }

        public HttpRequest.Builder withHttpVersion(String httpVersion) {
            this.httpVersion = httpVersion;

            return this;
        }

        public HttpRequest.Builder withHeaders(HashMap<String, String> headers) {
            this.headers = headers;

            return this;
        }

        public HttpRequest.Builder withBody(String body) {
            this.body = body;

            return this;
        }

        public HttpRequest build() {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.method = this.method;
            httpRequest.path = this.path;
            httpRequest.httpVersion = this.httpVersion;
            httpRequest.headers = this.headers;
            httpRequest.body = this.body;

            return httpRequest;
        }
    }
}
