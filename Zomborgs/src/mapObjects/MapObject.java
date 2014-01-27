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
public class MapObject {

    public MapObject() {
        
    }

    public boolean playerInteract(Player player, int door){return true;};
    
    public boolean monsterInteract(Monster monster){return true;};

    public char getSmbol(){return ' ';};
}
