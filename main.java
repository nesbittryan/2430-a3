/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a3nesbittr;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Ryan
 */
public class main {

    /**
     *  MAIN, runs program
     * @param args
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        String filename;
        if(args.length != 1) {
            System.out.println("Please include an input file");
            return;
        }
        
        eStoreSearch store = new eStoreSearch(System.getProperty("user.dir") + "/" + args[0]);
        store.fileInput(store.getFileName(), store.getArrayList(), store.getHm());
        
        Window win = new Window();
        win.initWin(store);
        win.setVisible(true);
    }
}