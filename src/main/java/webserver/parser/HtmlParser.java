package webserver.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlParser {
    public static String parseHtml(String htmlPath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(htmlPath));
        String stringLine;
        while ((stringLine = in.readLine()) != null) {
            content.append(stringLine);
        }
        return content.toString();
    }
}
