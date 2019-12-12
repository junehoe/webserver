package webserver;

import webserver.socket.SocketCreator;

import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;

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

    private static void createServerRoutes(Router router) {
        router.addRoute("/", "public/index.html");
        router.addRoute("/health-check", "public/health-check.html");
    }
}
