package main;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Tutorial {

    //QUICK START de la pagina de MongoDB

    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("test");

       //para isertar de a un doc
//        Document doc = new Document("name", "MongoDB")
//                .append("type", "database")
//                .append("count", 1)
//                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//                .append("info", new Document("x", 203).append("y", 102));
//        collection.insertOne(doc);

        //para insertar muchos docs
//        List<Document> documents = new ArrayList<Document>();
//        for (int i = 0; i < 100; i++) {
//            documents.add(new Document("i", i));
//        }
//        collection.insertMany(documents);

        //para ver la cantidad de docs en una coleccion
//        System.out.println(collection.count());

        //para traer el primer elemento
//        Document myDoc = collection.find().first();
//        System.out.println(myDoc.toJson());

        //para traer todos los elementos de la coleccion (con un cursor)
//        MongoCursor<Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }

        //para traer todos los elementos de la coleccion (sin cursor)
        // evite su uso ya que la aplicación puede perder un cursor si el ciclo termina antes de tiempo
//        for (Document cur : collection.find()) {
//            System.out.println(cur.toJson());
//        }


        //para traer el primer doc que encuentre con el filtro de i=71
//        Document myDoc = collection.find(eq("i", 71)).first();
//        System.out.println(myDoc.toJson());

        //para hacer un filtrado por rangos se hace un Block:
//        Block<Document> printBlock = new Block<Document>() {
//            @Override
//            public void apply(final Document document) {
//                System.out.println(document.toJson());
//            }
//        };

        //trae los elementos MAYORES a i=50
//        collection.find(gt("i", 50)).forEach(printBlock);
        //trae los elementos MAYORES a i=50, y MENORES IGUAL a i=90 => (50;90]
//        collection.find(and(gte("i", 89), lt("i", 93))).forEach(printBlock);
        //si es gt/lt NO incluye el num en cuestion ()
        //si es gte/lte/ INCLUYE el numero en cuestion []


        //para modificar un elemento i=10 => i=110
//        collection.updateOne(eq("i", 10), new Document("$set", new Document("i", 110)));
        //para modificar muchos elementos segun restriccion
//        UpdateResult updateResult = collection.updateMany(
//                lt("i", 100), //si son menores a 100 (sin incliur)
//                inc("i", 100)); //se incrementan en 100
//        System.out.println(updateResult.getModifiedCount()); //obtenes el numero de elementos modificados

        //para eliminar un elemento de la coleccion i=110
//        collection.deleteOne(eq("i", 110));
        //para eliminar muchos elementos de la coleccion segun restriccion
//        DeleteResult deleteResult = collection.deleteMany(
//                gte("i", 100));//si i>=100 los elimina
//        System.out.println(deleteResult.getDeletedCount());//obtenes el numero de elementos eliminados


       //para crear indices:
        // (incremental:1 , decremental:-1 ==> en type)
        // (campo a indexar ==> field)
//        collection.createIndex(new Document("i", 1));// new Document(<field1>, <type1>).append(<field2>, <type2>) ...

    }
}
