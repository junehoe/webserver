package webserver;

import webserver.database.DatabaseHandler;
import webserver.database.Database;
import webserver.parser.CliParser;

import java.io.IOException;
import java.sql.Connection;

public class App {
    private static final String PORT = "port";
    private static final String DIR = "dir";
    public static final String URL = "jdbc:postgresql://localhost/todolist";

    public static void main(String[] args) {
        int port = CliParser.getPort(System.getProperty(PORT));
        String directory = CliParser.getDirectory(System.getProperty(DIR));

        try {
            Connection connection = Database.connect(URL);
            DatabaseHandler databaseHandler = new DatabaseHandler(connection);
            Server server = new Server(port, directory, databaseHandler);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
