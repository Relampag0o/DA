package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Company {
    private String name;
    private Address address;


    public Company(String name, Address address) {
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void toXML(Document doc, Element father) {
        Element companyElement = doc.createElement("companies");
        father.appendChild(companyElement);

        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(this.name));
        companyElement.appendChild(nameElement);

        Element addressesElement = doc.createElement("addresses");
        companyElement.appendChild(addressesElement);

        this.address.toXML(doc,addressesElement);


    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
