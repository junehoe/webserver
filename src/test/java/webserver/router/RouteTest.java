package webserver.router;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class RouteTest {
    Route route;

    public Function<HttpRequest, HttpResponse> callback = (request) -> {
        return new HttpResponse.Builder("GET")
                .build();
    };

    @Before
    public void initialize() {
        route = new Route("GET", "/", callback);
    }

    @Test
    public void returnsTheRequestMethod() {
        assertEquals("GET", route.getMethod());
    }

    @Test
    public void returnsTheRequestPath() {
        assertEquals("/", route.getPath());
    }

    @Test
    public void returnsTheCallbackController() {
        assertEquals(callback, route.getCallback());
    }
}
