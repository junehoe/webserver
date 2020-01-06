package webserver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoggerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void initialize() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void postInitialize() {
        System.setOut(originalOut);
    }

    @Test
    public void connectedToPortMessageGetsOutput() {
        int port = 9001;

        Logger.printConnectionMessage(port);

        assertEquals("Connected to port 9001...\n", outContent.toString());
    }

    @Test
    public void defaultDirectoryMessageGetsOutput() {
        Logger.printDefaultDirectoryMessage();

        assertEquals("Using default directory\n", outContent.toString());
    }

    @Test
    public void customDirectoryMessageGetsOutput() {
        String directory = "/this/is/a/real/directory";

        Logger.printCustomDirectoryMessage(directory);

        assertEquals("Serving files from /this/is/a/real/directory\n", outContent.toString());
    }
}
