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
    private final UnitRepository unitRepository;

    public SimulationService(SimulationRunner simulationRunner, UnitRepository unitRepository) {
        this.simulationRunner = simulationRunner;
        this.unitRepository = unitRepository;
    }
    public SimulationStatistics runSimulation(SimulationRequestDTO request) throws SQLException{
        Unit attacker = unitRepository.getUnitByID(request.attackerID());
        Unit defender = unitRepository.getUnitByID(request.defenderID());
        CombatOptions options = new CombatOptions(request.attackerStrategy(), request.defenderStrategy(), request.distance(),
                                                    request.attackerCharge(), request.defenderCharge());
        return simulationRunner.runSimulations(attacker, defender, options, request.runs());
    }
}
