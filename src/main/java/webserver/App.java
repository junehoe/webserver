package webserver;

import webserver.socket.SocketCreator;

import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        int port;

        if (args.length >= 1 && InputValidator.isValidPort(args[0])) {
            port = Integer.parseInt(args[0]);
        } else if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            port = 5000;
        }

        try {
            final ServerSocket serverSocket = SocketCreator.createServerSocket(port);
            Router router = new Router();
            createServerRoutes(router);
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

    private static void createServerRoutes(Router router) throws IOException {
        String todoList = "";
        todoList += "<header><h1>Todo List</h1></header>";
        todoList += "<section><a rel='item' href='/todo/1'>Do a barrel roll</a></section>";
        todoList += "<section><a rel='item' href='/todo/2'>Buy groceries for the week</a></section>";
        todoList += "<section><a rel='item' href='/todo/3'>Pretend to be a tree for a day</a></section>";
        todoList += "<section><a rel='item' href='/todo/4'>Adopt a kitten</a></section>";
        todoList += "<section><a rel='item' href='/todo/5'>Create a todo list</a></section>";
        createRoute(router, "/", "Todo List", "<header><a href='/todo'>Click to view todo list</a></header>");
        createRoute(router, "/health-check", "Health Check", "<h1>Everything is good!</h1>");
        createRoute(router, "/todo", "Todo List", todoList);
        createRoute(router, "/todo/1", "Do a barrel roll",
                "<header><h1>Do a barrel roll</h1></header><footer><a rel='collection' href='/todo'>Go Back</a></footer>");
        createRoute(router, "/todo/2", "Buy groceries for the week",
                "<header><h1>Buy groceries for the week</h1></header><footer><a rel='collection' href='/todo'>Go Back</a></footer>");
        createRoute(router, "/todo/3", "Pretend to be a tree for a day",
                "<header><h1>Pretend to be a tree for a day</h1></header><footer><a rel='collection' href='/todo'>Go Back</a></footer>");
        createRoute(router, "/todo/4", "Adopt a kitten",
                "<header><h1>Adopt a kitten</h1></header><footer><a rel='collection' href='/todo'>Go Back</a></footer>");
        createRoute(router, "/todo/5", "Create a todo list",
                "<header><h1>Create a todo list</h1></header><footer><a rel='collection' href='/todo'>Go Back</a></footer>");
    }

    private static void createRoute(Router router, String path, String title, String body) throws IOException {
        HashMap<String, String> descriptors = HtmlPageHandler.createPageHashMap(title, body);
        String htmlString = HtmlPageHandler.createHtmlString(descriptors);
        router.addRoute(path, htmlString);
    }
}
