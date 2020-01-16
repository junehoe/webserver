package webserver.parser;

import webserver.validator.InputValidator;

import java.io.File;

public class CliParser {
    private static final int DEFAULT_PORT = 5000;
    public static final String EMPTY_DIRECTORY = "";

    public static int getPort(String port) {
        if (InputValidator.isValidPort(port)) {
            return Integer.parseInt(port);
        } else if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        } else {
            return DEFAULT_PORT;
        }
    }

    public static String getDirectory(String directory) {
        if (directory == null) {
            return EMPTY_DIRECTORY;
        }
        File file = new File(directory);
        if (file.isDirectory()) {
            return directory;
        } else {
            return EMPTY_DIRECTORY;
        }
    }
}
