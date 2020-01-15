package webserver;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mock;
import webserver.database.DatabaseHandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    @Mock
    DatabaseHandler databaseHandler;

    @Test
    public void isRunningReturnsTrueIfServerIsRunning() throws IOException {
        Server server = new Server(8000, "", databaseHandler);

        server.start();

        assertTrue(server.isRunning());

        server.stop();
    }

    @Test
    public void isRunningReturnsFalseIfServerIsNotRunning() throws IOException {
        Server server = new Server(5000, "", databaseHandler);

        server.stop();

        assertFalse(server.isRunning());
    }
}
