package org.example;

public class Pokemon {
    private String name;
    private String type;
    private String description;
    private Evolution evolution;


    public Pokemon() {
    }
    public Pokemon(String name, String type, String description, Evolution evolution) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.evolution = evolution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Evolution getEvolution() {
        return evolution;
    }

    public void setEvolution(Evolution evolution) {
        this.evolution = evolution;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", evolution=" + evolution +
                '}';
    }
}
