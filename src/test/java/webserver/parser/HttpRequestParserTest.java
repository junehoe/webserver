package webserver.parser;

import org.junit.Before;
import org.junit.Test;
import webserver.request.HttpRequest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HttpRequestParserTest {
    HttpRequestParser httpRequestParser;

    @Before
    public void initialize() {
        httpRequestParser = new HttpRequestParser();
    }

    @Test
    public void parsesTheInitialHeaderAndGetsMethod() throws IOException {
        String inputString = "GET / HTTP/1.1\r\nThe rest is irrelevant";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())));

        HttpRequest httpRequest = httpRequestParser.parse(input);

        assertEquals("GET", httpRequest.getMethod());
    }

    @Test
    public void parsesTheInitialHeaderAndGetsPath() throws IOException {
        String inputString = "GET / HTTP/1.1\r\nThe rest is irrelevant";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())));

        HttpRequest httpRequest = httpRequestParser.parse(input);

        assertEquals("/", httpRequest.getPath());
    }

    @Test
    public void parsesTheInitialHeaderAndGetsHttpVersion() throws IOException {
        String inputString = "GET / HTTP/1.1\r\nThe rest is irrelevant";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())));

        HttpRequest httpRequest = httpRequestParser.parse(input);

        assertEquals("HTTP/1.1", httpRequest.getHttpVersion());
    }
}
