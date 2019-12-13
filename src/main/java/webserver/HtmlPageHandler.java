package webserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HtmlPageHandler {
    public static String createHtmlString(HashMap<String, String> pageContents) throws IOException {
        String templateString = HtmlParser.parseHtml("public/template.html");
        for (Map.Entry<String, String> entry : pageContents.entrySet()) {
            templateString = replaceSubstring(templateString, entry.getKey(), entry.getValue());
        }
        return templateString;
    }

    public static HashMap<String, String> createPageHashMap(String title, String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("\\$title", title);
        hashMap.put("\\$body", body);
        return hashMap;
    }

    private static String replaceSubstring(String string, String placeholder, String value) {
        return string.replaceAll(placeholder, value);
    }
}
