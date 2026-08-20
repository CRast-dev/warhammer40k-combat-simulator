package com.warhammer.controller;

import com.warhammer.dto.SimulationRequestDTO;
import com.warhammer.service.SimulationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.warhammer.statistics.SimulationStatistics;

import java.sql.SQLException;


@RestController
public class SimulationController {
    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }
    @PostMapping("/simulations")
    public SimulationStatistics runSimulation(
            @RequestBody SimulationRequestDTO request) throws SQLException{
        return simulationService.runSimulation(request);
    }

}
