/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3nesbittr;

/**
 *
 * @author Ryan
 */
public abstract class Product {
    final String productID;
    final int year;
    final double price;
    final String name;

    Product (String productID, int year, double price, String name) throws Exception {
        if(productID.length() != 6) {
            throw new Exception("Product Not Created: Incorrect ID");
        } else if(year < 1000 || year >= 10000) {
            throw new Exception("Product Not Created: Incorrect Year");
        } else if(price < 0) {
            throw new Exception("Product Not Created: Price below zero");
        } else {
            this.productID = productID;
            this.year = year;
            this.price = price;
            this.name = name;
        }
    }

    /**
     * @return ID of item (string)
     */
    public abstract String getID();

    /**
     * @return ID of item (string)
     */
    public abstract int getYear();

    /**
     * @return ID of item (string)
     */
    public abstract double getPrice();

    /**
     * @return ID of item (string)
     */
    public abstract String getName();

    /**
     * displays item for search
     * @return string for data dump
     */
    public abstract String dataDump();

    /**
     * returns false in case of non book
     * @return false, is overridden by Book
     */
    public abstract boolean isBook();

    /**
     * is overridden by Book and Electronics
     * @return the output to be printed to file
     */
    public String printOutput() {
       return("productID = " + this.getID() + "\nname = " + this.getName() + "\nprice = " + this.getPrice() + "\nyear = " + this.getYear());
    }
}