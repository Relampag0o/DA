package org.example;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Library {
    private String id;
    private String address;

    @SerializedName("books")
    private List<Book> bookList;


    public Library() {

    }

    public Library(String id, String address) {
        this.id = id;
        this.address = address;
        this.bookList = new ArrayList<Book>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void toXML(Document doc) {
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
         */
        Element libraries = doc.getDocumentElement();
        Element library = doc.createElement("library");
        libraries.appendChild(library);

        Element idElement = doc.createElement("id");
        idElement.appendChild(doc.createTextNode(this.id + " "));
        library.appendChild(idElement);

        Element addressElement = doc.createElement("address");
        addressElement.appendChild(doc.createTextNode(this.address));
        library.appendChild(addressElement);

        Element booksElement = doc.createElement("books");
        library.appendChild(booksElement);

        for (Book book : this.bookList) {
            book.toXML(doc, booksElement);
        }


    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
