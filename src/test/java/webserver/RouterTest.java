package webserver;

import webserver.response.HttpResponse;
import webserver.request.HttpRequest;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouterTest {
    private Router router;

    @Mock
    private HttpRequest httpRequest;

    @Before
    public void initialize() {
        router = new Router();
    }

    @Test
    public void addsRouteAndReturnsResponseObject() throws IOException {
        String path = "/";
        String html = "index.html";
        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn(path);

        router.addRoute(path, html);

        assertNotNull(router.route(httpRequest));
    }

    @Test
    public void returnsResponseObjectDespiteNotInRoute() throws IOException {
        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn("/fake-path");

        assertNotNull(router.route(httpRequest));
    }
}
