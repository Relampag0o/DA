package org.example;

import com.mongodb.client.MongoIterable;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class DOM {
    MongoIterable database;
    List<Food> foodList;

    Food food;

    public DOM() {
        this.foodList = new ArrayList<Food>();
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

    }

    public void showFoodList() {
        for (Food food : this.foodList) {
            System.out.println(food);
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


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}