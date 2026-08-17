package statistics;

import result.BattleResult;
import result.ChargeResult;

public class SimulationStatistics {
    private int simulations;
    private int totalAttackerAttacks;
    private int totalDefenderAttacks;
    private int totalAttackerHits;
    private int totalDefenderHits;
    private int totalAttackerWounds;
    private int totalDefenderWounds;
    private int totalAttackerUnsavedWounds;
    private int totalDefenderUnsavedWounds;

    private int totalAttackerDamage;
    private int totalDefenderDamage;

    private int totalAttackerDestroyedModels;
    private int totalDefenderDestroyedModels;

    private int totalAttackerSuccessfulCharges;
    private int totalDefenderSuccessfulCharges;

    public void record(BattleResult battleResult){
        simulations++;
        CombatStatistics attackerStatistics = battleResult.getStatistic(battleResult.getAttacker());
        CombatStatistics defenderStatistics = battleResult.getStatistic(battleResult.getDefender());
        totalAttackerAttacks += attackerStatistics.getAttacks();
        totalDefenderAttacks += defenderStatistics.getAttacks();
        totalAttackerHits += attackerStatistics.getHits();
        totalDefenderHits += defenderStatistics.getHits();
        totalAttackerWounds += attackerStatistics.getWounds();
        totalDefenderWounds += defenderStatistics.getWounds();
        totalAttackerUnsavedWounds += attackerStatistics.getUnsavedWounds();
        totalDefenderUnsavedWounds += defenderStatistics.getUnsavedWounds();
        totalAttackerDamage += attackerStatistics.getDamage();
        totalDefenderDamage += defenderStatistics.getDamage();
        totalAttackerDestroyedModels += attackerStatistics.getDestroyedModels();
        totalDefenderDestroyedModels += defenderStatistics.getDestroyedModels();
        totalAttackerSuccessfulCharges += attackerStatistics.getSuccessfulCharges();
        totalDefenderSuccessfulCharges += defenderStatistics.getSuccessfulCharges();

    }

    public int getSimulations() {
        return simulations;
    }
    public int getAverageAttackerAttacks(){
        return totalAttackerAttacks / simulations;
    }
    public int getAverageDefenderAttacks(){
        return totalDefenderAttacks / simulations;
    }
    public int getAverageAttackerHits(){
        return totalAttackerHits / simulations;
    }
    public int getAverageDefenderHits(){
        return totalDefenderHits / simulations;
    }
    public int getAverageAttackerWounds(){
        return totalAttackerWounds / simulations;
    }
    public int getAverageDefenderWounds(){
        return totalDefenderWounds / simulations;
    }
    public int getAverageAttackerUnsavedWounds(){
        return totalAttackerUnsavedWounds / simulations;
    }
    public int getAverageDefenderUnsavedWounds(){
        return totalDefenderUnsavedWounds / simulations;
    }
    public int getAverageAttackerDamage(){
        return totalAttackerDamage / simulations;
    }
    public int getAverageDefenderDamage(){
        return totalDefenderDamage / simulations;
    }
    public int getAverageAttackerDestroyedModels(){
        return totalAttackerDestroyedModels / simulations;
    }
    public int getAverageDefenderDestroyedModels(){
        return totalDefenderDestroyedModels / simulations;
    }

    public int getTotalAttackerSuccessfulCharges() {
        return totalAttackerSuccessfulCharges;
    }

    public int getTotalDefenderSuccessfulCharges() {
        return totalDefenderSuccessfulCharges;
    }
}
