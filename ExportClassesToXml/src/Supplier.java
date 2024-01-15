import java.util.LinkedList;

public class Supplier {
    private int id;
    private String name;
    private String country;
    private LinkedList<Product> products;


    public Supplier(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.products = new LinkedList<Product>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "<supplier> " + "id=" + id + ", name=" + name + ", country=" + country + "<products>" + products + "</products>" + "</supplier>";
    }
}
