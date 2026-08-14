package model;

import util.Dice;

/**
 * represents a single weapon.
 * Contains the name and the offensive parameters
 */
public class Weapon {
    private final String name;
    private final int flat_attacks;
    private final int attack_dice_sides;
    private final int attack_dice_count;
    private final int skill;
    private final int strength;
    //ap is used in the notation of the datasheets by being negative (0, -1, -2 etc)
    private final int ap;
    private final Damage damage;
    private final int range;
    private final WeaponType type;
    public enum WeaponType {
        RANGED,
        MELEE
    }

    public Weapon(String name, int attacks, int attack_dice_sides, int attack_dice_count, int skill, int strength, int ap, Damage damage, int range, WeaponType type){
        this.name = name;
        this.flat_attacks = attacks;
        this.attack_dice_sides = attack_dice_sides;
        this.attack_dice_count = attack_dice_count;
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
        int attacks = getFlat_attacks();
        int diceAttacks = 0;
        for(int i = 0; i < getAttack_dice_count(); i++){
            diceAttacks += Dice.roll(getAttack_dice_sides());
        }

        return attacks + diceAttacks;
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

    //TODO get rid of the extra Damage class and do it like with attacks
    public int getDamage() {
        return damage.damageOutcome();
    }

    public int getRange() {
        return range;
    }

    public int getFlat_attacks() {
        return flat_attacks;
    }

    public int getAttack_dice_sides() {
        return attack_dice_sides;
    }

    public int getAttack_dice_count() {
        return attack_dice_count;
    }

    public WeaponType getType() {
        return type;
    }
}
