package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Exercise2 {
    ArrayList<Team> teams = new ArrayList<>();
    MongoDatabase database;

    public void loadData() {
        for (int i = 0; i < 15; i++) {
            Team t = new Team();
            t.set_id(i);
            t.set_city("City_" + i);
            t.set_name("Team_" + i);
            t.set_rate(Math.random() * 100);
            for (int j = 0; j < (i + 3) % 10; j++) {
                Player p = new Player(j, "Player_" + j, "Italy", Math.random() * 50);
                t._teamMates.add(p);
            }
            teams.add(t);
        }
    }
    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("teams");
    }

    public void exportToJsonAndMongo() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this.teams);
        System.out.println(json);
        MongoCollection<Document> collection = database.getCollection("teams");

        for (Team team: this.teams){
            String jsonString = gson.toJson(team);
            Document doc = org.bson.Document.parse(jsonString);
            collection.insertOne(doc);
        }

        System.out.println("Json successfully added");



    }


    public void exportXMLToJson(){

    }

    public static void main(String[] args) {
        Exercise2 e = new Exercise2();
        e.openConnection();
        e.loadData();
        e.exportToJsonAndMongo();


    }

}
