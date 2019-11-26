package webserver;

import java.io.IOException;
import java.io.PrintWriter;
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
            final ServerSocket serverSocket = createServerSocket(5000);
            System.out.println("Listening for connection on port 5000...");
            final Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println("\r\n");
            out.println("<p>Hello World!</p>");

            while (true) {

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

    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }
}
