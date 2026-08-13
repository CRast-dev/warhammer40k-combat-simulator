package model;
import util.Dice;

public class Damage {

    private int flatDamage;
    private int diceCount;
    private int diceSides;

    public int damageOutcome(Damage weaponDamageCharacteristic){
        int flatdamage = weaponDamageCharacteristic.getFlatDamage();
        int diceDamage = 0;
        for(int i = 0; i < weaponDamageCharacteristic.getDiceCount(); i++){
            //TODO UNSAFE CALL DUE TO GETDICESIDES POTENTIALLY BEING 0!!!
            if(weaponDamageCharacteristic.getDiceSides() > 0){
                diceDamage += Dice.roll(weaponDamageCharacteristic.getDiceSides());
            }else{
                diceDamage = 0;
            }
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
