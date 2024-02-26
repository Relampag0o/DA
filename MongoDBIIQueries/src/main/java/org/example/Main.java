package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
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

    // Seleccionar los 10 primeros pokemon que tengan 2 evoluciones siguientes. Ordenar la salida por nombre del pokemon Ascendente (Z a la A).
    public void get10FirstPokemons() {

        Bson filter = Filters.size("next_evolution", 2);
        Bson sorter = Sorts.ascending("name");


        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).sort(sorter);
        for (Document document : collection) {
            System.out.println(document);
        }

    }

    // Seleccionar el segundo pokemon cuyo huevo eclosione a los 2 km.
    public void getSecondPokemonWith2KmEgg() {

        Bson filter = Filters.eq("egg", "2 km");

        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).skip(1).limit(1);
        for (Document document : collection) {
            System.out.println(document);
        }

    }

    // Mostrar todos los pokemon cuyo id sea mayor a 50 y menor que 60.
    public void showIds() {
        Bson filter = Filters.and(Filters.gt("_id", 50), Filters.lt("_id", 60));

        FindIterable<Document> collection = database.getCollection("pokemons").find(filter);
        for (Document document : collection) {
            System.out.println(document);
        }


    }


    // Mostrar todos los pokemon que sean una evolución intermedia (es decir, que tenga evolución previa y evolución siguiente). Se deberá mostrar sólo el nombre de ese pokemon, de la evolución previa y de la evolución siguiente.

    public void showEvolutions() {
        Bson filter = Filters.and(Filters.size("prev_evolution", 1), Filters.size("next_evolution", 1));
        Bson projection = Projections.include("name", "prev_evolution", "next_evolution");
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).projection(projection);
        for (Document document : collection) {
            System.out.println(document);
        }

    }

    // Mostrar los pokemon que tengan el campo candy_count y que "Grass" NO esté entre sus debilidades.

    public void showPokemonsWithCandy_Count() {
        Bson filter = Filters.and(Filters.exists("candy_count"), Filters.nin("weaknesses", "Grass"));
        Bson projections = Projections.include("candy_count", "weaknesses");
        FindIterable<Document> collection = database.getCollection("pokemons").find(filter).projection(projections);
        for (Document document : collection) {
            System.out.println(document);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        //m.get10FirstPokemons();
        //m.getSecondPokemonWith2KmEgg();
        //m.showIds();
        //m.showEvolutions();
        //m.showPokemonsWithCandy_Count();


    }
}