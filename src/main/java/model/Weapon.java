package model;

/**
 * represents a single weapon.
 * Contains the name and the offensive parameters
 */
public class Weapon {
    private String name;
    private int attacks;
    private int skill;
    private int strength;
    //ap is used in the notation of the datasheets by being negative (0, -1, -2 etc)
    private int ap;
    private Damage damage;
    private int range;
    private WeaponType type;
    public enum WeaponType {
        RANGED,
        MELEE
    }

    public Weapon(String name, int attacks, int skill, int strength, int ap, Damage damage, int range, WeaponType type){
        this.name = name;
        this.attacks = attacks;
        this.strength = strength;
        this.skill = skill;
        this.ap = ap;
        this.damage = damage;
        if(type == WeaponType.MELEE){
            this.range = 0;
        }else{
            this.range = range;
        }
        this.type = type;
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

    public int getSkill() {
        return skill;
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

    public WeaponType getType() {
        return type;
    }
}
