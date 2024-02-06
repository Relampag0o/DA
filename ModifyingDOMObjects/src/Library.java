import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int id;
    private String address;
    private List<Book> books;


    public Library() {
        this.id = -1;
        this.address = "";
        this.books = new ArrayList<Book>();
    }

    public Library(int id, String address) {
        this.id = id;
        this.address = address;
        this.books = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void showBooks() {
        if (this.books.size() == 0) {
            System.out.println("There are no books in this library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void deleteBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equalsIgnoreCase(id)) {
                books.remove(i);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    public void toXML(Document doc){
        Element libraries = doc.getDocumentElement();
        Element library = doc.createElement("library");
        libraries.appendChild(library);

        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(String.valueOf(this.id)));
        library.appendChild(id);

        Element address = doc.createElement("address");
        address.appendChild(doc.createTextNode(this.address));
        library.appendChild(address);

        Element books = doc.createElement("books");
        library.appendChild(books);

        for (Book book : this.books) {
            book.toXML(doc, books);
        }




    }
}
