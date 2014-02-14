/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zomborgs;

import engine.GameEngine;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Dariusz
 */
public class Zomborgs {

    /**
     * @param args the command line arguments
     */
    
    void aboutGame() {
        
    }
    
    void gameInformation() {
        
    }
    
    private static void startGame() throws IOException {
        String playerName;
        int playerClass = 0;
        
        Scanner scan = new Scanner(System.in);
        String output;
        
        
        
        String message;
        
        System.out.println("...");
        System.in.read();
        System.out.println("...");
        System.in.read();
        
        System.out.println("Where am I.... Where did everyone go....");
        System.in.read();
        
        while(true) {
            System.out.print("Name: ");
            playerName = scan.next();
            
            if(playerName.equals("") || playerName.length() > 10) {
                System.out.println("Player name was either null or too long!");
                continue;
            } else
                break;
        }
        
        System.out.println("Choose your class: ");
        playerClass = scan.nextInt();
        
        System.out.println("Player name: " + playerName + " Player class: " + playerClass);
        
        System.in.read();
        
        GameEngine engine = new GameEngine(playerName, playerClass);
        engine.mainLoop();
        
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Starting the game...");
        startGame();
        
    }
}
