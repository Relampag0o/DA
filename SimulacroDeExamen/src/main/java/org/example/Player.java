package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Player {
    public int _id;
    public String _name;
    public String _country;
    public double _reputation;

    public Player(int id, String name, String country, double reputation) {
        this._id = id;
        this._name = name;
        this._country = country;
        this._reputation = reputation;
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
     * @return the _country
     */
    public String get_country() {
        return _country;
    }

    /**
     * @param _country the _country to set
     */
    public void set_country(String _country) {
        this._country = _country;
    }

    /**
     * @return the _reputation
     */
    public double get_reputation() {
        return _reputation;
    }

    /**
     * @param _reputation the _reputation to set
     */
    public void set_reputation(double _reputation) {
        this._reputation = _reputation;
    }

    @Override
    public String toString() {
        return "Player [_id=" + _id + ", _name=" + _name + ", _country=" + _country + ", _reputation=" + _reputation
                + "]";
    }

    public void toXML(Document doc, Element _teamMates) {
		Element teamMate = doc.createElement("teamMate");
        _teamMates.appendChild(teamMate);

        /*
        public int _id;
    public String _name;
    public String _country;
    public double _reputation;
         */

        Element _id = doc.createElement("_id");
        _id.appendChild(doc.createTextNode(this._id + ""));
        teamMate.appendChild(_id);

        Element _name = doc.createElement("_name");
        _name.appendChild(doc.createTextNode(this._name));
        teamMate.appendChild(_name);

        Element _country = doc.createElement("_country");
        _country.appendChild(doc.createTextNode(this._country));
        teamMate.appendChild(_country);

        Element _reputation = doc.createElement("_reputation");
        _reputation.appendChild(doc.createTextNode(this._reputation+""));
        teamMate.appendChild(_reputation);









    }

}
