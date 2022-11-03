package servicios;

import api.PostService;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import modelo.Post;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PostServicio implements PostService {

    @Override
    public String findPost(String id) {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("post");

//            Document myDoc = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            FindIterable<Document> ds = collection.find(Filters.eq("_id", new ObjectId(id)));


            return (StreamSupport.stream(ds.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));


        } catch (Exception e) {
            throw new RuntimeException("ERRORRR post");
        }
    }

    @Override
    public String findLatestPosts() {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("post");

//            Document myDoc = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            FindIterable<Document> ds = collection.find().sort(Sorts.descending("date")).limit(4);

            return (StreamSupport.stream(ds.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));

//            return null;
        } catch (Exception e) {
            throw new RuntimeException("ERRORRR post");
        }
    }

    //PREGUNTAR:
    @Override
    public String findByAuthor() {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("post");


//            collection.aggregate(
//                    Arrays.asList(
//                            Aggregates.match(Filters.eq("author", "Marti")),
//                            Aggregates.group("Marti", Accumulators.sum("count", 1))
//                    )
//            );
            AggregateIterable<Document> doc = collection.aggregate(
                    Arrays.asList(
                            Aggregates.group("$author", Accumulators.sum("count", 1))
                    ));


            return (StreamSupport.stream(doc.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));
        } catch (Exception e) {
            throw new RuntimeException("ERRORRR post");
        }
    }

    @Override
    public String findPostByAuthor(String name) {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("post");

//            Document myDoc = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            FindIterable<Document> ds = collection.find(Filters.eq("author", name));


            return (StreamSupport.stream(ds.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));


        } catch (Exception e) {
            throw new RuntimeException("ERRORRR post");
        }
    }

    @Override
    public String searchPosts(String text) {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("post");

            FindIterable<Document> i = collection.find(Filters.text(text));

            return (StreamSupport.stream(i.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));
        } catch (Exception e) {
            throw new RuntimeException("ERRORRR post");
        }
    }
}
