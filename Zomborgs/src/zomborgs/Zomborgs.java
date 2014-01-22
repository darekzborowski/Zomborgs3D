/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zomborgs;

import persistance.characters.*;
import persistance.items.ItemCreator;
import util.Utilities;

/**
 *
 * @author Dariusz
 */
public class Zomborgs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        

        
        Player player = Player.factory(1, "Hello");
        ItemCreator item = ItemCreator.getInstance();
        //System.out.println(item.test);
        System.out.println(player.getName());
        System.out.print(item.globalItemList.get(1).getName());
        player.additem(item.globalItemList.get(1));
        




    }
}
