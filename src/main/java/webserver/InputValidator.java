package webserver;

public class InputValidator {
    public static boolean isValidPort(String portString) {
        try {
            Integer.parseInt(portString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
