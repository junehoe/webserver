package webserver.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileParserTest {
    @Test
    public void getsTheFileExtensionFromTheFullFileName() {
        String fileName = "hello.html";

        assertEquals(".html", FileParser.getFileExtension(fileName));
    }

    @Test
    public void returnsEmptyStringIfNoExtensionExists() {
        String fileName = "hello/this/is/not/a/file";

        assertEquals("", FileParser.getFileExtension(fileName));
    }
}
