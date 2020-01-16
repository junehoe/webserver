package webserver.validator;

public class InputValidator {
    private static final int LOWER_PORT_BOUND = 1024;
    private static final int UPPER_PORT_BOUND = 65535;

    public static boolean isValidPort(String portString) {
        int port;
        try {
            port = Integer.parseInt(portString);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return isValidPortNumber(port);
    }

    private static boolean isValidPortNumber(int port) {
        return (port >= LOWER_PORT_BOUND && port <= UPPER_PORT_BOUND);
    }

    public static boolean isCaseInsensitiveStringContained(String inputKeyword, String title) {
        return title.toLowerCase().contains(inputKeyword.toLowerCase());
    }
}
