package webserver.todo;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class TodoPageCreatorTest {
    @Test
    public void createsATodoPage() throws IOException {
        String path = Paths.get("").toAbsolutePath().toString() + "/public/test/page-creator-test";
        String title = "Create a todo page";

        TodoPageCreator.createTodoPage(path, title);

        assertTrue(Files.exists(Paths.get(path)));
    }
}
