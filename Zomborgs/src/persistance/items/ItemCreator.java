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
        String delims = "[\n]";
        String[] tokens = util.file.split(delims);
        System.out.println(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            String[] singles = tokens[i].split("[ ]");
            if (i <= 9) {

                Item item = new Item(i,singles[0], Integer.parseInt(singles[1].toString().trim()));
                globalItemList.add(item);
            } else {

                Equipment eq = new Equipment(i,singles[0].toString().trim(),
                        Integer.parseInt(singles[1].toString().trim()),
                        Integer.parseInt(singles[2].toString().trim()),
                        Integer.parseInt(singles[3].toString().trim()),
                        Integer.parseInt(singles[4].toString().trim()),
                        Integer.parseInt(singles[5]) == 0 ? false : true);

                globalItemList.add(eq);
            }
        }

    }

    public static ItemCreator getInstance() {
        if (instance == null) {
            instance = new ItemCreator();
        }
        return instance;
    }
}
