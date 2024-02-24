package org.example;
import com.mongodb.client.*;
import org.bson.Document;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public void openConnection(){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");

        MongoDatabase database = mongoClient.getDatabase("pokemon");
        FindIterable<Document> collection = database.getCollection("pokemons").find();
        for (Document document : collection) {
            System.out.println(document);
        }


    }


    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();


    }
}