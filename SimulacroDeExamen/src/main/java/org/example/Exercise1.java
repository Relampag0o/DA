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
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Exercise1 {
    ArrayList<Team> teams = new ArrayList<>();

    public void loadData() {
        for (int i = 0; i < 15; i++) {
            Team t = new Team();
            t.set_id(i);
            t.set_city("City_" + i);
            t.set_name("Team_" + i);
            t.set_rate(Math.random() * 100);
            for (int j = 0; j < (i + 3) % 10; j++) {
                Player p = new Player(j, "Player_" + j, "Italy", Math.random() * 50);
                t._teamMates.add(p);
            }
            teams.add(t);
        }
    }

    public void showData() {
        for (Team t : teams) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        Exercise1 exercise1 = new Exercise1();
        exercise1.loadData();
        exercise1.showData();
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("players");
            doc.appendChild(rootElement);

            for (Team team : exercise1.teams) {
                team.toXML(doc, rootElement);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("players.xml"));

            transformer.transform(source, result);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
}