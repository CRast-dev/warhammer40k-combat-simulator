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
    public static int compareStrengthToToughness(int strength, int toughness) {
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
    public static int calculateWounds(int numberOfHits, int strength, int toughness) {
        int targetToWound = compareStrengthToToughness(strength, toughness);
        int woundCount = 0;
        for(int i = 0; i < numberOfHits; i++){
            if(Dice.roll(6) >= targetToWound){
                woundCount++;
            }
        }
        return woundCount;
    }
    private static int choosingSavingThrow(int ap, Model defender){
        int modifiedSave = defender.getSave() - ap;
        if(defender.getInvulnSave() == 0){
            return modifiedSave;
        }else{
            return Math.min(modifiedSave, defender.getInvulnSave());
        }
    }


    public static int savingThrow(int numberOfWounds, int ap, Model defender) {
        int targetToSave = choosingSavingThrow(ap, defender);
        int woundCount = numberOfWounds;
        for(int i = 0; i < numberOfWounds; i++){
            if(Dice.roll(6) >= targetToSave){
                woundCount--;
            }
        }
        return woundCount;
    }

    public static int rollWounds(int hitCount, Weapon weapon, Model defender){
        int woundCount = calculateWounds(hitCount, weapon.getStrength(), defender.getToughness());
        woundCount = savingThrow(woundCount, weapon.getAp(), defender);
        return woundCount;
    }

    public static int calculateDamage(int wounds, int damage){
        return wounds * damage;
    }
}
