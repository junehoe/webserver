package webserver;

import webserver.request.HttpRequest;
import webserver.socket.SocketIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

public class HttpHandler implements Runnable {
    private Socket clientSocket;
    private Router router;

    public HttpHandler(Socket clientSocket, Router router) {
        this.clientSocket = clientSocket;
        this.router = router;
    }

    public void run() {
        try {
            PrintWriter output = SocketIO.createSocketWriter(clientSocket);
            BufferedReader input = SocketIO.createSocketReader(clientSocket);
            HttpRequest httpRequest = new HttpRequest(input);
            String httpResponse = router.route(httpRequest);

            output.println(httpResponse);

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
