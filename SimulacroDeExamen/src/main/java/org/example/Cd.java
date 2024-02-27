package org.example;

public class Cd {


    private String TITLE;
    private String ARTIST;
    private String COUNTRY;
    private String COMPANY;
    private double PRICE;
    private int YEAR;

    public Cd() {
    }
    public Cd (String title, String artist, String country, String company, double price, int year){
        this.TITLE = title;
        this.ARTIST = artist;
        this.COUNTRY = country;
        this.COMPANY = company;
        this.PRICE = price;
        this.YEAR = year;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public int getYEAR() {
        return YEAR;
    }

    public void setYEAR(int YEAR) {
        this.YEAR = YEAR;
    }

    @Override
    public String toString() {
        return "Cd{" +
                "TITLE='" + TITLE + '\'' +
                ", ARTIST='" + ARTIST + '\'' +
                ", COUNTRY='" + COUNTRY + '\'' +
                ", COMPANY='" + COMPANY + '\'' +
                ", PRICE=" + PRICE +
                ", YEAR=" + YEAR +
                '}';
    }
}
