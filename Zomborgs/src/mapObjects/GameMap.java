/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapObjects.MapInfo.*;
import persistance.characters.Monster;
import persistance.items.Item;
import persistance.items.ItemCreator;

/**
 *
 * @author Dariusz
 */
public class GameMap {

    private List<List<MapObject>> theMap = new ArrayList<>();
    private int dimX;
    private int dimY;
    private int numZomb;
    private int numItems;
    private int numDoors;
    private List<DoorInfo> doorLocs = new ArrayList<>();
    private List<MonsterInfo> zomLocs = new ArrayList<>();
    private List<ItemInfo> itemLocs = new ArrayList<>();

    GameMap(int dimX, int dimY, int numZomb, int numItems, int numDoors,
            List<DoorInfo> doorLocs,
            List<MonsterInfo> zomLocs,
            List<ItemInfo> itemLocs) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.numZomb = numZomb;
        this.numItems = numItems;
        this.numDoors = numDoors;
        this.doorLocs = doorLocs;
        this.zomLocs = zomLocs;
        this.itemLocs = itemLocs;
        initializeMap();
    }

    public static List<GameMap> factory(String fileName) {
        List<GameMap> gameMap = new ArrayList<>();
        try {
            int dimX = 0, dimY = 0, numZomb = 0, numItems = 0, numDoors = 0, mapDimX = 0, mapDimY = 0;

            FileInputStream fstream = new FileInputStream("files/" + fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            String[] tokens;
            String[] parsedItems;
            String delims = "[ ]";
            while ((strLine = br.readLine()) != null) {

                if (strLine.contains("#M")) {
                    ArrayList monsterTempInfo = new ArrayList();
                    ArrayList doorTempInfo = new ArrayList();
                    ArrayList itemTempInfo = new ArrayList();
                    tokens = strLine.split(delims);
                    mapDimX = Integer.parseInt(tokens[0].trim());
                    mapDimY = Integer.parseInt(tokens[1]);
                    numZomb = Integer.parseInt(tokens[2]);
                    numItems = Integer.parseInt(tokens[3]);
                    numDoors = Integer.parseInt(tokens[4]);

                    for (int i = 0; i < numZomb; i++) {
                        strLine = br.readLine();
                        parsedItems = strLine.split(delims);

                        dimX = Integer.parseInt(parsedItems[0]);
                        dimY = Integer.parseInt(parsedItems[1]);
                        int diff = Integer.parseInt(parsedItems[2].trim());

                        monsterTempInfo.add(new MonsterInfo(dimX, dimY, diff));
                    }
                    System.out.println("Monster size list: " + monsterTempInfo.size());

                    for (int i = 0; i < numItems; i++) {
                        strLine = br.readLine();
                        parsedItems = strLine.split(delims);

                        dimX = Integer.parseInt(parsedItems[0]);
                        dimY = Integer.parseInt(parsedItems[1].trim());

                        itemTempInfo.add(new ItemInfo(dimX, dimY));
                    }

                    for (int i = 0; i < numDoors; i++) {
                        strLine = br.readLine();
                        parsedItems = strLine.split(delims);

                        dimX = Integer.parseInt(parsedItems[0]);
                        dimY = Integer.parseInt(parsedItems[1]);
                        int nextMap = Integer.parseInt(parsedItems[2].trim());

                        doorTempInfo.add(new DoorInfo(dimX, dimY, nextMap));
                    }

                    gameMap.add(new GameMap(mapDimX, mapDimY, numZomb, numItems, numDoors, doorTempInfo, monsterTempInfo, itemTempInfo));

                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameMap;
    }

    private void initializeMap() {

        for (int i = 0; i < dimY; i++) {
            theMap.add(new ArrayList<MapObject>());
        }

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                theMap.get(i).add(j, new MapObject());
            }
        }

        for (int i = 0; i < dimX; i++) {
            theMap.get(0).set(i, new Wall());
        }
        for (int i = 1; i < dimY - 1; i++) {
            theMap.get(i).set(0, new Wall());
            theMap.get(i).set(dimX - 1, new Wall());

        }

        for (int i = 0; i < dimX; i++) {
            theMap.get(dimY - 1).set(i, new Wall());
        }

        for (int i = 0; i < numDoors; i++) {
            DoorInfo tempDoor = doorLocs.get(i);
            //theMap.remove(tempDoor.getLocY()).remove(tempDoor.getLocX);
            theMap.get(tempDoor.getLocY()).set(tempDoor.getLocX(), new Door(tempDoor.getNextMap()));

        }

        for (int i = 0; i < numItems; i++) {
            ItemInfo tempItem = itemLocs.get(i);
            Item item = ItemCreator.getInstance().globalItemList.get(i);
            // theMap.remove(tempItem.getLocY()).remove(tempItem.getLocX());
            theMap.get(tempItem.getLocY()).set(tempItem.getLocX(), new ItemObj(item));

        }

    }

    public void killMonster(int iterator) {
        zomLocs.remove(iterator);
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public int getNumZomb() {
        return numZomb;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public List<List<MapObject>> getTheMap() {
        return theMap;
    }

    public List<DoorInfo> getDoorLocs() {
        return doorLocs;
    }

    public List<MonsterInfo> getZomLocs() {
        return zomLocs;
    }

    public List<ItemInfo> getItemLocs() {
        return itemLocs;
    }
}
