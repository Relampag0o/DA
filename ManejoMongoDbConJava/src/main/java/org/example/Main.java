package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    MongoDatabase database = null;

    public Main() {
        openConnection();
    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("pokemon");
    }


    // Seleccionar todos los podemos de tipo veneno (Poison).
    public void showPoisonPkmns() {

        Bson filter = Filters.and(Filters.eq("type", "Poison"), Filters.size("type", 1));
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter);
        for (Document document : collection) {
            System.out.println(document);
        }
    }

    // Seleccionar 1 pokemon cuyo huevo eclosione a los 2 km.
    public void showPokemonWithEgg() {
        Bson filter = Filters.eq("egg", "2 km");
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).limit(1);
        for (Document document : collection) {
            System.out.println(document);
        }
    }

    // Mostrar todos los pokemon de tipo Fuego cuyo id sea mayor que 70. Mostrar sólo nombre, id y tipo.
    public void showFirePokemons() {
        Bson filter = Filters.and(Filters.eq("type", "Fire"), Filters.or(Filters.gt("_id", 70)));
        Bson projections = Projections.include("name", "_id", "type");
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).projection(projections);
        for (Document document : collection) {
            System.out.println(document);
        }

    }

    // Mostrar los pokemon cuyas debilidades contengan Poison, Ice o ambas.
    public void showWeaknesses() {
        Bson filter = Filters.or(Filters.eq("weaknesses", "Poison"), Filters.eq("weaknesses", "Ice"), Filters.all("weaknesses", "Poison", "Ice"));
        Bson projection = Projections.include("weaknesses");
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).projection(projection);
        for (Document document : collection) {
            System.out.println(document);
        }

    }

    // Actualizar los pokemon de tipo Poison para añadir un campo nuevo llamado: esPoison: true
    public void addIsPoison() {
        Bson update = Updates.set("isPoison", true);
        Bson filter = Filters.eq("type", "Poison");
        database.getCollection("pokemons").updateMany(new Document(), update);
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter);
        for (Document document : collection) {
            System.out.println(document);
        }


    }


    // Eliminar todos los pokemon que no tengan huevo.
    public void deletePokemons() {
        Bson filter = Filters.eq("egg", "Not in Eggs");

        database.getCollection("pokemons").deleteMany(filter);

        FindIterable<Document> collection = database.getCollection("pokemons").find();
        for (Document document : collection) {
            System.out.println(document);
        }


    }

    public void createCollection(String collectionName) {
        database.createCollection(collectionName);
        System.out.println("Collection successfully created!");
        MongoIterable<String> collections=database.listCollectionNames();

        System.out.println("Showing collections..");
        for (String collects: collections){
            System.out.println(collects);

        }

        System.out.println("Deleting collection " + collectionName);
        database.getCollection(collectionName).drop();
        System.out.println("Showing collections..");

        for (String collects: collections){
            System.out.println(collects);

        }



    }

    public static void main(String[] args) {

        Main m = new Main();
        //m.showPoisonPkmns();
        //m.showPokemonWithEgg();
        //m.showFirePokemons();
        //m.deletePokemons();
        m.createCollection("pepito");

    }
}