package model;

/**
 * represents a single weapon.
 * Contains the name and the offensive parameters
 */
public class Weapon {
    private String name;
    private int attacks;
    private int ballisticSkill;
    private int strength;
    //ap is used in the notation of the datasheets by being negative (0, -1, -2 etc)
    private int ap;
    private Damage damage;
    private int range;

    public Weapon(String name, int attacks, int ballisticSkill, int strength, int ap, Damage damage, int range){
        this.name = name;
        this.attacks = attacks;
        this.strength = strength;
        this.ballisticSkill = ballisticSkill;
        this.ap = ap;
        this.damage = damage;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getStrength() {
        return strength;
    }

    public int getBallisticSkill() {
        return ballisticSkill;
    }

    public int getAp() {
        return ap;
    }

    public int getDamage() {
        return damage.damageOutcome(damage);
    }

    public int getRange() {
        return range;
    }
}
