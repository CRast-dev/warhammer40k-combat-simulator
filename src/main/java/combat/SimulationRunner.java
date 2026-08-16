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
            BattleResult battleResult = combatSimulator.simulateBattle(attacker, defender, options);
            statistics.record(battleResult);
        }
        return statistics;
    }
}
