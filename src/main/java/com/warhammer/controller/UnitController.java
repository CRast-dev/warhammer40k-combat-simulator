package com.warhammer.controller;

import com.warhammer.model.UnitSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.warhammer.repository.UnitRepository;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UnitController {

    @GetMapping("/units")
    public List<UnitSummary> getUnits() throws SQLException {
        return UnitRepository.findAll();
    }

    @GetMapping("/units/{id}")
    public UnitSummary getUnit(@PathVariable int id) throws SQLException {
        return new UnitSummary(UnitRepository.getUnitByID(id));
    }

}



