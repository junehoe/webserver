package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import static org.junit.Assert.assertEquals;
import static webserver.pages.ServerPages.*;

public class AppControllerTest {
    private AppController appController;

    @Before
    public void initialize() {
        appController = new AppController();
    }

    @Test
    public void returnsErrorResponse() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/asdf")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.error.apply(httpRequest);

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
    }

    @Test
    public void returnsGetResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
    }

    @Test
    public void returnsHeadResponseForIndexPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(INDEX_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }

    @Test
    public void returnsHeadResponseForHealthPath() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath(HEALTH_PATH)
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = appController.healthCheck.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
    }
}
