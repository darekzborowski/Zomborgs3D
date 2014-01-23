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

            Item item = new Item(i, singles[0], Integer.parseInt(singles[1].trim()));
            globalItemList.add(item);
            
        }
        int itemsize = globalItemList.size();
        System.out.println("Items loaded: " + itemsize);
        //load eq
        for (int i = 0; i < eq.length; i++) {
            String[] singles = eq[i].trim().split("[ ]");
            Equipment eqItem = new Equipment(i, singles[0].trim(),
                    Integer.parseInt(singles[1].trim()),
                    Integer.parseInt(singles[2].trim()),
                    Integer.parseInt(singles[3].trim()),
                    Integer.parseInt(singles[4].trim()),
                    Integer.parseInt(singles[5]) == 0 ? false : true);
            globalItemList.add(eqItem);
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
}
