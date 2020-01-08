package webserver.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.todo.TodoList;

import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    private PostController postController;
    private TodoList todoList;

    @Mock
    HttpRequest httpRequest;

    @Before
    public void initialize() {
        todoList = new TodoList();
        String path = Paths.get("").toAbsolutePath().toString() + "/public/test/post-test";
        todoList.setDirectory(path);
        postController = new PostController(todoList);
    }

    @Test
    public void createsAPostResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getBody()).thenReturn("title=This+is+cool");
        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = postController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getMethod(), "POST");
    }

    @Test
    public void createsA415UnsupportedMediaTypeResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/xml");

        when(httpRequest.getHeaders()).thenReturn(headers);

        HttpResponse httpResponse = postController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getStatusCode(), 415);
    }

    @Test
    public void createsA400BadRequestResponse() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        when(httpRequest.getHeaders()).thenReturn(headers);
        when(httpRequest.getBody()).thenReturn("title=this is so cool");

        HttpResponse httpResponse = postController.createTodoItem.apply(httpRequest);

        assertEquals(httpResponse.getStatusCode(), 400);
    }
}
