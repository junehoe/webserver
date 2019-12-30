package webserver.response;

public class HttpResponseFormatter {
    public static String format(HttpResponse res) {
        final String CRLF = "\r\n";
        String response = "";
        response += "HTTP/1.1 " + res.getStatusCode() + " " + res.getStatusString();
        response += CRLF;
        if (res.getMethod().equals("POST")) {
            response += "Location: " + res.getLocation();
        } else {
            response += "Content-Length: " + res.getContentLength();
            response += CRLF;
            response += "Content-Type: " + res.getContentType() + "; " + "charset=utf-8";
            response += CRLF + CRLF;
            if (res.getMethod().equals("GET")) {
                response += res.getContent();
            }
        }

        return response;
    }
}
