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
    public void returnsTrueIfCaseInsensitiveStringIsContainedInAnotherString() {
        String str1 = "Hello";
        String str2 = "Yo HeLLo";

        assertTrue(InputValidator.isCaseInsensitiveStringContained(str1, str2));
    }

    @Test
    public void returnsFalseIfCaseInsensitiveStringIsNotContainedInAnotherString() {
        String str1 = "hi";
        String str2 = "the above word does not exist in here";

        assertFalse(InputValidator.isCaseInsensitiveStringContained(str1, str2));
    }
}
