package webserver;

import webserver.router.RouteInitializer;
import webserver.router.Router;
import webserver.socket.SocketCreator;
import webserver.todo.TodoList;
import static webserver.parser.CliParser.EMPTY_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            TodoList todoList = new TodoList();
            createRoutes(router, todoList);
            while (running) {
                Socket clientSocket = SocketCreator.createClientSocket(serverSocket);
                HttpHandler httpHandler = new HttpHandler(clientSocket, router, todoList);
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

    private void createRoutes(Router router, TodoList todoList) throws IOException {
        RouteInitializer routeInitializer = new RouteInitializer(todoList);
        routeInitializer.createServerRoutes(router);
        if (this.directory.equals(EMPTY_DIRECTORY)) {
            Logger.printDefaultDirectoryMessage();
            Path todoListPath = Paths.get("").toAbsolutePath();
            todoList.setDirectory(todoListPath.toString() + "/todo");
            todoList.initializeHardCodedList(todoListPath.toString());
        } else {
            Logger.printCustomDirectoryMessage(this.directory);
            File folder = new File(this.directory);
            File[] customFiles = folder.listFiles();
            todoList.setDirectory(this.directory);
            todoList.initializeCustomList(customFiles);
        }
    }
}
