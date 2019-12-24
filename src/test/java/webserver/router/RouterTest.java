package webserver.router;

import webserver.parser.HtmlParser;
import webserver.request.HttpRequest;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.response.HttpResponse;

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
    public void return200ResponseObjectIfRouteExists() throws IOException {
        String path = "/";
        String html = "/index.html";

        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn("/");
        router.addRoute(new Route("GET", path, HtmlParser.parseHtml(html, true)));

        HttpResponse httpResponse = router.route(httpRequest);

        assertEquals(200, httpResponse.getStatusCode());
    }

    @Test
    public void returns404ResponseObjectIfNotInRoute() throws IOException {
        String path = "/";
        String html = "/index.html";

        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn("/fake-path");
        router.addRoute(new Route("GET", path, HtmlParser.parseHtml(html, true)));

        HttpResponse httpResponse = router.route(httpRequest);

        assertEquals(404, httpResponse.getStatusCode());
    }

    @Test
    public void getsTheRoutes() {
        Route route1 = new Route("GET", "/route1", "Route 1");
        Route route2 = new Route("HEAD", "/route2", "Route 2");

        router.addRoute(route1);
        router.addRoute(route2);

        assertEquals(2, router.getRoutes().size());
    }
}
