package webserver.parser;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

public class HtmlParserTest {
    @Test
    public void returnsParsedHtmlStringForIndexHtml() throws IOException {
        String htmlPath = "public/index.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("View todo list"));
    }

    @Test
    public void returnsParsedHtmlStringForErrorHtml() throws IOException {
        String htmlPath = "public/error.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>404 Page Not Found</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForHealthCheckHtml() throws IOException {
        String htmlPath = "public/health-check.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Everything is good!</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoList() throws IOException {
        String htmlPath = "public/todo-list.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Todo List</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoItem1() throws IOException {
        String htmlPath = "public/todo-item-1.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Do a barrel roll</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoItem2() throws IOException {
        String htmlPath = "public/todo-item-2.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Buy groceries for the week</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoItem3() throws IOException {
        String htmlPath = "public/todo-item-3.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Pretend to be a tree for a day</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoItem4() throws IOException {
        String htmlPath = "public/todo-item-4.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Adopt a kitten</h1>"));
    }

    @Test
    public void returnsParsedHtmlStringForTodoItem5() throws IOException {
        String htmlPath = "public/todo-item-5.html";

        assertTrue(HtmlParser.parseHtml(htmlPath).contains("<h1>Create a todo list</h1>"));
    }

    @Test
    public void throwsErrorIfHtmlDoesNotExist() {
        String htmlPath = "obviouslyFakePath.html";
        try {
            HtmlParser.parseHtml(htmlPath);
            System.out.println("This does not get printed");
        } catch (IOException e) {
            System.out.println("Exception was thrown");
        }
    }
}
