package servicios;

import api.PageService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PageServicio implements PageService {

    @Override
    public String findPage(String id) {

        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("page");

            FindIterable<Document> ds = collection.find(Filters.eq("_id", new ObjectId(id)));
           // FindIterable myDoc = collection.find(Filters.eq("_id", new ObjectId(id))).first();

            return (StreamSupport.stream(ds.spliterator(), false)
                    .map(Document::toJson)
                    .collect(Collectors.joining(", ", "[", "]")));

        } catch (Exception e) {
            throw new RuntimeException("ERRORRR page");
        }


    }

    @Override
    public List<Document> findAllPages() {
        try (MongoClient mongoClient = new MongoClient()) {
            MongoDatabase database = mongoClient.getDatabase("blog");
            MongoCollection<Document> collection = database.getCollection("page");
            List<Document> lista = new ArrayList<>();

//            para traer todos los elementos de la coleccion (con un cursor)
            for (Document cur : collection.find()) {
//                System.out.println(cur.toJson());
                lista.add(cur);
            }
            return lista;

        } catch (Exception e) {
            throw new RuntimeException("ERRORRR");
        }
    }
}
