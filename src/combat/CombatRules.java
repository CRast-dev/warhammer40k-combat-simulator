package combat;

import model.Model;
import model.Weapon;
import util.Dice;

/**
 * Core combat rules to be used during a phase.
 * responsible for hit, wound, saving rolls and damage calculation
 */
public class CombatRules {
    public static int rollHits(Weapon weapon) {
        int hitcount = 0;
        for (int i = 0; i < weapon.getAttacks(); i++) {
            if (Dice.roll(6) >= weapon.getBallisticSkill()) {
                hitcount++;
            }
        }
        return hitcount;
    }

    /**
     * Method to determine the Target to roll for the calculateWounds method
     * @param strength strength of the attacking weapon
     * @param toughness toughness characteristic of the defending model
     * @return Integer of the number to be rolled for a succenssful wound
     */
    private static int compareStrengthToToughness(int strength, int toughness) {
        int targetToWound = 0;
        if(strength >= toughness * 2){
            targetToWound = 2;
        } else if (strength > toughness) {
            targetToWound = 3;
        }else if (strength == toughness){
            targetToWound = 4;
        } else if (strength * 2 <= toughness){
            targetToWound = 6;
        } else{
            targetToWound = 5;
        }
        return targetToWound;
    }

    /**
     * Method to roll for the amount of wounds caused by a number of hits
     * @param numberOfHits Amount of hits to be evaluated for wounds
     * @param strength strength of the attacking weapon
     * @param toughness toughness of the defending model
     * @return Number of Wounds inflicted by the hits.
     */
    private static int calculateWounds(int numberOfHits, int strength, int toughness) {
        int targetToWound = compareStrengthToToughness(strength, toughness);
        int woundCount = 0;
        for(int i = 0; i < numberOfHits; i++){
            if(Dice.roll(6) >= targetToWound){
                woundCount++;
            }
        }
        return woundCount;
    }

    /**
     * Method to determine whether a save or invulnerable save is to be used
     * @param ap ap characteristic of the attacking weapon
     * @param defender defending model.Model
     * @return The target roll for a successful save
     */
    private static int choosingSavingThrow(int ap, Model defender){
        int modifiedSave = defender.getSave() - ap;
        if(defender.getInvulnSave() == 0){
            return modifiedSave;
        }else{
            return Math.min(modifiedSave, defender.getInvulnSave());
        }
    }


    /**
     * Method to determine how many wounds are left after the saving throw step
     * @param numberOfWounds initial number of wounds to be processed
     * @param ap ap characteristic of the attacking weapon
     * @param defender defending model
     * @return Amount of Wounds that are left after the saving throws
     */
    private static int savingThrow(int numberOfWounds, int ap, Model defender) {
        int targetToSave = choosingSavingThrow(ap, defender);
        int woundCount = numberOfWounds;
        for(int i = 0; i < numberOfWounds; i++){
            if(Dice.roll(6) >= targetToSave){
                woundCount--;
            }
        }
        return woundCount;
    }

    /**
     * Method to represent the entire Wound roll phase of combat
     * @param hitCount Amount of hits to be processed for wounds
     * @param weapon attacking weapon
     * @param defender defending model
     * @return Amount of wounds that were inflicted
     */
    public static int rollWounds(int hitCount, Weapon weapon, Model defender){
        int woundCount = calculateWounds(hitCount, weapon.getStrength(), defender.getToughness());
        return woundCount;
    }

    /**
     * Method to represent rolling for Saves
     * @param woundCount Amount of wounds to roll saves for
     * @param weapon attacking weapon
     * @param defender defending model
     * @return Amount of unsaved Wounds.
     */
    public static int rollSaves(int woundCount, Weapon weapon, Model defender){
        return savingThrow(woundCount, weapon.getAp(), defender);
    }

    /**
     * Method to represent the damage roll step of combat
     * @param wounds wounds to be evaluated for damage
     * @param damage damage characteristic of the attacking weapon
     * @return Amount of damage done
     */
    public static int calculateDamage(int wounds, int damage){
        //TODO dice-dependant weapon damage (2d6 etc)
        return wounds * damage;
    }
}
