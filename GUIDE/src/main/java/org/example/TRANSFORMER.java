package org.example;

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

public class TRANSFORMER {
    public static void main(String[] args) {
        // EXAMPLE OF TOXML FROM A CHILD CLASS:
        /*
        public void toXML(Document doc){
         Element workers = doc.getDocumentElement();
         Element worker = doc.createElement("worker");
         workers.appendChild(worker);

         Element fullNameElement = doc.createElement("full_name");
         fullNameElement.appendChild(doc.createTextNode(this.full_name));
         worker.appendChild(fullNameElement);

        Element jobTitleElement = doc.createElement("jobTitle");
        jobTitleElement.appendChild(doc.createTextNode(this.jobTitle));
        worker.appendChild(jobTitleElement);

        Element addressesElement = doc.createElement("addresses");
        worker.appendChild(addressesElement);

        this.address.toXML(doc,addressesElement);

        this.company.toXML(doc,addressesElement);

    }

    TODO: Si hay atributes a la hora de crear la clase se hace asi:
    Attr attribute = doc.createAttribute("firstname");
        attribute.setValue(this.full_name);
        worker.setAttributeNode(attribute);

         */


        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("suppliers");
            doc.appendChild(rootElement);

            // CHANGE THIS METHOD TO THE CLASS NEEDED ONE
            //supplier.toXML(doc);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("CHANGE THE FILE NAME!!!"));

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
