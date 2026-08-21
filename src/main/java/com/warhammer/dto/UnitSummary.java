package com.warhammer.dto;

import com.warhammer.model.Unit;

public class UnitSummary {

    private final int id;
    private final String name;

    public UnitSummary(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UnitSummary (Unit unit){
        this.id = unit.getId();
        this.name = unit.getName();
    }
}
