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

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    private List<User> users;


    public Main() {
        this.users = new ArrayList<>();
    }

    public void importFromJson() {
        Gson gson = new Gson();

        Type type = new TypeToken<List<User>>() {
        }.getType();

        try {

            this.users = gson.fromJson(new BufferedReader(new FileReader("users.json")), type);
            System.out.println("Data loaded...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.importFromJson();
        main.showUsers();


        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            for (User user : main.users) {
                user.toXML(doc, rootElement);
            }

            System.out.println("XML created...");



            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("usersExam.xml"));

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}