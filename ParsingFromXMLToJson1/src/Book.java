import java.io.BufferedWriter;

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

    public void toJson(BufferedWriter bfw, boolean isLastBook) {
        try {
            bfw.write("{\n");
            bfw.write("\"id\": \"" + this.id + "\",\n");
            bfw.write("\"author\": \"" + this.author + "\",\n");
            bfw.write("\"title\": \"" + this.title + "\",\n");
            bfw.write("\"genre\": \"" + this.genre + "\",\n");
            bfw.write("\"price\": " + this.price + ",\n");
            bfw.write("\"publish_date\": \"" + this.publish_date + "\",\n");
            bfw.write("\"description\": \"" + this.description + "\"\n");
            bfw.write("}");
            if (!isLastBook) {
                bfw.write(",\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }
}
