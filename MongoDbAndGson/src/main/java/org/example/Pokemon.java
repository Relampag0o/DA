package org.example;

import java.util.Arrays;

public class Pokemon {
    private int _id;
    private int num;
    private String name;
    private String img;
    private String[] type;
    private String height;
    private String weight;
    private String candy;
    private double spawn_chance;
    private double avg_spawns;
    private double[] multipliers;
    private String[] weaknesses;
    private Object[] next_evolution;

    public Pokemon(int _id, int num, String name, String img, String[] type, String height, String weight, String candy, double spawn_chance, double avg_spawns, double[] multipliers, String[] weaknesses, Object[] next_evolution) {
        this._id = _id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.candy = candy;
        this.spawn_chance = spawn_chance;
        this.avg_spawns = avg_spawns;
        this.multipliers = multipliers;
        this.weaknesses = weaknesses;
        this.next_evolution = next_evolution;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public double getSpawn_chance() {
        return spawn_chance;
    }

    public void setSpawn_chance(double spawn_chance) {
        this.spawn_chance = spawn_chance;
    }

    public double getAvg_spawns() {
        return avg_spawns;
    }

    public void setAvg_spawns(double avg_spawns) {
        this.avg_spawns = avg_spawns;
    }

    public double[] getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(double[] multipliers) {
        this.multipliers = multipliers;
    }

    public String[] getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Object[] getNext_evolution() {
        return next_evolution;
    }

    public void setNext_evolution(Object[] next_evolution) {
        this.next_evolution = next_evolution;
    }

    public void showEvolutions(){
        for (Object evo: this.next_evolution){
            System.out.println(evo.toString());
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "_id=" + _id +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", type=" + Arrays.toString(type) +
                ", height=" + height +
                ", weight=" + weight +
                ", candy='" + candy + '\'' +
                ", spawn_chance=" + spawn_chance +
                ", avg_spawns=" + avg_spawns +
                ", multipliers=" + Arrays.toString(multipliers) +
                ", weaknesses=" + Arrays.toString(weaknesses) +
                ", next_evolution=" + Arrays.toString(next_evolution) +
                '}';
    }
}
