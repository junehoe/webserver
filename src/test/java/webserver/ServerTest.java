package webserver;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    @Test
    public void isRunningReturnsTrueIfServerIsRunning() throws IOException {
        Server server = new Server(8000, "");

        server.start();

        assertTrue(server.isRunning());

        server.stop();
    }

    @Test
    public void isRunningReturnsFalseIfServerIsNotRunning() throws IOException {
        Server server = new Server(5000, "");

        server.stop();

        assertFalse(server.isRunning());
    }
}
