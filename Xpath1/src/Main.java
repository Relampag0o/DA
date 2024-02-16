import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String filename = "plant_catalog.xml";
        // EXERCISE 1:
        // 1. "/CATALOG/PLANT/PRICE"
        // 2. "/CATALOG/PLANT[last()]"
        // 3. "/CATALOG/PLANT[PRICE>5.0 and PRICE<10.0]"
        // 4. "/CATALOG/PLANT[ZONE=4]"

        // EXERCISE 2

        // "/*"


        String xPath = "/*";
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filename));
            XPathExpression xPathExp = XPathFactory.newInstance().newXPath().compile(xPath);
            NodeList results = (NodeList) xPathExp.evaluate(doc, XPathConstants.STRING);
            for(int i = 0; i < results.getLength(); i++){
                System.out.println(results.item(i).getTextContent());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}