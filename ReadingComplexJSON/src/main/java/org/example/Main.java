package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private List<Person> persons;


    public Main() {
        this.persons = new ArrayList<Person>();
    }

    public void listPpl() throws FileNotFoundException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Person>>() {
        }.getType();
        this.persons = gson.fromJson(new BufferedReader(new FileReader("Expense.json")), type);
        showPpl();

    }

    public void showPpl() {
        for (Person person : persons) {
            System.out.println(person);
        }
    }

    public void getHighestExpense() {
        double max = 0;
        String person = "";
        for (Person ppl : persons) {
            if (ppl.getHighestExpense() > max) {
                max = ppl.getHighestExpense();
                person = ppl.getWHO();
            }

        }
        System.out.println("The person with the highest expense is " + person + " with " + max + " expense");
    }

    public void getAverageExpense() {
        double sum = 0;
        for (Person ppl : persons) {
            sum += ppl.averageExpense();
        }
        System.out.println("The average expense is " + sum / persons.size() + "€");
    }

    public void getHighestBillPrice() {
        double max = 0;
        String person = "";
        for (Person ppl : persons) {
            if (ppl.totalExpense() > max) {
                max = ppl.totalExpense();
                person = ppl.getWHO();
            }

        }
        System.out.println("The person with the highest expense is " + person + " with " + max + " expense");
    }



    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.listPpl();
            main.getHighestBillPrice();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}