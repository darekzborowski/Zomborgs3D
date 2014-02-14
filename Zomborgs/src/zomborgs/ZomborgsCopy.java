/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zomborgs;

import engine.GameEngine;
import java.util.List;
import java.util.Scanner;
import mapObjects.GameMap;
import persistance.characters.*;
import persistance.items.Equipment;
import persistance.items.Item;
import persistance.items.ItemCreator;

/**
 *
 * @author Dariusz
 */
public class ZomborgsCopy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Simulate zomborgs backend
        while (true) {
            //Initilize
            GameEngine ge = new GameEngine("zomborg.txt",1);
            Item tempItem = new Item();
            ItemCreator item = ItemCreator.getInstance();
            Scanner scan = new Scanner(System.in);
            int output = 1;
            System.out.println("Choose your class: ");
            int choice = scan.nextInt();
            Player player = Player.factory(choice, "PlayerName");
            System.out.println("-Player Factory chosen: " + player.toString());

            System.out.println("-Creating monsters.. ");

            for (int i = 1; i <= 4; i++) {
                Monster monster = Monster.factory(i);
                ge.listOfLiveMonsters.add(monster);
                System.out.println("Monster added to the list: " + monster.getName());
            }

            while (output != 0) {
                System.out.println("\n1. Attack\n"
                        + "2.Get Item to EQ\n"
                        + "3.Put item from inventory\n");

                output = scan.nextInt();
                switch (output) {
                    case 1:
                        System.out.println("Test Game Map");
                        List<GameMap> gm = GameMap.factory("zomborg.txt");
                        System.out.println("done");
                        break;
                    //call combat engine
                    case 2:
                        System.out.println("Find Item that is not Eq");
                        tempItem = item.searchItemById(16);

                        if (tempItem.getClass() == Item.class) {
                            System.out.println("Adding this item to list");
                            player.additem(tempItem);
                        } else if (tempItem.getClass() == Equipment.class) {
                            System.out.println("Adding this EQ to the list");
                            player.addToInventory((Equipment) tempItem);
                        }
                        break;
                    case 3:
                        System.out.println("Putting Eq");
                        if (player.getEqOnPlayer().get(0).isIsWeapon()) {
                            player.equip(player.getEqOnPlayer().get(0));
                            System.out.println("Putting " + player.getEqOnPlayer().get(0).getName()
                                    + " Weapon on player");
                        } else if (!player.getEqOnPlayer().get(0).isIsWeapon()) {
                            player.equip(player.getEqOnPlayer().get(0));
                            System.out.println("Putting " + player.getEqOnPlayer().get(0).getName()
                                    + "Armor on player");
                        }
                        break;
                    case 4:
                        System.out.println("");
                    case 5:
                        output=0;
                        break;
                }


            }
            /*            
             System.out.println("Player Status: \n"+
             player.getName() + " \n" +
             "Attack: " + player.getAttack() + " \n" +
             "CurHealth: " + player.getCurHealth() + " \n" +
             "mHealth: " + player.getmHealth() + " \n" +
             "Damage: " + player.getDamage()+ " \n" +
             "Defence: " + player.getDefence()+ " \n" +
             "EqOnPlayer: " + player.getEqOnPlayer()+ " \n" +
             "EqArmor: " +player.getEquipedArmor().getName()+ " \n" +
             "EqWep: " +player.getEquipedWeapon() + " \n" +
             "Items: " +player.getItemsOnPlayer() + " \n" +
             "Level: " +player.getLevel() + " \n");
             System.out.println("Exited");
             break; */
        }



    }
}
