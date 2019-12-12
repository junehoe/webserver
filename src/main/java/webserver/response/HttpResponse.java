package webserver.response;

import webserver.socket.SocketIO;

import java.io.PrintWriter;

public class HttpResponse {
    private String method;
    private int statusCode;
    private String statusString;
    private int contentLength;
    private String contentType;
    private String content;

    private HttpResponse() {}

    public void send(PrintWriter output) {
        SocketIO.writeToOutputStream(output, responseString());
    }

    private String responseString() {
        final String CRLF = "\r\n";
        String response = "";
        response += "HTTP/1.1 " + this.statusCode + " " + this.statusString;
        response += CRLF;
        response += "Content-Length: " + this.contentLength;
        response += CRLF;
        response += "Content-Type: " + this.contentType + "; " + "charset=utf-8";
        response += CRLF + CRLF;
        if (this.method.equals("GET")) {
            response += this.content;
        }

        return response;
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
