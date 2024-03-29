import java.io.BufferedWriter;
import java.io.File;
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

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", books=" + books +
                '}';
    }

    public void toJson(BufferedWriter bfw, boolean isLastLibrary) {
        try {
            bfw.write("{\n");
            bfw.write("\"address\": \"" + this.address + "\",\n");
            bfw.write("\"books\": [\n");
            for (int i = 0; i < books.size(); i++) {
                books.get(i).toJson(bfw, i == books.size() - 1);
            }
            bfw.write("]\n");
            bfw.write("}");
            if (!isLastLibrary) {
                bfw.write(",\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
