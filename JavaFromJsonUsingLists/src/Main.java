import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Person>>() {}.getType();
            ArrayList<Person> people = gson.fromJson(new BufferedReader(new FileReader("personas.json")), type);
            for (Person person : people) {
                System.out.println(person);
            }


        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

}