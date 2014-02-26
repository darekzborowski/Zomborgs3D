/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceOld;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dariusz
 */
public class Interface {

    public Interface() {
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void displayMessage(int imessage){
        System.out.println(imessage);
    }
    
    public String userInput(){
        Scanner scan = new Scanner(System.in);
        String output;
        output = scan.next();
        return output;        
    }

    public void pressAnyKey() {
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int intLen(int start) {
        if(start ==0) {
            return 1;
        }
        
        int end = 0;
        
        if (start < 0) {
            start *= -1;
            while(start > 0){
                start = start/10;
                end++;
            }
            return end+1;
        }
        
        while(start > 0) {
            start = start/10;
            end++;
        }
        return end;
    }
    
    
    protected static String setw(int space) {
        String out = "";
        for(int i=0; i< space; i++) {
            out += " ";
        }
        
        return out;   
    }
    
    
    
}
