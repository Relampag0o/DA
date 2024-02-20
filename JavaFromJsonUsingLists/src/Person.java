import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Person {
    private int id;


    @SerializedName("nombre")
    private String name;

    @SerializedName("fecha")
    private String date;

    @SerializedName("perro")
    private Dog dog;

    @SerializedName("perro")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", " + dog +
                '}';
    }
}
