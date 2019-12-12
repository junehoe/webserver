package webserver.socket;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SocketCreatorTest {
    @Mock
    ServerSocket mockServerSocket;

    @Test
    public void testServerSocketGetsCreated() throws IOException {
        final int port = 9003;
        ServerSocket testServerSocket = SocketCreator.createServerSocket(port);

        assertNotNull(testServerSocket);

        testServerSocket.close();
    }

    @Test
    public void testServerSocketWithSpecificPortGetsCreated() throws IOException {
        final int port = 9006;
        ServerSocket testServerSocket = SocketCreator.createServerSocket(port);

        assertEquals(testServerSocket.getLocalPort(), port);
    }

    @Test
    public void testClientSocketGetsCreated() throws IOException {
        when(mockServerSocket.accept()).thenReturn(new Socket());

        assertNotNull(SocketCreator.createClientSocket(mockServerSocket));
    }
}
