package org.example;

import com.google.gson.Gson;
import com.mongodb.client.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;




import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class DOM {
    MongoDatabase database;
    List<Food> foodList;

    Food food;

    public DOM() {
        this.foodList = new ArrayList<Food>();
        openConnection();
    }

    public void showNode(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        switch (node.getNodeType()) {
            case Node.DOCUMENT_NODE:
                Document doc = (Document) node;
                System.out.println("Documento DOM. Versión: " + doc.getXmlVersion() + " . Codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:
                System.out.print("<" + node.getNodeName());
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    System.out.print("@" + attribute.getNodeName() + "[" + attribute.getNodeValue() + "]");
                }
                System.out.println(">");

                if (node.getNodeName().equalsIgnoreCase("food")) {
                    /*
                    this.library = new Library();
                    this.libraries.add(library);
                    library.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));

                     */

                    this.food = new Food();
                    this.foodList.add(food);
                }

                break;
            case Node.TEXT_NODE:
                String text = node.getNodeValue();
                if (text.trim().length() > 0) {
                    System.out.println(node.getNodeName() + "[" + node.getNodeValue() + "]");
                }

                System.out.println(node.getNodeValue());
                switch (node.getParentNode().getNodeName()) {

                    case "name":
                        this.food.setName(node.getNodeValue());

                        break;

                    case "price":
                        this.food.setPrice(Double.parseDouble(node.getNodeValue()));

                        break;
                    case "description":
                        this.food.setDescription(node.getNodeValue());

                        break;
                    case "calories":
                        this.food.setCalories(Integer.parseInt(node.getNodeValue()));

                        break;

                }
                break;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            showNode(childNodes.item(i), level + 1);
        }
    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("foods");
    }

    public void showFoodList() {
        for (Food food : this.foodList) {
            System.out.println(food);
        }
    }
    public void exportToJson() {
        try {
            Gson gson = new Gson();

            // Get a MongoCollection from the MongoDatabase
            MongoCollection<org.bson.Document> collection = database.getCollection("foods");


            // Iterate over the persons list
            for (Food food : this.foodList) {
                // Convert each Person object to a JSON string
                String json = gson.toJson(food);

                // Create a Document object from the JSON string
                org.bson.Document doc = org.bson.Document.parse(json);

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
        DOM d = new DOM();
        File file = new File("food_menu.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            d.showNode(doc, 0);
            d.showFoodList();
            d.exportToJson();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}