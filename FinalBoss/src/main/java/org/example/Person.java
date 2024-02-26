package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstname;
    private String lastname;
    private String city;
    private String country;
    private String email;
    public List<Pet> pets;


    public Person() {
        this.pets = new ArrayList<Pet>();

    }

    public Person(String firstname, String lastname, String city, String country, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.country = country;
        this.email = email;
        this.pets = new ArrayList<Pet>();

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public List<Pet> getPets() {
        return pets;
    }


    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", pets=" + pets +
                '}';
    }
}
