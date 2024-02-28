package org.example;

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
public class Main {


    private List<Pokemon> pokemons;
    private Pokemon pokemon;
    private Evolution evolution;

    public Main() {
        this.pokemons = new ArrayList<>();
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

                if (node.getNodeName().equalsIgnoreCase("pokemon")) {
                    /*
                    this.library = new Library();
                    this.libraries.add(library);
                    library.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));

                     */
                    this.pokemon = new Pokemon();
                    this.pokemons.add(this.pokemon);


                }else if (node.getNodeName().equalsIgnoreCase("evolution")){
                    this.evolution = new Evolution();
                    this.pokemon.setEvolution(evolution);
                    evolution.setName(node.getAttributes().getNamedItem("name").getNodeValue());
                    evolution.setMethod(node.getAttributes().getNamedItem("method").getNodeValue());
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
                        pokemon.setName(node.getNodeValue());
                        break;
                    case "type":
                        pokemon.setType(node.getNodeValue());
                        break;
                    case "description":
                        pokemon.setDescription(node.getNodeValue());
                        break;


                }
                break;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            showNode(childNodes.item(i), level + 1);
        }
    }

    public void showPokemons() {
        for (Pokemon pokemon : pokemons) {
            System.out.println(pokemon);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        File file = new File("pokemons.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            m.showNode(doc, 0);
            m.showPokemons();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}