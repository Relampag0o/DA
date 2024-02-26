package org.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    List<Library> libraryList;
    MongoDatabase database;
    MongoClient mongoClient;


    public Main() {
        this.libraryList = new ArrayList<Library>();
        openConnection();

    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";

        // Create a CodecRegistry containing the PojoCodecProvider instance
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .register(Library.class)
                .register(Book.class) // Register the Book class
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider));

        // Specify the CodecRegistry when creating the MongoClient
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();

        mongoClient = MongoClients.create(settings);

        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("libraries");
    }

    public void importFromMongo() {
        MongoCollection<Library> collection = database.getCollection("libraries", Library.class);
        collection.find().into(this.libraryList);

        showLibraries();


    }

    public void showLibraries() {
        for (Library library : this.libraryList) {
            System.out.println(library);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.importFromMongo();
        m.showLibraries();

    }
}