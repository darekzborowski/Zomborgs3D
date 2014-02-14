/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceOld;

import java.util.List;
import persistance.characters.Player;
import persistance.items.Equipment;
import persistance.items.Item;

/**
 *
 * @author Dariusz
 */
public class MenuInterface extends Interface{

    static void displayMenu() {
        Interface inter = new Interface();

        inter.displayMessage("Your inventory\n");
        inter.displayMessage("1. Items\n");
        inter.displayMessage("2. Equipment\n");
        inter.displayMessage("3. Status\n");
        inter.displayMessage("0. Cancel\n\n");
        inter.displayMessage("8. Exit Game\n");

    }
    
    static void displayItem(Player player, List<Item> items) {
        Interface inter = new Interface();
        
        int spacer;
        
        System.out.println("=======================" + " Items " +
                "=======================");
        
        spacer = 43 - inter.intLen(player.getCurHealth()) - inter.intLen(player.getmHealth());
        
        System.out.println("| Health: " + player.getCurHealth() + "/" + player.getmHealth() + 
                setw(spacer) + "|");
        
        System.out.println("|                                                   |");
        
        for(Item item : items) {
            spacer = 37 - item.getName().length() - inter.intLen(item.getHealth());
            
            System.out.println("| " + item.getId() + ". " + item.getName() + 
                    " | Healing: " + item.getHealth() + setw(spacer) + "|");
        }
        
        System.out.println("|                                                   |");
        System.out.println("|0.Cancel" + setw(43) + "|");
        
        System.out.print("|                                                   |\n" + 
                "=====================================================\n");
                
    }
    
    static void displayInventory(Player player, List<Equipment> equipList) {
        int spacer;
        Interface inter = new Interface();
        
        System.out.println("=====================" + " Equipment " + 
                "=====================");
        System.out.println("| Backpack: " + setw(43) + "|");
        System.out.println("|                                                   |");
        
        for(Equipment eq : equipList) {
            spacer = 28 - eq.getName().length();
            
            System.out.print("| " + eq.getId() + ". Name: " + eq.getName() + "| Type: ");
            if (eq.isIsWeapon()) {
                System.out.print("Weapon"); 
            } else 
                System.out.print("Armour");
            
            System.out.println(setw(spacer)+"|");
            
            spacer = 10 - inter.intLen(eq.getAttack()) - inter.intLen(eq.getDamage()) - 
                    inter.intLen(eq.getDefence()) - inter.intLen(eq.getHealth());
            
            System.out.println("| Attack: " + eq.getAttack() + " Damage: " + eq.getDamage() + 
                    " Defence: " + eq.getDefence() + " Bonus Health: " + eq.getHealth() + setw(spacer) + "|");
            
            System.out.println("|                                                   |");            
        }
        
        System.out.println("-----------------------------------------------------");
        
        System.out.println("| Equipped Items: " + setw(37) + "|");
        System.out.println("|                                                   |");
        
        System.out.print("| Weapon : ");
        if(player.getEquipedWeapon() == null) {
            System.out.print("EMPTY" + setw(39) + "|\n");
        } else {
            spacer = 43 - player.getEquipedWeapon().getName().length();
            System.out.println(player.getEquipedWeapon().getName() + setw(spacer) + "|");
        }
        
        System.out.print("| Armour : ");
        if(player.getEquipedArmor() == null) {
            System.out.println("EMPTY" + setw(39) + "|");
        } else {
            spacer = 43 - player.getEquipedArmor().getName().length();
            System.out.println(player.getEquipedArmor().getName() + setw(spacer) + "|");
        }
        
        System.out.println("|                                                   |");
        System.out.println("| 0.Cancel" + setw(43) + "|");
        System.out.print("|                                                   |\n" +
                "=====================================================\n");
    }
    
}
