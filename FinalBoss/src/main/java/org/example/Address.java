package org.example;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Address {
    private String street;
    private String city;


    public Address(String name, String city) {
        this.street = name;
        this.city = city;

    }

    public String getName() {
        return street;
    }

    public void setName(String name) {
        this.street = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void toXML(Document doc, Element father) {
        Element addressElement = doc.createElement("address");
        father.appendChild(addressElement);

        Element streetElement = doc.createElement("street");
        streetElement.appendChild(doc.createTextNode(this.street));
        addressElement.appendChild(streetElement);

        Element cityElement = doc.createElement("city");
        cityElement.appendChild(doc.createTextNode(this.city));
        addressElement.appendChild(cityElement);









    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
