package com.warhammer.dto;

import com.warhammer.combat.AllocationStrategy;

public record SimulationRequestDTO(int attackerID, int defenderID, int runs,
                                   AllocationStrategy attackerStrategy, AllocationStrategy defenderStrategy,
                                   int distance, boolean attackerCharge, boolean defenderCharge){}
