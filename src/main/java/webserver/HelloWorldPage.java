package webserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HelloWorldPage implements Runnable {
    private final Socket clientSocket;

    public HelloWorldPage(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        String httpResponse = "HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 12\n\nHello World!";
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
