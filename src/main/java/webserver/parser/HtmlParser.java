package webserver.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlParser {
    public static String parseHtml(String htmlPath, boolean isResource) throws IOException {
        BufferedReader in;
        String stringLine;
        StringBuilder content = new StringBuilder();
        if (isResource) {
            in = new BufferedReader(new InputStreamReader(HtmlParser.class.getResourceAsStream(htmlPath)));
        } else {
            in = new BufferedReader(new FileReader(htmlPath));
        }
        while ((stringLine = in.readLine()) != null) {
            content.append(stringLine);
        }
        return content.toString();
    }
}
