/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import InterfaceOld.CombatInterface;
import InterfaceOld.Interface;
import java.util.List;
import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class CombatEngine extends Engine {

    Player player;
    Monster monster;
    List<String> logs;

    public CombatEngine(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }

    public boolean mainLoop() {
        Interface inter = new Interface();
        CombatInterface ci = new CombatInterface();

        String input;
        String message;

        message = "You are about to fight a monster!";

        logs.add(message);
        while (true) {

            ci.displayMainCombat(player, monster, logs);
            ci.displayActions();

            input = inter.userInput();
            switch (Integer.parseInt(input)) {
                case 1:
                    ci.displayActions();
                    playerAttack();
                    break;
                case 2:
                    if (!accessInventory()) {
                    }
                    break;
                default:
                    inter.displayMessage("Bad Selection");
                    break;
            }

            if (monster.getCurHealth() <= 0) {
                monster.setCurHealth(0);
                message = "You won..";
                logs.add(message);
                ci.displayMainCombat(player, monster, logs);
                inter.pressAnyKey();
                return true;
            }

            monsterAttack();

            if (player.getCurHealth() <= 0) {
                player.setCurHealth(0);
                message = "You died... The game will now exit";
                logs.add(message);
                ci.displayMainCombat(player, monster, logs);
                inter.pressAnyKey();
                return false;
            }
        }
    }

    public boolean playerAttack() {
        return false;
    }

    public boolean monsterAttack() {
        return false;
    }

    private boolean accessInventory() {
        return false;
    }
}
