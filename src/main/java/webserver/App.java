package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        try {
            final ServerSocket server = createServerSocket(5000);
            System.out.println("Listening for connection on port 5000...");
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
