package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
    private List<Worker> workers;


    public Exercise2() {
        this.workers = new ArrayList<Worker>();
    }

    public void exportToClasses() {
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Worker>>() {
            }.getType();
            this.workers = gson.fromJson(new BufferedReader(new FileReader("workers.json")), type);

            for (Worker worker : workers) {
                System.out.println(worker);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Exercise2 exercise2 = new Exercise2();
        exercise2.exportToClasses();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("workers");
            doc.appendChild(rootElement);


            // CHANGE THIS METHOD TO THE CLASS NEEDED ONE
            for (Worker worker : exercise2.workers) {
                worker.toXML(doc);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("WORKERS.xml"));

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}
