package org.example;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MONGOHELP {
    MongoDatabase database = null;

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("CHANGE THIS");
    }

    public void exportToJson() {
        try {
            Gson gson = new Gson();

            // Get a MongoCollection from the MongoDatabase
            MongoCollection<Document> collection = database.getCollection("CHANG€€E THIS!!");

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

    public void importFromMongo() {
        MongoCollection<Document> collection = database.getCollection("!!!CHANGE THIS!!!!");
        /*
        for (Document doc : collection.find()) {
            Person person = gson.fromJson(doc.toJson(), Person.class);
            persons.add(person);
        }

         */
        // TODO IF THERE IS A LIST:
        /*
         public void importFromMongo(){
        MongoCollection<Document> collection = database.getCollection("records");

        for (Document doc: collection.find()){
            Record r = new Record();
            r.setName(doc.getString("name"));
            r.setNumberrange((doc.getInteger("numberrange")));
            r.setText(doc.getString("text"));
            r.setRegion(doc.getString("region"));
            r.setActive(doc.getBoolean("active"));
            List<String> list = doc.getList("languages", String.class);
            String[] languages = null;
            if (list != null) {
                languages = list.toArray(new String[0]);
            }
            // OR:
            List<String> list = doc.getList("languages", String.class);
            if (list != null) {
                r.setLanguages(list);
            }

            .....
            r.setLanguages(languages);
            records.add(r);


        }
        System.out.println("Json successfully loaded to list!");


    }
         */

    }
}
