/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceOld;

import java.util.List;
import persistance.characters.Monster;
import persistance.characters.Player;
import persistance.items.Item;

/**
 *
 * @author Dariusz
 */
public class CombatInterface extends Interface {
    
    public void displayMainCombat(Player player, Monster monster, List<String> logs) {
        Interface inter = new Interface();
        
        int spacer;
        
        spacer = 15 - player.getName().length();
        
        System.out.print("=====================================================\n"
                + "| Name : " + player.getName() + setw(spacer) + " | " + 
                "Monster: " + monster.getName());
        
        spacer = 16 - monster.getName().length();
        System.out.println(setw(spacer) + "|");
        
        spacer = 16 - intLen(player.getLevel());
        System.out.println("| Level:" + player.getLevel() + setw(spacer) + " | " + 
                setw(25) + "|");
        
        spacer = 15 - intLen(player.getDamage());
        System.out.println("| Damage:" + player.getDamage() + setw(spacer) + " | " + 
                setw(25) + "|");
        
        spacer = 14 - intLen(player.getCurHealth()) - inter.intLen(player.getmHealth());
        System.out.print("| Health: " + player.getCurHealth() + "/" +player.getmHealth() + setw(spacer) + "| ");
        
        spacer = 14 - intLen(monster.getCurHealth()) - inter.intLen(monster.getmHealth());
        System.out.println("Health: " + monster.getCurHealth() +  "/" + monster.getmHealth() + setw(spacer) + "|");
        
        System.out.println("=====================================================");
        System.out.println("|                                                   |");
        System.out.println("| Combat Log:"+setw(39) + "|");
        System.out.println("|                                                   |");
        
        int temp = 0;
        
        if(logs.size() >= 2) {
            temp = logs.size() - 2;
        } else
            temp = 0;
        
        for(int i=temp; i<logs.size(); i++) {
            spacer = 50 - logs.get(i).length();
            
            System.out.println("| " + logs.get(i) + setw(spacer) + "|");
        }
        
        System.out.println("|                                                   |");
        System.out.println("=====================================================");
        
    }
    
    public void displayItem(Player player) {
        
        System.out.println("| Inventory : " + setw(41) + "|");
        System.out.println("|                                                   |");
        int spacer=0;
        List<Item> pList = player.getItemsOnPlayer();
        
        for(Item item : pList) {
            spacer = 38 - item.getName().length();
            
            System.out.print("| " + item.getId() + ". " + item.getName() + ": " + 
                    item.getHealth() + " Health" + setw(spacer) + "|\n");
        }
        
        System.out.println("|                                                   |");
        System.out.println("| 0. Cancel" + setw(43)+"|");
        
        System.out.println("=====================================================");
    }
    
    public void displayActions() {
        System.out.println("-----------------------------------------------------");
        System.out.print("| Player Actions: " + setw(34) + "|\n");
        System.out.print("|                                                   |\n");
        System.out.print("| 1. Attack" + setw(41) + "|\n");
        System.out.print("| 2. Inventory" + setw(38) + "|\n");
        System.out.print("|                                                   |\n");
        System.out.print("-----------------------------------------------------\n");
    }
    
    public void winGame(){
        
    }

    public void loseGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
