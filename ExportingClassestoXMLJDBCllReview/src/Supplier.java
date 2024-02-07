import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private int id;
    private String name;
    private String country;

    private List<Product> products;




    public Supplier() {
        this.id = -1;
        this.name = "";
        this.country = "";
        this.products = new ArrayList<>();
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

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void toXML(Document doc) {
        Element suppliers = doc.getDocumentElement();

        Element supplier = doc.createElement("supplier");
        suppliers.appendChild(supplier);

        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(String.valueOf(this.id)));
        supplier.appendChild(id);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(this.name));
        supplier.appendChild(name);

        Element country = doc.createElement("country");
        country.appendChild(doc.createTextNode(this.country));
        supplier.appendChild(country);

        Element products = doc.createElement("products");
        supplier.appendChild(products);

        for (Product product : this.products) {
            product.toXML(doc, products);
        }
    }

}
