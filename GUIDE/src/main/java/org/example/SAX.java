package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Stack;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SAX extends DefaultHandler {



    Stack<String> stack;

    @Override
    public void startDocument() {
        System.out.println("<Begin>");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        stack.push(qName);
        if (qName.equalsIgnoreCase("library")) {
            /*
            library = new Library();
            library.setId(Integer.parseInt(attributes.getValue("id")));
            libraries.add(library);

             */
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (value.trim().length() > 0) {
            String tag = stack.peek();
            switch (tag) {
                case "address":
                    // do whats needed..
                    break;
                case "book":
                    /*
                    String[] parts = value.split(",");
                    Book book = new Book();
                    book.setId(parts[0]);
                    etc...

                     */
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

    public static void main(String[] args) {
        SAX p = new SAX();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse("libraries.xml", p);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}