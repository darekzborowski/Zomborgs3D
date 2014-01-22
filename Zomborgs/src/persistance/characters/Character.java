/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance.characters;

/**
 *
 * @author Dariusz
 */
public class Character {

    private String name;
    private int mHealth;
    private int attack;
    private int damage;
    private int defence;
    private int curHealth;

    public Character(String name, int maxHealth, int attack, int damage, int defence) {
        this.name = name;
        this.mHealth = maxHealth;
        this.attack = attack;
        this.damage = damage;
        this.defence = defence;
        this.curHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmHealth() {
        return mHealth;
    }

    public void setmHealth(int mHealth) {
        this.mHealth = mHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(int curHealth) {
        this.curHealth = curHealth;
    }
}
