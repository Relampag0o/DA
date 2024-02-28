package org.example;

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
}
