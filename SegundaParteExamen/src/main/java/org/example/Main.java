package org.example;

import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    MongoDatabase database;
    List<Record> records = new ArrayList<Record>();

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("records");
    }

    public void importFromMongo() {
        MongoCollection<Document> collection = database.getCollection("records");

        for (Document doc : collection.find()) {
            Record r = new Record();
            r.setName(doc.getString("name"));
            r.setNumberrange((doc.getInteger("numberrange")));
            r.setText(doc.getString("text"));
            r.setRegion(doc.getString("region"));
            r.setActive(doc.getBoolean("active"));
            List<String> list = doc.getList("languages", String.class);
            if (list != null) {
                r.setLanguages(list);
            }

            records.add(r);


        }
        System.out.println("Json successfully loaded to list!");


    }

    public void showRecords() {
        for (Record r : records) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {

        Main main = new Main();
        main.openConnection();
        main.importFromMongo();
        main.showRecords();
    }
}