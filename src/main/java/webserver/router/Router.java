package webserver.router;

import webserver.controller.AppController;
import webserver.response.HttpResponse;
import webserver.request.HttpRequest;

import static webserver.router.HttpVerb.GET;
import static webserver.router.HttpVerb.HEAD;
import static webserver.router.HttpVerb.POST;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router {
    private ArrayList<Route> routes;

    public Router() {
        this.routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void get(String path, Function<HttpRequest, HttpResponse> callback) {
        addRoute(new Route(GET, path, callback));
    }

    public void head(String path, Function<HttpRequest, HttpResponse> callback) {
        addRoute(new Route(HEAD, path, callback));
    }

    public void post(String path, Function<HttpRequest, HttpResponse> callback) {
        addRoute(new Route(POST, path, callback));
    }

    public HttpResponse route(HttpRequest httpRequest) {
        Function<HttpRequest, HttpResponse> controller = findController(httpRequest);
        return controller.apply(httpRequest);
    }

    public Function<HttpRequest, HttpResponse> findController(HttpRequest httpRequest) {
        String requestMethod = httpRequest.getMethod();
        String requestPath = httpRequest.getPath();
        Optional<Route> route = routes
                .stream()
                .filter(entry -> routeMatcher(requestPath, entry.getPath()))
                .filter(entry -> isSameMethod(requestMethod, entry.getMethod()))
                .findFirst();

        if (route.isPresent()) {
            return route.get().getCallback();
        } else {
            return AppController.error;
        }
    }

    private boolean routeMatcher(String requestPath, String route) {
        Pattern pattern = Pattern.compile(route);
        Matcher matcher = pattern.matcher(requestPath);
        return matcher.matches();
    }

    private boolean isSameMethod(String requestMethod, String method) {
        return requestMethod.equals(method);
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }
}
