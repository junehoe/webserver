package webserver;

import webserver.parser.CliParser;

import java.io.IOException;

public class App {
    private static final String PORT = "port";
    private static final String DIR = "dir";

    public static void main(String[] args) {
        int port = CliParser.getPort(System.getProperty(PORT));
        String directory = CliParser.getDirectory(System.getProperty(DIR));

        try {
            Server server = new Server(port, directory);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
