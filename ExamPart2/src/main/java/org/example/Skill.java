package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Skill {

    private String name;
    private String level;

    public Skill(){

    }

    public Skill(String name, String level){
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public void toXML(Document doc, Element element) {
        Element skill = doc.createElement("skill");
        element.appendChild(skill);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(this.name));
        skill.appendChild(name);

        Element level = doc.createElement("level");
        level.appendChild(doc.createTextNode(this.level));
        skill.appendChild(level);
    }
}
