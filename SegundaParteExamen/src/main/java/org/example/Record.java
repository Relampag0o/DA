package org.example;

import java.util.Arrays;
import java.util.List;

public class Record {
    private String name;
    private int numberrange;
    private String text;
    private String region;
    private boolean active;
    private List<String> languages;

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Record(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberrange() {
        return numberrange;
    }

    public void setNumberrange(int numberrange) {
        this.numberrange = numberrange;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", numberrange=" + numberrange +
                ", text='" + text + '\'' +
                ", region='" + region + '\'' +
                ", active=" + active +
                ", languages=" + languages +
                '}';
    }
}
