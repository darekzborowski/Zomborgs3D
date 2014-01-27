/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import InterfaceOld.CombatInterface;
import engine.CombatEngine;
import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class MonsterObj extends MapObject {

    Monster monster;

    public MonsterObj(Monster monster) {
        this.monster = monster;
    }

    @Override
    public boolean playerInteract(Player player, int door) {

        CombatEngine combat = new CombatEngine(player, monster);
        if (combat.mainLoop()) {
            player.reduceXP(monster.getXp());
            monster = null;

            return true;
        }

        CombatInterface ci = new CombatInterface();
        ci.loseGame();
        return false;
    }

    @Override
    public boolean monsterInteract(Monster monster) {
        return false;
    }

    @Override
    public char getSmbol() {
        return 'M';
    }
}
