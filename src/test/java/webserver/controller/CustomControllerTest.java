package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomControllerTest {
    private CustomController customController;

    @Before
    public void initialize() {
        File f = new File("./public/test/custom-controller-test");
        File[] customFiles = f.listFiles();
        customController = new CustomController(customFiles);
    }

    @Test
    public void returns200ResponseForGetCustomListingPage() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/custom")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForHeadCustomListingPage() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/custom")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.index.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForGetCustomHtmlFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/custom/test1.html")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForHeadCustomHtmlFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/custom/test1.html")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForGetCustomJsonFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/custom/test2.json")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForHeadCustomJsonFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/custom/test2.json")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForGetCustomTxtFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/custom/test3.txt")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns200ResponseForHeadCustomTxtFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/custom/test3.txt")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 200);
    }

    @Test
    public void returns415ResponseForGetUnsupportedCustomFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("GET")
                .withPath("/custom/unsupported.jpg")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "GET");
        assertEquals(httpResponse.getStatusCode(), 415);
    }

    @Test
    public void returns415ResponseForHeadUnsupportedCustomFile() {
        HttpRequest httpRequest = new HttpRequest.Builder("HEAD")
                .withPath("/custom/unsupported.jpg")
                .withHttpVersion("HTTP/1.1")
                .build();
        HttpResponse httpResponse = customController.showFile.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "HEAD");
        assertEquals(httpResponse.getStatusCode(), 415);
    }
}
