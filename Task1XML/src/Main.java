import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private Connection c;

    private List<Book> books;

    private List<Library> libraries;
    private List<String> genres;

    private Book book;
    private Library library;


    public Main() {
        this.books = new ArrayList<Book>();
        this.libraries = new ArrayList<Library>();
        this.genres = new ArrayList<String>();

    }

    public void openConnection() {
        String db = "xmltask2";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port + "/" + db;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected to " + db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

                if (node.getNodeName().equalsIgnoreCase("library")) {
                    this.library = new Library();
                    this.libraries.add(library);
                    library.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));
                }

                // THIS CODE IS FROM THE PREVIOUS EXERCISE...

                if (node.getNodeName().equalsIgnoreCase("book")) {
                    this.book = new Book();
                    book.setId(node.getAttributes().getNamedItem("id").getNodeValue());
                    this.books.add(book);
                    this.libraries.get(libraries.size() - 1).addBook(book);
                    book.setLibrary_id(libraries.get(libraries.size() - 1).getId());

                }
                break;
            case Node.TEXT_NODE:
                String text = node.getNodeValue();
                if (text.trim().length() > 0) {
                    System.out.println(node.getNodeName() + "[" + node.getNodeValue() + "]");
                }

                System.out.println(node.getNodeValue());
                switch (node.getParentNode().getNodeName()) {

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

                }
                break;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            showNode(childNodes.item(i), level + 1);
        }
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println("Books number: " + books.size());
    }

    public void showLibraries() {
        for (Library lib : libraries) {
            System.out.println(lib + " Books: " + "\n");
            lib.showBooks();
        }
        System.out.println("Libraries number: " + libraries.size());
    }

    public void exportToDatabase() {
        openConnection();
        PreparedStatement pst = null;
        try {
            c.setAutoCommit(true);
            pst = c.prepareStatement("INSERT INTO library (id, address) VALUES (?, ?)");
            for (Library lib : libraries) {
                pst.setInt(1, lib.getId());
                pst.setString(2, lib.getAddress());
                pst.executeUpdate();
                c.commit();
            }

            pst = c.prepareStatement("INSERT INTO book (id, author, title, genre, price, publish_date, description, library_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            for (Book b : books) {
                pst.setString(1, b.getId());
                pst.setString(2, b.getAuthor());
                pst.setString(3, b.getTitle());
                pst.setString(4, b.getGenre());
                pst.setDouble(5, b.getPrice());
                pst.setString(6, b.getPublish_date());
                pst.setString(7, b.getDescription());
                pst.setInt(8, b.getLibrary_id());
                pst.executeUpdate();
                c.commit();
            }

            pst.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        File file = new File("libraries.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            m.showNode(doc, 0);
            m.exportToDatabase();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
