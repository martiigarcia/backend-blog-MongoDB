package api;

import modelo.Page;
import org.bson.Document;

import java.util.List;

public interface PageService {

    String findPage(String id);

    List<Document> findAllPages();

}
