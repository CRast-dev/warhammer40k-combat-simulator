import combat.*;
import model.*;
import repository.UnitRepository;
import result.*;
import statistics.SimulationStatistics;
import view.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CombatOptions options = new CombatOptions(AllocationStrategy.WORST_SAVE_FIRST, AllocationStrategy.WORST_SAVE_FIRST, 14, false, true);
        CombatSimulator combatSimulator = new CombatSimulator();
        Unit attacker = null;
        try {
            attacker = UnitRepository.getUnitByID(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Unit defender = null;
        try {
            defender = UnitRepository.getUnitByID(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        BattleResult battle = combatSimulator.simulateBattle(attacker,defender, options);
        BattlePrinter.print(battle, PrintLevel.SUMMARY);
        //SimulationRunner simulationRunner = new SimulationRunner(combatSimulator);
        //SimulationStatistics statistics = simulationRunner.runSimulations(attacker, defender, options, 100);
        //SimulationPrinter.print(statistics, attacker, defender);


    }
}
