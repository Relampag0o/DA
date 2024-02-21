package org.example;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String WHO;
    private List<Week> WEEK;


    public Person(String WHO) {
        this.WHO = WHO;
        this.WEEK = new ArrayList<Week>();
    }

    public String getWHO() {
        return WHO;
    }

    public void setWHO(String WHO) {
        this.WHO = WHO;
    }

    public List<Week> getWEEK() {
        return WEEK;
    }

    public void setWEEK(List<Week> WEEK) {
        this.WEEK = WEEK;
    }

    public double getHighestExpense() {
        double max = 0;
        for (Week week : WEEK) {
            if (week.highestExpense() > max) {
                max = week.highestExpense();
            }
        }
        return max;
    }

    public double averageExpense() {
        double sum = 0;
        for (Week week : WEEK) {
            sum += week.averageExpense();
        }
        return sum / WEEK.size();
    }

    public double totalExpense() {
        double sum = 0;
        for (Week week : WEEK) {
            sum += week.totalExpense();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Person{" +
                "WHO='" + WHO + '\'' +
                ", WEEK=" + WEEK +
                '}';
    }
}
