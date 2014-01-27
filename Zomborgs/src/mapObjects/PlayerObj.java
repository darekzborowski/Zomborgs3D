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
public class PlayerObj extends MapObject {

    Player player;
    
    public PlayerObj(Player player) {
        this.player = player;
    }

    @Override
    public boolean playerInteract(Player player, int door) {
        return true;
    }

    @Override
    public boolean monsterInteract(Monster monster) {
        CombatEngine combat = new CombatEngine(player, monster);
        if (combat.mainLoop()) {
            player.reduceXP(monster.getXp());
            monster = null;
            return false;
        }
        CombatInterface ci = new CombatInterface();
        ci.loseGame();
        return true;
    }

    @Override
    public char getSmbol() {
        return 'P';
    }
}
