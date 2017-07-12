package com.example.ibrahim_01.notebookapp;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by ibrahim-01 on 12/15/2016.
 */
public class Note {


    private String productName, productDescription;
    private long productID;
    private Category category;

    private String price;


    public enum Category {MANGO, APPLE, BANANA,POMAGARANTE, TOMATO,POTATO,BEANS,CABBAGE,ONION,BROCLI,RED_CHILE}

    public Note(String productName, String productDescription,String price, Category category) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.price = price;
        this.productID = 0;

    }


    public Note(String productName, String productDescription,String price, Category category, long productID) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.price = price;
        this.productID = productID;

    }

    public String getPrice() {
        return price;
    }

    public String getproductName() {
        return productName;
    }

    public String getproductDescription() {
        return productDescription;
    }

    public long getproductID() {
        return productID;
    }

    public Category getCategory() {
        return category;
    }

    public  int getAssoicatedDrawable(){
        return  CategoryToDrawable(category);
    }

    @Override
    public String toString() {
        return "Note{" +
                "productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productID=" + productID +
                ", category=" + category +
                ", price='" + price + '\'' +
                '}';
    }


    public static int CategoryToDrawable(Category category) {


        switch (category){

            case MANGO:
                return R.drawable.mango;
            case APPLE:
                return  R.drawable.apple;
            case BANANA:
                return  R.drawable.banana;
            case POMAGARANTE:
                return R.drawable.pomagarante;
            case TOMATO:
                return  R.drawable.tomato;
            case POTATO:
                return  R.drawable.potato;
            case BEANS:
                return  R.drawable.beans;
            case CABBAGE:
                return R.drawable.cabbageicon;
            case ONION:
                return  R.drawable.onionicon;
            case BROCLI:
                return  R.drawable.brocli;
            case RED_CHILE:
                return  R.drawable.redchile;






        }
        return  R.drawable.mango;
    }


}

















