package webserver;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputValidatorTest {
    @Test
    public void returnsTrueIfInputPortIsAnInteger() {
        String inputPort = "1234";

        assertTrue(InputValidator.isValidPort(inputPort));
    }

    @Test
    public void returnsFalseIfInputPortIsNotAnInteger() {
        String inputPort = "1234Hello";

        assertFalse(InputValidator.isValidPort(inputPort));
    }

    @Test
    public void returnsFalseIfInputPortIsNull() {
        assertFalse(InputValidator.isValidPort(null));
    }
}
