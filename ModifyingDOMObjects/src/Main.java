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

                if (node.getNodeName().equalsIgnoreCase("book")) {
                    this.book = new Book();
                    book.setId((node.getAttributes().getNamedItem("id").getNodeValue()));
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

    public void showLibraries() {
        for (Library library : libraries) {
            System.out.println(library);
            library.showBooks();
        }
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void showGenres() {
        for (String genre : genres) {
            System.out.println(genre);
        }
    }

    public void increasePrice(double amount) {
        for (Book book : books) {
            int id = book.getId().charAt(book.getId().length() - 1);
            if (id % 2 == 0)
                book.setPrice(book.getPrice() + amount);
        }
    }

    public void addBookToLibrary1(Book book) {
        for (Library library : libraries) {
            if (library.getId() == 1)
                library.addBook(book);
        }
    }

    public void deleteBooks() {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getPrice() < 5)
                books.remove(i);
        }
    }

    public void moveBooksFromLib2ToLib3(String idBook) {
        for (Book book : this.books) {
            if (book.getLibrary_id() == 2 && book.getId().equalsIgnoreCase(idBook)) {
                for (Library library : this.libraries) {
                    if (library.getId() ==2){
                        library.deleteBook(idBook);
                    }else if (library.getId() == 3){
                        library.addBook(book);

                    }
                }

            }
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
            m.moveBooksFromLib2ToLib3("bk105");
            m.showLibraries();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}