package org.example;

public class Expense {
    private String WHAT;
    private double AMOUNT;


    public Expense(String WHAT, double AMOUNT) {
        this.WHAT = WHAT;
        this.AMOUNT = AMOUNT;
    }

    public String getWHAT() {
        return WHAT;
    }

    public void setWHAT(String WHAT) {
        this.WHAT = WHAT;
    }

    public double getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(double AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "WHAT='" + WHAT + '\'' +
                ", AMOUNT=" + AMOUNT +
                '}';
    }
}
