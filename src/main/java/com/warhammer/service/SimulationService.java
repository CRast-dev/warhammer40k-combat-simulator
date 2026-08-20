package com.warhammer.service;

import com.warhammer.combat.CombatOptions;
import com.warhammer.combat.SimulationRunner;
import com.warhammer.model.Unit;
import org.springframework.stereotype.Service;
import com.warhammer.repository.UnitRepository;
import com.warhammer.statistics.SimulationStatistics;
import com.warhammer.dto.*;

import java.sql.SQLException;

@Service
public class SimulationService {
    private final SimulationRunner simulationRunner;

    public SimulationService(SimulationRunner simulationRunner) {
        this.simulationRunner = simulationRunner;
    }
    public SimulationStatistics runSimulation(SimulationRequestDTO request) throws SQLException{
        Unit attacker = UnitRepository.getUnitByID(request.attackerID());
        Unit defender = UnitRepository.getUnitByID(request.defenderID());
        CombatOptions options = new CombatOptions(request.attackerStrategy(), request.defenderStrategy(), request.distance(),
                                                    request.attackerCharge(), request.defenderCharge());
        return simulationRunner.runSimulations(attacker, defender, options, request.runs());
    }
}
