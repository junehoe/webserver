package webserver;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HttpRequestValidatorTest {
    @Test
    public void returnsTrueIfContentTypeIsNotSupported() {
        String contentType = "fake content type";

        assertTrue(HttpRequestValidator.isUnsupportedMediaType(contentType));
    }

    @Test
    public void returnsFalseIfContentTypeIsNotSupported() {
        String contentType = "application/x-www-form-urlencoded";

        assertFalse(HttpRequestValidator.isUnsupportedMediaType(contentType));
    }

    @Test
    public void returnsTrueIfValueIsInvalid() {
        String body = "invalid values";

        assertTrue(HttpRequestValidator.isInvalidValue(body));
    }

    @Test
    public void returnsFalseIfValueIsValid() {
        String body = "valid+values";

        assertFalse(HttpRequestValidator.isInvalidValue(body));
    }
}
