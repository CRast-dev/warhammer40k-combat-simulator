package combat;
import model.Unit;
import result.BattleResult;
import statistics.*;

public class SimulationRunner {
    private final CombatSimulator combatSimulator;

    public SimulationRunner(CombatSimulator combatSimulator) {
        this.combatSimulator = combatSimulator;
    }
    public SimulationStatistics runSimulations(Unit attacker, Unit defender, CombatOptions options, int runs){
        SimulationStatistics statistics = new SimulationStatistics();
        for(int i = 0; i < runs; i++){
            //creating deep copies so the modelcounts are "reset" each simulation
            Unit attackerCopy = new Unit(attacker);
            Unit defenderCopy = new Unit(defender);
            BattleResult battleResult = combatSimulator.simulateBattle(attackerCopy, defenderCopy, options);
            statistics.record(battleResult);
        }
        return statistics;
    }

}
