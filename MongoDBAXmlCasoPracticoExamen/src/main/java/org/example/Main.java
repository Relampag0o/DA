package org.example;

import com.google.gson.Gson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
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
        /*
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

         */

        mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("libraries");
    }

    public void importFromMongo() {
        Gson gson = new Gson();
        MongoCollection<org.bson.Document> collection = database.getCollection("libraries");
        FindIterable<org.bson.Document> results = collection.find();
        for (org.bson.Document document : results) {
            Library lib = gson.fromJson(document.toJson(), Library.class);
            libraryList.add(lib);
        }

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

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("libraries");
            doc.appendChild(rootElement);

            for (Library library : m.libraryList) {
                library.toXML(doc);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("libraries.xml"));

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}