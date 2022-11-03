package web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import api.PageService;
import api.PostService;
import exceptions.ModeloException;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.bson.Document;
import org.bson.types.ObjectId;


public class WebApi {


    private int webPort;
    private PageService pageService;
    private PostService postService;


    public WebApi(int webPort, PageService pageService, PostService postService) {
        this.webPort = webPort;
        this.pageService = pageService;
        this.postService = postService;
    }

    public void start() {

        Javalin app = Javalin.create(config ->
        {
            config.enableCorsForAllOrigins();
        }).start(this.webPort);
        //posts y gets
        app.get("/pages/{id}", this.getPage());
        app.get("/posts/latest", this.getLastestPosts());
        app.get("/posts/{id}", this.getPost());
        app.get("/byauthor", this.getByAuthor());//nombre de autor y cantidad de posteos --> calcular
        app.get("/posts/author/{nombreautor}", this.getPostsByAuthor());
        app.get("/search/{text}", this.searchPost());


        //excepciones
        app.exception(ModeloException.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "errors", e.toMap()));
            // log error in a stream...
        });

        app.exception(Exception.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.json(Map.of("result", "error", "message", "Ups... algo se rompió: " + e.getMessage()));
            // log error in a stream...
        });
    }

    private Handler getPage() {
        return ctx -> {

            String id = ctx.pathParam("id"); // con esto recibe el parametro de la ruta pages/id

            String doc = pageService.findPage(id);
            System.out.println(doc);

            ctx.json(doc);

        };
    }

    private Handler getPost() {
        return ctx -> {

            String id = ctx.pathParam("id"); // con esto recibe el parametro de la ruta pages/id

            String doc = postService.findPost(id);
            System.out.println(doc);

            ctx.json(doc);

        };

    }

    private Handler getLastestPosts() {
        return ctx -> {

            String doc = postService.findLatestPosts();
            System.out.println(doc);

            ctx.json(doc);

        };
    }

    private Handler getByAuthor() {
        return ctx -> {

            String doc = postService.findByAuthor();
            System.out.println(doc);

            ctx.json(doc);

        };
    }

    private Handler getPostsByAuthor() {
        return ctx -> {

            String author = ctx.pathParam("nombreautor"); // con esto recibe el parametro de la ruta pages/id

            String doc = postService.findPostByAuthor(author);
            System.out.println(doc);

            ctx.json(doc);

        };
    }

    private Handler searchPost() {
        return ctx -> {
            String text = ctx.pathParam("text"); // con esto recibe el parametro de la ruta pages/id

            System.out.println(text);

            String doc = postService.searchPosts(text);
            System.out.println(doc);

            ctx.json(doc);

        };
    }

}
