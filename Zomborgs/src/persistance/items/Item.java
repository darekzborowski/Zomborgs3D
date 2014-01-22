/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.items;

/**
 * Item is only a pot that has + currhealth.
 *
 * @author Dariusz
 */
public class Item {

    private int id;
    private String name;
    private int health;

    public Item(int id, String name, int health) {
        this.id = id;
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean canEquip() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
