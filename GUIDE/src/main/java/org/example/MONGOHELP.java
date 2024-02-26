package org.example;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MONGOHELP {
    MongoDatabase database = null;


    public void exportToJson() {
        try {
            Gson gson = new Gson();

            // Get a MongoCollection from the MongoDatabase
            MongoCollection<Document> collection = database.getCollection("persons");

            /*
            // Iterate over the persons list
            for (Person person : this.persons) {
                // Convert each Person object to a JSON string
                String json = gson.toJson(person);

                // Create a Document object from the JSON string
                Document doc = Document.parse(json);

                // Insert the Document object into the MongoDB collection
                collection.insertOne(doc);
            }

             */

            System.out.println("Data loaded...");
        } catch (Exception e) {
            System.out.println("An error occurred while exporting to JSON:");
            e.printStackTrace();
        }
    }
}
