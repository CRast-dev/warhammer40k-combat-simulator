package model;
import util.Dice;

public class Damage {

    private int flatDamage;
    private int diceCount;
    private int diceSides;

    public int damageOutcome() {
        int flatDamage = getFlatDamage();
        int diceDamage = 0;

        for (int i = 0; i < getDiceCount(); i++) {
            diceDamage += Dice.roll(getDiceSides());
        }

        return flatDamage + diceDamage;
    }

    public Damage(int flatDamage, int diceSides, int diceCount) {
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
