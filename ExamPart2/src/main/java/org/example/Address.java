package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Address {
    private String street;
    private String city;
    private String country;


    public Address() {
    }

    public Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public void toXML(Document document, Element element) {
        Element address = document.createElement("address");
        element.appendChild(address);

        Element street = document.createElement("street");
        street.appendChild(document.createTextNode(this.street));
        address.appendChild(street);

        Element city = document.createElement("city");
        city.appendChild(document.createTextNode(this.city));
        address.appendChild(city);

        Element country = document.createElement("country");
        country.appendChild(document.createTextNode(this.country));
        address.appendChild(country);
    }
}
