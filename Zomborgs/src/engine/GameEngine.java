/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import InterfaceOld.CombatInterface;
import InterfaceOld.MapInterface;
import java.util.ArrayList;
import java.util.List;
import mapObjects.*;
import mapObjects.MapInfo.MonsterInfo;
import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class GameEngine {

    Player player;
    List<Monster> monsters;
    List<GameMap> mapList;
    List<List<MapObject>> theMap;
    int curMap;
    public List<Monster> listOfLiveMonsters = new ArrayList<>();

    public GameEngine(String name, int choice) {
        this.player = Player.factory(choice, name);
        mapList = GameMap.factory("zomborg.txt");
        curMap = 0;
        newMap(1);
    }

    public boolean mainLoop() {
        MapInterface inter = new MapInterface();

        while (true) {
            inter.displayMap(mapList.get(curMap).getTheMap());
            String input;
            char choice;
            boolean continueMove = true;

            System.out.println(player.getLocX() + " " + player.getLocY());
            int[] newPlayerLoc = {player.getLocX(), player.getLocY()};

            inter.displayMessage("Your turn: ");
            input = inter.userInput();

            switch (input.charAt(0)) {
                case 'w':
                    newPlayerLoc[1] -= 1;
                    break;
                case 's':
                    newPlayerLoc[1] += 1;
                    break;
                case 'a':
                    newPlayerLoc[0] -= 1;
                    break;
                case 'd':
                    newPlayerLoc[0] += 1;
                    break;
                case 'm':
                    if (!accessMenu()) {
                        inter.displayMap(theMap);
                        continueMove = false;
                    }
                    break;
            }
            if (continueMove) {
                int door = 0;
                if (theMap.get(newPlayerLoc[1]).get(newPlayerLoc[0]).playerInteract(player, door)) {
                    if (door != 0) {
                        player.setLocX(newPlayerLoc[0]);
                        player.setLocY(newPlayerLoc[1]);
                        newMap(door);
                        continue;
                    }

                    theMap.get(newPlayerLoc[1]).add(newPlayerLoc[0], new PlayerObj(player));
                    theMap.get(player.getLocY()).add(player.getLocX(), new MapObject());

                    player.setLocX(newPlayerLoc[0]);
                    player.setLocY(newPlayerLoc[1]);
                }
                monsterTurn();
            }
            return true;
        }

    }

    void newMap(int mapNum) {

        if (mapNum == -1) {
            finalBoss();
        }

        if (curMap != 0) {
            for (Monster monster : monsters) {
                if (monster.getCurHealth() <= 0) {

                   // mapList.get(curMap - 1).killMonster(monster);

                    monsters.remove(monster);
                }
            }
        }
    }

    boolean accessMenu() {
        return false;
    }

    boolean accessInventory() {
        return false;
    }

    boolean accessItem() {
        return false;
    }

    void monsterTurn() {

        for (Monster monster : listOfLiveMonsters) {
            if (monster.getCurHealth() <= 0) {
                continue;
            }
            Monster tempMonster = (Monster) monster.clone();
            int[] newMonsterLoc = {monster.getLocX(), monster.getLocY()};
            int xDist, yDist, distance, direction;

            xDist = Math.abs(player.getLocX() - monster.getLocX());
            yDist = Math.abs(player.getLocY() - monster.getLocY());
            distance = xDist + yDist;

            if (distance <= 5) {
                if (xDist >= yDist) {
                    if (monster.getLocX() > player.getLocX()) {
                        direction = 3;
                    } else {
                        direction = 4;
                    }
                } else {
                    if (monster.getLocY() > player.getLocY()) {
                        direction = 1;
                    } else {
                        direction = 2;
                    }
                }
            } else {
                direction = (int) Math.random() % 4 + 1;
            }
            switch (direction) {
                case 1:
                    newMonsterLoc[1] -= 1;
                    break;
                case 2:
                    newMonsterLoc[1] += 1;
                    break;
                case 3:
                    newMonsterLoc[0] -= 1;
                    break;
                case 4:
                    newMonsterLoc[0] += 1;
                    break;
                default:
                    ;
            }
            if (theMap.get(newMonsterLoc[1]).get(newMonsterLoc[0]).monsterInteract(monster)) {
                theMap.get(newMonsterLoc[1]).add(newMonsterLoc[0], new MonsterObj(monster));
                theMap.get(monster.getLocY()).add(monster.getLocX(), new MapObject());

                monster.setLocX(newMonsterLoc[0]);
                monster.setLocY(newMonsterLoc[1]);
            } else {
                if (monster == null) {
                    System.out.println("You win!");
                    theMap.get(tempMonster.getLocY()).set(tempMonster.getLocX(), new MapObject());
                }
            }
        }
    }

    void finalBoss() {
        Monster boss = Monster.factory(4);
        CombatInterface inter = new CombatInterface();

        CombatEngine combat = new CombatEngine(player, boss);

        if (combat.mainLoop()) {
            inter.winGame();
        } else {
            inter.loseGame();
        }
    }
}
