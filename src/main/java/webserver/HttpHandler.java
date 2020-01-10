package webserver;

import webserver.parser.BufferedReaderParser;
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
    private BufferedReaderParser bufferedReaderParser;
    private Socket clientSocket;
    private Router router;
    private TodoList todoList;

    public HttpHandler(Socket clientSocket, Router router, TodoList todoList) {
        this.clientSocket = clientSocket;
        this.router = router;
        this.todoList = todoList;
        this.bufferedReaderParser = new BufferedReaderParser();
    }

    public void run() {
        try {
            PrintWriter output = SocketIO.createSocketWriter(clientSocket);
            BufferedReader input = SocketIO.createSocketReader(clientSocket);
            HttpRequest httpRequest = bufferedReaderParser.parse(input);
            HttpResponse httpResponse = router.route(httpRequest);
            HttpResponseSender.send(output, httpResponse);

            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
