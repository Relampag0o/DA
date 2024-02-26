package org.example;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private int NUMBER;
    private List<Expense> EXPENSE;

    public Week(int NUMBER) {
        this.NUMBER = NUMBER;
        this.EXPENSE = new ArrayList<Expense>();
    }

    public int getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(int NUMBER) {
        this.NUMBER = NUMBER;
    }

    public List<Expense> getEXPENSE() {
        return EXPENSE;
    }

    public void setEXPENSE(List<Expense> EXPENSE) {
        this.EXPENSE = EXPENSE;
    }

    public double highestExpense() {
        double max = 0;
        for (Expense expense : EXPENSE) {
            if (expense.getAMOUNT() > max) {
                max = expense.getAMOUNT();
            }
        }
        return max;
    }


    public double averageExpense() {
        double sum = 0;
        for (Expense expense : EXPENSE) {
            sum += expense.getAMOUNT();
        }
        return sum / EXPENSE.size();
    }

    public double totalExpense() {
        double sum = 0;
        for (Expense expense : EXPENSE) {
            sum += expense.getAMOUNT();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Week{" +
                "NUMBER=" + NUMBER +
                ", EXPENSE=" + EXPENSE +
                '}';
    }
}
