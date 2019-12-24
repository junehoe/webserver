package webserver.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpRequestTest {
    HttpRequest httpRequest;

    @Before
    public void initialize() {
        httpRequest = new HttpRequest("GET", "/", "HTTP/1.1");
    }

    @Test
    public void getsTheRequestMethod() {
        assertEquals("GET", httpRequest.getMethod());
    }

    @Test
    public void getsTheRequestPath() {
        assertEquals("/", httpRequest.getPath());
    }

    @Test
    public void getsTheRequestHttpVersion() {
        assertEquals("HTTP/1.1", httpRequest.getHttpVersion());
    }
}
