import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Book {

    private String id;

    private String author;
    private String title;
    private String genre;
    private double price;
    private String publish_date;
    private String description;
    private int library_id;


    public Book() {
        this.id = "";
        this.author = "";
        this.title = "";
        this.genre = "";
        this.price = 0;
        this.publish_date = "";
        this.description = "";
        this.library_id = -1;

    }

    public Book(String id, String author, String title, String genre, double price, String publish_date, String description, int library_id) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publish_date = publish_date;
        this.description = description;
        this.library_id = library_id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", publish_date='" + publish_date + '\'' +
                ", description='" + description + '\''
                + ", library_id=" + library_id + '}';
    }

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }


    public void toXML(Document doc, Element books) {
        Element book = doc.createElement("book");
        books.appendChild(book);

        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(this.id));
        book.appendChild(id);

        Element author = doc.createElement("author");
        author.appendChild(doc.createTextNode(this.author));
        book.appendChild(author);

        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode(this.title));
        book.appendChild(title);

        Element genre = doc.createElement("genre");
        genre.appendChild(doc.createTextNode(this.genre));
        book.appendChild(genre);

        Element price = doc.createElement("price");
        price.appendChild(doc.createTextNode(String.valueOf(this.price)));
        book.appendChild(price);

        Element publish_date = doc.createElement("publish_date");
        publish_date.appendChild(doc.createTextNode(this.publish_date));
        book.appendChild(publish_date);

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(this.description));
        book.appendChild(description);
    }
}
