package com.warhammer.controller;

import model.UnitSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.UnitRepository;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UnitController {

    @GetMapping("/units")
    public List<UnitSummary> getUnits() throws SQLException {
        return UnitRepository.findAll();
    }
}



