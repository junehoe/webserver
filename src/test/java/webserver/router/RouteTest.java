package webserver.router;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteTest {
    Route route;

    @Before
    public void initialize() {
        route = new Route("GET", "/", "This is the body");
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
    public void returnsTheHtmlBody() {
        assertEquals("This is the body", route.getBody());
    }
}
