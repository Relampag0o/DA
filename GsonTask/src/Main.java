import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public void exercise1() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("json1.json"));
            Gson gson = new Gson();
            Properties properties = gson.fromJson(bfr, Properties.class);
            System.out.println(properties.getProperty("loginNumber"));
            System.out.println(properties.getProperty("username"));
            System.out.println(properties.getProperty("password"));
            System.out.println(properties.getProperty("loginDate"));
            System.out.println(properties.getProperty("isAdmin"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void exercise2() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("json2.json"));
            Gson gson = new Gson();
            Employee employee = gson.fromJson(bfr, Employee.class);
            System.out.println(employee);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Main main = new Main();
        //main.exercise1();
        main.exercise2();

    }
}