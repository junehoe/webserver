package webserver.controller;

import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import static org.junit.Assert.assertEquals;

public class AppControllerTest {
    @Test
    public void returnsErrorResponse() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/asdf")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = AppController.error.apply(httpRequest);

        assertEquals(httpResponse.getStatusCode(), 404);
    }
}
