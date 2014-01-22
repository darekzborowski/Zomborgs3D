/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.characters;

import java.util.ArrayList;
import java.util.List;
import persistance.items.Equipment;
import persistance.items.Item;

/**
 *
 * @author Dariusz
 */
public class Player extends Character {

    private List<Item> allItemsOnPlayer = new ArrayList<>();
    private List<Equipment> allEqOnPlayer = new ArrayList<>();
    private Equipment equipedWeapon;
    private Equipment equipedArmor;
    private int levelXP;
    private int level;

    public Player(String name, int maxHealth, int attack, int damage, int defence) {
        super(name, maxHealth, attack, damage, defence);
        this.level = 1;
        this.levelXP = 10;
        this.equipedWeapon = null;
        this.equipedArmor = null;
    }

    public void equip(Equipment eq) {
        if (eq.isIsWeapon()) {
            if (this.equipedWeapon != null) {
                this.setmHealth(this.getmHealth() - equipedWeapon.getHealth());
                this.setAttack(this.getAttack() - equipedWeapon.getAttack());
                this.setDefence(this.getDefence() - equipedWeapon.getDefence());
                this.setDamage(this.getDamage() - equipedWeapon.getDamage());
            }

            this.setmHealth(this.getmHealth() + eq.getHealth());
            this.setAttack(this.getAttack() + eq.getAttack());
            this.setDefence(this.getDefence() + eq.getDefence());
            this.setDamage(this.getDamage() + eq.getDamage());

            equipedWeapon = eq;
        } else {
            if (this.equipedArmor != null) {
                this.setmHealth(this.getmHealth() + equipedArmor.getHealth());
                this.setAttack(this.getAttack() + equipedArmor.getAttack());
                this.setDefence(this.getDefence() + equipedArmor.getDefence());
                this.setDamage(this.getDamage() + equipedArmor.getDamage());
            }

            this.setmHealth(eq.getHealth() + equipedArmor.getHealth());
            this.setAttack(eq.getAttack() + equipedArmor.getAttack());
            this.setDefence(eq.getDefence() + equipedArmor.getDefence());
            this.setDamage(eq.getDamage() + equipedArmor.getDamage());

            equipedArmor = eq;
        }
    }

    public void additem(Item item) {
        allItemsOnPlayer.add(item);
    }

    public void addToInventory(Equipment eq) {
        allEqOnPlayer.add(eq);
    }

    public void levelUp() {
        level += 1;
        levelXP = 10 * level;

        this.setmHealth(this.getmHealth() + 8);
        this.setCurHealth(this.getmHealth());
        this.setAttack(this.getAttack() + 1);
        this.setDefence(this.getDefence() + 2);
        this.setDamage(this.getDamage() + 2);
    }

    public void reduceXP(int xp) {
        this.levelXP -= xp;
        if (levelXP <= 0) {
            levelUp();
        }
    }

    public List<Item> getItemsOnPlayer() {
        return allItemsOnPlayer;
    }

    public void setItemsOnPlayer(List<Item> itemsOnPlayer) {
        this.allItemsOnPlayer = itemsOnPlayer;
    }

    public List<Equipment> getEqOnPlayer() {
        return allEqOnPlayer;
    }

    public void setEqOnPlayer(List<Equipment> eqOnPlayer) {
        this.allEqOnPlayer = eqOnPlayer;
    }

    public Equipment getEquipedWeapon() {
        return equipedWeapon;
    }

    public void setEquipedWeapon(Equipment equipedWeapon) {
        this.equipedWeapon = equipedWeapon;
    }

    public Equipment getEquipedArmor() {
        return equipedArmor;
    }

    public void setEquipedArmor(Equipment equipedArmor) {
        this.equipedArmor = equipedArmor;
    }

    public void useItem(int id, List<Item> itemList) {
        for (Item item : itemList) {
            if (item.getId() == id) {
                setCurHealth(item.getHealth() + getCurHealth());
                if (getCurHealth() > getmHealth()) {
                    setCurHealth(getmHealth());
                }
                allItemsOnPlayer.remove(item);
                return;
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static Player factory(int choice, String name) {
        System.out.println("Choice in factory: " + choice);
        switch (choice) {
            case 1:
                return new Player(name, 100, 2, 10, 2);
            case 2:
                return new Player(name, 120, 3, 8, 3);
            case 3:
                return new Player(name, 80, 4, 12, 1);
        }
        //if returned null, something went wrong
        return null;
    }
}
