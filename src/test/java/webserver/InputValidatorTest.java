package webserver;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputValidatorTest {
    @Test
    public void returnsTrueIfInputPortIsAnIntegerAndBetweenPortBoundaries() {
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

    @Test
    public void returnsFalseIfInputIsAnIntegerButNotBetweenPortBoundaries() {
        String inputPort = "1000";

        assertFalse(InputValidator.isValidPort(inputPort));
    }

    @Test
    public void returnsTrueIfContentTypeIsNotSupported() {
        String contentType = "fake content type";

        assertTrue(InputValidator.isUnsupportedMediaType(contentType));
    }

    @Test
    public void returnsFalseIfContentTypeIsNotSupported() {
        String contentType = "application/x-www-form-urlencoded";

        assertFalse(InputValidator.isUnsupportedMediaType(contentType));
    }

    @Test
    public void returnsTrueIfValueIsInvalid() {
        String body = "invalid values";

        assertTrue(InputValidator.isInvalidValue(body));
    }

    @Test
    public void returnsFalseIfValueIsValid() {
        String body = "valid+values";

        assertFalse(InputValidator.isInvalidValue(body));
    }
}
