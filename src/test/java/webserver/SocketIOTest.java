package webserver;

import java.io.*;

import java.net.Socket;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocketIOTest {
    @Mock
    Socket mockSocket;

    @Mock
    InputStream mockInputStream;

    @Mock
    OutputStream mockOutputStream;

    @Test
    public void testSocketInputGetsCreated() throws IOException {
        when(mockSocket.getInputStream()).thenReturn(mockInputStream);

        assertNotNull(SocketIO.createSocketReader(mockSocket));
    }

    @Test
    public void testSocketOutputGetsCreated() throws IOException {
        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);

        assertNotNull(SocketIO.createSocketWriter(mockSocket));
    }

    @Test
    public void testReadClientStream() throws IOException {
        String inputString = "Hello\n";
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputString.getBytes())));

        assertEquals("Hello", SocketIO.readFromInputStream(input));
    }

    @Test
    public void testWriteClientStream() {
        String inputString = "World";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outContent, true);

        SocketIO.writeToOutputStream(printWriter, inputString);

        assertEquals("World\n", outContent.toString());
    }
}
