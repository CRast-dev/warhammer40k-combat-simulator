package com.warhammer;

import com.warhammer.combat.AllocationStrategy;
import com.warhammer.combat.CombatOptions;
import com.warhammer.combat.CombatSimulator;
import com.warhammer.combat.SimulationRunner;
import com.warhammer.model.Unit;
import com.warhammer.model.UnitSummary;
import com.warhammer.view.SimulationPrinter;
import com.warhammer.repository.UnitRepository;
import com.warhammer.statistics.SimulationStatistics;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int startingDistance = 16;
        int simulationRuns = 10;
        int ATTACKER_ID = 3;
        int DEFENDER_ID = 2;
        CombatOptions options = new CombatOptions(AllocationStrategy.WORST_SAVE_FIRST, AllocationStrategy.WORST_SAVE_FIRST, startingDistance, false, true);
        CombatSimulator combatSimulator = new CombatSimulator();
        Unit attacker = null;
        try {
            attacker = UnitRepository.getUnitByID(DEFENDER_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Unit defender = null;
        try {
            defender = UnitRepository.getUnitByID(ATTACKER_ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Outputting all available Units
        try {
            List<UnitSummary> unitSummaries = UnitRepository.findAll();
            System.out.println("Supported units: ");
            for (UnitSummary unit : unitSummaries) {
                System.out.println(unit.getId() + " - " + unit.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //BattleResult battle = combatSimulator.simulateBattle(attacker,defender, options);
        //BattlePrinter.print(battle, PrintLevel.SUMMARY);
        SimulationRunner simulationRunner = new SimulationRunner(combatSimulator);
        SimulationStatistics statistics = simulationRunner.runSimulations(attacker, defender, options, simulationRuns);
        SimulationPrinter.print(statistics, attacker, defender);


    }
}
