package webserver;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResponseCodesTest {
    @Test
    public void hashMapContains200ResponseCode() {
        assertTrue(ResponseCodes.RESPONSE_CODES.containsKey(200));
    }

    @Test
    public void hashMapContains404ResponseCode() {
        assertTrue(ResponseCodes.RESPONSE_CODES.containsKey(404));
    }

    @Test
    public void hashMap200CodeEqualsOKString() {
        assertEquals(ResponseCodes.RESPONSE_CODES.get(200), "OK");
    }

    @Test
    public void hashMap404CodeEqualsNotFoundString() {
        assertEquals(ResponseCodes.RESPONSE_CODES.get(404), "Not Found");
    }
}
