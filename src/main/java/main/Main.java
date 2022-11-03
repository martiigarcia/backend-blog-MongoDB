package main;


import api.PageService;
import com.mongodb.client.model.Indexes;
import modelo.Page;
import servicios.PageServicio;
import servicios.PostServicio;
import web.WebApi;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

import com.mongodb.Block;

import com.mongodb.client.MongoCursor;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Updates.*;

import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {


//        WebApi servicio =
//                new WebApi(1234);
//        servicio.start();


//        MongoClient mongoClient = new MongoClient();
//        MongoDatabase database = mongoClient.getDatabase("blog");
//        MongoCollection<Document> collectionPage = database.getCollection("page");
//        MongoCollection<Document> collectionPost = database.getCollection("post");


        //AGREGO UNA PAGINA:

//        Document doc = new Document("title", "Pagina 1")
//                .append("text", "Texto de pagina 1 ...")
//                .append("author", "Marti")
//                .append("date", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        collectionPage.insertOne(doc);

        //AGREGO UN POST:

//        Document doc = new Document("title", "Post 1")
//                .append("text", "Texto de post 1 ...")
//                .append("author", "Marti")
//                .append("tags", "tags...")
//                .append("resume", "resumen...")
//                .append("relatedLinks", "links...")
//                .append("date", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        collectionPost.insertOne(doc);

        WebApi servicio =
                new WebApi(1234, new PageServicio(), new PostServicio());
        servicio.start();

//       ID PAGINA: "635da6a61d745a244b4f3349"

    }
}
