import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Parser extends DefaultHandler {

    List<Library> libraries;
    Stack<String> stack;

    Library library;
    private Connection c;


    public Parser() {
        this.libraries = new ArrayList<Library>();
        this.stack = new Stack<String>();
    }

    @Override
    public void startDocument() {
        System.out.println("<Begin>");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        stack.push(qName);
        if (qName.equalsIgnoreCase("library")) {
            library = new Library();
            library.setId(Integer.parseInt(attributes.getValue("id")));
            libraries.add(library);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (value.trim().length() > 0) {
            String tag = stack.peek();
            switch (tag) {
                case "address":
                    library.setAddress(value);
                    break;
                case "book":
                    String[] parts = value.split(",");
                    Book book = new Book();
                    book.setId(parts[0]);
                    book.setAuthor(parts[1]);
                    book.setTitle(parts[2]);
                    book.setGenre(parts[3]);
                    book.setPrice(Double.parseDouble(parts[4]));
                    book.setPublish_date(parts[5]);
                    book.setDescription(parts[6]);
                    book.setLibrary_id(Integer.parseInt(parts[7]));
                    library.addBook(book);
                    break;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        stack.pop();
        System.out.println("</" + qName + ">");
    }

    @Override
    public void endDocument() {
        System.out.println("<End>");

    }

    public void showLibraries() {
        for (Library library : libraries) {
            System.out.println(library);
        }
    }

    // FIX THAT WITH OPENCONECCTION METHOD

    public void exportDataToDb() {
        PreparedStatement pst = null;
        try {
            pst = this.c.getConnection().prepareStatement("INSERT INTO libraries VALUES (?, ?)");
            for (Library library : libraries) {
                pst.setInt(1, library.getId());
                pst.setString(2, library.getAddress());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();


        }

    }

    public static void main(String[] args) {
        Parser p = new Parser();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse("libraries.xml", p);
            p.showLibraries();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}