package webserver.request;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HttpRequestTest {
    @Test
    public void getsTheRequestMethod() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .build();

        assertEquals("GET", httpRequest.getMethod());
    }

    @Test
    public void getsTheRequestPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/")
                .build();

        assertEquals("/", httpRequest.getPath());
    }

    @Test
    public void getsTheRequestHttpVersion() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withHttpVersion("HTTP/1.1")
                .build();

        assertEquals("HTTP/1.1", httpRequest.getHttpVersion());
    }

    @Test
    public void getsTheRequestHeaders() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Hello", "World");
        hashMap.put("Olleh", "Dlrow");
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withHeaders(hashMap)
                .build();

        assertEquals(hashMap, httpRequest.getHeaders());
    }

    @Test
    public void getsTheBody() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withBody("Hello")
                .build();

        assertEquals("Hello", httpRequest.getBody());
    }
}
