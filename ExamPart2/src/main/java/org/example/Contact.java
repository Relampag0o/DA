package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Contact {
    private String email;
    private String phone;


    public Contact() {
    }
    public Contact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void toXML(Document document,Element element) {
        Element contact = document.createElement("contact");
        element.appendChild(contact);

        Element email = document.createElement("email");
        email.appendChild(document.createTextNode(this.email));
        contact.appendChild(email);

        Element phone = document.createElement("phone");
        phone.appendChild(document.createTextNode(this.phone));
        contact.appendChild(phone);
    }
}
