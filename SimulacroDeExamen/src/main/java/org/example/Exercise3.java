package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Exercise3 {


    List<Cd> cds = new ArrayList<>();

    Cd cd;

    MongoDatabase database;

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

                if (node.getNodeName().equalsIgnoreCase("CD")) {
                    /*
                    this.library = new Library();
                    this.libraries.add(library);
                    library.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));

                     */
                    this.cd = new Cd();
                    this.cds.add(cd);

                }


                if (node.getNodeName().equalsIgnoreCase("book")) {
                    /*
                    this.book = new Book();
                    book.setId(node.getAttributes().getNamedItem("id").getNodeValue());
                    this.books.add(book);
                    this.libraries.get(libraries.size() - 1).addBook(book);
                    book.setLibrary_id(libraries.get(libraries.size() - 1).getId());

                     */

                }
                break;
            case Node.TEXT_NODE:
                String text = node.getNodeValue();
                if (text.trim().length() > 0) {
                    System.out.println(node.getNodeName() + "[" + node.getNodeValue() + "]");
                }

                System.out.println(node.getNodeValue());
                switch (node.getParentNode().getNodeName()) {

                    /*
                    case "address":
                        library.setAddress(node.getNodeValue());
                        break;
                    case "author":
                        book.setAuthor(node.getNodeValue());
                        break;
                    case "title":
                        book.setTitle(node.getNodeValue());
                        break;
                    case "genre":
                        book.setGenre(node.getNodeValue());
                        break;
                    case "price":
                        book.setPrice(Double.parseDouble(node.getNodeValue()));
                        break;
                    case "publish_date":
                        book.setPublish_date(node.getNodeValue());
                        break;
                    case "description":
                        book.setDescription(node.getNodeValue());
                        break;

                     */

                    case "TITLE":
                        cd.setTITLE(node.getNodeValue());
                        break;
                    case "ARTIST":
                        cd.setARTIST(node.getNodeValue());
                        break;
                    case "COUNTRY":
                        cd.setCOUNTRY(node.getNodeValue());
                        break;
                    case "COMPANY":
                        cd.setCOMPANY(node.getNodeValue());
                        break;
                    case "PRICE":
                        cd.setPRICE(Double.parseDouble(node.getNodeValue()));
                        break;
                    case "YEAR":
                        cd.setPRICE(Integer.parseInt(node.getNodeValue()));
                        break;

                }
                break;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            showNode(childNodes.item(i), level + 1);
        }
    }

    public void showCds() {
        for (Cd cd : cds) {
            System.out.println(cd);
        }
    }

    public void openConnection() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        System.out.println("Connected to the database successfully");
        database = mongoClient.getDatabase("cds");
    }

    public void exportToMongo() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MongoCollection<org.bson.Document> collection = database.getCollection("cds");

        for (Cd cd : cds) {
            String json = gson.toJson(cd);
            org.bson.Document doc = org.bson.Document.parse(json);
            collection.insertOne(doc);

        }
        System.out.println("Data succesfully added!");
    }


    public static void main(String[] args) {
        Exercise3 exercise3 = new Exercise3();
        File file = new File("cd_catalog.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            exercise3.showNode(doc, 0);
            exercise3.showCds();
            exercise3.openConnection();
            exercise3.exportToMongo();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
