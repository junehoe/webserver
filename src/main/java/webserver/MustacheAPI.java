package webserver;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;

import java.io.IOException;
import java.io.StringWriter;

public class MustacheAPI {
    public static String createHtml(Object context, String templatePath) {
        try {
            Mustache mustache = new DefaultMustacheFactory().compile(templatePath);
            StringWriter writer = new StringWriter();
            mustache.execute(writer, context).flush();
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred";
        }
    }
}
