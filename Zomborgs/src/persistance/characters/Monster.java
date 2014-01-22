/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.characters;

/**
 *
 * @author Dariusz
 */
public class Monster extends Character {

    private int xp;

    public Monster(String name, int maxHealth, int attack, int damage, int defence, int xp) {
        super(name, maxHealth, attack, damage, defence);
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
                return new Monster("Zombie", 30, 2, 5, 1, 5);
            case 2:
                return new Monster("Mutant Zombie", 50, 4, 10, 3, 10);
            case 3:
                return new Monster("Pro Zombie", 80, 7, 20, 4, 20);
            case 4:
                return new Monster("Zomborg", 200, 10, 30, 10, 60);
        }
        //if returned null, something went wrong
        return null;
    }
}
