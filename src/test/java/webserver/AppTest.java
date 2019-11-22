package webserver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    App classUnderTest;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    private void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void initialize() {
        classUnderTest = new App();
        setupStreams();
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test
    public void testSystemOutput() {
        String message = "Hello World!";

        classUnderTest.printSystemOutput(message);

        assertEquals("Hello World!\n", outContent.toString());
    }

    @Test
    public void testSystemInput() {
        String inputString = "Hello!";
        InputStream input = new ByteArrayInputStream(inputString.getBytes());

        System.setIn(input);

        assertEquals("Hello!", classUnderTest.getSystemInput());
    }
}
