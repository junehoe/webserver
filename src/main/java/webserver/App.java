package webserver;

import webserver.socket.SocketCreator;

import static webserver.ServerPages.*;
import static webserver.TodoItems.*;

import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class App {
    private static final String BLANK = "";

    public static void main(String[] args) {
        String portArg = System.getProperty("p");
        String dirArg = System.getProperty("dir");

        try {
            int port = getPort(portArg);
            final ServerSocket serverSocket = SocketCreator.createServerSocket(port);
            Router router = new Router();
            createServerRoutes(router);
            if (dirArg.equals(BLANK)) {
                createTodoListRoutes(router);
            } else {
                createTodoListRoutes(router, dirArg);
            }
            System.out.println("Listening for connection on port " + port + "...");
            while (true) {
                Socket clientSocket = SocketCreator.createClientSocket(serverSocket);
                Thread thread = new Thread(new HttpHandler(clientSocket, router));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getPort(String port) {
        if (port != null && InputValidator.isValidPort(port)) {
            return Integer.parseInt(port);
        } else if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        } else {
            return 5000;
        }
    }

    private static void createServerRoutes(Router router) throws IOException {
        createRoute(router, INDEX_PATH, INDEX_TITLE, INDEX_BODY);
        createRoute(router, HEALTH_PATH, HEALTH_TITLE, HEALTH_BODY);
    }

    private static void createTodoListRoutes(Router router) throws IOException {
        createRoute(router, TODO_PATH, TODO_TITLE, TodoListBuilder.buildList());
        createRoute(router, TODO_1_PATH, TODO_1_TITLE, TodoListBuilder.buildItem(TODO_1_TITLE));
        createRoute(router, TODO_2_PATH, TODO_2_TITLE, TodoListBuilder.buildItem(TODO_2_TITLE));
        createRoute(router, TODO_3_PATH, TODO_3_TITLE, TodoListBuilder.buildItem(TODO_3_TITLE));
        createRoute(router, TODO_4_PATH, TODO_4_TITLE, TodoListBuilder.buildItem(TODO_4_TITLE));
        createRoute(router, TODO_5_PATH, TODO_5_TITLE, TodoListBuilder.buildItem(TODO_5_TITLE));
    }

    private static void createTodoListRoutes(Router router, String directory) throws IOException {
        File folder = new File(directory);
        String[] customFiles = folder.list();
        String todoList = TodoListBuilder.buildList(customFiles);
        createCustomRoutes(router, directory, customFiles);
        createRoute(router, TODO_PATH, TODO_TITLE, todoList);
    }

    private static void createCustomRoutes(Router router, String directory, String[] customFiles) throws IOException {
        for (int i = 0; i < customFiles.length; i++) {
            String path = "/todo/" + (i + 1);
            String fullPath = directory + "/" + customFiles[i];
            router.addRoute(path, HtmlBuilder.createHtmlString(fullPath));
        }
    }

    private static void createRoute(Router router, String path, String title, String body) throws IOException {
        HashMap<String, String> descriptors = HtmlBuilder.createPageHashMap(title, body);
        String htmlString = HtmlBuilder.createHtmlString(descriptors);
        router.addRoute(path, htmlString);
    }
}
