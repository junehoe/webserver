package webserver;

public class Logger {
    public static void printConnectionMessage(int port) {
        System.out.println("Connected to port " + port + "...");
    }

    public static void printDefaultDirectoryMessage() {
        System.out.println("Using default directory");
    }

    public static void printCustomDirectoryMessage(String directory) {
        System.out.println("Serving files from " + directory);
    }
}
