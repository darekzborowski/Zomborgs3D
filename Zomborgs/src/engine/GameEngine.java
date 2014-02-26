/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import InterfaceOld.CombatInterface;
import InterfaceOld.MapInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mapObjects.*;
import mapObjects.MapInfo.DoorInfo;
import mapObjects.MapInfo.MonsterInfo;
import persistance.characters.Monster;
import persistance.characters.Player;

/**
 *
 * @author Dariusz
 */
public class GameEngine {

    Player player;
    List<Monster> monsters = new ArrayList<>();
    List<GameMap> mapList;
    List<List<MapObject>> theMap;
    int curMap;

    public GameEngine(String name, int choice) {
        this.player = Player.factory(choice, name);
        mapList = GameMap.factory("zomborg.txt");
        curMap = 0;
        newMap(1);
    }

    public boolean mainLoop() {
        MapInterface inter = new MapInterface();

        while (true) {
            inter.displayMap(theMap);
            String input;
            boolean continueMove = true;

            System.out.println(player.getLocX() + " " + player.getLocY());
            int[] newPlayerLoc = {player.getLocX(), player.getLocY()};
            
            while(true) {
                inter.displayMessage("Your turn: ");
                input = inter.userInput();
                if(input.equals("w") || input.equals("s") || input.equals("a") ||
                        input.equals("d") || input.equals("m")) {
                    break;
                } else {
                    inter.displayMessage("Wrong input!");
                    continue;
                }
            }

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

                    theMap.get(newPlayerLoc[1]).set(newPlayerLoc[0], new PlayerObj(player));
                    theMap.get(player.getLocY()).set(player.getLocX(), new MapObject());

                    player.setLocX(newPlayerLoc[0]);
                    player.setLocY(newPlayerLoc[1]);
                }
                monsterTurn();
            }
        }

    }

    void newMap(int mapNum) {

        if (mapNum == -1) {
            finalBoss();
        }
        
        if (curMap != 0) {
            while (true) { //DeleteMonsters:
                int it = 0;
                for (Monster monster : monsters) {
                    if (monster.getCurHealth() <= 0) {

                        mapList.get(curMap - 1).killMonster(it);
                        monsters.remove(monster);
                    } else {
                        it++;
                    }
                }
                break;
            }
            List<MonsterInfo> oldMonsterInfo = mapList.get(curMap - 1).getZomLocs();
            int monsterNum = 0;

            for (Monster monster : monsters) {
                oldMonsterInfo.get(monsterNum).setLocY(monster.getLocY());
                oldMonsterInfo.get(monsterNum).setLocX(monster.getLocX());
                monsterNum++;
            }
        }

        int[] newPlayerLoc = {player.getLocY(), player.getLocX()};

        theMap = mapList.get(mapNum - 1).getTheMap();

        List<DoorInfo> doorInfo = mapList.get(mapNum - 1).getDoorLocs();

        for (DoorInfo doors : doorInfo) {
            if (doors.getNextMap() == curMap) {
                newPlayerLoc[0] = doors.getLocY();
                newPlayerLoc[1] = doors.getLocX();

                final int dimX = mapList.get(mapNum - 1).getDimX();
                final int dimY = mapList.get(mapNum - 1).getDimY();

                if (newPlayerLoc[0] == 0) {
                    newPlayerLoc[0] += 1;
                } else if (newPlayerLoc[0] == dimY - 1) {
                    newPlayerLoc[0] -= 1;
                } else if (newPlayerLoc[1] == 0) {
                    newPlayerLoc[1] += 1;
                } else if (newPlayerLoc[1] == dimX - 1) {
                    newPlayerLoc[1] -= 1;
                }

                player.setLocX(newPlayerLoc[1]);
                player.setLocY(newPlayerLoc[0]);
                break;

            }
        }
        
        MapObject pObj = new PlayerObj(player);
        theMap.get(player.getLocY()).set(player.getLocX(), pObj);
        List<MonsterInfo> monstInfo = mapList.get(mapNum - 1).getZomLocs();
        
        if(monsters != null) {
            monsters.clear();
        }
        
        for(int i=0; i<monstInfo.size(); i++) {
            Monster tempMonster = Monster.factory(monstInfo.get(i).getDifficulty());
            
            tempMonster.setLocX(monstInfo.get(i).getLocX());
            tempMonster.setLocY(monstInfo.get(i).getLocY());
            monsters.add(tempMonster);
            theMap.get(tempMonster.getLocY()).set(tempMonster.getLocX(), new MonsterObj(tempMonster));
        }
        
        curMap = mapNum;
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

        for (Monster monster : monsters) {
            Random random = new Random();
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
                direction = Math.abs(random.nextInt()) % 4 + 1;
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
                theMap.get(newMonsterLoc[1]).set(newMonsterLoc[0], new MonsterObj(monster));
                theMap.get(monster.getLocY()).set(monster.getLocX(), new MapObject());

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
