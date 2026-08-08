package model;
import util.Dice;

public class Damage {
    private int diceCount;
    private int diceSides;
    private int flatDamage;
    public int damageOutcome(Damage weaponDamageCharacteristic){
        int flatdamage = weaponDamageCharacteristic.getFlatDamage();
        int diceDamage = 0;
        for(int i = 0; i < weaponDamageCharacteristic.getDiceCount(); i++){
            diceDamage += Dice.roll(weaponDamageCharacteristic.getDiceSides());
        }
        return flatdamage + diceDamage;
    }

    public Damage(int diceCount, int diceSides, int flatDamage) {
        this.diceCount = diceCount;
        this.diceSides = diceSides;
        this.flatDamage = flatDamage;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int getDiceSides() {
        return diceSides;
    }

    public int getFlatDamage() {
        return flatDamage;
    }
}
