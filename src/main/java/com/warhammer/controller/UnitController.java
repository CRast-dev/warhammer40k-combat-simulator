package com.warhammer.controller;

import com.warhammer.dto.CreateUnitRequestDTO;
import com.warhammer.dto.UnitSummary;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.warhammer.repository.UnitRepository;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UnitController {


    @PostMapping("/units/test")
    public String testCreateUnit(
            @Valid @RequestBody CreateUnitRequestDTO request) {
        return "Unit received: " + request.name();
    }


    @GetMapping("/units")
    public List<UnitSummary> getUnits() throws SQLException {
        return UnitRepository.findAll();
    }

    @GetMapping("/units/{id}")
    public UnitSummary getUnit(@PathVariable int id) throws SQLException {
        return new UnitSummary(UnitRepository.getUnitByID(id));
    }

}



