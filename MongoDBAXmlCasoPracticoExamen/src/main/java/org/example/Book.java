package org.example;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Book {



    private String id;
    private String author;
    private String title;
    private String genre;
    private String price;

    private String publish_date;
    private String description;

    public Book() {
    }

    public Book(String id, String author, String title, String genre, String price, String publish_date, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void toXML(Document doc, Element books) {
        Element bookElement = doc.createElement("book");
        books.appendChild(bookElement);

        Element idElement = doc.createElement("id");
        idElement.appendChild(doc.createTextNode(this.id + " "));
        bookElement.appendChild(idElement);

        Element authorElement = doc.createElement("author");
        authorElement.appendChild(doc.createTextNode(this.author));
        bookElement.appendChild(authorElement);

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(this.title));
        bookElement.appendChild(titleElement);

        Element genreElement = doc.createElement("genre");
        genreElement.appendChild(doc.createTextNode(this.genre));
        bookElement.appendChild(genreElement);

        Element priceElement = doc.createElement("price");
        priceElement.appendChild(doc.createTextNode(this.price));
        bookElement.appendChild(priceElement);

        Element publishDateElement = doc.createElement("publish_date");
        publishDateElement.appendChild(doc.createTextNode(this.publish_date));
        bookElement.appendChild(publishDateElement);

        Element descriptionElement = doc.createElement("description");
        descriptionElement.appendChild(doc.createTextNode(this.description));
        bookElement.appendChild(descriptionElement);


    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", publish_date='" + publish_date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
