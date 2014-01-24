/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.items;

import java.util.ArrayList;
import java.util.List;
import util.Utilities;

/**
 *
 * @author Dariusz
 */
public class ItemCreator {

    public List<Item> globalItemList = new ArrayList<>();
    private static ItemCreator instance = null;

    protected ItemCreator() {
        int idCounter = 0;
        Utilities util = new Utilities("items.txt");
        String delims = "[.]+";
        String[] tokens = util.file.split(delims);
        //split first by .
        System.out.println(tokens.length);

        String[] items = tokens[0].split("[\n]+");
        System.out.println(tokens[1]);
        String[] eq = tokens[1].split("[\n]+");

        //load items
        for (int i = 0; i < items.length; i++) {
            String[] singles = items[i].split("[ ]");

            Item item = new Item(idCounter, singles[0], Integer.parseInt(singles[1].trim()));
            globalItemList.add(item);
            idCounter++;

        }
        int itemsize = globalItemList.size();
        System.out.println("Items loaded: " + itemsize);
        //load eq
        for (int i = 0; i < eq.length; i++) {
            String[] singles = eq[i].trim().split("[ ]");
            Equipment eqItem = new Equipment(idCounter, singles[0].trim(),
                    Integer.parseInt(singles[1].trim()),
                    Integer.parseInt(singles[2].trim()),
                    Integer.parseInt(singles[3].trim()),
                    Integer.parseInt(singles[4].trim()),
                    Integer.parseInt(singles[5]) == 0 ? false : true);
            globalItemList.add(eqItem);
            idCounter++;
        }
        int eqsize = globalItemList.size() - itemsize;
        System.out.println("Eq Loaded: " + eqsize);

    }

    public static ItemCreator getInstance() {
        if (instance == null) {
            instance = new ItemCreator();
        }
        return instance;
    }

    public Item searchItemById(int id) {
        for (Item item : globalItemList) {
            if (item.getId() == id) {
                System.out.println("Found item " + item.getName());
                return item;
            } 
        }
        System.out.println("Item_id: " + id +" was not found in globalItemList");
        return null;
    }
    
    public void removeItemById(int id) {
        for (Item item : globalItemList) {
            if (item.getId() == id) {
                System.out.println("Removing item :" + item.getName());
                globalItemList.remove(item);
            }
        }
    }
}
