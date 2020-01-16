package webserver.validator;

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

    @Test
    public void returnsTrueForValidFilterRequestPath() {
        String requestPath = "/todo?filter=hello%20world";

        assertTrue(HttpRequestValidator.isValidFilterRequestPath(requestPath));
    }

    @Test
    public void returnsFalseForInvalidFilterRequestPath() {
        String requestPath = "/todo?thisis=incorrect";

        assertFalse(HttpRequestValidator.isValidFilterRequestPath(requestPath));
    }

    @Test
    public void returnsTrueForValidRequestBody() {
        String requestBody = "todo-name=Hello+there+this+is+a+title";

        assertTrue(HttpRequestValidator.isValidRequestBody(requestBody));
    }

    @Test
    public void returnsFalseForInvalidRequestBody() {
        String requestBody = "incorrect-name=Hello+there+this+is+a+title";

        assertFalse(HttpRequestValidator.isValidRequestBody(requestBody));
    }

    @Test
    public void returnsFalseForEmptyInvalidRequestBody() {
        assertFalse(HttpRequestValidator.isValidRequestBody(""));
    }

    @Test
    public void returnsTrueForCaseInsensitiveContentTypeKey() {
        String key = "ContEnt-Type";

        assertTrue(HttpRequestValidator.isContentTypeHeader(key));
    }

    @Test
    public void returnsFalseForIncorrectContentTypeKey() {
        String incorrectKey = "Not-Content-Type";

        assertFalse(HttpRequestValidator.isContentTypeHeader(incorrectKey));
    }
}
