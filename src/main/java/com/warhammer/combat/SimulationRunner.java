package com.warhammer.combat;
import com.warhammer.model.Unit;
import com.warhammer.statistics.SimulationStatistics;
import org.springframework.stereotype.Service;
import com.warhammer.result.BattleResult;

@Service
public class SimulationRunner {
    private final CombatSimulator combatSimulator;
    public SimulationRunner(CombatSimulator combatSimulator) {
        this.combatSimulator = combatSimulator;
    }
    public SimulationStatistics runSimulations(Unit attacker, Unit defender, CombatOptions options, int runs){
        SimulationStatistics statistics = new SimulationStatistics();
        for(int i = 0; i < runs; i++){
            //creating deep copies so the modelcounts are "reset" each simulation
            CombatOptions optionsCopy = new CombatOptions(options);
            Unit attackerCopy = new Unit(attacker);
            Unit defenderCopy = new Unit(defender);
            BattleResult battleResult = combatSimulator.simulateBattle(attackerCopy, defenderCopy, optionsCopy);
            statistics.record(battleResult);
        }
        return statistics;
    }

}
