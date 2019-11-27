package webserver;

import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    private static int port = 5000;

    public static void main(String[] args) {
        int port;

        if (args.length >= 1 && isValidPort(args[0])) {
            port = Integer.parseInt(args[0]);
        } else if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            port = 5000;
        }

        try {
            final ServerSocket serverSocket = SocketCreator.createServerSocket(port);
            System.out.println("Listening for connection on port " + port + "...");
            while (true) {
                Socket clientSocket = SocketCreator.createClientSocket(serverSocket);
                Thread thread = new Thread(new HelloWorldPage(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidPort(String port) {
        try {
            Integer.parseInt(port);
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
        return true;
    }
}
