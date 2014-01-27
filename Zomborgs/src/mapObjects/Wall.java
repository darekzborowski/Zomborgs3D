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
public class Wall extends MapObject {

    public Wall() {
    }

    @Override
    public boolean playerInteract(Player player, int door) {
        return false;
    }

    @Override
    public boolean monsterInteract(Monster monster) {
        return false;
    }

    @Override
    public char getSmbol() {
        return 'X';
    }
}
