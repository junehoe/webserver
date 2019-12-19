package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpRequestTest {
    private HttpRequest httpRequest;

    @Mock
    private BufferedReader input;

    @Before
    public void initialize() throws IOException {
        when(input.readLine()).thenReturn("GET /helloworld HTTP/1.1\r\n");
        httpRequest = new HttpRequest(input);
    }

    @Test
    public void getParsedMethod() throws IOException {
        assertEquals(httpRequest.getMethod(), "GET");
    }

    @Test
    public void getParsedPath() throws IOException {
        assertEquals(httpRequest.getPath(), "/helloworld");
    }
}
