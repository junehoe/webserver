package webserver.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CliParserTest {
    @Test
    public void returnsAnIntegerPortForValidPortString() {
        String port = "8000";
        int expected = 8000;

        assertEquals(CliParser.getPort(port), expected);
    }

    @Test
    public void returnsDefaultPort5000ForInvalidPortString() {
        String port = "800A";
        int expected = 5000;

        assertEquals(CliParser.getPort(port), expected);
    }

    @Test
    public void returnsDirectoryIfDirectoryIsValid() {
        String validDirectory = "./public";

        assertEquals(CliParser.getDirectory(validDirectory), validDirectory);
    }

    @Test
    public void returnsNullForInvalidDirectory() {
        String invalidDirectory = "totally invalid directory";

        assertEquals(CliParser.getDirectory(invalidDirectory), "");;
    }

    @Test
    public void returnsBlankDirectoryForNullDirectory() {
        assertEquals(CliParser.getDirectory(null), "");
    }
}
