package api;

import modelo.Post;
import org.bson.Document;

import java.util.List;

public interface PostService {

    String findPost(String id);

    String findLatestPosts();

    String findByAuthor();

    String findPostByAuthor(String name);

    String searchPosts(String text);
}
