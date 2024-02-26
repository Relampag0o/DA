package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    MongoDatabase database = null;
    List<Pokemon> pokemons;

    public Main() {
        openConnection();
        this.pokemons = new ArrayList<Pokemon>();
    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("pokemon");
    }


    public void exportToClasses() {

        try {
            // Get the collection from the database
            MongoCollection<Document> collection = database.getCollection("pokemons");

            // Create a Gson object
            Gson gson = new Gson();


            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String json = doc.toJson();

                // Use Gson to convert the JSON string to a Pokemon object
                Pokemon pokemon = gson.fromJson(json, Pokemon.class);

                // Add the Pokemon object to the list
                pokemons.add(pokemon);
            }

            showPokemons();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void showPokemons() {
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
        }
        System.out.println("List of pokemons: " + pokemons.size());
    }

    public void showTypes() {
        HashMap<String, Integer> types = new HashMap<String, Integer>();
        for (Pokemon pok : pokemons) {
            for (String pokemonType : pok.getType()) {
                if (!types.containsKey(pokemonType))
                    types.put(pokemonType, 1);
                else
                    types.put(pokemonType, types.get(pokemonType) + 1);


            }

        }

        for (String key : types.keySet()) {
            System.out.println(key + " : " + types.get(key));
        }

        System.out.println("Looking for the type that appears the most..");

        String type = "";
        int max = 0;

        for (Map.Entry<String, Integer> entry : types.entrySet()) {
            if (entry.getValue() > max) {
                type = entry.getKey();
                max = entry.getValue();
            }

        }

        System.out.println("The most common type is : " + type + ". Appeared " + max + " times.");

    }

    public void showEeveeEvolutions() {
        pokemons.get(132).showEvolutions();
    }


    public static void main(String[] args) {

        Main m = new Main();
        m.exportToClasses();
        m.showEeveeEvolutions();


    }
}