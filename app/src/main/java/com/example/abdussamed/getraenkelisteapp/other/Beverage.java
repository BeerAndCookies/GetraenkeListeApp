package com.example.abdussamed.getraenkelisteapp.other;

/**
 * Created by Abdussamed on 3/12/2017.
 *
 *
 *
 * This class is used to make a model of beverage of the GetraenkelisteApp in a Database.
 */

public class Beverage {
    private String category;    //the category of beverage is either water, softdrinks or beer.
    private String name;        //name of beverage

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    private String barcode;     //barcode of beverage
    private int price;          //price of beverage

    public Beverage(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
