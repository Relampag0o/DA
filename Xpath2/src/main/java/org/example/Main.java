package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String filename = "books.xml";
        // EXERCISE 1:
        // 1. "/CATALOG/PLANT/PRICE"
        // 2. "/CATALOG/PLANT[last()]"
        // 3. "/CATALOG/PLANT[PRICE>5.0 and PRICE<10.0]"
        // 4. "/CATALOG/PLANT[ZONE=4]"



        // Selecciona la categoría de los libros que tengan 3 o más autores

        // "//book[count(author) >= 3]/genre"

        // Selecciona el título de los libros cuyo precio sea mayor a 30 y cuya categoría sea web

        //  "//book[price > 30]/title"

        // Selecciona el último autor del libro cuyo título sea 'XQuery Kick Start'
        // "//book[title ='XQuery Kick Start']/author[last()]";

        // Selecciona el lenguaje de los libros cuya categoría sea distinta de 'web'

        //  "//book[@category != 'web']/title/@lang"

        // Selecciona el año de los libros que tengan cubierta (cover).

        // "//book[@cover]/year"

        String xPath = "//book[@cover]/year";
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename));
            XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);
            NodeList results = (NodeList) xPathExp.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < results.getLength(); i++) {
                System.out.println(results.item(i).getTextContent());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}