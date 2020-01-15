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

    @Test
    public void returns201StatusCode() {
        assertEquals(HttpStatusCode.CREATED.getStatusCode(), 201);
    }

    @Test
    public void returnsCreatedStatusString() {
        assertEquals(HttpStatusCode.CREATED.getStatusString(), "Created");
    }

    @Test
    public void returns303StatusCode() {
        assertEquals(HttpStatusCode.SEE_OTHER.getStatusCode(), 303);
    }

    @Test
    public void returnsSeeOtherStatusString() {
        assertEquals(HttpStatusCode.SEE_OTHER.getStatusString(), "See Other");
    }
}
