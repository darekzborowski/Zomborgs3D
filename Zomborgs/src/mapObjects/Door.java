/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class Door extends MapObject {

    private int next;

    public Door(int next) {
        this.next = next;
    }

    @Override
    public boolean playerInteract(Player player, int door) {
        door = next;
        return true;
    }

    @Override
    public boolean monsterInteract(Monster monster) {
        return false;
    }

    @Override
    public char getSmbol() {
        return ' ';
    }
}
