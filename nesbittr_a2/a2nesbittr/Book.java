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
public class Book extends Product {
    
    private final String author;
    private final String publisher;
    
    
    
    Book(String id, int year, double price, String name, String author, String publisher) {
        
        super(id, year, price, name);
        
        this.author = author;
        this.publisher = publisher;
    }
    
    /**
     * overrides the parent class and adds the additional attributes of Book
     * @return string of data dump
     */
    @Override
    public String dataDump() {
        String string = super.dataDump() + this.getAuthor() + "\n" + this.getPub() + "\n";
        return string;
    }
    
    /**
     * @return author of book (string)
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * @return publisher of book (string)
     */
    public String getPub() {
        return this.publisher;
    }
    
    /**
     * returns true, overrides from parent 
     * @return true
     */
    @Override
    public boolean isBook() {
        return true;
    }
    
    /**
     * overrides product, but calls super and adds the additional attributes to print to file
     * @return the output to be printed to file
     */
    @Override
    public String printOutput() {
        return (super.printOutput() + "\nauthors = " + this.getAuthor() + "\npublisher = " + this.getPub() + "\n");
    }
}
