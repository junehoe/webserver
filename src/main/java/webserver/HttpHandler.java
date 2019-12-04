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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader br = SocketIO.createSocketReader(clientSocket);
            HttpRequest httpRequest = new HttpRequest(SocketIO.readFromInputStream(br));
            String path = httpRequest.getPath();
            if (path.equals("/")) {
                content = "<h1>Hello World!</h1>";
            } else {
                statusCode = 404;
                content = "<h1>Page not found</h1>";
            }
            out.println(HttpResponse.response(statusCode, contentType, content));
            br.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
