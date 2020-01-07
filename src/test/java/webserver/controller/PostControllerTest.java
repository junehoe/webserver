package webserver.controller;

import org.junit.Test;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class PostControllerTest {
    private PostController postController;
    private TodoList todoList;

    @Test
    public void createsAPostResponse() {
        todoList = new TodoList();
        String path = Paths.get("").toAbsolutePath().toString() + "/public/test/post-test";
        todoList.setDirectory(path);
        postController = new PostController(todoList);
        HttpRequest httpRequest = new HttpRequest.Builder("POST")
                .withBody("title=This is cool")
                .build();

        HttpResponse httpResponse = postController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "POST");
    }
}
