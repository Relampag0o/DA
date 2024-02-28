package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private boolean isStudent;
    private double height;
    private Address address;
    private Contact contact;
    private List<String> hobbies;

    private List<Skill> skills;


    public User() {

        this.hobbies = new ArrayList<>();
        this.skills = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isStudent=" + isStudent +
                ", height=" + height +
                ", address=" + address +
                ", contact=" + contact +
                ", hobbies=" + hobbies +
                ", skills=" + skills +
                '}';
    }

    public void toXML(Document doc, Element users){
        Element user = doc.createElement("user");
        users.appendChild(user);

        Element nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(this.name));
        user.appendChild(nameElement);

        Element ageElement = doc.createElement("age");
        ageElement.appendChild(doc.createTextNode(String.valueOf(this.age)));
        user.appendChild(ageElement);

        Element isStudentElement = doc.createElement("isStudent");
        isStudentElement.appendChild(doc.createTextNode(String.valueOf(this.isStudent)));
        user.appendChild(isStudentElement);

        Element heightElement = doc.createElement("height");
        heightElement.appendChild(doc.createTextNode(String.valueOf(this.height)));
        user.appendChild(heightElement);

        Element hobbiesElement = doc.createElement("hobbies");
        user.appendChild(hobbiesElement);

        for (String hobby : this.hobbies) {
            Element hobbyElement = doc.createElement("hobby");
            hobbyElement.appendChild(doc.createTextNode(hobby));
            hobbiesElement.appendChild(hobbyElement);
        }

        this.address.toXML(doc, user);
        this.contact.toXML(doc, user);

        Element skillsElement = doc.createElement("skills");
        user.appendChild(skillsElement);

        for (Skill skill : this.skills) {
            skill.toXML(doc, skillsElement);
        }
    }
}
