/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import InterfaceOld.CombatInterface;
import InterfaceOld.Interface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class CombatEngine extends Engine {

    Player player;
    Monster monster;
    List<String> logs = new ArrayList<>();

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

        //battle loop
        while (true) {
            boolean access = true;
            ci.displayMainCombat(player, monster, logs);
            ci.displayActions();
            while (true) {
                inter.displayMessage("What now?");
                input = inter.userInput();
                if (input.equals("1") || input.equals("2")) {
                    break;
                } else {
                    inter.displayMessage("Bad selection!");
                }
            }

            while (access) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        playerAttack();
                        break;
                    case 2:
                        access = accessInventory();
                        break;
                    default:
                        inter.displayMessage("Bad selection!");
                        break;
                }

                //access false means item consumed and monster needs to attack

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
                break;

            }
        }
    }

    public void playerAttack() {
        String message;
        int finalAttack;
        int defValue;
        int result;

        Random random = new Random();
        int chance = random.nextInt() % 20 + 1;

        finalAttack = chance + player.getAttack();
        defValue = 10 + monster.getDefence();

        if (finalAttack > defValue) {
            if (chance == 20) {
                result = monster.getCurHealth() - (player.getDamage() * 2);
                monster.setCurHealth(result);
                message = "Monster - Critical Strike!";
                logs.add(message);
            } else {
                result = monster.getCurHealth() - player.getDamage();
                monster.setCurHealth(result);
                message = "You hit monster you for " + player.getDamage() + " damage!";
                logs.add(message);
            }
        } else {
            message = "You missed!";
            logs.add(message);
        }
    }

    public void monsterAttack() {
        String message;
        int finalAttack;
        int defValue;
        int result;

        Random random = new Random();
        int chance = random.nextInt() % 20 + 1;

        finalAttack = chance + monster.getAttack();
        defValue = 10 + player.getDefence();

        if (finalAttack > defValue) {
            if (chance == 20) {
                result = player.getCurHealth() - (monster.getDamage() * 2);
                player.setCurHealth(result);
                message = "Monster - Critical Strike!";
                logs.add(message);
            } else {
                result = player.getCurHealth() - monster.getDamage();
                player.setCurHealth(result);

                message = "A monster hit you for " + monster.getDamage() + " damage!";
                logs.add(message);
            }
        } else {
            message = "Monster missed!";
            logs.add(message);
        }
    }

    private boolean accessInventory() {
        return false;
    }
}
