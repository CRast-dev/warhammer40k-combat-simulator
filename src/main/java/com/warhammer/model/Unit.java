package com.warhammer.model;

import java.util.ArrayList;
import java.util.List;
/**
 * A model.Unit of one or more Models.
 * A unit has a name along with a list of models in the unit
 */
public class Unit {
    private final int id;
    private final String name;
    private List<Model> models;

    public Unit(int id, String name,  List<Model> models) {
        this.id = id;
        this.name = name;
        this.models = models;
    }

    public String getName() {
        return name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void removeModel(Model model){
        models.remove(model);
    }
    public boolean isDestroyed() {
        return models.isEmpty();
    }

    public Unit(Unit other){
        this.id = other.id;
        this.name = other.name;
        this.models = new ArrayList<>();
        for(Model model : other.getModels()){
            this.models.add(new Model(model));
        }
    }
    public boolean isAlive(){
        return !(this.getModels().isEmpty());
    }

    public int getId() {
        return id;
    }
}

