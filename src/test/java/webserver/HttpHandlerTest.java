package webserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpHandlerTest {
    private ByteArrayOutputStream outContent;

    @Mock
    Socket clientSocket;

    public void initialize() throws IOException {
        outContent = new ByteArrayOutputStream();
        when(clientSocket.getOutputStream()).thenReturn(outContent);
    }

    @Before
    public void runBefore() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServerOutputsHelloWorldMessage() throws IOException {
        String inputString = "GET / HTTP/1.1\r\n";
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket);

        httpHandler.run();

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 21\r\nContent-Type: text/html; charset=utf-8\r\n\r\n<h1>Hello World!</h1>\n",
                outContent.toString());
    }

    @Test
    public void testServerOutputsPageNotFoundMessage() throws IOException {
        String inputString = "GET /asdf HTTP/1.1\r\n";
        when(clientSocket.getInputStream()).thenReturn(new ByteArrayInputStream(inputString.getBytes()));
        HttpHandler httpHandler = new HttpHandler(clientSocket);

        httpHandler.run();

        assertEquals("HTTP/1.1 404 Not Found\r\nContent-Length: 23\r\nContent-Type: text/html; charset=utf-8\r\n\r\n<h1>Page not found</h1>\n",
                outContent.toString());
    }
}
