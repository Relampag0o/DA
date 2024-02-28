package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    private List<User> users;


    public Main() {
        this.users = new ArrayList<>();
    }

    public void importFromJson() {
        Gson gson = new Gson();

        Type type = new TypeToken<List<User>>() {
        }.getType();

        try {

            this.users = gson.fromJson(new BufferedReader(new FileReader("users.json")), type);
            System.out.println("Data loaded...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.importFromJson();
        main.showUsers();
    }
}