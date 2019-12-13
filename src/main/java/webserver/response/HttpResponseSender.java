package webserver.response;

import webserver.socket.SocketIO;

import java.io.PrintWriter;

public class HttpResponseSender {
    public static void send(PrintWriter output, HttpResponse httpResponse) {
        String response = HttpResponseFormatter.format(httpResponse);
        SocketIO.writeToOutputStream(output, response);
    }
}
