package webserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HttpRequestTest {
    HttpRequest httpRequest;

    @Before
    public void initialize() {
        httpRequest = new HttpRequest("GET /helloworld HTTP/1.1\r\n");
    }

    @Test
    public void getParsedMethod() {
        assertEquals(httpRequest.getMethod(), "GET");
    }

    @Test
    public void getParsedPath() {
        assertEquals(httpRequest.getPath(), "/helloworld");
    }
}
