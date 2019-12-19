package webserver;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HtmlPageHandlerTest {
    @Test
    public void createsAnHtmlPageHashMap() {
        HashMap<String, String> expected = HtmlPageHandler.createPageHashMap("Hello", "World");

        assertEquals(expected.get("\\$title"), "Hello");
        assertEquals(expected.get("\\$body"), "World");
    }

    @Test
    public void createsAnHtmlPageBasedOnHashMap() throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("\\$title", "Hello World");
        hashMap.put("\\$body", "This is the body");

        String htmlString = HtmlPageHandler.createHtmlString(hashMap);

        assertTrue(htmlString.contains("Hello World"));
        assertTrue(htmlString.contains("This is the body"));
    }
}
