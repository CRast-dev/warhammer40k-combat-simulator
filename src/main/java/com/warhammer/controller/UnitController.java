package com.warhammer.controller;

import com.warhammer.dto.CreateUnitRequestDTO;
import com.warhammer.dto.UnitSummary;
import com.warhammer.service.UnitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.warhammer.repository.UnitRepository;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UnitController {

    private final UnitRepository unitRepository;
    private final UnitService unitService;

    public UnitController(UnitRepository unitRepository, UnitService unitService) {
        this.unitRepository = unitRepository;
        this.unitService = unitService;
    }

    @DeleteMapping("/units/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable int id) throws SQLException {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/units/test")
    public String testCreateUnit(
            @Valid @RequestBody CreateUnitRequestDTO request) {
        return "Unit received: " + request.name();
    }
    @PostMapping("/units")
    public void createUnit(
            @Valid @RequestBody CreateUnitRequestDTO request)
            throws SQLException {

        unitService.createUnit(request);
    }


    @GetMapping("/units")
    public List<UnitSummary> getUnits() throws SQLException {
        return unitRepository.findAll();
    }

    @GetMapping("/units/{id}")
    public UnitSummary getUnit(@PathVariable int id) throws SQLException {
        return new UnitSummary(unitRepository.getUnitByID(id));
    }

}



