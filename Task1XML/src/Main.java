import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void showNode(Node node, int level)
    {
        for(int i=0; i<level;i++) {
            System.out.print(" ");
        }
        switch(node.getNodeType()) {
            case Node.DOCUMENT_NODE:
                Document doc = (Document) node;
                System.out.println("Documento DOM. Versión: " + doc.getXmlVersion() + " . Codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:
                System.out.print("<" + node.getNodeName());
                NamedNodeMap attributes = node.getAttributes();
                for(int i=0; i<attributes.getLength();i++)
                {
                    Node attribute = attributes.item(i);
                    System.out.print("@" + attribute.getNodeName() + "[" + attribute.getNodeValue() + "]");
                }
                System.out.println(">");
                break;
            case Node.TEXT_NODE:
                String text = node.getNodeValue();
                if(text.trim().length()>0) {
                    System.out.println(node.getNodeName() + "[" + node.getNodeValue() + "]");
                }
                break;
        }
        NodeList childNodes = node.getChildNodes();
        for(int i=0; i<childNodes.getLength(); i++) {
            showNode(childNodes.item(i), level+1);
        }
    }

    public static void main(String[] args) {
        {
            File file = new File("XMLFile.xml");
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                showNode(doc,0);

            } catch(Exception e) {
                e.printStackTrace();
            }

        }

    }
}