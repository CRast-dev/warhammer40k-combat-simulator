package statistics;

import result.ChargeResult;
import result.CombatPhaseResult;

public class CombatStatistics {
    private int attacks;
    private int hits;
    private int wounds;
    private int unsavedWounds;
    private int damage;
    private int destroyedModels;
    private int successfulCharges;


    public void add(CombatPhaseResult phase) {
        attacks += phase.getTotalAttacks();
        hits += phase.getTotalHits();
        wounds += phase.getTotalWounds();
        unsavedWounds += phase.getTotalUnsavedWounds();
        damage += phase.getTotalDamage();
        destroyedModels += phase.getDestroyedModels();
    }
    public void add(ChargeResult charge){
        if(charge.isSuccessful()){
            successfulCharges++;
        }
    }


    public int getHits() {
        return hits;
    }

    public int getWounds() {
        return wounds;
    }

    public int getUnsavedWounds() {
        return unsavedWounds;
    }

    public int getDamage() {
        return damage;
    }

    public int getDestroyedModels() {
        return destroyedModels;
    }

    public int getAttacks() {
        return attacks;
    }

    public int getSuccessfulCharges() {
        return successfulCharges;
    }
}
