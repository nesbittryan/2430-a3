/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2nesbittr;

/**
 *
 * @author Ryan
 */
public class Product {
    
    private final String productID;
    private final int year;
    private final double price;
    private final String name;
    
    Product (String productID, int year, double price, String name) {
        this.productID = productID;
        this.year = year;
        this.price = price;
        this.name = name;
    }
    
    /**
     * @return ID of item (string)
     */
    public String getID() {
        return this.productID;
    }
    
    /**
     * @return ID of item (string)
     */
    public int getYear() {
        return this.year;
    }

    /**
     * @return ID of item (string)
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * @return ID of item (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     * displays item for search
     * @return string for data dump
     */
    public String dataDump() {
        return (this.productID + "\n" + this.name + "\n" + this.price + "\n" + this.year + "\n");
    }

    /**
     * returns false in case of non book
     * @return false, is overridden by Book
     */
    public boolean isBook() {
        return false;
    }
    
    /**
     * is overridden by Book and Electronics
     * @return the output to be printed to file
     */
    public String printOutput() {
       return("productID = " + this.getID() + "\nname = " + this.getName() + "\nprice = " + this.getPrice() + "\nyear = " + this.getYear());
    }
}
