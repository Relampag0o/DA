package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.mongodb.client.*;
import org.bson.Document;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Sax extends DefaultHandler {
    Stack<String> stack;
    List<Person> persons;
    private Person person;
    private Pet pet;
    MongoDatabase database = null;


    public Sax() {
        openConnection();
        this.stack = new Stack<String>();
        this.persons = new ArrayList<Person>();
        this.person = null;
        this.pet = null;

    }

    @Override
    public void startDocument() {
        System.out.println("<Begin>");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        stack.push(qName);
        System.out.println("!QNAME:" + qName);
        if (qName.equalsIgnoreCase("person")) {
            /*
            library = new Library();
            library.setId(Integer.parseInt(attributes.getValue("id")));
            libraries.add(library);

             */
            person = new Person();
            person.setFirstname(attributes.getValue("firstname"));
            person.setLastname(attributes.getValue("lastname"));
            person.setCity(attributes.getValue("city"));
            person.setCountry(attributes.getValue("country"));
            person.setEmail(attributes.getValue("email"));
            persons.add(person);


        } else if (qName.equalsIgnoreCase("pet")) {
            this.pet = new Pet();
            person.addPet(pet);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (value.trim().length() > 0) {
            String tag = stack.peek();
            switch (tag) {
                case "name":
                    String[] parts = value.split(",");
                    this.pet.setName(parts[0]);

                    break;

                case "age":
                    parts = value.split(",");
                    this.pet.setAge(Integer.parseInt(parts[0]));
                    break;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        stack.pop();
        System.out.println("</" + qName + ">");
    }

    @Override
    public void endDocument() {
        System.out.println("<End>");

    }

    public void showPersons() {
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("persons");
    }

    public void exportToJson() {
        try {
            Gson gson = new Gson();

            // Get a MongoCollection from the MongoDatabase
            MongoCollection<Document> collection = database.getCollection("persons");

            // Iterate over the persons list
            for (Person person : this.persons) {
                // Convert each Person object to a JSON string
                String json = gson.toJson(person);

                // Create a Document object from the JSON string
                Document doc = Document.parse(json);

                // Insert the Document object into the MongoDB collection
                collection.insertOne(doc);
            }

            System.out.println("Data loaded...");
        } catch (Exception e) {
            System.out.println("An error occurred while exporting to JSON:");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Sax s = new Sax();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse("people.xml", s);
            s.showPersons();
            s.exportToJson();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}