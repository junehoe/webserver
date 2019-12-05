package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

public class HttpHandler implements Runnable {
    Socket clientSocket;

    public HttpHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        int statusCode = 200;
        String contentType = "text/html";
        String content;
        try {
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = SocketIO.createSocketReader(clientSocket);
            HttpRequest httpRequest = new HttpRequest(SocketIO.readFromInputStream(input));
            String path = httpRequest.getPath();
            if (path.equals("/")) {
                content = "<h1>Hello World!</h1>";
            } else {
                statusCode = 404;
                content = "<h1>Page not found</h1>";
            }
            output.println(HttpResponse.response(statusCode, contentType, content));
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
