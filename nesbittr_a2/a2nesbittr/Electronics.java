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
public class Electronics extends Product {
  
    private final String maker;
    
    Electronics(String id, int year, double price, String name, String maker) {
            
       super(id, year, price, name);
       this.maker = maker;
    }
    
    /**
     * overrides the parent class and adds the additional attributes of Electronics
     * @return
     */
    @Override
    public String dataDump() {
        String string = super.dataDump() + this.getMaker() + "\n";
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
}
