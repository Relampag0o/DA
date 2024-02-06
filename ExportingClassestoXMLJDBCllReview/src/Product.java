import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Product {
    private int id;
    private String name;
    private int price;
    private String currency;
    private int supplierId;


    public Product() {
        this.id = -1;
        this.name = " ";
        this.price = -1;
        this.currency = " ";
        this.supplierId = -1;

    }

    public Product( int id, String name, int price, String currency, int supplierId) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.supplierId = supplierId;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }


    public void toXML(Document doc,Element products) {
        Element productElement = doc.createElement("product");
        products.appendChild(productElement);
        Attr attr = doc.createAttribute("id");
        attr.setValue(String.valueOf(this.id));
        productElement.setAttributeNode(attr);
        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(this.name));
        productElement.appendChild(nameElement);
        Element priceElement = doc.createElement("price");
        priceElement.appendChild(doc.createTextNode(String.valueOf(this.price)));
        productElement.appendChild(priceElement);
        Element currencyElement = doc.createElement("currency");
        currencyElement.appendChild(doc.createTextNode(this.currency));
        productElement.appendChild(currencyElement);
        Element supplierIdElement = doc.createElement("supplierId");
        supplierIdElement.appendChild(doc.createTextNode(String.valueOf(this.supplierId)));
        productElement.appendChild(supplierIdElement);


    }
}
