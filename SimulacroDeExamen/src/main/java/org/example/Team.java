package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class Team {
    public int _id;
    public String _name;
    public String _city;
    public ArrayList<Player> _teamMates;
    public double _rate;

    public Team() {
        this._teamMates = new ArrayList<>();
    }

    /**
     * @return the _id
     */
    public int get_id() {
        return _id;
    }

    /**
     * @param _id the _id to set
     */
    public void set_id(int _id) {
        this._id = _id;
    }

    /**
     * @return the _name
     */
    public String get_name() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * @return the _city
     */
    public String get_city() {
        return _city;
    }

    /**
     * @param _city the _city to set
     */
    public void set_city(String _city) {
        this._city = _city;
    }

    /**
     * @return the _teamMates
     */
    public ArrayList<Player> get_teamMates() {
        return _teamMates;
    }

    /**
     * @param _teamMates the _teamMates to set
     */
    public void set_teamMates(ArrayList<Player> _teamMates) {
        this._teamMates = _teamMates;
    }

    /**
     * @return the _rate
     */
    public double get_rate() {
        return _rate;
    }

    /**
     * @param _rate the _rate to set
     */
    public void set_rate(double _rate) {
        this._rate = _rate;
    }

    @Override
    public String toString() {
        return "Team [_id=" + _id + ", _name=" + _name + ", _city=" + _city + ", _teamMates=" + _teamMates + ", _rate="
                + _rate + "]";
    }

    public void toXML(Document doc, Element players) {
        /*
         public int _id;
    public String _name;
    public String _city;
    public ArrayList<Player> _teamMates;
    public double _rate;
         */
        Element player = doc.createElement("team");
        players.appendChild(player);

        Element _idElement = doc.createElement("_idElement");
        _idElement.appendChild(doc.createTextNode(this._id + ""));
        player.appendChild(_idElement);

        Element _nameElement = doc.createElement("_name");
        _nameElement.appendChild(doc.createTextNode(this._name));
        player.appendChild(_nameElement);

        Element _city = doc.createElement("_city");
        _city.appendChild(doc.createTextNode(this._city));
        player.appendChild(_city);

        Element _teamMates = doc.createElement("_teamMates");
        player.appendChild(_teamMates);

        for (Player p : this._teamMates) {
            p.toXML(doc,_teamMates );

        }


    }

}
