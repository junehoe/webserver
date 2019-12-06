package webserver;

import java.io.IOException;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        int port;

        if (args.length >= 1 && isValidPort(args[0])) {
            port = Integer.parseInt(args[0]);
        } else if (System.getenv("PORT") != null) {
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            port = 5000;
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(new HelloWorldPage(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSystemOutput(String message) {
        System.out.println(message);
    }

    public String getSystemInput() {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        scan.close();
        return s;
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
