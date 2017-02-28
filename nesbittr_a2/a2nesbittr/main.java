/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2nesbittr;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author Ryan
 */
public class main {
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String filename;
        if(args.length != 1) {
            System.out.println("Please include an input file");
            return;
        } else {
            filename = System.getProperty("user.dir") + "/" + args[0];
        }
        
        eStoreSearch store = new eStoreSearch();
        
        Scanner scan = new Scanner(System.in);
        String input = "";
        
        System.out.println("Loading... " + filename);
        store.fileInput(filename, store.getArrayList(), store.getHm());
        
        while(!input.toLowerCase().contains("q")) {
            String searchID;
            String searchKey;
            String searchYear;
            String junk;
            
            System.out.println("Please either 'add', 'search', or 'quit/q'");
            input = scan.nextLine();
            if("".equals(input)) {
                input = " ";
            }
            switch (input.toLowerCase().charAt(0)) {
                case 'a':
                    System.out.println("Please type in 'B' for a book or 'E' for electronic item");
                    junk = scan.nextLine();
                    switch (junk.toUpperCase()) {
                        case "E":
                            System.out.println("Electronic");
                            store.createProduct(store.getArrayList(), "elec", store.getHm());
                            break;
                        case "B":
                            System.out.println("BOOK");
                            store.createProduct(store.getArrayList(), "book", store.getHm());
                            break; 
                        default:
                            System.out.println("Incorrect Input, returning to main menu");
                            break;
                    }
                    break;
                case 's':
                    System.out.println("Please type in a productID");
                    searchID = scan.nextLine();
                    System.out.println("Please type in a keyword");
                    searchKey = scan.nextLine();
                    System.out.println("Please type in a year or range");
                    searchYear = scan.nextLine();
                    eStoreSearch.search(searchID, searchKey, searchYear, store.getArrayList(), store.getHm());
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid command. Try 'add', 'search', or 'quit/q'\n");
                    break;
            }
        }
        store.fileOutput(store.getArrayList());
    }
}
