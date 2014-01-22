/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.items;

/**
 * EQ is only items that are equipable
 *
 * @author Dariusz
 */
public class Equipment extends Item {

    private int attack;
    private int defence;
    private int damage;
    private boolean isWeapon;

    public Equipment(int id, String name, int health, int attack, int defence, int damage, boolean isWeapon) {
        super(id, name, health);
        this.attack = attack;
        this.defence = defence;
        this.damage = damage;
        this.isWeapon = isWeapon;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isIsWeapon() {
        return isWeapon;
    }

    public void setIsWeapon(boolean isWeapon) {
        this.isWeapon = isWeapon;
    }

    @Override
    public boolean canEquip() {
        return true;
    }
}
