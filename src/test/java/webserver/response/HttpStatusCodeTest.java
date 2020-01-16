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

    @Test
    public void returns400StatusCode() {
        assertEquals(HttpStatusCode.BAD_REQUEST.getStatusCode(), 400);
    }

    @Test
    public void returnsBadRequestStatusString() {
        assertEquals(HttpStatusCode.BAD_REQUEST.getStatusString(), "Bad Request");
    }

    @Test
    public void returns415StatusCode() {
        assertEquals(HttpStatusCode.UNSUPPORTED_MEDIA_TYPE.getStatusCode(), 415);
    }

    @Test
    public void returnsUnsupportedMediaTypeStatusString() {
        assertEquals(HttpStatusCode.UNSUPPORTED_MEDIA_TYPE.getStatusString(), "Unsupported Media Type");
    }

    @Test
    public void returns204StatusCode() {
        assertEquals(HttpStatusCode.NO_CONTENT.getStatusCode(), 204);
    }

    @Test
    public void returnsNoContentStatusString() {
        assertEquals(HttpStatusCode.NO_CONTENT.getStatusString(), "No Content");
    }
}
