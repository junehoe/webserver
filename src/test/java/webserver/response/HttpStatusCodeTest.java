package webserver.response;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HttpStatusCodeTest {
    @Test
    public void returns200StatusCode() {
        assertEquals(HttpStatusCode.OK.getStatusCode(), 200);
    }

    @Test
    public void returnsOKStatusString() {
        assertEquals(HttpStatusCode.OK.getStatusString(), "OK");
    }

    @Test
    public void returns404StatusCode() {
        assertEquals(HttpStatusCode.NOT_FOUND.getStatusCode(), 404);
    }

    @Test
    public void returnsNotFoundStatusString() {
        assertEquals(HttpStatusCode.NOT_FOUND.getStatusString(), "Not Found");
    }
}
