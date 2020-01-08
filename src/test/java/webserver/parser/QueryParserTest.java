package webserver.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryParserTest {
    @Test
    public void returnsTheFilterKeyword() {
        String requestPath = "/todo?filter=hello%20world";

        assertEquals(QueryParser.getFilterKeyword(requestPath), "hello%20world");
    }

    @Test
    public void returnsTheTitleFromRequestBody() {
        String requestBody = "title=Hello+there+this+is+a+title";

        assertEquals(QueryParser.getTitle(requestBody), "Hello there this is a title");
    }
}
