package webserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import webserver.parser.HtmlParser;

import static webserver.pages.Page.BODY;
import static webserver.pages.Page.TEMPLATE_HTML;
import static webserver.pages.Page.TITLE;

public class HtmlBuilder {
    public static String createHtmlString(HashMap<String, String> pageContents) throws IOException {
        String templateString = HtmlParser.parseHtml(TEMPLATE_HTML, true);
        for (Map.Entry<String, String> entry : pageContents.entrySet()) {
            templateString = replaceSubstring(templateString, entry.getKey(), entry.getValue());
        }
        return templateString;
    }

    public static String createHtmlString(String path) throws IOException {
        return HtmlParser.parseHtml(path, true);
    }

    public static String createCustomHtmlString(String path) throws IOException {
        return HtmlParser.parseHtml(path, false);
    }

    public static HashMap<String, String> createPageDescriptors(String title, String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TITLE, title);
        hashMap.put(BODY, body);
        return hashMap;
    }

    public static String createHeader(String headerTitle) {
        return "<header><h1>" + headerTitle + "</h1></header>";
    }

    public static String createSection(String path, String sectionBody) {
        return "<section><a rel='item' href='" + path + "'>" + sectionBody + "</a></section>";
    }

    private static String replaceSubstring(String string, String placeholder, String value) {
        return string.replaceAll(placeholder, value);
    }
}
