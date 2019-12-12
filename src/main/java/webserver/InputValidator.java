package webserver;

public class InputValidator {
    public static boolean isValidPort(String portString) {
        try {
            Integer.parseInt(portString);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}
