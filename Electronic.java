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
public class Electronic extends Product {
    private final String maker;

    Electronic(String id, int year, double price, String name, String maker) throws Exception {

       super(id, year, price, name);
       this.maker = maker;
    }

    /**
     * overrides the parent class and adds the additional attributes of Electronics
     * @return
     */
    @Override
    public String dataDump() {
        String string = this.getID() + "\n" + this.getName() + "\n" + this.getPrice() + "\n" + this.getYear() + "\n" + this.getMaker() + "\n";
        return string;
    }

    /**
     * @return maker of electronic (string)
     */
    public String getMaker() {
        return this.maker;
    }

    /**
     * prints the output with the additional attributes of Electronics
     * @return string output for file
     */
    @Override
    public String printOutput() {
        return (super.printOutput() + "\nmaker = " + this.getMaker() + "\n");
    }

    @Override
    public String getID() {
        return this.productID;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
        }

    @Override
    public boolean isBook() {
        return false;
    }
}