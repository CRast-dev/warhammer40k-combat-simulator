package com.warhammer.statistics;

import com.warhammer.result.BattleResult;

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
    public double getAverageAttackerHits(){
        return (double) totalAttackerHits / simulations;
    }
    public double getAverageDefenderHits(){
        return (double) totalDefenderHits / simulations;
    }
    public double getAverageAttackerWounds(){
        return (double) totalAttackerWounds / simulations;
    }
    public double getAverageDefenderWounds(){
        return (double) totalDefenderWounds / simulations;
    }
    public double getAverageAttackerUnsavedWounds(){
        return (double) totalAttackerUnsavedWounds / simulations;
    }
    public double getAverageDefenderUnsavedWounds(){
        return (double) totalDefenderUnsavedWounds / simulations;
    }
    public double getAverageAttackerDamage(){
        return (double) totalAttackerDamage / simulations;
    }
    public double getAverageDefenderDamage(){
        return (double) totalDefenderDamage / simulations;
    }
    public double getAverageAttackerDestroyedModels(){
        return (double) totalAttackerDestroyedModels / simulations;
    }
    public double getAverageDefenderDestroyedModels(){
        return (double) totalDefenderDestroyedModels / simulations;
    }

    public int getTotalAttackerSuccessfulCharges() {
        return totalAttackerSuccessfulCharges;
    }

    public int getTotalDefenderSuccessfulCharges() {
        return totalDefenderSuccessfulCharges;
    }
}
