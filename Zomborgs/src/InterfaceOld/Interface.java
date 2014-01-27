/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceOld;

import java.util.Scanner;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
