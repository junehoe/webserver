package webserver;

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
        if (isValidPortNumber(port)) {
            return true;
        }
        return false;
    }

    private static boolean isValidPortNumber(int port) {
        return (port >= LOWER_PORT_BOUND && port <= UPPER_PORT_BOUND);
    }
}
