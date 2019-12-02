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

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started on port 5000");
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
}
