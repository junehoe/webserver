package webserver.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlParser {
    public static String parseHtml(String htmlPath, boolean isResource) {
        BufferedReader in;
        String stringLine;
        StringBuilder content = new StringBuilder();
        try {
            if (isResource) {
                in = new BufferedReader(new InputStreamReader(HtmlParser.class.getResourceAsStream(htmlPath)));
            } else {
                in = new BufferedReader(new FileReader(htmlPath));
            }
            while ((stringLine = in.readLine()) != null) {
                content.append(stringLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
