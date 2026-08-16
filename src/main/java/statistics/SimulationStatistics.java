package statistics;

import result.BattleResult;

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
        CombatStatistics attackerStatistics = battleResult.getAttackerStatistic();
        CombatStatistics defenderStatistics = battleResult.getDefenderStatistic();
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
        totalAttackerSuccessfulCharges += battleResult.getSuccessfulCharges(battleResult.getAttacker());
        totalDefenderSuccessfulCharges += battleResult.getSuccessfulCharges(battleResult.getDefender());
    }
}
