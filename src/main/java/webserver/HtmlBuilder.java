package webserver;

import java.util.HashMap;
import java.util.Map;

import webserver.parser.HtmlParser;

import static webserver.pages.Page.BODY;
import static webserver.pages.Page.TEMPLATE_HTML;
import static webserver.pages.Page.TITLE;

public class HtmlBuilder {
    public static String createHtmlString(HashMap<String, String> pageContents) {
        String templateString = HtmlParser.parseHtml(TEMPLATE_HTML, true);
        for (Map.Entry<String, String> entry : pageContents.entrySet()) {
            templateString = replaceSubstring(templateString, entry.getKey(), entry.getValue());
        }
        return templateString;
    }

    public static String createHtmlString(String path, boolean isResource) {
        return HtmlParser.parseHtml(path, isResource);
    }

    public static HashMap<String, String> createPageDescriptors(String title, String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE, title);
        hashMap.put(BODY, body);
        return hashMap;
    }

    public static String createTodoDetailHtml(String title, int id, boolean isComplete) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append(HtmlBuilder.createHeader(title));
        bodyBuilder.append(HtmlBuilder.createBreak());
        bodyBuilder.append(HtmlBuilder.createStatusSection(id, isComplete));
        bodyBuilder.append(HtmlBuilder.createBreak());
        bodyBuilder.append(HtmlBuilder.createGoBackFooter());
        return bodyBuilder.toString();
    }

    private static String createStatusSection(int id, boolean isComplete) {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("<form action='/todo/" + id + "/toggle' method='POST'>");
        bodyBuilder.append("<label class='" + getClass(isComplete) + "'>");
        bodyBuilder.append("Complete: " + isComplete + "</label><br>");
        bodyBuilder.append("<input type='submit' value='Toggle'>");
        bodyBuilder.append("</form>");
        return bodyBuilder.toString();
    }

    public static String createHeader(String headerTitle) {
        return "<header><h1>" + headerTitle + "</h1></header>";
    }

    public static String createSection(int id, String sectionBody) {
        return "<section><a rel='item' href='/todo/" + id + "'>" + sectionBody + "</a></section>";
    }

    public static String createForm(String path, String method, String body) {
        return "<form action='" + path + "' method='" + method + "'>" + body + "</form>";
    }

    public static String createInput(String type) {
        return "<input type='" + type + "'>";
    }

    public static String createInput(String type, String name) {
        return "<input type='" + type + "' name='" + name + "'>";
    }

    public static String createGoBackFooter() {
        return "<footer><a rel='collection' href='/todo'>Go Back</a></footer>";
    }

    public static String createBreak() {
        return "<br>";
    }

    private static String replaceSubstring(String string, String placeholder, String value) {
        return string.replaceAll(placeholder, value);
    }

    private static String getClass(boolean isComplete) {
        if (isComplete) {
            return "complete";
        } else {
            return "incomplete";
        }
    }
}
