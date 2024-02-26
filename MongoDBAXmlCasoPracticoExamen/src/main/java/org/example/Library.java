package org.example;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;


public class Library {
    private ObjectId id;
    private String address;

    @BsonProperty("books")
    private List<Book> bookList;



    public Library(){

    }
    public Library(ObjectId id, String address) {
        this.id = id;
        this.address = address;
        this.bookList = new ArrayList<Book>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
