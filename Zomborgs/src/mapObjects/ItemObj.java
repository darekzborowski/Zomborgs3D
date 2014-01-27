/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import InterfaceOld.Interface;
import persistance.characters.Monster;
import persistance.characters.Player;
import persistance.items.Equipment;
import persistance.items.Item;

/**
 *
 * @author Dariusz
 */
public class ItemObj extends MapObject {

    Item item;

    public ItemObj(Item item) {
        this.item = item;
    }

    @Override
    public boolean playerInteract(Player player, int door) {
        Interface inter = new Interface();

        if (item.canEquip()) {
            Equipment equip = (Equipment) item;
            player.addToInventory(equip);
        } else {
            player.additem(item);
        }

        inter.displayMessage("You picked up: ");
        inter.displayMessage(item.getName());
        inter.displayMessage("\n");
        inter.pressAnyKey();
        return false;

    }

    @Override
    public boolean monsterInteract(Monster monster) {
        return false;
    }

    @Override
    public char getSmbol() {
        return 'I';
    }
}
