package webserver;

import webserver.router.RouteInitializer;
import webserver.router.Router;
import webserver.socket.SocketCreator;
import static webserver.parser.CliParser.EMPTY_DIRECTORY;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private int port;
    private String directory;
    private boolean running;

    public Server(int port, String directory) throws IOException {
        this.port = port;
        this.directory = directory;
        this.serverSocket = SocketCreator.createServerSocket(port);
    }

    @Override
    public void run() {
        try {
            Router router = new Router();
            createRoutes(router);
            while (running) {
                Socket clientSocket = SocketCreator.createClientSocket(serverSocket);
                HttpHandler httpHandler = new HttpHandler(clientSocket, router);
                new Thread(httpHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.running = true;
        Logger.printConnectionMessage(this.port);
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }

    public boolean isRunning() {
        return this.running;
    }

    private void createRoutes(Router router) throws IOException {
        RouteInitializer.createServerRoutes(router);
        if (this.directory.equals(EMPTY_DIRECTORY)) {
            Logger.printDefaultDirectoryMessage();
            RouteInitializer.createTodoListRoutes(router);
        } else {
            Logger.printCustomDirectoryMessage(this.directory);
            RouteInitializer.createTodoListRoutes(router, this.directory);
        }
    }
}
