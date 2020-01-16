package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import static org.junit.Assert.assertEquals;
import static webserver.router.RoutePath.HEALTH_PATH;
import static webserver.router.RoutePath.INDEX_PATH;

public class AppControllerTest {
    private AppController appController;

    @Before
    public void initialize() {
        appController = new AppController();
    }

    @Test
    public void returnsErrorGetResponse() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/asdf")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.error.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 404);
    }

    @Test
    public void returnsErrorHeadResponse() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/asdf")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.error.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 404);
    }

    @Test
    public void returnsGetResponseForIndexPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(INDEX_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsGetResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsHeadResponseForIndexPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(INDEX_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returnsHeadResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.healthCheck.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }
}
