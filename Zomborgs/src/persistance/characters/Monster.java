/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dariusz
 */
public class Monster extends Character implements Cloneable {

    private int xp;
    public List<Monster> listOfLiveMonsters;

    public Monster(String name, int maxHealth, int attack, int damage, int defence, int xp, int locX, int locY) {
        super(name, maxHealth, attack, damage, defence, locX, locY);
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    //depending on difficulty level, create different monsters
    //different xp and stats
    public static Monster factory(int difficulty) {
        switch (difficulty) {
            case 1:
                return new Monster("Zombie", 30, 2, 5, 1, 1, 1, 5);
            case 2:
                return new Monster("Mutant Zombie", 50, 4, 10, 3, 1, 1, 10);
            case 3:
                return new Monster("Pro Zombie", 80, 7, 20, 4, 1, 1, 20);
            case 4:
                return new Monster("Zomborg", 200, 10, 30, 10, 1, 1, 50);
        }
        //if returned null, something went wrong
        return null;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Monster.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
