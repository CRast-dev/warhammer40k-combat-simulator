package com.warhammer.combat;

import com.warhammer.model.Model;
import com.warhammer.model.Weapon;
import com.warhammer.util.Dice;

import java.util.ArrayList;
import java.util.List;

/**
 * Core combat rules to be used during a phase.
 * responsible for hit, wound, saving rolls and damage calculation
 */
public class CombatRules {
    public static int rollHits(Weapon weapon) {
        int hitcount = 0;
        for (int i = 0; i < weapon.getAttacks(); i++) {
            if (Dice.roll(6) >= weapon.getSkill()) {
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
        //DEBUG CASE TO FORCE SHOTS TO GO THROUGH
        if(strength == -1){
            targetToWound = 0;
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
     * @param ap ap characteristic of the attacking weapon
     * @param defender defending model
     * @param saveRoll save diceroll to evaluate
     * @return boolean if the save succeeded (True) or failed (False)
     */
    public static boolean savingThrow(int ap, Model defender, int saveRoll) {
        int targetToSave = choosingSavingThrow(ap, defender);
        return saveRoll >= targetToSave;
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
     * @return List of save roll dice results as an ordered List of ascending dice results.
     */
    public static List<Integer> rollSaves(int woundCount){
        List<Integer> saveRolls = new ArrayList<>();
        for(int i = 0; i < woundCount; i++){
            saveRolls.add(Dice.roll(6));
        }
        saveRolls.sort(Integer::compareTo);
        return saveRolls;
    }
}
