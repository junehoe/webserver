package webserver;

import webserver.parser.HttpRequestParser;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpResponseSender;
import webserver.router.Router;
import webserver.socket.SocketIO;
import webserver.todo.TodoList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

public class HttpHandler implements Runnable {
    private HttpRequestParser httpRequestParser;
    private Socket clientSocket;
    private Router router;
    private TodoList todoList;

    public HttpHandler(Socket clientSocket, Router router, TodoList todoList) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.todoList = todoList;
        this.httpRequestParser = new HttpRequestParser();
    }

    public void run() {
        try {
            PrintWriter output = SocketIO.createSocketWriter(clientSocket);
            BufferedReader input = SocketIO.createSocketReader(clientSocket);
            HttpRequest httpRequest = httpRequestParser.parse(input);
            HttpResponse httpResponse = router.route(httpRequest);
            HttpResponseSender.send(output, httpResponse);

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
