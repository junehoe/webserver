package webserver;

import webserver.response.HttpResponseBuilder;
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
    public void addsRouteToRouterHashMapAndReturnsCorrectResponse() throws IOException {
        String path = "/";
        String html = "index.html";
        String htmlContent = HtmlParser.parseHtml(html);
        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn(path);

        router.addRoute(path, html);

        assertEquals(router.route(httpRequest), new HttpResponseBuilder()
                .withStatusCode(200)
                .withContentLength(htmlContent.length())
                .withContentType("text/html")
                .withContent(htmlContent)
                .build()
        );
    }

    @Test
    public void returnsNotFoundHtmlForResponseNotInRouter() throws IOException {
        String path = "/fake-path";
        String html = "error.html";
        String htmlContent = HtmlParser.parseHtml(html);
        when(httpRequest.getMethod()).thenReturn("GET");
        when(httpRequest.getPath()).thenReturn(path);

        assertEquals(router.route(httpRequest), new HttpResponseBuilder()
                .withStatusCode(404)
                .withContentLength(htmlContent.length())
                .withContentType("text/html")
                .withContent(htmlContent)
                .build()
        );
    }
}
