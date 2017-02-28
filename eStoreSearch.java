/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3nesbittr;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class eStoreSearch {

    private final ArrayList<Product> productList;
    private final HashMap<String, List<Integer>> hm;
    private final String filename;

    eStoreSearch(String filename) {
        this.productList = new ArrayList <> ();
        this.hm = new HashMap<>();
        this.filename = filename;
}
    /**
     * this function uses the search terms to match items, and prints them
     * @param searchID
     * @param searchKey
     * @param searchYear
     * @param hm
     * @param productList
     * @return string of product matched
     */
    public String search(String searchID, String searchKey, String searchYear, ArrayList <Product> productList, HashMap<String, List<Integer>> hm) {
        String str;
        List<Integer> hash = null;
        String [] splitSearch = searchKey.toLowerCase().split(" +");
        for(int i = 0; i < splitSearch.length; ++i) {
            
            if(hash == null) {
                hash = hm.get(splitSearch[i]);
            } else {
                hash.retainAll(hm.get(splitSearch[i]));
            }
        }
        str = "Products Matched:\n";
        
        for(Product product: productList) {
            int flag = 1;               //flag set to determine matches
                                        //default is set too no match
            if("".equals(searchKey)) {
                flag = 0;
            } else if(hash == null) {
                flag = 1;
            } else {
                for(Integer j: hash) {
                    if(productList.indexOf(product) == j) {
                        flag = 0;       //set to zero if found in name
                    }
                }
            }
            if(!(searchID.contentEquals("")) && !(searchID.contentEquals(product.getID()))) {
               
                flag++;                      //if searchID != "" and doesnt equal electronic flag is raised
            }
            if(checkYear(searchYear, product.getYear()) != 0) {
                flag++;                     //calls a functions that returns 0 if year is in range
            }                               //else if adds to the flag
   
            if(flag == 0) {                 //if all of the flags havent been tripped it will print
                str = str + "\n" + product.dataDump();
            }
        }
        return str;
    }

    /**
     * checks if the year is within the range, equal to the range, or not in the range
     * @param searchYear
     * @param year
     * @return 0 for found, 1 for not found
     */
    public static int checkYear(String searchYear, int year) {
        String yearString = Integer.toString(year);
        int firstYear;
        int lastYear;

        if(searchYear.contains(yearString)) {
            return(0);          //if the search string contains the year return 0
        }

        if("-".equals(searchYear)) {
            return(0);          //if the search string is blank, return 0
        }

        String[] split = searchYear.split("-");

        if(searchYear.charAt(searchYear.length() - 1) == '-') {
            firstYear = Integer.parseInt(split[0]);
            lastYear = 9999;
            if(year >= firstYear && year <= lastYear) {
                return(0);      //if the last char is a -, check if year is above year before
            }
        } else if(searchYear.contains("-")) {
            if("".equals(split[0])) {
                firstYear = 0;  //if first split is blank, first year is set to 0
            } else {
                firstYear = Integer.parseInt(split[0]); //else its set to what it was
            }
            if("".equals(split[1])) {
               lastYear = 9999; //sets last year to highest range if its blank
            } else {
                lastYear = Integer.parseInt(split[1]);  //or else set to given upper range
            }
            if(year >= firstYear && year <= lastYear) {
                return(0); //if year is in between first and last year, return 0
            }
        }
        return(1); //if 0 has not been returned, it will get here indicating no match, return 1
    }
    
    /**
     *
     * @param productList
     * @param hm
     * @param id
     * @param name
     * @param year
     * @param price
     * @param author
     * @param pub
     * @return string displaying status
     */
    public String createBook(ArrayList <Product> productList, HashMap<String, List<Integer>> hm, String id, String name, String year, String price, String author, String pub) {
    
        for(Product p: productList) {
            if(id.equals(p.getID())) {
                return "Book Not Created: Duplicate ID";
            }
        }
        Product b;
        try {
            b = new Book(id, Integer.parseInt(year), Float.parseFloat(price), name, author, pub);
        } catch (Exception ex) {
            return ex.toString();
        }
        productList.add(b);
        String nameKey [] = name.split(" ");
        for(int i = 0; i < nameKey.length; ++i) {
            List<Integer> values = new ArrayList<>();
            nameKey[i] = nameKey[i].toLowerCase();
            if(hm.containsKey(nameKey[i]) == true) {
                if(hm.get(nameKey[i]) != null) {
                    values = hm.get(nameKey[i]);
                }
            }
            values.add(productList.indexOf(b));
            hm.put(nameKey[i], values);
        }
        return "New Book Created";
    }

    /**
     * Gets users input for each of the required values,
     * and checking to make sure they are in the valid format
     * Needs book class and constructor to work
     * @param productList
     * @param id
     * @param name
     * @param year
     * @param hm
     * @param price
     * @param maker
     * @return string displaying status
     */
    public String createElec(ArrayList <Product> productList, HashMap<String, List<Integer>> hm, String id, String name, String year, String price, String maker) {

        for(Product p: productList) {
            if(id.equals(p.getID())) {
                return "Electronic Not Created: Duplicate ID";
            }
        }
        Product e;
        try {
            e = new Electronic(id, Integer.parseInt(year), Float.parseFloat(price), name, maker);
        } catch (Exception ex) {
            return ex.toString();
        }
        productList.add(e);
        String nameKey [] = name.split(" ");
        for(int i = 0; i < nameKey.length; ++i) {
            List<Integer> values = new ArrayList<>();
            nameKey[i] = nameKey[i].toLowerCase();
            if(hm.containsKey(nameKey[i]) == true) {
                if(hm.get(nameKey[i]) != null) {
                    values = hm.get(nameKey[i]);
                }
            }
            values.add(productList.indexOf(e));
            hm.put(nameKey[i], values);
        }
        return "New Electronic Created";
    }

    /**
     * Inputs a file with name passed in, and then populates the ArrayList and HashMap
     * @param filename
     * @param productList
     * @param hm
     * @throws FileNotFoundException
     */
    public void fileInput(String filename, ArrayList<Product> productList, HashMap<String, List<Integer>> hm) throws FileNotFoundException {
        File inputFile = new File(filename);
        try (Scanner input = new Scanner(inputFile)) {
            String junk;
            char type = 0;
            String id = null;
            String name = null;
            String maker = null;
            String author = null;
            int year = 0;
            double price = 0;
            int index = 0;


            while(input.hasNextLine()) {

                String nextLine = input.nextLine();
                String [] split = nextLine.split(" ");
                switch(split[0]) {
                    case "type":
                        if(split[2].toLowerCase().contains("b")) {
                            type = 'b';
                        } else {
                            type = 'e';
                        }
                        break;
                    case "name":
                        name = nextLine.replace("name","");
                        name = name.replace(" = ", "");
                        index += 1;
                        break;
                    case "productID":
                        id = nextLine.replace("productID","");
                        id = id.replace("=", "");
                        id = id.replace(" ","");
                        index += 1;
                        break;
                    case "maker":
                        maker = nextLine.replace("maker","");
                        maker = maker.replace(" = ","");
                        index += 1;
                        break;
                    case "publisher":
                        maker = nextLine.replace("publisher","");
                        maker = maker.replace(" = ","");
                        index += 1;
                        break;
                    case "authors":
                        author = nextLine.replace("authors","");
                        author = author.replace(" = ","");
                        break;
                    case "year":
                        junk = nextLine.replace("year","");
                        junk = junk.replace("=","");
                        junk = junk.replace(" ","");
                        year = Integer.parseInt(junk);
                        index += 1;
                        break;
                    case "price":
                        junk = nextLine.replace("price","");
                        junk = junk.replace("=","");
                        junk = junk.replace(" ","");
                        price = Double.parseDouble(junk);
                        index += 1;
                        break;
                }

                if(type == 'b' && index == 5) {

                    Product b = new Book(id, year, price, name, author, maker);
                    productList.add(b);

                    if(name == null) {
                        name = "Error: Name Not Found";
                    }
                    String nameKey [] = name.split(" ");
                    for(int i = 0; i < nameKey.length; ++i) {
                        List<Integer> values = new ArrayList<>();
                        nameKey[i] = nameKey[i].toLowerCase();
                        if(hm.containsKey(nameKey[i]) == true) {
                            if(hm.get(nameKey[i]) != null) {
                                values = hm.get(nameKey[i]);
                            }
                        }
                        values.add(productList.indexOf(b));
                        hm.put(nameKey[i], values);
                    }

                } else if(type == 'e' && index == 5) {

                    Product e = new Electronic(id, year, price, name, maker);
                    productList.add(e);
                    if(name == null) {
                        name = "Error: Name Not Found";
                    }
                    String nameKey [] = name.split(" ");
                    for(int i = 0; i < nameKey.length; ++i) {
                        List<Integer> values = new ArrayList<>();
                        nameKey[i] = nameKey[i].toLowerCase();
                        if(hm.containsKey(nameKey[i]) == true) {
                            if(hm.get(nameKey[i]) != null) {
                                values = hm.get(nameKey[i]);
                            }
                        }
                        values.add(productList.indexOf(e));
                        hm.put(nameKey[i], values);
                    }
                }
                if(index == 5) {

                    type = 'a';
                    id = "";
                    name = "";
                    maker = "";
                    author = "";
                    year = 0;
                    price = 0.0;
                    index = 0;
                }
            }

            System.out.println("File succesfully loaded...");
        } catch (Exception ex) {
            System.out.println("File Not Loaded" + ex.toString());
        }
    }

    /**
     * outputs the current arrayList to a file
     * @param productList
     * @param filename
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void fileOutput(String filename, ArrayList<Product> productList) throws FileNotFoundException, UnsupportedEncodingException {    
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            for (Product p : productList) {
                if(p.isBook() == true) {
                    writer.println("type = book");
                } else {
                    writer.println("type = electronics");
                }
                writer.println(p.printOutput());
            }
        }
        System.out.println("File Successfully Written...");
        System.out.println("At " + filename);
    }

    /**
     * @return  array list
     */
    public ArrayList<Product> getArrayList() {
        return this.productList;
    }

    /**
     * @return hash map
     */
    public HashMap<String, List<Integer>> getHm() {
        return this.hm;
    }

    /**
     *
     * @return filename
     */
    public String getFileName() {
        return this.filename;
    }
}